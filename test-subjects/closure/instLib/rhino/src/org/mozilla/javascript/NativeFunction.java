/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

/**
 * This class implements the Function native object.
 * See ECMA 15.3.
 */
public abstract class NativeFunction extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.ping();
  }


    static final long serialVersionUID = 8713897114082216401L;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[1]++;
  }

    public final void initScriptFunction(Context cx, Scriptable scope)
    {
        ScriptRuntime.setFunctionProtoAndParent(this, scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[2]++;
    }

    /**
     * @param indent How much to indent the decompiled result
     *
     * @param flags Flags specifying format of decompilation output
     */
    @Override
    final String decompile(int indent, int flags)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[3]++;
        String encodedSource = getEncodedSource();
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((encodedSource == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.branches[1]++;
            return super.decompile(indent, flags);

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.branches[2]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[5]++;
            UintMap properties = new UintMap(1);
            properties.put(Decompiler.INITIAL_INDENT_PROP, indent);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[6]++;
            return Decompiler.decompile(encodedSource, flags, properties);
        }
    }

    @Override
    public int getLength()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[7]++;
        int paramCount = getParamCount();
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getLanguageVersion() != Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.branches[3]++;
            return paramCount;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.branches[4]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[9]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[10]++;
        NativeCall activation = ScriptRuntime.findFunctionActivation(cx, this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((activation == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.branches[5]++;
            return paramCount;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01.branches[6]++;}
        return activation.originalArgs.length;
    }

    @Override
    public int getArity()
    {
        return getParamCount();
    }

    /**
     * @deprecated Use {@link BaseFunction#getFunctionName()} instead.
     * For backwards compatibility keep an old method name used by
     * Batik and possibly others.
     */
    public String jsGet_name()
    {
        return getFunctionName();
    }

    /**
     * Get encoded source string.
     */
    public String getEncodedSource()
    {
        return null;
    }

    public DebuggableScript getDebuggableView()
    {
        return null;
    }

    /**
     * Resume execution of a suspended generator.
     * @param cx The current context
     * @param scope Scope for the parent generator function
     * @param operation The resumption operation (next, send, etc.. )
     * @param state The generator state (has locals, stack, etc.)
     * @param value The return value of yield (if required).
     * @return The next yielded value (if any)
     */
    public Object resumeGenerator(Context cx, Scriptable scope,
                                  int operation, Object state, Object value)
    {
        throw new EvaluatorException("resumeGenerator() not implemented");
    }


    protected abstract int getLanguageVersion();

    /**
     * Get number of declared parameters. It should be 0 for scripts.
     */
    protected abstract int getParamCount();

    /**
     * Get number of declared parameters and variables defined through var
     * statements.
     */
    protected abstract int getParamAndVarCount();

    /**
     * Get parameter or variable name.
     * If <tt>index < {@link #getParamCount()}</tt>, then return the name of the
     * corresponding parameter. Otherwise return the name of variable.
     */
    protected abstract String getParamOrVarName(int index);

    /**
     * Get parameter or variable const-ness.
     * If <tt>index < {@link #getParamCount()}</tt>, then return the const-ness
     * of the corresponding parameter. Otherwise return whether the variable is
     * const.
     */
    protected boolean getParamOrVarConst(int index)
    {
        // By default return false to preserve compatibility with existing
        // classes subclassing this class, which are mostly generated by jsc
        // from earlier Rhino versions. See Bugzilla #396117.
        return false;
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01 ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeFunction.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevua4xcnuh445p2927tobjv9zr91i01 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeFunction.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeFunction.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

