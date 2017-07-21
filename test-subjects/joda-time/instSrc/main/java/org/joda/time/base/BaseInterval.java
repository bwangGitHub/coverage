/*
 *  Copyright 2001-2011 Stephen Colebourne
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
package org.joda.time.base;

import java.io.Serializable;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.MutableInterval;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadableInterval;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.IntervalConverter;
import org.joda.time.field.FieldUtils;

/**
 * BaseInterval is an abstract implementation of ReadableInterval that stores
 * data in two <code>long</code> millisecond fields.
 * <p>
 * This class should generally not be used directly by API users.
 * The {@link ReadableInterval} interface should be used when different 
 * kinds of interval objects are to be referenced.
 * <p>
 * BaseInterval subclasses may be mutable and not thread-safe.
 *
 * @author Brian S O'Neill
 * @author Sean Geoghegan
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class BaseInterval
        extends AbstractInterval
        implements ReadableInterval, Serializable {
  static {
    CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 576586928732749278L;
  static {
    CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[1]++;
  }

    /** The chronology of the interval */
    private volatile Chronology iChronology;
    /** The start of the interval */
    private volatile long iStartMillis;
    /** The end of the interval */
    private volatile long iEndMillis;

    /**
     * Constructs an interval from a start and end instant.
     * 
     * @param startInstant  start of this interval, as milliseconds from 1970-01-01T00:00:00Z.
     * @param endInstant  end of this interval, as milliseconds from 1970-01-01T00:00:00Z.
     * @param chrono  the chronology to use, null is ISO default
     * @throws IllegalArgumentException if the end is before the start
     */
    protected BaseInterval(long startInstant, long endInstant, Chronology chrono) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[2]++;
        iChronology = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[3]++;
        checkInterval(startInstant, endInstant);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[4]++;
        iStartMillis = startInstant;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[5]++;
        iEndMillis = endInstant;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[6]++;
    }

    /**
     * Constructs an interval from a start and end instant.
     * 
     * @param start  start of this interval, null means now
     * @param end  end of this interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     */
    protected BaseInterval(ReadableInstant start, ReadableInstant end) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[7]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((end == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[1]++;
            iStartMillis = iEndMillis = DateTimeUtils.currentTimeMillis();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[9]++;
            iChronology = ISOChronology.getInstance();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[10]++;

        } else {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[2]++;
            iChronology = DateTimeUtils.getInstantChronology(start);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[11]++;
            iStartMillis = DateTimeUtils.getInstantMillis(start);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[12]++;
            iEndMillis = DateTimeUtils.getInstantMillis(end);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[13]++;
            checkInterval(iStartMillis, iEndMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[14]++;
        }
    }

    /**
     * Constructs an interval from a start instant and a duration.
     * 
     * @param start  start of this interval, null means now
     * @param duration  the duration of this interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    protected BaseInterval(ReadableInstant start, ReadableDuration duration) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[15]++;
        iChronology = DateTimeUtils.getInstantChronology(start);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[16]++;
        iStartMillis = DateTimeUtils.getInstantMillis(start);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[17]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[18]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
        iEndMillis = FieldUtils.safeAdd(iStartMillis, durationMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[19]++;
        checkInterval(iStartMillis, iEndMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[20]++;
    }

    /**
     * Constructs an interval from a millisecond duration and an end instant.
     * 
     * @param duration  the duration of this interval, null means zero length
     * @param end  end of this interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    protected BaseInterval(ReadableDuration duration, ReadableInstant end) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[21]++;
        iChronology = DateTimeUtils.getInstantChronology(end);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[22]++;
        iEndMillis = DateTimeUtils.getInstantMillis(end);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[23]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[24]++;
        long durationMillis = DateTimeUtils.getDurationMillis(duration);
        iStartMillis = FieldUtils.safeAdd(iEndMillis, -durationMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[25]++;
        checkInterval(iStartMillis, iEndMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[26]++;
    }

    /**
     * Constructs an interval from a start instant and a time period.
     * <p>
     * When forming the interval, the chronology from the instant is used
     * if present, otherwise the chronology of the period is used.
     * 
     * @param start  start of this interval, null means now
     * @param period  the period of this interval, null means zero length
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the end instant exceeds the capacity of a long
     */
    protected BaseInterval(ReadableInstant start, ReadablePeriod period) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[27]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[28]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(start);
        iChronology = chrono;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[29]++;
        iStartMillis = DateTimeUtils.getInstantMillis(start);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[30]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[31]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[3]++;
            iEndMillis = iStartMillis;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[32]++;

        } else {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[4]++;
            iEndMillis = chrono.add(period, iStartMillis, 1);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[33]++;
        }
        checkInterval(iStartMillis, iEndMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[34]++;
    }

    /**
     * Constructs an interval from a time period and an end instant.
     * <p>
     * When forming the interval, the chronology from the instant is used
     * if present, otherwise the chronology of the period is used.
     * 
     * @param period  the period of this interval, null means zero length
     * @param end  end of this interval, null means now
     * @throws IllegalArgumentException if the end is before the start
     * @throws ArithmeticException if the start instant exceeds the capacity of a long
     */
    protected BaseInterval(ReadablePeriod period, ReadableInstant end) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[35]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[36]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(end);
        iChronology = chrono;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[37]++;
        iEndMillis = DateTimeUtils.getInstantMillis(end);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[38]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[39]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[5]++;
            iStartMillis = iEndMillis;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[40]++;

        } else {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[6]++;
            iStartMillis = chrono.add(period, iEndMillis, -1);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[41]++;
        }
        checkInterval(iStartMillis, iEndMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[42]++;
    }

    /**
     * Constructs a time interval converting or copying from another object
     * that describes an interval.
     * 
     * @param interval  the time interval to copy
     * @param chrono  the chronology to use, null means let converter decide
     * @throws IllegalArgumentException if the interval is invalid
     */
    protected BaseInterval(Object interval, Chronology chrono) {
        super();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[43]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[44]++;
        IntervalConverter converter = ConverterManager.getInstance().getIntervalConverter(interval);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[45]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((converter.isReadableInterval(interval, chrono)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[7]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[46]++;
            ReadableInterval input = (ReadableInterval) interval;
            iChronology = (chrono != null ? chrono : input.getChronology());
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[47]++;
            iStartMillis = input.getStartMillis();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[48]++;
            iEndMillis = input.getEndMillis();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[49]++;

        } else {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[8]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[50]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this instanceof ReadWritableInterval) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[9]++;
            converter.setInto((ReadWritableInterval) this, interval, chrono);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[51]++;

        } else {
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.branches[10]++;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[52]++;
            MutableInterval mi = new MutableInterval();
            converter.setInto(mi, interval, chrono);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[53]++;
            iChronology = mi.getChronology();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[54]++;
            iStartMillis = mi.getStartMillis();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[55]++;
            iEndMillis = mi.getEndMillis();
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[56]++;
        }
}
        checkInterval(iStartMillis, iEndMillis);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[57]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of this interval.
     *
     * @return the chronology
     */
    public Chronology getChronology() {
        return iChronology;
    }

    /**
     * Gets the start of this time interval which is inclusive.
     *
     * @return the start of the time interval,
     *  millisecond instant from 1970-01-01T00:00:00Z
     */
    public long getStartMillis() {
        return iStartMillis;
    }

    /**
     * Gets the end of this time interval which is exclusive.
     *
     * @return the end of the time interval,
     *  millisecond instant from 1970-01-01T00:00:00Z
     */
    public long getEndMillis() {
        return iEndMillis;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets this interval from two millisecond instants and a chronology.
     *
     * @param startInstant  the start of the time interval
     * @param endInstant  the start of the time interval
     * @param chrono  the chronology, not null
     * @throws IllegalArgumentException if the end is before the start
     */
    protected void setInterval(long startInstant, long endInstant, Chronology chrono) {
        checkInterval(startInstant, endInstant);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[58]++;
        iStartMillis = startInstant;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[59]++;
        iEndMillis = endInstant;
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[60]++;
        iChronology = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox.statements[61]++;
    }

}

class CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.base.BaseInterval.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$ry1x81otkwc7y3835k8vp2a6ox () {
    super("org.joda.time.base.BaseInterval.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.BaseInterval.java");
      for (int i = 1; i <= 61; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

