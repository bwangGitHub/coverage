/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * A proxy for the regexp package, so that the regexp package can be
 * loaded optionally.
 *
 */
public interface RegExpProxy
{
    // Types of regexp actions
    public static final int RA_MATCH   = 1;
    public static final int RA_REPLACE = 2;
    public static final int RA_SEARCH  = 3;

    public boolean isRegExp(Scriptable obj);

    public Object compileRegExp(Context cx, String source, String flags);

    public Scriptable wrapRegExp(Context cx, Scriptable scope,
                                 Object compiled);

    public Object action(Context cx, Scriptable scope,
                         Scriptable thisObj, Object[] args,
                         int actionType);

    public int find_split(Context cx, Scriptable scope, String target,
                          String separator, Scriptable re,
                          int[] ip, int[] matchlen,
                          boolean[] matched, String[][] parensp);

    public Object js_split(Context _cx, Scriptable _scope,
                           String thisString, Object[] _args);
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9ide0lsti4kiixzuktaq0547d5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9ide0lsti4kiixzuktaq0547d5t ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9ide0lsti4kiixzuktaq0547d5t () {
    super("org.mozilla.javascript.RHINO-SRC-RegExpProxy.java");
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-RegExpProxy.java");
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

