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

/**
 * Converts a lenient DateTimeField into a strict one. By being strict, the set
 * throws an IllegalArgumentException if the value is out of bounds.
 * <p>
 * StrictDateTimeField is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @see org.joda.time.chrono.StrictChronology
 * @see LenientDateTimeField
 * @since 1.0
 */
public class StrictDateTimeField extends DelegatedDateTimeField {
  static {
    CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.ping();
  }


    private static final long serialVersionUID = 3154803964207950910L;
  static {
    CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[1]++;
  }

    /**
     * Returns a strict version of the given field. If it is already strict,
     * then it is returned as-is. Otherwise, a new StrictDateTimeField is
     * returned.
     */
    public static DateTimeField getInstance(DateTimeField field) {
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((field == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.branches[1]++;
            return null;

        } else {
  CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.branches[2]++;}
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((field instanceof LenientDateTimeField) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.branches[3]++;
            field = ((LenientDateTimeField)field).getWrappedField();
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[4]++;

        } else {
  CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.branches[4]++;}
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((field.isLenient()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.branches[5]++;
            return field;

        } else {
  CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.branches[6]++;}
        return new StrictDateTimeField(field);
    }

    protected StrictDateTimeField(DateTimeField field) {
        super(field);
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[6]++;
    }

    public final boolean isLenient() {
        return false;
    }

    /**
     * Does a bounds check before setting the value.
     *
     * @throws IllegalArgumentException if the value is invalid
     */
    public long set(long instant, int value) {
        FieldUtils.verifyValueBounds
            (this, value, getMinimumValue(instant), getMaximumValue(instant));
CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd.statements[7]++;
        return super.set(instant, value);
    }
}

class CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.joda.time.field.StrictDateTimeField.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$j8fwr583affl73b2fczdnzpbgsup0i0w0bvxd () {
    super("org.joda.time.field.StrictDateTimeField.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.field.StrictDateTimeField.java");
      for (int i = 1; i <= 7; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

