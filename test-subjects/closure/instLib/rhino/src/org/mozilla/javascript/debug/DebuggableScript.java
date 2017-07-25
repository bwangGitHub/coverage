/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript.debug;

/**
 * This interface exposes debugging information from executable
 * code (either functions or top-level scripts).
 */
public interface DebuggableScript
{
    public boolean isTopLevel();

    /**
     * Returns true if this is a function, false if it is a script.
     */
    public boolean isFunction();

    /**
     * Get name of the function described by this script.
     * Return null or an empty string if this script is not a function.
     */
    public String getFunctionName();

    /**
     * Get number of declared parameters in the function.
     * Return 0 if this script is not a function.
     *
     * @see #getParamAndVarCount()
     * @see #getParamOrVarName(int index)
     */
    public int getParamCount();

    /**
     * Get number of declared parameters and local variables.
     * Return number of declared global variables if this script is not a
     * function.
     *
     * @see #getParamCount()
     * @see #getParamOrVarName(int index)
     */
    public int getParamAndVarCount();

    /**
     * Get name of a declared parameter or local variable.
     * <tt>index</tt> should be less then {@link #getParamAndVarCount()}.
     * If <tt>index&nbsp;&lt;&nbsp;{@link #getParamCount()}</tt>, return
     * the name of the corresponding parameter, otherwise return the name
     * of variable.
     * If this script is not function, return the name of the declared
     * global variable.
     */
    public String getParamOrVarName(int index);

    /**
     * Get the name of the source (usually filename or URL)
     * of the script.
     */
    public String getSourceName();

    /**
     * Returns true if this script or function were runtime-generated
     * from JavaScript using <tt>eval</tt> function or <tt>Function</tt>
     * or <tt>Script</tt> constructors.
     */
    public boolean isGeneratedScript();

    /**
     * Get array containing the line numbers that
     * that can be passed to <code>DebugFrame.onLineChange()<code>.
     * Note that line order in the resulting array is arbitrary
     */
    public int[] getLineNumbers();

    public int getFunctionCount();

    public DebuggableScript getFunction(int index);

    public DebuggableScript getParent();

}

class CodeCoverCoverageCounter$adralqrs9n89mg8nk2h549zxjli8wk2f87ei7o80h2momc0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nk2h549zxjli8wk2f87ei7o80h2momc0x ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$adralqrs9n89mg8nk2h549zxjli8wk2f87ei7o80h2momc0x () {
    super("org.mozilla.javascript.debug.RHINO-SRC-DebuggableScript.java");
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
    log.startNamedSection("org.mozilla.javascript.debug.RHINO-SRC-DebuggableScript.java");
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

