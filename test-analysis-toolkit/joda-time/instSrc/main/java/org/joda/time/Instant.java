/*
 *  Copyright 2001-2010 Stephen Colebourne
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

import org.joda.convert.FromString;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Instant is the standard implementation of a fully immutable instant in time.
 * <p>
 * <code>Instant</code> is an implementation of {@link ReadableInstant}.
 * As with all instants, it represents an exact point on the time-line,
 * but limited to the precision of milliseconds. An <code>Instant</code>
 * should be used to represent a point in time irrespective of any other
 * factor, such as chronology or time zone.
 * <p>
 * Internally, the class holds one piece of data, the instant as milliseconds
 * from the Java epoch of 1970-01-01T00:00:00Z.
 * <p>
 * For example, an Instant can be used to compare two <code>DateTime</code>
 * objects irrespective of chronology or time zone.
 * <pre>
 * boolean sameInstant = dt1.toInstant().equals(dt2.toInstant());
 * </pre>
 * Note that the following code will also perform the same check:
 * <pre>
 * boolean sameInstant = dt1.isEqual(dt2);
 * </pre>
 * <p>
 * Instant is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public final class Instant
        extends AbstractInstant
        implements ReadableInstant, Serializable {
  static {
    CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.ping();
  }


    /** Serialization lock */
    private static final long serialVersionUID = 3299096530934209741L;
  static {
    CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[1]++;
  }

    /** The millis from 1970-01-01T00:00:00Z */
    private final long iMillis;

    //-----------------------------------------------------------------------
    /**
     * Obtains an {@code Instant} set to the current system millisecond time.
     * 
     * @return the current instant, not null
     * @since 2.0
     */
    public static Instant now() {
        return new Instant();
    }

    //-----------------------------------------------------------------------
    /**
     * Parses a {@code Instant} from the specified string.
     * <p>
     * This uses {@link ISODateTimeFormat#dateTimeParser()}.
     * 
     * @param str  the string to parse, not null
     * @since 2.0
     */
    @FromString
    public static Instant parse(String str) {
        return parse(str, ISODateTimeFormat.dateTimeParser());
    }

    /**
     * Parses a {@code Instant} from the specified string using a formatter.
     * 
     * @param str  the string to parse, not null
     * @param formatter  the formatter to use, not null
     * @since 2.0
     */
    public static Instant parse(String str, DateTimeFormatter formatter) {
        return formatter.parseDateTime(str).toInstant();
    }

    //-----------------------------------------------------------------------
    /**
     * Constructs an instance set to the current system millisecond time.
     * 
     * @see #now()
     */
    public Instant() {
        super();
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[2]++;
        iMillis = DateTimeUtils.currentTimeMillis();
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[3]++;
    }

    /**
     * Constructs an instance set to the milliseconds from 1970-01-01T00:00:00Z.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public Instant(long instant) {
        super();
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[4]++;
        iMillis = instant;
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[5]++;
    }

    /**
     * Constructs an instance from an Object that represents a datetime.
     * <p>
     * The recognised object types are defined in {@link ConverterManager} and
     * include String, Calendar and Date.
     *
     * @param instant  the datetime object, null means now
     * @throws IllegalArgumentException if the instant is invalid
     */
    public Instant(Object instant) {
        super();
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[6]++;
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[7]++;
        InstantConverter converter = ConverterManager.getInstance().getInstantConverter(instant);
        iMillis = converter.getInstantMillis(instant, ISOChronology.getInstanceUTC());
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[8]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Get this object as an Instant by returning <code>this</code>.
     * 
     * @return <code>this</code>
     */
    public Instant toInstant() {
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a copy of this instant with different millis.
     * <p>
     * The returned object will be either be a new Instant or <code>this</code>.
     *
     * @param newMillis  the new millis, from 1970-01-01T00:00:00Z
     * @return a copy of this instant with different millis
     */
    public Instant withMillis(long newMillis) {
        return (newMillis == iMillis ? this : new Instant(newMillis));
    }

    /**
     * Gets a copy of this instant with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * 
     * @param durationToAdd  the duration to add to this one
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this instant with the duration added
     * @throws ArithmeticException if the new instant exceeds the capacity of a long
     */
    public Instant withDurationAdded(long durationToAdd, int scalar) {
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((durationToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.branches[1]++;
            return this;

        } else {
  CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.branches[2]++;}
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[10]++;
        long instant = getChronology().add(getMillis(), durationToAdd, scalar);
        return withMillis(instant);
    }

    /**
     * Gets a copy of this instant with the specified duration added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * 
     * @param durationToAdd  the duration to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this instant with the duration added
     * @throws ArithmeticException if the new instant exceeds the capacity of a long
     */
    public Instant withDurationAdded(ReadableDuration durationToAdd, int scalar) {
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((durationToAdd == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.branches[3]++;
            return this;

        } else {
  CodeCoverCoverageCounter$27bhw361v7t7unyt3s1.branches[4]++;}
        return withDurationAdded(durationToAdd.getMillis(), scalar);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a copy of this instant with the specified duration added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration to add to this one
     * @return a copy of this instant with the duration added
     * @throws ArithmeticException if the new instant exceeds the capacity of a long
     */
    public Instant plus(long duration) {
        return withDurationAdded(duration, 1);
    }

    /**
     * Gets a copy of this instant with the specified duration added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration to add to this one, null means zero
     * @return a copy of this instant with the duration added
     * @throws ArithmeticException if the new instant exceeds the capacity of a long
     */
    public Instant plus(ReadableDuration duration) {
        return withDurationAdded(duration, 1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a copy of this instant with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration to reduce this instant by
     * @return a copy of this instant with the duration taken away
     * @throws ArithmeticException if the new instant exceeds the capacity of a long
     */
    public Instant minus(long duration) {
        return withDurationAdded(duration, -1);
    }

    /**
     * Gets a copy of this instant with the specified duration taken away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     * 
     * @param duration  the duration to reduce this instant by
     * @return a copy of this instant with the duration taken away
     * @throws ArithmeticException if the new instant exceeds the capacity of a long
     */
    public Instant minus(ReadableDuration duration) {
        return withDurationAdded(duration, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the milliseconds of the instant.
     * 
     * @return the number of milliseconds since 1970-01-01T00:00:00Z
     */
    public long getMillis() {
        return iMillis;
    }

    /**
     * Gets the chronology of the instant, which is ISO in the UTC zone.
     * <p>
     * This method returns {@link ISOChronology#getInstanceUTC()} which
     * corresponds to the definition of the Java epoch 1970-01-01T00:00:00Z.
     * 
     * @return ISO in the UTC zone
     */
    public Chronology getChronology() {
        return ISOChronology.getInstanceUTC();
    }

    //-----------------------------------------------------------------------
    /**
     * Get this object as a DateTime using ISOChronology in the default zone.
     * <p>
     * This method returns a DateTime object in the default zone.
     * This differs from the similarly named method on DateTime, DateMidnight
     * or MutableDateTime which retains the time zone. The difference is
     * because Instant really represents a time <i>without</i> a zone,
     * thus calling this method we really have no zone to 'retain' and
     * hence expect to switch to the default zone.
     * <p>
     * This method definition preserves compatibility with earlier versions
     * of Joda-Time.
     *
     * @return a DateTime using the same millis
     */
    public DateTime toDateTime() {
        return new DateTime(getMillis(), ISOChronology.getInstance());
    }

    /**
     * Get this object as a DateTime using ISOChronology in the default zone.
     * This method is identical to <code>toDateTime()</code>.
     * <p>
     * This method returns a DateTime object in the default zone.
     * This differs from the similarly named method on DateTime, DateMidnight
     * or MutableDateTime which retains the time zone. The difference is
     * because Instant really represents a time <i>without</i> a zone,
     * thus calling this method we really have no zone to 'retain' and
     * hence expect to switch to the default zone.
     * <p>
     * This method is deprecated because it is a duplicate of {@link #toDateTime()}.
     * However, removing it would cause the superclass implementation to be used,
     * which would create silent bugs in any caller depending on this implementation.
     * As such, the method itself is not currently planned to be removed.
     * <p>
     * This method definition preserves compatibility with earlier versions
     * of Joda-Time.
     *
     * @return a DateTime using the same millis with ISOChronology
     * @deprecated Use toDateTime() as it is identical
     */
    @Deprecated
    public DateTime toDateTimeISO() {
        return toDateTime();
    }

    /**
     * Get this object as a MutableDateTime using ISOChronology in the default zone.
     * <p>
     * This method returns a MutableDateTime object in the default zone.
     * This differs from the similarly named method on DateTime, DateMidnight
     * or MutableDateTime which retains the time zone. The difference is
     * because Instant really represents a time <i>without</i> a zone,
     * thus calling this method we really have no zone to 'retain' and
     * hence expect to switch to the default zone.
     * <p>
     * This method definition preserves compatibility with earlier versions
     * of Joda-Time.
     *
     * @return a MutableDateTime using the same millis
     */
    public MutableDateTime toMutableDateTime() {
        return new MutableDateTime(getMillis(), ISOChronology.getInstance());
    }

    /**
     * Get this object as a MutableDateTime using ISOChronology in the default zone.
     * This method is identical to <code>toMutableDateTime()</code>.
     * <p>
     * This method returns a MutableDateTime object in the default zone.
     * This differs from the similarly named method on DateTime, DateMidnight
     * or MutableDateTime which retains the time zone. The difference is
     * because Instant really represents a time <i>without</i> a zone,
     * thus calling this method we really have no zone to 'retain' and
     * hence expect to switch to the default zone.
     * <p>
     * This method is deprecated because it is a duplicate of {@link #toMutableDateTime()}.
     * However, removing it would cause the superclass implementation to be used,
     * which would create silent bugs in any caller depending on this implementation.
     * As such, the method itself is not currently planned to be removed.
     * <p>
     * This method definition preserves compatibility with earlier versions
     * of Joda-Time.
     *
     * @return a MutableDateTime using the same millis with ISOChronology
     * @deprecated Use toMutableDateTime() as it is identical
     */
    @Deprecated
    public MutableDateTime toMutableDateTimeISO() {
        return toMutableDateTime();
    }

}

class CodeCoverCoverageCounter$27bhw361v7t7unyt3s1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$27bhw361v7t7unyt3s1 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.Instant.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2};
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

  public CodeCoverCoverageCounter$27bhw361v7t7unyt3s1 () {
    super("org.joda.time.Instant.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("org.joda.time.Instant.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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

