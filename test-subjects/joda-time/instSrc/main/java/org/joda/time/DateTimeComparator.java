/*
 *  Copyright 2001-2009 Stephen Colebourne
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
package org.joda.time;

import java.io.Serializable;
import java.util.Comparator;

import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

/**
 * DateTimeComparator provides comparators to compare one date with another.
 * <p>
 * Dates may be specified using any object recognised by the
 * {@link org.joda.time.convert.ConverterManager ConverterManager} class.
 * <p>
 * The default objects recognised by the comparator are:
 * <ul>
 * <li>ReadableInstant
 * <li>String
 * <li>Calendar
 * <li>Date
 * <li>Long (milliseconds)
 * <li>null (now)
 * </ul>
 *
 * <p>
 * DateTimeComparator is thread-safe and immutable.
 *
 * @author Guy Allard
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public class DateTimeComparator implements Comparator<Object>, Serializable {
  static {
    CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -6097339773320178364L;
  static {
    CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[1]++;
  }

    /** Singleton instance */
    private static final DateTimeComparator ALL_INSTANCE = new DateTimeComparator(null, null);
  static {
    CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[2]++;
  }
    /** Singleton instance */
    private static final DateTimeComparator DATE_INSTANCE = new DateTimeComparator(DateTimeFieldType.dayOfYear(), null);
  static {
    CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[3]++;
  }
    /** Singleton instance */
    private static final DateTimeComparator TIME_INSTANCE = new DateTimeComparator(null, DateTimeFieldType.dayOfYear());
  static {
    CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[4]++;
  }

    /** The lower limit of fields to compare, null if no limit */
    private final DateTimeFieldType iLowerLimit;
    /** The upper limit of fields to compare, null if no limit */
    private final DateTimeFieldType iUpperLimit;

    //-----------------------------------------------------------------------
    /**
     * Returns a DateTimeComparator the compares the entire date time value.
     * 
     * @return a comparator over all fields
     */
    public static DateTimeComparator getInstance() {
        return ALL_INSTANCE;
    }

    /**
     * Returns a DateTimeComparator with a lower limit only. Fields of a
     * magnitude less than the lower limit are excluded from comparisons.
     *
     * @param lowerLimit  inclusive lower limit for fields to be compared, null means no limit
     * @return a comparator over all fields above the lower limit
     */
    public static DateTimeComparator getInstance(DateTimeFieldType lowerLimit) {
        return getInstance(lowerLimit, null);
    }

    /**
     * Returns a DateTimeComparator with a lower and upper limit. Fields of a
     * magnitude less than the lower limit are excluded from comparisons.
     * Fields of a magnitude greater than or equal to the upper limit are also
     * excluded from comparisons. Either limit may be specified as null, which
     * indicates an unbounded limit.
     *
     * @param lowerLimit  inclusive lower limit for fields to be compared, null means no limit
     * @param upperLimit  exclusive upper limit for fields to be compared, null means no limit
     * @return a comparator over all fields between the limits
     */
    public static DateTimeComparator getInstance(DateTimeFieldType lowerLimit, DateTimeFieldType upperLimit) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((lowerLimit == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((upperLimit == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[1]++;
            return ALL_INSTANCE;

        } else {
  CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[2]++;}
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((lowerLimit == DateTimeFieldType.dayOfYear()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((upperLimit == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[3]++;
            return DATE_INSTANCE;

        } else {
  CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[4]++;}
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((lowerLimit == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((upperLimit == DateTimeFieldType.dayOfYear()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[5]++;
            return TIME_INSTANCE;

        } else {
  CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[6]++;}
        return new DateTimeComparator(lowerLimit, upperLimit);
    }

    /**
     * Returns a comparator that only considers date fields.
     * Time of day is ignored.
     * 
     * @return a comparator over all date fields
     */
    public static DateTimeComparator getDateOnlyInstance() {
        return DATE_INSTANCE;
    }

    /**
     * Returns a comparator that only considers time fields.
     * Date is ignored.
     * 
     * @return a comparator over all time fields
     */
    public static DateTimeComparator getTimeOnlyInstance() {
        return TIME_INSTANCE;
    }

    /**
     * Restricted constructor.
     * 
     * @param lowerLimit  the lower field limit, null means no limit
     * @param upperLimit  the upper field limit, null means no limit
     */
    protected DateTimeComparator(DateTimeFieldType lowerLimit, DateTimeFieldType upperLimit) {
        super();
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[8]++;
        iLowerLimit = lowerLimit;
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[9]++;
        iUpperLimit = upperLimit;
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[10]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the field type that represents the lower limit of comparison.
     * 
     * @return the field type, null if no upper limit
     */
    public DateTimeFieldType getLowerLimit() {
        return iLowerLimit;
    }

    /**
     * Gets the field type that represents the upper limit of comparison.
     * 
     * @return the field type, null if no upper limit
     */
    public DateTimeFieldType getUpperLimit() {
        return iUpperLimit;
    }

    /**
     * Compare two objects against only the range of date time fields as
     * specified in the constructor.
     * 
     * @param lhsObj  the first object,
     *      logically on the left of a &lt; comparison, null means now
     * @param rhsObj  the second object,
     *      logically on the right of a &lt; comparison, null means now
     * @return zero if order does not matter,
     *      negative value if lhsObj &lt; rhsObj, positive value otherwise.
     * @throws IllegalArgumentException if either argument is not supported
     */
    public int compare(Object lhsObj, Object rhsObj) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[11]++;
        InstantConverter conv = ConverterManager.getInstance().getInstantConverter(lhsObj);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[12]++;
        Chronology lhsChrono = conv.getChronology(lhsObj, (Chronology) null);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[13]++;
        long lhsMillis = conv.getInstantMillis(lhsObj, lhsChrono);
        
        conv = ConverterManager.getInstance().getInstantConverter(rhsObj);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[14]++;
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[15]++;
        Chronology rhsChrono = conv.getChronology(rhsObj, (Chronology) null);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[16]++;
        long rhsMillis = conv.getInstantMillis(rhsObj, rhsChrono);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((iLowerLimit != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[7]++;
            lhsMillis = iLowerLimit.getField(lhsChrono).roundFloor(lhsMillis);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[18]++;
            rhsMillis = iLowerLimit.getField(rhsChrono).roundFloor(rhsMillis);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[19]++;

        } else {
  CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[8]++;}
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iUpperLimit != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[9]++;
            lhsMillis = iUpperLimit.getField(lhsChrono).remainder(lhsMillis);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[21]++;
            rhsMillis = iUpperLimit.getField(rhsChrono).remainder(rhsMillis);
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[22]++;

        } else {
  CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[10]++;}
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((lhsMillis < rhsMillis) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[11]++;
            return -1;

        } else {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[12]++;
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[24]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lhsMillis > rhsMillis) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[13]++;
            return 1;

        } else {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[14]++;
            return 0;
        }
}
    }

    //-----------------------------------------------------------------------
    /**
     * Support serialization singletons.
     * 
     * @return the resolved singleton instance
     */
    private Object readResolve() {
        return getInstance(iLowerLimit, iUpperLimit);
    }

    /**
     * Compares this comparator to another.
     * 
     * @param object  the object to compare to
     * @return true if equal
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((object instanceof DateTimeComparator) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[15]++;
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[26]++;
            DateTimeComparator other = (DateTimeComparator) object;
            return (iLowerLimit == other.getLowerLimit() ||
                    (iLowerLimit != null && iLowerLimit.equals(other.getLowerLimit()))) &&
                   (iUpperLimit == other.getUpperLimit() ||
                    (iUpperLimit != null && iUpperLimit.equals(other.getUpperLimit())));

        } else {
  CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[16]++;}
        return false;
    }

    /**
     * Gets a suitable hashcode.
     * 
     * @return the hashcode
     */
    public int hashCode() {
        return (iLowerLimit == null ? 0 : iLowerLimit.hashCode()) +
               (123 * (iUpperLimit == null ? 0 : iUpperLimit.hashCode()));
    }

    /**
     * Gets a debugging string.
     * 
     * @return a debugging string
     */
    public String toString() {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((iLowerLimit == iUpperLimit) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[17]++;
            return "DateTimeComparator["
                + (iLowerLimit == null ? "" : iLowerLimit.getName())
                + "]";

        } else {
CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01.branches[18]++;
            return "DateTimeComparator["
                + (iLowerLimit == null ? "" : iLowerLimit.getName())
                + "-"
                + (iUpperLimit == null ? "" : iUpperLimit.getName())
                + "]";
        }
    }

}

class CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01 ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.joda.time.DateTimeComparator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,2,1,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$27savqplkoneg7dsj12wsmh079ae611fgm01 () {
    super("org.joda.time.DateTimeComparator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DateTimeComparator.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
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

