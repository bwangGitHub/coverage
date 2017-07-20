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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.RemainderDateTimeField;

/**
 * Implements a chronology that follows the rules of the ISO8601 standard,
 * which is compatible with Gregorian for all modern dates.
 * When ISO does not define a field, but it can be determined (such as AM/PM)
 * it is included.
 * <p>
 * With the exception of century related fields, ISOChronology is exactly the
 * same as {@link GregorianChronology}. In this chronology, centuries and year
 * of century are zero based. For all years, the century is determined by
 * dropping the last two digits of the year, ignoring sign. The year of century
 * is the value of the last two year digits.
 * <p>
 * ISOChronology is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class ISOChronology extends AssembledChronology {
  static {
    CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.ping();
  }

    
    /** Serialization lock */
    private static final long serialVersionUID = -6212696554273812441L;
  static {
    CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[1]++;
  }

    /** Singleton instance of a UTC ISOChronology */
    private static final ISOChronology INSTANCE_UTC;
        
    private static final int FAST_CACHE_SIZE = 64;
  static {
    CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[2]++;
  }

    /** Fast cache of zone to chronology */
    private static final ISOChronology[] cFastCache;

    /** Cache of zone to chronology */
    private static final Map<DateTimeZone, ISOChronology> cCache = new HashMap<DateTimeZone, ISOChronology>();
  static {
    CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[3]++;
  }
    static {
        cFastCache = new ISOChronology[FAST_CACHE_SIZE];
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[4]++;
        INSTANCE_UTC = new ISOChronology(GregorianChronology.getInstanceUTC());
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[5]++;
        cCache.put(DateTimeZone.UTC, INSTANCE_UTC);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[6]++;
    }

    /**
     * Gets an instance of the ISOChronology.
     * The time zone of the returned instance is UTC.
     * 
     * @return a singleton UTC instance of the chronology
     */
    public static ISOChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    /**
     * Gets an instance of the ISOChronology in the default time zone.
     * 
     * @return a chronology in the default time zone
     */
    public static ISOChronology getInstance() {
        return getInstance(DateTimeZone.getDefault());
    }

    /**
     * Gets an instance of the ISOChronology in the given time zone.
     * 
     * @param zone  the time zone to get the chronology in, null is default
     * @return a chronology in the specified time zone
     */
    public static ISOChronology getInstance(DateTimeZone zone) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[1]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[8]++;

        } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[2]++;}
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[9]++;
        int index = System.identityHashCode(zone) & (FAST_CACHE_SIZE - 1);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[10]++;
        ISOChronology chrono = cFastCache[index];
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((chrono != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((chrono.getZone() == zone) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[3]++;
            return chrono;

        } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[4]++;}
        synchronized (cCache) {
            chrono = cCache.get(zone);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[12]++;
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[5]++;
                chrono = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, zone));
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[14]++;
                cCache.put(zone, chrono);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[15]++;

            } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[6]++;}
        }
        cFastCache[index] = chrono;
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[16]++;
        return chrono;
    }

    // Constructors and instance variables
    //-----------------------------------------------------------------------

    /**
     * Restricted constructor
     */
    private ISOChronology(Chronology base) {
        super(base, null);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[17]++;
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
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[7]++;
            zone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[19]++;

        } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[8]++;}
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((zone == getZone()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[9]++;
            return this;

        } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[10]++;}
        return getInstance(zone);
    }

    // Output
    //-----------------------------------------------------------------------
    /**
     * Gets a debugging toString.
     * 
     * @return a debugging string
     */
    public String toString() {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[21]++;
        String str = "ISOChronology";
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[22]++;
        DateTimeZone zone = getZone();
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[11]++;
            str = str + '[' + zone.getID() + ']';
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[24]++;

        } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[12]++;}
        return str;
    }

    protected void assemble(Fields fields) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getBase().getZone() == DateTimeZone.UTC) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[13]++;
            // Use zero based century and year of century.
            fields.centuryOfEra = new DividedDateTimeField(
                ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[26]++;
            fields.yearOfCentury = new RemainderDateTimeField(
                (DividedDateTimeField) fields.centuryOfEra, DateTimeFieldType.yearOfCentury());
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[27]++;
            fields.weekyearOfCentury = new RemainderDateTimeField(
                (DividedDateTimeField) fields.centuryOfEra, DateTimeFieldType.weekyearOfCentury());
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[28]++;

            fields.centuries = fields.centuryOfEra.getDurationField();
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[29]++;

        } else {
  CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.branches[14]++;}
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
        return "ISO".hashCode() * 11 + getZone().hashCode();
    }

    /**
     * Serialize ISOChronology instances using a small stub. This reduces the
     * serialized size, and deserialized instances come from the cache.
     */
    private Object writeReplace() {
        return new Stub(getZone());
    }

    private static final class Stub implements Serializable {
        private static final long serialVersionUID = -6212696554273812441L;

        private transient DateTimeZone iZone;

        Stub(DateTimeZone zone) {
            iZone = zone;
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[30]++;
        }

        private Object readResolve() {
            return ISOChronology.getInstance(iZone);
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(iZone);
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[31]++;
        }

        private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException
        {
            iZone = (DateTimeZone)in.readObject();
CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p.statements[32]++;
        }
    }

}

class CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.joda.time.chrono.ISOChronology.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$63imky3pu0839qmfmjup7g2syv6p () {
    super("org.joda.time.chrono.ISOChronology.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.chrono.ISOChronology.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

