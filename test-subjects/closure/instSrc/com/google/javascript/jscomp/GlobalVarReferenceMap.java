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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Reference;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceMap;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An implementation for {@code ReferenceMap} that is specific to global scope
 * and can be used in different passes. In other words instead of relying on
 * Var object it relies on the name of the variable. It also supports hot-swap
 * update of reference map for a specific script.
 *
 * @see ReferenceCollectingCallback#exitScope(NodeTraversal)
 *
 * @author bashir@google.com (Bashir Sadjad)
 */
class GlobalVarReferenceMap implements ReferenceMap {
  static {
    CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.ping();
  }


  private Map<String, ReferenceCollection> refMap = null;
  {
    CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[1]++;
  }

  private final Map<InputId, Integer> inputOrder;

  /**
   * @param inputs The ordered list of all inputs for the compiler.
   */
  GlobalVarReferenceMap(List<CompilerInput> inputs, List<CompilerInput> externs) {
    inputOrder = Maps.newHashMap();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[2]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[3]++;
    int ind = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[1]++;


    for (CompilerInput extern : externs) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[1]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[2]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[3]++;
}
      inputOrder.put(extern.getInputId(), ind);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[5]++;
      ind++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[6]++;
    }
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[7]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[4]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[4]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[5]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[6]++;
}
      inputOrder.put(input.getInputId(), ind);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[8]++;
      ind++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[9]++;
    }
  }

  @Override
  public ReferenceCollection getReferences(Var var) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[1]++;
      return null;

    } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[2]++;}
    return refMap.get(var.getName());
  }

  /**
   * Resets global var reference map with the new provide map.
   *
   * @param globalRefMap The reference map result of a
   *     {@link ReferenceCollectingCallback} pass collected from the whole AST.
   */
  private void resetGlobalVarReferences(
      Map<Var, ReferenceCollection> globalRefMap) {
    refMap = Maps.newHashMap();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[11]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[12]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[7]++;


    for (Entry<Var, ReferenceCollection> entry : globalRefMap.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[7]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[8]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[9]++;
}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[13]++;
      Var var = entry.getKey();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[3]++;
        refMap.put(var.getName(), entry.getValue());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[15]++;

      } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[4]++;}
    }
  }

  /**
   * Updates the internal reference map based on the provided parameters. If
   * {@code scriptRoot} is not SCRIPT, it basically replaces the internal map
   * with the new one, otherwise it replaces all the information associated to
   * the given script.
   *
   * @param refMapPatch The reference map result of a
   *     {@link ReferenceCollectingCallback} pass which might be collected from
   *     the whole AST or just a sub-tree associated to a SCRIPT node.
   * @param root AST sub-tree root on which reference collection was done.
   */
  void updateGlobalVarReferences(Map<Var, ReferenceCollection>
      refMapPatch, Node root) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((refMap == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((root.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[5]++;
      resetGlobalVarReferences(refMapPatch);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[17]++;
      return;

    } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[6]++;}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[18]++;

    InputId inputId = root.getInputId();
    Preconditions.checkNotNull(inputId);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[19]++;
    // Note there are two assumptions here (i) the order of compiler inputs
    // has not changed and (ii) all references are in the order they appear
    // in AST (this is enforced in ReferenceCollectionCallback).
    removeScriptReferences(inputId);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[20]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[21]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[10]++;


    for (Entry<Var, ReferenceCollection> entry : refMapPatch.entrySet()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[10]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[11]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[12]++;
}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[22]++;
      Var var = entry.getKey();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((var.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[7]++;
        replaceReferences(var.getName(), inputId, entry.getValue());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[24]++;

      } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[8]++;}
    }
  }

  private void removeScriptReferences(InputId inputId) {
    Preconditions.checkNotNull(inputId);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[25]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((inputOrder.containsKey(inputId)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[9]++;
      return;
 // Input did not exist when last computed, so skip
    } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[10]++;}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[27]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[13]++;


    // TODO(bashir): If this is too slow it is not too difficult to make it
    // faster with keeping an index for variables accessed in sourceName.
    for (ReferenceCollection collection : refMap.values()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[13]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[14]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[15]++;
}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((collection == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[11]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[29]++;
        continue;

      } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[12]++;}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[30]++;
      List<Reference> oldRefs = collection.references;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[31]++;
      SourceRefRange range = findSourceRefRange(oldRefs, inputId);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[32]++;
      List<Reference> newRefs = Lists.newArrayList(range.refsBefore());
      newRefs.addAll(range.refsAfter());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[33]++;
      collection.references = newRefs;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[34]++;
    }
  }

  private void replaceReferences(String varName, InputId inputId,
      ReferenceCollection newSourceCollection) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[35]++;
    ReferenceCollection combined = new ReferenceCollection();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[36]++;
    List<Reference> combinedRefs = combined.references;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[37]++;
    ReferenceCollection oldCollection = refMap.get(varName);
    refMap.put(varName, combined);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[38]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((oldCollection == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[13]++;
      combinedRefs.addAll(newSourceCollection.references);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[40]++;
      return;

    } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[14]++;}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[41]++;
    // otherwise replace previous references that are from sourceName
    SourceRefRange range = findSourceRefRange(oldCollection.references,
      inputId);
    combinedRefs.addAll(range.refsBefore());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[42]++;
    combinedRefs.addAll(newSourceCollection.references);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[43]++;
    combinedRefs.addAll(range.refsAfter());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[44]++;
  }

  /**
   * Finds the range of references associated to {@code sourceName}. Note that
   * even if there is no sourceName references the returned information can be
   * used to decide where to insert new sourceName refs.
   */
  private SourceRefRange findSourceRefRange(List<Reference> refList,
      InputId inputId) {
    Preconditions.checkNotNull(inputId);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[45]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[46]++;

    // TODO(bashir): We can do binary search here, but since this is fast enough
    // right now, we just do a linear search for simplicity.
    int lastBefore = -1;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[47]++;
    int firstAfter = refList.size();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[48]++;
    int index = 0;

    Preconditions.checkState(inputOrder.containsKey(inputId), inputId.getIdName());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[49]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[50]++;
    int sourceInputOrder = inputOrder.get(inputId);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[51]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[16]++;


    for (Reference ref : refList) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[16]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[17]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[18]++;
}
      Preconditions.checkNotNull(ref.getInputId());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[52]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[53]++;
      int order = inputOrder.get(ref.getInputId());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[54]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((order < sourceInputOrder) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[15]++;
        lastBefore = index;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[55]++;

      } else {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[16]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[56]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((order > sourceInputOrder) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[17]++;
        firstAfter = index;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[57]++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[58]++;
        break;

      } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[18]++;}
}
      index++;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[59]++;
    }
    return new SourceRefRange(refList, lastBefore, firstAfter);
  }

  private static class SourceRefRange {
    private final int lastBefore;
    private final int firstAfter;
    private final List<Reference> refList;

    SourceRefRange(List<Reference> refList, int lastBefore,
        int firstAfter) {
      this.lastBefore = Math.max(lastBefore, -1);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[60]++;
      this.firstAfter = Math.min(firstAfter, refList.size());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[61]++;
      this.refList = refList;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[62]++;
    }

    /** Note that the returned list is backed by {@code refList}! */
    List<Reference> refsBefore() {
      return refList.subList(0, lastBefore + 1);
    }

    /** Note that the returned list is backed by {@code refList}! */
    List<Reference> refsAfter() {
      return refList.subList(firstAfter, refList.size());
    }
  }

  /**
   * @param globalScope a new Global Scope to replace the scope of references
   *        with.
   */
  public void updateReferencesWithGlobalScope(Scope globalScope) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[63]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[19]++;


    for (ReferenceCollection collection : refMap.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[19]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[20]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[21]++;
}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[64]++;
      List<Reference> newRefs =
          Lists.newArrayListWithCapacity(collection.references.size());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[65]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[22]++;


      for (Reference ref : collection.references) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[22]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[23]--;
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.loops[24]++;
}
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[66]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ref.getScope() != globalScope) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[19]++;
          newRefs.add(ref.cloneWithNewScope(globalScope));
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[67]++;

        } else {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[20]++;
          newRefs.add(ref);
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[68]++;
        }
      }
      collection.references = newRefs;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[69]++;
    }
  }

  /**
   * A CleanupPass implementation that will replace references to old Syntactic
   * Global Scopes generated in previous compile runs with references to the
   * Global Typed Scope.
   *
   * @author tylerg@google.com (Tyler Goodwin)
   */
  static class GlobalVarRefCleanupPass implements HotSwapCompilerPass {

    private final AbstractCompiler compiler;

    public GlobalVarRefCleanupPass(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[70]++;
    }

    @Override
    public void hotSwapScript(Node scriptRoot, Node originalRoot) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[71]++;
      GlobalVarReferenceMap refMap = compiler.getGlobalVarReferences();
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[72]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((refMap != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[21]++;
        refMap.updateReferencesWithGlobalScope(compiler.getTopScope());
CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.statements[73]++;

      } else {
  CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d.branches[22]++;}
    }

    @Override
    public void process(Node externs, Node root) {
      // GlobalVarRefCleanupPass should not do work during process.
    }
  }
}

class CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d ());
  }
    public static long[] statements = new long[74];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GlobalVarReferenceMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$n4fc7u5dhg6t4zyb4uvzo6jfr3dg3uxp88qlfl9d () {
    super("com.google.javascript.jscomp.GlobalVarReferenceMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 73; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.GlobalVarReferenceMap.java");
      for (int i = 1; i <= 73; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

