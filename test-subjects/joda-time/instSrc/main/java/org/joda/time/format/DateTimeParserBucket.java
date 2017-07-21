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
package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;

/**
 * DateTimeParserBucket is an advanced class, intended mainly for parser
 * implementations. It can also be used during normal parsing operations to
 * capture more information about the parse.
 * <p>
 * This class allows fields to be saved in any order, but be physically set in
 * a consistent order. This is useful for parsing against formats that allow
 * field values to contradict each other.
 * <p>
 * Field values are applied in an order where the "larger" fields are set
 * first, making their value less likely to stick.  A field is larger than
 * another when it's range duration is longer. If both ranges are the same,
 * then the larger field has the longer duration. If it cannot be determined
 * which field is larger, then the fields are set in the order they were saved.
 * <p>
 * For example, these fields were saved in this order: dayOfWeek, monthOfYear,
 * dayOfMonth, dayOfYear. When computeMillis is called, the fields are set in
 * this order: monthOfYear, dayOfYear, dayOfMonth, dayOfWeek.
 * <p>
 * DateTimeParserBucket is mutable and not thread-safe.
 *
 * @author Brian S O'Neill
 * @author Fredrik Borgh
 * @since 1.0
 */
public class DateTimeParserBucket {
  static {
    CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.ping();
  }


    /** The chronology to use for parsing. */
    private final Chronology iChrono;
    private final long iMillis;
    
    /** The parsed zone, initialised to formatter zone. */
    private DateTimeZone iZone;
    /** The parsed offset. */
    private Integer iOffset;
    /** The locale to use for parsing. */
    private Locale iLocale;
    /** Used for parsing two-digit years. */
    private Integer iPivotYear;
    /** Used for parsing month/day without year. */
    private int iDefaultYear;

    private SavedField[] iSavedFields = new SavedField[8];
  {
    CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[1]++;
  }
    private int iSavedFieldsCount;
    private boolean iSavedFieldsShared;
    
    private Object iSavedState;

    /**
     * Constructs a bucket.
     * 
     * @param instantLocal  the initial millis from 1970-01-01T00:00:00, local time
     * @param chrono  the chronology to use
     * @param locale  the locale to use
     * @deprecated Use longer constructor
     */
    @Deprecated
    public DateTimeParserBucket(long instantLocal, Chronology chrono, Locale locale) {
        this(instantLocal, chrono, locale, null, 2000);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[2]++;
    }

    /**
     * Constructs a bucket, with the option of specifying the pivot year for
     * two-digit year parsing.
     *
     * @param instantLocal  the initial millis from 1970-01-01T00:00:00, local time
     * @param chrono  the chronology to use
     * @param locale  the locale to use
     * @param pivotYear  the pivot year to use when parsing two-digit years
     * @since 1.1
     * @deprecated Use longer constructor
     */
    @Deprecated
    public DateTimeParserBucket(long instantLocal, Chronology chrono, Locale locale, Integer pivotYear) {
        this(instantLocal, chrono, locale, pivotYear, 2000);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[3]++;
    }

    /**
     * Constructs a bucket, with the option of specifying the pivot year for
     * two-digit year parsing.
     *
     * @param instantLocal  the initial millis from 1970-01-01T00:00:00, local time
     * @param chrono  the chronology to use
     * @param locale  the locale to use
     * @param pivotYear  the pivot year to use when parsing two-digit years
     * @since 2.0
     */
    public DateTimeParserBucket(long instantLocal, Chronology chrono,
            Locale locale, Integer pivotYear, int defaultYear) {
        super();
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[4]++;
        chrono = DateTimeUtils.getChronology(chrono);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[5]++;
        iMillis = instantLocal;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[6]++;
        iZone = chrono.getZone();
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[7]++;
        iChrono = chrono.withUTC();
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[8]++;
        iLocale = (locale == null ? Locale.getDefault() : locale);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[9]++;
        iPivotYear = pivotYear;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[10]++;
        iDefaultYear = defaultYear;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[11]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the chronology of the bucket, which will be a local (UTC) chronology.
     */
    public Chronology getChronology() {
        return iChrono;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the locale to be used during parsing.
     * 
     * @return the locale to use
     */
    public Locale getLocale() {
        return iLocale;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the time zone used by computeMillis.
     */
    public DateTimeZone getZone() {
        return iZone;
    }

    /**
     * Set a time zone to be used when computeMillis is called.
     */
    public void setZone(DateTimeZone zone) {
        iSavedState = null;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[12]++;
        iZone = zone;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[13]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the time zone offset in milliseconds used by computeMillis.
     * @deprecated use Integer version
     */
    @Deprecated
    public int getOffset() {
        return (iOffset != null ? iOffset : 0);
    }

    /**
     * Returns the time zone offset in milliseconds used by computeMillis.
     */
    public Integer getOffsetInteger() {
        return iOffset;
    }

    /**
     * Set a time zone offset to be used when computeMillis is called.
     * @deprecated use Integer version
     */
    @Deprecated
    public void setOffset(int offset) {
        iSavedState = null;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[14]++;
        iOffset = offset;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[15]++;
    }

    /**
     * Set a time zone offset to be used when computeMillis is called.
     */
    public void setOffset(Integer offset) {
        iSavedState = null;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[16]++;
        iOffset = offset;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[17]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the default year used when information is incomplete.
     * <p>
     * This is used for two-digit years and when the largest parsed field is
     * months or days.
     * <p>
     * A null value for two-digit years means to use the value from DateTimeFormatterBuilder.
     * A null value for month/day only parsing will cause the default of 2000 to be used.
     *
     * @return Integer value of the pivot year, null if not set
     * @since 1.1
     */
    public Integer getPivotYear() {
        return iPivotYear;
    }

    /**
     * Sets the pivot year to use when parsing two digit years.
     * <p>
     * If the value is set to null, this will indicate that default
     * behaviour should be used.
     *
     * @param pivotYear  the pivot year to use
     * @since 1.1
     */
    public void setPivotYear(Integer pivotYear) {
        iPivotYear = pivotYear;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[18]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Saves a datetime field value.
     * 
     * @param field  the field, whose chronology must match that of this bucket
     * @param value  the value
     */
    public void saveField(DateTimeField field, int value) {
        saveField(new SavedField(field, value));
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[19]++;
    }
    
    /**
     * Saves a datetime field value.
     * 
     * @param fieldType  the field type
     * @param value  the value
     */
    public void saveField(DateTimeFieldType fieldType, int value) {
        saveField(new SavedField(fieldType.getField(iChrono), value));
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[20]++;
    }
    
    /**
     * Saves a datetime field text value.
     * 
     * @param fieldType  the field type
     * @param text  the text value
     * @param locale  the locale to use
     */
    public void saveField(DateTimeFieldType fieldType, String text, Locale locale) {
        saveField(new SavedField(fieldType.getField(iChrono), text, locale));
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[21]++;
    }
    
    private void saveField(SavedField field) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[22]++;
        SavedField[] savedFields = iSavedFields;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[23]++;
        int savedFieldsCount = iSavedFieldsCount;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[24]++;
int CodeCoverConditionCoverageHelper_C1;
        
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((savedFieldsCount == savedFields.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((iSavedFieldsShared) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[1]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[25]++;
            // Expand capacity or merely copy if saved fields are shared.
            SavedField[] newArray = new SavedField
                [savedFieldsCount == savedFields.length ? savedFieldsCount * 2 : savedFields.length];
            System.arraycopy(savedFields, 0, newArray, 0, savedFieldsCount);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[26]++;
            iSavedFields = savedFields = newArray;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[27]++;
            iSavedFieldsShared = false;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[28]++;

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[2]++;}
        
        iSavedState = null;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[29]++;
        savedFields[savedFieldsCount] = field;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[30]++;
        iSavedFieldsCount = savedFieldsCount + 1;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[31]++;
    }
    
    /**
     * Saves the state of this bucket, returning it in an opaque object. Call
     * restoreState to undo any changes that were made since the state was
     * saved. Calls to saveState may be nested.
     *
     * @return opaque saved state, which may be passed to restoreState
     */
    public Object saveState() {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[32]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iSavedState == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[3]++;
            iSavedState = new SavedState();
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[33]++;

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[4]++;}
        return iSavedState;
    }
    
    /**
     * Restores the state of this bucket from a previously saved state. The
     * state object passed into this method is not consumed, and it can be used
     * later to restore to that state again.
     *
     * @param savedState opaque saved state, returned from saveState
     * @return true state object is valid and state restored
     */
    public boolean restoreState(Object savedState) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[34]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((savedState instanceof SavedState) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[5]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[35]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((((SavedState) savedState).restoreState(this)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[7]++;
                iSavedState = savedState;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[36]++;
                return true;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[6]++;}
        return false;
    }
    
    /**
     * Computes the parsed datetime by setting the saved fields.
     * This method is idempotent, but it is not thread-safe.
     *
     * @return milliseconds since 1970-01-01T00:00:00Z
     * @throws IllegalArgumentException if any field is out of range
     */
    public long computeMillis() {
        return computeMillis(false, null);
    }
    
    /**
     * Computes the parsed datetime by setting the saved fields.
     * This method is idempotent, but it is not thread-safe.
     *
     * @param resetFields false by default, but when true, unsaved field values are cleared
     * @return milliseconds since 1970-01-01T00:00:00Z
     * @throws IllegalArgumentException if any field is out of range
     */
    public long computeMillis(boolean resetFields) {
        return computeMillis(resetFields, null);
    }

    /**
     * Computes the parsed datetime by setting the saved fields.
     * This method is idempotent, but it is not thread-safe.
     *
     * @param resetFields false by default, but when true, unsaved field values are cleared
     * @param text optional text being parsed, to be included in any error message
     * @return milliseconds since 1970-01-01T00:00:00Z
     * @throws IllegalArgumentException if any field is out of range
     * @since 1.3
     */
    public long computeMillis(boolean resetFields, String text) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[37]++;
        SavedField[] savedFields = iSavedFields;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[38]++;
        int count = iSavedFieldsCount;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[39]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iSavedFieldsShared) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[9]++;
            iSavedFields = savedFields = (SavedField[])iSavedFields.clone();
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[40]++;
            iSavedFieldsShared = false;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[41]++;

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[10]++;}
        sort(savedFields, count);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[42]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[43]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((count > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[11]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[44]++;
            // alter base year for parsing if first field is month or day
            DurationField months = DurationFieldType.months().getField(iChrono);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[45]++;
            DurationField days = DurationFieldType.days().getField(iChrono);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[46]++;
            DurationField first = savedFields[0].iField.getDurationField();
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((compareReverse(first, months) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((compareReverse(first, days) <= 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[13]++;
                saveField(DateTimeFieldType.year(), iDefaultYear);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[48]++;
                return computeMillis(resetFields, text);

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[12]++;}
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[49]++;

        long millis = iMillis;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[50]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[51]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[1]++;


int CodeCoverConditionCoverageHelper_C8;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[1]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[2]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[3]++;
}
                millis = savedFields[i].set(millis, resetFields);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[52]++;
            }
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[53]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((resetFields) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[16]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[54]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[4]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[5]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[6]++;
}
                    millis = savedFields[i].set(millis, i == (count - 1));
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[55]++;
                }

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[17]++;}
        } catch (IllegalFieldValueException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[18]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((text != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[19]++;
                e.prependMessage("Cannot parse \"" + text + '"');
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[57]++;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[20]++;}
            throw e;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[15]++;
}
  }
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[58]++;
int CodeCoverConditionCoverageHelper_C12;
        
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((iOffset != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[21]++;
            millis -= iOffset;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[59]++;

        } else {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[22]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[60]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iZone != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[23]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[61]++;
            int offset = iZone.getOffsetFromLocal(millis);
            millis -= offset;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[62]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((offset != iZone.getOffset(millis)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[25]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[64]++;
                String message =
                    "Illegal instant due to time zone offset transition (" + iZone + ')';
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[65]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((text != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[27]++;
                    message = "Cannot parse \"" + text + "\": " + message;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[66]++;

                } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[28]++;}
                throw new IllegalArgumentException(message);

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[24]++;}
}
        
        return millis;
    }
    
    /**
     * Sorts elements [0,high). Calling java.util.Arrays isn't always the right
     * choice since it always creates an internal copy of the array, even if it
     * doesn't need to. If the array slice is small enough, an insertion sort
     * is chosen instead, but it doesn't need a copy!
     * <p>
     * This method has a modified version of that insertion sort, except it
     * doesn't create an unnecessary array copy. If high is over 10, then
     * java.util.Arrays is called, which will perform a merge sort, which is
     * faster than insertion sort on large lists.
     * <p>
     * The end result is much greater performance when computeMillis is called.
     * Since the amount of saved fields is small, the insertion sort is a
     * better choice. Additional performance is gained since there is no extra
     * array allocation and copying. Also, the insertion sort here does not
     * perform any casting operations. The version in java.util.Arrays performs
     * casts within the insertion sort loop.
     */
    private static void sort(SavedField[] array, int high) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((high > 10) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[29]++;
            Arrays.sort(array, 0, high);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[68]++;

        } else {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[30]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[69]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[7]++;


int CodeCoverConditionCoverageHelper_C17;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i<high) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[7]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[8]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[9]++;
}
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[70]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[10]++;


int CodeCoverConditionCoverageHelper_C18;
                for (int j=i;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((j>0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 (((array[j-1]).compareTo(array[j])>0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false); j--) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[10]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[11]--;
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.loops[12]++;
}
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[71]++;
                    SavedField t = array[j];
                    array[j] = array[j-1];
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[72]++;
                    array[j-1] = t;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[73]++;
                }
            }
        }
    }

    class SavedState {
        final DateTimeZone iZone;
        final Integer iOffset;
        final SavedField[] iSavedFields;
        final int iSavedFieldsCount;
        
        SavedState() {
            this.iZone = DateTimeParserBucket.this.iZone;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[74]++;
            this.iOffset = DateTimeParserBucket.this.iOffset;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[75]++;
            this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[76]++;
            this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[77]++;
        }
        
        boolean restoreState(DateTimeParserBucket enclosing) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[78]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((enclosing != DateTimeParserBucket.this) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[31]++;
                return false;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[32]++;}
            enclosing.iZone = this.iZone;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[79]++;
            enclosing.iOffset = this.iOffset;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[80]++;
            enclosing.iSavedFields = this.iSavedFields;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[81]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[82]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.iSavedFieldsCount < enclosing.iSavedFieldsCount) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[33]++;
                // Since count is being restored to a lower count, the
                // potential exists for new saved fields to destroy data being
                // shared by another state. Set this flag such that the array
                // of saved fields is cloned prior to modification.
                enclosing.iSavedFieldsShared = true;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[83]++;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[34]++;}
            enclosing.iSavedFieldsCount = this.iSavedFieldsCount;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[84]++;
            return true;
        }
    }
    
    static class SavedField implements Comparable<SavedField> {
        final DateTimeField iField;
        final int iValue;
        final String iText;
        final Locale iLocale;
        
        SavedField(DateTimeField field, int value) {
            iField = field;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[85]++;
            iValue = value;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[86]++;
            iText = null;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[87]++;
            iLocale = null;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[88]++;
        }
        
        SavedField(DateTimeField field, String text, Locale locale) {
            iField = field;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[89]++;
            iValue = 0;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[90]++;
            iText = text;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[91]++;
            iLocale = locale;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[92]++;
        }
        
        long set(long millis, boolean reset) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[93]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((iText == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[35]++;
                millis = iField.set(millis, iValue);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[94]++;

            } else {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[36]++;
                millis = iField.set(millis, iText, iLocale);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[95]++;
            }
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[96]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((reset) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[37]++;
                millis = iField.roundFloor(millis);
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[97]++;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[38]++;}
            return millis;
        }
        
        /**
         * The field with the longer range duration is ordered first, where
         * null is considered infinite. If the ranges match, then the field
         * with the longer duration is ordered first.
         */
        public int compareTo(SavedField obj) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[98]++;
            DateTimeField other = obj.iField;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[99]++;
            int result = compareReverse
                (iField.getRangeDurationField(), other.getRangeDurationField());
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[100]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((result != 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[39]++;
                return result;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[40]++;}
            return compareReverse
                (iField.getDurationField(), other.getDurationField());
        }
    }

    static int compareReverse(DurationField a, DurationField b) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[101]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((a == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((a.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[41]++;
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[102]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((b.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[43]++;
                return 0;

            } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[44]++;}
            return -1;

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[42]++;}
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.statements[103]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((b.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[45]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox.branches[46]++;}
        return -a.compareTo(b);
    }
}

class CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox ());
  }
    public static long[] statements = new long[104];
    public static long[] branches = new long[47];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "org.joda.time.format.DateTimeParserBucket.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,2,2};
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
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$342mapki23be134nozovs31heglnxr1nsdrymox () {
    super("org.joda.time.format.DateTimeParserBucket.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 103; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 46; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.DateTimeParserBucket.java");
      for (int i = 1; i <= 103; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 46; i++) {
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
      for (int i = 1; i <= 4; i++) {
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

