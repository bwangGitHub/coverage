/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.*;
import java.util.Map;

/**
 * This class reflects Java classes into the JavaScript environment, mainly
 * for constructors and static members.  We lazily reflect properties,
 * and currently do not guarantee that a single j.l.Class is only
 * reflected once into the JS environment, although we should.
 * The only known case where multiple reflections
 * are possible occurs when a j.l.Class is wrapped as part of a
 * method return or property access, rather than by walking the
 * Packages/java tree.
 *
 * @see NativeJavaArray
 * @see NativeJavaObject
 * @see NativeJavaPackage
 */

public class NativeJavaClass extends NativeJavaObject implements Function
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.ping();
  }

    static final long serialVersionUID = -6460763940409461664L;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[1]++;
  }

    // Special property for getting the underlying Java class object.
    static final String javaClassPropertyName = "__javaObject__";
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[2]++;
  }

    public NativeJavaClass() {
    }

    public NativeJavaClass(Scriptable scope, Class<?> cl) {
        this(scope, cl, false);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[3]++;
    }

    public NativeJavaClass(Scriptable scope, Class<?> cl, boolean isAdapter) {
        super(scope, cl, null, isAdapter);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[4]++;
    }

    @Override
    protected void initMembers() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[5]++;
        Class<?> cl = (Class<?>)javaObject;
        members = JavaMembers.lookupClass(parent, cl, cl, isAdapter);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[6]++;
        staticFieldAndMethods = members.getFieldAndMethodsObjects(this, cl, true);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[7]++;
    }

    @Override
    public String getClassName() {
        return "JavaClass";
    }

    @Override
    public boolean has(String name, Scriptable start) {
        return members.has(name, true) || javaClassPropertyName.equals(name);
    }

    @Override
    public Object get(String name, Scriptable start) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        // When used as a constructor, ScriptRuntime.newObject() asks
        // for our prototype to create an object of the correct type.
        // We don't really care what the object is, since we're returning
        // one constructed out of whole cloth, so we return null.
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((name.equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[1]++;
            return null;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[2]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

         if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((staticFieldAndMethods != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[3]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[10]++;
            Object result = staticFieldAndMethods.get(name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[5]++;
                return result;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[4]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((members.has(name, true)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[7]++;
            return members.get(this, name, javaObject, true);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[8]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[13]++;

        Context cx = Context.getContext();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[14]++;
        Scriptable scope = ScriptableObject.getTopLevelScope(start);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[15]++;
        WrapFactory wrapFactory = cx.getWrapFactory();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((javaClassPropertyName.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[9]++;
            return wrapFactory.wrap(cx, scope, javaObject,
                                    ScriptRuntime.ClassClass);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[10]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[17]++;

        // experimental:  look for nested classes by appending $name to
        // current class' name.
        Class<?> nestedClass = findNestedClass(getClassObject(), name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((nestedClass != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[11]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[19]++;
            Scriptable nestedValue = wrapFactory.wrapJavaClass(cx, scope,
                    nestedClass);
            nestedValue.setParentScope(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[20]++;
            return nestedValue;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[12]++;}

        throw members.reportMemberNotFound(name);
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
        members.put(this, name, javaObject, value, true);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[21]++;
    }

    @Override
    public Object[] getIds() {
        return members.getIds(true);
    }

    public Class<?> getClassObject() {
        return (Class<?>) super.unwrap();
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((hint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[13]++;
            return this.toString();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[14]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[15]++;
            return Boolean.TRUE;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[16]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.NumberClass) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[17]++;
            return ScriptRuntime.NaNobj;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[18]++;}
        return this;
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;
        // If it looks like a "cast" of an object to this class type,
        // walk the prototype chain to see if there's a wrapper of a
        // object that's an instanceof this class.
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((args.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((args[0] instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[19]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[26]++;
            Class<?> c = getClassObject();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[27]++;
            Scriptable p = (Scriptable) args[0];
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
            do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[3]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[29]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((p instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[21]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[30]++;
                    Object o = ((Wrapper) p).unwrap();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[31]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c.isInstance(o)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[23]++;
                        return p;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[24]++;}

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[22]++;}
                p = p.getPrototype();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[32]++;
            } while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false));

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[20]++;}
        return construct(cx, scope, args);
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[33]++;
        Class<?> classObject = getClassObject();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[34]++;
        int modifiers = classObject.getModifiers();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((Modifier.isInterface(modifiers)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((Modifier.isAbstract(modifiers)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[25]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[36]++;
            NativeJavaMethod ctors = members.ctors;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[37]++;
            int index = ctors.findCachedFunction(cx, args);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[27]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[39]++;
                String sig = NativeJavaMethod.scriptSignature(args);
                throw Context.reportRuntimeError2(
                    "msg.no.java.ctor", classObject.getName(), sig);

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[28]++;}

            // Found the constructor, so try invoking it.
            return constructSpecific(cx, scope, args, ctors.methods[index]);

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[26]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[40]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[29]++;
                throw Context.reportRuntimeError0("msg.adapter.zero.args");

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[30]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[41]++;
            Scriptable topLevel = ScriptableObject.getTopLevelScope(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[42]++;
            String msg = "";
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[43]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[44]++;
int CodeCoverConditionCoverageHelper_C17;
                // When running on Android create an InterfaceAdapter since our
                // bytecode generation won't work on Dalvik VM.
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 (("Dalvik".equals(System.getProperty("java.vm.name"))) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((classObject.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[32]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[45]++;
                    Object obj = createInterfaceAdapter(classObject,
                            ScriptableObject.ensureScriptableObject(args[0]));
                    return cx.getWrapFactory().wrapAsJavaObject(cx, scope, obj, null);

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[33]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[46]++;
                // use JavaAdapter to construct a new class on the fly that
                // implements/extends this interface/abstract class.
                Object v = topLevel.get("JavaAdapter", topLevel);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[47]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((v != NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[34]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[48]++;
                    Function f = (Function) v;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[49]++;
                    // Args are (interface, js object)
                    Object[] adapterArgs = { this, args[0] };
                    return f.construct(cx, topLevel, adapterArgs);

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[35]++;}
            } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[36]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[50]++;
                // fall through to error
                String m = ex.getMessage();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[51]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[37]++;
                    msg = m;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[52]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[38]++;}
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[31]++;
}
  }
            throw Context.reportRuntimeError2(
                "msg.cant.instantiate", msg, classObject.getName());
        }
    }

    static Scriptable constructSpecific(Context cx, Scriptable scope,
                                        Object[] args, MemberBox ctor)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[53]++;
        Object instance = constructInternal(args, ctor);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[54]++;
        // we need to force this to be wrapped, because construct _has_
        // to return a scriptable
        Scriptable topLevel = ScriptableObject.getTopLevelScope(scope);
        return cx.getWrapFactory().wrapNewObject(cx, topLevel, instance);
    }

    static Object constructInternal(Object[] args, MemberBox ctor)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[55]++;
        Class<?>[] argTypes = ctor.argTypes;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[56]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ctor.vararg) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[39]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[57]++;
            // marshall the explicit parameter
            Object[] newArgs = new Object[argTypes.length];
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[58]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[4]++;


int CodeCoverConditionCoverageHelper_C21;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < argTypes.length-1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[6]++;
}
                newArgs[i] = Context.jsToJava(args[i], argTypes[i]);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[59]++;
            }

            Object varArgs;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[60]++;
int CodeCoverConditionCoverageHelper_C22;

            // Handle special situation where a single variable parameter
            // is given and it is a Java or ECMA array.
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (128)) == 0 || true) &&
 ((args.length == argTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((args[args.length-1] == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((args[args.length-1] instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((args[args.length-1] instanceof NativeJavaArray) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 4) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 4) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[41]++;
                // convert the ECMA array into a native array
                varArgs = Context.jsToJava(args[args.length-1],
                                           argTypes[argTypes.length - 1]);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[61]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[42]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[62]++;
                // marshall the variable parameter
                Class<?> componentType = argTypes[argTypes.length - 1].
                                        getComponentType();
                varArgs = Array.newInstance(componentType,
                                            args.length - argTypes.length + 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[63]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[64]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[7]++;


int CodeCoverConditionCoverageHelper_C23;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < Array.getLength(varArgs)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[9]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[65]++;
                    Object value = Context.jsToJava(args[argTypes.length-1 + i],
                                                    componentType);
                    Array.set(varArgs, i, value);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[66]++;
                }
            }

            // add varargs
            newArgs[argTypes.length-1] = varArgs;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[67]++;
            // replace the original args with the new one
            args = newArgs;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[68]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[40]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[69]++;
            Object[] origArgs = args;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[70]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[10]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[11]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.loops[12]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[71]++;
                Object arg = args[i];
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[72]++;
                Object x = Context.jsToJava(arg, argTypes[i]);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[73]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((x != arg) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[43]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[74]++;
int CodeCoverConditionCoverageHelper_C26;
                    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((args == origArgs) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[45]++;
                        args = origArgs.clone();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[75]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[46]++;}
                    args[i] = x;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[76]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[44]++;}
            }
        }

        return ctor.newInstance(args);
    }

    @Override
    public String toString() {
        return "[JavaClass " + getClassObject().getName() + "]";
    }

    /**
     * Determines if prototype is a wrapped Java object and performs
     * a Java "instanceof".
     * Exception: if value is an instance of NativeJavaClass, it isn't
     * considered an instance of the Java class; this forestalls any
     * name conflicts between java.lang.Class's methods and the
     * static methods exposed by a JavaNativeClass.
     */
    @Override
    public boolean hasInstance(Scriptable value) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((value instanceof NativeJavaClass) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[47]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[78]++;
            Object instance = ((Wrapper)value).unwrap();

            return getClassObject().isInstance(instance);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[48]++;}

        // value wasn't something we understand
        return false;
    }

    private static Class<?> findNestedClass(Class<?> parentClass, String name) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[79]++;
        String nestedClassName = parentClass.getName() + '$' + name;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[80]++;
        ClassLoader loader = parentClass.getClassLoader();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.statements[81]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((loader == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[49]++;
            // ALERT: if loader is null, nested class should be loaded
            // via system class loader which can be different from the
            // loader that brought Rhino classes that Class.forName() would
            // use, but ClassLoader.getSystemClassLoader() is Java 2 only
            return Kit.classOrNull(nestedClassName);

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl.branches[50]++;
            return Kit.classOrNull(loader, nestedClassName);
        }
    }

    private Map<String,FieldAndMethods> staticFieldAndMethods;
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl ());
  }
    public static long[] statements = new long[82];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJavaClass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,1,1,3,1,1,1,1,2,1};
    for (int i = 1; i <= 28; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8jtkbkas5hi9mezl () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaClass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 81; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaClass.java");
      for (int i = 1; i <= 81; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

