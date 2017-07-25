/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the Boolean native object.
 * See ECMA 15.6.
 */
final class NativeBoolean extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.ping();
  }

    static final long serialVersionUID = -3716996899943880933L;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[1]++;
  }

    private static final Object BOOLEAN_TAG = "Boolean";
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[3]++;
        NativeBoolean obj = new NativeBoolean(false);
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[4]++;
    }

    NativeBoolean(boolean b)
    {
        booleanValue = b;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[5]++;
    }

    @Override
    public String getClassName()
    {
        return "Boolean";
    }

    @Override
    public Object getDefaultValue(Class<?> typeHint) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        // This is actually non-ECMA, but will be proposed
        // as a change in round 2.
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((typeHint == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[1]++;
            return ScriptRuntime.wrapBoolean(booleanValue);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[2]++;}
        return super.getDefaultValue(typeHint);
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[7]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[3]++; arity=1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[8]++; s="constructor";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[9]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[10]++; break;
          case Id_toString:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[4]++;    arity=0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[11]++; s="toString";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[12]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[13]++;    break;
          case Id_toSource:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[5]++;    arity=0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[14]++; s="toSource";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[15]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[16]++;    break;
          case Id_valueOf:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[6]++;     arity=0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[17]++; s="valueOf";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[18]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[19]++;     break;
          default:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[7]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(BOOLEAN_TAG, id, s, arity);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[20]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((f.hasTag(BOOLEAN_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[8]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[9]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[22]++;
        int id = f.methodId();
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[10]++;
            boolean b;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[12]++;
                b = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[25]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[13]++;
                b = args[0] instanceof ScriptableObject &&
                        ((ScriptableObject) args[0]).avoidObjectDetection()
                    ? true
                    : ScriptRuntime.toBoolean(args[0]);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[26]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[14]++;
                // new Boolean(val) creates a new boolean object.
                return new NativeBoolean(b);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[15]++;}
            // Boolean(val) converts val to a boolean.
            return ScriptRuntime.wrapBoolean(b);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[11]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;

        // The rest of Boolean.prototype methods require thisObj to be Boolean

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeBoolean) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[16]++;
            throw incompatibleCallError(f);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[17]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[29]++;
        boolean value = ((NativeBoolean)thisObj).booleanValue;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[30]++;

        switch (id) {

          case Id_toString:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[18]++;
            return value ? "true" : "false";

          case Id_toSource:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[19]++;
            return value ? "(new Boolean(true))" : "(new Boolean(false))";

          case Id_valueOf:
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[20]++;
            return ScriptRuntime.wrapBoolean(value); default : CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[21]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:15:31 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[31]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[32]++; String X = null; int c;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[33]++;
            int s_length = s.length();
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[34]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((s_length==7) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[22]++; X="valueOf";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[35]++;id=Id_valueOf;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[36]++;
 }
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[23]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[37]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s_length==8) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[24]++;
                c=s.charAt(3);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[38]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[26]++; X="toSource";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[40]++;id=Id_toSource;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[41]++;
 }
                else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[27]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[42]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[28]++; X="toString";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[43]++;id=Id_toString;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[44]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[29]++;}
}

            }
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[25]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[45]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((s_length==11) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[30]++; X="constructor";
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[46]++;id=Id_constructor;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[47]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[31]++;}
}
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[32]++; id = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[49]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.branches[33]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[50]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor          = 1,
        Id_toString             = 2,
        Id_toSource             = 3,
        Id_valueOf              = 4,
        MAX_PROTOTYPE_ID        = 4;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35.statements[51]++;
  }

// #/string_id_map#

    private boolean booleanValue;
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35 ());
  }
    public static long[] statements = new long[52];
    public static long[] branches = new long[34];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeBoolean.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$11f1r6z5fa12k16lcp1tfucq14ypdoaup45cb8drjj35 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeBoolean.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 51; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 33; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeBoolean.java");
      for (int i = 1; i <= 51; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 33; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

