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
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.CodingConvention.AssertionFunctionSpec;
import com.google.javascript.jscomp.NodeTraversal.AbstractScopedCallback;
import com.google.javascript.jscomp.type.ReverseAbstractInterpreter;
import com.google.javascript.rhino.Node;

import java.util.Map;

/**
 * A compiler pass to run the type inference analysis.
 *
 */
class TypeInferencePass implements CompilerPass {
  static {
    CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.ping();
  }


  static final DiagnosticType DATAFLOW_ERROR = DiagnosticType.warning(
      "JSC_INTERNAL_ERROR_DATAFLOW",
      "non-monotonic data-flow analysis");
  static {
    CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final ReverseAbstractInterpreter reverseInterpreter;
  private Scope topScope;
  private MemoizedScopeCreator scopeCreator;
  private final Map<String, AssertionFunctionSpec> assertionFunctionsMap;

  TypeInferencePass(AbstractCompiler compiler,
      ReverseAbstractInterpreter reverseInterpreter,
      Scope topScope, MemoizedScopeCreator scopeCreator) {
    this.compiler = compiler;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[2]++;
    this.reverseInterpreter = reverseInterpreter;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[3]++;
    this.topScope = topScope;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[4]++;
    this.scopeCreator = scopeCreator;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[5]++;

    assertionFunctionsMap = Maps.newHashMap();
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[6]++;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.loops[1]++;


    for (AssertionFunctionSpec assertionFucntion :
        compiler.getCodingConvention().getAssertionFunctions()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.loops[1]--;
  CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.loops[2]--;
  CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.loops[3]++;
}
      assertionFunctionsMap.put(assertionFucntion.getFunctionName(),
          assertionFucntion);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[8]++;
    }
  }

  /**
   * Main entry point for type inference when running over the whole tree.
   *
   * @param externsRoot The root of the externs parse tree.
   * @param jsRoot The root of the input parse tree to be checked.
   */
  @Override
  public void process(Node externsRoot, Node jsRoot) {
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[9]++;
    Node externsAndJs = jsRoot.getParent();
    Preconditions.checkState(externsAndJs != null);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[10]++;
    Preconditions.checkState(
        externsRoot == null || externsAndJs.hasChild(externsRoot));
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[11]++;

    inferAllScopes(externsAndJs);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[12]++;
  }

  /** Entry point for type inference when running over part of the tree. */
  void inferAllScopes(Node node) {
    // Type analysis happens in two major phases.
    // 1) Finding all the symbols.
    // 2) Propagating all the inferred types.
    //
    // The order of this analysis is non-obvious. In a complete inference
    // system, we may need to backtrack arbitrarily far. But the compile-time
    // costs would be unacceptable.
    //
    // We do one pass where we do typed scope creation for all scopes
    // in pre-order.
    //
    // Then we do a second pass where we do all type inference
    // (type propagation) in pre-order.
    //
    // We use a memoized scope creator so that we never create a scope
    // more than once.
    //
    // This will allow us to handle cases like:
    // var ns = {};
    // (function() { /** JSDoc */ ns.method = function() {}; })();
    // ns.method();
    // In this code, we need to build the symbol table for the inner scope in
    // order to propagate the type of ns.method in the outer scope.
    (new NodeTraversal(
        compiler, new FirstScopeBuildingCallback(), scopeCreator))
        .traverseWithScope(node, topScope);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[13]++;
    (new NodeTraversal(
        compiler, new SecondScopeBuildingCallback(), scopeCreator))
        .traverseWithScope(node, topScope);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[14]++;
  }

  void inferScope(Node n, Scope scope) {
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[15]++;
    TypeInference typeInference =
        new TypeInference(
            compiler, computeCfg(n), reverseInterpreter, scope,
            assertionFunctionsMap);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[16]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      typeInference.analyze();
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[17]++;

      // Resolve any new type names found during the inference.
      compiler.getTypeRegistry().resolveTypesInScope(scope);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[18]++;

    } catch (DataFlowAnalysis.MaxIterationsExceededException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.branches[2]++;
      compiler.report(JSError.make(n.getSourceFileName(), n, DATAFLOW_ERROR));
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[19]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.branches[1]++;
}
  }
  }

  private class FirstScopeBuildingCallback extends AbstractScopedCallback {
    @Override
    public void enterScope(NodeTraversal t) {
      t.getScope();
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[20]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
      // Do nothing
    }
  }

  private class SecondScopeBuildingCallback extends AbstractScopedCallback {
    @Override
    public void enterScope(NodeTraversal t) {
      // Only infer the entry root, rather than the scope root.
      // This ensures that incremental compilation only touches the root
      // that's been swapped out.
      inferScope(t.getCurrentNode(), t.getScope());
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[21]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
      // Do nothing
    }
  }

  private ControlFlowGraph<Node> computeCfg(Node n) {
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[22]++;
    ControlFlowAnalysis cfa = new ControlFlowAnalysis(compiler, false, false);
    cfa.process(null, n);
CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1.statements[23]++;
    return cfa.getCfg();
  }
}

class CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1 ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[3];
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$duzbokvy8bsh3f5wrimao37yl5yq8z8pk1 () {
    super("com.google.javascript.jscomp.TypeInferencePass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TypeInferencePass.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
      for (int i = 1; i <= 1; i++) {
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

