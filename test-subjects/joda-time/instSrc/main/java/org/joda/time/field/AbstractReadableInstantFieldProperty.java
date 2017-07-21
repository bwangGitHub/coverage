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
package org.joda.time.field;

import java.io.Serializable;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.Interval;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

/**
 * AbstractReadableInstantFieldProperty is a base class for binding a
 * ReadableInstant to a DateTimeField.
 * <p>
 * It allows the date and time manipulation code to be field based yet
 * still easy to use.
 * <p>
 * AbstractReadableInstantFieldProperty itself is thread-safe and immutable,
 * but the ReadableInstant being operated on may be mutable and not
 * thread-safe.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @author Mike Schrag
 * @since 1.0
 */
public abstract class AbstractReadableInstantFieldProperty implements Serializable {
  static {
    CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.ping();
  }


    /** Serialization version. */
    private static final long serialVersionUID = 1971226328211649661L;
  static {
    CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[1]++;
  }

    /**
     * Constructor.
     */
    public AbstractReadableInstantFieldProperty() {
        super();
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[2]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the field being used.
     * 
     * @return the field
     */
    public abstract DateTimeField getField();

    /**
     * Gets the field type being used.
     * 
     * @return the field type
     */
    public DateTimeFieldType getFieldType() {
        return getField().getType();
    }

    /**
     * Gets the name of the field.
     * 
     * @return the field name
     */
    public String getName() {
        return getField().getName();
    }

    /**
     * Gets the milliseconds of the datetime that this property is linked to.
     * 
     * @return the milliseconds
     */
    protected abstract long getMillis();

    /**
     * Gets the chronology of the datetime that this property is linked to.
     * <p>
     * This implementation throws UnsupportedOperationException, and must be
     * implemented by subclasses to enable the equals() and hashCode() methods.
     * 
     * @return the chronology
     * @since 1.4
     */
    protected Chronology getChronology() {
        throw new UnsupportedOperationException(
                "The method getChronology() was added in v1.4 and needs " +
                "to be implemented by subclasses of AbstractReadableInstantFieldProperty");
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the value of this property from the instant.
     * <p>
     * For example, the following two lines of code are equivalent:
     * <pre>
     * datetime.getDayOfMonth();
     * datetime.dayOfMonth().get();
     * </pre>
     * 
     * @return the current value
     * @see DateTimeField#get
     */
    public int get() {
        return getField().get(getMillis());
    }

    /**
     * Gets the value of this property from the instant as a string.
     * <p>
     * This method returns the value converted to a <code>String</code>
     * using <code>Integer.toString</code>. This method does NOT return
     * textual descriptions such as 'Monday' or 'January'.
     * See {@link #getAsText()} and {@link #getAsShortText()} for those.
     * 
     * @return the current value
     * @see DateTimeField#get
     * @since 1.1
     */
    public String getAsString() {
        return Integer.toString(get());
    }

    /**
     * Gets the textual value of this property from the instant as a
     * string in the default locale.
     * <p>
     * This method returns the value converted to a <code>String</code>
     * returning the appropriate textual description wherever possible.
     * Thus, a day of week of 1 would return 'Monday' in English.
     * 
     * @return the current text value
     * @see DateTimeField#getAsText
     */
    public String getAsText() {
        return getAsText(null);
    }

    /**
     * Gets the textual value of this property from the instant as a
     * string in the specified locale.
     * <p>
     * This method returns the value converted to a <code>String</code>
     * returning the appropriate textual description wherever possible.
     * Thus, a day of week of 1 would return 'Monday' in English.
     * 
     * @param locale  locale to use for selecting a text symbol, null means default
     * @return the current text value
     * @see DateTimeField#getAsText
     */
    public String getAsText(Locale locale) {
        return getField().getAsText(getMillis(), locale);
    }

    /**
     * Gets the short textual value of this property from the instant as a
     * string in the default locale.
     * <p>
     * This method returns the value converted to a <code>String</code>
     * returning the appropriate textual description wherever possible.
     * Thus, a day of week of 1 would return 'Mon' in English.
     * 
     * @return the current text value
     * @see DateTimeField#getAsShortText
     */
    public String getAsShortText() {
        return getAsShortText(null);
    }

    /**
     * Gets the short textual value of this property from the instant as a
     * string in the specified locale.
     * <p>
     * This method returns the value converted to a <code>String</code>
     * returning the appropriate textual description wherever possible.
     * Thus, a day of week of 1 would return 'Mon' in English.
     * 
     * @param locale  locale to use for selecting a text symbol, null means default
     * @return the current text value
     * @see DateTimeField#getAsShortText
     */
    public String getAsShortText(Locale locale) {
        return getField().getAsShortText(getMillis(), locale);
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the difference between this field property instant and the one
     * passed in, in the units of this field. The sign of the difference
     * matches that of compareTo. In other words, this field property's instant
     * is the minuend.
     *
     * @param instant  the subtrahend, null means now
     * @return the difference in the units of this field
     * @see DateTimeField#getDifference
     */
    public int getDifference(ReadableInstant instant) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[1]++;
            return getField().getDifference(getMillis(), DateTimeUtils.currentTimeMillis());

        } else {
  CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[2]++;}
        return getField().getDifference(getMillis(), instant.getMillis());
    }

    /**
     * Returns the difference between this field property instant and the one
     * passed in, in the units of this field. The sign of the difference
     * matches that of compareTo. In other words, this field property's instant
     * is the minuend.
     *
     * @param instant  the subtrahend, null means now
     * @return the difference in the units of this field
     * @see DateTimeField#getDifference
     */
    public long getDifferenceAsLong(ReadableInstant instant) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[3]++;
            return getField().getDifferenceAsLong(getMillis(), DateTimeUtils.currentTimeMillis());

        } else {
  CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[4]++;}
        return getField().getDifferenceAsLong(getMillis(), instant.getMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the duration per unit value of this field. For example, if this
     * field represents "hour of day", then the duration is an hour.
     *
     * @return the duration of this field, or UnsupportedDurationField
     */
    public DurationField getDurationField() {
        return getField().getDurationField();
    }

    /**
     * Returns the range duration of this field. For example, if this field
     * represents "hour of day", then the range duration is a day.
     *
     * @return the range duration of this field, or null if field has no range
     */
    public DurationField getRangeDurationField() {
        return getField().getRangeDurationField();
    }

    /**
     * Gets whether this field is leap.
     * 
     * @return true if a leap field
     * @see DateTimeField#isLeap
     */
    public boolean isLeap() {
        return getField().isLeap(getMillis());
    }

    /**
     * Gets the amount by which this field is leap.
     * 
     * @return the amount by which the field is leap
     * @see DateTimeField#getLeapAmount
     */
    public int getLeapAmount() {
        return getField().getLeapAmount(getMillis());
    }

    /**
     * If this field were to leap, then it would be in units described by the
     * returned duration. If this field doesn't ever leap, null is returned.
     */
    public DurationField getLeapDurationField() {
        return getField().getLeapDurationField();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the minimum value for the field ignoring the current time.
     * 
     * @return the minimum value
     * @see DateTimeField#getMinimumValue
     */
    public int getMinimumValueOverall() {
        return getField().getMinimumValue();
    }

    /**
     * Gets the minimum value for the field.
     * 
     * @return the minimum value
     * @see DateTimeField#getMinimumValue
     */
    public int getMinimumValue() {
        return getField().getMinimumValue(getMillis());
    }

    /**
     * Gets the maximum value for the field ignoring the current time.
     * 
     * @return the maximum value
     * @see DateTimeField#getMaximumValue
     */
    public int getMaximumValueOverall() {
        return getField().getMaximumValue();
    }

    /**
     * Gets the maximum value for the field.
     * 
     * @return the maximum value
     * @see DateTimeField#getMaximumValue
     */
    public int getMaximumValue() {
        return getField().getMaximumValue(getMillis());
    }

    /**
     * Gets the maximum text length for the field.
     * 
     * @param locale  optional locale to use for selecting a text symbol
     * @return the maximum length
     * @see DateTimeField#getMaximumTextLength
     */
    public int getMaximumTextLength(Locale locale) {
        return getField().getMaximumTextLength(locale);
    }

    /**
     * Gets the maximum short text length for the field.
     * 
     * @param locale  optional locale to use for selecting a text symbol
     * @return the maximum length
     * @see DateTimeField#getMaximumShortTextLength
     */
    public int getMaximumShortTextLength(Locale locale) {
        return getField().getMaximumShortTextLength(locale);
    }


    /**
     * Returns the fractional duration milliseconds of this field.
     *
     * @see DateTimeField#remainder
     * @return remainder duration, in milliseconds
     */
    public long remainder() {
        return getField().remainder(getMillis());
    }

    /**
     * Returns the interval that represents the range of the minimum
     * and maximum values of this field.
     * <p>
     * For example, <code>datetime.monthOfYear().toInterval()</code>
     * will return an interval over the whole month.
     *
     * @return the interval of this field
     * @since 1.2
     */
    public Interval toInterval() {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[5]++;
        DateTimeField field = getField();
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[6]++;
        long start = field.roundFloor(getMillis());
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[7]++;
        long end = field.add(start, 1);
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[8]++;
        Interval interval = new Interval(start, end);
        return interval;
    }

    //-----------------------------------------------------------------------
    /**
     * Compare this field to the same field on another instant.
     * <p>
     * The comparison is based on the value of the same field type, irrespective
     * of any difference in chronology. Thus, if this property represents the
     * hourOfDay field, then the hourOfDay field of the other instant will be queried
     * whether in the same chronology or not.
     * 
     * @param instant  the instant to compare to
     * @return negative value if this is less, 0 if equal, or positive value if greater
     * @throws IllegalArgumentException if the instant is null
     */
    public int compareTo(ReadableInstant instant) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[5]++;
            throw new IllegalArgumentException("The instant must not be null");

        } else {
  CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[6]++;}
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[10]++;
        int thisValue = get();
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[11]++;
        int otherValue = instant.get(getFieldType());
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thisValue < otherValue) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[7]++;
            return -1;

        } else {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[8]++;
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisValue > otherValue) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[9]++;
            return 1;

        } else {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[10]++;
            return 0;
        }
}
    }

    //-----------------------------------------------------------------------
    /**
     * Compare this field to the same field on another partial instant.
     * <p>
     * The comparison is based on the value of the same field type, irrespective
     * of any difference in chronology. Thus, if this property represents the
     * hourOfDay field, then the hourOfDay field of the other partial will be queried
     * whether in the same chronology or not.
     * 
     * @param partial  the partial to compare to
     * @return negative value if this is less, 0 if equal, or positive value if greater
     * @throws IllegalArgumentException if the partial is null
     * @throws IllegalArgumentException if the partial doesn't support this field
     */
    public int compareTo(ReadablePartial partial) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[11]++;
            throw new IllegalArgumentException("The partial must not be null");

        } else {
  CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[12]++;}
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[15]++;
        int thisValue = get();
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[16]++;
        int otherValue = partial.get(getFieldType());
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((thisValue < otherValue) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[13]++;
            return -1;

        } else {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[14]++;
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[18]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((thisValue > otherValue) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[15]++;
            return 1;

        } else {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[16]++;
            return 0;
        }
}
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this property to another.
     * 
     * @param object  the object to compare to
     * @return true if equal
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[19]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this == object) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[17]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[18]++;}
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[20]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((object instanceof AbstractReadableInstantFieldProperty == false) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[19]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.branches[20]++;}
CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9.statements[21]++;
        AbstractReadableInstantFieldProperty other = (AbstractReadableInstantFieldProperty) object;
        return 
            get() == other.get() &&
            getFieldType().equals(other.getFieldType()) &&
            FieldUtils.equals(getChronology(), other.getChronology());
    }

    /**
     * Returns a hashcode compatible with the equals method.
     * 
     * @return the hashcode
     */
    public int hashCode() {
        return get() * 17 + getFieldType().hashCode() + getChronology().hashCode();
    }

    //-----------------------------------------------------------------------
    /**
     * Output a debugging string.
     * 
     * @return debugging string
     */
    public String toString() {
        return "Property[" + getName() + "]";
    }

}

class CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9 ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.joda.time.field.AbstractReadableInstantFieldProperty.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$1944r9ys7c7zxr70eujnfcacryq1gkrnohfnlb9q81a6zhhwj8qfwdttbxzs0va9 () {
    super("org.joda.time.field.AbstractReadableInstantFieldProperty.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.AbstractReadableInstantFieldProperty.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

