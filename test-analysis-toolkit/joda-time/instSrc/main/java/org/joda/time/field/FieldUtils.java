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

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

/**
 * General utilities that don't fit elsewhere.
 * <p>
 * FieldUtils is thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public class FieldUtils {
  static {
    CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.ping();
  }


    /**
     * Restricted constructor.
     */
    private FieldUtils() {
        super();
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[1]++;
    }
    
    //------------------------------------------------------------------------
    /**
     * Negates the input throwing an exception if it can't negate it.
     * 
     * @param value  the value to negate
     * @return the negated value
     * @throws ArithmeticException if the value is Integer.MIN_VALUE
     * @since 1.1
     */
    public static int safeNegate(int value) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value == Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[1]++;
            throw new ArithmeticException("Integer.MIN_VALUE cannot be negated");

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[2]++;}
        return -value;
    }
    
    /**
     * Add two values throwing an exception if overflow occurs.
     * 
     * @param val1  the first value
     * @param val2  the second value
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     */
    public static int safeAdd(int val1, int val2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[3]++;
        int sum = val1 + val2;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        // If there is a sign change, but the two values have the same sign...
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 (((val1 ^ sum) < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((val1 ^ val2) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[3]++;
            throw new ArithmeticException
                ("The calculation caused an overflow: " + val1 + " + " + val2);

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[4]++;}
        return sum;
    }
    
    /**
     * Add two values throwing an exception if overflow occurs.
     * 
     * @param val1  the first value
     * @param val2  the second value
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     */
    public static long safeAdd(long val1, long val2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[5]++;
        long sum = val1 + val2;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        // If there is a sign change, but the two values have the same sign...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 (((val1 ^ sum) < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 (((val1 ^ val2) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[5]++;
            throw new ArithmeticException
                ("The calculation caused an overflow: " + val1 + " + " + val2);

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[6]++;}
        return sum;
    }
    
    /**
     * Subtracts two values throwing an exception if overflow occurs.
     * 
     * @param val1  the first value, to be taken away from
     * @param val2  the second value, the amount to take away
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     */
    public static long safeSubtract(long val1, long val2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[7]++;
        long diff = val1 - val2;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        // If there is a sign change, but the two values have different signs...
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 (((val1 ^ diff) < 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (((val1 ^ val2) < 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[7]++;
            throw new ArithmeticException
                ("The calculation caused an overflow: " + val1 + " - " + val2);

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[8]++;}
        return diff;
    }
    
    /**
     * Multiply two values throwing an exception if overflow occurs.
     * 
     * @param val1  the first value
     * @param val2  the second value
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     * @since 1.2
     */
    public static int safeMultiply(int val1, int val2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[9]++;
        long total = (long) val1 * (long) val2;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((total < Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((total > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[9]++;
            throw new ArithmeticException
                ("The calculation caused an overflow: " + val1 + " * " + val2);

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[10]++;}
        return (int) total;
    }

    /**
     * Multiply two values throwing an exception if overflow occurs.
     * 
     * @param val1  the first value
     * @param scalar  the second value
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     * @since 1.2
     */
    public static long safeMultiply(long val1, int scalar) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[11]++;
        switch (scalar) {
        case -1:
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[11]++;
            return -val1;
        case 0:
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[12]++;
            return 0L;
        case 1:
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[13]++;
            return val1; default : CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[14]++;
        }
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[12]++;
        long total = val1 * scalar;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((total / scalar != val1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[15]++;
            throw new ArithmeticException
                ("The calculation caused an overflow: " + val1 + " * " + scalar);

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[16]++;}
        return total;
    }

    /**
     * Multiply two values throwing an exception if overflow occurs.
     * 
     * @param val1  the first value
     * @param val2  the second value
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     */
    public static long safeMultiply(long val1, long val2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((val2 == 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[17]++;
            return val1;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[18]++;}
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[15]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((val2 == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[19]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[20]++;}
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[16]++;
        long total = val1 * val2;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((total / val2 != val1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[21]++;
            throw new ArithmeticException
                ("The calculation caused an overflow: " + val1 + " * " + val2);

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[22]++;}
        return total;
    }
    
    /**
     * Casts to an int throwing an exception if overflow occurs.
     * 
     * @param value  the value
     * @return the value as an int
     * @throws ArithmeticException if the value is too big or too small
     */
    public static int safeToInt(long value) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[18]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((Integer.MIN_VALUE <= value) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value <= Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[23]++;
            return (int) value;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[24]++;}
        throw new ArithmeticException("Value cannot fit in an int: " + value);
    }
    
    /**
     * Multiply two values to return an int throwing an exception if overflow occurs.
     * 
     * @param val1  the first value
     * @param val2  the second value
     * @return the new total
     * @throws ArithmeticException if the value is too big or too small
     */
    public static int safeMultiplyToInt(long val1, long val2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[19]++;
        long val = FieldUtils.safeMultiply(val1, val2);
        return FieldUtils.safeToInt(val);
    }

    //-----------------------------------------------------------------------
    /**
     * Verify that input values are within specified bounds.
     * 
     * @param value  the value to check
     * @param lowerBound  the lower bound allowed for value
     * @param upperBound  the upper bound allowed for value
     * @throws IllegalFieldValueException if value is not in the specified bounds
     */
    public static void verifyValueBounds(DateTimeField field, 
                                         int value, int lowerBound, int upperBound) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[20]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((value < lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value > upperBound) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[25]++;
            throw new IllegalFieldValueException
                (field.getType(), Integer.valueOf(value),
                 Integer.valueOf(lowerBound), Integer.valueOf(upperBound));

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[26]++;}
    }

    /**
     * Verify that input values are within specified bounds.
     * 
     * @param value  the value to check
     * @param lowerBound  the lower bound allowed for value
     * @param upperBound  the upper bound allowed for value
     * @throws IllegalFieldValueException if value is not in the specified bounds
     * @since 1.1
     */
    public static void verifyValueBounds(DateTimeFieldType fieldType, 
                                         int value, int lowerBound, int upperBound) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[21]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((value < lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value > upperBound) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[27]++;
            throw new IllegalFieldValueException
                (fieldType, Integer.valueOf(value),
                 Integer.valueOf(lowerBound), Integer.valueOf(upperBound));

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[28]++;}
    }

    /**
     * Verify that input values are within specified bounds.
     * 
     * @param value  the value to check
     * @param lowerBound  the lower bound allowed for value
     * @param upperBound  the upper bound allowed for value
     * @throws IllegalFieldValueException if value is not in the specified bounds
     */
    public static void verifyValueBounds(String fieldName,
                                         int value, int lowerBound, int upperBound) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[22]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((value < lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value > upperBound) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[29]++;
            throw new IllegalFieldValueException
                (fieldName, Integer.valueOf(value),
                 Integer.valueOf(lowerBound), Integer.valueOf(upperBound));

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[30]++;}
    }

    /**
     * Utility method used by addWrapField implementations to ensure the new
     * value lies within the field's legal value range.
     *
     * @param currentValue the current value of the data, which may lie outside
     * the wrapped value range
     * @param wrapValue  the value to add to current value before
     *  wrapping.  This may be negative.
     * @param minValue the wrap range minimum value.
     * @param maxValue the wrap range maximum value.  This must be
     *  greater than minValue (checked by the method).
     * @return the wrapped value
     * @throws IllegalArgumentException if minValue is greater
     *  than or equal to maxValue
     */
    public static int getWrappedValue(int currentValue, int wrapValue,
                                      int minValue, int maxValue) {
        return getWrappedValue(currentValue + wrapValue, minValue, maxValue);
    }

    /**
     * Utility method that ensures the given value lies within the field's
     * legal value range.
     * 
     * @param value  the value to fit into the wrapped value range
     * @param minValue the wrap range minimum value.
     * @param maxValue the wrap range maximum value.  This must be
     *  greater than minValue (checked by the method).
     * @return the wrapped value
     * @throws IllegalArgumentException if minValue is greater
     *  than or equal to maxValue
     */
    public static int getWrappedValue(int value, int minValue, int maxValue) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[23]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((minValue >= maxValue) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[31]++;
            throw new IllegalArgumentException("MIN > MAX");

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[32]++;}
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[24]++;

        int wrapRange = maxValue - minValue + 1;
        value -= minValue;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[25]++;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[26]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[33]++;
            return (value % wrapRange) + minValue;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[34]++;}
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[27]++;

        int remByRange = (-value) % wrapRange;
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[28]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((remByRange == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[35]++;
            return 0 + minValue;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[36]++;}
        return (wrapRange - remByRange) + minValue;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares two objects as equals handling null.
     * 
     * @param object1  the first object
     * @param object2  the second object
     * @return true if equal
     * @since 1.4
     */
    public static boolean equals(Object object1, Object object2) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[29]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((object1 == object2) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[37]++;
            return true;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[38]++;}
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.statements[30]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((object1 == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((object2 == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d.branches[40]++;}
        return object1.equals(object2);
    }

}

class CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "org.joda.time.field.FieldUtils.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,2,2,1,1,1,1,2,2,2,2,1,1,1,1,2};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$l3pt8y4meaa4q025dw8qi5d () {
    super("org.joda.time.field.FieldUtils.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.FieldUtils.java");
      for (int i = 1; i <= 30; i++) {
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
    for (int i = 1; i <= 18; i++) {
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

