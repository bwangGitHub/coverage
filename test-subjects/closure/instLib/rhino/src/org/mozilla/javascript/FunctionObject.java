/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

import java.lang.reflect.*;
import java.io.*;

public class FunctionObject extends BaseFunction
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.ping();
  }

    static final long serialVersionUID = -5332312783643935019L;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[1]++;
  }

    /**
     * Create a JavaScript function object from a Java method.
     *
     * <p>The <code>member</code> argument must be either a java.lang.reflect.Method
     * or a java.lang.reflect.Constructor and must match one of two forms.<p>
     *
     * The first form is a member with zero or more parameters
     * of the following types: Object, String, boolean, Scriptable,
     * int, or double. The Long type is not supported
     * because the double representation of a long (which is the
     * EMCA-mandated storage type for Numbers) may lose precision.
     * If the member is a Method, the return value must be void or one
     * of the types allowed for parameters.<p>
     *
     * The runtime will perform appropriate conversions based
     * upon the type of the parameter. A parameter type of
     * Object specifies that no conversions are to be done. A parameter
     * of type String will use Context.toString to convert arguments.
     * Similarly, parameters of type double, boolean, and Scriptable
     * will cause Context.toNumber, Context.toBoolean, and
     * Context.toObject, respectively, to be called.<p>
     *
     * If the method is not static, the Java 'this' value will
     * correspond to the JavaScript 'this' value. Any attempt
     * to call the function with a 'this' value that is not
     * of the right Java type will result in an error.<p>
     *
     * The second form is the variable arguments (or "varargs")
     * form. If the FunctionObject will be used as a constructor,
     * the member must have the following parameters
     * <pre>
     *      (Context cx, Object[] args, Function ctorObj,
     *       boolean inNewExpr)</pre>
     * and if it is a Method, be static and return an Object result.<p>
     *
     * Otherwise, if the FunctionObject will <i>not</i> be used to define a
     * constructor, the member must be a static Method with parameters
     * <pre>
     *      (Context cx, Scriptable thisObj, Object[] args,
     *       Function funObj) </pre>
     * and an Object result.<p>
     *
     * When the function varargs form is called as part of a function call,
     * the <code>args</code> parameter contains the
     * arguments, with <code>thisObj</code>
     * set to the JavaScript 'this' value. <code>funObj</code>
     * is the function object for the invoked function.<p>
     *
     * When the constructor varargs form is called or invoked while evaluating
     * a <code>new</code> expression, <code>args</code> contains the
     * arguments, <code>ctorObj</code> refers to this FunctionObject, and
     * <code>inNewExpr</code> is true if and only if  a <code>new</code>
     * expression caused the call. This supports defining a function that
     * has different behavior when called as a constructor than when
     * invoked as a normal function call. (For example, the Boolean
     * constructor, when called as a function,
     * will convert to boolean rather than creating a new object.)<p>
     *
     * @param name the name of the function
     * @param methodOrConstructor a java.lang.reflect.Method or a java.lang.reflect.Constructor
     *                            that defines the object
     * @param scope enclosing scope of function
     * @see org.mozilla.javascript.Scriptable
     */
    public FunctionObject(String name, Member methodOrConstructor,
                          Scriptable scope)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((methodOrConstructor instanceof Constructor) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[1]++;
            member = new MemberBox((Constructor<?>) methodOrConstructor);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[3]++;
            isStatic = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[4]++;
 // well, doesn't take a 'this'
        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[2]++;
            member = new MemberBox((Method) methodOrConstructor);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[5]++;
            isStatic = member.isStatic();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[6]++;
        }
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[7]++;
        String methodName = member.getName();
        this.functionName = name;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[8]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[9]++;
        Class<?>[] types = member.argTypes;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[10]++;
        int arity = types.length;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((arity == 4) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((types[1].isArray()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((types[2].isArray()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[3]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            // Either variable args or an error.
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((types[1].isArray()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[5]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (512)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((types[0] != ScriptRuntime.ContextClass) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((types[1].getComponentType() != ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((types[2] != ScriptRuntime.FunctionClass) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((types[3] != Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 5) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 5) && false))
                {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[7]++;
                    throw Context.reportRuntimeError1(
                        "msg.varargs.ctor", methodName);

                } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[8]++;}
                parmsLength = VARARGS_CTOR;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[14]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[6]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (512)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (128)) == 0 || true) &&
 ((types[0] != ScriptRuntime.ContextClass) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((types[1] != ScriptRuntime.ScriptableClass) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((types[2].getComponentType() != ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((types[3] != ScriptRuntime.FunctionClass) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 5) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 5) && false))
                {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[9]++;
                    throw Context.reportRuntimeError1(
                        "msg.varargs.fun", methodName);

                } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[10]++;}
                parmsLength = VARARGS_METHOD;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[16]++;
            }

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[4]++;
            parmsLength = arity;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[17]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((arity > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[11]++;
                typeTags = new byte[arity];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[19]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[20]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i != arity) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[1]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[2]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[3]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[21]++;
                    int tag = getTypeTag(types[i]);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((tag == JAVA_UNSUPPORTED_TYPE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[13]++;
                        throw Context.reportRuntimeError2(
                            "msg.bad.parms", types[i].getName(), methodName);

                    } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[14]++;}
                    typeTags[i] = (byte)tag;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[23]++;
                }

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[12]++;}
        }
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((member.isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[15]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[25]++;
            Method method = member.method();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[26]++;
            Class<?> returnType = method.getReturnType();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((returnType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[17]++;
                hasVoidReturn = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[28]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[18]++;
                returnTypeTag = getTypeTag(returnType);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[29]++;
            }

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[16]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[30]++;
            Class<?> ctorType = member.getDeclaringClass();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ScriptRuntime.ScriptableClass.isAssignableFrom(ctorType)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[19]++;
                throw Context.reportRuntimeError1(
                    "msg.bad.ctor.return", ctorType.getName());

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[20]++;}
        }

        ScriptRuntime.setFunctionProtoAndParent(this, scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[32]++;
    }

    /**
     * @return One of <tt>JAVA_*_TYPE</tt> constants to indicate desired type
     *         or {@link #JAVA_UNSUPPORTED_TYPE} if the convertion is not
     *         possible
     */
    public static int getTypeTag(Class<?> type)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[33]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[21]++;
            return JAVA_STRING_TYPE;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[22]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[34]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.IntegerClass) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type == Integer.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[23]++;
            return JAVA_INT_TYPE;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[24]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((type == Boolean.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[25]++;
            return JAVA_BOOLEAN_TYPE;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[26]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[36]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((type == ScriptRuntime.DoubleClass) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((type == Double.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[27]++;
            return JAVA_DOUBLE_TYPE;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[28]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[37]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ScriptRuntime.ScriptableClass.isAssignableFrom(type)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[29]++;
            return JAVA_SCRIPTABLE_TYPE;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[30]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[38]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type == ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[31]++;
            return JAVA_OBJECT_TYPE;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[32]++;}

        // Note that the long type is not supported; see the javadoc for
        // the constructor for this class

        return JAVA_UNSUPPORTED_TYPE;
    }

    public static Object convertArg(Context cx, Scriptable scope,
                                    Object arg, int typeTag)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[39]++;
        switch (typeTag) {
          case JAVA_STRING_TYPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[33]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[40]++;
int CodeCoverConditionCoverageHelper_C18;
              if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((arg instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[34]++;
                return arg;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[35]++;}
            return ScriptRuntime.toString(arg);
          case JAVA_INT_TYPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[36]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[41]++;
int CodeCoverConditionCoverageHelper_C19;
              if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((arg instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[37]++;
                return arg;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[38]++;}
            return Integer.valueOf(ScriptRuntime.toInt32(arg));
          case JAVA_BOOLEAN_TYPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[39]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[42]++;
int CodeCoverConditionCoverageHelper_C20;
              if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((arg instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[40]++;
                return arg;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[41]++;}
            return ScriptRuntime.toBoolean(arg) ? Boolean.TRUE
                                                : Boolean.FALSE;
          case JAVA_DOUBLE_TYPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[42]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[43]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((arg instanceof Double) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[43]++;
                return arg;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[44]++;}
            return new Double(ScriptRuntime.toNumber(arg));
          case JAVA_SCRIPTABLE_TYPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[45]++;
              return ScriptRuntime.toObjectOrNull(cx, arg, scope);
          case JAVA_OBJECT_TYPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[46]++;
            return arg;
          default:
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[47]++;
            throw new IllegalArgumentException();
        }
    }

    /**
     * Return the value defined by  the method used to construct the object
     * (number of parameters of the method, or 1 if the method is a "varargs"
     * form).
     */
    @Override
    public int getArity() {
        return parmsLength < 0 ? 1 : parmsLength;
    }

    /**
     * Return the same value as {@link #getArity()}.
     */
    @Override
    public int getLength() {
        return getArity();
    }

    @Override
    public String getFunctionName()
    {
        return (functionName == null) ? "" : functionName;
    }

    /**
     * Get Java method or constructor this function represent.
     */
    public Member getMethodOrConstructor()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[44]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((member.isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[48]++;
            return member.method();

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[49]++;
            return member.ctor();
        }
    }

    static Method findSingleMethod(Method[] methods, String name)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[45]++;
        Method found = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[46]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[4]++;


int CodeCoverConditionCoverageHelper_C23;
        for (int i = 0, N = methods.length;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[4]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[5]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[6]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[47]++;
            Method method = methods[i];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[48]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((method != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((name.equals(method.getName())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[50]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[49]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((found != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[52]++;
                    throw Context.reportRuntimeError2(
                        "msg.no.overload", name,
                        method.getDeclaringClass().getName());

                } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[53]++;}
                found = method;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[50]++;

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[51]++;}
        }
        return found;
    }

    /**
     * Returns all public methods declared by the specified class. This excludes
     * inherited methods.
     *
     * @param clazz the class from which to pull public declared methods
     * @return the public methods declared in the specified class
     * @see Class#getDeclaredMethods()
     */
    static Method[] getMethodList(Class<?> clazz) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[51]++;
        Method[] methods = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[52]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[53]++;
int CodeCoverConditionCoverageHelper_C26;
            // getDeclaredMethods may be rejected by the security manager
            // but getMethods is more expensive
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((sawSecurityException) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[55]++;
                methods = clazz.getDeclaredMethods();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[54]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[56]++;}
        } catch (SecurityException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[57]++;
            // If we get an exception once, give up on getDeclaredMethods
            sawSecurityException = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[55]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[54]++;
}
  }
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[56]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((methods == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[58]++;
            methods = clazz.getMethods();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[57]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[59]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[58]++;
        int count = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[59]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[7]++;


int CodeCoverConditionCoverageHelper_C28;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i < methods.length) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[7]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[8]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[9]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[60]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((sawSecurityException) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 ? 
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((methods[i].getDeclaringClass() != clazz) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 : !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((Modifier.isPublic(methods[i].getModifiers())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false))
            {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[60]++;
                methods[i] = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[61]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[61]++;
                count++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[62]++;
            }
        }
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[63]++;
        Method[] result = new Method[count];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[64]++;
        int j=0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[65]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[10]++;


int CodeCoverConditionCoverageHelper_C30;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i < methods.length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[10]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[11]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[12]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[66]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((methods[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[62]++;
                result[j++] = methods[i];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[67]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[63]++;}
        }
        return result;
    }

    /**
     * Define this function as a JavaScript constructor.
     * <p>
     * Sets up the "prototype" and "constructor" properties. Also
     * calls setParent and setPrototype with appropriate values.
     * Then adds the function object as a property of the given scope, using
     *      <code>prototype.getClassName()</code>
     * as the name of the property.
     *
     * @param scope the scope in which to define the constructor (typically
     *              the global object)
     * @param prototype the prototype object
     * @see org.mozilla.javascript.Scriptable#setParentScope
     * @see org.mozilla.javascript.Scriptable#setPrototype
     * @see org.mozilla.javascript.Scriptable#getClassName
     */
    public void addAsConstructor(Scriptable scope, Scriptable prototype)
    {
        initAsConstructor(scope, prototype);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[68]++;
        defineProperty(scope, prototype.getClassName(),
                       this, ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[69]++;
    }

    void initAsConstructor(Scriptable scope, Scriptable prototype)
    {
        ScriptRuntime.setFunctionProtoAndParent(this, scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[70]++;
        setImmunePrototypeProperty(prototype);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[71]++;

        prototype.setParentScope(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[72]++;

        defineProperty(prototype, "constructor", this,
                       ScriptableObject.DONTENUM  |
                       ScriptableObject.PERMANENT |
                       ScriptableObject.READONLY);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[73]++;
        setParentScope(scope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[74]++;
    }

    /**
     * @deprecated Use {@link #getTypeTag(Class)}
     * and {@link #convertArg(Context, Scriptable, Object, int)}
     * for type conversion.
     */
    public static Object convertArg(Context cx, Scriptable scope,
                                    Object arg, Class<?> desired)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[75]++;
        int tag = getTypeTag(desired);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[76]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((tag == JAVA_UNSUPPORTED_TYPE) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[64]++;
            throw Context.reportRuntimeError1
                ("msg.cant.convert", desired.getName());

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[65]++;}
        return convertArg(cx, scope, arg, tag);
    }

    /**
     * Performs conversions on argument types if needed and
     * invokes the underlying Java method or constructor.
     * <p>
     * Implements Function.call.
     *
     * @see org.mozilla.javascript.Function#call(
     *          Context, Scriptable, Scriptable, Object[])
     */
    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        Object result;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[77]++;
        boolean checkMethodResult = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[78]++;
        int argsLength = args.length;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[79]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[13]++;


int CodeCoverConditionCoverageHelper_C33;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((i < argsLength) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[13]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[14]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[15]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[80]++;
int CodeCoverConditionCoverageHelper_C34;
            // flatten cons-strings before passing them as arguments
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((args[i] instanceof ConsString) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[66]++;
                args[i] = args[i].toString();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[81]++;

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[67]++;}
        }
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[82]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((parmsLength < 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[68]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[83]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((parmsLength == VARARGS_METHOD) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[70]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[84]++;
                Object[] invokeArgs = { cx, thisObj, args, this };
                result = member.invoke(null, invokeArgs);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[85]++;
                checkMethodResult = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[86]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[71]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[87]++;
                boolean inNewExpr = (thisObj == null);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[88]++;
                Boolean b = inNewExpr ? Boolean.TRUE : Boolean.FALSE;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[89]++;
                Object[] invokeArgs = { cx, args, this, b };
                result = (member.isCtor())
                         ? member.newInstance(invokeArgs)
                         : member.invoke(null, invokeArgs);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[90]++;
            }


        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[69]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[91]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((isStatic) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[72]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[92]++;
                Class<?> clazz = member.getDeclaringClass();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[93]++;
int CodeCoverConditionCoverageHelper_C38;
                if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((clazz.isInstance(thisObj)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[74]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[94]++;
                    boolean compatible = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[95]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((thisObj == scope) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[76]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[96]++;
                        Scriptable parentScope = getParentScope();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[97]++;
int CodeCoverConditionCoverageHelper_C40;
                        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((scope != parentScope) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[78]++;
                            // Call with dynamic scope for standalone function,
                            // use parentScope as thisObj
                            compatible = clazz.isInstance(parentScope);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[98]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[99]++;
int CodeCoverConditionCoverageHelper_C41;
                            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((compatible) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[80]++;
                                thisObj = parentScope;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[100]++;

                            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[81]++;}

                        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[79]++;}

                    } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[77]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[101]++;
int CodeCoverConditionCoverageHelper_C42;
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((compatible) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[82]++;
                        // Couldn't find an object to call this on.
                        throw ScriptRuntime.typeError1("msg.incompat.call",
                                                       functionName);

                    } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[83]++;}

                } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[75]++;}

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[73]++;}

            Object[] invokeArgs;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[102]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((parmsLength == argsLength) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[84]++;
                // Do not allocate new argument array if java arguments are
                // the same as the original js ones.
                invokeArgs = args;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[103]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[104]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[16]++;


int CodeCoverConditionCoverageHelper_C44;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i != parmsLength) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[16]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[17]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[18]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[105]++;
                    Object arg = args[i];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[106]++;
                    Object converted = convertArg(cx, scope, arg, typeTags[i]);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[107]++;
int CodeCoverConditionCoverageHelper_C45;
                    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((arg != converted) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[86]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[108]++;
int CodeCoverConditionCoverageHelper_C46;
                        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((invokeArgs == args) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[88]++;
                            invokeArgs = args.clone();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[109]++;

                        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[89]++;}
                        invokeArgs[i] = converted;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[110]++;

                    } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[87]++;}
                }

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[85]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[111]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((parmsLength == 0) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[90]++;
                invokeArgs = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[112]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[91]++;
                invokeArgs = new Object[parmsLength];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[113]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[114]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[19]++;


int CodeCoverConditionCoverageHelper_C48;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((i != parmsLength) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[19]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[20]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[21]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[115]++;
                    Object arg = (i < argsLength)
                                 ? args[i]
                                 : Undefined.instance;
                    invokeArgs[i] = convertArg(cx, scope, arg, typeTags[i]);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[116]++;
                }
            }
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[117]++;
int CodeCoverConditionCoverageHelper_C49;

            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((member.isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[92]++;
                result = member.invoke(thisObj, invokeArgs);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[118]++;
                checkMethodResult = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[119]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[93]++;
                result = member.newInstance(invokeArgs);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[120]++;
            }

        }
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[121]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((checkMethodResult) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[94]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[122]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((hasVoidReturn) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[96]++;
                result = Undefined.instance;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[123]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[97]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[124]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((returnTypeTag == JAVA_UNSUPPORTED_TYPE) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[98]++;
                result = cx.getWrapFactory().wrap(cx, scope, result, null);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[125]++;

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[99]++;}
}

            // XXX: the code assumes that if returnTypeTag == JAVA_OBJECT_TYPE
            // then the Java method did a proper job of converting the
            // result to JS primitive or Scriptable to avoid
            // potentially costly Context.javaToJS call.
        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[95]++;}

        return result;
    }

    /**
     * Return new {@link Scriptable} instance using the default
     * constructor for the class of the underlying Java method.
     * Return null to indicate that the call method should be used to create
     * new objects.
     */
    @Override
    public Scriptable createObject(Context cx, Scriptable scope) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[126]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((member.isCtor()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((parmsLength == VARARGS_CTOR) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[100]++;
            return null;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[101]++;}
        Scriptable result;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[127]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            result = (Scriptable) member.getDeclaringClass().newInstance();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[128]++;
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[103]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[102]++;
}
  }

        result.setPrototype(getClassPrototype());
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[129]++;
        result.setParentScope(getParentScope());
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[130]++;
        return result;
    }

    boolean isVarArgsMethod() {
        return parmsLength == VARARGS_METHOD;
    }

    boolean isVarArgsConstructor() {
        return parmsLength == VARARGS_CTOR;
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[131]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[132]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((parmsLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[104]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[133]++;
            Class<?>[] types = member.argTypes;
            typeTags = new byte[parmsLength];
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[134]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[135]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[22]++;


int CodeCoverConditionCoverageHelper_C55;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((i != parmsLength) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[22]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[23]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.loops[24]++;
}
                typeTags[i] = (byte)getTypeTag(types[i]);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[136]++;
            }

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[105]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[137]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((member.isMethod()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[106]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[138]++;
            Method method = member.method();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[139]++;
            Class<?> returnType = method.getReturnType();
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[140]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((returnType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[108]++;
                hasVoidReturn = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[141]++;

            } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[109]++;
                returnTypeTag = getTypeTag(returnType);
CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[142]++;
            }

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.branches[107]++;}
    }

    private static final short VARARGS_METHOD = -1;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[143]++;
  }
    private static final short VARARGS_CTOR =   -2;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[144]++;
  }

    private static boolean sawSecurityException;

    public static final int JAVA_UNSUPPORTED_TYPE = 0;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[145]++;
  }
    public static final int JAVA_STRING_TYPE      = 1;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[146]++;
  }
    public static final int JAVA_INT_TYPE         = 2;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[147]++;
  }
    public static final int JAVA_BOOLEAN_TYPE     = 3;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[148]++;
  }
    public static final int JAVA_DOUBLE_TYPE      = 4;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[149]++;
  }
    public static final int JAVA_SCRIPTABLE_TYPE  = 5;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[150]++;
  }
    public static final int JAVA_OBJECT_TYPE      = 6;
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx.statements[151]++;
  }

    MemberBox member;
    private String functionName;
    private transient byte[] typeTags;
    private int parmsLength;
    private transient boolean hasVoidReturn;
    private transient int returnTypeTag;
    private boolean isStatic;
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx ());
  }
    public static long[] statements = new long[152];
    public static long[] branches = new long[110];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[58];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-FunctionObject.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,1,3,3,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 57; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevplhjou02dxwig0hsjj69efxlkykwx () {
    super("org.mozilla.javascript.RHINO-SRC-FunctionObject.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 151; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 109; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-FunctionObject.java");
      for (int i = 1; i <= 151; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 109; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 57; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

