/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.*;
import java.lang.reflect.*;
import java.util.Map;
import java.util.Date;

/**
 * This class reflects non-Array Java objects into the JavaScript environment.  It
 * reflect fields directly, and uses NativeJavaMethod objects to reflect (possibly
 * overloaded) methods.<p>
 *
 * @see NativeJavaArray
 * @see NativeJavaPackage
 * @see NativeJavaClass
 */

public class NativeJavaObject implements Scriptable, Wrapper, Serializable
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.ping();
  }

    static final long serialVersionUID = -6948590651130498591L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[1]++;
  }

    public NativeJavaObject() { }

    public NativeJavaObject(Scriptable scope, Object javaObject,
                            Class<?> staticType)
    {
        this(scope, javaObject, staticType, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[2]++;
    }

    public NativeJavaObject(Scriptable scope, Object javaObject,
                            Class<?> staticType, boolean isAdapter)
    {
        this.parent = scope;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[3]++;
        this.javaObject = javaObject;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[4]++;
        this.staticType = staticType;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[5]++;
        this.isAdapter = isAdapter;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[6]++;
        initMembers();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[7]++;
    }

    protected void initMembers() {
        Class<?> dynamicType;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((javaObject != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[1]++;
            dynamicType = javaObject.getClass();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[9]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[2]++;
            dynamicType = staticType;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[10]++;
        }
        members = JavaMembers.lookupClass(parent, dynamicType, staticType,
                                          isAdapter);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[11]++;
        fieldAndMethods
            = members.getFieldAndMethodsObjects(this, javaObject, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[12]++;
    }

    public boolean has(String name, Scriptable start) {
        return members.has(name, false);
    }

    public boolean has(int index, Scriptable start) {
        return false;
    }

    public Object get(String name, Scriptable start) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fieldAndMethods != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[3]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[14]++;
            Object result = fieldAndMethods.get(name);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[5]++;
                return result;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[4]++;}
        // TODO: passing 'this' as the scope is bogus since it has
        //  no parent scope
        return members.get(this, name, javaObject, false);
    }

    public Object get(int index, Scriptable start) {
        throw members.reportMemberNotFound(Integer.toString(index));
    }

    public void put(String name, Scriptable start, Object value) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        // We could be asked to modify the value of a property in the
        // prototype. Since we can't add a property to a Java object,
        // we modify it in the prototype rather than copy it down.
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((prototype == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((members.has(name, false)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[7]++;
            members.put(this, name, javaObject, value, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[17]++;
}
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[8]++;
            prototype.put(name, prototype, value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[18]++;
}
    }

    public void put(int index, Scriptable start, Object value) {
        throw members.reportMemberNotFound(Integer.toString(index));
    }

    public boolean hasInstance(Scriptable value) {
        // This is an instance of a Java class, so always return false
        return false;
    }

    public void delete(String name) {
    }

    public void delete(int index) {
    }

    public Scriptable getPrototype() {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((prototype == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((javaObject instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[9]++;
            return TopLevel.getBuiltinPrototype(
                    ScriptableObject.getTopLevelScope(parent),
                    TopLevel.Builtins.String);

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[10]++;}
        return prototype;
    }

    /**
     * Sets the prototype of the object.
     */
    public void setPrototype(Scriptable m) {
        prototype = m;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[20]++;
    }

    /**
     * Returns the parent (enclosing) scope of the object.
     */
    public Scriptable getParentScope() {
        return parent;
    }

    /**
     * Sets the parent (enclosing) scope of the object.
     */
    public void setParentScope(Scriptable m) {
        parent = m;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[21]++;
    }

    public Object[] getIds() {
        return members.getIds(false);
    }

/**
@deprecated Use {@link Context#getWrapFactory()} together with calling {@link
WrapFactory#wrap(Context, Scriptable, Object, Class)}
*/
    public static Object wrap(Scriptable scope, Object obj, Class<?> staticType) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[22]++;

        Context cx = Context.getContext();
        return cx.getWrapFactory().wrap(cx, scope, obj, staticType);
    }

    public Object unwrap() {
        return javaObject;
    }

    public String getClassName() {
        return "JavaObject";
    }

    public Object getDefaultValue(Class<?> hint)
    {
        Object value;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((hint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[11]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((javaObject instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[13]++;
                hint = ScriptRuntime.BooleanClass;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[25]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[12]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((hint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[15]++;
            value = javaObject.toString();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[27]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[16]++;
            String converterName;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[17]++;
                converterName = "booleanValue";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[29]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[18]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[30]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.NumberClass) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[19]++;
                converterName = "doubleValue";
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[31]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[20]++;
                throw Context.reportRuntimeError0("msg.default.value");
            }
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[32]++;
            Object converterObject = get(converterName, this);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((converterObject instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[21]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[34]++;
                Function f = (Function)converterObject;
                value = f.call(Context.getContext(), f.getParentScope(),
                               this, ScriptRuntime.emptyArgs);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[35]++;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[22]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[36]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((hint == ScriptRuntime.NumberClass) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((javaObject instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false))
                {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[23]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[37]++;
                    boolean b = ((Boolean)javaObject).booleanValue();
                    value = ScriptRuntime.wrapNumber(b ? 1.0 : 0.0);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[38]++;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[24]++;
                    value = javaObject.toString();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[39]++;
                }
            }
        }
        return value;
    }

    /**
     * Determine whether we can/should convert between the given type and the
     * desired one.  This should be superceded by a conversion-cost calculation
     * function, but for now I'll hide behind precedent.
     */
    public static boolean canConvert(Object fromObj, Class<?> to) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[40]++;
        int weight = getConversionWeight(fromObj, to);

        return (weight < CONVERSION_NONE);
    }

    private static final int JSTYPE_UNDEFINED   = 0;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[41]++;
  } // undefined type
    private static final int JSTYPE_NULL        = 1;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[42]++;
  } // null
    private static final int JSTYPE_BOOLEAN     = 2;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[43]++;
  } // boolean
    private static final int JSTYPE_NUMBER      = 3;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[44]++;
  } // number
    private static final int JSTYPE_STRING      = 4;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[45]++;
  } // string
    private static final int JSTYPE_JAVA_CLASS  = 5;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[46]++;
  } // JavaClass
    private static final int JSTYPE_JAVA_OBJECT = 6;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[47]++;
  } // JavaObject
    private static final int JSTYPE_JAVA_ARRAY  = 7;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[48]++;
  } // JavaArray
    private static final int JSTYPE_OBJECT      = 8;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[49]++;
  } // Scriptable

    static final byte CONVERSION_TRIVIAL      = 1;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[50]++;
  }
    static final byte CONVERSION_NONTRIVIAL   = 0;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[51]++;
  }
    static final byte CONVERSION_NONE         = 99;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[52]++;
  }

    /**
     * Derive a ranking based on how "natural" the conversion is.
     * The special value CONVERSION_NONE means no conversion is possible,
     * and CONVERSION_NONTRIVIAL signals that more type conformance testing
     * is required.
     * Based on
     * <a href="http://www.mozilla.org/js/liveconnect/lc3_method_overloading.html">
     * "preferred method conversions" from Live Connect 3</a>
     */
    static int getConversionWeight(Object fromObj, Class<?> to) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[53]++;
        int fromCode = getJSTypeCode(fromObj);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[54]++;

        switch (fromCode) {

        case JSTYPE_UNDEFINED:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[25]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[26]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[27]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[56]++;
            break;

        case JSTYPE_NULL:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[28]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[57]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((to.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[29]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[30]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[58]++;
            break;

        case JSTYPE_BOOLEAN:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[31]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
            // "boolean" is #1
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((to == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[32]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[33]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[60]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[34]++;
                return 2;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[35]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[61]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[36]++;
                return 3;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[37]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[62]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[38]++;
                return 4;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[39]++;}
}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[63]++;
            break;

        case JSTYPE_NUMBER:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[40]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[64]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((to.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[41]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[65]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((to == Double.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[43]++;
                    return 1;

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[44]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[66]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((to != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[45]++;
                    return 1 + getSizeRank(to);

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[46]++;}
}

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[42]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[67]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[47]++;
                    // native numbers are #1-8
                    return 9;

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[48]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[68]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[49]++;
                    return 10;

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[50]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[69]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((ScriptRuntime.NumberClass.isAssignableFrom(to)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[51]++;
                    // "double" is #1
                    return 2;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[52]++;}
}
}
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[70]++;
            break;

        case JSTYPE_STRING:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[53]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[71]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[54]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[55]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[72]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((to.isInstance(fromObj)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[56]++;
                return 2;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[57]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[73]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((to.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[58]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[74]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((to == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[60]++;
                    return 3;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[61]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[75]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((to != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[62]++;
                    return 4;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[63]++;}
}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[59]++;}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[76]++;
            break;

        case JSTYPE_JAVA_CLASS:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[64]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[77]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.ClassClass) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[65]++;
                return 1;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[66]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[78]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[67]++;
                return 3;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[68]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[79]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[69]++;
                return 4;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[70]++;}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[80]++;
            break;

        case JSTYPE_JAVA_OBJECT:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[71]++;
        case JSTYPE_JAVA_ARRAY:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[72]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[81]++;
            Object javaObj = fromObj;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[82]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((javaObj instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[73]++;
                javaObj = ((Wrapper)javaObj).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[83]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[74]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[84]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((to.isInstance(javaObj)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[75]++;
                return CONVERSION_NONTRIVIAL;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[76]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[85]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[77]++;
                return 2;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[78]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[86]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((to.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((to != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[79]++;
                return (fromCode == JSTYPE_JAVA_ARRAY)
                       ? CONVERSION_NONE : 2 + getSizeRank(to);

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[80]++;}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[87]++;
            break;

        case JSTYPE_OBJECT:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[81]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[88]++;
int CodeCoverConditionCoverageHelper_C37;
            // Other objects takes #1-#3 spots
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((to != ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((to.isInstance(fromObj)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[82]++;
                // No conversion required, but don't apply for java.lang.Object
                return 1;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[83]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[89]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((to.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[84]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[90]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((fromObj instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[86]++;
                    // This is a native array conversion to a java array
                    // Array conversions are all equal, and preferable to object
                    // and string conversion, per LC3.
                    return 2;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[87]++;}

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[85]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[91]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[88]++;
                return 3;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[89]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[92]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[90]++;
                return 4;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[91]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[93]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((to == ScriptRuntime.DateClass) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[92]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[94]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((fromObj instanceof NativeDate) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[94]++;
                    // This is a native date to java date conversion
                    return 1;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[95]++;}

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[93]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[95]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((to.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[96]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[96]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((fromObj instanceof NativeObject) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((fromObj instanceof NativeFunction) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[98]++;
                    // See comments in createInterfaceAdapter
                    return 1;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[99]++;}
                return 12;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[97]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[97]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((to.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((to != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[100]++;
                return 4 + getSizeRank(to);

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[101]++;}
}
}
}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[98]++;
            break; default : CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[102]++;
        }

        return CONVERSION_NONE;
    }

    static int getSizeRank(Class<?> aType) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[99]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((aType == Double.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[103]++;
            return 1;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[104]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[100]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((aType == Float.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[105]++;
            return 2;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[106]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[101]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((aType == Long.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[107]++;
            return 3;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[108]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[102]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((aType == Integer.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[109]++;
            return 4;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[110]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[103]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((aType == Short.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[111]++;
            return 5;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[112]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[104]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((aType == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[113]++;
            return 6;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[114]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[105]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((aType == Byte.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[115]++;
            return 7;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[116]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[106]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((aType == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[117]++;
            return CONVERSION_NONE;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[118]++;
            return 8;
        }
}
}
}
}
}
}
}
    }

    private static int getJSTypeCode(Object value) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[107]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[119]++;
            return JSTYPE_NULL;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[120]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[108]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[121]++;
            return JSTYPE_UNDEFINED;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[122]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[109]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((value instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[123]++;
            return JSTYPE_STRING;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[124]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[110]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[125]++;
            return JSTYPE_NUMBER;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[126]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[111]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[127]++;
            return JSTYPE_BOOLEAN;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[128]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[112]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[129]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[113]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((value instanceof NativeJavaClass) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[131]++;
                return JSTYPE_JAVA_CLASS;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[132]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[114]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((value instanceof NativeJavaArray) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[133]++;
                return JSTYPE_JAVA_ARRAY;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[134]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[115]++;
int CodeCoverConditionCoverageHelper_C63; if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[135]++;
                return JSTYPE_JAVA_OBJECT;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[136]++;
                return JSTYPE_OBJECT;
            }
}
}

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[130]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[116]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((value instanceof Class) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[137]++;
            return JSTYPE_JAVA_CLASS;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[138]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[117]++;
            Class<?> valueClass = value.getClass();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[118]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((valueClass.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[139]++;
                return JSTYPE_JAVA_ARRAY;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[140]++;
                return JSTYPE_JAVA_OBJECT;
            }
        }
}
}
}
}
}
}
    }

    /**
     * Not intended for public use. Callers should use the
     * public API Context.toType.
     * @deprecated as of 1.5 Release 4
     * @see org.mozilla.javascript.Context#jsToJava(Object, Class)
     */
    public static Object coerceType(Class<?> type, Object value)
    {
        return coerceTypeImpl(type, value);
    }

    /**
     * Type-munging for field setting and method invocation.
     * Conforms to LC3 specification
     */
    static Object coerceTypeImpl(Class<?> type, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[119]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((value.getClass() == type) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[141]++;
            return value;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[142]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[120]++;

        switch (getJSTypeCode(value)) {

        case JSTYPE_NULL:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[143]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[121]++;
int CodeCoverConditionCoverageHelper_C67;
            // raise error if type.isPrimitive()
            if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((type.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[144]++;
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[122]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[145]++;}
            return null;

        case JSTYPE_UNDEFINED:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[146]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[123]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[147]++;
                return "undefined";

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[148]++;
                reportConversionError("undefined", type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[124]++;
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[125]++;
            break;

        case JSTYPE_BOOLEAN:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[149]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[126]++;
int CodeCoverConditionCoverageHelper_C69;
            // Under LC3, only JS Booleans can be coerced into a Boolean value
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((type == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[150]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[151]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[127]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[152]++;
                return value.toString();

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[153]++;
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[128]++;
            }
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[129]++;
            break;

        case JSTYPE_NUMBER:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[154]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[130]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[155]++;
                return ScriptRuntime.toString(value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[156]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[131]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[157]++;
                return coerceToNumber(Double.TYPE, value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[158]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[132]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C73 |= (32)) == 0 || true) &&
 ((type.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((type != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((ScriptRuntime.NumberClass.isAssignableFrom(type)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[159]++;
                return coerceToNumber(type, value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[160]++;
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[133]++;
            }
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[134]++;
            break;

        case JSTYPE_STRING:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[161]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[135]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((type.isInstance(value)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[162]++;
                return value.toString();

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[163]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[136]++;
int CodeCoverConditionCoverageHelper_C75; if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((type == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.CharacterClass) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[164]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[137]++;
int CodeCoverConditionCoverageHelper_C76;
                // Special case for converting a single char string to a
                // character
                // Placed here because it applies *only* to JS strings,
                // not other JS objects converted to strings
                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((((CharSequence)value).length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[166]++;
                    return Character.valueOf(((CharSequence)value).charAt(0));

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[167]++;
                    return coerceToNumber(type, value);
                }

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[165]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[138]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C77 |= (32)) == 0 || true) &&
 ((type.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((type != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((ScriptRuntime.NumberClass.isAssignableFrom(type)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 3) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[168]++;
                return coerceToNumber(type, value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[169]++;
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[139]++;
            }
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[140]++;
            break;

        case JSTYPE_JAVA_CLASS:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[170]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[141]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[171]++;
                value = ((Wrapper)value).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[142]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[172]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[143]++;
int CodeCoverConditionCoverageHelper_C79;

            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.ClassClass) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[173]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[174]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[144]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[175]++;
                return value.toString();

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[176]++;
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[145]++;
            }
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[146]++;
            break;

        case JSTYPE_JAVA_OBJECT:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[177]++;
        case JSTYPE_JAVA_ARRAY:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[178]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[147]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[179]++;
              value = ((Wrapper)value).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[148]++;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[180]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[149]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((type.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[181]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[150]++;
int CodeCoverConditionCoverageHelper_C83;
                if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((type == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[183]++;
                    reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[151]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[184]++;}
                return coerceToNumber(type, value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[182]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[152]++;
int CodeCoverConditionCoverageHelper_C84;
              if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[185]++;
                    return value.toString();

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[186]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[153]++;
int CodeCoverConditionCoverageHelper_C85;
                    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((type.isInstance(value)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[187]++;
                        return value;

                    }
                    else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[188]++;
                        reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[154]++;
                    }
                }
            }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[155]++;
            break;

        case JSTYPE_OBJECT:
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[189]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[156]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[190]++;
                return ScriptRuntime.toString(value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[191]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[157]++;
int CodeCoverConditionCoverageHelper_C87; if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((type.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[192]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[158]++;
int CodeCoverConditionCoverageHelper_C88;
                if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((type == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[194]++;
                    reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[159]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[195]++;}
                return coerceToNumber(type, value);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[193]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[160]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((type.isInstance(value)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[196]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[197]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[161]++;
int CodeCoverConditionCoverageHelper_C90; if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.DateClass) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((value instanceof NativeDate) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 2) && false))
            {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[198]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[162]++;
                double time = ((NativeDate)value).getJSTimeValue();
                // XXX: This will replace NaN by 0
                return new Date((long)time);

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[199]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[163]++;
int CodeCoverConditionCoverageHelper_C91; if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((type.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((value instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[200]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[164]++;
                // Make a new java array, and coerce the JS array components
                // to the target (component) type.
                NativeArray array = (NativeArray) value;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[165]++;
                long length = array.getLength();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[166]++;
                Class<?> arrayType = type.getComponentType();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[167]++;
                Object Result = Array.newInstance(arrayType, (int)length);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[168]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.loops[1]++;


int CodeCoverConditionCoverageHelper_C92;
                for (int i = 0 ;(((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false) ; ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.loops[1]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.loops[2]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.loops[3]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[169]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                    try  {
CodeCoverTryBranchHelper_Try1 = true;
                        Array.set(Result, i, coerceTypeImpl(
                                arrayType, array.get(i, array)));
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[170]++;
                    }
                    catch (EvaluatorException ee) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[203]++;
                        reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[171]++;
                    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[202]++;
}
  }
                }

                return Result;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[201]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[172]++;
int CodeCoverConditionCoverageHelper_C93; if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[204]++;
                value = ((Wrapper)value).unwrap();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[173]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[174]++;
int CodeCoverConditionCoverageHelper_C94;
                if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((type.isInstance(value)) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[206]++;
                    return value;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[207]++;}
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[175]++;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[205]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[176]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (32)) == 0 || true) &&
 ((type.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((value instanceof NativeObject) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((value instanceof NativeFunction) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[208]++;
                // Try to use function/object as implementation of Java interface.
                return createInterfaceAdapter(type, (ScriptableObject) value);

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[209]++;
                reportConversionError(value, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[177]++;
            }
}
}
}
}
}
}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[178]++;
            break; default : CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[210]++;
        }

        return value;
    }

    protected static Object createInterfaceAdapter(Class<?>type, ScriptableObject so) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[179]++;
        // XXX: Currently only instances of ScriptableObject are
        // supported since the resulting interface proxies should
        // be reused next time conversion is made and generic
        // Callable has no storage for it. Weak references can
        // address it but for now use this restriction.

        Object key = Kit.makeHashKeyFromPair(COERCED_INTERFACE_KEY, type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[180]++;
        Object old = so.getAssociatedValue(key);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[181]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((old != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[211]++;
            // Function was already wrapped
            return old;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[212]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[182]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[183]++;
        Object glue = InterfaceAdapter.create(cx, type, so);
        // Store for later retrieval
        glue = so.associateValue(key, glue);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[184]++;
        return glue;
    }

    private static Object coerceToNumber(Class<?> type, Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[185]++;
        Class<?> valueClass = value.getClass();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[186]++;
int CodeCoverConditionCoverageHelper_C97;

        // Character
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (8)) == 0 || true) &&
 ((type == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.CharacterClass) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[213]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[187]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((valueClass == ScriptRuntime.CharacterClass) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[215]++;
                return value;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[216]++;}
            return Character.valueOf((char)toInteger(value,
                                                 ScriptRuntime.CharacterClass,
                                                 Character.MIN_VALUE,
                                                 Character.MAX_VALUE));

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[214]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[188]++;
int CodeCoverConditionCoverageHelper_C99;

        // Double, Float
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (32)) == 0 || true) &&
 ((type == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.DoubleClass) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((type == Double.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[217]++;
            return valueClass == ScriptRuntime.DoubleClass
                ? value
                : new Double(toDouble(value));

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[218]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[189]++;
int CodeCoverConditionCoverageHelper_C100;

        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.FloatClass) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((type == Float.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[219]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[190]++;
int CodeCoverConditionCoverageHelper_C101;
            if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((valueClass == ScriptRuntime.FloatClass) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[221]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[222]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[191]++;
                double number = toDouble(value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[192]++;
int CodeCoverConditionCoverageHelper_C102;
                if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (32)) == 0 || true) &&
 ((Double.isInfinite(number)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((Double.isNaN(number)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((number == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[223]++;
                    return new Float((float)number);

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[224]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[193]++;
                    double absNumber = Math.abs(number);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[194]++;
int CodeCoverConditionCoverageHelper_C103;
                    if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((absNumber < Float.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[225]++;
                        return new Float((number > 0.0) ? +0.0 : -0.0);

                    }
                    else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[226]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[195]++;
int CodeCoverConditionCoverageHelper_C104; if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((absNumber > Float.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[227]++;
                        return new Float((number > 0.0) ?
                                         Float.POSITIVE_INFINITY :
                                         Float.NEGATIVE_INFINITY);

                    }
                    else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[228]++;
                        return new Float((float)number);
                    }
}
                }
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[220]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[196]++;
int CodeCoverConditionCoverageHelper_C105;

        // Integer, Long, Short, Byte
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.IntegerClass) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((type == Integer.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[229]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[197]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((valueClass == ScriptRuntime.IntegerClass) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[231]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[232]++;
                return Integer.valueOf((int)toInteger(value,
                                                  ScriptRuntime.IntegerClass,
                                                  Integer.MIN_VALUE,
                                                  Integer.MAX_VALUE));
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[230]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[198]++;
int CodeCoverConditionCoverageHelper_C107;

        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.LongClass) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((type == Long.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[233]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[199]++;
int CodeCoverConditionCoverageHelper_C108;
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((valueClass == ScriptRuntime.LongClass) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[235]++;
                return value;

            } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[236]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[200]++;
                /* Long values cannot be expressed exactly in doubles.
                 * We thus use the largest and smallest double value that
                 * has a value expressible as a long value. We build these
                 * numerical values from their hexidecimal representations
                 * to avoid any problems caused by attempting to parse a
                 * decimal representation.
                 */
                final double max = Double.longBitsToDouble(0x43dfffffffffffffL);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[201]++;
                final double min = Double.longBitsToDouble(0xc3e0000000000000L);
                return Long.valueOf(toInteger(value,
                                          ScriptRuntime.LongClass,
                                          min,
                                          max));
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[234]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[202]++;
int CodeCoverConditionCoverageHelper_C109;

        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.ShortClass) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((type == Short.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[237]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[203]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((valueClass == ScriptRuntime.ShortClass) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[239]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[240]++;
                return Short.valueOf((short)toInteger(value,
                                                  ScriptRuntime.ShortClass,
                                                  Short.MIN_VALUE,
                                                  Short.MAX_VALUE));
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[238]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[204]++;
int CodeCoverConditionCoverageHelper_C111;

        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.ByteClass) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((type == Byte.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[241]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[205]++;
int CodeCoverConditionCoverageHelper_C112;
            if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((valueClass == ScriptRuntime.ByteClass) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[243]++;
                return value;

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[244]++;
                return Byte.valueOf((byte)toInteger(value,
                                                ScriptRuntime.ByteClass,
                                                Byte.MIN_VALUE,
                                                Byte.MAX_VALUE));
            }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[242]++;}

        return new Double(toDouble(value));
    }


    private static double toDouble(Object value)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[206]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[245]++;
            return ((Number)value).doubleValue();

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[246]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[207]++;
int CodeCoverConditionCoverageHelper_C114; if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[247]++;
            return ScriptRuntime.toNumber((String)value);

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[248]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[208]++;
int CodeCoverConditionCoverageHelper_C115; if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[249]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[209]++;
int CodeCoverConditionCoverageHelper_C116;
            if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[251]++;
                // XXX: optimize tail-recursion?
                return toDouble(((Wrapper)value).unwrap());

            }
            else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[252]++;
                return ScriptRuntime.toNumber(value);
            }

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[250]++;
            Method meth;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[210]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                meth = value.getClass().getMethod("doubleValue",
                		                          (Class [])null);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[211]++;
            }
            catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[254]++;
                meth = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[212]++;
            }
            catch (SecurityException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[255]++;
                meth = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[213]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[253]++;
}
  }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[214]++;
int CodeCoverConditionCoverageHelper_C117;
            if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((meth != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[256]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[215]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
                try {
CodeCoverTryBranchHelper_Try3 = true;
                    return ((Number)meth.invoke(value,
                    		                    (Object [])null)).doubleValue();
                }
                catch (IllegalAccessException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[259]++;
                    // XXX: ignore, or error message?
                    reportConversionError(value, Double.TYPE);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[216]++;
                }
                catch (InvocationTargetException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[260]++;
                    // XXX: ignore, or error message?
                    reportConversionError(value, Double.TYPE);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[217]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[258]++;
}
  }

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[257]++;}
            return ScriptRuntime.toNumber(value.toString());
        }
}
}
    }

    private static long toInteger(Object value, Class<?> type,
                                  double min, double max)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[218]++;
        double d = toDouble(value);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[219]++;
int CodeCoverConditionCoverageHelper_C118;

        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((Double.isInfinite(d)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((Double.isNaN(d)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[261]++;
            // Convert to string first, for more readable message
            reportConversionError(ScriptRuntime.toString(value), type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[220]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[262]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[221]++;
int CodeCoverConditionCoverageHelper_C119;

        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((d > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[263]++;
            d = Math.floor(d);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[222]++;

        }
        else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[264]++;
            d = Math.ceil(d);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[223]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[224]++;
int CodeCoverConditionCoverageHelper_C120;

        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (8)) == 0 || true) &&
 ((d < min) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((d > max) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 2) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 2) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[265]++;
            // Convert to string first, for more readable message
            reportConversionError(ScriptRuntime.toString(value), type);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[225]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[266]++;}
        return (long)d;
    }

    static void reportConversionError(Object value, Class<?> type)
    {
        // It uses String.valueOf(value), not value.toString() since
        // value can be null, bug 282447.
        throw Context.reportRuntimeError2(
            "msg.conversion.not.allowed",
            String.valueOf(value),
            JavaMembers.javaSignature(type));
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[226]++;

        out.writeBoolean(isAdapter);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[227]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[228]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((isAdapter) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[267]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[229]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((adapter_writeAdapterObject == null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[269]++;
                throw new IOException();

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[270]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[230]++;
            Object[] args = { javaObject, out };
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[231]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
            try {
CodeCoverTryBranchHelper_Try4 = true;
                adapter_writeAdapterObject.invoke(null, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[232]++;
            } catch (Exception ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[272]++;
                throw new IOException();
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[271]++;
}
  }

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[268]++;
            out.writeObject(javaObject);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[233]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[234]++;
int CodeCoverConditionCoverageHelper_C123;

        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((staticType != null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[273]++;
            out.writeObject(staticType.getClass().getName());
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[235]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[274]++;
            out.writeObject(null);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[236]++;
        }
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[237]++;

        isAdapter = in.readBoolean();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[238]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[239]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((isAdapter) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[275]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[240]++;
int CodeCoverConditionCoverageHelper_C125;
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((adapter_readAdapterObject == null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[277]++;
                throw new ClassNotFoundException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[278]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[241]++;
            Object[] args = { this, in };
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[242]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
            try {
CodeCoverTryBranchHelper_Try5 = true;
                javaObject = adapter_readAdapterObject.invoke(null, args);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[243]++;
            } catch (Exception ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[280]++;
                throw new IOException();
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[279]++;
}
  }

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[276]++;
            javaObject = in.readObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[244]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[245]++;

        String className = (String)in.readObject();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[246]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((className != null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[281]++;
            staticType = Class.forName(className);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[247]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[282]++;
            staticType = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[248]++;
        }

        initMembers();
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[249]++;
    }

    /**
     * The prototype of this object.
     */
    protected Scriptable prototype;

    /**
     * The parent scope of this object.
     */
    protected Scriptable parent;

    protected transient Object javaObject;

    protected transient Class<?> staticType;
    protected transient JavaMembers members;
    private transient Map<String,FieldAndMethods> fieldAndMethods;
    protected transient boolean isAdapter;

    private static final Object COERCED_INTERFACE_KEY = "Coerced Interface";
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[250]++;
  }
    private static Method adapter_writeAdapterObject;
    private static Method adapter_readAdapterObject;

    static {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[251]++;
        // Reflection in java is verbose
        Class<?>[] sig2 = new Class[2];
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[252]++;
        Class<?> cl = Kit.classOrNull("org.mozilla.javascript.JavaAdapter");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[253]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((cl != null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[283]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[254]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
            try {
CodeCoverTryBranchHelper_Try6 = true;
                sig2[0] = ScriptRuntime.ObjectClass;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[255]++;
                sig2[1] = Kit.classOrNull("java.io.ObjectOutputStream");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[256]++;
                adapter_writeAdapterObject = cl.getMethod("writeAdapterObject",
                                                          sig2);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[257]++;

                sig2[0] = ScriptRuntime.ScriptableClass;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[258]++;
                sig2[1] = Kit.classOrNull("java.io.ObjectInputStream");
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[259]++;
                adapter_readAdapterObject = cl.getMethod("readAdapterObject",
                                                         sig2);
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[260]++;

            } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[286]++;
                adapter_writeAdapterObject = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[261]++;
                adapter_readAdapterObject = null;
CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.statements[262]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[285]++;
}
  }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1.branches[284]++;}
    }

}

class CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1 ());
  }
    public static long[] statements = new long[263];
    public static long[] branches = new long[287];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[128];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJavaObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,2,1,1,2,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,3,1,1,1,3,2,2,1,3,1,2,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,3,1,2,1,3,2,1,3,1,1,2,1,2,1,2,1,2,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 127; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$adralqrs9n89mg8nsd1xkaz7asud8c3imcxk25ta0ym2uvs1 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 262; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 286; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 127; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaObject.java");
      for (int i = 1; i <= 262; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 286; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 127; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

