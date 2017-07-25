/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

/**
 * Abstract base type for loops.
 */
public abstract class Loop extends Scope {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.ping();
  }


    protected AstNode body;
    protected int lp = -1;
  {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[1]++;
  }
    protected int rp = -1;
  {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[2]++;
  }

    public Loop() {
    }

    public Loop(int pos) {
        super(pos);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[3]++;
    }

    public Loop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[4]++;
    }

    /**
     * Returns loop body
     */
    public AstNode getBody() {
        return body;
    }

    /**
     * Sets loop body.  Sets the parent of the body to this loop node,
     * and updates its offset to be relative.  Extends the length of this
     * node to include the body.
     */
    public void setBody(AstNode body) {
        this.body = body;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[5]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[6]++;
        int end = body.getPosition() + body.getLength();
        this.setLength(end - this.getPosition());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[7]++;
        body.setParent(this);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[8]++;
    }

    /**
     * Returns left paren position, -1 if missing
     */
    public int getLp() {
        return lp;
    }

    /**
     * Sets left paren position
     */
    public void setLp(int lp) {
        this.lp = lp;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[9]++;
    }

    /**
     * Returns right paren position, -1 if missing
     */
    public int getRp() {
        return rp;
    }

    /**
     * Sets right paren position
     */
    public void setRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[10]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[11]++;
        this.rp = rp;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt.statements[12]++;
    }
}

class CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1cnp1i09zf4s7wx3bb7af2ncsfhopt () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Loop.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Loop.java");
      for (int i = 1; i <= 12; i++) {
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

