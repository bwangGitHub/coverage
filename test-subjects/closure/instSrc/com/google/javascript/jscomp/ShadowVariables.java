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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.RenameVars.Assignment;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;

/**
 * Tries to compute a list of variables that can shadow a variable in the
 * outer scope.
 *
 * For example:
 *
 * <code>
 * var a = function() {
 *   var b = getB();
 *   b();
 *   return function(y) {};
 * };
 * </code>
 *
 * Normally, b would be mapped to variable L0, y would be L1.
 *
 * Instead we are going to make y shadows L0 in hope of using less variables
 * and reusing frequently used local names.
 *
 */
class ShadowVariables implements CompilerPass {
  static {
    CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.ping();
  }


  // Keep a map of Upward Referencing name nodes of each scope.
  // A name is upward referencing name of a scope if:
  //
  // 1) It refers to (or defines) a name that is defined in the current
  // scope or any scope above the current scope that isn't the
  // global scope.
  //
  // 2) It is a upward referencing name of a child scope of this scope.
  //
  // Example:
  // var x; var y; function foo(a) { function bar(b) { x, a } }
  // The upward referencing names in scope 'foo' is bar, b, x and a;
  // The key to this map is the root node of the scope.
  //
  // We can see that for any variable x in the current scope, we can shadow
  // a variable y in an outer scope given that y is not a upward referencing
  // name of the current scope.

  // TODO(user): Maps scope to string instead of Node to string.
  // Make sure of scope memorization to minimize scope creation cost.
  private final Multimap<Node, String> scopeUpRefMap = HashMultimap.create();
  {
    CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[1]++;
  }

  // Maps all local Scope.Var to all of its referencing NAME node
  // in any scope.
  private final Multimap<Var, Node> varToNameUsage = HashMultimap.create();
  {
    CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[2]++;
  }

  private final AbstractCompiler compiler;

  // All the information used for renaming.
  private final SortedSet<Assignment> varsByFrequency;
  private final Map<String, Assignment> assignments;
  private final Map<Node, String> oldPseudoNameMap;
  private final Map<Node, String> deltaPseudoNameMap;


  /**
   * @param assignments Map of old variable names to its assignment Objects.
   * @param varsByFrequency Sorted variable assignments by Frequency.
   * @param pseudoNameMap The current pseudo name map so this pass can update
   *     it accordingly.
   */
  ShadowVariables(
      AbstractCompiler compiler,
      Map<String, Assignment> assignments,
      SortedSet<Assignment> varsByFrequency,
      Map<Node, String> pseudoNameMap) {
    this.compiler = compiler;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[3]++;
    this.assignments = assignments;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[4]++;
    this.varsByFrequency = varsByFrequency;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[5]++;
    this.oldPseudoNameMap = pseudoNameMap;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[6]++;
    this.deltaPseudoNameMap = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[7]++;
  }

  @Override
  public void process(Node externs, Node root) {

    // The algorithm is divided into two stages:
    //
    // 1. Information gathering (variable usage, upward referencing)
    //
    // 2. Tries to find shadows for each variables, updates the
    //    variable usage frequency map.
    //
    // 3. Updates the pseudo naming map if needed.
    NodeTraversal.traverse(compiler, root, new GatherReferenceInfo());
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[8]++;
    NodeTraversal.traverse(compiler, root, new DoShadowVariables());
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[9]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((oldPseudoNameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[1]++;
      oldPseudoNameMap.putAll(deltaPseudoNameMap);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[11]++;

    } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[2]++;}
  }

  private class GatherReferenceInfo extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
      // Skipping over non-name nodes and empty function names.
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isReferenceName(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[3]++;
        return;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[4]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;

      // We focus on shadowing local variables as their name occurs much more
      // than global names.
      // TODO(user): Alternatively, we could experiment with using a local
      // name to shadow a global variable.
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[5]++;
        return;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[6]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[14]++;

      Var var = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[7]++;
        // extern name or undefined name.
        return;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[8]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((var.getScope().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[9]++;
        // We will not shadow a global variable name.
        return;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[10]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;

      // Using the definition of upward referencing, fill in the map.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((var.getScope() != t.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[11]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
        for (Scope s = t.getScope();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((s != var.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((s.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false); s = s.getParent()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[1]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[2]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[3]++;
}
          scopeUpRefMap.put(s.getRootNode(), var.name);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[19]++;
        }

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[12]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((var.getScope() == t.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[13]++;
        scopeUpRefMap.put(t.getScopeRoot(), var.name);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[21]++;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[14]++;}

      // Find in the usage map that tracks a var and all of its usage.
      varToNameUsage.put(var, n);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[22]++;
    }
  }

  private class DoShadowVariables extends AbstractPostOrderCallback
      implements ScopedCallback {

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[23]++;
      Scope s = t.getScope();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((s.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[15]++;
        return;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[16]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;

      // Since we don't shadow global, there is nothing to be done in the
      // first immediate local scope as well.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((s.getParent().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[17]++;
        return;

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[18]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;

      for (Iterator<Var> vars = s.getVars();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((vars.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[4]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[5]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[6]++;
}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[27]++;
        Var var = vars.next();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[28]++;
int CodeCoverConditionCoverageHelper_C12;

        // Don't shadow variables that is bleed-out to fix an IE bug.
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((var.isBleedingFunction()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[19]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[29]++;
          continue;

        } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[20]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[30]++;
int CodeCoverConditionCoverageHelper_C13;

        // Don't shadow an exported local.
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(var.name, s.isLocal())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[21]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[31]++;
          continue;

        } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[22]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[32]++;

        // Try to look for the best shadow for the current candidate.
        Assignment bestShadow = findBestShadow(s);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[33]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((bestShadow == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[23]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[34]++;
          continue;

        } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[24]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[35]++;

        // The name assignment being shadowed.
        Assignment localAssignment = assignments.get(var.getName());
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[36]++;
int CodeCoverConditionCoverageHelper_C15;

        // Only shadow if this increases the number of occurrences of the
        // shadowed variable.
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((bestShadow.count < localAssignment.count) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[25]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[37]++;
          continue;
 // Hope the next local variable would have a smaller count.
        } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[26]++;}

        doShadow(localAssignment, bestShadow, var);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[38]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[39]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((oldPseudoNameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[27]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[40]++;
          String targetPseudoName =
            oldPseudoNameMap.get(s.getVar(bestShadow.oldName).nameNode);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[41]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[7]++;


          for (Node use : varToNameUsage.get(var)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[7]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[8]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[9]++;
}
            deltaPseudoNameMap.put(use, targetPseudoName);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[42]++;
          }

        } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[28]++;}
      }
    }

    @Override
    public void exitScope(NodeTraversal t) {}

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {}

    /**
     * @returns An assignment that can be used as a shadow for a local variable
     *     in the scope defined by curScopeRoot.
     */
    private Assignment findBestShadow(Scope curScope) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[43]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[10]++;


      // Search for the candidate starting from the most used local.
      for (Assignment assignment : varsByFrequency) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[10]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[11]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[12]++;
}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[44]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((assignment.oldName.startsWith(RenameVars.LOCAL_VAR_PREFIX)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[29]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[45]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((scopeUpRefMap.get(curScope.getRootNode()).contains(
              assignment.oldName)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[31]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[46]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((curScope.isDeclared(assignment.oldName, true)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[33]++;
              return assignment;

            } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[34]++;}

          } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[32]++;}

        } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[30]++;}
      }
      return null;
    }

    private void doShadow(Assignment original, Assignment toShadow, Var var) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[47]++;
      Scope s = var.getScope();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[48]++;
      // We are now shadowing 'bestShadow' with localAssignment.
      // All of the reference NAME node of this variable.
      Collection<Node> references = varToNameUsage.get(var);

      // First remove both assignments from the sorted list since they need
      // to be re-sorted.
      varsByFrequency.remove(original);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[49]++;
      varsByFrequency.remove(toShadow);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[50]++;

      // Adjust the count offset by the inner scope variable.
      original.count -= references.size();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[51]++;
      toShadow.count += references.size();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[52]++;

      // Add it back to the sorted list after re-adjustment.
      varsByFrequency.add(original);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[53]++;
      varsByFrequency.add(toShadow);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[54]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[55]++;

      // This is an important step. If variable L7 is going to be renamed to
      // L1, by definition of upward referencing, The name L1 is now in the
      // set of upward referencing names of the current scope up to the
      // declaring scope of the best shadow variable.
      Var shadowed = s.getVar(toShadow.oldName);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[56]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((shadowed != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[35]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[57]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[13]++;


int CodeCoverConditionCoverageHelper_C21;
        for (Scope curScope = s;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((curScope != shadowed.scope) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false);
            curScope = curScope.getParent()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[13]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[14]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[15]++;
}
          scopeUpRefMap.put(curScope.getRootNode(), toShadow.oldName);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[58]++;
        }

      } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[36]++;}
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[59]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[16]++;



      // Mark all the references as shadowed.
      for (Node n : references) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[16]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[17]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[18]++;
}
        n.setString(toShadow.oldName);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[60]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[61]++;
        Node cur = n;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[62]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[19]++;


int CodeCoverConditionCoverageHelper_C22;
        while((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((cur != s.getRootNode()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[19]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[20]--;
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.loops[21]++;
}
          cur = cur.getParent();
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[63]++;
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[64]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((cur.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[37]++;
            scopeUpRefMap.put(cur, toShadow.oldName);
CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.statements[65]++;

          } else {
  CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1.branches[38]++;}
        }
      }
    }
  }
}

class CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1 ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ShadowVariables.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$9qqv9eapqw8zr71s6w3lg2kfv55wwo1 () {
    super("com.google.javascript.jscomp.ShadowVariables.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ShadowVariables.java");
      for (int i = 1; i <= 65; i++) {
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
    for (int i = 1; i <= 23; i++) {
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

