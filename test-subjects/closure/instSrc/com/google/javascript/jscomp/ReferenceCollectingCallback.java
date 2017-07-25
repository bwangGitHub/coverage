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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.StaticReference;
import com.google.javascript.rhino.jstype.StaticSourceFile;
import com.google.javascript.rhino.jstype.StaticSymbolTable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A helper class for passes that want to access all information about where a
 * variable is referenced and declared at once and then make a decision as to
 * how it should be handled, possibly inlining, reordering, or generating
 * warnings. Callers do this by providing {@link Behavior} and then
 * calling {@link #process(Node, Node)}.
 *
 * @author kushal@google.com (Kushal Dave)
 */
class ReferenceCollectingCallback implements ScopedCallback,
    HotSwapCompilerPass,
    StaticSymbolTable<Var, ReferenceCollectingCallback.Reference> {
  static {
    CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.ping();
  }


  /**
   * Maps a given variable to a collection of references to that name. Note that
   * Var objects are not stable across multiple traversals (unlike scope root or
   * name).
   */
  private final Map<Var, ReferenceCollection> referenceMap =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[1]++;
  }

  /**
   * The stack of basic blocks and scopes the current traversal is in.
   */
  private final Deque<BasicBlock> blockStack = new ArrayDeque<BasicBlock>();
  {
    CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[2]++;
  }

  /**
   * Source of behavior at various points in the traversal.
   */
  private final Behavior behavior;

  /**
   * JavaScript compiler to use in traversing.
   */
  private final AbstractCompiler compiler;

  /**
   * Only collect references for filtered variables.
   */
  private final Predicate<Var> varFilter;

  /**
   * Constructor initializes block stack.
   */
  ReferenceCollectingCallback(AbstractCompiler compiler, Behavior behavior) {
    this(compiler, behavior, Predicates.<Var>alwaysTrue());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[3]++;
  }

  /**
   * Constructor only collects references that match the given variable.
   *
   * The test for Var equality uses reference equality, so it's necessary to
   * inject a scope when you traverse.
   */
  ReferenceCollectingCallback(AbstractCompiler compiler, Behavior behavior,
      Predicate<Var> varFilter) {
    this.compiler = compiler;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[4]++;
    this.behavior = behavior;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[5]++;
    this.varFilter = varFilter;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[6]++;
  }

  /**
   * Convenience method for running this pass over a tree with this
   * class as a callback.
   */
  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverseRoots(
        compiler, Lists.newArrayList(externs, root), this);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[7]++;
  }

  /**
   * Same as process but only runs on a part of AST associated to one script.
   */
  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    NodeTraversal.traverse(compiler, scriptRoot, this);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[8]++;
  }

  /**
   * Gets the variables that were referenced in this callback.
   */
  @Override
  public Iterable<Var> getAllSymbols() {
    return referenceMap.keySet();
  }

  @Override
  public Scope getScope(Var var) {
    return var.scope;
  }

  /**
   * Gets the reference collection for the given variable.
   */
  @Override
  public ReferenceCollection getReferences(Var v) {
    return referenceMap.get(v);
  }

  /**
   * For each node, update the block stack and reference collection
   * as appropriate.
   */
  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[1]++;
      Var v;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.getString().equals("arguments")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[3]++;
        v = t.getScope().getArgumentsVar();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[11]++;

      } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[4]++;
        v = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[12]++;
      }
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((varFilter.apply(v)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[5]++;
        addReference(v, new Reference(n, t, blockStack.peek()));
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[14]++;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[2]++;}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isBlockBoundary(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[7]++;
      blockStack.pop();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[16]++;

    } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[8]++;}
  }

  /**
   * Updates block stack and invokes any additional behavior.
   */
  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[17]++;
    Node n = t.getScope().getRootNode();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[18]++;
    BasicBlock parent = blockStack.isEmpty() ? null : blockStack.peek();
    blockStack.push(new BasicBlock(parent, n));
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[19]++;
  }

  /**
   * Updates block stack and invokes any additional behavior.
   */
  @Override
  public void exitScope(NodeTraversal t) {
    blockStack.pop();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[20]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((t.getScope().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[9]++;
      // Update global scope reference lists when we are done with it.
      compiler.updateGlobalVarReferences(referenceMap, t.getScopeRoot());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[22]++;
      behavior.afterExitScope(t, compiler.getGlobalVarReferences());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[23]++;

    } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[10]++;
      behavior.afterExitScope(t, new ReferenceMapWrapper(referenceMap));
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[24]++;
    }
  }

  /**
   * Updates block stack.
   */
  @Override
  public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
      Node parent) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
    // If node is a new basic block, put on basic block stack
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isBlockBoundary(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[11]++;
      blockStack.push(new BasicBlock(blockStack.peek(), n));
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[26]++;

    } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[12]++;}
    return true;
  }

  /**
   * @return true if this node marks the start of a new basic block
   */
  private static boolean isBlockBoundary(Node n, Node parent) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[13]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[28]++;
      switch (parent.getType()) {
        case Token.DO:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[15]++;
        case Token.FOR:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[16]++;
        case Token.TRY:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[17]++;
        case Token.WHILE:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[18]++;
        case Token.WITH:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[19]++;
          // NOTE: TRY has up to 3 child blocks:
          // TRY
          //   BLOCK
          //   BLOCK
          //     CATCH
          //   BLOCK
          // Note that there is an explicit CATCH token but no explicit
          // FINALLY token. For simplicity, we consider each BLOCK
          // a separate basic BLOCK.
          return true;
        case Token.AND:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[20]++;
        case Token.HOOK:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[21]++;
        case Token.IF:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[22]++;
        case Token.OR:
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[23]++;
          // The first child of a conditional is not a boundary,
          // but all the rest of the children are.
          return n != parent.getFirstChild(); default : CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[24]++;

      }

    } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[14]++;}

    return n.isCase();
  }

  private void addReference(Var v, Reference reference) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[29]++;
    // Create collection if none already
    ReferenceCollection referenceInfo = referenceMap.get(v);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((referenceInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[25]++;
      referenceInfo = new ReferenceCollection();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[31]++;
      referenceMap.put(v, referenceInfo);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[32]++;

    } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[26]++;}

    // Add this particular reference
    referenceInfo.add(reference);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[33]++;
  }

  interface ReferenceMap {
    ReferenceCollection getReferences(Var var);
  }

  private static class ReferenceMapWrapper implements ReferenceMap {
    private final Map<Var, ReferenceCollection> referenceMap;

    public ReferenceMapWrapper(Map<Var, ReferenceCollection> referenceMap) {
      this.referenceMap = referenceMap;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[34]++;
    }

    @Override
    public ReferenceCollection getReferences(Var var) {
      return referenceMap.get(var);
    }
  }

  /**
   * Way for callers to add specific behavior during traversal that
   * utilizes the built-up reference information.
   */
  interface Behavior {
    /**
     * Called after we finish with a scope.
     */
    void afterExitScope(NodeTraversal t, ReferenceMap referenceMap);
  }

  static Behavior DO_NOTHING_BEHAVIOR = new Behavior() {
    @Override
    public void afterExitScope(NodeTraversal t, ReferenceMap referenceMap) {}
  };
  static {
    CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[35]++;
  }

  /**
   * A collection of references. Can be subclassed to apply checks or
   * store additional state when adding.
   */
  static class ReferenceCollection implements Iterable<Reference> {

    List<Reference> references = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[36]++;
  }

    @Override
    public Iterator<Reference> iterator() {
      return references.iterator();
    }

    void add(Reference reference) {
      references.add(reference);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[37]++;
    }

    /**
     * Determines if the variable for this reference collection is
     * "well-defined." A variable is well-defined if we can prove at
     * compile-time that it's assigned a value before it's used.
     *
     * Notice that if this function returns false, this doesn't imply that the
     * variable is used before it's assigned. It just means that we don't
     * have enough information to make a definitive judgment.
     */
    protected boolean isWellDefined() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[38]++;
      int size = references.size();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((size == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[27]++;
        return false;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[28]++;}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[40]++;

      // If this is a declaration that does not instantiate the variable,
      // it's not well-defined.
      Reference init = getInitializingReference();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((init == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[29]++;
        return false;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[30]++;}

      Preconditions.checkState(references.get(0).isDeclaration());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[42]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[43]++;
      BasicBlock initBlock = init.getBasicBlock();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[44]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[1]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[2]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[3]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((initBlock.provablyExecutesBefore(
                references.get(i).getBasicBlock())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[31]++;
          return false;

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[32]++;}
      }

      return true;
    }

    /**
     * Whether the variable is escaped into an inner scope.
     */
    boolean isEscaped() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[46]++;
      Scope scope = null;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[47]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[4]++;


      for (Reference ref : references) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[4]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[5]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[6]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[33]++;
          scope = ref.scope;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[49]++;

        } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[34]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[50]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((scope != ref.scope) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[35]++;
          return true;

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[36]++;}
}
      }
      return false;
    }

    /**
     * @param index The index into the references array to look for an
     * assigning declaration.
     *
     * This is either the declaration if a value is assigned (such as
     * "var a = 2", "function a()...", "... catch (a)...").
     */
    private boolean isInitializingDeclarationAt(int index) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[51]++;
      Reference maybeInit = references.get(index);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((maybeInit.isInitializingDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[37]++;
        // This is a declaration that represents the initial value.
        // Specifically, var declarations without assignments such as "var a;"
        // are not.
        return true;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[38]++;}
      return false;
    }

    /**
     * @param index The index into the references array to look for an
     * initialized assignment reference. That is, an assignment immediately
     * follow a variable declaration that itself does not initialize the
     * variable.
     */
    private boolean isInitializingAssignmentAt(int index) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[53]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((index < references.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((index > 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[39]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[54]++;
        Reference maybeDecl = references.get(index-1);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((maybeDecl.isVarDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[41]++;
          Preconditions.checkState(!maybeDecl.isInitializingDeclaration());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[56]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[57]++;
          Reference maybeInit = references.get(index);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[58]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((maybeInit.isSimpleAssignmentToName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[43]++;
            return true;

          } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[42]++;}

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[40]++;}
      return false;
    }

    /**
     * @return The reference that provides the value for the variable at the
     * time of the first read, if known, otherwise null.
     *
     * This is either the variable declaration ("var a = ...") or first
     * reference following the declaration if it is an assignment.
     */
    Reference getInitializingReference() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[59]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isInitializingDeclarationAt(0)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[45]++;
        return references.get(0);

      } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[46]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[60]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isInitializingAssignmentAt(1)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[47]++;
        return references.get(1);

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[48]++;}
}
      return null;
    }

    /**
     * Constants are allowed to be defined after their first use.
     */
    Reference getInitializingReferenceForConstants() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[61]++;
      int size = references.size();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[62]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[7]++;


int CodeCoverConditionCoverageHelper_C21;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[7]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[8]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[9]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[63]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((isInitializingDeclarationAt(i)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isInitializingAssignmentAt(i)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[49]++;
          return references.get(i);

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[50]++;}
      }
      return null;
    }

    /**
     * @return Whether the variable is only assigned a value once for its
     *     lifetime.
     */
    boolean isAssignedOnceInLifetime() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[64]++;
      Reference ref = getOneAndOnlyAssignment();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[65]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ref == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[51]++;
        return false;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[52]++;}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[66]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;

      // Make sure this assignment is not in a loop.
      for (BasicBlock block = ref.getBasicBlock();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((block != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); block = block.getParent()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[10]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[11]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[12]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[67]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((block.isFunction) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[53]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[68]++;
          break;

        } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[54]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[69]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((block.isLoop) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[55]++;
          return false;

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[56]++;}
}
      }

      return true;
    }

    /**
     * @return The one and only assignment. Returns if there are 0 or 2+
     *    assignments.
     */
    private Reference getOneAndOnlyAssignment() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[70]++;
      Reference assignment = null;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[71]++;
      int size = references.size();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[72]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[13]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[14]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[15]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[73]++;
        Reference ref = references.get(i);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[74]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((ref.isLvalue()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((ref.isInitializingDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[57]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[75]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((assignment == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[59]++;
            assignment = ref;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[76]++;

          } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[60]++;
            return null;
          }

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[58]++;}
      }
      return assignment;
    }

    /**
     * @return Whether the variable is never assigned a value.
     */
    boolean isNeverAssigned() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[77]++;
      int size = references.size();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[78]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[16]++;


int CodeCoverConditionCoverageHelper_C30;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((i < size) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[16]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[17]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[18]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[79]++;
        Reference ref = references.get(i);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[80]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((ref.isLvalue()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ref.isInitializingDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[61]++;
          return false;

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[62]++;}
      }
      return true;
    }

    boolean firstReferenceIsAssigningDeclaration() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[81]++;
      int size = references.size();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[82]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((size > 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((references.get(0).isInitializingDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[63]++;
        return true;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[64]++;}
      return false;
    }
  }

  /**
   * Represents a single declaration or reference to a variable.
   */
  static final class Reference implements StaticReference<JSType> {

    private static final Set<Integer> DECLARATION_PARENTS =
        ImmutableSet.of(Token.VAR, Token.FUNCTION, Token.CATCH);

    private final Node nameNode;
    private final BasicBlock basicBlock;
    private final Scope scope;
    private final InputId inputId;
    private final StaticSourceFile sourceFile;

    Reference(Node nameNode, NodeTraversal t,
        BasicBlock basicBlock) {
      this(nameNode, basicBlock, t.getScope(), t.getInput().getInputId());
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[83]++;
    }

    // Bleeding functions are weird, because the declaration does
    // not appear inside their scope. So they need their own constructor.
    static Reference newBleedingFunction(NodeTraversal t,
        BasicBlock basicBlock, Node func) {
      return new Reference(func.getFirstChild(),
          basicBlock, t.getScope(), t.getInput().getInputId());
    }

    /**
     * Creates a variable reference in a given script file name, used in tests.
     *
     * @return The created reference.
     */
    @VisibleForTesting
    static Reference createRefForTest(CompilerInput input) {
      return new Reference(new Node(Token.NAME), null, null,
          input.getInputId());
    }

    private Reference(Node nameNode,
        BasicBlock basicBlock, Scope scope, InputId inputId) {
      this.nameNode = nameNode;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[84]++;
      this.basicBlock = basicBlock;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[85]++;
      this.scope = scope;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[86]++;
      this.inputId = inputId;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[87]++;
      this.sourceFile = nameNode.getStaticSourceFile();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[88]++;
    }

    /**
     * Makes a copy of the current reference using a new Scope instance.
     */
    Reference cloneWithNewScope(Scope newScope) {
      return new Reference(nameNode, basicBlock, newScope, inputId);
    }

    @Override
    public Var getSymbol() {
      return scope.getVar(nameNode.getString());
    }

    @Override
    public Node getNode() {
      return nameNode;
    }

    public InputId getInputId() {
      return inputId;
    }

    @Override
    public StaticSourceFile getSourceFile() {
      return sourceFile;
    }

    boolean isDeclaration() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[89]++;
      Node parent = getParent();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[90]++;
      Node grandparent = parent.getParent();
      return DECLARATION_PARENTS.contains(parent.getType()) ||
          parent.isParamList() &&
          grandparent.isFunction();
    }

    boolean isVarDeclaration() {
      return getParent().isVar();
    }

    boolean isHoistedFunction() {
      return NodeUtil.isHoistedFunctionDeclaration(getParent());
    }

    /**
     * Determines whether the variable is initialized at the declaration.
     */
    boolean isInitializingDeclaration() {
      // VAR is the only type of variable declaration that may not initialize
      // its variable. Catch blocks, named functions, and parameters all do.
      return isDeclaration() &&
          !getParent().isVar() ||
          nameNode.getFirstChild() != null;
    }

   /**
    * @return For an assignment, variable declaration, or function declaration
    * return the assigned value, otherwise null.
    */
    Node getAssignedValue() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[91]++;
      Node parent = getParent();
      return (parent.isFunction())
          ? parent : NodeUtil.getAssignedValue(nameNode);
    }

    BasicBlock getBasicBlock() {
      return basicBlock;
    }

    Node getParent() {
      return getNode().getParent();
    }

    Node getGrandparent() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[92]++;
      Node parent = getParent();
      return parent == null ? null : parent.getParent();
    }

    private static boolean isLhsOfForInExpression(Node n) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[93]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[94]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[65]++;
        return isLhsOfForInExpression(parent);

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[66]++;}
      return NodeUtil.isForIn(parent) && parent.getFirstChild() == n;
    }

    boolean isSimpleAssignmentToName() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[95]++;
      Node parent = getParent();
      return parent.isAssign()
          && parent.getFirstChild() == nameNode;
    }

    boolean isLvalue() {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[96]++;
      Node parent = getParent();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[97]++;
      int parentType = parent.getType();
      return (parentType == Token.VAR && nameNode.getFirstChild() != null)
          || parentType == Token.INC
          || parentType == Token.DEC
          || (NodeUtil.isAssignmentOp(parent)
              && parent.getFirstChild() == nameNode)
          || isLhsOfForInExpression(nameNode);
    }

    Scope getScope() {
      return scope;
    }
  }

  /**
   * Represents a section of code that is uninterrupted by control structures
   * (conditional or iterative logic).
   */
  static final class BasicBlock {

    private final BasicBlock parent;

    /**
     * Determines whether the block may not be part of the normal control flow,
     * but instead "hoisted" to the top of the scope.
     */
    private final boolean isHoisted;

    /**
     * Whether this block denotes a function scope.
     */
    private final boolean isFunction;

    /**
     * Whether this block denotes a loop.
     */
    private final boolean isLoop;

    /**
     * Creates a new block.
     * @param parent The containing block.
     * @param root The root node of the block.
     */
    BasicBlock(BasicBlock parent, Node root) {
      this.parent = parent;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[98]++;

      // only named functions may be hoisted.
      this.isHoisted = NodeUtil.isHoistedFunctionDeclaration(root);
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[99]++;

      this.isFunction = root.isFunction();
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[100]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[101]++;
int CodeCoverConditionCoverageHelper_C34;

      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((root.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[67]++;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[102]++;
        int pType = root.getParent().getType();
        this.isLoop = pType == Token.DO ||
            pType == Token.WHILE ||
            pType == Token.FOR;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[103]++;

      } else {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[68]++;
        this.isLoop = false;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[104]++;
      }
    }

    BasicBlock getParent() {
      return parent;
    }

    /**
     * Determines whether this block is equivalent to the very first block that
     * is created when reference collection traversal enters global scope. Note
     * that when traversing a single script in a hot-swap fashion a new instance
     * of {@code BasicBlock} is created.
     *
     * @return true if this is global scope block.
     */
    boolean isGlobalScopeBlock() {
      return getParent() == null;
    }

    /**
     * Determines whether this block is guaranteed to begin executing before
     * the given block does.
     */
    boolean provablyExecutesBefore(BasicBlock thatBlock) {
      // If thatBlock is a descendant of this block, and there are no hoisted
      // blocks between them, then this block must start before thatBlock.
      BasicBlock currentBlock;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[105]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[19]++;


int CodeCoverConditionCoverageHelper_C35;
      for (currentBlock = thatBlock;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((currentBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((currentBlock != this) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false);
           currentBlock = currentBlock.getParent()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[19]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[20]--;
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.loops[21]++;
}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[106]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((currentBlock.isHoisted) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[69]++;
          return false;

        } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[70]++;}
      }
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[107]++;
int CodeCoverConditionCoverageHelper_C37;

      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((currentBlock == this) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[71]++;
        return true;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[72]++;}
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.statements[108]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((isGlobalScopeBlock()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((thatBlock.isGlobalScopeBlock()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[73]++;
        return true;

      } else {
  CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p.branches[74]++;}
      return false;
    }
  }
}

class CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p ());
  }
    public static long[] statements = new long[109];
    public static long[] branches = new long[75];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReferenceCollectingCallback.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,2,1,1,2,1,1,2};
    for (int i = 1; i <= 38; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$21xic8kiqphujrkvm1j8h3566uumvao9aqhzrsq9sy6kc3mo2p () {
    super("com.google.javascript.jscomp.ReferenceCollectingCallback.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 108; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 74; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ReferenceCollectingCallback.java");
      for (int i = 1; i <= 108; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 74; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

