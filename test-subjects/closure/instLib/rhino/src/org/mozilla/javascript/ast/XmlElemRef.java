/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for an E4X XML {@code [expr]} member-ref expression.
 * The node type is {@link Token#REF_MEMBER}.<p>
 *
 * Syntax:<p>
 *
 * <pre> @<i><sub>opt</sub></i> ns:: <i><sub>opt</sub></i> [ expr ]</pre>
 *
 * Examples include {@code ns::[expr]}, {@code @ns::[expr]}, {@code @[expr]},
 * {@code *::[expr]} and {@code @*::[expr]}.<p>
 *
 * Note that the form {@code [expr]} (i.e. no namespace or
 * attribute-qualifier) is not a legal {@code XmlElemRef} expression,
 * since it's already used for standard JavaScript {@link ElementGet}
 * array-indexing.  Hence, an {@code XmlElemRef} node always has
 * either the attribute-qualifier, a non-{@code null} namespace node,
 * or both.<p>
 *
 * The node starts at the {@code @} token, if present.  Otherwise it starts
 * at the namespace name.  The node bounds extend through the closing
 * right-bracket, or if it is missing due to a syntax error, through the
 * end of the index expression.<p>
 */
public class XmlElemRef extends XmlRef {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.ping();
  }


    private AstNode indexExpr;
    private int lb = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[1]++;
  }
    private int rb = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[2]++;
  }

    {
        type = Token.REF_MEMBER;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[3]++;
    }

    public XmlElemRef() {
    }

    public XmlElemRef(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[4]++;
    }

    public XmlElemRef(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[5]++;
    }

    /**
     * Returns index expression: the 'expr' in {@code @[expr]}
     * or {@code @*::[expr]}.
     */
    public AstNode getExpression() {
        return indexExpr;
    }

    /**
     * Sets index expression, and sets its parent to this node.
     * @throws IllegalArgumentException if {@code expr} is {@code null}
     */
    public void setExpression(AstNode expr) {
        assertNotNull(expr);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[6]++;
        indexExpr = expr;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[7]++;
        expr.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[8]++;
    }

    /**
     * Returns left bracket position, or -1 if missing.
     */
    public int getLb() {
        return lb;
    }

    /**
     * Sets left bracket position, or -1 if missing.
     */
    public void setLb(int lb) {
        this.lb = lb;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[9]++;
    }

    /**
     * Returns left bracket position, or -1 if missing.
     */
    public int getRb() {
        return rb;
    }

    /**
     * Sets right bracket position, -1 if missing.
     */
    public void setRb(int rb) {
        this.rb = rb;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[10]++;
    }

    /**
     * Sets both bracket positions.
     */
    public void setBrackets(int lb, int rb) {
        this.lb = lb;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[11]++;
        this.rb = rb;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[12]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[13]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[14]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isAttributeAccess()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[1]++;
            sb.append("@");
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[16]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[2]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[3]++;
            sb.append(namespace.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[18]++;
            sb.append("::");
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[19]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[4]++;}
        sb.append("[");
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[20]++;
        sb.append(indexExpr.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[21]++;
        sb.append("]");
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[22]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the namespace if provided, then the
     * index expression.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[5]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[7]++;
                namespace.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[25]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[8]++;}
            indexExpr.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.statements[26]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh ());
  }
    public static long[] statements = new long[27];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-XmlElemRef.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aantfsyajtvo0ac3do1gh () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlElemRef.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 26; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlElemRef.java");
      for (int i = 1; i <= 26; i++) {
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

