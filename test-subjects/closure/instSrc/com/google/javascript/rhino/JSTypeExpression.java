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

package com.google.javascript.rhino;

import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.StaticScope;

import java.io.Serializable;


/**
 * Represents a type expression as a miniature Rhino AST, so that the
 * type expression can be evaluated later.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public final class JSTypeExpression implements Serializable {
  static {
    CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.statements[1]++;
  }

  /** The root of the AST. */
  private final Node root;

  /** The source name where the type expression appears. */
  private final String sourceName;

  public JSTypeExpression(Node root, String sourceName) {
    this.root = root;
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.statements[2]++;
    this.sourceName = sourceName;
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.statements[3]++;
  }

  /**
   * Make the given type expression into an optional type expression,
   * if possible.
   */
  public static JSTypeExpression makeOptionalArg(JSTypeExpression expr) {
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((expr.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((expr.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.branches[1]++;
      return expr;

    } else {
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.branches[2]++;
      return new JSTypeExpression(
          new Node(Token.EQUALS, expr.root), expr.sourceName);
    }
  }

  /**
   * @return Whether this expression denotes an optional {@code @param}.
   */
  public boolean isOptionalArg() {
    return root.getType() == Token.EQUALS;
  }

  /**
   * @return Whether this expression denotes a rest args {@code @param}.
   */
  public boolean isVarArgs() {
    return root.getType() == Token.ELLIPSIS;
  }

  /**
   * Evaluates the type expression into a {@code JSType} object.
   */
  public JSType evaluate(StaticScope<JSType> scope, JSTypeRegistry registry) {
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.statements[5]++;
    JSType type = registry.createFromTypeNodes(root, sourceName, scope);
    root.setJSType(type);
CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5.statements[6]++;
    return type;
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof JSTypeExpression &&
        ((JSTypeExpression) other).root.isEquivalentTo(root);
  }

  @Override
  public int hashCode() {
    return root.toStringTree().hashCode();
  }

  /**
   * @return The source for this type expression.  Note that it will not
   * contain an expression if there's an @override tag.
   */
  public Node getRoot() {
    return root;
  }
}

class CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.JSTypeExpression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$1pqlraf7ag7cvncpnatqcrwnnj5pc9lr5 () {
    super("com.google.javascript.rhino.JSTypeExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.JSTypeExpression.java");
      for (int i = 1; i <= 6; i++) {
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
    for (int i = 1; i <= 1; i++) {
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

