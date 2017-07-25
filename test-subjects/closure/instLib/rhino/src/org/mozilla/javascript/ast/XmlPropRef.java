/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for an E4X XML {@code [expr]} property-ref expression.
 * The node type is {@link Token#REF_NAME}.<p>
 *
 * Syntax:<p>
 *
 * <pre> @<i><sub>opt</sub></i> ns:: <i><sub>opt</sub></i> name</pre>
 *
 * Examples include {@code name}, {@code ns::name}, {@code ns::*},
 * {@code *::name}, {@code *::*}, {@code @attr}, {@code @ns::attr},
 * {@code @ns::*}, {@code @*::attr}, {@code @*::*} and {@code @*}.<p>
 *
 * The node starts at the {@code @} token, if present.  Otherwise it starts
 * at the namespace name.  The node bounds extend through the closing
 * right-bracket, or if it is missing due to a syntax error, through the
 * end of the index expression.<p>
 */
public class XmlPropRef extends XmlRef {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.ping();
  }


    private Name propName;

    {
        type = Token.REF_NAME;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[1]++;
    }

    public XmlPropRef() {
    }

    public XmlPropRef(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[2]++;
    }

    public XmlPropRef(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[3]++;
    }

    /**
     * Returns property name.
     */
    public Name getPropName() {
        return propName;
    }

    /**
     * Sets property name, and sets its parent to this node.
     * @throws IllegalArgumentException if {@code propName} is {@code null}
     */
    public void setPropName(Name propName) {
        assertNotNull(propName);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[4]++;
        this.propName = propName;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[5]++;
        propName.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[6]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[7]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[8]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isAttributeAccess()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[1]++;
            sb.append("@");
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[10]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[2]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[3]++;
            sb.append(namespace.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[12]++;
            sb.append("::");
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[13]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[4]++;}
        sb.append(propName.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[14]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the namespace if present, then the property name.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[5]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[7]++;
                namespace.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[17]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[8]++;}
            propName.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.statements[18]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-XmlPropRef.java";
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rwb3aao5ch22mq23dia813mn5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlPropRef.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlPropRef.java");
      for (int i = 1; i <= 18; i++) {
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

