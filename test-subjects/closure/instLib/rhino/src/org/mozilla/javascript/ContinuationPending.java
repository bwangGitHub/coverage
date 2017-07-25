/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * Exception thrown by
 * {@link org.mozilla.javascript.Context#executeScriptWithContinuations(Script, Scriptable)}
 * and {@link org.mozilla.javascript.Context#callFunctionWithContinuations(Callable, Scriptable, Object[])}
 * when execution encounters a continuation captured by
 * {@link org.mozilla.javascript.Context#captureContinuation()}.
 * Exception will contain the captured state needed to restart the continuation
 * with {@link org.mozilla.javascript.Context#resumeContinuation(Object, Scriptable, Object)}.
 */
public class ContinuationPending extends RuntimeException {
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd.ping();
  }

    private static final long serialVersionUID = 4956008116771118856L;
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd.statements[1]++;
  }
    private NativeContinuation continuationState;
    private Object applicationState;

    /**
     * Construct a ContinuationPending exception. Internal call only;
     * users of the API should get continuations created on their behalf by
     * calling {@link org.mozilla.javascript.Context#executeScriptWithContinuations(Script, Scriptable)}
     * and {@link org.mozilla.javascript.Context#callFunctionWithContinuations(Callable, Scriptable, Object[])}
     * @param continuationState Internal Continuation object
     */
    ContinuationPending(NativeContinuation continuationState) {
        this.continuationState = continuationState;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd.statements[2]++;
    }

    /**
     * Get continuation object. The only
     * use for this object is to be passed to
     * {@link org.mozilla.javascript.Context#resumeContinuation(Object, Scriptable, Object)}.
     * @return continuation object
     */
    public Object getContinuation() {
        return continuationState;
    }

    /**
     * @return internal continuation state
     */
    NativeContinuation getContinuationState() {
        return continuationState;
    }

    /**
     * Store an arbitrary object that applications can use to associate
     * their state with the continuation.
     * @param applicationState arbitrary application state
     */
    public void setApplicationState(Object applicationState) {
        this.applicationState = applicationState;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd.statements[3]++;
    }

    /**
     * @return arbitrary application state
     */
    public Object getApplicationState() {
        return applicationState;
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojyd2nnssj9lwkvema2xp1feis1vkrctqccsmctd () {
    super("org.mozilla.javascript.RHINO-SRC-ContinuationPending.java");
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ContinuationPending.java");
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

