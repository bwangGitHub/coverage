/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * The JavaScript Script object.
 *
 * Note that the C version of the engine uses XDR as the format used
 * by freeze and thaw. Since this depends on the internal format of
 * structures in the C runtime, we cannot duplicate it.
 *
 * Since we cannot replace 'this' as a result of the compile method,
 * will forward requests to execute to the nonnull 'script' field.
 *
 * @since 1.3
 */

class NativeScript extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.ping();
  }

    static final long serialVersionUID = -6795101161980121700L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[1]++;
  }

    private static final Object SCRIPT_TAG = "Script";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[3]++;
        NativeScript obj = new NativeScript(null);
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[4]++;
    }

    private NativeScript(Script script)
    {
        this.script = script;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[5]++;
    }

    /**
     * Returns the name of this JavaScript class, "Script".
     */
    @Override
    public String getClassName()
    {
        return "Script";
    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[1]++;
            return script.exec(cx, scope);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[2]++;}
        return Undefined.instance;
    }

    @Override
    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
        throw Context.reportRuntimeError0("msg.script.is.not.constructor");
    }

    @Override
    public int getLength()
    {
        return 0;
    }

    @Override
    public int getArity()
    {
        return 0;
    }

    @Override
    String decompile(int indent, int flags)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((script instanceof NativeFunction) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[3]++;
            return ((NativeFunction)script).decompile(indent, flags);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[4]++;}
        return super.decompile(indent, flags);
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[8]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[5]++; arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[9]++; s="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[10]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[11]++; break;
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[6]++;    arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[12]++; s="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[13]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[14]++;    break;
          case Id_exec:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[7]++;        arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[15]++; s="exec";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[16]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[17]++;        break;
          case Id_compile:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[8]++;     arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[18]++; s="compile";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[19]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[20]++;     break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[9]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(SCRIPT_TAG, id, s, arity);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[21]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((f.hasTag(SCRIPT_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[10]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[11]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[23]++;
        int id = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[24]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[12]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[25]++;
            String source = (args.length == 0)
                            ? ""
                            : ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[26]++;
            Script script = compile(cx, source);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[27]++;
            NativeScript nscript = new NativeScript(script);
            ScriptRuntime.setObjectProtoAndParent(nscript, scope);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[28]++;
            return nscript;
          }

          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[13]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[29]++;
            NativeScript real = realThis(thisObj, f);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[30]++;
            Script realScript = real.script;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((realScript == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[14]++; return "";
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[15]++;}
            return cx.decompileScript(realScript, 0);
          }

          case Id_exec:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[16]++; {
            throw Context.reportRuntimeError1(
                "msg.cant.call.indirect", "exec");
          }

          case Id_compile:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[17]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[32]++;
            NativeScript real = realThis(thisObj, f);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[33]++;
            String source = ScriptRuntime.toString(args, 0);
            real.script = compile(cx, source);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[34]++;
            return real;
          } default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[18]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static NativeScript realThis(Scriptable thisObj, IdFunctionObject f)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeScript) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[19]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[20]++;}
        return (NativeScript)thisObj;
    }

    private static Script compile(Context cx, String source)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[36]++;
        int[] linep = { 0 };
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[37]++;
        String filename = Context.getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[21]++;
            filename = "<Script object>";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[39]++;
            linep[0] = 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[40]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[22]++;}
        ErrorReporter reporter;
        reporter = DefaultErrorReporter.forEval(cx.getErrorReporter());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[41]++;
        return cx.compileString(source, null, reporter, filename,
                                linep[0], null);
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:16:01 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[42]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[43]++; String X = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[44]++;
            L: switch (s.length()) {
            case 4:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[23]++; X="exec";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[45]++;id=Id_exec;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[46]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[47]++; break L;
            case 7:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[24]++; X="compile";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[48]++;id=Id_compile;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[49]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[50]++; break L;
            case 8:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[25]++; X="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[51]++;id=Id_toString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[52]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[53]++; break L;
            case 11:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[26]++; X="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[54]++;id=Id_constructor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[55]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[56]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[27]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[57]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[28]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[58]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.branches[29]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[59]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor    = 1,
        Id_toString       = 2,
        Id_compile        = 3,
        Id_exec           = 4,
        MAX_PROTOTYPE_ID  = 4;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h.statements[60]++;
  }

// #/string_id_map#

    private Script script;
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h ());
  }
    public static long[] statements = new long[61];
    public static long[] branches = new long[30];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeScript.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,3};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbyefwidg35gkgws4h () {
    super("org.mozilla.javascript.RHINO-SRC-NativeScript.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 60; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 29; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeScript.java");
      for (int i = 1; i <= 60; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 29; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

