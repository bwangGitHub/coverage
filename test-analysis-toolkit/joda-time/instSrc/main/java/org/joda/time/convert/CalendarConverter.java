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
package org.joda.time.convert;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;

/**
 * CalendarConverter converts a java util Calendar to an instant or partial.
 * The Calendar is converted to milliseconds and the chronology that best
 * matches the calendar.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
final class CalendarConverter extends AbstractConverter
        implements InstantConverter, PartialConverter {
  static {
    CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.ping();
  }


    /**
     * Singleton instance.
     */
    static final CalendarConverter INSTANCE = new CalendarConverter();
  static {
    CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    protected CalendarConverter() {
        super();
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology.
     * <p>
     * If a chronology is specified then it is used.
     * Otherwise, it is the GJChronology if a GregorianCalendar is used,
     * BuddhistChronology if a BuddhistCalendar is used or ISOChronology otherwise.
     * The time zone is extracted from the calendar if possible, default used if not.
     * 
     * @param object  the Calendar to convert, must not be null
     * @param chrono  the chronology to use, null means use Calendar
     * @return the chronology, never null
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public Chronology getChronology(Object object, Chronology chrono) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((chrono != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[1]++;
            return chrono;

        } else {
  CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[2]++;}
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[4]++;
        Calendar cal = (Calendar) object;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[5]++;
        DateTimeZone zone = null;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[6]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            zone = DateTimeZone.forTimeZone(cal.getTimeZone());
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[7]++;
            
        } catch (IllegalArgumentException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[4]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[8]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[3]++;
}
  }
        return getChronology(cal, zone);
    }

    /**
     * Gets the chronology, which is the GJChronology if a GregorianCalendar is used,
     * BuddhistChronology if a BuddhistCalendar is used or ISOChronology otherwise.
     * The time zone specified is used in preference to that on the calendar.
     * 
     * @param object  the Calendar to convert, must not be null
     * @param zone  the specified zone to use, null means default zone
     * @return the chronology, never null
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public Chronology getChronology(Object object, DateTimeZone zone) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((object.getClass().getName().endsWith(".BuddhistCalendar")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[5]++;
            return BuddhistChronology.getInstance(zone);

        } else {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[6]++;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[10]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((object instanceof GregorianCalendar) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[7]++;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[11]++;
            GregorianCalendar gc = (GregorianCalendar) object;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[12]++;
            long cutover = gc.getGregorianChange().getTime();
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cutover == Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[9]++;
                return GregorianChronology.getInstance(zone);

            } else {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[10]++;
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[14]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cutover == Long.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[11]++;
                return JulianChronology.getInstance(zone);

            } else {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[12]++;
                return GJChronology.getInstance(zone, cutover, 4);
            }
}

        } else {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.branches[8]++;
            return ISOChronology.getInstance(zone);
        }
}
    }

    /**
     * Gets the millis, which is the Calendar millis value.
     * 
     * @param object  the Calendar to convert, must not be null
     * @param chrono  the chronology result from getChronology, non-null
     * @return the millisecond value
     * @throws NullPointerException if the object is null
     * @throws ClassCastException if the object is an invalid type
     */
    public long getInstantMillis(Object object, Chronology chrono) {
CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch.statements[15]++;
        Calendar calendar = (Calendar) object;
        return calendar.getTime().getTime();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns Calendar.class.
     * 
     * @return Calendar.class
     */
    public Class<?> getSupportedType() {
        return Calendar.class;
    }

}

class CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.joda.time.convert.CalendarConverter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$b20ctgobhl6jnz68ccj01q8mhgwtxlwqch () {
    super("org.joda.time.convert.CalendarConverter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.joda.time.convert.CalendarConverter.java");
      for (int i = 1; i <= 15; i++) {
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

