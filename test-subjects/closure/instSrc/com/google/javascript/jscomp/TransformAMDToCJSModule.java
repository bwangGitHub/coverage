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

import java.util.Iterator;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterators;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;

/**
 * Rewrites an AMD module https://github.com/amdjs/amdjs-api/wiki/AMD to a
 * CommonJS module. See {@link ProcessCommonJSModules} for follow up processing
 * step.
 */
class TransformAMDToCJSModule implements CompilerPass {
  static {
    CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.ping();
  }


  @VisibleForTesting
  final static DiagnosticType UNSUPPORTED_DEFINE_SIGNATURE_ERROR =
      DiagnosticType.error(
          "UNSUPPORTED_DEFINE_SIGNATURE",
          "Only define(function() ...), define(OBJECT_LITERAL) and define("
              + "['dep', 'dep1'], function(d0, d2, [exports, module]) ...) forms "
              + "are currently supported.");
  static {
    CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[1]++;
  }
  final static DiagnosticType NON_TOP_LEVEL_STATEMENT_DEFINE_ERROR =
      DiagnosticType.error(
            "NON_TOP_LEVEL_STATEMENT_DEFINE",
            "The define function must be called as a top-level statement.");
  static {
    CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[2]++;
  }
  final static DiagnosticType REQUIREJS_PLUGINS_NOT_SUPPORTED_WARNING =
    DiagnosticType.warning(
          "REQUIREJS_PLUGINS_NOT_SUPPORTED",
          "Plugins in define requirements are not supported: {0}");
  static {
    CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[3]++;
  }

  final static String VAR_RENAME_SUFFIX = "__alias";
  static {
    CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[4]++;
  }


  private final AbstractCompiler compiler;
  private int renameIndex = 0;
  {
    CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[5]++;
  }

  TransformAMDToCJSModule(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new TransformAMDModulesCallback());
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[7]++;
  }

  private void unsupportedDefineError(NodeTraversal t, Node n) {
    t.report(n, UNSUPPORTED_DEFINE_SIGNATURE_ERROR);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[8]++;
  }

  /**
   * The modules "exports", "require" and "module" are virtual in terms of
   * existing implicitly in CommonJS.
   */
  private boolean isVirtualModuleName(String moduleName) {
    return "exports".equals(moduleName) || "require".equals(moduleName) ||
        "module".equals(moduleName);
  }

  /**
   * Rewrites calls to define which has to be in void context just below the
   * current script node.
   */
  private class TransformAMDModulesCallback extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((n.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (("define".equals(n.getFirstChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[1]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[10]++;
        Scope.Var define = t.getScope().getVar(n.getFirstChild().
            getString());
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((define != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((define.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[3]++;
          // Ignore non-global define.
          return;

        } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[4]++;}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent.getParent().isScript()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[5]++;
          t.report(n, NON_TOP_LEVEL_STATEMENT_DEFINE_ERROR);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[13]++;
          return;

        } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[6]++;}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[14]++;
        Node script = parent.getParent();
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[15]++;
        Node requiresNode = null;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[16]++;
        Node callback = null;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[17]++;
        int defineArity = n.getChildCount() - 1;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((defineArity == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[7]++;
          unsupportedDefineError(t, n);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[19]++;
          return;

        } else {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[8]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((defineArity == 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[9]++;
          callback = n.getChildAtIndex(1);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[21]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((callback.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[11]++;
            handleDefineObjectLiteral(parent, callback, script);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[23]++;
            return;

          } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[12]++;}

        } else {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[10]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[24]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((defineArity == 2) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[13]++;
          requiresNode = n.getChildAtIndex(1);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[25]++;
          callback = n.getChildAtIndex(2);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[26]++;

        } else {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[14]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[27]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((defineArity >= 3) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[15]++;
          unsupportedDefineError(t, n);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[28]++;
          return;

        } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[16]++;}
}
}
}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((callback.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((requiresNode != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((requiresNode.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[17]++;
          unsupportedDefineError(t, n);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[30]++;
          return;

        } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[18]++;}

        handleRequiresAndParamList(t, n, script, requiresNode, callback);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[31]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[32]++;

        Node callbackBlock = callback.getChildAtIndex(2);
        NodeTraversal.traverse(compiler, callbackBlock,
            new DefineCallbackReturnCallback());
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[33]++;

        moveCallbackContentToTopLevel(parent, script, callbackBlock);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[34]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[35]++;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[2]++;}
    }

    /**
     * When define is called with an object literal, assign it to exports and
     * we're done.
     */
    private void handleDefineObjectLiteral(Node parent, Node onlyExport,
        Node script) {
      onlyExport.getParent().removeChild(onlyExport);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[36]++;
      script.replaceChild(parent,
          IR.exprResult(IR.assign(IR.name("exports"), onlyExport))
              .copyInformationFromForTree(onlyExport));
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[37]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[38]++;
    }

    /**
     * Rewrites the required modules to
     * <code>var nameInParamList = require("nameFromRequireList");</code>
     */
    private void handleRequiresAndParamList(NodeTraversal t, Node defineNode,
        Node script, Node requiresNode, Node callback) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[39]++;
      Iterator<Node> paramList = callback.getChildAtIndex(1).children().
          iterator();
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[40]++;
      Iterator<Node> requires = requiresNode != null ?
          requiresNode.children().iterator() : Iterators.<Node>emptyIterator();
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[41]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
      while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((paramList.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((requires.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[1]--;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[2]--;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[3]++;
}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[42]++;
        Node aliasNode = paramList.hasNext() ? paramList.next() : null;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[43]++;
        Node modNode = requires.hasNext() ? requires.next() : null;
        handleRequire(t, defineNode, script, callback, aliasNode, modNode);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[44]++;
      }
    }

    /**
     * Rewrite a single require call.
     */
    private void handleRequire(NodeTraversal t, Node defineNode, Node script,
        Node callback, Node aliasNode, Node modNode) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[45]++;
      String moduleName = null;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((modNode != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[19]++;
        moduleName = handlePlugins(t, script, modNode.getString(), modNode);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[47]++;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[20]++;}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isVirtualModuleName(moduleName)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[21]++;
        return;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[22]++;}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[49]++;

      String aliasName = aliasNode != null ? aliasNode.getString() : null;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[50]++;
      Scope globalScope = t.getScope();
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((aliasName != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((globalScope.isDeclared(aliasName, true)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[23]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[52]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[4]++;


        while (true) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[4]--;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[5]--;
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.loops[6]++;
}
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[53]++;
          String renamed = aliasName + VAR_RENAME_SUFFIX + renameIndex;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((globalScope.isDeclared(renamed, true)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[25]++;
            NodeTraversal.traverse(compiler, callback,
                new RenameCallback(aliasName, renamed));
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[55]++;
            aliasName = renamed;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[56]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[57]++;
            break;

          } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[26]++;}
          renameIndex++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[58]++;
        }

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[24]++;}

      Node requireNode;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((moduleName != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[27]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[60]++;
        Node call = IR.call(IR.name("require"), IR.string(moduleName));
        call.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[61]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((aliasName != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[29]++;
          requireNode = IR.var(IR.name(aliasName), call)
              .copyInformationFromForTree(aliasNode);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[63]++;

        } else {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[30]++;
          requireNode = IR.exprResult(call).
              copyInformationFromForTree(modNode);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[64]++;
        }

      } else {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[28]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[65]++;
int CodeCoverConditionCoverageHelper_C18;
        // ignore exports, require and module (because they are implicit
        // in CommonJS);
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isVirtualModuleName(aliasName)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[31]++;
          return;

        } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[32]++;}
        requireNode = IR.var(IR.name(aliasName), IR.nullNode())
            .copyInformationFromForTree(aliasNode);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[66]++;
      }

      script.addChildBefore(requireNode,
          defineNode.getParent());
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[67]++;
    }

    /**
     * Require.js supports a range of plugins that are hard to support
     * statically. Generally none are supported right now with the
     * exception of a simple hack to support condition loading. This
     * was added to make compilation of Dojo work better but will
     * probably break, so just don't use them :)
     */
    private String handlePlugins(NodeTraversal t, Node script,
        String moduleName, Node modNode) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[68]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((moduleName.contains("!")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[33]++;
        t.report(modNode, REQUIREJS_PLUGINS_NOT_SUPPORTED_WARNING, moduleName);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[69]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[70]++;
        int condition = moduleName.indexOf('?');
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[71]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((condition > 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[35]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((moduleName.contains(":")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[37]++;
            return null;

          } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[38]++;}
          return handlePlugins(t, script, moduleName.substring(condition + 1),
              modNode);

        } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[36]++;}
        moduleName = null;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[73]++;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[34]++;}
      return moduleName;
    }

    /**
     * Moves the statements in the callback to be direct children of the
     * current script.
     */
    private void moveCallbackContentToTopLevel(Node defineParent, Node script,
        Node callbackBlock) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[74]++;
      int curIndex = script.getIndexOfChild(defineParent);
      script.removeChild(defineParent);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[75]++;
      callbackBlock.getParent().removeChild(callbackBlock);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[76]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[77]++;
      Node before = script.getChildAtIndex(curIndex);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[78]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((before != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[39]++;
        script.addChildBefore(callbackBlock, before);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[79]++;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[40]++;}
      script.addChildToBack(callbackBlock);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[80]++;
      NodeUtil.tryMergeBlock(callbackBlock);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[81]++;
    }
  }

  /**
   * Rewrites the return statement of the callback to be an assignment to
   * module.exports.
   */
  private class DefineCallbackReturnCallback extends
      NodeTraversal.AbstractShallowStatementCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[82]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((n.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[41]++;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[83]++;
        Node retVal = n.getFirstChild();
        n.removeChild(retVal);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[84]++;
        parent.replaceChild(n, IR.exprResult(
            IR.assign(
                IR.getprop(IR.name("module"), IR.string("exports")), retVal))
                    .useSourceInfoFromForTree(n));
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[85]++;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[42]++;}
    }
  }

  /**
   * Renames names;
   */
  private class RenameCallback extends AbstractPostOrderCallback {

    private final String from;
    private final String to;

    public RenameCallback(String from, String to) {
      this.from = from;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[86]++;
      this.to = to;
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[87]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[88]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((from.equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[43]++;
        n.setString(to);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[89]++;
        n.putProp(Node.ORIGINALNAME_PROP, from);
CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.statements[90]++;

      } else {
  CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh.branches[44]++;}
    }
  }
}

class CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh ());
  }
    public static long[] statements = new long[91];
    public static long[] branches = new long[45];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TransformAMDToCJSModule.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,2,2,1,1,1,1,1,3,2,1,1,2,0,1,1,1,1,1,1,1,1,2,2};
    for (int i = 1; i <= 24; i++) {
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

  public CodeCoverCoverageCounter$12ehdnrgv9eqfbl1zuj7b4vt8ql0afq5mgbsn590vskh () {
    super("com.google.javascript.jscomp.TransformAMDToCJSModule.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 90; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 44; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TransformAMDToCJSModule.java");
      for (int i = 1; i <= 90; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 44; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
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

