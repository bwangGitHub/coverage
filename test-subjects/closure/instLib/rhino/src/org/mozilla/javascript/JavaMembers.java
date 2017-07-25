/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.*;
import java.util.*;

import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

/**
 *
 * @see NativeJavaObject
 * @see NativeJavaClass
 */
class JavaMembers
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.ping();
  }

    JavaMembers(Scriptable scope, Class<?> cl)
    {
        this(scope, cl, false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[1]++;
    }

    JavaMembers(Scriptable scope, Class<?> cl, boolean includeProtected)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[2]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[3]++;
            Context cx = ContextFactory.getGlobal().enterContext();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[4]++;
            ClassShutter shutter = cx.getClassShutter();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((shutter != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((shutter.visibleToScripts(cl.getName())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[2]++;
                throw Context.reportRuntimeError1("msg.access.prohibited",
                                                  cl.getName());

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[3]++;}
            this.members = new HashMap<String,Object>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[6]++;
            this.staticMembers = new HashMap<String,Object>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[7]++;
            this.cl = cl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[8]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[9]++;
            boolean includePrivate = cx.hasFeature(
                    Context.FEATURE_ENHANCED_JAVA_ACCESS);
            reflect(scope, includeProtected, includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[10]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[1]++;
}
            Context.exit();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[11]++;
        }
    }

    boolean has(String name, boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[12]++;
        Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[13]++;
        Object obj = ht.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[4]++;
            return true;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[5]++;}
        return findExplicitFunction(name, isStatic) != null;
    }

    Object get(Scriptable scope, String name, Object javaObject,
               boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[15]++;
        Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[16]++;
        Object member = ht.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[6]++;
            // Try to get static member from instance (LC3)
            member = staticMembers.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[18]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[7]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[8]++;
            member = this.getExplicitFunction(scope, name,
                                              javaObject, isStatic);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[20]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[10]++;
                return Scriptable.NOT_FOUND;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[11]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[9]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((member instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[12]++;
            return member;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[13]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[23]++;
        Context cx = Context.getContext();
        Object rval;
        Class<?> type;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[24]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((member instanceof BeanProperty) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[15]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[26]++;
                BeanProperty bp = (BeanProperty) member;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((bp.getter == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[17]++;
                    return Scriptable.NOT_FOUND;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[18]++;}
                rval = bp.getter.invoke(javaObject, Context.emptyArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[28]++;
                type = bp.getter.method().getReturnType();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[29]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[16]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[30]++;
                Field field = (Field) member;
                rval = field.get(isStatic ? null : javaObject);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[31]++;
                type = field.getType();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[32]++;
            }
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[19]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[14]++;
}
  }
        // Need to wrap the object before we return it.
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[33]++;
        return cx.getWrapFactory().wrap(cx, scope, rval, type);
    }

    void put(Scriptable scope, String name, Object javaObject,
             Object value, boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[34]++;
        Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[35]++;
        Object member = ht.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[20]++;
            // Try to get static member from instance (LC3)
            member = staticMembers.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[37]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[21]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[22]++;
            throw reportMemberNotFound(name);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[23]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[39]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((member instanceof FieldAndMethods) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[24]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[40]++;
            FieldAndMethods fam = (FieldAndMethods) ht.get(name);
            member = fam.field;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[41]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[25]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[42]++;
int CodeCoverConditionCoverageHelper_C12;

        // Is this a bean property "set"?
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((member instanceof BeanProperty) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[26]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[43]++;
            BeanProperty bp = (BeanProperty)member;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[44]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((bp.setter == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[28]++;
                throw reportMemberNotFound(name);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[29]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[45]++;
int CodeCoverConditionCoverageHelper_C14;
            // If there's only one setter or if the value is null, use the
            // main setter. Otherwise, let the NativeJavaMethod decide which
            // setter to use:
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((bp.setters == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[30]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[46]++;
                Class<?> setType = bp.setter.argTypes[0];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[47]++;
                Object[] args = { Context.jsToJava(value, setType) };
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[48]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
                try {
CodeCoverTryBranchHelper_Try3 = true;
                    bp.setter.invoke(javaObject, args);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[49]++;
                } catch (Exception ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[33]++;
                  throw Context.throwAsScriptRuntimeEx(ex);
                } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[32]++;
}
  }

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[31]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[50]++;
                Object[] args = { value };
                bp.setters.call(Context.getContext(),
                                ScriptableObject.getTopLevelScope(scope),
                                scope, args);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[51]++;
            }

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[27]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((member instanceof Field) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[34]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[53]++;
                String str = (member == null) ? "msg.java.internal.private"
                                              : "msg.java.method.assign";
                throw Context.reportRuntimeError1(str, name);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[35]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[54]++;
            Field field = (Field)member;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[55]++;
            Object javaValue = Context.jsToJava(value, field.getType());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[56]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
            try {
CodeCoverTryBranchHelper_Try4 = true;
                field.set(javaObject, javaValue);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[57]++;
            } catch (IllegalAccessException accessEx) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[58]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((field.getModifiers() & Modifier.FINAL) != 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[38]++;
                    // treat Java final the same as JavaScript [[READONLY]]
                    return;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[39]++;}
                throw Context.throwAsScriptRuntimeEx(accessEx);
            } catch (IllegalArgumentException argEx) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[40]++;
                throw Context.reportRuntimeError3(
                    "msg.java.internal.field.type",
                    value.getClass().getName(), field,
                    javaObject.getClass().getName());
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[36]++;
}
  }
        }
    }

    Object[] getIds(boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[59]++;
        Map<String,Object> map = isStatic ? staticMembers : members;
        return map.keySet().toArray(new Object[map.size()]);
    }

    static String javaSignature(Class<?> type)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[60]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[41]++;
            return type.getName();

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[42]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[61]++;
            int arrayDimension = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[62]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[1]++;


int CodeCoverConditionCoverageHelper_C18;
            do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[3]++;
}
                ++arrayDimension;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[63]++;
                type = type.getComponentType();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[64]++;
            } while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((type.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[65]++;
            String name = type.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[66]++;
            String suffix = "[]";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[67]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((arrayDimension == 1) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[43]++;
                return name.concat(suffix);

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[44]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[68]++;
                int length = name.length() + arrayDimension * suffix.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[69]++;
                StringBuilder sb = new StringBuilder(length);
                sb.append(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[70]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[71]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
                while ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((arrayDimension != 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[6]++;
}
                    --arrayDimension;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[72]++;
                    sb.append(suffix);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[73]++;
                }
                return sb.toString();
            }
        }
    }

    static String liveConnectSignature(Class<?>[] argTypes)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[74]++;
        int N = argTypes.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[75]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[45]++; return "()";
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[46]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[76]++;
        StringBuilder sb = new StringBuilder();
        sb.append('(');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[77]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[78]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[9]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[47]++;
                sb.append(',');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[80]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[48]++;}
            sb.append(javaSignature(argTypes[i]));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[81]++;
        }
        sb.append(')');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[82]++;
        return sb.toString();
    }

    private MemberBox findExplicitFunction(String name, boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[83]++;
        int sigStart = name.indexOf('(');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[84]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((sigStart < 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[49]++; return null;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[50]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[85]++;

        Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[86]++;
        MemberBox[] methodsOrCtors = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[87]++;
        boolean isCtor = (isStatic && sigStart == 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[88]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isCtor) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[51]++;
            // Explicit request for an overloaded constructor
            methodsOrCtors = ctors.methods;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[89]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[52]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[90]++;
            // Explicit request for an overloaded method
            String trueName = name.substring(0,sigStart);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[91]++;
            Object obj = ht.get(trueName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[92]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[53]++;
                // Try to get static member from instance (LC3)
                obj = staticMembers.get(trueName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[93]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[54]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[94]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((obj instanceof NativeJavaMethod) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[55]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[95]++;
                NativeJavaMethod njm = (NativeJavaMethod)obj;
                methodsOrCtors = njm.methods;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[96]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[56]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[97]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((methodsOrCtors != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[57]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[98]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[10]++;


            for (MemberBox methodsOrCtor : methodsOrCtors) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[10]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[11]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[12]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[99]++;
                Class<?>[] type = methodsOrCtor.argTypes;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[100]++;
                String sig = liveConnectSignature(type);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[101]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((sigStart + sig.length() == name.length()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((name.regionMatches(sigStart, sig, 0, sig.length())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false))
                {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[59]++;
                    return methodsOrCtor;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[60]++;}
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[58]++;}

        return null;
    }

    private Object getExplicitFunction(Scriptable scope, String name,
                                       Object javaObject, boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[102]++;
        Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[103]++;
        Object member = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[104]++;
        MemberBox methodOrCtor = findExplicitFunction(name, isStatic);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[105]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((methodOrCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[61]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[106]++;
            Scriptable prototype =
                ScriptableObject.getFunctionPrototype(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[107]++;
int CodeCoverConditionCoverageHelper_C31;

            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((methodOrCtor.isCtor()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[63]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[108]++;
                NativeJavaConstructor fun =
                    new NativeJavaConstructor(methodOrCtor);
                fun.setPrototype(prototype);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[109]++;
                member = fun;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[110]++;
                ht.put(name, fun);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[111]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[64]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[112]++;
                String trueName = methodOrCtor.getName();
                member = ht.get(trueName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[113]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[114]++;
int CodeCoverConditionCoverageHelper_C32;

                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((member instanceof NativeJavaMethod) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((((NativeJavaMethod)member).methods.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false) ) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[65]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[115]++;
                    NativeJavaMethod fun =
                        new NativeJavaMethod(methodOrCtor, name);
                    fun.setPrototype(prototype);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[116]++;
                    ht.put(name, fun);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[117]++;
                    member = fun;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[118]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[66]++;}
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[62]++;}

        return member;
    }

    /**
     * Retrieves mapping of methods to accessible methods for a class.
     * In case the class is not public, retrieves methods with same
     * signature as its public methods from public superclasses and
     * interfaces (if they exist). Basically upcasts every method to the
     * nearest accessible method.
     */
    private static Method[] discoverAccessibleMethods(Class<?> clazz,
                                                      boolean includeProtected,
                                                      boolean includePrivate)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[119]++;
        Map<MethodSignature,Method> map = new HashMap<MethodSignature,Method>();
        discoverAccessibleMethods(clazz, map, includeProtected, includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[120]++;
        return map.values().toArray(new Method[map.size()]);
    }

    private static void discoverAccessibleMethods(Class<?> clazz,
            Map<MethodSignature,Method> map, boolean includeProtected,
            boolean includePrivate)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[121]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((isPublic(clazz.getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[67]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[122]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
            try {
CodeCoverTryBranchHelper_Try5 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[123]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((includeProtected) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[70]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[124]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[13]++;


int CodeCoverConditionCoverageHelper_C35;
                    while ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((clazz != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[13]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[14]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[15]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[125]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
                        try {
CodeCoverTryBranchHelper_Try6 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[126]++;
                            Method[] methods = clazz.getDeclaredMethods();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[127]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[16]++;


                            for (Method method : methods) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[16]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[17]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[18]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[128]++;
                                int mods = method.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[129]++;
int CodeCoverConditionCoverageHelper_C36;

                                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((isPublic(mods)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((isProtected(mods)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[73]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[130]++;
                                    MethodSignature sig = new MethodSignature(method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[131]++;
int CodeCoverConditionCoverageHelper_C37;
                                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((map.containsKey(sig)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[75]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[132]++;
int CodeCoverConditionCoverageHelper_C38;
                                        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((method.isAccessible()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[77]++;
                                            method.setAccessible(true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[133]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[78]++;}
                                        map.put(sig, method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[134]++;

                                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[76]++;}

                                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[74]++;}
                            }
                            clazz = clazz.getSuperclass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[135]++;
                        } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[79]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[136]++;
                            // Some security settings (i.e., applets) disallow
                            // access to Class.getDeclaredMethods. Fall back to
                            // Class.getMethods.
                            Method[] methods = clazz.getMethods();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[137]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[19]++;


                            for (Method method : methods) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[19]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[20]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[21]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[138]++;
                                MethodSignature sig = new MethodSignature(method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[139]++;
int CodeCoverConditionCoverageHelper_C39;
                                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((map.containsKey(sig)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[80]++;
                                    map.put(sig, method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[140]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[81]++;}
                            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[141]++;
                            break; // getMethods gets superclass methods, no
                                   // need to loop any more
                        } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[72]++;
}
  }
                    }

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[71]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[142]++;
                    Method[] methods = clazz.getMethods();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[143]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[22]++;


                    for (Method method : methods) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[22]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[23]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[24]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[144]++;
                        MethodSignature sig = new MethodSignature(method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[145]++;
int CodeCoverConditionCoverageHelper_C40;
                        // Array may contain methods with same signature but different return value!
                        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((map.containsKey(sig)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[82]++;
                            map.put(sig, method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[146]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[83]++;}
                    }
                }
                return;
            } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[84]++;
                Context.reportWarning(
                        "Could not discover accessible methods of class " +
                            clazz.getName() + " due to lack of privileges, " +
                            "attemping superclasses/interfaces.");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[147]++;
                // Fall through and attempt to discover superclass/interface
                // methods
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[69]++;
}
  }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[68]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[148]++;

        Class<?>[] interfaces = clazz.getInterfaces();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[149]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[25]++;


        for (Class<?> intface : interfaces) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[25]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[26]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[27]++;
}
            discoverAccessibleMethods(intface, map, includeProtected,
                    includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[150]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[151]++;
        Class<?> superclass = clazz.getSuperclass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[152]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((superclass != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[85]++;
            discoverAccessibleMethods(superclass, map, includeProtected,
                    includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[153]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[86]++;}
    }

    private static final class MethodSignature
    {
        private final String name;
        private final Class<?>[] args;

        private MethodSignature(String name, Class<?>[] args)
        {
            this.name = name;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[154]++;
            this.args = args;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[155]++;
        }

        MethodSignature(Method method)
        {
            this(method.getName(), method.getParameterTypes());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[156]++;
        }

        @Override
        public boolean equals(Object o)
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[157]++;
int CodeCoverConditionCoverageHelper_C42;
            if((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((o instanceof MethodSignature) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[87]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[158]++;
                MethodSignature ms = (MethodSignature)o;
                return ms.name.equals(name) && Arrays.equals(args, ms.args);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[88]++;}
            return false;
        }

        @Override
        public int hashCode()
        {
            return name.hashCode() ^ args.length;
        }
    }

    private void reflect(Scriptable scope,
                         boolean includeProtected,
                         boolean includePrivate)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[159]++;
        // We reflect methods first, because we want overloaded field/method
        // names to be allocated to the NativeJavaMethod before the field
        // gets in the way.

        Method[] methods = discoverAccessibleMethods(cl, includeProtected,
                                                     includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[160]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[28]++;


        for (Method method : methods) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[28]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[29]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[30]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[161]++;
            int mods = method.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[162]++;
            boolean isStatic = Modifier.isStatic(mods);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[163]++;
            Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[164]++;
            String name = method.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[165]++;
            Object value = ht.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[166]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[89]++;
                ht.put(name, method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[167]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[90]++;
                ObjArray overloadedMethods;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[168]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((value instanceof ObjArray) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[91]++;
                    overloadedMethods = (ObjArray)value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[169]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[92]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[170]++;
int CodeCoverConditionCoverageHelper_C45;
                    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((value instanceof Method) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[93]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[171]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[94]++;}
                    // value should be instance of Method as at this stage
                    // staticMembers and members can only contain methods
                    overloadedMethods = new ObjArray();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[172]++;
                    overloadedMethods.add(value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[173]++;
                    ht.put(name, overloadedMethods);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[174]++;
                }
                overloadedMethods.add(method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[175]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[176]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[31]++;


int CodeCoverConditionCoverageHelper_C46;

        // replace Method instances by wrapped NativeJavaMethod objects
        // first in staticMembers and then in members
        for (int tableCursor = 0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((tableCursor != 2) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); ++tableCursor) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[31]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[32]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[33]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[177]++;
            boolean isStatic = (tableCursor == 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[178]++;
            Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[179]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[34]++;


            for (Map.Entry<String, Object> entry: ht.entrySet()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[34]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[35]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[36]++;
}
                MemberBox[] methodBoxes;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[180]++;
                Object value = entry.getValue();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[181]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((value instanceof Method) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[95]++;
                    methodBoxes = new MemberBox[1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[182]++;
                    methodBoxes[0] = new MemberBox((Method)value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[183]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[96]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[184]++;
                    ObjArray overloadedMethods = (ObjArray)value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[185]++;
                    int N = overloadedMethods.size();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[186]++;
int CodeCoverConditionCoverageHelper_C48;
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((N < 2) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[97]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[187]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[98]++;}
                    methodBoxes = new MemberBox[N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[188]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[189]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[37]++;


int CodeCoverConditionCoverageHelper_C49;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[37]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[38]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[39]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[190]++;
                        Method method = (Method)overloadedMethods.get(i);
                        methodBoxes[i] = new MemberBox(method);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[191]++;
                    }
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[192]++;
                NativeJavaMethod fun = new NativeJavaMethod(methodBoxes);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[193]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[99]++;
                    ScriptRuntime.setFunctionProtoAndParent(fun, scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[194]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[100]++;}
                ht.put(entry.getKey(), fun);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[195]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[196]++;

        // Reflect fields.
        Field[] fields = getAccessibleFields(includeProtected, includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[197]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[40]++;


        for (Field field : fields) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[40]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[41]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[42]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[198]++;
            String name = field.getName();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[199]++;
            int mods = field.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[200]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
            try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[201]++;
                boolean isStatic = Modifier.isStatic(mods);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[202]++;
                Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[203]++;
                Object member = ht.get(name);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[204]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((member == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[102]++;
                    ht.put(name, field);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[205]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[103]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[206]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((member instanceof NativeJavaMethod) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[104]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[207]++;
                    NativeJavaMethod method = (NativeJavaMethod) member;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[208]++;
                    FieldAndMethods fam
                        = new FieldAndMethods(scope, method.methods, field);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[209]++;
                    Map<String,FieldAndMethods> fmht = isStatic ? staticFieldAndMethods
                                              : fieldAndMethods;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[210]++;
int CodeCoverConditionCoverageHelper_C53;
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((fmht == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[106]++;
                        fmht = new HashMap<String,FieldAndMethods>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[211]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[212]++;
int CodeCoverConditionCoverageHelper_C54;
                        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[108]++;
                            staticFieldAndMethods = fmht;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[213]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[109]++;
                            fieldAndMethods = fmht;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[214]++;
                        }

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[107]++;}
                    fmht.put(name, fam);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[215]++;
                    ht.put(name, fam);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[216]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[105]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[217]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((member instanceof Field) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[110]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[218]++;
                    Field oldField = (Field) member;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[219]++;
int CodeCoverConditionCoverageHelper_C56;
                    // If this newly reflected field shadows an inherited field,
                    // then replace it. Otherwise, since access to the field
                    // would be ambiguous from Java, no field should be
                    // reflected.
                    // For now, the first field found wins, unless another field
                    // explicitly shadows it.
                    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((oldField.getDeclaringClass().
                            isAssignableFrom(field.getDeclaringClass())) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false))
                    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[112]++;
                        ht.put(name, field);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[220]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[113]++;}

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[111]++;
                    // "unknown member type"
                    Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[221]++;
                }
}
}
            } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[114]++;
                // skip this field
                Context.reportWarning("Could not access field "
                        + name + " of class " + cl.getName() +
                        " due to lack of privileges.");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[222]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[101]++;
}
  }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[223]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[43]++;


int CodeCoverConditionCoverageHelper_C57;

        // Create bean properties from corresponding get/set methods first for
        // static members and then for instance members
        for (int tableCursor = 0;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((tableCursor != 2) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); ++tableCursor) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[43]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[44]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[45]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[224]++;
            boolean isStatic = (tableCursor == 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[225]++;
            Map<String,Object> ht = isStatic ? staticMembers : members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[226]++;

            Map<String,BeanProperty> toAdd = new HashMap<String,BeanProperty>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[227]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[46]++;



            // Now, For each member, make "bean" properties.
            for (String name: ht.keySet()) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[46]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[47]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[48]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[228]++;
                // Is this a getter?
                boolean memberIsGetMethod = name.startsWith("get");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[229]++;
                boolean memberIsSetMethod = name.startsWith("set");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[230]++;
                boolean memberIsIsMethod = name.startsWith("is");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[231]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (32)) == 0 || true) &&
 ((memberIsGetMethod) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((memberIsIsMethod) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((memberIsSetMethod) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[115]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[232]++;
                    // Double check name component.
                    String nameComponent
                        = name.substring(memberIsIsMethod ? 2 : 3);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[233]++;
int CodeCoverConditionCoverageHelper_C59;
                    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((nameComponent.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[117]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[234]++;
                        continue;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[118]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[235]++;

                    // Make the bean property name.
                    String beanPropertyName = nameComponent;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[236]++;
                    char ch0 = nameComponent.charAt(0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[237]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((Character.isUpperCase(ch0)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[119]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[238]++;
int CodeCoverConditionCoverageHelper_C61;
                        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((nameComponent.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[121]++;
                            beanPropertyName = nameComponent.toLowerCase();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[239]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[122]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[240]++;
                            char ch1 = nameComponent.charAt(1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[241]++;
int CodeCoverConditionCoverageHelper_C62;
                            if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((Character.isUpperCase(ch1)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[123]++;
                                beanPropertyName = Character.toLowerCase(ch0)
                                                   +nameComponent.substring(1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[242]++;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[124]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[120]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[243]++;
int CodeCoverConditionCoverageHelper_C63;

                    // If we already have a member by this name, don't do this
                    // property.
                    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((toAdd.containsKey(beanPropertyName)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[125]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[244]++;
                        continue;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[126]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[245]++;
                    Object v = ht.get(beanPropertyName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[246]++;
int CodeCoverConditionCoverageHelper_C64;
                    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[127]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[247]++;
int CodeCoverConditionCoverageHelper_C65;
                        // A private field shouldn't mask a public getter/setter
                        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (32)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (16)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((v instanceof Member) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((Modifier.isPrivate(((Member)v).getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 3) && false))

                        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[129]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[248]++;
                            continue;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[130]++;}

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[128]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[249]++;

                    // Find the getter method, or if there is none, the is-
                    // method.
                    MemberBox getter = null;
                    getter = findGetter(isStatic, ht, "get", nameComponent);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[250]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[251]++;
int CodeCoverConditionCoverageHelper_C66;
                    // If there was no valid getter, check for an is- method.
                    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((getter == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[131]++;
                        getter = findGetter(isStatic, ht, "is", nameComponent);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[252]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[132]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[253]++;

                    // setter
                    MemberBox setter = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[254]++;
                    NativeJavaMethod setters = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[255]++;
                    String setterName = "set".concat(nameComponent);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[256]++;
int CodeCoverConditionCoverageHelper_C67;

                    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((ht.containsKey(setterName)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[133]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[257]++;
                        // Is this value a method?
                        Object member = ht.get(setterName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[258]++;
int CodeCoverConditionCoverageHelper_C68;
                        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((member instanceof NativeJavaMethod) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[135]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[259]++;
                            NativeJavaMethod njmSet = (NativeJavaMethod)member;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[260]++;
int CodeCoverConditionCoverageHelper_C69;
                            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((getter != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[137]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[261]++;
                                // We have a getter. Now, do we have a matching
                                // setter?
                                Class<?> type = getter.method().getReturnType();
                                setter = extractSetMethod(type, njmSet.methods,
                                                            isStatic);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[262]++;

                            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[138]++;
                                // No getter, find any set method
                                setter = extractSetMethod(njmSet.methods,
                                                            isStatic);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[263]++;
                            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[264]++;
int CodeCoverConditionCoverageHelper_C70;
                            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((njmSet.methods.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[139]++;
                                setters = njmSet;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[265]++;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[140]++;}

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[136]++;}

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[134]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[266]++;
                    // Make the property.
                    BeanProperty bp = new BeanProperty(getter, setter,
                                                       setters);
                    toAdd.put(beanPropertyName, bp);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[267]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[116]++;}
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[268]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[49]++;



            // Add the new bean properties.
            for (String key: toAdd.keySet()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[49]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[50]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[51]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[269]++;
                Object value = toAdd.get(key);
                ht.put(key, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[270]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[271]++;

        // Reflect constructors
        Constructor<?>[] constructors = getAccessibleConstructors(includePrivate);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[272]++;
        MemberBox[] ctorMembers = new MemberBox[constructors.length];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[273]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[52]++;


int CodeCoverConditionCoverageHelper_C71;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((i != constructors.length) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[52]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[53]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[54]++;
}
            ctorMembers[i] = new MemberBox(constructors[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[274]++;
        }
        ctors = new NativeJavaMethod(ctorMembers, cl.getSimpleName());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[275]++;
    }

    private Constructor<?>[] getAccessibleConstructors(boolean includePrivate)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[276]++;
int CodeCoverConditionCoverageHelper_C72;
      // The JVM currently doesn't allow changing access on java.lang.Class
      // constructors, so don't try
      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((cl != ScriptRuntime.ClassClass) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[141]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[277]++;
boolean CodeCoverTryBranchHelper_Try8 = false;
          try {
CodeCoverTryBranchHelper_Try8 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[278]++;
              Constructor<?>[] cons = cl.getDeclaredConstructors();
              AccessibleObject.setAccessible(cons, true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[279]++;

              return cons;
          } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try8 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[144]++;
              // Fall through to !includePrivate case
              Context.reportWarning("Could not access constructor " +
                    " of class " + cl.getName() +
                    " due to lack of privileges.");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[280]++;
          } finally {
    if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[143]++;
}
  }

      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[142]++;}
      return cl.getConstructors();
    }

    private Field[] getAccessibleFields(boolean includeProtected,
                                        boolean includePrivate) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[281]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((includeProtected) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[145]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[282]++;
boolean CodeCoverTryBranchHelper_Try9 = false;
            try {
CodeCoverTryBranchHelper_Try9 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[283]++;
                List<Field> fieldsList = new ArrayList<Field>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[284]++;
                Class<?> currentClass = cl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[285]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[55]++;


int CodeCoverConditionCoverageHelper_C74;

                while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((currentClass != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[55]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[56]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[57]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[286]++;
                    // get all declared fields in this class, make them
                    // accessible, and save
                    Field[] declared = currentClass.getDeclaredFields();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[287]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[58]++;


                    for (Field field : declared) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[58]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[59]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[60]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[288]++;
                        int mod = field.getModifiers();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[289]++;
int CodeCoverConditionCoverageHelper_C75;
                        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (32)) == 0 || true) &&
 ((includePrivate) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((isPublic(mod)) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((isProtected(mod)) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[148]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[290]++;
int CodeCoverConditionCoverageHelper_C76;
                            if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((field.isAccessible()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[150]++;
                                field.setAccessible(true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[291]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[151]++;}
                            fieldsList.add(field);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[292]++;

                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[149]++;}
                    }
                    // walk up superclass chain.  no need to deal specially with
                    // interfaces, since they can't have fields
                    currentClass = currentClass.getSuperclass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[293]++;
                }

                return fieldsList.toArray(new Field[fieldsList.size()]);
            } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[152]++;
                // fall through to !includePrivate case
            } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[147]++;
}
  }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[146]++;}
        return cl.getFields();
    }

    private MemberBox findGetter(boolean isStatic, Map<String,Object> ht, String prefix,
                                 String propertyName)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[294]++;
        String getterName = prefix.concat(propertyName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[295]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((ht.containsKey(getterName)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[153]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[296]++;
            // Check that the getter is a method.
            Object member = ht.get(getterName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[297]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((member instanceof NativeJavaMethod) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[155]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[298]++;
                NativeJavaMethod njmGet = (NativeJavaMethod) member;
                return extractGetMethod(njmGet.methods, isStatic);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[156]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[154]++;}
        return null;
    }

    private static MemberBox extractGetMethod(MemberBox[] methods,
                                              boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[299]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[61]++;


        // Inspect the list of all MemberBox for the only one having no
        // parameters
        for (MemberBox method : methods) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[61]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[62]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[63]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[300]++;
int CodeCoverConditionCoverageHelper_C79;
            // Does getter method have an empty parameter list with a return
            // value (eg. a getSomething() or isSomething())?
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (32)) == 0 || true) &&
 ((method.argTypes.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (16)) == 0 || true)))
 && (!
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((method.isStatic()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[157]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[301]++;
                Class<?> type = method.method().getReturnType();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[302]++;
int CodeCoverConditionCoverageHelper_C80;
                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((type != Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[159]++;
                    return method;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[160]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[303]++;
                break;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[158]++;}
        }
        return null;
    }

    private static MemberBox extractSetMethod(Class<?> type, MemberBox[] methods,
                                              boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[304]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[64]++;


int CodeCoverConditionCoverageHelper_C81;
        //
        // Note: it may be preferable to allow NativeJavaMethod.findFunction()
        //       to find the appropriate setter; unfortunately, it requires an
        //       instance of the target arg to determine that.
        //

        // Make two passes: one to find a method with direct type assignment,
        // and one to find a widening conversion.
        for (int pass = 1;(((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((pass <= 2) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false); ++pass) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[64]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[65]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[66]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[305]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[67]++;


            for (MemberBox method : methods) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[67]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[68]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[69]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[306]++;
int CodeCoverConditionCoverageHelper_C82;
                if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((method.isStatic()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[161]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[307]++;
                    Class<?>[] params = method.argTypes;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[308]++;
int CodeCoverConditionCoverageHelper_C83;
                    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((params.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[163]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[309]++;
int CodeCoverConditionCoverageHelper_C84;
                        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((pass == 1) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[165]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[310]++;
int CodeCoverConditionCoverageHelper_C85;
                            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((params[0] == type) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[167]++;
                                return method;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[168]++;}

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[166]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[311]++;
int CodeCoverConditionCoverageHelper_C86;
                            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((pass != 2) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[169]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[312]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[170]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[313]++;
int CodeCoverConditionCoverageHelper_C87;
                            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((params[0].isAssignableFrom(type)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[171]++;
                                return method;

                            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[172]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[164]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[162]++;}
            }
        }
        return null;
    }

    private static MemberBox extractSetMethod(MemberBox[] methods,
                                              boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[314]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[70]++;



        for (MemberBox method : methods) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[70]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[71]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[72]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[315]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((method.isStatic()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[173]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[316]++;
int CodeCoverConditionCoverageHelper_C89;
                if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((method.method().getReturnType() == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[175]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[317]++;
int CodeCoverConditionCoverageHelper_C90;
                    if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((method.argTypes.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[177]++;
                        return method;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[178]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[176]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[174]++;}
        }
        return null;
    }

    Map<String,FieldAndMethods> getFieldAndMethodsObjects(Scriptable scope,
            Object javaObject, boolean isStatic)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[318]++;
        Map<String,FieldAndMethods> ht = isStatic ? staticFieldAndMethods : fieldAndMethods;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[319]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((ht == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[179]++;
            return null;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[180]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[320]++;
        int len = ht.size();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[321]++;
        Map<String,FieldAndMethods> result = new HashMap<String,FieldAndMethods>(len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[322]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[73]++;


        for (FieldAndMethods fam: ht.values()) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[73]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[74]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[75]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[323]++;
            FieldAndMethods famNew = new FieldAndMethods(scope, fam.methods,
                                                         fam.field);
            famNew.javaObject = javaObject;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[324]++;
            result.put(fam.field.getName(), famNew);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[325]++;
        }
        return result;
    }

    static JavaMembers lookupClass(Scriptable scope, Class<?> dynamicType,
                                   Class<?> staticType, boolean includeProtected)
    {
        JavaMembers members;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[326]++;
        ClassCache cache = ClassCache.get(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[327]++;
        Map<Class<?>,JavaMembers> ct = cache.getClassCacheMap();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[328]++;

        Class<?> cl = dynamicType;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[329]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[76]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[76]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[77]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.loops[78]++;
}
            members = ct.get(cl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[330]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[331]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((members != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[181]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[332]++;
int CodeCoverConditionCoverageHelper_C94;
                if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((cl != dynamicType) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[183]++;
                    // member lookup for the original class failed because of
                    // missing privileges, cache the result so we don't try again
                    ct.put(dynamicType, members);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[333]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[184]++;}
                return members;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[182]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[334]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
            try {
CodeCoverTryBranchHelper_Try10 = true;
                members = new JavaMembers(cache.getAssociatedScope(), cl,
                        includeProtected);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[335]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[336]++;
                break;
            } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try10 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[186]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[337]++;
int CodeCoverConditionCoverageHelper_C95;
                // Reflection may fail for objects that are in a restricted
                // access package (e.g. sun.*).  If we get a security
                // exception, try again with the static type if it is interface.
                // Otherwise, try superclass
                if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((staticType != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((staticType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[187]++;
                    cl = staticType;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[338]++;
                    staticType = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[339]++;
 // try staticType only once
                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[188]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[340]++;
                    Class<?> parent = cl.getSuperclass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[341]++;
int CodeCoverConditionCoverageHelper_C96;
                    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[189]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[342]++;
int CodeCoverConditionCoverageHelper_C97;
                        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((cl.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[191]++;
                            // last resort after failed staticType interface
                            parent = ScriptRuntime.ObjectClass;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[343]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[192]++;
                            throw e;
                        }

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[190]++;}
                    cl = parent;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[344]++;
                }
            } finally {
    if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[185]++;
}
  }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[345]++;
int CodeCoverConditionCoverageHelper_C98;

        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((cache.isCachingEnabled()) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[193]++;
            ct.put(cl, members);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[346]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[347]++;
int CodeCoverConditionCoverageHelper_C99;
            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((cl != dynamicType) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[195]++;
                // member lookup for the original class failed because of
                // missing privileges, cache the result so we don't try again
                ct.put(dynamicType, members);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[348]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[196]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[194]++;}
        return members;
    }

    RuntimeException reportMemberNotFound(String memberName)
    {
        return Context.reportRuntimeError2(
            "msg.java.member.not.found", cl.getName(), memberName);
    }

    private Class<?> cl;
    private Map<String,Object> members;
    private Map<String,FieldAndMethods> fieldAndMethods;
    private Map<String,Object> staticMembers;
    private Map<String,FieldAndMethods> staticFieldAndMethods;
    NativeJavaMethod ctors; // we use NativeJavaMethod for ctor overload resolution
}

class BeanProperty
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.ping();
  }

    BeanProperty(MemberBox getter, MemberBox setter, NativeJavaMethod setters)
    {
        this.getter = getter;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[349]++;
        this.setter = setter;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[350]++;
        this.setters = setters;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[351]++;
    }

    MemberBox getter;
    MemberBox setter;
    NativeJavaMethod setters;
}

class FieldAndMethods extends NativeJavaMethod
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.ping();
  }

    static final long serialVersionUID = -9222428244284796755L;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[352]++;
  }

    FieldAndMethods(Scriptable scope, MemberBox[] methods, Field field)
    {
        super(methods);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[353]++;
        this.field = field;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[354]++;
        setParentScope(scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[355]++;
        setPrototype(ScriptableObject.getFunctionPrototype(scope));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[356]++;
    }

    @Override
    public Object getDefaultValue(Class<?> hint)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[357]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.FunctionClass) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[197]++;
            return this;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[198]++;}
        Object rval;
        Class<?> type;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[358]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
        try {
CodeCoverTryBranchHelper_Try11 = true;
            rval = field.get(javaObject);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[359]++;
            type = field.getType();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[360]++;
        } catch (IllegalAccessException accEx) {
CodeCoverTryBranchHelper_Try11 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[200]++;
            throw Context.reportRuntimeError1(
                "msg.java.internal.private", field.getName());
        } finally {
    if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[199]++;
}
  }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[361]++;
        Context cx  = Context.getContext();
        rval = cx.getWrapFactory().wrap(cx, this, rval, type);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[362]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[363]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((rval instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[201]++;
            rval = ((Scriptable) rval).getDefaultValue(hint);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.statements[364]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx.branches[202]++;}
        return rval;
    }

    Field field;
    Object javaObject;
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx ());
  }
    public static long[] statements = new long[365];
    public static long[] branches = new long[203];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[102];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-JavaMembers.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,2,2,1,3,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,3,1,1,1,1,1,1,2,2,1,3,1,1,1,3,1,1,2,1,1,1,1,1,2,1,1,1,0,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 101; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[79];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9icwxm3dld62dfz33uit66dqfkx () {
    super("org.mozilla.javascript.RHINO-SRC-JavaMembers.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 364; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 202; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 101; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 78; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-JavaMembers.java");
      for (int i = 1; i <= 364; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 202; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 101; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 26; i++) {
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

