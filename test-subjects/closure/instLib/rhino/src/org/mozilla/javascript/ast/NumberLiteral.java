/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for a Number literal. Node type is {@link Token#NUMBER}.<p>
 */
public class NumberLiteral extends AstNode {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.ping();
  }


    private String value;
    private double number;

    {
        type = Token.NUMBER;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[1]++;
    }

    public NumberLiteral() {
    }

    public NumberLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[2]++;
    }

    public NumberLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[3]++;
    }

    /**
     * Constructor.  Sets the length to the length of the {@code value} string.
     */
    public NumberLiteral(int pos, String value) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[4]++;
        setValue(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[5]++;
        setLength(value.length());
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[6]++;
    }

    /**
     * Constructor.  Sets the length to the length of the {@code value} string.
     */
    public NumberLiteral(int pos, String value, double number) {
        this(pos, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[7]++;
        setDouble(number);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[8]++;
    }

    public NumberLiteral(double number) {
        setDouble(number);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[9]++;
        setValue(Double.toString(number));
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[10]++;
    }

    /**
     * Returns the node's string value (the original source token)
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the node's value
     * @throws IllegalArgumentException} if value is {@code null}
     */
    public void setValue(String value) {
        assertNotNull(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[11]++;
        this.value = value;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[12]++;
    }

    /**
     * Gets the {@code double} value.
     */
    public double getNumber() {
        return number;
    }

    /**
     * Sets the node's {@code double} value.
     */
    public void setNumber(double value) {
        number = value;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[13]++;
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth) + (value == null ? "<null>" : value);
    }

    /**
     * Visits this node.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5.statements[14]++;
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16lcpa851zt3z134qepl3pnt1vtcpr5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-NumberLiteral.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-NumberLiteral.java");
      for (int i = 1; i <= 14; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

