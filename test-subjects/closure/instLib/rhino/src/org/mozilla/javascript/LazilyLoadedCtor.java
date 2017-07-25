/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Avoid loading classes unless they are used.
 *
 * <p> This improves startup time and average memory usage.
 */
public final class LazilyLoadedCtor implements java.io.Serializable {
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.ping();
  }

	private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[1]++;
  }

    private static final int STATE_BEFORE_INIT = 0;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[2]++;
  }
    private static final int STATE_INITIALIZING = 1;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[3]++;
  }
    private static final int STATE_WITH_VALUE = 2;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[4]++;
  }

    private final ScriptableObject scope;
    private final String propertyName;
    private final String className;
    private final boolean sealed;
    private final boolean privileged;
    private Object initializedValue;
    private int state;

    public LazilyLoadedCtor(ScriptableObject scope, String propertyName,
            String className, boolean sealed)
    {
        this(scope, propertyName, className, sealed, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[5]++;
    }

    LazilyLoadedCtor(ScriptableObject scope, String propertyName,
            String className, boolean sealed, boolean privileged)
    {

        this.scope = scope;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[6]++;
        this.propertyName = propertyName;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[7]++;
        this.className = className;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[8]++;
        this.sealed = sealed;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[9]++;
        this.privileged = privileged;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[10]++;
        this.state = STATE_BEFORE_INIT;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[11]++;

        scope.addLazilyInitializedValue(propertyName, 0, this,
                ScriptableObject.DONTENUM);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[12]++;
    }

    void init()
    {
        synchronized (this) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((state == STATE_INITIALIZING) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[1]++;
                throw new IllegalStateException(
                    "Recursive initialization for "+propertyName);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[2]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((state == STATE_BEFORE_INIT) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[3]++;
                state = STATE_INITIALIZING;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[15]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[16]++;
                // Set value now to have something to set in finally block if
                // buildValue throws.
                Object value = Scriptable.NOT_FOUND;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[17]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    value = buildValue();
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[18]++;
                } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[5]++;
}
                    initializedValue = value;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[19]++;
                    state = STATE_WITH_VALUE;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[20]++;
                }

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[4]++;}
        }
    }

    Object getValue()
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((state != STATE_WITH_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[6]++;
            throw new IllegalStateException(propertyName);
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[7]++;}
        return initializedValue;
    }

    private Object buildValue()
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
        if((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((privileged) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false))
        {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[8]++;
            return AccessController.doPrivileged(new PrivilegedAction<Object>()
            {
                public Object run()
                {
                    return buildValue0();
                }
            });

        }
        else
        {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[9]++;
            return buildValue0();
        }
    }

    private Object buildValue0()
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[23]++;
        Class<? extends Scriptable> cl = cast(Kit.classOrNull(className));
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((cl != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[10]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[25]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[26]++;
                Object value = ScriptableObject.buildClassCtor(scope, cl,
                                                               sealed, false);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[13]++;
                    return value;

                }
                else {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[14]++;
                    // cl has own static initializer which is expected
                    // to set the property on its own.
                    value = scope.get(propertyName, scope);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[28]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[15]++;
                        return value;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[16]++;}
                }
            } catch (InvocationTargetException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[17]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[30]++;
                Throwable target = ex.getTargetException();
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((target instanceof RuntimeException) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[18]++;
                    throw (RuntimeException)target;

                } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[19]++;}
            } catch (RhinoException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[20]++;
            } catch (InstantiationException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[21]++;
            } catch (IllegalAccessException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[22]++;
            } catch (SecurityException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[23]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[12]++;
}
  }

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh.branches[11]++;}
        return Scriptable.NOT_FOUND;
    }

    @SuppressWarnings({"unchecked"})
    private Class<? extends Scriptable> cast(Class<?> cl) {
        return (Class<? extends Scriptable>)cl;
    }

}

class CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[24];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-LazilyLoadedCtor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$adralqrs9n89mg8nqp8yu488ilgd1v2ul1fzvd4220n16gkh () {
    super("org.mozilla.javascript.RHINO-SRC-LazilyLoadedCtor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 23; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-LazilyLoadedCtor.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 23; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

