/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

/**
 * AST node for an XML-text-only component of an XML literal expression.  This
 * node differs from a {@link StringLiteral} in that it does not have quotes for
 * delimiters.
 */
public class XmlString extends XmlFragment {
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.ping();
  }


    private String xml;

    public XmlString() {
    }

    public XmlString(int pos) {
        super(pos);
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[1]++;
    }

    public XmlString(int pos, String s) {
        super(pos);
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[2]++;
        setXml(s);
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[3]++;
    }

    /**
     * Sets the string for this XML component.  Sets the length of the
     * component to the length of the passed string.
     * @param s a string of xml text
     * @throws IllegalArgumentException} if {@code s} is {@code null}
     */
    public void setXml(String s) {
        assertNotNull(s);
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[4]++;
        xml = s;
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[5]++;
        setLength(s.length());
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[6]++;
    }

    /**
     * Returns the xml string for this component.
     * Note that it may not be well-formed XML; it is a fragment.
     */
    public String getXml() {
        return xml;
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth) + xml;
    }

    /**
     * Visits this node.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox.statements[7]++;
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzhb5dvj7ij9v09e8ksa8eox () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlString.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 7; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlString.java");
      for (int i = 1; i <= 7; i++) {
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

