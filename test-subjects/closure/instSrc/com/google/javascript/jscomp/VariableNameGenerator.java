/*
 * Copyright 2009 The Closure Compiler Authors.
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

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Generates new variables names that would not collide with existing names in
 * a scope.
 *
 *
 */
class VariableNameGenerator {
  static {
    CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.ping();
  }

  private final NameGenerator names;
  private final Scope scope;
  VariableNameGenerator(Scope scope) {
    this.scope = scope;
CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.statements[1]++;
CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.statements[2]++;
    Set<String> usedNames = Sets.newHashSet();
    names = new NameGenerator(usedNames, "", null);
CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.statements[3]++;
  }

  String getNextNewName() {
CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.statements[4]++;
    String name = null;
CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.loops[1]++;


    while (scope.isDeclared(name = names.generateNextName(), true)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.loops[1]--;
  CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.loops[2]--;
  CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up.loops[3]++;
}}
    return name;
  }
}

class CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$ryqwtpo7cd844etaao29p4khqjhv75zdrr7mq9up () {
    super("com.google.javascript.jscomp.VariableNameGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.VariableNameGenerator.java");
      for (int i = 1; i <= 5; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

