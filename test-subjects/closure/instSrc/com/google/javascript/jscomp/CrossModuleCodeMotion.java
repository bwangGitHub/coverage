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
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A {@link Compiler} pass for moving code to a deeper module if possible.
 * - currently it only moves functions + variables
 *
 */
class CrossModuleCodeMotion extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.ping();
  }


  private static final Logger logger =
      Logger.getLogger(CrossModuleCodeMotion.class.getName());
  static {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final JSModuleGraph graph;

  /**
   * Map from module to the node in that module that should parent any string
   * variable declarations that have to be moved into that module
   */
  private final Map<JSModule, Node> moduleVarParentMap =
      new HashMap<JSModule, Node>();
  {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[2]++;
  }

  /*
   * NOTE - I made this a LinkedHashMap to make testing easier. With a regular
   * HashMap, the variables may not output in a consistent order
   */
  private final Map<Scope.Var, NamedInfo> namedInfo =
      new LinkedHashMap<Var, NamedInfo>();
  {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[3]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The compiler
   */
  CrossModuleCodeMotion(AbstractCompiler compiler, JSModuleGraph graph) {
    this.compiler = compiler;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[4]++;
    this.graph = graph;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
    logger.fine("Moving functions + variable into deeper modules");
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[6]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

    // If there are <2 modules, then we will never move anything, so we're done
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((graph != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((graph.getModuleCount() > 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[1]++;

      // Traverse the tree and find the modules where a var is declared + used
      NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[8]++;

      // Move the functions + variables to a deeper module [if possible]
      moveCode();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[9]++;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[2]++;}
  }

  /** move the code accordingly */
  private void moveCode() {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[1]++;


    for (NamedInfo info : namedInfo.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[1]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[2]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[3]++;
}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[11]++;
      JSModule deepestDependency = info.deepestModule;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;

      // Only move if all are true:
      // a) allowMove is true
      // b) it was used + declared somewhere [if not, then it will be removed
      // as dead or invalid code elsewhere]
      // c) the new dependency depends on the declModule
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((info.allowMove) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((deepestDependency != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[3]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[13]++;
        Iterator<Declaration> it = info.declarationIterator();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[14]++;
        JSModuleGraph moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
        while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[4]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[5]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[6]++;
}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[16]++;
          Declaration decl = it.next();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((decl.module != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(deepestDependency,
                  decl.module)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[5]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[18]++;

            // Find the appropriate spot to move it to
            Node destParent = moduleVarParentMap.get(deepestDependency);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((destParent == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[7]++;
              destParent = compiler.getNodeForCodeInsertion(deepestDependency);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[20]++;
              moduleVarParentMap.put(deepestDependency, destParent);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[21]++;

            } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[8]++;}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[22]++;

            // VAR Nodes are normalized to have only one child.
            Node declParent = decl.node.getParent();
            Preconditions.checkState(
                !declParent.isVar() || declParent.hasOneChild(),
                "AST not normalized.");
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[23]++;

            // Remove it
            declParent.detachFromParent();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[24]++;

            // Add it to the new spot
            destParent.addChildToFront(declParent);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[25]++;

            compiler.reportCodeChange();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[26]++;

          } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[6]++;}
        }

      } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[4]++;}
    }
  }

  /** useful information for each variable candidate */
  private class NamedInfo {
    boolean allowMove = true;
  {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[27]++;
  }

    // The deepest module where the variable is used. Starts at null
    private JSModule deepestModule = null;
  {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[28]++;
  }

    // The module where declarations appear
    private JSModule declModule = null;
  {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[29]++;
  }

    // information on the spot where the item was declared
    private final Deque<Declaration> declarations =
        new ArrayDeque<Declaration>();
  {
    CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[30]++;
  }

    // Add a Module where it is used
    void addUsedModule(JSModule m) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
      // If we are not allowed to move it, all bets are off
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((allowMove) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[9]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[10]++;}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;

      // If we have no deepest module yet, set this one
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((deepestModule == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[11]++;
        deepestModule = m;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[33]++;

      } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[12]++;
        // Find the deepest common dependency
        deepestModule =
            graph.getDeepestCommonDependencyInclusive(m, deepestModule);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[34]++;
      }
    }

    /**
     * Add a declaration for this name.
     * @return Whether this is a valid declaration. If this returns false,
     *    this should be added as a reference.
     */
    boolean addDeclaration(Declaration d) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
      // all declarations must appear in the same module.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((declModule != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((d.module != declModule) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[13]++;
        return false;

      } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[14]++;}
      declarations.push(d);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[36]++;
      declModule = d.module;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[37]++;
      return true;
    }

    /**
     * Returns an iterator over the declarations, in the order that they were
     * declared.
     */
    Iterator<Declaration> declarationIterator() {
      return declarations.iterator();
    }
  }

  private class Declaration {
    final JSModule module;
    final Node node;

    Declaration(JSModule module, Node node) {
      this.module = module;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[38]++;
      this.node = node;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[39]++;
    }
  }

  /**
   * return true if the node has any form of conditional in its ancestry
   * TODO(nicksantos) keep track of the conditionals in the ancestry, so
   * that we don't have to recrawl it.
   */
  private boolean hasConditionalAncestor(Node n) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[40]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[7]++;


    for (Node ancestor : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[7]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[8]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[9]++;
}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[41]++;
      switch (ancestor.getType()) {
        case Token.DO:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[15]++;
        case Token.FOR:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[16]++;
        case Token.HOOK:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[17]++;
        case Token.IF:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[18]++;
        case Token.SWITCH:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[19]++;
        case Token.WHILE:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[20]++;
        case Token.FUNCTION:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[21]++;
          return true; default : CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[22]++;
      }
    }
    return false;
  }

  /**
   * get the information on a variable
   */
  private NamedInfo getNamedInfo(Var v) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[42]++;
    NamedInfo info = namedInfo.get(v);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[23]++;
      info = new NamedInfo();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[44]++;
      namedInfo.put(v, info);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[45]++;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[24]++;}
    return info;
  }

  /**
   * Process the references to named variables
   */
  private void processReference(NodeTraversal t, NamedInfo info, String name) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[46]++;
    // A name is recursively defined if:
    //   1: It is calling itself.
    //   2: One of its property calls itself.
    // Recursive definition should not block movement.

    boolean recursive = false;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[47]++;
    Node rootNode = t.getScope().getRootNode();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((rootNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[25]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[49]++;

      // CASE #1:
      String scopeFuncName = rootNode.getFirstChild().getString();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[50]++;
      Node scopeFuncParent = rootNode.getParent();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((scopeFuncName.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[27]++;
        recursive = true;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[52]++;

      } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[28]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[53]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((scopeFuncParent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((scopeFuncParent.getString().equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[29]++;
        recursive = true;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[54]++;

      } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[30]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[55]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
        // CASE #2:
        // Suppose name is Foo, we keep look up the scope stack to look for
        // a scope with "Foo.prototype.bar = function() { ..... "
        for (Scope s = t.getScope();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((s.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); s = s.getParent()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[10]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[11]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[12]++;
}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[56]++;
          Node curRoot = s.getRootNode();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[57]++;
int CodeCoverConditionCoverageHelper_C14;
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((curRoot.getParent().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[31]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[58]++;
            Node owner = curRoot.getParent().getFirstChild();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[59]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
            while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((owner.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[13]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[14]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[15]++;
}
              owner = owner.getFirstChild();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[60]++;
            }
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((owner.isName()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((owner.getString().equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[33]++;
              recursive = true;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[62]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[63]++;
              break;

            } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[34]++;}

          } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[32]++;}
        }
      }
}

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[26]++;}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[64]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((recursive) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[35]++;
      info.addUsedModule(t.getModule());
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[65]++;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[36]++;}
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[66]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[37]++;
      return;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[38]++;}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[67]++;

    // Skip empty and exported names
    String name = n.getString();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(name)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[39]++;
      return;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[40]++;}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[69]++;

    // If the JSCompiler can't find a Var for this string, then all
    // bets are off. This sometimes occurs with closures. Alternately, we skip
    // non-global variables
    Var v = t.getScope().getVar(name);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[70]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((v.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[41]++;
      return;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[42]++;}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[71]++;

    NamedInfo info = getNamedInfo(v);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((info.allowMove) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[43]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[73]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((maybeProcessDeclaration(t, n, parent, info)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[45]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;
        // Check to see if the declaration is conditional starting at the
        // grandparent of the name node. Since a function declaration
        // is considered conditional (the function might not be called)
        // we would need to skip the parent in this check as the name could
        // just be a function itself.
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((hasConditionalAncestor(parent.getParent())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[47]++;
          info.allowMove = false;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[75]++;

        } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[48]++;}

      } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[46]++;
        // Otherwise, it's a reference
        processReference(t, info, name);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[76]++;
      }

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[44]++;}
  }

  /**
   * Determines whether the given NAME node belongs to a declaration that
   * can be moved across modules. If it is, registers it properly.
   *
   * There are four types of movable declarations:
   * 1) var NAME = [movable object];
   * 2) function NAME() {}
   * 3) NAME = [movable object];
   *    NAME.prop = [movable object];
   *    NAME.prop.prop2 = [movable object];
   *    etc.
   * 4) Class-defining function calls, like "inherits" and "mixin".
   *    NAME.inherits([some other name]);
   * where "movable object" is a literal or a function.
   */
  private boolean maybeProcessDeclaration(NodeTraversal t, Node name,
      Node parent, NamedInfo info) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[77]++;
    Node gramps = parent.getParent();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[78]++;
    switch (parent.getType()) {
      case Token.VAR:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[49]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[79]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((canMoveValue(name.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[50]++;
          return info.addDeclaration(
              new Declaration(t.getModule(), name));

        } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[51]++;}
        return false;

      case Token.FUNCTION:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[52]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[80]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(parent)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[53]++;
          return info.addDeclaration(
              new Declaration(t.getModule(), name));

        } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[54]++;}
        return false;

      case Token.ASSIGN:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[55]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[56]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[81]++;
        Node child = name;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[82]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[16]++;



        // Look for assignment expressions where the name is the root
        // of a qualified name on the left hand side of the assignment.
        for (Node current : name.getAncestors()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[16]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[17]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[18]++;
}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[83]++;
int CodeCoverConditionCoverageHelper_C26;
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((current.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[57]++;

            // fallthrough
          } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[58]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[84]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((current.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((current.getFirstChild() == child) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[59]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[85]++;
            Node currentParent = current.getParent();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[86]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((currentParent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((canMoveValue(current.getLastChild())) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[61]++;
              return info.addDeclaration(
                  new Declaration(t.getModule(), current));

            } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[62]++;}

          } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[60]++;
            return false;
          }
}

          child = current;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[87]++;
        }
        return false;

      case Token.CALL:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[63]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[88]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprCall(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[64]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[89]++;
          SubclassRelationship relationship =
              compiler.getCodingConvention().getClassesDefinedByCall(parent);
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[90]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((relationship != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((name.getString().equals(relationship.subclassName)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[66]++;
            return info.addDeclaration(
                new Declaration(t.getModule(), parent));

          } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[67]++;}

        } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[65]++;}
        return false;

      default:
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[68]++;
        return false;
    }
  }

  /**
   * Determines whether the given value is eligible to be moved across modules.
   */
  private boolean canMoveValue(Node n) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[91]++;
int CodeCoverConditionCoverageHelper_C31;
    // the value is only movable if it's
    // a) nothing,
    // b) a constant literal,
    // c) a function, or
    // d) an array/object literal of movable values.
    // e) a function stub generated by CrossModuleMethodMotion.
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(n, true)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[69]++;
      return true;

    } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[70]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[92]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[71]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[93]++;
      Node functionName = n.getFirstChild();
      return functionName.isName() &&
          (functionName.getString().equals(
              CrossModuleMethodMotion.STUB_METHOD_NAME) ||
           functionName.getString().equals(
              CrossModuleMethodMotion.UNSTUB_METHOD_NAME));

    } else {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[72]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[94]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((n.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[73]++;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[95]++;
      boolean isObjectLit = n.isObjectLit();
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[96]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[19]++;


int CodeCoverConditionCoverageHelper_C34;
      for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false);
           child = child.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[19]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[20]--;
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.loops[21]++;
}
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.statements[97]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((canMoveValue(isObjectLit ? child.getFirstChild() : child)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[75]++;
          return false;

        } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[76]++;}
      }

      return true;

    } else {
  CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5.branches[74]++;}
}
}

    return false;
  }
}

class CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5 ());
  }
    public static long[] statements = new long[98];
    public static long[] branches = new long[77];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[36];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CrossModuleCodeMotion.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,2,2,1,1,1,1,1,1,2,2,1,2,3,1,2,1,1};
    for (int i = 1; i <= 35; i++) {
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

  public CodeCoverCoverageCounter$lu2vv41nchy3p2kv6gl1ztr9fkiu8oqitb8ec2n5 () {
    super("com.google.javascript.jscomp.CrossModuleCodeMotion.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 97; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 76; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 35; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CrossModuleCodeMotion.java");
      for (int i = 1; i <= 97; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 76; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 35; i++) {
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

