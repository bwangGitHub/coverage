/*
 * Copyright 2004 The Closure Compiler Authors.
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
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * NodeTraversal allows an iteration through the nodes in the parse tree,
 * and facilitates the optimizations on the parse tree.
 *
 */
public class NodeTraversal {
  static {
    CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.ping();
  }

  // Package protected for tests
  private final AbstractCompiler compiler;
  private final Callback callback;

  /** Contains the current node*/
  private Node curNode;

  public static final DiagnosticType NODE_TRAVERSAL_ERROR =
      DiagnosticType.error("JSC_NODE_TRAVERSAL_ERROR", "{0}");
  static {
    CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[1]++;
  }

  /**
   * Stack containing the Scopes that have been created. The Scope objects
   * are lazily created; so the {@code scopeRoots} stack contains the
   * Nodes for all Scopes that have not been created yet.
   */
  private final Deque<Scope> scopes = new ArrayDeque<Scope>();
  {
    CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[2]++;
  }

  /**
   * A stack of scope roots. All scopes that have not been created
   * are represented in this Deque.
   */
  private final Deque<Node> scopeRoots = new ArrayDeque<Node>();
  {
    CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[3]++;
  }


  /**
   * Stack of control flow graphs (CFG). There is one CFG per scope. CFGs
   * are lazily populated: elements are {@code null} until requested by
   * {@link #getControlFlowGraph()}. Note that {@link ArrayDeque} does not allow
   * {@code null} elements, so {@link LinkedList} is used instead.
   */
  Deque<ControlFlowGraph<Node>> cfgs = new LinkedList<ControlFlowGraph<Node>>();
  {
    CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[4]++;
  }

  /** The current source file name */
  private String sourceName;

  /** The current input */
  private InputId inputId;

  /** The scope creator */
  private ScopeCreator scopeCreator;

  /** Possible callback for scope entry and exist **/
  private ScopedCallback scopeCallback;

  /**
   * Callback
   */
  public interface Callback {
    /**
     * <p>Visits a node in pre order (before visiting its children) and decides
     * whether this node's children should be traversed. If children are
     * traversed, they will be visited by
     * {@link #visit(NodeTraversal, Node, Node)} in post order.</p>
     * <p>Implementations can have side effects (e.g. modifying the parse
     * tree).</p>
     * @return whether the children of this node should be visited
     */
    boolean shouldTraverse(NodeTraversal nodeTraversal, Node n, Node parent);

    /**
     * <p>Visits a node in post order (after its children have been visited).
     * A node is visited only if all its parents should be traversed
     * ({@link #shouldTraverse(NodeTraversal, Node, Node)}).</p>
     * <p>Implementations can have side effects (e.g. modifying the parse
     * tree).</p>
     */
    void visit(NodeTraversal t, Node n, Node parent);
  }

  /**
   * Callback that also knows about scope changes
   */
  public interface ScopedCallback extends Callback {

    /**
     * Called immediately after entering a new scope. The new scope can
     * be accessed through t.getScope()
     */
    void enterScope(NodeTraversal t);

    /**
     * Called immediately before exiting a scope. The ending scope can
     * be accessed through t.getScope()
     */
    void exitScope(NodeTraversal t);
  }

  /**
   * Abstract callback to visit all nodes in post order.
   */
  public abstract static class AbstractPostOrderCallback implements Callback {
    @Override
    public final boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
      return true;
    }
  }

  /**
   * Abstract scoped callback to visit all nodes in post order.
   */
  public abstract static class AbstractScopedCallback
      implements ScopedCallback {
    @Override
    public final boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
      return true;
    }

    @Override
    public void enterScope(NodeTraversal t) {}

    @Override
    public void exitScope(NodeTraversal t) {}
  }

  /**
   * Abstract callback to visit all nodes but not traverse into function
   * bodies.
   */
  public abstract static class AbstractShallowCallback implements Callback {
    @Override
    public final boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
      // We do want to traverse the name of a named function, but we don't
      // want to traverse the arguments or body.
      return parent == null || !parent.isFunction() ||
          n == parent.getFirstChild();
    }
  }

  /**
   * Abstract callback to visit all structure and statement nodes but doesn't
   * traverse into functions or expressions.
   */
  public abstract static class AbstractShallowStatementCallback
      implements Callback {
    @Override
    public final boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
      return parent == null || NodeUtil.isControlStructure(parent)
         || NodeUtil.isStatementBlock(parent);
    }
  }

  /**
   * Abstract callback to visit a pruned set of nodes.
   */
  public abstract static class AbstractNodeTypePruningCallback
        implements Callback {
    private final Set<Integer> nodeTypes;
    private final boolean include;

    /**
     * Creates an abstract pruned callback.
     * @param nodeTypes the nodes to include in the traversal
     */
    public AbstractNodeTypePruningCallback(Set<Integer> nodeTypes) {
      this(nodeTypes, true);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[5]++;
    }

    /**
     * Creates an abstract pruned callback.
     * @param nodeTypes the nodes to include/exclude in the traversal
     * @param include whether to include or exclude the nodes in the traversal
     */
    public AbstractNodeTypePruningCallback(Set<Integer> nodeTypes,
          boolean include) {
      this.nodeTypes = nodeTypes;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[6]++;
      this.include = include;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[7]++;
    }

    @Override
    public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
      return include == nodeTypes.contains(n.getType());
    }
  }

  /**
   * Creates a node traversal using the specified callback interface.
   */
  public NodeTraversal(AbstractCompiler compiler, Callback cb) {
    this(compiler, cb, new SyntacticScopeCreator(compiler));
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[8]++;
  }

  /**
   * Creates a node traversal using the specified callback interface
   * and the scope creator.
   */
  public NodeTraversal(AbstractCompiler compiler, Callback cb,
      ScopeCreator scopeCreator) {
    this.callback = cb;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[9]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cb instanceof ScopedCallback) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[1]++;
      this.scopeCallback = (ScopedCallback) cb;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[11]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[2]++;}
    this.compiler = compiler;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[12]++;
    this.inputId = null;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[13]++;
    this.sourceName = "";
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[14]++;
    this.scopeCreator = scopeCreator;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[15]++;
  }

  private void throwUnexpectedException(Exception unexpectedException) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[16]++;
    // If there's an unexpected exception, try to get the
    // line number of the code that caused it.
    String message = unexpectedException.getMessage();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;

    // TODO(user): It is possible to get more information if curNode or
    // its parent is missing. We still have the scope stack in which it is still
    // very useful to find out at least which function caused the exception.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((inputId != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[3]++;
      message =
          unexpectedException.getMessage() + "\n" +
          formatNodeContext("Node", curNode) +
          (curNode == null ?
              "" :
              formatNodeContext("Parent", curNode.getParent()));
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[18]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[4]++;}
    compiler.throwInternalError(message, unexpectedException);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[19]++;
  }

  private String formatNodeContext(String label, Node n) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[5]++;
      return "  " + label + ": NULL";

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[6]++;}
    return "  " + label + "(" + n.toString(false, false, false) + "): "
        + formatNodePosition(n);
  }

  /**
   * Traverses a parse tree recursively.
   */
  public void traverse(Node root) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[21]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      inputId = NodeUtil.getInputId(root);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[22]++;
      sourceName = "";
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[23]++;
      curNode = root;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[24]++;
      pushScope(root);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[25]++;
      traverseBranch(root, null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[26]++;
      popScope();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[27]++;
    } catch (Exception unexpectedException) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[8]++;
      throwUnexpectedException(unexpectedException);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[28]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[7]++;
}
  }
  }

  public void traverseRoots(Node ... roots) {
    traverseRoots(Lists.newArrayList(roots));
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[29]++;
  }

  public void traverseRoots(List<Node> roots) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((roots.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[9]++;
      return;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[10]++;}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[31]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

    try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[32]++;
      Node scopeRoot = roots.get(0).getParent();
      Preconditions.checkState(scopeRoot != null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[33]++;

      inputId = NodeUtil.getInputId(scopeRoot);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[34]++;
      sourceName = "";
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[35]++;
      curNode = scopeRoot;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[36]++;
      pushScope(scopeRoot);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[37]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[38]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[1]++;



      for (Node root : roots) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[1]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[2]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[3]++;
}
        Preconditions.checkState(root.getParent() == scopeRoot);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[39]++;
        traverseBranch(root, scopeRoot);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[40]++;
      }

      popScope();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[41]++;
    } catch (Exception unexpectedException) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[12]++;
      throwUnexpectedException(unexpectedException);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[42]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[11]++;
}
  }
  }

  private static final String MISSING_SOURCE = "[source unknown]";
  static {
    CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[43]++;
  }

  private String formatNodePosition(Node n) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[44]++;
    String sourceFileName = getBestSourceFileName(n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[45]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((sourceFileName == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[13]++;
      return MISSING_SOURCE + "\n";

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[14]++;}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[46]++;

    int lineNumber = n.getLineno();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[47]++;
    int columnNumber = n.getCharno();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[48]++;
    String src = compiler.getSourceLine(sourceFileName, lineNumber);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[49]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((src == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[15]++;
      src = MISSING_SOURCE;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[50]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[16]++;}
    return sourceFileName + ":" + lineNumber + ":" + columnNumber + "\n"
        + src + "\n";
  }

  /**
   * Traverses a parse tree recursively with a scope, starting with the given
   * root. This should only be used in the global scope. Otherwise, use
   * {@link #traverseAtScope}.
   */
  void traverseWithScope(Node root, Scope s) {
    Preconditions.checkState(s.isGlobal());
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[51]++;

    inputId = null;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[52]++;
    sourceName = "";
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[53]++;
    curNode = root;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[54]++;
    pushScope(s);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[55]++;
    traverseBranch(root, null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[56]++;
    popScope();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[57]++;
  }

  /**
   * Traverses a parse tree recursively with a scope, starting at that scope's
   * root.
   */
  void traverseAtScope(Scope s) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[58]++;
    Node n = s.getRootNode();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[59]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[17]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[60]++;
int CodeCoverConditionCoverageHelper_C8;
      // We need to do some extra magic to make sure that the scope doesn't
      // get re-created when we dive into the function.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((inputId == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[19]++;
        inputId = NodeUtil.getInputId(n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[61]++;

      } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[20]++;}
      sourceName = getSourceName(n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[62]++;
      curNode = n;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[63]++;
      pushScope(s);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[64]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[65]++;

      Node args = n.getFirstChild().getNext();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[66]++;
      Node body = args.getNext();
      traverseBranch(args, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[67]++;
      traverseBranch(body, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[68]++;

      popScope();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[69]++;

    } else {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[18]++;
      traverseWithScope(n, s);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[70]++;
    }
  }

  /**
   * Traverses an inner node recursively with a refined scope. An inner node may
   * be any node with a non {@code null} parent (i.e. all nodes except the
   * root).
   *
   * @param node the node to traverse
   * @param parent the node's parent, it may not be {@code null}
   * @param refinedScope the refined scope of the scope currently at the top of
   *     the scope stack or in trivial cases that very scope or {@code null}
   */
  protected void traverseInnerNode(Node node, Node parent, Scope refinedScope) {
    Preconditions.checkNotNull(parent);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[71]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[72]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((refinedScope != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getScope() != refinedScope) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[21]++;
      curNode = node;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[73]++;
      pushScope(refinedScope);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[74]++;
      traverseBranch(node, parent);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[75]++;
      popScope();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[76]++;

    } else {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[22]++;
      traverseBranch(node, parent);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[77]++;
    }
  }

  /**
   * Gets the compiler.
   */
  public Compiler getCompiler() {
    // TODO(nicksantos): Remove this type cast. This is just temporary
    // while refactoring.
    return (Compiler) compiler;
  }

  /**
   * Gets the current line number, or zero if it cannot be determined. The line
   * number is retrieved lazily as a running time optimization.
   */
  public int getLineNumber() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[78]++;
    Node cur = curNode;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[79]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
    while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[4]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[5]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[6]++;
}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[80]++;
      int line = cur.getLineno();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[81]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((line >=0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[23]++;
        return line;

      } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[24]++;}
      cur = cur.getParent();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[82]++;
    }
    return 0;
  }

  /**
   * Gets the current input source name.
   *
   * @return A string that may be empty, but not null
   */
  public String getSourceName() {
    return sourceName;
  }

  /**
   * Gets the current input source.
   */
  public CompilerInput getInput() {
    return compiler.getInput(inputId);
  }

  /**
   * Gets the current input module.
   */
  public JSModule getModule() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[83]++;
    CompilerInput input = getInput();
    return input == null ? null : input.getModule();
  }

  /** Returns the node currently being traversed. */
  public Node getCurrentNode() {
    return curNode;
  }

  /**
   * Traverses a node recursively.
   */
  public static void traverse(
      AbstractCompiler compiler, Node root, Callback cb) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[84]++;
    NodeTraversal t = new NodeTraversal(compiler, cb);
    t.traverse(root);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[85]++;
  }

  /**
   * Traverses a list of node trees.
   */
  public static void traverseRoots(
      AbstractCompiler compiler, List<Node> roots, Callback cb) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[86]++;
    NodeTraversal t = new NodeTraversal(compiler, cb);
    t.traverseRoots(roots);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[87]++;
  }

  public static void traverseRoots(
      AbstractCompiler compiler, Callback cb, Node ... roots) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[88]++;
    NodeTraversal t = new NodeTraversal(compiler, cb);
    t.traverseRoots(roots);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[89]++;
  }

  /**
   * Traverses a branch.
   */
  @SuppressWarnings("fallthrough")
  private void traverseBranch(Node n, Node parent) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[90]++;
    int type = n.getType();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[91]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[25]++;
      inputId = n.getInputId();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[92]++;
      sourceName = getSourceName(n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[93]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[26]++;}

    curNode = n;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[94]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[95]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((callback.shouldTraverse(this, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[27]++; return;
} else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[28]++;}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[96]++;

    switch (type) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[29]++;
        traverseFunction(n, parent);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[97]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[98]++;
        break;

      default:
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[30]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[99]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); ) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[7]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[8]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[9]++;
}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[100]++;
          // child could be replaced, in which case our child node
          // would no longer point to the true next
          Node next = child.getNext();
          traverseBranch(child, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[101]++;
          child = next;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[102]++;
        }
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[103]++;
        break;
    }

    curNode = n;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[104]++;
    callback.visit(this, n, parent);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[105]++;
  }

  /**
   * Traverses a function.
   */
  private void traverseFunction(Node n, Node parent) {
    Preconditions.checkState(n.getChildCount() == 3);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[106]++;
    Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[107]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[108]++;

    final Node fnName = n.getFirstChild();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[109]++;

    boolean isFunctionExpression = (parent != null)
        && NodeUtil.isFunctionExpression(n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[110]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isFunctionExpression) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[31]++;
      // Functions declarations are in the scope containing the declaration.
      traverseBranch(fnName, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[111]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[32]++;}

    curNode = n;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[112]++;
    pushScope(n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[113]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[114]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isFunctionExpression) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[33]++;
      // Function expression names are only accessible within the function
      // scope.
      traverseBranch(fnName, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[115]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[34]++;}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[116]++;

    final Node args = fnName.getNext();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[117]++;
    final Node body = args.getNext();

    // Args
    traverseBranch(args, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[118]++;

    // Body
    Preconditions.checkState(body.getNext() == null &&
            body.isBlock(), body);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[119]++;
    traverseBranch(body, n);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[120]++;

    popScope();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[121]++;
  }

  /** Examines the functions stack for the last instance of a function node. */
  @SuppressWarnings("unchecked")
  public Node getEnclosingFunction() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[122]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((scopes.size() + scopeRoots.size() < 2) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[35]++;
      return null;

    } else {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[36]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[123]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((scopeRoots.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[37]++;
        return scopes.peek().getRootNode();

      } else {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[38]++;
        return scopeRoots.peek();
      }
    }
  }

  /** Creates a new scope (e.g. when entering a function). */
  private void pushScope(Node node) {
    Preconditions.checkState(curNode != null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[124]++;
    scopeRoots.push(node);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[125]++;
    cfgs.push(null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[126]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[127]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((scopeCallback != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[39]++;
      scopeCallback.enterScope(this);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[128]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[40]++;}
  }

  /** Creates a new scope (e.g. when entering a function). */
  private void pushScope(Scope s) {
    Preconditions.checkState(curNode != null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[129]++;
    scopes.push(s);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[130]++;
    cfgs.push(null);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[131]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[132]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((scopeCallback != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[41]++;
      scopeCallback.enterScope(this);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[133]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[42]++;}
  }

  /** Pops back to the previous scope (e.g. when leaving a function). */
  private void popScope() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[134]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((scopeCallback != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[43]++;
      scopeCallback.exitScope(this);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[135]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[44]++;}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[136]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((scopeRoots.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[45]++;
      scopes.pop();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[137]++;

    } else {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[46]++;
      scopeRoots.pop();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[138]++;
    }
    cfgs.pop();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[139]++;
  }

  /** Gets the current scope. */
  public Scope getScope() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[140]++;
    Scope scope = scopes.isEmpty() ? null : scopes.peek();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[141]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((scopeRoots.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[47]++;
      return scope;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[48]++;}
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[142]++;

    Iterator<Node> it = scopeRoots.descendingIterator();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[143]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[10]++;


int CodeCoverConditionCoverageHelper_C24;
    while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[10]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[11]--;
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.loops[12]++;
}
      scope = scopeCreator.createScope(it.next(), scope);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[144]++;
      scopes.push(scope);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[145]++;
    }
    scopeRoots.clear();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[146]++;

    return scope;
  }

  /** Gets the control flow graph for the current JS scope. */
  public ControlFlowGraph<Node> getControlFlowGraph() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[147]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((cfgs.peek() == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[49]++;
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[148]++;
      ControlFlowAnalysis cfa = new ControlFlowAnalysis(compiler, false, true);
      cfa.process(null, getScopeRoot());
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[149]++;
      cfgs.pop();
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[150]++;
      cfgs.push(cfa.getCfg());
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[151]++;

    } else {
  CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[50]++;}
    return cfgs.peek();
  }

  /** Returns the current scope's root. */
  public Node getScopeRoot() {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[152]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((scopeRoots.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[51]++;
      return scopes.peek().getRootNode();

    } else {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.branches[52]++;
      return scopeRoots.peek();
    }
  }

  /**
   * Determines whether the traversal is currently in the global scope.
   */
  boolean inGlobalScope() {
    return getScopeDepth() <= 1;
  }

  int getScopeDepth() {
    return scopes.size() + scopeRoots.size();
  }

  public boolean hasScope() {
    return !(scopes.isEmpty() && scopeRoots.isEmpty());
  }

  /** Reports a diagnostic (error or warning) */
  public void report(Node n, DiagnosticType diagnosticType,
      String... arguments) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[153]++;
    JSError error = JSError.make(getBestSourceFileName(n),
        n, diagnosticType, arguments);
    compiler.report(error);
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[154]++;
  }

  private static String getSourceName(Node n) {
CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt.statements[155]++;
    String name = n.getSourceFileName();
    return name == null ? "" : name;
  }

  InputId getInputId() {
    return inputId;
  }

  /**
   * Creates a JSError during NodeTraversal.
   *
   * @param n Determines the line and char position within the source file name
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public JSError makeError(Node n, CheckLevel level, DiagnosticType type,
      String... arguments) {
    return JSError.make(getBestSourceFileName(n), n, level, type, arguments);
  }

  /**
   * Creates a JSError during NodeTraversal.
   *
   * @param n Determines the line and char position within the source file name
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public JSError makeError(Node n, DiagnosticType type, String... arguments) {
    return JSError.make(getBestSourceFileName(n), n, type, arguments);
  }

  private String getBestSourceFileName(Node n) {
    return n == null ? sourceName : n.getSourceFileName();
  }
}

class CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt ());
  }
    public static long[] statements = new long[156];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NodeTraversal.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$6itbn7vumm9xxsyyq4d3zqtrakpt () {
    super("com.google.javascript.jscomp.NodeTraversal.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 155; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NodeTraversal.java");
      for (int i = 1; i <= 155; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

