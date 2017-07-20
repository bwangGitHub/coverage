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
 * Identifies a field, such as year or minuteOfHour, in a chronology-neutral way.
 * <p>
 * A field type defines the type of the field, such as hourOfDay.
 * If does not directly enable any calculations, however it does provide a
 * {@link #getField(Chronology)} method that returns the actual calculation engine
 * for a particular chronology.
 * It also provides access to the related {@link DurationFieldType}s.
 * <p>
 * Instances of <code>DateTimeFieldType</code> are singletons.
 * They can be compared using <code>==</code>.
 * <p>
 * If required, you can create your own field, for example a quarterOfYear.
 * You must create a subclass of <code>DateTimeFieldType</code> that defines the field type.
 * This class returns the actual calculation engine from {@link #getField(Chronology)}.
 * The subclass should implement equals and hashCode.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class DateTimeFieldType implements Serializable {
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = -42615285973990L;
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[1]++;
  }

    /** Ordinal values for standard field types. */
    static final byte
        ERA = 1,
        YEAR_OF_ERA = 2,
        CENTURY_OF_ERA = 3,
        YEAR_OF_CENTURY = 4,
        YEAR = 5,
        DAY_OF_YEAR = 6,
        MONTH_OF_YEAR = 7,
        DAY_OF_MONTH = 8,
        WEEKYEAR_OF_CENTURY = 9,
        WEEKYEAR = 10,
        WEEK_OF_WEEKYEAR = 11,
        DAY_OF_WEEK = 12,
        HALFDAY_OF_DAY = 13,
        HOUR_OF_HALFDAY = 14,
        CLOCKHOUR_OF_HALFDAY = 15,
        CLOCKHOUR_OF_DAY = 16,
        HOUR_OF_DAY = 17,
        MINUTE_OF_DAY = 18,
        MINUTE_OF_HOUR = 19,
        SECOND_OF_DAY = 20,
        SECOND_OF_MINUTE = 21,
        MILLIS_OF_DAY = 22,
        MILLIS_OF_SECOND = 23;
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[2]++;
  }

    /** The era field type. */
    private static final DateTimeFieldType ERA_TYPE = new StandardDateTimeFieldType(
        "era", ERA, DurationFieldType.eras(), null);
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[3]++;
  }
    /** The yearOfEra field type. */
    private static final DateTimeFieldType YEAR_OF_ERA_TYPE = new StandardDateTimeFieldType(
        "yearOfEra", YEAR_OF_ERA, DurationFieldType.years(), DurationFieldType.eras());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[4]++;
  }
    /** The centuryOfEra field type. */
    private static final DateTimeFieldType CENTURY_OF_ERA_TYPE = new StandardDateTimeFieldType(
        "centuryOfEra", CENTURY_OF_ERA, DurationFieldType.centuries(), DurationFieldType.eras());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[5]++;
  }
    /** The yearOfCentury field type. */
    private static final DateTimeFieldType YEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType(
        "yearOfCentury", YEAR_OF_CENTURY, DurationFieldType.years(), DurationFieldType.centuries());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[6]++;
  }
    /** The year field type. */
    private static final DateTimeFieldType YEAR_TYPE = new StandardDateTimeFieldType(
        "year", YEAR, DurationFieldType.years(), null);
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[7]++;
  }
    /** The dayOfYear field type. */
    private static final DateTimeFieldType DAY_OF_YEAR_TYPE = new StandardDateTimeFieldType(
        "dayOfYear", DAY_OF_YEAR, DurationFieldType.days(), DurationFieldType.years());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[8]++;
  }
    /** The monthOfYear field type. */
    private static final DateTimeFieldType MONTH_OF_YEAR_TYPE = new StandardDateTimeFieldType(
        "monthOfYear", MONTH_OF_YEAR, DurationFieldType.months(), DurationFieldType.years());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[9]++;
  }
    /** The dayOfMonth field type. */
    private static final DateTimeFieldType DAY_OF_MONTH_TYPE = new StandardDateTimeFieldType(
        "dayOfMonth", DAY_OF_MONTH, DurationFieldType.days(), DurationFieldType.months());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[10]++;
  }
    /** The weekyearOfCentury field type. */
    private static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType(
        "weekyearOfCentury", WEEKYEAR_OF_CENTURY, DurationFieldType.weekyears(), DurationFieldType.centuries());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[11]++;
  }
    /** The weekyear field type. */
    private static final DateTimeFieldType WEEKYEAR_TYPE = new StandardDateTimeFieldType(
        "weekyear", WEEKYEAR, DurationFieldType.weekyears(), null);
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[12]++;
  }
    /** The weekOfWeekyear field type. */
    private static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE = new StandardDateTimeFieldType(
        "weekOfWeekyear", WEEK_OF_WEEKYEAR, DurationFieldType.weeks(), DurationFieldType.weekyears());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[13]++;
  }
    /** The dayOfWeek field type. */
    private static final DateTimeFieldType DAY_OF_WEEK_TYPE = new StandardDateTimeFieldType(
        "dayOfWeek", DAY_OF_WEEK, DurationFieldType.days(), DurationFieldType.weeks());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[14]++;
  }

    /** The halfday field type. */
    private static final DateTimeFieldType HALFDAY_OF_DAY_TYPE = new StandardDateTimeFieldType(
        "halfdayOfDay", HALFDAY_OF_DAY, DurationFieldType.halfdays(), DurationFieldType.days());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[15]++;
  }
    /** The hourOfHalfday field type. */
    private static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType(
        "hourOfHalfday", HOUR_OF_HALFDAY, DurationFieldType.hours(), DurationFieldType.halfdays());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[16]++;
  }
    /** The clockhourOfHalfday field type. */
    private static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType(
        "clockhourOfHalfday", CLOCKHOUR_OF_HALFDAY, DurationFieldType.hours(), DurationFieldType.halfdays());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[17]++;
  }
    /** The clockhourOfDay field type. */
    private static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE = new StandardDateTimeFieldType(
        "clockhourOfDay", CLOCKHOUR_OF_DAY, DurationFieldType.hours(), DurationFieldType.days());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[18]++;
  }
    /** The hourOfDay field type. */
    private static final DateTimeFieldType HOUR_OF_DAY_TYPE = new StandardDateTimeFieldType(
        "hourOfDay", HOUR_OF_DAY, DurationFieldType.hours(), DurationFieldType.days());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[19]++;
  }
    /** The minuteOfDay field type. */
    private static final DateTimeFieldType MINUTE_OF_DAY_TYPE = new StandardDateTimeFieldType(
        "minuteOfDay", MINUTE_OF_DAY, DurationFieldType.minutes(), DurationFieldType.days());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[20]++;
  }
    /** The minuteOfHour field type. */
    private static final DateTimeFieldType MINUTE_OF_HOUR_TYPE = new StandardDateTimeFieldType(
        "minuteOfHour", MINUTE_OF_HOUR, DurationFieldType.minutes(), DurationFieldType.hours());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[21]++;
  }
    /** The secondOfDay field type. */
    private static final DateTimeFieldType SECOND_OF_DAY_TYPE = new StandardDateTimeFieldType(
        "secondOfDay", SECOND_OF_DAY, DurationFieldType.seconds(), DurationFieldType.days());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[22]++;
  }
    /** The secondOfMinute field type. */
    private static final DateTimeFieldType SECOND_OF_MINUTE_TYPE = new StandardDateTimeFieldType(
        "secondOfMinute", SECOND_OF_MINUTE, DurationFieldType.seconds(), DurationFieldType.minutes());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[23]++;
  }
    /** The millisOfDay field type. */
    private static final DateTimeFieldType MILLIS_OF_DAY_TYPE = new StandardDateTimeFieldType(
        "millisOfDay", MILLIS_OF_DAY, DurationFieldType.millis(), DurationFieldType.days());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[24]++;
  }
    /** The millisOfSecond field type. */
    private static final DateTimeFieldType MILLIS_OF_SECOND_TYPE = new StandardDateTimeFieldType(
        "millisOfSecond", MILLIS_OF_SECOND, DurationFieldType.millis(), DurationFieldType.seconds());
  static {
    CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[25]++;
  }

    /** The name of the field. */
    private final String iName;

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     * 
     * @param name  the name to use
     */
    protected DateTimeFieldType(String name) {
        super();
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[26]++;
        iName = name;
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[27]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the millis of second field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType millisOfSecond() {
        return MILLIS_OF_SECOND_TYPE;
    }

    /**
     * Get the millis of day field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType millisOfDay() {
        return MILLIS_OF_DAY_TYPE;
    }

    /**
     * Get the second of minute field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType secondOfMinute() {
        return SECOND_OF_MINUTE_TYPE;
    }

    /**
     * Get the second of day field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType secondOfDay() {
        return SECOND_OF_DAY_TYPE;
    }

    /**
     * Get the minute of hour field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType minuteOfHour() {
        return MINUTE_OF_HOUR_TYPE;
    }

    /**
     * Get the minute of day field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType minuteOfDay() {
        return MINUTE_OF_DAY_TYPE;
    }

    /**
     * Get the hour of day (0-23) field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType hourOfDay() {
        return HOUR_OF_DAY_TYPE;
    }

    /**
     * Get the hour of day (offset to 1-24) field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType clockhourOfDay() {
        return CLOCKHOUR_OF_DAY_TYPE;
    }

    /**
     * Get the hour of am/pm (0-11) field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType hourOfHalfday() {
        return HOUR_OF_HALFDAY_TYPE;
    }

    /**
     * Get the hour of am/pm (offset to 1-12) field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType clockhourOfHalfday() {
        return CLOCKHOUR_OF_HALFDAY_TYPE;
    }

    /**
     * Get the AM(0) PM(1) field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType halfdayOfDay() {
        return HALFDAY_OF_DAY_TYPE;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the day of week field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType dayOfWeek() {
        return DAY_OF_WEEK_TYPE;
    }

    /**
     * Get the day of month field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType dayOfMonth() {
        return DAY_OF_MONTH_TYPE;
    }

    /**
     * Get the day of year field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType dayOfYear() {
        return DAY_OF_YEAR_TYPE;
    }

    /**
     * Get the week of a week based year field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType weekOfWeekyear() {
        return WEEK_OF_WEEKYEAR_TYPE;
    }

    /**
     * Get the year of a week based year field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType weekyear() {
        return WEEKYEAR_TYPE;
    }

    /**
     * Get the year of a week based year within a century field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType weekyearOfCentury() {
        return WEEKYEAR_OF_CENTURY_TYPE;
    }

    /**
     * Get the month of year field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType monthOfYear() {
        return MONTH_OF_YEAR_TYPE;
    }

    /**
     * Get the year field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType year() {
        return YEAR_TYPE;
    }

    /**
     * Get the year of era field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType yearOfEra() {
        return YEAR_OF_ERA_TYPE;
    }

    /**
     * Get the year of century field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType yearOfCentury() {
        return YEAR_OF_CENTURY_TYPE;
    }

    /**
     * Get the century of era field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType centuryOfEra() {
        return CENTURY_OF_ERA_TYPE;
    }

    /**
     * Get the era field type.
     * 
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType era() {
        return ERA_TYPE;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the name of the field.
     * <p>
     * By convention, names follow a pattern of "dddOfRrr", where "ddd" represents
     * the (singular) duration unit field name and "Rrr" represents the (singular)
     * duration range field name. If the range field is not applicable, then
     * the name of the field is simply the (singular) duration field name.
     * 
     * @return field name
     */
    public String getName() {
        return iName;
    }

    /**
     * Get the duration unit of the field.
     * 
     * @return duration unit of the field, never null
     */
    public abstract DurationFieldType getDurationType();

    /**
     * Get the duration range of the field.
     * 
     * @return duration range of the field, null if unbounded
     */
    public abstract DurationFieldType getRangeDurationType();

    /**
     * Gets a suitable field for this type from the given Chronology.
     *
     * @param chronology  the chronology to use, null means ISOChronology in default zone
     * @return a suitable field
     */
    public abstract DateTimeField getField(Chronology chronology);

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

    private static class StandardDateTimeFieldType extends DateTimeFieldType {
        /** Serialization version */
        private static final long serialVersionUID = -9937958251642L;

        /** The ordinal of the standard field type, for switch statements */
        private final byte iOrdinal;

        /** The unit duration of the field. */
        private final transient DurationFieldType iUnitType;
        /** The range duration of the field. */
        private final transient DurationFieldType iRangeType;

        /**
         * Constructor.
         * 
         * @param name  the name to use
         * @param ordinal  the byte value for the oridinal index
         * @param unitType  the unit duration type
         * @param rangeType  the range duration type
         */
        StandardDateTimeFieldType(String name, byte ordinal,
                                  DurationFieldType unitType, DurationFieldType rangeType) {
            super(name);
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[28]++;
            iOrdinal = ordinal;
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[29]++;
            iUnitType = unitType;
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[30]++;
            iRangeType = rangeType;
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[31]++;
        }

        /** @inheritdoc */
        public DurationFieldType getDurationType() {
            return iUnitType;
        }

        /** @inheritdoc */
        public DurationFieldType getRangeDurationType() {
            return iRangeType;
        }

        /** @inheritdoc */
        @Override
        public boolean equals(Object obj) {
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[32]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == obj) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[1]++;
                return true;

            } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[2]++;}
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof StandardDateTimeFieldType) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[3]++;
                return iOrdinal == ((StandardDateTimeFieldType) obj).iOrdinal;

            } else {
  CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[4]++;}
            return false;
        }

        /** @inheritdoc */
        @Override
        public int hashCode() {
            return (1 << iOrdinal);
        }

        /** @inheritdoc */
        public DateTimeField getField(Chronology chronology) {
            chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[34]++;
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[35]++;

            switch (iOrdinal) {
                case ERA:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[5]++;
                    return chronology.era();
                case YEAR_OF_ERA:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[6]++;
                    return chronology.yearOfEra();
                case CENTURY_OF_ERA:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[7]++;
                    return chronology.centuryOfEra();
                case YEAR_OF_CENTURY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[8]++;
                    return chronology.yearOfCentury();
                case YEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[9]++;
                    return chronology.year();
                case DAY_OF_YEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[10]++;
                    return chronology.dayOfYear();
                case MONTH_OF_YEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[11]++;
                    return chronology.monthOfYear();
                case DAY_OF_MONTH:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[12]++;
                    return chronology.dayOfMonth();
                case WEEKYEAR_OF_CENTURY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[13]++;
                    return chronology.weekyearOfCentury();
                case WEEKYEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[14]++;
                    return chronology.weekyear();
                case WEEK_OF_WEEKYEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[15]++;
                    return chronology.weekOfWeekyear();
                case DAY_OF_WEEK:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[16]++;
                    return chronology.dayOfWeek();
                case HALFDAY_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[17]++;
                    return chronology.halfdayOfDay();
                case HOUR_OF_HALFDAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[18]++;
                    return chronology.hourOfHalfday();
                case CLOCKHOUR_OF_HALFDAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[19]++;
                    return chronology.clockhourOfHalfday();
                case CLOCKHOUR_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[20]++;
                    return chronology.clockhourOfDay();
                case HOUR_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[21]++;
                    return chronology.hourOfDay();
                case MINUTE_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[22]++;
                    return chronology.minuteOfDay();
                case MINUTE_OF_HOUR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[23]++;
                    return chronology.minuteOfHour();
                case SECOND_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[24]++;
                    return chronology.secondOfDay();
                case SECOND_OF_MINUTE:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[25]++;
                    return chronology.secondOfMinute();
                case MILLIS_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[26]++;
                    return chronology.millisOfDay();
                case MILLIS_OF_SECOND:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[27]++;
                    return chronology.millisOfSecond();
                default:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[28]++;
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
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.statements[36]++;
            switch (iOrdinal) {
                case ERA:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[29]++;
                    return ERA_TYPE;
                case YEAR_OF_ERA:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[30]++;
                    return YEAR_OF_ERA_TYPE;
                case CENTURY_OF_ERA:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[31]++;
                    return CENTURY_OF_ERA_TYPE;
                case YEAR_OF_CENTURY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[32]++;
                    return YEAR_OF_CENTURY_TYPE;
                case YEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[33]++;
                    return YEAR_TYPE;
                case DAY_OF_YEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[34]++;
                    return DAY_OF_YEAR_TYPE;
                case MONTH_OF_YEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[35]++;
                    return MONTH_OF_YEAR_TYPE;
                case DAY_OF_MONTH:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[36]++;
                    return DAY_OF_MONTH_TYPE;
                case WEEKYEAR_OF_CENTURY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[37]++;
                    return WEEKYEAR_OF_CENTURY_TYPE;
                case WEEKYEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[38]++;
                    return WEEKYEAR_TYPE;
                case WEEK_OF_WEEKYEAR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[39]++;
                    return WEEK_OF_WEEKYEAR_TYPE;
                case DAY_OF_WEEK:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[40]++;
                    return DAY_OF_WEEK_TYPE;
                case HALFDAY_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[41]++;
                    return HALFDAY_OF_DAY_TYPE;
                case HOUR_OF_HALFDAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[42]++;
                    return HOUR_OF_HALFDAY_TYPE;
                case CLOCKHOUR_OF_HALFDAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[43]++;
                    return CLOCKHOUR_OF_HALFDAY_TYPE;
                case CLOCKHOUR_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[44]++;
                    return CLOCKHOUR_OF_DAY_TYPE;
                case HOUR_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[45]++;
                    return HOUR_OF_DAY_TYPE;
                case MINUTE_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[46]++;
                    return MINUTE_OF_DAY_TYPE;
                case MINUTE_OF_HOUR:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[47]++;
                    return MINUTE_OF_HOUR_TYPE;
                case SECOND_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[48]++;
                    return SECOND_OF_DAY_TYPE;
                case SECOND_OF_MINUTE:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[49]++;
                    return SECOND_OF_MINUTE_TYPE;
                case MILLIS_OF_DAY:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[50]++;
                    return MILLIS_OF_DAY_TYPE;
                case MILLIS_OF_SECOND:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[51]++;
                    return MILLIS_OF_SECOND_TYPE;
                default:
CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh.branches[52]++;
                    // Shouldn't happen.
                    return this;
            }
        }
    }

}

class CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.DateTimeFieldType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$b7x136z3gysgwnw5l0ych90y6865un5kkh () {
    super("org.joda.time.DateTimeFieldType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DateTimeFieldType.java");
      for (int i = 1; i <= 36; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

