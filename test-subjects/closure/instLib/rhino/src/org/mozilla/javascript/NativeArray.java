/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class implements the Array native object.
 */
public class NativeArray extends IdScriptableObject implements List
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.ping();
  }

    static final long serialVersionUID = 7331366857676127338L;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[1]++;
  }

    /*
     * Optimization possibilities and open issues:
     * - Long vs. double schizophrenia.  I suspect it might be better
     * to use double throughout.
     *
     * - Functions that need a new Array call "new Array" in the
     * current scope rather than using a hardwired constructor;
     * "Array" could be redefined.  It turns out that js calls the
     * equivalent of "new Array" in the current scope, except that it
     * always gets at least an object back, even when Array == null.
     */

    private static final Object ARRAY_TAG = "Array";
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[2]++;
  }
    private static final Integer NEGATIVE_ONE = Integer.valueOf(-1);
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[3]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[4]++;
        NativeArray obj = new NativeArray(0);
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[5]++;
    }

    static int getMaximumInitialCapacity() {
        return maximumInitialCapacity;
    }

    static void setMaximumInitialCapacity(int maximumInitialCapacity) {
        NativeArray.maximumInitialCapacity = maximumInitialCapacity;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[6]++;
    }

    public NativeArray(long lengthArg)
    {
        denseOnly = lengthArg <= maximumInitialCapacity;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[7]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[1]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[9]++;
            int intLength = (int) lengthArg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((intLength < DEFAULT_INITIAL_CAPACITY) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[3]++;
                intLength = DEFAULT_INITIAL_CAPACITY;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[11]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[4]++;}
            dense = new Object[intLength];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[12]++;
            Arrays.fill(dense, Scriptable.NOT_FOUND);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[13]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[2]++;}
        length = lengthArg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[14]++;
    }

    public NativeArray(Object[] array)
    {
        denseOnly = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[15]++;
        dense = array;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[16]++;
        length = array.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[17]++;
    }

    @Override
    public String getClassName()
    {
        return "Array";
    }

    private static final int
        Id_length        =  1,
        MAX_INSTANCE_ID  =  1;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[18]++;
  }

    @Override
    protected int getMaxInstanceId()
    {
        return MAX_INSTANCE_ID;
    }

    @Override
    protected void setInstanceIdAttributes(int id, int attr) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id == Id_length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[5]++;
            lengthAttr = attr;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[20]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[6]++;}
    }

    @Override
    protected int findInstanceIdInfo(String s)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((s.equals("length")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[7]++;
            return instanceIdInfo(lengthAttr, Id_length);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[8]++;}
        return super.findInstanceIdInfo(s);
    }

    @Override
    protected String getInstanceIdName(int id)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((id == Id_length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[9]++; return "length";
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[10]++;}
        return super.getInstanceIdName(id);
    }

    @Override
    protected Object getInstanceIdValue(int id)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((id == Id_length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[11]++;
            return ScriptRuntime.wrapNumber(length);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[12]++;}
        return super.getInstanceIdValue(id);
    }

    @Override
    protected void setInstanceIdValue(int id, Object value)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((id == Id_length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[13]++;
            setLength(value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[25]++; return;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[14]++;}
        super.setInstanceIdValue(id, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[26]++;
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_join,
                "join", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[27]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_reverse,
                "reverse", 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[28]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_sort,
                "sort", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[29]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_push,
                "push", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[30]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_pop,
                "pop", 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[31]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_shift,
                "shift", 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[32]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_unshift,
                "unshift", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[33]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_splice,
                "splice", 2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[34]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_concat,
                "concat", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[35]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_slice,
                "slice", 2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[36]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_indexOf,
                "indexOf", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[37]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_lastIndexOf,
                "lastIndexOf", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[38]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_every,
                "every", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[39]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_filter,
                "filter", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[40]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_forEach,
                "forEach", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[41]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_map,
                "map", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[42]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_some,
                "some", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[43]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_reduce,
                "reduce", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[44]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_reduceRight,
                "reduceRight", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[45]++;
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_isArray,
                "isArray", 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[46]++;
        super.fillConstructorProperties(ctor);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[47]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[48]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[15]++;    arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[49]++; s="constructor";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[50]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[51]++;    break;
          case Id_toString:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[16]++;       arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[52]++; s="toString";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[53]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[54]++;       break;
          case Id_toLocaleString:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[17]++; arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[55]++; s="toLocaleString";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[56]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[57]++; break;
          case Id_toSource:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[18]++;       arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[58]++; s="toSource";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[59]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[60]++;       break;
          case Id_join:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[19]++;           arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[61]++; s="join";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[62]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[63]++;           break;
          case Id_reverse:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[20]++;        arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[64]++; s="reverse";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[65]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[66]++;        break;
          case Id_sort:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[21]++;           arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[67]++; s="sort";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[68]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[69]++;           break;
          case Id_push:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[22]++;           arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[70]++; s="push";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[71]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[72]++;           break;
          case Id_pop:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[23]++;            arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[73]++; s="pop";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[74]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[75]++;            break;
          case Id_shift:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[24]++;          arity=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[76]++; s="shift";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[77]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[78]++;          break;
          case Id_unshift:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[25]++;        arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[79]++; s="unshift";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[80]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[81]++;        break;
          case Id_splice:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[26]++;         arity=2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[82]++; s="splice";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[83]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[84]++;         break;
          case Id_concat:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[27]++;         arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[85]++; s="concat";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[86]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[87]++;         break;
          case Id_slice:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[28]++;          arity=2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[88]++; s="slice";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[89]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[90]++;          break;
          case Id_indexOf:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[29]++;        arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[91]++; s="indexOf";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[92]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[93]++;        break;
          case Id_lastIndexOf:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[30]++;    arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[94]++; s="lastIndexOf";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[95]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[96]++;    break;
          case Id_every:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[31]++;          arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[97]++; s="every";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[98]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[99]++;          break;
          case Id_filter:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[32]++;         arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[100]++; s="filter";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[101]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[102]++;         break;
          case Id_forEach:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[33]++;        arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[103]++; s="forEach";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[104]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[105]++;        break;
          case Id_map:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[34]++;            arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[106]++; s="map";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[107]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[108]++;            break;
          case Id_some:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[35]++;           arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[109]++; s="some";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[110]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[111]++;           break;
          case Id_reduce:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[36]++;         arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[112]++; s="reduce";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[113]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[114]++;         break;
          case Id_reduceRight:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[37]++;    arity=1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[115]++; s="reduceRight";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[116]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[117]++;    break;
          default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[38]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(ARRAY_TAG, id, s, arity);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[118]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[119]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((f.hasTag(ARRAY_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[39]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[40]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[120]++;
        int id = f.methodId();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[121]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[1]++;


      again:
        for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[3]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[122]++;
            switch (id) {
              case ConstructorId_join:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[41]++;
              case ConstructorId_reverse:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[42]++;
              case ConstructorId_sort:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[43]++;
              case ConstructorId_push:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[44]++;
              case ConstructorId_pop:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[45]++;
              case ConstructorId_shift:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[46]++;
              case ConstructorId_unshift:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[47]++;
              case ConstructorId_splice:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[48]++;
              case ConstructorId_concat:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[49]++;
              case ConstructorId_slice:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[50]++;
              case ConstructorId_indexOf:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[51]++;
              case ConstructorId_lastIndexOf:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[52]++;
              case ConstructorId_every:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[53]++;
              case ConstructorId_filter:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[54]++;
              case ConstructorId_forEach:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[55]++;
              case ConstructorId_map:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[56]++;
              case ConstructorId_some:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[57]++;
              case ConstructorId_reduce:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[58]++;
              case ConstructorId_reduceRight:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[59]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[123]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[60]++;
                    thisObj = ScriptRuntime.toObject(scope, args[0]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[124]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[125]++;
                    Object[] newArgs = new Object[args.length-1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[126]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
                    for (int i=0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < newArgs.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[6]++;
}
                        newArgs[i] = args[i+1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[127]++;
  }
                    args = newArgs;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[128]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[61]++;}
                id = -id;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[129]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[130]++;
                continue again;
              }

              case ConstructorId_isArray:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[62]++;
                return args.length > 0 && (args[0] instanceof NativeArray);

              case Id_constructor:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[63]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[131]++;
                boolean inNewExpr = (thisObj == null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[132]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((inNewExpr) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[64]++;
                    // IdFunctionObject.construct will set up parent, proto
                    return f.construct(cx, scope, args);

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[65]++;}
                return jsConstructor(cx, scope, args);
              }

              case Id_toString:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[66]++;
                return toStringHelper(cx, scope, thisObj,
                    cx.hasFeature(Context.FEATURE_TO_STRING_AS_SOURCE), false);

              case Id_toLocaleString:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[67]++;
                return toStringHelper(cx, scope, thisObj, false, true);

              case Id_toSource:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[68]++;
                return toStringHelper(cx, scope, thisObj, true, false);

              case Id_join:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[69]++;
                return js_join(cx, thisObj, args);

              case Id_reverse:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[70]++;
                return js_reverse(cx, thisObj, args);

              case Id_sort:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[71]++;
                return js_sort(cx, scope, thisObj, args);

              case Id_push:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[72]++;
                return js_push(cx, thisObj, args);

              case Id_pop:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[73]++;
                return js_pop(cx, thisObj, args);

              case Id_shift:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[74]++;
                return js_shift(cx, thisObj, args);

              case Id_unshift:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[75]++;
                return js_unshift(cx, thisObj, args);

              case Id_splice:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[76]++;
                return js_splice(cx, scope, thisObj, args);

              case Id_concat:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[77]++;
                return js_concat(cx, scope, thisObj, args);

              case Id_slice:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[78]++;
                return js_slice(cx, thisObj, args);

              case Id_indexOf:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[79]++;
                return indexOfHelper(cx, thisObj, args, false);

              case Id_lastIndexOf:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[80]++;
                return indexOfHelper(cx, thisObj, args, true);

              case Id_every:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[81]++;
              case Id_filter:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[82]++;
              case Id_forEach:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[83]++;
              case Id_map:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[84]++;
              case Id_some:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[85]++;
                return iterativeMethod(cx, id, scope, thisObj, args);
              case Id_reduce:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[86]++;
              case Id_reduceRight:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[87]++;
                return reduceMethod(cx, id, scope, thisObj, args); default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[88]++;
            }
            throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    @Override
    public Object get(int index, Scriptable start)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[133]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isGetterOrSetter(null, index, false)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[89]++;
            return super.get(index, start);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[90]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[134]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((index < dense.length) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[91]++;
            return dense[index];
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[92]++;}
        return super.get(index, start);
    }

    @Override
    public boolean has(int index, Scriptable start)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[135]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isGetterOrSetter(null, index, false)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[93]++;
            return super.has(index, start);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[94]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[136]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((index < dense.length) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[95]++;
            return dense[index] != NOT_FOUND;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[96]++;}
        return super.has(index, start);
    }

    private static long toArrayIndex(Object id) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[137]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[97]++;
            return toArrayIndex((String)id);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[98]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[138]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((id instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[99]++;
            return toArrayIndex(((Number)id).doubleValue());

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[100]++;}
}
        return -1;
    }

    // if id is an array index (ECMA 15.4.0), return the number,
    // otherwise return -1L
    private static long toArrayIndex(String id)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[139]++;
        long index = toArrayIndex(ScriptRuntime.toNumber(id));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[140]++;
int CodeCoverConditionCoverageHelper_C19;
        // Assume that ScriptRuntime.toString(index) is the same
        // as java.lang.Long.toString(index) for long
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((Long.toString(index).equals(id)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[101]++;
            return index;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[102]++;}
        return -1;
    }

    private static long toArrayIndex(double d) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[141]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((d == d) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[103]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[142]++;
            long index = ScriptRuntime.toUint32(d);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[143]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((index == d) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((index != 4294967295L) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[105]++;
                return index;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[106]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[104]++;}
        return -1;
    }

    private static int toDenseIndex(Object id) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[144]++;
      long index = toArrayIndex(id);
      return 0 <= index && index < Integer.MAX_VALUE ? (int) index : -1;
    }

    @Override
    public void put(String id, Scriptable start, Object value)
    {
        super.put(id, start, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[145]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[146]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[107]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[147]++;
            // If the object is sealed, super will throw exception
            long index = toArrayIndex(id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[148]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((index >= length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[109]++;
                length = index + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[149]++;
                denseOnly = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[150]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[110]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[108]++;}
    }

    private boolean ensureCapacity(int capacity)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[151]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((capacity > dense.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[111]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[152]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((capacity > MAX_PRE_GROW_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[113]++;
                denseOnly = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[153]++;
                return false;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[114]++;}
            capacity = Math.max(capacity, (int)(dense.length * GROW_FACTOR));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[154]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[155]++;
            Object[] newDense = new Object[capacity];
            System.arraycopy(dense, 0, newDense, 0, dense.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[156]++;
            Arrays.fill(newDense, dense.length, newDense.length,
                        Scriptable.NOT_FOUND);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[157]++;
            dense = newDense;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[158]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[112]++;}
        return true;
    }

    @Override
    public void put(int index, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[159]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2048)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1024)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C26 |= (512)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (128)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isGetterOrSetter(null, index, true)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 6) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 6) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[115]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[160]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((index < dense.length) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[117]++;
                dense[index] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[161]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[162]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.length <= index) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[119]++;
                    this.length = (long)index + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[163]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[120]++;}
                return;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[118]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[164]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((index < dense.length * GROW_FACTOR) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((ensureCapacity(index+1)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[121]++;
                dense[index] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[165]++;
                this.length = (long)index + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[166]++;
                return;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[122]++;
                denseOnly = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[167]++;
            }
}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[116]++;}
        super.put(index, start, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[168]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[169]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 (((lengthAttr & READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[123]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[170]++;
int CodeCoverConditionCoverageHelper_C31;
            // only set the array length if given an array index (ECMA 15.4.0)
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.length <= index) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[125]++;
                // avoid overflowing index!
                this.length = (long)index + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[171]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[126]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[124]++;}
    }

    @Override
    public void delete(int index)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[172]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2048)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (512)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (128)) == 0 || true) &&
 ((index < dense.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isGetterOrSetter(null, index, true)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 6) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 6) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[127]++;
            dense[index] = NOT_FOUND;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[173]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[128]++;
            super.delete(index);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[174]++;
        }
    }

    @Override
    public Object[] getIds()
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[175]++;
        Object[] superIds = super.getIds();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[176]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((dense == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[129]++; return superIds;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[130]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[177]++;
        int N = dense.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[178]++;
        long currentLength = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[179]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((N > currentLength) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[131]++;
            N = (int)currentLength;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[180]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[132]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[181]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[133]++; return superIds;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[134]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[182]++;
        int superLength = superIds.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[183]++;
        Object[] ids = new Object[N + superLength];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[184]++;

        int presentCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[185]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[7]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[9]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[186]++;
int CodeCoverConditionCoverageHelper_C37;
            // Replace existing elements by their indexes
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((dense[i] != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[135]++;
                ids[presentCount] = Integer.valueOf(i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[187]++;
                ++presentCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[188]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[136]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[189]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((presentCount != N) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[137]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[190]++;
            // dense contains deleted elems, need to shrink the result
            Object[] tmp = new Object[presentCount + superLength];
            System.arraycopy(ids, 0, tmp, 0, presentCount);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[191]++;
            ids = tmp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[192]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[138]++;}
        System.arraycopy(superIds, 0, ids, presentCount, superLength);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[193]++;
        return ids;
    }

    @Override
    public Object[] getAllIds()
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[194]++;
      Set<Object> allIds = new LinkedHashSet<Object>(
            Arrays.asList(this.getIds()));
      allIds.addAll(Arrays.asList(super.getAllIds()));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[195]++;
      return allIds.toArray();
    }

    public Integer[] getIndexIds() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[196]++;
      Object[] ids = getIds();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[197]++;
      java.util.List<Integer> indices = new java.util.ArrayList<Integer>(ids.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[198]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[10]++;


      for (Object id : ids) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[10]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[11]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[12]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[199]++;
        int int32Id = ScriptRuntime.toInt32(id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[200]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((int32Id >= 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ScriptRuntime.toString(int32Id).equals(ScriptRuntime.toString(id))) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[139]++;
          indices.add(int32Id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[201]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[140]++;}
      }
      return indices.toArray(new Integer[indices.size()]);
    }

    @Override
    public Object getDefaultValue(Class<?> hint)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[202]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.NumberClass) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[141]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[203]++;
            Context cx = Context.getContext();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[204]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[143]++;
                return Long.valueOf(length);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[144]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[142]++;}
        return super.getDefaultValue(hint);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object value) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[205]++;
      Scriptable scope = getParentScope();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[206]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[145]++; scope = this;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[207]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[146]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[208]++;
      ScriptableObject desc = new NativeObject();
      ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[209]++;
      desc.defineProperty("value", value, EMPTY);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[210]++;
      desc.defineProperty("writable", true, EMPTY);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[211]++;
      desc.defineProperty("enumerable", true, EMPTY);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[212]++;
      desc.defineProperty("configurable", true, EMPTY);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[213]++;
      return desc;
    }

    @Override
    public int getAttributes(int index) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[214]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (128)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (32)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((index < dense.length) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((dense[index] != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 4) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 4) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[147]++;
            return EMPTY;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[148]++;}
        return super.getAttributes(index);
    }

    @Override
    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[215]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[149]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[216]++;
        int index = toDenseIndex(id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[217]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (32)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((index < dense.length) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((dense[index] != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[151]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[218]++;
          Object value = dense[index];
          return defaultIndexPropertyDescriptor(value);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[152]++;}

      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[150]++;}
      return super.getOwnPropertyDescriptor(cx, id);
    }

    @Override
    protected void defineOwnProperty(Context cx, Object id,
                                     ScriptableObject desc,
                                     boolean checkValid) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[219]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((dense != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[153]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[220]++;
        Object[] values = dense;
        dense = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[221]++;
        denseOnly = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[222]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[223]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[13]++;


int CodeCoverConditionCoverageHelper_C47;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((i < values.length) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[13]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[14]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[15]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[224]++;
int CodeCoverConditionCoverageHelper_C48;
          if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((values[i] != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[155]++;
            put(i, this, values[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[225]++;

          } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[156]++;}
        }

      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[154]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[226]++;
      long index = toArrayIndex(id);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[227]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((index >= length) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[157]++;
        length = index + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[228]++;

      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[158]++;}
      super.defineOwnProperty(cx, id, desc, checkValid);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[229]++;
    }

    /**
     * See ECMA 15.4.1,2
     */
    private static Object jsConstructor(Context cx, Scriptable scope,
                                        Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[230]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[159]++;
            return new NativeArray(0);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[160]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[231]++;
int CodeCoverConditionCoverageHelper_C51;

        // Only use 1 arg as first element for version 1.2; for
        // any other version (including 1.3) follow ECMA and use it as
        // a length.
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[161]++;
            return new NativeArray(args);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[162]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[232]++;
            Object arg0 = args[0];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[233]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((args.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((arg0 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[163]++;
                return new NativeArray(args);

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[164]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[234]++;
                long len = ScriptRuntime.toUint32(arg0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[235]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((len != ((Number)arg0).doubleValue()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[165]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[236]++;
                    String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
                    throw ScriptRuntime.constructError("RangeError", msg);

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[166]++;}
                return new NativeArray(len);
            }
        }
    }

    public long getLength() {
        return length;
    }

    /** @deprecated Use {@link #getLength()} instead. */
    public long jsGet_length() {
        return getLength();
    }

    /**
     * Change the value of the internal flag that determines whether all
     * storage is handed by a dense backing array rather than an associative
     * store.
     * @param denseOnly new value for denseOnly flag
     * @throws IllegalArgumentException if an attempt is made to enable
     *   denseOnly after it was disabled; NativeArray code is not written
     *   to handle switching back to a dense representation
     */
    void setDenseOnly(boolean denseOnly) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[237]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[167]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[168]++;}
        this.denseOnly = denseOnly;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[238]++;
    }

    private void setLength(Object val) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[239]++;
int CodeCoverConditionCoverageHelper_C55;
        /* XXX do we satisfy this?
         * 15.4.5.1 [[Put]](P, V):
         * 1. Call the [[CanPut]] method of A with name P.
         * 2. If Result(1) is false, return.
         * ?
         */
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 (((lengthAttr & READONLY) != 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[169]++;
            return;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[170]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[240]++;

        double d = ScriptRuntime.toNumber(val);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[241]++;
        long longVal = ScriptRuntime.toUint32(d);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[242]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((longVal != d) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[171]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[243]++;
            String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
            throw ScriptRuntime.constructError("RangeError", msg);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[172]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[244]++;
int CodeCoverConditionCoverageHelper_C57;

        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[173]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[245]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((longVal < length) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[175]++;
                // downcast okay because denseOnly
                Arrays.fill(dense, (int) longVal, dense.length, NOT_FOUND);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[246]++;
                length = longVal;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[247]++;
                return;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[176]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[248]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (32)) == 0 || true) &&
 ((longVal < MAX_PRE_GROW_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((longVal < (length * GROW_FACTOR)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((ensureCapacity((int)longVal)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 3) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[177]++;
                length = longVal;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[249]++;
                return;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[178]++;
                denseOnly = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[250]++;
            }
}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[174]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[251]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((longVal < length) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[179]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[252]++;
int CodeCoverConditionCoverageHelper_C61;
            // remove all properties between longVal and length
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((length - longVal > 0x1000) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[181]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[253]++;
                // assume that the representation is sparse
                Object[] e = getIds();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[254]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[16]++;


int CodeCoverConditionCoverageHelper_C62; // will only find in object itself
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i < e.length) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[16]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[17]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[18]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[255]++;
                    Object id = e[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[256]++;
int CodeCoverConditionCoverageHelper_C63;
                    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[183]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[257]++;
                        // > MAXINT will appear as string
                        String strId = (String)id;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[258]++;
                        long index = toArrayIndex(strId);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[259]++;
int CodeCoverConditionCoverageHelper_C64;
                        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((index >= longVal) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[185]++;
                            delete(strId);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[260]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[186]++;}

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[184]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[261]++;
                        int index = ((Integer)id).intValue();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[262]++;
int CodeCoverConditionCoverageHelper_C65;
                        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((index >= longVal) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[187]++;
                            delete(index);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[263]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[188]++;}
                    }
                }

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[182]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[264]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[19]++;


int CodeCoverConditionCoverageHelper_C66;
                // assume a dense representation
                for (long i = longVal;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[19]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[20]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[21]++;
}
                    deleteElem(this, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[265]++;
                }
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[180]++;}
        length = longVal;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[266]++;
    }

    /* Support for generic Array-ish objects.  Most of the Array
     * functions try to be generic; anything that has a length
     * property is assumed to be an array.
     * getLengthProperty returns 0 if obj does not have the length property
     * or its value is not convertible to a number.
     */
    static long getLengthProperty(Context cx, Scriptable obj) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[267]++;
int CodeCoverConditionCoverageHelper_C67;
        // These will both give numeric lengths within Uint32 range.
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((obj instanceof NativeString) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[189]++;
            return ((NativeString)obj).getLength();

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[190]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[268]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((obj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[191]++;
            return ((NativeArray)obj).getLength();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[192]++;}
}
        return ScriptRuntime.toUint32(
            ScriptRuntime.getObjectProp(obj, "length", cx));
    }

    private static Object setLengthProperty(Context cx, Scriptable target,
                                            long length)
    {
        return ScriptRuntime.setObjectProp(
                   target, "length", ScriptRuntime.wrapNumber(length), cx);
    }

    /* Utility functions to encapsulate index > Integer.MAX_VALUE
     * handling.  Also avoids unnecessary object creation that would
     * be necessary to use the general ScriptRuntime.get/setElem
     * functions... though this is probably premature optimization.
     */
    private static void deleteElem(Scriptable target, long index) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[269]++;
        int i = (int)index;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[270]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((i == index) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[193]++; target.delete(i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[271]++;
 }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[194]++; target.delete(Long.toString(index));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[272]++; }
    }

    private static Object getElem(Context cx, Scriptable target, long index)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[273]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((index > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[195]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[274]++;
            String id = Long.toString(index);
            return ScriptRuntime.getObjectProp(target, id, cx);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[196]++;
            return ScriptRuntime.getObjectIndex(target, (int)index, cx);
        }
    }

    // same as getElem, but without converting NOT_FOUND to undefined
    private static Object getRawElem(Scriptable target, long index) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[275]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((index > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[197]++;
            return ScriptableObject.getProperty(target, Long.toString(index));

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[198]++;
            return ScriptableObject.getProperty(target, (int)index);
        }
    }

    private static void setElem(Context cx, Scriptable target, long index,
                                Object value)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[276]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((index > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[199]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[277]++;
            String id = Long.toString(index);
            ScriptRuntime.setObjectProp(target, id, value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[278]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[200]++;
            ScriptRuntime.setObjectIndex(target, (int)index, value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[279]++;
        }
    }

    // Similar as setElem(), but triggers deleteElem() if value is NOT_FOUND
    private static void setRawElem(Context cx, Scriptable target, long index,
                                   Object value) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[280]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((value == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[201]++;
            deleteElem(target, index);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[281]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[202]++;
            setElem(cx, target, index, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[282]++;
        }
    }

    private static String toStringHelper(Context cx, Scriptable scope,
                                         Scriptable thisObj,
                                         boolean toSource, boolean toLocale)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[283]++;
        /* It's probably redundant to handle long lengths in this
         * function; StringBuilders are limited to 2^31 in java.
         */

        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[284]++;

        StringBuilder result = new StringBuilder(256);

        // whether to return '4,unquoted,5' or '[4, "quoted", 5]'
        String separator;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[285]++;
int CodeCoverConditionCoverageHelper_C74;

        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[203]++;
            result.append('[');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[286]++;
            separator = ", ";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[287]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[204]++;
            separator = ",";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[288]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[289]++;

        boolean haslast = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[290]++;
        long i = 0;

        boolean toplevel, iterating;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[291]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((cx.iterating == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[205]++;
            toplevel = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[292]++;
            iterating = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[293]++;
            cx.iterating = new ObjToIntMap(31);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[294]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[206]++;
            toplevel = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[295]++;
            iterating = cx.iterating.has(thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[296]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[297]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

        // Make sure cx.iterating is set to null when done
        // so we don't leak memory
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[298]++;
int CodeCoverConditionCoverageHelper_C76;
            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((iterating) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[208]++;
                cx.iterating.put(thisObj, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[299]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[300]++; // stop recursion.
                // make toSource print null and undefined values in recent versions
                boolean skipUndefinedAndNull = !toSource
                        || cx.getLanguageVersion() < Context.VERSION_1_5;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[301]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[22]++;


int CodeCoverConditionCoverageHelper_C77;
                for (i = 0;(((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[22]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[23]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[24]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[302]++;
int CodeCoverConditionCoverageHelper_C78;
                    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[210]++; result.append(separator);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[303]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[211]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[304]++;
                    Object elem = getRawElem(thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[305]++;
int CodeCoverConditionCoverageHelper_C79;
                    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (128)) == 0 || true) &&
 ((elem == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (64)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C79 |= (32)) == 0 || true) &&
 ((skipUndefinedAndNull) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((elem == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((elem == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 4) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 4) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[212]++;
                        haslast = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[306]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[307]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[213]++;}
                    haslast = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[308]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[309]++;
int CodeCoverConditionCoverageHelper_C80;

                    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[214]++;
                        result.append(ScriptRuntime.uneval(cx, scope, elem));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[310]++;


                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[215]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[311]++;
int CodeCoverConditionCoverageHelper_C81; if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((elem instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[216]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[312]++;
                        String s = (String)elem;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[313]++;
int CodeCoverConditionCoverageHelper_C82;
                        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[218]++;
                            result.append('\"');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[314]++;
                            result.append(ScriptRuntime.escapeString(s));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[315]++;
                            result.append('\"');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[316]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[219]++;
                            result.append(s);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[317]++;
                        }


                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[217]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[318]++;
int CodeCoverConditionCoverageHelper_C83;
                        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((toLocale) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[220]++;
                            Callable fun;
                            Scriptable funThis;
                            fun = ScriptRuntime.getPropFunctionAndThis(
                                      elem, "toLocaleString", cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[319]++;
                            funThis = ScriptRuntime.lastStoredScriptable(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[320]++;
                            elem = fun.call(cx, scope, funThis,
                                            ScriptRuntime.emptyArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[321]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[221]++;}
                        result.append(ScriptRuntime.toString(elem));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[322]++;
                    }
}
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[209]++;}
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[207]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[323]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((toplevel) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[222]++;
                cx.iterating = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[324]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[223]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[325]++;
int CodeCoverConditionCoverageHelper_C85;

        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((toSource) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[224]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[326]++;
int CodeCoverConditionCoverageHelper_C86;
            //for [,,].length behavior; we want toString to be symmetric.
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((haslast) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[226]++;
                result.append(", ]");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[327]++;
}
            else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[227]++;
                result.append(']');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[328]++;
}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[225]++;}
        return result.toString();
    }

    /**
     * See ECMA 15.4.4.3
     */
    private static String js_join(Context cx, Scriptable thisObj,
                                  Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[329]++;
        long llength = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[330]++;
        int length = (int)llength;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[331]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((llength != length) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[228]++;
            throw Context.reportRuntimeError1(
                "msg.arraylength.too.big", String.valueOf(llength));

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[229]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[332]++;
        // if no args, use "," as separator
        String separator = (args.length < 1 || args[0] == Undefined.instance)
                           ? ","
                           : ScriptRuntime.toString(args[0]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[333]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[230]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[334]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[335]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[232]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[336]++;
                StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[337]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[25]++;


int CodeCoverConditionCoverageHelper_C90;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[25]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[26]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[27]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[338]++;
int CodeCoverConditionCoverageHelper_C91;
                    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[234]++;
                        sb.append(separator);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[339]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[235]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[340]++;
int CodeCoverConditionCoverageHelper_C92;
                    if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((i < na.dense.length) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[236]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[341]++;
                        Object temp = na.dense[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[342]++;
int CodeCoverConditionCoverageHelper_C93;
                        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (32)) == 0 || true) &&
 ((temp != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((temp != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((temp != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 3) && false))
                        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[238]++;
                            sb.append(ScriptRuntime.toString(temp));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[343]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[239]++;}

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[237]++;}
                }
                return sb.toString();

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[233]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[231]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[344]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[240]++;
            return "";

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[241]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[345]++;
        String[] buf = new String[length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[346]++;
        int total_size = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[347]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[28]++;


int CodeCoverConditionCoverageHelper_C95;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[28]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[29]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[30]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[348]++;
            Object temp = getElem(cx, thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[349]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (8)) == 0 || true) &&
 ((temp != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((temp != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[242]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[350]++;
                String str = ScriptRuntime.toString(temp);
                total_size += str.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[351]++;
                buf[i] = str;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[352]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[243]++;}
        }
        total_size += (length - 1) * separator.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[353]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[354]++;
        StringBuilder sb = new StringBuilder(total_size);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[355]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[31]++;


int CodeCoverConditionCoverageHelper_C97;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[31]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[32]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[33]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[356]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[244]++;
                sb.append(separator);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[357]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[245]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[358]++;
            String str = buf[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[359]++;
int CodeCoverConditionCoverageHelper_C99;
            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((str != null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[246]++;
                // str == null for undefined or null
                sb.append(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[360]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[247]++;}
        }
        return sb.toString();
    }

    /**
     * See ECMA 15.4.4.4
     */
    private static Scriptable js_reverse(Context cx, Scriptable thisObj,
                                         Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[361]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[248]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[362]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[363]++;
int CodeCoverConditionCoverageHelper_C101;
            if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[250]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[364]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[34]++;


int CodeCoverConditionCoverageHelper_C102;
                for (int i=0, j=((int)na.length)-1;(((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((i < j) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false); i++,j--) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[34]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[35]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[36]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[365]++;
                    Object temp = na.dense[i];
                    na.dense[i] = na.dense[j];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[366]++;
                    na.dense[j] = temp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[367]++;
                }
                return thisObj;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[251]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[249]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[368]++;
        long len = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[369]++;

        long half = len / 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[370]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[37]++;


int CodeCoverConditionCoverageHelper_C103;
        for(long i=0;(((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((i < half) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[37]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[38]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[39]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[371]++;
            long j = len - i - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[372]++;
            Object temp1 = getRawElem(thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[373]++;
            Object temp2 = getRawElem(thisObj, j);
            setRawElem(cx, thisObj, i, temp2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[374]++;
            setRawElem(cx, thisObj, j, temp1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[375]++;
        }
        return thisObj;
    }

    /**
     * See ECMA 15.4.4.5
     */
    private static Scriptable js_sort(final Context cx, final Scriptable scope,
            final Scriptable thisObj, final Object[] args)
    {
        final Comparator<Object> comparator;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[376]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (8)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((Undefined.instance != args[0]) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[252]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[377]++;
            final Callable jsCompareFunction = ScriptRuntime
                    .getValueFunctionAndThis(args[0], cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[378]++;
            final Scriptable funThis = ScriptRuntime.lastStoredScriptable(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[379]++;
            final Object[] cmpBuf = new Object[2]; // Buffer for cmp arguments
            comparator = new Comparator<Object>() {
                public int compare(final Object x, final Object y) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[381]++;
int CodeCoverConditionCoverageHelper_C105;
                    // sort undefined to end
                    if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((x == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[254]++;
                        return y == NOT_FOUND ? 0 : 1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[255]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[382]++;
int CodeCoverConditionCoverageHelper_C106; if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((y == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[256]++;
                        return -1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[257]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[383]++;
int CodeCoverConditionCoverageHelper_C107; if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((x == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[258]++;
                        return y == Undefined.instance ? 0 : 1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[259]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[384]++;
int CodeCoverConditionCoverageHelper_C108; if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((y == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[260]++;
                        return -1;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[261]++;}
}
}
}

                    cmpBuf[0] = x;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[385]++;
                    cmpBuf[1] = y;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[386]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[387]++;
                    Object ret = jsCompareFunction.call(cx, scope, funThis,
                            cmpBuf);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[388]++;
                    final double d = ScriptRuntime.toNumber(ret);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[389]++;
int CodeCoverConditionCoverageHelper_C109;
                    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((d < 0) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[262]++;
                        return -1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[263]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[390]++;
int CodeCoverConditionCoverageHelper_C110; if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((d > 0) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[264]++;
                        return +1;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[265]++;}
}
                    return 0; // ??? double and 0???
                }
            };
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[380]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[253]++;
            comparator = new Comparator<Object>() {
                public int compare(final Object x, final Object y) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[392]++;
int CodeCoverConditionCoverageHelper_C111;
                    // sort undefined to end
                    if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((x == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[266]++;
                        return y == NOT_FOUND ? 0 : 1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[267]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[393]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((y == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[268]++;
                        return -1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[269]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[394]++;
int CodeCoverConditionCoverageHelper_C113; if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((x == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[270]++;
                        return y == Undefined.instance ? 0 : 1;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[271]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[395]++;
int CodeCoverConditionCoverageHelper_C114; if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((y == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[272]++;
                        return -1;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[273]++;}
}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[396]++;

                    final String a = ScriptRuntime.toString(x);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[397]++;
                    final String b = ScriptRuntime.toString(y);
                    return a.compareTo(b);
                }
            };
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[391]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[398]++;

        long llength = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[399]++;
        final int length = (int) llength;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[400]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((llength != length) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[274]++;
            throw Context.reportRuntimeError1(
                "msg.arraylength.too.big", String.valueOf(llength));

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[275]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[401]++;
        // copy the JS array into a working array, so it can be
        // sorted cheaply.
        final Object[] working = new Object[length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[402]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[40]++;


int CodeCoverConditionCoverageHelper_C116;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[40]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[41]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[42]++;
}
            working[i] = getRawElem(thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[403]++;
        }

        Arrays.sort(working, comparator);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[404]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[405]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[43]++;


int CodeCoverConditionCoverageHelper_C117;

        // copy the working array back into thisObj
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[43]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[44]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[45]++;
}
            setRawElem(cx, thisObj, i, working[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[406]++;
        }

        return thisObj;
    }

    /**
     * Non-ECMA methods.
     */

    private static Object js_push(Context cx, Scriptable thisObj,
                                  Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[407]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[276]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[408]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[409]++;
int CodeCoverConditionCoverageHelper_C119;
            if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (8)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((na.ensureCapacity((int) na.length + args.length)) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[278]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[410]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[46]++;


int CodeCoverConditionCoverageHelper_C120;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[46]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[47]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[48]++;
}
                    na.dense[(int)na.length++] = args[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[411]++;
                }
                return ScriptRuntime.wrapNumber(na.length);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[279]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[277]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[412]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[413]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[49]++;


int CodeCoverConditionCoverageHelper_C121;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[49]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[50]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[51]++;
}
            setElem(cx, thisObj, length + i, args[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[414]++;
        }

        length += args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[415]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[416]++;
        Object lengthObj = setLengthProperty(cx, thisObj, length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[417]++;
int CodeCoverConditionCoverageHelper_C122;

        /*
         * If JS1.2, follow Perl4 by returning the last thing pushed.
         * Otherwise, return the new array length.
         */
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[280]++;
            // if JS1.2 && no arguments, return undefined.
            return args.length == 0
                ? Undefined.instance
                : args[args.length - 1];
}

        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[281]++;
            return lengthObj;
}
    }

    private static Object js_pop(Context cx, Scriptable thisObj,
                                 Object[] args)
    {
        Object result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[418]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[282]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[419]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[420]++;
int CodeCoverConditionCoverageHelper_C124;
            if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (8)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((na.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[284]++;
                na.length--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[421]++;
                result = na.dense[(int)na.length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[422]++;
                na.dense[(int)na.length] = NOT_FOUND;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[423]++;
                return result;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[285]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[283]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[424]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[425]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((length > 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[286]++;
            length--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[426]++;

            // Get the to-be-deleted property's value.
            result = getElem(cx, thisObj, length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[427]++;


            // We don't need to delete the last property, because
            // setLength does that for us.
        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[287]++;
            result = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[428]++;
        }
        // necessary to match js even when length < 0; js pop will give a
        // length property to any target it is called on.
        setLengthProperty(cx, thisObj, length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[429]++;

        return result;
    }

    private static Object js_shift(Context cx, Scriptable thisObj,
                                   Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[430]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[288]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[431]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[432]++;
int CodeCoverConditionCoverageHelper_C127;
            if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (8)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((na.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[290]++;
                na.length--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[433]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[434]++;
                Object result = na.dense[0];
                System.arraycopy(na.dense, 1, na.dense, 0, (int)na.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[435]++;
                na.dense[(int)na.length] = NOT_FOUND;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[436]++;
                return result == NOT_FOUND ? Undefined.instance : result;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[291]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[289]++;}
        Object result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[437]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[438]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((length > 0) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[292]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[439]++;
            long i = 0;
            length--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[440]++;

            // Get the to-be-deleted property's value.
            result = getElem(cx, thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[441]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[442]++;
int CodeCoverConditionCoverageHelper_C129;

            /*
             * Slide down the array above the first element.  Leave i
             * set to point to the last element.
             */
            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((length > 0) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[294]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[443]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[52]++;


int CodeCoverConditionCoverageHelper_C130;
                for (i = 1;(((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((i <= length) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[52]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[53]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[54]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[444]++;
                    Object temp = getRawElem(thisObj, i);
                    setRawElem(cx, thisObj, i - 1, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[445]++;
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[295]++;}

            // We don't need to delete the last property, because
            // setLength does that for us.
        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[293]++;
            result = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[446]++;
        }
        setLengthProperty(cx, thisObj, length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[447]++;
        return result;
    }

    private static Object js_unshift(Context cx, Scriptable thisObj,
                                     Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[448]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[296]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[449]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[450]++;
int CodeCoverConditionCoverageHelper_C132;
            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (8)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((na.ensureCapacity((int)na.length + args.length)) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 2) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[298]++;
                System.arraycopy(na.dense, 0, na.dense, args.length,
                                 (int) na.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[451]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[452]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[55]++;


int CodeCoverConditionCoverageHelper_C133;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[55]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[56]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[57]++;
}
                    na.dense[i] = args[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[453]++;
                }
                na.length += args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[454]++;
                return ScriptRuntime.wrapNumber(na.length);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[299]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[297]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[455]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[456]++;
        int argc = args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[457]++;
int CodeCoverConditionCoverageHelper_C134;

        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((args.length > 0) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[300]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[458]++;
int CodeCoverConditionCoverageHelper_C135;
            /*  Slide up the array to make room for args at the bottom */
            if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((length > 0) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[302]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[459]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[58]++;


int CodeCoverConditionCoverageHelper_C136;
                for (long last = length - 1;(((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((last >= 0) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false); last--) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[58]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[59]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[60]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[460]++;
                    Object temp = getRawElem(thisObj, last);
                    setRawElem(cx, thisObj, last + argc, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[461]++;
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[303]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[462]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[61]++;


int CodeCoverConditionCoverageHelper_C137;

            /* Copy from argv to the bottom of the array. */
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[61]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[62]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[63]++;
}
                setElem(cx, thisObj, i, args[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[463]++;
            }

            /* Follow Perl by returning the new array length. */
            length += args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[464]++;
            return setLengthProperty(cx, thisObj, length);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[301]++;}
        return ScriptRuntime.wrapNumber(length);
    }

    private static Object js_splice(Context cx, Scriptable scope,
                                    Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[465]++;
    	NativeArray na = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[466]++;
    	boolean denseMode = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[467]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[304]++;
            na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[468]++;
            denseMode = na.denseOnly;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[469]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[305]++;}

        /* create an empty Array to return. */
        scope = getTopLevelScope(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[470]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[471]++;
        int argc = args.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[472]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((argc == 0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[306]++;
            return cx.newArray(scope, 0);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[307]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[473]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[474]++;

        /* Convert the first argument into a starting index. */
        long begin = toSliceIndex(ScriptRuntime.toInteger(args[0]), length);
        argc--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[475]++;

        /* Convert the second argument into count */
        long count;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[476]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[308]++;
            count = length - begin;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[477]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[309]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[478]++;
            double dcount = ScriptRuntime.toInteger(args[1]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[479]++;
int CodeCoverConditionCoverageHelper_C141;
            if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((dcount < 0) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[310]++;
                count = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[480]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[311]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[481]++;
int CodeCoverConditionCoverageHelper_C142; if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((dcount > (length - begin)) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[312]++;
                count = length - begin;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[482]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[313]++;
                count = (long)dcount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[483]++;
            }
}
            argc--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[484]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[485]++;

        long end = begin + count;

        /* If there are elements to remove, put them into the return value. */
        Object result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[486]++;
int CodeCoverConditionCoverageHelper_C143;
        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[314]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[487]++;
int CodeCoverConditionCoverageHelper_C144;
            if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 ((count == 1) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[316]++;
                /*
                 * JS lacks "list context", whereby in Perl one turns the
                 * single scalar that's spliced out into an array just by
                 * assigning it to @single instead of $single, or by using it
                 * as Perl push's first argument, for instance.
                 *
                 * JS1.2 emulated Perl too closely and returned a non-Array for
                 * the single-splice-out case, requiring callers to test and
                 * wrap in [] if necessary.  So JS1.3, default, and other
                 * versions all return an array of length 1 for uniformity.
                 */
                result = getElem(cx, thisObj, begin);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[488]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[317]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[489]++;
int CodeCoverConditionCoverageHelper_C145;
            	if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((denseMode) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[318]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[490]++;
                    int intLen = (int) (end - begin);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[491]++;
                    Object[] copy = new Object[intLen];
                    System.arraycopy(na.dense, (int) begin, copy, 0, intLen);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[492]++;
                    result = cx.newArray(scope, copy);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[493]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[319]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[494]++;
                    Scriptable resultArray = cx.newArray(scope, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[495]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[64]++;


int CodeCoverConditionCoverageHelper_C146;
                    for (long last = begin;(((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((last != end) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false); last++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[64]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[65]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[66]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[496]++;
                        Object temp = getRawElem(thisObj, last);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[497]++;
int CodeCoverConditionCoverageHelper_C147;
                        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((temp != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[320]++;
                            setElem(cx, resultArray, last - begin, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[498]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[321]++;}
                    }
                    // Need to set length for sparse result array
                    setLengthProperty(cx, resultArray, end - begin);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[499]++;
                    result = resultArray;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[500]++;
            	}
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[315]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[501]++;
int CodeCoverConditionCoverageHelper_C148; // (count == 0)
        	if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((cx.getLanguageVersion() == Context.VERSION_1_2) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[322]++;
                /* Emulate C JS1.2; if no elements are removed, return undefined. */
                result = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[502]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[323]++;
                result = cx.newArray(scope, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[503]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[504]++;

        /* Find the direction (up or down) to copy and make way for argv. */
        long delta = argc - count;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[505]++;
int CodeCoverConditionCoverageHelper_C149;
        if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (32)) == 0 || true) &&
 ((denseMode) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C149 |= (8)) == 0 || true) &&
 ((length + delta < Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((na.ensureCapacity((int) (length + delta))) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 3) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[324]++;
            System.arraycopy(na.dense, (int) end, na.dense,
                             (int) (begin + argc), (int) (length - end));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[506]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[507]++;
int CodeCoverConditionCoverageHelper_C150;
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((argc > 0) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[326]++;
                System.arraycopy(args, 2, na.dense, (int) begin, argc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[508]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[327]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[509]++;
int CodeCoverConditionCoverageHelper_C151;
            if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((delta < 0) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[328]++;
                Arrays.fill(na.dense, (int) (length + delta), (int) length,
                            NOT_FOUND);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[510]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[329]++;}
            na.length = length + delta;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[511]++;
            return result;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[325]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[512]++;
int CodeCoverConditionCoverageHelper_C152;

        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((delta > 0) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[330]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[513]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[67]++;


int CodeCoverConditionCoverageHelper_C153;
            for (long last = length - 1;(((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((last >= end) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false); last--) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[67]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[68]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[69]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[514]++;
                Object temp = getRawElem(thisObj, last);
                setRawElem(cx, thisObj, last + delta, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[515]++;
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[331]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[516]++;
int CodeCoverConditionCoverageHelper_C154; if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((delta < 0) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[332]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[517]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[70]++;


int CodeCoverConditionCoverageHelper_C155;
            for (long last = end;(((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((last < length) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false); last++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[70]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[71]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[72]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[518]++;
                Object temp = getRawElem(thisObj, last);
                setRawElem(cx, thisObj, last + delta, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[519]++;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[333]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[520]++;

        /* Copy from argv into the hole to complete the splice. */
        int argoffset = args.length - argc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[521]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[73]++;


int CodeCoverConditionCoverageHelper_C156;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((i < argc) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[73]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[74]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[75]++;
}
            setElem(cx, thisObj, begin + i, args[i + argoffset]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[522]++;
        }

        /* Update length in case we deleted elements from the end. */
        setLengthProperty(cx, thisObj, length + delta);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[523]++;
        return result;
    }

    /*
     * See Ecma 262v3 15.4.4.4
     */
    private static Scriptable js_concat(Context cx, Scriptable scope,
                                        Scriptable thisObj, Object[] args)
    {
        // create an empty Array to return.
        scope = getTopLevelScope(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[524]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[525]++;
        Function ctor = ScriptRuntime.getExistingCtor(cx, scope, "Array");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[526]++;
        Scriptable result = ctor.construct(cx, scope, ScriptRuntime.emptyArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[527]++;
int CodeCoverConditionCoverageHelper_C157;
        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (8)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((result instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[334]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[528]++;
            NativeArray denseThis = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[529]++;
            NativeArray denseResult = (NativeArray) result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[530]++;
int CodeCoverConditionCoverageHelper_C158;
            if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (8)) == 0 || true) &&
 ((denseThis.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((denseResult.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[336]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[531]++;
                // First calculate length of resulting array
                boolean canUseDense = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[532]++;
                int length = (int) denseThis.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[533]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[76]++;


int CodeCoverConditionCoverageHelper_C159;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (8)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((canUseDense) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[76]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[77]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[78]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[534]++;
int CodeCoverConditionCoverageHelper_C160;
                    if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((args[i] instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[338]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[535]++;
                        // only try to use dense approach for Array-like
                        // objects that are actually NativeArrays
                        final NativeArray arg = (NativeArray) args[i];
                        canUseDense = arg.denseOnly;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[536]++;
                        length += arg.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[537]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[339]++;
                        length++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[538]++;
                    }
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[539]++;
int CodeCoverConditionCoverageHelper_C161;
                if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (8)) == 0 || true) &&
 ((canUseDense) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((denseResult.ensureCapacity(length)) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[340]++;
                    System.arraycopy(denseThis.dense, 0, denseResult.dense,
                                     0, (int) denseThis.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[540]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[541]++;
                    int cursor = (int) denseThis.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[542]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[79]++;


int CodeCoverConditionCoverageHelper_C162;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((canUseDense) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[79]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[80]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[81]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[543]++;
int CodeCoverConditionCoverageHelper_C163;
                        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((args[i] instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[342]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[544]++;
                            NativeArray arg = (NativeArray) args[i];
                            System.arraycopy(arg.dense, 0,
                                    denseResult.dense, cursor,
                                    (int)arg.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[545]++;
                            cursor += (int)arg.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[546]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[343]++;
                            denseResult.dense[cursor++] = args[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[547]++;
                        }
                    }
                    denseResult.length = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[548]++;
                    return result;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[341]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[337]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[335]++;}

        long length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[549]++;
        long slot = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[550]++;
int CodeCoverConditionCoverageHelper_C164;

        /* Put the target in the result array; only add it as an array
         * if it looks like one.
         */
        if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((ScriptRuntime.instanceOf(thisObj, ctor, cx)) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[344]++;
            length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[551]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[552]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[82]++;


int CodeCoverConditionCoverageHelper_C165;

            // Copy from the target object into the result
            for (slot = 0;(((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((slot < length) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false); slot++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[82]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[83]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[84]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[553]++;
                Object temp = getRawElem(thisObj, slot);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[554]++;
int CodeCoverConditionCoverageHelper_C166;
                if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((temp != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[346]++;
                    setElem(cx, result, slot, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[555]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[347]++;}
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[345]++;
            setElem(cx, result, slot++, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[556]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[557]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[85]++;


int CodeCoverConditionCoverageHelper_C167;

        /* Copy from the arguments into the result.  If any argument
         * has a numeric length property, treat it as an array and add
         * elements separately; otherwise, just copy the argument.
         */
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[85]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[86]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[87]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[558]++;
int CodeCoverConditionCoverageHelper_C168;
            if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((ScriptRuntime.instanceOf(args[i], ctor, cx)) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[348]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[559]++;
                // ScriptRuntime.instanceOf => instanceof Scriptable
                Scriptable arg = (Scriptable)args[i];
                length = getLengthProperty(cx, arg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[560]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[561]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[88]++;


int CodeCoverConditionCoverageHelper_C169;
                for (long j = 0;(((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((j < length) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false); j++, slot++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[88]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[89]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[90]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[562]++;
                    Object temp = getRawElem(arg, j);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[563]++;
int CodeCoverConditionCoverageHelper_C170;
                    if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((temp != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[350]++;
                        setElem(cx, result, slot, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[564]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[351]++;}
                }

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[349]++;
                setElem(cx, result, slot++, args[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[565]++;
            }
        }
        setLengthProperty(cx, result, slot);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[566]++;
        return result;
    }

    private Scriptable js_slice(Context cx, Scriptable thisObj,
                                Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[567]++;
        Scriptable scope = getTopLevelScope(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[568]++;
        Scriptable result = cx.newArray(scope, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[569]++;
        long length = getLengthProperty(cx, thisObj);

        long begin, end;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[570]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[352]++;
            begin = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[571]++;
            end = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[572]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[353]++;
            begin = toSliceIndex(ScriptRuntime.toInteger(args[0]), length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[573]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[574]++;
int CodeCoverConditionCoverageHelper_C172;
            if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[354]++;
                end = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[575]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[355]++;
                end = toSliceIndex(ScriptRuntime.toInteger(args[1]), length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[576]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[577]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[91]++;


int CodeCoverConditionCoverageHelper_C173;

        for (long slot = begin;(((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((slot < end) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false); slot++) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[91]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[92]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[93]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[578]++;
            Object temp = getRawElem(thisObj, slot);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[579]++;
int CodeCoverConditionCoverageHelper_C174;
            if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((temp != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[356]++;
                setElem(cx, result, slot - begin, temp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[580]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[357]++;}
        }
        setLengthProperty(cx, result, Math.max(0, end - begin));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[581]++;

        return result;
    }

    private static long toSliceIndex(double value, long length) {
        long result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[582]++;
int CodeCoverConditionCoverageHelper_C175;
        if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((value < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[358]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[583]++;
int CodeCoverConditionCoverageHelper_C176;
            if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((value + length < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[360]++;
                result = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[584]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[361]++;
                result = (long)(value + length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[585]++;
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[359]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[586]++;
int CodeCoverConditionCoverageHelper_C177; if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((value > length) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[362]++;
            result = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[587]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[363]++;
            result = (long)value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[588]++;
        }
}
        return result;
    }

    /**
     * Implements the methods "indexOf" and "lastIndexOf".
     */
    private Object indexOfHelper(Context cx, Scriptable thisObj,
                                 Object[] args, boolean isLast)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[589]++;
        Object compareTo = args.length > 0 ? args[0] : Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[590]++;
        long length = getLengthProperty(cx, thisObj);
        long start;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[591]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((isLast) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[364]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[592]++;
int CodeCoverConditionCoverageHelper_C179;
            // lastIndexOf
            /*
             * From http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Objects:Array:lastIndexOf
             * The index at which to start searching backwards. Defaults to the
             * array's length, i.e. the whole array will be searched. If the
             * index is greater than or equal to the length of the array, the
             * whole array will be searched. If negative, it is taken as the
             * offset from the end of the array. Note that even when the index
             * is negative, the array is still searched from back to front. If
             * the calculated index is less than 0, -1 is returned, i.e. the
             * array will not be searched.
             */
            if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((args.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[366]++;
                // default
                start = length-1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[593]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[367]++;
                start = (long)ScriptRuntime.toInteger(args[1]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[594]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[595]++;
int CodeCoverConditionCoverageHelper_C180;
                if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((start >= length) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[368]++;
                    start = length-1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[596]++;
}
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[369]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[597]++;
int CodeCoverConditionCoverageHelper_C181; if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[370]++;
                    start += length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[598]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[371]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[599]++;
int CodeCoverConditionCoverageHelper_C182;
                if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[372]++; return NEGATIVE_ONE;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[373]++;}
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[365]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[600]++;
int CodeCoverConditionCoverageHelper_C183;
            // indexOf
            /*
             * From http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Objects:Array:indexOf
             * The index at which to begin the search. Defaults to 0, i.e. the
             * whole array will be searched. If the index is greater than or
             * equal to the length of the array, -1 is returned, i.e. the array
             * will not be searched. If negative, it is taken as the offset from
             * the end of the array. Note that even when the index is negative,
             * the array is still searched from front to back. If the calculated
             * index is less than 0, the whole array will be searched.
             */
            if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((args.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[374]++;
                // default
                start = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[601]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[375]++;
                start = (long)ScriptRuntime.toInteger(args[1]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[602]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[603]++;
int CodeCoverConditionCoverageHelper_C184;
                if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[376]++;
                    start += length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[604]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[605]++;
int CodeCoverConditionCoverageHelper_C185;
                    if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[378]++;
                        start = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[606]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[379]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[377]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[607]++;
int CodeCoverConditionCoverageHelper_C186;
                if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((start > length - 1) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[380]++; return NEGATIVE_ONE;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[381]++;}
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[608]++;
int CodeCoverConditionCoverageHelper_C187;
        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[382]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[609]++;
            NativeArray na = (NativeArray) thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[610]++;
int CodeCoverConditionCoverageHelper_C188;
            if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((na.denseOnly) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[384]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[611]++;
int CodeCoverConditionCoverageHelper_C189;
                if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((isLast) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[386]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[612]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[94]++;


int CodeCoverConditionCoverageHelper_C190;
                  for (int i=(int)start;(((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[94]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[95]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[96]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[613]++;
int CodeCoverConditionCoverageHelper_C191;
                      if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (8)) == 0 || true) &&
 ((na.dense[i] != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((ScriptRuntime.shallowEq(na.dense[i], compareTo)) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 2) && false))
                      {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[388]++;
                          return Long.valueOf(i);

                      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[389]++;}
                  }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[387]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[614]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[97]++;


int CodeCoverConditionCoverageHelper_C192;
                  for (int i=(int)start;(((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[97]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[98]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[99]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[615]++;
int CodeCoverConditionCoverageHelper_C193;
                      if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (8)) == 0 || true) &&
 ((na.dense[i] != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((ScriptRuntime.shallowEq(na.dense[i], compareTo)) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 2) && false))
                      {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[390]++;
                          return Long.valueOf(i);

                      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[391]++;}
                  }
                }
                return NEGATIVE_ONE;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[385]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[383]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[616]++;
int CodeCoverConditionCoverageHelper_C194;
        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((isLast) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[392]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[617]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[100]++;


int CodeCoverConditionCoverageHelper_C195;
          for (long i=start;(((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[100]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[101]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[102]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[618]++;
              Object val = getRawElem(thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[619]++;
int CodeCoverConditionCoverageHelper_C196;
              if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (8)) == 0 || true) &&
 ((val != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((ScriptRuntime.shallowEq(val, compareTo)) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[394]++;
                  return Long.valueOf(i);

              } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[395]++;}
          }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[393]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[620]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[103]++;


int CodeCoverConditionCoverageHelper_C197;
          for (long i=start;(((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[103]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[104]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[105]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[621]++;
              Object val = getRawElem(thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[622]++;
int CodeCoverConditionCoverageHelper_C198;
              if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (8)) == 0 || true) &&
 ((val != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((ScriptRuntime.shallowEq(val, compareTo)) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[396]++;
                  return Long.valueOf(i);

              } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[397]++;}
          }
        }
        return NEGATIVE_ONE;
    }

    /**
     * Implements the methods "every", "filter", "forEach", "map", and "some".
     */
    private Object iterativeMethod(Context cx, int id, Scriptable scope,
                                   Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[623]++;
        Object callbackArg = args.length > 0 ? args[0] : Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[624]++;
int CodeCoverConditionCoverageHelper_C199;
        if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (8)) == 0 || true) &&
 ((callbackArg == null) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((callbackArg instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[398]++;
            throw ScriptRuntime.notFunctionError(callbackArg);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[399]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[625]++;
        Function f = (Function) callbackArg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[626]++;
        Scriptable parent = ScriptableObject.getTopLevelScope(f);
        Scriptable thisArg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[627]++;
int CodeCoverConditionCoverageHelper_C200;
        if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (32)) == 0 || true) &&
 ((args.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C200 |= (8)) == 0 || true) &&
 ((args[1] == null) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((args[1] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 3) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[400]++;
            thisArg = parent;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[628]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[401]++;
            thisArg = ScriptRuntime.toObject(cx, scope, args[1]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[629]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[630]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[631]++;
        int resultLength = id == Id_map ? (int) length : 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[632]++;
        Scriptable array = cx.newArray(scope, resultLength);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[633]++;
        long j=0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[634]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[106]++;


int CodeCoverConditionCoverageHelper_C201;
        for (long i=0;(((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[106]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[107]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[108]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[635]++;
            Object[] innerArgs = new Object[3];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[636]++;
            Object elem = getRawElem(thisObj, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[637]++;
int CodeCoverConditionCoverageHelper_C202;
            if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((elem == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[402]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[638]++;
                continue;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[403]++;}
            innerArgs[0] = elem;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[639]++;
            innerArgs[1] = Long.valueOf(i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[640]++;
            innerArgs[2] = thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[641]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[642]++;
            Object result = f.call(cx, parent, thisArg, innerArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[643]++;
            switch (id) {
              case Id_every:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[404]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[644]++;
int CodeCoverConditionCoverageHelper_C203;
                if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((ScriptRuntime.toBoolean(result)) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[405]++;
                    return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[406]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[645]++;
                break;
              case Id_filter:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[407]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[646]++;
int CodeCoverConditionCoverageHelper_C204;
                if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((ScriptRuntime.toBoolean(result)) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[408]++;
                  setElem(cx, array, j++, innerArgs[0]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[647]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[409]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[648]++;
                break;
              case Id_forEach:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[410]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[649]++;
                break;
              case Id_map:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[411]++;
                setElem(cx, array, i, result);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[650]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[651]++;
                break;
              case Id_some:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[412]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[652]++;
int CodeCoverConditionCoverageHelper_C205;
                if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((ScriptRuntime.toBoolean(result)) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[413]++;
                    return Boolean.TRUE;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[414]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[653]++;
                break; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[415]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[654]++;
        switch (id) {
          case Id_every:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[416]++;
            return Boolean.TRUE;
          case Id_filter:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[417]++;
          case Id_map:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[418]++;
            return array;
          case Id_some:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[419]++;
            return Boolean.FALSE;
          case Id_forEach:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[420]++;
          default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[421]++;
            return Undefined.instance;
        }
    }

    /**
     * Implements the methods "reduce" and "reduceRight".
     */
    private Object reduceMethod(Context cx, int id, Scriptable scope,
                                   Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[655]++;
        Object callbackArg = args.length > 0 ? args[0] : Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[656]++;
int CodeCoverConditionCoverageHelper_C206;
        if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (8)) == 0 || true) &&
 ((callbackArg == null) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((callbackArg instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[422]++;
            throw ScriptRuntime.notFunctionError(callbackArg);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[423]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[657]++;
        Function f = (Function) callbackArg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[658]++;
        Scriptable parent = ScriptableObject.getTopLevelScope(f);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[659]++;
        long length = getLengthProperty(cx, thisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[660]++;
        // hack to serve both reduce and reduceRight with the same loop
        boolean movingLeft = id == Id_reduce;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[661]++;
        Object value = args.length > 1 ? args[1] : Scriptable.NOT_FOUND;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[662]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[109]++;


int CodeCoverConditionCoverageHelper_C207;
        for (long i = 0;(((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[109]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[110]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[111]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[663]++;
            long index = movingLeft ? i : (length - 1 - i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[664]++;
            Object elem = getRawElem(thisObj, index);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[665]++;
int CodeCoverConditionCoverageHelper_C208;
            if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((elem == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[424]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[666]++;
                continue;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[425]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[667]++;
int CodeCoverConditionCoverageHelper_C209;
            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[426]++;
                // no initial value passed, use first element found as inital value
                value = elem;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[668]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[427]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[669]++;
                Object[] innerArgs = { value, elem, index, thisObj };
                value = f.call(cx, parent, parent, innerArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[670]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[671]++;
int CodeCoverConditionCoverageHelper_C210;
        if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[428]++;
            // reproduce spidermonkey error message
            throw ScriptRuntime.typeError0("msg.empty.array.reduce");

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[429]++;}
        return value;
    }

    // methods to implement java.util.List

    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    public Object[] toArray() {
        return toArray(ScriptRuntime.emptyArgs);
    }

    public Object[] toArray(Object[] a) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[672]++;
        long longLen = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[673]++;
int CodeCoverConditionCoverageHelper_C211;
        if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((longLen > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[430]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[431]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[674]++;
        int len = (int) longLen;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[675]++;
        Object[] array = a.length >= len ?
                a : (Object[]) java.lang.reflect.Array
                .newInstance(a.getClass().getComponentType(), len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[676]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[112]++;


int CodeCoverConditionCoverageHelper_C212;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[112]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[113]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[114]++;
}
            array[i] = get(i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[677]++;
        }
        return array;
    }

    public boolean containsAll(Collection c) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[678]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[115]++;


        for (Object aC : c) { 
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[115]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[116]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[117]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[679]++;
int CodeCoverConditionCoverageHelper_C213;
            if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((contains(aC)) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[432]++;
                return false;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[433]++;}
  }
        return true;
    }

    public int size() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[680]++;
        long longLen = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[681]++;
int CodeCoverConditionCoverageHelper_C214;
        if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((longLen > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[434]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[435]++;}
        return (int) longLen;
    }

    public Object get(long index) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[682]++;
int CodeCoverConditionCoverageHelper_C215;
        if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((index >= length) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[436]++;
            throw new IndexOutOfBoundsException();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[437]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[683]++;
        Object value = getRawElem(this, index);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[684]++;
int CodeCoverConditionCoverageHelper_C216;
        if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (8)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[438]++;
            return null;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[439]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[685]++;
int CodeCoverConditionCoverageHelper_C217; if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[440]++;
            return ((Wrapper) value).unwrap();

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[441]++;
            return value;
        }
}
    }

    public Object get(int index) {
        return get((long) index);
    }

    public int indexOf(Object o) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[686]++;
        long longLen = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[687]++;
int CodeCoverConditionCoverageHelper_C218;
        if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((longLen > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[442]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[443]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[688]++;
        int len = (int) longLen;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[689]++;
int CodeCoverConditionCoverageHelper_C219;
        if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((o == null) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[444]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[690]++;
byte CodeCoverTryBranchHelper_L40 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[118]++;


int CodeCoverConditionCoverageHelper_C220;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L40 == 0) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[118]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[119]++;
} else if (CodeCoverTryBranchHelper_L40 == 1) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[119]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[120]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[691]++;
int CodeCoverConditionCoverageHelper_C221;
                if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((get(i) == null) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[446]++;
                    return i;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[447]++;}
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[445]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[692]++;
byte CodeCoverTryBranchHelper_L41 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[121]++;


int CodeCoverConditionCoverageHelper_C222;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L41 == 0) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[121]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[122]++;
} else if (CodeCoverTryBranchHelper_L41 == 1) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[122]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[123]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[693]++;
int CodeCoverConditionCoverageHelper_C223;
                if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((o.equals(get(i))) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[448]++;
                    return i;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[449]++;}
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[694]++;
        long longLen = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[695]++;
int CodeCoverConditionCoverageHelper_C224;
        if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((longLen > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[450]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[451]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[696]++;
        int len = (int) longLen;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[697]++;
int CodeCoverConditionCoverageHelper_C225;
        if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((o == null) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[452]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[698]++;
byte CodeCoverTryBranchHelper_L42 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[124]++;


int CodeCoverConditionCoverageHelper_C226;
            for (int i = len - 1;(((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L42 == 0) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[124]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[125]++;
} else if (CodeCoverTryBranchHelper_L42 == 1) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[125]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[126]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[699]++;
int CodeCoverConditionCoverageHelper_C227;
                if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((get(i) == null) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[454]++;
                    return i;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[455]++;}
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[453]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[700]++;
byte CodeCoverTryBranchHelper_L43 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[127]++;


int CodeCoverConditionCoverageHelper_C228;
            for (int i = len - 1;(((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L43 == 0) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[127]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[128]++;
} else if (CodeCoverTryBranchHelper_L43 == 1) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[128]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.loops[129]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[701]++;
int CodeCoverConditionCoverageHelper_C229;
                if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((o.equals(get(i))) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[456]++;
                    return i;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[457]++;}
            }
        }
        return -1;
    }

    public Iterator iterator() {
        return listIterator(0);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public ListIterator listIterator(final int start) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[702]++;
        long longLen = length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[703]++;
int CodeCoverConditionCoverageHelper_C230;
        if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((longLen > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[458]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[459]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[704]++;
        final int len = (int) longLen;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[705]++;
int CodeCoverConditionCoverageHelper_C231;

        if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (8)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((start > len) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[460]++;
            throw new IndexOutOfBoundsException("Index: " + start);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[461]++;}

        return new ListIterator() {

            int cursor = start;

            public boolean hasNext() {
                return cursor < len;
            }

            public Object next() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[706]++;
int CodeCoverConditionCoverageHelper_C232;
                if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((cursor == len) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[462]++;
                    throw new NoSuchElementException();

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[463]++;}
                return get(cursor++);
            }

            public boolean hasPrevious() {
                return cursor > 0;
            }

            public Object previous() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[707]++;
int CodeCoverConditionCoverageHelper_C233;
                if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((cursor == 0) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[464]++;
                    throw new NoSuchElementException();

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[465]++;}
                return get(--cursor);
            }

            public int nextIndex() {
                return cursor;
            }

            public int previousIndex() {
                return cursor - 1;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void add(Object o) {
                throw new UnsupportedOperationException();
            }

            public void set(Object o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    public Object set(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    public Object remove(int index) {
        throw new UnsupportedOperationException();
    }

    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2005-09-26 15:47:42 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[708]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[709]++; String X = null; int c;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[710]++;
            L: switch (s.length()) {
            case 3:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[466]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[711]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[712]++;
int CodeCoverConditionCoverageHelper_C234;
                if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((c=='m') && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[467]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[713]++;
int CodeCoverConditionCoverageHelper_C235; if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='p') && 
  ((CodeCoverConditionCoverageHelper_C235 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='a') && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[469]++;id=Id_map;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[714]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[715]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[470]++;}
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[468]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[716]++;
int CodeCoverConditionCoverageHelper_C236; if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((c=='p') && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[471]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[717]++;
int CodeCoverConditionCoverageHelper_C237; if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (8)) == 0 || true) &&
 ((s.charAt(2)=='p') && 
  ((CodeCoverConditionCoverageHelper_C237 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((s.charAt(1)=='o') && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[473]++;id=Id_pop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[718]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[719]++; break L0;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[474]++;}
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[472]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[720]++;
                break L;
            case 4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[475]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[721]++; switch (s.charAt(2)) {
                case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[476]++; X="join";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[722]++;id=Id_join;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[723]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[724]++; break L;
                case 'm':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[477]++; X="some";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[725]++;id=Id_some;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[726]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[727]++; break L;
                case 'r':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[478]++; X="sort";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[728]++;id=Id_sort;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[729]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[730]++; break L;
                case 's':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[479]++; X="push";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[731]++;id=Id_push;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[732]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[733]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[480]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[734]++; break L;
            case 5:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[481]++; c=s.charAt(1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[735]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[736]++;
int CodeCoverConditionCoverageHelper_C238;
                if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((c=='h') && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[482]++; X="shift";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[737]++;id=Id_shift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[738]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[483]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[739]++;
int CodeCoverConditionCoverageHelper_C239; if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[484]++; X="slice";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[740]++;id=Id_slice;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[741]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[485]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[742]++;
int CodeCoverConditionCoverageHelper_C240; if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((c=='v') && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[486]++; X="every";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[743]++;id=Id_every;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[744]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[487]++;}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[745]++;
                break L;
            case 6:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[488]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[746]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[747]++;
int CodeCoverConditionCoverageHelper_C241;
                if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[489]++; X="concat";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[748]++;id=Id_concat;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[749]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[490]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[750]++;
int CodeCoverConditionCoverageHelper_C242; if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((c=='f') && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[491]++; X="filter";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[751]++;id=Id_filter;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[752]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[492]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[753]++;
int CodeCoverConditionCoverageHelper_C243; if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((c=='s') && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[493]++; X="splice";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[754]++;id=Id_splice;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[755]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[494]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[756]++;
int CodeCoverConditionCoverageHelper_C244; if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[495]++; X="reduce";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[757]++;id=Id_reduce;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[758]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[496]++;}
}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[759]++;
                break L;
            case 7:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[497]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[760]++; switch (s.charAt(0)) {
                case 'f':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[498]++; X="forEach";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[761]++;id=Id_forEach;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[762]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[763]++; break L;
                case 'i':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[499]++; X="indexOf";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[764]++;id=Id_indexOf;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[765]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[766]++; break L;
                case 'r':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[500]++; X="reverse";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[767]++;id=Id_reverse;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[768]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[769]++; break L;
                case 'u':
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[501]++; X="unshift";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[770]++;id=Id_unshift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[771]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[772]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[502]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[773]++; break L;
            case 8:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[503]++; c=s.charAt(3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[774]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[775]++;
int CodeCoverConditionCoverageHelper_C245;
                if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[504]++; X="toSource";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[776]++;id=Id_toSource;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[777]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[505]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[778]++;
int CodeCoverConditionCoverageHelper_C246; if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[506]++; X="toString";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[779]++;id=Id_toString;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[780]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[507]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[781]++;
                break L;
            case 11:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[508]++; c=s.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[782]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[783]++;
int CodeCoverConditionCoverageHelper_C247;
                if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((c=='c') && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[509]++; X="constructor";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[784]++;id=Id_constructor;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[785]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[510]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[786]++;
int CodeCoverConditionCoverageHelper_C248; if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[511]++; X="lastIndexOf";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[787]++;id=Id_lastIndexOf;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[788]++;
 }
                else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[512]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[789]++;
int CodeCoverConditionCoverageHelper_C249; if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((c=='r') && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[513]++; X="reduceRight";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[790]++;id=Id_reduceRight;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[791]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[514]++;}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[792]++;
                break L;
            case 14:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[515]++; X="toLocaleString";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[793]++;id=Id_toLocaleString;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[794]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[795]++; break L; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[516]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[796]++;
int CodeCoverConditionCoverageHelper_C250;
            if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C250 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[517]++; id = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[797]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.branches[518]++;}
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor          = 1,
        Id_toString             = 2,
        Id_toLocaleString       = 3,
        Id_toSource             = 4,
        Id_join                 = 5,
        Id_reverse              = 6,
        Id_sort                 = 7,
        Id_push                 = 8,
        Id_pop                  = 9,
        Id_shift                = 10,
        Id_unshift              = 11,
        Id_splice               = 12,
        Id_concat               = 13,
        Id_slice                = 14,
        Id_indexOf              = 15,
        Id_lastIndexOf          = 16,
        Id_every                = 17,
        Id_filter               = 18,
        Id_forEach              = 19,
        Id_map                  = 20,
        Id_some                 = 21,
        Id_reduce               = 22,
        Id_reduceRight          = 23,

        MAX_PROTOTYPE_ID        = 23;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[798]++;
  }

// #/string_id_map#

    private static final int
        ConstructorId_join                 = -Id_join,
        ConstructorId_reverse              = -Id_reverse,
        ConstructorId_sort                 = -Id_sort,
        ConstructorId_push                 = -Id_push,
        ConstructorId_pop                  = -Id_pop,
        ConstructorId_shift                = -Id_shift,
        ConstructorId_unshift              = -Id_unshift,
        ConstructorId_splice               = -Id_splice,
        ConstructorId_concat               = -Id_concat,
        ConstructorId_slice                = -Id_slice,
        ConstructorId_indexOf              = -Id_indexOf,
        ConstructorId_lastIndexOf          = -Id_lastIndexOf,
        ConstructorId_every                = -Id_every,
        ConstructorId_filter               = -Id_filter,
        ConstructorId_forEach              = -Id_forEach,
        ConstructorId_map                  = -Id_map,
        ConstructorId_some                 = -Id_some,
        ConstructorId_reduce               = -Id_reduce,
        ConstructorId_reduceRight          = -Id_reduceRight,
        ConstructorId_isArray              = -24;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[799]++;
  }

    /**
     * Internal representation of the JavaScript array's length property.
     */
    private long length;

    /**
     * Attributes of the array's length property
     */
    private int lengthAttr = DONTENUM | PERMANENT;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[800]++;
  }

    /**
     * Fast storage for dense arrays. Sparse arrays will use the superclass's
     * hashtable storage scheme.
     */
    private Object[] dense;

    /**
     * True if all numeric properties are stored in <code>dense</code>.
     */
    private boolean denseOnly;

    /**
     * The maximum size of <code>dense</code> that will be allocated initially.
     */
    private static int maximumInitialCapacity = 10000;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[801]++;
  }

    /**
     * The default capacity for <code>dense</code>.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[802]++;
  }

    /**
     * The factor to grow <code>dense</code> by.
     */
    private static final double GROW_FACTOR = 1.5;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[803]++;
  }
    private static final int MAX_PRE_GROW_SIZE = (int)(Integer.MAX_VALUE / GROW_FACTOR);
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1.statements[804]++;
  }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1 ());
  }
    public static long[] statements = new long[805];
    public static long[] branches = new long[519];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[251];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeArray.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,0,1,1,1,2,3,2,3,1,1,1,1,2,1,1,1,1,3,1,1,3,2,1,3,1,1,1,1,1,1,2,1,1,1,3,1,3,1,1,1,1,1,1,2,1,2,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,2,1,1,1,1,1,1,3,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,2,2,2,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1,2,2,3,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 250; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[130];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9id5gilpxlhh1q7ucwli68xkns1 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeArray.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 804; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 518; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 250; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 129; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeArray.java");
      for (int i = 1; i <= 804; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 518; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 250; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 43; i++) {
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

