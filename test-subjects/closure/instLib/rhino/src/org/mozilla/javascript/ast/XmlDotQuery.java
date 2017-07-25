/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing an E4X {@code foo.(bar)} query expression.
 * The node type (operator) is {@link Token#DOTQUERY}.
 * Its {@code getLeft} node is the target ("foo" in the example),
 * and the {@code getRight} node is the filter expression node.<p>
 *
 * This class exists separately from {@link InfixExpression} largely because it
 * has different printing needs.  The position of the left paren is just after
 * the dot (operator) position, and the right paren is the final position in the
 * bounds of the node.  If the right paren is missing, the node ends at the end
 * of the filter expression.
 */
public class XmlDotQuery extends InfixExpression {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.ping();
  }


    private int rp = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[1]++;
  }

    {
        type = Token.DOTQUERY;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[2]++;
    }

    public XmlDotQuery() {
    }

    public XmlDotQuery(int pos) {
        super(pos);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[3]++;
    }

    public XmlDotQuery(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[4]++;
    }

    /**
     * Returns right-paren position, -1 if missing.<p>
     *
     * Note that the left-paren is automatically the character
     * immediately after the "." in the operator - no whitespace is
     * permitted between the dot and lp by the scanner.
     */
    public int getRp() {
        return rp;
    }

    /**
     * Sets right-paren position
     */
    public void setRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[5]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[6]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[7]++;
        sb.append(getLeft().toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[8]++;
        sb.append(".(");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[9]++;
        sb.append(getRight().toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[10]++;
        sb.append(")");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l.statements[11]++;
        return sb.toString();
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7t5r5uftbgcfptmmwud7l () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlDotQuery.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlDotQuery.java");
      for (int i = 1; i <= 11; i++) {
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

