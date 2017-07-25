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

import com.google.javascript.jscomp.AnalyzeNameReferences.NameInfo;
import com.google.javascript.jscomp.NameReferenceGraph.Name;
import com.google.javascript.jscomp.NameReferenceGraph.Reference;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.rhino.Node;

import java.util.logging.Logger;

/**
 * Removes unused names.
 *
 */
class RemoveUnusedNames implements CompilerPass {
  static {
    CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.ping();
  }


  private static final Logger logger =
    Logger.getLogger(RemoveUnusedNames.class.getName());
  static {
    CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[1]++;
  }

  private final AbstractCompiler compiler;

  private final boolean canModifyExterns;

  /**
   * Creates a new pass for removing unused prototype properties, based
   * on the uniqueness of property names.
   * @param compiler The compiler.
   */
  RemoveUnusedNames(AbstractCompiler compiler,
      boolean canModifyExterns) {
    this.compiler = compiler;
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[2]++;
    this.canModifyExterns = canModifyExterns;
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[3]++;
  }

  @Override
  public void process(Node externRoot, Node root) {
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[4]++;
    AnalyzeNameReferences analyzer =
        new AnalyzeNameReferences(compiler);
    analyzer.process(externRoot, root);
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[5]++;
    removeUnusedProperties(analyzer.getGraph());
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[6]++;
  }

  /**
   * Remove all properties under a given name if the property name is
   * never referenced.
   */
  private void removeUnusedProperties(NameReferenceGraph graph) {
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.loops[1]++;


    for (GraphNode<Name, Reference> node : graph.getNodes()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.loops[1]--;
  CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.loops[2]--;
  CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.loops[3]++;
}
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[8]++;
      Name name = node.getValue();
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[9]++;
      NameInfo nameInfo = node.getAnnotation();
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((nameInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameInfo.isReferenced()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.branches[1]++;
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((canModifyExterns) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((name.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.branches[3]++;
          name.remove();
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[12]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[13]++;
          logger.fine("Removed unused name" + name);
CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.statements[14]++;

        } else {
  CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.branches[4]++;}

      } else {
  CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9.branches[2]++;}
    }
  }
}

class CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RemoveUnusedNames.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$dipelbgrlefwjwjbgl0d9nqqz2dpdu92e9 () {
    super("com.google.javascript.jscomp.RemoveUnusedNames.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RemoveUnusedNames.java");
      for (int i = 1; i <= 14; i++) {
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

