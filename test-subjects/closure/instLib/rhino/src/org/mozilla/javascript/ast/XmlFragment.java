/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * Abstract base type for components that comprise an {@link XmlLiteral}
 * object. Node type is {@link Token#XML}.<p>
 */
public abstract class XmlFragment extends AstNode {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l.ping();
  }


    {
        type = Token.XML;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l.statements[1]++;
    }

    public XmlFragment() {
    }

    public XmlFragment(int pos) {
        super(pos);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l.statements[2]++;
    }

    public XmlFragment(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l.statements[3]++;
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9idqvd7tl7djyp3txxe9coq117l () {
    super("org.mozilla.javascript.ast.RHINO-SRC-XmlFragment.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-XmlFragment.java");
      for (int i = 1; i <= 3; i++) {
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

