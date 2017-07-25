/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * This is interface that all functions in JavaScript must implement.
 * The interface provides for calling functions and constructors.
 *
 * @see org.mozilla.javascript.Scriptable
 */

public interface Function extends Scriptable, Callable
{
    /**
     * Call the function.
     *
     * Note that the array of arguments is not guaranteed to have
     * length greater than 0.
     *
     * @param cx the current Context for this thread
     * @param scope the scope to execute the function relative to. This is
     *              set to the value returned by getParentScope() except
     *              when the function is called from a closure.
     * @param thisObj the JavaScript <code>this</code> object
     * @param args the array of arguments
     * @return the result of the call
     */
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args);

    /**
     * Call the function as a constructor.
     *
     * This method is invoked by the runtime in order to satisfy a use
     * of the JavaScript <code>new</code> operator.  This method is
     * expected to create a new object and return it.
     *
     * @param cx the current Context for this thread
     * @param scope an enclosing scope of the caller except
     *              when the function is called from a closure.
     * @param args the array of arguments
     * @return the allocated object
     */
    public Scriptable construct(Context cx, Scriptable scope, Object[] args);
}

class CodeCoverCoverageCounter$2o08iygcpwmc6w269jo6ln6zucqhqpoea541 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmc6w269jo6ln6zucqhqpoea541 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2o08iygcpwmc6w269jo6ln6zucqhqpoea541 () {
    super("org.mozilla.javascript.RHINO-SRC-Function.java");
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Function.java");
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

