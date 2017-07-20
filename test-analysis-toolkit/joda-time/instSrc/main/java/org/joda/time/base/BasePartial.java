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

import java.io.Serializable;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadablePartial;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PartialConverter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * BasePartial is an abstract implementation of ReadablePartial that stores
 * data in array and <code>Chronology</code> fields.
 * <p>
 * This class should generally not be used directly by API users.
 * The {@link org.joda.time.ReadablePartial} interface should be used when different 
 * kinds of partial objects are to be referenced.
 * <p>
 * BasePartial subclasses may be mutable and not thread-safe.
 *
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class BasePartial
        extends AbstractPartial
        implements ReadablePartial, Serializable {
  static {
    CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.ping();
  }


    /** Serialization version */
    private static final long serialVersionUID = 2353678632973660L;
  static {
    CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[1]++;
  }

    /** The chronology in use */
    private final Chronology iChronology;
    /** The values of each field in this partial */
    private final int[] iValues;

    //-----------------------------------------------------------------------
    /**
     * Constructs a partial with the current time, using ISOChronology in
     * the default zone to extract the fields.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     */
    protected BasePartial() {
        this(DateTimeUtils.currentTimeMillis(), null);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[2]++;
    }

    /**
     * Constructs a partial with the current time, using the specified chronology
     * and zone to extract the fields.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    protected BasePartial(Chronology chronology) {
        this(DateTimeUtils.currentTimeMillis(), chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[3]++;
    }

    /**
     * Constructs a partial extracting the partial fields from the specified
     * milliseconds using the ISOChronology in the default zone.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    protected BasePartial(long instant) {
        this(instant, null);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[4]++;
    }

    /**
     * Constructs a partial extracting the partial fields from the specified
     * milliseconds using the chronology provided.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    protected BasePartial(long instant, Chronology chronology) {
        super();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[5]++;
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[6]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[7]++;
        iValues = chronology.get(this, instant);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[8]++;
    }

    /**
     * Constructs a partial from an Object that represents a time, using the
     * specified chronology.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param instant  the datetime object
     * @param chronology  the chronology, null means use converter
     * @throws IllegalArgumentException if the date is invalid
     */
    protected BasePartial(Object instant, Chronology chronology) {
        super();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[9]++;
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[10]++;
        PartialConverter converter = ConverterManager.getInstance().getPartialConverter(instant);
        chronology = converter.getChronology(instant, chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[11]++;
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[12]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[13]++;
        iValues = converter.getPartialValues(this, instant, chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[14]++;
    }

    /**
     * Constructs a partial from an Object that represents a time, using the
     * specified chronology.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param instant  the datetime object
     * @param chronology  the chronology, null means use converter
     * @param parser  if converting from a String, the given parser is preferred
     * @throws IllegalArgumentException if the date is invalid
     * @since 1.3
     */
    protected BasePartial(Object instant, Chronology chronology, DateTimeFormatter parser) {
        super();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[15]++;
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[16]++;
        PartialConverter converter = ConverterManager.getInstance().getPartialConverter(instant);
        chronology = converter.getChronology(instant, chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[17]++;
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[18]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[19]++;
        iValues = converter.getPartialValues(this, instant, chronology, parser);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[20]++;
    }

    /**
     * Constructs a partial with specified time field values and chronology.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     * <p>
     * The array of values is assigned (not cloned) to the new instance.
     *
     * @param values  the new set of values
     * @param chronology  the chronology, null means ISOChronology in the default zone
     * @throws IllegalArgumentException if the values are invalid
     */
    protected BasePartial(int[] values, Chronology chronology) {
        super();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[21]++;
        chronology = DateTimeUtils.getChronology(chronology);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[22]++;
        iChronology = chronology.withUTC();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[23]++;
        chronology.validate(this, values);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[24]++;
        iValues = values;
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[25]++;
    }

    /**
     * Private constructor to be used by subclasses only which performs no validation.
     * <p>
     * Data is assigned (not cloned) to the new instance.
     *
     * @param base  the base partial
     * @param values  the new set of values, not cloned, null means use base
     */
    protected BasePartial(BasePartial base, int[] values) {
        super();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[26]++;
        iChronology = base.iChronology;
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[27]++;
        iValues = values;
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[28]++;
    }

    /**
     * Private constructor to be used by subclasses only which performs no validation.
     * <p>
     * Data is assigned (not cloned) to the new instance.
     * This should not be used by mutable subclasses.
     *
     * @param base  the base partial
     * @param chrono  the chronology to use, null means use base
     */
    protected BasePartial(BasePartial base, Chronology chrono) {
        super();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[29]++;
        iChronology = chrono.withUTC();
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[30]++;
        iValues = base.iValues;
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[31]++;
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
     * Gets an array of the value of each of the fields that this partial supports.
     * <p>
     * The fields are returned largest to smallest, for example Hour, Minute, Second.
     * Each value corresponds to the same array index as <code>getFields()</code>
     *
     * @return the current values of each field (cloned), largest to smallest
     */
    public int[] getValues() {
        return (int[]) iValues.clone();
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

    //-----------------------------------------------------------------------
    /**
     * Sets the value of the field at the specified index.
     * <p>
     * In version 2.0 and later, this method copies the array into the original.
     * This is because the instance variable has been changed to be final to satisfy the Java Memory Model.
     * This only impacts subclasses that are mutable.
     * 
     * @param index  the index
     * @param value  the value to set
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    protected void setValue(int index, int value) {
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[32]++;
        DateTimeField field = getField(index);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[33]++;
        int[] values = field.set(this, index, iValues, value);
        System.arraycopy(values, 0, iValues, 0, iValues.length);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[34]++;
    }

    /**
     * Sets the values of all fields.
     * <p>
     * In version 2.0 and later, this method copies the array into the original.
     * This is because the instance variable has been changed to be final to satisfy the Java Memory Model.
     * This only impacts subclasses that are mutable.
     * 
     * @param values  the array of values
     */
    protected void setValues(int[] values) {
        getChronology().validate(this, values);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[35]++;
        System.arraycopy(values, 0, iValues, 0, iValues.length);
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[36]++;
    }

    //-----------------------------------------------------------------------
    /**
     * Output the date using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern) {
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[37]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.branches[1]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.branches[2]++;}
        return DateTimeFormat.forPattern(pattern).print(this);
    }

    /**
     * Output the date using the specified format pattern.
     *
     * @param pattern  the pattern specification, null means use <code>toString</code>
     * @param locale  Locale to use, null means default
     * @see org.joda.time.format.DateTimeFormat
     */
    public String toString(String pattern, Locale locale) throws IllegalArgumentException {
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.statements[38]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((pattern == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.branches[3]++;
            return toString();

        } else {
  CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx.branches[4]++;}
        return DateTimeFormat.forPattern(pattern).withLocale(locale).print(this);
    }

}

class CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx ());
  }
    public static long[] statements = new long[39];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.joda.time.base.BasePartial.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
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

  public CodeCoverCoverageCounter$3xh58fzrezbxv2xcavkkgopcx () {
    super("org.joda.time.base.BasePartial.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 38; i++) {
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
    log.startNamedSection("org.joda.time.base.BasePartial.java");
      for (int i = 1; i <= 38; i++) {
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

