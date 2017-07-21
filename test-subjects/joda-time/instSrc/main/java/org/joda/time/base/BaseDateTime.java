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
package org.joda.time.base;

import java.io.Serializable;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

/**
 * BaseDateTime is an abstract implementation of ReadableDateTime that stores
 * data in <code>long</code> and <code>Chronology</code> fields.
 * <p>
 * This class should generally not be used directly by API users.
 * The {@link ReadableDateTime} interface should be used when different 
 * kinds of date/time objects are to be referenced.
 * <p>
 * BaseDateTime subclasses may be mutable and not thread-safe.
 *
 * @author Stephen Colebourne
 * @author Kandarp Shah
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class BaseDateTime
        extends AbstractDateTime
        implements ReadableDateTime, Serializable {
  static {
    CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = -6728882245981L;
  static {
    CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[1]++;
  }

    /** The millis from 1970-01-01T00:00:00Z */
    private volatile long iMillis;
    /** The chronology to use */
    private volatile Chronology iChronology;

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the current system millisecond time
     * using <code>ISOChronology</code> in the default time zone.
     */
    public BaseDateTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[2]++;
    }

    /**
     * Constructs an instance set to the current system millisecond time
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param zone  the time zone, null means default zone
     */
    public BaseDateTime(DateTimeZone zone) {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[3]++;
    }

    /**
     * Constructs an instance set to the current system millisecond time
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public BaseDateTime(Chronology chronology) {
        this(DateTimeUtils.currentTimeMillis(), chronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[4]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using <code>ISOChronology</code> in the default time zone.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public BaseDateTime(long instant) {
        this(instant, ISOChronology.getInstance());
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[5]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param zone  the time zone, null means default zone
     */
    public BaseDateTime(long instant, DateTimeZone zone) {
        this(instant, ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[6]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public BaseDateTime(long instant, Chronology chronology) {
        super();
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[7]++;
        iChronology = checkChronology(chronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[8]++;
        iMillis = checkInstant(instant, iChronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[9]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from an Object that represents a datetime,
     * forcing the time zone to that specified.
     * <p>
     * If the object contains no chronology, <code>ISOChronology</code> is used.
     * If the specified time zone is null, the default zone is used.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     *
     * @param instant  the datetime object
     * @param zone  the time zone
     * @throws IllegalArgumentException if the instant is invalid
     */
    public BaseDateTime(Object instant, DateTimeZone zone) {
        super();
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[10]++;
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[11]++;
        InstantConverter converter = ConverterManager.getInstance().getInstantConverter(instant);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[12]++;
        Chronology chrono = checkChronology(converter.getChronology(instant, zone));
        iChronology = chrono;
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[13]++;
        iMillis = checkInstant(converter.getInstantMillis(instant, chrono), chrono);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[14]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime,
     * using the specified chronology.
     * <p>
     * If the chronology is null, ISO in the default time zone is used.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     *
     * @param instant  the datetime object
     * @param chronology  the chronology
     * @throws IllegalArgumentException if the instant is invalid
     */
    public BaseDateTime(Object instant, Chronology chronology) {
        super();
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[15]++;
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[16]++;
        InstantConverter converter = ConverterManager.getInstance().getInstantConverter(instant);
        iChronology = checkChronology(converter.getChronology(instant, chronology));
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[17]++;
        iMillis = checkInstant(converter.getInstantMillis(instant, chronology), iChronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[18]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the default time zone.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     */
    public BaseDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
        this(year, monthOfYear, dayOfMonth, hourOfDay,
            minuteOfHour, secondOfMinute, millisOfSecond, ISOChronology.getInstance());
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[19]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using <code>ISOChronology</code> in the specified time zone.
     * <p>
     * If the specified time zone is null, the default zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @param zone  the time zone, null means default time zone
     */
    public BaseDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond,
            DateTimeZone zone) {
        this(year, monthOfYear, dayOfMonth, hourOfDay,
            minuteOfHour, secondOfMinute, millisOfSecond, ISOChronology.getInstance(zone));
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[20]++;
    }

    /**
     * Constructs an instance from datetime field values
     * using the specified chronology.
     * <p>
     * If the chronology is null, <code>ISOChronology</code>
     * in the default time zone is used.
     *
     * @param year  the year
     * @param monthOfYear  the month of the year
     * @param dayOfMonth  the day of the month
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @param chronology  the chronology, null means ISOChronology in default zone
     */
    public BaseDateTime(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond,
            Chronology chronology) {
        super();
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[21]++;
        iChronology = checkChronology(chronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[22]++;
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[23]++;
        long instant = iChronology.getDateTimeMillis(year, monthOfYear, dayOfMonth,
            hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
        iMillis = checkInstant(instant, iChronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[24]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks the specified chronology before storing it, potentially altering it.
     * This method must not access any instance variables.
     * <p>
     * This implementation converts nulls to ISOChronology in the default zone.
     *
     * @param chronology  the chronology to use, may be null
     * @return the chronology to store in this datetime, not null
     */
    protected Chronology checkChronology(Chronology chronology) {
        return DateTimeUtils.getChronology(chronology);
    }

    /**
     * Checks the specified instant before storing it, potentially altering it.
     * This method must not access any instance variables.
     * <p>
     * This implementation simply returns the instant.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @param chronology  the chronology to use, not null
     * @return the instant to store in this datetime
     */
    protected long checkInstant(long instant, Chronology chronology) {
        return instant;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the milliseconds of the datetime instant from the Java epoch
     * of 1970-01-01T00:00:00Z.
     * 
     * @return the number of milliseconds since 1970-01-01T00:00:00Z
     */
    public long getMillis() {
        return iMillis;
    }

    /**
     * Gets the chronology of the datetime.
     * 
     * @return the Chronology that the datetime is using
     */
    public Chronology getChronology() {
        return iChronology;
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the milliseconds of the datetime.
     * <p>
     * All changes to the millisecond field occurs via this method.
     * Override and block this method to make a subclass immutable.
     *
     * @param instant  the milliseconds since 1970-01-01T00:00:00Z to set the datetime to
     */
    protected void setMillis(long instant) {
        iMillis = checkInstant(instant, iChronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[25]++;
    }

    /**
     * Sets the chronology of the datetime.
     * <p>
     * All changes to the chronology field occurs via this method.
     * Override and block this method to make a subclass immutable.
     *
     * @param chronology  the chronology to set
     */
    protected void setChronology(Chronology chronology) {
        iChronology = checkChronology(chronology);
CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p.statements[26]++;
    }

}

class CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p ());
  }
    public static long[] statements = new long[27];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$ry1x81nqs80affw6ujk7o8rk2p () {
    super("org.joda.time.base.BaseDateTime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 26; i++) {
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
    log.startNamedSection("org.joda.time.base.BaseDateTime.java");
      for (int i = 1; i <= 26; i++) {
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

