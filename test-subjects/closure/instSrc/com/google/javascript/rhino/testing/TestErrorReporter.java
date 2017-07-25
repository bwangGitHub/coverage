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

package com.google.javascript.rhino.testing;

import com.google.javascript.rhino.ErrorReporter;
import junit.framework.Assert;

/**
 * <p>An error reporter for testing that verifies that messages reported to the
 * reporter are expected.</p>
 *
 * <p>Sample use</p>
 * <pre>
 * TestErrorReporter e =
 *   new TestErrorReporter(null, new String[] { "first warning" });
 * ...
 * assertTrue(e.hasEncounteredAllWarnings());
 * </pre>
 *
 */
public final class TestErrorReporter extends Assert implements ErrorReporter {
  static {
    CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.ping();
  }

  private String[] errors;
  private String[] warnings;
  private int errorsIndex = 0;
  {
    CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[1]++;
  }
  private int warningsIndex = 0;
  {
    CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[2]++;
  }

  public TestErrorReporter(String[] errors, String[] warnings) {
    this.errors = errors;
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[3]++;
    this.warnings = warnings;
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[4]++;
  }

  public static TestErrorReporter forNoExpectedReports() {
    return new TestErrorReporter(null, null);
  }

  public void setErrors(String[] errors) {
    this.errors = errors;
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[5]++;
    errorsIndex = 0;
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[6]++;
  }

  public void setWarnings(String[] warnings) {
    this.warnings = warnings;
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[7]++;
    warningsIndex = 0;
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[8]++;
  }

  @Override
  public void error(String message, String sourceName, int line,
      int lineOffset) {
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((errors != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorsIndex < errors.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.branches[1]++;
      assertEquals(errors[errorsIndex++], message);
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[10]++;

    } else {
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.branches[2]++;
      fail("extra error: " + message);
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[11]++;
    }
  }

  @Override
  public void warning(String message, String sourceName, int line,
      int lineOffset) {
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((warnings != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((warningsIndex < warnings.length) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.branches[3]++;
      assertEquals(warnings[warningsIndex++], message);
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[13]++;

    } else {
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.branches[4]++;
      fail("extra warning: " + message);
CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x.statements[14]++;
    }
  }

  /**
   * Returns whether all warnings were reported to this reporter.
   */
  public boolean hasEncounteredAllWarnings() {
    return (warnings == null) ?
        warningsIndex == 0 :
        warnings.length == warningsIndex;
  }

  /**
   * Returns whether all errors were reported to this reporter.
   */
  public boolean hasEncounteredAllErrors() {
    return (errors == null) ?
        errorsIndex == 0 :
        errors.length == errorsIndex;
  }
}

class CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.testing.TestErrorReporter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2};
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

  public CodeCoverCoverageCounter$duipyt5lpuyqqgi0y7uk8m6kjlmqgek28x () {
    super("com.google.javascript.rhino.testing.TestErrorReporter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.testing.TestErrorReporter.java");
      for (int i = 1; i <= 14; i++) {
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

