/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.format;

import java.util.Locale;

import org.joda.time.ReadWritablePeriod;

/**
 * Internal interface for parsing textual representations of time periods.
 * <p>
 * Application users will rarely use this class directly. Instead, you
 * will use one of the factory classes to create a {@link PeriodFormatter}.
 * <p>
 * The factory classes are:<br />
 * - {@link PeriodFormatterBuilder}<br />
 * - {@link PeriodFormat}<br />
 * - {@link ISOPeriodFormat}<br />
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 * @see PeriodFormatter
 * @see PeriodFormatterBuilder
 * @see PeriodFormat
 */
public interface PeriodParser {

    /**
     * Parses a period from the given text, at the given position, saving the
     * result into the fields of the given ReadWritablePeriod. If the parse
     * succeeds, the return value is the new text position. Note that the parse
     * may succeed without fully reading the text.
     * <p>
     * If it fails, the return value is negative, but the period may still be
     * modified. To determine the position where the parse failed, apply the
     * one's complement operator (~) on the return value.
     *
     * @param period  a period that will be modified
     * @param periodStr  text to parse
     * @param position position to start parsing from
     * @param locale  the locale to use for parsing
     * @return new position, if negative, parse failed. Apply complement
     * operator (~) to get position of failure
     * @throws IllegalArgumentException if any field is out of range
     */
    int parseInto(ReadWritablePeriod period, String periodStr, int position, Locale locale);

}

class CodeCoverCoverageCounter$xugz0vnvd7ezywwixi8hseymw1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$xugz0vnvd7ezywwixi8hseymw1 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$xugz0vnvd7ezywwixi8hseymw1 () {
    super("org.joda.time.format.PeriodParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.PeriodParser.java");
      for (int i = 1; i <= -1; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
      for (int i = 1; i <= 0; i++) {
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

