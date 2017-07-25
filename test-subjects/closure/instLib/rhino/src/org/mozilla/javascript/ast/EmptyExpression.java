/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for an empty expression.  Node type is {@link Token#EMPTY}.<p>
 *
 * To create an empty statement, wrap it with an {@link ExpressionStatement}.
 */
public class EmptyExpression extends AstNode {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx.ping();
  }


    {
        type = Token.EMPTY;
CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx.statements[1]++;
    }

    public EmptyExpression() {
    }

    public EmptyExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx.statements[2]++;
    }

    public EmptyExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx.statements[3]++;
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth);
    }

    /**
     * Visits this node.  There are no children.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx.statements[4]++;
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwptdoop4u0o9dqp0157lswm1g0q5nqxcx () {
    super("org.mozilla.javascript.ast.RHINO-SRC-EmptyExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-EmptyExpression.java");
      for (int i = 1; i <= 4; i++) {
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

