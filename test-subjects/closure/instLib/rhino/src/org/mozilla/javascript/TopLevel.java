/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

 package org.mozilla.javascript;

import java.util.EnumMap;

/**
 * A top-level scope object that provides special means to cache and preserve
 * the initial values of the built-in constructor properties for better
 * ECMAScript compliance.
 *
 * <p>ECMA 262 requires that most constructors used internally construct
 * objects with the original prototype object as value of their [[Prototype]]
 * internal property. Since built-in global constructors are defined as
 * writable and deletable, this means they should be cached to protect against
 * redefinition at runtime.</p>
 *
 * <p>In order to implement this efficiently, this class provides a mechanism
 * to access the original built-in global constructors and their prototypes
 * via numeric class-ids. To make use of this, the new
 * {@link ScriptRuntime#newBuiltinObject ScriptRuntime.newBuiltinObject} and
 * {@link ScriptRuntime#setBuiltinProtoAndParent ScriptRuntime.setBuiltinProtoAndParent}
 * methods should be used to create and initialize objects of built-in classes
 * instead of their generic counterparts.</p>
 *
 * <p>Calling {@link org.mozilla.javascript.Context#initStandardObjects()}
 * with an instance of this class as argument will automatically cache
 * built-in classes after initialization. For other setups involving
 * top-level scopes that inherit global properties from their proptotypes
 * (e.g. with dynamic scopes) embeddings should explicitly call
 * {@link #cacheBuiltins()} to initialize the class cache for each top-level
 * scope.</p>
 */
public class TopLevel extends IdScriptableObject {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.ping();
  }


    static final long serialVersionUID = -4648046356662472260L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[1]++;
  }

    /**
     * An enumeration of built-in ECMAScript objects.
     */
    public enum Builtins {
        /** The built-in Object type. */
        Object,
        /** The built-in Array type. */
        Array,
        /** The built-in Function type. */
        Function,
        /** The built-in String type. */
        String,
        /** The built-in Number type. */
        Number,
        /** The built-in Boolean type. */
        Boolean,
        /** The built-in RegExp type. */
        RegExp,
        /** The built-in Error type. */
        Error
    }

    private EnumMap<Builtins, BaseFunction> ctors;

    @Override
    public String getClassName() {
        return "global";
    }

    /**
     * Cache the built-in ECMAScript objects to protect them against
     * modifications by the script. This method is called automatically by
     * {@link ScriptRuntime#initStandardObjects ScriptRuntime.initStandardObjects}
     * if the scope argument is an instance of this class. It only has to be
     * called by the embedding if a top-level scope is not initialized through
     * <code>initStandardObjects()</code>.
     */
    public void cacheBuiltins() {
        ctors = new EnumMap<Builtins, BaseFunction>(Builtins.class);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[2]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.loops[1]++;


        for (Builtins builtin : Builtins.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.loops[1]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.loops[2]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.loops[3]++;
}
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[4]++;
            Object value = ScriptableObject.getProperty(this, builtin.name());
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[1]++;
                ctors.put(builtin, (BaseFunction)value);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[6]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[2]++;}
        }
    }

    /**
     * Static helper method to get a built-in object constructor with the given
     * <code>type</code> from the given <code>scope</code>. If the scope is not
     * an instance of this class or does have a cache of built-ins,
     * the constructor is looked up via normal property lookup.
     *
     * @param cx the current Context
     * @param scope the top-level scope
     * @param type the built-in type
     * @return the built-in constructor
     */
    public static Function getBuiltinCtor(Context cx,
                                          Scriptable scope,
                                          Builtins type) {
        // must be called with top level scope
        assert scope.getParentScope() == null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((scope instanceof TopLevel) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[3]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[8]++;
            Function result = ((TopLevel)scope).getBuiltinCtor(type);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[5]++;
                return result;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[4]++;}
        // fall back to normal constructor lookup
        return ScriptRuntime.getExistingCtor(cx, scope, type.name());
    }

    /**
     * Static helper method to get a built-in object prototype with the given
     * <code>type</code> from the given <code>scope</code>. If the scope is not
     * an instance of this class or does have a cache of built-ins,
     * the prototype is looked up via normal property lookup.
     *
     * @param scope the top-level scope
     * @param type the built-in type
     * @return the built-in prototype
     */
    public static Scriptable getBuiltinPrototype(Scriptable scope,
                                                 Builtins type) {
        // must be called with top level scope
        assert scope.getParentScope() == null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((scope instanceof TopLevel) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[7]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[11]++;
            Scriptable result = ((TopLevel)scope)
                    .getBuiltinPrototype(type);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[9]++;
                return result;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.branches[8]++;}
        // fall back to normal prototype lookup
        return ScriptableObject.getClassPrototype(scope, type.name());
    }

    /**
     * Get the cached built-in object constructor from this scope with the
     * given <code>type</code>. Returns null if {@link #cacheBuiltins()} has not
     * been called on this object.
     * @param type the built-in type
     * @return the built-in constructor
     */
    public BaseFunction getBuiltinCtor(Builtins type) {
        return ctors != null ? ctors.get(type) : null;
    }

    /**
     * Get the cached built-in object prototype from this scope with the
     * given <code>type</code>. Returns null if {@link #cacheBuiltins()} has not
     * been called on this object.
     * @param type the built-in type
     * @return the built-in prototype
     */
    public Scriptable getBuiltinPrototype(Builtins type) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[13]++;
        BaseFunction func = getBuiltinCtor(type);
CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up.statements[14]++;
        Object proto = func != null ? func.getPrototypeProperty() : null;
        return proto instanceof Scriptable ? (Scriptable) proto : null;
    }

}

class CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-TopLevel.java";
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

  public CodeCoverCoverageCounter$2o08iygcpwmc6w26cj13115z9wgadz8uz5up () {
    super("org.mozilla.javascript.RHINO-SRC-TopLevel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-TopLevel.java");
      for (int i = 1; i <= 14; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
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

