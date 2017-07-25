/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AST node for an Object literal (also called an Object initialiser in
 * Ecma-262).  The elements list will always be non-{@code null}, although
 * the list will have no elements if the Object literal is empty.<p>
 *
 * Node type is {@link Token#OBJECTLIT}.<p>
 *
 * <pre><i>ObjectLiteral</i> :
 *       <b>{}</b>
 *       <b>{</b> PropertyNameAndValueList <b>}</b>
 * <i>PropertyNameAndValueList</i> :
 *       PropertyName <b>:</b> AssignmentExpression
 *       PropertyNameAndValueList , PropertyName <b>:</b> AssignmentExpression
 * <i>PropertyName</i> :
 *       Identifier
 *       StringLiteral
 *       NumericLiteral</pre>
 */
public class ObjectLiteral extends AstNode implements DestructuringForm {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.ping();
  }


    private static final List<ObjectProperty> NO_ELEMS =
        Collections.unmodifiableList(new ArrayList<ObjectProperty>());
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[1]++;
  }

    private List<ObjectProperty> elements;
    boolean isDestructuring;

    {
        type = Token.OBJECTLIT;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[2]++;
    }

    public ObjectLiteral() {
    }

    public ObjectLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[3]++;
    }

    public ObjectLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[4]++;
    }

    /**
     * Returns the element list.  Returns an immutable empty list if there are
     * no elements.
     */
    public List<ObjectProperty> getElements() {
        return elements != null ? elements : NO_ELEMS;
    }

    /**
     * Sets the element list, and updates the parent of each element.
     * Replaces any existing elements.
     * @param elements the element list.  Can be {@code null}.
     */
    public void setElements(List<ObjectProperty> elements) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[1]++;
            this.elements = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[6]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[2]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.elements != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[3]++;
                this.elements.clear();
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[8]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[4]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[1]++;


            for (ObjectProperty o : elements) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[1]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[2]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[3]++;
}
                addElement(o);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[10]++;
  }
        }
    }

    /**
     * Adds an element to the list, and sets its parent to this node.
     * @param element the property node to append to the end of the list
     * @throws IllegalArgumentException} if element is {@code null}
     */
    public void addElement(ObjectProperty element) {
        assertNotNull(element);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[11]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[5]++;
            elements = new ArrayList<ObjectProperty>();
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[13]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[6]++;}
        elements.add(element);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[14]++;
        element.setParent(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[15]++;
    }

    /**
     * Marks this node as being a destructuring form - that is, appearing
     * in a context such as {@code for ([a, b] in ...)} where it's the
     * target of a destructuring assignment.
     */
    public void setIsDestructuring(boolean destructuring) {
        isDestructuring = destructuring;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[16]++;
    }

    /**
     * Returns true if this node is in a destructuring position:
     * a function parameter, the target of a variable initializer, the
     * iterator of a for..in loop, etc.
     */
    public boolean isDestructuring() {
        return isDestructuring;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[17]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[18]++;
        sb.append("{");
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[19]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((elements != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[7]++;
            printList(elements, sb);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[21]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[8]++;}
        sb.append("}");
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[22]++;
        return sb.toString();
    }

    /**
     * Visits this node, then visits each child property node, in lexical
     * (source) order.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[9]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[4]++;


            for (ObjectProperty prop : getElements()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[4]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[5]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.loops[6]++;
}
                prop.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.statements[25]++;
            }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt.branches[10]++;}
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ObjectLiteral.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16lcs1zw509j8qdbzzxqgt9lo99d1lt () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ObjectLiteral.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ObjectLiteral.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

