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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.Collection;
import java.util.List;

/**
 * Rewrites prototyped methods calls as static calls that take "this"
 * as their first argument.  This transformation simplifies the call
 * graph so smart name removal, cross module code motion and other
 * passes can do more.
 *
 * <p>This pass should only be used in production code if property
 * and variable renaming are turned on.  Resulting code may also
 * benefit from --collapse_anonymous_functions and
 * --collapse_variable_declarations
 *
 * <p>This pass only rewrites functions that are part of an objects
 * prototype.  Functions that access the "arguments" variable
 * arguments object are not eligible for this optimization.
 *
 * <p>For example:
 * <pre>
 *     A.prototype.accumulate = function(value) {
 *       this.total += value; return this.total
 *     }
 *     var total = a.accumulate(2)
 * </pre>
 *
 * <p>will be rewritten as:
 *
 * <pre>
 *     var accumulate = function(self, value) {
 *       self.total += value; return self.total
 *     }
 *     var total = accumulate(a, 2)
 * </pre>
 *
 */
class DevirtualizePrototypeMethods
    implements OptimizeCalls.CallGraphCompilerPass,
               SpecializationAwareCompilerPass {
  static {
    CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.ping();
  }

  private final AbstractCompiler compiler;
  private SpecializeModule.SpecializationState specializationState;

  DevirtualizePrototypeMethods(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[1]++;
  }

  @Override
  public void enableSpecialization(SpecializeModule.SpecializationState state) {
    this.specializationState = state;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[3]++;
    SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
    defFinder.process(externs, root);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[4]++;
    process(externs, root, defFinder);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[5]++;
  }

  @Override
  public void process(
      Node externs, Node root, SimpleDefinitionFinder definitions) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[1]++;


    for (DefinitionSite defSite : definitions.getDefinitionSites()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[1]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[2]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[3]++;
}
      rewriteDefinitionIfEligible(defSite, definitions);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[7]++;
    }
  }

  /**
   * Determines if the name node acts as the function name in a call expression.
   */
  private static boolean isCall(UseSite site) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[8]++;
    Node node = site.node;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[9]++;
    Node parent = node.getParent();
    return (parent.getFirstChild() == node) && parent.isCall();
  }

  /**
   * Determines if the current node is a function prototype definition.
   */
  private static boolean isPrototypeMethodDefinition(Node node) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[10]++;
    Node parent = node.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[2]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[12]++;
    Node gramp = parent.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((gramp == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[4]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[5]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() != node) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[7]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[8]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(gramp)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[9]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[10]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[17]++;

      Node functionNode = parent.getLastChild();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((functionNode == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((functionNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[11]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[12]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[19]++;

      Node nameNode = node.getFirstChild();
      return nameNode.isGetProp() &&
          nameNode.getLastChild().getString().equals("prototype");

    } else {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[6]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[20]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[13]++;
      Preconditions.checkState(parent.isObjectLit());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[21]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((gramp.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[15]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[16]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[23]++;
int CodeCoverConditionCoverageHelper_C9;

      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((gramp.getLastChild() != parent) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[17]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[18]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[24]++;

      Node greatGramp = gramp.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((greatGramp == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((greatGramp.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[19]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[20]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[26]++;

      Node functionNode = node.getFirstChild();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[27]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((functionNode == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((functionNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[21]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[22]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[28]++;

      Node target = gramp.getFirstChild();
      return target.isGetProp() &&
          target.getLastChild().getString().equals("prototype");

    } else {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[14]++;
      return false;
    }
}
  }

  private String getMethodName(Node node) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[29]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[23]++;
      return node.getLastChild().getString();

    } else {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[24]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[30]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((node.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[25]++;
      return node.getString();

    } else {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[26]++;
      throw new IllegalStateException("unexpected");
    }
}
  }

  /**
   * @returns The new name for a rewritten method.
   */
  private String getRewrittenMethodName(String originalMethodName) {
    return "JSCompiler_StaticMethods_" + originalMethodName;
  }

  /**
   * Rewrites method definition and call sites if the method is
   * defined in the global scope exactly once.
   *
   * Definition and use site information is provided by the
   * {@link SimpleDefinitionFinder} passed in as an argument.
   *
   * @param defSite definition site to process.
   * @param defFinder structure that hold Node -> Definition and
   * Definition -> [UseSite] maps.
   */
  private void rewriteDefinitionIfEligible(DefinitionSite defSite,
                                           SimpleDefinitionFinder defFinder) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[31]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((defSite.inExterns) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((defSite.inGlobalScope) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((isEligibleDefinition(defFinder, defSite)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[27]++;
      return;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[28]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[32]++;

    Node node = defSite.node;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[33]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isPrototypeMethodDefinition(node)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[29]++;
      return;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[30]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;

    for (Node ancestor = node.getParent();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ancestor != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false);
         ancestor = ancestor.getParent()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[4]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[5]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[6]++;
}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[35]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.isControlStructure(ancestor)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[31]++;
        return;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[32]++;}
    }
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[36]++;

    // TODO(user) The code only works if there is a single definition
    // associated with a property name.  Once this pass starts using
    // the NameReferenceGraph to disambiguate call sites, it will be
    // necessary to consider type information when generating static
    // method names and/or append unique ids to duplicate static
    // method names.
    // Whatever scheme we use should not break stable renaming.
    String newMethodName = getRewrittenMethodName(
        getMethodName(node));
    rewriteDefinition(node, newMethodName);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[37]++;
    rewriteCallSites(defFinder, defSite.definition, newMethodName);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[38]++;
  }

  /**
   * Determines if a method definition is eligible for rewrite as a
   * global function.  In order to be eligible for rewrite, the
   * definition must:
   *
   * - Refer to a function that takes a fixed number of arguments.
   * - Function must not be exported.
   * - Function must be used at least once.
   * - Property is never accessed outside a function call context.
   * - The definition under consideration must be the only possible
   *   choice at each call site.
   * - Definition must happen in a module loaded before the first use.
   */
  private boolean isEligibleDefinition(SimpleDefinitionFinder defFinder,
                                       DefinitionSite definitionSite) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[39]++;

    Definition definition = definitionSite.definition;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[40]++;
    JSModule definitionModule = definitionSite.module;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[41]++;

    // Only functions may be rewritten.
    // Functions that access "arguments" are not eligible since
    // rewrite changes the structure of this object.
    Node rValue = definition.getRValue();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[42]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((rValue == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarArgsFunction(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[33]++;
      return false;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[34]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[43]++;

    // Exporting a method prevents rewrite.
    Node lValue = definition.getLValue();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[44]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((lValue == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((lValue.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[35]++;
      return false;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[36]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[45]++;
    CodingConvention codingConvention = compiler.getCodingConvention();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[46]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((codingConvention.isExported(lValue.getLastChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[37]++;
      return false;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[38]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[47]++;

    Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[48]++;
int CodeCoverConditionCoverageHelper_C21;

    // Rewriting unused methods is not sound.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((useSites.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[39]++;
      return false;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[40]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[49]++;

    JSModuleGraph moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[7]++;



    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[7]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[8]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[9]++;
}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[51]++;
int CodeCoverConditionCoverageHelper_C22;
      // Accessing the property directly prevents rewrite.
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isCall(site)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[41]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[42]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[52]++;

      Node nameNode = site.node;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[53]++;
int CodeCoverConditionCoverageHelper_C23;

      // Don't rewrite methods called in functions that can't be specialized
      // if we are specializing
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((specializationState.canFixupSpecializedFunctionContainingNode(
              nameNode)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[43]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[44]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[54]++;

      // Multiple definitions prevent rewrite.
      Collection<Definition> singleSiteDefinitions =
          defFinder.getDefinitionsReferencedAt(nameNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[55]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((singleSiteDefinitions.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[45]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[46]++;}
      Preconditions.checkState(!singleSiteDefinitions.isEmpty());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[56]++;
      Preconditions.checkState(singleSiteDefinitions.contains(definition));
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[57]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[58]++;

      // Accessing the property in a module loaded before the
      // definition module prevents rewrite; accessing a variable
      // before definition results in a parse error.
      JSModule callModule = site.module;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[59]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((definitionModule != callModule) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((callModule == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(callModule, definitionModule)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[47]++;
        return false;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[48]++;}
    }

    return true;
  }

  /**
   * Rewrites object method call sites as calls to global functions
   * that take "this" as their first argument.
   *
   * Before:
   *   o.foo(a, b, c)
   *
   * After:
   *   foo(o, a, b, c)
   */
  private void rewriteCallSites(SimpleDefinitionFinder defFinder,
                                Definition definition,
                                String newMethodName) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[60]++;
    Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[61]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[10]++;


    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[10]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[11]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[12]++;
}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[62]++;
      Node node = site.node;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[63]++;
      Node parent = node.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[64]++;

      Node objectNode = node.getFirstChild();
      node.removeChild(objectNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[65]++;
      parent.replaceChild(node, objectNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[66]++;
      parent.addChildToFront(IR.name(newMethodName).srcref(node));
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[67]++;
      Preconditions.checkState(parent.isCall());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[68]++;
      parent.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[69]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[70]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[71]++;
int CodeCoverConditionCoverageHelper_C26;

      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[49]++;
        specializationState.reportSpecializedFunctionContainingNode(parent);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[72]++;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[50]++;}
    }
  }

  /**
   * Rewrites method definitions as global functions that take "this"
   * as their first argument.
   *
   * Before:
   *   a.prototype.b = function(a, b, c) {...}
   *
   * After:
   *   var b = function(self, a, b, c) {...}
   */
  private void rewriteDefinition(Node node, String newMethodName) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[73]++;
    boolean isObjLitDefKey = node.isStringKey();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[74]++;

    Node parent = node.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[75]++;

    Node refNode = isObjLitDefKey ? node : parent.getFirstChild();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[76]++;
    Node newNameNode = IR.name(newMethodName).copyInformationFrom(refNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[77]++;
    Node newVarNode = IR.var(newNameNode).copyInformationFrom(refNode);

    Node functionNode;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[78]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isObjLitDefKey) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[51]++;
      Preconditions.checkState(parent.isAssign());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[79]++;
      functionNode = parent.getLastChild();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[80]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[81]++;
      Node expr = parent.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[82]++;
      Node block = expr.getParent();
      parent.removeChild(functionNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[83]++;
      newNameNode.addChildToFront(functionNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[84]++;
      block.replaceChild(expr, newVarNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[85]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[86]++;
int CodeCoverConditionCoverageHelper_C28;

      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[53]++;
        specializationState.reportRemovedFunction(functionNode, block);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[87]++;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[54]++;}

    } else {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[52]++;
      Preconditions.checkState(parent.isObjectLit());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[88]++;
      functionNode = node.getFirstChild();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[89]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[90]++;
      Node assign = parent.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[91]++;
      Node expr = assign.getParent();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[92]++;
      Node block = expr.getParent();

      node.removeChild(functionNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[93]++;
      parent.removeChild(node);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[94]++;
      newNameNode.addChildToFront(functionNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[95]++;
      block.addChildAfter(newVarNode, expr);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[96]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[97]++;
int CodeCoverConditionCoverageHelper_C29;

      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[55]++;
        specializationState.reportRemovedFunction(functionNode, block);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[98]++;

      } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[56]++;}
    }
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[99]++;

    // add extra argument
    String self = newMethodName + "$self";
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[100]++;
    Node argList = functionNode.getFirstChild().getNext();
    argList.addChildToFront(IR.name(self)
        .copyInformationFrom(functionNode));
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[101]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[102]++;

    // rewrite body
    Node body = functionNode.getLastChild();
    replaceReferencesToThis(body, self);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[103]++;

    // fix type
    fixFunctionType(functionNode);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[104]++;

    compiler.reportCodeChange();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[105]++;
  }

  /**
   * Creates a new JSType based on the original function type by
   * adding the original this pointer type to the beginning of the
   * argument type list and replacing the this pointer type with
   * NO_TYPE.
   */
  private void fixFunctionType(Node functionNode) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[106]++;
    FunctionType type = JSType.toMaybeFunctionType(functionNode.getJSType());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[107]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[57]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[108]++;
      JSTypeRegistry typeRegistry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[109]++;

      List<JSType> parameterTypes = Lists.newArrayList();
      parameterTypes.add(type.getTypeOfThis());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[110]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[111]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[13]++;



      for (Node param : type.getParameters()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[13]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[14]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[15]++;
}
        parameterTypes.add(param.getJSType());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[112]++;
      }
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[113]++;

      ObjectType thisType =
          typeRegistry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[114]++;
      JSType returnType = type.getReturnType();
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[115]++;

      JSType newType = typeRegistry.createFunctionType(
          thisType, returnType, parameterTypes);
      functionNode.setJSType(newType);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[116]++;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[58]++;}
  }

  /**
   * Replaces references to "this" with references to name.  Do not
   * traverse function boundaries.
   */
  private void replaceReferencesToThis(Node node, String name) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[117]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[59]++;
      return;

    } else {
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[60]++;}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[118]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[16]++;



    for (Node child : node.children()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[16]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[17]--;
  CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.loops[18]++;
}
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[119]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((child.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[61]++;
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[120]++;
        Node newName = IR.name(name);
        newName.setJSType(child.getJSType());
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[121]++;
        node.replaceChild(child, newName);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[122]++;

      } else {
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.branches[62]++;
        replaceReferencesToThis(child, name);
CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d.statements[123]++;
      }
    }
  }
}

class CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d ());
  }
    public static long[] statements = new long[124];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[33];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DevirtualizePrototypeMethods.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,2,2,1,1,3,1,1,1,3,2,1,1,1,2,1,3,1,1,1,1,1,1,1};
    for (int i = 1; i <= 32; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$c4ek7j4i5qn30oxsyqgp0i4m60sa2i7ve7zav6rcefomghjwh9d () {
    super("com.google.javascript.jscomp.DevirtualizePrototypeMethods.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 123; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DevirtualizePrototypeMethods.java");
      for (int i = 1; i <= 123; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 32; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

