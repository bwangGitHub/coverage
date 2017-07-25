/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for a single name:value entry in an Object literal.
 * For simple entries, the node type is {@link Token#COLON}, and
 * the name (left side expression) is either a {@link Name}, a
 * {@link StringLiteral} or a {@link NumberLiteral}.<p>
 *
 * This node type is also used for getter/setter properties in object
 * literals.  In this case the node bounds include the "get" or "set"
 * keyword.  The left-hand expression in this case is always a
 * {@link Name}, and the overall node type is {@link Token#GET} or
 * {@link Token#SET}, as appropriate.<p>
 *
 * The {@code operatorPosition} field is meaningless if the node is
 * a getter or setter.<p>
 *
 * <pre><i>ObjectProperty</i> :
 *       PropertyName <b>:</b> AssignmentExpression
 * <i>PropertyName</i> :
 *       Identifier
 *       StringLiteral
 *       NumberLiteral</pre>
 */
public class ObjectProperty extends InfixExpression {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.ping();
  }


    {
        type = Token.COLON;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[1]++;
    }

    /**
     * Sets the node type.  Must be one of
     * {@link Token#COLON}, {@link Token#GET}, or {@link Token#SET}.
     * @throws IllegalArgumentException if {@code nodeType} is invalid
     */
    public void setNodeType(int nodeType) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((nodeType != Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((nodeType != Token.GET) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nodeType != Token.SET) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[1]++;
            throw new IllegalArgumentException("invalid node type: "
                                               + nodeType);
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[2]++;}
        setType(nodeType);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[3]++;
    }

    public ObjectProperty() {
    }

    public ObjectProperty(int pos) {
        super(pos);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[4]++;
    }

    public ObjectProperty(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[5]++;
    }

    /**
     * Marks this node as a "getter" property.
     */
    public void setIsGetter() {
        type = Token.GET;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[6]++;
    }

    /**
     * Returns true if this is a getter function.
     */
    public boolean isGetter() {
        return type == Token.GET;
    }

    /**
     * Marks this node as a "setter" property.
     */
    public void setIsSetter() {
        type = Token.SET;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[7]++;
    }

    /**
     * Returns true if this is a setter function.
     */
    public boolean isSetter() {
        return type == Token.SET;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[8]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[9]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isGetter()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[3]++;
            sb.append("get ");
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[11]++;

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[4]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[12]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isSetter()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[5]++;
            sb.append("set ");
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[13]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[6]++;}
}
        sb.append(left.toSource(0));
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[14]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type == Token.COLON) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[7]++;
            sb.append(": ");
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[16]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.branches[8]++;}
        sb.append(right.toSource(0));
CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1.statements[17]++;
        return sb.toString();
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1 ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ObjectProperty.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1};
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevuvi78jlvsuyimejesyhdvvfgxf4o1 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ObjectProperty.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ObjectProperty.java");
      for (int i = 1; i <= 17; i++) {
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

