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
package org.joda.time.field;

import java.util.Locale;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

/**
 * AbstractPartialFieldProperty is a base class for binding a
 * ReadablePartial to a DateTimeField.
 * <p>
 * It allows the date and time manipulation code to be field based yet
 * still easy to use.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class AbstractPartialFieldProperty {
  static {
    CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.ping();
  }


    /**
     * Constructor.
     */
    protected AbstractPartialFieldProperty() {
        super();
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[1]++;
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
     * Gets the partial instant being used.
     * 
     * @return the partial instant
     */
    protected abstract ReadablePartial getReadablePartial();

    //-----------------------------------------------------------------------
    /**
     * Gets the value of this property from the instant.
     * <p>
     * For example, the following two lines of code are equivalent:
     * <pre>
     * partial.getDayOfMonth();
     * partial.dayOfMonth().get();
     * </pre>
     * 
     * @return the current value
     */
    public abstract int get();

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
        return getField().getAsText(getReadablePartial(), get(), locale);
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
        return getField().getAsShortText(getReadablePartial(), get(), locale);
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
     * Gets the minimum value for this field given the current field values.
     * 
     * @return the minimum value
     * @see DateTimeField#getMinimumValue
     */
    public int getMinimumValue() {
        return getField().getMinimumValue(getReadablePartial());
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
     * Gets the maximum value for this field given the current field values.
     * 
     * @return the maximum value
     * @see DateTimeField#getMaximumValue
     */
    public int getMaximumValue() {
        return getField().getMaximumValue(getReadablePartial());
    }

    //-----------------------------------------------------------------------
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
     * @throws IllegalArgumentException if the instant is null or the instant
     *  doesn't support the field of this property
     */
    public int compareTo(ReadableInstant instant) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((instant == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[1]++;
            throw new IllegalArgumentException("The instant must not be null");

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[2]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[3]++;
        int thisValue = get();
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[4]++;
        int otherValue = instant.get(getFieldType());
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisValue < otherValue) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[3]++;
            return -1;

        } else {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[4]++;
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[6]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((thisValue > otherValue) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[5]++;
            return 1;

        } else {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[6]++;
            return 0;
        }
}
    }

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
     * @throws IllegalArgumentException if the instant is null
     * @throws IllegalArgumentException if the field of this property cannot be queried
     *  on the specified instant
     */
    public int compareTo(ReadablePartial partial) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((partial == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[7]++;
            throw new IllegalArgumentException("The instant must not be null");

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[8]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[8]++;
        int thisValue = get();
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[9]++;
        int otherValue = partial.get(getFieldType());
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisValue < otherValue) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[9]++;
            return -1;

        } else {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[10]++;
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[11]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((thisValue > otherValue) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[11]++;
            return 1;

        } else {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[12]++;
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
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[12]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this == object) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[13]++;
            return true;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[14]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[13]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((object instanceof AbstractPartialFieldProperty == false) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.branches[16]++;}
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[14]++;
        AbstractPartialFieldProperty other = (AbstractPartialFieldProperty) object;
        return
            get() == other.get() &&
            getFieldType() == other.getFieldType() &&
            FieldUtils.equals(getReadablePartial().getChronology(), other.getReadablePartial().getChronology());
    }

    //-----------------------------------------------------------------------
    /**
     * Gets a suitable hashcode for the object.
     * 
     * @return the hashcode
     * @since 1.3
     */
    public int hashCode() {
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[15]++;
        int hash = 19;
        hash = 13 * hash + get();
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[16]++;
        hash = 13 * hash + getFieldType().hashCode();
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[17]++;
        hash = 13 * hash + getReadablePartial().getChronology().hashCode();
CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9.statements[18]++;
        return hash;
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

class CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.joda.time.field.AbstractPartialFieldProperty.java";
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

  public CodeCoverCoverageCounter$bl6rgcoxr7ya00y1w7tby0sjr5ap35f58tia52ayvnp9taslly9 () {
    super("org.joda.time.field.AbstractPartialFieldProperty.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.joda.time.field.AbstractPartialFieldProperty.java");
      for (int i = 1; i <= 18; i++) {
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

