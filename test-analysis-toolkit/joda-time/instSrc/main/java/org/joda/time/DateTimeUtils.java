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

import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.util.Locale;

import org.joda.time.chrono.ISOChronology;

/**
 * DateTimeUtils provide public utility methods for the date-time library.
 * <p>
 * DateTimeUtils is thread-safe although shared static variables are used.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public class DateTimeUtils {
  static {
    CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.ping();
  }


    /** The singleton instance of the system millisecond provider. */
    private static final SystemMillisProvider SYSTEM_MILLIS_PROVIDER = new SystemMillisProvider();
  static {
    CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[1]++;
  }
    /** The millisecond provider currently in use. */
    private static volatile MillisProvider cMillisProvider = SYSTEM_MILLIS_PROVIDER;
  static {
    CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[2]++;
  }

    /**
     * Restrictive constructor
     */
    protected DateTimeUtils() {
        super();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[3]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the current time in milliseconds.
     * <p>
     * By default this returns <code>System.currentTimeMillis()</code>.
     * This may be changed using other methods in this class.
     * 
     * @return the current time in milliseconds from 1970-01-01T00:00:00Z
     */
    public static final long currentTimeMillis() {
        return cMillisProvider.getMillis();
    }

    /**
     * Resets the current time to return the system time.
     * <p>
     * This method changes the behaviour of {@link #currentTimeMillis()}.
     * Whenever the current time is queried, {@link System#currentTimeMillis()} is used.
     * 
     * @throws SecurityException if the application does not have sufficient security rights
     */
    public static final void setCurrentMillisSystem() throws SecurityException {
        checkPermission();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[4]++;
        cMillisProvider = SYSTEM_MILLIS_PROVIDER;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[5]++;
    }

    /**
     * Sets the current time to return a fixed millisecond time.
     * <p>
     * This method changes the behaviour of {@link #currentTimeMillis()}.
     * Whenever the current time is queried, the same millisecond time will be returned.
     * 
     * @param fixedMillis  the fixed millisecond time to use
     * @throws SecurityException if the application does not have sufficient security rights
     */
    public static final void setCurrentMillisFixed(long fixedMillis) throws SecurityException {
        checkPermission();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[6]++;
        cMillisProvider = new FixedMillisProvider(fixedMillis);
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[7]++;
    }

    /**
     * Sets the current time to return the system time plus an offset.
     * <p>
     * This method changes the behaviour of {@link #currentTimeMillis()}.
     * Whenever the current time is queried, {@link System#currentTimeMillis()} is used
     * and then offset by adding the millisecond value specified here.
     * 
     * @param offsetMillis  the fixed millisecond time to use
     * @throws SecurityException if the application does not have sufficient security rights
     */
    public static final void setCurrentMillisOffset(long offsetMillis) throws SecurityException {
        checkPermission();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[8]++;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((offsetMillis == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[1]++;
            cMillisProvider = SYSTEM_MILLIS_PROVIDER;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[10]++;

        } else {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[2]++;
            cMillisProvider = new OffsetMillisProvider(offsetMillis);
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[11]++;
        }
    }

    /**
     * Sets the provider of the current time to class specified.
     * <p>
     * This method changes the behaviour of {@link #currentTimeMillis()}.
     * Whenever the current time is queried, the specified class will be called.
     * 
     * @param millisProvider  the provider of the current time to use, not null
     * @throws SecurityException if the application does not have sufficient security rights
     * @since 2.0
     */
    public static final void setCurrentMillisProvider(MillisProvider millisProvider) throws SecurityException {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((millisProvider == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[3]++;
            throw new IllegalArgumentException("The MillisProvider must not be null");

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[4]++;}
        checkPermission();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[13]++;
        cMillisProvider = millisProvider;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[14]++;
    }

    /**
     * Checks whether the provider may be changed using permission 'CurrentTime.setProvider'.
     * 
     * @throws SecurityException if the provider may not be changed
     */
    private static void checkPermission() throws SecurityException {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[15]++;
        SecurityManager sm = System.getSecurityManager();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sm != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[5]++;
            sm.checkPermission(new JodaTimePermission("CurrentTime.setProvider"));
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[17]++;

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[6]++;}
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the millisecond instant from the specified instant object handling null.
     * <p>
     * If the instant object is <code>null</code>, the {@link #currentTimeMillis()}
     * will be returned. Otherwise, the millis from the object are returned.
     * 
     * @param instant  the instant to examine, null means now
     * @return the time in milliseconds from 1970-01-01T00:00:00Z
     */
    public static final long getInstantMillis(ReadableInstant instant) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[7]++;
            return DateTimeUtils.currentTimeMillis();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[8]++;}
        return instant.getMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology from the specified instant object handling null.
     * <p>
     * If the instant object is <code>null</code>, or the instant's chronology is
     * <code>null</code>, {@link ISOChronology#getInstance()} will be returned.
     * Otherwise, the chronology from the object is returned.
     * 
     * @param instant  the instant to examine, null means ISO in the default zone
     * @return the chronology, never null
     */
    public static final Chronology getInstantChronology(ReadableInstant instant) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[9]++;
            return ISOChronology.getInstance();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[10]++;}
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[20]++;
        Chronology chrono = instant.getChronology();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[11]++;
            return ISOChronology.getInstance();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[12]++;}
        return chrono;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology from the specified instant based interval handling null.
     * <p>
     * The chronology is obtained from the start if that is not null, or from the
     * end if the start is null. The result is additionally checked, and if still
     * null then {@link ISOChronology#getInstance()} will be returned.
     * 
     * @param start  the instant to examine and use as the primary source of the chronology
     * @param end  the instant to examine and use as the secondary source of the chronology
     * @return the chronology, never null
     */
    public static final Chronology getIntervalChronology(ReadableInstant start, ReadableInstant end) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[22]++;
        Chronology chrono = null;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((start != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[13]++;
            chrono = start.getChronology();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[24]++;

        } else {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[14]++;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[25]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((end != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[15]++;
            chrono = end.getChronology();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[26]++;

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[16]++;}
}
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[17]++;
            chrono = ISOChronology.getInstance();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[28]++;

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[18]++;}
        return chrono;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology from the specified interval object handling null.
     * <p>
     * If the interval object is <code>null</code>, or the interval's chronology is
     * <code>null</code>, {@link ISOChronology#getInstance()} will be returned.
     * Otherwise, the chronology from the object is returned.
     * 
     * @param interval  the interval to examine, null means ISO in the default zone
     * @return the chronology, never null
     */
    public static final Chronology getIntervalChronology(ReadableInterval interval) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[19]++;
            return ISOChronology.getInstance();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[20]++;}
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[30]++;
        Chronology chrono = interval.getChronology();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[21]++;
            return ISOChronology.getInstance();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[22]++;}
        return chrono;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the interval handling null.
     * <p>
     * If the interval is <code>null</code>, an interval representing now
     * to now in the {@link ISOChronology#getInstance() ISOChronology}
     * will be returned. Otherwise, the interval specified is returned.
     * 
     * @param interval  the interval to use, null means now to now
     * @return the interval, never null
     * @since 1.1
     */
    public static final ReadableInterval getReadableInterval(ReadableInterval interval) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[23]++;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[33]++;
            long now = DateTimeUtils.currentTimeMillis();
            interval = new Interval(now, now);
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[34]++;

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[24]++;}
        return interval;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology handling null.
     * <p>
     * If the chronology is <code>null</code>, {@link ISOChronology#getInstance()}
     * will be returned. Otherwise, the chronology is returned.
     * 
     * @param chrono  the chronology to use, null means ISO in the default zone
     * @return the chronology, never null
     */
    public static final Chronology getChronology(Chronology chrono) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[35]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((chrono == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[25]++;
            return ISOChronology.getInstance();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[26]++;}
        return chrono;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the zone handling null.
     * <p>
     * If the zone is <code>null</code>, {@link DateTimeZone#getDefault()}
     * will be returned. Otherwise, the zone specified is returned.
     * 
     * @param zone  the time zone to use, null means the default zone
     * @return the time zone, never null
     */
    public static final DateTimeZone getZone(DateTimeZone zone) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[36]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[27]++;
            return DateTimeZone.getDefault();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[28]++;}
        return zone;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the period type handling null.
     * <p>
     * If the zone is <code>null</code>, {@link PeriodType#standard()}
     * will be returned. Otherwise, the type specified is returned.
     * 
     * @param type  the time zone to use, null means the standard type
     * @return the type to use, never null
     */
    public static final PeriodType getPeriodType(PeriodType type) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[37]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[29]++;
            return PeriodType.standard();

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[30]++;}
        return type;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the millisecond duration from the specified duration object handling null.
     * <p>
     * If the duration object is <code>null</code>, zero will be returned.
     * Otherwise, the millis from the object are returned.
     * 
     * @param duration  the duration to examine, null means zero
     * @return the duration in milliseconds
     */
    public static final long getDurationMillis(ReadableDuration duration) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[38]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((duration == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[31]++;
            return 0L;

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[32]++;}
        return duration.getMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Checks whether the partial is contiguous.
     * <p>
     * A partial is contiguous if one field starts where another ends.
     * <p>
     * For example <code>LocalDate</code> is contiguous because DayOfMonth has
     * the same range (Month) as the unit of the next field (MonthOfYear), and
     * MonthOfYear has the same range (Year) as the unit of the next field (Year).
     * <p>
     * Similarly, <code>LocalTime</code> is contiguous, as it consists of
     * MillisOfSecond, SecondOfMinute, MinuteOfHour and HourOfDay (note how
     * the names of each field 'join up').
     * <p>
     * However, a Year/HourOfDay partial is not contiguous because the range
     * field Day is not equal to the next field Year.
     * Similarly, a DayOfWeek/DayOfMonth partial is not contiguous because
     * the range Month is not equal to the next field Day.
     * 
     * @param partial  the partial to check
     * @return true if the partial is contiguous
     * @throws IllegalArgumentException if the partial is null
     * @since 1.1
     */
    public static final boolean isContiguous(ReadablePartial partial) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[39]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[33]++;
            throw new IllegalArgumentException("Partial must not be null");

        } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[34]++;}
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[40]++;
        DurationFieldType lastType = null;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[41]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.loops[1]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < partial.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.loops[1]--;
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.loops[2]--;
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.loops[3]++;
}
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[42]++;
            DateTimeField loopField = partial.getField(i);
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[43]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[35]++;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[44]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((loopField.getRangeDurationField().getType() != lastType) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[37]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[36]++;}
            lastType = loopField.getDurationField().getType();
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[45]++;
        }
        return true;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the {@link DateFormatSymbols} based on the given locale.
     * <p>
     * If JDK 6 or newer is being used, DateFormatSymbols.getInstance(locale) will
     * be used in order to allow the use of locales defined as extensions.
     * Otherwise, new DateFormatSymbols(locale) will be used.
     * See JDK 6 {@link DateFormatSymbols} for further information.
     * 
     * @param locale  the {@link Locale} used to get the correct {@link DateFormatSymbols}
     * @return the symbols
     * @since 2.0
     */
    public static final DateFormatSymbols getDateFormatSymbols(Locale locale) {
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[46]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[47]++;        	
        	Method method = DateFormatSymbols.class.getMethod("getInstance", new Class[] {Locale.class});        	
        	return (DateFormatSymbols) method.invoke(null, new Object[] {locale});        	
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[40]++;
        	return new DateFormatSymbols(locale);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.branches[39]++;
}
  } 
    }

    //-----------------------------------------------------------------------
    /**
     * A millisecond provider, allowing control of the system clock.
     * 
     * @author Stephen Colebourne
     * @since 2.0 (previously private)
     */
    public static interface MillisProvider {
        /**
         * Gets the current time.
         * <p>
         * Implementations of this method must be thread-safe.
         * 
         * @return the current time in milliseconds
         */
        long getMillis();
    }

    /**
     * System millis provider.
     */
    static class SystemMillisProvider implements MillisProvider {
        /**
         * Gets the current time.
         * @return the current time in millis
         */
        public long getMillis() {
            return System.currentTimeMillis();
        }
    }

    /**
     * Fixed millisecond provider.
     */
    static class FixedMillisProvider implements MillisProvider {
        /** The fixed millis value. */
        private final long iMillis;
        
        /**
         * Constructor.
         * @param offsetMillis  the millis offset
         */
        FixedMillisProvider(long fixedMillis) {
            iMillis = fixedMillis;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[48]++;
        }
        
        /**
         * Gets the current time.
         * @return the current time in millis
         */
        public long getMillis() {
            return iMillis;
        }
    }

    /**
     * Offset from system millis provider.
     */
    static class OffsetMillisProvider implements MillisProvider {
        /** The millis offset. */
        private final long iMillis;
        
        /**
         * Constructor.
         * @param offsetMillis  the millis offset
         */
        OffsetMillisProvider(long offsetMillis) {
            iMillis = offsetMillis;
CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp.statements[49]++;
        }
        
        /**
         * Gets the current time.
         * @return the current time in millis
         */
        public long getMillis() {
            return System.currentTimeMillis() + iMillis;
        }
    }

}

class CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp ());
  }
    public static long[] statements = new long[50];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.joda.time.DateTimeUtils.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$5oppigcqxr5kw7pd6ej1t2bhmyqp () {
    super("org.joda.time.DateTimeUtils.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 49; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.DateTimeUtils.java");
      for (int i = 1; i <= 49; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 40; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

