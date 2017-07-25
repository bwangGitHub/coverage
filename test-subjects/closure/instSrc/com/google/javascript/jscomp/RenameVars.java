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
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Nullable;

/**
 * RenameVars renames all the variables names into short names, to reduce code
 * size and also to obfuscate the code.
 *
 */
final class RenameVars implements CompilerPass {
  static {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.ping();
  }

  private final AbstractCompiler compiler;

  /** List of global NAME nodes */
  private final ArrayList<Node> globalNameNodes = new ArrayList<Node>();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[1]++;
  }

  /** List of local NAME nodes */
  private final ArrayList<Node> localNameNodes = new ArrayList<Node>();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[2]++;
  }

  /**
   * Maps a name node to its pseudo name, null if we are not generating so
   * there will be no overhead unless we are debugging.
   */
  private final Map<Node, String> pseudoNameMap;

  /** Set of extern variable names */
  private final Set<String> externNames = new HashSet<String>();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[3]++;
  }

  /** Set of reserved variable names */
  private final Set<String> reservedNames;

  /** The renaming map */
  private final Map<String, String> renameMap = new HashMap<String, String>();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[4]++;
  }

  /** The previously used rename map. */
  private final VariableMap prevUsedRenameMap;

  /** The global name prefix */
  private final String prefix;

  /** Counter for each assignment */
  private int assignmentCount = 0;
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[5]++;
  }

  /** Logs all name assignments */
  private StringBuilder assignmentLog;

  // Logic for bleeding functions, where the name leaks into the outer
  // scope on IE but not on other browsers.
  private Set<Var> localBleedingFunctions = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[6]++;
  }
  private ArrayListMultimap<Scope, Var> localBleedingFunctionsPerScope =
      ArrayListMultimap.create();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[7]++;
  }

  class Assignment {
    final String oldName;
    final int orderOfOccurrence;
    String newName;
    int count; // Number of times this is referenced

    Assignment(String name) {
      this.oldName = name;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[8]++;
      this.newName = null;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[9]++;
      this.count = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[10]++;

      // Represents the order at which a symbol appears in the source.
      this.orderOfOccurrence = assignmentCount++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[11]++;
    }

    /**
     * Assigns the new name.
     */
    void setNewName(String newName) {
      Preconditions.checkState(this.newName == null);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[12]++;
      this.newName = newName;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[13]++;
    }
  }

  /** Maps an old name to a new name assignment */
  private final Map<String, Assignment> assignments =
      new HashMap<String, Assignment>();
  {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[14]++;
  }

  /** Whether renaming should apply to local variables only. */
  private final boolean localRenamingOnly;

  /**
   * Whether function expression names should be preserved. Typically, for
   * debugging purposes.
   *
   * @see NameAnonymousFunctions
   */
  private boolean preserveFunctionExpressionNames;

  private final boolean shouldShadow;

  /** Characters that shouldn't be used in variable names. */
  private final char[] reservedCharacters;

  /** A prefix to distinguish temporary local names from global names */
  // TODO(user): No longer needs to be public when shadowing doesn't use it.
  public static final String LOCAL_VAR_PREFIX = "L ";
  static {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[15]++;
  }

  RenameVars(AbstractCompiler compiler, String prefix,
      boolean localRenamingOnly, boolean preserveFunctionExpressionNames,
      boolean generatePseudoNames, boolean shouldShadow,
      VariableMap prevUsedRenameMap,
      @Nullable char[] reservedCharacters,
      @Nullable Set<String> reservedNames) {
    this.compiler = compiler;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[16]++;
    this.prefix = prefix == null ? "" : prefix;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[17]++;
    this.localRenamingOnly = localRenamingOnly;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[18]++;
    this.preserveFunctionExpressionNames = preserveFunctionExpressionNames;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[19]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[20]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((generatePseudoNames) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[1]++;
      this.pseudoNameMap = Maps.newHashMap();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[21]++;

    } else {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[2]++;
      this.pseudoNameMap = null;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[22]++;
    }
    this.prevUsedRenameMap = prevUsedRenameMap;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[23]++;
    this.reservedCharacters = reservedCharacters;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[24]++;
    this.shouldShadow = shouldShadow;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[25]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((reservedNames == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[3]++;
      this.reservedNames = Sets.newHashSet();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[27]++;

    } else {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[4]++;
      this.reservedNames = Sets.newHashSet(reservedNames);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[28]++;
    }
  }

  /**
   * Iterate through the nodes, collect all the NAME nodes that need to be
   * renamed, and count how many times each variable name is referenced.
   *
   * There are 2 passes:
   * - externs: keep track of the global vars in the externNames_ map.
   * - source: keep track of all name references in globalNameNodes_, and
   *   localNameNodes_.
   *
   * To get shorter local variable renaming, we rename local variables to a
   * temporary name "LOCAL_VAR_PREFIX + index" where index is the index of the
   * variable declared in the local scope stack.
   * e.g.
   * Foo(fa, fb) {
   *   var c = function(d, e) { return fa; }
   * }
   * The indexes are: fa:0, fb:1, c:2, d:3, e:4
   *
   * In that way, local variable names are reused in each global function.
   * e.g. the final code might look like
   * function x(a,b) { ... }
   * function y(a,b,c) { ... }
   */
  class ProcessVars extends AbstractPostOrderCallback
      implements ScopedCallback {
    private final boolean isExternsPass_;

    ProcessVars(boolean isExterns) {
      isExternsPass_ = isExterns;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[29]++;
    }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[5]++; return;
} else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[6]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[31]++;

      Iterator<Var> it = t.getScope().getVars();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[32]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
      while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[1]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[2]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[3]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[33]++;
        Var current = it.next();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((current.isBleedingFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[7]++;
          localBleedingFunctions.add(current);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[35]++;
          localBleedingFunctionsPerScope.put(
              t.getScope().getParent(), current);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[36]++;

        } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[8]++;}
      }
    }

    @Override
    public void exitScope(NodeTraversal t) {}

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[9]++;
        return;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[10]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[38]++;

      String name = n.getString();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;

      // Ignore anonymous functions
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((name.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[12]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[40]++;

      // Is this local or Global?
      // Bleeding functions should be treated as part of their outer
      // scope, because IE has bugs in how it handles bleeding
      // functions.
      Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[41]++;
      boolean local = (var != null) && var.isLocal() &&
          (!var.scope.getParent().isGlobal() ||
           !var.isBleedingFunction());
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;

      // Are we renaming global variables?
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((localRenamingOnly) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[13]++;
        reservedNames.add(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[43]++;
        return;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[14]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;

      // Are we renaming function expression names?
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((preserveFunctionExpressionNames) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(var.getParentNode())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[15]++;
        reservedNames.add(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[45]++;
        return;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[16]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;

      // Check if we can rename this.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((okToRenameVar(name, local)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[17]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[19]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[48]++;
          // Blindly de-uniquify for the Prototype library for issue 103.
          String newName = MakeDeclaredNamesUnique.ContextualRenameInverter
              .getOrginalName(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((newName.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[21]++;
            n.setString(newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[50]++;

          } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[22]++;}

        } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[20]++;}
        return;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[18]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;

      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isExternsPass_) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[23]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;
        // Keep track of extern globals.
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[25]++;
          externNames.add(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[53]++;

        } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[26]++;}
        return;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[24]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pseudoNameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[27]++;
        recordPseudoName(n);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[55]++;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[28]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((local) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[29]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[57]++;
        // Local var: assign a new name
        String tempName = LOCAL_VAR_PREFIX + getLocalVarIndex(var);
        incCount(tempName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[58]++;
        localNameNodes.add(n);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[59]++;
        n.setString(tempName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[60]++;

      } else {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[30]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[61]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[31]++; // Not an extern
        // If it's global, increment global count
        incCount(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[62]++;
        globalNameNodes.add(n);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[63]++;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[32]++;}
}
    }

    // Increment count of an assignment
    void incCount(String name) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[64]++;
      Assignment s = assignments.get(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[65]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[33]++;
        s = new Assignment(name);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[66]++;
        assignments.put(name, s);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[67]++;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[34]++;}
      s.count++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[68]++;
    }
  }

  /**
   * Sorts Assignment objects by their count, breaking ties by their order of
   * occurrence in the source to ensure a deterministic total ordering.
   */
  private static final Comparator<Assignment> FREQUENCY_COMPARATOR =
      new Comparator<Assignment>() {
    @Override
    public int compare(Assignment a1, Assignment a2) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[69]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((a1.count != a2.count) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[35]++;
        return a2.count - a1.count;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[36]++;}
      // Break a tie using the order in which the variable first appears in
      // the source.
      return ORDER_OF_OCCURRENCE_COMPARATOR.compare(a1, a2);
    }
  };
  static {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[70]++;
  }

  /**
   * Sorts Assignment objects by the order the variable name first appears in
   * the source.
   */
  private static final Comparator<Assignment> ORDER_OF_OCCURRENCE_COMPARATOR =
      new Comparator<Assignment>() {
        @Override
        public int compare(Assignment a1, Assignment a2) {
          return a1.orderOfOccurrence - a2.orderOfOccurrence;
        }
      };
  static {
    CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[71]++;
  }

  @Override
  public void process(Node externs, Node root) {
    assignmentLog = new StringBuilder();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[72]++;

    // Do variable reference counting.
    NodeTraversal.traverse(compiler, externs, new ProcessVars(true));
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[73]++;
    NodeTraversal.traverse(compiler, root, new ProcessVars(false));
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[74]++;

    // Make sure that new names don't overlap with extern names.
    reservedNames.addAll(externNames);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[75]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[76]++;

    // Rename vars, sorted by frequency of occurrence to minimize code size.
    SortedSet<Assignment> varsByFrequency =
        new TreeSet<Assignment>(FREQUENCY_COMPARATOR);
    varsByFrequency.addAll(assignments.values());
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[77]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[78]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((shouldShadow) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[37]++;
      new ShadowVariables(
          compiler, assignments, varsByFrequency, pseudoNameMap).process(
              externs, root);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[79]++;

    } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[38]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[80]++;
int CodeCoverConditionCoverageHelper_C21;

    // First try to reuse names from an earlier compilation.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((prevUsedRenameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[39]++;
      reusePreviouslyUsedVariableMap();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[81]++;

    } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[40]++;}

    // Assign names, sorted by descending frequency to minimize code size.
    assignNames(varsByFrequency);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[82]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[83]++;

    boolean changed = false;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[84]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[4]++;



    // Rename the globals!
    for (Node n : globalNameNodes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[4]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[5]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[6]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[85]++;
      String newName = getNewGlobalName(n);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[86]++;
int CodeCoverConditionCoverageHelper_C22;
      // Note: if newName is null, then oldName is an extern.
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((newName != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[41]++;
        n.setString(newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[87]++;
        changed = true;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[88]++;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[42]++;}
    }
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[89]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[7]++;



    // Rename the locals!
    for (Node n : localNameNodes) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[7]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[8]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[9]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[90]++;
      String newName = getNewLocalName(n);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[91]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((newName != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[43]++;
        n.setString(newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[92]++;
        changed = true;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[93]++;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[44]++;}
    }
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[94]++;
int CodeCoverConditionCoverageHelper_C24;

    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[45]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[95]++;

    } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[46]++;}

    // Lastly, write the name assignments to the debug log.
    compiler.addToDebugLog("JS var assignments:\n" + assignmentLog);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[96]++;
    assignmentLog = null;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[97]++;
  }

  private String getNewGlobalName(Node n) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[98]++;
    String oldName = n.getString();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[99]++;
    Assignment a = assignments.get(oldName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[100]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((a.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((a.newName.equals(oldName)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[47]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[101]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((pseudoNameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[49]++;
        return pseudoNameMap.get(n);

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[50]++;}
      return a.newName;

    } else {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[48]++;
      return null;
    }
  }

  private String getNewLocalName(Node n) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[102]++;
    String oldTempName = n.getString();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[103]++;
    Assignment a = assignments.get(oldTempName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[104]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((a.newName.equals(oldTempName)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[51]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[105]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((pseudoNameMap != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[53]++;
        return pseudoNameMap.get(n);

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[54]++;}
      return a.newName;

    } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[52]++;}
    return null;
  }

  private void recordPseudoName(Node n) {
    // Variable names should be in a different name space than
    // property pseudo names.
    pseudoNameMap.put(n, '$' + n.getString() + "$$" );
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[106]++;
  }

  /**
   * Runs through the assignments and reuses as many names as possible from the
   * previously used variable map. Updates reservedNames with the set of names
   * that were reused.
   */
  private void reusePreviouslyUsedVariableMap() {
    // If prevUsedRenameMap had duplicate values then this pass would be
    // non-deterministic.
    // In such a case, the following will throw an IllegalArgumentException.
    Preconditions.checkState(
        prevUsedRenameMap.getNewNameToOriginalNameMap() instanceof Map);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[107]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[108]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[10]++;


    for (Assignment a : assignments.values()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[10]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[11]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[12]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[109]++;
      String prevNewName = prevUsedRenameMap.lookupNewName(a.oldName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[110]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((prevNewName == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((reservedNames.contains(prevNewName)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[55]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[111]++;
        continue;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[56]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[112]++;
int CodeCoverConditionCoverageHelper_C30;

      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (32)) == 0 || true) &&
 ((a.oldName.startsWith(LOCAL_VAR_PREFIX)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (16)) == 0 || true)))
 || (!
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((externNames.contains(a.oldName)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((prevNewName.startsWith(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[57]++;
        reservedNames.add(prevNewName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[113]++;
        finalizeNameAssignment(a, prevNewName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[114]++;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[58]++;}
    }
  }

  /**
   * Determines which new names to substitute for the original names.
   */
  private void assignNames(SortedSet<Assignment> varsToRename) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[115]++;
    NameGenerator globalNameGenerator =
        new NameGenerator(reservedNames, prefix, reservedCharacters);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[116]++;

    // Local variables never need a prefix.
    NameGenerator localNameGenerator =
        prefix.isEmpty() ? globalNameGenerator : new NameGenerator(
            reservedNames, "", reservedCharacters);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[117]++;

    // Generated names and the assignments for non-local vars.
    List<Assignment> pendingAssignments = new ArrayList<Assignment>();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[118]++;
    List<String> generatedNamesForAssignments = new ArrayList<String>();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[119]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[13]++;



    for (Assignment a : varsToRename) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[13]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[14]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[15]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[120]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((a.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[59]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[121]++;
        continue;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[60]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[122]++;
int CodeCoverConditionCoverageHelper_C32;

      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((externNames.contains(a.oldName)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[61]++;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[123]++;
        continue;

      } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[62]++;}

      String newName;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[124]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((a.oldName.startsWith(LOCAL_VAR_PREFIX)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[63]++;
        // For local variable, we make the assignment right away.
        newName = localNameGenerator.generateNextName();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[125]++;
        finalizeNameAssignment(a, newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[126]++;

      } else {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[64]++;
        // For non-local variable, delay finalizing the name assignment
        // until we know how many new names we'll have of length 2, 3, etc.
        newName = globalNameGenerator.generateNextName();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[127]++;
        pendingAssignments.add(a);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[128]++;
        generatedNamesForAssignments.add(newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[129]++;
      }
      reservedNames.add(newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[130]++;
    }
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[131]++;

    // Now that we have a list of generated names, and a list of variable
    // Assignment objects, we assign the generated names to the vars as
    // follows:
    // 1) The most frequent vars get the shorter names.
    // 2) If N number of vars are going to be assigned names of the same
    //    length, we assign the N names based on the order at which the vars
    //    first appear in the source. This makes the output somewhat less
    //    random, because symbols declared close together are assigned names
    //    that are quite similar. With this heuristic, the output is more
    //    compressible.
    //    For instance, the output may look like:
    //    var da = "..", ea = "..";
    //    function fa() { .. } function ga() { .. }

    int numPendingAssignments = generatedNamesForAssignments.size();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[132]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[16]++;


int CodeCoverConditionCoverageHelper_C34;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i < numPendingAssignments) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false);) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[16]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[17]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[18]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[133]++;
      SortedSet<Assignment> varsByOrderOfOccurrence =
          new TreeSet<Assignment>(ORDER_OF_OCCURRENCE_COMPARATOR);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[134]++;

      // Add k number of Assignment to the set, where k is the number of
      // generated names of the same length.
      int len = generatedNamesForAssignments.get(i).length();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[135]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[19]++;


int CodeCoverConditionCoverageHelper_C35;
      for (int j = i;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((j < numPendingAssignments) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((generatedNamesForAssignments.get(j).length() == len) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false); j++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[19]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[20]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[21]++;
}
        varsByOrderOfOccurrence.add(pendingAssignments.get(j));
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[136]++;
      }
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[137]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[22]++;



      // Now, make the assignments
      for (Assignment a : varsByOrderOfOccurrence) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[22]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[23]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[24]++;
}
        finalizeNameAssignment(a, generatedNamesForAssignments.get(i));
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[138]++;
        ++i;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[139]++;
      }
    }
  }

  /**
   * Makes a final name assignment.
   */
  private void finalizeNameAssignment(Assignment a, String newName) {
    a.setNewName(newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[140]++;

    // Keep track of the mapping
    renameMap.put(a.oldName, newName);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[141]++;

    // Log the mapping
    assignmentLog.append(a.oldName).append(" => ").append(newName).append('\n');
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[142]++;
  }

  /**
   * Gets the variable map.
   */
  VariableMap getVariableMap() {
    return new VariableMap(ImmutableMap.copyOf(renameMap));
  }

  /**
   * Determines whether a variable name is okay to rename.
   */
  private boolean okToRenameVar(String name, boolean isLocal) {
    return !compiler.getCodingConvention().isExported(name, isLocal);
  }

  /**
   * Returns the index within the scope stack.
   * e.g. function Foo(a) { var b; function c(d) { } }
   * a = 0, b = 1, c = 2, d = 3
   */
  private int getLocalVarIndex(Var v) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[143]++;
    int num = v.index;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[144]++;
    Scope s = v.scope.getParent();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[145]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((s == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[65]++;
      throw new IllegalArgumentException("Var is not local");

    } else {
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[66]++;}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[146]++;

    boolean isBleedingIntoScope = s.getParent() != null &&
        localBleedingFunctions.contains(v);
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[147]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[25]++;


int CodeCoverConditionCoverageHelper_C37;

    while ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((s.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[25]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[26]--;
  CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.loops[27]++;
}
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[148]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((isBleedingIntoScope) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[67]++;
        num += localBleedingFunctionsPerScope.get(s).indexOf(v) + 1;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[149]++;
        isBleedingIntoScope = false;
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[150]++;

      } else {
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.branches[68]++;
        num += localBleedingFunctionsPerScope.get(s).size();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[151]++;
      }

      num += s.getVarCount();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[152]++;
      s = s.getParent();
CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9.statements[153]++;
    }
    return num;
  }
}

class CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9 ());
  }
    public static long[] statements = new long[154];
    public static long[] branches = new long[69];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RenameVars.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,3,1,1,1,1,2,1,1,1};
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
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$op0x61b74utda9sej8jm1y9 () {
    super("com.google.javascript.jscomp.RenameVars.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 153; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 68; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RenameVars.java");
      for (int i = 1; i <= 153; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 68; i++) {
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
      for (int i = 1; i <= 9; i++) {
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

