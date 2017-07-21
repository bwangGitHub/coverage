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

import java.util.Locale;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;

/**
 * BaseDateTimeField provides the common behaviour for DateTimeField
 * implementations. 
 * <p>
 * This class should generally not be used directly by API users. The
 * DateTimeField class should be used when different kinds of DateTimeField
 * objects are to be referenced.
 * <p>
 * BaseDateTimeField is thread-safe and immutable, and its subclasses must
 * be as well.
 *
 * @author Brian S O'Neill
 * @since 1.0
 * @see DecoratedDateTimeField
 */
public abstract class BaseDateTimeField extends DateTimeField {
  static {
    CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.ping();
  }


    /** The field type. */
    private final DateTimeFieldType iType;

    /**
     * Constructor.
     */
    protected BaseDateTimeField(DateTimeFieldType type) {
        super();
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[1]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[1]++;
            throw new IllegalArgumentException("The type must not be null");

        } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[2]++;}
        iType = type;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[3]++;
    }
    
    public final DateTimeFieldType getType() {
        return iType;
    }

    public final String getName() {
        return iType.getName();
    }

    /**
     * @return true always
     */
    public final boolean isSupported() {
        return true;
    }

    // Main access API
    //------------------------------------------------------------------------
    /**
     * Get the value of this field from the milliseconds.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @return the value of the field, in the units of the field
     */
    public abstract int get(long instant);

    //-----------------------------------------------------------------------
    /**
     * Get the human-readable, text value of this field from the milliseconds.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation returns getAsText(get(instant), locale).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @param locale the locale to use for selecting a text symbol, null means default
     * @return the text value of the field
     */
    public String getAsText(long instant, Locale locale) {
        return getAsText(get(instant), locale);
    }

    /**
     * Get the human-readable, text value of this field from the milliseconds.
     * <p>
     * The default implementation calls {@link #getAsText(long, Locale)}.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @return the text value of the field
     */
    public final String getAsText(long instant) {
        return getAsText(instant, null);
    }

    /**
     * Get the human-readable, text value of this field from a partial instant.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation returns getAsText(fieldValue, locale).
     *
     * @param partial  the partial instant to query
     * @param fieldValue  the field value of this field, provided for performance
     * @param locale  the locale to use for selecting a text symbol, null for default
     * @return the text value of the field
     */
    public String getAsText(ReadablePartial partial, int fieldValue, Locale locale) {
        return getAsText(fieldValue, locale);
    }

    /**
     * Get the human-readable, text value of this field from a partial instant.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation calls {@link ReadablePartial#get(DateTimeFieldType)}
     * and {@link #getAsText(ReadablePartial, int, Locale)}.
     *
     * @param partial  the partial instant to query
     * @param locale  the locale to use for selecting a text symbol, null for default
     * @return the text value of the field
     */
    public final String getAsText(ReadablePartial partial, Locale locale) {
        return getAsText(partial, partial.get(getType()), locale);
    }

    /**
     * Get the human-readable, text value of this field from the field value.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation returns Integer.toString(get(instant)).
     * <p>
     * Note: subclasses that override this method should also override
     * getMaximumTextLength.
     *
     * @param fieldValue  the numeric value to convert to text
     * @param locale the locale to use for selecting a text symbol, null for default
     * @return the text value of the field
     */
    public String getAsText(int fieldValue, Locale locale) {
        return Integer.toString(fieldValue);
    }

    //-----------------------------------------------------------------------
    /**
     * Get the human-readable, short text value of this field from the milliseconds.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation returns getAsShortText(get(instant), locale).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @param locale the locale to use for selecting a text symbol, null means default
     * @return the text value of the field
     */
    public String getAsShortText(long instant, Locale locale) {
        return getAsShortText(get(instant), locale);
    }

    /**
     * Get the human-readable, short text value of this field from the milliseconds.
     * <p>
     * The default implementation calls {@link #getAsShortText(long, Locale)}.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @return the text value of the field
     */
    public final String getAsShortText(long instant) {
        return getAsShortText(instant, null);
    }

    /**
     * Get the human-readable, short text value of this field from a partial instant.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation returns getAsShortText(fieldValue, locale).
     *
     * @param partial  the partial instant to query
     * @param fieldValue  the field value of this field, provided for performance
     * @param locale  the locale to use for selecting a text symbol, null for default
     * @return the text value of the field
     */
    public String getAsShortText(ReadablePartial partial, int fieldValue, Locale locale) {
        return getAsShortText(fieldValue, locale);
    }

    /**
     * Get the human-readable, short text value of this field from a partial instant.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation calls {@link ReadablePartial#get(DateTimeFieldType)}
     * and {@link #getAsText(ReadablePartial, int, Locale)}.
     *
     * @param partial  the partial instant to query
     * @param locale  the locale to use for selecting a text symbol, null for default
     * @return the text value of the field
     */
    public final String getAsShortText(ReadablePartial partial, Locale locale) {
        return getAsShortText(partial, partial.get(getType()), locale);
    }

    /**
     * Get the human-readable, short text value of this field from the field value.
     * If the specified locale is null, the default locale is used.
     * <p>
     * The default implementation returns getAsText(fieldValue, locale).
     * <p>
     * Note: subclasses that override this method should also override
     * getMaximumShortTextLength.
     *
     * @param fieldValue  the numeric value to convert to text
     * @param locale the locale to use for selecting a text symbol, null for default
     * @return the text value of the field
     */
    public String getAsShortText(int fieldValue, Locale locale) {
        return getAsText(fieldValue, locale);
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a value (which may be negative) to the instant value,
     * overflowing into larger fields if necessary.
     * <p>
     * The value will be added to this field. If the value is too large to be
     * added solely to this field, larger fields will increase as required.
     * Smaller fields should be unaffected, except where the result would be
     * an invalid value for a smaller field. In this case the smaller field is
     * adjusted to be in range.
     * <p>
     * For example, in the ISO chronology:<br>
     * 2000-08-20 add six months is 2001-02-20<br>
     * 2000-08-20 add twenty months is 2002-04-20<br>
     * 2000-08-20 add minus nine months is 1999-11-20<br>
     * 2001-01-31 add one month  is 2001-02-28<br>
     * 2001-01-31 add two months is 2001-03-31<br>
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to add to
     * @param value  the value to add, in the units of the field
     * @return the updated milliseconds
     */
    public long add(long instant, int value) {
        return getDurationField().add(instant, value);
    }

    /**
     * Adds a value (which may be negative) to the instant value,
     * overflowing into larger fields if necessary.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to add to
     * @param value  the long value to add, in the units of the field
     * @return the updated milliseconds
     * @throws IllegalArgumentException if value is too large
     * @see #add(long,int)
     */
    public long add(long instant, long value) {
        return getDurationField().add(instant, value);
    }

    /**
     * Adds a value (which may be negative) to the partial instant,
     * throwing an exception if the maximum size of the instant is reached.
     * <p>
     * The value will be added to this field, overflowing into larger fields
     * if necessary. Smaller fields should be unaffected, except where the
     * result would be an invalid value for a smaller field. In this case the
     * smaller field is adjusted to be in range.
     * <p>
     * Partial instants only contain some fields. This may result in a maximum
     * possible value, such as TimeOfDay being limited to 23:59:59:999. If this
     * limit is breached by the add an exception is thrown.
     * <p>
     * For example, in the ISO chronology:<br>
     * 2000-08-20 add six months is 2000-02-20<br>
     * 2000-08-20 add twenty months is 2000-04-20<br>
     * 2000-08-20 add minus nine months is 2000-11-20<br>
     * 2001-01-31 add one month  is 2001-02-28<br>
     * 2001-01-31 add two months is 2001-03-31<br>
     * 
     * @param instant  the partial instant
     * @param fieldIndex  the index of this field in the partial
     * @param values  the values of the partial instant which should be updated
     * @param valueToAdd  the value to add, in the units of the field
     * @return the passed in values
     * @throws IllegalArgumentException if the value is invalid or the maximum instant is reached
     */
    public int[] add(ReadablePartial instant, int fieldIndex, int[] values, int valueToAdd) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((valueToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[3]++;
            return values;

        } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[4]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[5]++;
        // there are more efficient algorithms than this (especially for time only fields)
        // trouble is when dealing with days and months, so we use this technique of
        // adding/removing one from the larger field at a time
        DateTimeField nextField = null;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((valueToAdd > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[1]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[2]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[3]++;
}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[7]++;
            int max = getMaximumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[8]++;
            long proposed = values[fieldIndex] + valueToAdd;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((proposed <= max) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[5]++;
                values[fieldIndex] = (int) proposed;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[10]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[11]++;
                break;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[6]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((nextField == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[7]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fieldIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[9]++;
                    throw new IllegalArgumentException("Maximum value exceeded for add");

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[10]++;}
                nextField = instant.getField(fieldIndex - 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[14]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
                // test only works if this field is UTC (ie. local)
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getRangeDurationField().getType() != nextField.getDurationField().getType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[11]++;
                    throw new IllegalArgumentException("Fields invalid for add");

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[12]++;}

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[8]++;}
            valueToAdd -= (max + 1) - values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[16]++;  // reduce the amount to add
            values = nextField.add(instant, fieldIndex - 1, values, 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[17]++;  // add 1 to next bigger field
            values[fieldIndex] = getMinimumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[18]++;  // reset this field to zero
        }
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((valueToAdd < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[4]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[5]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[6]++;
}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[20]++;
            int min = getMinimumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[21]++;
            long proposed = values[fieldIndex] + valueToAdd;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((proposed >= min) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[13]++;
                values[fieldIndex] = (int) proposed;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[23]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[24]++;
                break;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[14]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nextField == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[15]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[26]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((fieldIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[17]++;
                    throw new IllegalArgumentException("Maximum value exceeded for add");

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[18]++;}
                nextField = instant.getField(fieldIndex - 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[27]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[28]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((getRangeDurationField().getType() != nextField.getDurationField().getType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[19]++;
                    throw new IllegalArgumentException("Fields invalid for add");

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[16]++;}
            valueToAdd -= (min - 1) - values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[29]++;  // reduce the amount to add
            values = nextField.add(instant, fieldIndex - 1, values, -1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[30]++;  // subtract 1 from next bigger field
            values[fieldIndex] = getMaximumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[31]++;  // reset this field to max value
        }
        
        return set(instant, fieldIndex, values, values[fieldIndex]);  // adjusts smaller fields
    }

    /**
     * Adds a value (which may be negative) to the partial instant,
     * wrapping the whole partial if the maximum size of the partial is reached.
     * <p>
     * The value will be added to this field, overflowing into larger fields
     * if necessary. Smaller fields should be unaffected, except where the
     * result would be an invalid value for a smaller field. In this case the
     * smaller field is adjusted to be in range.
     * <p>
     * Partial instants only contain some fields. This may result in a maximum
     * possible value, such as TimeOfDay normally being limited to 23:59:59:999.
     * If ths limit is reached by the addition, this method will wrap back to
     * 00:00:00.000. In fact, you would generally only use this method for
     * classes that have a limitation such as this.
     * <p>
     * For example, in the ISO chronology:<br>
     * 10:20:30 add 20 minutes is 10:40:30<br>
     * 10:20:30 add 45 minutes is 11:05:30<br>
     * 10:20:30 add 16 hours is 02:20:30<br>
     * 
     * @param instant  the partial instant
     * @param fieldIndex  the index of this field in the partial
     * @param values  the values of the partial instant which should be updated
     * @param valueToAdd  the value to add, in the units of the field
     * @return the passed in values
     * @throws IllegalArgumentException if the value is invalid or the maximum instant is reached
     */
    public int[] addWrapPartial(ReadablePartial instant, int fieldIndex, int[] values, int valueToAdd) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[32]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((valueToAdd == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[21]++;
            return values;

        } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[22]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[33]++;
        // there are more efficient algorithms than this (especially for time only fields)
        // trouble is when dealing with days and months, so we use this technique of
        // adding/removing one from the larger field at a time
        DateTimeField nextField = null;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
        
        while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((valueToAdd > 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[7]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[8]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[9]++;
}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[35]++;
            int max = getMaximumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[36]++;
            long proposed = values[fieldIndex] + valueToAdd;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[37]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((proposed <= max) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[23]++;
                values[fieldIndex] = (int) proposed;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[38]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[39]++;
                break;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[24]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[40]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((nextField == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[25]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[41]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((fieldIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[27]++;
                    valueToAdd -= (max + 1) - values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[42]++;
                    values[fieldIndex] = getMinimumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[43]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[44]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[28]++;}
                nextField = instant.getField(fieldIndex - 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[45]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[46]++;
int CodeCoverConditionCoverageHelper_C18;
                // test only works if this field is UTC (ie. local)
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((getRangeDurationField().getType() != nextField.getDurationField().getType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[29]++;
                    throw new IllegalArgumentException("Fields invalid for add");

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[26]++;}
            valueToAdd -= (max + 1) - values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[47]++;  // reduce the amount to add
            values = nextField.addWrapPartial(instant, fieldIndex - 1, values, 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[48]++;  // add 1 to next bigger field
            values[fieldIndex] = getMinimumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[49]++;  // reset this field to zero
        }
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[50]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((valueToAdd < 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[10]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[11]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[12]++;
}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[51]++;
            int min = getMinimumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[52]++;
            long proposed = values[fieldIndex] + valueToAdd;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[53]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((proposed >= min) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[31]++;
                values[fieldIndex] = (int) proposed;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[54]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[55]++;
                break;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[32]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[56]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((nextField == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[33]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[57]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fieldIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[35]++;
                    valueToAdd -= (min - 1) - values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[58]++;
                    values[fieldIndex] = getMaximumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[59]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[60]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[36]++;}
                nextField = instant.getField(fieldIndex - 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[61]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[62]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getRangeDurationField().getType() != nextField.getDurationField().getType()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[37]++;
                    throw new IllegalArgumentException("Fields invalid for add");

                } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[34]++;}
            valueToAdd -= (min - 1) - values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[63]++;  // reduce the amount to add
            values = nextField.addWrapPartial(instant, fieldIndex - 1, values, -1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[64]++;  // subtract 1 from next bigger field
            values[fieldIndex] = getMaximumValue(instant, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[65]++;  // reset this field to max value
        }
        
        return set(instant, fieldIndex, values, values[fieldIndex]);  // adjusts smaller fields
    }

    /**
     * Adds a value (which may be negative) to the instant value,
     * wrapping within this field.
     * <p>
     * The value will be added to this field. If the value is too large to be
     * added solely to this field then it wraps. Larger fields are always
     * unaffected. Smaller fields should be unaffected, except where the
     * result would be an invalid value for a smaller field. In this case the
     * smaller field is adjusted to be in range.
     * <p>
     * For example, in the ISO chronology:<br>
     * 2000-08-20 addWrapField six months is 2000-02-20<br>
     * 2000-08-20 addWrapField twenty months is 2000-04-20<br>
     * 2000-08-20 addWrapField minus nine months is 2000-11-20<br>
     * 2001-01-31 addWrapField one month  is 2001-02-28<br>
     * 2001-01-31 addWrapField two months is 2001-03-31<br>
     * <p>
     * The default implementation internally calls set. Subclasses are
     * encouraged to provide a more efficient implementation.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to add to
     * @param value  the value to add, in the units of the field
     * @return the updated milliseconds
     */
    public long addWrapField(long instant, int value) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[66]++;
        int current = get(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[67]++;
        int wrapped = FieldUtils.getWrappedValue
            (current, value, getMinimumValue(instant), getMaximumValue(instant));
        return set(instant, wrapped);
    }

    /**
     * Adds a value (which may be negative) to the partial instant,
     * wrapping within this field.
     * <p>
     * The value will be added to this field. If the value is too large to be
     * added solely to this field then it wraps. Larger fields are always
     * unaffected. Smaller fields should be unaffected, except where the
     * result would be an invalid value for a smaller field. In this case the
     * smaller field is adjusted to be in range.
     * <p>
     * For example, in the ISO chronology:<br>
     * 2000-08-20 addWrapField six months is 2000-02-20<br>
     * 2000-08-20 addWrapField twenty months is 2000-04-20<br>
     * 2000-08-20 addWrapField minus nine months is 2000-11-20<br>
     * 2001-01-31 addWrapField one month  is 2001-02-28<br>
     * 2001-01-31 addWrapField two months is 2001-03-31<br>
     * <p>
     * The default implementation internally calls set. Subclasses are
     * encouraged to provide a more efficient implementation.
     * 
     * @param instant  the partial instant
     * @param fieldIndex  the index of this field in the instant
     * @param values  the values of the partial instant which should be updated
     * @param valueToAdd  the value to add, in the units of the field
     * @return the passed in values
     * @throws IllegalArgumentException if the value is invalid
     */
    public int[] addWrapField(ReadablePartial instant, int fieldIndex, int[] values, int valueToAdd) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[68]++;
        int current = values[fieldIndex];
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[69]++;
        int wrapped = FieldUtils.getWrappedValue
            (current, valueToAdd, getMinimumValue(instant), getMaximumValue(instant));
        return set(instant, fieldIndex, values, wrapped);  // adjusts smaller fields
    }

    //-----------------------------------------------------------------------
    /**
     * Computes the difference between two instants, as measured in the units
     * of this field. Any fractional units are dropped from the result. Calling
     * getDifference reverses the effect of calling add. In the following code:
     *
     * <pre>
     * long instant = ...
     * int v = ...
     * int age = getDifference(add(instant, v), instant);
     * </pre>
     *
     * The value 'age' is the same as the value 'v'.
     *
     * @param minuendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract from
     * @param subtrahendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract off the minuend
     * @return the difference in the units of this field
     */
    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return getDurationField().getDifference(minuendInstant, subtrahendInstant);
    }

    /**
     * Computes the difference between two instants, as measured in the units
     * of this field. Any fractional units are dropped from the result. Calling
     * getDifference reverses the effect of calling add. In the following code:
     *
     * <pre>
     * long instant = ...
     * long v = ...
     * long age = getDifferenceAsLong(add(instant, v), instant);
     * </pre>
     *
     * The value 'age' is the same as the value 'v'.
     *
     * @param minuendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract from
     * @param subtrahendInstant the milliseconds from 1970-01-01T00:00:00Z to
     * subtract off the minuend
     * @return the difference in the units of this field
     */
    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return getDurationField().getDifferenceAsLong(minuendInstant, subtrahendInstant);
    }

    /**
     * Sets a value in the milliseconds supplied.
     * <p>
     * The value of this field will be set.
     * If the value is invalid, an exception if thrown.
     * <p>
     * If setting this field would make other fields invalid, then those fields
     * may be changed. For example if the current date is the 31st January, and
     * the month is set to February, the day would be invalid. Instead, the day
     * would be changed to the closest value - the 28th/29th February as appropriate.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to set in
     * @param value  the value to set, in the units of the field
     * @return the updated milliseconds
     * @throws IllegalArgumentException if the value is invalid
     */
    public abstract long set(long instant, int value);

    /**
     * Sets a value using the specified partial instant.
     * <p>
     * The value of this field (specified by the index) will be set.
     * If the value is invalid, an exception if thrown.
     * <p>
     * If setting this field would make other fields invalid, then those fields
     * may be changed. For example if the current date is the 31st January, and
     * the month is set to February, the day would be invalid. Instead, the day
     * would be changed to the closest value - the 28th/29th February as appropriate.
     * 
     * @param partial  the partial instant
     * @param fieldIndex  the index of this field in the instant
     * @param values  the values to update
     * @param newValue  the value to set, in the units of the field
     * @return the updated values
     * @throws IllegalArgumentException if the value is invalid
     */
    public int[] set(ReadablePartial partial, int fieldIndex, int[] values, int newValue) {
        FieldUtils.verifyValueBounds(this, newValue, getMinimumValue(partial, values), getMaximumValue(partial, values));
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[70]++;
        values[fieldIndex] = newValue;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[71]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[72]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[13]++;


int CodeCoverConditionCoverageHelper_C24;
        
        // may need to adjust smaller fields
        for (int i = fieldIndex + 1;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < partial.size()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[13]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[14]--;
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.loops[15]++;
}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[73]++;
            DateTimeField field = partial.getField(i);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[74]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((values[i] > field.getMaximumValue(partial, values)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[39]++;
                values[i] = field.getMaximumValue(partial, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[75]++;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[40]++;}
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[76]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((values[i] < field.getMinimumValue(partial, values)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[41]++;
                values[i] = field.getMinimumValue(partial, values);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[77]++;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[42]++;}
        }
        return values;
    }

    /**
     * Sets a value in the milliseconds supplied from a human-readable, text value.
     * If the specified locale is null, the default locale is used.
     * <p>
     * This implementation uses <code>convertText(String, Locale)</code> and
     * {@link #set(long, int)}.
     * <p>
     * Note: subclasses that override this method should also override
     * getAsText.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to set in
     * @param text  the text value to set
     * @param locale the locale to use for selecting a text symbol, null for default
     * @return the updated milliseconds
     * @throws IllegalArgumentException if the text value is invalid
     */
    public long set(long instant, String text, Locale locale) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[78]++;
        int value = convertText(text, locale);
        return set(instant, value);
    }

    /**
     * Sets a value in the milliseconds supplied from a human-readable, text value.
     * <p>
     * This implementation uses {@link #set(long, String, Locale)}.
     * <p>
     * Note: subclasses that override this method should also override getAsText.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to set in
     * @param text  the text value to set
     * @return the updated milliseconds
     * @throws IllegalArgumentException if the text value is invalid
     */
    public final long set(long instant, String text) {
        return set(instant, text, null);
    }

    /**
     * Sets a value in the milliseconds supplied from a human-readable, text value.
     * If the specified locale is null, the default locale is used.
     * <p>
     * This implementation uses <code>convertText(String, Locale)</code> and
     * {@link #set(ReadablePartial, int, int[], int)}.
     *
     * @param instant  the partial instant
     * @param fieldIndex  the index of this field in the instant
     * @param values  the values of the partial instant which should be updated
     * @param text  the text value to set
     * @param locale the locale to use for selecting a text symbol, null for default
     * @return the passed in values
     * @throws IllegalArgumentException if the text value is invalid
     */
    public int[] set(ReadablePartial instant, int fieldIndex, int[] values, String text, Locale locale) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[79]++;
        int value = convertText(text, locale);
        return set(instant, fieldIndex, values, value);
    }

    /**
     * Convert the specified text and locale into a value.
     * 
     * @param text  the text to convert
     * @param locale  the locale to convert using
     * @return the value extracted from the text
     * @throws IllegalArgumentException if the text is invalid
     */
    protected int convertText(String text, Locale locale) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[80]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[44]++;
            throw new IllegalFieldValueException(getType(), text);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[43]++;
}
  }
    }

    // Extra information API
    //------------------------------------------------------------------------
    /**
     * Returns the duration per unit value of this field. For example, if this
     * field represents "hour of day", then the unit duration is an hour.
     *
     * @return the duration of this field, or UnsupportedDurationField if field
     * has no duration
     */
    public abstract DurationField getDurationField();

    /**
     * Returns the range duration of this field. For example, if this field
     * represents "hour of day", then the range duration is a day.
     *
     * @return the range duration of this field, or null if field has no range
     */
    public abstract DurationField getRangeDurationField();

    /**
     * Returns whether this field is 'leap' for the specified instant.
     * <p>
     * For example, a leap year would return true, a non leap year would return
     * false.
     * <p>
     * This implementation returns false.
     * 
     * @return true if the field is 'leap'
     */
    public boolean isLeap(long instant) {
        return false;
    }

    /**
     * Gets the amount by which this field is 'leap' for the specified instant.
     * <p>
     * For example, a leap year would return one, a non leap year would return
     * zero.
     * <p>
     * This implementation returns zero.
     */
    public int getLeapAmount(long instant) {
        return 0;
    }

    /**
     * If this field were to leap, then it would be in units described by the
     * returned duration. If this field doesn't ever leap, null is returned.
     * <p>
     * This implementation returns null.
     */
    public DurationField getLeapDurationField() {
        return null;
    }

    /**
     * Get the minimum allowable value for this field.
     * 
     * @return the minimum valid value for this field, in the units of the
     * field
     */
    public abstract int getMinimumValue();

    /**
     * Get the minimum value for this field evaluated at the specified time.
     * <p>
     * This implementation returns the same as {@link #getMinimumValue()}.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @return the minimum value for this field, in the units of the field
     */
    public int getMinimumValue(long instant) {
        return getMinimumValue();
    }

    /**
     * Get the minimum value for this field evaluated at the specified instant.
     * <p>
     * This implementation returns the same as {@link #getMinimumValue()}.
     * 
     * @param instant  the partial instant to query
     * @return the minimum value for this field, in the units of the field
     */
    public int getMinimumValue(ReadablePartial instant) {
        return getMinimumValue();
    }

    /**
     * Get the minimum value for this field using the partial instant and
     * the specified values.
     * <p>
     * This implementation returns the same as {@link #getMinimumValue(ReadablePartial)}.
     * 
     * @param instant  the partial instant to query
     * @param values  the values to use
     * @return the minimum value for this field, in the units of the field
     */
    public int getMinimumValue(ReadablePartial instant, int[] values) {
        return getMinimumValue(instant);
    }

    /**
     * Get the maximum allowable value for this field.
     * 
     * @return the maximum valid value for this field, in the units of the
     * field
     */
    public abstract int getMaximumValue();

    /**
     * Get the maximum value for this field evaluated at the specified time.
     * <p>
     * This implementation returns the same as {@link #getMaximumValue()}.
     * 
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to query
     * @return the maximum value for this field, in the units of the field
     */
    public int getMaximumValue(long instant) {
        return getMaximumValue();
    }

    /**
     * Get the maximum value for this field evaluated at the specified instant.
     * <p>
     * This implementation returns the same as {@link #getMaximumValue()}.
     * 
     * @param instant  the partial instant to query
     * @return the maximum value for this field, in the units of the field
     */
    public int getMaximumValue(ReadablePartial instant) {
        return getMaximumValue();
    }

    /**
     * Get the maximum value for this field using the partial instant and
     * the specified values.
     * <p>
     * This implementation returns the same as {@link #getMaximumValue(ReadablePartial)}.
     * 
     * @param instant  the partial instant to query
     * @param values  the values to use
     * @return the maximum value for this field, in the units of the field
     */
    public int getMaximumValue(ReadablePartial instant, int[] values) {
        return getMaximumValue(instant);
    }

    /**
     * Get the maximum text value for this field. The default implementation
     * returns the equivalent of Integer.toString(getMaximumValue()).length().
     * 
     * @param locale  the locale to use for selecting a text symbol
     * @return the maximum text length
     */
    public int getMaximumTextLength(Locale locale) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[81]++;
        int max = getMaximumValue();
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[82]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((max >= 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[45]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[83]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((max < 10) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[47]++;
                return 1;

            } else {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[48]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[84]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((max < 100) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[49]++;
                return 2;

            } else {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[50]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[85]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((max < 1000) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[51]++;
                return 3;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[52]++;}
}
}

        } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[46]++;}
        return Integer.toString(max).length();
    }

    /**
     * Get the maximum short text value for this field. The default
     * implementation returns getMaximumTextLength().
     * 
     * @param locale  the locale to use for selecting a text symbol
     * @return the maximum short text length
     */
    public int getMaximumShortTextLength(Locale locale) {
        return getMaximumTextLength(locale);
    }

    // Calculation API
    //------------------------------------------------------------------------
    /**
     * Round to the lowest whole unit of this field. After rounding, the value
     * of this field and all fields of a higher magnitude are retained. The
     * fractional millis that cannot be expressed in whole increments of this
     * field are set to minimum.
     * <p>
     * For example, a datetime of 2002-11-02T23:34:56.789, rounded to the
     * lowest whole hour is 2002-11-02T23:00:00.000.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @return rounded milliseconds
     */
    public abstract long roundFloor(long instant);

    /**
     * Round to the highest whole unit of this field. The value of this field
     * and all fields of a higher magnitude may be incremented in order to
     * achieve this result. The fractional millis that cannot be expressed in
     * whole increments of this field are set to minimum.
     * <p>
     * For example, a datetime of 2002-11-02T23:34:56.789, rounded to the
     * highest whole hour is 2002-11-03T00:00:00.000.
     * <p>
     * The default implementation calls roundFloor, and if the instant is
     * modified as a result, adds one field unit. Subclasses are encouraged to
     * provide a more efficient implementation.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @return rounded milliseconds
     */
    public long roundCeiling(long instant) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[86]++;
        long newInstant = roundFloor(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[87]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((newInstant != instant) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[53]++;
            instant = add(newInstant, 1);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[88]++;

        } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[54]++;}
        return instant;
    }

    /**
     * Round to the nearest whole unit of this field. If the given millisecond
     * value is closer to the floor or is exactly halfway, this function
     * behaves like roundFloor. If the millisecond value is closer to the
     * ceiling, this function behaves like roundCeiling.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @return rounded milliseconds
     */
    public long roundHalfFloor(long instant) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[89]++;
        long floor = roundFloor(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[90]++;
        long ceiling = roundCeiling(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[91]++;

        long diffFromFloor = instant - floor;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[92]++;
        long diffToCeiling = ceiling - instant;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[93]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((diffFromFloor <= diffToCeiling) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[55]++;
            // Closer to the floor, or halfway - round floor
            return floor;

        } else {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[56]++;
            return ceiling;
        }
    }

    /**
     * Round to the nearest whole unit of this field. If the given millisecond
     * value is closer to the floor, this function behaves like roundFloor. If
     * the millisecond value is closer to the ceiling or is exactly halfway,
     * this function behaves like roundCeiling.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @return rounded milliseconds
     */
    public long roundHalfCeiling(long instant) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[94]++;
        long floor = roundFloor(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[95]++;
        long ceiling = roundCeiling(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[96]++;

        long diffFromFloor = instant - floor;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[97]++;
        long diffToCeiling = ceiling - instant;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[98]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((diffToCeiling <= diffFromFloor) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[57]++;
            // Closer to the ceiling, or halfway - round ceiling
            return ceiling;

        } else {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[58]++;
            return floor;
        }
    }

    /**
     * Round to the nearest whole unit of this field. If the given millisecond
     * value is closer to the floor, this function behaves like roundFloor. If
     * the millisecond value is closer to the ceiling, this function behaves
     * like roundCeiling.
     * <p>
     * If the millisecond value is exactly halfway between the floor and
     * ceiling, the ceiling is chosen over the floor only if it makes this
     * field's value even.
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z to round
     * @return rounded milliseconds
     */
    public long roundHalfEven(long instant) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[99]++;
        long floor = roundFloor(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[100]++;
        long ceiling = roundCeiling(instant);
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[101]++;

        long diffFromFloor = instant - floor;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[102]++;
        long diffToCeiling = ceiling - instant;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[103]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((diffFromFloor < diffToCeiling) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[59]++;
            // Closer to the floor - round floor
            return floor;

        } else {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[60]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[104]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((diffToCeiling < diffFromFloor) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[61]++;
            // Closer to the ceiling - round ceiling
            return ceiling;

        } else {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[62]++;
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.statements[105]++;
int CodeCoverConditionCoverageHelper_C36;
            // Round to the instant that makes this field even. If both values
            // make this field even (unlikely), favor the ceiling.
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 (((get(ceiling) & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[63]++;
                return ceiling;

            } else {
  CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t.branches[64]++;}
            return floor;
        }
}
    }

    /**
     * Returns the fractional duration milliseconds of this field. In other
     * words, calling remainder returns the duration that roundFloor would
     * subtract.
     * <p>
     * For example, on a datetime of 2002-11-02T23:34:56.789, the remainder by
     * hour is 34 minutes and 56.789 seconds.
     * <p>
     * The default implementation computes
     * <code>instant - roundFloor(instant)</code>. Subclasses are encouraged to
     * provide a more efficient implementation.
     *
     * @param instant the milliseconds from 1970-01-01T00:00:00Z to get the
     * remainder
     * @return remainder duration, in milliseconds
     */
    public long remainder(long instant) {
        return instant - roundFloor(instant);
    }

    /**
     * Get a suitable debug string.
     * 
     * @return debug string
     */
    public String toString() {
        return "DateTimeField[" + getName() + ']';
    }

}

class CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t ());
  }
    public static long[] statements = new long[106];
    public static long[] branches = new long[65];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[37];
  static {
    final String SECTION_NAME = "org.joda.time.field.BaseDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 36; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$aw3qat1087ugh9lp6sequ6rgbalrp0ls9t () {
    super("org.joda.time.field.BaseDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 105; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 64; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 36; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.BaseDateTimeField.java");
      for (int i = 1; i <= 105; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 64; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 36; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

