/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;
import java.lang.reflect.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.v8dtoa.FastDtoa;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xml.XMLLib;

/**
 * This is the class that implements the runtime.
 *
 */

public class ScriptRuntime {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.ping();
  }


    /**
     * No instances should be created.
     */
    protected ScriptRuntime() {
    }


    /**
     * Returns representation of the [[ThrowTypeError]] object.
     * See ECMA 5 spec, 13.2.3
     */
    public static BaseFunction typeErrorThrower() {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((THROW_TYPE_ERROR == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[1]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[2]++;
        BaseFunction thrower = new BaseFunction() {
          static final long serialVersionUID = -5891740962154902286L;

          @Override
          public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
            throw typeError0("msg.op.not.allowed");
          }
          @Override
          public int getLength() {
            return 0;
          }
        };
        thrower.preventExtensions();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[3]++;
        THROW_TYPE_ERROR = thrower;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[4]++;

      } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[2]++;}
      return THROW_TYPE_ERROR;
    }
    private static BaseFunction THROW_TYPE_ERROR = null;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[5]++;
  }

    static class NoSuchMethodShim implements Callable {
        String methodName;
        Callable noSuchMethodMethod;

        NoSuchMethodShim(Callable noSuchMethodMethod, String methodName)
        {
            this.noSuchMethodMethod = noSuchMethodMethod;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[6]++;
            this.methodName = methodName;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[7]++;
        }
        /**
         * Perform the call.
         *
         * @param cx the current Context for this thread
         * @param scope the scope to use to resolve properties.
         * @param thisObj the JavaScript <code>this</code> object
         * @param args the array of arguments
         * @return the result of the call
         */
        public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                           Object[] args)
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[8]++;
            Object[] nestedArgs = new Object[2];

            nestedArgs[0] = methodName;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[9]++;
            nestedArgs[1] = newArrayLiteral(args, null, cx, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[10]++;
            return noSuchMethodMethod.call(cx, scope, thisObj, nestedArgs);
        }

    }
    /*
     * There's such a huge space (and some time) waste for the Foo.class
     * syntax: the compiler sticks in a test of a static field in the
     * enclosing class for null and the code for creating the class value.
     * It has to do this since the reference has to get pushed off until
     * execution time (i.e. can't force an early load), but for the
     * 'standard' classes - especially those in java.lang, we can trust
     * that they won't cause problems by being loaded early.
     */

    public final static Class<?>
        BooleanClass      = Kit.classOrNull("java.lang.Boolean"),
        ByteClass         = Kit.classOrNull("java.lang.Byte"),
        CharacterClass    = Kit.classOrNull("java.lang.Character"),
        ClassClass        = Kit.classOrNull("java.lang.Class"),
        DoubleClass       = Kit.classOrNull("java.lang.Double"),
        FloatClass        = Kit.classOrNull("java.lang.Float"),
        IntegerClass      = Kit.classOrNull("java.lang.Integer"),
        LongClass         = Kit.classOrNull("java.lang.Long"),
        NumberClass       = Kit.classOrNull("java.lang.Number"),
        ObjectClass       = Kit.classOrNull("java.lang.Object"),
        ShortClass        = Kit.classOrNull("java.lang.Short"),
        StringClass       = Kit.classOrNull("java.lang.String"),
        DateClass         = Kit.classOrNull("java.util.Date");
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[11]++;
  }

    public final static Class<?>
        ContextClass
            = Kit.classOrNull("org.mozilla.javascript.Context"),
        ContextFactoryClass
            = Kit.classOrNull("org.mozilla.javascript.ContextFactory"),
        FunctionClass
            = Kit.classOrNull("org.mozilla.javascript.Function"),
        ScriptableObjectClass
            = Kit.classOrNull("org.mozilla.javascript.ScriptableObject");
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[12]++;
  }
    public static final Class<Scriptable> ScriptableClass =
        Scriptable.class;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[13]++;
  }

    // Locale object used to request locale-neutral operations.
    public static Locale ROOT_LOCALE = new Locale("");
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[14]++;
  }

    private static final Object LIBRARY_SCOPE_KEY = "LIBRARY_SCOPE";
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[15]++;
  }

    public static boolean isRhinoRuntimeType(Class<?> cl)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cl.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[3]++;
            return (cl != Character.TYPE);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[4]++;
            return (cl == StringClass || cl == BooleanClass
                    || NumberClass.isAssignableFrom(cl)
                    || ScriptableClass.isAssignableFrom(cl));
        }
    }

    public static ScriptableObject initStandardObjects(Context cx,
                                                       ScriptableObject scope,
                                                       boolean sealed)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[5]++;
            scope = new NativeObject();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[18]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[6]++;}
        scope.associateValue(LIBRARY_SCOPE_KEY, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[19]++;
        (new ClassCache()).associate(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[20]++;

        BaseFunction.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[21]++;
        NativeObject.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[22]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[23]++;

        Scriptable objectProto = ScriptableObject.getObjectPrototype(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[24]++;

        // Function.prototype.__proto__ should be Object.prototype
        Scriptable functionProto = ScriptableObject.getClassPrototype(scope, "Function");
        functionProto.setPrototype(objectProto);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[25]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;

        // Set the prototype of the object passed in if need be
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((scope.getPrototype() == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[7]++;
            scope.setPrototype(objectProto);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[27]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[8]++;}

        // must precede NativeGlobal since it's needed therein
        NativeError.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[28]++;
        NativeGlobal.init(cx, scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[29]++;

        NativeArray.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[30]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cx.getOptimizationLevel() > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[9]++;
            // When optimizing, attempt to fulfill all requests for new Array(N)
            // with a higher threshold before switching to a sparse
            // representation
            NativeArray.setMaximumInitialCapacity(200000);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[32]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[10]++;}
        NativeString.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[33]++;
        NativeBoolean.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[34]++;
        NativeNumber.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[35]++;
        NativeDate.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[36]++;
        NativeMath.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[37]++;
        NativeJSON.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[38]++;

        NativeWith.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[39]++;
        NativeCall.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[40]++;
        NativeScript.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[41]++;

        NativeIterator.init(scope, sealed);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[42]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[43]++; // Also initializes NativeGenerator

        boolean withXml = cx.hasFeature(Context.FEATURE_E4X) &&
                          cx.getE4xImplementationFactory() != null;

        // define lazy-loaded properties using their class name
        new LazilyLoadedCtor(scope, "RegExp",
                "org.mozilla.javascript.regexp.NativeRegExp", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[44]++;
        new LazilyLoadedCtor(scope, "Packages",
                "org.mozilla.javascript.NativeJavaTopPackage", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[45]++;
        new LazilyLoadedCtor(scope, "getClass",
                "org.mozilla.javascript.NativeJavaTopPackage", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[46]++;
        new LazilyLoadedCtor(scope, "JavaAdapter",
                "org.mozilla.javascript.JavaAdapter", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[47]++;
        new LazilyLoadedCtor(scope, "JavaImporter",
                "org.mozilla.javascript.ImporterTopLevel", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[48]++;
        new LazilyLoadedCtor(scope, "Continuation",
                "org.mozilla.javascript.NativeContinuation", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[49]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[50]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[1]++;



        for (String packageName : getTopPackageNames()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[1]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[2]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[3]++;
}
            new LazilyLoadedCtor(scope, packageName,
                    "org.mozilla.javascript.NativeJavaTopPackage", sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[51]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[52]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((withXml) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[11]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[53]++;
            String xmlImpl = cx.getE4xImplementationFactory().getImplementationClassName();
            new LazilyLoadedCtor(scope, "XML", xmlImpl, sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[54]++;
            new LazilyLoadedCtor(scope, "XMLList", xmlImpl, sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[55]++;
            new LazilyLoadedCtor(scope, "Namespace", xmlImpl, sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[56]++;
            new LazilyLoadedCtor(scope, "QName", xmlImpl, sealed, true);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[57]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[12]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[58]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((scope instanceof TopLevel) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[13]++;
            ((TopLevel)scope).cacheBuiltins();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[59]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[14]++;}

        return scope;
    }

    static String[] getTopPackageNames() {
        // Include "android" top package if running on Android
        return "Dalvik".equals(System.getProperty("java.vm.name")) ?
            new String[] { "java", "javax", "org", "com", "edu", "net", "android" } :
            new String[] { "java", "javax", "org", "com", "edu", "net" };
    }

    public static ScriptableObject getLibraryScopeOrNull(Scriptable scope)
    {
        ScriptableObject libScope;
        libScope = (ScriptableObject)ScriptableObject.
                       getTopScopeValue(scope, LIBRARY_SCOPE_KEY);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[60]++;
        return libScope;
    }

    // It is public so NativeRegExp can access it.
    public static boolean isJSLineTerminator(int c)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[61]++;
int CodeCoverConditionCoverageHelper_C8;
        // Optimization for faster check for eol character:
        // they do not have 0xDFD0 bits set
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 (((c & 0xDFD0) != 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[16]++;}
        return c == '\n' || c == '\r' || c == 0x2028 || c == 0x2029;
    }

    public static boolean isJSWhitespaceOrLineTerminator(int c) {
      return (isStrWhiteSpaceChar(c) || isJSLineTerminator(c));
    }

    /**
     * Indicates if the character is a Str whitespace char according to ECMA spec:
     * StrWhiteSpaceChar :::
      <TAB>
      <SP>
      <NBSP>
      <FF>
      <VT>
      <CR>
      <LF>
      <LS>
      <PS>
      <USP>
      <BOM>
     */
    static boolean isStrWhiteSpaceChar(int c)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[62]++;
    	switch (c) {
    		case ' ':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[17]++; // <SP>
    		case '\n':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[18]++; // <LF>
    		case '\r':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[19]++; // <CR>
    		case '\t':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[20]++; // <TAB>
    		case '\u00A0':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[21]++; // <NBSP>
    		case '\u000C':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[22]++; // <FF>
    		case '\u000B':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[23]++; // <VT>
    		case '\u2028':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[24]++; // <LS>
    		case '\u2029':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[25]++; // <PS>
        case '\uFEFF':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[26]++; // <BOM>
    			return true;
    		default:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[27]++;
    			return Character.getType(c) == Character.SPACE_SEPARATOR;
    	}
    }

    public static Boolean wrapBoolean(boolean b)
    {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Integer wrapInt(int i)
    {
        return Integer.valueOf(i);
    }

    public static Number wrapNumber(double x)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[63]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((x != x) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[28]++;
            return ScriptRuntime.NaNobj;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[29]++;}
        return new Double(x);
    }

    /**
     * Convert the value to a boolean.
     *
     * See ECMA 9.2.
     */
    public static boolean toBoolean(Object val)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[64]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[4]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[4]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[5]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[6]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[65]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((val instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[30]++;
                return ((Boolean) val).booleanValue();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[31]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((val == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[32]++;
                return false;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[33]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[67]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((val instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[34]++;
                return ((CharSequence) val).length() != 0;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[35]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[68]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((val instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[36]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[69]++;
                double d = ((Number) val).doubleValue();
                return (d == d && d != 0.0);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[37]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[70]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[38]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[71]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((val instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((((ScriptableObject) val).avoidObjectDetection()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false))
                {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[40]++;
                    return false;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[41]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[72]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((Context.getContext().isVersionECMA1()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[42]++;
                    // pure ECMA
                    return true;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[43]++;}
                // ECMA extension
                val = ((Scriptable) val).getDefaultValue(BooleanClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[73]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[44]++;
                    throw errorWithClassName("msg.primitive.expected", val);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[45]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[75]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[39]++;}
            warnAboutNonJSObject(val);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[76]++;
            return true;
        }
    }

    /**
     * Convert the value to a number.
     *
     * See ECMA 9.3.
     */
    public static double toNumber(Object val)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[77]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[7]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[7]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[8]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[9]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[78]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((val instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[46]++;
                return ((Number) val).doubleValue();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[47]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[48]++;
                return +0.0;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[49]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[80]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((val == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[50]++;
                return NaN;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[51]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[81]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((val instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[52]++;
                return toNumber((String) val);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[53]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[82]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((val instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[54]++;
                return toNumber(val.toString());
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[55]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[83]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((val instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[56]++;
                return ((Boolean) val).booleanValue() ? 1 : +0.0;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[57]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[84]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[58]++;
                val = ((Scriptable) val).getDefaultValue(NumberClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[85]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[86]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[60]++;
                    throw errorWithClassName("msg.primitive.expected", val);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[61]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[87]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[59]++;}
            warnAboutNonJSObject(val);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[88]++;
            return NaN;
        }
    }

    public static double toNumber(Object[] args, int index) {
        return (index < args.length) ? toNumber(args[index]) : NaN;
    }

    // Can not use Double.NaN defined as 0.0d / 0.0 as under the Microsoft VM,
    // versions 2.01 and 3.0P1, that causes some uses (returns at least) of
    // Double.NaN to be converted to 1.0.
    // So we use ScriptRuntime.NaN instead of Double.NaN.
    public static final double
        NaN = Double.longBitsToDouble(0x7ff8000000000000L);
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[89]++;
  }

    // A similar problem exists for negative zero.
    public static final double
        negativeZero = Double.longBitsToDouble(0x8000000000000000L);
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[90]++;
  }

    public static final Double NaNobj = new Double(NaN);
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[91]++;
  }

    /*
     * Helper function for toNumber, parseInt, and TokenStream.getToken.
     */
    static double stringToNumber(String s, int start, int radix) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[92]++;
        char digitMax = '9';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[93]++;
        char lowerCaseBound = 'a';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[94]++;
        char upperCaseBound = 'A';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[95]++;
        int len = s.length();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[96]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((radix < 10) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[62]++;
            digitMax = (char) ('0' + radix - 1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[97]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[63]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[98]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((radix > 10) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[64]++;
            lowerCaseBound = (char) ('a' + radix - 10);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[99]++;
            upperCaseBound = (char) ('A' + radix - 10);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[100]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[65]++;}
        int end;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[101]++;
        double sum = 0.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[102]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[10]++;


int CodeCoverConditionCoverageHelper_C30;
        for (end=start;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((end < len) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); end++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[10]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[11]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[12]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[103]++;
            char c = s.charAt(end);
            int newDigit;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[104]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c <= digitMax) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[66]++;
                newDigit = c - '0';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[105]++;
}
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[67]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[106]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c < lowerCaseBound) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[68]++;
                newDigit = c - 'a' + 10;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[107]++;
}
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[69]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[108]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((c < upperCaseBound) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[70]++;
                newDigit = c - 'A' + 10;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[109]++;
}
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[71]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[110]++;
                break;
}
}
}
            sum = sum*radix + newDigit;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[111]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[112]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((start == end) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[72]++;
            return NaN;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[73]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[113]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((sum >= 9007199254740992.0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[74]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[114]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((radix == 10) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[76]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[115]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                /* If we're accumulating a decimal number and the number
                 * is >= 2^53, then the result from the repeated multiply-add
                 * above may be inaccurate.  Call Java to get the correct
                 * answer.
                 */
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    return Double.parseDouble(s.substring(start, end));
                } catch (NumberFormatException nfe) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[79]++;
                    return NaN;
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[78]++;
}
  }

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[77]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[116]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (512)) == 0 || true) &&
 ((radix == 2) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (128)) == 0 || true) &&
 ((radix == 4) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((radix == 8) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((radix == 16) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((radix == 32) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 5) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 5) && false))
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[80]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[117]++;
                /* The number may also be inaccurate for one of these bases.
                 * This happens if the addition in value*radix + digit causes
                 * a round-down to an even least significant mantissa bit
                 * when the first dropped bit is a one.  If any of the
                 * following digits in the number (which haven't been added
                 * in yet) are nonzero then the correct action would have
                 * been to round up instead of down.  An example of this
                 * occurs when reading the number 0x1000000000000081, which
                 * rounds to 0x1000000000000000 instead of 0x1000000000000100.
                 */
                int bitShiftInChar = 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[118]++;
                int digit = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[119]++;

                final int SKIP_LEADING_ZEROS = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[120]++;
                final int FIRST_EXACT_53_BITS = 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[121]++;
                final int AFTER_BIT_53         = 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[122]++;
                final int ZEROS_AFTER_54 = 3;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[123]++;
                final int MIXED_AFTER_54 = 4;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[124]++;

                int state = SKIP_LEADING_ZEROS;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[125]++;
                int exactBitsLimit = 53;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[126]++;
                double factor = 0.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[127]++;
                boolean bit53 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[128]++;
                // bit54 is the 54th bit (the first dropped from the mantissa)
                boolean bit54 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[129]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[13]++;



                for (;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[13]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[14]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[15]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[130]++;
int CodeCoverConditionCoverageHelper_C39;
                    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((bitShiftInChar == 1) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[82]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[131]++;
int CodeCoverConditionCoverageHelper_C40;
                        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((start == end) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[84]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[132]++;
                            break;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[85]++;}
                        digit = s.charAt(start++);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[133]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[134]++;
int CodeCoverConditionCoverageHelper_C41;
                        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 (('0' <= digit) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((digit <= '9') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[86]++;
                            digit -= '0';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[135]++;
}
                        else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[87]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[136]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 (('a' <= digit) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((digit <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[88]++;
                            digit -= 'a' - 10;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[137]++;
}
                        else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[89]++;
                            digit -= 'A' - 10;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[138]++;
}
}
                        bitShiftInChar = radix;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[139]++;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[83]++;}
                    bitShiftInChar >>= 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[140]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[141]++;
                    boolean bit = (digit & bitShiftInChar) != 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[142]++;

                    switch (state) {
                      case SKIP_LEADING_ZEROS:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[90]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[143]++;
int CodeCoverConditionCoverageHelper_C43;
                          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((bit) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[91]++;
                            --exactBitsLimit;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[144]++;
                            sum = 1.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[145]++;
                            state = FIRST_EXACT_53_BITS;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[146]++;

                        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[92]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[147]++;
                        break;
                      case FIRST_EXACT_53_BITS:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[93]++;
                           sum *= 2.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[148]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[149]++;
int CodeCoverConditionCoverageHelper_C44;
                        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((bit) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[94]++;
                            sum += 1.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[150]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[95]++;}
                        --exactBitsLimit;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[151]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[152]++;
int CodeCoverConditionCoverageHelper_C45;
                        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((exactBitsLimit == 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[96]++;
                            bit53 = bit;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[153]++;
                            state = AFTER_BIT_53;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[154]++;

                        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[97]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[155]++;
                        break;
                      case AFTER_BIT_53:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[98]++;
                        bit54 = bit;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[156]++;
                        factor = 2.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[157]++;
                        state = ZEROS_AFTER_54;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[158]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[159]++;
                        break;
                      case ZEROS_AFTER_54:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[99]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[160]++;
int CodeCoverConditionCoverageHelper_C46;
                        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((bit) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[100]++;
                            state = MIXED_AFTER_54;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[161]++;

                        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[101]++;}
                        // fallthrough
                      case MIXED_AFTER_54:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[102]++;
                        factor *= 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[162]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[163]++;
                        break; default : CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[103]++;
                    }
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[164]++;
                switch (state) {
                  case SKIP_LEADING_ZEROS:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[104]++;
                    sum = 0.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[165]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[166]++;
                    break;
                  case FIRST_EXACT_53_BITS:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[105]++;
                  case AFTER_BIT_53:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[106]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[167]++;
                    // do nothing
                    break;
                  case ZEROS_AFTER_54:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[107]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[168]++;
int CodeCoverConditionCoverageHelper_C47;
                    // x1.1 -> x1 + 1 (round up)
                    // x0.1 -> x0 (round down)
                    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((bit54) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 & 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((bit53) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[108]++;
                        sum += 1.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[169]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[109]++;}
                    sum *= factor;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[170]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[171]++;
                    break;
                  case MIXED_AFTER_54:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[110]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[172]++;
int CodeCoverConditionCoverageHelper_C48;
                    // x.100...1.. -> x + 1 (round up)
                    // x.0anything -> x (round down)
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((bit54) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[111]++;
                        sum += 1.0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[173]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[112]++;}
                    sum *= factor;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[174]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[175]++;
                    break; default : CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[113]++;
                }

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[81]++;}
}

            /* We don't worry about inaccurate numbers for any other base. */
        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[75]++;}
        return sum;
    }


    /**
     * ToNumber applied to the String type
     *
     * See ECMA 9.3.1
     */
    public static double toNumber(String s) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[176]++;
        int len = s.length();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[177]++;
        int start = 0;
        char startChar;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[178]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[16]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[16]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[17]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[18]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[179]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((start == len) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[114]++;
                // Empty or contains only whitespace
                return +0.0;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[115]++;}
            startChar = s.charAt(start);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[180]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[181]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isStrWhiteSpaceChar(startChar)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[116]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[182]++;
                break;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[117]++;}
            start++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[183]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[184]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((startChar == '0') && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[118]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[185]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((start + 2 < len) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[120]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[186]++;
                int c1 = s.charAt(start + 1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[187]++;
int CodeCoverConditionCoverageHelper_C54;
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((c1 == 'x') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((c1 == 'X') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[122]++;
                    // A hexadecimal number
                    return stringToNumber(s, start + 2, 16);

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[123]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[121]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[119]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[188]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((startChar == '+') && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((startChar == '-') && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[124]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[189]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((start + 3 < len) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((s.charAt(start + 1) == '0') && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[126]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[190]++;
                int c2 = s.charAt(start + 2);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[191]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((c2 == 'x') && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((c2 == 'X') && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[128]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[192]++;
                    // A hexadecimal number with sign
                    double val = stringToNumber(s, start + 3, 16);
                    return startChar == '-' ? -val : val;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[129]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[127]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[125]++;}
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[193]++;

        int end = len - 1;
        char endChar;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[194]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[19]++;


        while (ScriptRuntime.isStrWhiteSpaceChar(endChar = s.charAt(end))) { 
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[19]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[20]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[21]++;
}
            end--;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[195]++;
  }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[196]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((endChar == 'y') && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[130]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[197]++;
int CodeCoverConditionCoverageHelper_C60;
            // check for "Infinity"
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((startChar == '+') && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((startChar == '-') && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[132]++;
                start++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[198]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[133]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[199]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((start + 7 == end) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((s.regionMatches(start, "Infinity", 0, 8)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[134]++;
                return startChar == '-'
                    ? Double.NEGATIVE_INFINITY
                    : Double.POSITIVE_INFINITY;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[135]++;}
            return NaN;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[131]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[200]++;
        // A non-hexadecimal, non-infinity number:
        // just try a normal floating point conversion
        String sub = s.substring(start, end+1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[201]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[22]++;


int CodeCoverConditionCoverageHelper_C62;
        // Quick test to check string contains only valid characters because
        // Double.parseDouble() can be slow and accept input we want to reject
        for (int i = sub.length() - 1;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[22]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[23]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[24]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[202]++;
            char c = sub.charAt(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[203]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C63 |= (8192)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4096)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2048)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1024)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C63 |= (512)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (128)) == 0 || true) &&
 ((c == 'e') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((c == 'E') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((c == '+') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 7) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 7) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[136]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[204]++;
                continue;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[137]++;}
            return NaN;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[205]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            return Double.parseDouble(sub);
        } catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[139]++;
            return NaN;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[138]++;
}
  }
    }

    /**
     * Helper function for builtin objects that use the varargs form.
     * ECMA function formal arguments are undefined if not supplied;
     * this function pads the argument array out to the expected
     * length, if necessary.
     */
    public static Object[] padArguments(Object[] args, int count) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[206]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((count < args.length) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[140]++;
            return args;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[141]++;}

        int i;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[207]++;
        Object[] result = new Object[count];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[208]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[25]++;


int CodeCoverConditionCoverageHelper_C65;
        for (i = 0;(((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((i < args.length) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[25]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[26]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[27]++;
}
            result[i] = args[i];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[209]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[210]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[28]++;


int CodeCoverConditionCoverageHelper_C66;

        for (;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[28]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[29]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[30]++;
}
            result[i] = Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[211]++;
        }

        return result;
    }

    public static String escapeString(String s)
    {
        return escapeString(s, '"');
    }

    /**
     * For escaping strings printed by object and array literals; not quite
     * the same as 'escape.'
     */
    public static String escapeString(String s, char escapeQuote)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[212]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((escapeQuote == '"') && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((escapeQuote == '\'') && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[142]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[213]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[143]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[214]++;
        StringBuffer sb = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[215]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[31]++;


int CodeCoverConditionCoverageHelper_C68;

        for(int i = 0, L = s.length();(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[31]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[32]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[33]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[216]++;
            int c = s.charAt(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[217]++;
int CodeCoverConditionCoverageHelper_C69;

            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (128)) == 0 || true) &&
 ((' ' <= c) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((c <= '~') && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((c != escapeQuote) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((c != '\\') && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 4) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 4) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[144]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[218]++;
int CodeCoverConditionCoverageHelper_C70;
                // an ordinary print character (like C isprint()) and not "
                // or \ .
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[146]++;
                    sb.append((char)c);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[219]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[147]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[220]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[145]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[221]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[148]++;
                sb = new StringBuffer(L + 3);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[222]++;
                sb.append(s);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[223]++;
                sb.setLength(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[224]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[149]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[225]++;

            int escape = -1;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[226]++;
            switch (c) {
                case '\b':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[150]++;  escape = 'b';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[227]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[228]++;  break;
                case '\f':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[151]++;  escape = 'f';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[229]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[230]++;  break;
                case '\n':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[152]++;  escape = 'n';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[231]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[232]++;  break;
                case '\r':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[153]++;  escape = 'r';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[233]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[234]++;  break;
                case '\t':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[154]++;  escape = 't';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[235]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[236]++;  break;
                case 0xb:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[155]++;   escape = 'v';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[237]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[238]++;  break; // Java lacks \v.
                case ' ':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[156]++;   escape = ' ';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[239]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[240]++;  break;
                case '\\':
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[157]++;  escape = '\\';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[241]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[242]++; break; default : CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[158]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[243]++;
int CodeCoverConditionCoverageHelper_C72;
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((escape >= 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[159]++;
                // an \escaped sort of character
                sb.append('\\');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[244]++;
                sb.append((char)escape);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[245]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[160]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[246]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((c == escapeQuote) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[161]++;
                sb.append('\\');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[247]++;
                sb.append(escapeQuote);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[248]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[162]++;
                int hexSize;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[249]++;
int CodeCoverConditionCoverageHelper_C74;
                if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((c < 256) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[163]++;
                    // 2-digit hex
                    sb.append("\\x");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[250]++;
                    hexSize = 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[251]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[164]++;
                    // Unicode.
                    sb.append("\\u");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[252]++;
                    hexSize = 4;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[253]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[254]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[34]++;


int CodeCoverConditionCoverageHelper_C75;
                // append hexadecimal form of c left-padded with 0
                for (int shift = (hexSize - 1) * 4;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((shift >= 0) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); shift -= 4) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[34]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[35]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[36]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[255]++;
                    int digit = 0xf & (c >> shift);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[256]++;
                    int hc = (digit < 10) ? '0' + digit : 'a' - 10 + digit;
                    sb.append((char)hc);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[257]++;
                }
            }
}
        }
        return (sb == null) ? s : sb.toString();
    }

    static boolean isValidIdentifierName(String s)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[258]++;
        int L = s.length();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[259]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((L == 0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[165]++;
            return false;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[166]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[260]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(s.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[167]++;
            return false;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[168]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[261]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[37]++;


int CodeCoverConditionCoverageHelper_C78;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[37]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[38]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[39]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[262]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(s.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[169]++;
                return false;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[170]++;}
        }
        return !TokenStream.isKeyword(s);
    }

    public static CharSequence toCharSequence(Object val) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[263]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((val instanceof NativeString) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[171]++;
            return ((NativeString)val).toCharSequence();

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[172]++;}
        return val instanceof CharSequence ? (CharSequence) val : toString(val);
    }

    /**
     * Convert the value to a string.
     *
     * See ECMA 9.8.
     */
    public static String toString(Object val) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[264]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[40]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[40]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[41]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[42]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[265]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[173]++;
                return "null";

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[174]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[266]++;
int CodeCoverConditionCoverageHelper_C83;
            if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((val == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[175]++;
                return "undefined";

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[176]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[267]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((val instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[177]++;
                return (String)val;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[178]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[268]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((val instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[179]++;
                return val.toString();

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[180]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[269]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((val instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[181]++;
                // XXX should we just teach NativeNumber.stringValue()
                // about Numbers?
                return numberToString(((Number)val).doubleValue(), 10);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[182]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[270]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[183]++;
                val = ((Scriptable) val).getDefaultValue(StringClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[271]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[272]++;
int CodeCoverConditionCoverageHelper_C88;
                if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[185]++;
                    throw errorWithClassName("msg.primitive.expected", val);

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[186]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[273]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[184]++;}
            return val.toString();
        }
    }

    static String defaultObjectToString(Scriptable obj)
    {
        return "[object " + obj.getClassName() + ']';
    }

    public static String toString(Object[] args, int index)
    {
        return (index < args.length) ? toString(args[index]) : "undefined";
    }

    /**
     * Optimized version of toString(Object) for numbers.
     */
    public static String toString(double val) {
        return numberToString(val, 10);
    }

    public static String numberToString(double d, int base) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[274]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[187]++;
            return "NaN";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[188]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[275]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((d == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[189]++;
            return "Infinity";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[190]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[276]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((d == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[191]++;
            return "-Infinity";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[192]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[277]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((d == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[193]++;
            return "0";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[194]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[278]++;
int CodeCoverConditionCoverageHelper_C93;

        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((base < 2) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((base > 36) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[195]++;
            throw Context.reportRuntimeError1(
                "msg.bad.radix", Integer.toString(base));

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[196]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[279]++;
int CodeCoverConditionCoverageHelper_C94;

        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((base != 10) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[197]++;
            return DToA.JS_dtobasestr(base, d);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[198]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[280]++;
            // V8 FastDtoa can't convert all numbers, so try it first but
            // fall back to old DToA in case it fails
            String result = FastDtoa.numberToString(d);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[281]++;
int CodeCoverConditionCoverageHelper_C95;
            if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[199]++;
                return result;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[200]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[282]++;
            StringBuilder buffer = new StringBuilder();
            DToA.JS_dtostr(buffer, DToA.DTOSTR_STANDARD, 0, d);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[283]++;
            return buffer.toString();
        }

    }

    static String uneval(Context cx, Scriptable scope, Object value)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[284]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[201]++;
            return "null";

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[202]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[285]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[203]++;
            return "undefined";

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[204]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[286]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((value instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[205]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[287]++;
            String escaped = escapeString(value.toString());
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[288]++;
            StringBuffer sb = new StringBuffer(escaped.length() + 2);
            sb.append('\"');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[289]++;
            sb.append(escaped);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[290]++;
            sb.append('\"');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[291]++;
            return sb.toString();

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[206]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[292]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[207]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[293]++;
            double d = ((Number)value).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[294]++;
int CodeCoverConditionCoverageHelper_C100;
            if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((d == 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((1 / d < 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[209]++;
                return "-0";

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[210]++;}
            return toString(d);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[208]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[295]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[211]++;
            return toString(value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[212]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[296]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[213]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[297]++;
            Scriptable obj = (Scriptable)value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[298]++;
int CodeCoverConditionCoverageHelper_C103;
            // Wrapped Java objects won't have "toSource" and will report
            // errors for get()s of nonexistent name, so use has() first
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(obj, "toSource")) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[215]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[299]++;
                Object v = ScriptableObject.getProperty(obj, "toSource");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[300]++;
int CodeCoverConditionCoverageHelper_C104;
                if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((v instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[217]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[301]++;
                    Function f = (Function)v;
                    return toString(f.call(cx, scope, obj, emptyArgs));

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[218]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[216]++;}
            return toString(value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[214]++;}
        warnAboutNonJSObject(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[302]++;
        return value.toString();
    }

    static String defaultObjectToSource(Context cx, Scriptable scope,
                                        Scriptable thisObj, Object[] args)
    {
        boolean toplevel, iterating;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[303]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((cx.iterating == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[219]++;
            toplevel = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[304]++;
            iterating = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[305]++;
            cx.iterating = new ObjToIntMap(31);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[306]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[220]++;
            toplevel = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[307]++;
            iterating = cx.iterating.has(thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[308]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[309]++;

        StringBuffer result = new StringBuffer(128);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[310]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((toplevel) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[221]++;
            result.append("(");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[311]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[222]++;}
        result.append('{');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[312]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[313]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

        // Make sure cx.iterating is set to null when done
        // so we don't leak memory
        try {
CodeCoverTryBranchHelper_Try3 = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[314]++;
int CodeCoverConditionCoverageHelper_C107;
            if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((iterating) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[224]++;
                cx.iterating.intern(thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[315]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[316]++; // stop recursion.
                Object[] ids = thisObj.getIds();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[317]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[43]++;


int CodeCoverConditionCoverageHelper_C108;
                for (int i=0;(((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((i < ids.length) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[43]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[44]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[45]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[318]++;
                    Object id = ids[i];
                    Object value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[319]++;
int CodeCoverConditionCoverageHelper_C109;
                    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((id instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[226]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[320]++;
                        int intId = ((Integer)id).intValue();
                        value = thisObj.get(intId, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[321]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[322]++;
int CodeCoverConditionCoverageHelper_C110;
                        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[228]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[323]++;
                            continue;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[229]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[324]++;
int CodeCoverConditionCoverageHelper_C111;   // a property has been removed
                        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[230]++;
                            result.append(", ");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[325]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[231]++;}
                        result.append(intId);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[326]++;

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[227]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[327]++;
                        String strId = (String)id;
                        value = thisObj.get(strId, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[328]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[329]++;
int CodeCoverConditionCoverageHelper_C112;
                        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[232]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[330]++;
                            continue;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[233]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[331]++;
int CodeCoverConditionCoverageHelper_C113;   // a property has been removed
                        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[234]++;
                            result.append(", ");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[332]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[235]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[333]++;
int CodeCoverConditionCoverageHelper_C114;
                        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isValidIdentifierName(strId)) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[236]++;
                            result.append(strId);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[334]++;

                        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[237]++;
                            result.append('\'');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[335]++;
                            result.append(
                                ScriptRuntime.escapeString(strId, '\''));
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[336]++;
                            result.append('\'');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[337]++;
                        }
                    }
                    result.append(':');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[338]++;
                    result.append(ScriptRuntime.uneval(cx, scope, value));
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[339]++;
                }

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[225]++;}
        } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[223]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[340]++;
int CodeCoverConditionCoverageHelper_C115;
            if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((toplevel) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[238]++;
                cx.iterating = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[341]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[239]++;}
        }

        result.append('}');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[342]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[343]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((toplevel) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[240]++;
            result.append(')');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[344]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[241]++;}
        return result.toString();
    }

    public static Scriptable toObject(Scriptable scope, Object val)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[345]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[242]++;
            return (Scriptable)val;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[243]++;}
        return toObject(Context.getContext(), scope, val);
    }

    /**
     * Warning: this doesn't allow to resolve primitive prototype properly when many top scopes are involved
     */
    public static Scriptable toObjectOrNull(Context cx, Object obj)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[346]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((obj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[244]++;
            return (Scriptable)obj;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[245]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[347]++;
int CodeCoverConditionCoverageHelper_C119; if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (8)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((obj != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[246]++;
            return toObject(cx, getTopCallScope(cx), obj);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[247]++;}
}
        return null;
    }

    /**
     * @param scope the scope that should be used to resolve primitive prototype
     */
    public static Scriptable toObjectOrNull(Context cx, Object obj,
                                            final Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[348]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((obj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[248]++;
            return (Scriptable)obj;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[249]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[349]++;
int CodeCoverConditionCoverageHelper_C121; if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (8)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((obj != Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[250]++;
            return toObject(cx, scope, obj);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[251]++;}
}
        return null;
    }

    /**
     * @deprecated Use {@link #toObject(Scriptable, Object)} instead.
     */
    public static Scriptable toObject(Scriptable scope, Object val,
                                      Class<?> staticClass)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[350]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[252]++;
            return (Scriptable)val;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[253]++;}
        return toObject(Context.getContext(), scope, val);
    }

    /**
     * Convert the value to an object.
     *
     * See ECMA 9.9.
     */
    public static Scriptable toObject(Context cx, Scriptable scope, Object val)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[351]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[254]++;
            return (Scriptable) val;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[255]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[352]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((val instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[256]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[353]++;
            // FIXME we want to avoid toString() here, especially for concat()
            NativeString result = new NativeString((CharSequence)val);
            setBuiltinProtoAndParent(result, scope, TopLevel.Builtins.String);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[354]++;
            return result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[257]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[355]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((val instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[258]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[356]++;
            NativeNumber result = new NativeNumber(((Number)val).doubleValue());
            setBuiltinProtoAndParent(result, scope, TopLevel.Builtins.Number);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[357]++;
            return result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[259]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[358]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((val instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[260]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[359]++;
            NativeBoolean result = new NativeBoolean(((Boolean)val).booleanValue());
            setBuiltinProtoAndParent(result, scope, TopLevel.Builtins.Boolean);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[360]++;
            return result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[261]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[361]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[262]++;
            throw typeError0("msg.null.to.object");

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[263]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[362]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((val == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[264]++;
            throw typeError0("msg.undef.to.object");

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[265]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[363]++;

        // Extension: Wrap as a LiveConnect object.
        Object wrapped = cx.getWrapFactory().wrap(cx, scope, val, null);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[364]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((wrapped instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[266]++;
            return (Scriptable) wrapped;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[267]++;}
        throw errorWithClassName("msg.invalid.type", val);
    }

    /**
     * @deprecated Use {@link #toObject(Context, Scriptable, Object)} instead.
     */
    public static Scriptable toObject(Context cx, Scriptable scope, Object val,
                                      Class<?> staticClass)
    {
        return toObject(cx, scope, val);
    }

    /**
     * @deprecated The method is only present for compatibility.
     */
    public static Object call(Context cx, Object fun, Object thisArg,
                              Object[] args, Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[365]++;
int CodeCoverConditionCoverageHelper_C130;
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((fun instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[268]++;
            throw notFunctionError(toString(fun));

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[269]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[366]++;
        Function function = (Function)fun;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[367]++;
        Scriptable thisObj = toObjectOrNull(cx, thisArg);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[368]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[270]++;
            throw undefCallError(thisObj, "function");

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[271]++;}
        return function.call(cx, scope, thisObj, args);
    }

    public static Scriptable newObject(Context cx, Scriptable scope,
                                       String constructorName, Object[] args)
    {
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[369]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[370]++;
        Function ctor = getExistingCtor(cx, scope, constructorName);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[371]++;
int CodeCoverConditionCoverageHelper_C132;
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[272]++; args = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[372]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[273]++;}
        return ctor.construct(cx, scope, args);
    }

    public static Scriptable newBuiltinObject(Context cx, Scriptable scope,
                                              TopLevel.Builtins type,
                                              Object[] args)
    {
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[373]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[374]++;
        Function ctor = TopLevel.getBuiltinCtor(cx, scope, type);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[375]++;
int CodeCoverConditionCoverageHelper_C133;
        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((args == null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[274]++; args = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[376]++;
 } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[275]++;}
        return ctor.construct(cx, scope, args);
    }

    /**
     *
     * See ECMA 9.4.
     */
    public static double toInteger(Object val) {
        return toInteger(toNumber(val));
    }

    // convenience method
    public static double toInteger(double d) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[377]++;
int CodeCoverConditionCoverageHelper_C134;
        // if it's NaN
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[276]++;
            return +0.0;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[277]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[378]++;
int CodeCoverConditionCoverageHelper_C135;

        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (32)) == 0 || true) &&
 ((d == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C135 |= (8)) == 0 || true) &&
 ((d == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((d == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 3) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[278]++;
            return d;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[279]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[379]++;
int CodeCoverConditionCoverageHelper_C136;

        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((d > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[280]++;
            return Math.floor(d);
}
        else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[281]++;
            return Math.ceil(d);
}
    }

    public static double toInteger(Object[] args, int index) {
        return (index < args.length) ? toInteger(args[index]) : +0.0;
    }

    /**
     *
     * See ECMA 9.5.
     */
    public static int toInt32(Object val)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[380]++;
int CodeCoverConditionCoverageHelper_C137;
        // short circuit for common integer values
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((val instanceof Integer) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[282]++;
            return ((Integer)val).intValue();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[283]++;}

        return toInt32(toNumber(val));
    }

    public static int toInt32(Object[] args, int index) {
        return (index < args.length) ? toInt32(args[index]) : 0;
    }

    public static int toInt32(double d) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[381]++;
        int id = (int)d;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[382]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((id == d) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[284]++;
            // This covers -0.0 as well
            return id;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[285]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[383]++;
int CodeCoverConditionCoverageHelper_C139;

        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (32)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C139 |= (8)) == 0 || true) &&
 ((d == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((d == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 3) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[286]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[287]++;}

        d = (d >= 0) ? Math.floor(d) : Math.ceil(d);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[384]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[385]++;

        double two32 = 4294967296.0;
        d = Math.IEEEremainder(d, two32);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[386]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[387]++;
        // (double)(long)d == d should hold here

        long l = (long)d;
        // returning (int)d does not work as d can be outside int range
        // but the result must always be 32 lower bits of l
        return (int)l;
    }

    /**
     * See ECMA 9.6.
     * @return long value representing 32 bits unsigned integer
     */
    public static long toUint32(double d) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[388]++;
        long l = (long)d;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[389]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((l == d) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[288]++;
            // This covers -0.0 as well
            return l & 0xffffffffL;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[289]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[390]++;
int CodeCoverConditionCoverageHelper_C141;

        if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (32)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((d == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((d == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 3) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[290]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[291]++;}

        d = (d >= 0) ? Math.floor(d) : Math.ceil(d);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[391]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[392]++;

        // 0x100000000 gives me a numeric overflow...
        double two32 = 4294967296.0;
        l = (long)Math.IEEEremainder(d, two32);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[393]++;

        return l & 0xffffffffL;
    }

    public static long toUint32(Object val) {
        return toUint32(toNumber(val));
    }

    /**
     *
     * See ECMA 9.7.
     */
    public static char toUint16(Object val) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[394]++;
        double d = toNumber(val);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[395]++;

        int i = (int)d;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[396]++;
int CodeCoverConditionCoverageHelper_C142;
        if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((i == d) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[292]++;
            return (char)i;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[293]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[397]++;
int CodeCoverConditionCoverageHelper_C143;

        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (32)) == 0 || true) &&
 ((d != d) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C143 |= (8)) == 0 || true) &&
 ((d == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((d == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 3) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[294]++;
            return 0;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[295]++;}

        d = (d >= 0) ? Math.floor(d) : Math.ceil(d);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[398]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[399]++;

        int int16 = 0x10000;
        i = (int)Math.IEEEremainder(d, int16);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[400]++;

        return (char)i;
    }

    // XXX: this is until setDefaultNamespace will learn how to store NS
    // properly and separates namespace form Scriptable.get etc.
    private static final String DEFAULT_NS_TAG = "__default_namespace__";
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[401]++;
  }

    public static Object setDefaultNamespace(Object namespace, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[402]++;
        Scriptable scope = cx.currentActivationCall;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[403]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[296]++;
            scope = getTopCallScope(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[404]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[297]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[405]++;

        XMLLib xmlLib = currentXMLLib(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[406]++;
        Object ns = xmlLib.toDefaultXmlNamespace(cx, namespace);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[407]++;
int CodeCoverConditionCoverageHelper_C145;

        // XXX : this should be in separated namesapce from Scriptable.get/put
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((scope.has(DEFAULT_NS_TAG, scope)) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[298]++;
            // XXX: this is racy of cause
            ScriptableObject.defineProperty(scope, DEFAULT_NS_TAG, ns,
                                            ScriptableObject.PERMANENT
                                            | ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[408]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[299]++;
            scope.put(DEFAULT_NS_TAG, scope, ns);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[409]++;
        }

        return Undefined.instance;
    }

    public static Object searchDefaultNamespace(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[410]++;
        Scriptable scope = cx.currentActivationCall;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[411]++;
int CodeCoverConditionCoverageHelper_C146;
        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[300]++;
            scope = getTopCallScope(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[412]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[301]++;}
        Object nsObject;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[413]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[46]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[46]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[47]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[48]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[414]++;
            Scriptable parent = scope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[415]++;
int CodeCoverConditionCoverageHelper_C148;
            if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[302]++;
                nsObject = ScriptableObject.getProperty(scope, DEFAULT_NS_TAG);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[416]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[417]++;
int CodeCoverConditionCoverageHelper_C149;
                if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((nsObject == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[304]++;
                    return null;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[305]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[418]++;
                break;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[303]++;}
            nsObject = scope.get(DEFAULT_NS_TAG, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[419]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[420]++;
int CodeCoverConditionCoverageHelper_C150;
            if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((nsObject != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[306]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[421]++;
                break;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[307]++;}
            scope = parent;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[422]++;
        }
        return nsObject;
    }

    public static Object getTopLevelProp(Scriptable scope, String id) {
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[423]++;
        return ScriptableObject.getProperty(scope, id);
    }

    static Function getExistingCtor(Context cx, Scriptable scope,
                                    String constructorName)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[424]++;
        Object ctorVal = ScriptableObject.getProperty(scope, constructorName);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[425]++;
int CodeCoverConditionCoverageHelper_C151;
        if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((ctorVal instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[308]++;
            return (Function)ctorVal;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[309]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[426]++;
int CodeCoverConditionCoverageHelper_C152;
        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((ctorVal == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[310]++;
            throw Context.reportRuntimeError1(
                "msg.ctor.not.found", constructorName);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[311]++;
            throw Context.reportRuntimeError1(
                "msg.not.ctor", constructorName);
        }
    }

    /**
     * Return -1L if str is not an index, or the index value as lower 32
     * bits of the result. Note that the result needs to be cast to an int
     * in order to produce the actual index, which may be negative.
     */
    public static long indexFromString(String str)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[427]++;
        // The length of the decimal string representation of
        //  Integer.MAX_VALUE, 2147483647
        final int MAX_VALUE_LENGTH = 10;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[428]++;

        int len = str.length();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[429]++;
int CodeCoverConditionCoverageHelper_C153;
        if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((len > 0) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[312]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[430]++;
            int i = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[431]++;
            boolean negate = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[432]++;
            int c = str.charAt(0);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[433]++;
int CodeCoverConditionCoverageHelper_C154;
            if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((c == '-') && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[314]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[434]++;
int CodeCoverConditionCoverageHelper_C155;
                if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((len > 1) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[316]++;
                    c = str.charAt(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[435]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[436]++;
int CodeCoverConditionCoverageHelper_C156;
                    if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((c == '0') && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[318]++; return -1L;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[319]++;} // "-0" is not an index
                    i = 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[437]++;
                    negate = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[438]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[317]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[315]++;}
            c -= '0';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[439]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[440]++;
int CodeCoverConditionCoverageHelper_C157;
            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (32)) == 0 || true) &&
 ((0 <= c) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C157 |= (8)) == 0 || true) &&
 ((c <= 9) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((len <= (negate ? MAX_VALUE_LENGTH + 1 : MAX_VALUE_LENGTH)) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 3) && false))
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[320]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[441]++;
                // Use negative numbers to accumulate index to handle
                // Integer.MIN_VALUE that is greater by 1 in absolute value
                // then Integer.MAX_VALUE
                int index = -c;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[442]++;
                int oldIndex = 0;
                i++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[443]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[444]++;
int CodeCoverConditionCoverageHelper_C158;
                if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[322]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[445]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[49]++;


                    // Note that 00, 01, 000 etc. are not indexes
                    while (i != len && 0 <= (c = str.charAt(i) - '0') && c <= 9)
                    {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[49]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[50]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[51]++;
}
                        oldIndex = index;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[446]++;
                        index = 10 * index - c;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[447]++;
                        i++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[448]++;
                    }

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[323]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[449]++;
int CodeCoverConditionCoverageHelper_C160;
                // Make sure all characters were consumed and that it couldn't
                // have overflowed.
                if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (128)) == 0 || true) &&
 ((i == len) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C160 |= (32)) == 0 || true) &&
 ((oldIndex > (Integer.MIN_VALUE / 10)) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C160 |= (8)) == 0 || true) &&
 ((oldIndex == (Integer.MIN_VALUE / 10)) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((c <= (negate ? -(Integer.MIN_VALUE % 10)
                                   : (Integer.MAX_VALUE % 10))) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 4) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 4) && false))
                {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[324]++;
                    return 0xFFFFFFFFL & (negate ? index : -index);

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[325]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[321]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[313]++;}
        return -1L;
    }

    /**
     * If str is a decimal presentation of Uint32 value, return it as long.
     * Othewise return -1L;
     */
    public static long testUint32String(String str)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[450]++;
        // The length of the decimal string representation of
        //  UINT32_MAX_VALUE, 4294967296
        final int MAX_VALUE_LENGTH = 10;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[451]++;

        int len = str.length();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[452]++;
int CodeCoverConditionCoverageHelper_C161;
        if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (8)) == 0 || true) &&
 ((1 <= len) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((len <= MAX_VALUE_LENGTH) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[326]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[453]++;
            int c = str.charAt(0);
            c -= '0';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[454]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[455]++;
int CodeCoverConditionCoverageHelper_C162;
            if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((c == 0) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[328]++;
                // Note that 00,01 etc. are not valid Uint32 presentations
                return (len == 1) ? 0L : -1L;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[329]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[456]++;
int CodeCoverConditionCoverageHelper_C163;
            if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (8)) == 0 || true) &&
 ((1 <= c) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((c <= 9) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[330]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[457]++;
                long v = c;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[458]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[52]++;


int CodeCoverConditionCoverageHelper_C164;
                for (int i = 1;(((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((i != len) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[52]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[53]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[54]++;
}
                    c = str.charAt(i) - '0';
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[459]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[460]++;
int CodeCoverConditionCoverageHelper_C165;
                    if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C165 |= (8)) == 0 || true) &&
 ((0 <= c) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((c <= 9) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[332]++;
                        return -1;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[333]++;}
                    v = 10 * v + c;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[461]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[462]++;
int CodeCoverConditionCoverageHelper_C166;
                // Check for overflow
                if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 (((v >>> 32) == 0) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[334]++;
                    return v;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[335]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[331]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[327]++;}
        return -1;
    }

    /**
     * If s represents index, then return index value wrapped as Integer
     * and othewise return s.
     */
    static Object getIndexObject(String s)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[463]++;
        long indexTest = indexFromString(s);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[464]++;
int CodeCoverConditionCoverageHelper_C167;
        if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((indexTest >= 0) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[336]++;
            return Integer.valueOf((int)indexTest);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[337]++;}
        return s;
    }

    /**
     * If d is exact int value, return its value wrapped as Integer
     * and othewise return d converted to String.
     */
    static Object getIndexObject(double d)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[465]++;
        int i = (int)d;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[466]++;
int CodeCoverConditionCoverageHelper_C168;
        if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((i == d) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[338]++;
            return Integer.valueOf(i);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[339]++;}
        return toString(d);
    }

    /**
     * If toString(id) is a decimal presentation of int32 value, then id
     * is index. In this case return null and make the index available
     * as ScriptRuntime.lastIndexResult(cx). Otherwise return toString(id).
     */
    static String toStringIdOrIndex(Context cx, Object id)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[467]++;
int CodeCoverConditionCoverageHelper_C169;
        if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((id instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[340]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[468]++;
            double d = ((Number)id).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[469]++;
            int index = (int)d;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[470]++;
int CodeCoverConditionCoverageHelper_C170;
            if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((index == d) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[342]++;
                storeIndexResult(cx, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[471]++;
                return null;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[343]++;}
            return toString(id);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[341]++;
            String s;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[472]++;
int CodeCoverConditionCoverageHelper_C171;
            if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[344]++;
                s = (String)id;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[473]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[345]++;
                s = toString(id);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[474]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[475]++;
            long indexTest = indexFromString(s);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[476]++;
int CodeCoverConditionCoverageHelper_C172;
            if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((indexTest >= 0) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[346]++;
                storeIndexResult(cx, (int)indexTest);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[477]++;
                return null;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[347]++;}
            return s;
        }
    }

    /**
     * Call obj.[[Get]](id)
     */
    public static Object getObjectElem(Object obj, Object elem, Context cx)
    {
    	return getObjectElem(obj, elem, cx, getTopCallScope(cx));
    }

    /**
     * Call obj.[[Get]](id)
     */
    public static Object getObjectElem(Object obj, Object elem, Context cx, final Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[478]++;
        Scriptable sobj = toObjectOrNull(cx, obj, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[479]++;
int CodeCoverConditionCoverageHelper_C173;
        if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[348]++;
            throw undefReadError(obj, elem);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[349]++;}
        return getObjectElem(sobj, elem, cx);
    }

    public static Object getObjectElem(Scriptable obj, Object elem,
                                       Context cx)
    {

        Object result;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[480]++;
int CodeCoverConditionCoverageHelper_C174;

        if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((obj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[350]++;
            result = ((XMLObject)obj).get(cx, elem);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[481]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[351]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[482]++;
            String s = toStringIdOrIndex(cx, elem);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[483]++;
int CodeCoverConditionCoverageHelper_C175;
            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[352]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[484]++;
                int index = lastIndexResult(cx);
                result = ScriptableObject.getProperty(obj, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[485]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[353]++;
                result = ScriptableObject.getProperty(obj, s);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[486]++;
            }
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[487]++;
int CodeCoverConditionCoverageHelper_C176;

        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[354]++;
            result = Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[488]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[355]++;}

        return result;
    }

    /**
     * Version of getObjectElem when elem is a valid JS identifier name.
     */
    public static Object getObjectProp(Object obj, String property,
                                       Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[489]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[490]++;
int CodeCoverConditionCoverageHelper_C177;
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[356]++;
            throw undefReadError(obj, property);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[357]++;}
        return getObjectProp(sobj, property, cx);
    }

    /**
     * @param scope the scope that should be used to resolve primitive prototype
     */
    public static Object getObjectProp(Object obj, String property,
                                       Context cx, final Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[491]++;
        Scriptable sobj = toObjectOrNull(cx, obj, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[492]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[358]++;
            throw undefReadError(obj, property);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[359]++;}
        return getObjectProp(sobj, property, cx);
    }

    public static Object getObjectProp(Scriptable obj, String property,
                                       Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[493]++;

        Object result = ScriptableObject.getProperty(obj, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[494]++;
int CodeCoverConditionCoverageHelper_C179;
        if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[360]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[495]++;
int CodeCoverConditionCoverageHelper_C180;
            if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_STRICT_MODE)) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[362]++;
                Context.reportWarning(ScriptRuntime.getMessage1(
                    "msg.ref.undefined.prop", property));
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[496]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[363]++;}
            result = Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[497]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[361]++;}

        return result;
    }

    public static Object getObjectPropNoWarn(Object obj, String property,
                                             Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[498]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[499]++;
int CodeCoverConditionCoverageHelper_C181;
        if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[364]++;
            throw undefReadError(obj, property);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[365]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[500]++;
        Object result = ScriptableObject.getProperty(sobj, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[501]++;
int CodeCoverConditionCoverageHelper_C182;
        if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[366]++;
          return Undefined.instance;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[367]++;}
        return result;
    }

    /*
     * A cheaper and less general version of the above for well-known argument
     * types.
     */
    public static Object getObjectIndex(Object obj, double dblIndex,
                                        Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[502]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[503]++;
int CodeCoverConditionCoverageHelper_C183;
        if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[368]++;
            throw undefReadError(obj, toString(dblIndex));

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[369]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[504]++;

        int index = (int)dblIndex;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[505]++;
int CodeCoverConditionCoverageHelper_C184;
        if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((index == dblIndex) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[370]++;
            return getObjectIndex(sobj, index, cx);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[371]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[506]++;
            String s = toString(dblIndex);
            return getObjectProp(sobj, s, cx);
        }
    }

    public static Object getObjectIndex(Scriptable obj, int index,
                                        Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[507]++;

        Object result = ScriptableObject.getProperty(obj, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[508]++;
int CodeCoverConditionCoverageHelper_C185;
        if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[372]++;
            result = Undefined.instance;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[509]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[373]++;}

        return result;
    }

    /*
     * Call obj.[[Put]](id, value)
     */
    public static Object setObjectElem(Object obj, Object elem, Object value,
                                       Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[510]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[511]++;
int CodeCoverConditionCoverageHelper_C186;
        if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[374]++;
            throw undefWriteError(obj, elem, value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[375]++;}
        return setObjectElem(sobj, elem, value, cx);
    }

    public static Object setObjectElem(Scriptable obj, Object elem,
                                       Object value, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[512]++;
int CodeCoverConditionCoverageHelper_C187;
        if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((obj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[376]++;
            ((XMLObject)obj).put(cx, elem, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[513]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[377]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[514]++;
            String s = toStringIdOrIndex(cx, elem);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[515]++;
int CodeCoverConditionCoverageHelper_C188;
            if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[378]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[516]++;
                int index = lastIndexResult(cx);
                ScriptableObject.putProperty(obj, index, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[517]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[379]++;
                ScriptableObject.putProperty(obj, s, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[518]++;
            }
        }

        return value;
    }

    /**
     * Version of setObjectElem when elem is a valid JS identifier name.
     */
    public static Object setObjectProp(Object obj, String property,
                                       Object value, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[519]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[520]++;
int CodeCoverConditionCoverageHelper_C189;
        if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[380]++;
            throw undefWriteError(obj, property, value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[381]++;}
        return setObjectProp(sobj, property, value, cx);
    }

    public static Object setObjectProp(Scriptable obj, String property,
                                       Object value, Context cx)
    {
        ScriptableObject.putProperty(obj, property, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[521]++;
        return value;
    }

    /*
     * A cheaper and less general version of the above for well-known argument
     * types.
     */
    public static Object setObjectIndex(Object obj, double dblIndex,
                                        Object value, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[522]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[523]++;
int CodeCoverConditionCoverageHelper_C190;
        if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[382]++;
            throw undefWriteError(obj, String.valueOf(dblIndex), value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[383]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[524]++;

        int index = (int)dblIndex;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[525]++;
int CodeCoverConditionCoverageHelper_C191;
        if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((index == dblIndex) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[384]++;
            return setObjectIndex(sobj, index, value, cx);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[385]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[526]++;
            String s = toString(dblIndex);
            return setObjectProp(sobj, s, value, cx);
        }
    }

    public static Object setObjectIndex(Scriptable obj, int index, Object value,
                                        Context cx)
    {
        ScriptableObject.putProperty(obj, index, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[527]++;
        return value;
    }

    public static boolean deleteObjectElem(Scriptable target, Object elem,
                                           Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[528]++;
        String s = toStringIdOrIndex(cx, elem);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[529]++;
int CodeCoverConditionCoverageHelper_C192;
        if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[386]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[530]++;
            int index = lastIndexResult(cx);
            target.delete(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[531]++;
            return !target.has(index, target);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[387]++;
            target.delete(s);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[532]++;
            return !target.has(s, target);
        }
    }

    public static boolean hasObjectElem(Scriptable target, Object elem,
                                        Context cx)
    {
        boolean result;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[533]++;

        String s = toStringIdOrIndex(cx, elem);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[534]++;
int CodeCoverConditionCoverageHelper_C193;
        if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[388]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[535]++;
            int index = lastIndexResult(cx);
            result = ScriptableObject.hasProperty(target, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[536]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[389]++;
            result = ScriptableObject.hasProperty(target, s);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[537]++;
        }

        return result;
    }

    public static Object refGet(Ref ref, Context cx)
    {
        return ref.get(cx);
    }

    public static Object refSet(Ref ref, Object value, Context cx)
    {
        return ref.set(cx, value);
    }

    public static Object refDel(Ref ref, Context cx)
    {
        return wrapBoolean(ref.delete(cx));
    }

    static boolean isSpecialProperty(String s)
    {
        return s.equals("__proto__") || s.equals("__parent__");
    }

    public static Ref specialRef(Object obj, String specialProperty,
                                 Context cx)
    {
        return SpecialRef.createSpecial(cx, obj, specialProperty);
    }

    /**
     * @deprecated
     */
    public static Object delete(Object obj, Object id, Context cx)
    {
        return delete(obj, id, cx, false);
    }

    /**
     * The delete operator
     *
     * See ECMA 11.4.1
     *
     * In ECMA 0.19, the description of the delete operator (11.4.1)
     * assumes that the [[Delete]] method returns a value. However,
     * the definition of the [[Delete]] operator (8.6.2.5) does not
     * define a return value. Here we assume that the [[Delete]]
     * method doesn't return a value.
     */
    public static Object delete(Object obj, Object id, Context cx, boolean isName)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[538]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[539]++;
int CodeCoverConditionCoverageHelper_C194;
        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[390]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[540]++;
int CodeCoverConditionCoverageHelper_C195;
            if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((isName) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[392]++;
                return Boolean.TRUE;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[393]++;}
            throw undefDeleteError(obj, id);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[391]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[541]++;
        boolean result = deleteObjectElem(sobj, id, cx);
        return wrapBoolean(result);
    }

    /**
     * Looks up a name in the scope chain and returns its value.
     */
    public static Object name(Context cx, Scriptable scope, String name)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[542]++;
        Scriptable parent = scope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[543]++;
int CodeCoverConditionCoverageHelper_C196;
        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[394]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[544]++;
            Object result = topScopeName(cx, scope, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[545]++;
int CodeCoverConditionCoverageHelper_C197;
            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[396]++;
                throw notFoundError(scope, name);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[397]++;}
            return result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[395]++;}

        return nameOrFunction(cx, scope, parent, name, false);
    }

    private static Object nameOrFunction(Context cx, Scriptable scope,
                                         Scriptable parentScope, String name,
                                         boolean asFunctionCall)
    {
        Object result;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[546]++;
        Scriptable thisObj = scope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[547]++; // It is used only if asFunctionCall==true.

        XMLObject firstXMLObject = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[548]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[55]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[55]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[56]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[57]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[549]++;
int CodeCoverConditionCoverageHelper_C199;
            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((scope instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[398]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[550]++;
                Scriptable withObj = scope.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[551]++;
int CodeCoverConditionCoverageHelper_C200;
                if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((withObj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[400]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[552]++;
                    XMLObject xmlObj = (XMLObject)withObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[553]++;
int CodeCoverConditionCoverageHelper_C201;
                    if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((xmlObj.has(name, xmlObj)) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[402]++;
                        // function this should be the target object of with
                        thisObj = xmlObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[554]++;
                        result = xmlObj.get(name, xmlObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[555]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[556]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[403]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[557]++;
int CodeCoverConditionCoverageHelper_C202;
                    if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((firstXMLObject == null) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[404]++;
                        firstXMLObject = xmlObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[558]++;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[405]++;}

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[401]++;
                    result = ScriptableObject.getProperty(withObj, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[559]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[560]++;
int CodeCoverConditionCoverageHelper_C203;
                    if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((result != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[406]++;
                        // function this should be the target object of with
                        thisObj = withObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[561]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[562]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[407]++;}
                }

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[399]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[563]++;
int CodeCoverConditionCoverageHelper_C204; if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((scope instanceof NativeCall) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[408]++;
                // NativeCall does not prototype chain and Scriptable.get
                // can be called directly.
                result = scope.get(name, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[564]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[565]++;
int CodeCoverConditionCoverageHelper_C205;
                if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((result != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[410]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[566]++;
int CodeCoverConditionCoverageHelper_C206;
                    if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((asFunctionCall) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[412]++;
                        // ECMA 262 requires that this for nested funtions
                        // should be top scope
                        thisObj = ScriptableObject.
                                      getTopLevelScope(parentScope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[567]++;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[413]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[568]++;
                    break;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[411]++;}

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[409]++;
                // Can happen if Rhino embedding decided that nested
                // scopes are useful for what ever reasons.
                result = ScriptableObject.getProperty(scope, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[569]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[570]++;
int CodeCoverConditionCoverageHelper_C207;
                if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((result != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[414]++;
                    thisObj = scope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[571]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[572]++;
                    break;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[415]++;}
            }
}
            scope = parentScope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[573]++;
            parentScope = parentScope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[574]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[575]++;
int CodeCoverConditionCoverageHelper_C208;
            if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((parentScope == null) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[416]++;
                result = topScopeName(cx, scope, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[576]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[577]++;
int CodeCoverConditionCoverageHelper_C209;
                if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[418]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[578]++;
int CodeCoverConditionCoverageHelper_C210;
                    if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (8)) == 0 || true) &&
 ((firstXMLObject == null) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((asFunctionCall) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[420]++;
                        throw notFoundError(scope, name);

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[421]++;}
                    // The name was not found, but we did find an XML
                    // object in the scope chain and we are looking for name,
                    // not function. The result should be an empty XMLList
                    // in name context.
                    result = firstXMLObject.get(name, firstXMLObject);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[579]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[419]++;}
                // For top scope thisObj for functions is always scope itself.
                thisObj = scope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[580]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[581]++;
                break;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[417]++;}
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[582]++;
int CodeCoverConditionCoverageHelper_C211;

        if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((asFunctionCall) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[422]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[583]++;
int CodeCoverConditionCoverageHelper_C212;
            if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((result instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[424]++;
                throw notFunctionError(result, name);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[425]++;}
            storeScriptable(cx, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[584]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[423]++;}

        return result;
    }

    private static Object topScopeName(Context cx, Scriptable scope,
                                       String name)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[585]++;
int CodeCoverConditionCoverageHelper_C213;
        if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((cx.useDynamicScope) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[426]++;
            scope = checkDynamicScope(cx.topCallScope, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[586]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[427]++;}
        return ScriptableObject.getProperty(scope, name);
    }


    /**
     * Returns the object in the scope chain that has a given property.
     *
     * The order of evaluation of an assignment expression involves
     * evaluating the lhs to a reference, evaluating the rhs, and then
     * modifying the reference with the rhs value. This method is used
     * to 'bind' the given name to an object containing that property
     * so that the side effects of evaluating the rhs do not affect
     * which property is modified.
     * Typically used in conjunction with setName.
     *
     * See ECMA 10.1.4
     */
    public static Scriptable bind(Context cx, Scriptable scope, String id)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[587]++;
        Scriptable firstXMLObject = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[588]++;
        Scriptable parent = scope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[589]++;
int CodeCoverConditionCoverageHelper_C214;
        childScopesChecks: if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[428]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[590]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[58]++;


int CodeCoverConditionCoverageHelper_C215;
            // Check for possibly nested "with" scopes first
            while ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((scope instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[58]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[59]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[60]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[591]++;
                Scriptable withObj = scope.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[592]++;
int CodeCoverConditionCoverageHelper_C216;
                if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((withObj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[430]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[593]++;
                    XMLObject xmlObject = (XMLObject)withObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[594]++;
int CodeCoverConditionCoverageHelper_C217;
                    if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((xmlObject.has(cx, id)) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[432]++;
                        return xmlObject;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[433]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[595]++;
int CodeCoverConditionCoverageHelper_C218;
                    if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((firstXMLObject == null) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[434]++;
                        firstXMLObject = xmlObject;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[596]++;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[435]++;}

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[431]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[597]++;
int CodeCoverConditionCoverageHelper_C219;
                    if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(withObj, id)) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[436]++;
                        return withObj;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[437]++;}
                }
                scope = parent;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[598]++;
                parent = parent.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[599]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[600]++;
int CodeCoverConditionCoverageHelper_C220;
                if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[438]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[601]++;
                    break childScopesChecks;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[439]++;}
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[602]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[61]++;


            for (;;) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[61]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[62]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[63]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[603]++;
int CodeCoverConditionCoverageHelper_C222;
                if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(scope, id)) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[440]++;
                    return scope;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[441]++;}
                scope = parent;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[604]++;
                parent = parent.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[605]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[606]++;
int CodeCoverConditionCoverageHelper_C223;
                if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[442]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[607]++;
                    break childScopesChecks;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[443]++;}
            }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[429]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[608]++;
int CodeCoverConditionCoverageHelper_C224;
        // scope here is top scope
        if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((cx.useDynamicScope) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[444]++;
            scope = checkDynamicScope(cx.topCallScope, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[609]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[445]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[610]++;
int CodeCoverConditionCoverageHelper_C225;
        if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(scope, id)) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[446]++;
            return scope;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[447]++;}
        // Nothing was found, but since XML objects always bind
        // return one if found
        return firstXMLObject;
    }

    public static Object setName(Scriptable bound, Object value,
                                 Context cx, Scriptable scope, String id)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[611]++;
int CodeCoverConditionCoverageHelper_C226;
        if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((bound != null) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[448]++;
            // TODO: we used to special-case XMLObject here, but putProperty
            // seems to work for E4X and it's better to optimize  the common case
            ScriptableObject.putProperty(bound, id, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[612]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[449]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[613]++;
int CodeCoverConditionCoverageHelper_C227;
            // "newname = 7;", where 'newname' has not yet
            // been defined, creates a new property in the
            // top scope unless strict mode is specified.
            if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (8)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_STRICT_MODE)) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_STRICT_VARS)) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 2) && false))
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[450]++;
                Context.reportWarning(
                    ScriptRuntime.getMessage1("msg.assn.create.strict", id));
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[614]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[451]++;}
            // Find the top scope by walking up the scope chain.
            bound = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[615]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[616]++;
int CodeCoverConditionCoverageHelper_C228;
            if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((cx.useDynamicScope) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[452]++;
                bound = checkDynamicScope(cx.topCallScope, bound);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[617]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[453]++;}
            bound.put(id, bound, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[618]++;
        }
        return value;
    }

    public static Object strictSetName(Scriptable bound, Object value,
            Context cx, Scriptable scope, String id) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[619]++;
int CodeCoverConditionCoverageHelper_C229;
        if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((bound != null) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[454]++;
            // TODO: The LeftHandSide also may not be a reference to a
            // data property with the attribute value {[[Writable]]:false},
            // to an accessor property with the attribute value
            // {[[Put]]:undefined}, nor to a non-existent property of an
            // object whose [[Extensible]] internal property has the value
            // false. In these cases a TypeError exception is thrown (11.13.1).
            // TODO: we used to special-case XMLObject here, but putProperty
            // seems to work for E4X and we should optimize  the common case
            ScriptableObject.putProperty(bound, id, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[620]++;
            return value;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[455]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[621]++;
            // See ES5 8.7.2
            String msg = "Assignment to undefined \"" + id + "\" in strict mode";
            throw constructError("ReferenceError", msg);
        }
    }

    public static Object setConst(Scriptable bound, Object value,
                                 Context cx, String id)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[622]++;
int CodeCoverConditionCoverageHelper_C230;
        if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((bound instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[456]++;
            bound.put(id, bound, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[623]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[457]++;
            ScriptableObject.putConstProperty(bound, id, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[624]++;
        }
        return value;
    }

    /**
     * This is the enumeration needed by the for..in statement.
     *
     * See ECMA 12.6.3.
     *
     * IdEnumeration maintains a ObjToIntMap to make sure a given
     * id is enumerated only once across multiple objects in a
     * prototype chain.
     *
     * XXX - ECMA delete doesn't hide properties in the prototype,
     * but js/ref does. This means that the js/ref for..in can
     * avoid maintaining a hash table and instead perform lookups
     * to see if a given property has already been enumerated.
     *
     */
    private static class IdEnumeration implements Serializable
    {
        private static final long serialVersionUID = 1L;
        Scriptable obj;
        Object[] ids;
        int index;
        ObjToIntMap used;
        Object currentId;
        int enumType; /* one of ENUM_INIT_KEYS, ENUM_INIT_VALUES,
                         ENUM_INIT_ARRAY */

        // if true, integer ids will be returned as numbers rather than strings
        boolean enumNumbers;

        Scriptable iterator;
    }

    public static Scriptable toIterator(Context cx, Scriptable scope,
                                        Scriptable obj, boolean keyOnly)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[625]++;
int CodeCoverConditionCoverageHelper_C231;
        if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(obj,
            NativeIterator.ITERATOR_PROPERTY_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[458]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[626]++;
            Object v = ScriptableObject.getProperty(obj,
                NativeIterator.ITERATOR_PROPERTY_NAME);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[627]++;
int CodeCoverConditionCoverageHelper_C232;
            if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((v instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[460]++;
               throw typeError0("msg.invalid.iterator");

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[461]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[628]++;
            Callable f = (Callable) v;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[629]++;
            Object[] args = new Object[] { keyOnly ? Boolean.TRUE
                                                   : Boolean.FALSE };
            v = f.call(cx, scope, obj, args);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[630]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[631]++;
int CodeCoverConditionCoverageHelper_C233;
            if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((v instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[462]++;
                throw typeError0("msg.iterator.primitive");

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[463]++;}
            return (Scriptable) v;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[459]++;}
        return null;
    }

    // for backwards compatibility with generated class files
    public static Object enumInit(Object value, Context cx, boolean enumValues)
    {
        return enumInit(value, cx, enumValues ? ENUMERATE_VALUES
                                              : ENUMERATE_KEYS);
    }

    public static final int ENUMERATE_KEYS = 0;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[632]++;
  }
    public static final int ENUMERATE_VALUES = 1;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[633]++;
  }
    public static final int ENUMERATE_ARRAY = 2;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[634]++;
  }
    public static final int ENUMERATE_KEYS_NO_ITERATOR = 3;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[635]++;
  }
    public static final int ENUMERATE_VALUES_NO_ITERATOR = 4;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[636]++;
  }
    public static final int ENUMERATE_ARRAY_NO_ITERATOR = 5;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[637]++;
  }

    public static Object enumInit(Object value, Context cx, int enumType)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[638]++;
        IdEnumeration x = new IdEnumeration();
        x.obj = toObjectOrNull(cx, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[639]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[640]++;
int CodeCoverConditionCoverageHelper_C234;
        if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((x.obj == null) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[464]++;
            // null or undefined do not cause errors but rather lead to empty
            // "for in" loop
            return x;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[465]++;}
        x.enumType = enumType;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[641]++;
        x.iterator = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[642]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[643]++;
int CodeCoverConditionCoverageHelper_C235;
        if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (32)) == 0 || true) &&
 ((enumType != ENUMERATE_KEYS_NO_ITERATOR) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C235 |= (8)) == 0 || true) &&
 ((enumType != ENUMERATE_VALUES_NO_ITERATOR) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((enumType != ENUMERATE_ARRAY_NO_ITERATOR) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 3) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 3) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[466]++;
            x.iterator = toIterator(cx, x.obj.getParentScope(), x.obj,
                                    enumType == ScriptRuntime.ENUMERATE_KEYS);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[644]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[467]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[645]++;
int CodeCoverConditionCoverageHelper_C236;
        if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((x.iterator == null) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[468]++;
            // enumInit should read all initial ids before returning
            // or "for (a.i in a)" would wrongly enumerate i in a as well
            enumChangeObject(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[646]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[469]++;}

        return x;
    }

    public static void setEnumNumbers(Object enumObj, boolean enumNumbers) {
        ((IdEnumeration)enumObj).enumNumbers = enumNumbers;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[647]++;
    }

    public static Boolean enumNext(Object enumObj)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[648]++;
        IdEnumeration x = (IdEnumeration)enumObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[649]++;
int CodeCoverConditionCoverageHelper_C237;
        if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((x.iterator != null) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[470]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[650]++;
            Object v = ScriptableObject.getProperty(x.iterator, "next");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[651]++;
int CodeCoverConditionCoverageHelper_C238;
            if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((v instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[472]++;
                return Boolean.FALSE;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[473]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[652]++;
            Callable f = (Callable) v;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[653]++;
            Context cx = Context.getContext();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[654]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
            try {
CodeCoverTryBranchHelper_Try4 = true;
                x.currentId = f.call(cx, x.iterator.getParentScope(),
                                     x.iterator, emptyArgs);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[655]++;
                return Boolean.TRUE;
            } catch (JavaScriptException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[475]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[656]++;
int CodeCoverConditionCoverageHelper_C239;
                if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((e.getValue() instanceof NativeIterator.StopIteration) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[476]++;
                  return Boolean.FALSE;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[477]++;}
                throw e;
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[474]++;
}
  }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[471]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[657]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[64]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[64]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[65]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[66]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[658]++;
int CodeCoverConditionCoverageHelper_C241;
            if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((x.obj == null) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[478]++;
                return Boolean.FALSE;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[479]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[659]++;
int CodeCoverConditionCoverageHelper_C242;
            if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((x.index == x.ids.length) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[480]++;
                x.obj = x.obj.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[660]++;
                enumChangeObject(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[661]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[662]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[481]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[663]++;
            Object id = x.ids[x.index++];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[664]++;
int CodeCoverConditionCoverageHelper_C243;
            if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (8)) == 0 || true) &&
 ((x.used != null) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((x.used.has(id)) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[482]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[665]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[483]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[666]++;
int CodeCoverConditionCoverageHelper_C244;
            if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[484]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[667]++;
                String strId = (String)id;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[668]++;
int CodeCoverConditionCoverageHelper_C245;
                if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((x.obj.has(strId, x.obj)) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[486]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[669]++;
                    continue;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[487]++;}   // must have been deleted
                x.currentId = strId;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[670]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[485]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[671]++;
                int intId = ((Number)id).intValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[672]++;
int CodeCoverConditionCoverageHelper_C246;
                if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((x.obj.has(intId, x.obj)) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[488]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[673]++;
                    continue;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[489]++;}   // must have been deleted
                x.currentId = x.enumNumbers ? (Object) (Integer.valueOf(intId))
                                            : String.valueOf(intId);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[674]++;
            }
            return Boolean.TRUE;
        }
    }

    public static Object enumId(Object enumObj, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[675]++;
        IdEnumeration x = (IdEnumeration)enumObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[676]++;
int CodeCoverConditionCoverageHelper_C247;
        if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((x.iterator != null) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[490]++;
            return x.currentId;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[491]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[677]++;
        switch (x.enumType) {
          case ENUMERATE_KEYS:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[492]++;
          case ENUMERATE_KEYS_NO_ITERATOR:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[493]++;
            return x.currentId;
          case ENUMERATE_VALUES:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[494]++;
          case ENUMERATE_VALUES_NO_ITERATOR:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[495]++;
            return enumValue(enumObj, cx);
          case ENUMERATE_ARRAY:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[496]++;
          case ENUMERATE_ARRAY_NO_ITERATOR:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[497]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[678]++;
            Object[] elements = { x.currentId, enumValue(enumObj, cx) };
            return cx.newArray(ScriptableObject.getTopLevelScope(x.obj), elements);
          default:
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[498]++;
            throw Kit.codeBug();
        }
    }

    public static Object enumValue(Object enumObj, Context cx) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[679]++;
        IdEnumeration x = (IdEnumeration)enumObj;

        Object result;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[680]++;

        String s = toStringIdOrIndex(cx, x.currentId);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[681]++;
int CodeCoverConditionCoverageHelper_C248;
        if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[499]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[682]++;
            int index = lastIndexResult(cx);
            result = x.obj.get(index, x.obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[683]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[500]++;
            result = x.obj.get(s, x.obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[684]++;
        }

        return result;
    }

    private static void enumChangeObject(IdEnumeration x)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[685]++;
        Object[] ids = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[686]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[67]++;


int CodeCoverConditionCoverageHelper_C249;
        while ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((x.obj != null) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[67]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[68]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[69]++;
}
            ids = x.obj.getIds();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[687]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[688]++;
int CodeCoverConditionCoverageHelper_C250;
            if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((ids.length != 0) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[501]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[689]++;
                break;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[502]++;}
            x.obj = x.obj.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[690]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[691]++;
int CodeCoverConditionCoverageHelper_C251;
        if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (8)) == 0 || true) &&
 ((x.obj != null) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((x.ids != null) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[503]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[692]++;
            Object[] previous = x.ids;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[693]++;
            int L = previous.length;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[694]++;
int CodeCoverConditionCoverageHelper_C252;
            if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((x.used == null) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[505]++;
                x.used = new ObjToIntMap(L);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[695]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[506]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[696]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[70]++;


int CodeCoverConditionCoverageHelper_C253;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[70]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[71]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[72]++;
}
                x.used.intern(previous[i]);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[697]++;
            }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[504]++;}
        x.ids = ids;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[698]++;
        x.index = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[699]++;
    }

    /**
     * Prepare for calling name(...): return function corresponding to
     * name and make current top scope available
     * as ScriptRuntime.lastStoredScriptable() for consumption as thisObj.
     * The caller must call ScriptRuntime.lastStoredScriptable() immediately
     * after calling this method.
     */
    public static Callable getNameFunctionAndThis(String name,
                                                  Context cx,
                                                  Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[700]++;
        Scriptable parent = scope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[701]++;
int CodeCoverConditionCoverageHelper_C254;
        if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[507]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[702]++;
            Object result = topScopeName(cx, scope, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[703]++;
int CodeCoverConditionCoverageHelper_C255;
            if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((result instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[509]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[704]++;
int CodeCoverConditionCoverageHelper_C256;
                if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((result == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[511]++;
                    throw notFoundError(scope, name);

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[512]++;
                    throw notFunctionError(result, name);
                }

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[510]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[705]++;
            // Top scope is not NativeWith or NativeCall => thisObj == scope
            Scriptable thisObj = scope;
            storeScriptable(cx, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[706]++;
            return (Callable)result;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[508]++;}

        // name will call storeScriptable(cx, thisObj);
        return (Callable)nameOrFunction(cx, scope, parent, name, true);
    }

    /**
     * Prepare for calling obj[id](...): return function corresponding to
     * obj[id] and make obj properly converted to Scriptable available
     * as ScriptRuntime.lastStoredScriptable() for consumption as thisObj.
     * The caller must call ScriptRuntime.lastStoredScriptable() immediately
     * after calling this method.
     */
    public static Callable getElemFunctionAndThis(Object obj,
                                                  Object elem,
                                                  Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[707]++;
        String str = toStringIdOrIndex(cx, elem);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[708]++;
int CodeCoverConditionCoverageHelper_C257;
        if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((str != null) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[513]++;
            return getPropFunctionAndThis(obj, str, cx);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[514]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[709]++;
        int index = lastIndexResult(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[710]++;

        Scriptable thisObj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[711]++;
int CodeCoverConditionCoverageHelper_C258;
        if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[515]++;
            throw undefCallError(obj, String.valueOf(index));

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[516]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[712]++;

        Object value = ScriptableObject.getProperty(thisObj, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[713]++;
int CodeCoverConditionCoverageHelper_C259;
        if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[517]++;
            throw notFunctionError(value, elem);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[518]++;}

        storeScriptable(cx, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[714]++;
        return (Callable)value;
    }

    /**
     * Prepare for calling obj.property(...): return function corresponding to
     * obj.property and make obj properly converted to Scriptable available
     * as ScriptRuntime.lastStoredScriptable() for consumption as thisObj.
     * The caller must call ScriptRuntime.lastStoredScriptable() immediately
     * after calling this method.
     * Warning: this doesn't allow to resolve primitive prototype properly when
     * many top scopes are involved.
     */
    public static Callable getPropFunctionAndThis(Object obj,
                                                  String property,
                                                  Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[715]++;
        Scriptable thisObj = toObjectOrNull(cx, obj);
        return getPropFunctionAndThisHelper(obj, property, cx, thisObj);
    }

    /**
     * Prepare for calling obj.property(...): return function corresponding to
     * obj.property and make obj properly converted to Scriptable available
     * as ScriptRuntime.lastStoredScriptable() for consumption as thisObj.
     * The caller must call ScriptRuntime.lastStoredScriptable() immediately
     * after calling this method.
     */
    public static Callable getPropFunctionAndThis(Object obj,
                                                  String property,
                                                  Context cx, final Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[716]++;
        Scriptable thisObj = toObjectOrNull(cx, obj, scope);
        return getPropFunctionAndThisHelper(obj, property, cx, thisObj);
    }

    private static Callable getPropFunctionAndThisHelper(Object obj,
          String property, Context cx, Scriptable thisObj)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[717]++;
int CodeCoverConditionCoverageHelper_C260;
        if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[519]++;
            throw undefCallError(obj, property);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[520]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[718]++;

        Object value = ScriptableObject.getProperty(thisObj, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[719]++;
int CodeCoverConditionCoverageHelper_C261;
        if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[521]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[720]++;
            Object noSuchMethod = ScriptableObject.getProperty(thisObj, "__noSuchMethod__");
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[721]++;
int CodeCoverConditionCoverageHelper_C262;
            if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((noSuchMethod instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[523]++;
                value = new NoSuchMethodShim((Callable)noSuchMethod, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[722]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[524]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[522]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[723]++;
int CodeCoverConditionCoverageHelper_C263;

        if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[525]++;
            throw notFunctionError(thisObj, value, property);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[526]++;}

        storeScriptable(cx, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[724]++;
        return (Callable)value;
    }

    /**
     * Prepare for calling <expression>(...): return function corresponding to
     * <expression> and make parent scope of the function available
     * as ScriptRuntime.lastStoredScriptable() for consumption as thisObj.
     * The caller must call ScriptRuntime.lastStoredScriptable() immediately
     * after calling this method.
     */
    public static Callable getValueFunctionAndThis(Object value, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[725]++;
int CodeCoverConditionCoverageHelper_C264;
        if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[527]++;
            throw notFunctionError(value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[528]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[726]++;

        Callable f = (Callable)value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[727]++;
        Scriptable thisObj = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[728]++;
int CodeCoverConditionCoverageHelper_C265;
        if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((f instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[529]++;
            thisObj = ((Scriptable)f).getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[729]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[530]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[730]++;
int CodeCoverConditionCoverageHelper_C266;
        if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((thisObj == null) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[531]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[731]++;
int CodeCoverConditionCoverageHelper_C267;
            if ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((cx.topCallScope == null) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[533]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[534]++;}
            thisObj = cx.topCallScope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[732]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[532]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[733]++;
int CodeCoverConditionCoverageHelper_C268;
        if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((thisObj.getParentScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[535]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[734]++;
int CodeCoverConditionCoverageHelper_C269;
            if ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[537]++;

                // functions defined inside with should have with target
                // as their thisObj
            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[538]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[735]++;
int CodeCoverConditionCoverageHelper_C270; if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((thisObj instanceof NativeCall) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[539]++;
                // nested functions should have top scope as their thisObj
                thisObj = ScriptableObject.getTopLevelScope(thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[736]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[540]++;}
}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[536]++;}
        storeScriptable(cx, thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[737]++;
        return f;
    }

    /**
     * Perform function call in reference context. Should always
     * return value that can be passed to
     * {@link #refGet(Ref, Context)} or {@link #refSet(Ref, Object, Context)}
     * arbitrary number of times.
     * The args array reference should not be stored in any object that is
     * can be GC-reachable after this method returns. If this is necessary,
     * store args.clone(), not args array itself.
     */
    public static Ref callRef(Callable function, Scriptable thisObj,
                              Object[] args, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[738]++;
int CodeCoverConditionCoverageHelper_C271;
        if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((function instanceof RefCallable) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[541]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[739]++;
            RefCallable rfunction = (RefCallable)function;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[740]++;
            Ref ref = rfunction.refCall(cx, thisObj, args);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[741]++;
int CodeCoverConditionCoverageHelper_C272;
            if ((((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((ref == null) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[543]++;
                throw new IllegalStateException(rfunction.getClass().getName()+".refCall() returned null");

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[544]++;}
            return ref;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[542]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[742]++;
        // No runtime support for now
        String msg = getMessage1("msg.no.ref.from.function",
                                 toString(function));
        throw constructError("ReferenceError", msg);
    }

    /**
     * Operator new.
     *
     * See ECMA 11.2.2
     */
    public static Scriptable newObject(Object fun, Context cx,
                                       Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[743]++;
int CodeCoverConditionCoverageHelper_C273;
        if ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((fun instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[545]++;
            throw notFunctionError(fun);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[546]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[744]++;
        Function function = (Function)fun;
        return function.construct(cx, scope, args);
    }

    public static Object callSpecial(Context cx, Callable fun,
                                     Scriptable thisObj,
                                     Object[] args, Scriptable scope,
                                     Scriptable callerThis, int callType,
                                     String filename, int lineNumber)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[745]++;
int CodeCoverConditionCoverageHelper_C274;
        if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((callType == Node.SPECIALCALL_EVAL) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[547]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[746]++;
int CodeCoverConditionCoverageHelper_C275;
            if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (8)) == 0 || true) &&
 ((thisObj.getParentScope() == null) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((NativeGlobal.isEvalFunction(fun)) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[549]++;
                return evalSpecial(cx, scope, callerThis, args,
                                   filename, lineNumber);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[550]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[548]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[747]++;
int CodeCoverConditionCoverageHelper_C276; if ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((callType == Node.SPECIALCALL_WITH) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[551]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[748]++;
int CodeCoverConditionCoverageHelper_C277;
            if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((NativeWith.isWithFunction(fun)) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[553]++;
                throw Context.reportRuntimeError1("msg.only.from.new",
                                                  "With");

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[554]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[552]++;
            throw Kit.codeBug();
        }
}

        return fun.call(cx, scope, thisObj, args);
    }

    public static Object newSpecial(Context cx, Object fun,
                                    Object[] args, Scriptable scope,
                                    int callType)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[749]++;
int CodeCoverConditionCoverageHelper_C278;
        if ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((callType == Node.SPECIALCALL_EVAL) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[555]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[750]++;
int CodeCoverConditionCoverageHelper_C279;
            if ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((NativeGlobal.isEvalFunction(fun)) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[557]++;
                throw typeError1("msg.not.ctor", "eval");

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[558]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[556]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[751]++;
int CodeCoverConditionCoverageHelper_C280; if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((callType == Node.SPECIALCALL_WITH) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[559]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[752]++;
int CodeCoverConditionCoverageHelper_C281;
            if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((NativeWith.isWithFunction(fun)) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[561]++;
                return NativeWith.newWithSpecial(cx, scope, args);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[562]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[560]++;
            throw Kit.codeBug();
        }
}

        return newObject(fun, cx, scope, args);
    }

    /**
     * Function.prototype.apply and Function.prototype.call
     *
     * See Ecma 15.3.4.[34]
     */
    public static Object applyOrCall(boolean isApply,
                                     Context cx, Scriptable scope,
                                     Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[753]++;
        int L = args.length;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[754]++;
        Callable function = getCallable(thisObj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[755]++;

        Scriptable callThis = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[756]++;
int CodeCoverConditionCoverageHelper_C282;
        if ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((L != 0) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[563]++;
            callThis = toObjectOrNull(cx, args[0]);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[757]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[564]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[758]++;
int CodeCoverConditionCoverageHelper_C283;
        if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((callThis == null) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[565]++;
            // This covers the case of args[0] == (null|undefined) as well.
            callThis = getTopCallScope(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[759]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[566]++;}

        Object[] callArgs;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[760]++;
int CodeCoverConditionCoverageHelper_C284;
        if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((isApply) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[567]++;
            // Follow Ecma 15.3.4.3
            callArgs = L <= 1 ? ScriptRuntime.emptyArgs :
                getApplyArguments(cx, args[1]);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[761]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[568]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[762]++;
int CodeCoverConditionCoverageHelper_C285;
            // Follow Ecma 15.3.4.4
            if ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((L <= 1) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[569]++;
                callArgs = ScriptRuntime.emptyArgs;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[763]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[570]++;
                callArgs = new Object[L - 1];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[764]++;
                System.arraycopy(args, 1, callArgs, 0, L - 1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[765]++;
            }
        }

        return function.call(cx, scope, callThis, callArgs);
    }

    static Object[] getApplyArguments(Context cx, Object arg1)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[766]++;
int CodeCoverConditionCoverageHelper_C286;
        if ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (8)) == 0 || true) &&
 ((arg1 == null) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((arg1 == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[571]++;
            return ScriptRuntime.emptyArgs;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[572]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[767]++;
int CodeCoverConditionCoverageHelper_C287; if ((((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (8)) == 0 || true) &&
 ((arg1 instanceof NativeArray) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((arg1 instanceof Arguments) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[573]++;
            return cx.getElements((Scriptable) arg1);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[574]++;
            throw ScriptRuntime.typeError0("msg.arg.isnt.array");
        }
}
    }

    static Callable getCallable(Scriptable thisObj)
    {
        Callable function;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[768]++;
int CodeCoverConditionCoverageHelper_C288;
        if ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((thisObj instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[575]++;
            function = (Callable)thisObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[769]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[576]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[770]++;
            Object value = thisObj.getDefaultValue(ScriptRuntime.FunctionClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[771]++;
int CodeCoverConditionCoverageHelper_C289;
            if ((((((CodeCoverConditionCoverageHelper_C289 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C289 |= (2)) == 0 || true) &&
 ((value instanceof Callable) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[577]++;
                throw ScriptRuntime.notFunctionError(value, thisObj);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[578]++;}
            function = (Callable)value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[772]++;
        }
        return function;
    }

    /**
     * The eval function property of the global object.
     *
     * See ECMA 15.1.2.1
     */
    public static Object evalSpecial(Context cx, Scriptable scope,
                                     Object thisArg, Object[] args,
                                     String filename, int lineNumber)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[773]++;
int CodeCoverConditionCoverageHelper_C290;
        if ((((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 ((args.length < 1) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[579]++;
            return Undefined.instance;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[580]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[774]++;
        Object x = args[0];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[775]++;
int CodeCoverConditionCoverageHelper_C291;
        if ((((((CodeCoverConditionCoverageHelper_C291 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C291 |= (2)) == 0 || true) &&
 ((x instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C291 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[581]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[776]++;
int CodeCoverConditionCoverageHelper_C292;
            if ((((((CodeCoverConditionCoverageHelper_C292 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C292 |= (8)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_STRICT_MODE)) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C292 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_STRICT_EVAL)) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 2) && false))
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[583]++;
                throw Context.reportRuntimeError0("msg.eval.nonstring.strict");

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[584]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[777]++;
            String message = ScriptRuntime.getMessage0("msg.eval.nonstring");
            Context.reportWarning(message);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[778]++;
            return x;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[582]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[779]++;
int CodeCoverConditionCoverageHelper_C293;
        if ((((((CodeCoverConditionCoverageHelper_C293 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C293 |= (2)) == 0 || true) &&
 ((filename == null) && 
  ((CodeCoverConditionCoverageHelper_C293 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[585]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[780]++;
            int[] linep = new int[1];
            filename = Context.getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[781]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[782]++;
int CodeCoverConditionCoverageHelper_C294;
            if ((((((CodeCoverConditionCoverageHelper_C294 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C294 |= (2)) == 0 || true) &&
 ((filename != null) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[587]++;
                lineNumber = linep[0];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[783]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[588]++;
                filename = "";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[784]++;
            }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[586]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[785]++;
        String sourceName = ScriptRuntime.
            makeUrlForGeneratedScript(true, filename, lineNumber);

        ErrorReporter reporter;
        reporter = DefaultErrorReporter.forEval(cx.getErrorReporter());
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[786]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[787]++;

        Evaluator evaluator = Context.createInterpreter();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[788]++;
int CodeCoverConditionCoverageHelper_C295;
        if ((((((CodeCoverConditionCoverageHelper_C295 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C295 |= (2)) == 0 || true) &&
 ((evaluator == null) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[589]++;
            throw new JavaScriptException("Interpreter not present",
                    filename, lineNumber);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[590]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[789]++;

        // Compile with explicit interpreter instance to force interpreter
        // mode.
        Script script = cx.compileString(x.toString(), evaluator,
                                         reporter, sourceName, 1, null);
        evaluator.setEvalScriptFlag(script);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[790]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[791]++;
        Callable c = (Callable)script;
        return c.call(cx, scope, (Scriptable)thisArg, ScriptRuntime.emptyArgs);
    }

    /**
     * The typeof operator
     */
    public static String typeof(Object value)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[792]++;
int CodeCoverConditionCoverageHelper_C296;
        if ((((((CodeCoverConditionCoverageHelper_C296 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C296 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C296 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[591]++;
            return "object";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[592]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[793]++;
int CodeCoverConditionCoverageHelper_C297;
        if ((((((CodeCoverConditionCoverageHelper_C297 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C297 |= (2)) == 0 || true) &&
 ((value == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C297 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[593]++;
            return "undefined";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[594]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[794]++;
int CodeCoverConditionCoverageHelper_C298;
        if ((((((CodeCoverConditionCoverageHelper_C298 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C298 |= (2)) == 0 || true) &&
 ((value instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C298 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[595]++;
        	return ((ScriptableObject) value).getTypeOf();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[596]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[795]++;
int CodeCoverConditionCoverageHelper_C299;
        if ((((((CodeCoverConditionCoverageHelper_C299 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C299 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C299 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[597]++;
            return (value instanceof Callable) ? "function" : "object";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[598]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[796]++;
int CodeCoverConditionCoverageHelper_C300;
        if ((((((CodeCoverConditionCoverageHelper_C300 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C300 |= (2)) == 0 || true) &&
 ((value instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[599]++;
            return "string";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[600]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[797]++;
int CodeCoverConditionCoverageHelper_C301;
        if ((((((CodeCoverConditionCoverageHelper_C301 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C301 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[601]++;
            return "number";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[602]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[798]++;
int CodeCoverConditionCoverageHelper_C302;
        if ((((((CodeCoverConditionCoverageHelper_C302 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C302 |= (2)) == 0 || true) &&
 ((value instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C302 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[603]++;
            return "boolean";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[604]++;}
        throw errorWithClassName("msg.invalid.type", value);
    }

    /**
     * The typeof operator that correctly handles the undefined case
     */
    public static String typeofName(Scriptable scope, String id)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[799]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[800]++;
        Scriptable val = bind(cx, scope, id);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[801]++;
int CodeCoverConditionCoverageHelper_C303;
        if ((((((CodeCoverConditionCoverageHelper_C303 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C303 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C303 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[605]++;
            return "undefined";
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[606]++;}
        return typeof(getObjectProp(val, id, cx));
    }

    // neg:
    // implement the '-' operator inline in the caller
    // as "-toNumber(val)"

    // not:
    // implement the '!' operator inline in the caller
    // as "!toBoolean(val)"

    // bitnot:
    // implement the '~' operator inline in the caller
    // as "~toInt32(val)"

    public static Object add(Object val1, Object val2, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[802]++;
int CodeCoverConditionCoverageHelper_C304;
        if((((((CodeCoverConditionCoverageHelper_C304 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C304 |= (8)) == 0 || true) &&
 ((val1 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C304 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C304 |= (2)) == 0 || true) &&
 ((val2 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C304 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[607]++;
            return wrapNumber(((Number)val1).doubleValue() +
                              ((Number)val2).doubleValue());

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[608]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[803]++;
int CodeCoverConditionCoverageHelper_C305;
        if ((((((CodeCoverConditionCoverageHelper_C305 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C305 |= (2)) == 0 || true) &&
 ((val1 instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C305 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[609]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[804]++;
            Object test = ((XMLObject)val1).addValues(cx, true, val2);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[805]++;
int CodeCoverConditionCoverageHelper_C306;
            if ((((((CodeCoverConditionCoverageHelper_C306 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C306 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C306 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[611]++;
                return test;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[612]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[610]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[806]++;
int CodeCoverConditionCoverageHelper_C307;
        if ((((((CodeCoverConditionCoverageHelper_C307 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C307 |= (2)) == 0 || true) &&
 ((val2 instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C307 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[613]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[807]++;
            Object test = ((XMLObject)val2).addValues(cx, false, val1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[808]++;
int CodeCoverConditionCoverageHelper_C308;
            if ((((((CodeCoverConditionCoverageHelper_C308 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C308 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C308 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[615]++;
                return test;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[616]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[614]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[809]++;
int CodeCoverConditionCoverageHelper_C309;
        if ((((((CodeCoverConditionCoverageHelper_C309 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C309 |= (2)) == 0 || true) &&
 ((val1 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C309 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[617]++;
            val1 = ((Scriptable) val1).getDefaultValue(null);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[810]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[618]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[811]++;
int CodeCoverConditionCoverageHelper_C310;
        if ((((((CodeCoverConditionCoverageHelper_C310 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C310 |= (2)) == 0 || true) &&
 ((val2 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C310 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[619]++;
            val2 = ((Scriptable) val2).getDefaultValue(null);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[812]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[620]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[813]++;
int CodeCoverConditionCoverageHelper_C311;
        if ((((((CodeCoverConditionCoverageHelper_C311 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C311 |= (8)) == 0 || true) &&
 ((val1 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C311 |= (4)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C311 |= (2)) == 0 || true) &&
 ((val2 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C311 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[621]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[814]++;
int CodeCoverConditionCoverageHelper_C312;
            if ((((((CodeCoverConditionCoverageHelper_C312 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C312 |= (8)) == 0 || true) &&
 ((val1 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C312 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C312 |= (2)) == 0 || true) &&
 ((val2 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C312 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[623]++;
                return wrapNumber(((Number)val1).doubleValue() +
                                  ((Number)val2).doubleValue());
}
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[624]++;
                return wrapNumber(toNumber(val1) + toNumber(val2));
}
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[622]++;}
        return new ConsString(toCharSequence(val1), toCharSequence(val2));
    }

    public static CharSequence add(CharSequence val1, Object val2) {
        return new ConsString(val1, toCharSequence(val2));
    }

    public static CharSequence add(Object val1, CharSequence val2) {
        return new ConsString(toCharSequence(val1), val2);
    }

    /**
     * @deprecated The method is only present for compatibility.
     */
    public static Object nameIncrDecr(Scriptable scopeChain, String id,
                                      int incrDecrMask)
    {
        return nameIncrDecr(scopeChain, id, Context.getContext(), incrDecrMask);
    }

    public static Object nameIncrDecr(Scriptable scopeChain, String id,
                                      Context cx, int incrDecrMask)
    {
        Scriptable target;
        Object value;
      search: {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[815]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[73]++;


int CodeCoverConditionCoverageHelper_C313;
            do {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[73]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[74]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[75]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[816]++;
int CodeCoverConditionCoverageHelper_C314;
                if ((((((CodeCoverConditionCoverageHelper_C314 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C314 |= (8)) == 0 || true) &&
 ((cx.useDynamicScope) && 
  ((CodeCoverConditionCoverageHelper_C314 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C314 |= (2)) == 0 || true) &&
 ((scopeChain.getParentScope() == null) && 
  ((CodeCoverConditionCoverageHelper_C314 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[625]++;
                    scopeChain = checkDynamicScope(cx.topCallScope, scopeChain);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[817]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[626]++;}
                target = scopeChain;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[818]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[819]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[76]++;


int CodeCoverConditionCoverageHelper_C315;
                do {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[76]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[77]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[78]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[820]++;
int CodeCoverConditionCoverageHelper_C316;
                    if ((((((CodeCoverConditionCoverageHelper_C316 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C316 |= (8)) == 0 || true) &&
 ((target instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C316 |= (2)) == 0 || true) &&
 ((target.getPrototype() instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[627]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[821]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[628]++;}
                    value = target.get(id, scopeChain);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[822]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[823]++;
int CodeCoverConditionCoverageHelper_C317;
                    if ((((((CodeCoverConditionCoverageHelper_C317 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C317 |= (2)) == 0 || true) &&
 ((value != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[629]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[824]++;
                        break search;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[630]++;}
                    target = target.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[825]++;
                } while ((((((CodeCoverConditionCoverageHelper_C315 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C315 |= (2)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 1) && false));
                scopeChain = scopeChain.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[826]++;
            } while ((((((CodeCoverConditionCoverageHelper_C313 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C313 |= (2)) == 0 || true) &&
 ((scopeChain != null) && 
  ((CodeCoverConditionCoverageHelper_C313 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) && false));
            throw notFoundError(scopeChain, id);
        }
        return doScriptableIncrDecr(target, id, scopeChain, value,
                                    incrDecrMask);
    }

    public static Object propIncrDecr(Object obj, String id,
                                      Context cx, int incrDecrMask)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[827]++;
        Scriptable start = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[828]++;
int CodeCoverConditionCoverageHelper_C318;
        if ((((((CodeCoverConditionCoverageHelper_C318 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C318 |= (2)) == 0 || true) &&
 ((start == null) && 
  ((CodeCoverConditionCoverageHelper_C318 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[631]++;
            throw undefReadError(obj, id);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[632]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[829]++;

        Scriptable target = start;
        Object value;
      search: {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[830]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[79]++;


int CodeCoverConditionCoverageHelper_C319;
            do {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[79]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[80]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[81]++;
}
                value = target.get(id, start);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[831]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[832]++;
int CodeCoverConditionCoverageHelper_C320;
                if ((((((CodeCoverConditionCoverageHelper_C320 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C320 |= (2)) == 0 || true) &&
 ((value != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C320 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[633]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[833]++;
                    break search;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[634]++;}
                target = target.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[834]++;
            } while ((((((CodeCoverConditionCoverageHelper_C319 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C319 |= (2)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C319 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) && false));
            start.put(id, start, NaNobj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[835]++;
            return NaNobj;
        }
        return doScriptableIncrDecr(target, id, start, value,
                                    incrDecrMask);
    }

    private static Object doScriptableIncrDecr(Scriptable target,
                                               String id,
                                               Scriptable protoChainStart,
                                               Object value,
                                               int incrDecrMask)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[836]++;
        boolean post = ((incrDecrMask & Node.POST_FLAG) != 0);
        double number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[837]++;
int CodeCoverConditionCoverageHelper_C321;
        if ((((((CodeCoverConditionCoverageHelper_C321 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C321 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C321 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[635]++;
            number = ((Number)value).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[838]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[636]++;
            number = toNumber(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[839]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[840]++;
int CodeCoverConditionCoverageHelper_C322;
            if ((((((CodeCoverConditionCoverageHelper_C322 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C322 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C322 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[637]++;
                // convert result to number
                value = wrapNumber(number);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[841]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[638]++;}
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[842]++;
int CodeCoverConditionCoverageHelper_C323;
        if ((((((CodeCoverConditionCoverageHelper_C323 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C323 |= (2)) == 0 || true) &&
 (((incrDecrMask & Node.DECR_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C323 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[639]++;
            ++number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[843]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[640]++;
            --number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[844]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[845]++;
        Number result = wrapNumber(number);
        target.put(id, protoChainStart, result);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[846]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[847]++;
int CodeCoverConditionCoverageHelper_C324;
        if ((((((CodeCoverConditionCoverageHelper_C324 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C324 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C324 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[641]++;
            return value;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[642]++;
            return result;
        }
    }

    public static Object elemIncrDecr(Object obj, Object index,
                                      Context cx, int incrDecrMask)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[848]++;
        Object value = getObjectElem(obj, index, cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[849]++;
        boolean post = ((incrDecrMask & Node.POST_FLAG) != 0);
        double number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[850]++;
int CodeCoverConditionCoverageHelper_C325;
        if ((((((CodeCoverConditionCoverageHelper_C325 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C325 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C325 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[643]++;
            number = ((Number)value).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[851]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[644]++;
            number = toNumber(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[852]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[853]++;
int CodeCoverConditionCoverageHelper_C326;
            if ((((((CodeCoverConditionCoverageHelper_C326 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C326 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C326 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[645]++;
                // convert result to number
                value = wrapNumber(number);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[854]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[646]++;}
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[855]++;
int CodeCoverConditionCoverageHelper_C327;
        if ((((((CodeCoverConditionCoverageHelper_C327 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C327 |= (2)) == 0 || true) &&
 (((incrDecrMask & Node.DECR_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C327 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[327].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C327, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[327].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C327, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[647]++;
            ++number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[856]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[648]++;
            --number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[857]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[858]++;
        Number result = wrapNumber(number);
        setObjectElem(obj, index, result, cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[859]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[860]++;
int CodeCoverConditionCoverageHelper_C328;
        if ((((((CodeCoverConditionCoverageHelper_C328 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C328 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C328 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[649]++;
            return value;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[650]++;
            return result;
        }
    }

    public static Object refIncrDecr(Ref ref, Context cx, int incrDecrMask)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[861]++;
        Object value = ref.get(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[862]++;
        boolean post = ((incrDecrMask & Node.POST_FLAG) != 0);
        double number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[863]++;
int CodeCoverConditionCoverageHelper_C329;
        if ((((((CodeCoverConditionCoverageHelper_C329 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C329 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C329 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[651]++;
            number = ((Number)value).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[864]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[652]++;
            number = toNumber(value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[865]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[866]++;
int CodeCoverConditionCoverageHelper_C330;
            if ((((((CodeCoverConditionCoverageHelper_C330 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C330 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C330 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[653]++;
                // convert result to number
                value = wrapNumber(number);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[867]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[654]++;}
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[868]++;
int CodeCoverConditionCoverageHelper_C331;
        if ((((((CodeCoverConditionCoverageHelper_C331 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C331 |= (2)) == 0 || true) &&
 (((incrDecrMask & Node.DECR_FLAG) == 0) && 
  ((CodeCoverConditionCoverageHelper_C331 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[655]++;
            ++number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[869]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[656]++;
            --number;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[870]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[871]++;
        Number result = wrapNumber(number);
        ref.set(cx, result);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[872]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[873]++;
int CodeCoverConditionCoverageHelper_C332;
        if ((((((CodeCoverConditionCoverageHelper_C332 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C332 |= (2)) == 0 || true) &&
 ((post) && 
  ((CodeCoverConditionCoverageHelper_C332 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[657]++;
            return value;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[658]++;
            return result;
        }
    }

    public static Object toPrimitive(Object val) {
        return toPrimitive(val, null);
    }

    public static Object toPrimitive(Object val, Class<?> typeHint)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[874]++;
int CodeCoverConditionCoverageHelper_C333;
        if ((((((CodeCoverConditionCoverageHelper_C333 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C333 |= (2)) == 0 || true) &&
 ((val instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C333 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[659]++;
            return val;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[660]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[875]++;
        Scriptable s = (Scriptable)val;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[876]++;
        Object result = s.getDefaultValue(typeHint);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[877]++;
int CodeCoverConditionCoverageHelper_C334;
        if ((((((CodeCoverConditionCoverageHelper_C334 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C334 |= (2)) == 0 || true) &&
 ((result instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C334 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[661]++;
            throw typeError0("msg.bad.default.value");
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[662]++;}
        return result;
    }

    /**
     * Equality
     *
     * See ECMA 11.9
     */
    public static boolean eq(Object x, Object y)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[878]++;
int CodeCoverConditionCoverageHelper_C335;
        if ((((((CodeCoverConditionCoverageHelper_C335 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C335 |= (8)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C335 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C335 |= (2)) == 0 || true) &&
 ((x == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C335 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[663]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[879]++;
int CodeCoverConditionCoverageHelper_C336;
            if ((((((CodeCoverConditionCoverageHelper_C336 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C336 |= (8)) == 0 || true) &&
 ((y == null) && 
  ((CodeCoverConditionCoverageHelper_C336 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C336 |= (2)) == 0 || true) &&
 ((y == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C336 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[665]++;
                return true;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[666]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[880]++;
int CodeCoverConditionCoverageHelper_C337;
            if ((((((CodeCoverConditionCoverageHelper_C337 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C337 |= (2)) == 0 || true) &&
 ((y instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C337 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[667]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[881]++;
                Object test = ((ScriptableObject)y).equivalentValues(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[882]++;
int CodeCoverConditionCoverageHelper_C338;
                if ((((((CodeCoverConditionCoverageHelper_C338 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C338 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C338 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[669]++;
                    return ((Boolean)test).booleanValue();

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[670]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[668]++;}
            return false;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[664]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[883]++;
int CodeCoverConditionCoverageHelper_C339; if ((((((CodeCoverConditionCoverageHelper_C339 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C339 |= (2)) == 0 || true) &&
 ((x instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C339 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[671]++;
            return eqNumber(((Number)x).doubleValue(), y);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[672]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[884]++;
int CodeCoverConditionCoverageHelper_C340; if ((((((CodeCoverConditionCoverageHelper_C340 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C340 |= (2)) == 0 || true) &&
 ((x == y) && 
  ((CodeCoverConditionCoverageHelper_C340 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[673]++;
            return true;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[674]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[885]++;
int CodeCoverConditionCoverageHelper_C341; if ((((((CodeCoverConditionCoverageHelper_C341 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C341 |= (2)) == 0 || true) &&
 ((x instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C341 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[675]++;
            return eqString((CharSequence)x, y);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[676]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[886]++;
int CodeCoverConditionCoverageHelper_C342; if ((((((CodeCoverConditionCoverageHelper_C342 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C342 |= (2)) == 0 || true) &&
 ((x instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C342 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[677]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[887]++;
            boolean b = ((Boolean)x).booleanValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[888]++;
int CodeCoverConditionCoverageHelper_C343;
            if ((((((CodeCoverConditionCoverageHelper_C343 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C343 |= (2)) == 0 || true) &&
 ((y instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C343 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[679]++;
                return b == ((Boolean)y).booleanValue();

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[680]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[889]++;
int CodeCoverConditionCoverageHelper_C344;
            if ((((((CodeCoverConditionCoverageHelper_C344 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C344 |= (2)) == 0 || true) &&
 ((y instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C344 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[681]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[890]++;
                Object test = ((ScriptableObject)y).equivalentValues(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[891]++;
int CodeCoverConditionCoverageHelper_C345;
                if ((((((CodeCoverConditionCoverageHelper_C345 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C345 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C345 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[683]++;
                    return ((Boolean)test).booleanValue();

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[684]++;}

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[682]++;}
            return eqNumber(b ? 1.0 : 0.0, y);

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[678]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[892]++;
int CodeCoverConditionCoverageHelper_C346; if ((((((CodeCoverConditionCoverageHelper_C346 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C346 |= (2)) == 0 || true) &&
 ((x instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C346 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[685]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[893]++;
int CodeCoverConditionCoverageHelper_C347;
            if ((((((CodeCoverConditionCoverageHelper_C347 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C347 |= (2)) == 0 || true) &&
 ((y instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C347 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[347].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C347, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[347].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C347, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[687]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[894]++;
int CodeCoverConditionCoverageHelper_C348;
                if ((((((CodeCoverConditionCoverageHelper_C348 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C348 |= (2)) == 0 || true) &&
 ((x instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C348 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[348].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C348, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[348].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C348, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[689]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[895]++;
                    Object test = ((ScriptableObject)x).equivalentValues(y);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[896]++;
int CodeCoverConditionCoverageHelper_C349;
                    if ((((((CodeCoverConditionCoverageHelper_C349 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C349 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C349 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[349].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C349, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[349].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C349, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[691]++;
                        return ((Boolean)test).booleanValue();

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[692]++;}

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[690]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[897]++;
int CodeCoverConditionCoverageHelper_C350;
                if ((((((CodeCoverConditionCoverageHelper_C350 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C350 |= (2)) == 0 || true) &&
 ((y instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C350 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[350].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C350, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[350].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C350, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[693]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[898]++;
                    Object test = ((ScriptableObject)y).equivalentValues(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[899]++;
int CodeCoverConditionCoverageHelper_C351;
                    if ((((((CodeCoverConditionCoverageHelper_C351 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C351 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C351 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[351].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C351, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[351].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C351, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[695]++;
                        return ((Boolean)test).booleanValue();

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[696]++;}

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[694]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[900]++;
int CodeCoverConditionCoverageHelper_C352;
                if ((((((CodeCoverConditionCoverageHelper_C352 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C352 |= (8)) == 0 || true) &&
 ((x instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C352 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C352 |= (2)) == 0 || true) &&
 ((y instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C352 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[352].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C352, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[352].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C352, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[697]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[901]++;
                    // See bug 413838. Effectively an extension to ECMA for
                    // the LiveConnect case.
                    Object unwrappedX = ((Wrapper)x).unwrap();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[902]++;
                    Object unwrappedY = ((Wrapper)y).unwrap();
                    return unwrappedX == unwrappedY ||
                           (isPrimitive(unwrappedX) &&
                            isPrimitive(unwrappedY) &&
                            eq(unwrappedX, unwrappedY));

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[698]++;}
                return false;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[688]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[903]++;
int CodeCoverConditionCoverageHelper_C353; if ((((((CodeCoverConditionCoverageHelper_C353 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C353 |= (2)) == 0 || true) &&
 ((y instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C353 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[353].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C353, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[353].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C353, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[699]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[904]++;
int CodeCoverConditionCoverageHelper_C354;
                if ((((((CodeCoverConditionCoverageHelper_C354 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C354 |= (2)) == 0 || true) &&
 ((x instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C354 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[354].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C354, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[354].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C354, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[701]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[905]++;
                    Object test = ((ScriptableObject)x).equivalentValues(y);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[906]++;
int CodeCoverConditionCoverageHelper_C355;
                    if ((((((CodeCoverConditionCoverageHelper_C355 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C355 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C355 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[355].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C355, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[355].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C355, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[703]++;
                        return ((Boolean)test).booleanValue();

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[704]++;}

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[702]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[907]++;
                double d = ((Boolean)y).booleanValue() ? 1.0 : 0.0;
                return eqNumber(d, x);

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[700]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[908]++;
int CodeCoverConditionCoverageHelper_C356; if ((((((CodeCoverConditionCoverageHelper_C356 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C356 |= (2)) == 0 || true) &&
 ((y instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C356 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[356].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C356, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[356].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C356, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[705]++;
                return eqNumber(((Number)y).doubleValue(), x);

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[706]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[909]++;
int CodeCoverConditionCoverageHelper_C357; if ((((((CodeCoverConditionCoverageHelper_C357 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C357 |= (2)) == 0 || true) &&
 ((y instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C357 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[357].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C357, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[357].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C357, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[707]++;
                return eqString((CharSequence)y, x);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[708]++;}
}
}
}
            // covers the case when y == Undefined.instance as well
            return false;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[686]++;
            warnAboutNonJSObject(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[910]++;
            return x == y;
        }
}
}
}
}
}
    }

    public static boolean isPrimitive(Object obj) {
        return obj == null || obj == Undefined.instance ||
                (obj instanceof Number) || (obj instanceof String) ||
                (obj instanceof Boolean);
    }

    static boolean eqNumber(double x, Object y)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[911]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[82]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[82]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[83]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[84]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[912]++;
int CodeCoverConditionCoverageHelper_C359;
            if ((((((CodeCoverConditionCoverageHelper_C359 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C359 |= (8)) == 0 || true) &&
 ((y == null) && 
  ((CodeCoverConditionCoverageHelper_C359 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C359 |= (2)) == 0 || true) &&
 ((y == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C359 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[359].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C359, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[359].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C359, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[709]++;
                return false;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[710]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[913]++;
int CodeCoverConditionCoverageHelper_C360; if ((((((CodeCoverConditionCoverageHelper_C360 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C360 |= (2)) == 0 || true) &&
 ((y instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C360 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[360].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C360, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[360].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C360, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[711]++;
                return x == ((Number)y).doubleValue();

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[712]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[914]++;
int CodeCoverConditionCoverageHelper_C361; if ((((((CodeCoverConditionCoverageHelper_C361 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C361 |= (2)) == 0 || true) &&
 ((y instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C361 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[361].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C361, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[361].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C361, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[713]++;
                return x == toNumber(y);

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[714]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[915]++;
int CodeCoverConditionCoverageHelper_C362; if ((((((CodeCoverConditionCoverageHelper_C362 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C362 |= (2)) == 0 || true) &&
 ((y instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C362 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[362].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C362, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[362].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C362, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[715]++;
                return x == (((Boolean)y).booleanValue() ? 1.0 : +0.0);

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[716]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[916]++;
int CodeCoverConditionCoverageHelper_C363; if ((((((CodeCoverConditionCoverageHelper_C363 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C363 |= (2)) == 0 || true) &&
 ((y instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C363 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[363].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C363, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[363].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C363, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[717]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[917]++;
int CodeCoverConditionCoverageHelper_C364;
                if ((((((CodeCoverConditionCoverageHelper_C364 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C364 |= (2)) == 0 || true) &&
 ((y instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C364 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[364].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C364, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[364].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C364, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[719]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[918]++;
                    Object xval = wrapNumber(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[919]++;
                    Object test = ((ScriptableObject)y).equivalentValues(xval);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[920]++;
int CodeCoverConditionCoverageHelper_C365;
                    if ((((((CodeCoverConditionCoverageHelper_C365 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C365 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C365 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[365].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C365, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[365].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C365, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[721]++;
                        return ((Boolean)test).booleanValue();

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[722]++;}

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[720]++;}
                y = toPrimitive(y);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[921]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[718]++;
                warnAboutNonJSObject(y);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[922]++;
                return false;
            }
}
}
}
}
        }
    }

    private static boolean eqString(CharSequence x, Object y)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[923]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[85]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[85]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[86]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[87]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[924]++;
int CodeCoverConditionCoverageHelper_C367;
            if ((((((CodeCoverConditionCoverageHelper_C367 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C367 |= (8)) == 0 || true) &&
 ((y == null) && 
  ((CodeCoverConditionCoverageHelper_C367 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C367 |= (2)) == 0 || true) &&
 ((y == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C367 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[367].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C367, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[367].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C367, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[723]++;
                return false;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[724]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[925]++;
int CodeCoverConditionCoverageHelper_C368; if ((((((CodeCoverConditionCoverageHelper_C368 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C368 |= (2)) == 0 || true) &&
 ((y instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C368 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[368].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C368, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[368].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C368, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[725]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[926]++;
                CharSequence c = (CharSequence)y;
                return x.length() == c.length() && x.toString().equals(c.toString());

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[726]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[927]++;
int CodeCoverConditionCoverageHelper_C369; if ((((((CodeCoverConditionCoverageHelper_C369 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C369 |= (2)) == 0 || true) &&
 ((y instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C369 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[369].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C369, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[369].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C369, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[727]++;
                return toNumber(x.toString()) == ((Number)y).doubleValue();

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[728]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[928]++;
int CodeCoverConditionCoverageHelper_C370; if ((((((CodeCoverConditionCoverageHelper_C370 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C370 |= (2)) == 0 || true) &&
 ((y instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C370 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[370].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C370, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[370].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C370, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[729]++;
                return toNumber(x.toString()) == (((Boolean)y).booleanValue() ? 1.0 : 0.0);

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[730]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[929]++;
int CodeCoverConditionCoverageHelper_C371; if ((((((CodeCoverConditionCoverageHelper_C371 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C371 |= (2)) == 0 || true) &&
 ((y instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C371 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[371].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C371, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[371].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C371, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[731]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[930]++;
int CodeCoverConditionCoverageHelper_C372;
                if ((((((CodeCoverConditionCoverageHelper_C372 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C372 |= (2)) == 0 || true) &&
 ((y instanceof ScriptableObject) && 
  ((CodeCoverConditionCoverageHelper_C372 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[372].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C372, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[372].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C372, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[733]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[931]++;
                    Object test = ((ScriptableObject)y).equivalentValues(x.toString());
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[932]++;
int CodeCoverConditionCoverageHelper_C373;
                    if ((((((CodeCoverConditionCoverageHelper_C373 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C373 |= (2)) == 0 || true) &&
 ((test != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C373 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[373].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C373, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[373].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C373, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[735]++;
                        return ((Boolean)test).booleanValue();

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[736]++;}

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[734]++;}
                y = toPrimitive(y);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[933]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[934]++;
                continue;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[732]++;
                warnAboutNonJSObject(y);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[935]++;
                return false;
            }
}
}
}
}
        }
    }
    public static boolean shallowEq(Object x, Object y)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[936]++;
int CodeCoverConditionCoverageHelper_C374;
        if ((((((CodeCoverConditionCoverageHelper_C374 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C374 |= (2)) == 0 || true) &&
 ((x == y) && 
  ((CodeCoverConditionCoverageHelper_C374 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[374].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C374, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[374].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C374, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[737]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[937]++;
int CodeCoverConditionCoverageHelper_C375;
            if ((((((CodeCoverConditionCoverageHelper_C375 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C375 |= (2)) == 0 || true) &&
 ((x instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C375 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[375].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C375, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[375].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C375, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[739]++;
                return true;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[740]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[938]++;
            // NaN check
            double d = ((Number)x).doubleValue();
            return d == d;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[738]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[939]++;
int CodeCoverConditionCoverageHelper_C376;
        if ((((((CodeCoverConditionCoverageHelper_C376 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C376 |= (8)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C376 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C376 |= (2)) == 0 || true) &&
 ((x == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C376 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[376].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C376, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[376].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C376, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[741]++;
            return false;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[742]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[940]++;
int CodeCoverConditionCoverageHelper_C377; if ((((((CodeCoverConditionCoverageHelper_C377 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C377 |= (2)) == 0 || true) &&
 ((x instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C377 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[377].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C377, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[377].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C377, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[743]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[941]++;
int CodeCoverConditionCoverageHelper_C378;
            if ((((((CodeCoverConditionCoverageHelper_C378 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C378 |= (2)) == 0 || true) &&
 ((y instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C378 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[378].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C378, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[378].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C378, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[745]++;
                return ((Number)x).doubleValue() == ((Number)y).doubleValue();

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[746]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[744]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[942]++;
int CodeCoverConditionCoverageHelper_C379; if ((((((CodeCoverConditionCoverageHelper_C379 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C379 |= (2)) == 0 || true) &&
 ((x instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C379 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[379].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C379, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[379].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C379, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[747]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[943]++;
int CodeCoverConditionCoverageHelper_C380;
            if ((((((CodeCoverConditionCoverageHelper_C380 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C380 |= (2)) == 0 || true) &&
 ((y instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C380 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[380].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C380, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[380].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C380, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[749]++;
                return x.toString().equals(y.toString());

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[750]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[748]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[944]++;
int CodeCoverConditionCoverageHelper_C381; if ((((((CodeCoverConditionCoverageHelper_C381 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C381 |= (2)) == 0 || true) &&
 ((x instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C381 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[381].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C381, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[381].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C381, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[751]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[945]++;
int CodeCoverConditionCoverageHelper_C382;
            if ((((((CodeCoverConditionCoverageHelper_C382 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C382 |= (2)) == 0 || true) &&
 ((y instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C382 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[382].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C382, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[382].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C382, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[753]++;
                return x.equals(y);

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[754]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[752]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[946]++;
int CodeCoverConditionCoverageHelper_C383; if ((((((CodeCoverConditionCoverageHelper_C383 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C383 |= (2)) == 0 || true) &&
 ((x instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C383 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[383].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C383, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[383].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C383, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[755]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[947]++;
int CodeCoverConditionCoverageHelper_C384;
            if ((((((CodeCoverConditionCoverageHelper_C384 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C384 |= (8)) == 0 || true) &&
 ((x instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C384 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C384 |= (2)) == 0 || true) &&
 ((y instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C384 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[384].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C384, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[384].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C384, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[757]++;
                return ((Wrapper)x).unwrap() == ((Wrapper)y).unwrap();

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[758]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[756]++;
            warnAboutNonJSObject(x);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[948]++;
            return x == y;
        }
}
}
}
}
        return false;
    }

    /**
     * The instanceof operator.
     *
     * @return a instanceof b
     */
    public static boolean instanceOf(Object a, Object b, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[949]++;
int CodeCoverConditionCoverageHelper_C385;
        // Check RHS is an object
        if ((((((CodeCoverConditionCoverageHelper_C385 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C385 |= (2)) == 0 || true) &&
 ((b instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C385 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[385].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C385, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[385].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C385, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[759]++;
            throw typeError0("msg.instanceof.not.object");

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[760]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[950]++;
int CodeCoverConditionCoverageHelper_C386;

        // for primitive values on LHS, return false
        if ((((((CodeCoverConditionCoverageHelper_C386 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C386 |= (2)) == 0 || true) &&
 ((a instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C386 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[386].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C386, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[386].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C386, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[761]++;
            return false;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[762]++;}

        return ((Scriptable)b).hasInstance((Scriptable)a);
    }

    /**
     * Delegates to
     *
     * @return true iff rhs appears in lhs' proto chain
     */
    public static boolean jsDelegatesTo(Scriptable lhs, Scriptable rhs) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[951]++;
        Scriptable proto = lhs.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[952]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[88]++;


int CodeCoverConditionCoverageHelper_C387;

        while ((((((CodeCoverConditionCoverageHelper_C387 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C387 |= (2)) == 0 || true) &&
 ((proto != null) && 
  ((CodeCoverConditionCoverageHelper_C387 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[387].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C387, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[387].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C387, 1) && false)) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[88]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[89]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[90]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[953]++;
int CodeCoverConditionCoverageHelper_C388;
            if ((((((CodeCoverConditionCoverageHelper_C388 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C388 |= (2)) == 0 || true) &&
 ((proto.equals(rhs)) && 
  ((CodeCoverConditionCoverageHelper_C388 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[388].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C388, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[388].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C388, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[763]++; return true;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[764]++;}
            proto = proto.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[954]++;
        }

        return false;
    }

    /**
     * The in operator.
     *
     * This is a new JS 1.3 language feature.  The in operator mirrors
     * the operation of the for .. in construct, and tests whether the
     * rhs has the property given by the lhs.  It is different from the
     * for .. in construct in that:
     * <BR> - it doesn't perform ToObject on the right hand side
     * <BR> - it returns true for DontEnum properties.
     * @param a the left hand operand
     * @param b the right hand operand
     *
     * @return true if property name or element number a is a property of b
     */
    public static boolean in(Object a, Object b, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[955]++;
int CodeCoverConditionCoverageHelper_C389;
        if ((((((CodeCoverConditionCoverageHelper_C389 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C389 |= (2)) == 0 || true) &&
 ((b instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C389 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[389].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C389, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[389].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C389, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[765]++;
            throw typeError0("msg.in.not.object");

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[766]++;}

        return hasObjectElem((Scriptable)b, a, cx);
    }

    public static boolean cmp_LT(Object val1, Object val2)
    {
        double d1, d2;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[956]++;
int CodeCoverConditionCoverageHelper_C390;
        if ((((((CodeCoverConditionCoverageHelper_C390 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C390 |= (8)) == 0 || true) &&
 ((val1 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C390 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C390 |= (2)) == 0 || true) &&
 ((val2 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C390 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[390].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C390, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[390].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C390, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[767]++;
            d1 = ((Number)val1).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[957]++;
            d2 = ((Number)val2).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[958]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[768]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[959]++;
int CodeCoverConditionCoverageHelper_C391;
            if ((((((CodeCoverConditionCoverageHelper_C391 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C391 |= (2)) == 0 || true) &&
 ((val1 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C391 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[391].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C391, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[391].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C391, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[769]++;
                val1 = ((Scriptable) val1).getDefaultValue(NumberClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[960]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[770]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[961]++;
int CodeCoverConditionCoverageHelper_C392;
            if ((((((CodeCoverConditionCoverageHelper_C392 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C392 |= (2)) == 0 || true) &&
 ((val2 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C392 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[392].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C392, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[392].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C392, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[771]++;
                val2 = ((Scriptable) val2).getDefaultValue(NumberClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[962]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[772]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[963]++;
int CodeCoverConditionCoverageHelper_C393;
            if ((((((CodeCoverConditionCoverageHelper_C393 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C393 |= (8)) == 0 || true) &&
 ((val1 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C393 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C393 |= (2)) == 0 || true) &&
 ((val2 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C393 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[393].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C393, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[393].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C393, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[773]++;
                return val1.toString().compareTo(val2.toString()) < 0;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[774]++;}
            d1 = toNumber(val1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[964]++;
            d2 = toNumber(val2);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[965]++;
        }
        return d1 < d2;
    }

    public static boolean cmp_LE(Object val1, Object val2)
    {
        double d1, d2;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[966]++;
int CodeCoverConditionCoverageHelper_C394;
        if ((((((CodeCoverConditionCoverageHelper_C394 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C394 |= (8)) == 0 || true) &&
 ((val1 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C394 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C394 |= (2)) == 0 || true) &&
 ((val2 instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C394 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[394].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C394, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[394].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C394, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[775]++;
            d1 = ((Number)val1).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[967]++;
            d2 = ((Number)val2).doubleValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[968]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[776]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[969]++;
int CodeCoverConditionCoverageHelper_C395;
            if ((((((CodeCoverConditionCoverageHelper_C395 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C395 |= (2)) == 0 || true) &&
 ((val1 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C395 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[395].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C395, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[395].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C395, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[777]++;
                val1 = ((Scriptable) val1).getDefaultValue(NumberClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[970]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[778]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[971]++;
int CodeCoverConditionCoverageHelper_C396;
            if ((((((CodeCoverConditionCoverageHelper_C396 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C396 |= (2)) == 0 || true) &&
 ((val2 instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C396 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[396].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C396, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[396].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C396, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[779]++;
                val2 = ((Scriptable) val2).getDefaultValue(NumberClass);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[972]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[780]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[973]++;
int CodeCoverConditionCoverageHelper_C397;
            if ((((((CodeCoverConditionCoverageHelper_C397 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C397 |= (8)) == 0 || true) &&
 ((val1 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C397 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C397 |= (2)) == 0 || true) &&
 ((val2 instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C397 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[397].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C397, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[397].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C397, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[781]++;
                return val1.toString().compareTo(val2.toString()) <= 0;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[782]++;}
            d1 = toNumber(val1);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[974]++;
            d2 = toNumber(val2);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[975]++;
        }
        return d1 <= d2;
    }

    // ------------------
    // Statements
    // ------------------

    public static ScriptableObject getGlobal(Context cx) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[976]++;
        final String GLOBAL_CLASS = "org.mozilla.javascript.tools.shell.Global";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[977]++;
        Class<?> globalClass = Kit.classOrNull(GLOBAL_CLASS);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[978]++;
int CodeCoverConditionCoverageHelper_C398;
        if ((((((CodeCoverConditionCoverageHelper_C398 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C398 |= (2)) == 0 || true) &&
 ((globalClass != null) && 
  ((CodeCoverConditionCoverageHelper_C398 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[398].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C398, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[398].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C398, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[783]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[979]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
            try {
CodeCoverTryBranchHelper_Try5 = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[980]++;
                Class<?>[] parm = { ScriptRuntime.ContextClass };
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[981]++;
                Constructor<?> globalClassCtor = globalClass.getConstructor(parm);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[982]++;
                Object[] arg = { cx };
                return (ScriptableObject) globalClassCtor.newInstance(arg);
            }
            catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[786]++;
                throw e;
            }
            catch (Exception e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[787]++;
                // fall through...
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[785]++;
}
  }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[784]++;}
        return new ImporterTopLevel(cx);
    }

    public static boolean hasTopCall(Context cx)
    {
        return (cx.topCallScope != null);
    }

    public static Scriptable getTopCallScope(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[983]++;
        Scriptable scope = cx.topCallScope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[984]++;
int CodeCoverConditionCoverageHelper_C399;
        if ((((((CodeCoverConditionCoverageHelper_C399 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C399 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C399 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[399].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C399, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[399].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C399, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[788]++;
            throw new IllegalStateException();

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[789]++;}
        return scope;
    }

    public static Object doTopCall(Callable callable,
                                   Context cx, Scriptable scope,
                                   Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[985]++;
int CodeCoverConditionCoverageHelper_C400;
        if ((((((CodeCoverConditionCoverageHelper_C400 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C400 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C400 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[400].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C400, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[400].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C400, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[790]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[791]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[986]++;
int CodeCoverConditionCoverageHelper_C401;
        if ((((((CodeCoverConditionCoverageHelper_C401 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C401 |= (2)) == 0 || true) &&
 ((cx.topCallScope != null) && 
  ((CodeCoverConditionCoverageHelper_C401 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[401].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C401, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[401].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C401, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[792]++; throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[793]++;}

        Object result;
        cx.topCallScope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[987]++;
        cx.useDynamicScope = cx.hasFeature(Context.FEATURE_DYNAMIC_SCOPE);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[988]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[989]++;
        ContextFactory f = cx.getFactory();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[990]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
        try {
CodeCoverTryBranchHelper_Try6 = true;
            result = f.doTopCall(callable, cx, scope, thisObj, args);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[991]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[794]++;
}
            cx.topCallScope = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[992]++;
            // Cleanup cached references
            cx.cachedXMLLib = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[993]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[994]++;
int CodeCoverConditionCoverageHelper_C402;

            if ((((((CodeCoverConditionCoverageHelper_C402 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C402 |= (2)) == 0 || true) &&
 ((cx.currentActivationCall != null) && 
  ((CodeCoverConditionCoverageHelper_C402 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[402].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C402, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[402].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C402, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[795]++;
                // Function should always call exitActivationFunction
                // if it creates activation record
                throw new IllegalStateException();

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[796]++;}
        }
        return result;
    }

    /**
     * Return <tt>possibleDynamicScope</tt> if <tt>staticTopScope</tt>
     * is present on its prototype chain and return <tt>staticTopScope</tt>
     * otherwise.
     * Should only be called when <tt>staticTopScope</tt> is top scope.
     */
    static Scriptable checkDynamicScope(Scriptable possibleDynamicScope,
                                        Scriptable staticTopScope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[995]++;
int CodeCoverConditionCoverageHelper_C403;
        // Return cx.topCallScope if scope
        if ((((((CodeCoverConditionCoverageHelper_C403 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C403 |= (2)) == 0 || true) &&
 ((possibleDynamicScope == staticTopScope) && 
  ((CodeCoverConditionCoverageHelper_C403 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[403].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C403, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[403].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C403, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[797]++;
            return possibleDynamicScope;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[798]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[996]++;
        Scriptable proto = possibleDynamicScope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[997]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[91]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[91]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[92]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[93]++;
}
            proto = proto.getPrototype();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[998]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[999]++;
int CodeCoverConditionCoverageHelper_C405;
            if ((((((CodeCoverConditionCoverageHelper_C405 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C405 |= (2)) == 0 || true) &&
 ((proto == staticTopScope) && 
  ((CodeCoverConditionCoverageHelper_C405 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[405].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C405, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[405].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C405, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[799]++;
                return possibleDynamicScope;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[800]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1000]++;
int CodeCoverConditionCoverageHelper_C406;
            if ((((((CodeCoverConditionCoverageHelper_C406 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C406 |= (2)) == 0 || true) &&
 ((proto == null) && 
  ((CodeCoverConditionCoverageHelper_C406 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[406].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C406, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[406].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C406, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[801]++;
                return staticTopScope;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[802]++;}
        }
    }

    public static void addInstructionCount(Context cx, int instructionsToAdd)
    {
    	cx.instructionCount += instructionsToAdd;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1001]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1002]++;
int CodeCoverConditionCoverageHelper_C407;
        if ((((((CodeCoverConditionCoverageHelper_C407 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C407 |= (2)) == 0 || true) &&
 ((cx.instructionCount > cx.instructionThreshold) && 
  ((CodeCoverConditionCoverageHelper_C407 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[407].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C407, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[407].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C407, 1) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[803]++;
            cx.observeInstructionCount(cx.instructionCount);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1003]++;
            cx.instructionCount = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1004]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[804]++;}
    }

    public static void initScript(NativeFunction funObj, Scriptable thisObj,
                                  Context cx, Scriptable scope,
                                  boolean evalScript)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1005]++;
int CodeCoverConditionCoverageHelper_C408;
        if ((((((CodeCoverConditionCoverageHelper_C408 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C408 |= (2)) == 0 || true) &&
 ((cx.topCallScope == null) && 
  ((CodeCoverConditionCoverageHelper_C408 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[408].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C408, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[408].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C408, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[805]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[806]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1006]++;

        int varCount = funObj.getParamAndVarCount();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1007]++;
int CodeCoverConditionCoverageHelper_C409;
        if ((((((CodeCoverConditionCoverageHelper_C409 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C409 |= (2)) == 0 || true) &&
 ((varCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C409 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[409].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C409, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[409].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C409, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[807]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1008]++;

            Scriptable varScope = scope;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1009]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[94]++;


int CodeCoverConditionCoverageHelper_C410;
            // Never define any variables from var statements inside with
            // object. See bug 38590.
            while ((((((CodeCoverConditionCoverageHelper_C410 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C410 |= (2)) == 0 || true) &&
 ((varScope instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C410 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[410].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C410, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[410].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C410, 1) && false)) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[94]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[95]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[96]++;
}
                varScope = varScope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1010]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1011]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[97]++;


int CodeCoverConditionCoverageHelper_C411;

            for (int i = varCount;(((((CodeCoverConditionCoverageHelper_C411 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C411 |= (2)) == 0 || true) &&
 ((i-- != 0) && 
  ((CodeCoverConditionCoverageHelper_C411 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[411].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C411, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[411].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C411, 1) && false);) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[97]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[98]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[99]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1012]++;
                String name = funObj.getParamOrVarName(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1013]++;
                boolean isConst = funObj.getParamOrVarConst(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1014]++;
int CodeCoverConditionCoverageHelper_C412;
                // Don't overwrite existing def if already defined in object
                // or prototypes of object.
                if ((((((CodeCoverConditionCoverageHelper_C412 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C412 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(scope, name)) && 
  ((CodeCoverConditionCoverageHelper_C412 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[412].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C412, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[412].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C412, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[809]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1015]++;
int CodeCoverConditionCoverageHelper_C413;
                    if ((((((CodeCoverConditionCoverageHelper_C413 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C413 |= (2)) == 0 || true) &&
 ((isConst) && 
  ((CodeCoverConditionCoverageHelper_C413 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[413].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C413, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[413].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C413, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[811]++;
                        ScriptableObject.defineConstProperty(varScope, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1016]++;

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[812]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1017]++;
int CodeCoverConditionCoverageHelper_C414; if ((((((CodeCoverConditionCoverageHelper_C414 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C414 |= (2)) == 0 || true) &&
 ((evalScript) && 
  ((CodeCoverConditionCoverageHelper_C414 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[414].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C414, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[414].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C414, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[813]++;
                        // Global var definitions are supposed to be DONTDELETE
                        ScriptableObject.defineProperty(
                            varScope, name, Undefined.instance,
                            ScriptableObject.PERMANENT);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1018]++;

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[814]++;
                        varScope.put(name, varScope, Undefined.instance);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1019]++;
                    }
}

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[810]++;
                    ScriptableObject.redefineProperty(scope, name, isConst);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1020]++;
                }
            }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[808]++;}
    }

    public static Scriptable createFunctionActivation(NativeFunction funObj,
                                                      Scriptable scope,
                                                      Object[] args)
    {
        return new NativeCall(funObj, scope, args);
    }


    public static void enterActivationFunction(Context cx,
                                               Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1021]++;
int CodeCoverConditionCoverageHelper_C415;
        if ((((((CodeCoverConditionCoverageHelper_C415 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C415 |= (2)) == 0 || true) &&
 ((cx.topCallScope == null) && 
  ((CodeCoverConditionCoverageHelper_C415 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[415].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C415, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[415].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C415, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[815]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[816]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1022]++;
        NativeCall call = (NativeCall)scope;
        call.parentActivationCall = cx.currentActivationCall;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1023]++;
        cx.currentActivationCall = call;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1024]++;
    }

    public static void exitActivationFunction(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1025]++;
        NativeCall call = cx.currentActivationCall;
        cx.currentActivationCall = call.parentActivationCall;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1026]++;
        call.parentActivationCall = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1027]++;
    }

    static NativeCall findFunctionActivation(Context cx, Function f)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1028]++;
        NativeCall call = cx.currentActivationCall;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1029]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[100]++;


int CodeCoverConditionCoverageHelper_C416;
        while ((((((CodeCoverConditionCoverageHelper_C416 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C416 |= (2)) == 0 || true) &&
 ((call != null) && 
  ((CodeCoverConditionCoverageHelper_C416 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[416].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C416, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[416].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C416, 1) && false)) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[100]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[101]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[102]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1030]++;
int CodeCoverConditionCoverageHelper_C417;
            if ((((((CodeCoverConditionCoverageHelper_C417 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C417 |= (2)) == 0 || true) &&
 ((call.function == f) && 
  ((CodeCoverConditionCoverageHelper_C417 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[417].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C417, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[417].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C417, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[817]++;
                return call;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[818]++;}
            call = call.parentActivationCall;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1031]++;
        }
        return null;
    }

    public static Scriptable newCatchScope(Throwable t,
                                           Scriptable lastCatchScope,
                                           String exceptionName,
                                           Context cx, Scriptable scope)
    {
        Object obj;
        boolean cacheObj;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1032]++;
int CodeCoverConditionCoverageHelper_C418;

        if ((((((CodeCoverConditionCoverageHelper_C418 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C418 |= (2)) == 0 || true) &&
 ((t instanceof JavaScriptException) && 
  ((CodeCoverConditionCoverageHelper_C418 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[418].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C418, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[418].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C418, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[819]++;
            cacheObj = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1033]++;
            obj = ((JavaScriptException)t).getValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1034]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[820]++;
            cacheObj = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1035]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1036]++;
int CodeCoverConditionCoverageHelper_C419;

            // Create wrapper object unless it was associated with
            // the previous scope object

            if ((((((CodeCoverConditionCoverageHelper_C419 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C419 |= (2)) == 0 || true) &&
 ((lastCatchScope != null) && 
  ((CodeCoverConditionCoverageHelper_C419 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[419].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C419, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[419].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C419, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[821]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1037]++;
                NativeObject last = (NativeObject)lastCatchScope;
                obj = last.getAssociatedValue(t);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1038]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1039]++;
int CodeCoverConditionCoverageHelper_C420;
                if ((((((CodeCoverConditionCoverageHelper_C420 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C420 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C420 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[420].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C420, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[420].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C420, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[823]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1040]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[824]++;}

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[822]++;
                obj = wrapException(t, scope, cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1041]++;
            }
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1042]++;

        NativeObject catchScopeObject = new NativeObject();
        // See ECMA 12.4
        catchScopeObject.defineProperty(
            exceptionName, obj, ScriptableObject.PERMANENT);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1043]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1044]++;
int CodeCoverConditionCoverageHelper_C421;

        if ((((((CodeCoverConditionCoverageHelper_C421 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C421 |= (2)) == 0 || true) &&
 ((isVisible(cx, t)) && 
  ((CodeCoverConditionCoverageHelper_C421 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[421].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C421, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[421].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C421, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[825]++;
            // Add special Rhino object __exception__ defined in the catch
            // scope that can be used to retrieve the Java exception associated
            // with the JavaScript exception (to get stack trace info, etc.)
            catchScopeObject.defineProperty(
                "__exception__", Context.javaToJS(t, scope),
                ScriptableObject.PERMANENT|ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1045]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[826]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1046]++;
int CodeCoverConditionCoverageHelper_C422;

        if ((((((CodeCoverConditionCoverageHelper_C422 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C422 |= (2)) == 0 || true) &&
 ((cacheObj) && 
  ((CodeCoverConditionCoverageHelper_C422 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[422].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C422, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[422].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C422, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[827]++;
            catchScopeObject.associateValue(t, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1047]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[828]++;}
        return catchScopeObject;
    }

    public static Scriptable wrapException(Throwable t,
                                           Scriptable scope,
                                           Context cx) {
        RhinoException re;
        String errorName;
        String errorMsg;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1048]++;
        Throwable javaException = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1049]++;
int CodeCoverConditionCoverageHelper_C423;

        if ((((((CodeCoverConditionCoverageHelper_C423 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C423 |= (2)) == 0 || true) &&
 ((t instanceof EcmaError) && 
  ((CodeCoverConditionCoverageHelper_C423 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[423].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C423, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[423].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C423, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[829]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1050]++;
            EcmaError ee = (EcmaError)t;
            re = ee;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1051]++;
            errorName = ee.getName();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1052]++;
            errorMsg = ee.getErrorMessage();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1053]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[830]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1054]++;
int CodeCoverConditionCoverageHelper_C424; if ((((((CodeCoverConditionCoverageHelper_C424 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C424 |= (2)) == 0 || true) &&
 ((t instanceof WrappedException) && 
  ((CodeCoverConditionCoverageHelper_C424 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[424].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C424, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[424].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C424, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[831]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1055]++;
            WrappedException we = (WrappedException)t;
            re = we;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1056]++;
            javaException = we.getWrappedException();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1057]++;
            errorName = "JavaException";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1058]++;
            errorMsg = javaException.getClass().getName()
                       +": "+javaException.getMessage();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1059]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[832]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1060]++;
int CodeCoverConditionCoverageHelper_C425; if ((((((CodeCoverConditionCoverageHelper_C425 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C425 |= (2)) == 0 || true) &&
 ((t instanceof EvaluatorException) && 
  ((CodeCoverConditionCoverageHelper_C425 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[425].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C425, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[425].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C425, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[833]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1061]++;
            // Pure evaluator exception, nor WrappedException instance
            EvaluatorException ee = (EvaluatorException)t;
            re = ee;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1062]++;
            errorName = "InternalError";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1063]++;
            errorMsg = ee.getMessage();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1064]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[834]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1065]++;
int CodeCoverConditionCoverageHelper_C426; if ((((((CodeCoverConditionCoverageHelper_C426 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C426 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)) && 
  ((CodeCoverConditionCoverageHelper_C426 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[426].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C426, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[426].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C426, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[835]++;
            // With FEATURE_ENHANCED_JAVA_ACCESS, scripts can catch
            // all exception types
            re = new WrappedException(t);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1066]++;
            errorName = "JavaException";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1067]++;
            errorMsg = t.toString();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1068]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[836]++;
            // Script can catch only instances of JavaScriptException,
            // EcmaError and EvaluatorException
            throw Kit.codeBug();
        }
}
}
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1069]++;

        String sourceUri = re.sourceName();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1070]++;
int CodeCoverConditionCoverageHelper_C427;
        if ((((((CodeCoverConditionCoverageHelper_C427 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C427 |= (2)) == 0 || true) &&
 ((sourceUri == null) && 
  ((CodeCoverConditionCoverageHelper_C427 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[427].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C427, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[427].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C427, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[837]++;
            sourceUri = "";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1071]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[838]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1072]++;
        int line = re.lineNumber();
        Object args[];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1073]++;
int CodeCoverConditionCoverageHelper_C428;
        if ((((((CodeCoverConditionCoverageHelper_C428 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C428 |= (2)) == 0 || true) &&
 ((line > 0) && 
  ((CodeCoverConditionCoverageHelper_C428 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[428].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C428, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[428].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C428, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[839]++;
            args = new Object[] { errorMsg, sourceUri, Integer.valueOf(line) };
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1074]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[840]++;
            args = new Object[] { errorMsg, sourceUri };
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1075]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1076]++;

        Scriptable errorObject = cx.newObject(scope, errorName, args);
        ScriptableObject.putProperty(errorObject, "name", errorName);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1077]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1078]++;
int CodeCoverConditionCoverageHelper_C429;
        // set exception in Error objects to enable non-ECMA "stack" property
        if ((((((CodeCoverConditionCoverageHelper_C429 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C429 |= (2)) == 0 || true) &&
 ((errorObject instanceof NativeError) && 
  ((CodeCoverConditionCoverageHelper_C429 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[429].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C429, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[429].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C429, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[841]++;
            ((NativeError) errorObject).setStackProvider(re);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1079]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[842]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1080]++;
int CodeCoverConditionCoverageHelper_C430;

        if ((((((CodeCoverConditionCoverageHelper_C430 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C430 |= (8)) == 0 || true) &&
 ((javaException != null) && 
  ((CodeCoverConditionCoverageHelper_C430 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C430 |= (2)) == 0 || true) &&
 ((isVisible(cx, javaException)) && 
  ((CodeCoverConditionCoverageHelper_C430 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[430].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C430, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[430].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C430, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[843]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1081]++;
            Object wrap = cx.getWrapFactory().wrap(cx, scope, javaException,
                                                   null);
            ScriptableObject.defineProperty(
                errorObject, "javaException", wrap,
                ScriptableObject.PERMANENT | ScriptableObject.READONLY);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1082]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[844]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1083]++;
int CodeCoverConditionCoverageHelper_C431;
        if ((((((CodeCoverConditionCoverageHelper_C431 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C431 |= (2)) == 0 || true) &&
 ((isVisible(cx, re)) && 
  ((CodeCoverConditionCoverageHelper_C431 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[431].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C431, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[431].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C431, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[845]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1084]++;
            Object wrap = cx.getWrapFactory().wrap(cx, scope, re, null);
            ScriptableObject.defineProperty(
                    errorObject, "rhinoException", wrap,
                    ScriptableObject.PERMANENT | ScriptableObject.READONLY);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1085]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[846]++;}
        return errorObject;
    }

    private static boolean isVisible(Context cx, Object obj) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1086]++;
        ClassShutter shutter = cx.getClassShutter();
        return shutter == null ||
            shutter.visibleToScripts(obj.getClass().getName());
    }

    public static Scriptable enterWith(Object obj, Context cx,
                                       Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1087]++;
        Scriptable sobj = toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1088]++;
int CodeCoverConditionCoverageHelper_C432;
        if ((((((CodeCoverConditionCoverageHelper_C432 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C432 |= (2)) == 0 || true) &&
 ((sobj == null) && 
  ((CodeCoverConditionCoverageHelper_C432 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[432].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C432, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[432].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C432, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[847]++;
            throw typeError1("msg.undef.with", toString(obj));

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[848]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1089]++;
int CodeCoverConditionCoverageHelper_C433;
        if ((((((CodeCoverConditionCoverageHelper_C433 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C433 |= (2)) == 0 || true) &&
 ((sobj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C433 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[433].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C433, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[433].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C433, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[849]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1090]++;
            XMLObject xmlObject = (XMLObject)sobj;
            return xmlObject.enterWith(scope);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[850]++;}
        return new NativeWith(scope, sobj);
    }

    public static Scriptable leaveWith(Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1091]++;
        NativeWith nw = (NativeWith)scope;
        return nw.getParentScope();
    }

    public static Scriptable enterDotQuery(Object value, Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1092]++;
int CodeCoverConditionCoverageHelper_C434;
        if ((((((CodeCoverConditionCoverageHelper_C434 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C434 |= (2)) == 0 || true) &&
 ((value instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C434 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[434].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C434, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[434].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C434, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[851]++;
            throw notXmlError(value);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[852]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1093]++;
        XMLObject object = (XMLObject)value;
        return object.enterDotQuery(scope);
    }

    public static Object updateDotQuery(boolean value, Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1094]++;
        // Return null to continue looping
        NativeWith nw = (NativeWith)scope;
        return nw.updateDotQuery(value);
    }

    public static Scriptable leaveDotQuery(Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1095]++;
        NativeWith nw = (NativeWith)scope;
        return nw.getParentScope();
    }

    public static void setFunctionProtoAndParent(BaseFunction fn,
                                                 Scriptable scope)
    {
        fn.setParentScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1096]++;
        fn.setPrototype(ScriptableObject.getFunctionPrototype(scope));
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1097]++;
    }

    public static void setObjectProtoAndParent(ScriptableObject object,
                                               Scriptable scope)
    {
        // Compared with function it always sets the scope to top scope
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1098]++;
        object.setParentScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1099]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1100]++;
        Scriptable proto
            = ScriptableObject.getClassPrototype(scope, object.getClassName());
        object.setPrototype(proto);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1101]++;
    }

    public static void setBuiltinProtoAndParent(ScriptableObject object,
                                                Scriptable scope,
                                                TopLevel.Builtins type)
    {
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1102]++;
        object.setParentScope(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1103]++;
        object.setPrototype(TopLevel.getBuiltinPrototype(scope, type));
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1104]++;
    }


    public static void initFunction(Context cx, Scriptable scope,
                                    NativeFunction function, int type,
                                    boolean fromEvalCode)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1105]++;
int CodeCoverConditionCoverageHelper_C435;
        if ((((((CodeCoverConditionCoverageHelper_C435 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C435 |= (2)) == 0 || true) &&
 ((type == FunctionNode.FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C435 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[435].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C435, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[435].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C435, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[853]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1106]++;
            String name = function.getFunctionName();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1107]++;
int CodeCoverConditionCoverageHelper_C436;
            if ((((((CodeCoverConditionCoverageHelper_C436 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C436 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C436 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C436 |= (2)) == 0 || true) &&
 ((name.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C436 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[436].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C436, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[436].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C436, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[855]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1108]++;
int CodeCoverConditionCoverageHelper_C437;
                if ((((((CodeCoverConditionCoverageHelper_C437 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C437 |= (2)) == 0 || true) &&
 ((fromEvalCode) && 
  ((CodeCoverConditionCoverageHelper_C437 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[437].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C437, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[437].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C437, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[857]++;
                    // ECMA specifies that functions defined in global and
                    // function scope outside eval should have DONTDELETE set.
                    ScriptableObject.defineProperty
                        (scope, name, function, ScriptableObject.PERMANENT);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1109]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[858]++;
                    scope.put(name, scope, function);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1110]++;
                }

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[856]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[854]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1111]++;
int CodeCoverConditionCoverageHelper_C438; if ((((((CodeCoverConditionCoverageHelper_C438 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C438 |= (2)) == 0 || true) &&
 ((type == FunctionNode.FUNCTION_EXPRESSION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C438 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[438].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C438, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[438].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C438, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[859]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1112]++;
            String name = function.getFunctionName();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1113]++;
int CodeCoverConditionCoverageHelper_C439;
            if ((((((CodeCoverConditionCoverageHelper_C439 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C439 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C439 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C439 |= (2)) == 0 || true) &&
 ((name.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C439 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[439].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C439, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[439].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C439, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[861]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1114]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[103]++;


int CodeCoverConditionCoverageHelper_C440;
                // Always put function expression statements into initial
                // activation object ignoring the with statement to follow
                // SpiderMonkey
                while ((((((CodeCoverConditionCoverageHelper_C440 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C440 |= (2)) == 0 || true) &&
 ((scope instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C440 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[440].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C440, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[440].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C440, 1) && false)) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[103]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[104]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[105]++;
}
                    scope = scope.getParentScope();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1115]++;
                }
                scope.put(name, scope, function);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1116]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[862]++;}

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[860]++;
            throw Kit.codeBug();
        }
}
    }

    public static Scriptable newArrayLiteral(Object[] objects,
                                             int[] skipIndices,
                                             Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1117]++;
        final int SKIP_DENSITY = 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1118]++;
        int count = objects.length;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1119]++;
        int skipCount = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1120]++;
int CodeCoverConditionCoverageHelper_C441;
        if ((((((CodeCoverConditionCoverageHelper_C441 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C441 |= (2)) == 0 || true) &&
 ((skipIndices != null) && 
  ((CodeCoverConditionCoverageHelper_C441 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[441].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C441, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[441].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C441, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[863]++;
            skipCount = skipIndices.length;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1121]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[864]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1122]++;
        int length = count + skipCount;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1123]++;
int CodeCoverConditionCoverageHelper_C442;
        if ((((((CodeCoverConditionCoverageHelper_C442 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C442 |= (8)) == 0 || true) &&
 ((length > 1) && 
  ((CodeCoverConditionCoverageHelper_C442 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C442 |= (2)) == 0 || true) &&
 ((skipCount * SKIP_DENSITY < length) && 
  ((CodeCoverConditionCoverageHelper_C442 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[442].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C442, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[442].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C442, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[865]++;
            // If not too sparse, create whole array for constructor
            Object[] sparse;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1124]++;
int CodeCoverConditionCoverageHelper_C443;
            if ((((((CodeCoverConditionCoverageHelper_C443 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C443 |= (2)) == 0 || true) &&
 ((skipCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C443 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[443].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C443, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[443].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C443, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[867]++;
                sparse = objects;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1125]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[868]++;
                sparse = new Object[length];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1126]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1127]++;
                int skip = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1128]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[106]++;


int CodeCoverConditionCoverageHelper_C444;
                for (int i = 0, j = 0;(((((CodeCoverConditionCoverageHelper_C444 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C444 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C444 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[444].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C444, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[444].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C444, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[106]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[107]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[108]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1129]++;
int CodeCoverConditionCoverageHelper_C445;
                    if ((((((CodeCoverConditionCoverageHelper_C445 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C445 |= (8)) == 0 || true) &&
 ((skip != skipCount) && 
  ((CodeCoverConditionCoverageHelper_C445 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C445 |= (2)) == 0 || true) &&
 ((skipIndices[skip] == i) && 
  ((CodeCoverConditionCoverageHelper_C445 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[445].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C445, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[445].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C445, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[869]++;
                        sparse[i] = Scriptable.NOT_FOUND;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1130]++;
                        ++skip;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1131]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1132]++;
                        continue;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[870]++;}
                    sparse[i] = objects[j];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1133]++;
                    ++j;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1134]++;
                }
            }
            return cx.newArray(scope, sparse);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[866]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1135]++;

        Scriptable array = cx.newArray(scope, length);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1136]++;

        int skip = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1137]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[109]++;


int CodeCoverConditionCoverageHelper_C446;
        for (int i = 0, j = 0;(((((CodeCoverConditionCoverageHelper_C446 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C446 |= (2)) == 0 || true) &&
 ((i != length) && 
  ((CodeCoverConditionCoverageHelper_C446 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[446].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C446, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[446].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C446, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[109]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[110]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[111]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1138]++;
int CodeCoverConditionCoverageHelper_C447;
            if ((((((CodeCoverConditionCoverageHelper_C447 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C447 |= (8)) == 0 || true) &&
 ((skip != skipCount) && 
  ((CodeCoverConditionCoverageHelper_C447 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C447 |= (2)) == 0 || true) &&
 ((skipIndices[skip] == i) && 
  ((CodeCoverConditionCoverageHelper_C447 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[447].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C447, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[447].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C447, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[871]++;
                ++skip;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1139]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1140]++;
                continue;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[872]++;}
            ScriptableObject.putProperty(array, i, objects[j]);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1141]++;
            ++j;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1142]++;
        }
        return array;
    }

  /**
   * This method is here for backward compat with existing compiled code.  It
   * is called when an object literal is compiled.  The next instance will be
   * the version called from new code.
   * @deprecated This method only present for compatibility.
   */
    public static Scriptable newObjectLiteral(Object[] propertyIds,
                                              Object[] propertyValues,
                                              Context cx, Scriptable scope)
    {
        // Passing null for getterSetters means no getters or setters
        return newObjectLiteral(propertyIds, propertyValues, null, cx, scope);

    }

    public static Scriptable newObjectLiteral(Object[] propertyIds,
                                              Object[] propertyValues,
                                              int [] getterSetters,
                                              Context cx, Scriptable scope)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1143]++;
        Scriptable object = cx.newObject(scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1144]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[112]++;


int CodeCoverConditionCoverageHelper_C448;
        for (int i = 0, end = propertyIds.length;(((((CodeCoverConditionCoverageHelper_C448 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C448 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C448 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[448].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C448, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[448].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C448, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[112]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[113]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[114]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1145]++;
            Object id = propertyIds[i];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1146]++;
            int getterSetter = getterSetters == null ? 0 : getterSetters[i];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1147]++;
            Object value = propertyValues[i];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1148]++;
int CodeCoverConditionCoverageHelper_C449;
            if ((((((CodeCoverConditionCoverageHelper_C449 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C449 |= (2)) == 0 || true) &&
 ((id instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C449 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[449].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C449, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[449].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C449, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[873]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1149]++;
int CodeCoverConditionCoverageHelper_C450;
                if ((((((CodeCoverConditionCoverageHelper_C450 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C450 |= (2)) == 0 || true) &&
 ((getterSetter == 0) && 
  ((CodeCoverConditionCoverageHelper_C450 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[450].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C450, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[450].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C450, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[875]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1150]++;
int CodeCoverConditionCoverageHelper_C451;
                    if ((((((CodeCoverConditionCoverageHelper_C451 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C451 |= (2)) == 0 || true) &&
 ((isSpecialProperty((String)id)) && 
  ((CodeCoverConditionCoverageHelper_C451 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[451].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C451, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[451].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C451, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[877]++;
                        specialRef(object, (String)id, cx).set(cx, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1151]++;

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[878]++;
                        object.put((String)id, object, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1152]++;
                    }

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[876]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1153]++;
                    ScriptableObject so = (ScriptableObject)object;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1154]++;
                    Callable getterOrSetter = (Callable)value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1155]++;
                    boolean isSetter = getterSetter == 1;
                    so.setGetterOrSetter((String)id, 0, getterOrSetter, isSetter);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1156]++;
                }

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[874]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1157]++;
                int index = ((Integer)id).intValue();
                object.put(index, object, value);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1158]++;
            }
        }
        return object;
    }

    public static boolean isArrayObject(Object obj)
    {
        return obj instanceof NativeArray || obj instanceof Arguments;
    }

    public static Object[] getArrayElements(Scriptable object)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1159]++;
        Context cx = Context.getContext();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1160]++;
        long longLen = NativeArray.getLengthProperty(cx, object);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1161]++;
int CodeCoverConditionCoverageHelper_C452;
        if ((((((CodeCoverConditionCoverageHelper_C452 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C452 |= (2)) == 0 || true) &&
 ((longLen > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C452 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[452].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C452, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[452].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C452, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[879]++;
            // arrays beyond  MAX_INT is not in Java in any case
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[880]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1162]++;
        int len = (int) longLen;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1163]++;
int CodeCoverConditionCoverageHelper_C453;
        if ((((((CodeCoverConditionCoverageHelper_C453 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C453 |= (2)) == 0 || true) &&
 ((len == 0) && 
  ((CodeCoverConditionCoverageHelper_C453 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[453].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C453, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[453].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C453, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[881]++;
            return ScriptRuntime.emptyArgs;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[882]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1164]++;
            Object[] result = new Object[len];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1165]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[115]++;


int CodeCoverConditionCoverageHelper_C454;
            for (int i=0;(((((CodeCoverConditionCoverageHelper_C454 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C454 |= (2)) == 0 || true) &&
 ((i < len) && 
  ((CodeCoverConditionCoverageHelper_C454 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[454].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C454, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[454].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C454, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[115]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[116]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.loops[117]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1166]++;
                Object elem = ScriptableObject.getProperty(object, i);
                result[i] = (elem == Scriptable.NOT_FOUND) ? Undefined.instance
                                                           : elem;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1167]++;
            }
            return result;
        }
    }

    static void checkDeprecated(Context cx, String name) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1168]++;
        int version = cx.getLanguageVersion();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1169]++;
int CodeCoverConditionCoverageHelper_C455;
        if ((((((CodeCoverConditionCoverageHelper_C455 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C455 |= (8)) == 0 || true) &&
 ((version >= Context.VERSION_1_4) && 
  ((CodeCoverConditionCoverageHelper_C455 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C455 |= (2)) == 0 || true) &&
 ((version == Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C455 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[455].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C455, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[455].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C455, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[883]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1170]++;
            String msg = getMessage1("msg.deprec.ctor", name);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1171]++;
int CodeCoverConditionCoverageHelper_C456;
            if ((((((CodeCoverConditionCoverageHelper_C456 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C456 |= (2)) == 0 || true) &&
 ((version == Context.VERSION_DEFAULT) && 
  ((CodeCoverConditionCoverageHelper_C456 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[456].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C456, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[456].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C456, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[885]++;
                Context.reportWarning(msg);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1172]++;
}
            else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[886]++;
                throw Context.reportRuntimeError(msg);
}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[884]++;}
    }

    public static String getMessage0(String messageId)
    {
        return getMessage(messageId, null);
    }

    public static String getMessage1(String messageId, Object arg1)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1173]++;
        Object[] arguments = {arg1};
        return getMessage(messageId, arguments);
    }

    public static String getMessage2(
        String messageId, Object arg1, Object arg2)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1174]++;
        Object[] arguments = {arg1, arg2};
        return getMessage(messageId, arguments);
    }

    public static String getMessage3(
        String messageId, Object arg1, Object arg2, Object arg3)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1175]++;
        Object[] arguments = {arg1, arg2, arg3};
        return getMessage(messageId, arguments);
    }

    public static String getMessage4(
        String messageId, Object arg1, Object arg2, Object arg3, Object arg4)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1176]++;
        Object[] arguments = {arg1, arg2, arg3, arg4};
        return getMessage(messageId, arguments);
    }

    /**
     * This is an interface defining a message provider. Create your
     * own implementation to override the default error message provider.
     *
     */
    public interface MessageProvider {

        /**
         * Returns a textual message identified by the given messageId,
         * parameterized by the given arguments.
         *
         * @param messageId the identifier of the message
         * @param arguments the arguments to fill into the message
         */
        String getMessage(String messageId, Object[] arguments);
    }

    public static MessageProvider messageProvider = new DefaultMessageProvider();
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1177]++;
  }

    public static String getMessage(String messageId, Object[] arguments)
    {
        return messageProvider.getMessage(messageId, arguments);
    }

    /* OPT there's a noticable delay for the first error!  Maybe it'd
     * make sense to use a ListResourceBundle instead of a properties
     * file to avoid (synchronized) text parsing.
     */
    private static class DefaultMessageProvider implements MessageProvider {
        public String getMessage(String messageId, Object[] arguments) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1178]++;
            final String defaultResource
                = "org.mozilla.javascript.resources.Messages";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1179]++;

            Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1180]++;
            Locale locale = cx != null ? cx.getLocale() : Locale.getDefault();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1181]++;

            // ResourceBundle does caching.
            ResourceBundle rb = ResourceBundle.getBundle(defaultResource, locale);

            String formatString;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1182]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
            try {
CodeCoverTryBranchHelper_Try7 = true;
                formatString = rb.getString(messageId);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1183]++;
            } catch (java.util.MissingResourceException mre) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[888]++;
                throw new RuntimeException
                    ("no message resource found for message property "+ messageId);
            } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[887]++;
}
  }
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1184]++;

            /*
             * It's OK to format the string, even if 'arguments' is null;
             * we need to format it anyway, to make double ''s collapse to
             * single 's.
             */
            MessageFormat formatter = new MessageFormat(formatString);
            return formatter.format(arguments);
        }
    }

    public static EcmaError constructError(String error, String message)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1185]++;
        int[] linep = new int[1];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1186]++;
        String filename = Context.getSourcePositionFromStack(linep);
        return constructError(error, message, filename, linep[0], null, 0);
    }

    public static EcmaError constructError(String error,
                                           String message,
                                           int lineNumberDelta)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1187]++;
        int[] linep = new int[1];
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1188]++;
        String filename = Context.getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1189]++;
int CodeCoverConditionCoverageHelper_C457;
        if ((((((CodeCoverConditionCoverageHelper_C457 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C457 |= (2)) == 0 || true) &&
 ((linep[0] != 0) && 
  ((CodeCoverConditionCoverageHelper_C457 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[457].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C457, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[457].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C457, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[889]++;
            linep[0] += lineNumberDelta;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1190]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[890]++;}
        return constructError(error, message, filename, linep[0], null, 0);
    }

    public static EcmaError constructError(String error,
                                           String message,
                                           String sourceName,
                                           int lineNumber,
                                           String lineSource,
                                           int columnNumber)
    {
        return new EcmaError(error, message, sourceName,
                             lineNumber, lineSource, columnNumber);
    }

    public static EcmaError typeError(String message)
    {
        return constructError("TypeError", message);
    }

    public static EcmaError typeError0(String messageId)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1191]++;
        String msg = getMessage0(messageId);
        return typeError(msg);
    }

    public static EcmaError typeError1(String messageId, String arg1)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1192]++;
        String msg = getMessage1(messageId, arg1);
        return typeError(msg);
    }

    public static EcmaError typeError2(String messageId, String arg1,
                                       String arg2)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1193]++;
        String msg = getMessage2(messageId, arg1, arg2);
        return typeError(msg);
    }

    public static EcmaError typeError3(String messageId, String arg1,
                                       String arg2, String arg3)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1194]++;
        String msg = getMessage3(messageId, arg1, arg2, arg3);
        return typeError(msg);
    }

    public static RuntimeException undefReadError(Object object, Object id)
    {
        return typeError2("msg.undef.prop.read", toString(object), toString(id));
    }

    public static RuntimeException undefCallError(Object object, Object id)
    {
        return typeError2("msg.undef.method.call", toString(object), toString(id));
    }

    public static RuntimeException undefWriteError(Object object,
                                                   Object id,
                                                   Object value)
    {
        return typeError3("msg.undef.prop.write", toString(object), toString(id),
                          toString(value));
    }

    private static RuntimeException undefDeleteError(Object object, Object id)
    {
        throw typeError2("msg.undef.prop.delete", toString(object), toString(id));
    }

    public static RuntimeException notFoundError(Scriptable object,
                                                 String property)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1195]++;
        // XXX: use object to improve the error message
        String msg = getMessage1("msg.is.not.defined", property);
        throw constructError("ReferenceError", msg);
    }

    public static RuntimeException notFunctionError(Object value)
    {
        return notFunctionError(value, value);
    }

    public static RuntimeException notFunctionError(Object value,
                                                    Object messageHelper)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1196]++;
        // Use value for better error reporting
        String msg = (messageHelper == null)
                     ? "null" : messageHelper.toString();
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1197]++;
int CodeCoverConditionCoverageHelper_C458;
        if ((((((CodeCoverConditionCoverageHelper_C458 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C458 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C458 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[458].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C458, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[458].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C458, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[891]++;
            return typeError1("msg.function.not.found", msg);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[892]++;}
        return typeError2("msg.isnt.function", msg, typeof(value));
    }

    public static RuntimeException notFunctionError(Object obj, Object value,
            String propertyName)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1198]++;
        // Use obj and value for better error reporting
        String objString = toString(obj);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1199]++;
int CodeCoverConditionCoverageHelper_C459;
        if ((((((CodeCoverConditionCoverageHelper_C459 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C459 |= (2)) == 0 || true) &&
 ((obj instanceof NativeFunction) && 
  ((CodeCoverConditionCoverageHelper_C459 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[459].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C459, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[459].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C459, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[893]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1200]++;
            // Omit function body in string representations of functions
            int paren = objString.indexOf(')');
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1201]++;
            int curly = objString.indexOf('{', paren);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1202]++;
int CodeCoverConditionCoverageHelper_C460;
            if ((((((CodeCoverConditionCoverageHelper_C460 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C460 |= (2)) == 0 || true) &&
 ((curly > -1) && 
  ((CodeCoverConditionCoverageHelper_C460 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[460].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C460, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[460].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C460, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[895]++;
                objString = objString.substring(0, curly + 1) + "...}";
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1203]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[896]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[894]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1204]++;
int CodeCoverConditionCoverageHelper_C461;
        if ((((((CodeCoverConditionCoverageHelper_C461 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C461 |= (2)) == 0 || true) &&
 ((value == Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C461 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[461].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C461, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[461].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C461, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[897]++;
            return typeError2("msg.function.not.found.in", propertyName,
                    objString);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[898]++;}
        return typeError3("msg.isnt.function.in", propertyName, objString,
                          typeof(value));
    }

    private static RuntimeException notXmlError(Object value)
    {
        throw typeError1("msg.isnt.xml.object", toString(value));
    }

    private static void warnAboutNonJSObject(Object nonJSObject)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1205]++;
        String message =
"RHINO USAGE WARNING: Missed Context.javaToJS() conversion:\n"
+"Rhino runtime detected object "+nonJSObject+" of class "+nonJSObject.getClass().getName()+" where it expected String, Number, Boolean or Scriptable instance. Please check your code for missing Context.javaToJS() call.";
        Context.reportWarning(message);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1206]++;
        // Just to be sure that it would be noticed
        System.err.println(message);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1207]++;
    }

    public static RegExpProxy getRegExpProxy(Context cx)
    {
        return cx.getRegExpProxy();
    }

    public static void setRegExpProxy(Context cx, RegExpProxy proxy)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1208]++;
int CodeCoverConditionCoverageHelper_C462;
        if ((((((CodeCoverConditionCoverageHelper_C462 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C462 |= (2)) == 0 || true) &&
 ((proxy == null) && 
  ((CodeCoverConditionCoverageHelper_C462 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[462].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C462, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[462].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C462, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[899]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[900]++;}
        cx.regExpProxy = proxy;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1209]++;
    }

    public static RegExpProxy checkRegExpProxy(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1210]++;
        RegExpProxy result = getRegExpProxy(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1211]++;
int CodeCoverConditionCoverageHelper_C463;
        if ((((((CodeCoverConditionCoverageHelper_C463 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C463 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C463 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[463].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C463, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[463].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C463, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[901]++;
            throw Context.reportRuntimeError0("msg.no.regexp");

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[902]++;}
        return result;
    }

    public static Scriptable wrapRegExp(Context cx, Scriptable scope,
                                        Object compiled) {
        return cx.getRegExpProxy().wrapRegExp(cx, scope, compiled);
    }

    private static XMLLib currentXMLLib(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1212]++;
int CodeCoverConditionCoverageHelper_C464;
        // Scripts should be running to access this
        if ((((((CodeCoverConditionCoverageHelper_C464 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C464 |= (2)) == 0 || true) &&
 ((cx.topCallScope == null) && 
  ((CodeCoverConditionCoverageHelper_C464 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[464].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C464, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[464].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C464, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[903]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[904]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1213]++;

        XMLLib xmlLib = cx.cachedXMLLib;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1214]++;
int CodeCoverConditionCoverageHelper_C465;
        if ((((((CodeCoverConditionCoverageHelper_C465 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C465 |= (2)) == 0 || true) &&
 ((xmlLib == null) && 
  ((CodeCoverConditionCoverageHelper_C465 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[465].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C465, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[465].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C465, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[905]++;
            xmlLib = XMLLib.extractFromScope(cx.topCallScope);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1215]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1216]++;
int CodeCoverConditionCoverageHelper_C466;
            if ((((((CodeCoverConditionCoverageHelper_C466 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C466 |= (2)) == 0 || true) &&
 ((xmlLib == null) && 
  ((CodeCoverConditionCoverageHelper_C466 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[466].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C466, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[466].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C466, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[907]++;
                throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[908]++;}
            cx.cachedXMLLib = xmlLib;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1217]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[906]++;}

        return xmlLib;
    }

    /**
     * Escapes the reserved characters in a value of an attribute
     *
     * @param value Unescaped text
     * @return The escaped text
     */
    public static String escapeAttributeValue(Object value, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1218]++;
        XMLLib xmlLib = currentXMLLib(cx);
        return xmlLib.escapeAttributeValue(value);
    }

    /**
     * Escapes the reserved characters in a value of a text node
     *
     * @param value Unescaped text
     * @return The escaped text
     */
    public static String escapeTextValue(Object value, Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1219]++;
        XMLLib xmlLib = currentXMLLib(cx);
        return xmlLib.escapeTextValue(value);
    }

    public static Ref memberRef(Object obj, Object elem,
                                Context cx, int memberTypeFlags)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1220]++;
int CodeCoverConditionCoverageHelper_C467;
        if ((((((CodeCoverConditionCoverageHelper_C467 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C467 |= (2)) == 0 || true) &&
 ((obj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C467 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[467].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C467, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[467].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C467, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[909]++;
            throw notXmlError(obj);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[910]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1221]++;
        XMLObject xmlObject = (XMLObject)obj;
        return xmlObject.memberRef(cx, elem, memberTypeFlags);
    }

    public static Ref memberRef(Object obj, Object namespace, Object elem,
                                Context cx, int memberTypeFlags)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1222]++;
int CodeCoverConditionCoverageHelper_C468;
        if ((((((CodeCoverConditionCoverageHelper_C468 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C468 |= (2)) == 0 || true) &&
 ((obj instanceof XMLObject) && 
  ((CodeCoverConditionCoverageHelper_C468 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[468].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C468, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[468].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C468, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[911]++;
            throw notXmlError(obj);

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[912]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1223]++;
        XMLObject xmlObject = (XMLObject)obj;
        return xmlObject.memberRef(cx, namespace, elem, memberTypeFlags);
    }

    public static Ref nameRef(Object name, Context cx,
                              Scriptable scope, int memberTypeFlags)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1224]++;
        XMLLib xmlLib = currentXMLLib(cx);
        return xmlLib.nameRef(cx, name, scope, memberTypeFlags);
    }

    public static Ref nameRef(Object namespace, Object name, Context cx,
                              Scriptable scope, int memberTypeFlags)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1225]++;
        XMLLib xmlLib = currentXMLLib(cx);
        return xmlLib.nameRef(cx, namespace, name, scope, memberTypeFlags);
    }

    private static void storeIndexResult(Context cx, int index)
    {
        cx.scratchIndex = index;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1226]++;
    }

    static int lastIndexResult(Context cx)
    {
        return cx.scratchIndex;
    }

    public static void storeUint32Result(Context cx, long value)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1227]++;
int CodeCoverConditionCoverageHelper_C469;
        if ((((((CodeCoverConditionCoverageHelper_C469 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C469 |= (2)) == 0 || true) &&
 (((value >>> 32) != 0) && 
  ((CodeCoverConditionCoverageHelper_C469 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[469].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C469, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[469].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C469, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[913]++;
            throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[914]++;}
        cx.scratchUint32 = value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1228]++;
    }

    public static long lastUint32Result(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1229]++;
        long value = cx.scratchUint32;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1230]++;
int CodeCoverConditionCoverageHelper_C470;
        if ((((((CodeCoverConditionCoverageHelper_C470 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C470 |= (2)) == 0 || true) &&
 (((value >>> 32) != 0) && 
  ((CodeCoverConditionCoverageHelper_C470 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[470].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C470, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[470].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C470, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[915]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[916]++;}
        return value;
    }

    private static void storeScriptable(Context cx, Scriptable value)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1231]++;
int CodeCoverConditionCoverageHelper_C471;
        // The previously stored scratchScriptable should be consumed
        if ((((((CodeCoverConditionCoverageHelper_C471 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C471 |= (2)) == 0 || true) &&
 ((cx.scratchScriptable != null) && 
  ((CodeCoverConditionCoverageHelper_C471 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[471].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C471, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[471].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C471, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[917]++;
            throw new IllegalStateException();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[918]++;}
        cx.scratchScriptable = value;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1232]++;
    }

    public static Scriptable lastStoredScriptable(Context cx)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1233]++;
        Scriptable result = cx.scratchScriptable;
        cx.scratchScriptable = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1234]++;
        return result;
    }

    static String makeUrlForGeneratedScript
        (boolean isEval, String masterScriptUrl, int masterScriptLine)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1235]++;
int CodeCoverConditionCoverageHelper_C472;
        if ((((((CodeCoverConditionCoverageHelper_C472 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C472 |= (2)) == 0 || true) &&
 ((isEval) && 
  ((CodeCoverConditionCoverageHelper_C472 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[472].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C472, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.conditionCounters[472].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C472, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[919]++;
            return masterScriptUrl+'#'+masterScriptLine+"(eval)";

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.branches[920]++;
            return masterScriptUrl+'#'+masterScriptLine+"(Function)";
        }
    }

    static boolean isGeneratedScript(String sourceUrl) {
        // ALERT: this may clash with a valid URL containing (eval) or
        // (Function)
        return sourceUrl.indexOf("(eval)") >= 0
               || sourceUrl.indexOf("(Function)") >= 0;
    }

    private static RuntimeException errorWithClassName(String msg, Object val)
    {
        return Context.reportRuntimeError1(msg, val.getClass().getName());
    }

    /**
     * Equivalent to executing "new Error(message)" from JavaScript.
     * @param cx the current context
     * @param scope the current scope
     * @param message the message
     * @return a JavaScriptException you should throw
     */
    public static JavaScriptException throwError(Context cx, Scriptable scope,
            String message) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1236]++;
      int[] linep = { 0 };
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1237]++;
      String filename = Context.getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1238]++;
        final Scriptable error = newBuiltinObject(cx, scope,
                TopLevel.Builtins.Error, new Object[] { message, filename, Integer.valueOf(linep[0]) });
        return new JavaScriptException(error, filename, linep[0]);
    }

    public static final Object[] emptyArgs = new Object[0];
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1239]++;
  }
    public static final String[] emptyStrings = new String[0];
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd.statements[1240]++;
  }

}

class CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd ());
  }
    public static long[] statements = new long[1241];
    public static long[] branches = new long[921];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[473];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ScriptRuntime.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,0,1,2,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,3,0,1,1,2,2,1,1,1,1,2,1,0,1,1,1,1,2,2,2,2,0,1,2,2,1,3,1,1,1,2,1,3,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,3,1,3,1,3,1,1,1,0,1,1,1,1,1,1,1,1,1,3,1,0,3,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1,1,3,1,1,1,1,0,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,0,2,1,1,1,1,1,1,0,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,2,1,1,2,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,2,1,1,2,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 472; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[118];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16ld41j2yrbjv1lg0gvvyznja43orxd () {
    super("org.mozilla.javascript.RHINO-SRC-ScriptRuntime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1240; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 920; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 472; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 117; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ScriptRuntime.java");
      for (int i = 1; i <= 1240; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 920; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 472; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 39; i++) {
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

