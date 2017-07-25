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
 * AST node for an Array literal.  The elements list will always be
 * non-{@code null}, although the list will have no elements if the Array literal
 * is empty.<p>
 *
 * Node type is {@link Token#ARRAYLIT}.<p>
 *
 * <pre><i>ArrayLiteral</i> :
 *        <b>[</b> Elisionopt <b>]</b>
 *        <b>[</b> ElementList <b>]</b>
 *        <b>[</b> ElementList , Elisionopt <b>]</b>
 * <i>ElementList</i> :
 *        Elisionopt AssignmentExpression
 *        ElementList , Elisionopt AssignmentExpression
 * <i>Elision</i> :
 *        <b>,</b>
 *        Elision <b>,</b></pre>
 */
public class ArrayLiteral extends AstNode implements DestructuringForm {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.ping();
  }


    private static final List<AstNode> NO_ELEMS =
        Collections.unmodifiableList(new ArrayList<AstNode>());
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[1]++;
  }

    private List<AstNode> elements;
    private int destructuringLength;
    private int skipCount;
    private boolean isDestructuring;

    {
        type = Token.ARRAYLIT;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[2]++;
    }

    public ArrayLiteral() {
    }

    public ArrayLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[3]++;
    }

    public ArrayLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[4]++;
    }

    /**
     * Returns the element list
     * @return the element list.  If there are no elements, returns an immutable
     *         empty list.  Elisions are represented as {@link EmptyExpression}
     *         nodes.
     */
    public List<AstNode> getElements() {
        return elements != null ? elements : NO_ELEMS;
    }

    /**
     * Sets the element list, and sets each element's parent to this node.
     * @param elements the element list.  Can be {@code null}.
     */
    public void setElements(List<AstNode> elements) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[1]++;
            this.elements = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[6]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[2]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.elements != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[3]++;
                this.elements.clear();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[8]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[4]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[1]++;


            for (AstNode e : elements) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[3]++;
}
                addElement(e);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[10]++;
  }
        }
    }

    /**
     * Adds an element to the list, and sets its parent to this node.
     * @param element the element to add
     * @throws IllegalArgumentException if element is {@code null}.  To indicate
     *         an empty element, use an {@link EmptyExpression} node.
     */
    public void addElement(AstNode element) {
        assertNotNull(element);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[11]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[5]++;
            elements = new ArrayList<AstNode>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[13]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[6]++;}
        elements.add(element);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[14]++;
        element.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[15]++;
    }

    /**
     * Returns the number of elements in this {@code Array} literal,
     * including empty elements.
     */
    public int getSize() {
        return elements == null ? 0 : elements.size();
    }

    /**
     * Returns element at specified index.
     * @param index the index of the element to retrieve
     * @return the element
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public AstNode getElement(int index) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((elements == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[7]++;
            throw new IndexOutOfBoundsException("no elements");
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[8]++;}
        return elements.get(index);
    }

    /**
     * Returns destructuring length
     */
    public int getDestructuringLength() {
      return destructuringLength;
    }

    /**
     * Sets destructuring length.  This is set by the parser and used
     * by the code generator.  {@code for ([a,] in obj)} is legal,
     * but {@code for ([a] in obj)} is not since we have both key and
     * value supplied.  The difference is only meaningful in array literals
     * used in destructuring-assignment contexts.
     */
    public void setDestructuringLength(int destructuringLength) {
      this.destructuringLength = destructuringLength;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[17]++;
    }

    /**
     * Used by code generator.
     * @return the number of empty elements
     */
    public int getSkipCount() {
        return skipCount;
    }

    /**
     * Used by code generator.
     * @param count the count of empty elements
     */
    public void setSkipCount(int count) {
        skipCount = count;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[18]++;
    }

    /**
     * Marks this node as being a destructuring form - that is, appearing
     * in a context such as {@code for ([a, b] in ...)} where it's the
     * target of a destructuring assignment.
     */
    public void setIsDestructuring(boolean destructuring) {
        isDestructuring = destructuring;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[19]++;
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
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[20]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[21]++;
        sb.append("[");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[22]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((elements != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[9]++;
            printList(elements, sb);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[24]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[10]++;}
        sb.append("]");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[25]++;
        return sb.toString();
    }

    /**
     * Visits this node, then visits its element expressions in order.
     * Any empty elements are represented by {@link EmptyExpression}
     * objects, so the callback will never be passed {@code null}.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[11]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[4]++;


            for (AstNode e : getElements()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.loops[6]++;
}
                e.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.statements[28]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd.branches[12]++;}
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ArrayLiteral.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmg2sqs4d56yymmivyk876slwktd () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ArrayLiteral.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ArrayLiteral.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

