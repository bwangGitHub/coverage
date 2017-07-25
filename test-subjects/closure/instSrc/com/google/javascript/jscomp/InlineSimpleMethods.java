/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Inlines methods that take no arguments and have only a return statement
 * returning a property. Because it works on method names rather than type
 * inference, a method with multiple definitions will be inlined if each
 * definition is identical.
 *
 * <pre>
 * A.prototype.foo = function() { return this.b; }
 * B.prototype.foo = function() { return this.b; }
 * </pre>
 *
 * will inline foo, but
 *
 * <pre>
 * A.prototype.foo = function() { return this.b; }
 * B.prototype.foo = function() { return this.c; }
 * </pre>
 *
 * will not.
 *
 * Declarations are not removed because we do not find all possible
 * call sites. For examples, calls of the form foo["bar"] are not
 * detected.
 *
 */
class InlineSimpleMethods extends MethodCompilerPass {
  static {
    CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.ping();
  }


  private static final Logger logger =
      Logger.getLogger(InlineSimpleMethods.class.getName());
  static {
    CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[1]++;
  }

  InlineSimpleMethods(AbstractCompiler compiler) {
    super(compiler);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[2]++;
  }

  /**
   * For each method call, see if it is a candidate for inlining.
   * TODO(kushal): Cache the results of the checks
   */
  private class InlineTrivialAccessors extends InvocationsCallback {

    @Override
    void visit(NodeTraversal t, Node callNode, Node parent, String callName) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((externMethods.contains(callName)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nonMethodProperties.contains(callName)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[1]++;
        return;

      } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[2]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[4]++;

      Collection<Node> definitions = methodDefinitions.get(callName);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((definitions == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((definitions.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[3]++;
        return;

      } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[4]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[6]++;

      // Do check of arity, complexity, and consistency in what we think is
      // the order from least to most complex
      Node firstDefinition = definitions.iterator().next();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;

      // Check any multiple definitions
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((definitions.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((allDefinitionsEquivalent(definitions)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[5]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((argsMayHaveSideEffects(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[7]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[9]++;
          // Verify this is a trivial return
          Node returned = returnedExpression(firstDefinition);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((returned != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[9]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[11]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isPropertyTree(returned)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[11]++;
              logger.fine("Inlining property accessor: " + callName);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[12]++;
              inlinePropertyReturn(parent, callNode, returned);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[13]++;

            } else {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[12]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[14]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(returned, false)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(
                  callNode.getFirstChild(), compiler)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[13]++;
              logger.fine("Inlining constant accessor: " + callName);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[15]++;
              inlineConstReturn(parent, callNode, returned);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[16]++;

            } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[14]++;}
}

          } else {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[10]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[17]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((isEmptyMethod(firstDefinition)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(
                  callNode.getFirstChild(), compiler)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[15]++;
            logger.fine("Inlining empty method: " + callName);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[18]++;
            inlineEmptyMethod(parent, callNode);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[19]++;

          } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[16]++;}
}

        } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[8]++;}

      } else {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[6]++;
        logger.fine("Method '" + callName + "' has conflicting definitions.");
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[20]++;
      }
    }
  }

  @Override
  Callback getActingCallback() {
    return new InlineTrivialAccessors();
  }

  /**
   * Returns true if the provided node is a getprop for
   * which the left child is this or a valid property tree
   * and for which the right side is a string.
   */
  private static boolean isPropertyTree(Node expectedGetprop) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((expectedGetprop.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[17]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[18]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[22]++;

    Node leftChild = expectedGetprop.getFirstChild();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((leftChild.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isPropertyTree(leftChild)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[19]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[20]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[24]++;

    Node retVal = leftChild.getNext();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[25]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.getStringValue(retVal) == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[21]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[22]++;}
    return true;
  }

  /**
   * Finds the occurrence of "this" in the provided property tree and replaces
   * it with replacement
   */
  private static void replaceThis(Node expectedGetprop, Node replacement) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[26]++;
    Node leftChild = expectedGetprop.getFirstChild();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[27]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((leftChild.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[23]++;
      expectedGetprop.replaceChild(leftChild, replacement);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[28]++;

    } else {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[24]++;
      replaceThis(leftChild, replacement);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[29]++;
    }
  }

  /**
   * Return the node that represents the expression returned
   * by the method, given a FUNCTION node.
   */
  private static Node returnedExpression(Node fn) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[30]++;
    Node expectedBlock = getMethodBlock(fn);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[31]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((expectedBlock.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[25]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[26]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[32]++;

    Node expectedReturn = expectedBlock.getFirstChild();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[33]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((expectedReturn.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[27]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[28]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[34]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((expectedReturn.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[29]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[30]++;}

    return expectedReturn.getLastChild();
  }


  /**
   * Return whether the given FUNCTION node is an empty method definition.
   *
   * Must be private, or moved to NodeUtil.
   */
  private static boolean isEmptyMethod(Node fn) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[35]++;
    Node expectedBlock = getMethodBlock(fn);
    return expectedBlock == null ?
        false : NodeUtil.isEmptyBlock(expectedBlock);
  }

  /**
   * Return a BLOCK node if the given FUNCTION node is a valid method
   * definition, null otherwise.
   *
   * Must be private, or moved to NodeUtil.
   */
  private static Node getMethodBlock(Node fn) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[36]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((fn.getChildCount() != 3) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[31]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[32]++;}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[37]++;

    Node expectedBlock = fn.getLastChild();
    return  expectedBlock.isBlock() ?
        expectedBlock : null;
  }

  /**
   * Given a set of method definitions, verify they are the same.
   */
  private boolean allDefinitionsEquivalent(
      Collection<Node> definitions) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[38]++;
    List<Node> list = Lists.newArrayList();
    list.addAll(definitions);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[39]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[40]++;
    Node node0 = list.get(0);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[41]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[1]++;


int CodeCoverConditionCoverageHelper_C17;
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < list.size()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[1]--;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[2]--;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[3]++;
}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[42]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((compiler.areNodesEqualForInlining(list.get(i), node0)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[33]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[34]++;}
    }
    return true;
  }

  /**
   * Replace the provided method call with the tree specified in returnedValue
   *
   * Parse tree of a call is
   * name
   *   call
   *     getprop
   *       obj
   *       string
   */
  private void inlinePropertyReturn(Node parent, Node call,
      Node returnedValue) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[43]++;
    Node getProp = returnedValue.cloneTree();
    replaceThis(getProp, call.getFirstChild().removeFirstChild());
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[44]++;
    parent.replaceChild(call, getProp);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[45]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[46]++;
  }

  /**
   * Replace the provided object and its method call with the tree specified
   * in returnedValue. Should be called only if the object reference has
   * no side effects.
   */
  private void inlineConstReturn(Node parent, Node call,
      Node returnedValue) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[47]++;
    Node retValue = returnedValue.cloneTree();
    parent.replaceChild(call, retValue);
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[48]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[49]++;
  }

  /**
   * Remove the provided object and its method call.
   */
  private void inlineEmptyMethod(Node parent, Node call) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[50]++;
int CodeCoverConditionCoverageHelper_C19;
    // If the return value of the method call is read,
    // replace it with "void 0". Otherwise, remove the call entirely.
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprCall(parent)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[35]++;
      parent.getParent().replaceChild(parent, IR.empty());
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[51]++;

    } else {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[36]++;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[52]++;
      Node srcLocation = call;
      parent.replaceChild(call, NodeUtil.newUndefinedNode(srcLocation));
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[53]++;
    }
    compiler.reportCodeChange();
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[54]++;
  }

  /**
   * Check whether the given method call's arguments have side effects.
   * @param call The call node of a method invocation.
   */
  private boolean argsMayHaveSideEffects(Node call) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[55]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[4]++;


int CodeCoverConditionCoverageHelper_C20;
    for (Node currentChild = call.getFirstChild().getNext();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((currentChild != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false);
         currentChild = currentChild.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[4]--;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[5]--;
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.loops[6]++;
}
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[56]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(currentChild, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[37]++;
        return true;

      } else {
  CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.branches[38]++;}
    }

    return false;
  }

  /**
   * A do-nothing signature store.
   */
  static final MethodCompilerPass.SignatureStore DUMMY_SIGNATURE_STORE =
      new MethodCompilerPass.SignatureStore() {
        @Override
        public void addSignature(
            String functionName, Node functionNode, String sourceFile) {
        }

        @Override
        public void removeSignature(String functionName) {
        }

        @Override
        public void reset() {
        }
      };
  static {
    CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd.statements[57]++;
  }

  @Override
  SignatureStore getSignatureStore() {
    return DUMMY_SIGNATURE_STORE;
  }
}

class CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InlineSimpleMethods.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,2,1,1,1,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 21; i++) {
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

  public CodeCoverCoverageCounter$gx9w01iyx85pnacrgd9rj2z16k0bpehjvmstd () {
    super("com.google.javascript.jscomp.InlineSimpleMethods.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InlineSimpleMethods.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
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

