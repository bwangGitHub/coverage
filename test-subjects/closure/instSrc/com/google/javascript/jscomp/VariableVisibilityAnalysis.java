/*
 * Copyright 2010 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import java.util.Map;

/**
 * An analysis pass that determines the visibility of variables -- that is,
 * whether a variable is truly local, a local captured by an inner scope, a
 * parameter, or a global variable.
 *
 * SideEffectsAnalysis uses this class to partition a potentially infinite
 * number of concrete storage locations into a (small) finite number of
 * abstract storage locations based on a variable's storage visibility.
 *
 * @author dcc@google.com (Devin Coughlin)
 */
class VariableVisibilityAnalysis implements CompilerPass {
  static {
    CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.ping();
  }


  enum VariableVisibility {

    /** Local variable, not captured by closure */
    LOCAL,

    /** Local variable captured by a closure */
    CAPTURED_LOCAL,

    /**
     * Formal parameter declaration variable
     *
     * Parameters are different than local variables because they can be
     * aliased by elements of the arguments object.
     */
    PARAMETER,

    /** A global variable */
    GLOBAL
  }

  private AbstractCompiler compiler;

  /**
   * Maps the declaring name node for a variable to that variable's
   * visibility.
   */
  private Map<Node, VariableVisibility> visibilityByDeclaringNameNode;

  public VariableVisibilityAnalysis(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[1]++;

    visibilityByDeclaringNameNode = Maps.newHashMap();
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[2]++;
  }

  /**
   * Returns the visibility of of a variable, given that variable's declaring
   * name node.
   *
   * The name node's parent must be one of:
   * <pre>
   *    Token.VAR (for a variable declaration)
   *    Token.FUNCTION (for a function declaration)
   *    Token.PARAM_LIST (for a function formal parameter)
   * </pre>
   *
   * The returned visibility will be one of:
   * <pre>
   *    LOCAL_VARIABLE : the variable is a local variable used only in its
   *        declared scope
   *    CAPTURED_LOCAL_VARIABLE : A local variable that is used in a capturing
   *        closure
   *     PARAMETER_VARIABLE : the variable is a formal parameter
   *     GLOBAL_VARIABLE : the variable is declared in the global scope
   *  </pre>
   *
   * @param declaringNameNode The name node for a declaration.
   */
  public VariableVisibility getVariableVisibility(Node declaringNameNode) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[3]++;
    Node parent = declaringNameNode.getParent();

    Preconditions.checkArgument(parent.isVar()
        || parent.isFunction()
        || parent.isParamList());
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[4]++;

    return visibilityByDeclaringNameNode.get(declaringNameNode);
  }

  /**
   * Determines the visibility class for each variable in root.
   */
  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[5]++;
    ReferenceCollectingCallback callback =
      new ReferenceCollectingCallback(compiler,
          ReferenceCollectingCallback.DO_NOTHING_BEHAVIOR);

    NodeTraversal.traverse(compiler, root, callback);
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[6]++;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.loops[1]++;



    for (Var variable : callback.getAllSymbols()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.loops[1]--;
  CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.loops[2]--;
  CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.loops[3]++;
}
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[8]++;
      ReferenceCollection referenceCollection =
          callback.getReferences(variable);

      VariableVisibility visibility;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;

      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((variableIsParameter(variable)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[1]++;
        visibility = VariableVisibility.PARAMETER;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[10]++;

      } else {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[2]++;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[11]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((variable.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[3]++;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((referenceCollection.isEscaped()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[5]++;
          visibility = VariableVisibility.CAPTURED_LOCAL;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[13]++;

        } else {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[6]++;
          visibility = VariableVisibility.LOCAL;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[14]++;
        }

      } else {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[4]++;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[15]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((variable.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[7]++;
        visibility = VariableVisibility.GLOBAL;
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[16]++;

      } else {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.branches[8]++;
        throw new IllegalStateException("Un-handled variable visibility for " +
            variable);
      }
}
}

      visibilityByDeclaringNameNode.put(variable.getNameNode(), visibility);
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[17]++;
    }
  }

  /**
   * Returns true if the variable is a formal parameter.
   */
  private static boolean variableIsParameter(Var variable) {
CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt.statements[18]++;
    Node variableParent = variable.getParentNode();

    return variableParent != null && variableParent.isParamList();
  }
}

class CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.VariableVisibilityAnalysis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$awdgwxq5rwod1kttnurw735db93mm0y9licic3g3vb2sh1lt () {
    super("com.google.javascript.jscomp.VariableVisibilityAnalysis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.VariableVisibilityAnalysis.java");
      for (int i = 1; i <= 18; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

