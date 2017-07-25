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

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.*;

/**
 * Garbage collection for variable and function definitions. Basically performs
 * a mark-and-sweep type algorithm over the JavaScript parse tree.
 *
 * For each scope:
 * (1) Scan the variable/function declarations at that scope.
 * (2) Traverse the scope for references, marking all referenced variables.
 *     Unlike other compiler passes, this is a pre-order traversal, not a
 *     post-order traversal.
 * (3) If the traversal encounters an assign without other side-effects,
 *     create a continuation. Continue the continuation iff the assigned
 *     variable is referenced.
 * (4) When the traversal completes, remove all unreferenced variables.
 *
 * If it makes it easier, you can think of the continuations of the traversal
 * as a reference graph. Each continuation represents a set of edges, where the
 * source node is a known variable, and the destination nodes are lazily
 * evaluated when the continuation is executed.
 *
 * This algorithm is similar to the algorithm used by {@code SmartNameRemoval}.
 * {@code SmartNameRemoval} maintains an explicit graph of dependencies
 * between global symbols. However, {@code SmartNameRemoval} cannot handle
 * non-trivial edges in the reference graph ("A is referenced iff both B and C
 * are referenced"), or local variables. {@code SmartNameRemoval} is also
 * substantially more complicated because it tries to handle namespaces
 * (which is largely unnecessary in the presence of {@code CollapseProperties}.
 *
 * This pass also uses a more complex analysis of assignments, where
 * an assignment to a variable or a property of that variable does not
 * necessarily count as a reference to that variable, unless we can prove
 * that it modifies external state. This is similar to
 * {@code FlowSensitiveInlineVariables}, except that it works for variables
 * used across scopes.
 *
 */
class RemoveUnusedVars
    implements CompilerPass, OptimizeCalls.CallGraphCompilerPass {
  static {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.ping();
  }


  private final AbstractCompiler compiler;

  private final CodingConvention codingConvention;

  private final boolean removeGlobals;

  private boolean preserveFunctionExpressionNames;

  /**
   * Keep track of variables that we've referenced.
   */
  private final Set<Var> referenced = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[1]++;
  }

  /**
   * Keep track of variables that might be unreferenced.
   */
  private final List<Var> maybeUnreferenced = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[2]++;
  }

  /**
   * Keep track of scopes that we've traversed.
   */
  private final List<Scope> allFunctionScopes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[3]++;
  }

  /**
   * Keep track of assigns to variables that we haven't referenced.
   */
  private final Multimap<Var, Assign> assignsByVar =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[4]++;
  }

  /**
   * The assigns, indexed by the NAME node that they assign to.
   */
  private final Map<Node, Assign> assignsByNode = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[5]++;
  }

  /**
   * Subclass name -> class-defining call EXPR node. (like inherits)
   */
  private final Multimap<Var, Node> classDefiningCalls =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[6]++;
  }

  /**
   * Keep track of continuations that are finished iff the variable they're
   * indexed by is referenced.
   */
  private final Multimap<Var, Continuation> continuations =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[7]++;
  }

  private boolean modifyCallSites;

  private CallSiteOptimizer callSiteOptimizer;

  RemoveUnusedVars(
      AbstractCompiler compiler,
      boolean removeGlobals,
      boolean preserveFunctionExpressionNames,
      boolean modifyCallSites) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[8]++;
    this.codingConvention = compiler.getCodingConvention();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[9]++;
    this.removeGlobals = removeGlobals;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[10]++;
    this.preserveFunctionExpressionNames = preserveFunctionExpressionNames;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[11]++;
    this.modifyCallSites = modifyCallSites;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[12]++;
  }

  /**
   * Traverses the root, removing all unused variables. Multiple traversals
   * may occur to ensure all unused variables are removed.
   */
  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[13]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[14]++;
    SimpleDefinitionFinder defFinder = null;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((modifyCallSites) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[1]++;
      // For testing, allow the SimpleDefinitionFinder to be build now.
      defFinder = new SimpleDefinitionFinder(compiler);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[16]++;
      defFinder.process(externs, root);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[17]++;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[2]++;}
    process(externs, root, defFinder);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[18]++;
  }

  @Override
  public void process(
      Node externs, Node root, SimpleDefinitionFinder defFinder) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((modifyCallSites) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[3]++;
      Preconditions.checkNotNull(defFinder);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[20]++;
      callSiteOptimizer = new CallSiteOptimizer(compiler, defFinder);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[21]++;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[4]++;}
    traverseAndRemoveUnusedReferences(root);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[22]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((callSiteOptimizer != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[5]++;
      callSiteOptimizer.applyChanges();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[24]++;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[6]++;}
  }

  /**
   * Traverses a node recursively. Call this once per pass.
   */
  private void traverseAndRemoveUnusedReferences(Node root) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[25]++;
    Scope scope = new SyntacticScopeCreator(compiler).createScope(root, null);
    traverseNode(root, null, scope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[26]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((removeGlobals) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[7]++;
      collectMaybeUnreferencedVars(scope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[28]++;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[8]++;}

    interpretAssigns();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[29]++;
    removeUnreferencedVars();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[30]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[1]++;


    for (Scope fnScope : allFunctionScopes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[1]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[2]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[3]++;
}
      removeUnreferencedFunctionArgs(fnScope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[32]++;
    }
  }

  /**
   * Traverses everything in the current scope and marks variables that
   * are referenced.
   *
   * During traversal, we identify subtrees that will only be
   * referenced if their enclosing variables are referenced. Instead of
   * traversing those subtrees, we create a continuation for them,
   * and traverse them lazily.
   */
  private void traverseNode(Node n, Node parent, Scope scope) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[33]++;
    int type = n.getType();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[34]++;
    Var var = null;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[35]++;
    switch (type) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[9]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;
        // If this function is a removable var, then create a continuation
        // for it instead of traversing immediately.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[10]++;
          var = scope.getVar(n.getFirstChild().getString());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[37]++;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[11]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isRemovableVar(var)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[12]++;
          continuations.put(var, new Continuation(n, scope));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[39]++;

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[13]++;
          traverseFunction(n, scope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[40]++;
        }
        return;

      case Token.ASSIGN:
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[14]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[41]++;
        Assign maybeAssign = Assign.maybeCreateAssign(n);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((maybeAssign != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[15]++;
          // Put this in the assign map. It might count as a reference,
          // but we won't know that until we have an index of all assigns.
          var = scope.getVar(maybeAssign.nameNode.getString());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[43]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[17]++;
            assignsByVar.put(var, maybeAssign);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[45]++;
            assignsByNode.put(maybeAssign.nameNode, maybeAssign);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[46]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;

            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((isRemovableVar(var)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((maybeAssign.mayHaveSecondarySideEffects) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[19]++;
              // If the var is unreferenced and performing this assign has
              // no secondary side effects, then we can create a continuation
              // for it instead of traversing immediately.
              continuations.put(var, new Continuation(n, scope));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[48]++;
              return;

            } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[20]++;}

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[16]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[49]++;
        break;

      case Token.CALL:
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[21]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[50]++;
        Var modifiedVar = null;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[51]++;

        // Look for calls to inheritance-defining calls (such as goog.inherits).
        SubclassRelationship subclassRelationship =
            codingConvention.getClassesDefinedByCall(n);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((subclassRelationship != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[22]++;
          modifiedVar = scope.getVar(subclassRelationship.subclassName);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[53]++;

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[23]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[54]++;
          // Look for calls to addSingletonGetter calls.
          String className = codingConvention.getSingletonGetterClassName(n);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[55]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((className != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[24]++;
            modifiedVar = scope.getVar(className);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[56]++;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[25]++;}
        }
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[57]++;
int CodeCoverConditionCoverageHelper_C12;

        // Don't try to track the inheritance calls for non-globals. It would
        // be more correct to only not track when the subclass does not
        // reference a constructor, but checking that it is a global is
        // easier and mostly the same.
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((modifiedVar != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((modifiedVar.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((referenced.contains(modifiedVar)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[26]++;
          // Save a reference to the EXPR node.
          classDefiningCalls.put(modifiedVar, parent);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[58]++;
          continuations.put(modifiedVar, new Continuation(n, scope));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[59]++;
          return;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[27]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[60]++;
        break;

      case Token.NAME:
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[28]++;
        var = scope.getVar(n.getString());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[61]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[62]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[29]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[63]++;
          Node value = n.getFirstChild();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (128)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((isRemovableVar(var)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(value, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[31]++;
            // If the var is unreferenced and creating its value has no side
            // effects, then we can create a continuation for it instead
            // of traversing immediately.
            continuations.put(var, new Continuation(n, scope));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[65]++;
            return;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[32]++;}

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[30]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;

          // If arguments is escaped, we just assume the worst and continue
          // on all the parameters.
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 (("arguments".equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((scope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[33]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[67]++;
            Node lp = scope.getRootNode().getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[68]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
            for (Node a = lp.getFirstChild();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((a != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); a = a.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[4]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[5]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[6]++;
}
              markReferencedVar(scope.getVar(a.getString()));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[69]++;
            }

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[34]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;

          // All name references that aren't declarations or assigns
          // are references to other vars.
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[35]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[71]++;
int CodeCoverConditionCoverageHelper_C18;
            // If that var hasn't already been marked referenced, then
            // start tracking it.  If this is an assign, do nothing
            // for now.
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isRemovableVar(var)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[37]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[72]++;
int CodeCoverConditionCoverageHelper_C19;
              if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((assignsByNode.containsKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[39]++;
                markReferencedVar(var);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[73]++;

              } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[40]++;}

            } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[38]++;
              markReferencedVar(var);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[74]++;
            }

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[36]++;}
        }
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[75]++;
        break; default : CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[41]++;
    }
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[76]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[7]++;


int CodeCoverConditionCoverageHelper_C20;

    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[7]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[8]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[9]++;
}
      traverseNode(c, n, scope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[77]++;
    }
  }

  private boolean isRemovableVar(Var var) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[78]++;
int CodeCoverConditionCoverageHelper_C21;
    // Global variables are off-limits if the user might be using them.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((removeGlobals) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[42]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[43]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;

    // Referenced variables are off-limits.
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((referenced.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[44]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[45]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;

    // Exported variables are off-limits.
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((codingConvention.isExported(var.getName())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[46]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[47]++;}

    return true;
  }

  /**
   * Traverses a function, which creates a new scope in JavaScript.
   *
   * Note that CATCH blocks also create a new scope, but only for the
   * catch variable. Declarations within the block actually belong to the
   * enclosing scope. Because we don't remove catch variables, there's
   * no need to treat CATCH blocks differently like we do functions.
   */
  private void traverseFunction(Node n, Scope parentScope) {
    Preconditions.checkState(n.getChildCount() == 3);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[81]++;
    Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[82]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[83]++;

    final Node body = n.getLastChild();
    Preconditions.checkState(body.getNext() == null &&
            body.isBlock());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[84]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[85]++;

    Scope fnScope =
        new SyntacticScopeCreator(compiler).createScope(n, parentScope);
    traverseNode(body, n, fnScope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[86]++;

    collectMaybeUnreferencedVars(fnScope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[87]++;
    allFunctionScopes.add(fnScope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[88]++;
  }

  /**
   * For each variable in this scope that we haven't found a reference
   * for yet, add it to the list of variables to check later.
   */
  private void collectMaybeUnreferencedVars(Scope scope) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[89]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;
    for (Iterator<Var> it = scope.getVars();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[10]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[11]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[12]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[90]++;
      Var var = it.next();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[91]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isRemovableVar(var)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[48]++;
        maybeUnreferenced.add(var);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[92]++;

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[49]++;}
    }
  }

  /**
   * Removes unreferenced arguments from a function declaration and when
   * possible the function's callSites.
   *
   * @param fnScope The scope inside the function
   */
  private void removeUnreferencedFunctionArgs(Scope fnScope) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[93]++;
int CodeCoverConditionCoverageHelper_C26;
    // Notice that removing unreferenced function args breaks
    // Function.prototype.length. In advanced mode, we don't really care
    // about this: we consider "length" the equivalent of reflecting on
    // the function's lexical source.
    //
    // Rather than create a new option for this, we assume that if the user
    // is removing globals, then it's OK to remove unused function args.
    //
    // See http://code.google.com/p/closure-compiler/issues/detail?id=253
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((removeGlobals) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[50]++;
      return;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[51]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[94]++;

    Node function = fnScope.getRootNode();

    Preconditions.checkState(function.isFunction());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[95]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[96]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((NodeUtil.isGetOrSetKey(function.getParent())) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[52]++;
      // The parameters object literal setters can not be removed.
      return;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[53]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[97]++;

    Node argList = getFunctionArgList(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[98]++;
    boolean modifyCallers = modifyCallSites
        && callSiteOptimizer.canModifyCallers(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[99]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((modifyCallers) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[54]++;
      // Strip unreferenced args off the end of the function declaration.
      Node lastArg;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[100]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[13]++;


      while ((lastArg = argList.getLastChild()) != null) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[13]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[14]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[15]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[101]++;
        Var var = fnScope.getVar(lastArg.getString());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[102]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((referenced.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[56]++;
          argList.removeChild(lastArg);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[103]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[104]++;

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[57]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[105]++;
          break;
        }
      }

    } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[55]++;
      callSiteOptimizer.optimize(fnScope, referenced);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[106]++;
    }
  }


  /**
   * @return the LP node containing the function parameters.
   */
  private static Node getFunctionArgList(Node function) {
    return function.getFirstChild().getNext();
  }

  private static class CallSiteOptimizer {
    private final AbstractCompiler compiler;
    private final SimpleDefinitionFinder defFinder;
    private final List<Node> toRemove = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[107]++;
  }
    private final List<Node> toReplaceWithZero = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[108]++;
  }

    CallSiteOptimizer(
        AbstractCompiler compiler,
        SimpleDefinitionFinder defFinder) {
      this.compiler = compiler;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[109]++;
      this.defFinder = defFinder;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[110]++;
    }

    public void optimize(Scope fnScope, Set<Var> referenced) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[111]++;
      Node function = fnScope.getRootNode();
      Preconditions.checkState(function.isFunction());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[112]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[113]++;
      Node argList = getFunctionArgList(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[114]++;

      // In this path we try to modify all the call sites to remove unused
      // function parameters.
      boolean changeCallSignature = canChangeSignature(function);
      markUnreferencedFunctionArgs(
          fnScope, function, referenced,
          argList.getFirstChild(), 0, changeCallSignature);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[115]++;
    }

    /**
     * Applies optimizations to all previously marked nodes.
     */
    public void applyChanges() {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[116]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[16]++;


      for (Node n : toRemove) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[16]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[17]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[18]++;
}
        n.getParent().removeChild(n);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[117]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[118]++;
      }
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[119]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[19]++;


      for (Node n : toReplaceWithZero) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[19]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[20]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[21]++;
}
        n.getParent().replaceChild(n, IR.number(0).srcref(n));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[120]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[121]++;
      }
    }

    /**
     * For each unused function parameter, determine if it can be removed
     * from all the call sites, if so, remove it from the function signature
     * and the call sites otherwise replace the unused value where possible
     * with a constant (0).
     *
     * @param scope The function scope
     * @param function The function
     * @param param The current parameter node in the parameter list.
     * @param paramIndex The index of the current parameter
     * @param canChangeSignature Whether function signature can be change.
     * @return Whether there is a following function parameter.
     */
    private boolean markUnreferencedFunctionArgs(
        Scope scope, Node function, Set<Var> referenced,
        Node param, int paramIndex,
        boolean canChangeSignature) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[122]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[58]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[123]++;
        // Take care of the following siblings first.
        boolean hasFollowing = markUnreferencedFunctionArgs(
            scope, function, referenced, param.getNext(), paramIndex+1,
            canChangeSignature);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[124]++;

        Var var = scope.getVar(param.getString());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[125]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((referenced.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[60]++;
          Preconditions.checkNotNull(var);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[126]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[127]++;

          // Remove call parameter if we can generally change the signature
          // or if it is the last parameter in the parameter list.
          boolean modifyAllCallSites = canChangeSignature || !hasFollowing;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[128]++;
int CodeCoverConditionCoverageHelper_C33;
          if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((modifyAllCallSites) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[62]++;
            modifyAllCallSites = canRemoveArgFromCallSites(
                function, paramIndex);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[129]++;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[63]++;}

          tryRemoveArgFromCallSites(function, paramIndex, modifyAllCallSites);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[130]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[131]++;
int CodeCoverConditionCoverageHelper_C34;

          // Remove an unused function parameter if all the call sites can
          // be modified to remove it, or if it is the last parameter.
          if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((modifyAllCallSites) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((hasFollowing) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[64]++;
            toRemove.add(param);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[132]++;
            return hasFollowing;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[65]++;}

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[61]++;}
        return true;

      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[59]++;
        // Anything past the last formal parameter can be removed from the call
        // sites.
        tryRemoveAllFollowingArgs(function, paramIndex-1);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[133]++;
        return false;
      }
    }

    /**
     * Remove all references to a parameter, otherwise simplify the known
     * references.
     * @return Whether all the references were removed.
     */
    private boolean canRemoveArgFromCallSites(Node function, int argIndex) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[134]++;
      Definition definition = getFunctionDefinition(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[135]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[22]++;



      // Check all the call sites.
      for (UseSite site : defFinder.getUseSites(definition)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[22]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[23]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[24]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[136]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isModifiableCallSite(site)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[66]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[137]++;
          Node arg = getArgumentForCallOrNewOrDotCall(site, argIndex);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[138]++;
int CodeCoverConditionCoverageHelper_C36;
          // TODO(johnlenz): try to remove parameters with side-effects by
          // decomposing the call expression.
          if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(arg, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[68]++;
            return false;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[69]++;}

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[67]++;
          return false;
        }
      }

      return true;
    }

    /**
     * Remove all references to a parameter if possible otherwise simplify the
     * side-effect free parameters.
     */
    private void tryRemoveArgFromCallSites(
        Node function, int argIndex, boolean canModifyAllSites) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[139]++;
      Definition definition = getFunctionDefinition(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[140]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[25]++;



      for (UseSite site : defFinder.getUseSites(definition)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[25]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[26]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[27]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[141]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((isModifiableCallSite(site)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[70]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[142]++;
          Node arg = getArgumentForCallOrNewOrDotCall(site, argIndex);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[143]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[72]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[144]++;
int CodeCoverConditionCoverageHelper_C39;
            // Even if we can't change the signature in general we can always
            // remove an unused value off the end of the parameter list.
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((canModifyAllSites) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((arg.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(arg, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[74]++;
              toRemove.add(arg);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[145]++;

            } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[75]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[146]++;
int CodeCoverConditionCoverageHelper_C40;
              // replace the node in the arg with 0
              if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(arg, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && (!
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((arg.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((arg.getDouble() != 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[76]++;
                toReplaceWithZero.add(arg);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[147]++;

              } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[77]++;}
            }

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[73]++;}

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[71]++;}
      }
    }

    /**
     * Remove all the following parameters without side-effects
     */
    private void tryRemoveAllFollowingArgs(Node function, final int argIndex) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[148]++;
      Definition definition = getFunctionDefinition(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[149]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[28]++;


      for (UseSite site : defFinder.getUseSites(definition)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[28]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[29]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[30]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[150]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isModifiableCallSite(site)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[78]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[151]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[79]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[152]++;
        Node arg = getArgumentForCallOrNewOrDotCall(site, argIndex + 1);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[153]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[31]++;


int CodeCoverConditionCoverageHelper_C42;
        while ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[31]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[32]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[33]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[154]++;
int CodeCoverConditionCoverageHelper_C43;
          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(arg)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[80]++;
            toRemove.add(arg);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[155]++;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[81]++;}
          arg = arg.getNext();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[156]++;
        }
      }
    }

    /**
     * Returns the nth argument node given a usage site for a direct function
     * call or for a func.call() node.
     */
    private static Node getArgumentForCallOrNewOrDotCall(UseSite site,
        final int argIndex) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[157]++;
      int adjustedArgIndex = argIndex;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[158]++;
      Node parent = site.node.getParent();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[159]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(parent)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[82]++;
        adjustedArgIndex++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[160]++;

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[83]++;}
      return NodeUtil.getArgumentForCallOrNew(parent, adjustedArgIndex);
    }

    /**
     * @param function
     * @return Whether the callers to this function can be modified in any way.
     */
    boolean canModifyCallers(Node function) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[161]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarArgsFunction(function)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[84]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[85]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[162]++;

      DefinitionSite defSite = defFinder.getDefinitionForFunction(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[163]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((defSite == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[86]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[87]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[164]++;

      Definition definition = defSite.definition;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[165]++;
int CodeCoverConditionCoverageHelper_C47;

      // Be conservative, don't try to optimize any declaration that isn't as
      // simple function declaration or assignment.
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((SimpleDefinitionFinder.isSimpleFunctionDeclaration(function)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[88]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[89]++;}

      return defFinder.canModifyDefinition(definition);
    }

    /**
     * @param site The site to inspect
     * @return Whether the call site is suitable for modification
     */
    private static boolean isModifiableCallSite(UseSite site) {
      return SimpleDefinitionFinder.isCallOrNewSite(site)
          && !NodeUtil.isFunctionObjectApply(site.node.getParent());
    }

    /**
     * @return Whether the definitionSite represents a function whose call
     *      signature can be modified.
     */
    private boolean canChangeSignature(Node function) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[166]++;
      Definition definition = getFunctionDefinition(function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[167]++;
      CodingConvention convention = compiler.getCodingConvention();

      Preconditions.checkState(!definition.isExtern());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[168]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[169]++;

      Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[170]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[34]++;


      for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[34]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[35]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[36]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[171]++;
        Node parent = site.node.getParent();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[172]++;
int CodeCoverConditionCoverageHelper_C48;

        // This was a use site removed by something else before we run.
        // 1. By another pass before us which means the definition graph is
        //    no updated properly.
        // 2. By the continuations algorithm above.
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[90]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[173]++;
          continue;
 // Ignore it.
        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[91]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[174]++;
int CodeCoverConditionCoverageHelper_C49;

        // Ignore references within goog.inherits calls.
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((convention.getClassesDefinedByCall(parent) != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[92]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[175]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[93]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[176]++;
int CodeCoverConditionCoverageHelper_C50;

        // Accessing the property directly prevents rewrite.
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((SimpleDefinitionFinder.isCallOrNewSite(site)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[94]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[177]++;
int CodeCoverConditionCoverageHelper_C51;
          if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(parent.getParent())) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[96]++;
            return false;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[97]++;}

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[95]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[178]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectApply(parent)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[98]++;
          return false;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[99]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[179]++;

        // TODO(johnlenz): support specialization

        // Multiple definitions prevent rewrite.
        // Attempt to validate the state of the simple definition finder.
        Node nameNode = site.node;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[180]++;
        Collection<Definition> singleSiteDefinitions =
            defFinder.getDefinitionsReferencedAt(nameNode);
        Preconditions.checkState(singleSiteDefinitions.size() == 1);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[181]++;
        Preconditions.checkState(singleSiteDefinitions.contains(definition));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[182]++;
      }

      return true;
    }

    /**
     * @param function
     * @return the Definition object for the function.
     */
    private Definition getFunctionDefinition(Node function) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[183]++;
      DefinitionSite definitionSite = defFinder.getDefinitionForFunction(
          function);
      Preconditions.checkNotNull(definitionSite);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[184]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[185]++;
      Definition definition = definitionSite.definition;
      Preconditions.checkState(!definitionSite.inExterns);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[186]++;
      Preconditions.checkState(definition.getRValue() == function);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[187]++;
      return definition;
    }
  }


  /**
   * Look at all the property assigns to all variables.
   * These may or may not count as references. For example,
   *
   * <code>
   * var x = {};
   * x.foo = 3; // not a reference.
   * var y = foo();
   * y.foo = 3; // is a reference.
   * </code>
   *
   * Interpreting assignments could mark a variable as referenced that
   * wasn't referenced before, in order to keep it alive. Because we find
   * references by lazily traversing subtrees, marking a variable as
   * referenced could trigger new traversals of new subtrees, which could
   * find new references.
   *
   * Therefore, this interpretation needs to be run to a fixed point.
   */
  private void interpretAssigns() {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[188]++;
    boolean changes = false;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[189]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[37]++;


int CodeCoverConditionCoverageHelper_C53;
    do {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[37]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[38]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[39]++;
}
      changes = false;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[190]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[191]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[40]++;


int CodeCoverConditionCoverageHelper_C54;

      // We can't use traditional iterators and iterables for this list,
      // because our lazily-evaluated continuations will modify it while
      // we traverse it.
      for (int current = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((current < maybeUnreferenced.size()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); current++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[40]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[41]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[42]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[192]++;
        Var var = maybeUnreferenced.get(current);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[193]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((referenced.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[100]++;
          maybeUnreferenced.remove(current);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[194]++;
          current--;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[195]++;

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[101]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[196]++;
          boolean assignedToUnknownValue = false;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[197]++;
          boolean hasPropertyAssign = false;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[198]++;
int CodeCoverConditionCoverageHelper_C56;

          if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((var.getParentNode().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(var.getParentNode().getParent())) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[102]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[199]++;
            Node value = var.getInitialValue();
            assignedToUnknownValue = value != null &&
                !NodeUtil.isLiteralValue(value, true);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[200]++;

          } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[103]++;
            // This was initialized to a function arg or a catch param
            // or a for...in variable.
            assignedToUnknownValue = true;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[201]++;
          }
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[202]++;

          boolean maybeEscaped = false;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[203]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[43]++;


          for (Assign assign : assignsByVar.get(var)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[43]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[44]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[45]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[204]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((assign.isPropertyAssign) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[104]++;
              hasPropertyAssign = true;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[205]++;

            } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[105]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[206]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(
                assign.assignNode.getLastChild(), true)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[106]++;
              assignedToUnknownValue = true;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[207]++;

            } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[107]++;}
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[208]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((assign.maybeAliased) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[108]++;
              maybeEscaped = true;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[209]++;

            } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[109]++;}
          }
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[210]++;
int CodeCoverConditionCoverageHelper_C60;

          if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C60 |= (32)) == 0 || true) &&
 ((assignedToUnknownValue) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((maybeEscaped) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((hasPropertyAssign) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 3) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 3) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[110]++;
            changes = markReferencedVar(var) || changes;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[211]++;
            maybeUnreferenced.remove(current);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[212]++;
            current--;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[213]++;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[111]++;}
        }
      }
    } while ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((changes) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false));
  }

  /**
   * Remove all assigns to a var.
   */
  private void removeAllAssigns(Var var) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[214]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[46]++;


    for (Assign assign : assignsByVar.get(var)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[46]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[47]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[48]++;
}
      assign.remove();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[215]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[216]++;
    }
  }

  /**
   * Marks a var as referenced, recursing into any values of this var
   * that we skipped.
   * @return True if this variable had not been referenced before.
   */
  private boolean markReferencedVar(Var var) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[217]++;
int CodeCoverConditionCoverageHelper_C61;
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((referenced.add(var)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[112]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[218]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[49]++;


      for (Continuation c : continuations.get(var)) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[49]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[50]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[51]++;
}
        c.apply();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[219]++;
      }
      return true;

    } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[113]++;}
    return false;
  }

  /**
   * Removes any vars in the scope that were not referenced. Removes any
   * assignments to those variables as well.
   */
  private void removeUnreferencedVars() {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[220]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[52]++;


int CodeCoverConditionCoverageHelper_C62;
    for (Iterator<Var> it = maybeUnreferenced.iterator();(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); ) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[52]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[53]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[54]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[221]++;
      Var var = it.next();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[222]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[55]++;



      // Remove calls to inheritance-defining functions where the unreferenced
      // class is the subclass.
      for (Node exprCallNode : classDefiningCalls.get(var)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[55]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[56]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[57]++;
}
        NodeUtil.removeChild(exprCallNode.getParent(), exprCallNode);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[223]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[224]++;
      }

      // Regardless of what happens to the original declaration,
      // we need to remove all assigns, because they may contain references
      // to other unreferenced variables.
      removeAllAssigns(var);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[225]++;

      compiler.addToDebugLog("Unreferenced var: " + var.name);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[226]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[227]++;
      Node nameNode = var.nameNode;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[228]++;
      Node toRemove = nameNode.getParent();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[229]++;
      Node parent = toRemove.getParent();

      Preconditions.checkState(
          toRemove.isVar() ||
          toRemove.isFunction() ||
          toRemove.isParamList() &&
          parent.isFunction(),
          "We should only declare vars and functions and function args");
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[230]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[231]++;
int CodeCoverConditionCoverageHelper_C63;

      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((toRemove.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[114]++;

        // Don't remove function arguments here. That's a special case
        // that's taken care of in removeUnreferencedFunctionArgs.
      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[115]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[232]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(toRemove)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[116]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[233]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((preserveFunctionExpressionNames) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[118]++;
          toRemove.getFirstChild().setString("");
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[234]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[235]++;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[119]++;}

        // Don't remove bleeding functions.
      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[117]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[236]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (32)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((parent.getChildCount() < 4) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 3) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 3) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[120]++;

        // foreach iterations have 3 children. Leave them alone.
      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[121]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[237]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (32)) == 0 || true) &&
 ((toRemove.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((nameNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(nameNode.getFirstChild(), compiler)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[122]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[238]++;
int CodeCoverConditionCoverageHelper_C68;
        // If this is a single var declaration, we can at least remove the
        // declaration itself and just leave the value, e.g.,
        // var a = foo(); => foo();
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((toRemove.getChildCount() == 1) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[124]++;
          parent.replaceChild(toRemove,
              IR.exprResult(nameNode.removeFirstChild()));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[239]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[240]++;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[125]++;}

      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[123]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[241]++;
int CodeCoverConditionCoverageHelper_C69; if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((toRemove.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((toRemove.getChildCount() > 1) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[126]++;
        // For var declarations with multiple names (i.e. var a, b, c),
        // only remove the unreferenced name
        toRemove.removeChild(nameNode);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[242]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[243]++;

      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[127]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[244]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[128]++;
        NodeUtil.removeChild(parent, toRemove);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[245]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[246]++;

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[129]++;}
}
}
}
}
}
    }
  }

  /**
   * Our progress in a traversal can be expressed completely as the
   * current node and scope. The continuation lets us save that
   * information so that we can continue the traversal later.
   */
  private class Continuation {
    private final Node node;
    private final Scope scope;

    Continuation(Node node, Scope scope) {
      this.node = node;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[247]++;
      this.scope = scope;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[248]++;
    }

    void apply() {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[249]++;
int CodeCoverConditionCoverageHelper_C71;
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(node)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[130]++;
        traverseFunction(node, scope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[250]++;

      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[131]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[251]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[58]++;


int CodeCoverConditionCoverageHelper_C72;
        for (Node child = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[58]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[59]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[60]++;
}
          traverseNode(child, node, scope);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[252]++;
        }
      }
    }
  }

  private static class Assign {

    final Node assignNode;

    final Node nameNode;

    // If false, then this is an assign to the normal variable. Otherwise,
    // this is an assign to a property of that variable.
    final boolean isPropertyAssign;

    // Secondary side effects are any side effects in this assign statement
    // that aren't caused by the assignment operation itself. For example,
    // a().b = 3;
    // a = b();
    // var foo = (a = b);
    // In the first two cases, the sides of the assignment have side-effects.
    // In the last one, the result of the assignment is used, so we
    // are conservative and assume that it may be used in a side-effecting
    // way.
    final boolean mayHaveSecondarySideEffects;

    // If true, the value may have escaped and any modification is a use.
    final boolean maybeAliased;

    Assign(Node assignNode, Node nameNode, boolean isPropertyAssign) {
      Preconditions.checkState(NodeUtil.isAssignmentOp(assignNode));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[253]++;
      this.assignNode = assignNode;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[254]++;
      this.nameNode = nameNode;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[255]++;
      this.isPropertyAssign = isPropertyAssign;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[256]++;

      this.maybeAliased = NodeUtil.isExpressionResultUsed(assignNode);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[257]++;
      this.mayHaveSecondarySideEffects =
          maybeAliased ||
          NodeUtil.mayHaveSideEffects(assignNode.getFirstChild()) ||
          NodeUtil.mayHaveSideEffects(assignNode.getLastChild());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[258]++;
    }

    /**
     * If this is an assign to a variable or its property, return it.
     * Otherwise, return null.
     */
    static Assign maybeCreateAssign(Node assignNode) {
      Preconditions.checkState(NodeUtil.isAssignmentOp(assignNode));
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[259]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[260]++;

      // Skip one level of GETPROPs or GETELEMs.
      //
      // Don't skip more than one level, because then we get into
      // situations where assigns to properties of properties will always
      // trigger side-effects, and the variable they're on cannot be removed.
      boolean isPropAssign = false;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[261]++;
      Node current = assignNode.getFirstChild();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[262]++;
int CodeCoverConditionCoverageHelper_C73;
      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(current)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[132]++;
        current = current.getFirstChild();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[263]++;
        isPropAssign = true;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[264]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[265]++;
int CodeCoverConditionCoverageHelper_C74;

        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((current.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((current.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[134]++;
          // Prototype properties sets should be considered like normal
          // property sets.
          current = current.getFirstChild();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[266]++;

        } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[135]++;}

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[133]++;}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[267]++;
int CodeCoverConditionCoverageHelper_C75;

      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((current.isName()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[136]++;
        return new Assign(assignNode, current, isPropAssign);

      } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[137]++;}
      return null;
    }

    /**
     * Replace the current assign with its right hand side.
     */
    void remove() {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[268]++;
      Node parent = assignNode.getParent();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[269]++;
int CodeCoverConditionCoverageHelper_C76;
      if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((mayHaveSecondarySideEffects) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[138]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[270]++;
        Node replacement = assignNode.getLastChild().detachFromParent();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[271]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[61]++;


int CodeCoverConditionCoverageHelper_C77;

        // Aggregate any expressions in GETELEMs.
        for (Node current = assignNode.getFirstChild();(((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((current.isName()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false);
             current = current.getFirstChild()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[61]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[62]--;
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.loops[63]++;
}
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[272]++;
int CodeCoverConditionCoverageHelper_C78;
          if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((current.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[140]++;
            replacement = IR.comma(
                current.getLastChild().detachFromParent(), replacement);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[273]++;
            replacement.copyInformationFrom(current);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[274]++;

          } else {
  CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[141]++;}
        }

        parent.replaceChild(assignNode, replacement);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[275]++;

      } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[139]++;
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[276]++;
        Node gramps = parent.getParent();
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[277]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[142]++;
          gramps.removeChild(parent);
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[278]++;

        } else {
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.branches[143]++;
          parent.replaceChild(assignNode,
              assignNode.getLastChild().detachFromParent());
CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t.statements[279]++;
        }
      }
    }
  }
}

class CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t ());
  }
    public static long[] statements = new long[280];
    public static long[] branches = new long[144];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[80];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RemoveUnusedVars.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,2,1,1,3,1,3,2,1,1,1,1,1,2,1,1,1,1,1,1,1,0,1,1,1,1,2,1,2,1,1,3,3,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,2,1,1,1,3,1,1,2,1,1,3,3,1,2,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 79; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[64];

  public CodeCoverCoverageCounter$1wfy44famp3d0jrgsrpdzbcyeil3oaq1t () {
    super("com.google.javascript.jscomp.RemoveUnusedVars.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 279; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 143; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 79; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 63; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RemoveUnusedVars.java");
      for (int i = 1; i <= 279; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 143; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 79; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 21; i++) {
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

