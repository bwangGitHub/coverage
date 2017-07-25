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
 *   Nick Santos
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

import com.google.common.collect.Iterables;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.StaticScope;

import junit.framework.Assert;

import java.util.Iterator;

/**
 * Helper methods for making assertions about the validity of types.
 * @author nicksantos@google.com (Nick Santos)
 */
public class Asserts {
  static {
    CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.ping();
  }

  private Asserts() {} // all static

  public static JSType assertResolvesToSame(JSType type) {
    Assert.assertSame(type, assertValidResolve(type));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[1]++;
    return type;
  }

  /** @return The resolved type */
  public static JSType assertValidResolve(JSType type) {
    return assertValidResolve(type, MapBasedScope.emptyScope());
  }

  /** @return The resolved type */
  public static JSType assertValidResolve(
      JSType type, StaticScope<JSType> scope) {
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[2]++;
    ErrorReporter t = TestErrorReporter.forNoExpectedReports();
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[3]++;
    JSType resolvedType = type.resolve(t, scope);
    assertTypeEquals("JSType#resolve should not affect object equality",
        type, resolvedType);
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[4]++;
    return resolvedType;
  }

  public static void assertTypeNotEquals(JSType a, JSType b) {
    assertTypeNotEquals("", a, b);
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[5]++;
  }

  public static void assertTypeNotEquals(String message, JSType a, JSType b) {
    Assert.assertFalse(
        message +
        (message.isEmpty() ? "" : "\n") +
        "Type: " + b + "\n",
        a.isEquivalentTo(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[6]++;
    Assert.assertFalse(
        message +
        " Equals is not symmetric.\n" +
        "Type: " + b + "\n",
        b.isEquivalentTo(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[7]++;
  }

  public static void assertTypeEquals(JSType a, JSType b) {
    assertTypeEquals("", a, b);
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[8]++;
  }

  public static void assertTypeEquals(String message, JSType a, JSType b) {
    Assert.assertTrue(
        "Both types must be null, or both must be non-null " + a + "," + b,
        (a == null) == (b == null));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[9]++;
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((a == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.branches[2]++;}
    Assert.assertTrue(
        message +
        (message.isEmpty() ? "" : "\n") +
        "Expected: " + a + "\n" +
        "Actual  : " + b,
        a.isEquivalentTo(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[11]++;
    Assert.assertTrue(
        message +
        " Equals is not symmetric.\n" +
        "Expected: " + b + "\n" +
        "Actual  : " + a,
        b.isEquivalentTo(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[12]++;
  }

  public static <T extends JSType, S extends JSType> void
      assertTypeCollectionEquals(Iterable<T> a, Iterable<S> b) {
    Assert.assertEquals(Iterables.size(a), Iterables.size(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[13]++;
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[14]++;
    Iterator<T> aIterator = a.iterator();
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[15]++;
    Iterator<S> bIterator = b.iterator();
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
    while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((aIterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.loops[1]--;
  CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.loops[2]--;
  CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.loops[3]++;
}
      assertTypeEquals(aIterator.next(), bIterator.next());
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[17]++;
    }
  }

  /**
   * For the given equivalent types, run all type operations that
   * should have trivial solutions (getGreatestSubtype, isEquivalentTo, etc)
   */
  public static void assertEquivalenceOperations(JSType a, JSType b) {
    Assert.assertTrue(a.isEquivalentTo(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[18]++;
    Assert.assertTrue(a.isEquivalentTo(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[19]++;
    Assert.assertTrue(b.isEquivalentTo(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[20]++;
    Assert.assertTrue(b.isEquivalentTo(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[21]++;

    Assert.assertTrue(a.isSubtype(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[22]++;
    Assert.assertTrue(a.isSubtype(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[23]++;
    Assert.assertTrue(b.isSubtype(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[24]++;
    Assert.assertTrue(b.isSubtype(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[25]++;

    assertTypeEquals(a, a.getGreatestSubtype(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[26]++;
    assertTypeEquals(a, a.getGreatestSubtype(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[27]++;
    assertTypeEquals(a, b.getGreatestSubtype(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[28]++;
    assertTypeEquals(a, b.getGreatestSubtype(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[29]++;

    assertTypeEquals(a, a.getLeastSupertype(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[30]++;
    assertTypeEquals(a, a.getLeastSupertype(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[31]++;
    assertTypeEquals(a, b.getLeastSupertype(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[32]++;
    assertTypeEquals(a, b.getLeastSupertype(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[33]++;

    Assert.assertTrue(a.canCastTo(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[34]++;
    Assert.assertTrue(a.canCastTo(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[35]++;
    Assert.assertTrue(b.canCastTo(b));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[36]++;
    Assert.assertTrue(b.canCastTo(a));
CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh.statements[37]++;
  }
}

class CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh ());
  }
    public static long[] statements = new long[38];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.testing.Asserts.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$1yp5wfijkx26db6r5gh () {
    super("com.google.javascript.rhino.testing.Asserts.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 37; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.testing.Asserts.java");
      for (int i = 1; i <= 37; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

