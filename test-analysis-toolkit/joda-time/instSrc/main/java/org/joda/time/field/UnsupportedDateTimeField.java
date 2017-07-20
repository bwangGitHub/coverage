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
package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

/**
 * A placeholder implementation to use when a datetime field is not supported.
 * <p>
 * UnsupportedDateTimeField is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class UnsupportedDateTimeField extends DateTimeField implements Serializable {
  static {
    CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.ping();
  }


    /** Serialilzation version */
    private static final long serialVersionUID = -1934618396111902255L;
  static {
    CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[1]++;
  }

    /** The cache of unsupported datetime field instances */
    private static HashMap<DateTimeFieldType, UnsupportedDateTimeField> cCache;

    /**
     * Gets an instance of UnsupportedDateTimeField for a specific named field.
     * Names should be of standard format, such as 'monthOfYear' or 'hourOfDay'.
     * The returned instance is cached.
     * 
     * @param type  the type to obtain
     * @return the instance
     * @throws IllegalArgumentException if durationField is null
     */
    public static synchronized UnsupportedDateTimeField getInstance(
            DateTimeFieldType type, DurationField durationField) {

        UnsupportedDateTimeField field;
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cCache == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[1]++;
            cCache = new HashMap<DateTimeFieldType, UnsupportedDateTimeField>(7);
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[3]++;
            field = null;
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[4]++;

        } else {
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[2]++;
            field = cCache.get(type);
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[5]++;
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((field != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((field.getDurationField() != durationField) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[3]++;
                field = null;
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[7]++;

            } else {
  CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[4]++;}
        }
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[5]++;
            field = new UnsupportedDateTimeField(type, durationField);
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[9]++;
            cCache.put(type, field);
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[10]++;

        } else {
  CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[6]++;}
        return field;
    }

    /** The field type */
    private final DateTimeFieldType iType;
    /** The duration of the datetime field */
    private final DurationField iDurationField;

    /**
     * Constructor.
     * 
     * @param type  the field type
     * @param durationField  the duration to use
     */
    private UnsupportedDateTimeField(DateTimeFieldType type, DurationField durationField) {
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((durationField == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[7]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.branches[8]++;}
        iType = type;
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[12]++;
        iDurationField = durationField;
CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl.statements[13]++;
    }

    //-----------------------------------------------------------------------
    // Design note: Simple accessors return a suitable value, but methods
    // intended to perform calculations throw an UnsupportedOperationException.

    public DateTimeFieldType getType() {
        return iType;
    }

    public String getName() {
        return iType.getName();
    }

    /**
     * This field is not supported.
     *
     * @return false always
     */
    public boolean isSupported() {
        return false;
    }

    /**
     * This field is not lenient.
     *
     * @return false always
     */
    public boolean isLenient() {
        return false;
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int get(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsText(long instant, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsText(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsText(ReadablePartial partial, int fieldValue, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsText(ReadablePartial partial, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsText(int fieldValue, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsShortText(long instant, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsShortText(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsShortText(ReadablePartial partial, int fieldValue, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsShortText(ReadablePartial partial, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public String getAsShortText(int fieldValue, Locale locale) {
        throw unsupported();
    }

    /**
     * Delegates to the duration field.
     *
     * @throws UnsupportedOperationException if the duration is unsupported
     */
    public long add(long instant, int value) {
        return getDurationField().add(instant, value);
    }

    /**
     * Delegates to the duration field.
     *
     * @throws UnsupportedOperationException if the duration is unsupported
     */
    public long add(long instant, long value) {
        return getDurationField().add(instant, value);
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int[] add(ReadablePartial instant, int fieldIndex, int[] values, int valueToAdd) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int[] addWrapPartial(ReadablePartial instant, int fieldIndex, int[] values, int valueToAdd) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long addWrapField(long instant, int value) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int[] addWrapField(ReadablePartial instant, int fieldIndex, int[] values, int valueToAdd) {
        throw unsupported();
    }

    /**
     * Delegates to the duration field.
     *
     * @throws UnsupportedOperationException if the duration is unsupported
     */
    public int getDifference(long minuendInstant, long subtrahendInstant) {
        return getDurationField().getDifference(minuendInstant, subtrahendInstant);
    }

    /**
     * Delegates to the duration field.
     *
     * @throws UnsupportedOperationException if the duration is unsupported
     */
    public long getDifferenceAsLong(long minuendInstant, long subtrahendInstant) {
        return getDurationField().getDifferenceAsLong(minuendInstant, subtrahendInstant);
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long set(long instant, int value) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int[] set(ReadablePartial instant, int fieldIndex, int[] values, int newValue) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long set(long instant, String text, Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long set(long instant, String text) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int[] set(ReadablePartial instant, int fieldIndex, int[] values, String text, Locale locale) {
        throw unsupported();
    }

    /**
     * Even though this DateTimeField is unsupported, the duration field might
     * be supported.
     *
     * @return a possibly supported DurationField
     */
    public DurationField getDurationField() {
        return iDurationField;
    }

    /**
     * Always returns null.
     *
     * @return null always
     */
    public DurationField getRangeDurationField() {
        return null;
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public boolean isLeap(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getLeapAmount(long instant) {
        throw unsupported();
    }

    /**
     * Always returns null.
     *
     * @return null always
     */
    public DurationField getLeapDurationField() {
        return null;
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMinimumValue() {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMinimumValue(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMinimumValue(ReadablePartial instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMinimumValue(ReadablePartial instant, int[] values) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMaximumValue() {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMaximumValue(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMaximumValue(ReadablePartial instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMaximumValue(ReadablePartial instant, int[] values) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMaximumTextLength(Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public int getMaximumShortTextLength(Locale locale) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long roundFloor(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long roundCeiling(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long roundHalfFloor(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long roundHalfCeiling(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long roundHalfEven(long instant) {
        throw unsupported();
    }

    /**
     * Always throws UnsupportedOperationException
     *
     * @throws UnsupportedOperationException
     */
    public long remainder(long instant) {
        throw unsupported();
    }

    //------------------------------------------------------------------------
    /**
     * Get a suitable debug string.
     * 
     * @return debug string
     */
    public String toString() {
        return "UnsupportedDateTimeField";
    }

    /**
     * Ensure proper singleton serialization
     */
    private Object readResolve() {
        return getInstance(iType, iDurationField);
    }

    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException(iType + " field is unsupported");
    }

}

class CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.joda.time.field.UnsupportedDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,2};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$7o9m8szbgmmxm3v5smbkdjt0o78h11xcsaho5c57hxobl () {
    super("org.joda.time.field.UnsupportedDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.UnsupportedDateTimeField.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

