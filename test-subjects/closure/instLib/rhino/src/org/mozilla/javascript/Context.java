/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Locale;

import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;
import org.mozilla.javascript.xml.XMLLib;

/**
 * This class represents the runtime context of an executing script.
 *
 * Before executing a script, an instance of Context must be created
 * and associated with the thread that will be executing the script.
 * The Context will be used to store information about the executing
 * of the script such as the call stack. Contexts are associated with
 * the current thread  using the {@link #call(ContextAction)}
 * or {@link #enter()} methods.<p>
 *
 * Different forms of script execution are supported. Scripts may be
 * evaluated from the source directly, or first compiled and then later
 * executed. Interactive execution is also supported.<p>
 *
 * Some aspects of script execution, such as type conversions and
 * object creation, may be accessed directly through methods of
 * Context.
 *
 * @see Scriptable
 */

public class Context
{
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.ping();
  }

    /**
     * Language versions.
     *
     * All integral values are reserved for future version numbers.
     */

    /**
     * The unknown version.
     */
    public static final int VERSION_UNKNOWN =   -1;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[1]++;
  }

    /**
     * The default version.
     */
    public static final int VERSION_DEFAULT =    0;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[2]++;
  }

    /**
     * JavaScript 1.0
     */
    public static final int VERSION_1_0 =      100;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[3]++;
  }

    /**
     * JavaScript 1.1
     */
    public static final int VERSION_1_1 =      110;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[4]++;
  }

    /**
     * JavaScript 1.2
     */
    public static final int VERSION_1_2 =      120;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[5]++;
  }

    /**
     * JavaScript 1.3
     */
    public static final int VERSION_1_3 =      130;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[6]++;
  }

    /**
     * JavaScript 1.4
     */
    public static final int VERSION_1_4 =      140;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[7]++;
  }

    /**
     * JavaScript 1.5
     */
    public static final int VERSION_1_5 =      150;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[8]++;
  }

    /**
     * JavaScript 1.6
     */
    public static final int VERSION_1_6 =      160;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[9]++;
  }

    /**
     * JavaScript 1.7
     */
    public static final int VERSION_1_7 =      170;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[10]++;
  }

    /**
     * JavaScript 1.8
     */
    public static final int VERSION_1_8 =      180;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[11]++;
  }

    /**
     * Controls behaviour of <tt>Date.prototype.getYear()</tt>.
     * If <tt>hasFeature(FEATURE_NON_ECMA_GET_YEAR)</tt> returns true,
     * Date.prototype.getYear subtructs 1900 only if 1900 <= date < 2000.
     * The default behavior of {@link #hasFeature(int)} is always to subtruct
     * 1900 as rquired by ECMAScript B.2.4.
     */
    public static final int FEATURE_NON_ECMA_GET_YEAR = 1;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[12]++;
  }

    /**
     * Control if member expression as function name extension is available.
     * If <tt>hasFeature(FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME)</tt> returns
     * true, allow <tt>function memberExpression(args) { body }</tt> to be
     * syntax sugar for <tt>memberExpression = function(args) { body }</tt>,
     * when memberExpression is not a simple identifier.
     * See ECMAScript-262, section 11.2 for definition of memberExpression.
     * By default {@link #hasFeature(int)} returns false.
     */
    public static final int FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME = 2;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[13]++;
  }

    /**
     * Control if reserved keywords are treated as identifiers.
     * If <tt>hasFeature(RESERVED_KEYWORD_AS_IDENTIFIER)</tt> returns true,
     * treat future reserved keyword (see  Ecma-262, section 7.5.3) as ordinary
     * identifiers but warn about this usage.
     *
     * By default {@link #hasFeature(int)} returns false.
     */
    public static final int FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER = 3;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[14]++;
  }

    /**
     * Control if <tt>toString()</tt> should returns the same result
     * as  <tt>toSource()</tt> when applied to objects and arrays.
     * If <tt>hasFeature(FEATURE_TO_STRING_AS_SOURCE)</tt> returns true,
     * calling <tt>toString()</tt> on JS objects gives the same result as
     * calling <tt>toSource()</tt>. That is it returns JS source with code
     * to create an object with all enumeratable fields of the original object
     * instead of printing <tt>[object <i>result of
     * {@link Scriptable#getClassName()}</i>]</tt>.
     * <p>
     * By default {@link #hasFeature(int)} returns true only if
     * the current JS version is set to {@link #VERSION_1_2}.
     */
    public static final int FEATURE_TO_STRING_AS_SOURCE = 4;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[15]++;
  }

    /**
     * Control if properties <tt>__proto__</tt> and <tt>__parent__</tt>
     * are treated specially.
     * If <tt>hasFeature(FEATURE_PARENT_PROTO_PROPERTIES)</tt> returns true,
     * treat <tt>__parent__</tt> and <tt>__proto__</tt> as special properties.
     * <p>
     * The properties allow to query and set scope and prototype chains for the
     * objects. The special meaning of the properties is available
     * only when they are used as the right hand side of the dot operator.
     * For example, while <tt>x.__proto__ = y</tt> changes the prototype
     * chain of the object <tt>x</tt> to point to <tt>y</tt>,
     * <tt>x["__proto__"] = y</tt> simply assigns a new value to the property
     * <tt>__proto__</tt> in <tt>x</tt> even when the feature is on.
     *
     * By default {@link #hasFeature(int)} returns true.
     */
    public static final int FEATURE_PARENT_PROTO_PROPERTIES = 5;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[16]++;
  }

        /**
         * @deprecated In previous releases, this name was given to
         * FEATURE_PARENT_PROTO_PROPERTIES.
         */
    public static final int FEATURE_PARENT_PROTO_PROPRTIES = 5;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[17]++;
  }

    /**
     * Control if support for E4X(ECMAScript for XML) extension is available.
     * If hasFeature(FEATURE_E4X) returns true, the XML syntax is available.
     * <p>
     * By default {@link #hasFeature(int)} returns true if
     * the current JS version is set to {@link #VERSION_DEFAULT}
     * or is at least {@link #VERSION_1_6}.
     * @since 1.6 Release 1
     */
    public static final int FEATURE_E4X = 6;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[18]++;
  }

    /**
     * Control if dynamic scope should be used for name access.
     * If hasFeature(FEATURE_DYNAMIC_SCOPE) returns true, then the name lookup
     * during name resolution will use the top scope of the script or function
     * which is at the top of JS execution stack instead of the top scope of the
     * script or function from the current stack frame if the top scope of
     * the top stack frame contains the top scope of the current stack frame
     * on its prototype chain.
     * <p>
     * This is useful to define shared scope containing functions that can
     * be called from scripts and functions using private scopes.
     * <p>
     * By default {@link #hasFeature(int)} returns false.
     * @since 1.6 Release 1
     */
    public static final int FEATURE_DYNAMIC_SCOPE = 7;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[19]++;
  }

    /**
     * Control if strict variable mode is enabled.
     * When the feature is on Rhino reports runtime errors if assignment
     * to a global variable that does not exist is executed. When the feature
     * is off such assignments create a new variable in the global scope as
     * required by ECMA 262.
     * <p>
     * By default {@link #hasFeature(int)} returns false.
     * @since 1.6 Release 1
     */
    public static final int FEATURE_STRICT_VARS = 8;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[20]++;
  }

    /**
     * Control if strict eval mode is enabled.
     * When the feature is on Rhino reports runtime errors if non-string
     * argument is passed to the eval function. When the feature is off
     * eval simply return non-string argument as is without performing any
     * evaluation as required by ECMA 262.
     * <p>
     * By default {@link #hasFeature(int)} returns false.
     * @since 1.6 Release 1
     */
    public static final int FEATURE_STRICT_EVAL = 9;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[21]++;
  }

    /**
     * When the feature is on Rhino will add a "fileName" and "lineNumber"
     * properties to Error objects automatically. When the feature is off, you
     * have to explicitly pass them as the second and third argument to the
     * Error constructor. Note that neither behavior is fully ECMA 262
     * compliant (as 262 doesn't specify a three-arg constructor), but keeping
     * the feature off results in Error objects that don't have
     * additional non-ECMA properties when constructed using the ECMA-defined
     * single-arg constructor and is thus desirable if a stricter ECMA
     * compliance is desired, specifically adherence to the point 15.11.5. of
     * the standard.
     * <p>
     * By default {@link #hasFeature(int)} returns false.
     * @since 1.6 Release 6
     */
    public static final int FEATURE_LOCATION_INFORMATION_IN_ERROR = 10;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[22]++;
  }

    /**
     * Controls whether JS 1.5 'strict mode' is enabled.
     * When the feature is on, Rhino reports more than a dozen different
     * warnings.  When the feature is off, these warnings are not generated.
     * FEATURE_STRICT_MODE implies FEATURE_STRICT_VARS and FEATURE_STRICT_EVAL.
     * <p>
     * By default {@link #hasFeature(int)} returns false.
     * @since 1.6 Release 6
     */
    public static final int FEATURE_STRICT_MODE = 11;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[23]++;
  }

    /**
     * Controls whether a warning should be treated as an error.
     * @since 1.6 Release 6
     */
    public static final int FEATURE_WARNING_AS_ERROR = 12;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[24]++;
  }

    /**
     * Enables enhanced access to Java.
     * Specifically, controls whether private and protected members can be
     * accessed, and whether scripts can catch all Java exceptions.
     * <p>
     * Note that this feature should only be enabled for trusted scripts.
     * <p>
     * By default {@link #hasFeature(int)} returns false.
     * @since 1.7 Release 1
     */
    public static final int FEATURE_ENHANCED_JAVA_ACCESS = 13;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[25]++;
  }

    public static final String languageVersionProperty = "language version";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[26]++;
  }
    public static final String errorReporterProperty   = "error reporter";
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[27]++;
  }

    /**
     * Convenient value to use as zero-length array of objects.
     */
    public static final Object[] emptyArgs = ScriptRuntime.emptyArgs;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[28]++;
  }

    /**
     * Creates a new Context. The context will be associated with the {@link
     * ContextFactory#getGlobal() global context factory}.
     *
     * Note that the Context must be associated with a thread before
     * it can be used to execute a script.
     * @deprecated this constructor is deprecated because it creates a
     * dependency on a static singleton context factory. Use
     * {@link ContextFactory#enter()} or
     * {@link ContextFactory#call(ContextAction)} instead. If you subclass
     * this class, consider using {@link #Context(ContextFactory)} constructor
     * instead in the subclasses' constructors.
     */
    public Context()
    {
        this(ContextFactory.getGlobal());
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[29]++;
    }

    /**
     * Creates a new context. Provided as a preferred super constructor for
     * subclasses in place of the deprecated default public constructor.
     * @param factory the context factory associated with this context (most
     * likely, the one that created the context). Can not be null. The context
     * features are inherited from the factory, and the context will also
     * otherwise use its factory's services.
     * @throws IllegalArgumentException if factory parameter is null.
     */
    protected Context(ContextFactory factory)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;
        if((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((factory == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[1]++;
            throw new IllegalArgumentException("factory == null");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[2]++;}
        this.factory = factory;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[31]++;
        version = VERSION_DEFAULT;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[32]++;
        optimizationLevel = codegenClass != null ? 0 : -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[33]++;
        maximumInterpreterStackDepth = Integer.MAX_VALUE;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[34]++;
    }

    /**
     * Get the current Context.
     *
     * The current Context is per-thread; this method looks up
     * the Context associated with the current thread. <p>
     *
     * @return the Context associated with the current thread, or
     *         null if no context is associated with the current
     *         thread.
     * @see ContextFactory#enterContext()
     * @see ContextFactory#call(ContextAction)
     */
    public static Context getCurrentContext()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[35]++;
        Object helper = VMBridge.instance.getThreadContextHelper();
        return VMBridge.instance.getContext(helper);
    }

    /**
     * Same as calling {@link ContextFactory#enterContext()} on the global
     * ContextFactory instance.
     * @return a Context associated with the current thread
     * @see #getCurrentContext()
     * @see #exit()
     * @see #call(ContextAction)
     */
    public static Context enter()
    {
        return enter(null);
    }

    /**
     * Get a Context associated with the current thread, using
     * the given Context if need be.
     * <p>
     * The same as <code>enter()</code> except that <code>cx</code>
     * is associated with the current thread and returned if
     * the current thread has no associated context and <code>cx</code>
     * is not associated with any other thread.
     * @param cx a Context to associate with the thread if possible
     * @return a Context associated with the current thread
     * @deprecated use {@link ContextFactory#enterContext(Context)} instead as
     * this method relies on usage of a static singleton "global" ContextFactory.
     * @see ContextFactory#enterContext(Context)
     * @see ContextFactory#call(ContextAction)
     */
    public static Context enter(Context cx)
    {
        return enter(cx, ContextFactory.getGlobal());
    }

    static final Context enter(Context cx, ContextFactory factory)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[36]++;
        Object helper = VMBridge.instance.getThreadContextHelper();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[37]++;
        Context old = VMBridge.instance.getContext(helper);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[38]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((old != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[3]++;
            cx = old;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[39]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[4]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[40]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[5]++;
                cx = factory.makeContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[41]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[42]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cx.enterCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[7]++;
                    throw new IllegalStateException("factory.makeContext() returned Context instance already associated with some thread");

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[8]++;}
                factory.onContextCreated(cx);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[43]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[44]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((factory.isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cx.isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[9]++;
                    cx.seal(null);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[45]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[10]++;}

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[6]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[46]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((cx.enterCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[11]++;
                    throw new IllegalStateException("can not use Context instance already associated with some thread");

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[12]++;}
            }
            VMBridge.instance.setContext(helper, cx);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[47]++;
        }
        ++cx.enterCount;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[48]++;
        return cx;
     }

    /**
     * Exit a block of code requiring a Context.
     *
     * Calling <code>exit()</code> will remove the association between
     * the current thread and a Context if the prior call to
     * {@link ContextFactory#enterContext()} on this thread newly associated a
     * Context with this thread. Once the current thread no longer has an
     * associated Context, it cannot be used to execute JavaScript until it is
     * again associated with a Context.
     * @see ContextFactory#enterContext()
     */
    public static void exit()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[49]++;
        Object helper = VMBridge.instance.getThreadContextHelper();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[50]++;
        Context cx = VMBridge.instance.getContext(helper);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[51]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[13]++;
            throw new IllegalStateException(
                "Calling Context.exit without previous Context.enter");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[14]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[52]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cx.enterCount < 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[15]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[53]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[16]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((--cx.enterCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[17]++;
            VMBridge.instance.setContext(helper, null);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[55]++;
            cx.factory.onContextReleased(cx);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[56]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[18]++;}
    }

    /**
     * Call {@link ContextAction#run(Context cx)}
     * using the Context instance associated with the current thread.
     * If no Context is associated with the thread, then
     * <tt>ContextFactory.getGlobal().makeContext()</tt> will be called to
     * construct new Context instance. The instance will be temporary
     * associated with the thread during call to
     * {@link ContextAction#run(Context)}.
     * @deprecated use {@link ContextFactory#call(ContextAction)} instead as
     * this method relies on usage of a static singleton "global"
     * ContextFactory.
     * @return The result of {@link ContextAction#run(Context)}.
     */
    public static Object call(ContextAction action)
    {
        return call(ContextFactory.getGlobal(), action);
    }

    /**
     * Call {@link
     * Callable#call(Context cx, Scriptable scope, Scriptable thisObj,
     *               Object[] args)}
     * using the Context instance associated with the current thread.
     * If no Context is associated with the thread, then
     * {@link ContextFactory#makeContext()} will be called to construct
     * new Context instance. The instance will be temporary associated
     * with the thread during call to {@link ContextAction#run(Context)}.
     * <p>
     * It is allowed but not advisable to use null for <tt>factory</tt>
     * argument in which case the global static singleton ContextFactory
     * instance will be used to create new context instances.
     * @see ContextFactory#call(ContextAction)
     */
    public static Object call(ContextFactory factory, final Callable callable,
                              final Scriptable scope, final Scriptable thisObj,
                              final Object[] args)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[57]++;
int CodeCoverConditionCoverageHelper_C10;
        if((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((factory == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[19]++;
            factory = ContextFactory.getGlobal();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[58]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[20]++;}
        return call(factory, new ContextAction() {
            public Object run(Context cx) {
                return callable.call(cx, scope, thisObj, args);
            }
        });
    }

    /**
     * The method implements {@link ContextFactory#call(ContextAction)} logic.
     */
    static Object call(ContextFactory factory, ContextAction action) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[59]++;
        Context cx = enter(null, factory);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[60]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return action.run(cx);
        }
        finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[21]++;
}
            exit();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[61]++;
        }
    }

    /**
     * @deprecated
     * @see ContextFactory#addListener(org.mozilla.javascript.ContextFactory.Listener)
     * @see ContextFactory#getGlobal()
     */
    public static void addContextListener(ContextListener listener)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[62]++;
        // Special workaround for the debugger
        String DBG = "org.mozilla.javascript.tools.debugger.Main";
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((DBG.equals(listener.getClass().getName())) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[22]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[64]++;
            Class<?> cl = listener.getClass();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[65]++;
            Class<?> factoryClass = Kit.classOrNull(
                "org.mozilla.javascript.ContextFactory");
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[66]++;
            Class<?>[] sig = { factoryClass };
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[67]++;
            Object[] args = { ContextFactory.getGlobal() };
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[68]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[69]++;
                Method m = cl.getMethod("attachTo", sig);
                m.invoke(listener, args);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[70]++;
            } catch (Exception ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[25]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[71]++;
                RuntimeException rex = new RuntimeException();
                Kit.initCause(rex, ex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[72]++;
                throw rex;
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[24]++;
}
  }
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[23]++;}

        ContextFactory.getGlobal().addListener(listener);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[73]++;
    }

    /**
     * @deprecated
     * @see ContextFactory#removeListener(org.mozilla.javascript.ContextFactory.Listener)
     * @see ContextFactory#getGlobal()
     */
    public static void removeContextListener(ContextListener listener)
    {
        ContextFactory.getGlobal().addListener(listener);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[74]++;
    }

    /**
     * Return {@link ContextFactory} instance used to create this Context.
     */
    public final ContextFactory getFactory()
    {
        return factory;
    }

    /**
     * Checks if this is a sealed Context. A sealed Context instance does not
     * allow to modify any of its properties and will throw an exception
     * on any such attempt.
     * @see #seal(Object sealKey)
     */
    public final boolean isSealed()
    {
        return sealed;
    }

    /**
     * Seal this Context object so any attempt to modify any of its properties
     * including calling {@link #enter()} and {@link #exit()} methods will
     * throw an exception.
     * <p>
     * If <tt>sealKey</tt> is not null, calling
     * {@link #unseal(Object sealKey)} with the same key unseals
     * the object. If <tt>sealKey</tt> is null, unsealing is no longer possible.
     *
     * @see #isSealed()
     * @see #unseal(Object)
     */
    public final void seal(Object sealKey)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[75]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[26]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[76]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[27]++;}
        sealed = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[77]++;
        this.sealKey = sealKey;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[78]++;
    }

    /**
     * Unseal previously sealed Context object.
     * The <tt>sealKey</tt> argument should not be null and should match
     * <tt>sealKey</tt> suplied with the last call to
     * {@link #seal(Object)} or an exception will be thrown.
     *
     * @see #isSealed()
     * @see #seal(Object sealKey)
     */
    public final void unseal(Object sealKey)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[79]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((sealKey == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[28]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[29]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[80]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.sealKey != sealKey) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[30]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[31]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[81]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[32]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[33]++;}
        sealed = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[82]++;
        this.sealKey = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[83]++;
    }

    static void onSealedMutation()
    {
        throw new IllegalStateException();
    }

    /**
     * Get the current language version.
     * <p>
     * The language version number affects JavaScript semantics as detailed
     * in the overview documentation.
     *
     * @return an integer that is one of VERSION_1_0, VERSION_1_1, etc.
     */
    public final int getLanguageVersion()
    {
       return version;
    }

    /**
     * Set the language version.
     *
     * <p>
     * Setting the language version will affect functions and scripts compiled
     * subsequently. See the overview documentation for version-specific
     * behavior.
     *
     * @param version the version as specified by VERSION_1_0, VERSION_1_1, etc.
     */
    public void setLanguageVersion(int version)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[84]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[34]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[85]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[35]++;}
        checkLanguageVersion(version);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[86]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[87]++;
        Object listeners = propertyListeners;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[88]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((listeners != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((version != this.version) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[36]++;
            firePropertyChangeImpl(listeners, languageVersionProperty,
                               Integer.valueOf(this.version),
                               Integer.valueOf(version));
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[89]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[37]++;}
        this.version = version;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[90]++;
    }

    public static boolean isValidLanguageVersion(int version)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[91]++;
        switch (version) {
            case VERSION_DEFAULT:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[38]++;
            case VERSION_1_0:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[39]++;
            case VERSION_1_1:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[40]++;
            case VERSION_1_2:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[41]++;
            case VERSION_1_3:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[42]++;
            case VERSION_1_4:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[43]++;
            case VERSION_1_5:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[44]++;
            case VERSION_1_6:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[45]++;
            case VERSION_1_7:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[46]++;
            case VERSION_1_8:
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[47]++;
                return true; default : CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[48]++;
        }
        return false;
    }

    public static void checkLanguageVersion(int version)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[92]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isValidLanguageVersion(version)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[49]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[50]++;}
        throw new IllegalArgumentException("Bad language version: "+version);
    }

    /**
     * Get the implementation version.
     *
     * <p>
     * The implementation version is of the form
     * <pre>
     *    "<i>name langVer</i> <code>release</code> <i>relNum date</i>"
     * </pre>
     * where <i>name</i> is the name of the product, <i>langVer</i> is
     * the language version, <i>relNum</i> is the release number, and
     * <i>date</i> is the release date for that specific
     * release in the form "yyyy mm dd".
     *
     * @return a string that encodes the product, language version, release
     *         number, and date.
     */
    public final String getImplementationVersion()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[93]++;
int CodeCoverConditionCoverageHelper_C19;
        // XXX Probably it would be better to embed this directly into source
        // with special build preprocessing but that would require some ant
        // tweaking and then replacing token in resource files was simpler
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((implementationVersion == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[51]++;
            implementationVersion
                = ScriptRuntime.getMessage0("implementation.version");
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[94]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[52]++;}
        return implementationVersion;
    }

    /**
     * Get the current error reporter.
     *
     * @see org.mozilla.javascript.ErrorReporter
     */
    public final ErrorReporter getErrorReporter()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[95]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((errorReporter == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[53]++;
            return DefaultErrorReporter.instance;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[54]++;}
        return errorReporter;
    }

    /**
     * Change the current error reporter.
     *
     * @return the previous error reporter
     * @see org.mozilla.javascript.ErrorReporter
     */
    public final ErrorReporter setErrorReporter(ErrorReporter reporter)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[96]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[55]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[97]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[56]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[98]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((reporter == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[57]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[58]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[99]++;
        ErrorReporter old = getErrorReporter();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[100]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((reporter == old) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[59]++;
            return old;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[60]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[101]++;
        Object listeners = propertyListeners;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[102]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((listeners != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[61]++;
            firePropertyChangeImpl(listeners, errorReporterProperty,
                                   old, reporter);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[103]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[62]++;}
        this.errorReporter = reporter;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[104]++;
        return old;
    }

    /**
     * Get the current locale.  Returns the default locale if none has
     * been set.
     *
     * @see java.util.Locale
     */

    public final Locale getLocale()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[105]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((locale == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[63]++;
            locale = Locale.getDefault();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[106]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[64]++;}
        return locale;
    }

    /**
     * Set the current locale.
     *
     * @see java.util.Locale
     */
    public final Locale setLocale(Locale loc)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[107]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[65]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[108]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[66]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[109]++;
        Locale result = locale;
        locale = loc;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[110]++;
        return result;
    }

    /**
     * Register an object to receive notifications when a bound property
     * has changed
     * @see java.beans.PropertyChangeEvent
     * @see #removePropertyChangeListener(java.beans.PropertyChangeListener)
     * @param l the listener
     */
    public final void addPropertyChangeListener(PropertyChangeListener l)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[111]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[67]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[112]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[68]++;}
        propertyListeners = Kit.addListener(propertyListeners, l);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[113]++;
    }

    /**
     * Remove an object from the list of objects registered to receive
     * notification of changes to a bounded property
     * @see java.beans.PropertyChangeEvent
     * @see #addPropertyChangeListener(java.beans.PropertyChangeListener)
     * @param l the listener
     */
    public final void removePropertyChangeListener(PropertyChangeListener l)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[114]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[69]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[115]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[70]++;}
        propertyListeners = Kit.removeListener(propertyListeners, l);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[116]++;
    }

    /**
     * Notify any registered listeners that a bounded property has changed
     * @see #addPropertyChangeListener(java.beans.PropertyChangeListener)
     * @see #removePropertyChangeListener(java.beans.PropertyChangeListener)
     * @see java.beans.PropertyChangeListener
     * @see java.beans.PropertyChangeEvent
     * @param  property  the bound property
     * @param  oldValue  the old value
     * @param  newValue   the new value
     */
    final void firePropertyChange(String property, Object oldValue,
                                  Object newValue)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[117]++;
        Object listeners = propertyListeners;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[118]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((listeners != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[71]++;
            firePropertyChangeImpl(listeners, property, oldValue, newValue);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[119]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[72]++;}
    }

    private void firePropertyChangeImpl(Object listeners, String property,
                                        Object oldValue, Object newValue)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[120]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[1]++;


        for (int i = 0; ; ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[3]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[121]++;
            Object l = Kit.getListener(listeners, i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[122]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((l == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[73]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[123]++;
                break;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[74]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[124]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((l instanceof PropertyChangeListener) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[75]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[125]++;
                PropertyChangeListener pcl = (PropertyChangeListener)l;
                pcl.propertyChange(new PropertyChangeEvent(
                    this, property, oldValue, newValue));
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[126]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[76]++;}
        }
    }

    /**
     * Report a warning using the error reporter for the current thread.
     *
     * @param message the warning message to report
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number
     * @param lineSource the text of the line (may be null)
     * @param lineOffset the offset into lineSource where problem was detected
     * @see org.mozilla.javascript.ErrorReporter
     */
    public static void reportWarning(String message, String sourceName,
                                     int lineno, String lineSource,
                                     int lineOffset)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[127]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[128]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cx.hasFeature(FEATURE_WARNING_AS_ERROR)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[77]++;
            reportError(message, sourceName, lineno, lineSource, lineOffset);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[129]++;
}
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[78]++;
            cx.getErrorReporter().warning(message, sourceName, lineno,
                                          lineSource, lineOffset);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[130]++;
}
    }

    /**
     * Report a warning using the error reporter for the current thread.
     *
     * @param message the warning message to report
     * @see org.mozilla.javascript.ErrorReporter
     */
    public static void reportWarning(String message)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[131]++;
        int[] linep = { 0 };
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[132]++;
        String filename = getSourcePositionFromStack(linep);
        Context.reportWarning(message, filename, linep[0], null, 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[133]++;
    }

    public static void reportWarning(String message, Throwable t)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[134]++;
        int[] linep = { 0 };
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[135]++;
        String filename = getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[136]++;
        Writer sw = new StringWriter();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[137]++;
        PrintWriter pw = new PrintWriter(sw);
        pw.println(message);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[138]++;
        t.printStackTrace(pw);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[139]++;
        pw.flush();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[140]++;
        Context.reportWarning(sw.toString(), filename, linep[0], null, 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[141]++;
    }

    /**
     * Report an error using the error reporter for the current thread.
     *
     * @param message the error message to report
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number
     * @param lineSource the text of the line (may be null)
     * @param lineOffset the offset into lineSource where problem was detected
     * @see org.mozilla.javascript.ErrorReporter
     */
    public static void reportError(String message, String sourceName,
                                   int lineno, String lineSource,
                                   int lineOffset)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[142]++;
        Context cx = getCurrentContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[143]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[79]++;
            cx.getErrorReporter().error(message, sourceName, lineno,
                                        lineSource, lineOffset);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[144]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[80]++;
            throw new EvaluatorException(message, sourceName, lineno,
                                         lineSource, lineOffset);
        }
    }

    /**
     * Report an error using the error reporter for the current thread.
     *
     * @param message the error message to report
     * @see org.mozilla.javascript.ErrorReporter
     */
    public static void reportError(String message)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[145]++;
        int[] linep = { 0 };
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[146]++;
        String filename = getSourcePositionFromStack(linep);
        Context.reportError(message, filename, linep[0], null, 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[147]++;
    }

    /**
     * Report a runtime error using the error reporter for the current thread.
     *
     * @param message the error message to report
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number
     * @param lineSource the text of the line (may be null)
     * @param lineOffset the offset into lineSource where problem was detected
     * @return a runtime exception that will be thrown to terminate the
     *         execution of the script
     * @see org.mozilla.javascript.ErrorReporter
     */
    public static EvaluatorException reportRuntimeError(String message,
                                                        String sourceName,
                                                        int lineno,
                                                        String lineSource,
                                                        int lineOffset)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[148]++;
        Context cx = getCurrentContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[149]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[81]++;
            return cx.getErrorReporter().
                            runtimeError(message, sourceName, lineno,
                                         lineSource, lineOffset);

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[82]++;
            throw new EvaluatorException(message, sourceName, lineno,
                                         lineSource, lineOffset);
        }
    }

    static EvaluatorException reportRuntimeError0(String messageId)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[150]++;
        String msg = ScriptRuntime.getMessage0(messageId);
        return reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError1(String messageId,
                                                  Object arg1)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[151]++;
        String msg = ScriptRuntime.getMessage1(messageId, arg1);
        return reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError2(String messageId,
                                                  Object arg1, Object arg2)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[152]++;
        String msg = ScriptRuntime.getMessage2(messageId, arg1, arg2);
        return reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError3(String messageId,
                                                  Object arg1, Object arg2,
                                                  Object arg3)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[153]++;
        String msg = ScriptRuntime.getMessage3(messageId, arg1, arg2, arg3);
        return reportRuntimeError(msg);
    }

    static EvaluatorException reportRuntimeError4(String messageId,
                                                  Object arg1, Object arg2,
                                                  Object arg3, Object arg4)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[154]++;
        String msg
            = ScriptRuntime.getMessage4(messageId, arg1, arg2, arg3, arg4);
        return reportRuntimeError(msg);
    }

    /**
     * Report a runtime error using the error reporter for the current thread.
     *
     * @param message the error message to report
     * @see org.mozilla.javascript.ErrorReporter
     */
    public static EvaluatorException reportRuntimeError(String message)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[155]++;
        int[] linep = { 0 };
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[156]++;
        String filename = getSourcePositionFromStack(linep);
        return Context.reportRuntimeError(message, filename, linep[0], null, 0);
    }

    /**
     * Initialize the standard objects.
     *
     * Creates instances of the standard objects and their constructors
     * (Object, String, Number, Date, etc.), setting up 'scope' to act
     * as a global object as in ECMA 15.1.<p>
     *
     * This method must be called to initialize a scope before scripts
     * can be evaluated in that scope.<p>
     *
     * This method does not affect the Context it is called upon.
     *
     * @return the initialized scope
     */
    public final ScriptableObject initStandardObjects()
    {
        return initStandardObjects(null, false);
    }

    /**
     * Initialize the standard objects.
     *
     * Creates instances of the standard objects and their constructors
     * (Object, String, Number, Date, etc.), setting up 'scope' to act
     * as a global object as in ECMA 15.1.<p>
     *
     * This method must be called to initialize a scope before scripts
     * can be evaluated in that scope.<p>
     *
     * This method does not affect the Context it is called upon.
     *
     * @param scope the scope to initialize, or null, in which case a new
     *        object will be created to serve as the scope
     * @return the initialized scope. The method returns the value of the scope
     *         argument if it is not null or newly allocated scope object which
     *         is an instance {@link ScriptableObject}.
     */
    public final Scriptable initStandardObjects(ScriptableObject scope)
    {
        return initStandardObjects(scope, false);
    }

    /**
     * Initialize the standard objects.
     *
     * Creates instances of the standard objects and their constructors
     * (Object, String, Number, Date, etc.), setting up 'scope' to act
     * as a global object as in ECMA 15.1.<p>
     *
     * This method must be called to initialize a scope before scripts
     * can be evaluated in that scope.<p>
     *
     * This method does not affect the Context it is called upon.<p>
     *
     * This form of the method also allows for creating "sealed" standard
     * objects. An object that is sealed cannot have properties added, changed,
     * or removed. This is useful to create a "superglobal" that can be shared
     * among several top-level objects. Note that sealing is not allowed in
     * the current ECMA/ISO language specification, but is likely for
     * the next version.
     *
     * @param scope the scope to initialize, or null, in which case a new
     *        object will be created to serve as the scope
     * @param sealed whether or not to create sealed standard objects that
     *        cannot be modified.
     * @return the initialized scope. The method returns the value of the scope
     *         argument if it is not null or newly allocated scope object.
     * @since 1.4R3
     */
    public ScriptableObject initStandardObjects(ScriptableObject scope,
                                                boolean sealed)
    {
        return ScriptRuntime.initStandardObjects(this, scope, sealed);
    }

    /**
     * Get the singleton object that represents the JavaScript Undefined value.
     */
    public static Object getUndefinedValue()
    {
        return Undefined.instance;
    }

    /**
     * Evaluate a JavaScript source string.
     *
     * The provided source name and line number are used for error messages
     * and for producing debug information.
     *
     * @param scope the scope to execute in
     * @param source the JavaScript source
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number
     * @param securityDomain an arbitrary object that specifies security
     *        information about the origin or owner of the script. For
     *        implementations that don't care about security, this value
     *        may be null.
     * @return the result of evaluating the string
     * @see org.mozilla.javascript.SecurityController
     */
    public final Object evaluateString(Scriptable scope, String source,
                                       String sourceName, int lineno,
                                       Object securityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[157]++;
        Script script = compileString(source, sourceName, lineno,
                                      securityDomain);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[158]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[83]++;
            return script.exec(this, scope);

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[84]++;
            return null;
        }
    }

    /**
     * Evaluate a reader as JavaScript source.
     *
     * All characters of the reader are consumed.
     *
     * @param scope the scope to execute in
     * @param in the Reader to get JavaScript source from
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number
     * @param securityDomain an arbitrary object that specifies security
     *        information about the origin or owner of the script. For
     *        implementations that don't care about security, this value
     *        may be null.
     * @return the result of evaluating the source
     *
     * @exception IOException if an IOException was generated by the Reader
     */
    public final Object evaluateReader(Scriptable scope, Reader in,
                                       String sourceName, int lineno,
                                       Object securityDomain)
        throws IOException
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[159]++;
        Script script = compileReader(scope, in, sourceName, lineno,
                                      securityDomain);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[160]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((script != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[85]++;
            return script.exec(this, scope);

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[86]++;
            return null;
        }
    }

    /**
     * Execute script that may pause execution by capturing a continuation.
     * Caller must be prepared to catch a ContinuationPending exception
     * and resume execution by calling
     * {@link #resumeContinuation(Object, Scriptable, Object)}.
     * @param script The script to execute. Script must have been compiled
     *      with interpreted mode (optimization level -1)
     * @param scope The scope to execute the script against
     * @throws ContinuationPending if the script calls a function that results
     *      in a call to {@link #captureContinuation()}
     * @since 1.7 Release 2
     */
    public Object executeScriptWithContinuations(Script script,
            Scriptable scope)
        throws ContinuationPending
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[161]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((script instanceof InterpretedFunction) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((((InterpretedFunction)script).isScript()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false))
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[87]++;
            // Can only be applied to scripts
            throw new IllegalArgumentException("Script argument was not" +
                    " a script or was not created by interpreted mode ");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[88]++;}
        return callFunctionWithContinuations((InterpretedFunction) script,
                scope, ScriptRuntime.emptyArgs);
    }

    /**
     * Call function that may pause execution by capturing a continuation.
     * Caller must be prepared to catch a ContinuationPending exception
     * and resume execution by calling
     * {@link #resumeContinuation(Object, Scriptable, Object)}.
     * @param function The function to call. The function must have been
     *      compiled with interpreted mode (optimization level -1)
     * @param scope The scope to execute the script against
     * @param args The arguments for the function
     * @throws ContinuationPending if the script calls a function that results
     *      in a call to {@link #captureContinuation()}
     * @since 1.7 Release 2
     */
    public Object callFunctionWithContinuations(Callable function,
            Scriptable scope, Object[] args)
        throws ContinuationPending
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[162]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((function instanceof InterpretedFunction) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[89]++;
            // Can only be applied to scripts
            throw new IllegalArgumentException("Function argument was not" +
                    " created by interpreted mode ");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[90]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[163]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((ScriptRuntime.hasTopCall(this)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[91]++;
            throw new IllegalStateException("Cannot have any pending top " +
                    "calls when executing a script with continuations");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[92]++;}
        // Annotate so we can check later to ensure no java code in
        // intervening frames
        isContinuationsTopCall = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[164]++;
        return ScriptRuntime.doTopCall(function, this, scope, scope, args);
    }

    /**
     * Capture a continuation from the current execution. The execution must
     * have been started via a call to
     * {@link #executeScriptWithContinuations(Script, Scriptable)} or
     * {@link #callFunctionWithContinuations(Callable, Scriptable, Object[])}.
     * This implies that the code calling
     * this method must have been called as a function from the
     * JavaScript script. Also, there cannot be any non-JavaScript code
     * between the JavaScript frames (e.g., a call to eval()). The
     * ContinuationPending exception returned must be thrown.
     * @return A ContinuationPending exception that must be thrown
     * @since 1.7 Release 2
     */
    public ContinuationPending captureContinuation() {
        return new ContinuationPending(
                Interpreter.captureContinuation(this));
    }

    /**
     * Restarts execution of the JavaScript suspended at the call
     * to {@link #captureContinuation()}. Execution of the code will resume
     * with the functionResult as the result of the call that captured the
     * continuation.
     * Execution of the script will either conclude normally and the
     * result returned, another continuation will be captured and
     * thrown, or the script will terminate abnormally and throw an exception.
     * @param continuation The value returned by
     * {@link ContinuationPending#getContinuation()}
     * @param functionResult This value will appear to the code being resumed
     *      as the result of the function that captured the continuation
     * @throws ContinuationPending if another continuation is captured before
     *      the code terminates
     * @since 1.7 Release 2
     */
    public Object resumeContinuation(Object continuation,
            Scriptable scope, Object functionResult)
            throws ContinuationPending
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[165]++;
        Object[] args = { functionResult };
        return Interpreter.restartContinuation(
                (org.mozilla.javascript.NativeContinuation) continuation,
                this, scope, args);
    }

    /**
     * Check whether a string is ready to be compiled.
     * <p>
     * stringIsCompilableUnit is intended to support interactive compilation of
     * JavaScript.  If compiling the string would result in an error
     * that might be fixed by appending more source, this method
     * returns false.  In every other case, it returns true.
     * <p>
     * Interactive shells may accumulate source lines, using this
     * method after each new line is appended to check whether the
     * statement being entered is complete.
     *
     * @param source the source buffer to check
     * @return whether the source is ready for compilation
     * @since 1.4 Release 2
     */
    public final boolean stringIsCompilableUnit(String source)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[166]++;
        boolean errorseen = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[167]++;
        CompilerEnvirons compilerEnv = new CompilerEnvirons();
        compilerEnv.initFromContext(this);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[168]++;
        // no source name or source text manager, because we're just
        // going to throw away the result.
        compilerEnv.setGeneratingSource(false);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[169]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[170]++;
        Parser p = new Parser(compilerEnv, DefaultErrorReporter.instance);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[171]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            p.parse(source, null, 1);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[172]++;
        } catch (EvaluatorException ee) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[94]++;
            errorseen = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[173]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[93]++;
}
  }
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[174]++;
int CodeCoverConditionCoverageHelper_C41;
        // Return false only if an error occurred as a result of reading past
        // the end of the file, i.e. if the source could be fixed by
        // appending more source.
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((errorseen) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((p.eof()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[95]++;
            return false;
}
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[96]++;
            return true;
}
    }

    /**
     * @deprecated
     * @see #compileReader(Reader in, String sourceName, int lineno,
     *                     Object securityDomain)
     */
    public final Script compileReader(Scriptable scope, Reader in,
                                      String sourceName, int lineno,
                                      Object securityDomain)
        throws IOException
    {
        return compileReader(in, sourceName, lineno, securityDomain);
    }

    /**
     * Compiles the source in the given reader.
     * <p>
     * Returns a script that may later be executed.
     * Will consume all the source in the reader.
     *
     * @param in the input reader
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number for reporting errors
     * @param securityDomain an arbitrary object that specifies security
     *        information about the origin or owner of the script. For
     *        implementations that don't care about security, this value
     *        may be null.
     * @return a script that may later be executed
     * @exception IOException if an IOException was generated by the Reader
     * @see org.mozilla.javascript.Script
     */
    public final Script compileReader(Reader in, String sourceName,
                                      int lineno, Object securityDomain)
        throws IOException
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[175]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((lineno < 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[97]++;
            // For compatibility IllegalArgumentException can not be thrown here
            lineno = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[176]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[98]++;}
        return (Script) compileImpl(null, in, null, sourceName, lineno,
                                    securityDomain, false, null, null);
    }

    /**
     * Compiles the source in the given string.
     * <p>
     * Returns a script that may later be executed.
     *
     * @param source the source string
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number for reporting errors. Use
     *        0 if the line number is unknown.
     * @param securityDomain an arbitrary object that specifies security
     *        information about the origin or owner of the script. For
     *        implementations that don't care about security, this value
     *        may be null.
     * @return a script that may later be executed
     * @see org.mozilla.javascript.Script
     */
    public final Script compileString(String source,
                                      String sourceName, int lineno,
                                      Object securityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[177]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((lineno < 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[99]++;
            // For compatibility IllegalArgumentException can not be thrown here
            lineno = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[178]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[100]++;}
        return compileString(source, null, null, sourceName, lineno,
                             securityDomain);
    }

    final Script compileString(String source,
                               Evaluator compiler,
                               ErrorReporter compilationErrorReporter,
                               String sourceName, int lineno,
                               Object securityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[179]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
            return (Script) compileImpl(null, null, source, sourceName, lineno,
                                        securityDomain, false,
                                        compiler, compilationErrorReporter);
        } catch (IOException ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[102]++;
            // Should not happen when dealing with source as string
            throw new RuntimeException();
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[101]++;
}
  }
    }

    /**
     * Compile a JavaScript function.
     * <p>
     * The function source must be a function definition as defined by
     * ECMA (e.g., "function f(a) { return a; }").
     *
     * @param scope the scope to compile relative to
     * @param source the function definition source
     * @param sourceName a string describing the source, such as a filename
     * @param lineno the starting line number
     * @param securityDomain an arbitrary object that specifies security
     *        information about the origin or owner of the script. For
     *        implementations that don't care about security, this value
     *        may be null.
     * @return a Function that may later be called
     * @see org.mozilla.javascript.Function
     */
    public final Function compileFunction(Scriptable scope, String source,
                                          String sourceName, int lineno,
                                          Object securityDomain)
    {
        return compileFunction(scope, source, null, null, sourceName, lineno,
                               securityDomain);
    }

    final Function compileFunction(Scriptable scope, String source,
                                   Evaluator compiler,
                                   ErrorReporter compilationErrorReporter,
                                   String sourceName, int lineno,
                                   Object securityDomain)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[180]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
        try {
CodeCoverTryBranchHelper_Try5 = true;
            return (Function) compileImpl(scope, null, source, sourceName,
                                          lineno, securityDomain, true,
                                          compiler, compilationErrorReporter);
        }
        catch (IOException ioe) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[104]++;
            // Should never happen because we just made the reader
            // from a String
            throw new RuntimeException();
        } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[103]++;
}
  }
    }

    /**
     * Decompile the script.
     * <p>
     * The canonical source of the script is returned.
     *
     * @param script the script to decompile
     * @param indent the number of spaces to indent the result
     * @return a string representing the script source
     */
    public final String decompileScript(Script script, int indent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[181]++;
        NativeFunction scriptImpl = (NativeFunction) script;
        return scriptImpl.decompile(indent, 0);
    }

    /**
     * Decompile a JavaScript Function.
     * <p>
     * Decompiles a previously compiled JavaScript function object to
     * canonical source.
     * <p>
     * Returns function body of '[native code]' if no decompilation
     * information is available.
     *
     * @param fun the JavaScript function to decompile
     * @param indent the number of spaces to indent the result
     * @return a string representing the function source
     */
    public final String decompileFunction(Function fun, int indent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[182]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((fun instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[105]++;
            return ((BaseFunction)fun).decompile(indent, 0);
}
        else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[106]++;
            return "function " + fun.getClassName() +
                   "() {\n\t[native code]\n}\n";
}
    }

    /**
     * Decompile the body of a JavaScript Function.
     * <p>
     * Decompiles the body a previously compiled JavaScript Function
     * object to canonical source, omitting the function header and
     * trailing brace.
     *
     * Returns '[native code]' if no decompilation information is available.
     *
     * @param fun the JavaScript function to decompile
     * @param indent the number of spaces to indent the result
     * @return a string representing the function body source.
     */
    public final String decompileFunctionBody(Function fun, int indent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[183]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((fun instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[107]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[184]++;
            BaseFunction bf = (BaseFunction)fun;
            return bf.decompile(indent, Decompiler.ONLY_BODY_FLAG);

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[108]++;}
        // ALERT: not sure what the right response here is.
        return "[native code]\n";
    }

    /**
     * Create a new JavaScript object.
     *
     * Equivalent to evaluating "new Object()".
     * @param scope the scope to search for the constructor and to evaluate
     *              against
     * @return the new object
     */
    public Scriptable newObject(Scriptable scope)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[185]++;
        NativeObject result = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(result, scope,
                TopLevel.Builtins.Object);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[186]++;
        return result;
    }

    /**
     * Create a new JavaScript object by executing the named constructor.
     *
     * The call <code>newObject(scope, "Foo")</code> is equivalent to
     * evaluating "new Foo()".
     *
     * @param scope the scope to search for the constructor and to evaluate against
     * @param constructorName the name of the constructor to call
     * @return the new object
     */
    public Scriptable newObject(Scriptable scope, String constructorName)
    {
        return newObject(scope, constructorName, ScriptRuntime.emptyArgs);
    }

    /**
     * Creates a new JavaScript object by executing the named constructor.
     *
     * Searches <code>scope</code> for the named constructor, calls it with
     * the given arguments, and returns the result.<p>
     *
     * The code
     * <pre>
     * Object[] args = { "a", "b" };
     * newObject(scope, "Foo", args)</pre>
     * is equivalent to evaluating "new Foo('a', 'b')", assuming that the Foo
     * constructor has been defined in <code>scope</code>.
     *
     * @param scope The scope to search for the constructor and to evaluate
     *              against
     * @param constructorName the name of the constructor to call
     * @param args the array of arguments for the constructor
     * @return the new object
     */
    public Scriptable newObject(Scriptable scope, String constructorName,
                                Object[] args)
    {
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[187]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[188]++;
        Function ctor = ScriptRuntime.getExistingCtor(this, scope,
                                                      constructorName);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[189]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[109]++; args = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[190]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[110]++;}
        return ctor.construct(this, scope, args);
    }

    /**
     * Create an array with a specified initial length.
     * <p>
     * @param scope the scope to create the object in
     * @param length the initial length (JavaScript arrays may have
     *               additional properties added dynamically).
     * @return the new array object
     */
    public Scriptable newArray(Scriptable scope, int length)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[191]++;
        NativeArray result = new NativeArray(length);
        ScriptRuntime.setBuiltinProtoAndParent(result, scope,
                TopLevel.Builtins.Array);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[192]++;
        return result;
    }

    /**
     * Create an array with a set of initial elements.
     *
     * @param scope the scope to create the object in.
     * @param elements the initial elements. Each object in this array
     *                 must be an acceptable JavaScript type and type
     *                 of array should be exactly Object[], not
     *                 SomeObjectSubclass[].
     * @return the new array object.
     */
    public Scriptable newArray(Scriptable scope, Object[] elements)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[193]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((elements.getClass().getComponentType() != ScriptRuntime.ObjectClass) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[111]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[112]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[194]++;
        NativeArray result = new NativeArray(elements);
        ScriptRuntime.setBuiltinProtoAndParent(result, scope,
                TopLevel.Builtins.Array);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[195]++;
        return result;
    }

    /**
     * Get the elements of a JavaScript array.
     * <p>
     * If the object defines a length property convertible to double number,
     * then the number is converted Uint32 value as defined in Ecma 9.6
     * and Java array of that size is allocated.
     * The array is initialized with the values obtained by
     * calling get() on object for each value of i in [0,length-1]. If
     * there is not a defined value for a property the Undefined value
     * is used to initialize the corresponding element in the array. The
     * Java array is then returned.
     * If the object doesn't define a length property or it is not a number,
     * empty array is returned.
     * @param object the JavaScript array or array-like object
     * @return a Java array of objects
     * @since 1.4 release 2
     */
    public final Object[] getElements(Scriptable object)
    {
        return ScriptRuntime.getArrayElements(object);
    }

    /**
     * Convert the value to a JavaScript boolean value.
     * <p>
     * See ECMA 9.2.
     *
     * @param value a JavaScript value
     * @return the corresponding boolean value converted using
     *         the ECMA rules
     */
    public static boolean toBoolean(Object value)
    {
        return ScriptRuntime.toBoolean(value);
    }

    /**
     * Convert the value to a JavaScript Number value.
     * <p>
     * Returns a Java double for the JavaScript Number.
     * <p>
     * See ECMA 9.3.
     *
     * @param value a JavaScript value
     * @return the corresponding double value converted using
     *         the ECMA rules
     */
    public static double toNumber(Object value)
    {
        return ScriptRuntime.toNumber(value);
    }

    /**
     * Convert the value to a JavaScript String value.
     * <p>
     * See ECMA 9.8.
     * <p>
     * @param value a JavaScript value
     * @return the corresponding String value converted using
     *         the ECMA rules
     */
    public static String toString(Object value)
    {
        return ScriptRuntime.toString(value);
    }

    /**
     * Convert the value to an JavaScript object value.
     * <p>
     * Note that a scope must be provided to look up the constructors
     * for Number, Boolean, and String.
     * <p>
     * See ECMA 9.9.
     * <p>
     * Additionally, arbitrary Java objects and classes will be
     * wrapped in a Scriptable object with its Java fields and methods
     * reflected as JavaScript properties of the object.
     *
     * @param value any Java object
     * @param scope global scope containing constructors for Number,
     *              Boolean, and String
     * @return new JavaScript object
     */
    public static Scriptable toObject(Object value, Scriptable scope)
    {
        return ScriptRuntime.toObject(scope, value);
    }

    /**
     * @deprecated
     * @see #toObject(Object, Scriptable)
     */
    public static Scriptable toObject(Object value, Scriptable scope,
                                      Class<?> staticType)
    {
        return ScriptRuntime.toObject(scope, value);
    }

    /**
     * Convenient method to convert java value to its closest representation
     * in JavaScript.
     * <p>
     * If value is an instance of String, Number, Boolean, Function or
     * Scriptable, it is returned as it and will be treated as the corresponding
     * JavaScript type of string, number, boolean, function and object.
     * <p>
     * Note that for Number instances during any arithmetic operation in
     * JavaScript the engine will always use the result of
     * <tt>Number.doubleValue()</tt> resulting in a precision loss if
     * the number can not fit into double.
     * <p>
     * If value is an instance of Character, it will be converted to string of
     * length 1 and its JavaScript type will be string.
     * <p>
     * The rest of values will be wrapped as LiveConnect objects
     * by calling {@link WrapFactory#wrap(Context cx, Scriptable scope,
     * Object obj, Class staticType)} as in:
     * <pre>
     *    Context cx = Context.getCurrentContext();
     *    return cx.getWrapFactory().wrap(cx, scope, value, null);
     * </pre>
     *
     * @param value any Java object
     * @param scope top scope object
     * @return value suitable to pass to any API that takes JavaScript values.
     */
    public static Object javaToJS(Object value, Scriptable scope)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[196]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (128)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C48 |= (32)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 4) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 4) && false))
        {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[113]++;
            return value;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[114]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[197]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((value instanceof Character) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[115]++;
            return String.valueOf(((Character)value).charValue());

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[116]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[198]++;
            Context cx = Context.getContext();
            return cx.getWrapFactory().wrap(cx, scope, value, null);
        }
}
    }

    /**
     * Convert a JavaScript value into the desired type.
     * Uses the semantics defined with LiveConnect3 and throws an
     * Illegal argument exception if the conversion cannot be performed.
     * @param value the JavaScript value to convert
     * @param desiredType the Java type to convert to. Primitive Java
     *        types are represented using the TYPE fields in the corresponding
     *        wrapper class in java.lang.
     * @return the converted value
     * @throws EvaluatorException if the conversion cannot be performed
     */
    public static Object jsToJava(Object value, Class<?> desiredType)
        throws EvaluatorException
    {
        return NativeJavaObject.coerceTypeImpl(desiredType, value);
    }

    /**
     * @deprecated
     * @see #jsToJava(Object, Class)
     * @throws IllegalArgumentException if the conversion cannot be performed.
     *         Note that {@link #jsToJava(Object, Class)} throws
     *         {@link EvaluatorException} instead.
     */
    public static Object toType(Object value, Class<?> desiredType)
        throws IllegalArgumentException
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[199]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
        try {
CodeCoverTryBranchHelper_Try6 = true;
            return jsToJava(value, desiredType);
        } catch (EvaluatorException ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[118]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[200]++;
            IllegalArgumentException
                ex2 = new IllegalArgumentException(ex.getMessage());
            Kit.initCause(ex2, ex);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[201]++;
            throw ex2;
        } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[117]++;
}
  }
    }

    /**
     * Rethrow the exception wrapping it as the script runtime exception.
     * Unless the exception is instance of {@link EcmaError} or
     * {@link EvaluatorException} it will be wrapped as
     * {@link WrappedException}, a subclass of {@link EvaluatorException}.
     * The resulting exception object always contains
     * source name and line number of script that triggered exception.
     * <p>
     * This method always throws an exception, its return value is provided
     * only for convenience to allow a usage like:
     * <pre>
     * throw Context.throwAsScriptRuntimeEx(ex);
     * </pre>
     * to indicate that code after the method is unreachable.
     * @throws EvaluatorException
     * @throws EcmaError
     */
    public static RuntimeException throwAsScriptRuntimeEx(Throwable e)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[202]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[4]++;


int CodeCoverConditionCoverageHelper_C50;
        while ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((e instanceof InvocationTargetException) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[6]++;
}
            e = ((InvocationTargetException) e).getTargetException();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[203]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[204]++;
int CodeCoverConditionCoverageHelper_C51;
        // special handling of Error so scripts would not catch them
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((e instanceof Error) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[119]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[205]++;
            Context cx = getContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[206]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[121]++;
                throw (Error)e;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[122]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[120]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[207]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((e instanceof RhinoException) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[123]++;
            throw (RhinoException)e;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[124]++;}
        throw new WrappedException(e);
    }

    /**
     * Tell whether debug information is being generated.
     * @since 1.3
     */
    public final boolean isGeneratingDebug()
    {
        return generatingDebug;
    }

    /**
     * Specify whether or not debug information should be generated.
     * <p>
     * Setting the generation of debug information on will set the
     * optimization level to zero.
     * @since 1.3
     */
    public final void setGeneratingDebug(boolean generatingDebug)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[208]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[125]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[209]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[126]++;}
        generatingDebugChanged = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[210]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[211]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((generatingDebug) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((getOptimizationLevel() > 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[127]++;
            setOptimizationLevel(0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[212]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[128]++;}
        this.generatingDebug = generatingDebug;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[213]++;
    }

    /**
     * Tell whether source information is being generated.
     * @since 1.3
     */
    public final boolean isGeneratingSource()
    {
        return generatingSource;
    }

    /**
     * Specify whether or not source information should be generated.
     * <p>
     * Without source information, evaluating the "toString" method
     * on JavaScript functions produces only "[native code]" for
     * the body of the function.
     * Note that code generated without source is not fully ECMA
     * conformant.
     * @since 1.3
     */
    public final void setGeneratingSource(boolean generatingSource)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[214]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[129]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[215]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[130]++;}
        this.generatingSource = generatingSource;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[216]++;
    }

    /**
     * Get the current optimization level.
     * <p>
     * The optimization level is expressed as an integer between -1 and
     * 9.
     * @since 1.3
     *
     */
    public final int getOptimizationLevel()
    {
        return optimizationLevel;
    }

    /**
     * Set the current optimization level.
     * <p>
     * The optimization level is expected to be an integer between -1 and
     * 9. Any negative values will be interpreted as -1, and any values
     * greater than 9 will be interpreted as 9.
     * An optimization level of -1 indicates that interpretive mode will
     * always be used. Levels 0 through 9 indicate that class files may
     * be generated. Higher optimization levels trade off compile time
     * performance for runtime performance.
     * The optimizer level can't be set greater than -1 if the optimizer
     * package doesn't exist at run time.
     * @param optimizationLevel an integer indicating the level of
     *        optimization to perform
     * @since 1.3
     *
     */
    public final void setOptimizationLevel(int optimizationLevel)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[217]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[131]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[218]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[132]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[219]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((optimizationLevel == -2) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[133]++;
            // To be compatible with Cocoon fork
            optimizationLevel = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[220]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[134]++;}
        checkOptimizationLevel(optimizationLevel);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[221]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[222]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((codegenClass == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[135]++;
            optimizationLevel = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[223]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[136]++;}
        this.optimizationLevel = optimizationLevel;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[224]++;
    }

    public static boolean isValidOptimizationLevel(int optimizationLevel)
    {
        return -1 <= optimizationLevel && optimizationLevel <= 9;
    }

    public static void checkOptimizationLevel(int optimizationLevel)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[225]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((isValidOptimizationLevel(optimizationLevel)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[137]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[138]++;}
        throw new IllegalArgumentException(
            "Optimization level outside [-1..9]: "+optimizationLevel);
    }

    /**
     * Returns the maximum stack depth (in terms of number of call frames)
     * allowed in a single invocation of interpreter. If the set depth would be
     * exceeded, the interpreter will throw an EvaluatorException in the script.
     * Defaults to Integer.MAX_VALUE. The setting only has effect for
     * interpreted functions (those compiled with optimization level set to -1).
     * As the interpreter doesn't use the Java stack but rather manages its own
     * stack in the heap memory, a runaway recursion in interpreted code would
     * eventually consume all available memory and cause OutOfMemoryError
     * instead of a StackOverflowError limited to only a single thread. This
     * setting helps prevent such situations.
     *
     * @return The current maximum interpreter stack depth.
     */
    public final int getMaximumInterpreterStackDepth()
    {
        return maximumInterpreterStackDepth;
    }

    /**
     * Sets the maximum stack depth (in terms of number of call frames)
     * allowed in a single invocation of interpreter. If the set depth would be
     * exceeded, the interpreter will throw an EvaluatorException in the script.
     * Defaults to Integer.MAX_VALUE. The setting only has effect for
     * interpreted functions (those compiled with optimization level set to -1).
     * As the interpreter doesn't use the Java stack but rather manages its own
     * stack in the heap memory, a runaway recursion in interpreted code would
     * eventually consume all available memory and cause OutOfMemoryError
     * instead of a StackOverflowError limited to only a single thread. This
     * setting helps prevent such situations.
     *
     * @param max the new maximum interpreter stack depth
     * @throws IllegalStateException if this context's optimization level is not
     * -1
     * @throws IllegalArgumentException if the new depth is not at least 1
     */
    public final void setMaximumInterpreterStackDepth(int max)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[226]++;
int CodeCoverConditionCoverageHelper_C61;
        if((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[139]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[227]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[140]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[228]++;
int CodeCoverConditionCoverageHelper_C62;
        if((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((optimizationLevel != -1) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[141]++;
            throw new IllegalStateException("Cannot set maximumInterpreterStackDepth when optimizationLevel != -1");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[142]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[229]++;
int CodeCoverConditionCoverageHelper_C63;
        if((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((max < 1) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[143]++;
            throw new IllegalArgumentException("Cannot set maximumInterpreterStackDepth to less than 1");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[144]++;}
        maximumInterpreterStackDepth = max;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[230]++;
    }

    /**
     * Set the security controller for this context.
     * <p> SecurityController may only be set if it is currently null
     * and {@link SecurityController#hasGlobal()} is <tt>false</tt>.
     * Otherwise a SecurityException is thrown.
     * @param controller a SecurityController object
     * @throws SecurityException if there is already a SecurityController
     *         object for this Context or globally installed.
     * @see SecurityController#initGlobal(SecurityController controller)
     * @see SecurityController#hasGlobal()
     */
    public final void setSecurityController(SecurityController controller)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[231]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[145]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[232]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[146]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[233]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((controller == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[147]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[148]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[234]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((securityController != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[149]++;
            throw new SecurityException("Can not overwrite existing SecurityController object");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[150]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[235]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((SecurityController.hasGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[151]++;
            throw new SecurityException("Can not overwrite existing global SecurityController object");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[152]++;}
        securityController = controller;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[236]++;
    }

    /**
     * Set the LiveConnect access filter for this context.
     * <p> {@link ClassShutter} may only be set if it is currently null.
     * Otherwise a SecurityException is thrown.
     * @param shutter a ClassShutter object
     * @throws SecurityException if there is already a ClassShutter
     *         object for this Context
     */
    public synchronized final void setClassShutter(ClassShutter shutter)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[237]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[153]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[238]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[154]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[239]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((shutter == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[155]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[156]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[240]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((hasClassShutter) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[157]++;
            throw new SecurityException("Cannot overwrite existing " +
                                        "ClassShutter object");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[158]++;}
        classShutter = shutter;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[241]++;
        hasClassShutter = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[242]++;
    }

    final synchronized ClassShutter getClassShutter()
    {
        return classShutter;
    }

    public interface ClassShutterSetter {
        public void setClassShutter(ClassShutter shutter);
        public ClassShutter getClassShutter();
    }

    public final synchronized ClassShutterSetter getClassShutterSetter() {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[243]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((hasClassShutter) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[159]++;
            return null;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[160]++;}
        hasClassShutter = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[244]++;
        return new ClassShutterSetter() {
            public void setClassShutter(ClassShutter shutter) {
                classShutter = shutter;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[245]++;
            }
            public ClassShutter getClassShutter() {
                return classShutter;
            }
        };
    }

    /**
     * Get a value corresponding to a key.
     * <p>
     * Since the Context is associated with a thread it can be
     * used to maintain values that can be later retrieved using
     * the current thread.
     * <p>
     * Note that the values are maintained with the Context, so
     * if the Context is disassociated from the thread the values
     * cannot be retrieved. Also, if private data is to be maintained
     * in this manner the key should be a java.lang.Object
     * whose reference is not divulged to untrusted code.
     * @param key the key used to lookup the value
     * @return a value previously stored using putThreadLocal.
     */
    public final Object getThreadLocal(Object key)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[246]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((threadLocalMap == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[161]++;
            return null;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[162]++;}
        return threadLocalMap.get(key);
    }

    /**
     * Put a value that can later be retrieved using a given key.
     * <p>
     * @param key the key used to index the value
     * @param value the value to save
     */
    public synchronized final void putThreadLocal(Object key, Object value)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[247]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[163]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[248]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[164]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[249]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((threadLocalMap == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[165]++;
            threadLocalMap = new HashMap<Object,Object>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[250]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[166]++;}
        threadLocalMap.put(key, value);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[251]++;
    }

    /**
     * Remove values from thread-local storage.
     * @param key the key for the entry to remove.
     * @since 1.5 release 2
     */
    public final void removeThreadLocal(Object key)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[252]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[167]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[253]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[168]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[254]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((threadLocalMap == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[169]++;
            return;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[170]++;}
        threadLocalMap.remove(key);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[255]++;
    }

    /**
     * @deprecated
     * @see ClassCache#get(Scriptable)
     * @see ClassCache#setCachingEnabled(boolean)
     */
    public static void setCachingEnabled(boolean cachingEnabled)
    {
    }

    /**
     * Set a WrapFactory for this Context.
     * <p>
     * The WrapFactory allows custom object wrapping behavior for
     * Java object manipulated with JavaScript.
     * @see WrapFactory
     * @since 1.5 Release 4
     */
    public final void setWrapFactory(WrapFactory wrapFactory)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[256]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[171]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[257]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[172]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[258]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((wrapFactory == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[173]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[174]++;}
        this.wrapFactory = wrapFactory;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[259]++;
    }

    /**
     * Return the current WrapFactory, or null if none is defined.
     * @see WrapFactory
     * @since 1.5 Release 4
     */
    public final WrapFactory getWrapFactory()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[260]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((wrapFactory == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[175]++;
            wrapFactory = new WrapFactory();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[261]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[176]++;}
        return wrapFactory;
    }

    /**
     * Return the current debugger.
     * @return the debugger, or null if none is attached.
     */
    public final Debugger getDebugger()
    {
        return debugger;
    }

    /**
     * Return the debugger context data associated with current context.
     * @return the debugger data, or null if debugger is not attached
     */
    public final Object getDebuggerContextData()
    {
        return debuggerData;
    }

    /**
     * Set the associated debugger.
     * @param debugger the debugger to be used on callbacks from
     * the engine.
     * @param contextData arbitrary object that debugger can use to store
     *        per Context data.
     */
    public final void setDebugger(Debugger debugger, Object contextData)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[262]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[177]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[263]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[178]++;}
        this.debugger = debugger;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[264]++;
        debuggerData = contextData;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[265]++;
    }

    /**
     * Return DebuggableScript instance if any associated with the script.
     * If callable supports DebuggableScript implementation, the method
     * returns it. Otherwise null is returned.
     */
    public static DebuggableScript getDebuggableView(Script script)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[266]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((script instanceof NativeFunction) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[179]++;
            return ((NativeFunction)script).getDebuggableView();

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[180]++;}
        return null;
    }

    /**
     * Controls certain aspects of script semantics.
     * Should be overwritten to alter default behavior.
     * <p>
     * The default implementation calls
     * {@link ContextFactory#hasFeature(Context cx, int featureIndex)}
     * that allows to customize Context behavior without introducing
     * Context subclasses.  {@link ContextFactory} documentation gives
     * an example of hasFeature implementation.
     *
     * @param featureIndex feature index to check
     * @return true if the <code>featureIndex</code> feature is turned on
     * @see #FEATURE_NON_ECMA_GET_YEAR
     * @see #FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME
     * @see #FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER
     * @see #FEATURE_TO_STRING_AS_SOURCE
     * @see #FEATURE_PARENT_PROTO_PROPRTIES
     * @see #FEATURE_E4X
     * @see #FEATURE_DYNAMIC_SCOPE
     * @see #FEATURE_STRICT_VARS
     * @see #FEATURE_STRICT_EVAL
     * @see #FEATURE_LOCATION_INFORMATION_IN_ERROR
     * @see #FEATURE_STRICT_MODE
     * @see #FEATURE_WARNING_AS_ERROR
     * @see #FEATURE_ENHANCED_JAVA_ACCESS
     */
    public boolean hasFeature(int featureIndex)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[267]++;
        ContextFactory f = getFactory();
        return f.hasFeature(this, featureIndex);
    }

    /**
     * Returns an object which specifies an E4X implementation to use within
     * this <code>Context</code>. Note that the XMLLib.Factory interface should
     * be considered experimental.
     *
     * The default implementation uses the implementation provided by this
     * <code>Context</code>'s {@link ContextFactory}.
     *
     * @return An XMLLib.Factory. Should not return <code>null</code> if
     *         {@link #FEATURE_E4X} is enabled. See {@link #hasFeature}.
     */
    public XMLLib.Factory getE4xImplementationFactory() {
        return getFactory().getE4xImplementationFactory();
    }

    /**
     * Get threshold of executed instructions counter that triggers call to
     * <code>observeInstructionCount()</code>.
     * When the threshold is zero, instruction counting is disabled,
     * otherwise each time the run-time executes at least the threshold value
     * of script instructions, <code>observeInstructionCount()</code> will
     * be called.
     */
    public final int getInstructionObserverThreshold()
    {
        return instructionThreshold;
    }

    /**
     * Set threshold of executed instructions counter that triggers call to
     * <code>observeInstructionCount()</code>.
     * When the threshold is zero, instruction counting is disabled,
     * otherwise each time the run-time executes at least the threshold value
     * of script instructions, <code>observeInstructionCount()</code> will
     * be called.<p/>
     * Note that the meaning of "instruction" is not guaranteed to be
     * consistent between compiled and interpretive modes: executing a given
     * script or function in the different modes will result in different
     * instruction counts against the threshold.
     * {@link #setGenerateObserverCount} is called with true if
     * <code>threshold</code> is greater than zero, false otherwise.
     * @param threshold The instruction threshold
     */
    public final void setInstructionObserverThreshold(int threshold)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[268]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[181]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[269]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[182]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[270]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((threshold < 0) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[183]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[184]++;}
        instructionThreshold = threshold;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[271]++;
        setGenerateObserverCount(threshold > 0);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[272]++;
    }

    /**
     * Turn on or off generation of code with callbacks to
     * track the count of executed instructions.
     * Currently only affects JVM byte code generation: this slows down the
     * generated code, but code generated without the callbacks will not
     * be counted toward instruction thresholds. Rhino's interpretive
     * mode does instruction counting without inserting callbacks, so
     * there is no requirement to compile code differently.
     * @param generateObserverCount if true, generated code will contain
     * calls to accumulate an estimate of the instructions executed.
     */
    public void setGenerateObserverCount(boolean generateObserverCount) {
        this.generateObserverCount = generateObserverCount;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[273]++;
    }

    /**
     * Allow application to monitor counter of executed script instructions
     * in Context subclasses.
     * Run-time calls this when instruction counting is enabled and the counter
     * reaches limit set by <code>setInstructionObserverThreshold()</code>.
     * The method is useful to observe long running scripts and if necessary
     * to terminate them.
     * <p>
     * The default implementation calls
     * {@link ContextFactory#observeInstructionCount(Context cx,
     *                                               int instructionCount)}
     * that allows to customize Context behavior without introducing
     * Context subclasses.
     *
     * @param instructionCount amount of script instruction executed since
     * last call to <code>observeInstructionCount</code>
     * @throws Error to terminate the script
     * @see #setOptimizationLevel(int)
     */
    protected void observeInstructionCount(int instructionCount)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[274]++;
        ContextFactory f = getFactory();
        f.observeInstructionCount(this, instructionCount);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[275]++;
    }

    /**
     * Create class loader for generated classes.
     * The method calls {@link ContextFactory#createClassLoader(ClassLoader)}
     * using the result of {@link #getFactory()}.
     */
    public GeneratedClassLoader createClassLoader(ClassLoader parent)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[276]++;
        ContextFactory f = getFactory();
        return f.createClassLoader(parent);
    }

    public final ClassLoader getApplicationClassLoader()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[277]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((applicationClassLoader == null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[185]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[278]++;
            ContextFactory f = getFactory();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[279]++;
            ClassLoader loader = f.getApplicationClassLoader();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[280]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((loader == null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[187]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[281]++;
                ClassLoader threadLoader
                    = VMBridge.instance.getCurrentThreadClassLoader();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[282]++;
int CodeCoverConditionCoverageHelper_C86;
                if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((threadLoader != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((Kit.testIfCanLoadRhinoClasses(threadLoader)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false))
                {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[189]++;
                    // Thread.getContextClassLoader is not cached since
                    // its caching prevents it from GC which may lead to
                    // a memory leak and hides updates to
                    // Thread.getContextClassLoader
                    return threadLoader;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[190]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[283]++;
                // Thread.getContextClassLoader can not load Rhino classes,
                // try to use the loader of ContextFactory or Context
                // subclasses.
                Class<?> fClass = f.getClass();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[284]++;
int CodeCoverConditionCoverageHelper_C87;
                if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((fClass != ScriptRuntime.ContextFactoryClass) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[191]++;
                    loader = fClass.getClassLoader();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[285]++;

                } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[192]++;
                    loader = getClass().getClassLoader();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[286]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[188]++;}
            applicationClassLoader = loader;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[287]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[186]++;}
        return applicationClassLoader;
    }

    public final void setApplicationClassLoader(ClassLoader loader)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[288]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[193]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[289]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[194]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[290]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((loader == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[195]++;
            // restore default behaviour
            applicationClassLoader = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[291]++;
            return;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[196]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[292]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((Kit.testIfCanLoadRhinoClasses(loader)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[197]++;
            throw new IllegalArgumentException(
                "Loader can not resolve Rhino classes");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[198]++;}
        applicationClassLoader = loader;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[293]++;
    }

    /********** end of API **********/

    /**
     * Internal method that reports an error for missing calls to
     * enter().
     */
    static Context getContext()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[294]++;
        Context cx = getCurrentContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[295]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[199]++;
            throw new RuntimeException(
                "No Context associated with current Thread");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[200]++;}
        return cx;
    }

    private Object compileImpl(Scriptable scope,
                               Reader sourceReader, String sourceString,
                               String sourceName, int lineno,
                               Object securityDomain, boolean returnFunction,
                               Evaluator compiler,
                               ErrorReporter compilationErrorReporter)
        throws IOException
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[296]++;
int CodeCoverConditionCoverageHelper_C92;
        if((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[201]++;
            sourceName = "unnamed script";
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[297]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[202]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[298]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((securityDomain != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((getSecurityController() == null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[203]++;
            throw new IllegalArgumentException(
                "securityDomain should be null if setSecurityController() was never called");

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[204]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[299]++;
int CodeCoverConditionCoverageHelper_C94;

        // One of sourceReader or sourceString has to be null
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((sourceReader == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 ^ 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((sourceString == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[205]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[300]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[206]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[301]++;
int CodeCoverConditionCoverageHelper_C95;
        // scope should be given if and only if compiling function
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 ^ 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[207]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[302]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[208]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[303]++;

        CompilerEnvirons compilerEnv = new CompilerEnvirons();
        compilerEnv.initFromContext(this);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[304]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[305]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((compilationErrorReporter == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[209]++;
            compilationErrorReporter = compilerEnv.getErrorReporter();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[306]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[210]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[307]++;
int CodeCoverConditionCoverageHelper_C97;

        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((debugger != null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[211]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[308]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((sourceReader != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[213]++;
                sourceString = Kit.readReader(sourceReader);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[309]++;
                sourceReader = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[310]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[214]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[212]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[311]++;

        Parser p = new Parser(compilerEnv, compilationErrorReporter);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[312]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[215]++;
            p.calledByCompileFunction = true;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[313]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[216]++;}
        AstRoot ast;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[314]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((sourceString != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[217]++;
            ast = p.parse(sourceString, sourceName, lineno);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[315]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[218]++;
            ast = p.parse(sourceReader, sourceName, lineno);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[316]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[317]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[219]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[318]++;
int CodeCoverConditionCoverageHelper_C102;
            // parser no longer adds function to script node
            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((ast.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((ast.getFirstChild().getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[221]++;
                // XXX: the check just looks for the first child
                // and allows for more nodes after it for compatibility
                // with sources like function() {};;;
                throw new IllegalArgumentException(
                    "compileFunction only accepts source with single JS function: "+sourceString);

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[222]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[220]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[319]++;

        IRFactory irf = new IRFactory(compilerEnv, compilationErrorReporter);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[320]++;
        ScriptNode tree = irf.transformTree(ast);

        // discard everything but the IR tree
        p = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[321]++;
        ast = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[322]++;
        irf = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[323]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[324]++;
int CodeCoverConditionCoverageHelper_C103;

        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((compiler == null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[223]++;
            compiler = createCompiler();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[325]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[224]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[326]++;

        Object bytecode = compiler.compile(compilerEnv,
                                           tree, tree.getEncodedSource(),
                                           returnFunction);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[327]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((debugger != null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[225]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[328]++;
int CodeCoverConditionCoverageHelper_C105;
            if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((sourceString == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[227]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[329]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[228]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[330]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((bytecode instanceof DebuggableScript) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[229]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[331]++;
                DebuggableScript dscript = (DebuggableScript)bytecode;
                notifyDebugger_r(this, dscript, sourceString);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[332]++;

            } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[230]++;
                throw new RuntimeException("NOT SUPPORTED");
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[226]++;}

        Object result;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[333]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[231]++;
            result = compiler.createFunctionObject(this, scope, bytecode, securityDomain);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[334]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[232]++;
            result = compiler.createScriptObject(bytecode, securityDomain);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[335]++;
        }

        return result;
    }

    private static void notifyDebugger_r(Context cx, DebuggableScript dscript,
                                         String debugSource)
    {
        cx.debugger.handleCompilationDone(cx, dscript, debugSource);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[336]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[337]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[7]++;


int CodeCoverConditionCoverageHelper_C108;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((i != dscript.getFunctionCount()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[9]++;
}
            notifyDebugger_r(cx, dscript.getFunction(i), debugSource);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[338]++;
        }
    }

    private static Class<?> codegenClass = Kit.classOrNull(
                             "org.mozilla.javascript.optimizer.Codegen");
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[339]++;
  }
    private static Class<?> interpreterClass = Kit.classOrNull(
                             "org.mozilla.javascript.Interpreter");
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[340]++;
  }

    private Evaluator createCompiler()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[341]++;
        Evaluator result = null;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[342]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((optimizationLevel >= 0) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((codegenClass != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[233]++;
            result = (Evaluator)Kit.newInstanceOrNull(codegenClass);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[343]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[234]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[344]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[235]++;
            result = createInterpreter();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[345]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[236]++;}
        return result;
    }

    static Evaluator createInterpreter()
    {
        return (Evaluator)Kit.newInstanceOrNull(interpreterClass);
    }

    static String getSourcePositionFromStack(int[] linep)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[346]++;
        Context cx = getCurrentContext();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[347]++;
int CodeCoverConditionCoverageHelper_C111;
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[237]++;
            return null;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[238]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[348]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((cx.lastInterpreterFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[239]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[349]++;
            Evaluator evaluator = createInterpreter();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[350]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((evaluator != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[241]++;
                return evaluator.getSourcePositionFromStack(cx, linep);
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[242]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[240]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[351]++;
        /**
         * A bit of a hack, but the only way to get filename and line
         * number from an enclosing frame.
         */
        CharArrayWriter writer = new CharArrayWriter();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[352]++;
        RuntimeException re = new RuntimeException();
        re.printStackTrace(new PrintWriter(writer));
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[353]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[354]++;
        String s = writer.toString();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[355]++;
        int open = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[356]++;
        int close = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[357]++;
        int colon = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[358]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[10]++;


int CodeCoverConditionCoverageHelper_C114;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((i < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.loops[12]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[359]++;
            char c = s.charAt(i);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[360]++;
int CodeCoverConditionCoverageHelper_C115;
            if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((c == ':') && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[243]++;
                colon = i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[361]++;
}
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[244]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[362]++;
int CodeCoverConditionCoverageHelper_C116; if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((c == '(') && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[245]++;
                open = i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[363]++;
}
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[246]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[364]++;
int CodeCoverConditionCoverageHelper_C117; if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((c == ')') && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[247]++;
                close = i;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[365]++;
}
            else {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[248]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[366]++;
int CodeCoverConditionCoverageHelper_C118; if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2048)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (512)) == 0 || true) &&
 ((open != -1) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (128)) == 0 || true) &&
 ((close != -1) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (32)) == 0 || true) &&
 ((colon != -1) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((open < colon) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((colon < close) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 6) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 6) && false))
            {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[249]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[367]++;
                String fileStr = s.substring(open + 1, colon);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[368]++;
int CodeCoverConditionCoverageHelper_C119;
                if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((fileStr.endsWith(".java")) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[251]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[369]++;
                    String lineStr = s.substring(colon + 1, close);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[370]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
                    try {
CodeCoverTryBranchHelper_Try7 = true;
                        linep[0] = Integer.parseInt(lineStr);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[371]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[372]++;
int CodeCoverConditionCoverageHelper_C120;
                        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((linep[0] < 0) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[254]++;
                            linep[0] = 0;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[373]++;

                        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[255]++;}
                        return fileStr;
                    }
                    catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[256]++;
                        // fall through
                    } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[253]++;
}
  }

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[252]++;}
                open = close = colon = -1;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[374]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[250]++;}
}
}
}
        }

        return null;
    }

    RegExpProxy getRegExpProxy()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[375]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((regExpProxy == null) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[257]++;
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[376]++;
            Class<?> cl = Kit.classOrNull(
                          "org.mozilla.javascript.regexp.RegExpImpl");
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[377]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((cl != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[259]++;
                regExpProxy = (RegExpProxy)Kit.newInstanceOrNull(cl);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[378]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[260]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[258]++;}
        return regExpProxy;
    }

    final boolean isVersionECMA1()
    {
        return version == VERSION_DEFAULT || version >= VERSION_1_3;
    }

// The method must NOT be public or protected
    SecurityController getSecurityController()
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[379]++;
        SecurityController global = SecurityController.global();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[380]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((global != null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[261]++;
            return global;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[262]++;}
        return securityController;
    }

    public final boolean isGeneratingDebugChanged()
    {
        return generatingDebugChanged;
    }

    /**
     * Add a name to the list of names forcing the creation of real
     * activation objects for functions.
     *
     * @param name the name of the object to add to the list
     */
    public void addActivationName(String name)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[381]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[263]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[382]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[264]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[383]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((activationNames == null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[265]++;
            activationNames = new HashSet<String>();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[384]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[266]++;}
        activationNames.add(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[385]++;
    }

    /**
     * Check whether the name is in the list of names of objects
     * forcing the creation of activation objects.
     *
     * @param name the name of the object to test
     *
     * @return true if an function activation object is needed.
     */
    public final boolean isActivationNeeded(String name)
    {
        return activationNames != null && activationNames.contains(name);
    }

    /**
     * Remove a name from the list of names forcing the creation of real
     * activation objects for functions.
     *
     * @param name the name of the object to remove from the list
     */
    public void removeActivationName(String name)
    {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[386]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[267]++; onSealedMutation();
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[387]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[268]++;}
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[388]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((activationNames != null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[269]++;
            activationNames.remove(name);
CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[389]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.branches[270]++;}
    }

    private static String implementationVersion;

    private final ContextFactory factory;
    private boolean sealed;
    private Object sealKey;

    Scriptable topCallScope;
    boolean isContinuationsTopCall;
    NativeCall currentActivationCall;
    XMLLib cachedXMLLib;

    // for Objects, Arrays to tag themselves as being printed out,
    // so they don't print themselves out recursively.
    // Use ObjToIntMap instead of java.util.HashSet for JDK 1.1 compatibility
    ObjToIntMap iterating;

    Object interpreterSecurityDomain;

    int version;

    private SecurityController securityController;
    private boolean hasClassShutter;
    private ClassShutter classShutter;
    private ErrorReporter errorReporter;
    RegExpProxy regExpProxy;
    private Locale locale;
    private boolean generatingDebug;
    private boolean generatingDebugChanged;
    private boolean generatingSource=true;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[390]++;
  }
    boolean useDynamicScope;
    private int optimizationLevel;
    private int maximumInterpreterStackDepth;
    private WrapFactory wrapFactory;
    Debugger debugger;
    private Object debuggerData;
    private int enterCount;
    private Object propertyListeners;
    private Map<Object,Object> threadLocalMap;
    private ClassLoader applicationClassLoader;

    /**
     * This is the list of names of objects forcing the creation of
     * function activation records.
     */
    Set<String> activationNames;

    // For the interpreter to store the last frame for error reports etc.
    Object lastInterpreterFrame;

    // For the interpreter to store information about previous invocations
    // interpreter invocations
    ObjArray previousInterpreterInvocations;

    // For instruction counting (interpreter only)
    int instructionCount;
    int instructionThreshold;

    // It can be used to return the second index-like result from function
    int scratchIndex;

    // It can be used to return the second uint32 result from function
    long scratchUint32;

    // It can be used to return the second Scriptable result from function
    Scriptable scratchScriptable;

    // Generate an observer count on compiled code
    public boolean generateObserverCount = false;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx.statements[391]++;
  }
}

class CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx ());
  }
    public static long[] statements = new long[392];
    public static long[] branches = new long[271];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[128];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Context.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,3,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$di175yxae5e37vt04k9mbetispm5e4bgwx () {
    super("org.mozilla.javascript.RHINO-SRC-Context.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 391; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 270; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 127; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Context.java");
      for (int i = 1; i <= 391; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 270; i++) {
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

