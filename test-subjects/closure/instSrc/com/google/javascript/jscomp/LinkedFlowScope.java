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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.type.FlowScope;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.SimpleSlot;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSlot;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A flow scope that tries to store as little symbol information as possible,
 * instead delegating to its parents. Optimized for low memory use.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class LinkedFlowScope implements FlowScope {
  static {
    CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.ping();
  }

  // The closest flow scope cache.
  private final FlatFlowScopeCache cache;

  // The parent flow scope.
  private final LinkedFlowScope parent;

  // The distance between this flow scope and the closest flat flow scope.
  private int depth;

  static final int MAX_DEPTH = 250;
  static {
    CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[1]++;
  }

  // A FlatFlowScopeCache equivalent to this scope.
  private FlatFlowScopeCache flattened;

  // Flow scopes assume that all their ancestors are immutable.
  // So once a child scope is created, this flow scope may not be modified.
  private boolean frozen = false;
  {
    CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[2]++;
  }

  // The last slot defined in this flow instruction, and the head of the
  // linked list of slots.
  private LinkedFlowSlot lastSlot;

  private LinkedFlowScope(FlatFlowScopeCache cache,
      LinkedFlowScope directParent) {
    this.cache = cache;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[3]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((directParent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[1]++;
      this.lastSlot = null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[5]++;
      this.depth = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[6]++;
      this.parent = cache.linkedEquivalent;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[7]++;

    } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[2]++;
      this.lastSlot = directParent.lastSlot;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[8]++;
      this.depth = directParent.depth + 1;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[9]++;
      this.parent = directParent;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[10]++;
    }
  }

  LinkedFlowScope(FlatFlowScopeCache cache) {
    this(cache, null);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[11]++;
  }

  LinkedFlowScope(LinkedFlowScope directParent) {
    this(directParent.cache, directParent);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[12]++;
  }

  /** Gets the function scope for this flow scope. */
  private Scope getFunctionScope() {
    return cache.functionScope;
  }

  /** Whether this flows from a bottom scope. */
  private boolean flowsFromBottom() {
    return getFunctionScope().isBottom();
  }

  /**
   * Creates an entry lattice for the flow.
   */
  public static LinkedFlowScope createEntryLattice(Scope scope) {
    return new LinkedFlowScope(new FlatFlowScopeCache(scope));
  }

  @Override
  public void inferSlotType(String symbol, JSType type) {
    Preconditions.checkState(!frozen);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[13]++;
    lastSlot = new LinkedFlowSlot(symbol, type, lastSlot);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[14]++;
    depth++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[15]++;
    cache.dirtySymbols.add(symbol);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[16]++;
  }

  @Override
  public void inferQualifiedSlot(Node node, String symbol, JSType bottomType,
      JSType inferredType) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[17]++;
    Scope functionScope = getFunctionScope();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((functionScope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[3]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((functionScope.getVar(symbol) == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((functionScope.isBottom()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[5]++;
        functionScope.declare(symbol, node, bottomType, null);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[20]++;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[6]++;}

      inferSlotType(symbol, inferredType);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[21]++;

    } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[4]++;}
  }

  @Override
  public JSType getTypeOfThis() {
    return cache.functionScope.getTypeOfThis();
  }

  @Override
  public Node getRootNode() {
    return getFunctionScope().getRootNode();
  }

  @Override
  public StaticScope<JSType> getParentScope() {
    return getFunctionScope().getParentScope();
  }

  /**
   * Get the slot for the given symbol.
   */
  @Override
  public StaticSlot<JSType> getSlot(String name) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cache.dirtySymbols.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[7]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
      for (LinkedFlowSlot slot = lastSlot;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); slot = slot.parent) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[1]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[2]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[3]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((slot.getName().equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[9]++;
          return slot;

        } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[10]++;}
      }

    } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[8]++;}
    return cache.getSlot(name);
  }

  @Override
  public StaticSlot<JSType> getOwnSlot(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public FlowScope createChildFlowScope() {
    frozen = true;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[25]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((depth > MAX_DEPTH) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[11]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((flattened == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[13]++;
        flattened = new FlatFlowScopeCache(this);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[28]++;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[14]++;}
      return new LinkedFlowScope(flattened);

    } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[12]++;}

    return new LinkedFlowScope(this);
  }

  /**
   * Iterate through all the linked flow scopes before this one.
   * If there's one and only one slot defined between this scope
   * and the blind scope, return it.
   */
  @Override
  public StaticSlot<JSType> findUniqueRefinedSlot(FlowScope blindScope) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[29]++;
    StaticSlot<JSType> result = null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[30]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;

    for (LinkedFlowScope currentScope = this;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((currentScope != blindScope) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false);
         currentScope = currentScope.parent) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[4]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[5]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[6]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[31]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
      for (LinkedFlowSlot currentSlot = currentScope.lastSlot;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((currentSlot != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((currentScope.parent == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((currentScope.parent.lastSlot != currentSlot) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false);
           currentSlot = currentSlot.parent) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[7]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[8]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[9]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[15]++;
          result = currentSlot;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[33]++;

        } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[16]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[34]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((currentSlot.getName().equals(result.getName())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[17]++;
          return null;

        } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[18]++;}
}
      }
    }

    return result;
  }

  /**
   * Look through the given scope, and try to find slots where it doesn't
   * have enough type information. Then fill in that type information
   * with stuff that we've inferred in the local flow.
   */
  @Override
  public void completeScope(StaticScope<JSType> staticScope) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[35]++;
    Scope scope = (Scope) staticScope;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[36]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
    for (Iterator<Var> it = scope.getVars();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false);) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[10]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[11]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[12]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[37]++;
      Var var = it.next();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[38]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[19]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[39]++;
        JSType type = var.getType();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[21]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[41]++;
          JSType flowType = getSlot(var.getName()).getType();
          var.setType(flowType);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[42]++;

        } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[22]++;}

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[20]++;}
    }
  }

  /**
   * Remove flow scopes that add nothing to the flow.
   */
  // NOTE(nicksantos): This function breaks findUniqueRefinedSlot, because
  // findUniqueRefinedSlot assumes that this scope is a direct descendant
  // of blindScope. This is not necessarily true if this scope has been
  // optimize()d and blindScope has not. This should be fixed. For now,
  // we only use optimize() where we know that we won't have to do
  // a findUniqueRefinedSlot on it.
  @Override
  public LinkedFlowScope optimize() {
    LinkedFlowScope current;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[43]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[13]++;


int CodeCoverConditionCoverageHelper_C16;
    for (current = this;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((current.parent != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((current.lastSlot == current.parent.lastSlot) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false);
         current = current.parent) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[13]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[14]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[15]++;
}}
    return current;
  }

  /** Join the two FlowScopes. */
  static class FlowScopeJoinOp extends JoinOp.BinaryJoinOp<FlowScope> {
    @SuppressWarnings("unchecked")
    @Override
    public FlowScope apply(FlowScope a, FlowScope b) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[44]++;
      // To join the two scopes, we have to
      LinkedFlowScope linkedA = (LinkedFlowScope) a;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[45]++;
      LinkedFlowScope linkedB = (LinkedFlowScope) b;
      linkedA.frozen = true;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[46]++;
      linkedB.frozen = true;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[47]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[48]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((linkedA.optimize() == linkedB.optimize()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[23]++;
        return linkedA.createChildFlowScope();

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[24]++;}
      return new LinkedFlowScope(new FlatFlowScopeCache(linkedA, linkedB));
    }
  }

  @Override
  public boolean equals(Object other) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[49]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((other instanceof LinkedFlowScope) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[25]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[50]++;
      LinkedFlowScope that = (LinkedFlowScope) other;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[51]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.optimize() == that.optimize()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[27]++;
        return true;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[28]++;}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[52]++;
int CodeCoverConditionCoverageHelper_C20;

      // If two flow scopes are in the same function, then they could have
      // two possible function scopes: the real one and the BOTTOM scope.
      // If they have different function scopes, we *should* iterate through all
      // the variables in each scope and compare. However, 99.9% of the time,
      // they're not equal. And the other .1% of the time, we can pretend
      // they're equal--this just means that data flow analysis will have
      // to propagate the entry lattice a little bit further than it
      // really needs to. Everything will still come out ok.
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.getFunctionScope() != that.getFunctionScope()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[29]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[30]++;}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[53]++;
int CodeCoverConditionCoverageHelper_C21;

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((cache == that.cache) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[31]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[54]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[16]++;


        // If the two flow scopes have the same cache, then we can check
        // equality a lot faster: by just looking at the "dirty" elements
        // in the cache, and comparing them in both scopes.
        for (String name : cache.dirtySymbols) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[16]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[17]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[18]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[55]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((diffSlots(getSlot(name), that.getSlot(name))) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[33]++;
            return false;

          } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[34]++;}
        }

        return true;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[32]++;}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[56]++;

      Map<String, StaticSlot<JSType>> myFlowSlots = allFlowSlots();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[57]++;
      Map<String, StaticSlot<JSType>> otherFlowSlots = that.allFlowSlots();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[58]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[19]++;



      for (StaticSlot<JSType> slot : myFlowSlots.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[19]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[20]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[21]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[59]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((diffSlots(slot, otherFlowSlots.get(slot.getName()))) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[35]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[36]++;}
        otherFlowSlots.remove(slot.getName());
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[60]++;
      }
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[61]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[22]++;


      for (StaticSlot<JSType> slot : otherFlowSlots.values()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[22]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[23]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[24]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[62]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((diffSlots(slot, myFlowSlots.get(slot.getName()))) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[37]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[38]++;}
      }
      return true;

    } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[26]++;}
    return false;
  }

  /**
   * Determines whether two slots are meaningfully different for the
   * purposes of data flow analysis.
   */
  private boolean diffSlots(StaticSlot<JSType> slotA,
                            StaticSlot<JSType> slotB) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[63]++;
    boolean aIsNull = slotA == null || slotA.getType() == null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[64]++;
    boolean bIsNull = slotB == null || slotB.getType() == null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[65]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((aIsNull) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((bIsNull) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[39]++;
      return false;

    } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[40]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[66]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((aIsNull) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 ^ 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((bIsNull) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[41]++;
      return true;

    } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[42]++;}
}

    // Both slots and types must be non-null.
    return slotA.getType().differsFrom(slotB.getType());
  }

  /**
   * Gets all the symbols that have been defined before this point
   * in the current flow. Does not return slots that have not changed during
   * the flow.
   *
   * For example, consider the code:
   * <code>
   * var x = 3;
   * function f() {
   *   var y = 5;
   *   y = 6; // FLOW POINT
   *   var z = y;
   *   return z;
   * }
   * </code>
   * A FlowScope at FLOW POINT will return a slot for y, but not
   * a slot for x or z.
   */
  private Map<String, StaticSlot<JSType>> allFlowSlots() {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[67]++;
    Map<String, StaticSlot<JSType>> slots = Maps.newHashMap();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[68]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[25]++;


int CodeCoverConditionCoverageHelper_C27;
    for (LinkedFlowSlot slot = lastSlot;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); slot = slot.parent) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[25]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[26]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[27]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[69]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((slots.containsKey(slot.getName())) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[43]++;
        slots.put(slot.getName(), slot);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[70]++;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[44]++;}
    }
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[71]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[28]++;



    for (Map.Entry<String, StaticSlot<JSType>> symbolEntry : cache.symbols.entrySet()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[28]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[29]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[30]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[72]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((slots.containsKey(symbolEntry.getKey())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[45]++;
        slots.put(symbolEntry.getKey(), symbolEntry.getValue());
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[73]++;

      } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[46]++;}
    }

    return slots;
  }

  /**
   * A static slot that can be used in a linked list.
   */
  private static class LinkedFlowSlot extends SimpleSlot {
    final LinkedFlowSlot parent;

    LinkedFlowSlot(String name, JSType type, LinkedFlowSlot parent) {
      super(name, type, true);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[74]++;
      this.parent = parent;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[75]++;
    }
  }

  /**
   * A map that tries to cache as much symbol table information
   * as possible in a map. Optimized for fast lookup.
   */
  private static class FlatFlowScopeCache {
    // The Scope for the entire function or for the global scope.
    private final Scope functionScope;

    // The linked flow scope that this cache represents.
    private final LinkedFlowScope linkedEquivalent;

    // All the symbols defined before this point in the local flow.
    // May not include lazily declared qualified names.
    private Map<String, StaticSlot<JSType>> symbols = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[76]++;
  }

    // Used to help make lookup faster for LinkedFlowScopes by recording
    // symbols that may be redefined "soon", for an arbitrary definition
    // of "soon". ;)
    //
    // More rigorously, if a symbol is redefined in a LinkedFlowScope,
    // and this is the closest FlatFlowScopeCache, then that symbol is marked
    // "dirty". In this way, we don't waste time looking in the LinkedFlowScope
    // list for symbols that aren't defined anywhere nearby.
    final Set<String> dirtySymbols = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[77]++;
  }

    // The cache at the bottom of the lattice.
    FlatFlowScopeCache(Scope functionScope) {
      this.functionScope = functionScope;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[78]++;
      symbols = ImmutableMap.of();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[79]++;
      linkedEquivalent = null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[80]++;
    }

    // A cache in the middle of a long scope chain.
    FlatFlowScopeCache(LinkedFlowScope directParent) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[81]++;
      FlatFlowScopeCache cache = directParent.cache;

      functionScope = cache.functionScope;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[82]++;
      symbols = directParent.allFlowSlots();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[83]++;
      linkedEquivalent = directParent;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[84]++;
    }

    // A cache at the join of two scope chains.
    FlatFlowScopeCache(LinkedFlowScope joinedScopeA,
        LinkedFlowScope joinedScopeB) {
      linkedEquivalent = null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[85]++;

      // Always prefer the "real" function scope to the faked-out
      // bottom scope.
      functionScope = joinedScopeA.flowsFromBottom() ?
          joinedScopeB.getFunctionScope() : joinedScopeA.getFunctionScope();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[86]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[87]++;

      Map<String, StaticSlot<JSType>> slotsA = joinedScopeA.allFlowSlots();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[88]++;
      Map<String, StaticSlot<JSType>> slotsB = joinedScopeB.allFlowSlots();

      symbols = slotsA;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[89]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[90]++;

      // There are 5 different join cases:
      // 1) The type is declared in joinedScopeA, not in joinedScopeB,
      //    and not in functionScope. Just use the one in A.
      // 2) The type is declared in joinedScopeB, not in joinedScopeA,
      //    and not in functionScope. Just use the one in B.
      // 3) The type is declared in functionScope and joinedScopeA, but
      //    not in joinedScopeB. Join the two types.
      // 4) The type is declared in functionScope and joinedScopeB, but
      //    not in joinedScopeA. Join the two types.
      // 5) The type is declared in joinedScopeA and joinedScopeB. Join
      //    the two types.
      Set<String> symbolNames = Sets.newHashSet(symbols.keySet());
      symbolNames.addAll(slotsB.keySet());
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[91]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[92]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[31]++;



      for (String name : symbolNames) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[31]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[32]--;
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.loops[33]++;
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[93]++;
        StaticSlot<JSType> slotA = slotsA.get(name);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[94]++;
        StaticSlot<JSType> slotB = slotsB.get(name);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[95]++;

        JSType joinedType = null;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[96]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((slotB == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((slotB.getType() == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[47]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[97]++;
          StaticSlot<JSType> fnSlot
              = joinedScopeB.getFunctionScope().getSlot(name);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[98]++;
          JSType fnSlotType = fnSlot == null ? null : fnSlot.getType();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[99]++;
int CodeCoverConditionCoverageHelper_C31;
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((fnSlotType == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[49]++;

            // Case #1 -- already inserted.
          } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[50]++;
            // Case #3
            joinedType = slotA.getType().getLeastSupertype(fnSlotType);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[100]++;
          }

        } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[48]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[101]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((slotA == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((slotA.getType() == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[51]++;
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[102]++;
          StaticSlot<JSType> fnSlot
              = joinedScopeA.getFunctionScope().getSlot(name);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[103]++;
          JSType fnSlotType = fnSlot == null ? null : fnSlot.getType();
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[104]++;
int CodeCoverConditionCoverageHelper_C33;
          if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((fnSlotType == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[53]++;
            // Case #2
            symbols.put(name, slotB);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[105]++;

          } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[54]++;
            // Case #4
            joinedType = slotB.getType().getLeastSupertype(fnSlotType);
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[106]++;
          }

        } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[52]++;
          // Case #5
          joinedType =
              slotA.getType().getLeastSupertype(slotB.getType());
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[107]++;
        }
}
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[108]++;
int CodeCoverConditionCoverageHelper_C34;

        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((joinedType != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[55]++;
          symbols.put(name, new SimpleSlot(name, joinedType, true));
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[109]++;

        } else {
  CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[56]++;}
      }
    }

    /**
     * Get the slot for the given symbol.
     */
    public StaticSlot<JSType> getSlot(String name) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.statements[110]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((symbols.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[57]++;
        return symbols.get(name);

      } else {
CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l.branches[58]++;
        return functionScope.getSlot(name);
      }
    }
  }
}

class CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l ());
  }
    public static long[] statements = new long[111];
    public static long[] branches = new long[59];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[36];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.LinkedFlowScope.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,3,1,1,1,1,2,2,1,1,1,1,1,1,1,1,2,2,1,1,1,2,1,2,1,1,1};
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
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$8xbs1lnlqiw3jz0f55fnwmm9utm9q3l () {
    super("com.google.javascript.jscomp.LinkedFlowScope.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 110; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 58; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 35; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.LinkedFlowScope.java");
      for (int i = 1; i <= 110; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 58; i++) {
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
      for (int i = 1; i <= 11; i++) {
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

