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
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Replace known jQuery aliases and methods with standard
 * conventions so that the compiler recognizes them. Expected
 * replacements include:
 *  - jQuery.fn -> jQuery.prototype
 *  - jQuery.extend -> expanded into direct object assignments
 *  - jQuery.expandedEach -> expand into direct assignments
 *
 * @author chadkillingsworth@missouristate.edu (Chad Killingsworth)
 */
class ExpandJqueryAliases extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.ping();
  }

  private final AbstractCompiler compiler;
  private final CodingConvention convention;
  private static final Logger logger =
      Logger.getLogger(ExpandJqueryAliases.class.getName());
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[1]++;
  }

  static final DiagnosticType JQUERY_UNABLE_TO_EXPAND_INVALID_LIT_ERROR =
      DiagnosticType.warning("JSC_JQUERY_UNABLE_TO_EXPAND_INVALID_LIT",
          "jQuery.expandedEach call cannot be expanded because the first " +
          "argument must be an object literal or an array of strings " +
          "literal.");
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[2]++;
  }

  static final DiagnosticType JQUERY_UNABLE_TO_EXPAND_INVALID_NAME_ERROR =
      DiagnosticType.error("JSC_JQUERY_UNABLE_TO_EXPAND_INVALID_NAME",
          "jQuery.expandedEach expansion would result in the invalid " +
          "property name \"{0}\".");
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[3]++;
  }

  static final DiagnosticType JQUERY_USELESS_EACH_EXPANSION =
      DiagnosticType.warning("JSC_JQUERY_USELESS_EACH_EXPANSION",
          "jQuery.expandedEach was not expanded as no valid property " +
          "assignments were encountered. Consider using jQuery.each instead.");
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[4]++;
  }

  private static final Set<String> JQUERY_EXTEND_NAMES = ImmutableSet.of(
      "jQuery.extend", "jQuery.fn.extend", "jQuery.prototype.extend");
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[5]++;
  }

  private static final String JQUERY_EXPANDED_EACH_NAME =
      "jQuery.expandedEach";
  static {
    CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[6]++;
  }

  private final PeepholeOptimizationsPass peepholePasses;

  ExpandJqueryAliases(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[7]++;
    this.convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[8]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[9]++;

    // All of the "early" peephole optimizations.
    // These passes should make the code easier to analyze.
    // Passes, such as StatementFusion, are omitted for this reason.
    final boolean late = false;
    this.peepholePasses = new PeepholeOptimizationsPass(compiler,
        new PeepholeSubstituteAlternateSyntax(late),
        new PeepholeReplaceKnownMethods(late),
        new PeepholeRemoveDeadCode(),
        new PeepholeFoldConstants(late),
        new PeepholeCollectPropertyAssignments());
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[10]++;
  }

  /**
   * Check that Node n is a call to one of the jQuery.extend methods that we
   * can expand. Valid calls are single argument calls where the first argument
   * is an object literal or two argument calls where the first argument
   * is a name and the second argument is an object literal.
   */
  public static boolean isJqueryExtendCall(Node n, String qname,
      AbstractCompiler compiler) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((JQUERY_EXTEND_NAMES.contains(qname)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[1]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[12]++;
      Node firstArgument = n.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((firstArgument == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[3]++;
        return false;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[4]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[14]++;

      Node secondArgument = firstArgument.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C3 |= (32768)) == 0 || true) &&
 ((firstArgument.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16384)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8192)) == 0 || true) &&
 ((secondArgument == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4096)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C3 |= (2048)) == 0 || true) &&
 ((firstArgument.isName()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (512)) == 0 || true) &&
 ((NodeUtil.isGet(firstArgument)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (256)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (128)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(firstArgument, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((secondArgument != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((secondArgument.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((secondArgument.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 8) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 8) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[5]++;
        return true;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[2]++;}
    return false;
  }

  public boolean isJqueryExpandedEachCall(Node call, String qName) {
    Preconditions.checkArgument(call.isCall());
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[16]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((call.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((JQUERY_EXPANDED_EACH_NAME.equals(qName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[7]++;
      return true;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[8]++;}
    return false;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((convention.isPrototypeAlias(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[9]++;
      maybeReplaceJqueryPrototypeAlias(n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[19]++;


    } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[10]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[20]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[11]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[21]++;
      Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[22]++;
      String qName = callTarget.getQualifiedName();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isJqueryExtendCall(callTarget, qName, this.compiler)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[13]++;
        maybeExpandJqueryExtendCall(n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[24]++;


      } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[14]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[25]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isJqueryExpandedEachCall(n, qName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[15]++;
        maybeExpandJqueryEachCall(t, n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[26]++;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[16]++;}
}

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[12]++;}
}
  }

  @Override
  public void process(Node externs, Node root) {
    logger.fine("Expanding Jquery Aliases");
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[27]++;

    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[28]++;
  }

  private void maybeReplaceJqueryPrototypeAlias(Node n) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
    // Check to see if this is the assignment of the original alias.
    // If so, leave it intact.
    if((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[17]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[30]++;
      Node maybeAssign = n.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[31]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
      while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((NodeUtil.isStatement(maybeAssign)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((maybeAssign.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[1]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[2]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[3]++;
}
        maybeAssign = maybeAssign.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[32]++;
      }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;

      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((maybeAssign.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[19]++;
        maybeAssign = maybeAssign.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[34]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((maybeAssign.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((maybeAssign.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatement(maybeAssign)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[21]++;
          return;

        } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[22]++;}

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[20]++;}

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[18]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[36]++;

    Node fn = n.getLastChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((fn != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[23]++;
      n.replaceChild(fn, IR.string("prototype"));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[38]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[39]++;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[24]++;}
  }

  /**
   * Expand jQuery.extend (and derivative) calls into direct object assignments
   * Example: jQuery.extend(obj1, {prop1: val1, prop2: val2}) ->
   *   obj1.prop1 = val1;
   *   obj1.prop2 = val2;
   */
  private void maybeExpandJqueryExtendCall(Node n) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[40]++;
    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[41]++;
    Node objectToExtend = callTarget.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[42]++; // first argument
    Node extendArg = objectToExtend.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[43]++; // second argument
    boolean ensureObjectDefined = true;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((extendArg == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[25]++;
      // Only one argument was specified, so extend jQuery namespace
      extendArg = objectToExtend;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[45]++;
      objectToExtend = callTarget.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[46]++;
      ensureObjectDefined = false;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[47]++;

    } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[26]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[48]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((objectToExtend.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((objectToExtend.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((convention.isPrototypeAlias(objectToExtend)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[27]++;
      ensureObjectDefined = false;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[49]++;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[28]++;}
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[50]++;
int CodeCoverConditionCoverageHelper_C16;

    // Check for an empty object literal
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((extendArg.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[29]++;
      return;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[30]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[51]++;

    // Since we are expanding jQuery.extend calls into multiple statements,
    // encapsulate the new statements in a new block.
    Node fncBlock = IR.block().srcref(n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[52]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ensureObjectDefined) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[31]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[53]++;
      Node assignVal = IR.or(objectToExtend.cloneTree(),
          IR.objectlit().srcref(n)).srcref(n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[54]++;
      Node assign = IR.assign(objectToExtend.cloneTree(), assignVal).srcref(n);
      fncBlock.addChildrenToFront(IR.exprResult(assign).srcref(n));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[55]++;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[32]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[56]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;

    while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((extendArg.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[4]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[5]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[6]++;
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[57]++;
      Node currentProp = extendArg.removeFirstChild();
      currentProp.setType(Token.STRING);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[58]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[59]++;

      Node propValue = currentProp.removeFirstChild();

      Node newProp;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[60]++;
int CodeCoverConditionCoverageHelper_C19;
      if((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((currentProp.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[33]++;
        newProp = IR.getelem(objectToExtend.cloneTree(),
            currentProp).srcref(currentProp);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[61]++;

      } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[34]++;
        newProp = IR.getprop(objectToExtend.cloneTree(),
            currentProp).srcref(currentProp);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[62]++;
      }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[63]++;

      Node assignNode = IR.assign(newProp, propValue).srcref(currentProp);
      fncBlock.addChildToBack(IR.exprResult(assignNode).srcref(currentProp));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[64]++;
    }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[65]++;
int CodeCoverConditionCoverageHelper_C20;

    // Check to see if the return value is used. If not, replace the original
    // call with new block. Otherwise, wrap the statements in an
    // immediately-called anonymous function.
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n.getParent().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[35]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[66]++;
      Node parent = n.getParent();
      parent.getParent().replaceChild(parent, fncBlock);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[67]++;

    } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[36]++;
      Node targetVal;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[68]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 (("jQuery.prototype".equals(objectToExtend.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[37]++;
        // When extending the jQuery prototype, return the jQuery namespace.
        // This is not commonly used.
        targetVal = objectToExtend.removeFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[69]++;

      } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[38]++;
        targetVal = objectToExtend.detachFromParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[70]++;
      }
      fncBlock.addChildToBack(IR.returnNode(targetVal).srcref(targetVal));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[71]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[72]++;

      Node fnc = IR.function(IR.name("").srcref(n),
          IR.paramList().srcref(n),
          fncBlock);
      n.replaceChild(callTarget, fnc);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[73]++;
      n.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[74]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[75]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;

      // remove any other pre-existing call arguments
      while((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fnc.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[7]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[8]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[9]++;
}
        n.removeChildAfter(fnc);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[76]++;
      }
    }
    compiler.reportCodeChange();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[77]++;
  }

  /**
   * Expand a jQuery.expandedEach call
   *
   * Expanded jQuery.expandedEach calls will replace the GETELEM nodes of a
   * property assignment with GETPROP nodes to allow for renaming.
   */
  private void maybeExpandJqueryEachCall(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[78]++;
    Node objectToLoopOver = n.getChildAtIndex(1);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[79]++;
int CodeCoverConditionCoverageHelper_C23;

    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((objectToLoopOver == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[39]++;
      return;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[40]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[80]++;

    Node callbackFunction = objectToLoopOver.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[81]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((callbackFunction == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((callbackFunction.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[41]++;
      return;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[42]++;}

    // Run the peephole optimizations on the first argument to handle
    // cases like ("a " + "b").split(" ")
    peepholePasses.process(null, n.getChildAtIndex(1));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[82]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[83]++;

    // Create a reference tree
    Node nClone = n.cloneTree();

    objectToLoopOver = nClone.getChildAtIndex(1);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[84]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;

    // Check to see if the first argument is something we recognize and can
    // expand.
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((objectToLoopOver.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((objectToLoopOver.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isArrayLitValidForExpansion(objectToLoopOver)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[43]++;
      t.report(n, JQUERY_UNABLE_TO_EXPAND_INVALID_LIT_ERROR, (String)null);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[86]++;
      return;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[44]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[87]++;

    // Find all references to the callback function arguments
    List<Node> keyNodeReferences = Lists.newArrayList();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[88]++;
    List<Node> valueNodeReferences = Lists.newArrayList();

    NodeTraversal.traverse(compiler,
        NodeUtil.getFunctionBody(callbackFunction),
        new FindCallbackArgumentReferences(callbackFunction,
            keyNodeReferences, valueNodeReferences,
            objectToLoopOver.isArrayLit()));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[89]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[90]++;
int CodeCoverConditionCoverageHelper_C26;

    if((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((keyNodeReferences.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[45]++;
     // We didn't do anything useful ...
      t.report(n, JQUERY_USELESS_EACH_EXPANSION, (String)null);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[91]++;
      return;

    } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[46]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[92]++;

    Node fncBlock = tryExpandJqueryEachCall(t, nClone, callbackFunction,
        keyNodeReferences, valueNodeReferences);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[93]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((fncBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((fncBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[47]++;
        replaceOriginalJqueryEachCall(n, fncBlock);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[94]++;

    } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[48]++;
      // We didn't do anything useful ...
      t.report(n, JQUERY_USELESS_EACH_EXPANSION, (String)null);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[95]++;
    }
  }

  private Node tryExpandJqueryEachCall(NodeTraversal t, Node n,
      Node callbackFunction, List<Node> keyNodes, List<Node> valueNodes) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[96]++;

    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[97]++;
    Node objectToLoopOver = callTarget.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[98]++;

    // New block to contain the expanded statements
    Node fncBlock = IR.block().srcref(callTarget);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[99]++;

    boolean isValidExpansion = true;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[100]++;

    // Expand the jQuery.expandedEach call
    Node key = objectToLoopOver.getFirstChild(), val = null;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[101]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[10]++;


int CodeCoverConditionCoverageHelper_C28;
    for(int i = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); key = key.getNext(), i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[10]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[11]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[12]++;
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[102]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[49]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[103]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((objectToLoopOver.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[51]++;
          // Arrays have a value of their index number
          val = IR.number(i).srcref(key);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[104]++;

        } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[52]++;
          val = key.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[105]++;
        }

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[50]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[106]++;

      // Keep track of the replaced nodes so we can reset the tree
      List<Node> newKeys = Lists.newArrayList();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[107]++;
      List<Node> newValues = Lists.newArrayList();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[108]++;
      List<Node> origGetElems = Lists.newArrayList();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[109]++;
      List<Node> newGetProps = Lists.newArrayList();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[110]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[13]++;


int CodeCoverConditionCoverageHelper_C31;

      // Replace all of the key nodes with the prop name
      for (int j = 0;(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((j < keyNodes.size()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[13]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[14]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[15]++;
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[111]++;
        Node origNode = keyNodes.get(j);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[112]++;
        Node ancestor = origNode.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[113]++;

        Node newNode = IR.string(key.getString()).srcref(key);
        newKeys.add(newNode);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[114]++;
        ancestor.replaceChild(origNode, newNode);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[115]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[116]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[16]++;


int CodeCoverConditionCoverageHelper_C32;

        // Walk up the tree to see if the key is used in a GETELEM
        // assignment
        while ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((ancestor != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((NodeUtil.isStatement(ancestor)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((ancestor.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[16]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[17]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[18]++;
}
          ancestor = ancestor.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[117]++;
        }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[118]++;
int CodeCoverConditionCoverageHelper_C33;

        // Convert GETELEM nodes to GETPROP nodes so that they can be
        // renamed or removed.
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((ancestor != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ancestor.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[53]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[119]++;

          Node propObject = ancestor;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[120]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[19]++;


int CodeCoverConditionCoverageHelper_C34;
          while ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((propObject.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((propObject.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[19]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[20]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[21]++;
}
            propObject = propObject.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[121]++;
          }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[122]++;

          Node ancestorClone = ancestor.cloneTree();
          // Run the peephole passes to handle cases such as
          // obj['lit' + key] = val;
          peepholePasses.process(null, ancestorClone.getChildAtIndex(1));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[123]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[124]++;
          Node prop = ancestorClone.getChildAtIndex(1);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[125]++;
int CodeCoverConditionCoverageHelper_C35;

          if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((prop.isString()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((NodeUtil.isValidPropertyName(prop.getString())) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[55]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[126]++;
            Node target = ancestorClone.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[127]++;
            Node newGetProp = IR.getprop(target.detachFromParent(),
                prop.detachFromParent());
            newGetProps.add(newGetProp);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[128]++;
            origGetElems.add(ancestor);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[129]++;
            ancestor.getParent().replaceChild(ancestor, newGetProp);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[130]++;

          } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[56]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[131]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((prop.isString()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((NodeUtil.isValidPropertyName(prop.getString())) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[57]++;
              t.report(n,
                  JQUERY_UNABLE_TO_EXPAND_INVALID_NAME_ERROR,
                  prop.getString());
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[132]++;

            } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[58]++;}
            isValidExpansion = false;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[133]++;
          }

        } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[54]++;}
      }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[134]++;
int CodeCoverConditionCoverageHelper_C37;

      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((isValidExpansion) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[59]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[135]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[22]++;


int CodeCoverConditionCoverageHelper_C38;
        // Replace all of the value nodes with the prop value
        for (int j = 0;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((val != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((j < valueNodes.size()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false); j++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[22]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[23]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[24]++;
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[136]++;
          Node origNode = valueNodes.get(j);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[137]++;
          Node newNode = val.cloneTree();
          newValues.add(newNode);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[138]++;
          origNode.getParent().replaceChild(origNode, newNode);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[139]++;
        }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[140]++;

        // Wrap the new tree in an anonymous function call
        Node fnc = IR.function(IR.name("").srcref(key),
            IR.paramList().srcref(key),
            callbackFunction.getChildAtIndex(2).cloneTree()).srcref(key);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[141]++;
        Node call = IR.call(fnc).srcref(key);
        call.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[142]++;
        fncBlock.addChildToBack(IR.exprResult(call).srcref(call));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[143]++;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[60]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[144]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[25]++;


int CodeCoverConditionCoverageHelper_C39;

      // Reset the source tree
      for (int j = 0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((j < newGetProps.size()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[25]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[26]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[27]++;
}
        newGetProps.get(j).getParent().replaceChild(newGetProps.get(j),
            origGetElems.get(j));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[145]++;
      }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[146]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[28]++;


int CodeCoverConditionCoverageHelper_C40;
      for (int j = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((j < newKeys.size()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[28]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[29]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[30]++;
}
        newKeys.get(j).getParent().replaceChild(newKeys.get(j),
            keyNodes.get(j));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[147]++;
      }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[148]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[31]++;


int CodeCoverConditionCoverageHelper_C41;
      for (int j = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((j < newValues.size()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[31]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[32]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[33]++;
}
        newValues.get(j).getParent().replaceChild(newValues.get(j),
            valueNodes.get(j));
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[149]++;
      }
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[150]++;
int CodeCoverConditionCoverageHelper_C42;

      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((isValidExpansion) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[61]++;
        return null;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[62]++;}
    }
    return fncBlock;
  }

  private void replaceOriginalJqueryEachCall(Node n, Node expandedBlock) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[151]++;
int CodeCoverConditionCoverageHelper_C43;
    // Check to see if the return value of the original jQuery.expandedEach
    // call is used. If so, we need to wrap each loop expansion in an anonymous
    // function and return the original objectToLoopOver.
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((n.getParent().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[63]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[152]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[153]++;
      Node grandparent = parent.getParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[154]++;
      Node insertAfter = parent;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[155]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[34]++;


int CodeCoverConditionCoverageHelper_C44;
      while ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((expandedBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[34]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[35]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[36]++;
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[156]++;
        Node child = expandedBlock.getFirstChild().detachFromParent();
        grandparent.addChildAfter(child, insertAfter);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[157]++;
        insertAfter = child;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[158]++;
      }
      grandparent.removeChild(parent);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[159]++;

    } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[64]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[160]++;
      // Return the original object
      Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[161]++;
      Node objectToLoopOver = callTarget.getNext();

      objectToLoopOver.detachFromParent();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[162]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[163]++;
      Node ret = IR.returnNode(objectToLoopOver).srcref(callTarget);
      expandedBlock.addChildToBack(ret);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[164]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[165]++;

      // Wrap all of the expanded loop calls in a new anonymous function
      Node fnc = IR.function(IR.name("").srcref(callTarget),
          IR.paramList().srcref(callTarget),
          expandedBlock);
      n.replaceChild(callTarget, fnc);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[166]++;
      n.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[167]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[168]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[37]++;


int CodeCoverConditionCoverageHelper_C45;

      // remove any other pre-existing call arguments
      while((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((fnc.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[37]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[38]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[39]++;
}
        n.removeChildAfter(fnc);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[169]++;
      }
    }
    compiler.reportCodeChange();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[170]++;
  }

  private boolean isArrayLitValidForExpansion(Node n) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[171]++;
    Iterator<Node> iter = n.children().iterator();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[172]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[40]++;


int CodeCoverConditionCoverageHelper_C46;
    while ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[40]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[41]--;
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.loops[42]++;
}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[173]++;
      Node child = iter.next();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[174]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((child.isString()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[65]++;
        return false;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[66]++;}
    }
    return true;
  }

  /**
   * Given a jQuery.expandedEach callback function, traverse it and collect any
   * references to its parameter names.
   */
  class FindCallbackArgumentReferences extends AbstractPostOrderCallback
      implements ScopedCallback {

    private final String keyName;
    private final String valueName;
    private Scope startingScope;
    private List<Node> keyReferences;
    private List<Node> valueReferences;

    FindCallbackArgumentReferences(Node functionRoot, List<Node> keyReferences,
        List<Node> valueReferences, boolean useArrayMode) {
      Preconditions.checkState(functionRoot.isFunction());
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[175]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[176]++;

      String keyString = null, valueString = null;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[177]++;
      Node callbackParams = NodeUtil.getFunctionParameters(functionRoot);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[178]++;
      Node param = callbackParams.getFirstChild();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[179]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[67]++;
        Preconditions.checkState(param.isName());
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[180]++;
        keyString = param.getString();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[181]++;

        param = param.getNext();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[182]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[183]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[69]++;
          Preconditions.checkState(param.isName());
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[184]++;
          valueString = param.getString();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[185]++;

        } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[70]++;}

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[68]++;}

      this.keyName = keyString;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[186]++;
      this.valueName = valueString;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[187]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[188]++;
int CodeCoverConditionCoverageHelper_C50;

      // For arrays, the keyString is the index number of the element.
      // We're interested in the value of the element instead
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((useArrayMode) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[71]++;
        this.keyReferences = valueReferences;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[189]++;
        this.valueReferences = keyReferences;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[190]++;

      } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[72]++;
        this.keyReferences = keyReferences;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[191]++;
        this.valueReferences = valueReferences;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[192]++;
      }

      this.startingScope = null;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[193]++;
    }

    private boolean isShadowed(String name, Scope scope) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[194]++;
      Var nameVar = scope.getVar(name);
      return nameVar != null &&
          nameVar.getScope() != this.startingScope;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[195]++;
      // In the top scope, "this" is a reference to "value"
      boolean isThis = false;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[196]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((t.getScope() == this.startingScope) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[73]++;
        isThis = n.isThis();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[197]++;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[74]++;}
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[198]++;
int CodeCoverConditionCoverageHelper_C52;

      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (32)) == 0 || true) &&
 ((isThis) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isShadowed(n.getString(), t.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 3) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[75]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[199]++;
        String nodeValue = isThis ? null : n.getString();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[200]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((isThis) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((keyName != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((nodeValue.equals(keyName)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[77]++;
          keyReferences.add(n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[201]++;

        } else {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[78]++;
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[202]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (32)) == 0 || true) &&
 ((isThis) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((valueName != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((nodeValue.equals(valueName)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 3) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[79]++;
          valueReferences.add(n);
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[203]++;

        } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[80]++;}
}

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[76]++;}
    }

    /**
     * As we enter each scope, make sure that the scope doesn't define
     * a local variable with the same name as our original callback method
     * parameter names.
     */
    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[204]++;
int CodeCoverConditionCoverageHelper_C55;
      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.startingScope == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[81]++;
        this.startingScope = t.getScope();
CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.statements[205]++;

      } else {
  CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox.branches[82]++;}
    }

    @Override
    public void exitScope(NodeTraversal t) { }
  }
}

class CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox ());
  }
    public static long[] statements = new long[206];
    public static long[] branches = new long[83];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[56];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ExpandJqueryAliases.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,2,2,1,1,1,1,2,1,3,1,1,3,1,1,1,1,1,1,1,1,2,3,1,2,1,1,1,1,3,2,2,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,3,3,3,1};
    for (int i = 1; i <= 55; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$g0es02funp4djrx2oy8dpddzrpsbtqstosuox () {
    super("com.google.javascript.jscomp.ExpandJqueryAliases.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 205; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 82; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ExpandJqueryAliases.java");
      for (int i = 1; i <= 205; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 82; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 55; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

