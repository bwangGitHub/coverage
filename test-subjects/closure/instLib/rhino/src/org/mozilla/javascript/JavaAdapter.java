/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.classfile.*;
import java.lang.reflect.*;
import java.io.*;
import java.security.*;
import java.util.*;

public final class JavaAdapter implements IdFunctionCall
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.ping();
  }

    /**
     * Provides a key with which to distinguish previously generated
     * adapter classes stored in a hash table.
     */
    static class JavaAdapterSignature
    {
        Class<?> superClass;
        Class<?>[] interfaces;
        ObjToIntMap names;

        JavaAdapterSignature(Class<?> superClass, Class<?>[] interfaces,
                             ObjToIntMap names)
        {
            this.superClass = superClass;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[1]++;
            this.interfaces = interfaces;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[2]++;
            this.names = names;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[3]++;
        }

        @Override
        public boolean equals(Object obj)
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj instanceof JavaAdapterSignature) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[1]++;
                return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[2]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[5]++;
            JavaAdapterSignature sig = (JavaAdapterSignature) obj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((superClass != sig.superClass) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[3]++;
                return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[4]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((interfaces != sig.interfaces) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[5]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((interfaces.length != sig.interfaces.length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[7]++;
                    return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[8]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < interfaces.length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[3]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[10]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((interfaces[i] != sig.interfaces[i]) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[9]++;
                        return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[10]++;}
  }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[6]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[11]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((names.size() != sig.names.size()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[11]++;
                return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[12]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[12]++;
            ObjToIntMap.Iterator iter = new ObjToIntMap.Iterator(names);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
            for (iter.start();(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((iter.done()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); iter.next()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[6]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[14]++;
                String name = (String)iter.getKey();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[15]++;
                int arity = iter.getValue();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[16]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((arity != sig.names.get(name, arity + 1)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[13]++;
                    return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[14]++;}
            }
            return true;
        }

        @Override
        public int hashCode()
        {
            return (superClass.hashCode() + Arrays.hashCode(interfaces)) ^ names.size();
        }
    }

    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[17]++;
        JavaAdapter obj = new JavaAdapter();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[18]++;
        IdFunctionObject ctor = new IdFunctionObject(obj, FTAG, Id_JavaAdapter,
                                                     "JavaAdapter", 1, scope);
        ctor.markAsConstructor(null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[19]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[20]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[15]++;
            ctor.sealObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[21]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[16]++;}
        ctor.exportAsScopeProperty();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[22]++;
    }

    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[23]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((f.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[17]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[24]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((f.methodId() == Id_JavaAdapter) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[19]++;
                return js_createAdapter(cx, scope, args);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[20]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[18]++;}
        throw f.unknown();
    }

    public static Object convertResult(Object result, Class<?> c)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[25]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((result == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((c != ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c != ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[21]++;
            // Avoid an error for an undefined value; return null instead.
            return null;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[22]++;}
        return Context.jsToJava(result, c);
    }

    public static Scriptable createAdapterWrapper(Scriptable obj, Object adapter)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[26]++;
        Scriptable scope = ScriptableObject.getTopLevelScope(obj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[27]++;
        NativeJavaObject res = new NativeJavaObject(scope, adapter, null, true);
        res.setPrototype(obj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[28]++;
        return res;
    }

    public static Object getAdapterSelf(Class<?> adapterClass, Object adapter)
        throws NoSuchFieldException, IllegalAccessException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[29]++;
        Field self = adapterClass.getDeclaredField("self");
        return self.get(adapter);
    }

    static Object js_createAdapter(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[30]++;
        int N = args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[31]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[23]++;
            throw ScriptRuntime.typeError0("msg.adapter.zero.args");

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[24]++;}

        // Expected arguments:
        // Any number of NativeJavaClass objects representing the super-class
        // and/or interfaces to implement, followed by one NativeObject providing
        // the implementation, followed by any number of arguments to pass on
        // to the (super-class) constructor.

        int classCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[7]++;


int CodeCoverConditionCoverageHelper_C15;
        for (classCount = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((classCount < N - 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); classCount++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[9]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[33]++;
            Object arg = args[classCount];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[34]++;
int CodeCoverConditionCoverageHelper_C16;
            // We explicitly test for NativeObject here since checking for
            // instanceof ScriptableObject or !(instanceof NativeJavaClass)
            // would fail for a Java class that isn't found in the class path
            // as NativeJavaPackage extends ScriptableObject.
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((arg instanceof NativeObject) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[25]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[35]++;
                break;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[26]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[36]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((arg instanceof NativeJavaClass) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[27]++;
                throw ScriptRuntime.typeError2("msg.not.java.class.arg",
                                               String.valueOf(classCount),
                                               ScriptRuntime.toString(arg));

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[28]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[37]++;
        Class<?> superClass = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[38]++;
        Class<?>[] intfs = new Class[classCount];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[39]++;
        int interfaceCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[40]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[10]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < classCount) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[10]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[11]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[12]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[41]++;
            Class<?> c = ((NativeJavaClass) args[i]).getClassObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[42]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[29]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[43]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((superClass != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[31]++;
                    throw ScriptRuntime.typeError2("msg.only.one.super",
                              superClass.getName(), c.getName());

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[32]++;}
                superClass = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[44]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[30]++;
                intfs[interfaceCount++] = c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[45]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[46]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((superClass == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[33]++;
            superClass = ScriptRuntime.ObjectClass;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[47]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[34]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[48]++;
        
        Class<?>[] interfaces = new Class[interfaceCount];
        System.arraycopy(intfs, 0, interfaces, 0, interfaceCount);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[49]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[50]++;
        // next argument is implementation, must be scriptable
        Scriptable obj = ScriptableObject.ensureScriptable(args[classCount]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[51]++;

        Class<?> adapterClass = getAdapterClass(scope, superClass, interfaces, obj);
        Object adapter;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[52]++;
        
        int argsCount = N - classCount - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[53]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[54]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((argsCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[36]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[55]++;
                // Arguments contain parameters for super-class constructor.
                // We use the generic Java method lookup logic to find and
                // invoke the right constructor.
                Object[] ctorArgs = new Object[argsCount + 2];
                ctorArgs[0] = obj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[56]++;
                ctorArgs[1] = cx.getFactory();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[57]++;
                System.arraycopy(args, classCount + 1, ctorArgs, 2, argsCount);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[58]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[59]++;
                // TODO: cache class wrapper?
                NativeJavaClass classWrapper = new NativeJavaClass(scope,
                        adapterClass, true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[60]++;
                NativeJavaMethod ctors = classWrapper.members.ctors;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[61]++;
                int index = ctors.findCachedFunction(cx, ctorArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[62]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[38]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[63]++;
                    String sig = NativeJavaMethod.scriptSignature(args);
                    throw Context.reportRuntimeError2(
                            "msg.no.java.ctor", adapterClass.getName(), sig);

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[39]++;}

                // Found the constructor, so try invoking it.
                adapter = NativeJavaClass.constructInternal(ctorArgs, ctors.methods[index]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[64]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[65]++;
                Class<?>[] ctorParms = {
                        ScriptRuntime.ScriptableClass,
                        ScriptRuntime.ContextFactoryClass
                };
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[66]++;
                Object[] ctorArgs = { obj, cx.getFactory() };
                adapter = adapterClass.getConstructor(ctorParms).newInstance(ctorArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[67]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[68]++;

            Object self = getAdapterSelf(adapterClass, adapter);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[69]++;
int CodeCoverConditionCoverageHelper_C24;
            // Return unwrapped JavaAdapter if it implements Scriptable
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((self instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[40]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[70]++;
                Object unwrapped = ((Wrapper) self).unwrap();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[71]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((unwrapped instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[42]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[72]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((unwrapped instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[44]++;
                        ScriptRuntime.setObjectProtoAndParent(
                                (ScriptableObject)unwrapped, scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[73]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[45]++;}
                    return unwrapped;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[43]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[41]++;}
            return self;
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[46]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[35]++;
}
  }
    }

    // Needed by NativeJavaObject serializer
    public static void writeAdapterObject(Object javaObject,
                                          ObjectOutputStream out)
        throws IOException
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[74]++;
        Class<?> cl = javaObject.getClass();
        out.writeObject(cl.getSuperclass().getName());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[75]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[76]++;

        Class<?>[] interfaces = cl.getInterfaces();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[77]++;
        String[] interfaceNames = new String[interfaces.length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[78]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;

        for (int i=0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < interfaces.length) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[13]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[14]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[15]++;
}
            interfaceNames[i] = interfaces[i].getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[79]++;
  }

        out.writeObject(interfaceNames);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[80]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[81]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[82]++;
            Object delegee = cl.getField("delegee").get(javaObject);
            out.writeObject(delegee);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[83]++;
            return;
        } catch (IllegalAccessException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[48]++;
        } catch (NoSuchFieldException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[49]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[47]++;
}
  }
        throw new IOException();
    }

    // Needed by NativeJavaObject de-serializer
    public static Object readAdapterObject(Scriptable self,
                                           ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        ContextFactory factory;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[84]++;
        Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[85]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[50]++;
            factory = cx.getFactory();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[86]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[51]++;
            factory = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[87]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[88]++;

        Class<?> superClass = Class.forName((String)in.readObject());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[89]++;

        String[] interfaceNames = (String[])in.readObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[90]++;
        Class<?>[] interfaces = new Class[interfaceNames.length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[91]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[16]++;


int CodeCoverConditionCoverageHelper_C29;

        for (int i=0;(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((i < interfaceNames.length) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[16]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[17]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[18]++;
}
            interfaces[i] = Class.forName(interfaceNames[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[92]++;
  }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[93]++;

        Scriptable delegee = (Scriptable)in.readObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[94]++;

        Class<?> adapterClass = getAdapterClass(self, superClass, interfaces,
                                             delegee);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[95]++;

        Class<?>[] ctorParms = {
            ScriptRuntime.ContextFactoryClass,
            ScriptRuntime.ScriptableClass,
            ScriptRuntime.ScriptableClass
        };
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[96]++;
        Object[] ctorArgs = { factory, delegee, self };
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[97]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            return adapterClass.getConstructor(ctorParms).newInstance(ctorArgs);
        } catch(InstantiationException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[53]++;
        } catch(IllegalAccessException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[54]++;
        } catch(InvocationTargetException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[55]++;
        } catch(NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[56]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[52]++;
}
  }

        throw new ClassNotFoundException("adapter");
    }

    private static ObjToIntMap getObjectFunctionNames(Scriptable obj)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[98]++;
        Object[] ids = ScriptableObject.getPropertyIds(obj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[99]++;
        ObjToIntMap map = new ObjToIntMap(ids.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[100]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[19]++;


int CodeCoverConditionCoverageHelper_C30;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i != ids.length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[19]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[20]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[21]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[101]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ids[i] instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[57]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[102]++;
                continue;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[58]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[103]++;
            String id = (String) ids[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[104]++;
            Object value = ScriptableObject.getProperty(obj, id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[105]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((value instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[59]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[106]++;
                Function f = (Function)value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[107]++;
                int length = ScriptRuntime.toInt32(
                                 ScriptableObject.getProperty(f, "length"));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[108]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((length < 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[61]++;
                    length = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[109]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[62]++;}
                map.put(id, length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[110]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[60]++;}
        }
        return map;
    }

    private static Class<?> getAdapterClass(Scriptable scope, Class<?> superClass,
                                            Class<?>[] interfaces, Scriptable obj)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[111]++;
        ClassCache cache = ClassCache.get(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[112]++;
        Map<JavaAdapterSignature,Class<?>> generated
            = cache.getInterfaceAdapterCacheMap();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[113]++;

        ObjToIntMap names = getObjectFunctionNames(obj);
        JavaAdapterSignature sig;
        sig = new JavaAdapterSignature(superClass, interfaces, names);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[114]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[115]++;
        Class<?> adapterClass = generated.get(sig);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[116]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((adapterClass == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[63]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[117]++;
            String adapterName = "adapter" + cache.newClassSerialNumber();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[118]++;
            byte[] code = createAdapterCode(names, adapterName,
                                            superClass, interfaces, null);

            adapterClass = loadAdapterClass(adapterName, code);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[119]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[120]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((cache.isCachingEnabled()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[65]++;
                generated.put(sig, adapterClass);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[121]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[66]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[64]++;}
        return adapterClass;
    }

    public static byte[] createAdapterCode(ObjToIntMap functionNames,
                                           String adapterName,
                                           Class<?> superClass,
                                           Class<?>[] interfaces,
                                           String scriptClassName)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[122]++;
        ClassFileWriter cfw = new ClassFileWriter(adapterName,
                                                  superClass.getName(),
                                                  "<adapter>");
        cfw.addField("factory", "Lorg/mozilla/javascript/ContextFactory;",
                     (short) (ClassFileWriter.ACC_PUBLIC |
                              ClassFileWriter.ACC_FINAL));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[123]++;
        cfw.addField("delegee", "Lorg/mozilla/javascript/Scriptable;",
                     (short) (ClassFileWriter.ACC_PUBLIC |
                              ClassFileWriter.ACC_FINAL));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[124]++;
        cfw.addField("self", "Lorg/mozilla/javascript/Scriptable;",
                     (short) (ClassFileWriter.ACC_PUBLIC |
                              ClassFileWriter.ACC_FINAL));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[125]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[126]++;
        int interfacesCount = interfaces == null ? 0 : interfaces.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[127]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[22]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < interfacesCount) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[22]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[23]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[24]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[128]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((interfaces[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[67]++;
                cfw.addInterface(interfaces[i].getName());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[129]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[68]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[130]++;

        String superName = superClass.getName().replace('.', '/');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[131]++;
        Constructor<?>[] ctors = superClass.getDeclaredConstructors();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[132]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[25]++;


        for (Constructor<?> ctor : ctors) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[25]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[26]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[27]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[133]++;
            int mod = ctor.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[134]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((Modifier.isPublic(mod)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((Modifier.isProtected(mod)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[69]++;
                generateCtor(cfw, adapterName, superName, ctor);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[135]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[70]++;}
        }
        generateSerialCtor(cfw, adapterName, superName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[136]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[137]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((scriptClassName != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[71]++;
            generateEmptyCtor(cfw, adapterName, superName, scriptClassName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[138]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[72]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[139]++;

        ObjToIntMap generatedOverrides = new ObjToIntMap();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[140]++;
        ObjToIntMap generatedMethods = new ObjToIntMap();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[141]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[28]++;


int CodeCoverConditionCoverageHelper_C40;

        // generate methods to satisfy all specified interfaces.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i < interfacesCount) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[28]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[29]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[30]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[142]++;
            Method[] methods = interfaces[i].getMethods();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[143]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[31]++;


int CodeCoverConditionCoverageHelper_C41;
            for (int j = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((j < methods.length) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[31]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[32]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[33]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[144]++;
                Method method = methods[j];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[145]++;
                int mods = method.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[146]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((Modifier.isStatic(mods)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((Modifier.isFinal(mods)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[73]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[147]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[74]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[148]++;
                String methodName = method.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[149]++;
                Class<?>[] argTypes = method.getParameterTypes();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[150]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((functionNames.has(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[75]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[151]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
                    try {
CodeCoverTryBranchHelper_Try4 = true;
                        superClass.getMethod(methodName, argTypes);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[152]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[153]++;
                        // The class we're extending implements this method and
                        // the JavaScript object doesn't have an override. See
                        // bug 61226.
                        continue;
                    } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[78]++;
                        // Not implemented by superclass; fall through
                    } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[77]++;
}
  }

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[76]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[154]++;
                // make sure to generate only one instance of a particular
                // method/signature.
                String methodSignature = getMethodSignature(method, argTypes);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[155]++;
                String methodKey = methodName + methodSignature;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[156]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((generatedOverrides.has(methodKey)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[79]++;
                    generateMethod(cfw, adapterName, methodName, argTypes,
                                   method.getReturnType(), true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[157]++;
                    generatedOverrides.put(methodKey, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[158]++;
                    generatedMethods.put(methodName, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[159]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[80]++;}
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[160]++;

        // Now, go through the superclass's methods, checking for abstract
        // methods or additional methods to override.

        // generate any additional overrides that the object might contain.
        Method[] methods = getOverridableMethods(superClass);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[161]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[34]++;


int CodeCoverConditionCoverageHelper_C45;
        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((j < methods.length) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[34]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[35]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[36]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[162]++;
            Method method = methods[j];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[163]++;
            int mods = method.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[164]++;
            // if a method is marked abstract, must implement it or the
            // resulting class won't be instantiable. otherwise, if the object
            // has a property of the same name, then an override is intended.
            boolean isAbstractMethod = Modifier.isAbstract(mods);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[165]++;
            String methodName = method.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[166]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((isAbstractMethod) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((functionNames.has(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[81]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[167]++;
                // make sure to generate only one instance of a particular
                // method/signature.
                Class<?>[] argTypes = method.getParameterTypes();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[168]++;
                String methodSignature = getMethodSignature(method, argTypes);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[169]++;
                String methodKey = methodName + methodSignature;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[170]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((generatedOverrides.has(methodKey)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[83]++;
                    generateMethod(cfw, adapterName, methodName, argTypes,
                                   method.getReturnType(), true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[171]++;
                    generatedOverrides.put(methodKey, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[172]++;
                    generatedMethods.put(methodName, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[173]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[174]++;
int CodeCoverConditionCoverageHelper_C48;

                    // if a method was overridden, generate a "super$method"
                    // which lets the delegate call the superclass' version.
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isAbstractMethod) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[85]++;
                        generateSuper(cfw, adapterName, superName,
                                      methodName, methodSignature,
                                      argTypes, method.getReturnType());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[175]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[86]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[84]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[82]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[176]++;

        // Generate Java methods for remaining properties that are not
        // overrides.
        ObjToIntMap.Iterator iter = new ObjToIntMap.Iterator(functionNames);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[177]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[37]++;


int CodeCoverConditionCoverageHelper_C49;
        for (iter.start();(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((iter.done()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); iter.next()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[37]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[38]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[39]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[178]++;
            String functionName = (String)iter.getKey();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[179]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((generatedMethods.has(functionName)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[87]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[180]++;
                continue;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[88]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[181]++;
            int length = iter.getValue();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[182]++;
            Class<?>[] parms = new Class[length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[183]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[40]++;


int CodeCoverConditionCoverageHelper_C51;
            for (int k=0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((k < length) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); k++) { 
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[40]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[41]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[42]++;
}
                parms[k] = ScriptRuntime.ObjectClass;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[184]++;
  }
            generateMethod(cfw, adapterName, functionName, parms,
                           ScriptRuntime.ObjectClass, false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[185]++;
        }
        return cfw.toByteArray();
    }

    static Method[] getOverridableMethods(Class<?> clazz)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[186]++;
        ArrayList<Method> list = new ArrayList<Method>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[187]++;
        HashSet<String> skip = new HashSet<String>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[188]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[43]++;


int CodeCoverConditionCoverageHelper_C52;
        // Check superclasses before interfaces so we always choose
        // implemented methods over abstract ones, even if a subclass
        // re-implements an interface already implemented in a superclass
        // (e.g. java.util.ArrayList)
        for (Class<?> c = clazz;(((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false); c = c.getSuperclass()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[43]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[44]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[45]++;
}
            appendOverridableMethods(c, list, skip);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[189]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[190]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[46]++;


int CodeCoverConditionCoverageHelper_C53;
        for (Class<?> c = clazz;(((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false); c = c.getSuperclass()) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[46]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[47]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[48]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[191]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[49]++;


            for (Class<?> intf: c.getInterfaces()) { 
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[49]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[50]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[51]++;
}
                appendOverridableMethods(intf, list, skip);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[192]++;
  }
        }
        return list.toArray(new Method[list.size()]);
    }

    private static void appendOverridableMethods(Class<?> c,
            ArrayList<Method> list, HashSet<String> skip)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[193]++;
        Method[] methods = c.getDeclaredMethods();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[194]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[52]++;


int CodeCoverConditionCoverageHelper_C54;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < methods.length) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[52]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[53]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[54]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[195]++;
            String methodKey = methods[i].getName() +
                getMethodSignature(methods[i],
                        methods[i].getParameterTypes());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[196]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((skip.contains(methodKey)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[89]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[197]++;
                continue;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[90]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[198]++; // skip this method
            int mods = methods[i].getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[199]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((Modifier.isStatic(mods)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[91]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[200]++;
                continue;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[92]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[201]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((Modifier.isFinal(mods)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[93]++;
                // Make sure we don't add a final method to the list
                // of overridable methods.
                skip.add(methodKey);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[202]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[203]++;
                continue;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[94]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[204]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((Modifier.isPublic(mods)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((Modifier.isProtected(mods)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[95]++;
                list.add(methods[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[205]++;
                skip.add(methodKey);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[206]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[96]++;}
        }
    }

    static Class<?> loadAdapterClass(String className, byte[] classBytes)
    {
        Object staticDomain;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[207]++;
        Class<?> domainClass = SecurityController.getStaticSecurityDomainClass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[208]++;
int CodeCoverConditionCoverageHelper_C59;
        if((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((domainClass == CodeSource.class) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((domainClass == ProtectionDomain.class) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[97]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[209]++;
            // use the calling script's security domain if available
            ProtectionDomain protectionDomain = SecurityUtilities.getScriptProtectionDomain();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[210]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((protectionDomain == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[99]++;
                protectionDomain = JavaAdapter.class.getProtectionDomain();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[211]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[100]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[212]++;
int CodeCoverConditionCoverageHelper_C61;
            if((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((domainClass == CodeSource.class) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[101]++;
                staticDomain = protectionDomain == null ? null : protectionDomain.getCodeSource();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[213]++;

            }
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[102]++;
                staticDomain = protectionDomain;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[214]++;
            }

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[98]++;
            staticDomain = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[215]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[216]++;
        GeneratedClassLoader loader = SecurityController.createLoader(null,
                staticDomain);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[217]++;
        Class<?> result = loader.defineClass(className, classBytes);
        loader.linkClass(result);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[218]++;
        return result;
    }

    public static Function getFunction(Scriptable obj, String functionName)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[219]++;
        Object x = ScriptableObject.getProperty(obj, functionName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[220]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((x == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[103]++;
            // This method used to swallow the exception from calling
            // an undefined method. People have come to depend on this
            // somewhat dubious behavior. It allows people to avoid
            // implementing listener methods that they don't care about,
            // for instance.
            return null;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[104]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[221]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((x instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[105]++;
            throw ScriptRuntime.notFunctionError(x, functionName);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[106]++;}

        return (Function)x;
    }

    /**
     * Utility method which dynamically binds a Context to the current thread,
     * if none already exists.
     */
    public static Object callMethod(ContextFactory factory,
                                    final Scriptable thisObj,
                                    final Function f, final Object[] args,
                                    final long argsToWrap)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[222]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[107]++;
            // See comments in getFunction
            return null;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[108]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[223]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((factory == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[109]++;
            factory = ContextFactory.getGlobal();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[224]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[110]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[225]++;

        final Scriptable scope = f.getParentScope();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[226]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((argsToWrap == 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[111]++;
            return Context.call(factory, f, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[112]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[227]++;

        Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[228]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[113]++;
            return doCall(cx, scope, thisObj, f, args, argsToWrap);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[114]++;
            return factory.call(new ContextAction() {
                public Object run(Context cx)
                {
                    return doCall(cx, scope, thisObj, f, args, argsToWrap);
                }
            });
        }
    }

    private static Object doCall(Context cx, Scriptable scope,
                                 Scriptable thisObj, Function f,
                                 Object[] args, long argsToWrap)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[229]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[55]++;


int CodeCoverConditionCoverageHelper_C68;
        // Wrap the rest of objects
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((i != args.length) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[55]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[56]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[57]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[230]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((0 != (argsToWrap & (1 << i))) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[115]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[231]++;
                Object arg = args[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[232]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((arg instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[117]++;
                    args[i] = cx.getWrapFactory().wrap(cx, scope, arg,
                                                       null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[233]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[118]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[116]++;}
        }
        return f.call(cx, scope, thisObj, args);
    }

    public static Scriptable runScript(final Script script)
    {
        return (Scriptable)ContextFactory.getGlobal().call(
            new ContextAction() {
                public Object run(Context cx)
                {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[234]++;
                    ScriptableObject global = ScriptRuntime.getGlobal(cx);
                    script.exec(cx, global);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[235]++;
                    return global;
                }
            });
    }

    private static void generateCtor(ClassFileWriter cfw, String adapterName,
                                     String superName, Constructor<?> superCtor)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[236]++;
        short locals = 3;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[237]++; // this + factory + delegee
        Class<?>[] parameters = superCtor.getParameterTypes();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[238]++;
int CodeCoverConditionCoverageHelper_C71;

        // Note that we swapped arguments in app-facing constructors to avoid
        // conflicting signatures with serial constructor defined below.
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((parameters.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[119]++;
            cfw.startMethod("<init>",
                        "(Lorg/mozilla/javascript/Scriptable;"
                        +"Lorg/mozilla/javascript/ContextFactory;)V",
                        ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[239]++;

            // Invoke base class constructor
            cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[240]++;  // this
            cfw.addInvoke(ByteCode.INVOKESPECIAL, superName, "<init>", "()V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[241]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[120]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[242]++;
            StringBuilder sig = new StringBuilder(
                    "(Lorg/mozilla/javascript/Scriptable;"
                    +"Lorg/mozilla/javascript/ContextFactory;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[243]++;
            int marker = sig.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[244]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[58]++;

 // lets us reuse buffer for super signature
            for (Class<?> c : parameters) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[58]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[59]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[60]++;
}
                appendTypeString(sig, c);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[245]++;
            }
            sig.append(")V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[246]++;
            cfw.startMethod("<init>", sig.toString(), ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[247]++;

            // Invoke base class constructor
            cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[248]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[249]++;  // this
            short paramOffset = 3;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[250]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[61]++;


            for (Class<?> parameter : parameters) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[61]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[62]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[63]++;
}
                paramOffset += generatePushParam(cfw, paramOffset, parameter);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[251]++;
            }
            locals = paramOffset;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[252]++;
            sig.delete(1, marker);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[253]++;
            cfw.addInvoke(ByteCode.INVOKESPECIAL, superName, "<init>", sig.toString());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[254]++;
        }

        // Save parameter in instance variable "delegee"
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[255]++;  // this
        cfw.add(ByteCode.ALOAD_1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[256]++;  // first arg: Scriptable delegee
        cfw.add(ByteCode.PUTFIELD, adapterName, "delegee",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[257]++;

        // Save parameter in instance variable "factory"
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[258]++;  // this
        cfw.add(ByteCode.ALOAD_2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[259]++;  // second arg: ContextFactory instance
        cfw.add(ByteCode.PUTFIELD, adapterName, "factory",
                "Lorg/mozilla/javascript/ContextFactory;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[260]++;

        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[261]++;  // this for the following PUTFIELD for self
        // create a wrapper object to be used as "this" in method calls
        cfw.add(ByteCode.ALOAD_1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[262]++;  // the Scriptable delegee
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[263]++;  // this
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/JavaAdapter",
                      "createAdapterWrapper",
                      "(Lorg/mozilla/javascript/Scriptable;"
                      +"Ljava/lang/Object;"
                      +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[264]++;
        cfw.add(ByteCode.PUTFIELD, adapterName, "self",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[265]++;

        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[266]++;
        cfw.stopMethod(locals);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[267]++;
    }

    private static void generateSerialCtor(ClassFileWriter cfw,
                                           String adapterName,
                                           String superName)
    {
        cfw.startMethod("<init>",
                        "(Lorg/mozilla/javascript/ContextFactory;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +"Lorg/mozilla/javascript/Scriptable;"
                        +")V",
                        ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[268]++;

        // Invoke base class constructor
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[269]++;  // this
        cfw.addInvoke(ByteCode.INVOKESPECIAL, superName, "<init>", "()V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[270]++;

        // Save parameter in instance variable "factory"
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[271]++;  // this
        cfw.add(ByteCode.ALOAD_1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[272]++;  // first arg: ContextFactory instance
        cfw.add(ByteCode.PUTFIELD, adapterName, "factory",
                "Lorg/mozilla/javascript/ContextFactory;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[273]++;

        // Save parameter in instance variable "delegee"
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[274]++;  // this
        cfw.add(ByteCode.ALOAD_2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[275]++;  // second arg: Scriptable delegee
        cfw.add(ByteCode.PUTFIELD, adapterName, "delegee",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[276]++;
        // save self
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[277]++;  // this
        cfw.add(ByteCode.ALOAD_3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[278]++;  // third arg: Scriptable self
        cfw.add(ByteCode.PUTFIELD, adapterName, "self",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[279]++;

        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[280]++;
        cfw.stopMethod((short)4);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[281]++; // 4: this + factory + delegee + self
    }

    private static void generateEmptyCtor(ClassFileWriter cfw,
                                          String adapterName,
                                          String superName,
                                          String scriptClassName)
    {
        cfw.startMethod("<init>", "()V", ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[282]++;

        // Invoke base class constructor
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[283]++;  // this
        cfw.addInvoke(ByteCode.INVOKESPECIAL, superName, "<init>", "()V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[284]++;

        // Set factory to null to use current global when necessary
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[285]++;
        cfw.add(ByteCode.ACONST_NULL);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[286]++;
        cfw.add(ByteCode.PUTFIELD, adapterName, "factory",
                "Lorg/mozilla/javascript/ContextFactory;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[287]++;

        // Load script class
        cfw.add(ByteCode.NEW, scriptClassName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[288]++;
        cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[289]++;
        cfw.addInvoke(ByteCode.INVOKESPECIAL, scriptClassName, "<init>", "()V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[290]++;

        // Run script and save resulting scope
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/JavaAdapter",
                      "runScript",
                      "(Lorg/mozilla/javascript/Script;"
                      +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[291]++;
        cfw.add(ByteCode.ASTORE_1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[292]++;

        // Save the Scriptable in instance variable "delegee"
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[293]++;  // this
        cfw.add(ByteCode.ALOAD_1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[294]++;  // the Scriptable
        cfw.add(ByteCode.PUTFIELD, adapterName, "delegee",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[295]++;

        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[296]++;  // this for the following PUTFIELD for self
        // create a wrapper object to be used as "this" in method calls
        cfw.add(ByteCode.ALOAD_1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[297]++;  // the Scriptable
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[298]++;  // this
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/JavaAdapter",
                      "createAdapterWrapper",
                      "(Lorg/mozilla/javascript/Scriptable;"
                      +"Ljava/lang/Object;"
                      +")Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[299]++;
        cfw.add(ByteCode.PUTFIELD, adapterName, "self",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[300]++;

        cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[301]++;
        cfw.stopMethod((short)2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[302]++; // this + delegee
    }

    /**
     * Generates code to wrap Java arguments into Object[].
     * Non-primitive Java types are left as-is pending conversion
     * in the helper method. Leaves the array object on the top of the stack.
     */
    static void generatePushWrappedArgs(ClassFileWriter cfw,
                                        Class<?>[] argTypes,
                                        int arrayLength)
    {
        // push arguments
        cfw.addPush(arrayLength);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[303]++;
        cfw.add(ByteCode.ANEWARRAY, "java/lang/Object");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[304]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[305]++;
        int paramOffset = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[306]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[64]++;


int CodeCoverConditionCoverageHelper_C72;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((i != argTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[64]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[65]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[66]++;
}
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[307]++; // duplicate array reference
            cfw.addPush(i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[308]++;
            paramOffset += generateWrapArg(cfw, paramOffset, argTypes[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[309]++;
            cfw.add(ByteCode.AASTORE);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[310]++;
        }
    }

    /**
     * Generates code to wrap Java argument into Object.
     * Non-primitive Java types are left unconverted pending conversion
     * in the helper method. Leaves the wrapper object on the top of the stack.
     */
    private static int generateWrapArg(ClassFileWriter cfw, int paramOffset,
                                       Class<?> argType)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[311]++;
        int size = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[312]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((argType.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[121]++;
            cfw.add(ByteCode.ALOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[313]++;


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[122]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[314]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((argType == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[123]++;
            // wrap boolean values with java.lang.Boolean.
            cfw.add(ByteCode.NEW, "java/lang/Boolean");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[315]++;
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[316]++;
            cfw.add(ByteCode.ILOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[317]++;
            cfw.addInvoke(ByteCode.INVOKESPECIAL, "java/lang/Boolean",
                          "<init>", "(Z)V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[318]++;


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[124]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[319]++;
int CodeCoverConditionCoverageHelper_C75; if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((argType == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[125]++;
            // Create a string of length 1 using the character parameter.
            cfw.add(ByteCode.ILOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[320]++;
            cfw.addInvoke(ByteCode.INVOKESTATIC, "java/lang/String",
                          "valueOf", "(C)Ljava/lang/String;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[321]++;


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[126]++;
            // convert all numeric values to java.lang.Double.
            cfw.add(ByteCode.NEW, "java/lang/Double");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[322]++;
            cfw.add(ByteCode.DUP);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[323]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[324]++;
            String typeName = argType.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[325]++;
            switch (typeName.charAt(0)) {
            case 'b':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[127]++;
            case 's':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[128]++;
            case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[129]++;
                // load an int value, convert to double.
                cfw.add(ByteCode.ILOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[326]++;
                cfw.add(ByteCode.I2D);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[327]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[328]++;
                break;
            case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[130]++;
                // load a long, convert to double.
                cfw.add(ByteCode.LLOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[329]++;
                cfw.add(ByteCode.L2D);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[330]++;
                size = 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[331]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[332]++;
                break;
            case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[131]++;
                // load a float, convert to double.
                cfw.add(ByteCode.FLOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[333]++;
                cfw.add(ByteCode.F2D);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[334]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[335]++;
                break;
            case 'd':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[132]++;
                cfw.add(ByteCode.DLOAD, paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[336]++;
                size = 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[337]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[338]++;
                break; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[133]++;
            }
            cfw.addInvoke(ByteCode.INVOKESPECIAL, "java/lang/Double",
                          "<init>", "(D)V");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[339]++;
        }
}
}
        return size;
    }

    /**
     * Generates code to convert a wrapped value type to a primitive type.
     * Handles unwrapping java.lang.Boolean, and java.lang.Number types.
     * Generates the appropriate RETURN bytecode.
     */
    static void generateReturnResult(ClassFileWriter cfw, Class<?> retType,
                                     boolean callConvertResult)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[340]++;
int CodeCoverConditionCoverageHelper_C76;
        // wrap boolean values with java.lang.Boolean, convert all other
        // primitive values to java.lang.Double.
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((retType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[134]++;
            cfw.add(ByteCode.POP);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[341]++;
            cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[342]++;


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[135]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[343]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((retType == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[136]++;
            cfw.addInvoke(ByteCode.INVOKESTATIC,
                          "org/mozilla/javascript/Context",
                          "toBoolean", "(Ljava/lang/Object;)Z");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[344]++;
            cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[345]++;


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[137]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[346]++;
int CodeCoverConditionCoverageHelper_C78; if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((retType == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[138]++;
            // characters are represented as strings in JavaScript.
            // return the first character.
            // first convert the value to a string if possible.
            cfw.addInvoke(ByteCode.INVOKESTATIC,
                          "org/mozilla/javascript/Context",
                          "toString",
                          "(Ljava/lang/Object;)Ljava/lang/String;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[347]++;
            cfw.add(ByteCode.ICONST_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[348]++;
            cfw.addInvoke(ByteCode.INVOKEVIRTUAL, "java/lang/String",
                          "charAt", "(I)C");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[349]++;
            cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[350]++;


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[139]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[351]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((retType.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[140]++;
            cfw.addInvoke(ByteCode.INVOKESTATIC,
                          "org/mozilla/javascript/Context",
                          "toNumber", "(Ljava/lang/Object;)D");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[352]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[353]++;
            String typeName = retType.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[354]++;
            switch (typeName.charAt(0)) {
            case 'b':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[142]++;
            case 's':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[143]++;
            case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[144]++;
                cfw.add(ByteCode.D2I);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[355]++;
                cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[356]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[357]++;
                break;
            case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[145]++;
                cfw.add(ByteCode.D2L);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[358]++;
                cfw.add(ByteCode.LRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[359]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[360]++;
                break;
            case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[146]++;
                cfw.add(ByteCode.D2F);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[361]++;
                cfw.add(ByteCode.FRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[362]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[363]++;
                break;
            case 'd':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[147]++;
                cfw.add(ByteCode.DRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[364]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[365]++;
                break;
            default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[148]++;
                throw new RuntimeException("Unexpected return type " +
                                           retType.toString());
            }


        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[141]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[366]++;
            String retTypeStr = retType.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[367]++;
int CodeCoverConditionCoverageHelper_C80;
            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((callConvertResult) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[149]++;
                cfw.addLoadConstant(retTypeStr);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[368]++;
                cfw.addInvoke(ByteCode.INVOKESTATIC,
                              "java/lang/Class",
                              "forName",
                              "(Ljava/lang/String;)Ljava/lang/Class;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[369]++;

                cfw.addInvoke(ByteCode.INVOKESTATIC,
                              "org/mozilla/javascript/JavaAdapter",
                              "convertResult",
                              "(Ljava/lang/Object;"
                              +"Ljava/lang/Class;"
                              +")Ljava/lang/Object;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[370]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[150]++;}
            // Now cast to return type
            cfw.add(ByteCode.CHECKCAST, retTypeStr);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[371]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[372]++;
        }
}
}
}
    }

    private static void generateMethod(ClassFileWriter cfw, String genName,
                                       String methodName, Class<?>[] parms,
                                       Class<?> returnType, boolean convertResult)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[373]++;
        StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[374]++;
        int paramsEnd = appendMethodSignature(parms, returnType, sb);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[375]++;
        String methodSignature = sb.toString();
        cfw.startMethod(methodName, methodSignature,
                        ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[376]++;

        // Prepare stack to call method

        // push factory
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[377]++;
        cfw.add(ByteCode.GETFIELD, genName, "factory",
                "Lorg/mozilla/javascript/ContextFactory;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[378]++;

        // push self
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[379]++;
        cfw.add(ByteCode.GETFIELD, genName, "self",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[380]++;

        // push function
        cfw.add(ByteCode.ALOAD_0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[381]++;
        cfw.add(ByteCode.GETFIELD, genName, "delegee",
                "Lorg/mozilla/javascript/Scriptable;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[382]++;
        cfw.addPush(methodName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[383]++;
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/JavaAdapter",
                      "getFunction",
                      "(Lorg/mozilla/javascript/Scriptable;"
                      +"Ljava/lang/String;"
                      +")Lorg/mozilla/javascript/Function;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[384]++;

        // push arguments
        generatePushWrappedArgs(cfw, parms, parms.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[385]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[386]++;
int CodeCoverConditionCoverageHelper_C81;

        // push bits to indicate which parameters should be wrapped
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((parms.length > 64) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[151]++;
            // If it will be an issue, then passing a static boolean array
            // can be an option, but for now using simple bitmask
            throw Context.reportRuntimeError0(
                "JavaAdapter can not subclass methods with more then"
                +" 64 arguments.");

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[152]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[387]++;
        long convertionMask = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[388]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[67]++;


int CodeCoverConditionCoverageHelper_C82;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i != parms.length) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[67]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[68]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[69]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[389]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((parms[i].isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[153]++;
                convertionMask |= (1 << i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[390]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[154]++;}
        }
        cfw.addPush(convertionMask);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[391]++;

        // go through utility method, which creates a Context to run the
        // method in.
        cfw.addInvoke(ByteCode.INVOKESTATIC,
                      "org/mozilla/javascript/JavaAdapter",
                      "callMethod",
                      "(Lorg/mozilla/javascript/ContextFactory;"
                      +"Lorg/mozilla/javascript/Scriptable;"
                      +"Lorg/mozilla/javascript/Function;"
                      +"[Ljava/lang/Object;"
                      +"J"
                      +")Ljava/lang/Object;");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[392]++;

        generateReturnResult(cfw, returnType, convertResult);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[393]++;

        cfw.stopMethod((short)paramsEnd);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[394]++;
    }

    /**
     * Generates code to push typed parameters onto the operand stack
     * prior to a direct Java method call.
     */
    private static int generatePushParam(ClassFileWriter cfw, int paramOffset,
                                         Class<?> paramType)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[395]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((paramType.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[155]++;
            cfw.addALoad(paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[396]++;
            return 1;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[156]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[397]++;
        String typeName = paramType.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[398]++;
        switch (typeName.charAt(0)) {
        case 'z':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[157]++;
        case 'b':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[158]++;
        case 'c':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[159]++;
        case 's':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[160]++;
        case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[161]++;
            // load an int value, convert to double.
            cfw.addILoad(paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[399]++;
            return 1;
        case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[162]++;
            // load a long, convert to double.
            cfw.addLLoad(paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[400]++;
            return 2;
        case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[163]++;
            // load a float, convert to double.
            cfw.addFLoad(paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[401]++;
            return 1;
        case 'd':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[164]++;
            cfw.addDLoad(paramOffset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[402]++;
            return 2; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[165]++;
        }
        throw Kit.codeBug();
    }

    /**
     * Generates code to return a Java type, after calling a Java method
     * that returns the same type.
     * Generates the appropriate RETURN bytecode.
     */
    private static void generatePopResult(ClassFileWriter cfw,
                                          Class<?> retType)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[403]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((retType.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[166]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[404]++;
            String typeName = retType.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[405]++;
            switch (typeName.charAt(0)) {
            case 'b':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[168]++;
            case 'c':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[169]++;
            case 's':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[170]++;
            case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[171]++;
            case 'z':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[172]++;
                cfw.add(ByteCode.IRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[406]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[407]++;
                break;
            case 'l':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[173]++;
                cfw.add(ByteCode.LRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[408]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[409]++;
                break;
            case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[174]++;
                cfw.add(ByteCode.FRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[410]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[411]++;
                break;
            case 'd':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[175]++;
                cfw.add(ByteCode.DRETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[412]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[413]++;
                break; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[176]++;
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[167]++;
            cfw.add(ByteCode.ARETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[414]++;
        }
    }

    /**
     * Generates a method called "super$methodName()" which can be called
     * from JavaScript that is equivalent to calling "super.methodName()"
     * from Java. Eventually, this may be supported directly in JavaScript.
     */
    private static void generateSuper(ClassFileWriter cfw,
                                      String genName, String superName,
                                      String methodName, String methodSignature,
                                      Class<?>[] parms, Class<?> returnType)
    {
        cfw.startMethod("super$" + methodName, methodSignature,
                        ClassFileWriter.ACC_PUBLIC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[415]++;

        // push "this"
        cfw.add(ByteCode.ALOAD, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[416]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[417]++;

        // push the rest of the parameters.
        int paramOffset = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[418]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[70]++;


        for (Class<?> parm : parms) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[70]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[71]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[72]++;
}
            paramOffset += generatePushParam(cfw, paramOffset, parm);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[419]++;
        }

        // call the superclass implementation of the method.
        cfw.addInvoke(ByteCode.INVOKESPECIAL,
                      superName,
                      methodName,
                      methodSignature);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[420]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[421]++;

        // now, handle the return type appropriately.
        Class<?> retType = returnType;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[422]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((retType.equals(Void.TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[177]++;
            generatePopResult(cfw, retType);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[423]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[178]++;
            cfw.add(ByteCode.RETURN);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[424]++;
        }
        cfw.stopMethod((short)(paramOffset + 1));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[425]++;
    }

    /**
     * Returns a fully qualified method name concatenated with its signature.
     */
    private static String getMethodSignature(Method method, Class<?>[] argTypes)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[426]++;
        StringBuilder sb = new StringBuilder();
        appendMethodSignature(argTypes, method.getReturnType(), sb);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[427]++;
        return sb.toString();
    }

    static int appendMethodSignature(Class<?>[] argTypes,
                                     Class<?> returnType,
                                     StringBuilder sb)
    {
        sb.append('(');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[428]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[429]++;
        int firstLocal = 1 + argTypes.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[430]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[73]++;

 // includes this.
        for (Class<?> type : argTypes) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[73]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[74]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[75]++;
}
            appendTypeString(sb, type);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[431]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[432]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((type == Long.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((type == Double.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[179]++;
                // adjust for double slot
                ++firstLocal;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[433]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[180]++;}
        }
        sb.append(')');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[434]++;
        appendTypeString(sb, returnType);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[435]++;
        return firstLocal;
    }

    private static StringBuilder appendTypeString(StringBuilder sb, Class<?> type)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[436]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[76]++;


int CodeCoverConditionCoverageHelper_C88;
        while ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((type.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[76]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[77]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[78]++;
}
            sb.append('[');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[437]++;
            type = type.getComponentType();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[438]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[439]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((type.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[181]++;
            char typeLetter;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[440]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((type == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[183]++;
                typeLetter = 'Z';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[441]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[184]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[442]++;
int CodeCoverConditionCoverageHelper_C91; if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((type == Long.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[185]++;
                typeLetter = 'J';
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[443]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[186]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[444]++;
                String typeName = type.getName();
                typeLetter = Character.toUpperCase(typeName.charAt(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[445]++;
            }
}
            sb.append(typeLetter);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[446]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[182]++;
            sb.append('L');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[447]++;
            sb.append(type.getName().replace('.', '/'));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[448]++;
            sb.append(';');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[449]++;
        }
        return sb;
    }

    static int[] getArgsToConvert(Class<?>[] argTypes)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[450]++;
        int count = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[451]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[79]++;


int CodeCoverConditionCoverageHelper_C92;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((i != argTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[79]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[80]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[81]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[452]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((argTypes[i].isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[187]++;
                ++count;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[453]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[188]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[454]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[189]++;
            return null;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[190]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[455]++;
        int[] array = new int[count];
        count = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[456]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[457]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[82]++;


int CodeCoverConditionCoverageHelper_C95;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((i != argTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[82]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[83]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.loops[84]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[458]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((argTypes[i].isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[191]++;
                array[count++] = i;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[459]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.branches[192]++;}
        }
        return array;
    }

    private static final Object FTAG = "JavaAdapter";
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[460]++;
  }
    private static final int Id_JavaAdapter = 1;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh.statements[461]++;
  }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh ());
  }
    public static long[] statements = new long[462];
    public static long[] branches = new long[193];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[97];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-JavaAdapter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 96; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[85];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dl079ni2so09ugpp1pwh () {
    super("org.mozilla.javascript.RHINO-SRC-JavaAdapter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 461; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 192; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 96; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 84; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-JavaAdapter.java");
      for (int i = 1; i <= 461; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 192; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 96; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 28; i++) {
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

