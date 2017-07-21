/*
 *  Copyright 2001-2009 Stephen Colebourne
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.joda.time.base.AbstractPartial;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Partial is an immutable partial datetime supporting any set of datetime fields.
 * <p>
 * A Partial instance can be used to hold any combination of fields.
 * The instance does not contain a time zone, so any datetime is local.
 * <p>
 * A Partial can be matched against an instant using {@link #isMatch(ReadableInstant)}.
 * This method compares each field on this partial with those of the instant
 * and determines if the partial matches the instant.
 * Given this definition, an empty Partial instance represents any datetime
 * and always matches.
 * <p>
 * Calculations on Partial are performed using a {@link Chronology}.
 * This chronology is set to be in the UTC time zone for all calculations.
 * <p>
 * Each individual field can be queried in two ways:
 * <ul>
 * <li><code>get(DateTimeFieldType.monthOfYear())</code>
 * <li><code>property(DateTimeFieldType.monthOfYear()).get()</code>
 * </ul>
 * The second technique also provides access to other useful methods on the
 * field:
 * <ul>
 * <li>numeric value - <code>monthOfYear().get()</code>
 * <li>text value - <code>monthOfYear().getAsText()</code>
 * <li>short text value - <code>monthOfYear().getAsShortText()</code>
 * <li>maximum/minimum values - <code>monthOfYear().getMaximumValue()</code>
 * <li>add/subtract - <code>monthOfYear().addToCopy()</code>
 * <li>set - <code>monthOfYear().setCopy()</code>
 * </ul>
 * <p>
 * Partial is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @since 1.1
 */
public final class Partial
        extends AbstractPartial
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 12324121189002L;
  static {
    CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[1]++;
  }

    /** The chronology in use. */
    private final Chronology iChronology;
    /** The set of field types. */
    private final DateTimeFieldType[] iTypes;
    /** The values of each field in this partial. */
    private final int[] iValues;
    /** The formatter to use, [0] may miss some fields, [1] doesn't miss any fields. */
    private transient DateTimeFormatter[] iFormatter;

    // Constructors
    //-----------------------------------------------------------------------
    /**
     * Constructs a Partial with no fields or values, which can be considered
     * to represent any date.
     * <p>
     * This is most useful when constructing partials, for example:
     * <pre>
     * Partial p = new Partial()
     *     .with(DateTimeFieldType.dayOfWeek(), 5)
     *     .with(DateTimeFieldType.hourOfDay(), 12)
     *     .with(DateTimeFieldType.minuteOfHour(), 20);
     * </pre>
     * Note that, although this is a clean way to write code, it is fairly
     * inefficient internally.
     * <p>
     * The constructor uses the default ISO chronology.
     */
    public Partial() {
        this((Chronology) null);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[2]++;
    }

    /**
     * Constructs a Partial with no fields or values, which can be considered
     * to represent any date.
     * <p>
     * This is most useful when constructing partials, for example:
     * <pre>
     * Partial p = new Partial(chrono)
     *     .with(DateTimeFieldType.dayOfWeek(), 5)
     *     .with(DateTimeFieldType.hourOfDay(), 12)
     *     .with(DateTimeFieldType.minuteOfHour(), 20);
     * </pre>
     * Note that, although this is a clean way to write code, it is fairly
     * inefficient internally.
     *
     * @param chrono  the chronology, null means ISO
     */
    public Partial(Chronology chrono) {
        super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[3]++;
        iChronology = DateTimeUtils.getChronology(chrono).withUTC();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[4]++;
        iTypes = new DateTimeFieldType[0];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[5]++;
        iValues = new int[0];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[6]++;
    }

    /**
     * Constructs a Partial with the specified field and value.
     * <p>
     * The constructor uses the default ISO chronology.
     * 
     * @param type  the single type to create the partial from, not null
     * @param value  the value to store
     * @throws IllegalArgumentException if the type or value is invalid
     */
    public Partial(DateTimeFieldType type, int value) {
        this(type, value, null);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[7]++;
    }

    /**
     * Constructs a Partial with the specified field and value.
     * <p>
     * The constructor uses the specified chronology.
     * 
     * @param type  the single type to create the partial from, not null
     * @param value  the value to store
     * @param chronology  the chronology, null means ISO
     * @throws IllegalArgumentException if the type or value is invalid
     */
    public Partial(DateTimeFieldType type, int value, Chronology chronology) {
        super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[8]++;
        chronology = DateTimeUtils.getChronology(chronology).withUTC();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[9]++;
        iChronology = chronology;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[10]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[1]++;
            throw new IllegalArgumentException("The field type must not be null");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[2]++;}
        iTypes = new DateTimeFieldType[] {type};
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[12]++;
        iValues = new int[] {value};
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[13]++;
        chronology.validate(this, iValues);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[14]++;
    }

    /**
     * Constructs a Partial with the specified fields and values.
     * The fields must be specified in the order largest to smallest.
     * <p>
     * The constructor uses the specified chronology.
     * 
     * @param types  the types to create the partial from, not null
     * @param values  the values to store, not null
     * @throws IllegalArgumentException if the types or values are invalid
     */
    public Partial(DateTimeFieldType[] types, int[] values) {
        this(types, values, null);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[15]++;
    }

    /**
     * Constructs a Partial with the specified fields and values.
     * The fields must be specified in the order largest to smallest.
     * <p>
     * The constructor uses the specified chronology.
     * 
     * @param types  the types to create the partial from, not null
     * @param values  the values to store, not null
     * @param chronology  the chronology, null means ISO
     * @throws IllegalArgumentException if the types or values are invalid
     */
    public Partial(DateTimeFieldType[] types, int[] values, Chronology chronology) {
        super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[16]++;
        chronology = DateTimeUtils.getChronology(chronology).withUTC();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[17]++;
        iChronology = chronology;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[18]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((types == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[3]++;
            throw new IllegalArgumentException("Types array must not be null");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[4]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[5]++;
            throw new IllegalArgumentException("Values array must not be null");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[6]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((values.length != types.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[7]++;
            throw new IllegalArgumentException("Values array must be the same length as the types array");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[8]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((types.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[9]++;
            iTypes = types;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[23]++;
            iValues = values;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[24]++;
            return;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[10]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[25]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < types.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[1]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[2]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[3]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((types[i] == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[11]++;
                throw new IllegalArgumentException("Types array must not contain null: index " + i);

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[12]++;}
        }
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[27]++;
        DurationField lastUnitField = null;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[28]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < types.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[4]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[5]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[6]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[29]++;
            DateTimeFieldType loopType = types[i];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[30]++;
            DurationField loopUnitField = loopType.getDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[13]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[32]++;
                int compare = lastUnitField.compareTo(loopUnitField);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((compare < 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((compare != 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((loopUnitField.isSupported() == false) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[15]++;
                    throw new IllegalArgumentException("Types array must be in order largest-smallest: " +
                            types[i - 1].getName() + " < " + loopType.getName());

                } else {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[16]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[34]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((compare == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[17]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((types[i - 1].getRangeDurationType() == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[19]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
                        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((loopType.getRangeDurationType() == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[21]++;
                            throw new IllegalArgumentException("Types array must not contain duplicate: " + loopType.getName());

                        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[22]++;}

                    } else {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[20]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[37]++;
int CodeCoverConditionCoverageHelper_C14;
                        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((loopType.getRangeDurationType() == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[23]++;
                            throw new IllegalArgumentException("Types array must be in order largest-smallest: " +
                                    types[i - 1].getName() + " < " + loopType.getName());

                        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[24]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[38]++;
                        DurationField lastRangeField = types[i - 1].getRangeDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[39]++;
                        DurationField loopRangeField = loopType.getRangeDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((lastRangeField.compareTo(loopRangeField) < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[25]++;
                            throw new IllegalArgumentException("Types array must be in order largest-smallest: " +
                                    types[i - 1].getName() + " < " + loopType.getName());

                        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[26]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[41]++;
int CodeCoverConditionCoverageHelper_C16;
                        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((lastRangeField.compareTo(loopRangeField) == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[27]++;
                            throw new IllegalArgumentException("Types array must not contain duplicate: " + loopType.getName());

                        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[28]++;}
                    }

                } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[18]++;}
}

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[14]++;}
            lastUnitField = loopUnitField;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[42]++;
        }
        
        iTypes = (DateTimeFieldType[]) types.clone();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[43]++;
        chronology.validate(this, values);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[44]++;
        iValues = (int[]) values.clone();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[45]++;
    }

    /**
     * Constructs a Partial by copying all the fields and types from
     * another partial.
     * <p>
     * This is most useful when copying from a YearMonthDay or TimeOfDay.
     */
    public Partial(ReadablePartial partial) {
        super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[46]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[47]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[29]++;
            throw new IllegalArgumentException("The partial must not be null");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[30]++;}
        iChronology = DateTimeUtils.getChronology(partial.getChronology()).withUTC();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[48]++;
        iTypes = new DateTimeFieldType[partial.size()];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[49]++;
        iValues = new int[partial.size()];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[50]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[51]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < partial.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[7]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[8]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[9]++;
}
            iTypes[i] = partial.getFieldType(i);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[52]++;
            iValues[i] = partial.getValue(i);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[53]++;
        }
    }

    /**
     * Constructs a Partial with the specified values.
     * This constructor assigns and performs no validation.
     * 
     * @param partial  the partial to copy
     * @param values  the values to store
     * @throws IllegalArgumentException if the types or values are invalid
     */
    Partial(Partial partial, int[] values) {
        super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[54]++;
        iChronology = partial.iChronology;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[55]++;
        iTypes = partial.iTypes;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[56]++;
        iValues = values;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[57]++;
    }

    /**
     * Constructs a Partial with the specified chronology, fields and values.
     * This constructor assigns and performs no validation.
     * 
     * @param chronology  the chronology
     * @param types  the types to create the partial from
     * @param values  the values to store
     * @throws IllegalArgumentException if the types or values are invalid
     */
    Partial(Chronology chronology, DateTimeFieldType[] types, int[] values) {
        super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[58]++;
        iChronology = chronology;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[59]++;
        iTypes = types;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[60]++;
        iValues = values;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[61]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the number of fields in this partial.
     * 
     * @return the field count
     */
    public int size() {
        return iTypes.length;
    }

    /**
     * Gets the chronology of the partial which is never null.
     * <p>
     * The {@link Chronology} is the calculation engine behind the partial and
     * provides conversion and validation of the fields in a particular calendar system.
     * 
     * @return the chronology, never null
     */
    public Chronology getChronology() {
        return iChronology;
    }

    /**
     * Gets the field for a specific index in the chronology specified.
     * 
     * @param index  the index to retrieve
     * @param chrono  the chronology to use
     * @return the field
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    protected DateTimeField getField(int index, Chronology chrono) {
        return iTypes[index].getField(chrono);
    }

    /**
     * Gets the field type at the specified index.
     *
     * @param index  the index to retrieve
     * @return the field at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public DateTimeFieldType getFieldType(int index) {
        return iTypes[index];
    }

    /**
     * Gets an array of the field type of each of the fields that
     * this partial supports.
     * <p>
     * The fields are returned largest to smallest.
     *
     * @return the array of field types (cloned), largest to smallest
     */
    public DateTimeFieldType[] getFieldTypes() {
        return (DateTimeFieldType[]) iTypes.clone();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the value of the field at the specifed index.
     * 
     * @param index  the index
     * @return the value
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public int getValue(int index) {
        return iValues[index];
    }

    /**
     * Gets an array of the value of each of the fields that
     * this partial supports.
     * <p>
     * The fields are returned largest to smallest.
     * Each value corresponds to the same array index as <code>getFieldTypes()</code>
     *
     * @return the current values of each field (cloned), largest to smallest
     */
    public int[] getValues() {
        return (int[]) iValues.clone();
    }

    //-----------------------------------------------------------------------
    /**
     * Creates a new Partial instance with the specified chronology.
     * This instance is immutable and unaffected by this method call.
     * <p>
     * This method retains the values of the fields, thus the result will
     * typically refer to a different instant.
     * <p>
     * The time zone of the specified chronology is ignored, as Partial
     * operates without a time zone.
     *
     * @param newChronology  the new chronology, null means ISO
     * @return a copy of this datetime with a different chronology
     * @throws IllegalArgumentException if the values are invalid for the new chronology
     */
    public Partial withChronologyRetainFields(Chronology newChronology) {
        newChronology = DateTimeUtils.getChronology(newChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[62]++;
        newChronology = newChronology.withUTC();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[63]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[64]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((newChronology == getChronology()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[31]++;
            return this;

        } else {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[32]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[65]++;
            Partial newPartial = new Partial(newChronology, iTypes, iValues);
            newChronology.validate(newPartial, iValues);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[66]++;
            return newPartial;
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a copy of this date with the specified field set to a new value.
     * <p>
     * If this partial did not previously support the field, the new one will.
     * Contrast this behaviour with {@link #withField(DateTimeFieldType, int)}.
     * <p>
     * For example, if the field type is <code>dayOfMonth</code> then the day
     * would be changed/added in the returned instance.
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this instance with the field set
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public Partial with(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[67]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((fieldType == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[33]++;
            throw new IllegalArgumentException("The field type must not be null");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[34]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[68]++;
        int index = indexOf(fieldType);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[69]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[35]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[70]++;
            DateTimeFieldType[] newTypes = new DateTimeFieldType[iTypes.length + 1];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[71]++;
            int[] newValues = new int[newTypes.length];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[72]++;
            
            // find correct insertion point to keep largest-smallest order
            int i = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[73]++;
            DurationField unitField = fieldType.getDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[74]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((unitField.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[37]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[75]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
                for (;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < iTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[10]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[11]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[12]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[76]++;
                    DateTimeFieldType loopType = iTypes[i];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[77]++;
                    DurationField loopUnitField = loopType.getDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[78]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((loopUnitField.isSupported()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[39]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[79]++;
                        int compare = unitField.compareTo(loopUnitField);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[80]++;
int CodeCoverConditionCoverageHelper_C25;
                        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((compare > 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[41]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[81]++;
                            break;

                        } else {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[42]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[82]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((compare == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[43]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[83]++;
                            DurationField rangeField = fieldType.getRangeDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[84]++;
                            DurationField loopRangeField = loopType.getRangeDurationType().getField(iChronology);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[85]++;
int CodeCoverConditionCoverageHelper_C27;
                            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((rangeField.compareTo(loopRangeField) > 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[45]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[86]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[46]++;}

                        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[44]++;}
}

                    } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[40]++;}
                }

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[38]++;}
            System.arraycopy(iTypes, 0, newTypes, 0, i);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[87]++;
            System.arraycopy(iValues, 0, newValues, 0, i);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[88]++;
            newTypes[i] = fieldType;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[89]++;
            newValues[i] = value;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[90]++;
            System.arraycopy(iTypes, i, newTypes, i + 1, newTypes.length - i - 1);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[91]++;
            System.arraycopy(iValues, i, newValues, i + 1, newValues.length - i - 1);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[92]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[93]++;
            
            Partial newPartial = new Partial(iChronology, newTypes, newValues);
            iChronology.validate(newPartial, newValues);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[94]++;
            return newPartial;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[36]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[95]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((value == getValue(index)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[47]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[48]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[96]++;
        int[] newValues = getValues();
        newValues = getField(index).set(this, index, newValues, value);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[97]++;
        return new Partial(this, newValues);
    }

    /**
     * Gets a copy of this date with the specified field removed.
     * <p>
     * If this partial did not previously support the field, no error occurs.
     *
     * @param fieldType  the field type to remove, may be null
     * @return a copy of this instance with the field removed
     */
    public Partial without(DateTimeFieldType fieldType) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[98]++;
        int index = indexOf(fieldType);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[99]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((index != -1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[49]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[100]++;
            DateTimeFieldType[] newTypes = new DateTimeFieldType[size() - 1];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[101]++;
            int[] newValues = new int[size() - 1];
            System.arraycopy(iTypes, 0, newTypes, 0, index);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[102]++;
            System.arraycopy(iTypes, index + 1, newTypes, index, newTypes.length - index);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[103]++;
            System.arraycopy(iValues, 0, newValues, 0, index);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[104]++;
            System.arraycopy(iValues, index + 1, newValues, index, newValues.length - index);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[105]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[106]++;
            Partial newPartial = new Partial(iChronology, newTypes, newValues);
            iChronology.validate(newPartial, newValues);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[107]++;
            return newPartial;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[50]++;}
        return this;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a copy of this Partial with the specified field set to a new value.
     * <p>
     * If this partial does not support the field, an exception is thrown.
     * Contrast this behaviour with {@link #with(DateTimeFieldType, int)}.
     * <p>
     * For example, if the field type is <code>dayOfMonth</code> then the day
     * would be changed in the returned instance if supported.
     *
     * @param fieldType  the field type to set, not null
     * @param value  the value to set
     * @return a copy of this instance with the field set
     * @throws IllegalArgumentException if the value is null or invalid
     */
    public Partial withField(DateTimeFieldType fieldType, int value) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[108]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[109]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((value == getValue(index)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[51]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[52]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[110]++;
        int[] newValues = getValues();
        newValues = getField(index).set(this, index, newValues, value);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[111]++;
        return new Partial(this, newValues);
    }

    /**
     * Gets a copy of this Partial with the value of the specified field increased.
     * If this partial does not support the field, an exception is thrown.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * The addition will overflow into larger fields (eg. minute to hour).
     * However, it will not wrap around if the top maximum is reached.
     *
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this instance with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new datetime exceeds the capacity
     */
    public Partial withFieldAdded(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[112]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[113]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[53]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[54]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[114]++;
        int[] newValues = getValues();
        newValues = getField(index).add(this, index, newValues, amount);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[115]++;
        return new Partial(this, newValues);
    }

    /**
     * Gets a copy of this Partial with the value of the specified field increased.
     * If this partial does not support the field, an exception is thrown.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * The addition will overflow into larger fields (eg. minute to hour).
     * If the maximum is reached, the addition will wra.
     *
     * @param fieldType  the field type to add to, not null
     * @param amount  the amount to add
     * @return a copy of this instance with the field updated
     * @throws IllegalArgumentException if the value is null or invalid
     * @throws ArithmeticException if the new datetime exceeds the capacity
     */
    public Partial withFieldAddWrapped(DurationFieldType fieldType, int amount) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[116]++;
        int index = indexOfSupported(fieldType);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[117]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((amount == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[55]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[56]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[118]++;
        int[] newValues = getValues();
        newValues = getField(index).addWrapPartial(this, index, newValues, amount);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[119]++;
        return new Partial(this, newValues);
    }

    /**
     * Gets a copy of this Partial with the specified period added.
     * <p>
     * If the addition is zero, then <code>this</code> is returned.
     * Fields in the period that aren't present in the partial are ignored.
     * <p>
     * This method is typically used to add multiple copies of complex
     * period instances. Adding one field is best achieved using the method
     * {@link #withFieldAdded(DurationFieldType, int)}.
     * 
     * @param period  the period to add to this one, null means zero
     * @param scalar  the amount of times to add, such as -1 to subtract once
     * @return a copy of this instance with the period added
     * @throws ArithmeticException if the new datetime exceeds the capacity
     */
    public Partial withPeriodAdded(ReadablePeriod period, int scalar) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[120]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((period == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((scalar == 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[57]++;
            return this;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[58]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[121]++;
        int[] newValues = getValues();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[122]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[13]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i < period.size()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[13]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[14]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[15]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[123]++;
            DurationFieldType fieldType = period.getFieldType(i);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[124]++;
            int index = indexOf(fieldType);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[125]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[59]++;
                newValues = getField(index).add(this, index, newValues,
                        FieldUtils.safeMultiply(period.getValue(i), scalar));
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[126]++;

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[60]++;}
        }
        return new Partial(this, newValues);
    }

    /**
     * Gets a copy of this instance with the specified period added.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     *
     * @param period  the duration to add to this one, null means zero
     * @return a copy of this instance with the period added
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public Partial plus(ReadablePeriod period) {
        return withPeriodAdded(period, 1);
    }

    /**
     * Gets a copy of this instance with the specified period take away.
     * <p>
     * If the amount is zero or null, then <code>this</code> is returned.
     *
     * @param period  the period to reduce this instant by
     * @return a copy of this instance with the period taken away
     * @throws ArithmeticException if the new datetime exceeds the capacity of a long
     */
    public Partial minus(ReadablePeriod period) {
        return withPeriodAdded(period, -1);
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the property object for the specified type, which contains
     * many useful methods for getting and manipulating the partial.
     * <p>
     * See also {@link ReadablePartial#get(DateTimeFieldType)}.
     *
     * @param type  the field type to get the property for, not null
     * @return the property object
     * @throws IllegalArgumentException if the field is null or unsupported
     */
    public Property property(DateTimeFieldType type) {
        return new Property(this, indexOfSupported(type));
    }

    //-----------------------------------------------------------------------
    /**
     * Does this partial match the specified instant.
     * <p>
     * A match occurs when all the fields of this partial are the same as the
     * corresponding fields on the specified instant.
     *
     * @param instant  an instant to check against, null means now in default zone
     * @return true if this partial matches the specified instant
     */
    public boolean isMatch(ReadableInstant instant) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[127]++;
        long millis = DateTimeUtils.getInstantMillis(instant);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[128]++;
        Chronology chrono = DateTimeUtils.getInstantChronology(instant);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[129]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[16]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < iTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[16]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[17]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[18]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[130]++;
            int value = iTypes[i].getField(chrono).get(millis);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[131]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((value != iValues[i]) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[61]++;
                return false;

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[62]++;}
        }
        return true;
    }

    /**
     * Does this partial match the specified partial.
     * <p>
     * A match occurs when all the fields of this partial are the same as the
     * corresponding fields on the specified partial.
     *
     * @param partial  a partial to check against, must not be null
     * @return true if this partial matches the specified partial
     * @throws IllegalArgumentException if the partial is null
     * @throws IllegalArgumentException if the fields of the two partials do not match
     * @since 1.5
     */
    public boolean isMatch(ReadablePartial partial) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[132]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[63]++;
            throw new IllegalArgumentException("The partial must not be null");

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[64]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[133]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[19]++;


int CodeCoverConditionCoverageHelper_C39;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i < iTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[19]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[20]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[21]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[134]++;
            int value = partial.get(iTypes[i]);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[135]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((value != iValues[i]) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[65]++;
                return false;

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[66]++;}
        }
        return true;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a formatter suitable for the fields in this partial.
     * <p>
     * If there is no appropriate ISO format, null is returned.
     * This method may return a formatter that does not display all the
     * fields of the partial. This might occur when you have overlapping
     * fields, such as dayOfWeek and dayOfMonth.
     *
     * @return a formatter suitable for the fields in this partial, null
     *  if none is suitable
     */
    public DateTimeFormatter getFormatter() {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[136]++;
        DateTimeFormatter[] f = iFormatter;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[137]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[67]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[138]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[69]++;
                return null;

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[70]++;}
            f = new DateTimeFormatter[2];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[139]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[140]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[141]++;
                List<DateTimeFieldType> list = new ArrayList<DateTimeFieldType>(Arrays.asList(iTypes));
                f[0] = ISODateTimeFormat.forFields(list, true, false);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[142]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[143]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((list.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[72]++;
                    f[1] = f[0];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[144]++;

                } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[73]++;}
            } catch (IllegalArgumentException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[74]++;
                // ignore
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[71]++;
}
  }
            iFormatter = f;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[145]++;

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[68]++;}
        return f[0];
    }

    //-----------------------------------------------------------------------
    /**
     * Output the date in an appropriate ISO8601 format.
     * <p>
     * This method will output the partial in one of two ways.
     * If {@link #getFormatter()}
     * <p>
     * If there is no appropriate ISO format a dump of the fields is output
     * via {@link #toStringList()}.
     * 
     * @return ISO8601 formatted string
     */
    public String toString() {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[146]++;
        DateTimeFormatter[] f = iFormatter;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[147]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[75]++;
            getFormatter();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[148]++;
            f = iFormatter;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[149]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[150]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[77]++;
                return toStringList();

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[78]++;}

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[76]++;}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[151]++;
        DateTimeFormatter f1 = f[1];
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[152]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((f1 == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[79]++;
            return toStringList();

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[80]++;}
        return f1.print(this);
    }

    /**
     * Gets a string version of the partial that lists all the fields.
     * <p>
     * This method exists to provide a better debugging toString than
     * the standard toString. This method lists all the fields and their
     * values in a style similar to the collections framework.
     *
     * @return a toString format that lists all the fields
     */
    public String toStringList() {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[153]++;
        int size = size();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[154]++;
        StringBuffer buf = new StringBuffer(20 * size);
        buf.append('[');
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[155]++;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[156]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[22]++;


int CodeCoverConditionCoverageHelper_C47;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[22]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[23]--;
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.loops[24]++;
}
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[157]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[81]++;
                buf.append(',').append(' ');
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[158]++;

            } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[82]++;}
            buf.append(iTypes[i].getName());
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[159]++;
            buf.append('=');
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[160]++;
            buf.append(iValues[i]);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[161]++;
        }
        buf.append(']');
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[162]++;
        return buf.toString();
    }

    /**
     * Output the date using the specified format pattern.
     * Unsupported fields will appear as special unicode characters.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[163]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[83]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[84]++;}
        return DateTimeFormat.forPattern(pattern).print(this);
    }

    /**
     * Output the date using the specified format pattern.
     * Unsupported fields will appear as special unicode characters.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @param locale  Locale to use, null means default
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern, Locale locale) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[164]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[85]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.branches[86]++;}
        return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
    }

    //-----------------------------------------------------------------------
    /**
     * The property class for <code>Partial</code>.
     * <p>
     * This class binds a <code>Partial</code> to a <code>DateTimeField</code>.
     * 
     * @author Stephen Colebourne
     * @since 1.1
     */
    public static class Property extends AbstractPartialFieldProperty implements Serializable {

        /** Serialization version */
        private static final long serialVersionUID = 53278362873888L;

        /** The partial */
        private final Partial iPartial;
        /** The field index */
        private final int iFieldIndex;

        /**
         * Constructs a property.
         * 
         * @param partial  the partial instance
         * @param fieldIndex  the index in the partial
         */
        Property(Partial partial, int fieldIndex) {
            super();
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[165]++;
            iPartial = partial;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[166]++;
            iFieldIndex = fieldIndex;
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[167]++;
        }

        /**
         * Gets the field that this property uses.
         * 
         * @return the field
         */
        public DateTimeField getField() {
            return iPartial.getField(iFieldIndex);
        }

        /**
         * Gets the partial that this property belongs to.
         * 
         * @return the partial
         */
        protected ReadablePartial getReadablePartial() {
            return iPartial;
        }

        /**
         * Gets the partial that this property belongs to.
         * 
         * @return the partial
         */
        public Partial getPartial() {
            return iPartial;
        }

        /**
         * Gets the value of this field.
         * 
         * @return the field value
         */
        public int get() {
            return iPartial.getValue(iFieldIndex);
        }

        //-----------------------------------------------------------------------
        /**
         * Adds to the value of this field in a copy of this Partial.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it will affect larger fields.
         * Smaller fields are unaffected.
         * <p>
         * If the result would be too large, beyond the maximum year, then an
         * IllegalArgumentException is thrown.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the Partial with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public Partial addToCopy(int valueToAdd) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[168]++;
            int[] newValues = iPartial.getValues();
            newValues = getField().add(iPartial, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[169]++;
            return new Partial(iPartial, newValues);
        }

        /**
         * Adds to the value of this field in a copy of this Partial wrapping
         * within this field if the maximum value is reached.
         * <p>
         * The value will be added to this field. If the value is too large to be
         * added solely to this field then it wraps within this field.
         * Other fields are unaffected.
         * <p>
         * For example,
         * <code>2004-12-20</code> addWrapField one month returns <code>2004-01-20</code>.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param valueToAdd  the value to add to the field in the copy
         * @return a copy of the Partial with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public Partial addWrapFieldToCopy(int valueToAdd) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[170]++;
            int[] newValues = iPartial.getValues();
            newValues = getField().addWrapField(iPartial, iFieldIndex, newValues, valueToAdd);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[171]++;
            return new Partial(iPartial, newValues);
        }

        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the Partial.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the Partial with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public Partial setCopy(int value) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[172]++;
            int[] newValues = iPartial.getValues();
            newValues = getField().set(iPartial, iFieldIndex, newValues, value);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[173]++;
            return new Partial(iPartial, newValues);
        }

        /**
         * Sets this field in a copy of the Partial to a parsed text value.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the Partial with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public Partial setCopy(String text, Locale locale) {
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[174]++;
            int[] newValues = iPartial.getValues();
            newValues = getField().set(iPartial, iFieldIndex, newValues, text, locale);
CodeCoverCoverageCounter$2etq718dvawyhzx3tr5.statements[175]++;
            return new Partial(iPartial, newValues);
        }

        /**
         * Sets this field in a copy of the Partial to a parsed text value.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         * Instead, a new instance is returned.
         * 
         * @param text  the text value to set
         * @return a copy of the Partial with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public Partial setCopy(String text) {
            return setCopy(text, null);
        }

        //-----------------------------------------------------------------------
        /**
         * Returns a new Partial with this field set to the maximum value
         * for this field.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         *
         * @return a copy of the Partial with this field set to its maximum
         * @since 1.2
         */
        public Partial withMaximumValue() {
            return setCopy(getMaximumValue());
        }

        /**
         * Returns a new Partial with this field set to the minimum value
         * for this field.
         * <p>
         * The Partial attached to this property is unchanged by this call.
         *
         * @return a copy of the Partial with this field set to its minimum
         * @since 1.2
         */
        public Partial withMinimumValue() {
            return setCopy(getMinimumValue());
        }
    }

}

class CodeCoverCoverageCounter$2etq718dvawyhzx3tr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2etq718dvawyhzx3tr5 ());
  }
    public static long[] statements = new long[176];
    public static long[] branches = new long[87];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[51];
  static {
    final String SECTION_NAME = "org.joda.time.Partial.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 50; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$2etq718dvawyhzx3tr5 () {
    super("org.joda.time.Partial.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 175; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 86; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 50; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.Partial.java");
      for (int i = 1; i <= 175; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 86; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 50; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

