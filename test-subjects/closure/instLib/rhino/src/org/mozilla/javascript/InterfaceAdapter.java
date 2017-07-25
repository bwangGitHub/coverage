/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.Method;

/**
 * Adapter to use JS function as implementation of Java interfaces with
 * single method or multiple methods with the same signature.
 */
public class InterfaceAdapter
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.ping();
  }

    private final Object proxyHelper;

    /**
     * Make glue object implementing interface cl that will
     * call the supplied JS function when called.
     * Only interfaces were all methods have the same signature is supported.
     *
     * @return The glue object or null if <tt>cl</tt> is not interface or
     *         has methods with different signatures.
     */
    static Object create(Context cx, Class<?> cl, ScriptableObject object)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cl.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[2]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[2]++;

        Scriptable topScope = ScriptRuntime.getTopCallScope(cx);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[3]++;
        ClassCache cache = ClassCache.get(topScope);
        InterfaceAdapter adapter;
        adapter = (InterfaceAdapter)cache.getInterfaceAdapter(cl);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[4]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[5]++;
        ContextFactory cf = cx.getFactory();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((adapter == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[3]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[7]++;
            Method[] methods = cl.getMethods();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((object instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[5]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[9]++;
                // Check if interface can be implemented by a single function.
                // We allow this if the interface has only one method or multiple 
                // methods with the same name (in which case they'd result in 
                // the same function to be invoked anyway).
                int length = methods.length;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((length == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[7]++;
                    throw Context.reportRuntimeError1(
                        "msg.no.empty.interface.conversion", cl.getName());

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[8]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((length > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[9]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[12]++;
                    String methodName = methods[0].getName();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
                    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[1]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[2]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[3]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
                        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((methodName.equals(methods[i].getName())) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[11]++;
                            throw Context.reportRuntimeError1(
                                    "msg.no.function.interface.conversion",
                                    cl.getName());

                        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[12]++;}
                    }

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[10]++;}

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[6]++;}
            adapter = new InterfaceAdapter(cf, cl);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[15]++;
            cache.cacheInterfaceAdapter(cl, adapter);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[16]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[4]++;}
        return VMBridge.instance.newInterfaceProxy(
            adapter.proxyHelper, cf, adapter, object, topScope);
    }

    private InterfaceAdapter(ContextFactory cf, Class<?> cl)
    {
        this.proxyHelper
            = VMBridge.instance.getInterfaceProxyHelper(
                cf, new Class[] { cl });
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[17]++;
    }

    public Object invoke(ContextFactory cf,
                         final Object target,
                         final Scriptable topScope,
                         final Object thisObject,
                         final Method method,
                         final Object[] args)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[18]++;
        ContextAction action = new ContextAction() {
                public Object run(Context cx)
                {
                    return invokeImpl(cx, target, topScope, thisObject, method, args);
                }
            };
        return cf.call(action);
    }

    Object invokeImpl(Context cx,
                      Object target,
                      Scriptable topScope,
                      Object thisObject,
                      Method method,
                      Object[] args)
    {
        Callable function;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((target instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[13]++;
            function = (Callable)target;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[20]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[14]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[21]++;
            Scriptable s = (Scriptable)target;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[22]++;
            String methodName = method.getName();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[23]++;
            Object value = ScriptableObject.getProperty(s, methodName);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value == ScriptableObject.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[15]++;
                // We really should throw an error here, but for the sake of
                // compatibility with JavaAdapter we silently ignore undefined
                // methods.
                Context.reportWarning(ScriptRuntime.getMessage1(
                        "msg.undefined.function.interface", methodName));
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[25]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[26]++;
                Class<?> resultType = method.getReturnType();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((resultType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[17]++;
                    return null;

                } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[18]++;
                    return Context.jsToJava(null, resultType);
                }

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[16]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[19]++;
                throw Context.reportRuntimeError1(
                        "msg.not.function.interface",methodName);

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[20]++;}
            function = (Callable)value;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[29]++;
        }
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[30]++;
        WrapFactory wf = cx.getWrapFactory();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[31]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[21]++;
            args = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[32]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[22]++;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
            for (int i = 0, N = args.length;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[4]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[5]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.loops[6]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[34]++;
                Object arg = args[i];
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;
                // neutralize wrap factory java primitive wrap feature
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((arg instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((arg instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((arg instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[23]++;
                    args[i] = wf.wrap(cx, topScope, arg, null);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[36]++;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[24]++;}
            }
        }
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[37]++;
        Scriptable thisObj = wf.wrapAsJavaObject(cx, topScope, thisObject, null);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[38]++;

        Object result = function.call(cx, topScope, thisObj, args);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[39]++;
        Class<?> javaResultType = method.getReturnType();
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((javaResultType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[25]++;
            result = null;
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[41]++;

        } else {
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.branches[26]++;
            result = Context.jsToJava(result, javaResultType);
CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d.statements[42]++;
        }
        return result;
    }
}

class CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-InterfaceAdapter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$adralqrs9n89mg8no91xwewmi15sc0ypiq2e60jkrxi1vl9d () {
    super("org.mozilla.javascript.RHINO-SRC-InterfaceAdapter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-InterfaceAdapter.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

