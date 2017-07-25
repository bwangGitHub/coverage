/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

/**
 * Cache of generated classes and data structures to access Java runtime
 * from JavaScript.
 *
 *
 * @since Rhino 1.5 Release 5
 */
public class ClassCache implements Serializable
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.ping();
  }

    private static final long serialVersionUID = -8866246036237312215L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[1]++;
  }
    private static final Object AKEY = "ClassCache";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[2]++;
  }
    private volatile boolean cachingIsEnabled = true;
  {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[3]++;
  }
    private transient HashMap<Class<?>,JavaMembers> classTable;
    private transient HashMap<JavaAdapter.JavaAdapterSignature,Class<?>> classAdapterCache;
    private transient HashMap<Class<?>,Object> interfaceAdapterCache;
    private int generatedClassSerial;
    private Scriptable associatedScope;

    /**
     * Search for ClassCache object in the given scope.
     * The method first calls
     * {@link ScriptableObject#getTopLevelScope(Scriptable scope)}
     * to get the top most scope and then tries to locate associated
     * ClassCache object in the prototype chain of the top scope.
     *
     * @param scope scope to search for ClassCache object.
     * @return previously associated ClassCache object or a new instance of
     *         ClassCache if no ClassCache object was found.
     *
     * @see #associate(ScriptableObject topScope)
     */
    public static ClassCache get(Scriptable scope)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[4]++;
        ClassCache cache = (ClassCache)
                ScriptableObject.getTopScopeValue(scope, AKEY);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cache == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[1]++;
            throw new RuntimeException("Can't find top level scope for " +
                    "ClassCache.get");

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[2]++;}
        return cache;
    }

    /**
     * Associate ClassCache object with the given top-level scope.
     * The ClassCache object can only be associated with the given scope once.
     *
     * @param topScope scope to associate this ClassCache object with.
     * @return true if no previous ClassCache objects were embedded into
     *         the scope and this ClassCache were successfully associated
     *         or false otherwise.
     *
     * @see #get(Scriptable scope)
     */
    public boolean associate(ScriptableObject topScope)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((topScope.getParentScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[3]++;
            // Can only associate cache with top level scope
            throw new IllegalArgumentException();

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[4]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this == topScope.associateValue(AKEY, this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[5]++;
            associatedScope = topScope;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[8]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[6]++;}
        return false;
    }

    /**
     * Empty caches of generated Java classes and Java reflection information.
     */
    public synchronized void clearCaches()
    {
        classTable = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[9]++;
        classAdapterCache = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[10]++;
        interfaceAdapterCache = null;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[11]++;
    }

    /**
     * Check if generated Java classes and Java reflection information
     * is cached.
     */
    public final boolean isCachingEnabled()
    {
        return cachingIsEnabled;
    }

     /**
     * Set whether to cache some values.
     * <p>
     * By default, the engine will cache the results of
     * <tt>Class.getMethods()</tt> and similar calls.
     * This can speed execution dramatically, but increases the memory
     * footprint. Also, with caching enabled, references may be held to
     * objects past the lifetime of any real usage.
     * <p>
     * If caching is enabled and this method is called with a
     * <code>false</code> argument, the caches will be emptied.
     * <p>
     * Caching is enabled by default.
     *
     * @param enabled if true, caching is enabled
     *
     * @see #clearCaches()
     */
    public synchronized void setCachingEnabled(boolean enabled)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((enabled == cachingIsEnabled) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[7]++;
            return;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[8]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((enabled) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[9]++;
            clearCaches();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[14]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[10]++;}
        cachingIsEnabled = enabled;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[15]++;
    }

    /**
     * @return a map from classes to associated JavaMembers objects
     */
    Map<Class<?>,JavaMembers> getClassCacheMap() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((classTable == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[11]++;
            classTable = new HashMap<Class<?>,JavaMembers>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[17]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[12]++;}
        return classTable;
    }

    Map<JavaAdapter.JavaAdapterSignature,Class<?>> getInterfaceAdapterCacheMap()
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((classAdapterCache == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[13]++;
            classAdapterCache = new HashMap<JavaAdapter.JavaAdapterSignature,Class<?>>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[19]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[14]++;}
        return classAdapterCache;
    }

    /**
     * @deprecated
     * The method always returns false.
     * @see #setInvokerOptimizationEnabled(boolean enabled)
     */
    public boolean isInvokerOptimizationEnabled()
    {
        return false;
    }

    /**
     * @deprecated
     * The method does nothing.
     * Invoker optimization is no longer used by Rhino.
     * On modern JDK like 1.4 or 1.5 the disadvantages of the optimization
     * like increased memory usage or longer initialization time overweight
     * small speed increase that can be gained using generated proxy class
     * to replace reflection.
     */
    public synchronized void setInvokerOptimizationEnabled(boolean enabled)
    {
    }

    /**
     * Internal engine method to return serial number for generated classes
     * to ensure name uniqueness.
     */
    public final synchronized int newClassSerialNumber()
    {
        return ++generatedClassSerial;
    }

    Object getInterfaceAdapter(Class<?> cl)
    {
        return interfaceAdapterCache == null
                    ? null
                    : interfaceAdapterCache.get(cl);
    }

    synchronized void cacheInterfaceAdapter(Class<?> cl, Object iadapter)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cachingIsEnabled) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[15]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((interfaceAdapterCache == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[17]++;
                interfaceAdapterCache = new HashMap<Class<?>,Object>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[22]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[18]++;}
            interfaceAdapterCache.put(cl, iadapter);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.statements[23]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p.branches[16]++;}
    }

    Scriptable getAssociatedScope() {
        return associatedScope;
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ClassCache.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw4snquwoi2li8yseurpen42p () {
    super("org.mozilla.javascript.RHINO-SRC-ClassCache.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ClassCache.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

