/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Bob Jervis
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino.jstype;

import static com.google.javascript.rhino.jstype.TernaryValue.FALSE;
import static com.google.javascript.rhino.jstype.TernaryValue.UNKNOWN;


/**
 * Boolean type.
 */
public class BooleanType extends ValueType {
  static {
    CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.statements[1]++;
  }

  BooleanType(JSTypeRegistry registry) {
    super(registry);
CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.statements[2]++;
  }

  @Override
  public boolean isNullable() {
    return false;
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.statements[3]++;
    TernaryValue result = super.testForEquality(that);
CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.branches[1]++;
      return result;

    } else {
  CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.branches[2]++;}
CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((that.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((that.isSubtype(
            getNativeType(JSTypeNative.NUMBER_STRING_BOOLEAN))) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((that.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) || true)) || (CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) && false)) {
CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.branches[3]++;
      return UNKNOWN;

    } else {
  CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh.branches[4]++;}
    return FALSE;
  }

  @Override
  public boolean isBooleanValueType() {
    return true;
  }

  @Override
  public boolean matchesNumberContext() {
    return true;
  }

  @Override
  public boolean matchesStringContext() {
    return true;
  }

  @Override
  public boolean matchesObjectContext() {
    // TODO(user): Revisit this for ES4, which is stricter.
    return true;
  }

  @Override
  public JSType autoboxesTo() {
    return getNativeType(JSTypeNative.BOOLEAN_OBJECT_TYPE);
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return getDisplayName();
  }

  @Override
  public String getDisplayName() {
    return "boolean";
  }

  @Override
  public BooleanLiteralSet getPossibleToBooleanOutcomes() {
    return BooleanLiteralSet.BOTH;
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseBooleanType();
  }
}

class CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.BooleanType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3};
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

  public CodeCoverCoverageCounter$3xlc4e9fodxn3pfqd3ou5ywkh () {
    super("com.google.javascript.rhino.jstype.BooleanType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.BooleanType.java");
      for (int i = 1; i <= 5; i++) {
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

