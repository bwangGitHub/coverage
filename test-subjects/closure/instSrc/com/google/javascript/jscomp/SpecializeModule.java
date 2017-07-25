/*
 * Copyright 2010 The Closure Compiler Authors.
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
import com.google.common.collect.Sets;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Beginnings of an optimization to specialize the initial module at the cost of
 * increasing code in later modules. This is still very experimental.
 *
 * High-level overview:
 *
 * This optimization replaces functions in the initial module with specialized
 * versions that are only safe in the initial module. The original, general,
 * versions of the functions are "fixed up" in later modules. This optimization
 * can shrink the initial module significantly but the fixup code in later
 * modules increases overall code size.
 *
 * Implementation approach:
 *
 * We take a ridiculously naive approach: remove the initial module
 * from the rest of the AST, optimize it with existing optimization passes
 * (recording which functions have been specialized), put it back in the AST,
 * and add fixups restoring the general versions of the functions in each module
 * that depends on the initial module.
 *
 * Since it is only safe to specialize functions that can be fixed up, we
 * don't allow specialization of local functions and functions that
 * are aliased.
 *
 * We currently run three optimizations on the isolated AST: InlineFunctions,
 * DevirtualizePrototypeMethods, and RemoveUnusedPrototypeProperties.
 *
 * These optimizations rely on a coarse-grained name-based analysis to
 * maintain safety properties and thus are likely to see some benefit when
 * applied in isolation.
 *
 * InlineFunctions is truly specializing -- it replaces functions with
 * versions that have calls to other functions inlined into them, while
 * RemoveUnusedPrototypeProperties is really just removing properties that
 * aren't used in the initial module and adding copies further down in the
 * module graph. It would probably be more elegant to give
 * CrossModuleMethodMotion permission to make copies of methods instead.
 *
 * There are additional passes that might benefit from being made
 * specialization-aware:
 *
 * - OptimizeParameters
 *
 * - Any pass that is too slow to run over the entire AST but might
 *      be acceptable on only the initial module:
 *  - RemoveUnusedNames
 *
 *  - Also, any pass that uses the results of PureFunctionIdentifier to
 *  determine when it is safe to remove code might benefit (e.g. the peephole
 *  passes), since PureFunctionIdentifier relies on SimpleDefinitionFinder,
 *  which would be more precise when running on only the initial module.
 *
 * @author dcc@google.com (Devin Coughlin)
 */
class SpecializeModule implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.ping();
  }

  private AbstractCompiler compiler;

  private Map<Node, Node> specializedInputRootsByOriginal;

  private Map<Node, OriginalFunctionInformation>
      functionInfoBySpecializedFunctionNode;

  private SpecializationState specializationState;

  private final PassFactory[] specializationPassFactories;

  public SpecializeModule(AbstractCompiler compiler,
      PassFactory ...specializationPassFactories) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[1]++;
    this.specializationPassFactories = specializationPassFactories;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[2]++;
  }

  /**
   * Performs initial module specialization.
   *
   * The process is as follows:
   *
   * 1) Make a copy of each of the inputs in the initial root and put them
   * in a fake AST that looks like it is the whole program.
   *
   * 2) Run the specializing compiler passes over the fake initial module AST
   * until it reaches a fixed point, recording which functions are specialized
   * or removed.
   *
   * 3) Replace the original input roots with the specialized input roots
   *
   * 4) For each module that directly depends on the initial module, add
   * fixups for the specialized and removed functions. Right now we add
   * fixups for for every function that was specialized or removed -- we could
   * be smarter about this and for each dependent module only add the functions
   * that it needs.
   *
   * 5) Add dummy variables declaring the removed function to the end of
   * the now-specialized initial module. This is needed to keep
   * {@link VarCheck} from complaining.
   */
  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[3]++;
    JSModuleGraph moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

    // Can't perform optimization without a module graph!
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((moduleGraph == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[2]++;}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[5]++;

    JSModule module = moduleGraph.getRootModule();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[6]++;

    Node fakeModuleRoot = copyModuleInputs(module);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[7]++;

    SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);

    defFinder.process(externs, fakeModuleRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[8]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[9]++;

    SimpleFunctionAliasAnalysis initialModuleFunctionAliasAnalysis =
        new SimpleFunctionAliasAnalysis();

    initialModuleFunctionAliasAnalysis.analyze(defFinder);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[10]++;

    specializationState =
        new SpecializationState(initialModuleFunctionAliasAnalysis);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[11]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[1]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[2]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[3]++;
}
      specializationState.resetHasChanged();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[13]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[4]++;



      for (SpecializationAwareCompilerPass pass : createSpecializingPasses()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[4]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[5]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[6]++;
}
        pass.enableSpecialization(specializationState);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[15]++;
        pass.process(externs, fakeModuleRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[16]++;
      }
    } while((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((specializationState.hasChanged()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false));

    // We must always add dummy variables before replacing the original module.
    addDummyVarDeclarationsToInitialModule(module);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[17]++;
    replaceOriginalModuleInputsWithSpecialized();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[18]++;
    addOriginalFunctionVersionsToDependentModules(module);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[19]++;
  }

  /**
   * Returns a collection of new instances of specializing passes.
   */
  private Collection<SpecializationAwareCompilerPass>
      createSpecializingPasses() {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[20]++;

    Collection<SpecializationAwareCompilerPass> passes = Lists.newLinkedList();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[21]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[7]++;



    for (PassFactory passFactory : specializationPassFactories) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[7]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[8]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[9]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[22]++;
      CompilerPass pass = passFactory.create(compiler);

      Preconditions.checkState(pass instanceof
          SpecializationAwareCompilerPass);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[23]++;

      passes.add((SpecializationAwareCompilerPass) pass);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[24]++;
    }

    return passes;
  }

  /**
   * Creates an AST that consists solely of copies of the input roots for the
   * passed in module.
   *
   * Also records a map in {@link #functionInfoBySpecializedFunctionNode}
   * of information about the original function keyed on the copies of the
   * functions to specialized.
   */
  private Node copyModuleInputs(JSModule module) {

    specializedInputRootsByOriginal = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[25]++;

    functionInfoBySpecializedFunctionNode = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[26]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[27]++;

    Node syntheticModuleJsRoot = IR.block();
    syntheticModuleJsRoot.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[28]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[29]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[10]++;



    for (CompilerInput input : module.getInputs()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[10]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[11]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[12]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[30]++;
      Node originalInputRoot = input.getAstRoot(compiler);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[31]++;

      Node copiedInputRoot = originalInputRoot.cloneTree();
      copiedInputRoot.copyInformationFromForTree(originalInputRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[32]++;

      specializedInputRootsByOriginal.put(originalInputRoot,
          copiedInputRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[33]++;

      matchTopLevelFunctions(originalInputRoot, copiedInputRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[34]++;

      syntheticModuleJsRoot.addChildToBack(copiedInputRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[35]++;
    }
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[36]++;

    // The jsRoot needs a parent (in a normal compilation this would be the
    // node that contains jsRoot and the externs).
    Node syntheticExternsAndJsRoot = IR.block();
    syntheticExternsAndJsRoot.addChildToBack(syntheticModuleJsRoot);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[37]++;

    return syntheticModuleJsRoot;
  }

  /**
   * Records information about original functions and creates a map from
   * the specialized functions to this information.
   *
   * This information is only recorded for global functions since non-global
   * functions cannot be inlined.
   *
   * @param original An original input root.
   * @param toBeSpecialized A copy of the input root (the copy to be
   * specialized)
   */
  private void matchTopLevelFunctions(Node original, Node toBeSpecialized) {
    new NodeMatcher() {
      @Override
      public void reportMatch(Node original, Node specialized) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[39]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((original.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[3]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[40]++;
          OriginalFunctionInformation functionInfo =
              new OriginalFunctionInformation(original);

          functionInfoBySpecializedFunctionNode.put(specialized,
              functionInfo);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[41]++;

        } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[4]++;}
      }

      @Override
      public boolean shouldTraverse(Node n1, Node n2) {
        return !n1.isFunction();
      }
    }.match(original, toBeSpecialized);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[38]++;
  }

  /**
   * Replaces the original input roots of the initial module with
   * their specialized versions.
   *
   * (Since {@link JsAst} holds a pointer to original inputs roots, we actually
   * replace the all the children of the root rather than swapping the
   * root pointers).
   */
  private void replaceOriginalModuleInputsWithSpecialized() {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[42]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[13]++;


    for (Node original : specializedInputRootsByOriginal.keySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[13]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[14]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[15]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[43]++;
      Node specialized = specializedInputRootsByOriginal.get(original);

      original.removeChildren();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[44]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[45]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[16]++;


int CodeCoverConditionCoverageHelper_C4;

      while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((specialized.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[16]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[17]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[18]++;
}
        original.addChildToBack(specialized.removeFirstChild());
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[46]++;
      }
    }
  }

  /**
   * Adds dummy variable declarations for all the function declarations we've
   * removed to the end of the initial module.
   *
   * We do this to make {@link VarCheck} happy, since it requires variables to
   * be declared before they are used in the whole program AST and doesn't
   * like it when they are declared multiple times.
   *
   * TODO(dcc): Be smarter about whether we need a VAR here or not.
   */
  private void addDummyVarDeclarationsToInitialModule(JSModule module) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[47]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[19]++;


    for (Node modifiedFunction :
      functionInfoBySpecializedFunctionNode.keySet()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[19]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[20]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[21]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[48]++;
int CodeCoverConditionCoverageHelper_C5;
     if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((specializationState.getRemovedFunctions().contains(modifiedFunction)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[5]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[49]++;
       OriginalFunctionInformation originalInfo =
         functionInfoBySpecializedFunctionNode.get(modifiedFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[50]++;
int CodeCoverConditionCoverageHelper_C6;

       if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((originalInfo.name != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((originalInfo.originalWasDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[7]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[51]++;
         Node block = specializationState.removedFunctionToBlock.get(
             modifiedFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[52]++;
int CodeCoverConditionCoverageHelper_C7;

         // Declaring block might be null if no fix-up declarations is needed.
         // For example, InlineFunction can inline an anonymous function call or
         // anything with prototype property requires no dummy declaration
         // fix-ups afterward.
         if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((block != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[9]++;
           block.addChildrenToBack(originalInfo.generateDummyDeclaration());
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[53]++;

         } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[10]++;}

       } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[8]++;}

     } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[6]++;}
    }
  }

  /**
   * Adds a copy of the original versions of specialized/removed functions
   * to each of the dependents of module.
   *
   * Currently we add all of these functions to all dependents; it
   * would be more efficient to only add the functions that could be used.
   *
   * TODO(dcc): Only add fixup functions where needed.
   */
  private void addOriginalFunctionVersionsToDependentModules(JSModule module) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[54]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[22]++;


    for (JSModule directDependent : getDirectDependents(module)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[22]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[23]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[24]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[55]++;
      CompilerInput firstInput = directDependent.getInputs().get(0);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[56]++;
      Node firstInputRootNode = firstInput.getAstRoot(compiler);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[57]++;

      // We don't iterate specializedFunctions directly because want to maintain
      // and specializedFunctions in source order, rather than
      // in the order that some optimization specialized the function.

      // So since we're adding to the front of the module each time, we
      // have to iterate in reverse source order.

      List<Node> possiblyModifiedFunctions =
        Lists.newArrayList(functionInfoBySpecializedFunctionNode.keySet());

      Collections.reverse(possiblyModifiedFunctions);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[58]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[59]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[25]++;



      for (Node modifiedFunction : possiblyModifiedFunctions) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[25]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[26]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[27]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[60]++;
        boolean declarationWasSpecialized =
          specializationState.getSpecializedFunctions()
          .contains(modifiedFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[61]++;

        boolean declarationWasRemoved =
            specializationState.getRemovedFunctions()
            .contains(modifiedFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[62]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((declarationWasSpecialized) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((declarationWasRemoved) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[11]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[63]++;
          OriginalFunctionInformation originalInfo =
               functionInfoBySpecializedFunctionNode.get(modifiedFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[64]++;
int CodeCoverConditionCoverageHelper_C9;

           // Don't add unspecialized versions of anonymous functions
           if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((originalInfo.name != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[13]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[65]++;
             Node newDefinition =
               originalInfo.generateFixupDefinition();

             firstInputRootNode.addChildrenToFront(newDefinition);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[66]++;

           } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[12]++;}
      }
    }
  }

  /**
   * Returns a list of modules that directly depend on the given module.
   *
   * This probably deserves to be in JSModuleGraph.
   */
  public Collection<JSModule> getDirectDependents(JSModule module) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[67]++;
    Set<JSModule> directDependents = Sets.newHashSet();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[68]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[28]++;



    for (JSModule possibleDependent :
          compiler.getModuleGraph().getAllModules()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[28]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[29]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[30]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[69]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((possibleDependent.getDependencies().contains(module)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[15]++;
        directDependents.add(possibleDependent);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[70]++;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[16]++;}
    }

    return directDependents;
  }

  /**
   * A simple abstract classes that takes two isomorphic ASTs and walks
   * each of them together, reporting matches to subclasses.
   *
   * This could probably be hardened and moved to NodeUtil
   */
  private abstract static class NodeMatcher {

    /**
     * Calls {@link #reportMatch(Node, Node)} for each pair of matching nodes
     * from the two ASTs.
     *
     * The two ASTs must be isomorphic. Currently no error checking is
     * performed to ensure that this is the case.
     */
    public void match(Node ast1, Node ast2) {
      // Just blunder ahead and assume that the two nodes actually match

      reportMatch(ast1, ast2);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[71]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[72]++;
int CodeCoverConditionCoverageHelper_C11;

      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((shouldTraverse(ast1, ast2)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[17]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[73]++;
        Node childOf1 = ast1.getFirstChild();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[74]++;
        Node childOf2 = ast2.getFirstChild();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[75]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[31]++;


int CodeCoverConditionCoverageHelper_C12;

        while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((childOf1 != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[31]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[32]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[33]++;
}
          match(childOf1, childOf2);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[76]++;
          childOf1 = childOf1.getNext();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[77]++;
          childOf2 = childOf2.getNext();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[78]++;
        }

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[18]++;}

    }

    /**
     * Subclasses should override to add their own behavior when two nodes
     * are matched.
     * @param n1 A node from the AST passed as ast1 in
     * {@link #match(Node, Node)}.
     * @param n2 A node from the AST passed as ast1 in
     * {@link #match(Node, Node)}.
     */
    public abstract void reportMatch(Node n1, Node n2);

    /**
     * Subclasses should override to determine whether matching should proceed
     * under a subtree.
     */
    public boolean shouldTraverse(Node node1, Node n2) {
      return true;
    }
  }

  /**
   * A class that stores information about the original version of a
   * function that will be/was specialized or removed.
   *
   * This class stores:
   * - how the function was defined
   * - a copy of the original function
   */
  private class OriginalFunctionInformation {
    private String name;

    /**
     *  a = function() {} if true;
     *  function a() {} otherwise
     */
    private boolean isAssignFunction;

    private boolean assignHasVar;

    private Node originalFunctionCopy;

    public OriginalFunctionInformation(Node originalFunction) {
      name = NodeUtil.getFunctionName(originalFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[79]++;

      originalFunctionCopy = originalFunction.cloneTree();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[80]++;
      originalFunctionCopy.copyInformationFromForTree(originalFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[81]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[82]++;

      Node originalParent = originalFunction.getParent();

      isAssignFunction = originalParent.isAssign() ||
          originalParent.isName();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[83]++;

      assignHasVar =
          isAssignFunction && originalParent.getParent().isVar();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[84]++;
    }

    private Node copiedOriginalFunction() {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[85]++;
      // Copy of a copy
      Node copy = originalFunctionCopy.cloneTree();
      copy.copyInformationFromForTree(originalFunctionCopy);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[86]++;

      return copy;
    }

    /**
     * Did the original function add its name to scope?
     * (If so, and specialization removes it, then we'll have to
     * add a VAR for it so VarCheck doesn't complain).
     */
    private boolean originalWasDeclaration() {
      return (!isAssignFunction) || (assignHasVar);
    }

    /**
     * Generates a definition of the original function that can be added as
     * a fixup in the modules that directly depend on the specialized module.
     *
     * <PRE>
     * The trick here is that even if the original function is declared as:
     *
     * function foo() {
     *   // stuff
     * }
     *
     * the fixup will have to be of the form
     *
     * foo = function() {
     *   // stuff
     * }
     * </PRE>
     *
     */
    private Node generateFixupDefinition() {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[87]++;
      Node functionCopy = copiedOriginalFunction();

      Node nameNode;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[88]++;
int CodeCoverConditionCoverageHelper_C13;

      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isAssignFunction) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[19]++;
        nameNode =
           NodeUtil.newQualifiedNameNode(
               compiler.getCodingConvention(), name, functionCopy, name);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[89]++;

      } else {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[20]++;
        // Grab the name node from the original function and make that
        // function anonymous.
        nameNode = functionCopy.getFirstChild();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[90]++;
        functionCopy.replaceChild(nameNode,
            NodeUtil.newName(compiler.getCodingConvention(), "", nameNode));
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[91]++;
      }
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[92]++;

      Node assignment = IR.assign(nameNode, functionCopy);
      assignment.copyInformationFrom(functionCopy);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[93]++;

      return NodeUtil.newExpr(assignment);
    }

    /**
     * Returns a new dummy var declaration for the function with no initial
     * value:
     *
     * var name;
     */
    private Node generateDummyDeclaration() {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[94]++;
      Node declaration = NodeUtil.newVarNode(name, null);
      declaration.copyInformationFromForTree(originalFunctionCopy);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[95]++;

      return declaration;
    }
  }

  /**
   * A class to hold state during SpecializeModule. An instance of this class
   * is passed to specialization-aware compiler passes -- they use it to
   * communicate with SpecializeModule.
   *
   * SpecializationAware optimizations are required to keep track of the
   * functions they remove and the functions that they modify so that the fixups
   * can be added. However, not all functions can be fixed up.
   *
   * Specialization-aware classes *must* call
   * {@link #reportSpecializedFunction} when a function is modified during
   * specialization and {@link #reportRemovedFunction} when one is removed.
   *
   * Also, when specializing, they must query {@link #canFixupFunction}
   * before modifying a function.
   *
   * This two-way communication, is the reason we don't use
   * {@link AstChangeProxy} to report code changes.
   */
  public static class SpecializationState {

    /**
     * The functions that the pass has specialized. These functions will
     * be fixed up in non-specialized modules to their more general versions.
     *
     * This field is also used to determine whether specialization is enabled.
     * If not null, specialization is enabled, otherwise it is disabled.
     */
    private Set<Node> specializedFunctions;

    /**
     * The functions that the pass has removed. These functions will be
     * redefined in non-specialized modules.
     */
    private Set<Node> removedFunctions;

    private Map<Node, Node> removedFunctionToBlock;

    private SimpleFunctionAliasAnalysis initialModuleAliasAnalysis;

    /** Will be true if any new functions have been removed or specialized since
     * {@link #resetHasChanged}.
     */
    private boolean hasChanged = false;
  {
    CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[96]++;
  }

    public SpecializationState(SimpleFunctionAliasAnalysis
        initialModuleAliasAnalysis) {

      this.initialModuleAliasAnalysis = initialModuleAliasAnalysis;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[97]++;

      specializedFunctions = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[98]++;
      removedFunctions = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[99]++;
      removedFunctionToBlock = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[100]++;
    }

    /**
     * Returns true if any new functions have been reported as removed or
     * specialized since {@link #resetHasChanged()} was last called.
     */
    private boolean hasChanged() {
      return hasChanged;
    }

    private void resetHasChanged() {
      hasChanged = false;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[101]++;
    }

    /**
     * Returns the functions specialized by this compiler pass.
     */
    public Set<Node> getSpecializedFunctions() {
      return specializedFunctions;
    }

    /**
     * Reports that a function has been specialized.
     *
     * @param functionNode A specialized AST node with type Token.FUNCTION
     */
    public void reportSpecializedFunction(Node functionNode) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[102]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((specializedFunctions.add(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[21]++;
        hasChanged = true;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[103]++;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[22]++;}
    }

    /**
     * Reports that the function containing the node has been specialized.
     */
    public void reportSpecializedFunctionContainingNode(Node node) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[104]++;
      Node containingFunction = containingFunction(node);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[105]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((containingFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[23]++;
        reportSpecializedFunction(containingFunction);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[106]++;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[24]++;}
    }

    /**
     * The functions removed by this compiler pass.
     */
    public Set<Node> getRemovedFunctions() {
      return removedFunctions;
    }

    /**
     * Reports that a function has been removed.
     *
     * @param functionNode A removed AST node with type Token.FUNCTION
     * @param declaringBlock If the function declaration puts a variable in the
     *    scope, we need to have a VAR statement in the scope where the
     *    function is declared. Null if the function does not put a name
     *    in the scope.
     */
    public void reportRemovedFunction(Node functionNode, Node declaringBlock) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[107]++;
int CodeCoverConditionCoverageHelper_C16;
      // Depends when we were notified, functionNode.getParent might or might
      // not be null. We are going to force the user to tell us the parent
      // instead.
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((removedFunctions.add(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[25]++;
        hasChanged = true;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[108]++;
        removedFunctionToBlock.put(functionNode, declaringBlock);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[109]++;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[26]++;}
    }

    /**
     * Returns true if the function can be fixed up (that is, if it can be
     * safely removed or specialized).
     *
     * <p>In order to be safely fixed up, a function must be:
     * <PRE>
     * - in the global scope
     * - not aliased in the initial module
     * - of one of the following forms:
     *    function f() {}
     *    var f = function() {}
     *    f = function(){}
     *    var ns = {}; ns.f = function() {}
     *    SomeClass.prototype.foo = function() {};
     * </PRE>
     *
     * <p>Anonymous functions cannot be safely fixed up, nor can functions
     * that have been aliased.
     *
     * <p>Some functions declared as object literals could be safely fixed up,
     * however we do not currently support this.
     */
    public boolean canFixupFunction(Node functionNode) {
      Preconditions.checkArgument(functionNode.isFunction());
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[110]++;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[111]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((nodeIsInGlobalScope(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((initialModuleAliasAnalysis.isAliased(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[27]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[28]++;}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[112]++;
int CodeCoverConditionCoverageHelper_C18;

      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatement(functionNode)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[29]++;
        // function F() {}
        return true;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[30]++;}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[113]++;

      Node parent = functionNode.getParent();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[114]++;
      Node gramps = parent.getParent();
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[115]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((gramps.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[31]++;
        // var f = function() {}
        return true;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[32]++;}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[116]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((NodeUtil.isExprAssign(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((parent.getChildAtIndex(1) == functionNode) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[33]++;
        // f = function() {}
        // ns.f = function() {}
        return true;

      } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[34]++;}

      return false;
    }

    /**
     * Returns true if the function containing n can be fixed up.
     * Also returns true if n is in the global scope -- since it is always safe
     * to specialize the global scope.
     */
    public boolean canFixupSpecializedFunctionContainingNode(Node n) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[117]++;
      Node containingFunction = containingFunction(n);
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[118]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((containingFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[35]++;
        return canFixupFunction(containingFunction);

      } else {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[36]++;
        // Always safe to specialize the global scope
        return true;
      }
    }

    /**
     * Returns true if a node is in the global scope; false otherwise.
     */
    private boolean nodeIsInGlobalScope(Node node) {
      return containingFunction(node) == null;
    }

    /**
     * Returns the function containing the node, or null if none exists.
     */
    private Node containingFunction(Node node) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[119]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[34]++;


      for (Node ancestor : node.getAncestors()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[34]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[35]--;
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.loops[36]++;
}
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.statements[120]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ancestor.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[37]++;
          return ancestor;

        } else {
  CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5.branches[38]++;}
      }

      return null;
    }
  }
}

class CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5 ());
  }
    public static long[] statements = new long[121];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SpecializeModule.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,2,1,2,2,1,1};
    for (int i = 1; i <= 22; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$1xb4rrf9zt5z5078oro3t3yn346okl9b5 () {
    super("com.google.javascript.jscomp.SpecializeModule.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 120; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SpecializeModule.java");
      for (int i = 1; i <= 120; i++) {
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
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

