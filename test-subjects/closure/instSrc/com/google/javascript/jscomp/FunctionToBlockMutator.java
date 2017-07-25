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

import static com.google.javascript.jscomp.FunctionArgumentInjector.THIS_MARKER;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.MakeDeclaredNamesUnique.InlineRenamer;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A class to transform the body of a function into a generic block suitable
 * for inlining.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class FunctionToBlockMutator {
  static {
    CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.ping();
  }


  private AbstractCompiler compiler;
  private Supplier<String> safeNameIdSupplier;


  FunctionToBlockMutator(
      AbstractCompiler compiler, Supplier<String> safeNameIdSupplier) {
    this.compiler = compiler;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[1]++;
    this.safeNameIdSupplier = safeNameIdSupplier;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[2]++;
  }

  /**
   * @param fnName The name to use when preparing human readable names.
   * @param fnNode The function to prepare.
   * @param callNode The call node that will be replaced.
   * @param resultName Function results should be assigned to this name.
   * @param needsDefaultResult Whether the result value must be set.
   * @param isCallInLoop Whether the function body must be prepared to be
   *   injected into the body of a loop.
   * @return A clone of the function body mutated to be suitable for injection
   *   as a statement into another code block.
   */
  Node mutate(String fnName, Node fnNode, Node callNode,
      String resultName, boolean needsDefaultResult, boolean isCallInLoop) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[3]++;
    Node newFnNode = fnNode.cloneTree();
    // Now that parameter names have been replaced, make sure all the local
    // names are unique, to allow functions to be inlined multiple times
    // without causing conflicts.
    makeLocalNamesUnique(newFnNode, isCallInLoop);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[4]++;

    // Function declarations must be rewritten as function expressions as
    // they will be within a block and normalization prevents function
    // declarations within block as browser implementations vary.
    rewriteFunctionDeclarations(newFnNode.getLastChild());
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[5]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[6]++;

    // TODO(johnlenz): Mark NAME nodes constant for parameters that are not
    // modified.
    Set<String> namesToAlias =
        FunctionArgumentInjector.findModifiedParameters(newFnNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[7]++;
    LinkedHashMap<String, Node> args =
        FunctionArgumentInjector.getFunctionCallParameterMap(
            newFnNode, callNode, this.safeNameIdSupplier);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[8]++;
    boolean hasArgs = !args.isEmpty();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((hasArgs) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[1]++;
      FunctionArgumentInjector.maybeAddTempsForCallArguments(
          newFnNode, args, namesToAlias, compiler.getCodingConvention());
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[10]++;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[2]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[11]++;

    Node newBlock = NodeUtil.getFunctionBody(newFnNode);
    // Make the newBlock insertable .
    newBlock.detachFromParent();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[12]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((hasArgs) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[3]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[14]++;
      Node inlineResult = aliasAndInlineArguments(newBlock,
          args, namesToAlias);
      Preconditions.checkState(newBlock == inlineResult);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[15]++;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[4]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;

    //
    // For calls inlined into loops, VAR declarations are not reinitialized to
    // undefined as they would have been if the function were called, so ensure
    // that they are properly initialized.
    //
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isCallInLoop) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[5]++;
      fixUnitializedVarDeclarations(newBlock);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[17]++;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[6]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[18]++;

    String labelName = getLabelNameForFunction(fnName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[19]++;
    Node injectableBlock = replaceReturns(
        newBlock, resultName, labelName, needsDefaultResult);
    Preconditions.checkState(injectableBlock != null);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[20]++;

    return injectableBlock;
  }


  /**
   * @param n The node to inspect
   */
  private void rewriteFunctionDeclarations(Node n) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[7]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[9]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[23]++;
        // Rewrite: function f() {} ==> var f = function() {}
        Node fnNameNode = n.getFirstChild();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[24]++;

        Node name = IR.name(fnNameNode.getString()).srcref(fnNameNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[25]++;
        Node var = IR.var(name).srcref(n);

        fnNameNode.setString("");
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[26]++;
        // Add the VAR, remove the FUNCTION
        n.getParent().replaceChild(n, var);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[27]++;
        // readd the function as a function expression
        name.addChildToFront(n);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[28]++;

      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[10]++;}
      return;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[8]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;

    for (Node c = n.getFirstChild(), next;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); c = next) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[1]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[2]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[3]++;
}
      next = c.getNext();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[30]++; // We may rewrite "c"
      rewriteFunctionDeclarations(c);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[31]++;
    }
  }

  /**
   *  For all VAR node with uninitialized declarations, set
   *  the values to be "undefined".
   */
  private void fixUnitializedVarDeclarations(Node n) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
    // Inner loop structure must already have logic to initialize its
    // variables.  In particular FOR-IN structures must not be modified.
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.isLoopStructure(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[11]++;
      return;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[12]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;

    // For all VARs
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[13]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[34]++;
      Node name = n.getFirstChild();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
      // It isn't initialized.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((name.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[15]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[36]++;
        Node srcLocation = name;
        name.addChildToBack(NodeUtil.newUndefinedNode(srcLocation));
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[37]++;

      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[16]++;}
      return;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[14]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[38]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;

    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[4]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[5]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[6]++;
}
      fixUnitializedVarDeclarations(c);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[39]++;
    }
  }


  /**
   * Fix-up all local names to be unique for this subtree.
   * @param fnNode A mutable instance of the function to be inlined.
   */
  private void makeLocalNamesUnique(Node fnNode, boolean isCallInLoop) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[40]++;
    Supplier<String> idSupplier = compiler.getUniqueNameIdSupplier();
    // Make variable names unique to this instance.
    NodeTraversal.traverse(
        compiler, fnNode, new MakeDeclaredNamesUnique(
            new InlineRenamer(
                compiler.getCodingConvention(),
                idSupplier,
                "inline_",
                isCallInLoop)));
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[41]++;
    // Make label names unique to this instance.
    new RenameLabels(compiler, new LabelNameSupplier(idSupplier), false)
        .process(null, fnNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[42]++;
  }

  static class LabelNameSupplier implements Supplier<String> {
    final Supplier<String> idSupplier;

    LabelNameSupplier(Supplier<String> idSupplier) {
      this.idSupplier = idSupplier;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[43]++;
    }

    @Override
    public String get() {
        return "JSCompiler_inline_label_" + idSupplier.get();
    }
  }

  /**
   * Create a unique label name.
   */
  private String getLabelNameForFunction(String fnName){
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[44]++;
    String name = (fnName == null || fnName.isEmpty()) ? "anon" : fnName;
    return "JSCompiler_inline_label_" + name + "_" + safeNameIdSupplier.get();
  }

  /**
   * Create a unique "this" name.
   */
  private String getUniqueThisName() {
    return "JSCompiler_inline_this_" + safeNameIdSupplier.get();
  }

  /**
   * Inlines the arguments within the node tree using the given argument map,
   * replaces "unsafe" names with local aliases.
   *
   * The aliases for unsafe require new VAR declarations, so this function
   * can not be used in for direct CALL node replacement as VAR nodes can not be
   * created there.
   *
   * @return The node or its replacement.
   */
  private Node aliasAndInlineArguments(
      Node fnTemplateRoot, LinkedHashMap<String, Node> argMap,
      Set<String> namesToAlias) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((namesToAlias == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((namesToAlias.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[17]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[46]++;
      // There are no names to alias, just inline the arguments directly.
      Node result = FunctionArgumentInjector.inject(
          compiler, fnTemplateRoot, null, argMap);
      Preconditions.checkState(result == fnTemplateRoot);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[47]++;
      return result;

    } else {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[18]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[48]++;
      // Create local alias of names that can not be safely
      // used directly.

      // An arg map that will be updated to contain the
      // safe aliases.
      Map<String, Node> newArgMap = Maps.newHashMap(argMap);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[49]++;

      // Declare the alias in the same order as they
      // are declared.
      List<Node> newVars = Lists.newLinkedList();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[7]++;


      // NOTE: argMap is a linked map so we get the parameters in the
      // order that they were declared.
      for (Entry<String, Node> entry : argMap.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[7]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[8]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[9]++;
}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[51]++;
        String name = entry.getKey();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[52]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((namesToAlias.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[19]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[53]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((name.equals(THIS_MARKER)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[21]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[54]++;
            boolean referencesThis = NodeUtil.referencesThis(fnTemplateRoot);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[55]++;
            // Update "this", this is only necessary if "this" is referenced
            // and the value of "this" is not Token.THIS, or the value of "this"
            // has side effects.

            Node value = entry.getValue();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((value.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((referencesThis) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(value, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[23]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[57]++;
              String newName = getUniqueThisName();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[58]++;
              Node newValue = entry.getValue().cloneTree();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[59]++;
              Node newNode = NodeUtil.newVarNode(newName, newValue)
                  .copyInformationFromForTree(newValue);
              newVars.add(0, newNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[60]++;
              // Remove the parameter from the list to replace.
              newArgMap.put(THIS_MARKER,
                  IR.name(newName)
                      .srcrefTree(newValue));
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[61]++;

            } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[24]++;}

          } else {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[22]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[62]++;
            Node newValue = entry.getValue().cloneTree();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[63]++;
            Node newNode = NodeUtil.newVarNode(name, newValue)
                .copyInformationFromForTree(newValue);
            newVars.add(0, newNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[64]++;
            // Remove the parameter from the list to replace.
            newArgMap.remove(name);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[65]++;
          }

        } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[20]++;}
      }
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[66]++;

      // Inline the arguments.
      Node result = FunctionArgumentInjector.inject(
          compiler, fnTemplateRoot, null, newArgMap);
      Preconditions.checkState(result == fnTemplateRoot);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[67]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[68]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[10]++;



      // Now that the names have been replaced, add the new aliases for
      // the old names.
      for (Node n : newVars) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[10]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[11]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[12]++;
}
        fnTemplateRoot.addChildToFront(n);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[69]++;
      }

      return result;
    }
  }

  /**
   *  Convert returns to assignments and breaks, as needed.
   *  For example, with a labelName of 'foo':
   *    {
   *      return a;
   *    }
   *  becomes:
   *    foo: {
   *      a;
   *      break foo;
   *    }
   *  or
   *    foo: {
   *      resultName = a;
   *      break foo;
   *    }
   *
   * @param resultMustBeSet Whether the result must always be set to a value.
   * @return The node containing the transformed block, this may be different
   *     than the passed in node 'block'.
   */
  private static Node replaceReturns(
      Node block, String resultName, String labelName,
      boolean resultMustBeSet) {
    Preconditions.checkNotNull(block);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[70]++;
    Preconditions.checkNotNull(labelName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[71]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[72]++;

    Node root = block;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[73]++;

    boolean hasReturnAtExit = false;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[74]++;
    int returnCount = NodeUtil.getNodeTypeReferenceCount(
        block, Token.RETURN, new NodeUtil.MatchShallowStatement());
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[75]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((returnCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[25]++;
      hasReturnAtExit = hasReturnAtExit(block);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[76]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
      // TODO(johnlenz): Simpler not to special case this,
      // and let it be optimized later.
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((hasReturnAtExit) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[27]++;
        convertLastReturnToStatement(block, resultName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[78]++;
        returnCount--;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[79]++;

      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[28]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((returnCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[29]++;
        // A label and breaks are needed.

        // Add the breaks
        replaceReturnWithBreak(block, null, resultName, labelName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[81]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[82]++;

        // Add label
        Node name = IR.labelName(labelName).srcref(block);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[83]++;
        Node label = IR.label(name, block).srcref(block);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[84]++;

        Node newRoot = IR.block().srcref(block);
        newRoot.addChildrenToBack(label);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[85]++;


        // The label is now the root.
        root = newRoot;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[86]++;

      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[30]++;}

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[26]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[87]++;
int CodeCoverConditionCoverageHelper_C18;

    // If there wasn't an return at the end of the function block, and we need
    // a result, add one to the block.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((resultMustBeSet) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((hasReturnAtExit) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((resultName != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[31]++;
      addDummyAssignment(block, resultName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[88]++;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[32]++;}

    return root;
  }

  /**********************************************************************
   *  Functions following here are general node transformation functions
   **********************************************************************/

  /**
   * Example:
   *   a = (void) 0;
   */
  private static void addDummyAssignment(Node node, String resultName) {
    Preconditions.checkArgument(node.isBlock());
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[89]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[90]++;

    // A result is needed create a dummy value.
    Node srcLocation = node;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[91]++;
    Node retVal = NodeUtil.newUndefinedNode(srcLocation);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[92]++;
    Node resultNode = createAssignStatementNode(resultName, retVal);
    resultNode.copyInformationFromForTree(node);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[93]++;

    node.addChildrenToBack(resultNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[94]++;
  }

  /**
   * Replace the 'return' statement with its child expression.
   *   "return foo()" becomes "foo()" or "resultName = foo()"
   *   "return" is removed or becomes "resultName = void 0".
   *
   * @param block
   * @param resultName
   */
  private static void convertLastReturnToStatement(
      Node block, String resultName) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[95]++;
    Node ret = block.getLastChild();
    Preconditions.checkArgument(ret.isReturn());
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[96]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[97]++;
    Node resultNode = getReplacementReturnStatement(ret, resultName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[98]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((resultNode == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[33]++;
      block.removeChild(ret);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[99]++;

    } else {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[34]++;
      resultNode.copyInformationFromForTree(ret);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[100]++;
      block.replaceChild(ret, resultNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[101]++;
    }
  }

  /**
   * Create a valid statement Node containing an assignment to name of the
   * given expression.
   */
  private static Node createAssignStatementNode(String name, Node expression) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[102]++;
    // Create 'name = result-expression;' statement.
    // EXPR (ASSIGN (NAME, EXPRESSION))
    Node nameNode = IR.name(name);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[103]++;
    Node assign = IR.assign(nameNode, expression);
    return NodeUtil.newExpr(assign);
  }

  /**
   * Replace the 'return' statement with its child expression.
   * If the result is needed (resultName != null):
   *   "return foo()" becomes "resultName = foo()"
   *   "return" becomes "resultName = void 0".
   * Otherwise:
   *   "return foo()" becomes "foo()"
   *   "return", null is returned.
   */
  private static Node getReplacementReturnStatement(
      Node node, String resultName) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[104]++;
    Node resultNode = null;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[105]++;

    Node retVal = null;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[106]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((node.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[35]++;
      // Clone the child as the child hasn't been removed
      // from the node yet.
      retVal = node.getFirstChild().cloneTree();
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[107]++;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[36]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[108]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((resultName == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[37]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[109]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((retVal != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[39]++;
        resultNode = NodeUtil.newExpr(retVal);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[110]++;
 // maybe null.
      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[40]++;}

    } else {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[38]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[111]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((retVal == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[41]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[112]++;
        // A result is needed create a dummy value.
        Node srcLocation = node;
        retVal = NodeUtil.newUndefinedNode(srcLocation);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[113]++;

      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[42]++;}
      // Create a "resultName = retVal;" statement.
      resultNode = createAssignStatementNode(resultName, retVal);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[114]++;
    }

    return resultNode;
  }

  /**
   * @return Whether the given block end with an return statement.
   */
  private static boolean hasReturnAtExit(Node block) {
    // Only inline functions that return something (empty returns
    // will be handled by ConstFolding+EmptyFunctionRemoval)
    return (block.getLastChild().isReturn());
  }

  /**
   * Replace the 'return' statement with its child expression.
   *   "return foo()" becomes "{foo(); break;}" or
   *      "{resultName = foo(); break;}"
   *   "return" becomes {break;} or "{resultName = void 0;break;}".
   */
  private static Node replaceReturnWithBreak(Node current, Node parent,
      String resultName, String labelName) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[115]++;
int CodeCoverConditionCoverageHelper_C24;

    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((current.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((current.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[43]++;
      // Don't recurse into functions definitions, and expressions can't
      // contain RETURN nodes.
      return current;

    } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[44]++;}
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[116]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((current.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[45]++;
      Preconditions.checkState(NodeUtil.isStatementBlock(parent));
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[117]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[118]++;

      Node resultNode = getReplacementReturnStatement(current, resultName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[119]++;
      Node breakNode = IR.breakNode(IR.labelName(labelName));

      // Replace the node in parent, and reset current to the first new child.
      breakNode.copyInformationFromForTree(current);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[120]++;
      parent.replaceChild(current, breakNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[121]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[122]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((resultNode != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[47]++;
        resultNode.copyInformationFromForTree(current);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[123]++;
        parent.addChildBefore(resultNode, breakNode);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[124]++;

      } else {
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[48]++;}
      current = breakNode;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[125]++;

    } else {
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.branches[46]++;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[126]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
      for (Node c = current.getFirstChild();(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[13]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[14]--;
  CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.loops[15]++;
}
        // c may be replaced.
        c = replaceReturnWithBreak(c, current, resultName, labelName);
CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9.statements[127]++;
      }
    }

    return current;
  }
}

class CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9 ());
  }
    public static long[] statements = new long[128];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FunctionToBlockMutator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,3,1,1,1,3,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$4i7iaxu8ynn9wccexkajnpklqv89frut7gaxkq9gm9 () {
    super("com.google.javascript.jscomp.FunctionToBlockMutator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 127; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FunctionToBlockMutator.java");
      for (int i = 1; i <= 127; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

