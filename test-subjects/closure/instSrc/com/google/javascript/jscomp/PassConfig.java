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
import com.google.common.collect.Iterables;
import com.google.javascript.jscomp.graph.GraphvizGraph;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.rhino.Node;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Pass factories and meta-data for native Compiler passes.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public abstract class PassConfig {
  static {
    CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.ping();
  }


  // Used by subclasses in this package.
  final CompilerOptions options;

  /**
   * A memoized version of scopeCreator. It must be memoized so that
   * we can make two separate passes over the AST, one for inferring types
   * and one for checking types.
   */
  private MemoizedScopeCreator typedScopeCreator;

  /**
   * This is the scope creator that {@code TypedScopeCreator} delegates to.
   */
  private TypedScopeCreator internalScopeCreator;

  /** The global typed scope. */
  Scope topScope = null;
  {
    CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[1]++;
  }

  public PassConfig(CompilerOptions options) {
    this.options = options;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[2]++;
  }

  /**
   * Regenerates the top scope from scratch.
   *
   * @param compiler The compiler for which the global scope is regenerated.
   * @param root The root of the AST.
   */
  void regenerateGlobalTypedScope(AbstractCompiler compiler, Node root) {
    internalScopeCreator = new TypedScopeCreator(compiler);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[3]++;
    typedScopeCreator = new MemoizedScopeCreator(internalScopeCreator);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[4]++;
    topScope = typedScopeCreator.createScope(root, null);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[5]++;
  }

  void clearTypedScope() {
    internalScopeCreator = null;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[6]++;
    typedScopeCreator = null;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[7]++;
    topScope = null;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[8]++;
  }

  /**
   * Regenerates the top scope potentially only for a sub-tree of AST and then
   * copies information for the old global scope.
   *
   * @param compiler The compiler for which the global scope is generated.
   * @param scriptRoot The root of the AST used to generate global scope.
   */
  void patchGlobalTypedScope(AbstractCompiler compiler, Node scriptRoot) {
    Preconditions.checkNotNull(internalScopeCreator);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[9]++;
    internalScopeCreator.patchGlobalScope(topScope, scriptRoot);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[10]++;
  }

  /**
   * Gets the scope creator for typed scopes.
   */
  MemoizedScopeCreator getTypedScopeCreator() {
    return typedScopeCreator;
  }

  /**
   * Gets the global scope, with type information.
   */
  Scope getTopScope() {
    return topScope;
  }

  /**
   * Gets the checking passes to run.
   *
   * Checking passes revolve around emitting warnings and errors.
   * They also may include pre-processor passes needed to do
   * error analysis more effectively.
   *
   * Clients that only want to analyze code (like IDEs) and not emit
   * code will only run checks and not optimizations.
   */
  abstract protected List<PassFactory> getChecks();

  /**
   * Gets the optimization passes to run.
   *
   * Optimization passes revolve around producing smaller and faster code.
   * They should always run after checking passes.
   */
  abstract protected List<PassFactory> getOptimizations();

  /**
   * Gets a graph of the passes run. For debugging.
   */
  GraphvizGraph getPassGraph() {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[11]++;
    LinkedDirectedGraph<String, String> graph =
        LinkedDirectedGraph.createWithoutAnnotations();
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[12]++;
    Iterable<PassFactory> allPasses =
        Iterables.concat(getChecks(), getOptimizations());
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[13]++;
    String lastPass = null;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[14]++;
    String loopStart = null;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[1]++;


    for (PassFactory pass : allPasses) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[1]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[2]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[3]++;
}
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[16]++;
      String passName = pass.getName();
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[17]++;
      int i = 1;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[4]++;


int CodeCoverConditionCoverageHelper_C1;
      while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((graph.hasNode(passName)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[4]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[5]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[6]++;
}
        passName = pass.getName() + (i++);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[19]++;
      }
      graph.createNode(passName);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[20]++;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;

      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((loopStart == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((pass.isOneTimePass()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[1]++;
        loopStart = passName;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[22]++;

      } else {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[2]++;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[23]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((loopStart != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pass.isOneTimePass()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[3]++;
        graph.connect(lastPass, "loop", loopStart);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[24]++;
        loopStart = null;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[25]++;

      } else {
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[4]++;}
}
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;

      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lastPass != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[5]++;
        graph.connect(lastPass, "", passName);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[27]++;

      } else {
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[6]++;}
      lastPass = passName;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[28]++;
    }
    return graph;
  }

  /**
   * Create a type inference pass.
   */
  final TypeInferencePass makeTypeInference(AbstractCompiler compiler) {
    return new TypeInferencePass(
        compiler, compiler.getReverseAbstractInterpreter(),
        topScope, typedScopeCreator);
  }

  final InferJSDocInfo makeInferJsDocInfo(AbstractCompiler compiler) {
    return new InferJSDocInfo(compiler);
  }

  /**
   * Create a type-checking pass.
   */
  final TypeCheck makeTypeCheck(AbstractCompiler compiler) {
    return new TypeCheck(
        compiler,
        compiler.getReverseAbstractInterpreter(),
        compiler.getTypeRegistry(),
        topScope,
        typedScopeCreator,
        options.reportMissingOverride,
        options.reportUnknownTypes)
        .reportMissingProperties(options.enables(
            DiagnosticGroup.forType(TypeCheck.INEXISTENT_PROPERTY)));
  }

  /**
   * Insert the given pass factory before the factory of the given name.
   */
  final static void addPassFactoryBefore(
      List<PassFactory> factoryList, PassFactory factory, String passName) {
    factoryList.add(
        findPassIndexByName(factoryList, passName), factory);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[29]++;
  }

  /**
   * Find a pass factory with the same name as the given one, and replace it.
   */
  final static void replacePassFactory(
      List<PassFactory> factoryList, PassFactory factory) {
    factoryList.set(
        findPassIndexByName(factoryList, factory.getName()), factory);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[30]++;
  }

  /**
   * Throws an exception if no pass with the given name exists.
   */
  private static int findPassIndexByName(
      List<PassFactory> factoryList, String name) {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[31]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < factoryList.size()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[7]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[8]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[9]++;
}
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((factoryList.get(i).getName().equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[7]++;
        return i;

      } else {
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.branches[8]++;}
    }

    throw new IllegalArgumentException(
        "No factory named '" + name + "' in the factory list");
  }

  /**
   * Find the first pass provider that does not have a delegate.
   */
  final PassConfig getBasePassConfig() {
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[33]++;
    PassConfig current = this;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[34]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;
    while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((current instanceof PassConfigDelegate) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[10]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[11]--;
  CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.loops[12]++;
}
      current = ((PassConfigDelegate) current).delegate;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[35]++;
    }
    return current;
  }

  /**
   * Get intermediate state for a running pass config, so it can
   * be paused and started again later.
   */
  protected abstract State getIntermediateState();

  /**
   * Set the intermediate state for a pass config, to restart
   * a compilation process that had been previously paused.
   */
  protected abstract void setIntermediateState(State state);

  /**
   * An implementation of PassConfig that just proxies all its method calls
   * into an inner class.
   */
  static class PassConfigDelegate extends PassConfig {

    private final PassConfig delegate;

    PassConfigDelegate(PassConfig delegate) {
      super(delegate.options);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[36]++;
      this.delegate = delegate;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[37]++;
    }

    @Override protected List<PassFactory> getChecks() {
      return delegate.getChecks();
    }

    @Override protected List<PassFactory> getOptimizations() {
      return delegate.getOptimizations();
    }

    @Override MemoizedScopeCreator getTypedScopeCreator() {
      return delegate.getTypedScopeCreator();
    }

    @Override Scope getTopScope() {
      return delegate.getTopScope();
    }

    @Override protected State getIntermediateState() {
      return delegate.getIntermediateState();
    }

    @Override protected void setIntermediateState(State state) {
      delegate.setIntermediateState(state);
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[38]++;
    }
  }

  /**
   * Intermediate state for a running pass configuration.
   */
  public static class State implements Serializable {
    private static final long serialVersionUID = 1L;

    final Map<String, Integer> cssNames;
    final Set<String> exportedNames;
    final CrossModuleMethodMotion.IdGenerator crossModuleIdGenerator;
    final VariableMap variableMap;
    final VariableMap propertyMap;
    final VariableMap anonymousFunctionNameMap;
    final VariableMap stringMap;
    final FunctionNames functionNames;
    final String idGeneratorMap;

    public State(Map<String, Integer> cssNames, Set<String> exportedNames,
        CrossModuleMethodMotion.IdGenerator crossModuleIdGenerator,
        VariableMap variableMap, VariableMap propertyMap,
        VariableMap anonymousFunctionNameMap,
        VariableMap stringMap, FunctionNames functionNames,
        String idGeneratorMap) {
      this.cssNames = cssNames;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[39]++;
      this.exportedNames = exportedNames;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[40]++;
      this.crossModuleIdGenerator = crossModuleIdGenerator;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[41]++;
      this.variableMap = variableMap;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[42]++;
      this.propertyMap = propertyMap;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[43]++;
      this.anonymousFunctionNameMap = anonymousFunctionNameMap;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[44]++;
      this.stringMap = stringMap;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[45]++;
      this.idGeneratorMap = idGeneratorMap;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[46]++;
      this.functionNames = functionNames;
CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t.statements[47]++;
    }
  }
}

class CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t ());
  }
    public static long[] statements = new long[48];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PassConfig.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$o3a10c9kaxdioaaxm53gi1t () {
    super("com.google.javascript.jscomp.PassConfig.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 47; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PassConfig.java");
      for (int i = 1; i <= 47; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

