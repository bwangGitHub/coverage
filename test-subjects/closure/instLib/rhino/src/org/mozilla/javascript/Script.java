/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * All compiled scripts implement this interface.
 * <p>
 * This class encapsulates script execution relative to an
 * object scope.
 * @since 1.3
 */

public interface Script {

    /**
     * Execute the script.
     * <p>
     * The script is executed in a particular runtime Context, which
     * must be associated with the current thread.
     * The script is executed relative to a scope--definitions and
     * uses of global top-level variables and functions will access
     * properties of the scope object. For compliant ECMA
     * programs, the scope must be an object that has been initialized
     * as a global object using <code>Context.initStandardObjects</code>.
     * <p>
     *
     * @param cx the Context associated with the current thread
     * @param scope the scope to execute relative to
     * @return the result of executing the script
     * @see org.mozilla.javascript.Context#initStandardObjects()
     */
    public Object exec(Context cx, Scriptable scope);

}

class CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbc75gur1w3vr20kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbc75gur1w3vr20kh ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1wcjkiz20v4sksnrnbc75gur1w3vr20kh () {
    super("org.mozilla.javascript.RHINO-SRC-Script.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Script.java");
      for (int i = 1; i <= -1; i++) {
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

