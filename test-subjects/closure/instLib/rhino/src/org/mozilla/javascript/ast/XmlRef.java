/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

/**
 * Base class for E4X XML attribute-access or property-get expressions.
 * Such expressions can take a variety of forms. The general syntax has
 * three parts:<p>
 *
 * <ol>
 *  <li>optional: an {@code @}</li>  (specifying an attribute access)</li>
 *  <li>optional: a namespace (a {@code Name}) and double-colon</li>
 *  <li>required:  either a {@code Name} or a bracketed [expression]</li>
 * </ol>
 *
 * The property-name expressions (examples:  {@code ns::name}, {@code @name})
 * are represented as {@link XmlPropRef} nodes.  The bracketed-expression
 * versions (examples:  {@code ns::[name]}, {@code @[name]}) become
 * {@link XmlElemRef} nodes.<p>
 *
 * This node type (or more specifically, its subclasses) will
 * sometimes be the right-hand child of a {@link PropertyGet} node or
 * an {@link XmlMemberGet} node.  The {@code XmlRef} node may also
 * be a standalone primary expression with no explicit target, which
 * is valid in certain expression contexts such as
 * {@code company..employee.(@id &lt; 100)} - in this case, the {@code @id}
 * is an {@code XmlRef} that is part of an infix '&lt;' expression
 * whose parent is an {@code XmlDotQuery} node.<p>
 */
public abstract class XmlRef extends AstNode {
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.ping();
  }


    protected Name namespace;
    protected int atPos = -1;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[1]++;
  }
    protected int colonPos = -1;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[2]++;
  }

    public XmlRef() {
    }

    public XmlRef(int pos) {
        super(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[3]++;
    }

    public XmlRef(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[4]++;
    }

    /**
     * Return the namespace.  May be {@code @null}.
     */
    public Name getNamespace() {
        return namespace;
    }

    /**
     * Sets namespace, and sets its parent to this node.
     * Can be {@code null}.
     */
    public void setNamespace(Name namespace) {
        this.namespace = namespace;
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[5]++;
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((namespace != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.branches[1]++;
            namespace.setParent(this);
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[7]++;
} else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.branches[2]++;}
    }

    /**
     * Returns {@code true} if this expression began with an {@code @}-token.
     */
    public boolean isAttributeAccess() {
        return atPos >= 0;
    }

    /**
     * Returns position of {@code @}-token, or -1 if this is not
     * an attribute-access expression.
     */
    public int getAtPos() {
        return atPos;
    }

    /**
     * Sets position of {@code @}-token, or -1
     */
    public void setAtPos(int atPos) {
        this.atPos = atPos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[8]++;
    }

    /**
     * Returns position of {@code ::} token, or -1 if not present.
     * It will only be present if the namespace node is non-{@code null}.
     */
    public int getColonPos() {
        return colonPos;
    }

    /**
     * Sets position of {@code ::} token, or -1 if not present
     */
    public void setColonPos(int colonPos) {
        this.colonPos = colonPos;
CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t.statements[9]++;
    }
}

class CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-XmlRef.java";
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

  public CodeCoverCoverageCounter$1wcjkiz20v4sksnro2w6psp92jupkk09t () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlRef.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlRef.java");
      for (int i = 1; i <= 9; i++) {
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

