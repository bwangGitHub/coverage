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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfoBuilder;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;

/**
 * Checks for non side effecting statements such as
 * <pre>
 * var s = "this string is "
 *         "continued on the next line but you forgot the +";
 * x == foo();  // should that be '='?
 * foo();;  // probably just a stray-semicolon. Doesn't hurt to check though
 * </p>
 * and generates warnings.
 *
 */
final class CheckSideEffects extends AbstractPostOrderCallback
    implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.ping();
  }


  static final DiagnosticType USELESS_CODE_ERROR = DiagnosticType.warning(
      "JSC_USELESS_CODE",
      "Suspicious code. {0}");
  static {
    CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[1]++;
  }

  static final String PROTECTOR_FN = "JSCOMPILER_PRESERVE";
  static {
    CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[2]++;
  }

  private final CheckLevel level;

  private final List<Node> problemNodes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[3]++;
  }

  private final AbstractCompiler compiler;

  private final boolean protectSideEffectFreeCode;

  CheckSideEffects(AbstractCompiler compiler, CheckLevel level,
      boolean protectSideEffectFreeCode) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[4]++;
    this.level = level;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[5]++;
    this.protectSideEffectFreeCode = protectSideEffectFreeCode;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[7]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;

    // Code with hidden side-effect code is common, for example
    // accessing "el.offsetWidth" forces a reflow in browsers, to allow this
    // will still allowing local dead code removal in general,
    // protect the "side-effect free" code in the source.
    //
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((protectSideEffectFreeCode) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[1]++;
      protectSideEffects();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[9]++;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[2]++;}
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    NodeTraversal.traverse(compiler, scriptRoot, this);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[10]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
    // VOID nodes appear when there are extra semicolons at the BLOCK level.
    // I've been unable to think of any cases where this indicates a bug,
    // and apparently some people like keeping these semicolons around,
    // so we'll allow it.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((n.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[4]++;}
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[6]++;}
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;

    // Do not try to remove a block or an expr result. We already handle
    // these cases when we visit the child, and the peephole passes will
    // fix up the tree in more clever ways when these are removed.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[7]++;
      return;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[8]++;}
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;

    // This no-op statement was there so that JSDoc information could
    // be attached to the name. This check should not complain about it.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((n.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[9]++;
      return;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[10]++;}
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[15]++;

    boolean isResultUsed = NodeUtil.isExpressionResultUsed(n);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[16]++;
    boolean isSimpleOp = NodeUtil.isSimpleOperator(n);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((isResultUsed) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((isSimpleOp) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(n, t.getCompiler())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[11]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[18]++;
      String msg = "This code lacks side-effects. Is there a bug?";
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.isString()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[13]++;
        msg = "Is there a missing '+' on the previous line?";
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[20]++;

      } else {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[14]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[21]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isSimpleOp) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[15]++;
        msg = "The result of the '" + Token.name(n.getType()).toLowerCase() +
            "' operator is not being used.";
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[22]++;

      } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[16]++;}
}

      t.getCompiler().report(
          t.makeError(n, level, USELESS_CODE_ERROR, msg));
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[23]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
      // TODO(johnlenz): determine if it is necessary to
      // try to protect side-effect free statements as well.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatement(n)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[17]++;
        problemNodes.add(n);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[25]++;

      } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[18]++;}

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[12]++;}
  }

  /**
   * Protect side-effect free nodes by making them parameters
   * to a extern function call.  This call will be removed
   * after all the optimizations passes have run.
   */
  private void protectSideEffects() {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((problemNodes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[19]++;
      addExtern();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[27]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[28]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.loops[1]++;


      for (Node n : problemNodes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.loops[1]--;
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.loops[2]--;
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.loops[3]++;
}
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[29]++;
        Node name = IR.name(PROTECTOR_FN).srcref(n);
        name.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[30]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[31]++;
        Node replacement = IR.call(name).srcref(n);
        replacement.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[32]++;
        n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[33]++;
        replacement.addChildToBack(n);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[34]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[35]++;

    } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[20]++;}
  }

  private void addExtern() {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[36]++;
    Node name = IR.name(PROTECTOR_FN);
    name.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[37]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[38]++;
    Node var = IR.var(name);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[39]++;
    // Add "@noalias" so we can strip the method when AliasExternals is enabled.
    JSDocInfoBuilder builder = new JSDocInfoBuilder(false);
    builder.recordNoAlias();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[40]++;
    var.setJSDocInfo(builder.build(var));
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[41]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[42]++;
    CompilerInput input = compiler.getSynthesizedExternsInput();
    input.getAstRoot(compiler).addChildrenToBack(var);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[43]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[44]++;
  }

  /**
   * Remove side-effect sync functions.
   */
  static class StripProtection extends AbstractPostOrderCallback implements CompilerPass {

    private final AbstractCompiler compiler;

    StripProtection(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[45]++;
    }

    @Override
    public void process(Node externs, Node root) {
      NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[46]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[21]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[48]++;
        Node target = n.getFirstChild();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
        // TODO(johnlenz): add this to the coding convention
        // so we can remove goog.reflect.sinkValue as well.
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((target.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((target.getString().equals(PROTECTOR_FN)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[23]++;
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[50]++;
          Node expr = n.getLastChild();
          n.detachChildren();
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[51]++;
          parent.replaceChild(n, expr);
CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.statements[52]++;

        } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[24]++;}

      } else {
  CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap.branches[22]++;}
    }
  }
}

class CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap ());
  }
    public static long[] statements = new long[53];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckSideEffects.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,2,2,3,1,1,1,1,1,2};
    for (int i = 1; i <= 12; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$1jzqmgk1ybxjjekzvekjti3ispz83xqap () {
    super("com.google.javascript.jscomp.CheckSideEffects.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 52; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckSideEffects.java");
      for (int i = 1; i <= 52; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

