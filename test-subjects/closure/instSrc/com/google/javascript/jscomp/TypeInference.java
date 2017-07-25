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

import static com.google.javascript.rhino.jstype.JSTypeNative.ARRAY_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.BOOLEAN_OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.BOOLEAN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.CHECKED_UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_VALUE_OR_OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeRegistry.OBJECT_ELEMENT_TEMPLATE;
import static com.google.javascript.rhino.jstype.JSTypeRegistry.OBJECT_INDEX_TEMPLATE;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.CodingConvention.AssertionFunctionSpec;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.type.FlowScope;
import com.google.javascript.jscomp.type.ReverseAbstractInterpreter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.BooleanLiteralSet;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ModificationVisitor;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.TemplateTypeMap;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.TemplateType;
import com.google.javascript.rhino.jstype.UnionType;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Type inference within a script node or a function body, using the data-flow
 * analysis framework.
 *
 */
class TypeInference
    extends DataFlowAnalysis.BranchedForwardDataFlowAnalysis<Node, FlowScope> {
  static {
    CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.ping();
  }


  // TODO(johnlenz): We no longer make this check, but we should.
  static final DiagnosticType FUNCTION_LITERAL_UNDEFINED_THIS =
    DiagnosticType.warning(
        "JSC_FUNCTION_LITERAL_UNDEFINED_THIS",
        "Function literal argument refers to undefined this argument");
  static {
    CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final JSTypeRegistry registry;
  private final ReverseAbstractInterpreter reverseInterpreter;
  private final Scope syntacticScope;
  private final FlowScope functionScope;
  private final FlowScope bottomScope;
  private final Map<String, AssertionFunctionSpec> assertionFunctionsMap;

  // For convenience
  private final ObjectType unknownType;

  TypeInference(AbstractCompiler compiler, ControlFlowGraph<Node> cfg,
                ReverseAbstractInterpreter reverseInterpreter,
                Scope functionScope,
                Map<String, AssertionFunctionSpec> assertionFunctionsMap) {
    super(cfg, new LinkedFlowScope.FlowScopeJoinOp());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[2]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[3]++;
    this.registry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[4]++;
    this.reverseInterpreter = reverseInterpreter;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[5]++;
    this.unknownType = registry.getNativeObjectType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[6]++;

    this.syntacticScope = functionScope;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[7]++;
    inferArguments(functionScope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[8]++;

    this.functionScope = LinkedFlowScope.createEntryLattice(functionScope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[9]++;
    this.assertionFunctionsMap = assertionFunctionsMap;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[10]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[11]++;

    // For each local variable declared with the VAR keyword, the entry
    // type is VOID.
    Iterator<Var> varIt =
        functionScope.getDeclarativelyUnboundVarsWithoutTypes();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((varIt.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[1]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[2]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[3]++;
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[13]++;
      Var var = varIt.next();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isUnflowable(var)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[1]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[15]++;
        continue;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[2]++;}

      this.functionScope.inferSlotType(
          var.getName(), getNativeType(VOID_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[16]++;
    }

    this.bottomScope = LinkedFlowScope.createEntryLattice(
        Scope.createLatticeBottom(functionScope.getRootNode()));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[17]++;
  }

  /**
   * Infers all of a function's arguments if their types aren't declared.
   */
  private void inferArguments(Scope functionScope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[18]++;
    Node functionNode = functionScope.getRootNode();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[19]++;
    Node astParameters = functionNode.getFirstChild().getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[20]++;
    Node iifeArgumentNode = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isCallOrNewTarget(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[3]++;
      iifeArgumentNode = functionNode.getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[22]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[4]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[23]++;

    FunctionType functionType =
        JSType.toMaybeFunctionType(functionNode.getJSType());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((functionType != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[5]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[25]++;
      Node parameterTypes = functionType.getParametersNode();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((parameterTypes != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[7]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[27]++;
        Node parameterTypeNode = parameterTypes.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[28]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[4]++;


        for (Node astParameter : astParameters.children()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[4]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[5]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[6]++;
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[29]++;
          Var var = functionScope.getVar(astParameter.getString());
          Preconditions.checkNotNull(var);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[30]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((var.getType() == unknownType) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[9]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[32]++;
            JSType newType = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;

            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((iifeArgumentNode != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[11]++;
              newType = iifeArgumentNode.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[34]++;

            } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[12]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[35]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((parameterTypeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[13]++;
              newType = parameterTypeNode.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[36]++;

            } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[14]++;}
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;

            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((newType != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[15]++;
              var.setType(newType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[38]++;
              astParameter.setJSType(newType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[39]++;

            } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[16]++;}

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[10]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[40]++;
int CodeCoverConditionCoverageHelper_C10;

          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parameterTypeNode != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[17]++;
            parameterTypeNode = parameterTypeNode.getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[41]++;

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[18]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((iifeArgumentNode != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[19]++;
            iifeArgumentNode = iifeArgumentNode.getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[43]++;

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[20]++;}
        }

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[6]++;}
  }

  @Override
  FlowScope createInitialEstimateLattice() {
    return bottomScope;
  }

  @Override
  FlowScope createEntryLattice() {
    return functionScope;
  }

  @Override
  FlowScope flowThrough(Node n, FlowScope input) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[44]++;
int CodeCoverConditionCoverageHelper_C12;
    // If we have not walked a path from <entry> to <n>, then we don't
    // want to infer anything about this scope.
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((input == bottomScope) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[21]++;
      return input;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[22]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[45]++;

    FlowScope output = input.createChildFlowScope();
    output = traverse(n, output);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[46]++;
    return output;
  }

  @Override
  @SuppressWarnings({"fallthrough", "incomplete-switch"})
  List<FlowScope> branchedFlowThrough(Node source, FlowScope input) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[47]++;
    // NOTE(nicksantos): Right now, we just treat ON_EX edges like UNCOND
    // edges. If we wanted to be perfect, we'd actually JOIN all the out
    // lattices of this flow with the in lattice, and then make that the out
    // lattice for the ON_EX edge. But it's probably too expensive to be
    // worthwhile.
    FlowScope output = flowThrough(source, input);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[48]++;
    Node condition = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[49]++;
    FlowScope conditionFlowScope = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[50]++;
    BooleanOutcomePair conditionOutcomes = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[51]++;

    List<DiGraphEdge<Node, Branch>> branchEdges = getCfg().getOutEdges(source);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[52]++;
    List<FlowScope> result = Lists.newArrayListWithCapacity(branchEdges.size());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[53]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[7]++;


    for (DiGraphEdge<Node, Branch> branchEdge : branchEdges) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[7]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[8]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[9]++;
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[54]++;
      Branch branch = branchEdge.getValue();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[55]++;
      FlowScope newScope = output;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[56]++;

      switch (branch) {
        case ON_TRUE:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[23]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(source)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[24]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[58]++;
            // item is assigned a property name, so its type should be string.
            Node item = source.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[59]++;
            Node obj = item.getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[60]++;

            FlowScope informed = traverse(obj, output.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;

            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((item.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[26]++;
              item = item.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[62]++;

            } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[27]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((item.isName()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[28]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[64]++;
              JSType iterKeyType = getNativeType(STRING_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[65]++;
              ObjectType objType = getJSType(obj).dereference();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[66]++;
              JSType objIndexType = objType == null ?
                  null : objType.getTemplateTypeMap().getTemplateType(OBJECT_INDEX_TEMPLATE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;
              if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((objIndexType != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((objIndexType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[30]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[68]++;
                JSType narrowedKeyType =
                    iterKeyType.getGreatestSubtype(objIndexType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((narrowedKeyType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[32]++;
                  iterKeyType = narrowedKeyType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[70]++;

                } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[33]++;}

              } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[31]++;}
              redeclareSimpleVar(informed, item, iterKeyType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[71]++;

            } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[29]++;}
            newScope = informed;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[72]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[73]++;
            break;

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[25]++;}

          // FALL THROUGH

        case ON_FALSE:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[34]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((condition == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[35]++;
            condition = NodeUtil.getConditionExpression(source);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[75]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[76]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((condition == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((source.isCase()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[37]++;
              condition = source;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[77]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[78]++;
int CodeCoverConditionCoverageHelper_C20;

              // conditionFlowScope is cached from previous iterations
              // of the loop.
              if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((conditionFlowScope == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[39]++;
                conditionFlowScope = traverse(
                    condition.getFirstChild(), output.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[79]++;

              } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[40]++;}

            } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[38]++;}

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[36]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[80]++;
int CodeCoverConditionCoverageHelper_C21;

          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((condition != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[41]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[81]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((condition.isAnd()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((condition.isOr()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[43]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[82]++;
int CodeCoverConditionCoverageHelper_C23;
              // When handling the short-circuiting binary operators,
              // the outcome scope on true can be different than the outcome
              // scope on false.
              //
              // TODO(nicksantos): The "right" way to do this is to
              // carry the known outcome all the way through the
              // recursive traversal, so that we can construct a
              // different flow scope based on the outcome. However,
              // this would require a bunch of code and a bunch of
              // extra computation for an edge case. This seems to be
              // a "good enough" approximation.

              // conditionOutcomes is cached from previous iterations
              // of the loop.
              if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((conditionOutcomes == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[45]++;
                conditionOutcomes = condition.isAnd() ?
                    traverseAnd(condition, output.createChildFlowScope()) :
                    traverseOr(condition, output.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[83]++;

              } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[46]++;}
              newScope =
                  reverseInterpreter.getPreciserScopeKnowingConditionOutcome(
                      condition,
                      conditionOutcomes.getOutcomeFlowScope(
                          condition.getType(), branch == Branch.ON_TRUE),
                      branch == Branch.ON_TRUE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[84]++;

            } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[44]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[85]++;
int CodeCoverConditionCoverageHelper_C24;
              // conditionFlowScope is cached from previous iterations
              // of the loop.
              if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((conditionFlowScope == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[47]++;
                conditionFlowScope =
                    traverse(condition, output.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[86]++;

              } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[48]++;}
              newScope =
                  reverseInterpreter.getPreciserScopeKnowingConditionOutcome(
                      condition, conditionFlowScope, branch == Branch.ON_TRUE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[87]++;
            }

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[42]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[88]++;
          break; default : CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[49]++;
      }

      result.add(newScope.optimize());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[89]++;
    }
    return result;
  }

  private FlowScope traverse(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[90]++;
    switch (n.getType()) {
      case Token.ASSIGN:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[50]++;
        scope = traverseAssign(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[91]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[92]++;
        break;

      case Token.NAME:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[51]++;
        scope = traverseName(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[93]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[94]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[52]++;
        scope = traverseGetProp(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[95]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[96]++;
        break;

      case Token.AND:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[53]++;
        scope = traverseAnd(n, scope).getJoinedFlowScope()
            .createChildFlowScope();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[97]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[98]++;
        break;

      case Token.OR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[54]++;
        scope = traverseOr(n, scope).getJoinedFlowScope()
            .createChildFlowScope();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[99]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[100]++;
        break;

      case Token.HOOK:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[55]++;
        scope = traverseHook(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[101]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[102]++;
        break;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[56]++;
        scope = traverseObjectLiteral(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[103]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[104]++;
        break;

      case Token.CALL:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[57]++;
        scope = traverseCall(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[105]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[106]++;
        break;

      case Token.NEW:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[58]++;
        scope = traverseNew(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[107]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[108]++;
        break;

      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[59]++;
      case Token.ADD:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[60]++;
        scope = traverseAdd(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[109]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[110]++;
        break;

      case Token.POS:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[61]++;
      case Token.NEG:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[62]++;
        scope = traverse(n.getFirstChild(), scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[111]++;  // Find types.
        n.setJSType(getNativeType(NUMBER_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[112]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[113]++;
        break;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[63]++;
        scope = traverseArrayLiteral(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[114]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[115]++;
        break;

      case Token.THIS:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[64]++;
        n.setJSType(scope.getTypeOfThis());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[116]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[117]++;
        break;

      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[65]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[66]++;
      case Token.LSH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[67]++;
      case Token.RSH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[68]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[69]++;
      case Token.URSH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[70]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[71]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[72]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[73]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[74]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[75]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[76]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[77]++;
      case Token.DIV:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[78]++;
      case Token.MOD:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[79]++;
      case Token.BITAND:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[80]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[81]++;
      case Token.BITOR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[82]++;
      case Token.MUL:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[83]++;
      case Token.SUB:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[84]++;
      case Token.DEC:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[85]++;
      case Token.INC:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[86]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[87]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[118]++;
        n.setJSType(getNativeType(NUMBER_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[119]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[120]++;
        break;

      case Token.PARAM_LIST:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[88]++;
        scope = traverse(n.getFirstChild(), scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[121]++;
        n.setJSType(getJSType(n.getFirstChild()));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[122]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[123]++;
        break;

      case Token.COMMA:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[89]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[124]++;
        n.setJSType(getJSType(n.getLastChild()));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[125]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[126]++;
        break;

      case Token.TYPEOF:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[90]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[127]++;
        n.setJSType(getNativeType(STRING_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[128]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[129]++;
        break;

      case Token.DELPROP:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[91]++;
      case Token.LT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[92]++;
      case Token.LE:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[93]++;
      case Token.GT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[94]++;
      case Token.GE:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[95]++;
      case Token.NOT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[96]++;
      case Token.EQ:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[97]++;
      case Token.NE:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[98]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[99]++;
      case Token.SHNE:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[100]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[101]++;
      case Token.IN:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[102]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[130]++;
        n.setJSType(getNativeType(BOOLEAN_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[131]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[132]++;
        break;

      case Token.GETELEM:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[103]++;
        scope = traverseGetElem(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[133]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[134]++;
        break;

      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[104]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[135]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[136]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[105]++;
          ensurePropertyDeclared(n.getFirstChild());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[137]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[106]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[138]++;
        break;

      case Token.SWITCH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[107]++;
        scope = traverse(n.getFirstChild(), scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[139]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[140]++;
        break;

      case Token.RETURN:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[108]++;
        scope = traverseReturn(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[141]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[142]++;
        break;

      case Token.VAR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[109]++;
      case Token.THROW:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[110]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[143]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[144]++;
        break;

      case Token.CATCH:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[111]++;
        scope = traverseCatch(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[145]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[146]++;
        break;

      case Token.CAST:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[112]++;
        scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[147]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[148]++;
        break; default : CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[113]++;
    }
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[149]++;
int CodeCoverConditionCoverageHelper_C26;

    // TODO(johnlenz): remove this after the CAST node change has shaken out.
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[114]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[150]++;
      JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[151]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[116]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[152]++;
        JSType castType = info.getType().evaluate(syntacticScope, registry);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[153]++;
int CodeCoverConditionCoverageHelper_C28;

        // A stubbed type declaration on a qualified name should take
        // effect for all subsequent accesses of that name,
        // so treat it the same as an assign to that name.
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.getParent().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[118]++;
          updateScopeForTypeChange(scope, n, n.getJSType(), castType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[154]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[119]++;}

        n.setJSType(castType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[155]++;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[117]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[115]++;}

    return scope;
  }

  /**
   * Traverse a return value.
   */
  private FlowScope traverseReturn(Node n, FlowScope scope) {
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[156]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[157]++;

    Node retValue = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[158]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((retValue != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[120]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[159]++;
      JSType type = functionScope.getRootNode().getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[160]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[122]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[161]++;
        FunctionType fnType = type.toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[162]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((fnType != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[124]++;
          inferPropertyTypesToMatchConstraint(
              retValue.getJSType(), fnType.getReturnType());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[163]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[125]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[123]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[121]++;}
    return scope;
  }

  /**
   * Any value can be thrown, so it's really impossible to determine the type
   * of a CATCH param. Treat it as the UNKNOWN type.
   */
  private FlowScope traverseCatch(Node catchNode, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[164]++;
    Node name = catchNode.getFirstChild();
    JSType type;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[165]++;
    // If the catch expression name was declared in the catch use that type,
    // otherwise use "unknown".
    JSDocInfo info = name.getJSDocInfo();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[166]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[126]++;
      type = info.getType().evaluate(syntacticScope, registry);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[167]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[127]++;
      type = getNativeType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[168]++;
    }
    redeclareSimpleVar(scope, name, type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[169]++;
    name.setJSType(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[170]++;
    return scope;
  }

  private FlowScope traverseAssign(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[171]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[172]++;
    Node right = n.getLastChild();
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[173]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[174]++;

    JSType leftType = left.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[175]++;
    JSType rightType = getJSType(right);
    n.setJSType(rightType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[176]++;

    updateScopeForTypeChange(scope, left, leftType, rightType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[177]++;
    return scope;
  }

  /**
   * Updates the scope according to the result of a type change, like
   * an assignment or a type cast.
   */
  private void updateScopeForTypeChange(
      FlowScope scope, Node left, JSType leftType, JSType resultType) {
    Preconditions.checkNotNull(resultType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[178]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[179]++;
    switch (left.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[128]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[180]++;
        String varName = left.getString();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[181]++;
        Var var = syntacticScope.getVar(varName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[182]++;

        // When looking at VAR initializers for declared VARs, we trust
        // the declared type over the type it's being initialized to.
        // This has two purposes:
        // 1) We avoid re-declaring declared variables so that built-in
        //    types defined in externs are not redeclared.
        // 2) When there's a lexical closure like
        //    /** @type {?string} */ var x = null;
        //    function f() { x = 'xyz'; }
        //    the inference will ignore the lexical closure,
        //    which is just wrong. This bug needs to be fixed eventually.
        boolean isVarDeclaration = left.hasChildren();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[183]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (32)) == 0 || true) &&
 ((isVarDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 3) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 3) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[129]++;
          redeclareSimpleVar(scope, left, resultType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[184]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[130]++;}
        left.setJSType(isVarDeclaration || leftType == null ?
            resultType : null);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[185]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[186]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[131]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[187]++;
          JSType oldType = var.getType();
          var.setType(oldType == null ?
              resultType : oldType.getLeastSupertype(resultType));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[188]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[132]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[189]++;
        break;
      case Token.GETPROP:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[133]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[190]++;
        String qualifiedName = left.getQualifiedName();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[191]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[134]++;
          scope.inferQualifiedSlot(left, qualifiedName,
              leftType == null ? unknownType : leftType,
              resultType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[192]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[135]++;}

        left.setJSType(resultType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[193]++;
        ensurePropertyDefined(left, resultType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[194]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[195]++;
        break; default : CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[136]++;
    }
  }

  /**
   * Defines a property if the property has not been defined yet.
   */
  private void ensurePropertyDefined(Node getprop, JSType rightType) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[196]++;
    String propName = getprop.getLastChild().getString();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[197]++;
    Node obj = getprop.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[198]++;
    JSType nodeType = getJSType(obj);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[199]++;
    ObjectType objectType = ObjectType.cast(
        nodeType.restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[200]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((objectType == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[137]++;
      registry.registerPropertyOnType(propName, nodeType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[201]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[138]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[202]++;
int CodeCoverConditionCoverageHelper_C37;
      // Don't add the property to @struct objects outside a constructor
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((nodeType.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((objectType.hasProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[139]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[203]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((obj.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((getJSType(syntacticScope.getRootNode()).isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[141]++;
          return;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[142]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[140]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[204]++;
int CodeCoverConditionCoverageHelper_C39;

      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ensurePropertyDeclaredHelper(getprop, objectType)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[143]++;
        return;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[144]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[205]++;
int CodeCoverConditionCoverageHelper_C40;

      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((objectType.isPropertyTypeDeclared(propName)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[145]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[206]++;
int CodeCoverConditionCoverageHelper_C41;
        // We do not want a "stray" assign to define an inferred property
        // for every object of this type in the program. So we use a heuristic
        // approach to determine whether to infer the property.
        //
        // 1) If the property is already defined, join it with the previously
        //    inferred type.
        // 2) If this isn't an instance object, define it.
        // 3) If the property of an object is being assigned in the constructor,
        //    define it.
        // 4) If this is a stub, define it.
        // 5) Otherwise, do not define the type, but declare it in the registry
        //    so that we can use it for missing property checks.
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((objectType.hasProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((objectType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[147]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[207]++;
int CodeCoverConditionCoverageHelper_C42;
          if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 (("prototype".equals(propName)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[149]++;
            objectType.defineDeclaredProperty(propName, rightType, getprop);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[208]++;

          } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[150]++;
            objectType.defineInferredProperty(propName, rightType, getprop);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[209]++;
          }

        } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[148]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[210]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((obj.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((getJSType(syntacticScope.getRootNode()).isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[151]++;
          objectType.defineInferredProperty(propName, rightType, getprop);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[211]++;

        } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[152]++;
          registry.registerPropertyOnType(propName, objectType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[212]++;
        }
}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[146]++;}
    }
  }

  /**
   * Defines a declared property if it has not been defined yet.
   *
   * This handles the case where a property is declared on an object where
   * the object type is inferred, and so the object type will not
   * be known in {@code TypedScopeCreator}.
   */
  private void ensurePropertyDeclared(Node getprop) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[213]++;
    ObjectType ownerType = ObjectType.cast(
        getJSType(getprop.getFirstChild()).restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[214]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[153]++;
      ensurePropertyDeclaredHelper(getprop, ownerType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[215]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[154]++;}
  }

  /**
   * Declares a property on its owner, if necessary.
   * @return True if a property was declared.
   */
  private boolean ensurePropertyDeclaredHelper(
      Node getprop, ObjectType objectType) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[216]++;
    String propName = getprop.getLastChild().getString();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[217]++;
    String qName = getprop.getQualifiedName();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[218]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((qName != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[155]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[219]++;
      Var var = syntacticScope.getVar(qName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[220]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[157]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[221]++;
int CodeCoverConditionCoverageHelper_C47;
        // Handle normal declarations that could not be addressed earlier.
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (512)) == 0 || true) &&
 ((propName.equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (256)) == 0 || true)))
 || (!
(((CodeCoverConditionCoverageHelper_C47 |= (128)) == 0 || true) &&
 ((objectType.hasOwnProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (64)) == 0 || true)))
 && (!
(((CodeCoverConditionCoverageHelper_C47 |= (32)) == 0 || true) &&
 ((objectType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((var.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((objectType.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
))))) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 5) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 5) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[159]++;
          return objectType.defineDeclaredProperty(
              propName, var.getType(), getprop);

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[160]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[158]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[156]++;}
    return false;
  }

  private FlowScope traverseName(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[222]++;
    String varName = n.getString();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[223]++;
    Node value = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[224]++;
    JSType type = n.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[225]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[161]++;
      scope = traverse(value, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[226]++;
      updateScopeForTypeChange(scope, n, n.getJSType() /* could be null */,
          getJSType(value));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[227]++;
      return scope;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[162]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[228]++;
      StaticSlot<JSType> var = scope.getSlot(varName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[229]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[163]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[230]++;
        // There are two situations where we don't want to use type information
        // from the scope, even if we have it.

        // 1) The var is escaped and assigned in an inner scope, e.g.,
        // function f() { var x = 3; function g() { x = null } (x); }
        boolean isInferred = var.isTypeInferred();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[231]++;
        boolean unflowable = isInferred &&
            isUnflowable(syntacticScope.getVar(varName));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[232]++;

        // 2) We're reading type information from another scope for an
        // inferred variable. That variable is assigned more than once,
        // and we can't know which type we're getting.
        //
        // var t = null; function f() { (t); } doStuff(); t = {};
        //
        // Notice that this heuristic isn't perfect. For example, you might
        // have:
        //
        // function f() { (t); } f(); var t = 3;
        //
        // In this case, we would infer the first reference to t as
        // type {number}, even though it's undefined.
        boolean nonLocalInferredSlot = false;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[233]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((isInferred) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((syntacticScope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[165]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[234]++;
          Var maybeOuterVar = syntacticScope.getParent().getVar(varName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[235]++;
int CodeCoverConditionCoverageHelper_C51;
          if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((var == maybeOuterVar) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((maybeOuterVar.isMarkedAssignedExactlyOnce()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[167]++;
            nonLocalInferredSlot = true;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[236]++;

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[168]++;}

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[166]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[237]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((unflowable) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((nonLocalInferredSlot) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[169]++;
          type = var.getType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[238]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[239]++;
int CodeCoverConditionCoverageHelper_C53;
          if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[171]++;
            type = unknownType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[240]++;

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[172]++;}

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[170]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[164]++;}
    }
    n.setJSType(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[241]++;
    return scope;
  }

  /** Traverse each element of the array. */
  private FlowScope traverseArrayLiteral(Node n, FlowScope scope) {
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[242]++;
    n.setJSType(getNativeType(ARRAY_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[243]++;
    return scope;
  }

  private FlowScope traverseObjectLiteral(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[244]++;
    JSType type = n.getJSType();
    Preconditions.checkNotNull(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[245]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[246]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[10]++;


int CodeCoverConditionCoverageHelper_C54;

    for (Node name = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); name = name.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[10]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[11]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[12]++;
}
      scope = traverse(name.getFirstChild(), scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[247]++;
    }
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[248]++;

    // Object literals can be reflected on other types, or changed with
    // type casts.
    // See CodingConvention#getObjectLiteralCase and goog.object.reflect.
    // Ignore these types of literals.
    // TODO(nicksantos): There should be an "anonymous object" type that
    // we can check for here.
    ObjectType objectType = ObjectType.cast(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[249]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((objectType == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[173]++;
      return scope;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[174]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[250]++;

    boolean hasLendsName = n.getJSDocInfo() != null &&
        n.getJSDocInfo().getLendsName() != null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[251]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((objectType.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((hasLendsName) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[175]++;
      return scope;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[176]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[252]++;

    String qObjName = NodeUtil.getBestLValueName(
        NodeUtil.getBestLValue(n));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[253]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[13]++;


int CodeCoverConditionCoverageHelper_C57;
    for (Node name = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false);
         name = name.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[13]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[14]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[15]++;
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[254]++;
      String memberName = NodeUtil.getObjectLitKeyName(name);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[255]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((memberName != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[177]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[256]++;
        JSType rawValueType =  name.getFirstChild().getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[257]++;
        JSType valueType = NodeUtil.getObjectLitKeyTypeFromValueType(
            name, rawValueType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[258]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((valueType == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[179]++;
          valueType = unknownType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[259]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[180]++;}
        objectType.defineInferredProperty(memberName, valueType, name);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[260]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[261]++;
int CodeCoverConditionCoverageHelper_C60;

        // Do normal flow inference if this is a direct property assignment.
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((qObjName != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((name.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[181]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[262]++;
          String qKeyName = qObjName + "." + memberName;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[263]++;
          Var var = syntacticScope.getVar(qKeyName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[264]++;
          JSType oldType = var == null ? null : var.getType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[265]++;
int CodeCoverConditionCoverageHelper_C61;
          if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[183]++;
            var.setType(oldType == null ?
                valueType : oldType.getLeastSupertype(oldType));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[266]++;

          } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[184]++;}

          scope.inferQualifiedSlot(name, qKeyName,
              oldType == null ? unknownType : oldType,
              valueType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[267]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[182]++;}

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[178]++;
        n.setJSType(unknownType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[268]++;
      }
    }
    return scope;
  }

  private FlowScope traverseAdd(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[269]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[270]++;
    Node right = left.getNext();
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[271]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[272]++;

    JSType leftType = left.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[273]++;
    JSType rightType = right.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[274]++;

    JSType type = unknownType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[275]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((leftType != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((rightType != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[185]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[276]++;
      boolean leftIsUnknown = leftType.isUnknownType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[277]++;
      boolean rightIsUnknown = rightType.isUnknownType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[278]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((leftIsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((rightIsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[187]++;
        type = unknownType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[279]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[188]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[280]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && ((!
(((CodeCoverConditionCoverageHelper_C64 |= (128)) == 0 || true) &&
 ((leftIsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (32)) == 0 || true) &&
 ((leftType.isString()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (16)) == 0 || true)))
) || (!
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((rightIsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((rightType.isString()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 4) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 4) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[189]++;
        type = getNativeType(STRING_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[281]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[190]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[282]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((leftIsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((rightIsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[191]++;
        type = unknownType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[283]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[192]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[284]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((isAddedAsNumber(leftType)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((isAddedAsNumber(rightType)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[193]++;
        type = getNativeType(NUMBER_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[285]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[194]++;
        type = registry.createUnionType(STRING_TYPE, NUMBER_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[286]++;
      }
}
}
}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[186]++;}
    n.setJSType(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[287]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[288]++;
int CodeCoverConditionCoverageHelper_C67;

    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((n.isAssignAdd()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[195]++;
      updateScopeForTypeChange(scope, left, leftType, type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[289]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[196]++;}

    return scope;
  }

  private boolean isAddedAsNumber(JSType type) {
    return type.isSubtype(registry.createUnionType(VOID_TYPE, NULL_TYPE,
        NUMBER_VALUE_OR_OBJECT_TYPE, BOOLEAN_TYPE, BOOLEAN_OBJECT_TYPE));
  }

  private FlowScope traverseHook(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[290]++;
    Node condition = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[291]++;
    Node trueNode = condition.getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[292]++;
    Node falseNode = n.getLastChild();

    // verify the condition
    scope = traverse(condition, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[293]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[294]++;

    // reverse abstract interpret the condition to produce two new scopes
    FlowScope trueScope = reverseInterpreter.
        getPreciserScopeKnowingConditionOutcome(
            condition, scope, true);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[295]++;
    FlowScope falseScope = reverseInterpreter.
        getPreciserScopeKnowingConditionOutcome(
            condition, scope, false);

    // traverse the true node with the trueScope
    traverse(trueNode, trueScope.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[296]++;

    // traverse the false node with the falseScope
    traverse(falseNode, falseScope.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[297]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[298]++;

    // meet true and false nodes' types and assign
    JSType trueType = trueNode.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[299]++;
    JSType falseType = falseNode.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[300]++;
int CodeCoverConditionCoverageHelper_C68;
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((trueType != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((falseType != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[197]++;
      n.setJSType(trueType.getLeastSupertype(falseType));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[301]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[198]++;
      n.setJSType(null);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[302]++;
    }

    return scope.createChildFlowScope();
  }

  private FlowScope traverseCall(Node n, FlowScope scope) {
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[303]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[304]++;

    Node left = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[305]++;
    JSType functionType = getJSType(left).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[306]++;
int CodeCoverConditionCoverageHelper_C69;
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((functionType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[199]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[307]++;
      FunctionType fnType = functionType.toMaybeFunctionType();
      n.setJSType(fnType.getReturnType());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[308]++;
      backwardsInferenceFromCallSite(n, fnType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[309]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[200]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[310]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((functionType.isEquivalentTo(
        getNativeType(CHECKED_UNKNOWN_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[201]++;
      n.setJSType(getNativeType(CHECKED_UNKNOWN_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[311]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[202]++;}
}

    scope = tightenTypesAfterAssertions(scope, n);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[312]++;
    return scope;
  }

  private FlowScope tightenTypesAfterAssertions(FlowScope scope,
      Node callNode) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[313]++;
    Node left = callNode.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[314]++;
    Node firstParam = left.getNext();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[315]++;
    AssertionFunctionSpec assertionFunctionSpec =
        assertionFunctionsMap.get(left.getQualifiedName());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[316]++;
int CodeCoverConditionCoverageHelper_C71;
    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((assertionFunctionSpec == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((firstParam == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[203]++;
      return scope;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[204]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[317]++;
    Node assertedNode = assertionFunctionSpec.getAssertedParam(firstParam);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[318]++;
int CodeCoverConditionCoverageHelper_C72;
    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((assertedNode == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[205]++;
      return scope;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[206]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[319]++;
    JSType assertedType = assertionFunctionSpec.getAssertedType(
        callNode, registry);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[320]++;
    String assertedNodeName = assertedNode.getQualifiedName();

    JSType narrowed;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[321]++;
int CodeCoverConditionCoverageHelper_C73;
    // Handle assertions that enforce expressions evaluate to true.
    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((assertedType == null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[207]++;
      // Handle arbitrary expressions within the assert.
      scope = reverseInterpreter.getPreciserScopeKnowingConditionOutcome(
          assertedNode, scope, true);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[322]++;
      // Build the result of the assertExpression
      narrowed = getJSType(assertedNode).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[323]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[208]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[324]++;
      // Handle assertions that enforce expressions are of a certain type.
      JSType type = getJSType(assertedNode);
      narrowed = type.getGreatestSubtype(assertedType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[325]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[326]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((assertedNodeName != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((type.differsFrom(narrowed)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[209]++;
        scope = narrowScope(scope, assertedNode, narrowed);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[327]++;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[210]++;}
    }

    callNode.setJSType(narrowed);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[328]++;
    return scope;
  }

  private FlowScope narrowScope(FlowScope scope, Node node, JSType narrowed) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[329]++;
int CodeCoverConditionCoverageHelper_C75;
    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((node.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[211]++;
      // "this" references don't need to be modeled in the control flow graph.
      return scope;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[212]++;}

    scope = scope.createChildFlowScope();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[330]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[331]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[213]++;
      scope.inferQualifiedSlot(
          node, node.getQualifiedName(), getJSType(node), narrowed);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[332]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[214]++;
      redeclareSimpleVar(scope, node, narrowed);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[333]++;
    }
    return scope;
  }

  /**
   * We only do forward type inference. We do not do full backwards
   * type inference.
   *
   * In other words, if we have,
   * <code>
   * var x = f();
   * g(x);
   * </code>
   * a forward type-inference engine would try to figure out the type
   * of "x" from the return type of "f". A backwards type-inference engine
   * would try to figure out the type of "x" from the parameter type of "g".
   *
   * However, there are a few special syntactic forms where we do some
   * some half-assed backwards type-inference, because programmers
   * expect it in this day and age. To take an example from Java,
   * <code>
   * List<String> x = Lists.newArrayList();
   * </code>
   * The Java compiler will be able to infer the generic type of the List
   * returned by newArrayList().
   *
   * In much the same way, we do some special-case backwards inference for
   * JS. Those cases are enumerated here.
   */
  private void backwardsInferenceFromCallSite(Node n, FunctionType fnType) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[334]++;
    boolean updatedFnType = inferTemplatedTypesForCall(n, fnType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[335]++;
int CodeCoverConditionCoverageHelper_C77;
    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((updatedFnType) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[215]++;
      fnType = n.getFirstChild().getJSType().toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[336]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[216]++;}
    updateTypeOfParameters(n, fnType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[337]++;
    updateBind(n);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[338]++;
  }

  /**
   * When "bind" is called on a function, we infer the type of the returned
   * "bound" function by looking at the number of parameters in the call site.
   */
  private void updateBind(Node n) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[339]++;
    CodingConvention.Bind bind =
        compiler.getCodingConvention().describeFunctionBind(n, true);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[340]++;
int CodeCoverConditionCoverageHelper_C78;
    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((bind == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[217]++;
      return;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[218]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[341]++;

    FunctionType callTargetFn = getJSType(bind.target)
        .restrictByNotNullOrUndefined().toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[342]++;
int CodeCoverConditionCoverageHelper_C79;
    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((callTargetFn == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[219]++;
      return;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[220]++;}

    n.setJSType(
        callTargetFn.getBindReturnType(
            // getBindReturnType expects the 'this' argument to be included.
            bind.getBoundParameterCount() + 1));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[343]++;
  }

  /**
   * For functions with function parameters, type inference will set the type of
   * a function literal argument from the function parameter type.
   */
  private void updateTypeOfParameters(Node n, FunctionType fnType) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[344]++;
    int i = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[345]++;
    int childCount = n.getChildCount();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[346]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[16]++;


    for (Node iParameter : fnType.getParameters()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[16]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[17]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[18]++;
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[347]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((i + 1 >= childCount) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[221]++;
        // TypeCheck#visitParametersList will warn so we bail.
        return;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[222]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[348]++;

      JSType iParameterType = getJSType(iParameter);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[349]++;
      Node iArgument = n.getChildAtIndex(i + 1);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[350]++;
      JSType iArgumentType = getJSType(iArgument);
      inferPropertyTypesToMatchConstraint(iArgumentType, iParameterType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[351]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[352]++;

      // TODO(johnlenz): Filter out non-function types
      // (such as null and undefined) as
      // we only care about FUNCTION subtypes here.
      JSType restrictedParameter = iParameterType
          .restrictByNotNullOrUndefined()
          .toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[353]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((restrictedParameter != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[223]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[354]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (32)) == 0 || true) &&
 ((iArgument.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((iArgumentType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((iArgument.getJSDocInfo() == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 3) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 3) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[225]++;
          iArgument.setJSType(restrictedParameter);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[355]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[226]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[224]++;}
      i++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[356]++;
    }
  }

  private Map<TemplateType, JSType> inferTemplateTypesFromParameters(
      FunctionType fnType, Node call) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[357]++;
int CodeCoverConditionCoverageHelper_C83;
    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((fnType.getTemplateTypeMap().getTemplateKeys().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[227]++;
      return Collections.emptyMap();

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[228]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[358]++;

    Map<TemplateType, JSType> resolvedTypes = Maps.newIdentityHashMap();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[359]++;

    Node callTarget = call.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[360]++;
int CodeCoverConditionCoverageHelper_C84;
    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(callTarget)) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[229]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[361]++;
      Node obj = callTarget.getFirstChild();
      maybeResolveTemplatedType(
          fnType.getTypeOfThis(),
          getJSType(obj),
          resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[362]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[230]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[363]++;
int CodeCoverConditionCoverageHelper_C85;

    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((call.hasMoreThanOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[231]++;
      maybeResolveTemplateTypeFromNodes(
          fnType.getParameters(),
          call.getChildAtIndex(1).siblings(),
          resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[364]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[232]++;}
    return resolvedTypes;
  }

  private void maybeResolveTemplatedType(
      JSType paramType,
      JSType argType,
      Map<TemplateType, JSType> resolvedTypes) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[365]++;
int CodeCoverConditionCoverageHelper_C86;
    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((paramType.isTemplateType()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[233]++;
      // @param {T}
      resolvedTemplateType(
          resolvedTypes, paramType.toMaybeTemplateType(), argType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[366]++;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[234]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[367]++;
int CodeCoverConditionCoverageHelper_C87; if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((paramType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[235]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[368]++;
      // @param {Array.<T>|NodeList|Arguments|{length:number}}
      UnionType unionType = paramType.toMaybeUnionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[369]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[19]++;


      for (JSType alernative : unionType.getAlternates()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[19]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[20]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[21]++;
}
        maybeResolveTemplatedType(alernative, argType, resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[370]++;
      }

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[236]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[371]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((paramType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[237]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[372]++;
      FunctionType paramFunctionType = paramType.toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[373]++;
      FunctionType argFunctionType = argType
          .restrictByNotNullOrUndefined()
          .collapseUnion()
          .toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[374]++;
int CodeCoverConditionCoverageHelper_C89;
      if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (8)) == 0 || true) &&
 ((argFunctionType != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((argFunctionType.isSubtype(paramType)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[239]++;
        // infer from return type of the function type
        maybeResolveTemplatedType(
            paramFunctionType.getTypeOfThis(),
            argFunctionType.getTypeOfThis(), resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[375]++;
        // infer from return type of the function type
        maybeResolveTemplatedType(
            paramFunctionType.getReturnType(),
            argFunctionType.getReturnType(), resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[376]++;
        // infer from parameter types of the function type
        maybeResolveTemplateTypeFromNodes(
            paramFunctionType.getParameters(),
            argFunctionType.getParameters(), resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[377]++;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[240]++;}

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[238]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[378]++;
int CodeCoverConditionCoverageHelper_C90; if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((paramType.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[241]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[379]++;
      // @param {Array.<T>}
      ObjectType referencedParamType = paramType
          .toMaybeTemplatizedType()
          .getReferencedType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[380]++;
      JSType argObjectType = argType
          .restrictByNotNullOrUndefined()
          .collapseUnion();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[381]++;
int CodeCoverConditionCoverageHelper_C91;

      if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((argObjectType.isSubtype(referencedParamType)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[243]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[382]++;
        // If the argument type is a subtype of the parameter type, resolve any
        // template types amongst their templatized types.
        TemplateTypeMap paramTypeMap = paramType.getTemplateTypeMap();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[383]++;
        TemplateTypeMap argTypeMap = argObjectType.getTemplateTypeMap();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[384]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[22]++;


        for (String key : paramTypeMap.getTemplateKeys()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[22]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[23]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[24]++;
}
          maybeResolveTemplatedType(
              paramTypeMap.getTemplateType(key),
              argTypeMap.getTemplateType(key),
              resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[385]++;
        }

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[244]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[242]++;}
}
}
}
  }

  private void maybeResolveTemplateTypeFromNodes(
      Iterable<Node> declParams,
      Iterable<Node> callParams,
      Map<TemplateType, JSType> resolvedTypes) {
    maybeResolveTemplateTypeFromNodes(
        declParams.iterator(), callParams.iterator(), resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[386]++;
  }

  private void maybeResolveTemplateTypeFromNodes(
      Iterator<Node> declParams,
      Iterator<Node> callParams,
      Map<TemplateType, JSType> resolvedTypes) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[387]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[25]++;


int CodeCoverConditionCoverageHelper_C92;
    while ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((declParams.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((callParams.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[25]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[26]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[27]++;
}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[388]++;
      Node declParam = declParams.next();
      maybeResolveTemplatedType(
          getJSType(declParam),
          getJSType(callParams.next()),
          resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[389]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[390]++;
int CodeCoverConditionCoverageHelper_C93;
      if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((declParam.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[245]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[391]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[28]++;


int CodeCoverConditionCoverageHelper_C94;
        while ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((callParams.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[28]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[29]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[30]++;
}
          maybeResolveTemplatedType(
              getJSType(declParam),
              getJSType(callParams.next()),
              resolvedTypes);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[392]++;
        }

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[246]++;}
    }
  }

  private void resolvedTemplateType(
      Map<TemplateType, JSType> map, TemplateType template, JSType resolved) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[393]++;
    JSType previous = map.get(template);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[394]++;
int CodeCoverConditionCoverageHelper_C95;
    if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((resolved.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[247]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[395]++;
int CodeCoverConditionCoverageHelper_C96;
      if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((previous == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[249]++;
        map.put(template, resolved);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[396]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[250]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[397]++;
        JSType join = previous.getLeastSupertype(resolved);
        map.put(template, join);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[398]++;
      }

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[248]++;}
  }

  private static class TemplateTypeReplacer extends ModificationVisitor {
    private final Map<TemplateType, JSType> replacements;
    private final JSTypeRegistry registry;
    boolean madeChanges = false;
  {
    CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[399]++;
  }

    TemplateTypeReplacer(
        JSTypeRegistry registry, Map<TemplateType, JSType> replacements) {
      super(registry);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[400]++;
      this.registry = registry;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[401]++;
      this.replacements = replacements;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[402]++;
    }

    @Override
    public JSType caseTemplateType(TemplateType type) {
      madeChanges = true;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[403]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[404]++;
      JSType replacement = replacements.get(type);
      return replacement != null ?
          replacement : registry.getNativeType(UNKNOWN_TYPE);
    }
  }

  /**
   * For functions with function(this: T, ...) and T as parameters, type
   * inference will set the type of this on a function literal argument to the
   * the actual type of T.
   */
  private boolean inferTemplatedTypesForCall(
      Node n, FunctionType fnType) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[405]++;
int CodeCoverConditionCoverageHelper_C97;
    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((fnType.getTemplateTypeMap().getTemplateKeys().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[251]++;
      return false;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[252]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[406]++;

    // Try to infer the template types
    Map<TemplateType, JSType> inferred = inferTemplateTypesFromParameters(
        fnType, n);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[407]++;

    // Replace all template types. If we couldn't find a replacement, we
    // replace it with UNKNOWN.
    TemplateTypeReplacer replacer = new TemplateTypeReplacer(
        registry, inferred);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[408]++;
    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[409]++;

    FunctionType replacementFnType = fnType.visit(replacer)
        .toMaybeFunctionType();
    Preconditions.checkNotNull(replacementFnType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[410]++;

    callTarget.setJSType(replacementFnType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[411]++;
    n.setJSType(replacementFnType.getReturnType());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[412]++;

    return replacer.madeChanges;
  }

  private FlowScope traverseNew(Node n, FlowScope scope) {
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[413]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[414]++;

    Node constructor = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[415]++;
    JSType constructorType = constructor.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[416]++;
    JSType type = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[417]++;
int CodeCoverConditionCoverageHelper_C98;
    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((constructorType != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[253]++;
      constructorType = constructorType.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[418]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[419]++;
int CodeCoverConditionCoverageHelper_C99;
      if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((constructorType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[255]++;
        type = unknownType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[420]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[256]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[421]++;
        FunctionType ct = constructorType.toMaybeFunctionType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[422]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((ct == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((constructorType instanceof FunctionType) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[257]++;
          // If constructorType is a NoObjectType, then toMaybeFunctionType will
          // return null. But NoObjectType implements the FunctionType
          // interface, precisely because it can validly construct objects.
          ct = (FunctionType) constructorType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[423]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[258]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[424]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (8)) == 0 || true) &&
 ((ct != null) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((ct.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[259]++;
          type = ct.getInstanceType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[425]++;
          backwardsInferenceFromCallSite(n, ct);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[426]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[260]++;}
      }

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[254]++;}
    n.setJSType(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[427]++;
    return scope;
  }

  private BooleanOutcomePair traverseAnd(Node n, FlowScope scope) {
    return traverseShortCircuitingBinOp(n, scope, true);
  }

  private FlowScope traverseChildren(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[428]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[31]++;


int CodeCoverConditionCoverageHelper_C102;
    for (Node el = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((el != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false); el = el.getNext()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[31]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[32]--;
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.loops[33]++;
}
      scope = traverse(el, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[429]++;
    }
    return scope;
  }

  private FlowScope traverseGetElem(Node n, FlowScope scope) {
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[430]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[431]++;
    JSType type = getJSType(n.getFirstChild()).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[432]++;
    TemplateTypeMap typeMap = type.getTemplateTypeMap();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[433]++;
int CodeCoverConditionCoverageHelper_C103;
    if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((typeMap.hasTemplateType(OBJECT_ELEMENT_TEMPLATE)) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[261]++;
      n.setJSType(typeMap.getTemplateType(OBJECT_ELEMENT_TEMPLATE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[434]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[262]++;}
    return dereferencePointer(n.getFirstChild(), scope);
  }

  private FlowScope traverseGetProp(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[435]++;
    Node objNode = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[436]++;
    Node property = n.getLastChild();
    scope = traverseChildren(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[437]++;

    n.setJSType(
        getPropertyType(
            objNode.getJSType(), property.getString(), n, scope));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[438]++;
    return dereferencePointer(n.getFirstChild(), scope);
  }

  /**
   * Suppose X is an object with inferred properties.
   * Suppose also that X is used in a way where it would only type-check
   * correctly if some of those properties are widened.
   * Then we should be polite and automatically widen X's properties for him.
   *
   * For a concrete example, consider:
   * param x {{prop: (number|undefined)}}
   * function f(x) {}
   * f({});
   *
   * If we give the anonymous object an inferred property of (number|undefined),
   * then this code will type-check appropriately.
   */
  private void inferPropertyTypesToMatchConstraint(
      JSType type, JSType constraint) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[439]++;
int CodeCoverConditionCoverageHelper_C104;
    if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((constraint == null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[263]++;
      return;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[264]++;}

    type.matchConstraint(constraint);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[440]++;
  }

  /**
   * If we access a property of a symbol, then that symbol is not
   * null or undefined.
   */
  private FlowScope dereferencePointer(Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[441]++;
int CodeCoverConditionCoverageHelper_C105;
    if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[265]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[442]++;
      JSType type = getJSType(n);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[443]++;
      JSType narrowed = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[444]++;
int CodeCoverConditionCoverageHelper_C106;
      if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((type != narrowed) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[267]++;
        scope = narrowScope(scope, n, narrowed);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[445]++;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[268]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[266]++;}
    return scope;
  }

  private JSType getPropertyType(JSType objType, String propName,
      Node n, FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[446]++;
    // We often have a couple of different types to choose from for the
    // property. Ordered by accuracy, we have
    // 1) A locally inferred qualified name (which is in the FlowScope)
    // 2) A globally declared qualified name (which is in the FlowScope)
    // 3) A property on the owner type (which is on objType)
    // 4) A name in the type registry (as a last resort)
    JSType propertyType = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[447]++;
    boolean isLocallyInferred = false;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[448]++;

    // Scopes sometimes contain inferred type info about qualified names.
    String qualifiedName = n.getQualifiedName();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[449]++;
    StaticSlot<JSType> var = scope.getSlot(qualifiedName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[450]++;
int CodeCoverConditionCoverageHelper_C107;
    if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[269]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[451]++;
      JSType varType = var.getType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[452]++;
int CodeCoverConditionCoverageHelper_C108;
      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((varType != null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[271]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[453]++;
        boolean isDeclared = !var.isTypeInferred();
        isLocallyInferred = (var != syntacticScope.getSlot(qualifiedName));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[454]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[455]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((isDeclared) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((isLocallyInferred) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[273]++;
          propertyType = varType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[456]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[274]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[272]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[270]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[457]++;
int CodeCoverConditionCoverageHelper_C110;

    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((propertyType == null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[275]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[458]++;
      JSType foundType = objType.findPropertyType(propName);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[459]++;
int CodeCoverConditionCoverageHelper_C111;
      if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((foundType != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[277]++;
        propertyType = foundType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[460]++;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[278]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[276]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[461]++;
int CodeCoverConditionCoverageHelper_C112;

    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C112 |= (32)) == 0 || true) &&
 ((propertyType == null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C112 |= (8)) == 0 || true) &&
 ((propertyType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 3) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 3) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[279]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[462]++;
      // If we find this node in the registry, then we can infer its type.
      ObjectType regType = ObjectType.cast(registry.getType(qualifiedName));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[463]++;
int CodeCoverConditionCoverageHelper_C113;
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((regType != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[281]++;
        propertyType = regType.getConstructor();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[464]++;

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[282]++;}

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[280]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[465]++;
int CodeCoverConditionCoverageHelper_C114;

    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((propertyType == null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[283]++;
      return unknownType;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[284]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[466]++;
int CodeCoverConditionCoverageHelper_C115; if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((propertyType.isEquivalentTo(unknownType)) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((isLocallyInferred) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[285]++;
      // If the type has been checked in this scope,
      // then use CHECKED_UNKNOWN_TYPE instead to indicate that.
      return getNativeType(CHECKED_UNKNOWN_TYPE);

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[286]++;
      return propertyType;
    }
}
  }

  private BooleanOutcomePair traverseOr(Node n, FlowScope scope) {
    return traverseShortCircuitingBinOp(n, scope, false);
  }

  private BooleanOutcomePair traverseShortCircuitingBinOp(
      Node n, FlowScope scope, boolean condition) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[467]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[468]++;
    Node right = n.getLastChild();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[469]++;

    // type the left node
    BooleanOutcomePair leftLiterals =
        traverseWithinShortCircuitingBinOp(left,
            scope.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[470]++;
    JSType leftType = left.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[471]++;

    // reverse abstract interpret the left node to produce the correct
    // scope in which to verify the right node
    FlowScope rightScope = reverseInterpreter.
        getPreciserScopeKnowingConditionOutcome(
            left, leftLiterals.getOutcomeFlowScope(left.getType(), condition),
            condition);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[472]++;

    // type the right node
    BooleanOutcomePair rightLiterals =
        traverseWithinShortCircuitingBinOp(
            right, rightScope.createChildFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[473]++;
    JSType rightType = right.getJSType();

    JSType type;
    BooleanOutcomePair literals;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[474]++;
int CodeCoverConditionCoverageHelper_C116;
    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (8)) == 0 || true) &&
 ((leftType != null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((rightType != null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[287]++;
      leftType = leftType.getRestrictedTypeGivenToBooleanOutcome(!condition);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[475]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[476]++;
int CodeCoverConditionCoverageHelper_C117;
      if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((leftLiterals.toBooleanOutcomes ==
          BooleanLiteralSet.get(!condition)) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[289]++;
        // Use the restricted left type, since the right side never gets
        // evaluated.
        type = leftType;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[477]++;
        literals = leftLiterals;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[478]++;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[290]++;
        // Use the join of the restricted left type knowing the outcome of the
        // ToBoolean predicate and of the right type.
        type = leftType.getLeastSupertype(rightType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[479]++;
        literals =
            getBooleanOutcomePair(leftLiterals, rightLiterals, condition);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[480]++;
      }
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[481]++;
int CodeCoverConditionCoverageHelper_C118;

      // Exclude the boolean type if the literal set is empty because a boolean
      // can never actually be returned.
      if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((literals.booleanValues == BooleanLiteralSet.EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((getNativeType(BOOLEAN_TYPE).isSubtype(type)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[291]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[482]++;
int CodeCoverConditionCoverageHelper_C119;
        // Exclusion only make sense for a union type.
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[293]++;
          type = type.toMaybeUnionType().getRestrictedUnion(
              getNativeType(BOOLEAN_TYPE));
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[483]++;

        } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[294]++;}

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[292]++;}

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[288]++;
      type = null;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[484]++;
      literals = new BooleanOutcomePair(
          BooleanLiteralSet.BOTH, BooleanLiteralSet.BOTH,
          leftLiterals.getJoinedFlowScope(),
          rightLiterals.getJoinedFlowScope());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[485]++;
    }
    n.setJSType(type);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[486]++;

    return literals;
  }

  private BooleanOutcomePair traverseWithinShortCircuitingBinOp(Node n,
      FlowScope scope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[487]++;
    switch (n.getType()) {
      case Token.AND:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[295]++;
        return traverseAnd(n, scope);

      case Token.OR:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[296]++;
        return traverseOr(n, scope);

      default:
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[297]++;
        scope = traverse(n, scope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[488]++;
        return newBooleanOutcomePair(n.getJSType(), scope);
    }
  }

  /**
   * Infers the boolean outcome pair that can be taken by a
   * short-circuiting binary operation ({@code &&} or {@code ||}).
   * @see #getBooleanOutcomes(BooleanLiteralSet, BooleanLiteralSet, boolean)
   */
  BooleanOutcomePair getBooleanOutcomePair(BooleanOutcomePair left,
      BooleanOutcomePair right, boolean condition) {
    return new BooleanOutcomePair(
        getBooleanOutcomes(left.toBooleanOutcomes, right.toBooleanOutcomes,
                           condition),
        getBooleanOutcomes(left.booleanValues, right.booleanValues, condition),
        left.getJoinedFlowScope(), right.getJoinedFlowScope());
  }

  /**
   * Infers the boolean literal set that can be taken by a
   * short-circuiting binary operation ({@code &&} or {@code ||}).
   * @param left the set of possible {@code ToBoolean} predicate results for
   *    the expression on the left side of the operator
   * @param right the set of possible {@code ToBoolean} predicate results for
   *    the expression on the right side of the operator
   * @param condition the left side {@code ToBoolean} predicate result that
   *    causes the right side to get evaluated (i.e. not short-circuited)
   * @return a set of possible {@code ToBoolean} predicate results for the
   *    entire expression
   */
  static BooleanLiteralSet getBooleanOutcomes(BooleanLiteralSet left,
      BooleanLiteralSet right, boolean condition) {
    return right.union(left.intersection(BooleanLiteralSet.get(!condition)));
  }

  /**
   * When traversing short-circuiting binary operations, we need to keep track
   * of two sets of boolean literals:
   * 1. {@code toBooleanOutcomes}: boolean literals as converted from any types,
   * 2. {@code booleanValues}: boolean literals from just boolean types.
   */
  private final class BooleanOutcomePair {
    final BooleanLiteralSet toBooleanOutcomes;
    final BooleanLiteralSet booleanValues;

    // The scope if only half of the expression executed, when applicable.
    final FlowScope leftScope;

    // The scope when the whole expression executed.
    final FlowScope rightScope;

    // The scope when we don't know how much of the expression is executed.
    FlowScope joinedScope = null;
  {
    CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[489]++;
  }

    BooleanOutcomePair(
        BooleanLiteralSet toBooleanOutcomes, BooleanLiteralSet booleanValues,
        FlowScope leftScope, FlowScope rightScope) {
      this.toBooleanOutcomes = toBooleanOutcomes;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[490]++;
      this.booleanValues = booleanValues;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[491]++;
      this.leftScope = leftScope;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[492]++;
      this.rightScope = rightScope;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[493]++;
    }

    /**
     * Gets the safe estimated scope without knowing if all of the
     * subexpressions will be evaluated.
     */
    FlowScope getJoinedFlowScope() {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[494]++;
int CodeCoverConditionCoverageHelper_C120;
      if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((joinedScope == null) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[298]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[495]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((leftScope == rightScope) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[300]++;
          joinedScope = rightScope;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[496]++;

        } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[301]++;
          joinedScope = join(leftScope, rightScope);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[497]++;
        }

      } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[299]++;}
      return joinedScope;
    }

    /**
     * Gets the outcome scope if we do know the outcome of the entire
     * expression.
     */
    FlowScope getOutcomeFlowScope(int nodeType, boolean outcome) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[498]++;
int CodeCoverConditionCoverageHelper_C122;
      if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (128)) == 0 || true) &&
 ((nodeType == Token.AND) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C122 |= (32)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C122 |= (8)) == 0 || true) &&
 ((nodeType == Token.OR) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 4) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 4) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[302]++;
        // We know that the whole expression must have executed.
        return rightScope;

      } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[303]++;
        return getJoinedFlowScope();
      }
    }
  }

  private BooleanOutcomePair newBooleanOutcomePair(
      JSType jsType, FlowScope flowScope) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[499]++;
int CodeCoverConditionCoverageHelper_C123;
    if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[304]++;
      return new BooleanOutcomePair(
          BooleanLiteralSet.BOTH, BooleanLiteralSet.BOTH, flowScope, flowScope);

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[305]++;}
    return new BooleanOutcomePair(jsType.getPossibleToBooleanOutcomes(),
        registry.getNativeType(BOOLEAN_TYPE).isSubtype(jsType) ?
            BooleanLiteralSet.BOTH : BooleanLiteralSet.EMPTY,
        flowScope, flowScope);
  }

  private void redeclareSimpleVar(
      FlowScope scope, Node nameNode, JSType varType) {
    Preconditions.checkState(nameNode.isName());
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[500]++;
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[501]++;
    String varName = nameNode.getString();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[502]++;
int CodeCoverConditionCoverageHelper_C124;
    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((varType == null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[306]++;
      varType = getNativeType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[503]++;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[307]++;}
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[504]++;
int CodeCoverConditionCoverageHelper_C125;
    if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((isUnflowable(syntacticScope.getVar(varName))) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[308]++;
      return;

    } else {
  CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[309]++;}
    scope.inferSlotType(varName, varType);
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[505]++;
  }

  private boolean isUnflowable(Var v) {
    return v != null && v.isLocal() && v.isMarkedEscaped() &&
        // It's OK to flow a variable in the scope where it's escaped.
        v.getScope() == syntacticScope;
  }

  /**
   * This method gets the JSType from the Node argument and verifies that it is
   * present.
   */
  private JSType getJSType(Node n) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[506]++;
    JSType jsType = n.getJSType();
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.statements[507]++;
int CodeCoverConditionCoverageHelper_C126;
    if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[310]++;
      // TODO(nicksantos): This branch indicates a compiler bug, not worthy of
      // halting the compilation but we should log this and analyze to track
      // down why it happens. This is not critical and will be resolved over
      // time as the type checker is extended.
      return unknownType;

    } else {
CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5.branches[311]++;
      return jsType;
    }
  }

  private JSType getNativeType(JSTypeNative typeId) {
    return registry.getNativeType(typeId);
  }
}

class CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5 ());
  }
    public static long[] statements = new long[508];
    public static long[] branches = new long[312];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[127];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TypeInference.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1,1,2,2,1,1,1,2,3,2,1,1,2,2,1,1,2,1,2,1,1,2,3,1,1,2,2,2,1,1,1,2,1,1,1,2,2,2,2,3,2,2,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,3,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,2,2,1,1,2,1,1,1,1,2,2,1,3,1,1,2,2,1,2,1,1,1,3,1,1,1,1};
    for (int i = 1; i <= 126; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$70w7936prg5rocf1wat2lwjpr4v5 () {
    super("com.google.javascript.jscomp.TypeInference.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 507; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 311; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 126; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TypeInference.java");
      for (int i = 1; i <= 507; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 311; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 126; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

