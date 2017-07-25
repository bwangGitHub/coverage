/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for an indexed property reference, such as {@code foo['bar']} or
 * {@code foo[2]}.  This is sometimes called an "element-get" operation, hence
 * the name of the node.<p>
 *
 * Node type is {@link Token#GETELEM}.<p>
 *
 * The node bounds extend from the beginning position of the target through the
 * closing right-bracket.  In the presence of a syntax error, the right bracket
 * position is -1, and the node ends at the end of the element expression.
 */
public class ElementGet extends AstNode {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.ping();
  }


    private AstNode target;
    private AstNode element;
    private int lb = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[1]++;
  }
    private int rb = -1;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[2]++;
  }

    {
        type = Token.GETELEM;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[3]++;
    }

    public ElementGet() {
    }

    public ElementGet(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[4]++;
    }

    public ElementGet(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[5]++;
    }

    public ElementGet(AstNode target, AstNode element) {
        setTarget(target);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[6]++;
        setElement(element);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[7]++;
    }

    /**
     * Returns the object on which the element is being fetched.
     */
    public AstNode getTarget() {
        return target;
    }

    /**
     * Sets target object, and sets its parent to this node.
     * @param target expression evaluating to the object upon which
     * to do the element lookup
     * @throws IllegalArgumentException if target is {@code null}
     */
    public void setTarget(AstNode target) {
        assertNotNull(target);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[8]++;
        this.target = target;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[9]++;
        target.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[10]++;
    }

    /**
     * Returns the element being accessed
     */
    public AstNode getElement() {
        return element;
    }

    /**
     * Sets the element being accessed, and sets its parent to this node.
     * @throws IllegalArgumentException if element is {@code null}
     */
    public void setElement(AstNode element) {
        assertNotNull(element);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[11]++;
        this.element = element;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[12]++;
        element.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[13]++;
    }

    /**
     * Returns left bracket position
     */
    public int getLb() {
        return lb;
    }

    /**
     * Sets left bracket position
     */
    public void setLb(int lb) {
        this.lb = lb;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[14]++;
    }

    /**
     * Returns right bracket position, -1 if missing
     */
    public int getRb() {
        return rb;
    }

    /**
     * Sets right bracket position, -1 if not present
     */
    public void setRb(int rb) {
        this.rb = rb;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[15]++;
    }

    public void setParens(int lb, int rb) {
        this.lb = lb;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[16]++;
        this.rb = rb;
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[17]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[18]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[19]++;
        sb.append(target.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[20]++;
        sb.append("[");
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[21]++;
        sb.append(element.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[22]++;
        sb.append("]");
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[23]++;
        return sb.toString();
    }

    /**
     * Visits this node, the target, and the index expression.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[24]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.branches[1]++;
            target.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[25]++;
            element.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.statements[26]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl ());
  }
    public static long[] statements = new long[27];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ElementGet.java";
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw5e8mgfuoqiicvqlfya05vfl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ElementGet.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 26; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ElementGet.java");
      for (int i = 1; i <= 26; i++) {
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

