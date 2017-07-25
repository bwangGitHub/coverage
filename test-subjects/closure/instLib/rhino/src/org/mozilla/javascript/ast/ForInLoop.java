/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * For-in or for-each-in statement.  Node type is {@link Token#FOR}.<p>
 *
 * <pre><b>for</b> [<b>each</b>] ( LeftHandSideExpression <b>in</b> Expression ) Statement</pre>
 * <pre><b>for</b> [<b>each</b>] ( <b>var</b> VariableDeclarationNoIn <b>in</b> Expression ) Statement</pre>
 */
public class ForInLoop extends Loop {
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.ping();
  }


    protected AstNode iterator;
    protected AstNode iteratedObject;
    protected int inPosition = -1;
  {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[1]++;
  }
    protected int eachPosition = -1;
  {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[2]++;
  }
    protected boolean isForEach;

    {
        type = Token.FOR;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[3]++;
    }

    public ForInLoop() {
    }

    public ForInLoop(int pos) {
        super(pos);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[4]++;
    }

    public ForInLoop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[5]++;
    }

    /**
     * Returns loop iterator expression
     */
    public AstNode getIterator() {
        return iterator;
    }

    /**
     * Sets loop iterator expression:  the part before the "in" keyword.
     * Also sets its parent to this node.
     * @throws IllegalArgumentException if {@code iterator} is {@code null}
     */
    public void setIterator(AstNode iterator) {
        assertNotNull(iterator);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[6]++;
        this.iterator = iterator;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[7]++;
        iterator.setParent(this);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[8]++;
    }

    /**
     * Returns object being iterated over
     */
    public AstNode getIteratedObject() {
        return iteratedObject;
    }

    /**
     * Sets object being iterated over, and sets its parent to this node.
     * @throws IllegalArgumentException if {@code object} is {@code null}
     */
    public void setIteratedObject(AstNode object) {
        assertNotNull(object);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[9]++;
        this.iteratedObject = object;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[10]++;
        object.setParent(this);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[11]++;
    }

    /**
     * Returns whether the loop is a for-each loop
     */
    public boolean isForEach() {
        return isForEach;
    }

    /**
     * Sets whether the loop is a for-each loop
     */
    public void setIsForEach(boolean isForEach) {
        this.isForEach = isForEach;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[12]++;
    }

    /**
     * Returns position of "in" keyword
     */
    public int getInPosition() {
        return inPosition;
    }

    /**
     * Sets position of "in" keyword
     * @param inPosition position of "in" keyword,
     * or -1 if not present (e.g. in presence of a syntax error)
     */
    public void setInPosition(int inPosition) {
        this.inPosition = inPosition;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[13]++;
    }

    /**
     * Returns position of "each" keyword
     */
    public int getEachPosition() {
        return eachPosition;
    }

    /**
     * Sets position of "each" keyword
     * @param eachPosition position of "each" keyword,
     * or -1 if not present.
     */
    public void setEachPosition(int eachPosition) {
        this.eachPosition = eachPosition;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[14]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[15]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[16]++;
        sb.append("for ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[17]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[18]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isForEach()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.branches[1]++;
            sb.append("each ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[19]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.branches[2]++;}
        sb.append("(");
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[20]++;
        sb.append(iterator.toSource(0));
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[21]++;
        sb.append(" in ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[22]++;
        sb.append(iteratedObject.toSource(0));
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[23]++;
        sb.append(") ");
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[24]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[25]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((body.getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.branches[3]++;
            sb.append(body.toSource(depth).trim()).append("\n");
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[26]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.branches[4]++;
            sb.append("\n").append(body.toSource(depth+1));
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[27]++;
        }
        return sb.toString();
    }

    /**
     * Visits this node, the iterator, the iterated object, and the body.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.branches[5]++;
            iterator.visit(v);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[29]++;
            iteratedObject.visit(v);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[30]++;
            body.visit(v);
CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.statements[31]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ForInLoop.java";
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

  public CodeCoverCoverageCounter$iypomt0ag7yuozzgjulzzqob94nus13mw5cgx () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ForInLoop.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ForInLoop.java");
      for (int i = 1; i <= 31; i++) {
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

