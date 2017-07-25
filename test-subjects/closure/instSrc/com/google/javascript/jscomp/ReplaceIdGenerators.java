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
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;
import com.google.debugging.sourcemap.Base64;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Replaces calls to id generators with ids.
 *
 * Use this to get unique and short ids.
 *
 */
class ReplaceIdGenerators implements CompilerPass {
  static {
    CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.ping();
  }

  static final DiagnosticType NON_GLOBAL_ID_GENERATOR_CALL =
      DiagnosticType.error(
          "JSC_NON_GLOBAL_ID_GENERATOR_CALL",
          "Id generator call must be in the global scope");
  static {
    CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[1]++;
  }

  static final DiagnosticType CONDITIONAL_ID_GENERATOR_CALL =
      DiagnosticType.error(
          "JSC_CONDITIONAL_ID_GENERATOR_CALL",
          "Id generator call must be unconditional");
  static {
    CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[2]++;
  }

  static final DiagnosticType CONFLICTING_GENERATOR_TYPE =
      DiagnosticType.error(
          "JSC_CONFLICTING_ID_GENERATOR_TYPE",
          "Id generator can only be one of consistent, inconsistent, or stable.");
  static {
    CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[3]++;
  }

  static final DiagnosticType INVALID_GENERATOR_ID_MAPPING =
      DiagnosticType.error(
          "JSC_INVALID_GENERATOR_ID_MAPPING",
          "Invalid generator id mapping. {0}");
  static {
    CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[4]++;
  }

  private final AbstractCompiler compiler;
  private final Map<String, NameSupplier> nameGenerators;
  private final Map<String, Map<String, String>> consistNameMap;

  private final Map<String, Map<String, String>> idGeneratorMaps;
  private final Map<String, BiMap<String, String>> previousMap;

  private final boolean generatePseudoNames;

  public ReplaceIdGenerators(
      AbstractCompiler compiler, Set<String> idGens,
      boolean generatePseudoNames,
      String previousMapSerialized) {
    this.compiler = compiler;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[5]++;
    this.generatePseudoNames = generatePseudoNames;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[6]++;
    nameGenerators = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[7]++;
    idGeneratorMaps = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[8]++;
    consistNameMap = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[9]++;

    Map<String, BiMap<String, String>> previousMap;
    previousMap = parsePreviousResults(previousMapSerialized);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[10]++;
    this.previousMap = previousMap;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[11]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((idGens != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[1]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[1]++;


      for (String gen : idGens) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[1]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[2]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[3]++;
}
        nameGenerators.put(
            gen, createNameSupplier(RenameStrategy.INCONSISTENT, previousMap.get(gen)));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[14]++;
        idGeneratorMaps.put(gen, Maps.<String, String>newLinkedHashMap());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[15]++;
      }

    } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[2]++;}
  }

  private enum RenameStrategy {
    CONSISTENT,
    INCONSISTENT,
    STABLE
  }

  private static interface NameSupplier {
    String getName(String id, String name);
    RenameStrategy getRenameStrategy();
  }

  private static class ObfuscatedNameSuppier implements NameSupplier {
    private final NameGenerator generator;
    private final Map<String, String> previousMappings;
    private RenameStrategy renameStrategy;

    public ObfuscatedNameSuppier(
        RenameStrategy renameStrategy, BiMap<String, String> previousMappings) {
      this.previousMappings = previousMappings.inverse();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[16]++;
      this.generator =
          new NameGenerator(previousMappings.keySet(), "", null);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[17]++;
      this.renameStrategy = renameStrategy;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[18]++;
    }

    @Override
    public String getName(String id, String name) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[19]++;
      String newName = previousMappings.get(id);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((newName == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[3]++;
        newName = generator.generateNextName();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[21]++;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[4]++;}
      return newName;
    }

    @Override
    public RenameStrategy getRenameStrategy() {
      return renameStrategy;
    }
  }

  private static class PseudoNameSuppier implements NameSupplier {
    private int counter = 0;
  {
    CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[22]++;
  }
    private RenameStrategy renameStrategy;

    public PseudoNameSuppier(RenameStrategy renameStrategy) {
      this.renameStrategy = renameStrategy;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[23]++;
    }

    @Override
    public String getName(String id, String name) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((renameStrategy == RenameStrategy.INCONSISTENT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[5]++;
        return name + "$" + counter++;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[6]++;}
      return name + "$0";
    }

    @Override
    public RenameStrategy getRenameStrategy() {
      return renameStrategy;
    }
  }

  private static class StableNameSupplier implements NameSupplier {
    @Override
    public String getName(String id, String name) {
      return Base64.base64EncodeInt(name.hashCode());
    }
    @Override
    public RenameStrategy getRenameStrategy() {
      return RenameStrategy.STABLE;
    }
  }

  private NameSupplier createNameSupplier(
      RenameStrategy renameStrategy, BiMap<String, String> previousMappings) {
    previousMappings = previousMappings != null ?
        previousMappings :
        ImmutableBiMap.<String, String>of();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[25]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((renameStrategy == RenameStrategy.STABLE) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[7]++;
      return new StableNameSupplier();

    } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[8]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[27]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((generatePseudoNames) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[9]++;
      return new PseudoNameSuppier(renameStrategy);

    } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[10]++;
      return new ObfuscatedNameSuppier(renameStrategy, previousMappings);
    }
}
  }

  private class GatherGenerators extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[28]++;
      JSDocInfo doc = n.getJSDocInfo();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((doc == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[12]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[30]++;

      int numGeneratorAnnotations =
          (doc.isConsistentIdGenerator() ? 1 : 0) +
          (doc.isIdGenerator() ? 1 : 0) +
          (doc.isStableIdGenerator() ? 1 : 0);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((numGeneratorAnnotations == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[13]++;
        return;

      } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[14]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[32]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((numGeneratorAnnotations > 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[15]++;
        compiler.report(t.makeError(n, CONFLICTING_GENERATOR_TYPE));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[33]++;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[16]++;}
}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[34]++;

      String name = null;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[17]++;
        name = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[36]++;

      } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[18]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[37]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[19]++;
        name = n.getFirstChild().getString();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[38]++;

      } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[20]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[39]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)){
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[21]++;
        name = n.getFirstChild().getString();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[40]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[23]++;
          return;

        } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[24]++;}

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[22]++;}
}
}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;

      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((doc.isConsistentIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[25]++;
        consistNameMap.put(name, Maps.<String, String>newLinkedHashMap());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[43]++;
        nameGenerators.put(
            name, createNameSupplier(RenameStrategy.CONSISTENT, previousMap.get(name)));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[44]++;

      } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[26]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[45]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((doc.isStableIdGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[27]++;
        nameGenerators.put(
            name, createNameSupplier(RenameStrategy.STABLE, previousMap.get(name)));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[46]++;

      } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[28]++;
        nameGenerators.put(
            name, createNameSupplier(RenameStrategy.INCONSISTENT, previousMap.get(name)));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[47]++;
      }
}
      idGeneratorMaps.put(name, Maps.<String, String>newLinkedHashMap());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[48]++;
    }
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new GatherGenerators());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[49]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[50]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((nameGenerators.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[29]++;
      NodeTraversal.traverse(compiler, root, new ReplaceGenerators());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[51]++;

    } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[30]++;}
  }

  private class ReplaceGenerators extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[31]++;
        return;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[32]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[53]++;

      String callName = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[54]++;
      NameSupplier nameGenerator = nameGenerators.get(callName);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((nameGenerator == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[33]++;
        return;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[34]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[56]++;
int CodeCoverConditionCoverageHelper_C18;

      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((nameGenerator.getRenameStrategy() == RenameStrategy.INCONSISTENT) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[35]++;
        // Warn about calls not in the global scope.
        compiler.report(t.makeError(n, NON_GLOBAL_ID_GENERATOR_CALL));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[57]++;
        return;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[36]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[58]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((nameGenerator.getRenameStrategy() == RenameStrategy.INCONSISTENT) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[37]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[59]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[4]++;


        for (Node ancestor : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[4]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[5]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[6]++;
}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[60]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.isControlStructure(ancestor)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[39]++;
            // Warn about conditional calls.
            compiler.report(t.makeError(n, CONDITIONAL_ID_GENERATOR_CALL));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[61]++;
            return;

          } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[40]++;}
        }

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[38]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[62]++;

      Node id = n.getFirstChild().getNext();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[63]++;
int CodeCoverConditionCoverageHelper_C21;

      // TODO(user): Error on id not a string literal.
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((id.isString()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[41]++;
        return;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[42]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[64]++;

      Map<String, String> idGeneratorMap = idGeneratorMaps.get(callName);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[65]++;
      String rename = null;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[66]++;

      String name = id.getString();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[67]++;
      String instanceId = getIdForGeneratorNode(
          nameGenerator.getRenameStrategy() == RenameStrategy.CONSISTENT, id);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[68]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((nameGenerator.getRenameStrategy() == RenameStrategy.CONSISTENT) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[43]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[69]++;
        Map<String, String> entry = consistNameMap.get(callName);
        rename = entry.get(instanceId);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[70]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[71]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((rename == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[45]++;
          rename = nameGenerator.getName(instanceId, name);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[72]++;
          entry.put(instanceId, rename);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[73]++;

        } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[46]++;}

      } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[44]++;
        rename = nameGenerator.getName(instanceId, name);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[74]++;
      }

      parent.replaceChild(n, IR.string(rename));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[75]++;
      idGeneratorMap.put(rename, instanceId);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[76]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[77]++;
    }
  }

  /**
   * @return The serialize map of generators and their ids and their
   *     replacements.
   */
  public String getSerializedIdMappings() {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[78]++;
    StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[79]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[7]++;


    for (Map.Entry<String, Map<String, String>> replacements :
        idGeneratorMaps.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[7]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[8]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[9]++;
}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[80]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((replacements.getValue().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[47]++;
        sb.append("[");
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[81]++;
        sb.append(replacements.getKey());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[82]++;
        sb.append("]\n\n");
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[83]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[84]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[10]++;


        for (Map.Entry<String, String> replacement :
            replacements.getValue().entrySet()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[10]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[11]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[12]++;
}
          sb.append(replacement.getKey());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[85]++;
          sb.append(':');
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[86]++;
          sb.append(replacement.getValue());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[87]++;
          sb.append("\n");
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[88]++;
        }
        sb.append("\n");
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[89]++;

      } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[48]++;}
    }
    return sb.toString();
  }

  private Map<String, BiMap<String, String>> parsePreviousResults(
      String serializedMap) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[90]++;
int CodeCoverConditionCoverageHelper_C25;

    //
    // The expected format looks like this:
    //
    // [generatorName]
    // someId:someFile:theLine:theColumn
    //
    //

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((serializedMap == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((serializedMap.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[49]++;
      return Collections.emptyMap();

    } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[50]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[91]++;

    Map<String, BiMap<String, String>> resultMap = Maps.newHashMap();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[92]++;
    BufferedReader reader = new BufferedReader(new StringReader(serializedMap));
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[93]++;
    BiMap<String, String> currentSectionMap = null;

    String line;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[94]++;
    int lineIndex = 0;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[95]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[96]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[13]++;


      while ((line = reader.readLine()) != null) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[13]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[14]--;
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.loops[15]++;
}
        lineIndex++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[97]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[98]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((line.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[52]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[99]++;
          continue;

        } else {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[53]++;}
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[100]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((line.charAt(0) == '[') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[54]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[101]++;
          String currentSection = line.substring(1, line.length() - 1);
          currentSectionMap = resultMap.get(currentSection);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[102]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[103]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((currentSectionMap == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[56]++;
            currentSectionMap = HashBiMap.create();
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[104]++;
            resultMap.put(currentSection, currentSectionMap);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[105]++;

          } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[57]++;
            reportInvalidLine(line, lineIndex);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[106]++;
            return Collections.emptyMap();
          }

        } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[55]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[107]++;
          int split = line.indexOf(':');
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[108]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((split != -1) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[58]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[109]++;
            String name = line.substring(0, split);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[110]++;
            String location = line.substring(split + 1, line.length());
            currentSectionMap.put(name, location);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[111]++;

          } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[59]++;
            reportInvalidLine(line, lineIndex);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[112]++;
            return Collections.emptyMap();
          }
        }
      }
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[60]++;
      JSError.make(INVALID_GENERATOR_ID_MAPPING, e.getMessage());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[113]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[51]++;
}
  }
    return resultMap;
  }

  private void reportInvalidLine(String line, int lineIndex) {
    JSError.make(INVALID_GENERATOR_ID_MAPPING,
        "line(" + line + "): " + lineIndex);
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[114]++;
  }

  String getIdForGeneratorNode(boolean consistent, Node n) {
    Preconditions.checkState(n.isString());
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[115]++;
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.statements[116]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((consistent) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[61]++;
      return n.getString();

    } else {
CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt.branches[62]++;
      return n.getSourceFileName() + ':' + n.getLineno() + ":" + n.getCharno();
    }
  }
}

class CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt ());
  }
    public static long[] statements = new long[117];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReplaceIdGenerators.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,0,1,1,1,1,1};
    for (int i = 1; i <= 31; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$iznp6nmz2lj89s5ld8q93btvlea24gi0qb7tt () {
    super("com.google.javascript.jscomp.ReplaceIdGenerators.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 116; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ReplaceIdGenerators.java");
      for (int i = 1; i <= 116; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

