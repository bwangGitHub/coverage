/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class implements the Object native object.
 * See ECMA 15.2.
 */
public class NativeObject extends IdScriptableObject implements Map
{
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.ping();
  }

    static final long serialVersionUID = -6345305608474346996L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[1]++;
  }

    private static final Object OBJECT_TAG = "Object";
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[3]++;
        NativeObject obj = new NativeObject();
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[4]++;
    }

    @Override
    public String getClassName()
    {
        return "Object";
    }

    @Override
    public String toString()
    {
        return ScriptRuntime.defaultObjectToString(this);
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor)
    {
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_getPrototypeOf,
                "getPrototypeOf", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[5]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_keys,
                "keys", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[6]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_getOwnPropertyNames,
                "getOwnPropertyNames", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[7]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_getOwnPropertyDescriptor,
                "getOwnPropertyDescriptor", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[8]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_defineProperty,
                "defineProperty", 3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[9]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_isExtensible,
                "isExtensible", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[10]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_preventExtensions,
                "preventExtensions", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[11]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_defineProperties,
                "defineProperties", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[12]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_create,
                "create", 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[13]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_isSealed,
                "isSealed", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[14]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_isFrozen,
                "isFrozen", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[15]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_seal,
                "seal", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[16]++;
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_freeze,
                "freeze", 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[17]++;
        super.fillConstructorProperties(ctor);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[18]++;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[19]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[1]++;    arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[20]++; s="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[21]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[22]++;    break;
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[2]++;       arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[23]++; s="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[24]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[25]++;       break;
          case Id_toLocaleString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[3]++; arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[26]++; s="toLocaleString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[27]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[28]++; break;
          case Id_valueOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[4]++;        arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[29]++; s="valueOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[30]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[31]++;        break;
          case Id_hasOwnProperty:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[5]++; arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[32]++; s="hasOwnProperty";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[33]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[34]++; break;
          case Id_propertyIsEnumerable:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[6]++;
            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[35]++; s="propertyIsEnumerable";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[36]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[37]++; break;
          case Id_isPrototypeOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[7]++;  arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[38]++; s="isPrototypeOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[39]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[40]++;  break;
          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[8]++;       arity=0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[41]++; s="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[42]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[43]++;       break;
          case Id___defineGetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[9]++;
            arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[44]++; s="__defineGetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[45]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[46]++;     break;
          case Id___defineSetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[10]++;
            arity=2;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[47]++; s="__defineSetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[48]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[49]++;     break;
          case Id___lookupGetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[11]++;
            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[50]++; s="__lookupGetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[51]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[52]++;     break;
          case Id___lookupSetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[12]++;
            arity=1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[53]++; s="__lookupSetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[54]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[55]++;     break;
          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[13]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(OBJECT_TAG, id, s, arity);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[56]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[57]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((f.hasTag(OBJECT_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[14]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[15]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[58]++;
        int id = f.methodId();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[59]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[16]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[60]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisObj != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[17]++;
                // BaseFunction.construct will set up parent, proto
                return f.construct(cx, scope, args);

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[18]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[61]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((args[0] == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((args[0] == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false))
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[19]++;
                return new NativeObject();

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[20]++;}
            return ScriptRuntime.toObject(cx, scope, args[0]);
          }

          case Id_toLocaleString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[21]++; // For now just alias toString
          case Id_toString:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[22]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[62]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_TO_STRING_AS_SOURCE)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[23]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[63]++;
                String s = ScriptRuntime.defaultObjectToSource(cx, scope,
                                                               thisObj, args);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[64]++;
                int L = s.length();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[65]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((L != 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((s.charAt(0) == '(') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((s.charAt(L - 1) == ')') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[25]++;
                    // Strip () that surrounds toSource
                    s = s.substring(1, L - 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[66]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[26]++;}
                return s;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[24]++;}
            return ScriptRuntime.defaultObjectToString(thisObj);
          }

          case Id_valueOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[27]++;
            return thisObj;

          case Id_hasOwnProperty:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[28]++; {
            boolean result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[67]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[29]++;
                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[68]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[30]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[69]++;
                String s = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[70]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[31]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[71]++;
                    int index = ScriptRuntime.lastIndexResult(cx);
                    result = thisObj.has(index, thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[72]++;

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[32]++;
                    result = thisObj.has(s, thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[73]++;
                }
            }
            return ScriptRuntime.wrapBoolean(result);
          }

          case Id_propertyIsEnumerable:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[33]++; {
            boolean result;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[74]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[34]++;
                result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[75]++;

            } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[35]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[76]++;
                String s = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[77]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[36]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[78]++;
                    int index = ScriptRuntime.lastIndexResult(cx);
                    result = thisObj.has(index, thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[79]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[80]++;
int CodeCoverConditionCoverageHelper_C10;
                    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((thisObj instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[38]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[81]++;
                        ScriptableObject so = (ScriptableObject)thisObj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[82]++;
                        int attrs = so.getAttributes(index);
                        result = ((attrs & ScriptableObject.DONTENUM) == 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[83]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[39]++;}

                } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[37]++;
                    result = thisObj.has(s, thisObj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[84]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[85]++;
int CodeCoverConditionCoverageHelper_C11;
                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((thisObj instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[40]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[86]++;
                        ScriptableObject so = (ScriptableObject)thisObj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[87]++;
                        int attrs = so.getAttributes(s);
                        result = ((attrs & ScriptableObject.DONTENUM) == 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[88]++;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[41]++;}
                }
            }
            return ScriptRuntime.wrapBoolean(result);
          }

          case Id_isPrototypeOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[42]++; {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[89]++;
            boolean result = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[90]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((args.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((args[0] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[43]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[91]++;
                Scriptable v = (Scriptable) args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[92]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
                do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[3]++;
}
                    v = v.getPrototype();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[93]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[94]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((v == thisObj) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[45]++;
                        result = true;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[95]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[96]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[46]++;}
                } while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false));

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[44]++;}
            return ScriptRuntime.wrapBoolean(result);
          }

          case Id_toSource:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[47]++;
            return ScriptRuntime.defaultObjectToSource(cx, scope, thisObj,
                                                       args);
          case Id___defineGetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[48]++;
          case Id___defineSetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[49]++;
            {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[97]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((args.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((args[1] instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[50]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[98]++;
                    Object badArg = (args.length >= 2 ? args[1]
                                     : Undefined.instance);
                    throw ScriptRuntime.notFunctionError(badArg);

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[51]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[99]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((thisObj instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[52]++;
                    throw Context.reportRuntimeError2(
                        "msg.extend.scriptable",
                        thisObj.getClass().getName(),
                        String.valueOf(args[0]));

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[53]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[100]++;
                ScriptableObject so = (ScriptableObject)thisObj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[101]++;
                String name = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[102]++;
                int index = (name != null ? 0
                             : ScriptRuntime.lastIndexResult(cx));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[103]++;
                Callable getterOrSetter = (Callable)args[1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[104]++;
                boolean isSetter = (id == Id___defineSetter__);
                so.setGetterOrSetter(name, index, getterOrSetter, isSetter);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[105]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[106]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((so instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[54]++;
                    ((NativeArray)so).setDenseOnly(false);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[107]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[55]++;}
            }
            return Undefined.instance;

            case Id___lookupGetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[56]++;
            case Id___lookupSetter__:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[57]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[108]++;
int CodeCoverConditionCoverageHelper_C18;
                  if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((thisObj instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[58]++;
                      return Undefined.instance;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[59]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[109]++;

                  ScriptableObject so = (ScriptableObject)thisObj;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[110]++;
                  String name = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[111]++;
                  int index = (name != null ? 0
                               : ScriptRuntime.lastIndexResult(cx));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[112]++;
                  boolean isSetter = (id == Id___lookupSetter__);
                  Object gs;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[113]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[4]++;


                  for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[6]++;
}
                      gs = so.getGetterOrSetter(name, index, isSetter);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[114]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[115]++;
int CodeCoverConditionCoverageHelper_C20;
                      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((gs != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[60]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[116]++;
                          break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[61]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[117]++;
                      // If there is no getter or setter for the object itself,
                      // how about the prototype?
                      Scriptable v = so.getPrototype();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[118]++;
int CodeCoverConditionCoverageHelper_C21;
                      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[62]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[119]++;
                          break;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[63]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[120]++;
int CodeCoverConditionCoverageHelper_C22;
                      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((v instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[64]++;
                          so = (ScriptableObject)v;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[121]++;
}
                      else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[65]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[122]++;
                          break;
}
                  }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[123]++;
int CodeCoverConditionCoverageHelper_C23;
                  if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((gs != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[66]++;
                      return gs;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[67]++;}
              }
              return Undefined.instance;

          case ConstructorId_getPrototypeOf:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[68]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[124]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[125]++;
                Scriptable obj = ensureScriptable(arg);
                return obj.getPrototype();
              }
          case ConstructorId_keys:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[69]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[126]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[127]++;
                Scriptable obj = ensureScriptable(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[128]++;
                Object[] ids = obj.getIds();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[129]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[7]++;


int CodeCoverConditionCoverageHelper_C24;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < ids.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[9]++;
}
                  ids[i] = ScriptRuntime.toString(ids[i]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[130]++;
                }
                return cx.newArray(scope, ids);
              }
          case ConstructorId_getOwnPropertyNames:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[70]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[131]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[132]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[133]++;
                Object[] ids = obj.getAllIds();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[134]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[10]++;


int CodeCoverConditionCoverageHelper_C25;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i < ids.length) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[10]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[11]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[12]++;
}
                  ids[i] = ScriptRuntime.toString(ids[i]);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[135]++;
                }
                return cx.newArray(scope, ids);
              }
          case ConstructorId_getOwnPropertyDescriptor:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[71]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[136]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[137]++;
                // TODO(norris): There's a deeper issue here if
                // arg instanceof Scriptable. Should we create a new
                // interface to admit the new ECMAScript 5 operations?
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[138]++;
                Object nameArg = args.length < 2 ? Undefined.instance : args[1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[139]++;
                String name = ScriptRuntime.toString(nameArg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[140]++;
                Scriptable desc = obj.getOwnPropertyDescriptor(cx, name);
                return desc == null ? Undefined.instance : desc;
              }
          case ConstructorId_defineProperty:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[72]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[141]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[142]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[143]++;
                Object name = args.length < 2 ? Undefined.instance : args[1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[144]++;
                Object descArg = args.length < 3 ? Undefined.instance : args[2];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[145]++;
                ScriptableObject desc = ensureScriptableObject(descArg);
                obj.defineOwnProperty(cx, name, desc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[146]++;
                return obj;
              }
          case ConstructorId_isExtensible:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[73]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[147]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[148]++;
                ScriptableObject obj = ensureScriptableObject(arg);
                return Boolean.valueOf(obj.isExtensible());
              }
          case ConstructorId_preventExtensions:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[74]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[149]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[150]++;
                ScriptableObject obj = ensureScriptableObject(arg);
                obj.preventExtensions();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[151]++;
                return obj;
              }
          case ConstructorId_defineProperties:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[75]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[152]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[153]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[154]++;
                Object propsObj = args.length < 2 ? Undefined.instance : args[1];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[155]++;
                Scriptable props = Context.toObject(propsObj, getParentScope());
                obj.defineOwnProperties(cx, ensureScriptableObject(props));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[156]++;
                return obj;
        }
          case ConstructorId_create:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[76]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[157]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[158]++;
                Scriptable obj = (arg == null) ? null : ensureScriptable(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[159]++;

                ScriptableObject newObject = new NativeObject();
                newObject.setParentScope(this.getParentScope());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[160]++;
                newObject.setPrototype(obj);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[161]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[162]++;
int CodeCoverConditionCoverageHelper_C26;

                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((args.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((args[1] != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[77]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[163]++;
                  Scriptable props = Context.toObject(args[1], getParentScope());
                  newObject.defineOwnProperties(cx, ensureScriptableObject(props));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[164]++;

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[78]++;}

                return newObject;
              }

          case ConstructorId_isSealed:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[79]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[165]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[166]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[167]++;
int CodeCoverConditionCoverageHelper_C27;

                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj.isExtensible()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[80]++; return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[81]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[168]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[13]++;



                for (Object name: obj.getAllIds()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[13]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[14]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[15]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[169]++;
                  Object configurable = obj.getOwnPropertyDescriptor(cx, name).get("configurable");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[170]++;
int CodeCoverConditionCoverageHelper_C28;
                  if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(configurable)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[82]++;
                    return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[83]++;}
                }

                return Boolean.TRUE;
              }
          case ConstructorId_isFrozen:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[84]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[171]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[172]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[173]++;
int CodeCoverConditionCoverageHelper_C29;

                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((obj.isExtensible()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[85]++; return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[86]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[174]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[16]++;



                for (Object name: obj.getAllIds()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[16]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[17]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[18]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[175]++;
                  ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[176]++;
int CodeCoverConditionCoverageHelper_C30;
                  if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(desc.get("configurable"))) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[87]++;
                    return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[88]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[177]++;
int CodeCoverConditionCoverageHelper_C31;
                  if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((isDataDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(desc.get("writable"))) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[89]++;
                    return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[90]++;}
                }

                return Boolean.TRUE;
              }
          case ConstructorId_seal:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[91]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[178]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[179]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[180]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[19]++;



                for (Object name: obj.getAllIds()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[19]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[20]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[21]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[181]++;
                  ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[182]++;
int CodeCoverConditionCoverageHelper_C32;
                  if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(desc.get("configurable"))) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[92]++;
                    desc.put("configurable", desc, Boolean.FALSE);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[183]++;
                    obj.defineOwnProperty(cx, name, desc, false);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[184]++;

                  } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[93]++;}
                }
                obj.preventExtensions();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[185]++;

                return obj;
              }
          case ConstructorId_freeze:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[94]++;
              {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[186]++;
                Object arg = args.length < 1 ? Undefined.instance : args[0];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[187]++;
                ScriptableObject obj = ensureScriptableObject(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[188]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[22]++;



                for (Object name: obj.getAllIds()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[22]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[23]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[24]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[189]++;
                  ScriptableObject desc = obj.getOwnPropertyDescriptor(cx, name);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[190]++;
int CodeCoverConditionCoverageHelper_C33;
                  if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((isDataDescriptor(desc)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(desc.get("writable"))) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[95]++;
                    desc.put("writable", desc, Boolean.FALSE);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[191]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[96]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[192]++;
int CodeCoverConditionCoverageHelper_C34;
                  if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((Boolean.TRUE.equals(desc.get("configurable"))) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[97]++;
                    desc.put("configurable", desc, Boolean.FALSE);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[193]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[98]++;}
                  obj.defineOwnProperty(cx, name, desc, false);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[194]++;
                }
                obj.preventExtensions();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[195]++;

                return obj;
              }


          default:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[99]++;
            throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    // methods implementing java.util.Map

    public boolean containsKey(Object key) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[196]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((key instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[100]++;
            return has((String) key, this);

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[101]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[197]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((key instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[102]++;
            return has(((Number) key).intValue(), this);

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[103]++;}
}
        return false;
    }

    public boolean containsValue(Object value) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[198]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[25]++;


        for (Object obj : values()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[25]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[26]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.loops[27]++;
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[199]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((value == obj) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((value.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[104]++;
                return true;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[105]++;}
        }
        return false;
    }

    public Object remove(Object key) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[200]++;
        Object value = get(key);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[201]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((key instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[106]++;
            delete((String) key);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[202]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[107]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[203]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((key instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[108]++;
            delete(((Number) key).intValue());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[204]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[109]++;}
}
        return value;
    }


    public Set<Object> keySet() {
        return new KeySet();
    }

    public Collection<Object> values() {
        return new ValueCollection();
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return new EntrySet();
    }

    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map m) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }


    class EntrySet extends AbstractSet<Entry<Object, Object>> {
        @Override
        public Iterator<Entry<Object, Object>> iterator() {
            return new Iterator<Map.Entry<Object, Object>>() {
                Object[] ids = getIds();
                Object key = null;
                int index = 0;

                public boolean hasNext() {
                    return index < ids.length;
                }

                public Map.Entry<Object, Object> next() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[205]++;
                    final Object ekey = key = ids[index++];
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[206]++;
                    final Object value = get(key);
                    return new Map.Entry<Object, Object>() {
                        public Object getKey() {
                            return ekey;
                        }

                        public Object getValue() {
                            return value;
                        }

                        public Object setValue(Object value) {
                            throw new UnsupportedOperationException();
                        }

                        public boolean equals(Object other) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[207]++;
int CodeCoverConditionCoverageHelper_C40;
                            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((other instanceof Map.Entry) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[110]++;
                                return false;

                            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[111]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[208]++;
                            Map.Entry e = (Map.Entry) other;
                            return (ekey == null ? e.getKey() == null : ekey.equals(e.getKey()))
                                && (value == null ? e.getValue() == null : value.equals(e.getValue()));
                        }

                        public int hashCode() {
                            return (ekey == null ? 0 : ekey.hashCode()) ^
                                   (value == null ? 0 : value.hashCode());
                        }

                        public String toString() {
                            return ekey + "=" + value;
                        }
                    };
                }

                public void remove() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[209]++;
int CodeCoverConditionCoverageHelper_C41;
                    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[112]++;
                        throw new IllegalStateException();

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[113]++;}
                    NativeObject.this.remove(key);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[210]++;
                    key = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[211]++;
                }
            };
        }

        @Override
        public int size() {
            return NativeObject.this.size();
        }
    }

    class KeySet extends AbstractSet<Object> {

        @Override
        public boolean contains(Object key) {
            return containsKey(key);
        }

        @Override
        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                Object[] ids = getIds();
                Object key;
                int index = 0;

                public boolean hasNext() {
                    return index < ids.length;
                }

                public Object next() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[212]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                    try {
CodeCoverTryBranchHelper_Try1 = true;
                        return (key = ids[index++]);
                    } catch(ArrayIndexOutOfBoundsException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[115]++;
                        key = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[213]++;
                        throw new NoSuchElementException();
                    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[114]++;
}
  }
                }

                public void remove() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[214]++;
int CodeCoverConditionCoverageHelper_C42;
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[116]++;
                        throw new IllegalStateException();

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[117]++;}
                    NativeObject.this.remove(key);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[215]++;
                    key = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[216]++;
                }
           };
        }

        @Override
        public int size() {
            return NativeObject.this.size();
        }
    }

    class ValueCollection extends AbstractCollection<Object> {

        @Override
        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                Object[] ids = getIds();
                Object key;
                int index = 0;

                public boolean hasNext() {
                    return index < ids.length;
                }

                public Object next() {
                    return get((key = ids[index++]));
                }

                public void remove() {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[217]++;
int CodeCoverConditionCoverageHelper_C43;
                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[118]++;
                        throw new IllegalStateException();

                    } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[119]++;}
                    NativeObject.this.remove(key);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[218]++;
                    key = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[219]++;
                }
            };
        }

        @Override
        public int size() {
            return NativeObject.this.size();
        }
    }


// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:15:55 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[220]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[221]++; String X = null; int c;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[222]++;
            L: switch (s.length()) {
            case 7:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[120]++; X="valueOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[223]++;id=Id_valueOf;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[224]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[225]++; break L;
            case 8:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[121]++; c=s.charAt(3);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[226]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[227]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((c=='o') && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[122]++; X="toSource";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[228]++;id=Id_toSource;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[229]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[123]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[230]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[124]++; X="toString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[231]++;id=Id_toString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[232]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[125]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[233]++;
                break L;
            case 11:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[126]++; X="constructor";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[234]++;id=Id_constructor;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[235]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[236]++; break L;
            case 13:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[127]++; X="isPrototypeOf";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[237]++;id=Id_isPrototypeOf;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[238]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[239]++; break L;
            case 14:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[128]++; c=s.charAt(0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[240]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[241]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((c=='h') && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[129]++; X="hasOwnProperty";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[242]++;id=Id_hasOwnProperty;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[243]++;
 }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[130]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[244]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((c=='t') && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[131]++; X="toLocaleString";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[245]++;id=Id_toLocaleString;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[246]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[132]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[247]++;
                break L;
            case 16:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[133]++; c=s.charAt(2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[248]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[249]++;
int CodeCoverConditionCoverageHelper_C48;
                if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((c=='d') && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[134]++;
                    c=s.charAt(8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[250]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[251]++;
int CodeCoverConditionCoverageHelper_C49;
                    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((c=='G') && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[136]++; X="__defineGetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[252]++;id=Id___defineGetter__;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[253]++;
 }
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[137]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[254]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((c=='S') && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[138]++; X="__defineSetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[255]++;id=Id___defineSetter__;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[256]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[139]++;}
}

                }
                else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[135]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[257]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((c=='l') && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[140]++;
                    c=s.charAt(8);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[258]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[259]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((c=='G') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[142]++; X="__lookupGetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[260]++;id=Id___lookupGetter__;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[261]++;
 }
                    else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[143]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[262]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((c=='S') && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[144]++; X="__lookupSetter__";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[263]++;id=Id___lookupSetter__;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[264]++;
 } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[145]++;}
}

                } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[141]++;}
}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[265]++;
                break L;
            case 20:
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[146]++; X="propertyIsEnumerable";
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[266]++;id=Id_propertyIsEnumerable;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[267]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[268]++; break L; default : CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[147]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[269]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[148]++; id = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[270]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.branches[149]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[271]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        ConstructorId_getPrototypeOf = -1,
        ConstructorId_keys = -2,
        ConstructorId_getOwnPropertyNames = -3,
        ConstructorId_getOwnPropertyDescriptor = -4,
        ConstructorId_defineProperty = -5,
        ConstructorId_isExtensible = -6,
        ConstructorId_preventExtensions = -7,
        ConstructorId_defineProperties= -8,
        ConstructorId_create = -9,
        ConstructorId_isSealed = -10,
        ConstructorId_isFrozen = -11,
        ConstructorId_seal = -12,
        ConstructorId_freeze = -13,

        Id_constructor           = 1,
        Id_toString              = 2,
        Id_toLocaleString        = 3,
        Id_valueOf               = 4,
        Id_hasOwnProperty        = 5,
        Id_propertyIsEnumerable  = 6,
        Id_isPrototypeOf         = 7,
        Id_toSource              = 8,
        Id___defineGetter__      = 9,
        Id___defineSetter__      = 10,
        Id___lookupGetter__      = 11,
        Id___lookupSetter__      = 12,
        MAX_PROTOTYPE_ID         = 12;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5.statements[272]++;
  }

// #/string_id_map#
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5 ());
  }
    public static long[] statements = new long[273];
    public static long[] branches = new long[150];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[55];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,3,1,1,1,1,2,2,2,1,1,2,1,1,2,0,1,1,1,1,1,1,2,1,1,1,1,2,1,2,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 54; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmlitgagewsbci71m5m8dvefman5 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 272; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 149; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 54; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeObject.java");
      for (int i = 1; i <= 272; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 149; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 54; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

