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
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * AbstractPeriod provides the common behaviour for period classes.
 * <p>
 * This class should generally not be used directly by API users. The 
 * {@link ReadablePeriod} interface should be used when different 
 * kinds of periods are to be referenced.
 * <p>
 * AbstractPeriod subclasses may be mutable and not thread-safe.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class AbstractPeriod implements ReadablePeriod {
  static {
    CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.ping();
  }


    /**
     * Constructor.
     */
    protected AbstractPeriod() {
        super();
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields that this period supports.
     *
     * @return the number of fields supported
     * @since 2.0 (previously on BasePeriod)
     */
    public int size() {
        return getPeriodType().size();
    }

    /**
     * Gets the field type at the specified index.
     *
     * @param index  the index to retrieve
     * @return the field at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     * @since 2.0 (previously on BasePeriod)
     */
    public DurationFieldType getFieldType(int index) {
        return getPeriodType().getFieldType(index);
    }

    /**
     * Gets an array of the field types that this period supports.
     * <p>
     * The fields are returned largest to smallest, for example Hours, Minutes, Seconds.
     *
     * @return the fields supported in an array that may be altered, largest to smallest
     */
    public DurationFieldType[] getFieldTypes() {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[2]++;
        DurationFieldType[] result = new DurationFieldType[size()];
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[1]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[2]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[3]++;
}
            result[i] = getFieldType(i);
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[4]++;
        }
        return result;
    }

    /**
     * Gets an array of the value of each of the fields that this period supports.
     * <p>
     * The fields are returned largest to smallest, for example Hours, Minutes, Seconds.
     * Each value corresponds to the same array index as <code>getFields()</code>
     *
     * @return the current values of each field in an array that may be altered, largest to smallest
     */
    public int[] getValues() {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[5]++;
        int[] result = new int[size()];
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[6]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[4]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[5]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[6]++;
}
            result[i] = getValue(i);
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[7]++;
        }
        return result;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the value of one of the fields.
     * <p>
     * If the field type specified is not supported by the period then zero
     * is returned.
     *
     * @param type  the field type to query, null returns zero
     * @return the value of that field, zero if field not supported
     */
    public int get(DurationFieldType type) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[8]++;
        int index = indexOf(type);
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[1]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[2]++;}
        return getValue(index);
    }

    /**
     * Checks whether the field specified is supported by this period.
     *
     * @param type  the type to check, may be null which returns false
     * @return true if the field is supported
     */
    public boolean isSupported(DurationFieldType type) {
        return getPeriodType().isSupported(type);
    }

    /**
     * Gets the index of the field in this period.
     *
     * @param type  the type to check, may be null which returns -1
     * @return the index of -1 if not supported
     */
    public int indexOf(DurationFieldType type) {
        return getPeriodType().indexOf(type);
    }

    //-----------------------------------------------------------------------
    /**
     * Get this period as an immutable <code>Period</code> object.
     * 
     * @return a Period using the same field set and values
     */
    public Period toPeriod() {
        return new Period(this);
    }

    /**
     * Get this object as a <code>MutablePeriod</code>.
     * <p>
     * This will always return a new <code>MutablePeriod</code> with the same fields.
     * 
     * @return a MutablePeriod using the same field set and values
     */
    public MutablePeriod toMutablePeriod() {
        return new MutablePeriod(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this object with the specified object for equality based
     * on the value of each field. All ReadablePeriod instances are accepted.
     * <p>
     * Note that a period of 1 day is not equal to a period of 24 hours,
     * nor is 1 hour equal to 60 minutes. Only periods with the same amount
     * in each field are equal.
     * <p>
     * This is because periods represent an abstracted definition of a time
     * period (eg. a day may not actually be 24 hours, it might be 23 or 25
     * at daylight savings boundary).
     * <p>
     * To compare the actual duration of two periods, convert both to
     * {@link org.joda.time.Duration Duration}s, an operation that emphasises
     * that the result may differ according to the date you choose.
     *
     * @param period  a readable period to check against
     * @return true if all the field values are equal, false if
     *  not or the period is null or of an incorrect type
     */
    public boolean equals(Object period) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this == period) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[3]++;
            return true;

        } else {
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[4]++;}
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((period instanceof ReadablePeriod == false) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[6]++;}
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[12]++;
        ReadablePeriod other = (ReadablePeriod) period;
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((size() != other.size()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[8]++;}
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[14]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[7]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[8]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[9]++;
}
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[15]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((getValue(i) != other.getValue(i)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getFieldType(i) != other.getFieldType(i)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[9]++;
                return false;

            } else {
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[10]++;}
        }
        return true;
    }

    /**
     * Gets a hash code for the period as defined by ReadablePeriod.
     *
     * @return a hash code
     */
    public int hashCode() {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[16]++;
        int total = 17;
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[17]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[10]++;


int CodeCoverConditionCoverageHelper_C9;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[10]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[11]--;
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.loops[12]++;
}
            total = 27 * total + getValue(i);
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[18]++;
            total = 27 * total + getFieldType(i).hashCode();
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[19]++;
        }
        return total;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the value as a String in the ISO8601 duration format.
     * <p>
     * For example, "P6H3M7S" represents 6 hours, 3 minutes, 7 seconds.
     * <p>
     * For more control over the output, see
     * {@link org.joda.time.format.PeriodFormatterBuilder PeriodFormatterBuilder}.
     *
     * @return the value as an ISO8601 string
     */
    @ToString
    public String toString() {
        return ISOPeriodFormat.standard().print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Uses the specified formatter to convert this period to a String.
     *
     * @param formatter  the formatter to use, null means use <code>toString()</code>.
     * @return the formatted string
     * @since 1.5
     */
    public String toString(PeriodFormatter formatter) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.statements[20]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[11]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5.branches[12]++;}
        return formatter.print(this);
    }

}

class CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5 ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.joda.time.base.AbstractPeriod.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$12nylthf4yo4huf7130fst4o11j8f5 () {
    super("org.joda.time.base.AbstractPeriod.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.AbstractPeriod.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

