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
package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.field.DelegatedDateTimeField;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.SkipUndoDateTimeField;

/**
 * A chronology that matches the BuddhistCalendar class supplied by Sun.
 * <p>
 * The chronology is identical to the Gregorian/Julian, except that the
 * year is offset by +543 and the era is named 'BE' for Buddhist Era.
 * <p>
 * This class was intended by Sun to model the calendar used in Thailand.
 * However, the actual rules for Thailand are much more involved than
 * this class covers. (This class is accurate after 1941-01-01 ISO).
 * <p>
 * This chronlogy is being retained for those who want a same effect
 * replacement for the Sun class. It is hoped that community support will
 * enable a more accurate chronology for Thailand, to be developed.
 * <p>
 * BuddhistChronology is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class BuddhistChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.ping();
  }

    
    /** Serialization lock */
    private static final long serialVersionUID = -3474595157769370126L;
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[1]++;
  }

    /**
     * Constant value for 'Buddhist Era', equivalent to the value returned
     * for AD/CE. Note that this differs from the constant in BuddhistCalendar.
     */
    public static final int BE = DateTimeConstants.CE;
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[2]++;
  }

    /** A singleton era field. */
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("BE");
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[3]++;
  }

    /** Number of years difference in calendars. */
    private static final int BUDDHIST_OFFSET = 543;
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[4]++;
  }

    /** Cache of zone to chronology */
    private static final Map<DateTimeZone, BuddhistChronology> cCache = new HashMap<DateTimeZone, BuddhistChronology>();
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[5]++;
  }

    /** UTC instance of the chronology */
    private static final BuddhistChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
  static {
    CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[6]++;
  }

    /**
     * Standard instance of a Buddhist Chronology, that matches
     * Sun's BuddhistCalendar class. This means that it follows the
     * GregorianJulian calendar rules with a cutover date.
     * <p>
     * The time zone of the returned instance is UTC.
     */
    public static BuddhistChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Standard instance of a Buddhist Chronology, that matches
     * Sun's BuddhistCalendar class. This means that it follows the
     * GregorianJulian calendar rules with a cutover date.
     */
    public static BuddhistChronology getInstance() {
        return getInstance(DateTimeZone.getDefault());
    }

    /**
     * Standard instance of a Buddhist Chronology, that matches
     * Sun's BuddhistCalendar class. This means that it follows the
     * GregorianJulian calendar rules with a cutover date.
     *
     * @param zone  the time zone to use, null is default
     */
    public static synchronized BuddhistChronology getInstance(DateTimeZone zone) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[1]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[8]++;

        } else {
  CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[2]++;}
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[9]++;
        BuddhistChronology chrono = cCache.get(zone);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[3]++;
            // First create without a lower limit.
            chrono = new BuddhistChronology(GJChronology.getInstance(zone, null), null);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[11]++;
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[12]++;
            // Impose lower limit and make another BuddhistChronology.
            DateTime lowerLimit = new DateTime(1, 1, 1, 0, 0, 0, 0, chrono);
            chrono = new BuddhistChronology(LimitChronology.getInstance(chrono, lowerLimit, null), "");
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[13]++;
            cCache.put(zone, chrono);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[14]++;

        } else {
  CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[4]++;}
        return chrono;
    }

    // Constructors and instance variables
    //-----------------------------------------------------------------------
    
    /**
     * Restricted constructor.
     *
     * @param param if non-null, then don't change the field set
     */
    private BuddhistChronology(Chronology base, Object param) {
        super(base, param);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[15]++;
    }

    /**
     * Serialization singleton
     */
    private Object readResolve() {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[16]++;
        Chronology base = getBase();
        return base == null ? getInstanceUTC() : getInstance(base.getZone());
    }

    // Conversion
    //-----------------------------------------------------------------------
    /**
     * Gets the Chronology in the UTC time zone.
     * 
     * @return the chronology in UTC
     */
    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets the Chronology in a specific time zone.
     * 
     * @param zone  the zone to get the chronology in, null is default
     * @return the chronology
     */
    public Chronology withZone(DateTimeZone zone) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[5]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[18]++;

        } else {
  CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[6]++;}
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[7]++;
            return this;

        } else {
  CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[8]++;}
        return getInstance(zone);
    }

    /**
     * Checks if this chronology instance equals another.
     * 
     * @param obj  the object to compare to
     * @return true if equal
     * @since 1.6
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * A suitable hash code for the chronology.
     * 
     * @return the hash code
     * @since 1.6
     */
    public int hashCode() {
        return "Buddhist".hashCode() * 11 + getZone().hashCode();
    }

    // Output
    //-----------------------------------------------------------------------
    /**
     * Gets a debugging toString.
     * 
     * @return a debugging string
     */
    public String toString() {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[20]++;
        String str = "BuddhistChronology";
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[21]++;
        DateTimeZone zone = getZone();
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[9]++;
            str = str + '[' + zone.getID() + ']';
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[23]++;

        } else {
  CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[10]++;}
        return str;
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((getParam() == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[11]++;
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[25]++;
            // julian chrono removed zero, but we need to put it back
            DateTimeField field = fields.year;
            fields.year = new OffsetDateTimeField(
                    new SkipUndoDateTimeField(this, field), BUDDHIST_OFFSET);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[26]++;
            
            // one era, so yearOfEra is the same
            field = fields.yearOfEra;
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[27]++;
            fields.yearOfEra = new DelegatedDateTimeField(
                fields.year, DateTimeFieldType.yearOfEra());
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[28]++;
            
            // julian chrono removed zero, but we need to put it back
            field = fields.weekyear;
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[29]++;
            fields.weekyear = new OffsetDateTimeField(
                    new SkipUndoDateTimeField(this, field), BUDDHIST_OFFSET);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[30]++;
            
            field = new OffsetDateTimeField(fields.yearOfEra, 99);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[31]++;
            fields.centuryOfEra = new DividedDateTimeField(
                field, DateTimeFieldType.centuryOfEra(), 100);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[32]++;
            
            field = new RemainderDateTimeField(
                (DividedDateTimeField) fields.centuryOfEra);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[33]++;
            fields.yearOfCentury = new OffsetDateTimeField(
                field, DateTimeFieldType.yearOfCentury(), 1);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[34]++;
            
            field = new RemainderDateTimeField(
                fields.weekyear, DateTimeFieldType.weekyearOfCentury(), 100);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[35]++;
            fields.weekyearOfCentury = new OffsetDateTimeField(
                field, DateTimeFieldType.weekyearOfCentury(), 1);
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[36]++;
            
            fields.era = ERA_FIELD;
CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.statements[37]++;

        } else {
  CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1.branches[12]++;}
    }
   
}

class CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1 ());
  }
    public static long[] statements = new long[38];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.BuddhistChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$25jkbiwn8itb6tpo42dy1txjsf3z2adat3s1 () {
    super("org.joda.time.chrono.BuddhistChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 37; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.BuddhistChronology.java");
      for (int i = 1; i <= 37; i++) {
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
    for (int i = 1; i <= 6; i++) {
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

