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

import java.util.Locale;

/**
 * Defines an instant in time that can be queried using datetime fields.
 * <p>
 * The implementation of this interface may be mutable or immutable.
 * This interface only gives access to retrieve data, never to change it.
 * <p>
 * Methods in your application should be defined using <code>ReadableDateTime</code>
 * as a parameter if the method only wants to read the datetime, and not perform
 * any advanced manipulations.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public interface ReadableDateTime extends ReadableInstant {

    /**
     * Get the day of week field value.
     * <p>
     * The values for the day of week are defined in {@link DateTimeConstants}.
     * 
     * @return the day of week
     */
    int getDayOfWeek();

    /**
     * Get the day of month field value.
     * 
     * @return the day of month
     */
    int getDayOfMonth();

    /**
     * Get the day of year field value.
     * 
     * @return the day of year
     */
    int getDayOfYear();

    /**
     * Get the week of weekyear field value.
     * <p>
     * This field is associated with the "weekyear" via {@link #getWeekyear()}.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * 
     * @return the week of a week based year
     */
    int getWeekOfWeekyear();

    /**
     * Get the weekyear field value.
     * <p>
     * The weekyear is the year that matches with the weekOfWeekyear field.
     * In the standard ISO8601 week algorithm, the first week of the year
     * is that in which at least 4 days are in the year. As a result of this
     * definition, day 1 of the first week may be in the previous year.
     * The weekyear allows you to query the effective year for that day.
     * 
     * @return the year of a week based year
     */
    int getWeekyear();

    /**
     * Get the month of year field value.
     * 
     * @return the month of year
     */
    int getMonthOfYear();

    /**
     * Get the year field value.
     * 
     * @return the year
     */
    int getYear();

    /**
     * Get the year of era field value.
     * 
     * @return the year of era
     */
    int getYearOfEra();

    /**
     * Get the year of century field value.
     * 
     * @return the year of century
     */
    int getYearOfCentury();

    /**
     * Get the year of era field value.
     * 
     * @return the year of era
     */
    int getCenturyOfEra();

    /**
     * Get the era field value.
     * 
     * @return the era
     */
    int getEra();

    // Time field access methods
    //-----------------------------------------------------------

    /**
     * Get the millis of second field value.
     *
     * @return the millis of second
     */
    int getMillisOfSecond();

    /**
     * Get the millis of day field value.
     *
     * @return the millis of day
     */
    int getMillisOfDay();

    /**
     * Get the second of minute field value.
     *
     * @return the second of minute
     */
    int getSecondOfMinute();

    /**
     * Get the second of day field value.
     *
     * @return the second of day
     */
    int getSecondOfDay();

    /**
     * Get the minute of hour field value.
     *
     * @return the minute of hour
     */
    int getMinuteOfHour();

    /**
     * Get the minute of day field value.
     *
     * @return the minute of day
     */
    int getMinuteOfDay();

    /**
     * Get the hour of day field value.
     *
     * @return the hour of day
     */
    int getHourOfDay();

    /**
     * Get this object as a DateTime.
     * <p>
     * If the implementation of the interface is a DateTime, it is returned directly.
     * 
     * @return a DateTime using the same millis
     */
    DateTime toDateTime();

    /**
     * Get this object as a MutableDateTime, always returning a new instance.
     * 
     * @return a MutableDateTime using the same millis
     */
    MutableDateTime toMutableDateTime();

    /**
     * Output the instant using the specified format pattern.
     *
     * @param pattern  pattern specification
     * @throws IllegalArgumentException  if pattern is invalid
     * @see  org.joda.time.format.DateTimeFormat
     */
    String toString(String pattern) throws IllegalArgumentException;

    /**
     * Output the instant using the specified format pattern.
     *
     * @param pattern  pattern specification
     * @param locale  Locale to use, or null for default
     * @throws IllegalArgumentException  if pattern is invalid
     * @see  org.joda.time.format.DateTimeFormat
     */
    String toString(String pattern, Locale locale) throws IllegalArgumentException;
    
}

class CodeCoverCoverageCounter$1wfxx01cbynj5dshwsriuk6cl91rl54dd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wfxx01cbynj5dshwsriuk6cl91rl54dd ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1wfxx01cbynj5dshwsriuk6cl91rl54dd () {
    super("org.joda.time.ReadableDateTime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.ReadableDateTime.java");
      for (int i = 1; i <= -1; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
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

