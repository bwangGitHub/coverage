/*
 * Copyright 2002 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

/**
 * Tracer provides a simple way to trace the handling of a request.
 *
 * By timing likely slow points in the code you can quickly pinpoint
 * why a request is slow.
 *
 * <p>Example usage:
 * <pre>
 * Tracer.initCurrentThreadTrace(); // must be called in each Thread
 * Tracer wholeRequest = new Tracer(null, "Request " + params);
 * try {
 *   ...
 *   t = new Tracer("Database", "getName()");
 *   try {
 *     name = database.getName();
 *   } finally {
 *     t.stop();
 *   }
 *   ...
 *   t = new Tracer(null, "call sendmail");
 *   try {
 *     sendMessage();
 *   } finally {
 *     t.stop();
 *   }
 *   ...
 *   t = new Tracer("Database", "updateinfo()");
 *   try {
 *     database.updateinfo("new info");
 *   } finally {
 *     t.stop();
 *   }
 *   ...
 * } finally {
 *   if (wholeRequest.stop() > 1000) {
 *     // more than a second, better log
 *     Tracer.logAndClearCurrentThreadTrace();
 *   } else {
 *     Tracer.clearCurrentThreadTrace();
 *   }
 * }
 * </pre>
 *
 * Now slow requests will produce a report like this:
 * <pre>
 *       10.452 Start        Request cmd=dostuff
 *     3 10.455 Start        [Database] getName()
 *    34 10.489 Done   34 ms [Database] getName()
 *     3 10.491 Start        call sendmail
 *  1042 11.533 Done 1042 ms call sendmail
 *     0 11.533 Start        [Database] updateinfo()
 *     3 11.536 Done    3 ms [Database] updateinfo()
 *    64 11.600 Done 1148 ms Request cmd=dostuff
 *   TOTAL Database 2 (37 ms)
 * </pre>
 *
 * If you enabled pretty-printing by calling {@link Tracer#setPrettyPrint},
 * it will print more easily readable reports that use indentation to visualize
 * the tracer hierarchy and dynamically adjusts the padding to handle large
 * durations. Like:
 * <pre>
 *       10.452 Start        Request cmd=dostuff
 *     3 10.455 Start        | [Database] getName()
 *    34 10.489 Done   34 ms | [Database] getName()
 *     3 10.491 Start        | call sendmail
 *  1042 11.533 Done 1042 ms | call sendmail
 *     0 11.533 Start        | [Database] updateinfo()
 *     3 11.536 Done    3 ms | [Database] updateinfo()
 *    64 11.600 Done 1148 ms Request cmd=dostuff
 *   TOTAL Database 2 (37 ms)
 * </pre>
 * Pretty-printing is an application global setting and should only be called
 * in the main setup of an application, not in library code.
 *
 * Now you can easily see that sendmail is causing your problems, not
 * the two database calls.
 *
 * You can easily add additional tracing statistics to your Trace output by
 * adding additional tracing statistics. Simply add to your initialization code:
 * <pre>
 *    Tracer.addTracingStatistic(myTracingStatistic)
 * </pre>
 * where myTracingStatistic implements the {@link TracingStatistic} interface.
 * The class com.google.monitoring.tracing.TracingStatistics contains
 * several useful statistics such as CPU time, wait time, and memory usage.
 * If you add your own tracing statistics, the output is not quite as pretty,
 * but includes additional useful information.

 * <p>If a Trace is given a type (the first argument to the constructor) and
 * multiple Traces are done on that type then a "TOTAL line will be
 * produced showing the total number of traces and the sum of the time
 * ("TOTAL Database 2 (37 ms)" in our example). These traces should be
 * mutually exclusive or else the sum won't make sense (the time will
 * be double counted if the second starts before the first ends).
 *
 * <p>It is also possible to have a "silent" Tracer which does not appear
 * in the trace because it was faster than the silence threshold. This
 * threshold can be set for the for the current ThreadTrace with
 * setDefaultSilenceThreshold(threshold), or on a per-Tracer basis with
 * t.stop(threshold). Silent tracers are still counted in the type
 * totals, so these events are not completely lost.
 *
 * <p><b>WARNING:</b> This code makes a big assumption that
 * everything for a given trace is done within a single thread.
 * It uses threads to identify requests. It is fine to have multiple
 * requests traced in multiple simultaneous threads but it is not ok
 * to have any given request traced in multiple threads. (the results
 * will be scattered across reports).
 *
 * Java objects do not support destructors (as in C++) so Tracer is not robust
 * when exceptions are thrown. Each Tracer object should be wrapped in a
 * try/finally block so that if an exception is thrown, the Tracer.stop()
 * method is guaranteed to be called.
 *
 * <p>A thread must call {@link Tracer#initCurrentThreadTrace()} to enable the
 * Tracer logging, otherwise Tracer does nothing.  The requirement to call
 * {@code initCurrentThreadTrace} avoids the situation where Tracer is called
 * without the explicit knowledge of the application authors because they
 * happen to use a class in another package that uses Tracer. If {@link
 * Tracer#logCurrentThreadTrace} is called without calling {@link
 * Tracer#initCurrentThreadTrace()}, then a Third Eye WARNING message is logged,
 * which should help track down the problem.
 *
 */
final class Tracer {
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.ping();
  }

  // package-private for access from unit tests
  static final Logger logger =
      Logger.getLogger(Tracer.class.getName());
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[1]++;
  }

  /**
   * Whether pretty printing is enabled. This is intended to be set once
   * at application startup.
   */
  private static volatile boolean defaultPrettyPrint;

  /* This list is guaranteed to only increase in length.  It contains
   * a list of additional statistics that the user wants to keep track
   * of.
   */
  private static List<TracingStatistic> extraTracingStatistics =
      new CopyOnWriteArrayList<TracingStatistic>();
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[2]++;
  }

  /** Values returned by extraTracingStatistics */
  private long[] extraTracingValues;

  /** The type for grouping traces, may be null */
  private final @Nullable String type;

  /** A comment string for the report */
  private final String comment;

  /** Start time of the trace */
  private final long startTimeMs;

  /** Stop time of the trace, non-final */
  private long stopTimeMs;

  /**
   * Record our starter thread in order to trap Traces that are started in one
   * thread and stopped in another
   */
  final Thread startThread;

  /**
   * We limit the number of events in a Trace in order to catch memory
   * leaks (a thread that keeps logging events and never clears them).
   * This number is arbitrary and can be increased if necessary (though
   * if there are more than 1000 events then the Tracer is probably being
   * misused).
   */
  static final int MAX_TRACE_SIZE = 1000;
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[3]++;
  }

  /**
   * For unit testing. Can't use {@link com.google.common.time.Clock} because
   * this code is in base and has minimal dependencies.
   */
  static interface InternalClock {
    long currentTimeMillis();
  }

  /**
   * Default clock that calls through to the system clock. Can be overridden
   * in unit tests.
   */
  static InternalClock clock = new InternalClock() {
    @Override
    public long currentTimeMillis() {
      return System.currentTimeMillis();
    }
  };
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[4]++;
  }

  /**
   * Create and start a tracer.
   * Both type and comment may be null. See class comment for usage.
   *
   * @param type The type for totaling
   * @param comment Comment about this tracer
   */
  Tracer(@Nullable String type, @Nullable String comment) {
    this.type = type;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[5]++;
    this.comment = comment == null ? "" : comment;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[6]++;
    startTimeMs = clock.currentTimeMillis();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[7]++;
    startThread = Thread.currentThread();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[8]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((extraTracingStatistics.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[1]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[10]++;
      int size = extraTracingStatistics.size();
      extraTracingValues = new long[size];
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[11]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[12]++;
      int i = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[1]++;


      for (TracingStatistic tracingStatistic : extraTracingStatistics) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[1]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[2]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[3]++;
}
        extraTracingValues[i] = tracingStatistic.start(startThread);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[14]++;
        i++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[15]++;
      }

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[2]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[16]++;

    ThreadTrace trace = getThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;

    // Do nothing if the current thread trace wasn't initialized.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((trace.isInitialized()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[4]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

    // Check if we are creating too many Tracers.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((trace.events.size() >= MAX_TRACE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[5]++;
      logger.log(Level.WARNING,
                  "Giant thread trace. Too many Tracers created. "
                    + "Clearing to avoid memory leak.",
                  new Throwable(trace.toString()));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[19]++;
      trace.truncateEvents();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[20]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[6]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;

    // Check if we forgot to close the Tracers.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((trace.outstandingEvents.size() >= MAX_TRACE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[7]++;
      logger.log(Level.WARNING,
                  "Too many outstanding Tracers. Tracer.stop() is missing "
                    + "or Tracer.stop() is not wrapped in a "
                    + "try/finally block. "
                    + "Clearing to avoid memory leak.",
                  new Throwable(trace.toString()));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[22]++;
      trace.truncateOutstandingEvents();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[23]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[8]++;}

    trace.startEvent(this);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[24]++;
  }

  /**
   * Create a tracer that isn't summed as part of a total
   *
   * @param comment  Comment about this tracer
   */
  Tracer(String comment) {
    this(null, comment);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[25]++;
  }

  /**
   * Construct a tracer whose type is based on the short name of the object
   * @param object   Object to use as type name
   * @param comment  A comment
   * @return  new Tracer.
   */
  static Tracer shortName(Object object, String comment) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[9]++;
      return new Tracer(comment);

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[10]++;}
    return new Tracer(object.getClass().getSimpleName(), comment);
  }

  /**
   * Converts 'v' to a string and pads it with up to 16 spaces for
   * improved alignment.
   * @param v The value to convert.
   * @param digits_column_width The desired with of the string.
   */
  private static String longToPaddedString(long v, int digits_column_width) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[27]++;
    int digit_width = numDigits(v);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[28]++;
    StringBuilder sb = new StringBuilder();
    appendSpaces(sb, digits_column_width - digit_width);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[29]++;
    sb.append(v);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[30]++;
    return sb.toString();
  }

  /**
   * Gets the number of digits in an integer when printed in base 10. Assumes
   * a positive integer.
   * @param n The value.
   * @return The number of digits in the string.
   */
  private static int numDigits(long n) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[31]++;
    int i = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
    do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[4]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[5]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[6]++;
}
      i++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[33]++;
      n = n / 10;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[34]++;
    } while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false));
    return i;
  }

  /**
   * Gets a string of spaces of the length specified.
   * @param sb The string builder to append to.
   * @param numSpaces The number of spaces in the string.
   */
  @VisibleForTesting
  static void appendSpaces(StringBuilder sb, int numSpaces) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((numSpaces > 16) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[11]++;
      logger.warning("Tracer.appendSpaces called with large numSpaces");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[36]++;
      // Avoid long loop in case some bug in the caller
      numSpaces = 16;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[37]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[12]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
    while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((numSpaces >= 5) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[7]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[8]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[9]++;
}
      sb.append("     ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[39]++;
      numSpaces -= 5;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[40]++;
    }
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[41]++;

    // We know it's less than 5 now
    switch (numSpaces) {
      case 1:
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[13]++;
        sb.append(" ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[42]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[43]++;
        break;
      case 2:
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[14]++;
        sb.append("  ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[44]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[45]++;
        break;
      case 3:
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[15]++;
        sb.append("   ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[46]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[47]++;
        break;
      case 4:
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[16]++;
        sb.append("    ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[48]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[49]++;
        break; default : CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[17]++;
    }
  }

  /**
   * Adds a new tracing statistic to a trace
   *
   * @param tracingStatistic to enable a run
   * @return The index of this statistic (for use with stat.extraInfo()), or
   *         -1 if the statistic is not enabled.
   */
  static int addTracingStatistic(TracingStatistic tracingStatistic) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;
    // Check to see if we can enable the tracing statistic before actually
    // adding it.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((tracingStatistic.enable()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[18]++;
      // No synchronization needed, since this is a copy-on-write array.
      extraTracingStatistics.add(tracingStatistic);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[51]++;
      // 99.9% of the time, this will be O(1) and return
      // extraTracingStatistics.length - 1
      return extraTracingStatistics.lastIndexOf(tracingStatistic);

    } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[19]++;
      return -1;
    }
  }

  /**
   * For testing purposes only.  These removes all current tracers.
   * Severe errors can occur if there are any active tracers going on
   * when this is called.
   *
   * The test suite uses this to remove any tracers that it has added.
   */
  @VisibleForTesting
  static void clearTracingStatisticsTestingOnly() {
    extraTracingStatistics.clear();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[52]++;
  }

  /**
   * Stop the trace.
   * This may only be done once and must be done from the same thread
   * that started it.
   * @param silence_threshold Traces for time less than silence_threshold
   * ms will be left out of the trace report. A value of -1 indicates
   * that the current ThreadTrace silence_threshold should be used.
   * @return The time that this trace actually ran
   */
  long stop(int silence_threshold) {
    Preconditions.checkState(Thread.currentThread() == startThread);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[53]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[54]++;

    ThreadTrace trace = getThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[55]++;
int CodeCoverConditionCoverageHelper_C10;
    // Do nothing if the thread trace was not initialized.
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((trace.isInitialized()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[20]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[21]++;}

    stopTimeMs = clock.currentTimeMillis();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[56]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((extraTracingValues != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[22]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[58]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[10]++;


int CodeCoverConditionCoverageHelper_C12;
      // We use extraTracingValues.length rather than
      // extraTracingStatistics.size() because a new statistic may
      // have been added
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < extraTracingValues.length) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[10]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[11]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[12]++;
}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[59]++;
        long value = extraTracingStatistics.get(i).stop(startThread);
        extraTracingValues[i] = value - extraTracingValues[i];
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[60]++;
      }

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[23]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;

    // Do nothing if the thread trace was not initialized.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((trace.isInitialized()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[24]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[25]++;}

    trace.endEvent(this, silence_threshold);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[62]++;
    return stopTimeMs - startTimeMs;
  }

  /** Stop the trace using the default silence_threshold
   *
   * @return  The time that this trace actually ran.
   */
  long stop() {
    return stop(-1);
  }

  @Override public String toString() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[26]++;
      return comment;

    } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[27]++;
      return "[" + type + "] " + comment;
    }
  }

  static void setDefaultSilenceThreshold(int threshold) {
    getThreadTrace().defaultSilenceThreshold = threshold;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[64]++;
  }

  /**
   * Initialize the trace associated with the current thread by clearing
   * out any existing trace. There shouldn't be a trace so if one is
   * found we log it as an error.
   */
  static void initCurrentThreadTrace() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[65]++;
    ThreadTrace events = getThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((events.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[28]++;
      logger.log(Level.WARNING,
                 "Non-empty timer log:\n" + events,
                 new Throwable());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[67]++;
      clearThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[68]++;

      // Grab a new thread trace if we find a previous non-empty ThreadTrace.
      events = getThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[69]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[29]++;}

    // Mark the thread trace as initialized.
    events.init();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[70]++;
  }

  static void initCurrentThreadTrace(int default_silence_threshold) {
    initCurrentThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[71]++;
    setDefaultSilenceThreshold(default_silence_threshold);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[72]++;
  }

  /**
   * Returns a timer report similar to the one described in the class comment.
   *
   * @return The timer report as a string
   */
  static String getCurrentThreadTraceReport() {
    return getThreadTrace().toString();
  }

  /**
   * Logs a timer report similar to the one described in the class comment.
   */
  static void logCurrentThreadTrace() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[73]++;
    ThreadTrace trace = getThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[74]++;
int CodeCoverConditionCoverageHelper_C16;

    // New threads must call Tracer.initCurrentThreadTrace() before Tracer
    // statistics are gathered. This is a recent change (Jun 2007) that
    // prevents spurious Third Eye messages when an application uses a class in
    // a different package that happens to call Tracer without knowledge of the
    // application authors.
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((trace.isInitialized()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[30]++;
      logger.log(Level.WARNING,
                 "Tracer log requested for this thread but was not "
                 + "initialized using Tracer.initCurrentThreadTrace().",
                 new Throwable());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[75]++;
      return;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[31]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[76]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((trace.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[32]++;
      logger.log(Level.WARNING, "timers:\n{0}", getCurrentThreadTraceReport());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[77]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[33]++;}
  }

  /**
   * Throw away any Trace associated with the current thread.
   */
  static void clearCurrentThreadTrace() {
    clearThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[78]++;
  }

  /**
   * logCurrentThreadTrace() then clearCurrentThreadTrace()
   */
  static void logAndClearCurrentThreadTrace() {
    logCurrentThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[79]++;
    clearThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[80]++;
  }

  /**
   * Sets whether pretty printing is enabled. See class-level comment. This
   * only affects tracers created after this is called.
   * @param enabled Whether to enable pretty printing.
   */
  static void setPrettyPrint(boolean enabled) {
    defaultPrettyPrint = enabled;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[81]++;
  }

  /** Statistics for a given tracer type */
  static final class Stat {
    private int count;
    private int silent;
    private int clockTime;
    private int[] extraInfo;

    /** total count of tracers of a type, including silent
     *
     * @return total count of tracers, including silent tracers
     */
    int getCount() { return count; }

    /** total count of silent tracers of a type
     *
     * @return total count of silent tracers
     */
    int getSilentCount() { return silent; }

    /** total time spent in tracers of a type, in ms
     *
     * @return total time spent in tracer, in ms
     */
    int getTotalTime() { return clockTime; }

    /** total time spent doing additional things that we are clocking */
    @VisibleForTesting
    int getExtraInfo(int index) {
      return index >= extraInfo.length ? 0 : extraInfo[index];
    }

  }

  /**
   * This map tracks counts of tracers for each type over all time.
   */
  private static @Nullable AtomicTracerStatMap typeToCountMap;

  /**
   * This map tracks counts of silent tracers for each type over all time.
   */
  private static @Nullable AtomicTracerStatMap typeToSilentMap;

  /**
   * This map tracks time (ms) for each type over all time.
   */
  private static @Nullable AtomicTracerStatMap typeToTimeMap;

  /**
   * This method MUST be called before getTypeToCountMap (and friends)
   * will return a valid map.  This is because computing this information
   * imposes a synchronization penalty on every Tracer that is stopped.
   */
  static synchronized void enableTypeMaps() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[82]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((typeToCountMap == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[34]++;
      typeToCountMap = new AtomicTracerStatMap();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[83]++;
      typeToSilentMap = new AtomicTracerStatMap();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[84]++;
      typeToTimeMap = new AtomicTracerStatMap();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[85]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[35]++;}
  }

  /**
   * Used for exporting this data via varz.  Accesses to this
   * map must be synchronized on the map.  If enableTypeMaps has not
   * been called, this will return null.
   */
  static @Nullable Map<String, Long> getTypeToCountMap() {
    return typeToCountMap != null ? typeToCountMap.getMap() : null;
  }

  /**
   * Used for exporting this data via varz.  Accesses to this
   * map must be synchronized on the map.  If enableTypeMaps has not
   * been called, this will return null.
   */
  static @Nullable Map<String, Long> getTypeToSilentMap() {
    return typeToSilentMap != null ? typeToSilentMap.getMap() : null;
  }

  /**
   * Used for exporting this data via varz.  Accesses to this
   * map must be synchronized on the map.  If enableTypeMaps has not
   * been called, this will return null.
   */
  static @Nullable Map<String, Long> getTypeToTimeMap() {
    return typeToTimeMap != null ? typeToTimeMap.getMap() : null;
  }

  /** Gets the Stat for a tracer type; never returns null */
  static Stat getStatsForType(String type) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[86]++;
    Stat stat = getThreadTrace().stats.get(type);
    return stat != null ? stat : ZERO_STAT;
  }

  private static final Stat ZERO_STAT = new Stat();
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[87]++;
  }

  /** Return the sec.ms part of time (if time = "20:06:11.566",  "11.566") */
  private static String formatTime(long time) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[88]++;
    int sec = (int) ((time / 1000) % 60);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[89]++;
    int ms = (int) (time % 1000);
    return String.format("%02d.%03d", sec, ms);
  }

  /** An event is created every time a Tracer is created or stopped */
  private static final class Event {
    boolean isStart;   // else is_stop
    Tracer tracer;

    Event(boolean start, Tracer t) {
      isStart = start;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[90]++;
      tracer = t;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[91]++;
    }

    long eventTime() {
      return isStart ? tracer.startTimeMs : tracer.stopTimeMs;
    }

    /**
     * Converts the event to a formatted string.
     * @param prevEventTime The time of the previous event which appears at
     *     the left most part of the trace line.
     * @param indent The indentation to put before the tracer to show the
     *     hierarchy.
     * @param digitsColWidth How many characters the digits should use.
     * @return The formatted string.
     */
    String toString(long prevEventTime, String indent, int digitsColWidth) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[92]++;
      StringBuilder sb = new StringBuilder(120);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[93]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((prevEventTime == -1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[36]++;
        appendSpaces(sb, digitsColWidth);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[94]++;

      } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[37]++;
        sb.append(longToPaddedString(
            eventTime() - prevEventTime, digitsColWidth));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[95]++;
      }

      sb.append(' ');
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[96]++;
      sb.append(formatTime(eventTime()));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[97]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isStart) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[38]++;
        sb.append(" Start ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[99]++;
        appendSpaces(sb, digitsColWidth);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[100]++;
        sb.append("   ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[101]++;

      } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[39]++;
        sb.append(" Done ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[102]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[103]++;
        long delta = tracer.stopTimeMs - tracer.startTimeMs;
        sb.append(longToPaddedString(delta, digitsColWidth));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[104]++;
        sb.append(" ms ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[105]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[106]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((tracer.extraTracingValues != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[40]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[107]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[13]++;


int CodeCoverConditionCoverageHelper_C22;
          for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < tracer.extraTracingValues.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[13]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[14]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[15]++;
}
            delta = tracer.extraTracingValues[i];
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[108]++;
            sb.append(String.format("%4d", delta));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[109]++;
            sb.append(extraTracingStatistics.get(i).getUnits());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[110]++;
            sb.append(";  ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[111]++;
          }

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[41]++;}
      }
      sb.append(indent);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[112]++;
      sb.append(tracer.toString());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[113]++;
      return sb.toString();
    }
  }

  /** Stores a thread's Trace */
  static final class ThreadTrace {

    /** Events taking less than this number of milliseconds are not reported. */
    int defaultSilenceThreshold; // non-final

    /** The Events corresponding to each startEvent/stopEvent */
    final ArrayList<Event> events = new ArrayList<Event>();
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[114]++;
  }

    /** Tracers that have not had their .stop() called */
    final HashSet<Tracer> outstandingEvents = new HashSet<Tracer>();
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[115]++;
  }

    /** Map from type to Stat object */
    final Map<String, Stat> stats = new HashMap<String, Stat>();
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[116]++;
  }

    /**
     * True if {@code outstandingEvents} has been cleared because we exceeded
     * the max trace limit.
     */
    boolean isOutstandingEventsTruncated = false;
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[117]++;
  }

    /**
     * True if {@code events} has been cleared because we exceeded the max
     * trace limit.
     */
    boolean isEventsTruncated = false;
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[118]++;
  }

    /**
     * Set to true if {@link Tracer#initCurrentThreadTrace()} was called by
     * the current thread.
     */
    boolean isInitialized = false;
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[119]++;
  }

    /**
     * Whether pretty printing is enabled for the trace.
     */
    boolean prettyPrint = false;
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[120]++;
  }

    /** Initialize the trace.  */
    void init() {
      isInitialized = true;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[121]++;
    }

    /** Is initialized? */
    boolean isInitialized() {
      return isInitialized;
    }

    /**
     * Called by the constructor {@link Tracer#Tracer(String, String)} to create
     * a start event.
     */
    void startEvent(Tracer t) {
      events.add(new Event(true, t));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[122]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[123]++;
      boolean notAlreadyOutstanding = outstandingEvents.add(t);
      Preconditions.checkState(notAlreadyOutstanding);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[124]++;
    }

    /**
     * Called by {@link Tracer#stop()} to create a stop event.
     */
    void endEvent(Tracer t, int silenceThreshold) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[125]++;
      boolean wasOutstanding = outstandingEvents.remove(t);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[126]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((wasOutstanding) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[42]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[127]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isOutstandingEventsTruncated) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[44]++;
          // The events stack overflowed and was truncated, so just log a
          // warning. Otherwise, we get an exception which is extremely
          // confusing.
          logger.log(Level.WARNING,
                      "event not found, probably because the event stack "
                          + "overflowed and was truncated",
                      new Throwable());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[128]++;

        } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[45]++;
          // throw an exception if the event was not found and the events stack
          // is pristine
          throw new IllegalStateException();
        }

      } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[43]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[129]++;

      long elapsed = t.stopTimeMs - t.startTimeMs;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[130]++;
int CodeCoverConditionCoverageHelper_C25;

      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((silenceThreshold == -1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[46]++;   // use default
        silenceThreshold = defaultSilenceThreshold;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[131]++;

      } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[47]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[132]++;
int CodeCoverConditionCoverageHelper_C26;

      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((elapsed < silenceThreshold) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[48]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[133]++;
        // If this one is silent then we need to remove the start Event
        boolean removed = false;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[134]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[16]++;


int CodeCoverConditionCoverageHelper_C27;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < events.size()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[16]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[17]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[18]++;
}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[135]++;
          Event e = events.get(i);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[136]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((e.tracer == t) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[50]++;
            Preconditions.checkState(e.isStart);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[137]++;
            events.remove(i);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[138]++;
            removed = true;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[139]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[140]++;
            break;

          } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[51]++;}
        }

        // Only assert if we didn't find the original and the events
        // weren't truncated.
        Preconditions.checkState(removed || isEventsTruncated);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[141]++;

      } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[49]++;
        events.add(new Event(false, t));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[142]++;
      }
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[143]++;
int CodeCoverConditionCoverageHelper_C29;

      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((t.type != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[52]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[144]++;
        Stat stat = stats.get(t.type);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[145]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((stat == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[54]++;
          stat = new Stat();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[146]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[147]++;
int CodeCoverConditionCoverageHelper_C31;
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((extraTracingStatistics.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[56]++;
            stat.extraInfo = new int[extraTracingStatistics.size()];
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[148]++;

          } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[57]++;}
          stats.put(t.type, stat);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[149]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[55]++;}

        stat.count++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[150]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[151]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((typeToCountMap != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[58]++;
          typeToCountMap.incrementBy(t.type, 1);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[152]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[59]++;}

        stat.clockTime += elapsed;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[153]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[154]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((typeToTimeMap != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[60]++;
          typeToTimeMap.incrementBy(t.type, elapsed);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[155]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[61]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[156]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((stat.extraInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((t.extraTracingValues != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[62]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[157]++;
          int overlapLength =
              Math.min(stat.extraInfo.length, t.extraTracingValues.length);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[158]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[19]++;


int CodeCoverConditionCoverageHelper_C35;
          for (int i = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i < overlapLength) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[19]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[20]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[21]++;
}
            stat.extraInfo[i] += t.extraTracingValues[i];
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[159]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[160]++;
            AtomicTracerStatMap map =
                extraTracingStatistics.get(i).getTracingStat();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[161]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((map != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[64]++;
              map.incrementBy(t.type, t.extraTracingValues[i]);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[162]++;

            } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[65]++;}
          }

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[63]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[163]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((elapsed < silenceThreshold) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[66]++;
          stat.silent++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[164]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[165]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((typeToSilentMap != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[68]++;
            typeToSilentMap.incrementBy(t.type, 1);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[166]++;

          } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[69]++;}

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[67]++;}

      } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[53]++;}
    }

    boolean isEmpty() {
      return events.size() == 0 && outstandingEvents.size() == 0;
    }

    void truncateOutstandingEvents() {
      isOutstandingEventsTruncated = true;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[167]++;
      outstandingEvents.clear();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[168]++;
    }

    void truncateEvents() {
      isEventsTruncated = true;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[169]++;
      events.clear();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[170]++;
    }

    /** Produces the lovely Trace seen in the class comments */
    // Nullness checker does not understand that prettyPrint => indent != null
    @SuppressWarnings("nullness")
    @Override public String toString() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[171]++;

      int numDigits = getMaxDigits();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[172]++;
      StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[173]++;
      long etime = -1;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[174]++;
      LinkedList<String> indent = prettyPrint ? new LinkedList<String>() : null;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[175]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[22]++;


      for (Event e : events) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[22]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[23]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[24]++;
}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[176]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((prettyPrint) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((e.isStart) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((indent.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[70]++;
          indent.pop();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[177]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[71]++;}
        sb.append(" ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[178]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[179]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((prettyPrint) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[72]++;
          sb.append(e.toString(etime, Joiner.on("").join(indent), numDigits));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[180]++;

        } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[73]++;
          sb.append(e.toString(etime, "", 4));
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[181]++;
        }
        etime = e.eventTime();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[182]++;
        sb.append('\n');
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[183]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[184]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((prettyPrint) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((e.isStart) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[74]++;
          indent.push("|  ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[185]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[75]++;}
      }
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[186]++;
int CodeCoverConditionCoverageHelper_C42;

      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((outstandingEvents.size() != 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[76]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[187]++;
        long now = clock.currentTimeMillis();

        sb.append(" Unstopped timers:\n");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[188]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[189]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[25]++;


        for (Tracer t : outstandingEvents) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[25]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[26]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[27]++;
}
          sb.append("  ").
            append(t).
            append(" (").
            append(now - t.startTimeMs).
            append(" ms, started at ").
            append(formatTime(t.startTimeMs)).
            append(")\n");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[190]++;
        }

      } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[77]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[191]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[28]++;



      for (String key : stats.keySet()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[28]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[29]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[30]++;
}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[192]++;
        Stat stat = stats.get(key);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[193]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((stat.count > 1) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[78]++;
          sb.append(" TOTAL ").
             append(key).
             append(" ").
             append(stat.count).
             append(" (").
             append(stat.clockTime).
             append(" ms");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[194]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[195]++;
int CodeCoverConditionCoverageHelper_C44;
          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((stat.extraInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[80]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[196]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[31]++;


int CodeCoverConditionCoverageHelper_C45;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((i < stat.extraInfo.length) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[31]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[32]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[33]++;
}
              sb.append("; ");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[197]++;
              sb.append(stat.extraInfo[i]).
                 append(' ').
                 append(extraTracingStatistics.get(i).getUnits());
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[198]++;
            }

          } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[81]++;}
          sb.append(")\n");
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[199]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[79]++;}
      }
      return sb.toString();
    }

    /**
     * Gets the maximum number of digits that can appear in the tracer output
     * in the gaps between tracers or the duration of a tracer. This is used
     * by the pretty printing case so that all of the tracers are aligned.
     */
    private int getMaxDigits() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[200]++;
      long etime = -1;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[201]++;
      long max_time = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[202]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[34]++;


      for (Event e : events) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[34]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[35]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[36]++;
}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[203]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((etime != -1) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[82]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[204]++;
          long time = e.eventTime() - etime;
          max_time = Math.max(max_time, time);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[205]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[83]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[206]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((e.isStart) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[84]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[207]++;
          long time = e.tracer.stopTimeMs - e.tracer.startTimeMs;
          max_time = Math.max(max_time, time);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[208]++;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[85]++;}
        etime = e.eventTime();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[209]++;
      }
      // Minimum is 3 to preserve an indent even when max is small.
      return Math.max(3, numDigits(max_time));
    }
  }

  /** Holds the ThreadTrace for each thread.  */
  private static ThreadLocal<ThreadTrace> traces =
      new ThreadLocal<ThreadTrace>();
  static {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[210]++;
  }

  /**
   * Get the ThreadTrace for the current thread, creating one if necessary.
   */
  static ThreadTrace getThreadTrace() {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[211]++;
    ThreadTrace t = traces.get();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[212]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((t == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[86]++;
      t = new ThreadTrace();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[213]++;
      t.prettyPrint = defaultPrettyPrint;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[214]++;
      traces.set(t);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[215]++;

    } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[87]++;}
    return t;
  }

  /** Remove any ThreadTrace associated with the current thread */
  static void clearThreadTrace() {
    traces.remove();
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[216]++;
  }

  /**
   * A TracingStatistic allows the program to add additional optional
   * statistics to the trace output.
   *
   * The class com.google.monitoring.tracing.TracingStatistics
   * contains several useful tracing statistics
   *
   */
  static interface TracingStatistic {
    /**
     * This method is called at the start of the trace.  It should
     * return a numeric result indicating the amount of the specific
     * resource in use before the call started
     * @param thread  The current thread
     * @return A numeric value indicating the amount of the resource
     * already used.
     */
    long start(Thread thread);

    /**
     * This method is called at the end of the trace.  It should
     * return a numeric result indicating the amount of the specific
     * resource in use after the call ends. The actual reported result
     * will be the result end() - start()
     * @param thread  The current thread
     * @return A numeric value indicating the amount of the resource
     * currently used.
     */
    long stop(Thread thread);

    /**
     * Called when this tracing statistic is first enabled.  A return
     * value of True indicates that this statistic can successfully
     * run in the current JVM.
     *
     * @return An indication of whether this statistic can be
     * implemented in the current JVM.
     */
    boolean enable();

    /** Returns this tracing statistic's trace map.
     *
     * @return This tracing statistic's trace map.
     */
    AtomicTracerStatMap getTracingStat();

    /** A string that should be appended to the numeric output
     * indicating what this is.
     *
     * @return  A string indicating the units of this statistic and what it is.
     */
    String getUnits();
  }

  /**
   * This class encapsulates a map for keeping track of tracing statistics.
   * It allows the caller to atomically increment named fields.
   *
   */
  static final class AtomicTracerStatMap {
    private ConcurrentMap<String, Long> map =
        new ConcurrentHashMap<String, Long>();
  {
    CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[217]++;
  }

    /**
     * Atomically increment the specified field by the specified amount.
     *
     * @param key      the name of the field
     * @param delta    the amount by which to increment the field
     */
    // Nullness checker is not powerful enough to prove null-safety of
    // this method
    @SuppressWarnings("nullness")
        void incrementBy(String key, long delta) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[218]++;
      // We use a compareAndSet strategy to update the map, which is much
      // faster when there isn't too much contention.  Look at a value, and
      // conditionally update the map if the value hasn't changed.
      // If it has changed, repeat.
      Long oldValue = map.get(key);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[219]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((oldValue == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[88]++;
        // Currently, the slot is empty
        oldValue = map.putIfAbsent(key, delta);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[220]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[221]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((oldValue == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[90]++;
          // The slot was still empty when we set it
          return;

        } else {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[91]++;
          // Someone filled in the slot behind our back.  oldValue has
          // its current value
        }

      } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[89]++;}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[222]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[37]++;


      while (true) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[37]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[38]--;
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.loops[39]++;
}
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[223]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((map.replace(key, oldValue, oldValue + delta)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[92]++;
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[224]++;
          break;

        } else {
  CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.branches[93]++;}
        // Nullness checker doesn't understand that this cannot return null.
        oldValue = map.get(key);
CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt.statements[225]++;
      }
    }

    /**
     * Returns a map of key:value pairs.
     */
    Map<String, Long> getMap() {
      return map;
    }
  }
}

class CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt ());
  }
    public static long[] statements = new long[226];
    public static long[] branches = new long[94];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[53];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.Tracer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,3,1,2,1,1,1,1,1,1,1,1,1,0,1};
    for (int i = 1; i <= 52; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$ctsgya5qvrh6cv9lt () {
    super("com.google.javascript.jscomp.Tracer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 225; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 93; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 52; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Tracer.java");
      for (int i = 1; i <= 225; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 93; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 52; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
        if (loops[i * 3 - 2] != 0L) {
          log.passCounter("L" + i + "-0", loops[i * 3 - 2]);
          loops[i * 3 - 2] = 0L;
        }
        if ( loops[i * 3 - 1] != 0L) {
          log.passCounter("L" + i + "-1", loops[i * 3 - 1]);
          loops[i * 3 - 1] = 0L;
        }
        if ( loops[i * 3] != 0L) {
          log.passCounter("L" + i + "-2", loops[i * 3]);
          loops[i * 3] = 0L;
        }
      }
  }
}

