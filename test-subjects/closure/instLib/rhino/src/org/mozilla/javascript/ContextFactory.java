/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Factory class that Rhino runtime uses to create new {@link Context}
 * instances.  A <code>ContextFactory</code> can also notify listeners
 * about context creation and release.
 * <p>
 * When the Rhino runtime needs to create new {@link Context} instance during
 * execution of {@link Context#enter()} or {@link Context}, it will call
 * {@link #makeContext()} of the current global ContextFactory.
 * See {@link #getGlobal()} and {@link #initGlobal(ContextFactory)}.
 * <p>
 * It is also possible to use explicit ContextFactory instances for Context
 * creation. This is useful to have a set of independent Rhino runtime
 * instances under single JVM. See {@link #call(ContextAction)}.
 * <p>
 * The following example demonstrates Context customization to terminate
 * scripts running more then 10 seconds and to provide better compatibility
 * with JavaScript code using MSIE-specific features.
 * <pre>
 * import org.mozilla.javascript.*;
 *
 * class MyFactory extends ContextFactory
 * {
 *
 *     // Custom {@link Context} to store execution time.
 *     private static class MyContext extends Context
 *     {
 *         long startTime;
 *     }
 *
 *     static {
 *         // Initialize GlobalFactory with custom factory
 *         ContextFactory.initGlobal(new MyFactory());
 *     }
 *
 *     // Override {@link #makeContext()}
 *     protected Context makeContext()
 *     {
 *         MyContext cx = new MyContext();
 *         // Make Rhino runtime to call observeInstructionCount
 *         // each 10000 bytecode instructions
 *         cx.setInstructionObserverThreshold(10000);
 *         return cx;
 *     }
 *
 *     // Override {@link #hasFeature(Context, int)}
 *     public boolean hasFeature(Context cx, int featureIndex)
 *     {
 *         // Turn on maximum compatibility with MSIE scripts
 *         switch (featureIndex) {
 *             case {@link Context#FEATURE_NON_ECMA_GET_YEAR}:
 *                 return true;
 *
 *             case {@link Context#FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME}:
 *                 return true;
 *
 *             case {@link Context#FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER}:
 *                 return true;
 *
 *             case {@link Context#FEATURE_PARENT_PROTO_PROPERTIES}:
 *                 return false;
 *         }
 *         return super.hasFeature(cx, featureIndex);
 *     }
 *
 *     // Override {@link #observeInstructionCount(Context, int)}
 *     protected void observeInstructionCount(Context cx, int instructionCount)
 *     {
 *         MyContext mcx = (MyContext)cx;
 *         long currentTime = System.currentTimeMillis();
 *         if (currentTime - mcx.startTime > 10*1000) {
 *             // More then 10 seconds from Context creation time:
 *             // it is time to stop the script.
 *             // Throw Error instance to ensure that script will never
 *             // get control back through catch or finally.
 *             throw new Error();
 *         }
 *     }
 *
 *     // Override {@link #doTopCall(Callable,
                               Context, Scriptable,
                               Scriptable, Object[])}
 *     protected Object doTopCall(Callable callable,
 *                                Context cx, Scriptable scope,
 *                                Scriptable thisObj, Object[] args)
 *     {
 *         MyContext mcx = (MyContext)cx;
 *         mcx.startTime = System.currentTimeMillis();
 *
 *         return super.doTopCall(callable, cx, scope, thisObj, args);
 *     }
 *
 * }
 *
 * </pre>
 */

public class ContextFactory
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.ping();
  }

    private static volatile boolean hasCustomGlobal;
    private static ContextFactory global = new ContextFactory();
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[1]++;
  }

    private volatile boolean sealed;

    private final Object listenersLock = new Object();
  {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[2]++;
  }
    private volatile Object listeners;
    private boolean disabledListening;
    private ClassLoader applicationClassLoader;

    /**
     * Listener of {@link Context} creation and release events.
     */
    public interface Listener
    {
        /**
         * Notify about newly created {@link Context} object.
         */
        public void contextCreated(Context cx);

        /**
         * Notify that the specified {@link Context} instance is no longer
         * associated with the current thread.
         */
        public void contextReleased(Context cx);
    }

    /**
     * Get global ContextFactory.
     *
     * @see #hasExplicitGlobal()
     * @see #initGlobal(ContextFactory)
     */
    public static ContextFactory getGlobal()
    {
        return global;
    }

    /**
     * Check if global factory was set.
     * Return true to indicate that {@link #initGlobal(ContextFactory)} was
     * already called and false to indicate that the global factory was not
     * explicitly set.
     *
     * @see #getGlobal()
     * @see #initGlobal(ContextFactory)
     */
    public static boolean hasExplicitGlobal()
    {
        return hasCustomGlobal;
    }

    /**
     * Set global ContextFactory.
     * The method can only be called once.
     *
     * @see #getGlobal()
     * @see #hasExplicitGlobal()
     */
    public synchronized static void initGlobal(ContextFactory factory)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((factory == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[1]++;
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[2]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((hasCustomGlobal) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[3]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[4]++;}
        hasCustomGlobal = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[5]++;
        global = factory;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[6]++;
    }

    public interface GlobalSetter {
        public void setContextFactoryGlobal(ContextFactory factory);
        public ContextFactory getContextFactoryGlobal();
    }

    public synchronized static GlobalSetter getGlobalSetter() {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((hasCustomGlobal) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[5]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[6]++;}
        hasCustomGlobal = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[8]++;
        class GlobalSetterImpl implements GlobalSetter {
            public void setContextFactoryGlobal(ContextFactory factory) {
                global = factory == null ? new ContextFactory() : factory;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[9]++;
            }
            public ContextFactory getContextFactoryGlobal() {
                return global;
            }
        }
        return new GlobalSetterImpl();
    }

    /**
     * Create new {@link Context} instance to be associated with the current
     * thread.
     * This is a callback method used by Rhino to create {@link Context}
     * instance when it is necessary to associate one with the current
     * execution thread. <tt>makeContext()</tt> is allowed to call
     * {@link Context#seal(Object)} on the result to prevent
     * {@link Context} changes by hostile scripts or applets.
     */
    protected Context makeContext()
    {
        return new Context(this);
    }

    /**
     * Implementation of {@link Context#hasFeature(int featureIndex)}.
     * This can be used to customize {@link Context} without introducing
     * additional subclasses.
     */
    protected boolean hasFeature(Context cx, int featureIndex)
    {
        int version;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[10]++;
        switch (featureIndex) {
          case Context.FEATURE_NON_ECMA_GET_YEAR:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[7]++;
           /*
            * During the great date rewrite of 1.3, we tried to track the
            * evolving ECMA standard, which then had a definition of
            * getYear which always subtracted 1900.  Which we
            * implemented, not realizing that it was incompatible with
            * the old behavior...  now, rather than thrash the behavior
            * yet again, we've decided to leave it with the - 1900
            * behavior and point people to the getFullYear method.  But
            * we try to protect existing scripts that have specified a
            * version...
            */
            version = cx.getLanguageVersion();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[11]++;
            return (version == Context.VERSION_1_0
                    || version == Context.VERSION_1_1
                    || version == Context.VERSION_1_2);

          case Context.FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[8]++;
            return false;

          case Context.FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[9]++;
            return true;

          case Context.FEATURE_TO_STRING_AS_SOURCE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[10]++;
            version = cx.getLanguageVersion();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[12]++;
            return version == Context.VERSION_1_2;

          case Context.FEATURE_PARENT_PROTO_PROPERTIES:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[11]++;
            return true;

          case Context.FEATURE_E4X:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[12]++;
            version = cx.getLanguageVersion();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[13]++;
            return (version == Context.VERSION_DEFAULT
                    || version >= Context.VERSION_1_6);

          case Context.FEATURE_DYNAMIC_SCOPE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[13]++;
            return false;

          case Context.FEATURE_STRICT_VARS:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[14]++;
            return false;

          case Context.FEATURE_STRICT_EVAL:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[15]++;
            return false;

          case Context.FEATURE_LOCATION_INFORMATION_IN_ERROR:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[16]++;
            return false;

          case Context.FEATURE_STRICT_MODE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[17]++;
            return false;

          case Context.FEATURE_WARNING_AS_ERROR:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[18]++;
            return false;

          case Context.FEATURE_ENHANCED_JAVA_ACCESS:
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[19]++;
            return false; default : CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[20]++;
        }
        // It is a bug to call the method with unknown featureIndex
        throw new IllegalArgumentException(String.valueOf(featureIndex));
    }

    private boolean isDom3Present() {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[14]++;
        Class<?> nodeClass = Kit.classOrNull("org.w3c.dom.Node");
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((nodeClass == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[21]++; return false;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[22]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[16]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        // Check to see whether DOM3 is present; use a new method defined in
        // DOM3 that is vital to our implementation
        try {
CodeCoverTryBranchHelper_Try1 = true;
            nodeClass.getMethod("getUserData", new Class<?>[] { String.class });
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[17]++;
            return true;
        } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[24]++;
            return false;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[23]++;
}
  }
    }

    /**
     * Provides a default
     * {@link org.mozilla.javascript.xml.XMLLib.Factory XMLLib.Factory}
     * to be used by the <code>Context</code> instances produced by this
     * factory. See {@link Context#getE4xImplementationFactory} for details.
     *
     * May return null, in which case E4X functionality is not supported in
     * Rhino.
     *
     * The default implementation now prefers the DOM3 E4X implementation.
     */
    protected org.mozilla.javascript.xml.XMLLib.Factory
        getE4xImplementationFactory()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        // Must provide default implementation, rather than abstract method,
        // so that past implementors of ContextFactory do not fail at runtime
        // upon invocation of this method.
        // Note that the default implementation returns null if we
        // neither have XMLBeans nor a DOM3 implementation present.

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isDom3Present()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[25]++;
            return org.mozilla.javascript.xml.XMLLib.Factory.create(
                "org.mozilla.javascript.xmlimpl.XMLLibImpl"
            );

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[26]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[19]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Kit.classOrNull("org.apache.xmlbeans.XmlCursor") != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[27]++;
            return org.mozilla.javascript.xml.XMLLib.Factory.create(
                "org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl"
            );

        } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[28]++;
            return null;
        }
}
    }


    /**
     * Create class loader for generated classes.
     * This method creates an instance of the default implementation
     * of {@link GeneratedClassLoader}. Rhino uses this interface to load
     * generated JVM classes when no {@link SecurityController}
     * is installed.
     * Application can override the method to provide custom class loading.
     */
    protected GeneratedClassLoader createClassLoader(final ClassLoader parent)
    {
        return AccessController.doPrivileged(new PrivilegedAction<DefiningClassLoader>() {
            public DefiningClassLoader run(){
                return new DefiningClassLoader(parent);
            }
        });
    }

    /**
     * Get ClassLoader to use when searching for Java classes.
     * Unless it was explicitly initialized with
     * {@link #initApplicationClassLoader(ClassLoader)} the method returns
     * null to indicate that Thread.getContextClassLoader() should be used.
     */
    public final ClassLoader getApplicationClassLoader()
    {
        return applicationClassLoader;
    }

    /**
     * Set explicit class loader to use when searching for Java classes.
     *
     * @see #getApplicationClassLoader()
     */
    public final void initApplicationClassLoader(ClassLoader loader)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((loader == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[29]++;
            throw new IllegalArgumentException("loader is null");
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[30]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Kit.testIfCanLoadRhinoClasses(loader)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[31]++;
            throw new IllegalArgumentException(
                "Loader can not resolve Rhino classes");
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[32]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.applicationClassLoader != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[33]++;
            throw new IllegalStateException(
                "applicationClassLoader can only be set once");
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[34]++;}
        checkNotSealed();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[23]++;

        this.applicationClassLoader = loader;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[24]++;
    }

    /**
     * Execute top call to script or function.
     * When the runtime is about to execute a script or function that will
     * create the first stack frame with scriptable code, it calls this method
     * to perform the real call. In this way execution of any script
     * happens inside this function.
     */
    protected Object doTopCall(Callable callable,
                               Context cx, Scriptable scope,
                               Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[25]++;
        Object result = callable.call(cx, scope, thisObj, args);
        return result instanceof ConsString ? result.toString() : result;
    }

    /**
     * Implementation of
     * {@link Context#observeInstructionCount(int instructionCount)}.
     * This can be used to customize {@link Context} without introducing
     * additional subclasses.
     */
    protected void observeInstructionCount(Context cx, int instructionCount) {
    }

    protected void onContextCreated(Context cx)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[26]++;
        Object listeners = this.listeners;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[27]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[1]++;


        for (int i = 0; ; ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[1]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[2]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[3]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[28]++;
            Listener l = (Listener)Kit.getListener(listeners, i);
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((l == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[35]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[30]++;
                break;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[36]++;}
            l.contextCreated(cx);
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[31]++;
        }
    }

    protected void onContextReleased(Context cx)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[32]++;
        Object listeners = this.listeners;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[4]++;


        for (int i = 0; ; ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[4]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[5]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.loops[6]++;
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[34]++;
            Listener l = (Listener)Kit.getListener(listeners, i);
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[35]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((l == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[37]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[36]++;
                break;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[38]++;}
            l.contextReleased(cx);
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[37]++;
        }
    }

    public final void addListener(Listener listener)
    {
        checkNotSealed();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[38]++;
        synchronized (listenersLock) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((disabledListening) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[39]++;
                throw new IllegalStateException();

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[40]++;}
            listeners = Kit.addListener(listeners, listener);
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[40]++;
        }
    }

    public final void removeListener(Listener listener)
    {
        checkNotSealed();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[41]++;
        synchronized (listenersLock) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((disabledListening) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[41]++;
                throw new IllegalStateException();

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[42]++;}
            listeners = Kit.removeListener(listeners, listener);
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[43]++;
        }
    }

    /**
     * The method is used only to implement
     * Context.disableStaticContextListening()
     */
    final void disableContextListening()
    {
        checkNotSealed();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[44]++;
        synchronized (listenersLock) {
            disabledListening = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[45]++;
            listeners = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[46]++;
        }
    }

    /**
     * Checks if this is a sealed ContextFactory.
     * @see #seal()
     */
    public final boolean isSealed()
    {
        return sealed;
    }

    /**
     * Seal this ContextFactory so any attempt to modify it like to add or
     * remove its listeners will throw an exception.
     * @see #isSealed()
     */
    public final void seal()
    {
        checkNotSealed();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[47]++;
        sealed = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[48]++;
    }

    protected final void checkNotSealed()
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[49]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[43]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.branches[44]++;}
    }

    /**
     * Call {@link ContextAction#run(Context cx)}
     * using the {@link Context} instance associated with the current thread.
     * If no Context is associated with the thread, then
     * {@link #makeContext()} will be called to construct
     * new Context instance. The instance will be temporary associated
     * with the thread during call to {@link ContextAction#run(Context)}.
     *
     * @see ContextFactory#call(ContextAction)
     * @see Context#call(ContextFactory factory, Callable callable,
     *                   Scriptable scope, Scriptable thisObj,
     *                   Object[] args)
     */
    public final Object call(ContextAction action)
    {
        return Context.call(this, action);
    }

    /**
     * Get a context associated with the current thread, creating one if need
     * be. The Context stores the execution state of the JavaScript engine, so
     * it is required that the context be entered before execution may begin.
     * Once a thread has entered a Context, then getCurrentContext() may be
     * called to find the context that is associated with the current thread.
     * <p>
     * Calling <code>enterContext()</code> will return either the Context
     * currently associated with the thread, or will create a new context and
     * associate it with the current thread. Each call to
     * <code>enterContext()</code> must have a matching call to
     * {@link Context#exit()}.
     * <pre>
     *      Context cx = contextFactory.enterContext();
     *      try {
     *          ...
     *          cx.evaluateString(...);
     *      } finally {
     *          Context.exit();
     *      }
     * </pre>
     * Instead of using <tt>enterContext()</tt>, <tt>exit()</tt> pair consider
     * using {@link #call(ContextAction)} which guarantees proper association
     * of Context instances with the current thread.
     * With this method the above example becomes:
     * <pre>
     *      ContextFactory.call(new ContextAction() {
     *          public Object run(Context cx) {
     *              ...
     *              cx.evaluateString(...);
     *              return null;
     *          }
     *      });
     * </pre>
     * @return a Context associated with the current thread
     * @see Context#getCurrentContext()
     * @see Context#exit()
     * @see #call(ContextAction)
     */
    public Context enterContext()
    {
        return enterContext(null);
    }

    /**
     * @deprecated use {@link #enterContext()} instead
     * @return a Context associated with the current thread
     */
    public final Context enter()
    {
        return enterContext(null);
    }

    /**
     * @deprecated Use {@link Context#exit()} instead.
     */
    public final void exit()
    {
        Context.exit();
CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h.statements[50]++;
    }

    /**
     * Get a Context associated with the current thread, using the given
     * Context if need be.
     * <p>
     * The same as <code>enterContext()</code> except that <code>cx</code>
     * is associated with the current thread and returned if the current thread
     * has no associated context and <code>cx</code> is not associated with any
     * other thread.
     * @param cx a Context to associate with the thread if possible
     * @return a Context associated with the current thread
     * @see #enterContext()
     * @see #call(ContextAction)
     * @throws IllegalStateException if <code>cx</code> is already associated
     * with a different thread
     */
    public final Context enterContext(Context cx)
    {
        return Context.enter(cx, this);
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h ());
  }
    public static long[] statements = new long[51];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ContextFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevnt4e34e40jm5xp066h7vywh9rx84h () {
    super("org.mozilla.javascript.RHINO-SRC-ContextFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 50; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ContextFactory.java");
      for (int i = 1; i <= 50; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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
