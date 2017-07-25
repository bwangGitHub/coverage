/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for a single 'for (foo in bar)' loop construct in a JavaScript 1.7
 * Array comprehension.  This node type is almost equivalent to a
 * {@link ForInLoop}, except that it has no body statement.
 * Node type is {@link Token#FOR}.<p>
 */
public class ArrayComprehensionLoop extends ForInLoop {
  static {
    CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.ping();
  }


    public ArrayComprehensionLoop() {
    }

    public ArrayComprehensionLoop(int pos) {
        super(pos);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.statements[1]++;
    }

    public ArrayComprehensionLoop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.statements[2]++;
    }
    
    /**
     * Returns {@code null} for loop body
     * @return loop body (always {@code null} for this node type)
     */
    public AstNode getBody() {
        return null;
    }

    /**
     * Throws an exception on attempts to set the loop body.
     * @param body loop body
     * @throws UnsupportedOperationException
     */
    public void setBody(AstNode body) {
        throw new UnsupportedOperationException("this node type has no body");
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth)
                + " for "
                + (isForEach()?"each ":"")
                + "("
                + iterator.toSource(0)
                + " in "
                + iteratedObject.toSource(0)
                + ")";
    }

    /**
     * Visits the iterator expression and the iterated object expression.
     * There is no body-expression for this loop type.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.branches[1]++;
            iterator.visit(v);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.statements[4]++;
            iteratedObject.visit(v);
CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.statements[5]++;

        } else {
  CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75 ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ArrayComprehensionLoop.java";
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

  public CodeCoverCoverageCounter$srvjzfg6sfs5ucp1159ui1xbb49pj9mfcp4j1zzfjnhsf56w0r5fa7q75 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ArrayComprehensionLoop.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ArrayComprehensionLoop.java");
      for (int i = 1; i <= 5; i++) {
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

