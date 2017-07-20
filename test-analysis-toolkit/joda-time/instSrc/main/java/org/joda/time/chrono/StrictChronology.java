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
package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.field.StrictDateTimeField;

/**
 * Wraps another Chronology, ensuring all the fields are strict.
 * <p>
 * StrictChronology is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see StrictDateTimeField
 * @see LenientChronology
 */
public final class StrictChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = 6633006628097111960L;
  static {
    CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[1]++;
  }

    /**
     * Create a StrictChronology for any chronology.
     *
     * @param base the chronology to wrap
     * @throws IllegalArgumentException if chronology is null
     */
    public static StrictChronology getInstance(Chronology base) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((base == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[1]++;
            throw new IllegalArgumentException("Must supply a chronology");

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[2]++;}
        return new StrictChronology(base);
    }

    private transient Chronology iWithUTC;

    /**
     * Create a StrictChronology for any chronology.
     *
     * @param base the chronology to wrap
     */
    private StrictChronology(Chronology base) {
        super(base, null);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[3]++;
    }

    public Chronology withUTC() {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iWithUTC == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[3]++;
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((getZone() == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[5]++;
                iWithUTC = this;
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[6]++;

            } else {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[6]++;
                iWithUTC = StrictChronology.getInstance(getBase().withUTC());
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[7]++;
            }

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[4]++;}
        return iWithUTC;
    }

    public Chronology withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[7]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[9]++;

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[8]++;}
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[9]++;
            return withUTC();

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[10]++;}
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[11]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[11]++;
            return this;

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[12]++;}
        return StrictChronology.getInstance(getBase().withZone(zone));
    }

    protected void assemble(Fields fields) {
        fields.year = convertField(fields.year);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[12]++;
        fields.yearOfEra = convertField(fields.yearOfEra);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[13]++;
        fields.yearOfCentury = convertField(fields.yearOfCentury);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[14]++;
        fields.centuryOfEra = convertField(fields.centuryOfEra);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[15]++;
        fields.era = convertField(fields.era);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[16]++;
        fields.dayOfWeek = convertField(fields.dayOfWeek);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[17]++;
        fields.dayOfMonth = convertField(fields.dayOfMonth);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[18]++;
        fields.dayOfYear = convertField(fields.dayOfYear);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[19]++;
        fields.monthOfYear = convertField(fields.monthOfYear);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[20]++;
        fields.weekOfWeekyear = convertField(fields.weekOfWeekyear);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[21]++;
        fields.weekyear = convertField(fields.weekyear);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[22]++;
        fields.weekyearOfCentury = convertField(fields.weekyearOfCentury);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[23]++;

        fields.millisOfSecond = convertField(fields.millisOfSecond);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[24]++;
        fields.millisOfDay = convertField(fields.millisOfDay);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[25]++;
        fields.secondOfMinute = convertField(fields.secondOfMinute);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[26]++;
        fields.secondOfDay = convertField(fields.secondOfDay);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[27]++;
        fields.minuteOfHour = convertField(fields.minuteOfHour);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[28]++;
        fields.minuteOfDay = convertField(fields.minuteOfDay);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[29]++;
        fields.hourOfDay = convertField(fields.hourOfDay);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[30]++;
        fields.hourOfHalfday = convertField(fields.hourOfHalfday);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[31]++;
        fields.clockhourOfDay = convertField(fields.clockhourOfDay);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[32]++;
        fields.clockhourOfHalfday = convertField(fields.clockhourOfHalfday);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[33]++;
        fields.halfdayOfDay = convertField(fields.halfdayOfDay);
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[34]++;
    }

    private static final DateTimeField convertField(DateTimeField field) {
        return StrictDateTimeField.getInstance(field);
    }

    //-----------------------------------------------------------------------
    /**
     * A strict chronology is only equal to a strict chronology with the
     * same base chronology.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     * @since 1.4
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[14]++;}
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((obj instanceof StrictChronology == false) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.branches[16]++;}
CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x.statements[37]++;
        StrictChronology chrono = (StrictChronology) obj;
        return getBase().equals(chrono.getBase());
    }

    /**
     * A suitable hashcode for the chronology.
     * 
     * @return the hashcode
     * @since 1.4
     */
    public int hashCode() {
        return 352831696 + getBase().hashCode() * 7;
    }

    /**
     * A debugging string for the chronology.
     * 
     * @return the debugging string
     */
    public String toString() {
        return "StrictChronology[" + getBase().toString() + ']';
    }

}

class CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x ());
  }
    public static long[] statements = new long[38];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.StrictChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$1xblt0fvtgktii7zzgedkqo2xuzi6sr4x () {
    super("org.joda.time.chrono.StrictChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 37; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.StrictChronology.java");
      for (int i = 1; i <= 37; i++) {
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
    for (int i = 1; i <= 8; i++) {
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

