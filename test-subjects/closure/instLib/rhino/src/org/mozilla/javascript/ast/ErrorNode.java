/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing a parse error or a warning.  Node type is
 * {@link Token#ERROR}.<p>
 */
public class ErrorNode extends AstNode {
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx.ping();
  }


    private String message;

    {
        type = Token.ERROR;
CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx.statements[1]++;
    }

    public ErrorNode() {
    }

    public ErrorNode(int pos) {
        super(pos);
CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx.statements[2]++;
    }

    public ErrorNode(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx.statements[3]++;
    }

    /**
     * Returns error message key
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets error message key
     */
    public void setMessage(String message) {
        this.message = message;
CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx.statements[4]++;
    }

    @Override
    public String toSource(int depth) {
        return "";
    }

    /**
     * Error nodes are not visited during normal visitor traversals,
     * but comply with the {@link AstNode#visit} interface.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx.statements[5]++;
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzgicmmwbzv852utkf1cljkx () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ErrorNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ErrorNode.java");
      for (int i = 1; i <= 5; i++) {
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

