/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.*;

public final class OptRuntime extends ScriptRuntime
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.ping();
  }


    public static final Double zeroObj = new Double(0.0);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[1]++;
  }
    public static final Double oneObj = new Double(1.0);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[2]++;
  }
    public static final Double minusOneObj = new Double(-1.0);
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[3]++;
  }

    /**
     * Implement ....() call shrinking optimizer code.
     */
    public static Object call0(Callable fun, Scriptable thisObj,
                               Context cx, Scriptable scope)
    {
        return fun.call(cx, scope, thisObj, ScriptRuntime.emptyArgs);
    }

    /**
     * Implement ....(arg) call shrinking optimizer code.
     */
    public static Object call1(Callable fun, Scriptable thisObj, Object arg0,
                               Context cx, Scriptable scope)
    {
        return fun.call(cx, scope, thisObj, new Object[] { arg0 } );
    }

    /**
     * Implement ....(arg0, arg1) call shrinking optimizer code.
     */
    public static Object call2(Callable fun, Scriptable thisObj,
                               Object arg0, Object arg1,
                               Context cx, Scriptable scope)
    {
        return fun.call(cx, scope, thisObj, new Object[] { arg0, arg1 });
    }

    /**
     * Implement ....(arg0, arg1, ...) call shrinking optimizer code.
     */
    public static Object callN(Callable fun, Scriptable thisObj,
                               Object[] args,
                               Context cx, Scriptable scope)
    {
        return fun.call(cx, scope, thisObj, args);
    }

    /**
     * Implement name(args) call shrinking optimizer code.
     */
    public static Object callName(Object[] args, String name,
                                  Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[4]++;
        Callable f = getNameFunctionAndThis(name, cx, scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[5]++;
        Scriptable thisObj = lastStoredScriptable(cx);
        return f.call(cx, scope, thisObj, args);
    }

    /**
     * Implement name() call shrinking optimizer code.
     */
    public static Object callName0(String name,
                                   Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[6]++;
        Callable f = getNameFunctionAndThis(name, cx, scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[7]++;
        Scriptable thisObj = lastStoredScriptable(cx);
        return f.call(cx, scope, thisObj, ScriptRuntime.emptyArgs);
    }

    /**
     * Implement x.property() call shrinking optimizer code.
     */
    public static Object callProp0(Object value, String property,
                                   Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[8]++;
        Callable f = getPropFunctionAndThis(value, property, cx, scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[9]++;
        Scriptable thisObj = lastStoredScriptable(cx);
        return f.call(cx, scope, thisObj, ScriptRuntime.emptyArgs);
    }

    public static Object add(Object val1, double val2)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((val1 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[1]++;
            val1 = ((Scriptable) val1).getDefaultValue(null);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[11]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[2]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((val1 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[3]++;
            return wrapDouble(toNumber(val1) + val2);
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[4]++;}
        return new ConsString((CharSequence)val1, toString(val2));
    }

    public static Object add(double val1, Object val2)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((val2 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[5]++;
            val2 = ((Scriptable) val2).getDefaultValue(null);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[14]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[6]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((val2 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[7]++;
            return wrapDouble(toNumber(val2) + val1);
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[8]++;}
        return new ConsString(toString(val1), (CharSequence)val2);
    }

    public static Object elemIncrDecr(Object obj, double index,
                                      Context cx, int incrDecrMask)
    {
        return ScriptRuntime.elemIncrDecr(obj, new Double(index), cx,
                                          incrDecrMask);
    }

    public static Object[] padStart(Object[] currentArgs, int count) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[16]++;
        Object[] result = new Object[currentArgs.length + count];
        System.arraycopy(currentArgs, 0, result, count, currentArgs.length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[17]++;
        return result;
    }

    public static void initFunction(NativeFunction fn, int functionType,
                                    Scriptable scope, Context cx)
    {
        ScriptRuntime.initFunction(cx, scope, fn, functionType, false);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[18]++;
    }

    public static Object callSpecial(Context cx, Callable fun,
                                     Scriptable thisObj, Object[] args,
                                     Scriptable scope,
                                     Scriptable callerThis, int callType,
                                     String fileName, int lineNumber)
    {
        return ScriptRuntime.callSpecial(cx, fun, thisObj, args, scope,
                                         callerThis, callType,
                                         fileName, lineNumber);
    }

    public static Object newObjectSpecial(Context cx, Object fun,
                                          Object[] args, Scriptable scope,
                                          Scriptable callerThis, int callType)
    {
        return ScriptRuntime.newSpecial(cx, fun, args, scope, callType);
    }

    public static Double wrapDouble(double num)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((num == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[9]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((1 / num > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[11]++;
                // +0.0
                return zeroObj;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[12]++;}

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[10]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[21]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((num == 1.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[13]++;
            return oneObj;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[14]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[22]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((num == -1.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[15]++;
            return minusOneObj;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[16]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[23]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((num != num) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[17]++;
            return NaNobj;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[18]++;}
}
}
}
        return new Double(num);
    }

    static String encodeIntArray(int[] array)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[24]++;
int CodeCoverConditionCoverageHelper_C10;
        // XXX: this extremely inefficient for small integers
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((array == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[19]++; return null;
 } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[20]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[25]++;
        int n = array.length;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[26]++;
        char[] buffer = new char[1 + n * 2];
        buffer[0] = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[27]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i != n) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[29]++;
            int value = array[i];
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[30]++;
            int shift = 1 + i * 2;
            buffer[shift] = (char)(value >>> 16);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[31]++;
            buffer[shift + 1] = (char)value;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[32]++;
        }
        return new String(buffer);
    }

    private static int[] decodeIntArray(String str, int arraySize)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[33]++;
int CodeCoverConditionCoverageHelper_C12;
        // XXX: this extremely inefficient for small integers
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((arraySize == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[21]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[34]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((str != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[23]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[24]++;}
            return null;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[22]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((str.length() != 1 + arraySize * 2) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((str.charAt(0) != 1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[25]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[26]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[36]++;
        int[] array = new int[arraySize];
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[37]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[4]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i != arraySize) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[38]++;
            int shift = 1 + i * 2;
            array[i] = (str.charAt(shift) << 16) | str.charAt(shift + 1);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[39]++;
        }
        return array;
    }

    public static Scriptable newArrayLiteral(Object[] objects,
                                             String encodedInts,
                                             int skipCount,
                                             Context cx,
                                             Scriptable scope)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[40]++;
        int[] skipIndexces = decodeIntArray(encodedInts, skipCount);
        return newArrayLiteral(objects, skipIndexces, cx, scope);
    }

    public static void main(final Script script, final String[] args)
    {
        ContextFactory.getGlobal().call(new ContextAction() {
            public Object run(Context cx)
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[42]++;
                ScriptableObject global = getGlobal(cx);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[43]++;

                // get the command line arguments and define "arguments"
                // array in the top-level object
                Object[] argsCopy = new Object[args.length];
                System.arraycopy(args, 0, argsCopy, 0, args.length);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[44]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[45]++;
                Scriptable argsObj = cx.newArray(global, argsCopy);
                global.defineProperty("arguments", argsObj,
                                      ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[46]++;
                script.exec(cx, global);
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[47]++;
                return null;
            }
        });
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[41]++;
    }

    public static void throwStopIteration(Object obj) {
        throw new JavaScriptException(
            NativeIterator.getStopIterationObject((Scriptable)obj), "", 0);
    }

    public static Scriptable createNativeGenerator(NativeFunction funObj,
                                                   Scriptable scope,
                                                   Scriptable thisObj,
                                                   int maxLocals,
                                                   int maxStack)
    {
        return new NativeGenerator(scope, funObj,
                new GeneratorState(thisObj, maxLocals, maxStack));
    }

    public static Object[] getGeneratorStackState(Object obj) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[48]++;
        GeneratorState rgs = (GeneratorState) obj;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[49]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((rgs.stackState == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[27]++;
            rgs.stackState = new Object[rgs.maxStack];
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[50]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[28]++;}
        return rgs.stackState;
    }

    public static Object[] getGeneratorLocalsState(Object obj) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[51]++;
        GeneratorState rgs = (GeneratorState) obj;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[52]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((rgs.localsState == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[29]++;
            rgs.localsState = new Object[rgs.maxLocals];
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[53]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.branches[30]++;}
        return rgs.localsState;
    }

    public static class GeneratorState {
        static final String CLASS_NAME =
            "org/mozilla/javascript/optimizer/OptRuntime$GeneratorState";

        public int resumptionPoint;
        static final String resumptionPoint_NAME = "resumptionPoint";
        static final String resumptionPoint_TYPE = "I";

        public Scriptable thisObj;
        static final String thisObj_NAME = "thisObj";
        static final String thisObj_TYPE =
            "Lorg/mozilla/javascript/Scriptable;";

        Object[] stackState;
        Object[] localsState;
        int maxLocals;
        int maxStack;

        GeneratorState(Scriptable thisObj, int maxLocals, int maxStack) {
            this.thisObj = thisObj;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[54]++;
            this.maxLocals = maxLocals;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[55]++;
            this.maxStack = maxStack;
CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9.statements[56]++;
        }
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9 ());
  }
    public static long[] statements = new long[57];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.optimizer.RHINO-SRC-OptRuntime.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw8eb1vg0nyz5kch9kkp5mjq9 () {
    super("org.mozilla.javascript.optimizer.RHINO-SRC-OptRuntime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 56; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.optimizer.RHINO-SRC-OptRuntime.java");
      for (int i = 1; i <= 56; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

