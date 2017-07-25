/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

final class InterpretedFunction extends NativeFunction implements Script
{
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.ping();
  }

    static final long serialVersionUID = 541475680333911468L;
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[1]++;
  }

    InterpreterData idata;
    SecurityController securityController;
    Object securityDomain;

    private InterpretedFunction(InterpreterData idata,
                                Object staticSecurityDomain)
    {
        this.idata = idata;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[2]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[3]++;

        // Always get Context from the current thread to
        // avoid security breaches via passing mangled Context instances
        // with bogus SecurityController
        Context cx = Context.getContext();
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[4]++;
        SecurityController sc = cx.getSecurityController();
        Object dynamicDomain;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sc != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[1]++;
            dynamicDomain = sc.getDynamicSecurityDomain(staticSecurityDomain);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[6]++;

        } else {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[2]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((staticSecurityDomain != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[3]++;
                throw new IllegalArgumentException();

            } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[4]++;}
            dynamicDomain = null;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[8]++;
        }

        this.securityController = sc;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[9]++;
        this.securityDomain = dynamicDomain;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[10]++;
    }

    private InterpretedFunction(InterpretedFunction parent, int index)
    {
        this.idata = parent.idata.itsNestedFunctions[index];
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[11]++;
        this.securityController = parent.securityController;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[12]++;
        this.securityDomain = parent.securityDomain;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[13]++;
    }

    /**
     * Create script from compiled bytecode.
     */
    static InterpretedFunction createScript(InterpreterData idata,
                                            Object staticSecurityDomain)
    {
        InterpretedFunction f;
        f = new InterpretedFunction(idata, staticSecurityDomain);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[14]++;
        return f;
    }

    /**
     * Create function compiled from Function(...) constructor.
     */
    static InterpretedFunction createFunction(Context cx,Scriptable scope,
                                              InterpreterData idata,
                                              Object staticSecurityDomain)
    {
        InterpretedFunction f;
        f = new InterpretedFunction(idata, staticSecurityDomain);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[15]++;
        f.initScriptFunction(cx, scope);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[16]++;
        return f;
    }

    /**
     * Create function embedded in script or another function.
     */
    static InterpretedFunction createFunction(Context cx, Scriptable scope,
                                              InterpretedFunction  parent,
                                              int index)
    {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[17]++;
        InterpretedFunction f = new InterpretedFunction(parent, index);
        f.initScriptFunction(cx, scope);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[18]++;
        return f;
    }


    @Override
    public String getFunctionName()
    {
        return (idata.itsName == null) ? "" : idata.itsName;
    }

    /**
     * Calls the function.
     * @param cx the current context
     * @param scope the scope used for the call
     * @param thisObj the value of "this"
     * @param args function arguments. Must not be null. You can use
     * {@link ScriptRuntime#emptyArgs} to pass empty arguments.
     * @return the result of the function call.
     */
    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ScriptRuntime.hasTopCall(cx)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[5]++;
            return ScriptRuntime.doTopCall(this, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[6]++;}
        return Interpreter.interpret(this, cx, scope, thisObj, args);
    }

    public Object exec(Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isScript()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[7]++;
            // Can only be applied to scripts
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[8]++;}
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ScriptRuntime.hasTopCall(cx)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[9]++;
            // It will go through "call" path. but they are equivalent
            return ScriptRuntime.doTopCall(
                this, cx, scope, scope, ScriptRuntime.emptyArgs);

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d.branches[10]++;}
        return Interpreter.interpret(
            this, cx, scope, scope, ScriptRuntime.emptyArgs);
    }

    public boolean isScript() {
        return idata.itsFunctionType == 0;
    }

    @Override
    public String getEncodedSource()
    {
        return Interpreter.getEncodedSource(idata);
    }

    @Override
    public DebuggableScript getDebuggableView()
    {
        return idata;
    }

    @Override
    public Object resumeGenerator(Context cx, Scriptable scope, int operation,
                                  Object state, Object value)
    {
        return Interpreter.resumeGenerator(cx, scope, operation, state, value);
    }

    @Override
    protected int getLanguageVersion()
    {
        return idata.languageVersion;
    }

    @Override
    protected int getParamCount()
    {
        return idata.argCount;
    }

    @Override
    protected int getParamAndVarCount()
    {
        return idata.argNames.length;
    }

    @Override
    protected String getParamOrVarName(int index)
    {
        return idata.argNames[index];
    }

    @Override
    protected boolean getParamOrVarConst(int index)
    {
        return idata.argIsConst[index];
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-InterpretedFunction.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojyegeptbrb9ymhpmkfais55l7cezi6t6ekzk19d () {
    super("org.mozilla.javascript.RHINO-SRC-InterpretedFunction.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-InterpretedFunction.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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


