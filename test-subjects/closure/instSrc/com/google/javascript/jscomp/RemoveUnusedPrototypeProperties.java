/*
 * Copyright 2006 The Closure Compiler Authors.
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
import com.google.javascript.jscomp.AnalyzePrototypeProperties.AssignmentProperty;
import com.google.javascript.jscomp.AnalyzePrototypeProperties.GlobalFunction;
import com.google.javascript.jscomp.AnalyzePrototypeProperties.LiteralProperty;
import com.google.javascript.jscomp.AnalyzePrototypeProperties.NameInfo;
import com.google.javascript.jscomp.AnalyzePrototypeProperties.Symbol;
import com.google.javascript.rhino.Node;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * Removes unused properties from prototypes.
 *
 */
class RemoveUnusedPrototypeProperties implements
    SpecializationAwareCompilerPass {
  static {
    CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.ping();
  }


  private static final Logger logger =
    Logger.getLogger(RemoveUnusedPrototypeProperties.class.getName());
  static {
    CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final boolean canModifyExterns;
  private final boolean anchorUnusedVars;
  private SpecializeModule.SpecializationState specializationState;

  /**
   * Creates a new pass for removing unused prototype properties, based
   * on the uniqueness of property names.
   * @param compiler The compiler.
   * @param canModifyExterns If true, then we can remove prototype
   *     properties that are declared in the externs file.
   * @param anchorUnusedVars If true, then we must keep unused variables
   *     and the prototype properties they reference, even if they are
   *     never used.
   */
  RemoveUnusedPrototypeProperties(AbstractCompiler compiler,
      boolean canModifyExterns,
      boolean anchorUnusedVars) {
    this.compiler = compiler;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[2]++;
    this.canModifyExterns = canModifyExterns;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[3]++;
    this.anchorUnusedVars = anchorUnusedVars;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[4]++;
  }

  @Override
  public void enableSpecialization(SpecializeModule.SpecializationState state) {
    this.specializationState = state;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[5]++;
  }

  @Override
  public void process(Node externRoot, Node root) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[6]++;
    AnalyzePrototypeProperties analyzer =
        new AnalyzePrototypeProperties(compiler,
            null /* no module graph */, canModifyExterns, anchorUnusedVars);
    analyzer.process(externRoot, root);
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[7]++;
    removeUnusedSymbols(analyzer.getAllNameInfo());
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[8]++;
  }

  /**
   * Remove all properties under a given name if the property name is
   * never referenced.
   */
  private void removeUnusedSymbols(Collection<NameInfo> allNameInfo) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[9]++;
    boolean changed = false;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[1]++;


    for (NameInfo nameInfo : allNameInfo) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[1]--;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[2]--;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[3]++;
}
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameInfo.isReferenced()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[1]++;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[4]++;


        for (Symbol declaration : nameInfo.getDeclarations()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[4]--;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[5]--;
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.loops[6]++;
}
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[13]++;
          boolean canRemove = false;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;

          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((specializationState == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[3]++;
            canRemove = true;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[15]++;

          } else {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[4]++;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[16]++;
            Node specializableFunction =
              getSpecializableFunctionFromSymbol(declaration);
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((specializableFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[5]++;
              specializationState.reportRemovedFunction(
                  specializableFunction, null);
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[18]++;
              canRemove = true;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[19]++;

            } else {
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[6]++;}
          }
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;

          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((canRemove) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[7]++;
            declaration.remove();
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[21]++;
            changed = true;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[22]++;

          } else {
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[8]++;}
        }

        logger.fine("Removed unused prototype property: " + nameInfo.name);
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[23]++;

      } else {
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[2]++;}
    }
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[9]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[25]++;

    } else {
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[10]++;}
  }

  /**
   * Attempts to find a specializable function from the Symbol.
   */
  private Node getSpecializableFunctionFromSymbol(Symbol symbol) {
    Preconditions.checkNotNull(specializationState);
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[26]++;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[27]++;

    Node specializableFunction = null;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((symbol instanceof GlobalFunction) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[11]++;
      specializableFunction = ((GlobalFunction) symbol).getFunctionNode();
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[29]++;

    } else {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[12]++;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[30]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((symbol instanceof AssignmentProperty) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[13]++;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[31]++;
      Node propertyValue = ((AssignmentProperty) symbol).getValue();
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((propertyValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[15]++;
        specializableFunction = propertyValue;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[33]++;

      } else {
  CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[16]++;}

    } else {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[14]++;
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[34]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((symbol instanceof LiteralProperty) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[17]++;
      // Module specialization doesn't know how to handle these
      // because the "name" of the function isn't the name
      // it needs to add an unspecialized version of.

      return null;

    } else {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[18]++;
      Preconditions.checkState(false, "Should be unreachable.");
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[35]++;
    }
}
}
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((specializableFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((specializationState.canFixupFunction(specializableFunction)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[19]++;
      return specializableFunction;

    } else {
CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l.branches[20]++;
      return null;
    }
  }
}

class CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RemoveUnusedPrototypeProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 10; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$41vdoap6bqvvh52i09jnbzup6x7zrl0zp4lio1kji4axaehqqbxwxq3l () {
    super("com.google.javascript.jscomp.RemoveUnusedPrototypeProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RemoveUnusedPrototypeProperties.java");
      for (int i = 1; i <= 36; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

