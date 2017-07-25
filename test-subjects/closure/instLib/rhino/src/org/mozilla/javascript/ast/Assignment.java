/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

/**
 * AST node representing the set of assignment operators such as {@code =},
 * {@code *=} and {@code +=}.
 */
public class Assignment extends InfixExpression {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5.ping();
  }


    public Assignment() {
    }

    public Assignment(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5.statements[1]++;
    }

    public Assignment(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5.statements[2]++;
    }

    public Assignment(int pos, int len, AstNode left, AstNode right) {
        super(pos, len, left, right);
CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5.statements[3]++;
    }

    public Assignment(AstNode left, AstNode right) {
        super(left, right);
CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5.statements[4]++;
    }

    public Assignment(int operator, AstNode left,
                      AstNode right, int operatorPos) {
        super(operator, left, right, operatorPos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5.statements[5]++;
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5 ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw47dmarjuaed6kesphf0zvj5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Assignment.java");
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Assignment.java");
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

