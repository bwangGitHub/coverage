/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * Embeddings that wish to provide their own custom wrappings for Java
 * objects may extend this class and call
 * {@link Context#setWrapFactory(WrapFactory)}
 * Once an instance of this class or an extension of this class is enabled
 * for a given context (by calling setWrapFactory on that context), Rhino
 * will call the methods of this class whenever it needs to wrap a value
 * resulting from a call to a Java method or an access to a Java field.
 *
 * @see org.mozilla.javascript.Context#setWrapFactory(WrapFactory)
 * @since 1.5 Release 4
 */
public class WrapFactory
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.ping();
  }

    /**
     * Wrap the object.
     * <p>
     * The value returned must be one of
     * <UL>
     * <LI>java.lang.Boolean</LI>
     * <LI>java.lang.String</LI>
     * <LI>java.lang.Number</LI>
     * <LI>org.mozilla.javascript.Scriptable objects</LI>
     * <LI>The value returned by Context.getUndefinedValue()</LI>
     * <LI>null</LI>
     * </UL>
     * @param cx the current Context for this thread
     * @param scope the scope of the executing script
     * @param obj the object to be wrapped. Note it can be null.
     * @param staticType type hint. If security restrictions prevent to wrap
              object based on its class, staticType will be used instead.
     * @return the wrapped value.
     */
    public Object wrap(Context cx, Scriptable scope,
                       Object obj, Class<?> staticType)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((obj == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[1]++;
            return obj;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[2]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((staticType != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((staticType.isPrimitive()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[3]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[3]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((staticType == Void.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[5]++;
                return Undefined.instance;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[6]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[4]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((staticType == Character.TYPE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[7]++;
                return Integer.valueOf(((Character) obj).charValue());
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[8]++;}
            return obj;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[4]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[5]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isJavaPrimitiveWrap()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[9]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[6]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((obj instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((obj instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((obj instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[11]++;
                return obj;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[12]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[7]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((obj instanceof Character) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[13]++;
                return String.valueOf(((Character)obj).charValue());

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[14]++;}
}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[10]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[8]++;
        Class<?> cls = obj.getClass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[9]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cls.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[15]++;
            return NativeJavaArray.wrap(scope, obj);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[16]++;}
        return wrapAsJavaObject(cx, scope, obj, staticType);
    }

    /**
     * Wrap an object newly created by a constructor call.
     * @param cx the current Context for this thread
     * @param scope the scope of the executing script
     * @param obj the object to be wrapped
     * @return the wrapped value.
     */
    public Scriptable wrapNewObject(Context cx, Scriptable scope, Object obj)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[10]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((obj instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[17]++;
            return (Scriptable)obj;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[18]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[11]++;
        Class<?> cls = obj.getClass();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[12]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cls.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[19]++;
            return NativeJavaArray.wrap(scope, obj);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[20]++;}
        return wrapAsJavaObject(cx, scope, obj, null);
    }

    /**
     * Wrap Java object as Scriptable instance to allow full access to its
     * methods and fields from JavaScript.
     * <p>
     * {@link #wrap(Context, Scriptable, Object, Class)} and
     * {@link #wrapNewObject(Context, Scriptable, Object)} call this method
     * when they can not convert <tt>javaObject</tt> to JavaScript primitive
     * value or JavaScript array.
     * <p>
     * Subclasses can override the method to provide custom wrappers
     * for Java objects.
     * @param cx the current Context for this thread
     * @param scope the scope of the executing script
     * @param javaObject the object to be wrapped
     * @param staticType type hint. If security restrictions prevent to wrap
                object based on its class, staticType will be used instead.
     * @return the wrapped value which shall not be null
     */
    public Scriptable wrapAsJavaObject(Context cx, Scriptable scope,
                                       Object javaObject, Class<?> staticType)
    {
        return new NativeJavaObject(scope, javaObject, staticType);
    }

    /**
     * Wrap a Java class as Scriptable instance to allow access to its static
     * members and fields and use as constructor from JavaScript.
     * <p>
     * Subclasses can override this method to provide custom wrappers for
     * Java classes.
     *
     * @param cx the current Context for this thread
     * @param scope the scope of the executing script
     * @param javaClass the class to be wrapped
     * @return the wrapped value which shall not be null
     * @since 1.7R3
     */
    public Scriptable wrapJavaClass(Context cx, Scriptable scope,
                                    Class javaClass)
    {
        return new NativeJavaClass(scope, javaClass);
    }

    /**
     * Return <code>false</code> if result of Java method, which is instance of
     * <code>String</code>, <code>Number</code>, <code>Boolean</code> and
     * <code>Character</code>, should be used directly as JavaScript primitive
     * type.
     * By default the method returns true to indicate that instances of
     * <code>String</code>, <code>Number</code>, <code>Boolean</code> and
     * <code>Character</code> should be wrapped as any other Java object and
     * scripts can access any Java method available in these objects.
     * Use {@link #setJavaPrimitiveWrap(boolean)} to change this.
     */
    public final boolean isJavaPrimitiveWrap()
    {
        return javaPrimitiveWrap;
    }

    /**
     * @see #isJavaPrimitiveWrap()
     */
    public final void setJavaPrimitiveWrap(boolean value)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[13]++;
        Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[14]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((cx != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cx.isSealed()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[21]++;
            Context.onSealedMutation();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[15]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.branches[22]++;}
        javaPrimitiveWrap = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[16]++;
    }

    private boolean javaPrimitiveWrap = true;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd.statements[17]++;
  }

}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-WrapFactory.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,2,1,1,1,3,1,1,1,1,2};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9idos4k6kuf7i2zfz0l7cqdpgtd () {
    super("org.mozilla.javascript.RHINO-SRC-WrapFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-WrapFactory.java");
      for (int i = 1; i <= 17; i++) {
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
    for (int i = 1; i <= 11; i++) {
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

