/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for a RegExp literal.
 * Node type is {@link Token#REGEXP}.<p>
 */
public class RegExpLiteral extends AstNode {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.ping();
  }


    private String value;
    private String flags;

    {
        type = Token.REGEXP;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[1]++;
    }

    public RegExpLiteral() {
    }

    public RegExpLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[2]++;
    }

    public RegExpLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[3]++;
    }

    /**
     * Returns the regexp string without delimiters
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the regexp string without delimiters
     * @throws IllegalArgumentException} if value is {@code null}
     */
    public void setValue(String value) {
        assertNotNull(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[4]++;
        this.value = value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[5]++;
    }

    /**
     * Returns regexp flags, {@code null} or "" if no flags specified
     */
    public String getFlags() {
        return flags;
    }

    /**
     * Sets regexp flags.  Can be {@code null} or "".
     */
    public void setFlags(String flags) {
        this.flags = flags;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[6]++;
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth) + "/" + value + "/"
                + (flags == null ? "" : flags);
    }

    /**
     * Visits this node.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5.statements[7]++;
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5 ());
  }
    public static long[] statements = new long[8];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16ld12kueodj31d2bjsd6rahphhbbz5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-RegExpLiteral.java");
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-RegExpLiteral.java");
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

