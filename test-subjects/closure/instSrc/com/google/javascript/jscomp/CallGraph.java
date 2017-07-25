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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.NameReferenceGraph.Name;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.graph.DiGraph;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * A pass the uses a {@link DefinitionProvider} to compute a call graph for an
 * AST.
 *
 * <p>A {@link CallGraph} connects {@link Function}s to {@link Callsite}s and
 * vice versa: each function in the graph links to the callsites it contains and
 * each callsite links to the functions it could call. Similarly, each callsite
 * links to the function that contains it and each function links to the
 * callsites that could call it.
 *
 * <p>The callgraph is not precise. That is, a callsite may indicate it can
 * call a function when in fact it does not do so in the running program.
 *
 * <p>The callgraph is also not complete: in some cases it may be unable to
 * determine some targets of a callsite. In this case,
 * Callsite.hasUnknownTarget() will return true.
 *
 * <p>The CallGraph doesn't (currently) have functions for externally defined
 * functions; however, callsites that target externs will have hasExternTarget()
 * return true.
 *
 * <p>TODO(dcc): Have CallGraph (optionally?) include functions for externs.
 *
 * @author dcc@google.com (Devin Coughlin)
 */
public class CallGraph implements CompilerPass {
  static {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.ping();
  }

  private AbstractCompiler compiler;

  /**
   * Maps an AST node (with type Token.CALL or Token.NEW) to a Callsite object.
   */
  private Map<Node, Callsite> callsitesByNode;

  /** Maps an AST node (with type Token.FUNCTION) to a Function object. */
  private Map<Node, Function> functionsByNode;

  /**
   * Will the call graph support looking up the callsites that could call a
   * given function?
   */
  private boolean computeBackwardGraph;

  /**
   * Will the call graph support looking up the functions that a given callsite
   * can call?
   */
  private boolean computeForwardGraph;

  /**
   * If true, then the callgraph will use NameReferenceGraph as a
   * definition provider; otherwise, use the faster SimpleDefinitionProvider.
   */
  private boolean useNameReferenceGraph = false;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[1]++;
  }

  /** Has the CallGraph already been constructed? */
  private boolean alreadyRun = false;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[2]++;
  }

  /** The name we give the main function. */
  @VisibleForTesting
  public static final String MAIN_FUNCTION_NAME = "{main}";
  static {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[3]++;
  }

  /**
   *  Represents the global function. Calling getBody() on this
   *  function will yield the global script/block.
   *
   *  TODO(dcc): having a single main function is somewhat misleading. Perhaps
   *  it might be better to make CallGraph module aware and have one per
   *  module?
   */
  private Function mainFunction;

  /**
   * Creates a call graph object supporting the specified lookups.
   *
   * At least one (and possibly both) of computeForwardGraph and
   * computeBackwardGraph must be true.
   *
   * @param compiler The compiler
   * @param computeForwardGraph Should the call graph allow lookup of the target
   *        functions a given callsite could call?
   * @param computeBackwardGraph Should the call graph allow lookup of the
   *        callsites that could call a given function?
   */
  public CallGraph(AbstractCompiler compiler, boolean computeForwardGraph,
      boolean computeBackwardGraph) {
    Preconditions.checkArgument(computeForwardGraph || computeBackwardGraph);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[4]++;

    this.compiler = compiler;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[5]++;

    this.computeForwardGraph = computeForwardGraph;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[6]++;
    this.computeBackwardGraph = computeBackwardGraph;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[7]++;

    callsitesByNode = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[8]++;
    functionsByNode = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[9]++;
  }

  /**
   * Creates a call graph object support both forward and backward lookups.
   */
  public CallGraph(AbstractCompiler compiler) {
    this(compiler, true, true);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[10]++;
  }

  /**
   * Builds a call graph for the given externsRoot and jsRoot.
   * This method must not be called more than once per CallGraph instance.
   */
  @Override
  public void process(Node externsRoot, Node jsRoot) {
    Preconditions.checkState(alreadyRun == false);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[11]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[12]++;

    DefinitionProvider definitionProvider =
        constructDefinitionProvider(externsRoot, jsRoot);

    createFunctionsAndCallsites(jsRoot, definitionProvider);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[13]++;

    fillInFunctionInformation(definitionProvider);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[14]++;

    alreadyRun = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[15]++;
  }

  /**
   * Returns the call graph Function object corresponding to the provided
   * AST Token.FUNCTION node, or null if no such object exists.
   */
  public Function getFunctionForAstNode(Node functionNode) {
    Preconditions.checkArgument(functionNode.isFunction());
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[16]++;

    return functionsByNode.get(functionNode);
  }

  /**
   * Returns a Function object representing the "main" global function.
   */
  public Function getMainFunction() {
    return mainFunction;
  }

  /**
   * Returns a collection of all functions (including the main function)
   * in the call graph.
   */
  public Collection<Function> getAllFunctions() {
    return functionsByNode.values();
  }

  /**
   * Finds a function with the given name. Throws an exception if
   * there are no functions or multiple functions with the name. This is
   * for testing purposes only.
   */
  @VisibleForTesting
  public Function getUniqueFunctionWithName(final String desiredName) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[17]++;
    Collection<Function> functions =
        Collections2.<Function>filter(getAllFunctions(),
            new Predicate<Function>() {
        @Override
        public boolean apply(Function function) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[18]++;

          String functionName = function.getName();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
          // Anonymous functions will have null names,
          // so it is important to handle that correctly here
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((desiredName != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[1]++;
            return desiredName.equals(functionName);

          } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[2]++;
            return desiredName == functionName;
          }
        }
      });
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((functions.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[3]++;
      return functions.iterator().next();

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[4]++;
      throw new IllegalStateException("Found " + functions.size()
          + " functions with name " + desiredName);
    }
  }

  /**
   * Returns the call graph Callsite object corresponding to the provided
   * AST Token.CALL or Token.NEW node, or null if no such object exists.
   */
  public Callsite getCallsiteForAstNode(Node callsiteNode) {
    Preconditions.checkArgument(callsiteNode.isCall() ||
        callsiteNode.isNew());
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[21]++;

    return callsitesByNode.get(callsiteNode);
  }

  /**
   * Returns a collection of all callsites in the call graph.
   */
  public Collection<Callsite> getAllCallsites() {
   return callsitesByNode.values();
  }

  /**
   * Creates {@link Function}s and {@link Callsite}s in a single
   * AST traversal.
   */
  private void createFunctionsAndCallsites(Node jsRoot,
      final DefinitionProvider provider) {
    // Create fake function representing global execution
    mainFunction = createFunction(jsRoot);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[22]++;

    NodeTraversal.traverse(compiler, jsRoot, new AbstractPostOrderCallback() {
      @Override
      public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[24]++;
        int nodeType = n.getType();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((nodeType == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nodeType == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[5]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[26]++;
          Callsite callsite = createCallsite(n);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[27]++;

          Node containingFunctionNode = t.getScopeRoot();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[28]++;

          Function containingFunction =
              functionsByNode.get(containingFunctionNode);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;

          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((containingFunction == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[7]++;
            containingFunction = createFunction(containingFunctionNode);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[30]++;

          } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[8]++;}
          callsite.containingFunction = containingFunction;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[31]++;
          containingFunction.addCallsiteInFunction(callsite);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[32]++;

          connectCallsiteToTargets(callsite, provider);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[33]++;


        } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[6]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[34]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[9]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[35]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((functionsByNode.containsKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[11]++;
            createFunction(n);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[36]++;

          } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[10]++;}
}
      }
    });
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[23]++;
  }

  /**
   * Create a Function object for given an Token.FUNCTION AST node.
   *
   * This is the bottleneck for Function creation: all Functions should
   * be created with this method.
   */
  private Function createFunction(Node functionNode) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[37]++;
    Function function = new Function(functionNode);
    functionsByNode.put(functionNode, function);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[38]++;

    return function;
  }

  private Callsite createCallsite(Node callsiteNode) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[39]++;
    Callsite callsite = new Callsite(callsiteNode);
    callsitesByNode.put(callsiteNode, callsite);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[40]++;

    return callsite;
  }

  /**
   * Maps a Callsite to the Function(s) it could call
   * and each Function to the Callsite(s) that could call it.
   *
   * If the definitionProvider cannot determine the target of the Callsite,
   * the Callsite's hasUnknownTarget field is set to true.
   *
   * If the definitionProvider determines that the target of the Callsite
   * could be an extern-defined function, then the Callsite's hasExternTarget
   * field is set to true.
   *
   * @param callsite The callsite for which target functions should be found
   * @param definitionProvider The DefinitionProvider used to determine
   *    targets of callsites.
   */
  private void connectCallsiteToTargets(Callsite callsite,
      DefinitionProvider definitionProvider) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[41]++;
    Collection<Definition> definitions =
      lookupDefinitionsForTargetsOfCall(callsite.getAstNode(),
          definitionProvider);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((definitions == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[13]++;
      callsite.hasUnknownTarget = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[43]++;

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[14]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[44]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[1]++;


      for (Definition definition : definitions) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[1]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[2]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[3]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((definition.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[15]++;
          callsite.hasExternTarget = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[46]++;

        } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[16]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[47]++;
          Node target = definition.getRValue();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;

          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((target.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[17]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[49]++;
            Function targetFunction = functionsByNode.get(target);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[50]++;
int CodeCoverConditionCoverageHelper_C10;

            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((targetFunction == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[19]++;
              targetFunction = createFunction(target);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[51]++;

            } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[20]++;}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;

            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((computeForwardGraph) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[21]++;
              callsite.addPossibleTarget(targetFunction);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[53]++;

            } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[22]++;}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;

            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((computeBackwardGraph) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[23]++;
              targetFunction.addCallsitePossiblyTargetingFunction(callsite);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[55]++;

            } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[24]++;}

          } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[18]++;
            callsite.hasUnknownTarget = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[56]++;
          }
        }
      }
    }
  }

  /**
   * Fills in function information (such as whether the function is ever
   * aliased or whether it is exposed to .call or .apply) using the
   * definition provider.
   *
   * We do this here, rather than when connecting the callgraph, to make sure
   * that we have correct information for all functions, rather than just
   * functions that are actually called.
   */
  private void fillInFunctionInformation(DefinitionProvider provider) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((useNameReferenceGraph) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[25]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[58]++;
      NameReferenceGraph referenceGraph = (NameReferenceGraph) provider;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[59]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[4]++;



      for (Function function : getAllFunctions()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[4]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[5]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[6]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[60]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((function.isMain()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[27]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[61]++;
          String functionName = function.getName();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;

          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[29]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[63]++;
            Name symbol = referenceGraph.getSymbol(functionName);
            updateFunctionForName(function, symbol);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[64]++;

          } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[28]++;}
      }

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[26]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[65]++;
      SimpleDefinitionFinder finder = (SimpleDefinitionFinder) provider;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[66]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[7]++;



      for (DefinitionSite definitionSite : finder.getDefinitionSites()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[7]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[8]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[9]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[67]++;
        Definition definition = definitionSite.definition;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[68]++;

        Function function = lookupFunctionForDefinition(definition);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[69]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((function != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[31]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[70]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[10]++;


          for (UseSite useSite : finder.getUseSites(definition)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[10]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[11]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[12]++;
}
            updateFunctionForUse(function, useSite.node);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[71]++;
          }

        } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[32]++;}
      }
    }
  }

  /**
   * Updates {@link Function} information (such as whether is is aliased
   * or exposed to .apply or .call from a {@link NameReferenceGraph.Name}.
   *
   * Note: this method may be called multiple times per Function, each time
   * with a different name.
   */
  private void updateFunctionForName(Function function, Name name) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[72]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((name.isAliased()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[33]++;
      function.isAliased = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[73]++;

    } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[34]++;}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name.exposedToCallOrApply()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[35]++;
      function.isExposedToCallOrApply = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[75]++;

    } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[36]++;}
  }

  /**
   * Updates {@link Function} information (such as whether is is aliased
   * or exposed to .apply or .call based a site where the function is used.
   *
   * Note: this method may be called multiple times per Function, each time
   * with a different useNode.
   */
  private void updateFunctionForUse(Function function, Node useNode) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[76]++;
    Node useParent = useNode.getParent();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[77]++;
    int parentType = useParent.getType();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[78]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((parentType == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((parentType == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((useParent.getFirstChild() == useNode) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[37]++;

      // Regular call sites don't count as aliases
    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[38]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[79]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(useParent)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[39]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[80]++;
int CodeCoverConditionCoverageHelper_C21;
      // GET{PROP,ELEM} don't count as aliases
      // but we have to check for using them in .call and .apply.

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((useParent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[41]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[81]++;
        Node gramps = useParent.getParent();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[82]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectApply(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[43]++;
          function.isExposedToCallOrApply = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[83]++;

        } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[44]++;}

      } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[42]++;}

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[40]++;
      function.isAliased = true;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[84]++;
    }
}
  }

  /**
   * Returns a {@link CallGraph.Function} for the passed in {@link Definition}
   * or null if the definition isn't for a function.
   */
  private Function lookupFunctionForDefinition(Definition definition) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[85]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((definition != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((definition.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[45]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[86]++;
      Node rValue = definition.getRValue();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[87]++;
int CodeCoverConditionCoverageHelper_C24;

      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[47]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[88]++;
        Function function = functionsByNode.get(rValue);
        Preconditions.checkNotNull(function);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[89]++;

        return function;

      } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[48]++;}

    } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[46]++;}

    return null;
  }

  /**
   * Constructs and returns a directed graph where the nodes are functions and
   * the edges are callsites connecting callers to callees.
   *
   * It is safe to call this method on both forward and backwardly constructed
   * CallGraphs.
   */
  public DiGraph<Function, Callsite> getForwardDirectedGraph() {
    return constructDirectedGraph(true);
  }

  /**
   * Constructs and returns a directed graph where the nodes are functions and
   * the edges are callsites connecting callees to callers.
   *
   * It is safe to call this method on both forward and backwardly constructed
   * CallGraphs.
   */
  public DiGraph<Function, Callsite> getBackwardDirectedGraph() {
    return constructDirectedGraph(false);
  }

  private static void digraphConnect(DiGraph<Function, Callsite> digraph,
      Function caller,
      Callsite callsite,
      Function callee,
      boolean forward) {

    Function source;
    Function destination;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[90]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((forward) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[49]++;
      source = caller;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[91]++;
      destination = callee;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[92]++;

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[50]++;
      source = callee;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[93]++;
      destination = caller;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[94]++;
    }

    digraph.connect(source, callsite, destination);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[95]++;
  }

  /**
   * Constructs a digraph of the call graph. If {@code forward} is true, then
   * the edges in the digraph will go from callers to callees, if false then
   * the edges will go from callees to callers.
   *
   * It is safe to run this method on both a forwardly constructed callgraph
   * and a backwardly constructed callgraph, regardless of the value of
   * {@code forward}.
   *
   * @param forward If true then the digraph will be a forward digraph.
   */
  private DiGraph<Function, Callsite> constructDirectedGraph(boolean forward) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[96]++;
    DiGraph<Function, Callsite>digraph =
        LinkedDirectedGraph.createWithoutAnnotations();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[97]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[13]++;



    // Create nodes in call graph
    for (Function function : getAllFunctions()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[13]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[14]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[15]++;
}
      digraph.createNode(function);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[98]++;
    }
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((computeForwardGraph) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[51]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[100]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[16]++;


      // The CallGraph is a forward graph, so go from callers to callees
      for (Function caller : getAllFunctions()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[16]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[17]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[18]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[101]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[19]++;


        for (Callsite callsite : caller.getCallsitesInFunction()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[19]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[20]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[21]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[102]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[22]++;


          for (Function callee : callsite.getPossibleTargets()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[22]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[23]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[24]++;
}
            digraphConnect(digraph, caller, callsite, callee,
                forward);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[103]++;
          }
        }
      }

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[52]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[104]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[25]++;


      // The CallGraph is a backward graph, so go from callees to callers
      for (Function callee : getAllFunctions()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[25]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[26]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[27]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[105]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[28]++;


        for (Callsite callsite :
            callee.getCallsitesPossiblyTargetingFunction()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[28]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[29]--;
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.loops[30]++;
}
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[106]++;

          Function caller = callsite.getContainingFunction();
          digraphConnect(digraph, caller, callsite, callee,
              forward);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[107]++;
        }
      }
    }

    return digraph;
  }

  /**
   * Constructs a DefinitionProvider that can be used to determine the
   * targets of callsites.
   *
   * This construction is the main cost of building the callgraph, so we offer
   * the client a choice of NameReferenceGraph, which is slow and hopefully more
   * precise, and SimpleDefinitionFinder, which is fast and perhaps not as
   * precise.
   *
   * We use SimpleNameFinder as the default because in practice it does
   * not appear to be less precise than NameReferenceGraph and is at least an
   * order of magnitude faster on large compiles.
   */
  private DefinitionProvider constructDefinitionProvider(Node externsRoot,
        Node jsRoot) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[108]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((useNameReferenceGraph) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[53]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[109]++;
      // Name reference graph is very, very slow
      NameReferenceGraphConstruction graphConstruction
          = new NameReferenceGraphConstruction(compiler);

      graphConstruction.process(externsRoot, jsRoot);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[110]++;

      return graphConstruction.getNameReferenceGraph();

    } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[54]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[111]++;
      SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
      defFinder.process(externsRoot, jsRoot);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[112]++;
      return defFinder;
    }
  }

  /**
   * Queries the definition provider for the definitions that could be the
   * targets of the given callsite node.
   *
   * This is complicated by the fact that NameReferenceGraph and
   * SimpleDefinitionProvider (the two definition providers we currently
   * use) differ on the types of target nodes they will analyze.
   */
  private Collection<Definition> lookupDefinitionsForTargetsOfCall(
      Node callsite, DefinitionProvider definitionProvider) {
    Preconditions.checkArgument(callsite.isCall()
        || callsite.isNew());
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[113]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[114]++;

    Node targetExpression = callsite.getFirstChild();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[115]++;
int CodeCoverConditionCoverageHelper_C28;

    // NameReferenceGraph throws an exception unless the node is
    // a GETPROP or a NAME
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (32)) == 0 || true) &&
 ((useNameReferenceGraph) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((targetExpression.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((targetExpression.isName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[55]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[116]++;

      Collection<Definition> definitions =
        definitionProvider.getDefinitionsReferencedAt(targetExpression);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[117]++;
int CodeCoverConditionCoverageHelper_C29;

      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((definitions != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((definitions.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[57]++;
        return definitions;

      } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[58]++;}

    } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[56]++;}

    return null;
  }

  /**
   * An inner class that represents functions in the call graph.
   * A Function knows how to get its AST node and what Callsites
   * it contains.
   */
  public class Function {

    private Node astNode;

    private boolean isAliased = false;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[118]++;
  }

    private boolean isExposedToCallOrApply = false;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[119]++;
  }

    private Collection<Callsite> callsitesInFunction;

    private Collection<Callsite> callsitesPossiblyTargetingFunction;

    private Function(Node functionAstNode) {
      astNode = functionAstNode;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[120]++;
    }

    /**
     * Does this function represent the global "main" function?
     */
    public boolean isMain() {
      return (this == CallGraph.this.mainFunction);
    }

    /**
     * Returns the underlying AST node for the function. This usually
     * has type Token.FUNCTION but in the case of the "main" function
     * will have type Token.BLOCK.
     */
    public Node getAstNode() {
      return astNode;
    }

    /**
     * Returns the AST node for the body of the function. If this function
     * is the main function, it will return the global block.
     */
    public Node getBodyNode() {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[121]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isMain()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[59]++;
        return astNode;

      } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[60]++;
        return NodeUtil.getFunctionBody(astNode);
      }
    }

    /**
     * Gets the name of this function. Returns null if the function is
     * anonymous.
     */
    public String getName() {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[122]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isMain()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[61]++;
        return MAIN_FUNCTION_NAME;

      } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[62]++;
        return NodeUtil.getFunctionName(astNode);
      }
    }

    /**
     * Returns the callsites in this function.
     */
    public Collection<Callsite> getCallsitesInFunction() {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[123]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((callsitesInFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[63]++;
        return callsitesInFunction;

      } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[64]++;
        return ImmutableList.of();
      }
    }

    private void addCallsiteInFunction(Callsite callsite) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[124]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((callsitesInFunction == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[65]++;
        callsitesInFunction = new LinkedList<Callsite>();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[125]++;

      } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[66]++;}
      callsitesInFunction.add(callsite);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[126]++;
    }

    /**
     * Returns a collection of callsites that might call this function.
     *
     * getCallsitesPossiblyTargetingFunction() is a best effort only: the
     * collection may include callsites that do not actually call this function
     * and if this function is exported or aliased may be missing actual
     * targets.
     *
     * This method should not be called on a Function from a CallGraph
     * that was constructed with {@code computeBackwardGraph} {@code false}.
     */
    public Collection<Callsite> getCallsitesPossiblyTargetingFunction() {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[127]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((computeBackwardGraph) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[67]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[128]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((callsitesPossiblyTargetingFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[69]++;
          return callsitesPossiblyTargetingFunction;

        } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[70]++;
          return ImmutableList.of();
        }

      } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[68]++;
        throw new UnsupportedOperationException("Cannot call " +
            "getCallsitesPossiblyTargetingFunction() on a Function "
            + "from a non-backward CallGraph");
      }
    }

    private void addCallsitePossiblyTargetingFunction(Callsite callsite) {
      Preconditions.checkState(computeBackwardGraph);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[129]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[130]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((callsitesPossiblyTargetingFunction == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[71]++;
        callsitesPossiblyTargetingFunction =
            new LinkedList<Callsite>();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[131]++;

      } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[72]++;}
      callsitesPossiblyTargetingFunction.add(callsite);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[132]++;
    }

    /**
     * Returns true if the function is aliased.
     */
    public boolean isAliased() {
      return isAliased;
    }

    /**
     * Returns true if the function is ever exposed to ".call" or ".apply".
     */
    public boolean isExposedToCallOrApply() {
      return isExposedToCallOrApply;
    }
  }

  /**
   * An inner class that represents call sites in the call graph.
   * A Callsite knows how to get its AST node, what its containing
   * Function is, and what its target Functions are.
   */
  public class Callsite {
    private Node astNode;

    private boolean hasUnknownTarget = false;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[133]++;
  }
    private boolean hasExternTarget = false;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[134]++;
  }

    private Function containingFunction = null;
  {
    CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[135]++;
  }

    private Collection<Function> possibleTargets;

    private Callsite(Node callsiteAstNode) {
      astNode = callsiteAstNode;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[136]++;
    }

    public Node getAstNode() {
      return astNode;
    }

    public Function getContainingFunction() {
      return containingFunction;
    }

    /**
     * Returns the possible target functions that this callsite could call.
     *
     * These targets do not include functions defined in externs. If this
     * callsite could call an extern function, then hasExternTarget() will
     * return true.
     *
     * getKnownTargets() is a best effort only: the collection may include
     * other functions that are not actual targets and (if hasUnknownTargets()
     * is true) may be missing actual targets.
     *
     * This method should not be called on a Callsite from a CallGraph
     * that was constructed with {@code computeForwardGraph} {@code false}.
     */
    public Collection<Function> getPossibleTargets() {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[137]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((computeForwardGraph) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[73]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[138]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((possibleTargets != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[75]++;
          return possibleTargets;

        } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[76]++;
          return ImmutableList.of();
        }

      } else {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[74]++;
        throw new UnsupportedOperationException("Cannot call " +
            "getPossibleTargets() on a Callsite from a non-forward " +
            "CallGraph");
      }
    }

    private void addPossibleTarget(Function target) {
      Preconditions.checkState(computeForwardGraph);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[139]++;
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[140]++;
int CodeCoverConditionCoverageHelper_C39;

      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((possibleTargets == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[77]++;
        possibleTargets = new LinkedList<Function>();
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[141]++;

      } else {
  CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.branches[78]++;}
      possibleTargets.add(target);
CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd.statements[142]++;
    }

    /**
     * If true, then DefinitionProvider used in callgraph construction
     * was unable find all target functions of this callsite.
     *
     * If false, then getKnownTargets() contains all the possible targets of
     * this callsite (and, perhaps, additional targets as well).
     */
    public boolean hasUnknownTarget() {
      return hasUnknownTarget;
    }

    /**
     * If true, then this callsite could target a function defined in the
     * externs. If false, then not.
     */
    public boolean hasExternTarget() {
      return hasExternTarget;
    }
  }
}

class CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd ());
  }
    public static long[] statements = new long[143];
    public static long[] branches = new long[79];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[40];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CallGraph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,3,1,1,2,2,2,1,1,1,3,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 39; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$2u8hy5umkxdtl4to8o94dd () {
    super("com.google.javascript.jscomp.CallGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 142; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 78; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CallGraph.java");
      for (int i = 1; i <= 142; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 78; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

