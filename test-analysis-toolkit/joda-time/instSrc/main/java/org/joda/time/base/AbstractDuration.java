/*
 *  Copyright 2001-2010 Stephen Colebourne
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

import org.joda.convert.ToString;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.format.FormatUtils;

/**
 * AbstractDuration provides the common behaviour for duration classes.
 * <p>
 * This class should generally not be used directly by API users. The 
 * {@link ReadableDuration} interface should be used when different 
 * kinds of durations are to be referenced.
 * <p>
 * AbstractDuration subclasses may be mutable and not thread-safe.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class AbstractDuration implements ReadableDuration {
  static {
    CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.ping();
  }


    /**
     * Constructor.
     */
    protected AbstractDuration() {
        super();
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get this duration as an immutable <code>Duration</code> object.
     * 
     * @return a Duration created using the millisecond duration from this instance
     */
    public Duration toDuration() {
        return new Duration(getMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Converts this duration to a Period instance using the standard period type
     * and the ISO chronology.
     * <p>
     * Only precise fields in the period type will be used. Thus, only the hour,
     * minute, second and millisecond fields on the period will be used.
     * The year, month, week and day fields will not be populated.
     * <p>
     * If the duration is small, less than one day, then this method will perform
     * as you might expect and split the fields evenly.
     * If the duration is larger than one day then all the remaining duration will
     * be stored in the largest available field, hours in this case.
     * <p>
     * For example, a duration effectively equal to (365 + 60 + 5) days will be
     * converted to ((365 + 60 + 5) * 24) hours by this constructor.
     * <p>
     * For more control over the conversion process, you must pair the duration with
     * an instant, see {@link Period#Period(ReadableInstant,ReadableDuration)}.
     * 
     * @return a Period created using the millisecond duration from this instance
     */
    public Period toPeriod() {
        return new Period(getMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this duration with the specified duration based on length.
     *
     * @param other  a duration to check against
     * @return negative value if this is less, 0 if equal, or positive value if greater
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the given object is not supported
     */
    public int compareTo(ReadableDuration other) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[2]++;
        long thisMillis = this.getMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[3]++;
        long otherMillis = other.getMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        
        // cannot do (thisMillis - otherMillis) as it can overflow
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((thisMillis < otherMillis) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[1]++;
            return -1;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[2]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisMillis > otherMillis) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[3]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[4]++;}
        return 0;
    }

    /**
     * Is the length of this duration equal to the duration passed in.
     *
     * @param duration  another duration to compare to, null means zero milliseconds
     * @return true if this duration is equal to than the duration passed in
     */
    public boolean isEqual(ReadableDuration duration) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((duration == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[5]++;
            duration = Duration.ZERO;
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[7]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[6]++;}
        return compareTo(duration) == 0;
    }

    /**
     * Is the length of this duration longer than the duration passed in.
     *
     * @param duration  another duration to compare to, null means zero milliseconds
     * @return true if this duration is equal to than the duration passed in
     */
    public boolean isLongerThan(ReadableDuration duration) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((duration == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[7]++;
            duration = Duration.ZERO;
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[9]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[8]++;}
        return compareTo(duration) > 0;
    }

    /**
     * Is the length of this duration shorter than the duration passed in.
     *
     * @param duration  another duration to compare to, null means zero milliseconds
     * @return true if this duration is equal to than the duration passed in
     */
    public boolean isShorterThan(ReadableDuration duration) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((duration == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[9]++;
            duration = Duration.ZERO;
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[11]++;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[10]++;}
        return compareTo(duration) < 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this object with the specified object for equality based
     * on the millisecond length. All ReadableDuration instances are accepted.
     *
     * @param duration  a readable duration to check against
     * @return true if the length of the duration is equal
     */
    public boolean equals(Object duration) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[12]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this == duration) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[11]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[12]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((duration instanceof ReadableDuration == false) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[14]++;}
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[14]++;
        ReadableDuration other = (ReadableDuration) duration;
        return (getMillis() == other.getMillis());
    }

    /**
     * Gets a hash code for the duration that is compatible with the 
     * equals method.
     *
     * @return a hash code
     */
    public int hashCode() {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[15]++;
        long len = getMillis();
        return (int) (len ^ (len >>> 32));
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the value as a String in the ISO8601 duration format including
     * only seconds and milliseconds.
     * <p>
     * For example, "PT72.345S" represents 1 minute, 12 seconds and 345 milliseconds.
     * <p>
     * For more control over the output, see
     * {@link org.joda.time.format.PeriodFormatterBuilder PeriodFormatterBuilder}.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[16]++;
        long millis = getMillis();
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[17]++;
        StringBuffer buf = new StringBuffer();
        buf.append("PT");
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[18]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[19]++;
        boolean negative = (millis < 0);
        FormatUtils.appendUnpaddedInteger(buf, millis);
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[20]++;
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((buf.length() < (negative ? 7 : 6)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.loops[1]--;
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.loops[2]--;
  CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.loops[3]++;
}
            buf.insert(negative ? 3 : 2, "0");
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[22]++;
        }
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[23]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 (((millis / 1000) * 1000 == millis) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[15]++;
            buf.setLength(buf.length() - 3);
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[24]++;

        } else {
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.branches[16]++;
            buf.insert(buf.length() - 3, ".");
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[25]++;
        }
        buf.append('S');
CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep.statements[26]++;
        return buf.toString();
    }

}

class CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep ());
  }
    public static long[] statements = new long[27];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.base.AbstractDuration.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$1ib8k6g9t1mz1xm8ot5z8gx81ap1tx1ep () {
    super("org.joda.time.base.AbstractDuration.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 26; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.AbstractDuration.java");
      for (int i = 1; i <= 26; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

