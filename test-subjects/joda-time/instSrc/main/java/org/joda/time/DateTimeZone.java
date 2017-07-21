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
package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

/**
 * DateTimeZone represents a time zone.
 * <p>
 * A time zone is a system of rules to convert time from one geographic 
 * location to another. For example, Paris, France is one hour ahead of
 * London, England. Thus when it is 10:00 in London, it is 11:00 in Paris.
 * <p>
 * All time zone rules are expressed, for historical reasons, relative to
 * Greenwich, London. Local time in Greenwich is referred to as Greenwich Mean
 * Time (GMT).  This is similar, but not precisely identical, to Universal 
 * Coordinated Time, or UTC. This library only uses the term UTC.
 * <p>
 * Using this system, America/Los_Angeles is expressed as UTC-08:00, or UTC-07:00
 * in the summer. The offset -08:00 indicates that America/Los_Angeles time is
 * obtained from UTC by adding -08:00, that is, by subtracting 8 hours.
 * <p>
 * The offset differs in the summer because of daylight saving time, or DST.
 * The following definitions of time are generally used:
 * <ul>
 * <li>UTC - The reference time.
 * <li>Standard Time - The local time without a daylight saving time offset.
 * For example, in Paris, standard time is UTC+01:00.
 * <li>Daylight Saving Time - The local time with a daylight saving time 
 * offset. This offset is typically one hour, but not always. It is typically
 * used in most countries away from the equator.  In Paris, daylight saving 
 * time is UTC+02:00.
 * <li>Wall Time - This is what a local clock on the wall reads. This will be
 * either Standard Time or Daylight Saving Time depending on the time of year
 * and whether the location uses Daylight Saving Time.
 * </ul>
 * <p>
 * Unlike the Java TimeZone class, DateTimeZone is immutable. It also only
 * supports long format time zone ids. Thus EST and ECT are not accepted.
 * However, the factory that accepts a TimeZone will attempt to convert from
 * the old short id to a suitable long id.
 * <p>
 * DateTimeZone is thread-safe and immutable, and all subclasses must be as
 * well.
 * 
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class DateTimeZone implements Serializable {
  static {
    CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.ping();
  }

    
    /** Serialization version. */
    private static final long serialVersionUID = 5546345482340108586L;
  static {
    CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[1]++;
  }

    /** The time zone for Universal Coordinated Time */
    public static final DateTimeZone UTC = new FixedDateTimeZone("UTC", "UTC", 0, 0);
  static {
    CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[2]++;
  }

    /** The instance that is providing time zones. */
    private static Provider cProvider;
    /** The instance that is providing time zone names. */
    private static NameProvider cNameProvider;
    /** The set of ID strings. */
    private static Set<String> cAvailableIDs;
    /** The default time zone. */
    private static volatile DateTimeZone cDefault;
    /** A formatter for printing and parsing zones. */
    private static DateTimeFormatter cOffsetFormatter;

    /** Cache that maps fixed offset strings to softly referenced DateTimeZones */
    private static Map<String, SoftReference<DateTimeZone>> iFixedOffsetCache;

    /** Cache of old zone IDs to new zone IDs */
    private static Map<String, String> cZoneIdConversion;

    static {
        setProvider0(null);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[3]++;
        setNameProvider0(null);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[4]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the default time zone.
     * <p>
     * The default time zone is derived from the system property {@code user.timezone}.
     * If that is {@code null} or is not a valid identifier, then the value of the
     * JDK {@code TimeZone} default is converted. If that fails, {@code UTC} is used.
     * <p>
     * NOTE: If the {@code java.util.TimeZone} default is updated <i>after</i> calling this
     * method, then the change will not be picked up here.
     * 
     * @return the default datetime zone object
     */
    public static DateTimeZone getDefault() {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[5]++;
        DateTimeZone zone = cDefault;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[1]++;
            synchronized(DateTimeZone.class) {
                zone = cDefault;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[7]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
                if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[3]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[9]++;
                    DateTimeZone temp = null;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[10]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[11]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[12]++;
                            String id = System.getProperty("user.timezone");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
                            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[7]++;  // null check avoids stack overflow
                                temp = forID(id);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[14]++;

                            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[8]++;}
                        } catch (RuntimeException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[9]++;
                            // ignored
                        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[6]++;
}
  }
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
                        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((temp == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[10]++;
                            temp = forTimeZone(TimeZone.getDefault());
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[16]++;

                        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[11]++;}
                    } catch (IllegalArgumentException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[12]++;
                        // ignored
                    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[5]++;
}
  }
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
                    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((temp == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[13]++;
                        temp = UTC;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[18]++;

                    } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[14]++;}
                    cDefault = zone = temp;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[19]++;

                } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[4]++;}
            }

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[2]++;}
        return zone;
    }

    /**
     * Sets the default time zone.
     * <p>
     * NOTE: Calling this method does <i>not</i> set the {@code java.util.TimeZone} default.
     * 
     * @param zone  the default datetime zone object, must not be null
     * @throws IllegalArgumentException if the zone is null
     * @throws SecurityException if the application has insufficient security rights
     */
    public static void setDefault(DateTimeZone zone) throws SecurityException {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[20]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[15]++;
            sm.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[22]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[16]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[17]++;
            throw new IllegalArgumentException("The datetime zone must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[18]++;}
        synchronized(DateTimeZone.class) {
            cDefault = zone;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[24]++;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a time zone instance for the specified time zone id.
     * <p>
     * The time zone id may be one of those returned by getAvailableIDs.
     * Short ids, as accepted by {@link java.util.TimeZone}, are not accepted.
     * All IDs must be specified in the long format.
     * The exception is UTC, which is an acceptable id.
     * <p>
     * Alternatively a locale independent, fixed offset, datetime zone can
     * be specified. The form <code>[+-]hh:mm</code> can be used.
     * 
     * @param id  the ID of the datetime zone, null means default
     * @return the DateTimeZone object for the ID
     * @throws IllegalArgumentException if the ID is not recognised
     */
    @FromString
    public static DateTimeZone forID(String id) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((id == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[19]++;
            return getDefault();

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[20]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((id.equals("UTC")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[21]++;
            return DateTimeZone.UTC;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[22]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[27]++;
        DateTimeZone zone = cProvider.getZone(id);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[28]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[23]++;
            return zone;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[24]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((id.startsWith("+")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((id.startsWith("-")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[25]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[30]++;
            int offset = parseOffset(id);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[31]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((offset == 0L) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[27]++;
                return DateTimeZone.UTC;

            } else {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[28]++;
                id = printOffset(offset);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[32]++;
                return fixedOffsetZone(id, offset);
            }

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[26]++;}
        throw new IllegalArgumentException("The datetime zone id '" + id + "' is not recognised");
    }

    /**
     * Gets a time zone instance for the specified offset to UTC in hours.
     * This method assumes standard length hours.
     * <p>
     * This factory is a convenient way of constructing zones with a fixed offset.
     * 
     * @param hoursOffset  the offset in hours from UTC
     * @return the DateTimeZone object for the offset
     * @throws IllegalArgumentException if the offset is too large or too small
     */
    public static DateTimeZone forOffsetHours(int hoursOffset) throws IllegalArgumentException {
        return forOffsetHoursMinutes(hoursOffset, 0);
    }

    /**
     * Gets a time zone instance for the specified offset to UTC in hours and minutes.
     * This method assumes 60 minutes in an hour, and standard length minutes.
     * <p>
     * This factory is a convenient way of constructing zones with a fixed offset.
     * The minutes value is always positive and in the range 0 to 59.
     * If constructed with the values (-2, 30), the resulting zone is '-02:30'.
     * 
     * @param hoursOffset  the offset in hours from UTC
     * @param minutesOffset  the offset in minutes from UTC, must be between 0 and 59 inclusive
     * @return the DateTimeZone object for the offset
     * @throws IllegalArgumentException if the offset or minute is too large or too small
     */
    public static DateTimeZone forOffsetHoursMinutes(int hoursOffset, int minutesOffset) throws IllegalArgumentException {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((hoursOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((minutesOffset == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[29]++;
            return DateTimeZone.UTC;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[30]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[34]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((minutesOffset < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((minutesOffset > 59) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[31]++;
            throw new IllegalArgumentException("Minutes out of range: " + minutesOffset);

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[32]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[35]++;
        int offset = 0;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[36]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[37]++;
            int hoursInMinutes = FieldUtils.safeMultiply(hoursOffset, 60);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((hoursInMinutes < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[34]++;
                minutesOffset = FieldUtils.safeAdd(hoursInMinutes, -minutesOffset);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[39]++;

            } else {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[35]++;
                minutesOffset = FieldUtils.safeAdd(hoursInMinutes, minutesOffset);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[40]++;
            }
            offset = FieldUtils.safeMultiply(minutesOffset, DateTimeConstants.MILLIS_PER_MINUTE);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[41]++;
        } catch (ArithmeticException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[36]++;
            throw new IllegalArgumentException("Offset is too large");
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[33]++;
}
  }
        return forOffsetMillis(offset);
    }

    /**
     * Gets a time zone instance for the specified offset to UTC in milliseconds.
     *
     * @param millisOffset  the offset in millis from UTC
     * @return the DateTimeZone object for the offset
     */
    public static DateTimeZone forOffsetMillis(int millisOffset) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[42]++;
        String id = printOffset(millisOffset);
        return fixedOffsetZone(id, millisOffset);
    }

    /**
     * Gets a time zone instance for a JDK TimeZone.
     * <p>
     * DateTimeZone only accepts a subset of the IDs from TimeZone. The
     * excluded IDs are the short three letter form (except UTC). This 
     * method will attempt to convert between time zones created using the
     * short IDs and the full version.
     * <p>
     * This method is not designed to parse time zones with rules created by
     * applications using <code>SimpleTimeZone</code> directly.
     * 
     * @param zone  the zone to convert, null means default
     * @return the DateTimeZone object for the zone
     * @throws IllegalArgumentException if the zone is not recognised
     */
    public static DateTimeZone forTimeZone(TimeZone zone) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[37]++;
            return getDefault();

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[38]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[44]++;
        final String id = zone.getID();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((id.equals("UTC")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[39]++;
            return DateTimeZone.UTC;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[40]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[46]++;

        // Convert from old alias before consulting provider since they may differ.
        DateTimeZone dtz = null;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[47]++;
        String convId = getConvertedId(id);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((convId != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[41]++;
            dtz = cProvider.getZone(convId);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[49]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[42]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[50]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dtz == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[43]++;
            dtz = cProvider.getZone(id);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[51]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[44]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[52]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((dtz != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[45]++;
            return dtz;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[46]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[53]++;
int CodeCoverConditionCoverageHelper_C21;

        // Support GMT+/-hh:mm formats
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((convId == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[47]++;
            convId = zone.getDisplayName();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[54]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[55]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((convId.startsWith("GMT+")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((convId.startsWith("GMT-")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[49]++;
                convId = convId.substring(3);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[56]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[57]++;
                int offset = parseOffset(convId);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[58]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((offset == 0L) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[51]++;
                    return DateTimeZone.UTC;

                } else {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[52]++;
                    convId = printOffset(offset);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[59]++;
                    return fixedOffsetZone(convId, offset);
                }

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[50]++;}

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[48]++;}
        throw new IllegalArgumentException("The datetime zone id '" + id + "' is not recognised");
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the zone using a fixed offset amount.
     * 
     * @param id  the zone id
     * @param offset  the offset in millis
     * @return the zone
     */
    private static synchronized DateTimeZone fixedOffsetZone(String id, int offset) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[60]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((offset == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[53]++;
            return DateTimeZone.UTC;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[54]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[61]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iFixedOffsetCache == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[55]++;
            iFixedOffsetCache = new HashMap<String, SoftReference<DateTimeZone>>();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[62]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[56]++;}
        DateTimeZone zone;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[63]++;
        Reference<DateTimeZone> ref = iFixedOffsetCache.get(id);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[64]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((ref != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[57]++;
            zone = ref.get();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[65]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[66]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((zone != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[59]++;
                return zone;

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[60]++;}

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[58]++;}
        zone = new FixedDateTimeZone(id, null, offset, offset);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[67]++;
        iFixedOffsetCache.put(id, new SoftReference<DateTimeZone>(zone));
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[68]++;
        return zone;
    }

    /**
     * Gets all the available IDs supported.
     * 
     * @return an unmodifiable Set of String IDs
     */
    public static Set<String> getAvailableIDs() {
        return cAvailableIDs;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the zone provider factory.
     * <p>
     * The zone provider is a pluggable instance factory that supplies the
     * actual instances of DateTimeZone.
     * 
     * @return the provider
     */
    public static Provider getProvider() {
        return cProvider;
    }

    /**
     * Sets the zone provider factory.
     * <p>
     * The zone provider is a pluggable instance factory that supplies the
     * actual instances of DateTimeZone.
     * 
     * @param provider  provider to use, or null for default
     * @throws SecurityException if you do not have the permission DateTimeZone.setProvider
     * @throws IllegalArgumentException if the provider is invalid
     */
    public static void setProvider(Provider provider) throws SecurityException {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[69]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[70]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[61]++;
            sm.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[71]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[62]++;}
        setProvider0(provider);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[72]++;
    }

    /**
     * Sets the zone provider factory without performing the security check.
     * 
     * @param provider  provider to use, or null for default
     * @throws IllegalArgumentException if the provider is invalid
     */
    private static void setProvider0(Provider provider) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[73]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((provider == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[63]++;
            provider = getDefaultProvider();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[74]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[64]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[75]++;
        Set<String> ids = provider.getAvailableIDs();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[76]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((ids == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((ids.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[65]++;
            throw new IllegalArgumentException
                ("The provider doesn't have any available ids");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[66]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[77]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ids.contains("UTC")) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[67]++;
            throw new IllegalArgumentException("The provider doesn't support UTC");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[68]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[78]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((UTC.equals(provider.getZone("UTC"))) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[69]++;
            throw new IllegalArgumentException("Invalid UTC zone provided");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[70]++;}
        cProvider = provider;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[79]++;
        cAvailableIDs = ids;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[80]++;
    }

    /**
     * Gets the default zone provider.
     * <p>
     * Tries the system property <code>org.joda.time.DateTimeZone.Provider</code>.
     * Then tries a <code>ZoneInfoProvider</code> using the data in <code>org/joda/time/tz/data</code>.
     * Then uses <code>UTCProvider</code>.
     * 
     * @return the default name provider
     */
    private static Provider getDefaultProvider() {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[81]++;
        Provider provider = null;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[82]++;
boolean CodeCoverTryBranchHelper_Try4 = false;

        try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[83]++;
            String providerClass =
                System.getProperty("org.joda.time.DateTimeZone.Provider");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[84]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((providerClass != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[72]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[85]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                try {
CodeCoverTryBranchHelper_Try5 = true;
                    provider = (Provider) Class.forName(providerClass).newInstance();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[86]++;
                } catch (Exception ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[75]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[87]++;
                    Thread thread = Thread.currentThread();
                    thread.getThreadGroup().uncaughtException(thread, ex);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[88]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[74]++;
}
  }

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[73]++;}
        } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[76]++;
            // ignored
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[71]++;
}
  }
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[89]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((provider == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[77]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[90]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
            try {
CodeCoverTryBranchHelper_Try6 = true;
                provider = new ZoneInfoProvider("org/joda/time/tz/data");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[91]++;
            } catch (Exception ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[80]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[92]++;
                Thread thread = Thread.currentThread();
                thread.getThreadGroup().uncaughtException(thread, ex);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[93]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[79]++;
}
  }

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[78]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[94]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((provider == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[81]++;
            provider = new UTCProvider();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[95]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[82]++;}

        return provider;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the name provider factory.
     * <p>
     * The name provider is a pluggable instance factory that supplies the
     * names of each DateTimeZone.
     * 
     * @return the provider
     */
    public static NameProvider getNameProvider() {
        return cNameProvider;
    }

    /**
     * Sets the name provider factory.
     * <p>
     * The name provider is a pluggable instance factory that supplies the
     * names of each DateTimeZone.
     * 
     * @param nameProvider  provider to use, or null for default
     * @throws SecurityException if you do not have the permission DateTimeZone.setNameProvider
     * @throws IllegalArgumentException if the provider is invalid
     */
    public static void setNameProvider(NameProvider nameProvider) throws SecurityException {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[96]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[97]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[83]++;
            sm.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[98]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[84]++;}
        setNameProvider0(nameProvider);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[99]++;
    }

    /**
     * Sets the name provider factory without performing the security check.
     * 
     * @param nameProvider  provider to use, or null for default
     * @throws IllegalArgumentException if the provider is invalid
     */
    private static void setNameProvider0(NameProvider nameProvider) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[100]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((nameProvider == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[85]++;
            nameProvider = getDefaultNameProvider();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[101]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[86]++;}
        cNameProvider = nameProvider;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[102]++;
    }

    /**
     * Gets the default name provider.
     * <p>
     * Tries the system property <code>org.joda.time.DateTimeZone.NameProvider</code>.
     * Then uses <code>DefaultNameProvider</code>.
     * 
     * @return the default name provider
     */
    private static NameProvider getDefaultNameProvider() {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[103]++;
        NameProvider nameProvider = null;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[104]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
        try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[105]++;
            String providerClass = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[106]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((providerClass != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[88]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[107]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
                try {
CodeCoverTryBranchHelper_Try8 = true;
                    nameProvider = (NameProvider) Class.forName(providerClass).newInstance();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[108]++;
                } catch (Exception ex) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[91]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[109]++;
                    Thread thread = Thread.currentThread();
                    thread.getThreadGroup().uncaughtException(thread, ex);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[110]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[90]++;
}
  }

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[89]++;}
        } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[92]++;
            // ignore
        } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[87]++;
}
  }
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[111]++;
int CodeCoverConditionCoverageHelper_C39;

        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((nameProvider == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[93]++;
            nameProvider = new DefaultNameProvider();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[112]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[94]++;}

        return nameProvider;
    }

    //-----------------------------------------------------------------------
    /**
     * Converts an old style id to a new style id.
     * 
     * @param id  the old style id
     * @return the new style id, null if not found
     */
    private static synchronized String getConvertedId(String id) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[113]++;
        Map<String, String> map = cZoneIdConversion;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[114]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((map == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[95]++;
            // Backwards compatibility with TimeZone.
            map = new HashMap<String, String>();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[115]++;
            map.put("GMT", "UTC");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[116]++;
            map.put("WET", "WET");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[117]++;
            map.put("CET", "CET");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[118]++;
            map.put("MET", "CET");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[119]++;
            map.put("ECT", "CET");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[120]++;
            map.put("EET", "EET");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[121]++;
            map.put("MIT", "Pacific/Apia");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[122]++;
            map.put("HST", "Pacific/Honolulu");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[123]++;  // JDK 1.1 compatible
            map.put("AST", "America/Anchorage");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[124]++;
            map.put("PST", "America/Los_Angeles");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[125]++;
            map.put("MST", "America/Denver");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[126]++;  // JDK 1.1 compatible
            map.put("PNT", "America/Phoenix");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[127]++;
            map.put("CST", "America/Chicago");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[128]++;
            map.put("EST", "America/New_York");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[129]++;  // JDK 1.1 compatible
            map.put("IET", "America/Indiana/Indianapolis");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[130]++;
            map.put("PRT", "America/Puerto_Rico");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[131]++;
            map.put("CNT", "America/St_Johns");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[132]++;
            map.put("AGT", "America/Argentina/Buenos_Aires");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[133]++;
            map.put("BET", "America/Sao_Paulo");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[134]++;
            map.put("ART", "Africa/Cairo");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[135]++;
            map.put("CAT", "Africa/Harare");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[136]++;
            map.put("EAT", "Africa/Addis_Ababa");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[137]++;
            map.put("NET", "Asia/Yerevan");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[138]++;
            map.put("PLT", "Asia/Karachi");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[139]++;
            map.put("IST", "Asia/Kolkata");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[140]++;
            map.put("BST", "Asia/Dhaka");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[141]++;
            map.put("VST", "Asia/Ho_Chi_Minh");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[142]++;
            map.put("CTT", "Asia/Shanghai");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[143]++;
            map.put("JST", "Asia/Tokyo");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[144]++;
            map.put("ACT", "Australia/Darwin");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[145]++;
            map.put("AET", "Australia/Sydney");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[146]++;
            map.put("SST", "Pacific/Guadalcanal");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[147]++;
            map.put("NST", "Pacific/Auckland");
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[148]++;
            cZoneIdConversion = map;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[149]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[96]++;}
        return map.get(id);
    }

    private static int parseOffset(String str) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[150]++;
        // Can't use a real chronology if called during class
        // initialization. Offset parser doesn't need it anyhow.
        Chronology chrono = new BaseChronology() {
            public DateTimeZone getZone() {
                return null;
            }
            public Chronology withUTC() {
                return this;
            }
            public Chronology withZone(DateTimeZone zone) {
                return this;
            }
            public String toString() {
                return getClass().getName();
            }
        };
        return -(int) offsetFormatter().withChronology(chrono).parseMillis(str);
    }

    /**
     * Formats a timezone offset string.
     * <p>
     * This method is kept separate from the formatting classes to speed and
     * simplify startup and classloading.
     * 
     * @param offset  the offset in milliseconds
     * @return the time zone string
     */
    private static String printOffset(int offset) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[151]++;
        StringBuffer buf = new StringBuffer();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[152]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((offset >= 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[97]++;
            buf.append('+');
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[153]++;

        } else {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[98]++;
            buf.append('-');
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[154]++;
            offset = -offset;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[155]++;
        }
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[156]++;

        int hours = offset / DateTimeConstants.MILLIS_PER_HOUR;
        FormatUtils.appendPaddedInteger(buf, hours, 2);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[157]++;
        offset -= hours * (int) DateTimeConstants.MILLIS_PER_HOUR;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[158]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[159]++;

        int minutes = offset / DateTimeConstants.MILLIS_PER_MINUTE;
        buf.append(':');
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[160]++;
        FormatUtils.appendPaddedInteger(buf, minutes, 2);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[161]++;
        offset -= minutes * DateTimeConstants.MILLIS_PER_MINUTE;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[162]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[163]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((offset == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[99]++;
            return buf.toString();

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[100]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[164]++;

        int seconds = offset / DateTimeConstants.MILLIS_PER_SECOND;
        buf.append(':');
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[165]++;
        FormatUtils.appendPaddedInteger(buf, seconds, 2);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[166]++;
        offset -= seconds * DateTimeConstants.MILLIS_PER_SECOND;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[167]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[168]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((offset == 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[101]++;
            return buf.toString();

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[102]++;}

        buf.append('.');
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[169]++;
        FormatUtils.appendPaddedInteger(buf, offset, 3);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[170]++;
        return buf.toString();
    }

    /**
     * Gets a printer/parser for managing the offset id formatting.
     * 
     * @return the formatter
     */
    private static synchronized DateTimeFormatter offsetFormatter() {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[171]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((cOffsetFormatter == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[103]++;
            cOffsetFormatter = new DateTimeFormatterBuilder()
                .appendTimeZoneOffset(null, true, 2, 4)
                .toFormatter();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[172]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[104]++;}
        return cOffsetFormatter;
    }

    // Instance fields and methods
    //--------------------------------------------------------------------

    private final String iID;

    /**
     * Constructor.
     * 
     * @param id  the id to use
     * @throws IllegalArgumentException if the id is null
     */
    protected DateTimeZone(String id) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[173]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((id == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[105]++;
            throw new IllegalArgumentException("Id must not be null");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[106]++;}
        iID = id;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[174]++;
    }

    // Principal methods
    //--------------------------------------------------------------------

    /**
     * Gets the ID of this datetime zone.
     * 
     * @return the ID of this datetime zone
     */
    @ToString
    public final String getID() {
        return iID;
    }

    /**
     * Returns a non-localized name that is unique to this time zone. It can be
     * combined with id to form a unique key for fetching localized names.
     *
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the name for
     * @return name key or null if id should be used for names
     */
    public abstract String getNameKey(long instant);

    /**
     * Gets the short name of this datetime zone suitable for display using
     * the default locale.
     * <p>
     * If the name is not available for the locale, then this method returns a
     * string in the format <code>[+-]hh:mm</code>.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the name for
     * @return the human-readable short name in the default locale
     */
    public final String getShortName(long instant) {
        return getShortName(instant, null);
    }

    /**
     * Gets the short name of this datetime zone suitable for display using
     * the specified locale.
     * <p>
     * If the name is not available for the locale, then this method returns a
     * string in the format <code>[+-]hh:mm</code>.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the name for
     * @param locale  the locale to get the name for
     * @return the human-readable short name in the specified locale
     */
    public String getShortName(long instant, Locale locale) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[175]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[107]++;
            locale = Locale.getDefault();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[176]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[108]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[177]++;
        String nameKey = getNameKey(instant);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[178]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((nameKey == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[109]++;
            return iID;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[110]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[179]++;
        String name = cNameProvider.getShortName(locale, iID, nameKey);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[180]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[111]++;
            return name;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[112]++;}
        return printOffset(getOffset(instant));
    }

    /**
     * Gets the long name of this datetime zone suitable for display using
     * the default locale.
     * <p>
     * If the name is not available for the locale, then this method returns a
     * string in the format <code>[+-]hh:mm</code>.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the name for
     * @return the human-readable long name in the default locale
     */
    public final String getName(long instant) {
        return getName(instant, null);
    }

    /**
     * Gets the long name of this datetime zone suitable for display using
     * the specified locale.
     * <p>
     * If the name is not available for the locale, then this method returns a
     * string in the format <code>[+-]hh:mm</code>.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the name for
     * @param locale  the locale to get the name for
     * @return the human-readable long name in the specified locale
     */
    public String getName(long instant, Locale locale) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[181]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[113]++;
            locale = Locale.getDefault();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[182]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[114]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[183]++;
        String nameKey = getNameKey(instant);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[184]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((nameKey == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[115]++;
            return iID;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[116]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[185]++;
        String name = cNameProvider.getName(locale, iID, nameKey);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[186]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[117]++;
            return name;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[118]++;}
        return printOffset(getOffset(instant));
    }

    /**
     * Gets the millisecond offset to add to UTC to get local time.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the offset for
     * @return the millisecond offset to add to UTC to get local time
     */
    public abstract int getOffset(long instant);

    /**
     * Gets the millisecond offset to add to UTC to get local time.
     * 
     * @param instant  instant to get the offset for, null means now
     * @return the millisecond offset to add to UTC to get local time
     */
    public final int getOffset(ReadableInstant instant) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[187]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[119]++;
            return getOffset(DateTimeUtils.currentTimeMillis());

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[120]++;}
        return getOffset(instant.getMillis());
    }

    /**
     * Gets the standard millisecond offset to add to UTC to get local time,
     * when standard time is in effect.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the offset for
     * @return the millisecond offset to add to UTC to get local time
     */
    public abstract int getStandardOffset(long instant);

    /**
     * Checks whether, at a particular instant, the offset is standard or not.
     * <p>
     * This method can be used to determine whether Summer Time (DST) applies.
     * As a general rule, if the offset at the specified instant is standard,
     * then either Winter time applies, or there is no Summer Time. If the
     * instant is not standard, then Summer Time applies.
     * <p>
     * The implementation of the method is simply whether {@link #getOffset(long)}
     * equals {@link #getStandardOffset(long)} at the specified instant.
     * 
     * @param instant  milliseconds from 1970-01-01T00:00:00Z to get the offset for
     * @return true if the offset at the given instant is the standard offset
     * @since 1.5
     */
    public boolean isStandardOffset(long instant) {
        return getOffset(instant) == getStandardOffset(instant);
    }

    /**
     * Gets the millisecond offset to subtract from local time to get UTC time.
     * This offset can be used to undo adding the offset obtained by getOffset.
     *
     * <pre>
     * millisLocal == millisUTC   + getOffset(millisUTC)
     * millisUTC   == millisLocal - getOffsetFromLocal(millisLocal)
     * </pre>
     *
     * NOTE: After calculating millisLocal, some error may be introduced. At
     * offset transitions (due to DST or other historical changes), ranges of
     * local times may map to different UTC times.
     * <p>
     * This method will return an offset suitable for calculating an instant
     * after any DST gap. For example, consider a zone with a cutover
     * from 01:00 to 01:59:<br />
     * Input: 00:00  Output: 00:00<br />
     * Input: 00:30  Output: 00:30<br />
     * Input: 01:00  Output: 02:00<br />
     * Input: 01:30  Output: 02:30<br />
     * Input: 02:00  Output: 02:00<br />
     * Input: 02:30  Output: 02:30<br />
     * <p>
     * During a DST overlap (where the local time is ambiguous) this method will return
     * the earlier instant. The combination of these two rules is to always favour
     * daylight (summer) time over standard (winter) time.
     * <p>
     * NOTE: Prior to v2.0, the DST overlap behaviour was not defined and varied by hemisphere.
     * Prior to v1.5, the DST gap behaviour was also not defined.
     *
     * @param instantLocal  the millisecond instant, relative to this time zone, to get the offset for
     * @return the millisecond offset to subtract from local time to get UTC time
     */
    public int getOffsetFromLocal(long instantLocal) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[188]++;
        // get the offset at instantLocal (first estimate)
        final int offsetLocal = getOffset(instantLocal);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[189]++;
        // adjust instantLocal using the estimate and recalc the offset
        final long instantAdjusted = instantLocal - offsetLocal;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[190]++;
        final int offsetAdjusted = getOffset(instantAdjusted);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[191]++;
int CodeCoverConditionCoverageHelper_C53;
        // if the offsets differ, we must be near a DST boundary
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((offsetLocal != offsetAdjusted) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[121]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[192]++;
int CodeCoverConditionCoverageHelper_C54;
            // we need to ensure that time is always after the DST gap
            // this happens naturally for positive offsets, but not for negative
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 (((offsetLocal - offsetAdjusted) < 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[123]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[193]++;
                // if we just return offsetAdjusted then the time is pushed
                // back before the transition, whereas it should be
                // on or after the transition
                long nextLocal = nextTransition(instantAdjusted);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[194]++;
                long nextAdjusted = nextTransition(instantLocal - offsetAdjusted);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[195]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((nextLocal != nextAdjusted) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[125]++;
                    return offsetLocal;

                } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[126]++;}

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[124]++;}

        } else {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[122]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[196]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((offsetLocal > 0) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[127]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[197]++;
            long prev = previousTransition(instantAdjusted);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[198]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((prev < instantAdjusted) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[129]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[199]++;
                int offsetPrev = getOffset(prev);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[200]++;
                int diff = offsetPrev - offsetLocal;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[201]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((instantAdjusted - prev <= diff) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[131]++;
                    return offsetPrev;

                } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[132]++;}

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[130]++;}

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[128]++;}
}
        return offsetAdjusted;
    }

    /**
     * Converts a standard UTC instant to a local instant with the same
     * local time. This conversion is used before performing a calculation
     * so that the calculation can be done using a simple local zone.
     *
     * @param instantUTC  the UTC instant to convert to local
     * @return the local instant with the same local time
     * @throws ArithmeticException if the result overflows a long
     * @since 1.5
     */
    public long convertUTCToLocal(long instantUTC) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[202]++;
        int offset = getOffset(instantUTC);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[203]++;
        long instantLocal = instantUTC + offset;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[204]++;
int CodeCoverConditionCoverageHelper_C59;
        // If there is a sign change, but the two values have the same sign...
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 (((instantUTC ^ instantLocal) < 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 (((instantUTC ^ offset) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[133]++;
            throw new ArithmeticException("Adding time zone offset caused overflow");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[134]++;}
        return instantLocal;
    }

    /**
     * Converts a local instant to a standard UTC instant with the same
     * local time attempting to use the same offset as the original.
     * <p>
     * This conversion is used after performing a calculation
     * where the calculation was done using a simple local zone.
     * Whenever possible, the same offset as the original offset will be used.
     * This is most significant during a daylight savings overlap.
     *
     * @param instantLocal  the local instant to convert to UTC
     * @param strict  whether the conversion should reject non-existent local times
     * @param originalInstantUTC  the original instant that the calculation is based on
     * @return the UTC instant with the same local time, 
     * @throws ArithmeticException if the result overflows a long
     * @throws IllegalArgumentException if the zone has no equivalent local time
     * @since 2.0
     */
    public long convertLocalToUTC(long instantLocal, boolean strict, long originalInstantUTC) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[205]++;
        int offsetOriginal = getOffset(originalInstantUTC);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[206]++;
        long instantUTC = instantLocal - offsetOriginal;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[207]++;
        int offsetLocalFromOriginal = getOffset(instantUTC);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[208]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((offsetLocalFromOriginal == offsetOriginal) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[135]++;
            return instantUTC;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[136]++;}
        return convertLocalToUTC(instantLocal, strict);
    }

    /**
     * Converts a local instant to a standard UTC instant with the same
     * local time. This conversion is used after performing a calculation
     * where the calculation was done using a simple local zone.
     *
     * @param instantLocal  the local instant to convert to UTC
     * @param strict  whether the conversion should reject non-existent local times
     * @return the UTC instant with the same local time, 
     * @throws ArithmeticException if the result overflows a long
     * @throws IllegalArgumentException if the zone has no equivalent local time
     * @since 1.5
     */
    public long convertLocalToUTC(long instantLocal, boolean strict) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[209]++;
        // get the offset at instantLocal (first estimate)
        int offsetLocal = getOffset(instantLocal);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[210]++;
        // adjust instantLocal using the estimate and recalc the offset
        int offset = getOffset(instantLocal - offsetLocal);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[211]++;
int CodeCoverConditionCoverageHelper_C61;
        // if the offsets differ, we must be near a DST boundary
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((offsetLocal != offset) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[137]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[212]++;
int CodeCoverConditionCoverageHelper_C62;
            // if strict then always check if in DST gap
            // otherwise only check if zone in Western hemisphere (as the
            // value of offset is already correct for Eastern hemisphere)
            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((strict) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((offsetLocal < 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[139]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[213]++;
                // determine if we are in the DST gap
                long nextLocal = nextTransition(instantLocal - offsetLocal);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[214]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((nextLocal == (instantLocal - offsetLocal)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[141]++;
                    nextLocal = Long.MAX_VALUE;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[215]++;

                } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[142]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[216]++;
                long nextAdjusted = nextTransition(instantLocal - offset);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[217]++;
int CodeCoverConditionCoverageHelper_C64;
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((nextAdjusted == (instantLocal - offset)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[143]++;
                    nextAdjusted = Long.MAX_VALUE;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[218]++;

                } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[144]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[219]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((nextLocal != nextAdjusted) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[145]++;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[220]++;
int CodeCoverConditionCoverageHelper_C66;
                    // yes we are in the DST gap
                    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((strict) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[147]++;
                        // DST gap is not acceptable
                        throw new IllegalArgumentException("Illegal instant due to time zone offset transition: " +
                                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(instantLocal)) +
                                " (" + getID() + ")");

                    } else {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[148]++;
                        // DST gap is acceptable, but for the Western hemisphere
                        // the offset is wrong and will result in local times
                        // before the cutover so use the offsetLocal instead
                        offset = offsetLocal;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[221]++;
                    }

                } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[146]++;}

            } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[140]++;}

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[138]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[222]++;
        // check for overflow
        long instantUTC = instantLocal - offset;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[223]++;
int CodeCoverConditionCoverageHelper_C67;
        // If there is a sign change, but the two values have different signs...
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 (((instantLocal ^ instantUTC) < 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 (((instantLocal ^ offset) < 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[149]++;
            throw new ArithmeticException("Subtracting time zone offset caused overflow");

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[150]++;}
        return instantUTC;
    }

    /**
     * Gets the millisecond instant in another zone keeping the same local time.
     * <p>
     * The conversion is performed by converting the specified UTC millis to local
     * millis in this zone, then converting back to UTC millis in the new zone.
     *
     * @param newZone  the new zone, null means default
     * @param oldInstant  the UTC millisecond instant to convert
     * @return the UTC millisecond instant with the same local time in the new zone
     */
    public long getMillisKeepLocal(DateTimeZone newZone, long oldInstant) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[224]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((newZone == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[151]++;
            newZone = DateTimeZone.getDefault();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[225]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[152]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[226]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((newZone == this) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[153]++;
            return oldInstant;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[154]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[227]++;
        long instantLocal = convertUTCToLocal(oldInstant);
        return newZone.convertLocalToUTC(instantLocal, false, oldInstant);
    }

//    //-----------------------------------------------------------------------
//    /**
//     * Checks if the given {@link LocalDateTime} is within an overlap.
//     * <p>
//     * When switching from Daylight Savings Time to standard time there is
//     * typically an overlap where the same clock hour occurs twice. This
//     * method identifies whether the local datetime refers to such an overlap.
//     * 
//     * @param localDateTime  the time to check, not null
//     * @return true if the given datetime refers to an overlap
//     */
//    public boolean isLocalDateTimeOverlap(LocalDateTime localDateTime) {
//        if (isFixed()) {
//            return false;
//        }
//        long instantLocal = localDateTime.toDateTime(DateTimeZone.UTC).getMillis();
//        // get the offset at instantLocal (first estimate)
//        int offsetLocal = getOffset(instantLocal);
//        // adjust instantLocal using the estimate and recalc the offset
//        int offset = getOffset(instantLocal - offsetLocal);
//        // if the offsets differ, we must be near a DST boundary
//        if (offsetLocal != offset) {
//            long nextLocal = nextTransition(instantLocal - offsetLocal);
//            long nextAdjusted = nextTransition(instantLocal - offset);
//            if (nextLocal != nextAdjusted) {
//                // in DST gap
//                return false;
//            }
//            long diff = Math.abs(offset - offsetLocal);
//            DateTime dateTime = localDateTime.toDateTime(this);
//            DateTime adjusted = dateTime.plus(diff);
//            if (dateTime.getHourOfDay() == adjusted.getHourOfDay() &&
//                    dateTime.getMinuteOfHour() == adjusted.getMinuteOfHour() &&
//                    dateTime.getSecondOfMinute() == adjusted.getSecondOfMinute()) {
//                return true;
//            }
//            adjusted = dateTime.minus(diff);
//            if (dateTime.getHourOfDay() == adjusted.getHourOfDay() &&
//                    dateTime.getMinuteOfHour() == adjusted.getMinuteOfHour() &&
//                    dateTime.getSecondOfMinute() == adjusted.getSecondOfMinute()) {
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }
//        
//        
//        DateTime dateTime = null;
//        try {
//            dateTime = localDateTime.toDateTime(this);
//        } catch (IllegalArgumentException ex) {
//            return false;  // it is a gap, not an overlap
//        }
//        long offset1 = Math.abs(getOffset(dateTime.getMillis() + 1) - getStandardOffset(dateTime.getMillis() + 1));
//        long offset2 = Math.abs(getOffset(dateTime.getMillis() - 1) - getStandardOffset(dateTime.getMillis() - 1));
//        long offset = Math.max(offset1, offset2);
//        if (offset == 0) {
//            return false;
//        }
//        DateTime adjusted = dateTime.plus(offset);
//        if (dateTime.getHourOfDay() == adjusted.getHourOfDay() &&
//                dateTime.getMinuteOfHour() == adjusted.getMinuteOfHour() &&
//                dateTime.getSecondOfMinute() == adjusted.getSecondOfMinute()) {
//            return true;
//        }
//        adjusted = dateTime.minus(offset);
//        if (dateTime.getHourOfDay() == adjusted.getHourOfDay() &&
//                dateTime.getMinuteOfHour() == adjusted.getMinuteOfHour() &&
//                dateTime.getSecondOfMinute() == adjusted.getSecondOfMinute()) {
//            return true;
//        }
//        return false;
        
//        long millis = dateTime.getMillis();
//        long nextTransition = nextTransition(millis);
//        long previousTransition = previousTransition(millis);
//        long deltaToPreviousTransition = millis - previousTransition;
//        long deltaToNextTransition = nextTransition - millis;
//        if (deltaToNextTransition < deltaToPreviousTransition) {
//            int offset = getOffset(nextTransition);
//            int standardOffset = getStandardOffset(nextTransition);
//            if (Math.abs(offset - standardOffset) >= deltaToNextTransition) {
//                return true;
//            }
//        } else  {
//            int offset = getOffset(previousTransition);
//            int standardOffset = getStandardOffset(previousTransition);
//            if (Math.abs(offset - standardOffset) >= deltaToPreviousTransition) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Checks if the given {@link LocalDateTime} is within a gap.
     * <p>
     * When switching from standard time to Daylight Savings Time there is
     * typically a gap where a clock hour is missing. This method identifies
     * whether the local datetime refers to such a gap.
     * 
     * @param localDateTime  the time to check, not null
     * @return true if the given datetime refers to a gap
     * @since 1.6
     */
    public boolean isLocalDateTimeGap(LocalDateTime localDateTime) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[228]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((isFixed()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[155]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[156]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[229]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
        try {
CodeCoverTryBranchHelper_Try9 = true;
            localDateTime.toDateTime(this);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[230]++;
            return false;
        } catch (IllegalArgumentException ex) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[158]++;
            return true;
        } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[157]++;
}
  }
    }

    /**
     * Adjusts the offset to be the earlier or later one during an overlap.
     * 
     * @param instant  the instant to adjust
     * @param earlierOrLater  false for earlier, true for later
     * @return the adjusted instant millis
     */
    public long adjustOffset(long instant, boolean earlierOrLater) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[231]++;
        long before = convertUTCToLocal(instant - 3 * DateTimeConstants.MILLIS_PER_HOUR);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[232]++;
        long after = convertUTCToLocal(instant + 3 * DateTimeConstants.MILLIS_PER_HOUR);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[233]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((before == after) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[159]++;
            return instant;

        } else {
  CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.branches[160]++;}
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[234]++;
        long local = convertUTCToLocal(instant);
        return convertLocalToUTC(local, false, earlierOrLater ? after : before);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns true if this time zone has no transitions.
     *
     * @return true if no transitions
     */
    public abstract boolean isFixed();

    /**
     * Advances the given instant to where the time zone offset or name changes.
     * If the instant returned is exactly the same as passed in, then
     * no changes occur after the given instant.
     *
     * @param instant  milliseconds from 1970-01-01T00:00:00Z
     * @return milliseconds from 1970-01-01T00:00:00Z
     */
    public abstract long nextTransition(long instant);

    /**
     * Retreats the given instant to where the time zone offset or name changes.
     * If the instant returned is exactly the same as passed in, then
     * no changes occur before the given instant.
     *
     * @param instant  milliseconds from 1970-01-01T00:00:00Z
     * @return milliseconds from 1970-01-01T00:00:00Z
     */
    public abstract long previousTransition(long instant);

    // Basic methods
    //--------------------------------------------------------------------

    /**
     * Get the datetime zone as a {@link java.util.TimeZone}.
     * 
     * @return the closest matching TimeZone object
     */
    public java.util.TimeZone toTimeZone() {
        return java.util.TimeZone.getTimeZone(iID);
    }

    /**
     * Compare this datetime zone with another.
     * 
     * @param object the object to compare with
     * @return true if equal, based on the ID and all internal rules
     */
    public abstract boolean equals(Object object);

    /**
     * Gets a hash code compatable with equals.
     * 
     * @return suitable hashcode
     */
    public int hashCode() {
        return 57 + getID().hashCode();
    }

    /**
     * Gets the datetime zone as a string, which is simply its ID.
     * @return the id of the zone
     */
    public String toString() {
        return getID();
    }

    /**
     * By default, when DateTimeZones are serialized, only a "stub" object
     * referring to the id is written out. When the stub is read in, it
     * replaces itself with a DateTimeZone object.
     * @return a stub object to go in the stream
     */
    protected Object writeReplace() throws ObjectStreamException {
        return new Stub(iID);
    }

    /**
     * Used to serialize DateTimeZones by id.
     */
    private static final class Stub implements Serializable {
        /** Serialization lock. */
        private static final long serialVersionUID = -6471952376487863581L;
        /** The ID of the zone. */
        private transient String iID;

        /**
         * Constructor.
         * @param id  the id of the zone
         */
        Stub(String id) {
            iID = id;
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[235]++;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeUTF(iID);
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[236]++;
        }

        private void readObject(ObjectInputStream in) throws IOException {
            iID = in.readUTF();
CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap.statements[237]++;
        }

        private Object readResolve() throws ObjectStreamException {
            return forID(iID);
        }
    }

}

class CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap ());
  }
    public static long[] statements = new long[238];
    public static long[] branches = new long[161];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[72];
  static {
    final String SECTION_NAME = "org.joda.time.DateTimeZone.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,2,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 71; i++) {
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

  public CodeCoverCoverageCounter$ssd5eoajddq01me6r1tbujfyap () {
    super("org.joda.time.DateTimeZone.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 237; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 160; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DateTimeZone.java");
      for (int i = 1; i <= 237; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 160; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 71; i++) {
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

