/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.util.Iterator;

public abstract class VMBridge
{
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.ping();
  }


    static final VMBridge instance = makeInstance();
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[1]++;
  }

    private static VMBridge makeInstance()
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[2]++;
        String[] classNames = {
            "org.mozilla.javascript.VMBridge_custom",
            "org.mozilla.javascript.jdk15.VMBridge_jdk15",
            "org.mozilla.javascript.jdk13.VMBridge_jdk13",
            "org.mozilla.javascript.jdk11.VMBridge_jdk11",
        };
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i != classNames.length) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.loops[1]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.loops[2]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.loops[3]++;
}
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[4]++;
            String className = classNames[i];
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[5]++;
            Class<?> cl = Kit.classOrNull(className);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cl != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[1]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[7]++;
                VMBridge bridge = (VMBridge)Kit.newInstanceOrNull(cl);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((bridge != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[3]++;
                    return bridge;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[4]++;}

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[2]++;}
        }
        throw new IllegalStateException("Failed to create VMBridge instance");
    }

    /**
     * Return a helper object to optimize {@link Context} access.
     * <p>
     * The runtime will pass the resulting helper object to the subsequent
     * calls to {@link #getContext(Object contextHelper)} and
     * {@link #setContext(Object contextHelper, Context cx)} methods.
     * In this way the implementation can use the helper to cache
     * information about current thread to make {@link Context} access faster.
     */
    protected abstract Object getThreadContextHelper();

    /**
     * Get {@link Context} instance associated with the current thread
     * or null if none.
     *
     * @param contextHelper The result of {@link #getThreadContextHelper()}
     *                      called from the current thread.
     */
    protected abstract Context getContext(Object contextHelper);

    /**
     * Associate {@link Context} instance with the current thread or remove
     * the current association if <tt>cx</tt> is null.
     *
     * @param contextHelper The result of {@link #getThreadContextHelper()}
     *                      called from the current thread.
     */
    protected abstract void setContext(Object contextHelper, Context cx);

    /**
     * Return the ClassLoader instance associated with the current thread.
     */
    protected abstract ClassLoader getCurrentThreadClassLoader();

    /**
     * In many JVMSs, public methods in private
     * classes are not accessible by default (Sun Bug #4071593).
     * VMBridge instance should try to workaround that via, for example,
     * calling method.setAccessible(true) when it is available.
     * The implementation is responsible to catch all possible exceptions
     * like SecurityException if the workaround is not available.
     *
     * @return true if it was possible to make method accessible
     *         or false otherwise.
     */
    protected abstract boolean tryToMakeAccessible(Object accessibleObject);

    /**
     * Create helper object to create later proxies implementing the specified
     * interfaces later. Under JDK 1.3 the implementation can look like:
     * <pre>
     * return java.lang.reflect.Proxy.getProxyClass(..., interfaces).
     *     getConstructor(new Class[] {
     *         java.lang.reflect.InvocationHandler.class });
     * </pre>
     *
     * @param interfaces Array with one or more interface class objects.
     */
    protected Object getInterfaceProxyHelper(ContextFactory cf,
                                             Class<?>[] interfaces)
    {
        throw Context.reportRuntimeError(
            "VMBridge.getInterfaceProxyHelper is not supported");
    }

    /**
     * Create proxy object for {@link InterfaceAdapter}. The proxy should call
     * {@link InterfaceAdapter#invoke(ContextFactory cf,
     *                                Object target,
     *                                Scriptable topScope,
     *                                Method method,
     *                                Object[] args)}
     * as implementation of interface methods associated with
     * <tt>proxyHelper</tt>.
     *
     * @param proxyHelper The result of the previous call to
     *        {@link #getInterfaceProxyHelper(ContextFactory, Class[])}.
     */
    protected Object newInterfaceProxy(Object proxyHelper,
                                       ContextFactory cf,
                                       InterfaceAdapter adapter,
                                       Object target,
                                       Scriptable topScope)
    {
        throw Context.reportRuntimeError(
            "VMBridge.newInterfaceProxy is not supported");
    }

    /**
     * Returns whether or not a given member (method or constructor)
     * has variable arguments.
     * Variable argument methods have only been supported in Java since
     * JDK 1.5.
     */
    protected abstract boolean isVarArgs(Member member);

    /**
     * If "obj" is a java.util.Iterator or a java.lang.Iterable, return a
     * wrapping as a JavaScript Iterator. Otherwise, return null.
     * This method is in VMBridge since Iterable is a JDK 1.5 addition.
     */
    public Iterator<?> getJavaIterator(Context cx, Scriptable scope, Object obj) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[5]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[10]++;
            Object unwrapped = ((Wrapper) obj).unwrap();
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[11]++;
            Iterator<?> iterator = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((unwrapped instanceof Iterator) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[7]++;
                iterator = (Iterator<?>) unwrapped;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.statements[13]++;
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[8]++;}
            return iterator;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t.branches[6]++;}
        return null;
    }
}

class CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-VMBridge.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$2o08iygcpwmc6w26cxd7uceugzroqy3feq1t () {
    super("org.mozilla.javascript.RHINO-SRC-VMBridge.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-VMBridge.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

