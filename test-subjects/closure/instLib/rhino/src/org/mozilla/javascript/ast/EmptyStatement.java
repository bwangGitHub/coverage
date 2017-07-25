/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for an empty statement.  Node type is {@link Token#EMPTY}.<p>
 *
 */
public class EmptyStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.ping();
  }


    {
        type = Token.EMPTY;
CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.statements[1]++;
    }

    public EmptyStatement() {
    }

    public EmptyStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.statements[2]++;
    }

    public EmptyStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.statements[3]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.statements[4]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth)).append(";\n");
CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.statements[5]++;
        return sb.toString();
    }

    /**
     * Visits this node.  There are no children.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9.statements[6]++;
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevozj818pzi1ces5f787fbyx21hnja9 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-EmptyStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-EmptyStatement.java");
      for (int i = 1; i <= 6; i++) {
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

