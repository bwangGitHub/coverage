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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.NameReferenceGraph.Name;
import com.google.javascript.jscomp.NameReferenceGraph.Reference;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Nullable;

/**
 * Constructs a name reference graph.
 *
 * @see NameReferenceGraph
 *
 */
class NameReferenceGraphConstruction implements CompilerPass {
  static {
    CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.ping();
  }


  private final AbstractCompiler compiler;
  private final NameReferenceGraph graph;

  // Maps "foo" -> (curFuncName, unknownObject.foo) if we have no idea what
  // the unknown object is. After we finish one pass, we must go through all
  // the nodes that might have a name foo and connect that to the curFuncName.
  // The accuracy of the analysis will depend heavily on eliminating the need
  // to resort to this map.
  private final Multimap<String, NameUse> unknownNameUse =
      HashMultimap.create();
  {
    CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[1]++;
  }

  // Should we continue even if we found a type checker bug.
  private static final boolean CONSERVATIVE = false;
  static {
    CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[2]++;
  }

  // The symbol for the current function so we can quickly create a reference
  // edge when we see a call: Example when this symbol is foo() and we see
  // bar(), we connect foo -> bar.
  private final ArrayList<Name> currentFunctionStack = new ArrayList<Name>();
  {
    CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[3]++;
  }

  NameReferenceGraphConstruction(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[4]++;
    this.graph = new NameReferenceGraph(compiler);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[5]++;
  }

  NameReferenceGraph getNameReferenceGraph() {
    return this.graph;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[6]++;
    // Use the MemoizedScopeCreator instance from TypeCheck if available
    // as FunctionTypeBuilder warns about existing types if TypedScopeCreator is
    // ran a second time.
    ScopeCreator scopeCreator = compiler.getTypedScopeCreator();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scopeCreator == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[1]++;
      // The TypedScopeCreator gives us correct handling of namespaces,
      // while the default NodeTraversal only gives us a
      // SyntacticScopeCreator.
      scopeCreator = new MemoizedScopeCreator(new TypedScopeCreator(compiler));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[8]++;

    } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[2]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[9]++;
    NodeTraversal externsTraversal = new NodeTraversal(compiler,
        new Traversal(true), scopeCreator);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[10]++;
    NodeTraversal codeTraversal = new NodeTraversal(compiler,
        new Traversal(false), scopeCreator);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[11]++;
    Scope topScope = compiler.getTopScope();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((topScope != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[3]++;
      externsTraversal.traverseWithScope(externs, topScope);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[13]++;
      codeTraversal.traverseWithScope(root, topScope);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[14]++;

    } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[4]++;
      externsTraversal.traverse(externs);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[15]++;
      codeTraversal.traverse(root);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[16]++;
    }
    connectUnknowns();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[17]++;
  }

  private class Traversal implements ScopedCallback {

    final boolean isExtern;

    private Traversal(boolean isExtern) {
      this.isExtern = isExtern;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[18]++;
      pushContainingFunction(graph.MAIN);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[19]++;
    }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[20]++;
      Node root = t.getScopeRoot();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[21]++;
      Node parent = root.getParent();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;

      // When we are not in a {{GLOBAL MAIN}}, we need to determine what the
      // current function is.
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[5]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[23]++;

        // TODO(user): A global function foo() is treated as the same
        // function as a inner function named foo(). We should use some clever
        // naming scheme to avoid this lost of precision.
        String name = NodeUtil.getFunctionName(root);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[7]++;
          // When the name is null, we have a function that is presumably not
          // reference-able again and should not be modeled in the name graph.
          // A common example would be (function() { ... })();
          pushContainingFunction(graph.UNKNOWN);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[25]++;
          return;

        } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[8]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[26]++;

        // If we've done type analysis, then we should be able to get the
        // correct JSFunctionType for the containing function.  If not,
        // we're probably going to get an unknown type here.
        JSType type = getType(root);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isPrototypeProperty(parent.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[9]++;
          pushContainingFunction(
              recordPrototypePropDefinition(parent.getFirstChild(), type, parent));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[28]++;

        } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[10]++;
          pushContainingFunction(
              recordStaticNameDefinition(
                name, type, root, root.getLastChild()));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[29]++;
        }

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[6]++;}
    }

    @Override
    public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[11]++;
        popContainingFunction();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[31]++;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[12]++;}
    }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      return true;
    }

    @SuppressWarnings("fallthrough")
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[32]++;
      switch (n.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[13]++;
        case Token.GETPROP:
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[14]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[15]++;
            // We will resolve this when we visit parent later in the traversal.
            return;

          } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[16]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[34]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[17]++;
            // Function declarations have been taken care of in enterScope();
            return;

          } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[18]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[35]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[19]++;
            // Handled below.
            return;

          } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[20]++;}
}
}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;

          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isLocalNameReference(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[21]++;
            // Ignore all local variable references unless is creates a closure.
            return;

          } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[22]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;

          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isPrototypeNameReference(n)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[23]++;
            recordPrototypePropUse(n, parent);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[38]++;

          } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[24]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[39]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isStaticNameReference(n, t.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[25]++;
            recordStaticNameUse(n, parent);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[40]++;

          } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[26]++;
            recordUnknownUse(n, parent);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[41]++;
          }
}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[42]++;
          break;

        case Token.ASSIGN:
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[27]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[43]++;
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[44]++;
          Node rhs = n.getLastChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[45]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((rhs.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[28]++;
            // These are recorded when entering the scope.
            return;

          } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[29]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[46]++;
int CodeCoverConditionCoverageHelper_C14;
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((rhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[30]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[47]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isPrototypeProperty(lhs)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[32]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[48]++;
              Name name = recordPrototypePropDefinition(
                  lhs, getType(rhs), n);
              name.setAliased(true);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[49]++;

            } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[33]++;}

          } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[31]++;}
          maybeAliasNamesOnAssign(lhs, rhs);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[50]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[51]++;
          break;

        case Token.VAR:
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[34]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[52]++;
          // var foo = bar;
          Node varName = n.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[53]++;
          Node assignedValue = varName.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[54]++;
int CodeCoverConditionCoverageHelper_C16;
          if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((assignedValue == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[35]++;
            return;

          } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[36]++;}
          maybeAliasNamesOnAssign(varName, assignedValue);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[55]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[56]++;
          break;

        case Token.CALL:
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[37]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[57]++;
          Node param = n.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[58]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[1]++;


          // We need to alias every name that is passed as a parameter because
          // they have different names inside the function's scope.
          while ((param = param.getNext()) != null) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[1]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[2]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[3]++;
}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[59]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((param.isName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((param.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[38]++;
              safeAlias(param);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[60]++;

            } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[39]++;}
          }

          maybeRecordExport(n);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[61]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[62]++;
          break; default : CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[40]++;
      }
    }

    private boolean containsName(Node n) {
      return NodeUtil.containsType(n, Token.NAME) ||
          NodeUtil.containsType(n, Token.GETELEM) ||
          NodeUtil.containsType(n, Token.GETPROP);
    }

    /**
     * Given a node, this alias all the names in the node that need aliasing.
     * This is safer than just calling getQualifiedName() because it can return
     * null it several situations.
     * @param n node to alias
     */
    private void safeAlias(Node n) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[63]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[41]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[64]++;
        String name = n.getQualifiedName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[65]++;
int CodeCoverConditionCoverageHelper_C20;
        // getQualifiedName can return null in cases like bar[0].baz
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[43]++;
          defineAndAlias(name);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[66]++;
          return;

        } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[44]++;}

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[42]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[67]++;
int CodeCoverConditionCoverageHelper_C21;

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[45]++;
        // var foo = bar[0].baz;
        defineAndAlias(n.getLastChild().getString());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[68]++;

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[46]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[69]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[47]++;
        // In case of nested assignment, we only consider the name of the
        // immediate neighbor.
        safeAlias(n.getFirstChild());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[70]++;

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[48]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[71]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[49]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[72]++;
        Node cur = n.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[73]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[4]++;


        do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[4]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[5]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[6]++;
}
          safeAlias(cur);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[74]++;
        } while ((cur = cur.getNext()) != null);

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[50]++;
        // No name to alias
      }
}
}
    }

    private void maybeAliasNamesOnAssign(Node lhs, Node rhs) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[75]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (512)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (128)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (64)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((containsName(rhs)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((rhs.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((rhs.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 5) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 5) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[51]++;
        safeAlias(lhs);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[76]++;
        safeAlias(rhs);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[77]++;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[52]++;}
    }

    private void defineAndAlias(String name) {
      graph.defineNameIfNotExists(name, isExtern).setAliased(true);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[78]++;
    }

    private void maybeRecordExport(Node call) {
      Preconditions.checkArgument(call.isCall());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[79]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[80]++;
      Node getProp = call.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[81]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((getProp.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[53]++;
        return;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[54]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[82]++;

      String propQName = getProp.getQualifiedName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[83]++;
int CodeCoverConditionCoverageHelper_C27;

      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((propQName == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[55]++;
        return;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[56]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[84]++;
int CodeCoverConditionCoverageHelper_C28;

      // Keep track of calls to "call" and "apply" because they mess up the name
      // graph.
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((propQName.endsWith(".call")) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((propQName.endsWith(".apply")) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[57]++;
        graph.defineNameIfNotExists(getProp.getFirstChild().getQualifiedName(),
            isExtern).markExposedToCallOrApply();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[85]++;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[58]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[86]++;
int CodeCoverConditionCoverageHelper_C29;

      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 (("goog.exportSymbol".equals(propQName)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[59]++;
        return;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[60]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[87]++;

      Node symbol = getProp.getNext();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[88]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((symbol.isString()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[61]++;
        return;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[62]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[89]++;

      Node obj = symbol.getNext();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[90]++;
      String qName = obj.getQualifiedName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[91]++;
int CodeCoverConditionCoverageHelper_C31;

      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((qName == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((obj.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[63]++;
        return;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[64]++;}

      graph.defineNameIfNotExists(qName, false).markExported();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[92]++;
    }

    /**
     * @return true if n MUST be a local name reference.
     */
    private boolean isLocalNameReference(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[93]++;
int CodeCoverConditionCoverageHelper_C32;
      // TODO(user): What happen if it is a reference to an outer local
      // variable (closures)?
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[65]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[94]++;
        Var v = t.getScope().getVar(n.getString());
        return v != null && v.isLocal();

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[66]++;}
      return false;
    }

    /**
     * @return true if n MUST be a static name reference.
     */
    private boolean isStaticNameReference(Node n, Scope scope) {
      Preconditions.checkArgument(n.isName() || n.isGetProp());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[95]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[96]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[67]++;
        return true;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[68]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[97]++;
      String qName = n.getQualifiedName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[98]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((qName == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[69]++;
        return false;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[70]++;}
      // TODO(user): This does not always work due to type system bugs.
      return scope.isDeclared(qName, true);
    }

    /**
     * @return true if n MUST be a prototype name reference.
     */
    private boolean isPrototypeNameReference(Node n) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[99]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[71]++;
        return false;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[72]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[100]++;
      JSType type = getType(n.getFirstChild());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[101]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[73]++;
        return false;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[74]++;}
      return (type.isInstanceType() || type.autoboxesTo() != null);
    }

    private Name recordStaticNameDefinition(String name, JSType type,
        Node n, Node rValue) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[102]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((getNamedContainingFunction() != graph.MAIN) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[75]++;

        // TODO(user): if A.B() defines A.C(), there is a dependence from
        // A.C() -> A.B(). However, this is not important in module code motion
        // and will be ignored (for now).
      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[76]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[103]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((type.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[77]++;
        return recordClassConstructorOrInterface(
            name, type.toMaybeFunctionType(),
            n, rValue);

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[78]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[104]++;
        Name symbol = graph.defineNameIfNotExists(name, isExtern);
        symbol.setType(type);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[105]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[106]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[79]++;
          symbol.addAssignmentDeclaration(n);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[107]++;

        } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[80]++;
          symbol.addFunctionDeclaration(n);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[108]++;
        }
        return symbol;
      }
    }

    /**
     * @param assign The assignment node, null if it is just a "forward"
     *     declaration for recording the rValue's type.
     */
    private Name recordPrototypePropDefinition(
        Node qName, JSType type, @Nullable Node assign) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[109]++;
      JSType constructor = getType(NodeUtil.getPrototypeClassName(qName));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[110]++;
      FunctionType classType = null;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[111]++;
      String className = null;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[112]++;
int CodeCoverConditionCoverageHelper_C40;

      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((constructor.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[81]++;
        // Case where the class has been properly declared with @constructor
        classType = constructor.toMaybeFunctionType();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[113]++;
        className = classType.getReferenceName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[114]++;

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[82]++;
        // We'll guess it is a constructor even if it didn't have @constructor
        classType = compiler.getTypeRegistry().getNativeFunctionType(
            JSTypeNative.U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[115]++;
        className = NodeUtil.getPrototypeClassName(qName).getQualifiedName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[116]++;
      }
      // In case we haven't seen the function yet.
      recordClassConstructorOrInterface(
          className, classType, null, null);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[117]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[118]++;

      String qNameStr = className + ".prototype." +
          NodeUtil.getPrototypePropertyName(qName);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[119]++;
      Name prototypeProp = graph.defineNameIfNotExists(qNameStr, isExtern);
      Preconditions.checkNotNull(prototypeProp,
          "%s should be in the name graph as a node.", qNameStr);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[120]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[121]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((assign != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[83]++;
        prototypeProp.addAssignmentDeclaration(assign);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[122]++;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[84]++;}
      prototypeProp.setType(type);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[123]++;
      return prototypeProp;
    }

    private Reference recordStaticNameUse(
        Node n, Node parent) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[124]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[85]++;
        // Don't count reference in extern as a use.
        return null;

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[86]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[125]++;
        Reference reference = new Reference(n, parent);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[126]++;
        Name name = graph.defineNameIfNotExists(n.getQualifiedName(), isExtern);
        name.setType(getType(n));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[127]++;
        graph.connect(getNamedContainingFunction(), reference, name);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[128]++;
        return reference;
      }
    }

    private void recordPrototypePropUse(Node n, Node parent) {
      Preconditions.checkArgument(n.isGetProp());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[129]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[130]++;
      Node instance = n.getFirstChild();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[131]++;
      JSType instanceType = getType(instance);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[132]++;
      JSType boxedType = instanceType.autoboxesTo();
      instanceType = boxedType != null ? boxedType : instanceType;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[133]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[134]++;

      // Retrieves the property.
      ObjectType objType = instanceType.toObjectType();
      Preconditions.checkState(objType != null);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[135]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[136]++;
int CodeCoverConditionCoverageHelper_C43;

      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[87]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[137]++;
        // Don't count reference in extern as a use.
        Reference ref = new Reference(n, parent);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[138]++;

        FunctionType constructor = objType.getConstructor();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[139]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[89]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[140]++;
          String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[141]++;
int CodeCoverConditionCoverageHelper_C45;
          if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((constructor.getPrototype().hasOwnProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[91]++;
            recordSuperClassPrototypePropUse(constructor, propName, ref);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[142]++;

          } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[92]++;}

          // TODO(user): TightenType can help a whole lot here.
          recordSubclassPrototypePropUse(constructor, propName, ref);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[143]++;

        } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[90]++;
          recordUnknownUse(n, parent);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[144]++;
        }

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[88]++;}
    }

    /**
     * Look for the super class implementation up the tree.
     */
    private void recordSuperClassPrototypePropUse(
        FunctionType classType, String prop, Reference ref) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[145]++;
      FunctionType superClass = classType.getSuperClassConstructor();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[146]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[7]++;


int CodeCoverConditionCoverageHelper_C46;
      while ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((superClass != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[7]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[8]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[9]++;
}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[147]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((superClass.getPrototype().hasOwnProperty(prop)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[93]++;
          graph.connect(getNamedContainingFunction(), ref,
              graph.defineNameIfNotExists(
                 superClass.getReferenceName() + ".prototype." + prop, false));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[148]++;
          return;

        } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[94]++;
          superClass = superClass.getSuperClassConstructor();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[149]++;
        }
      }
    }

    /**
     * Conservatively assumes that all subclass implementation of this property
     * might be called.
     */
    private void recordSubclassPrototypePropUse(
        FunctionType classType, String prop, Reference ref) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[150]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((classType.getPrototype().hasOwnProperty(prop)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[95]++;
        graph.connect(getNamedContainingFunction(), ref,
           graph.defineNameIfNotExists(
               classType.getReferenceName() + ".prototype." + prop, false));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[151]++;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[96]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[152]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((classType.getSubTypes() != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[97]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[153]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[10]++;


        for (FunctionType subclass : classType.getSubTypes()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[10]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[11]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[12]++;
}
            recordSubclassPrototypePropUse(subclass, prop, ref);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[154]++;
        }

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[98]++;}
    }

    private void recordUnknownUse(Node n, Node parent) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[155]++;
int CodeCoverConditionCoverageHelper_C50;
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[99]++;
        // Don't count reference in extern as a use.
        return;

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[100]++;
        Preconditions.checkArgument(n.isGetProp());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[156]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[157]++;
        Reference ref = new Reference(n, parent);
        ref.setUnknown(true);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[158]++;
        unknownNameUse.put(n.getLastChild().getString(),
            new NameUse(getNamedContainingFunction(), ref));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[159]++;
      }
    }

    /**
     * Creates the name in the graph if it does not already exist. Also puts all
     * the properties and prototype properties of this name in the graph.
     */
    private Name recordClassConstructorOrInterface(
        String name, FunctionType type, @Nullable Node n, @Nullable Node rhs) {
      Preconditions.checkArgument(type.isConstructor() || type.isInterface());
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[160]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[161]++;
      Name symbol = graph.defineNameIfNotExists(name, isExtern);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[162]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((rhs != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[101]++;
        // TODO(user): record the definition.
        symbol.setType(getType(rhs));
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[163]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[164]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[103]++;
          symbol.addAssignmentDeclaration(n);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[165]++;

        } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[104]++;
          symbol.addFunctionDeclaration(n);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[166]++;
        }

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[102]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[167]++;
      ObjectType prototype = type.getPrototype();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[168]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[13]++;


      for (String prop : prototype.getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[13]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[14]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[15]++;
}
        graph.defineNameIfNotExists(
            name + ".prototype." + prop, isExtern);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[169]++;
      }
      return symbol;
    }
  }

  private void connectUnknowns() {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[170]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[16]++;


    for (GraphNode<Name, Reference> node : graph.getNodes()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[16]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[17]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[18]++;
}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[171]++;
      Name name = node.getValue();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[172]++;
      String propName = name.getPropertyName();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[173]++;
int CodeCoverConditionCoverageHelper_C53;
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((propName == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[105]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[174]++;
        continue;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[106]++;}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[175]++;
      Collection<NameUse> uses = unknownNameUse.get(propName);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[176]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((uses != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[107]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[177]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[19]++;


        for (NameUse use : uses) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[19]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[20]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[21]++;
}
          graph.connect(use.name, use.reference, name);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[178]++;
        }

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[108]++;}
    }
  }

  /**
   * A helper to retrieve the type of a node.
   */
  private JSType getType(Node n) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[179]++;
    JSType type = n.getJSType();
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[180]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[109]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[181]++;
int CodeCoverConditionCoverageHelper_C56;
      if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((CONSERVATIVE) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[111]++;
        throw new RuntimeException("Type system failed us :(");

      } else {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[112]++;
        return compiler.getTypeRegistry().getNativeType(
            JSTypeNative.UNKNOWN_TYPE);
      }

    } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[110]++;}
    // Null-ability does not affect the name graph's result.
    return type.restrictByNotNullOrUndefined();
  }

  /**
   * Mark the provided node as the current function that we are analyzing.
   * and add it to the stack of scopes we are inside.
   *
   * @param functionNode node representing current function.
   */
  private void pushContainingFunction(Name functionNode) {
    currentFunctionStack.add(functionNode);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[182]++;
  }

  /**
   * Remove the top item off the containing function stack, and restore the
   * previous containing scope to the be the current containing function.
   */
  private void popContainingFunction() {
    currentFunctionStack.remove(currentFunctionStack.size() - 1);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[183]++;
  }

  /**
   * Find the first containing function that's not an function expression
   * closure.
   */
  private Name getNamedContainingFunction() {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[184]++;
    Name containingFn = null;
    int pos;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[185]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[22]++;


int CodeCoverConditionCoverageHelper_C57;
    for (pos = currentFunctionStack.size() - 1;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((pos >= 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); pos = pos - 1) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[22]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[23]--;
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.loops[24]++;
}
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[186]++;
      Name cf = currentFunctionStack.get(pos);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[187]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((cf != graph.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[113]++;
        containingFn = cf;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[188]++;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[189]++;
        break;

      } else {
  CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.branches[114]++;}
    }
    Preconditions.checkNotNull(containingFn);
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[190]++;
    return containingFn;
  }

  private static class NameUse {
    private final Name name;
    private final Reference reference;

    private NameUse(Name name, Reference reference) {
      this.name = name;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[191]++;
      this.reference = reference;
CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929.statements[192]++;
    }
  }
}

class CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929 ());
  }
    public static long[] statements = new long[193];
    public static long[] branches = new long[115];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[59];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NameReferenceGraphConstruction.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,1,3,1,1,0,2,2,1,1,1,1,0,3,1,1,2,1,1,2,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 58; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$jihgx8m9cvfvx896widga7r0ice9woqyuivvoth8inw7ueabcaa929 () {
    super("com.google.javascript.jscomp.NameReferenceGraphConstruction.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 192; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 114; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 58; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameReferenceGraphConstruction.java");
      for (int i = 1; i <= 192; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 114; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 58; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

