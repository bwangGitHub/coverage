/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for an embedded JavaScript expression within an E4X XML literal.
 * Node type, like {@link XmlLiteral}, is {@link Token#XML}.  The node length
 * includes the curly braces.
 */
public class XmlExpression extends XmlFragment {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.ping();
  }


    private AstNode expression;
    private boolean isXmlAttribute;

    public XmlExpression() {
    }

    public XmlExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[1]++;
    }

    public XmlExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[2]++;
    }

    public XmlExpression(int pos, AstNode expr) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[3]++;
        setExpression(expr);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[4]++;
    }

    /**
     * Returns the expression embedded in {}
     */
    public AstNode getExpression() {
        return expression;
    }

    /**
     * Sets the expression embedded in {}, and sets its parent to this node.
     * @throws IllegalArgumentException if {@code expression} is {@code null}
     */
    public void setExpression(AstNode expression) {
        assertNotNull(expression);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[5]++;
        this.expression = expression;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[6]++;
        expression.setParent(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[7]++;
    }

    /**
     * Returns whether this is part of an xml attribute value
     */
    public boolean isXmlAttribute() {
      return isXmlAttribute;
    }

    /**
     * Sets whether this is part of an xml attribute value
     */
    public void setIsXmlAttribute(boolean isXmlAttribute) {
      this.isXmlAttribute = isXmlAttribute;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[8]++;
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth) + "{" + expression.toSource(depth) + "}";
    }

    /**
     * Visits this node, then the child expression.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.branches[1]++;
            expression.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.statements[10]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-XmlExpression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
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

  public CodeCoverCoverageCounter$11f1r6z5fa12k16ldj4lq50txx29hx12mvfgit9km7a9 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlExpression.java");
      for (int i = 1; i <= 10; i++) {
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

