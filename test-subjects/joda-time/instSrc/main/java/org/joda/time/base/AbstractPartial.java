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

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;

/**
 * AbstractPartial provides a standard base implementation of most methods
 * in the ReadablePartial interface.
 * <p>
 * Calculations on are performed using a {@link Chronology}.
 * This chronology is set to be in the UTC time zone for all calculations.
 * <p>
 * The methods on this class use {@link ReadablePartial#size()},
 * {@link AbstractPartial#getField(int, Chronology)} and
 * {@link ReadablePartial#getValue(int)} to calculate their results.
 * Subclasses may have a better implementation.
 * <p>
 * AbstractPartial allows subclasses may be mutable and not thread-safe.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class AbstractPartial
        implements ReadablePartial, Comparable<ReadablePartial> {
  static {
    CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.ping();
  }


    //-----------------------------------------------------------------------
    /**
     * Constructor.
     */
    protected AbstractPartial() {
        super();
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[1]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the field for a specific index in the chronology specified.
     * <p>
     * This method must not use any instance variables.
     * 
     * @param index  the index to retrieve
     * @param chrono  the chronology to use
     * @return the field
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    protected abstract DateTimeField getField(int index, Chronology chrono);

    //-----------------------------------------------------------------------
    /**
     * Gets the field type at the specifed index.
     * 
     * @param index  the index
     * @return the field type
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DateTimeFieldType getFieldType(int index) {
        return getField(index, getChronology()).getType();
    }

    /**
     * Gets an array of the field types that this partial supports.
     * <p>
     * The fields are returned largest to smallest, for example Hour, Minute, Second.
     *
     * @return the fields supported in an array that may be altered, largest to smallest
     */
    public DateTimeFieldType[] getFieldTypes() {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[2]++;
        DateTimeFieldType[] result = new DateTimeFieldType[size()];
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[1]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[2]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[3]++;
}
            result[i] = getFieldType(i);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[4]++;
        }
        return result;
    }

    /**
     * Gets the field at the specifed index.
     * 
     * @param index  the index
     * @return the field
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DateTimeField getField(int index) {
        return getField(index, getChronology());
    }

    /**
     * Gets an array of the fields that this partial supports.
     * <p>
     * The fields are returned largest to smallest, for example Hour, Minute, Second.
     *
     * @return the fields supported in an array that may be altered, largest to smallest
     */
    public DateTimeField[] getFields() {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[5]++;
        DateTimeField[] result = new DateTimeField[size()];
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[6]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[4]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[5]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[6]++;
}
            result[i] = getField(i);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[7]++;
        }
        return result;
    }

    /**
     * Gets an array of the value of each of the fields that this partial supports.
     * <p>
     * The fields are returned largest to smallest, for example Hour, Minute, Second.
     * Each value corresponds to the same array index as <code>getFields()</code>
     *
     * @return the current values of each field in an array that may be altered, largest to smallest
     */
    public int[] getValues() {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[8]++;
        int[] result = new int[size()];
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[9]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[7]++;


int CodeCoverConditionCoverageHelper_C3;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < result.length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[7]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[8]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[9]++;
}
            result[i] = getValue(i);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[10]++;
        }
        return result;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the value of one of the fields of a datetime.
     * <p>
     * The field specified must be one of those that is supported by the partial.
     *
     * @param type  a DateTimeFieldType instance that is supported by this partial
     * @return the value of that field
     * @throws IllegalArgumentException if the field is null or not supported
     */
    public int get(DateTimeFieldType type) {
        return getValue(indexOfSupported(type));
    }

    /**
     * Checks whether the field specified is supported by this partial.
     *
     * @param type  the type to check, may be null which returns false
     * @return true if the field is supported
     */
    public boolean isSupported(DateTimeFieldType type) {
        return (indexOf(type) != -1);
    }

    /**
     * Gets the index of the specified field, or -1 if the field is unsupported.
     *
     * @param type  the type to check, may be null which returns -1
     * @return the index of the field, -1 if unsupported
     */
    public int indexOf(DateTimeFieldType type) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[11]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[10]++;


int CodeCoverConditionCoverageHelper_C4;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[10]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[11]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[12]++;
}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((getFieldType(i) == type) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[1]++;
                return i;

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[2]++;}
        }
        return -1;
    }

    /**
     * Gets the index of the specified field, throwing an exception if the
     * field is unsupported.
     *
     * @param type  the type to check, not null
     * @return the index of the field
     * @throws IllegalArgumentException if the field is null or not supported
     */
    protected int indexOfSupported(DateTimeFieldType type) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[13]++;
        int index = indexOf(type);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[3]++;
            throw new IllegalArgumentException("Field '" + type + "' is not supported");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[4]++;}
        return index;
    }

    /**
     * Gets the index of the first fields to have the specified duration,
     * or -1 if the field is unsupported.
     *
     * @param type  the type to check, may be null which returns -1
     * @return the index of the field, -1 if unsupported
     */
    protected int indexOf(DurationFieldType type) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[15]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[13]++;


int CodeCoverConditionCoverageHelper_C7;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[13]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[14]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[15]++;
}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getFieldType(i).getDurationType() == type) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[5]++;
                return i;

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[6]++;}
        }
        return -1;
    }

    /**
     * Gets the index of the first fields to have the specified duration,
     * throwing an exception if the field is unsupported.
     *
     * @param type  the type to check, not null
     * @return the index of the field
     * @throws IllegalArgumentException if the field is null or not supported
     */
    protected int indexOfSupported(DurationFieldType type) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[17]++;
        int index = indexOf(type);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[18]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[7]++;
            throw new IllegalArgumentException("Field '" + type + "' is not supported");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[8]++;}
        return index;
    }

    //-----------------------------------------------------------------------
    /**
     * Resolves this partial against another complete instant to create a new
     * full instant. The combination is performed using the chronology of the
     * specified instant.
     * <p>
     * For example, if this partial represents a time, then the result of this
     * method will be the datetime from the specified base instant plus the
     * time from this partial.
     *
     * @param baseInstant  the instant that provides the missing fields, null means now
     * @return the combined datetime
     */
    public DateTime toDateTime(ReadableInstant baseInstant) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[19]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(baseInstant);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[20]++;
        long instantMillis = DateTimeUtils.getInstantMillis(baseInstant);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[21]++;
        long resolved = chrono.set(this, instantMillis);
        return new DateTime(resolved, chrono);
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this ReadablePartial with another returning true if the chronology,
     * field types and values are equal.
     *
     * @param partial  an object to check against
     * @return true if fields and values are equal
     */
    public boolean equals(Object partial) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[22]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this == partial) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[9]++;
            return true;

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[10]++;}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[23]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((partial instanceof ReadablePartial == false) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[12]++;}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[24]++;
        ReadablePartial other = (ReadablePartial) partial;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[25]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((size() != other.size()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[14]++;}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[26]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[16]++;


int CodeCoverConditionCoverageHelper_C13;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[16]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[17]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[18]++;
}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[27]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((getValue(i) != other.getValue(i)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((getFieldType(i) != other.getFieldType(i)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[15]++;
                return false;

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[16]++;}
        }
        return FieldUtils.equals(getChronology(), other.getChronology());
    }

    /**
     * Gets a hash code for the ReadablePartial that is compatible with the 
     * equals method.
     *
     * @return a suitable hash code
     */
    public int hashCode() {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[28]++;
        int total = 157;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[29]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[19]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[19]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[20]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[21]++;
}
            total = 23 * total + getValue(i);
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[30]++;
            total = 23 * total + getFieldType(i).hashCode();
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[31]++;
        }
        total += getChronology().hashCode();
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[32]++;
        return total;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this partial with another returning an integer
     * indicating the order.
     * <p>
     * The fields are compared in order, from largest to smallest.
     * The first field that is non-equal is used to determine the result.
     * <p>
     * The specified object must be a partial instance whose field types
     * match those of this partial.
     * <p>
     * NOTE: Prior to v2.0, the {@code Comparable} interface was only implemented
     * in this class and not in the {@code ReadablePartial} interface.
     *
     * @param other  an object to check against
     * @return negative if this is less, zero if equal, positive if greater
     * @throws ClassCastException if the partial is the wrong class
     *  or if it has field types that don't match
     * @throws NullPointerException if the partial is null
     * @since 1.1
     */
    public int compareTo(ReadablePartial other) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[33]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this == other) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[17]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[18]++;}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[34]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((size() != other.size()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[19]++;
            throw new ClassCastException("ReadablePartial objects must have matching field types");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[20]++;}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[35]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[22]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[22]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[23]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[24]++;
}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[36]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getFieldType(i) != other.getFieldType(i)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[21]++;
                throw new ClassCastException("ReadablePartial objects must have matching field types");

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[22]++;}
        }
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[37]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[25]++;


int CodeCoverConditionCoverageHelper_C20;
        // fields are ordered largest first
        for (int i = 0, isize = size();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < isize) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[25]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[26]--;
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.loops[27]++;
}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[38]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((getValue(i) > other.getValue(i)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[23]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[24]++;}
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[39]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((getValue(i) < other.getValue(i)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[25]++;
                return -1;

            } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[26]++;}
        }
        return 0;
    }

    /**
     * Is this partial later than the specified partial.
     * <p>
     * The fields are compared in order, from largest to smallest.
     * The first field that is non-equal is used to determine the result.
     * <p>
     * You may not pass null into this method. This is because you need
     * a time zone to accurately determine the current date.
     *
     * @param partial  a partial to check against, must not be null
     * @return true if this date is after the date passed in
     * @throws IllegalArgumentException if the specified partial is null
     * @throws ClassCastException if the partial has field types that don't match
     * @since 1.1
     */
    public boolean isAfter(ReadablePartial partial) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[40]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[27]++;
            throw new IllegalArgumentException("Partial cannot be null");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[28]++;}
        return compareTo(partial) > 0;
    }

    /**
     * Is this partial earlier than the specified partial.
     * <p>
     * The fields are compared in order, from largest to smallest.
     * The first field that is non-equal is used to determine the result.
     * <p>
     * You may not pass null into this method. This is because you need
     * a time zone to accurately determine the current date.
     *
     * @param partial  a partial to check against, must not be null
     * @return true if this date is before the date passed in
     * @throws IllegalArgumentException if the specified partial is null
     * @throws ClassCastException if the partial has field types that don't match
     * @since 1.1
     */
    public boolean isBefore(ReadablePartial partial) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[41]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[29]++;
            throw new IllegalArgumentException("Partial cannot be null");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[30]++;}
        return compareTo(partial) < 0;
    }

    /**
     * Is this partial the same as the specified partial.
     * <p>
     * The fields are compared in order, from largest to smallest.
     * If all fields are equal, the result is true.
     * <p>
     * You may not pass null into this method. This is because you need
     * a time zone to accurately determine the current date.
     *
     * @param partial  a partial to check against, must not be null
     * @return true if this date is the same as the date passed in
     * @throws IllegalArgumentException if the specified partial is null
     * @throws ClassCastException if the partial has field types that don't match
     * @since 1.1
     */
    public boolean isEqual(ReadablePartial partial) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[42]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[31]++;
            throw new IllegalArgumentException("Partial cannot be null");

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[32]++;}
        return compareTo(partial) == 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Uses the specified formatter to convert this partial to a String.
     *
     * @param formatter  the formatter to use, null means use <code>toString()</code>.
     * @return the formatted string
     * @since 1.1
     */
    public String toString(DateTimeFormatter formatter) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.statements[43]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[33]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl.branches[34]++;}
        return formatter.print(this);
    }

}

class CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl ());
  }
    public static long[] statements = new long[44];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.joda.time.base.AbstractPartial.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$7mye35nvnajjywbg4aas8kirsab6ijl () {
    super("org.joda.time.base.AbstractPartial.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 43; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.base.AbstractPartial.java");
      for (int i = 1; i <= 43; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

