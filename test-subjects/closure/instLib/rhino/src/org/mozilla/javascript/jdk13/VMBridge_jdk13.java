/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.jdk13;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.lang.reflect.Proxy;

import org.mozilla.javascript.*;

public class VMBridge_jdk13 extends VMBridge
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.ping();
  }

    private ThreadLocal<Object[]> contextLocal = new ThreadLocal<Object[]>();
  {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[1]++;
  }

    @Override
    protected Object getThreadContextHelper()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[2]++;
        // To make subsequent batch calls to getContext/setContext faster
        // associate permanently one element array with contextLocal
        // so getContext/setContext would need just to read/write the first
        // array element.
        // Note that it is necessary to use Object[], not Context[] to allow
        // garbage collection of Rhino classes. For details see comments
        // by Attila Szegedi in
        // https://bugzilla.mozilla.org/show_bug.cgi?id=281067#c5

        Object[] storage = contextLocal.get();
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((storage == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[1]++;
            storage = new Object[1];
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[4]++;
            contextLocal.set(storage);
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[5]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[2]++;}
        return storage;
    }

    @Override
    protected Context getContext(Object contextHelper)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[6]++;
        Object[] storage = (Object[])contextHelper;
        return (Context)storage[0];
    }

    @Override
    protected void setContext(Object contextHelper, Context cx)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[7]++;
        Object[] storage = (Object[])contextHelper;
        storage[0] = cx;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[8]++;
    }

    @Override
    protected ClassLoader getCurrentThreadClassLoader()
    {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    protected boolean tryToMakeAccessible(Object accessibleObject)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((accessibleObject instanceof AccessibleObject) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[4]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[10]++;
        AccessibleObject accessible = (AccessibleObject)accessibleObject;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((accessible.isAccessible()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[6]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[12]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            accessible.setAccessible(true);
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[13]++;
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[8]++; } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[7]++;
}
  }

        return accessible.isAccessible();
    }

    @Override
    protected Object getInterfaceProxyHelper(ContextFactory cf,
                                             Class<?>[] interfaces)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[14]++;
        // XXX: How to handle interfaces array withclasses from different
        // class loaders? Using cf.getApplicationClassLoader() ?
        ClassLoader loader = interfaces[0].getClassLoader();
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[15]++;
        Class<?> cl = Proxy.getProxyClass(loader, interfaces);
        Constructor<?> c;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[16]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            c = cl.getConstructor(new Class[] { InvocationHandler.class });
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[17]++;
        } catch (NoSuchMethodException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[10]++;
            // Should not happen
            throw Kit.initCause(new IllegalStateException(), ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[9]++;
}
  }
        return c;
    }

    @Override
    protected Object newInterfaceProxy(Object proxyHelper,
                                       final ContextFactory cf,
                                       final InterfaceAdapter adapter,
                                       final Object target,
                                       final Scriptable topScope)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[18]++;
        Constructor<?> c = (Constructor<?>)proxyHelper;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[19]++;

        InvocationHandler handler = new InvocationHandler() {
                public Object invoke(Object proxy,
                                     Method method,
                                     Object[] args)
                {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
                    // In addition to methods declared in the interface, proxies
                    // also route some java.lang.Object methods through the
                    // invocation handler.
                    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((method.getDeclaringClass() == Object.class) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[11]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[21]++;
                        String methodName = method.getName();
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
                        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((methodName.equals("equals")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[13]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[23]++;
                            Object other = args[0];
                            // Note: we could compare a proxy and its wrapped function
                            // as equal here but that would break symmetry of equal().
                            // The reason == suffices here is that proxies are cached
                            // in ScriptableObject (see NativeJavaObject.coerceType())
                            return Boolean.valueOf(proxy == other);

                        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[14]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
                        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((methodName.equals("hashCode")) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[15]++;
                            return Integer.valueOf(target.hashCode());

                        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[16]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
                        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((methodName.equals("toString")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[17]++;
                            return "Proxy[" + target.toString() + "]";

                        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[18]++;}

                    } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[12]++;}
                    return adapter.invoke(cf, target, topScope, proxy, method, args);
                }
            };
        Object proxy;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[26]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            proxy = c.newInstance(handler);
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.statements[27]++;
        } catch (InvocationTargetException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[20]++;
            throw Context.throwAsScriptRuntimeEx(ex);
        } catch (IllegalAccessException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[21]++;
            // Should not happen
            throw Kit.initCause(new IllegalStateException(), ex);
        } catch (InstantiationException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[22]++;
            // Should not happen
            throw Kit.initCause(new IllegalStateException(), ex);
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox.branches[19]++;
}
  }
        return proxy;
    }

    @Override
    protected boolean isVarArgs(Member member) {
      return false;
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.jdk13.RHINO-SRC-VMBridge_jdk13.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk2xp6zm6ox () {
    super("org.mozilla.javascript.jdk13.RHINO-SRC-VMBridge_jdk13.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.jdk13.RHINO-SRC-VMBridge_jdk13.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

