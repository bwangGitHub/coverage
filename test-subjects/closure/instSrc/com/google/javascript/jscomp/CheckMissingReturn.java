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

import com.google.common.base.Predicate;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.TernaryValue;

/**
 * Checks functions for missing return statements. Return statements are only
 * expected for functions with return type information. Functions with empty
 * bodies are ignored.
 *
 */
class CheckMissingReturn implements ScopedCallback {
  static {
    CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.ping();
  }


  static final DiagnosticType MISSING_RETURN_STATEMENT =
      DiagnosticType.warning(
          "JSC_MISSING_RETURN_STATEMENT",
          "Missing return statement. Function expected to return {0}.");
  static {
    CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final CheckLevel level;

  private static final Predicate<Node> IS_RETURN = new Predicate<Node>() {
    @Override
    public boolean apply(Node input) {
      // Check for null because the control flow graph's implicit return node is
      // represented by null, so this value might be input.
      return input != null && input.isReturn();
    }
  };
  static {
    CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[2]++;
  }

  /* Skips all exception edges and impossible edges. */
  private static final Predicate<DiGraphEdge<Node, ControlFlowGraph.Branch>>
      GOES_THROUGH_TRUE_CONDITION_PREDICATE =
        new Predicate<DiGraphEdge<Node, ControlFlowGraph.Branch>>() {
    @Override
    public boolean apply(DiGraphEdge<Node, ControlFlowGraph.Branch> input) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[3]++;
      // First skill all exceptions.
      Branch branch = input.getValue();
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((branch == Branch.ON_EX) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[1]++;
        return false;

      } else {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[2]++;
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[5]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((branch.isConditional()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[3]++;
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[6]++;
        Node condition = NodeUtil.getConditionExpression(
            input.getSource().getValue());
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        // TODO(user): We CAN make this bit smarter just looking at
        // constants. We DO have a full blown ReverseAbstractInterupter and
        // type system that can evaluate some impressions' boolean value but
        // for now we will keep this pass lightweight.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((condition != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[5]++;
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[8]++;
          TernaryValue val = NodeUtil.getImpureBooleanValue(condition);
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((val != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[7]++;
            return val.toBoolean(true) == (Branch.ON_TRUE == branch);

          } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[6]++;}

      } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[4]++;}
}
      return true;
    }
  };
  static {
    CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[10]++;
  }

  /**
   * @param level level of severity to report when a missing return statement
   *     is discovered
   */
  CheckMissingReturn(AbstractCompiler compiler, CheckLevel level) {
    this.compiler = compiler;
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[11]++;
    this.level = level;
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[12]++;
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[13]++;
    JSType returnType = explicitReturnExpected(t.getScopeRoot());
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((returnType == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[9]++;
      return;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[10]++;}
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fastAllPathsReturnCheck(t.getControlFlowGraph())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[11]++;
      return;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[12]++;}
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[16]++;

    CheckPathsBetweenNodes<Node, ControlFlowGraph.Branch> test =
        new CheckPathsBetweenNodes<Node, ControlFlowGraph.Branch>(
            t.getControlFlowGraph(),
            t.getControlFlowGraph().getEntry(),
            t.getControlFlowGraph().getImplicitReturn(),
            IS_RETURN, GOES_THROUGH_TRUE_CONDITION_PREDICATE);
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((test.allPathsSatisfyPredicate()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[13]++;
      compiler.report(
          t.makeError(t.getScopeRoot(), level,
              MISSING_RETURN_STATEMENT, returnType.toString()));
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[18]++;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[14]++;}
  }

  /**
   * Fast check to see if all execution paths contain a return statement.
   * May spuriously report that a return statement is missing.
   *
   * @return true if all paths return, converse not necessarily true
   */
  private static boolean fastAllPathsReturnCheck(ControlFlowGraph<Node> cfg) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.loops[1]++;


    for (DiGraphEdge<Node, Branch> s : cfg.getImplicitReturn().getInEdges()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.loops[1]--;
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.loops[2]--;
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.loops[3]++;
}
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((s.getSource().getValue().isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[15]++;
        return false;

      } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[16]++;}
    }
    return true;
  }

  @Override
  public void exitScope(NodeTraversal t) {
  }

  @Override
  public boolean shouldTraverse(
      NodeTraversal nodeTraversal, Node n, Node parent) {
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
  }

  /**
   * Determines if the given scope should explicitly return. All functions
   * with non-void or non-unknown return types must have explicit returns.
   * @return If a return type is expected, returns it. Otherwise, returns null.
   */
  private JSType explicitReturnExpected(Node scope) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[21]++;
    FunctionType scopeType = JSType.toMaybeFunctionType(scope.getJSType());
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((scopeType == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[17]++;
      return null;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[18]++;}
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isEmptyFunction(scope)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[19]++;
      return null;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[20]++;}
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[24]++;

    JSType returnType = scopeType.getReturnType();
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[25]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((returnType == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[21]++;
      return null;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[22]++;}
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[26]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isVoidOrUnknown(returnType)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[23]++;
      return returnType;

    } else {
  CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.branches[24]++;}

    return null;
  }


  /**
   * @return {@code true} if function represents a JavaScript function
   *     with an empty body
   */
  private static boolean isEmptyFunction(Node function) {
    return function.getChildCount() == 3 &&
           !function.getFirstChild().getNext().getNext().hasChildren();
  }

  /**
   * @return {@code true} if returnType is void, unknown, or a union
   *     containing void or unknown
   */
  private boolean isVoidOrUnknown(JSType returnType) {
CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75.statements[27]++;
    final JSType voidType =
        compiler.getTypeRegistry().getNativeType(JSTypeNative.VOID_TYPE);
    return voidType.isSubtype(returnType);
  }
}

class CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75 ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckMissingReturn.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$26nfpvb176jumlobannxslfj2xujd17z3q75 () {
    super("com.google.javascript.jscomp.CheckMissingReturn.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckMissingReturn.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

