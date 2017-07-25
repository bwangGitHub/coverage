/*
 * Copyright 2008 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Group a set of related diagnostic types together, so that they can
 * be toggled on and off as one unit.
 * @author nicksantos@google.com (Nick Santos)
 */
public class DiagnosticGroup implements Serializable {
  static {
    CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.ping();
  }

  private static final long serialVersionUID = 1;
  static {
    CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[1]++;
  }

  // The set of types represented by this group, hashed by key.
  private final Set<DiagnosticType> types;

  // A human-readable name for the group.
  private final String name;

  /**
   * Create a group that matches all errors of the given types.
   */
  DiagnosticGroup(String name, DiagnosticType ...types) {
    this.name = name;
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[2]++;
    this.types = ImmutableSet.copyOf(Arrays.asList(types));
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[3]++;
  }

  /**
   * Create a group that matches all errors of the given types.
   */
  public DiagnosticGroup(DiagnosticType ...types) {
    this(null, types);
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[4]++;
  }

  /**
   * Create a diagnostic group with no name that only matches the given type.
   */
  private DiagnosticGroup(DiagnosticType type) {
    this.name = null;
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[5]++;
    this.types = ImmutableSet.of(type);
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[6]++;
  }

  // DiagnosticGroups with only a single DiagnosticType.
  private static final Map<DiagnosticType, DiagnosticGroup> singletons =
      Maps.newHashMap();
  static {
    CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[7]++;
  }

  /** Create a diagnostic group that matches only the given type. */
  public static DiagnosticGroup forType(DiagnosticType type) {
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((singletons.containsKey(type)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.branches[1]++;
      singletons.put(type, new DiagnosticGroup(type));
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[9]++;

    } else {
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.branches[2]++;}
    return singletons.get(type);
  }

  /**
   * Create a composite group.
   */
  public DiagnosticGroup(DiagnosticGroup ...groups) {
    this(null, groups);
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[10]++;
  }

  /**
   * Create a composite group.
   */
  public DiagnosticGroup(String name, DiagnosticGroup ...groups) {
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[11]++;
    Set<DiagnosticType> set = Sets.newHashSet();
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[1]++;



    for (DiagnosticGroup group : groups) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[1]--;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[2]--;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[3]++;
}
      set.addAll(group.types);
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[13]++;
    }

    this.name = name;
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[14]++;
    this.types = ImmutableSet.copyOf(set);
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[15]++;
  }

  /**
   * Returns whether the given error's type matches a type
   * in this group.
   */
  public boolean matches(JSError error) {
    return matches(error.getType());
  }

  /**
   * Returns whether the given type matches a type in this group.
   */
  public boolean matches(DiagnosticType type) {
    return types.contains(type);
  }

  /**
   * Returns whether all of the types in the given group are in this group.
   */
  boolean isSubGroup(DiagnosticGroup group) {
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[16]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[4]++;


    for (DiagnosticType type : group.types) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[4]--;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[5]--;
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.loops[6]++;
}
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((matches(type)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.branches[3]++;
        return false;

      } else {
  CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt.branches[4]++;}
    }
    return true;
  }

  /**
   * Returns an iterable over all the types in this group.
   */
  public Iterable<DiagnosticType> getTypes() {
    return types;
  }

  @Override
  public String toString() {
    return name == null ? super.toString() : "DiagnosticGroup<" + name + ">";
  }
}

class CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DiagnosticGroup.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$7zonfk39v33v6mtizizkb6jdu03hhlt () {
    super("com.google.javascript.jscomp.DiagnosticGroup.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DiagnosticGroup.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

