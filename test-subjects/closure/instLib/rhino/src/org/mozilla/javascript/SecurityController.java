/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * This class describes the support needed to implement security.
 * <p>
 * Three main pieces of functionality are required to implement
 * security for JavaScript. First, it must be possible to define
 * classes with an associated security domain. (This security
 * domain may be any object incorporating notion of access
 * restrictions that has meaning to an embedding; for a client-side
 * JavaScript embedding this would typically be
 * java.security.ProtectionDomain or similar object depending on an
 * origin URL and/or a digital certificate.)
 * Next it must be possible to get a security domain object that
 * allows a particular action only if all security domains
 * associated with code on the current Java stack allows it. And
 * finally, it must be possible to execute script code with
 * associated security domain injected into Java stack.
 * <p>
 * These three pieces of functionality are encapsulated in the
 * SecurityController class.
 *
 * @see org.mozilla.javascript.Context#setSecurityController(SecurityController)
 * @see java.lang.ClassLoader
 * @since 1.5 Release 4
 */
public abstract class SecurityController
{
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.ping();
  }

    private static SecurityController global;

// The method must NOT be public or protected
    static SecurityController global()
    {
        return global;
    }

    /**
     * Check if global {@link SecurityController} was already installed.
     * @see #initGlobal(SecurityController controller)
     */
    public static boolean hasGlobal()
    {
        return global != null;
    }

    /**
     * Initialize global controller that will be used for all
     * security-related operations. The global controller takes precedence
     * over already installed {@link Context}-specific controllers and cause
     * any subsequent call to
     * {@link Context#setSecurityController(SecurityController)}
     * to throw an exception.
     * <p>
     * The method can only be called once.
     *
     * @see #hasGlobal()
     */
    public static void initGlobal(SecurityController controller)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((controller == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[2]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((global != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[3]++;
            throw new SecurityException("Cannot overwrite already installed global SecurityController");

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[4]++;}
        global = controller;
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[3]++;
    }

    /**
     * Get class loader-like object that can be used
     * to define classes with the given security context.
     * @param parentLoader parent class loader to delegate search for classes
     *        not defined by the class loader itself
     * @param securityDomain some object specifying the security
     *        context of the code that is defined by the returned class loader.
     */
    public abstract GeneratedClassLoader createClassLoader(
        ClassLoader parentLoader, Object securityDomain);

    /**
     * Create {@link GeneratedClassLoader} with restrictions imposed by
     * staticDomain and all current stack frames.
     * The method uses the SecurityController instance associated with the
     * current {@link Context} to construct proper dynamic domain and create
     * corresponding class loader.
     * <par>
     * If no SecurityController is associated with the current {@link Context} ,
     * the method calls {@link Context#createClassLoader(ClassLoader parent)}.
     *
     * @param parent parent class loader. If null,
     *        {@link Context#getApplicationClassLoader()} will be used.
     * @param staticDomain static security domain.
     */
    public static GeneratedClassLoader createLoader(
        ClassLoader parent, Object staticDomain)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[4]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[5]++;
            parent = cx.getApplicationClassLoader();
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[6]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[6]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[7]++;
        SecurityController sc = cx.getSecurityController();
        GeneratedClassLoader loader;
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((sc == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[7]++;
            loader = cx.createClassLoader(parent);
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[9]++;

        } else {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.branches[8]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[10]++;
            Object dynamicDomain = sc.getDynamicSecurityDomain(staticDomain);
            loader = sc.createClassLoader(parent, dynamicDomain);
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[11]++;
        }
        return loader;
    }

    public static Class<?> getStaticSecurityDomainClass() {
CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht.statements[12]++;
        SecurityController sc = Context.getContext().getSecurityController();
        return sc == null ? null : sc.getStaticSecurityDomainClassInternal();
    }

    public Class<?> getStaticSecurityDomainClassInternal()
    {
        return null;
    }

    /**
     * Get dynamic security domain that allows an action only if it is allowed
     * by the current Java stack and <i>securityDomain</i>. If
     * <i>securityDomain</i> is null, return domain representing permissions
     * allowed by the current stack.
     */
    public abstract Object getDynamicSecurityDomain(Object securityDomain);

    /**
     * Call {@link
     * Callable#call(Context cx, Scriptable scope, Scriptable thisObj,
     *               Object[] args)}
     * of <i>callable</i> under restricted security domain where an action is
     * allowed only if it is allowed according to the Java stack on the
     * moment of the <i>execWithDomain</i> call and <i>securityDomain</i>.
     * Any call to {@link #getDynamicSecurityDomain(Object)} during
     * execution of <tt>callable.call(cx, scope, thisObj, args)</tt>
     * should return a domain incorporate restrictions imposed by
     * <i>securityDomain</i> and Java stack on the moment of callWithDomain
     * invocation.
     * <p>
     * The method should always be overridden, it is not declared abstract
     * for compatibility reasons.
     */
    public Object callWithDomain(Object securityDomain, Context cx,
                                 final Callable callable, Scriptable scope,
                                 final Scriptable thisObj, final Object[] args)
    {
        return execWithDomain(cx, scope, new Script()
        {
            public Object exec(Context cx, Scriptable scope)
            {
                return callable.call(cx, scope, thisObj, args);
            }

        }, securityDomain);
    }

    /**
     * @deprecated The application should not override this method and instead
     * override
     * {@link #callWithDomain(Object securityDomain, Context cx, Callable callable, Scriptable scope, Scriptable thisObj, Object[] args)}.
     */
    public Object execWithDomain(Context cx, Scriptable scope,
                                 Script script, Object securityDomain)
    {
        throw new IllegalStateException("callWithDomain should be overridden");
    }


}

class CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-SecurityController.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$el0607z4lg6oa7b18b4lpz4ifwrslzsgvzttjxg8psw231vq6ht () {
    super("org.mozilla.javascript.RHINO-SRC-SecurityController.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-SecurityController.java");
      for (int i = 1; i <= 12; i++) {
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
    for (int i = 1; i <= 4; i++) {
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

