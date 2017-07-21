/*
 *  Copyright 2001-2006 Stephen Colebourne
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

/**
 * Exception thrown when attempting to set a field outside its supported range.
 *
 * @author Brian S O'Neill
 * @since 1.1
 */
public class IllegalFieldValueException extends IllegalArgumentException {
  static {
    CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.ping();
  }

    
    /** Serialization lock. */
    private static final long serialVersionUID = 6305711765985447737L;
  static {
    CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[1]++;
  }

    /**
     * Creates a message for the exception.
     *
     * @param fieldName  the field name
     * @param value  the value rejected
     * @param lowerBound  the lower bound allowed
     * @param upperBound  the uppe bound allowed
     * @param explain  an explanation
     * @return the message
     */
    private static String createMessage(String fieldName, Number value,
                                        Number lowerBound, Number upperBound, String explain) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[2]++;
        StringBuffer buf = new StringBuffer()
            .append("Value ").append(value).append(" for ").append(fieldName).append(' ');
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lowerBound == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[1]++;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((upperBound == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[3]++;
                buf.append("is not supported");
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[5]++;

            } else {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[4]++;
                buf.append("must not be larger than ").append(upperBound);
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[6]++;
            }

        } else {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[2]++;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[7]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((upperBound == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[5]++;
            buf.append("must not be smaller than ").append(lowerBound);
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[8]++;

        } else {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[6]++;
            buf.append("must be in the range [")
                .append(lowerBound)
                .append(',')
                .append(upperBound)
                .append(']');
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[9]++;
        }
}
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((explain != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[7]++;
            buf.append(": ").append(explain);
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[11]++;

        } else {
  CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[8]++;}

        return buf.toString();
    }

    /**
     * Creates a message for the exception.
     *
     * @param fieldName  the field name
     * @param value  the value rejected
     * @return the message
     */
    private static String createMessage(String fieldName, String value) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[12]++;
        StringBuffer buf = new StringBuffer().append("Value ");
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[9]++;
            buf.append("null");
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[14]++;

        } else {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[10]++;
            buf.append('"');
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[15]++;
            buf.append(value);
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[16]++;
            buf.append('"');
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[17]++;
        }

        buf.append(" for ").append(fieldName).append(' ').append("is not supported");
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[18]++;
        
        return buf.toString();
    }

    private final DateTimeFieldType iDateTimeFieldType;
    private final DurationFieldType iDurationFieldType;
    private final String iFieldName;
    private final Number iNumberValue;
    private final String iStringValue;
    private final Number iLowerBound;
    private final Number iUpperBound;
    private String iMessage;

    /**
     * Constructor.
     * 
     * @param fieldType  type of field being set
     * @param value  illegal value being set
     * @param lowerBound  lower legal field value, or null if not applicable
     * @param upperBound  upper legal field value, or null if not applicable
     */
    public IllegalFieldValueException(DateTimeFieldType fieldType,
                                      Number value, Number lowerBound, Number upperBound) {
        super(createMessage(fieldType.getName(), value, lowerBound, upperBound, null));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[19]++;
        iDateTimeFieldType = fieldType;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[20]++;
        iDurationFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[21]++;
        iFieldName = fieldType.getName();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[22]++;
        iNumberValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[23]++;
        iStringValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[24]++;
        iLowerBound = lowerBound;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[25]++;
        iUpperBound = upperBound;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[26]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[27]++;
    }

    /**
     * Constructor.
     * 
     * @param fieldType  type of field being set
     * @param value  illegal value being set
     * @param explain  an explanation
     * @since 1.5
     */
    public IllegalFieldValueException(DateTimeFieldType fieldType,
                                      Number value, String explain) {
        super(createMessage(fieldType.getName(), value, null, null, explain));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[28]++;
        iDateTimeFieldType = fieldType;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[29]++;
        iDurationFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[30]++;
        iFieldName = fieldType.getName();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[31]++;
        iNumberValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[32]++;
        iStringValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[33]++;
        iLowerBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[34]++;
        iUpperBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[35]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[36]++;
    }

    /**
     * Constructor.
     * 
     * @param fieldType  type of field being set
     * @param value  illegal value being set
     * @param lowerBound  lower legal field value, or null if not applicable
     * @param upperBound  upper legal field value, or null if not applicable
     */
    public IllegalFieldValueException(DurationFieldType fieldType,
                                      Number value, Number lowerBound, Number upperBound) {
        super(createMessage(fieldType.getName(), value, lowerBound, upperBound, null));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[37]++;
        iDateTimeFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[38]++;
        iDurationFieldType = fieldType;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[39]++;
        iFieldName = fieldType.getName();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[40]++;
        iNumberValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[41]++;
        iStringValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[42]++;
        iLowerBound = lowerBound;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[43]++;
        iUpperBound = upperBound;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[44]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[45]++;
    }

    /**
     * Constructor.
     * 
     * @param fieldName  name of field being set
     * @param value  illegal value being set
     * @param lowerBound  lower legal field value, or null if not applicable
     * @param upperBound  upper legal field value, or null if not applicable
     */
    public IllegalFieldValueException(String fieldName,
                                      Number value, Number lowerBound, Number upperBound) {
        super(createMessage(fieldName, value, lowerBound, upperBound, null));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[46]++;
        iDateTimeFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[47]++;
        iDurationFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[48]++;
        iFieldName = fieldName;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[49]++;
        iNumberValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[50]++;
        iStringValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[51]++;
        iLowerBound = lowerBound;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[52]++;
        iUpperBound = upperBound;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[53]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[54]++;
    }

    /**
     * Constructor.
     * 
     * @param fieldType  type of field being set
     * @param value  illegal value being set
     */
    public IllegalFieldValueException(DateTimeFieldType fieldType, String value) {
        super(createMessage(fieldType.getName(), value));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[55]++;
        iDateTimeFieldType = fieldType;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[56]++;
        iDurationFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[57]++;
        iFieldName = fieldType.getName();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[58]++;
        iStringValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[59]++;
        iNumberValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[60]++;
        iLowerBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[61]++;
        iUpperBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[62]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[63]++;
    }

    /**
     * Constructor.
     * 
     * @param fieldType  type of field being set
     * @param value  illegal value being set
     */
    public IllegalFieldValueException(DurationFieldType fieldType, String value) {
        super(createMessage(fieldType.getName(), value));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[64]++;
        iDateTimeFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[65]++;
        iDurationFieldType = fieldType;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[66]++;
        iFieldName = fieldType.getName();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[67]++;
        iStringValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[68]++;
        iNumberValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[69]++;
        iLowerBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[70]++;
        iUpperBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[71]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[72]++;
    }

    /**
     * Constructor.
     * 
     * @param fieldName  name of field being set
     * @param value  illegal value being set
     */
    public IllegalFieldValueException(String fieldName, String value) {
        super(createMessage(fieldName, value));
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[73]++;
        iDateTimeFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[74]++;
        iDurationFieldType = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[75]++;
        iFieldName = fieldName;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[76]++;
        iStringValue = value;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[77]++;
        iNumberValue = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[78]++;
        iLowerBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[79]++;
        iUpperBound = null;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[80]++;
        iMessage = super.getMessage();
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[81]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the DateTimeFieldType whose value was invalid, or null if not applicable.
     * 
     * @return the datetime field type
     */
    public DateTimeFieldType getDateTimeFieldType() {
        return iDateTimeFieldType;
    }

    /**
     * Returns the DurationFieldType whose value was invalid, or null if not applicable.
     * 
     * @return the duration field type
     */
    public DurationFieldType getDurationFieldType() {
        return iDurationFieldType;
    }

    /**
     * Returns the name of the field whose value was invalid.
     * 
     * @return the field name
     */
    public String getFieldName() {
        return iFieldName;
    }

    /**
     * Returns the illegal integer value assigned to the field, or null if not applicable.
     * 
     * @return the value
     */
    public Number getIllegalNumberValue() {
        return iNumberValue;
    }

    /**
     * Returns the illegal string value assigned to the field, or null if not applicable.
     * 
     * @return the value
     */
    public String getIllegalStringValue() {
        return iStringValue;
    }

    /**
     * Returns the illegal value assigned to the field as a non-null string.
     * 
     * @return the value
     */
    public String getIllegalValueAsString() {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[82]++;
        String value = iStringValue;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[83]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[11]++;
            value = String.valueOf(iNumberValue);
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[84]++;

        } else {
  CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[12]++;}
        return value;
    }

    /**
     * Returns the lower bound of the legal value range, or null if not applicable.
     * 
     * @return the lower bound
     */
    public Number getLowerBound() {
        return iLowerBound;
    }

    /**
     * Returns the upper bound of the legal value range, or null if not applicable.
     * 
     * @return the upper bound
     */
    public Number getUpperBound() {
        return iUpperBound;
    }

    public String getMessage() {
        return iMessage;
    }

    /**
     * Provide additional detail by prepending a message to the existing message.
     * A colon is separator is automatically inserted between the messages.
     * @since 1.3
     */
    public void prependMessage(String message) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[85]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((iMessage == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[13]++;
            iMessage = message;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[86]++;

        } else {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[14]++;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[87]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((message != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[15]++;
            iMessage = message + ": " + iMessage;
CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.statements[88]++;

        } else {
  CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh.branches[16]++;}
}
    }
}

class CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh ());
  }
    public static long[] statements = new long[89];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.joda.time.IllegalFieldValueException.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$99in88xtzeyiirsgya1ju54lk2jvf3c3kqc16fl0013y9ysh () {
    super("org.joda.time.IllegalFieldValueException.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 88; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.IllegalFieldValueException.java");
      for (int i = 1; i <= 88; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

