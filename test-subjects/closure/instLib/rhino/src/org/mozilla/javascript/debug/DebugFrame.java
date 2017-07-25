/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript.debug;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
Interface to implement if the application is interested in receiving debug
information during execution of a particular script or function.
*/
public interface DebugFrame {

/**
Called when execution is ready to start bytecode interpretation for entered a particular function or script.

@param cx current Context for this thread
@param activation the activation scope for the function or script.
@param thisObj value of the JavaScript <code>this</code> object
@param args the array of arguments
*/
    public void onEnter(Context cx, Scriptable activation,
                        Scriptable thisObj, Object[] args);
/**
Called when executed code reaches new line in the source.
@param cx current Context for this thread
@param lineNumber current line number in the script source
*/
    public void onLineChange(Context cx, int lineNumber);

/**
Called when thrown exception is handled by the function or script.
@param cx current Context for this thread
@param ex exception object
*/
    public void onExceptionThrown(Context cx, Throwable ex);

/**
Called when the function or script for this frame is about to return.
@param cx current Context for this thread
@param byThrow if true function will leave by throwing exception, otherwise it
       will execute normal return
@param resultOrException function result in case of normal return or
       exception object if about to throw exception
*/
    public void onExit(Context cx, boolean byThrow, Object resultOrException);

/**
Called when the function or script executes a 'debugger' statement.
@param cx current Context for this thread
*/
    public void onDebuggerStatement(Context cx);
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw535k399u12zqzifu5zebty9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw535k399u12zqzifu5zebty9 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw535k399u12zqzifu5zebty9 () {
    super("org.mozilla.javascript.debug.RHINO-SRC-DebugFrame.java");
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
    log.startNamedSection("org.mozilla.javascript.debug.RHINO-SRC-DebugFrame.java");
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

