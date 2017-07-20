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
package org.joda.time;

import java.io.Serializable;

/**
 * Identifies a duration field, such as years or minutes, in a chronology-neutral way.
 * <p>
 * A duration field type defines the type of the field, such as hours.
 * If does not directly enable any calculations, however it does provide a
 * {@link #getField(Chronology)} method that returns the actual calculation engine
 * for a particular chronology.
 * <p>
 * Instances of <code>DurationFieldType</code> are singletons.
 * They can be compared using <code>==</code>.
 * <p>
 * If required, you can create your own field, for example a quarters.
 * You must create a subclass of <code>DurationFieldType</code> that defines the field type.
 * This class returns the actual calculation engine from {@link #getField(Chronology)}.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class DurationFieldType implements Serializable {
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 8765135187319L;
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[1]++;
  }

    // Ordinals for standard field types.
    static final byte
        ERAS = 1,
        CENTURIES = 2,
        WEEKYEARS = 3,
        YEARS = 4,
        MONTHS = 5,
        WEEKS = 6,
        DAYS = 7,
        HALFDAYS = 8,
        HOURS = 9,
        MINUTES = 10,
        SECONDS = 11,
        MILLIS = 12;
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[2]++;
  }

    /** The eras field type. */
    static final DurationFieldType ERAS_TYPE = new StandardDurationFieldType("eras", ERAS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[3]++;
  }
    /** The centuries field type. */
    static final DurationFieldType CENTURIES_TYPE = new StandardDurationFieldType("centuries", CENTURIES);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[4]++;
  }
    /** The weekyears field type. */
    static final DurationFieldType WEEKYEARS_TYPE = new StandardDurationFieldType("weekyears", WEEKYEARS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[5]++;
  }
    /** The years field type. */
    static final DurationFieldType YEARS_TYPE = new StandardDurationFieldType("years", YEARS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[6]++;
  }
    /** The months field type. */
    static final DurationFieldType MONTHS_TYPE = new StandardDurationFieldType("months", MONTHS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[7]++;
  }
    /** The weeks field type. */
    static final DurationFieldType WEEKS_TYPE = new StandardDurationFieldType("weeks", WEEKS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[8]++;
  }
    /** The days field type. */
    static final DurationFieldType DAYS_TYPE = new StandardDurationFieldType("days", DAYS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[9]++;
  }
    /** The halfdays field type. */
    static final DurationFieldType HALFDAYS_TYPE = new StandardDurationFieldType("halfdays", HALFDAYS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[10]++;
  }
    /** The hours field type. */
    static final DurationFieldType HOURS_TYPE = new StandardDurationFieldType("hours", HOURS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[11]++;
  }
    /** The minutes field type. */
    static final DurationFieldType MINUTES_TYPE = new StandardDurationFieldType("minutes", MINUTES);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[12]++;
  }
    /** The seconds field type. */
    static final DurationFieldType SECONDS_TYPE = new StandardDurationFieldType("seconds", SECONDS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[13]++;
  }
    /** The millis field type. */
    static final DurationFieldType MILLIS_TYPE = new StandardDurationFieldType("millis", MILLIS);
  static {
    CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[14]++;
  }

    /** The name of the field type. */
    private final String iName;

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     * 
     * @param name  the name to use, which by convention, are plural.
     */
    protected DurationFieldType(String name) {
        super();
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[15]++;
        iName = name;
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[16]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the millis field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType millis() {
        return MILLIS_TYPE;
    }

    /**
     * Get the seconds field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType seconds() {
        return SECONDS_TYPE;
    }

    /**
     * Get the minutes field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType minutes() {
        return MINUTES_TYPE;
    }

    /**
     * Get the hours field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType hours() {
        return HOURS_TYPE;
    }

    /**
     * Get the halfdays field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType halfdays() {
        return HALFDAYS_TYPE;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the days field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType days() {
        return DAYS_TYPE;
    }

    /**
     * Get the weeks field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType weeks() {
        return WEEKS_TYPE;
    }

    /**
     * Get the weekyears field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType weekyears() {
        return WEEKYEARS_TYPE;
    }

    /**
     * Get the months field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType months() {
        return MONTHS_TYPE;
    }

    /**
     * Get the years field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType years() {
        return YEARS_TYPE;
    }

    /**
     * Get the centuries field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType centuries() {
        return CENTURIES_TYPE;
    }

    /**
     * Get the eras field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DurationFieldType eras() {
        return ERAS_TYPE;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the name of the field.
     * By convention, names are plural.
     * 
     * @return field name
     */
    public String getName() {
        return iName;
    }

    /**
     * Gets a suitable field for this type from the given Chronology.
     *
     * @param chronology  the chronology to use, null means ISOChronology in default zone
     * @return a suitable field
     */
    public abstract DurationField getField(Chronology chronology);

    /**
     * Checks whether this field supported in the given Chronology.
     *
     * @param chronology  the chronology to use, null means ISOChronology in default zone
     * @return true if supported
     */
    public boolean isSupported(Chronology chronology) {
        return getField(chronology).isSupported();
    }

    /**
     * Get a suitable debug string.
     * 
     * @return debug string
     */
    public String toString() {
        return getName();
    }

    private static class StandardDurationFieldType extends DurationFieldType {
        /** Serialization version */
        private static final long serialVersionUID = 31156755687123L;

        /** The ordinal of the standard field type, for switch statements */
        private final byte iOrdinal;

        /**
         * Constructor.
         * 
         * @param name  the name to use
         */
        StandardDurationFieldType(String name, byte ordinal) {
            super(name);
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[17]++;
            iOrdinal = ordinal;
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[18]++;
        }
	
        public int hashCode() {	
	    return (1 << iOrdinal);	
         }

        public DurationField getField(Chronology chronology) {
            chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[19]++;
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[20]++;
            
            switch (iOrdinal) {
                case ERAS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[1]++;
                    return chronology.eras();
                case CENTURIES:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[2]++;
                    return chronology.centuries();
                case WEEKYEARS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[3]++;
                    return chronology.weekyears();
                case YEARS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[4]++;
                    return chronology.years();
                case MONTHS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[5]++;
                    return chronology.months();
                case WEEKS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[6]++;
                    return chronology.weeks();
                case DAYS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[7]++;
                    return chronology.days();
                case HALFDAYS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[8]++;
                    return chronology.halfdays();
                case HOURS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[9]++;
                    return chronology.hours();
                case MINUTES:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[10]++;
                    return chronology.minutes();
                case SECONDS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[11]++;
                    return chronology.seconds();
                case MILLIS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[12]++;
                    return chronology.millis();
                default:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[13]++;
                    // Shouldn't happen.
                    throw new InternalError();
            }
        }

        /**
         * Ensure a singleton is returned.
         * 
         * @return the singleton type
         */
        private Object readResolve() {
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.statements[21]++;
            switch (iOrdinal) {
                case ERAS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[14]++;
                    return ERAS_TYPE;
                case CENTURIES:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[15]++;
                    return CENTURIES_TYPE;
                case WEEKYEARS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[16]++;
                    return WEEKYEARS_TYPE;
                case YEARS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[17]++;
                    return YEARS_TYPE;
                case MONTHS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[18]++;
                    return MONTHS_TYPE;
                case WEEKS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[19]++;
                    return WEEKS_TYPE;
                case DAYS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[20]++;
                    return DAYS_TYPE;
                case HALFDAYS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[21]++;
                    return HALFDAYS_TYPE;
                case HOURS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[22]++;
                    return HOURS_TYPE;
                case MINUTES:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[23]++;
                    return MINUTES_TYPE;
                case SECONDS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[24]++;
                    return SECONDS_TYPE;
                case MILLIS:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[25]++;
                    return MILLIS_TYPE;
                default:
CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht.branches[26]++;
                    // Shouldn't happen.
                    return this;
            }
        }

    }
}

class CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[27];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$b8dmxcnw4aeo0lbsofq7pp2actz5whjyht () {
    super("org.joda.time.DurationFieldType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DurationFieldType.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
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

