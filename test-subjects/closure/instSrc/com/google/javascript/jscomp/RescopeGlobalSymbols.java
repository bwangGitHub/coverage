/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.common.collect.ImmutableSet;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowStatementCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Finds all references to global symbols and rewrites them to be property
 * accesses to a special object with the same name as the global symbol.
 *
 * Given the name of the global object is NS
 * <pre> var a = 1; function b() { return a }</pre>
 * becomes
 * <pre> NS.a = 1; NS.b = function b() { return NS.a }</pre>
 *
 * This allows splitting code into modules that depend on each other's
 * global symbols, without using polluting JavaScript's global scope with those
 * symbols.
 *
 * <p>This compile step requires moveFunctionDeclarations to be turned on
 * to guarantee semantics.
 *
 * <p>For lots of examples, see the unit test.
 *
 *
 */
class RescopeGlobalSymbols implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.ping();
  }


  // Appended to variables names that conflict with globalSymbolNamespace.
  private static final String DISAMBIGUATION_SUFFIX = "$";
  static {
    CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[1]++;
  }
  private static final String WINDOW = "window";
  static {
    CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[2]++;
  }
  private static final Set<String> SPECIAL_EXTERNS =
      ImmutableSet.of(WINDOW, "eval", "arguments");
  static {
    CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[3]++;
  }

  private final AbstractCompiler compiler;
  private final String globalSymbolNamespace;
  private final boolean addExtern;

  RescopeGlobalSymbols(AbstractCompiler compiler, String globalSymbolNamespace,
      boolean addExtern) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[4]++;
    this.globalSymbolNamespace = globalSymbolNamespace;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[5]++;
    this.addExtern = addExtern;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[6]++;
  }

  RescopeGlobalSymbols(AbstractCompiler compiler,
      String globalSymbolNamespace) {
    this(compiler, globalSymbolNamespace, true);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[7]++;
  }

  private void addExternForGlobalSymbolNamespace() {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[8]++;
    Node varNode = IR.var(IR.name(globalSymbolNamespace));
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[9]++;
    CompilerInput input = compiler.newExternInput(
        "{RescopeGlobalSymbolsNamespaceVar}");
    input.getAstRoot(compiler).addChildrenToBack(varNode);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[10]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[11]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    // Make the name of the globalSymbolNamespace an extern.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((addExtern) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[1]++;
      addExternForGlobalSymbolNamespace();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[13]++;

    } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[2]++;}
    // Rewrite all references to global symbols to properties of a
    // single symbol by:
    // (If necessary the 3 traversals could be combined. They are left
    // separate for readability reasons.)
    // 1. turning global named function statements into var assignments.
    NodeTraversal.traverse(compiler, root,
        new RewriteGlobalFunctionStatementsToVarAssignmentsCallback());
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[14]++;
    // 2. rewriting all references to be property accesses of the single symbol.
    NodeTraversal.traverse(compiler, root, new RewriteScopeCallback());
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[15]++;
    // 3. removing the var from every statement in global scope.
    NodeTraversal.traverse(compiler, root, new RemoveGlobalVarCallback());
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[16]++;

    // Extra pass which makes all extern global symbols reference window
    // explicitly.
    NodeTraversal.traverse(compiler, root,
        new MakeExternsReferenceWindowExplicitly());
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[17]++;
  }

  /**
   * Rewrites function statements to var statements + assignment.
   *
   * <pre>function test(){}</pre>
   * becomes
   * <pre>var test = function (){}</pre>
   *
   * After this traversal, the special case of global function statements
   * can be ignored.
   */
  private class RewriteGlobalFunctionStatementsToVarAssignmentsCallback
      extends AbstractShallowStatementCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[3]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[19]++;
        String name = NodeUtil.getFunctionName(n);
        n.getFirstChild().setString("");
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[20]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[21]++;
        Node prev = parent.getChildBefore(n);
        n.detachFromParent();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[22]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[23]++;
        Node var = NodeUtil.newVarNode(name, n);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((prev == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[5]++;
          parent.addChildToFront(var);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[25]++;

        } else {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[6]++;
          parent.addChildAfter(var, prev);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[26]++;
        }
        compiler.reportCodeChange();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[27]++;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[4]++;}
    }
  }

  /**
   * Visits each NAME token and checks whether it refers to a global variable.
   * If yes, rewrites the name to be a property access on the
   * "globalSymbolNamespace".
   *
   * <pre>var a = 1, b = 2, c = 3;</pre>
   * becomes
   * <pre>var NS.a = 1, NS.b = 2, NS.c = 4</pre>
   * (The var token is removed in a later traversal.)
   *
   * <pre>a + b</pre>
   * becomes
   * <pre>NS.a + NS.b</pre>
   *
   * <pre>a()</pre>
   * becomes
   * <pre>(0,NS.a)()</pre>
   * Notice the special syntax here to preserve the *this* semantics in the
   * function call.
   */
  private class RewriteScopeCallback extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[7]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[8]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[29]++;
      String name = n.getString();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
      // Ignore anonymous functions
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[9]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[10]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[31]++;
      Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[12]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
      // Don't touch externs.
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((var.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[13]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[14]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
      // When the globalSymbolNamespace is used as a local variable name
      // add suffix to avoid shadowing the namespace. Also add a suffix
      // if a name starts with the name of the globalSymbolNamespace and
      // the suffix.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((var.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((name.equals(globalSymbolNamespace)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((name.indexOf(globalSymbolNamespace + DISAMBIGUATION_SUFFIX) == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[15]++;
        n.setString(name + DISAMBIGUATION_SUFFIX);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[35]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[36]++;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[16]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
      // We only care about global vars.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[17]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[18]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[38]++;
      Node nameNode = var.getNameNode();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
      // The exception variable (e in try{}catch(e){}) should not be rewritten.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((nameNode != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((nameNode.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nameNode.getParent().isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[19]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[20]++;}
      replaceSymbol(n, name);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[40]++;
    }

    private void replaceSymbol(Node node, String name) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[41]++;
      Node parent = node.getParent();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[42]++;
      Node replacement = IR.getprop(
          IR.name(globalSymbolNamespace).srcref(node),
          IR.string(name).srcref(node));
      replacement.srcref(node);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[43]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((node.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[21]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[45]++;
        // var declaration list: var a = 1, b = 2;
        Node assign = IR.assign(replacement,
            node.removeFirstChild());
        parent.replaceChild(node, assign);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[46]++;

      } else {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[22]++;
        parent.replaceChild(node, replacement);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[47]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[48]++;
    }
  }

  /**
   * Removes every occurrence of var that declares a global variable.
   *
   * <pre>var NS.a = 1, NS.b = 2;</pre>
   * becomes
   * <pre>NS.a = 1; NS.b = 2;</pre>
   *
   * <pre>for (var a = 0, b = 0;;)</pre>
   * becomes
   * <pre>for (NS.a = 0, NS.b = 0;;)</pre>
   *
   * Declarations without assignments are optimized away:
   * <pre>var a = 1, b;</pre>
   * becomes
   * <pre>NS.a = 1</pre>
   */
  private class RemoveGlobalVarCallback extends
      AbstractShallowStatementCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[23]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[24]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[50]++;
      List<Node> commas = new ArrayList<Node>();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[51]++;
      List<Node> interestingChildren = new ArrayList<Node>();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[52]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[1]++;


      // Filter out declarations without assignments.
      // As opposed to regular var nodes, there are always assignments
      // because the previous traversal in RewriteScopeCallback creates
      // them.
      for (Node c : n.children()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[1]--;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[2]--;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[3]++;
}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[53]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((c.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[25]++;
          interestingChildren.add(c);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[54]++;

        } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[26]++;}
      }
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[55]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[4]++;


      for (Node c : interestingChildren) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[4]--;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[5]--;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[6]++;
}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[27]++;
          commas.add(c.cloneTree());
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[57]++;

        } else {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[28]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[58]++;
          // Var statement outside of for-loop.
          Node expr = IR.exprResult(c.cloneTree()).srcref(c);
          parent.addChildBefore(expr, n);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[59]++;
        }
      }
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[60]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((commas.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[29]++;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[61]++;
        Node comma = joinOnComma(commas, n);
        parent.addChildBefore(comma, n);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[62]++;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[30]++;}
      // Remove the var node.
      parent.removeChild(n);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[63]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[64]++;
    }

    private Node joinOnComma(List<Node> commas, Node source) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[65]++;
      Node comma = commas.get(0);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[66]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[7]++;


int CodeCoverConditionCoverageHelper_C16;
      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < commas.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[7]--;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[8]--;
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.loops[9]++;
}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[67]++;
        Node nextComma = IR.comma(comma, commas.get(i));
        nextComma.copyInformationFrom(source);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[68]++;
        comma = nextComma;
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[69]++;
      }
      return comma;
    }
  }

  /**
   * Rewrites extern names to be explicit children of window instead of only
   * implicitly referencing it.
   * This enables injecting window into a scope and make all global symbol
   * depend on the injected object.
   */
  private class MakeExternsReferenceWindowExplicitly extends
      AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[31]++;
        return;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[32]++;}
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[71]++;
      String name = n.getString();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[72]++;
      Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (512)) == 0 || true) &&
 ((name.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (256)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C18 |= (128)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((var.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((globalSymbolNamespace.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((SPECIAL_EXTERNS.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 5) || true)) || (CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 5) && false)) {
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[33]++;
        parent.replaceChild(n, IR.getprop(IR.name(WINDOW), IR.string(name))
            .srcrefTree(n));
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[74]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.statements[75]++;

      } else {
  CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl.branches[34]++;}
    }

  }
}

class CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl ());
  }
    public static long[] statements = new long[76];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RescopeGlobalSymbols.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,3,1,3,1,1,2,2,1,1,1,3};
    for (int i = 1; i <= 18; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$3r1kmjvr1hcfsms137a88sjdgorv783v1v977fl () {
    super("com.google.javascript.jscomp.RescopeGlobalSymbols.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 75; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RescopeGlobalSymbols.java");
      for (int i = 1; i <= 75; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

