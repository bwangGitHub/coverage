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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Set the NoSideEffects property for function and constructor calls
 * that refer to functions that are known to have no side effects.
 * Current implementation relies on @nosideeffects annotations at
 * function definition sites; eventually we should traverse function
 * bodies to determine if they have side effects.
 *
 */
class MarkNoSideEffectCalls implements CompilerPass {
  static {
    CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.ping();
  }

  static final DiagnosticType INVALID_NO_SIDE_EFFECT_ANNOTATION =
      DiagnosticType.error(
          "JSC_INVALID_NO_SIDE_EFFECT_ANNOTATION",
          "@nosideeffects may only appear in externs files.");
  static {
    CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[1]++;
  }

  private final AbstractCompiler compiler;

  // Left hand side expression associated with a function node that
  // has a @nosideeffects annotation.
  private final Set<Node> noSideEffectFunctionNames;

  MarkNoSideEffectCalls(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[2]++;
    this.noSideEffectFunctionNames = Sets.newHashSet();
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[3]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[4]++;
    SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
    defFinder.process(externs, root);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[5]++;

    // Gather the list of function nodes that have @nosideeffects annotations.
    // For use by SetNoSideEffectCallProperty.
    NodeTraversal.traverse(
        compiler, externs, new GatherNoSideEffectFunctions(true));
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[6]++;
    NodeTraversal.traverse(
        compiler, root, new GatherNoSideEffectFunctions(false));
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[7]++;

    NodeTraversal.traverse(compiler, root,
                           new SetNoSideEffectCallProperty(defFinder));
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[8]++;
  }

  /**
   * Determines if the type of the value of the RHS expression can
   * be a function node.
   */
  private static boolean definitionTypeContainsFunctionType(Definition def) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[9]++;
    Node rhs = def.getRValue();
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((rhs == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[1]++;
      return true;

    } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[2]++;}
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[11]++;

    switch (rhs.getType()) {
      case Token.ASSIGN:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[3]++;
      case Token.AND:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[4]++;
      case Token.CALL:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[5]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[6]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[7]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[8]++;
      case Token.HOOK:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[9]++;
      case Token.NAME:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[10]++;
      case Token.NEW:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[11]++;
      case Token.OR:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[12]++;
        return true;
      default:
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[13]++;
        return false;
    }
  }

  /**
   * Get the value of the @nosideeffects annotation stored in the
   * doc info.
   */
  private static boolean hasNoSideEffectsAnnotation(Node node) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[12]++;
    JSDocInfo docInfo = node.getJSDocInfo();
    return docInfo != null && docInfo.isNoSideEffects();
  }

  /**
   * Gather function nodes that have @nosideeffects annotations.
   */
  private class GatherNoSideEffectFunctions extends AbstractPostOrderCallback {
    private final boolean inExterns;

    GatherNoSideEffectFunctions(boolean inExterns) {
      this.inExterns = inExterns;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[13]++;
    }

    @Override
    public void visit(NodeTraversal traversal, Node node, Node parent) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((hasNoSideEffectsAnnotation(node)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[14]++;
        traversal.report(node, INVALID_NO_SIDE_EFFECT_ANNOTATION);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[15]++;

      } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[15]++;}
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[16]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((hasNoSideEffectsAnnotation(node)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[18]++;
          noSideEffectFunctionNames.add(node);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[18]++;

        } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[19]++;}

      } else {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[17]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[19]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[20]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[20]++;

        // The annotation may attached to the function node, the
        // variable declaration or assignment expression.
        boolean hasAnnotation = hasNoSideEffectsAnnotation(node);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[21]++;
        List<Node> nameNodes = Lists.newArrayList();
        nameNodes.add(node.getFirstChild());
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[22]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[22]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[24]++;
          Node gramp = parent.getParent();
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((gramp.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((gramp.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((hasNoSideEffectsAnnotation(gramp)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[24]++;
            hasAnnotation = true;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[26]++;

          } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[25]++;}

          nameNodes.add(parent);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[27]++;

        } else {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[23]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[28]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[26]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((hasNoSideEffectsAnnotation(parent)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[28]++;
            hasAnnotation = true;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[30]++;

          } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[29]++;}

          nameNodes.add(parent.getFirstChild());
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[31]++;

        } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[27]++;}
}
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((hasAnnotation) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[30]++;
          noSideEffectFunctionNames.addAll(nameNodes);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[33]++;

        } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[31]++;}

      } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[21]++;}
}
    }
  }

  /**
   * Set the no side effects property for CALL and NEW nodes that
   * refer to function names that are known to have no side effects.
   */
  private class SetNoSideEffectCallProperty extends AbstractPostOrderCallback {
    private final SimpleDefinitionFinder defFinder;

    SetNoSideEffectCallProperty(SimpleDefinitionFinder defFinder) {
      this.defFinder = defFinder;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[34]++;
    }

    @Override
    public void visit(NodeTraversal traversal, Node node, Node parent) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[35]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((node.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((node.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[32]++;
        return;

      } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[33]++;}
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[36]++;

      Collection<Definition> definitions =
          defFinder.getDefinitionsReferencedAt(node.getFirstChild());
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((definitions == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[34]++;
        return;

      } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[35]++;}
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[38]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.loops[1]++;



      for (Definition def : definitions) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.loops[1]--;
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.loops[2]--;
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.loops[3]++;
}
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[39]++;
        Node lValue = def.getLValue();
        Preconditions.checkNotNull(lValue);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[40]++;
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[41]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((noSideEffectFunctionNames.contains(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((definitionTypeContainsFunctionType(def)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[36]++;
          return;

        } else {
  CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.branches[37]++;}
      }

      node.setSideEffectFlags(Node.NO_SIDE_EFFECTS);
CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp.statements[42]++;
    }
  }
}

class CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[38];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MarkNoSideEffectCalls.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,2,1,1,3,1,1,1,2,1,2};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$p1urgrnsnhh05upiwgi8b4bq613vu66kp02qooyp () {
    super("com.google.javascript.jscomp.MarkNoSideEffectCalls.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 37; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MarkNoSideEffectCalls.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 37; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

