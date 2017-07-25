/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Collection of utilities
 */

public class Kit
{
  static {
    CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.ping();
  }

    /**
     * Reflection of Throwable.initCause(Throwable) from JDK 1.4
     * or nul if it is not available.
     */
    private static Method Throwable_initCause = null;
  static {
    CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[1]++;
  }

    static {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[2]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        // Are we running on a JDK 1.4 or later system?
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[3]++;
            Class<?> ThrowableClass = Kit.classOrNull("java.lang.Throwable");
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[4]++;
            Class<?>[] signature = { ThrowableClass };
            Throwable_initCause
                = ThrowableClass.getMethod("initCause", signature);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[5]++;
        } catch (Exception ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[2]++;
            // Assume any exceptions means the method does not exist.
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[1]++;
}
  }
    }

    public static Class<?> classOrNull(String className)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[6]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            return Class.forName(className);
        } catch  (ClassNotFoundException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[4]++;
        } catch  (SecurityException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[5]++;
        } catch  (LinkageError ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[6]++;
        } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[7]++;
            // Can be thrown if name has characters that a class name
            // can not contain
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[3]++;
}
  }
        return null;
    }

    /**
     * Attempt to load the class of the given name. Note that the type parameter
     * isn't checked.
     */
    public static Class<?> classOrNull(ClassLoader loader, String className)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[7]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
        try {
CodeCoverTryBranchHelper_Try3 = true;
            return loader.loadClass(className);
        } catch (ClassNotFoundException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[9]++;
        } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[10]++;
        } catch (LinkageError ex) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[11]++;
        } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[12]++;
            // Can be thrown if name has characters that a class name
            // can not contain
        } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[8]++;
}
  }
        return null;
    }

    static Object newInstanceOrNull(Class<?> cl)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[8]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
            return cl.newInstance();
        } catch (SecurityException x) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[14]++;
        } catch  (LinkageError ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[15]++;
        } catch (InstantiationException x) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[16]++;
        } catch (IllegalAccessException x) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[17]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[13]++;
}
  }
        return null;
    }

    /**
     * Check that testClass is accessible from the given loader.
     */
    static boolean testIfCanLoadRhinoClasses(ClassLoader loader)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[9]++;
        Class<?> testClass = ScriptRuntime.ContextFactoryClass;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[10]++;
        Class<?> x = Kit.classOrNull(loader, testClass.getName());
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((x != testClass) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[18]++;
            // The check covers the case when x == null =>
            // loader does not know about testClass or the case
            // when x != null && x != testClass =>
            // loader loads a class unrelated to testClass
            return false;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[19]++;}
        return true;
    }

    /**
     * If initCause methods exists in Throwable, call
     * <tt>ex.initCause(cause)</tt> or otherwise do nothing.
     * @return The <tt>ex</tt> argument.
     */
    public static RuntimeException initCause(RuntimeException ex,
                                             Throwable cause)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Throwable_initCause != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[20]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[13]++;
            Object[] args = { cause };
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[14]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
            try {
CodeCoverTryBranchHelper_Try5 = true;
                Throwable_initCause.invoke(ex, args);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[15]++;
            } catch (Exception e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[23]++;
                // Ignore any exceptions
            } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[22]++;
}
  }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[21]++;}
        return ex;
    }

    /**
     * If character <tt>c</tt> is a hexadecimal digit, return
     * <tt>accumulator</tt> * 16 plus corresponding
     * number. Otherise return -1.
     */
    public static int xDigitToInt(int c, int accumulator)
    {
        check: {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
            // Use 0..9 < A..Z < a..z
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c <= '9') && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[24]++;
                c -= '0';
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[17]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((0 <= c) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[26]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[19]++; break check;
 } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[27]++;}

            } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[25]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[20]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c <= 'F') && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[28]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[30]++;
                    c -= ('A' - 10);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[22]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[23]++;
                    break check;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[31]++;}

            } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[29]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[24]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c <= 'f') && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[32]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[34]++;
                    c -= ('a' - 10);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[26]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[27]++;
                    break check;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[35]++;}

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[33]++;}
}
}
            return -1;
        }
        return (accumulator << 4) | c;
    }

    /**
     * Add <i>listener</i> to <i>bag</i> of listeners.
     * The function does not modify <i>bag</i> and return a new collection
     * containing <i>listener</i> and all listeners from <i>bag</i>.
     * Bag without listeners always represented as the null value.
     * <p>
     * Usage example:
     * <pre>
     *     private volatile Object changeListeners;
     *
     *     public void addMyListener(PropertyChangeListener l)
     *     {
     *         synchronized (this) {
     *             changeListeners = Kit.addListener(changeListeners, l);
     *         }
     *     }
     *
     *     public void removeTextListener(PropertyChangeListener l)
     *     {
     *         synchronized (this) {
     *             changeListeners = Kit.removeListener(changeListeners, l);
     *         }
     *     }
     *
     *     public void fireChangeEvent(Object oldValue, Object newValue)
     *     {
     *     // Get immune local copy
     *         Object listeners = changeListeners;
     *         if (listeners != null) {
     *             PropertyChangeEvent e = new PropertyChangeEvent(
     *                 this, "someProperty" oldValue, newValue);
     *             for (int i = 0; ; ++i) {
     *                 Object l = Kit.getListener(listeners, i);
     *                 if (l == null)
     *                     break;
     *                 ((PropertyChangeListener)l).propertyChange(e);
     *             }
     *         }
     *     }
     * </pre>
     *
     * @param listener Listener to add to <i>bag</i>
     * @param bag Current collection of listeners.
     * @return A new bag containing all listeners from <i>bag</i> and
     *          <i>listener</i>.
     * @see #removeListener(Object bag, Object listener)
     * @see #getListener(Object bag, int index)
     */
    public static Object addListener(Object bag, Object listener)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[36]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[37]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((listener instanceof Object[]) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[38]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[39]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((bag == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[40]++;
            bag = listener;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[31]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[41]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[32]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((bag instanceof Object[]) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[42]++;
            bag = new Object[] { bag, listener };
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[33]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[43]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[34]++;
            Object[] array = (Object[])bag;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[35]++;
            int L = array.length;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
            // bag has at least 2 elements if it is array
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((L < 2) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[44]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[45]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[37]++;
            Object[] tmp = new Object[L + 1];
            System.arraycopy(array, 0, tmp, 0, L);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[38]++;
            tmp[L] = listener;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[39]++;
            bag = tmp;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[40]++;
        }
}

        return bag;
    }

    /**
     * Remove <i>listener</i> from <i>bag</i> of listeners.
     * The function does not modify <i>bag</i> and return a new collection
     * containing all listeners from <i>bag</i> except <i>listener</i>.
     * If <i>bag</i> does not contain <i>listener</i>, the function returns
     * <i>bag</i>.
     * <p>
     * For usage example, see {@link #addListener(Object bag, Object listener)}.
     *
     * @param listener Listener to remove from <i>bag</i>
     * @param bag Current collection of listeners.
     * @return A new bag containing all listeners from <i>bag</i> except
     *          <i>listener</i>.
     * @see #addListener(Object bag, Object listener)
     * @see #getListener(Object bag, int index)
     */
    public static Object removeListener(Object bag, Object listener)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[41]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[46]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[47]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[42]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((listener instanceof Object[]) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[48]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[49]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((bag == listener) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[50]++;
            bag = null;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[44]++;

        } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[51]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[45]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((bag instanceof Object[]) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[52]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[46]++;
            Object[] array = (Object[])bag;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[47]++;
            int L = array.length;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
            // bag has at least 2 elements if it is array
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((L < 2) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[54]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[55]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[49]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((L == 2) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[56]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[50]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((array[1] == listener) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[58]++;
                    bag = array[0];
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[51]++;

                } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[59]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[52]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((array[0] == listener) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[60]++;
                    bag = array[1];
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[53]++;

                } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[61]++;}
}

            } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[57]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[54]++;
                int i = L;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[55]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[1]++;


int CodeCoverConditionCoverageHelper_C22;
                do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[1]--;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[2]--;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[3]++;
}
                    --i;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[56]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[57]++;
int CodeCoverConditionCoverageHelper_C23;
                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((array[i] == listener) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[62]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[58]++;
                        Object[] tmp = new Object[L - 1];
                        System.arraycopy(array, 0, tmp, 0, i);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[59]++;
                        System.arraycopy(array, i + 1, tmp, i, L - (i + 1));
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[60]++;
                        bag = tmp;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[61]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[62]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[63]++;}
                } while ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false));
            }

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[53]++;}
}

        return bag;
    }

    /**
     * Get listener at <i>index</i> position in <i>bag</i> or null if
     * <i>index</i> equals to number of listeners in <i>bag</i>.
     * <p>
     * For usage example, see {@link #addListener(Object bag, Object listener)}.
     *
     * @param bag Current collection of listeners.
     * @param index Index of the listener to access.
     * @return Listener at the given index or null.
     * @see #addListener(Object bag, Object listener)
     * @see #removeListener(Object bag, Object listener)
     */
    public static Object getListener(Object bag, int index)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[63]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[64]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[64]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((bag == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[66]++;
                return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[67]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[65]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((bag instanceof Object[]) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[68]++;
                return bag;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[69]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[66]++;
            Object[] array = (Object[])bag;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[67]++;
int CodeCoverConditionCoverageHelper_C27;
            // bag has at least 2 elements if it is array
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((array.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[70]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[71]++;}
            return array[0];

        } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[65]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[68]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((index == 1) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[72]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[69]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((bag instanceof Object[]) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[74]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[70]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((bag == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[76]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[77]++;}
                return null;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[75]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[71]++;
            Object[] array = (Object[])bag;
            // the array access will check for index on its own
            return array[1];

        } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[73]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[72]++;
            // bag has to array
            Object[] array = (Object[])bag;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[73]++;
            int L = array.length;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[74]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((L < 2) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[78]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[79]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[75]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((index == L) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[80]++;
                return null;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[81]++;}
            return array[index];
        }
}
    }

    static Object initHash(Map<Object,Object> h, Object key, Object initialValue)
    {
        synchronized (h) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[76]++;
            Object current = h.get(key);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[77]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((current == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[82]++;
                h.put(key, initialValue);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[78]++;

            } else {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[83]++;
                initialValue = current;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[79]++;
            }
        }
        return initialValue;
    }

    private final static class ComplexKey
    {
        private Object key1;
        private Object key2;
        private int hash;

        ComplexKey(Object key1, Object key2)
        {
            this.key1 = key1;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[80]++;
            this.key2 = key2;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[81]++;
        }

        @Override
        public boolean equals(Object anotherObj)
        {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[82]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((anotherObj instanceof ComplexKey) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[84]++;
                return false;
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[85]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[83]++;
            ComplexKey another = (ComplexKey)anotherObj;
            return key1.equals(another.key1) && key2.equals(another.key2);
        }

        @Override
        public int hashCode()
        {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[84]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((hash == 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[86]++;
                hash = key1.hashCode() ^ key2.hashCode();
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[85]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[87]++;}
            return hash;
        }
    }

    public static Object makeHashKeyFromPair(Object key1, Object key2)
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[86]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((key1 == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[88]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[89]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[87]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((key2 == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[90]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[91]++;}
        return new ComplexKey(key1, key2);
    }

    public static String readReader(Reader r)
        throws IOException
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[88]++;
        char[] buffer = new char[512];
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[89]++;
        int cursor = 0;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[90]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[4]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[4]--;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[5]--;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[6]++;
}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[91]++;
            int n = r.read(buffer, cursor, buffer.length - cursor);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[92]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[92]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[93]++; break;
 } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[93]++;}
            cursor += n;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[94]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[95]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((cursor == buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[94]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[96]++;
                char[] tmp = new char[buffer.length * 2];
                System.arraycopy(buffer, 0, tmp, 0, cursor);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[97]++;
                buffer = tmp;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[98]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[95]++;}
        }
        return new String(buffer, 0, cursor);
    }

    public static byte[] readStream(InputStream is, int initialBufferCapacity)
        throws IOException
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[99]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((initialBufferCapacity <= 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[96]++;
            throw new IllegalArgumentException(
                "Bad initialBufferCapacity: "+initialBufferCapacity);

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[97]++;}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[100]++;
        byte[] buffer = new byte[initialBufferCapacity];
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[101]++;
        int cursor = 0;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[102]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[7]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[7]--;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[8]--;
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.loops[9]++;
}
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[103]++;
            int n = is.read(buffer, cursor, buffer.length - cursor);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[104]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[98]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[105]++; break;
 } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[99]++;}
            cursor += n;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[106]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[107]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((cursor == buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[100]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[108]++;
                byte[] tmp = new byte[buffer.length * 2];
                System.arraycopy(buffer, 0, tmp, 0, cursor);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[109]++;
                buffer = tmp;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[110]++;

            } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[101]++;}
        }
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[111]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((cursor != buffer.length) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[102]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[112]++;
            byte[] tmp = new byte[cursor];
            System.arraycopy(buffer, 0, tmp, 0, cursor);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[113]++;
            buffer = tmp;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[114]++;

        } else {
  CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.branches[103]++;}
        return buffer;
    }

    /**
     * Throws RuntimeException to indicate failed assertion.
     * The function never returns and its return type is RuntimeException
     * only to be able to write <tt>throw Kit.codeBug()</tt> if plain
     * <tt>Kit.codeBug()</tt> triggers unreachable code error.
     */
    public static RuntimeException codeBug()
        throws RuntimeException
    {
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[115]++;
        RuntimeException ex = new IllegalStateException("FAILED ASSERTION");
        // Print stack trace ASAP
        ex.printStackTrace(System.err);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[116]++;
        throw ex;
    }

    /**
     * Throws RuntimeException to indicate failed assertion.
     * The function never returns and its return type is RuntimeException
     * only to be able to write <tt>throw Kit.codeBug()</tt> if plain
     * <tt>Kit.codeBug()</tt> triggers unreachable code error.
     */
    public static RuntimeException codeBug(String msg)
        throws RuntimeException
    {
        msg = "FAILED ASSERTION: " + msg;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[117]++;
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[118]++;
        RuntimeException ex = new IllegalStateException(msg);
        // Print stack trace ASAP
        ex.printStackTrace(System.err);
CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt.statements[119]++;
        throw ex;
    }
}

class CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt ());
  }
    public static long[] statements = new long[120];
    public static long[] branches = new long[104];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[46];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Kit.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1};
    for (int i = 1; i <= 45; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$6ubyiuewjkcyv2ej0gakesbbzgpt () {
    super("org.mozilla.javascript.RHINO-SRC-Kit.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 119; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 103; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Kit.java");
      for (int i = 1; i <= 119; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 103; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

