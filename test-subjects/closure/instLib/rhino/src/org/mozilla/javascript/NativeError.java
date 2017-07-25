/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript;

/**
 *
 * The class of error objects
 *
 *  ECMA 15.11
 */
final class NativeError extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.ping();
  }

    static final long serialVersionUID = -5338413581437645187L;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[1]++;
  }

    private static final Object ERROR_TAG = "Error";
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[2]++;
  }

    private RhinoException stackProvider;

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[3]++;
        NativeError obj = new NativeError();
        ScriptableObject.putProperty(obj, "name", "Error");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[4]++;
        ScriptableObject.putProperty(obj, "message", "");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[5]++;
        ScriptableObject.putProperty(obj, "fileName", "");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[6]++;
        ScriptableObject.putProperty(obj, "lineNumber", Integer.valueOf(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[7]++;
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[8]++;
    }

    static NativeError make(Context cx, Scriptable scope,
                            IdFunctionObject ctorObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[9]++;
        Scriptable proto = (Scriptable)(ctorObj.get("prototype", ctorObj));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[10]++;

        NativeError obj = new NativeError();
        obj.setPrototype(proto);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[11]++;
        obj.setParentScope(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[12]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[13]++;

        int arglen = args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((arglen >= 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[1]++;
            ScriptableObject.putProperty(obj, "message",
                    ScriptRuntime.toString(args[0]));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[15]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arglen >= 2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[3]++;
                ScriptableObject.putProperty(obj, "fileName", args[1]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[17]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((arglen >= 3) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[5]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[19]++;
                    int line = ScriptRuntime.toInt32(args[2]);
                    ScriptableObject.putProperty(obj, "lineNumber",
                            Integer.valueOf(line));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[20]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[6]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[2]++;}
        return obj;
    }

    @Override
    public String getClassName()
    {
        return "Error";
    }

    @Override
    public String toString()
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[21]++;
        // According to spec, Error.prototype.toString() may return undefined.
        Object toString =  js_toString(this);
        return toString instanceof String ? (String) toString : super.toString();
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[22]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[7]++; arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[23]++; s="constructor";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[24]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[25]++; break;
          case Id_toString:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[8]++;    arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[26]++; s="toString";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[27]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[28]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[9]++;    arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[29]++; s="toSource";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[30]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[31]++;    break;
          default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[10]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(ERROR_TAG, id, s, arity);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[32]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((f.hasTag(ERROR_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[11]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[12]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[34]++;
        int id = f.methodId();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[35]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[13]++;
            return make(cx, scope, f, args);

          case Id_toString:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[14]++;
            return js_toString(thisObj);

          case Id_toSource:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[15]++;
            return js_toSource(cx, scope, thisObj); default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[16]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    public void setStackProvider(RhinoException re) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;
        // We go some extra miles to make sure the stack property is only
        // generated on demand, is cached after the first access, and is
        // overwritable like an ordinary property. Hence this setup with
        // the getter and setter below.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((stackProvider == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[17]++;
            stackProvider = re;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[38]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                defineProperty("stack", null,
                        NativeError.class.getMethod("getStack"),
                        NativeError.class.getMethod("setStack", Object.class), 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[39]++;
            } catch (NoSuchMethodException nsm) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[20]++;
                // should not happen
                throw new RuntimeException(nsm);
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[19]++;
}
  }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[18]++;}
    }

    public Object getStack() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[40]++;
        Object value =  stackProvider == null ?
                NOT_FOUND : stackProvider.getScriptStackTrace();
        // We store the stack as local property both to cache it
        // and to make the property writable
        setStack(value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[41]++;
        return value;
    }

    public void setStack(Object value) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((stackProvider != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[21]++;
            stackProvider = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[43]++;
            delete("stack");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[44]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[22]++;}
        put("stack", this, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[45]++;
    }

    private static Object js_toString(Scriptable thisObj) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[46]++;
        Object name = ScriptableObject.getProperty(thisObj, "name");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((name == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((name == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[23]++;
            name = "Error";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[48]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[24]++;
            name = ScriptRuntime.toString(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[49]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[50]++;
        Object msg = ScriptableObject.getProperty(thisObj, "message");
        final Object result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((msg == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((msg == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[25]++;
            result = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[52]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[26]++;
            result = ((String) name) + ": " + ScriptRuntime.toString(msg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[53]++;
        }
        return result;
    }

    private static String js_toSource(Context cx, Scriptable scope,
                                      Scriptable thisObj)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[54]++;
        // Emulation of SpiderMonkey behavior
        Object name = ScriptableObject.getProperty(thisObj, "name");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[55]++;
        Object message = ScriptableObject.getProperty(thisObj, "message");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[56]++;
        Object fileName = ScriptableObject.getProperty(thisObj, "fileName");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[57]++;
        Object lineNumber = ScriptableObject.getProperty(thisObj, "lineNumber");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[58]++;

        StringBuffer sb = new StringBuffer();
        sb.append("(new ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[59]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[60]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((name == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[27]++;
            name = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[61]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[28]++;}
        sb.append(ScriptRuntime.toString(name));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[62]++;
        sb.append("(");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[63]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[64]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((message != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((fileName != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((lineNumber != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[29]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[65]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((message == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[31]++;
                message = "";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[66]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[32]++;}
            sb.append(ScriptRuntime.uneval(cx, scope, message));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[67]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((fileName != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lineNumber != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[33]++;
                sb.append(", ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[69]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[70]++;
int CodeCoverConditionCoverageHelper_C13;
                if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((fileName == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[35]++;
                    fileName = "";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[71]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[36]++;}
                sb.append(ScriptRuntime.uneval(cx, scope, fileName));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[72]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[73]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lineNumber != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[74]++;
                    int line = ScriptRuntime.toInt32(lineNumber);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
                    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((line != 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[39]++;
                        sb.append(", ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[76]++;
                        sb.append(ScriptRuntime.toString(line));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[77]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[40]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[34]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[30]++;}
        sb.append("))");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[78]++;
        return sb.toString();
    }

    private static String getString(Scriptable obj, String id)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[79]++;
        Object value = ScriptableObject.getProperty(obj, id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[80]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[41]++; return "";
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[42]++;}
        return ScriptRuntime.toString(value);
    }

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #string_id_map#
// #generated# Last update: 2007-05-09 08:15:45 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[81]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[82]++; String X = null; int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[83]++;
            int s_length = s.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[84]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[43]++;
                c=s.charAt(3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[85]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[86]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[45]++; X="toSource";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[87]++;id=Id_toSource;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[88]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[46]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[89]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[47]++; X="toString";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[90]++;id=Id_toString;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[91]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[48]++;}
}

            }
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[44]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[92]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[49]++; X="constructor";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[93]++;id=Id_constructor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[94]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[50]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[95]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[51]++; id = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[96]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.branches[52]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[97]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor    = 1,
        Id_toString       = 2,
        Id_toSource       = 3,

        MAX_PROTOTYPE_ID  = 3;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox.statements[98]++;
  }

// #/string_id_map#
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox ());
  }
    public static long[] statements = new long[99];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeError.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,2,1,3,1,2,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh4sybp0ls2eor2ox () {
    super("org.mozilla.javascript.RHINO-SRC-NativeError.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 98; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeError.java");
      for (int i = 1; i <= 98; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

