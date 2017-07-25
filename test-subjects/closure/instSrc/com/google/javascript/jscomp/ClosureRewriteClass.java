/*
 * Copyright 2012 The Closure Compiler Authors.
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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;

/**
 * Rewrites "goog.defineClass" into a form that is suitable for
 * type checking and dead code elimination.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class ClosureRewriteClass extends AbstractPostOrderCallback
    implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.ping();
  }


  // Errors
  static final DiagnosticType GOOG_CLASS_TARGET_INVALID = DiagnosticType.error(
      "JSC_GOOG_CLASS_TARGET_INVALID",
      "Unsupported class definition expression.");
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[1]++;
  }

  static final DiagnosticType GOOG_CLASS_SUPER_CLASS_NOT_VALID = DiagnosticType.error(
      "JSC_GOOG_CLASS_SUPER_CLASS_NOT_VALID",
      "The super class must be null or a valid name reference");
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[2]++;
  }

  static final DiagnosticType GOOG_CLASS_DESCRIPTOR_NOT_VALID = DiagnosticType.error(
      "JSC_GOOG_CLASS_DESCRIPTOR_NOT_VALID",
      "The class descriptor must be an object literal");
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[3]++;
  }

  static final DiagnosticType GOOG_CLASS_CONSTRUCTOR_MISING = DiagnosticType.error(
      "JSC_GOOG_CLASS_CONSTRUCTOR_MISING",
      "The constructor expression is missing for the class descriptor");
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[4]++;
  }

  static final DiagnosticType GOOG_CLASS_STATICS_NOT_VALID = DiagnosticType.error(
      "JSC_GOOG_CLASS_STATICS_NOT_VALID",
      "The class statics descriptor must be an object or function literal");
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[5]++;
  }

  static final DiagnosticType GOOG_CLASS_UNEXPECTED_PARAMS = DiagnosticType.error(
      "JSC_GOOG_CLASS_UNEXPECTED_PARAMS",
      "The class definition has too many arguments.");
  static {
    CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[6]++;
  }

  private final AbstractCompiler compiler;

  public ClosureRewriteClass(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[7]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[8]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    this.compiler.process(this);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[9]++;

  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isGoogDefineClass(n)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[1]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((validateUsage(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[3]++;
        compiler.report(JSError.make(n, GOOG_CLASS_TARGET_INVALID));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[12]++;

      } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[2]++;}
    maybeRewriteClassDefinition(n);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[13]++;
  }

  private boolean validateUsage(Node n) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[14]++;
    // There are only three valid usage patterns for of goog.defineClass
    //   var ClassName = googDefineClass
    //   namespace.ClassName = googDefineClass
    //   and within an objectlit, used by the goog.defineClass.
    Node parent = n.getParent();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[15]++;
    switch (parent.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[5]++;
        return true;
      case Token.ASSIGN:
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[6]++;
        return n == parent.getLastChild() && parent.getParent().isExprResult();
      case Token.STRING_KEY:
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[7]++;
        return isContainedInGoogDefineClass(parent); default : CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[8]++;
    }
    return false;
  }

  private boolean isContainedInGoogDefineClass(Node n) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
    while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[1]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[2]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[3]++;
}
      n = n.getParent();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[17]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[9]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isGoogDefineClass(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[11]++;
          return true;

        } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[12]++;}

      } else {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[10]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[20]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[13]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[21]++;
        break;

      } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[14]++;}
}
    }
    return false;
  }

  private void maybeRewriteClassDefinition(Node n) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[15]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[23]++;
      Node target = n.getFirstChild();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[24]++;
      Node value = target.getFirstChild();
      maybeRewriteClassDefinition(n, target, value);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[25]++;

    } else {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[16]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[26]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[17]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[27]++;
      Node assign = n.getFirstChild();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[28]++;
      Node target = assign.getFirstChild();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[29]++;
      Node value = assign.getLastChild();
      maybeRewriteClassDefinition(n, target, value);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[30]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[18]++;}
}
  }

  private void maybeRewriteClassDefinition(
      Node n, Node target, Node value) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isGoogDefineClass(value)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[19]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((target.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[21]++;
        compiler.report(JSError.make(n, GOOG_CLASS_TARGET_INVALID));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[33]++;

      } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[22]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[34]++;
      ClassDefinition def = extractClassDefinition(target, value);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[35]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((def != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[23]++;
        value.detachFromParent();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[36]++;
        target.detachFromParent();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[37]++;
        rewriteGoogDefineClass(n, def);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[38]++;

      } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[24]++;}

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[20]++;}
  }

  private static class MemberDefinition {
    final JSDocInfo info;
    final Node name;
    final Node value;

    MemberDefinition(JSDocInfo info, Node name, Node value) {
      this.info = info;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[39]++;
      this.name = name;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[40]++;
      this.value = value;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[41]++;
    }
  }

  private final class ClassDefinition {
    final Node name;
    final Node superClass;
    final MemberDefinition constructor;
    final List<MemberDefinition> staticProps;
    final List<MemberDefinition> props;
    final Node classModifier;

    ClassDefinition(
        Node name,
        Node superClass,
        MemberDefinition constructor,
        List<MemberDefinition> staticProps,
        List<MemberDefinition> props,
        Node classModifier) {
      this.name = name;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[42]++;
      this.superClass = superClass;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[43]++;
      this.constructor = constructor;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[44]++;
      this.staticProps = staticProps;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[45]++;
      this.props = props;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[46]++;
      this.classModifier = classModifier;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[47]++;
    }
  }

  /**
   * Validates the class definition and if valid, destructively extracts
   * the class definition from the AST.
   */
  private ClassDefinition extractClassDefinition(
      Node targetName, Node callNode) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[48]++;

    // name = goog.defineClass(superClass, {...}, [modifier, ...])
    Node superClass = NodeUtil.getArgumentForCallOrNew(callNode, 0);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((superClass == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 || (!
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((superClass.isNull()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((superClass.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[25]++;
      compiler.report(JSError.make(callNode, GOOG_CLASS_SUPER_CLASS_NOT_VALID));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[50]++;
      return null;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[26]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isNullOrUndefined(superClass)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[27]++;
      superClass = null;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[52]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[28]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[53]++;

    Node description = NodeUtil.getArgumentForCallOrNew(callNode, 1);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((description == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((description.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((validateObjLit(description)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[29]++;
      // report bad class definition
      compiler.report(JSError.make(callNode, GOOG_CLASS_DESCRIPTOR_NOT_VALID));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[55]++;
      return null;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[30]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[56]++;

    int paramCount = callNode.getChildCount() -1;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((paramCount > 2) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[31]++;
      compiler.report(JSError.make(callNode, GOOG_CLASS_UNEXPECTED_PARAMS));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[58]++;
      return null;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[32]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[59]++;

    Node constructor = extractProperty(description, "constructor");
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((constructor == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[33]++;
      // report missing constructor
      compiler.report(JSError.make(description, GOOG_CLASS_CONSTRUCTOR_MISING));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[61]++;
      return null;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[34]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[62]++;
    JSDocInfo info = NodeUtil.getBestJSDocInfo(constructor);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[63]++;

    Node classModifier = null;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[64]++;
    Node statics = null;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[65]++;
    Node staticsProp = extractProperty(description, "statics");
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((staticsProp != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[35]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((staticsProp.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((validateObjLit(staticsProp)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[37]++;
        statics = staticsProp;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[68]++;

      } else {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[38]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[69]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((staticsProp.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[39]++;
        classModifier = staticsProp;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[70]++;

      } else {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[40]++;
        compiler.report(
            JSError.make(staticsProp, GOOG_CLASS_STATICS_NOT_VALID));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[71]++;
        return null;
      }
}

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[36]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[72]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((statics == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[41]++;
      statics = IR.objectlit();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[73]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[42]++;}

    // Ok, now rip apart the definition into its component pieces.
    // Remove the "special" property key nodes.
    maybeDetach(constructor.getParent());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[74]++;
    maybeDetach(statics.getParent());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[75]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((classModifier != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[43]++;
      maybeDetach(classModifier.getParent());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[77]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[44]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[78]++;
    ClassDefinition def = new ClassDefinition(
        targetName,
        maybeDetach(superClass),
        new MemberDefinition(info, null, maybeDetach(constructor)),
        objectLitToList(maybeDetach(statics)),
        objectLitToList(description),
        maybeDetach(classModifier));
    return def;
  }

  private Node maybeDetach(Node node) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((node.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[45]++;
      node.detachFromParent();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[80]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[46]++;}
    return node;
  }

  // Only unquoted plain properties are currently supported.
  private boolean validateObjLit(Node objlit) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[81]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[4]++;


    for (Node key : objlit.children()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[4]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[5]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[6]++;
}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[82]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((key.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((key.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[47]++;
        return false;

      } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[48]++;}
    }
    return true;
  }

  /**
   * @return The first property in the objlit that matches the key.
   */
  private Node extractProperty(Node objlit, String keyName) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[83]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[7]++;


    for (Node keyNode : objlit.children()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[7]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[8]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[9]++;
}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[84]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((keyNode.getString().equals(keyName)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[49]++;
        return keyNode.isStringKey() ? keyNode.getFirstChild() : null;

      } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[50]++;}
    }
    return null;
  }

  private List<MemberDefinition> objectLitToList(
      Node objlit) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[85]++;
    List<MemberDefinition> result = Lists.newArrayList();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[86]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[10]++;


    for (Node keyNode : objlit.children()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[10]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[11]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[12]++;
}
      result.add(
          new MemberDefinition(
                NodeUtil.getBestJSDocInfo(keyNode),
                keyNode,
                keyNode.removeFirstChild()));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[87]++;
    }
    objlit.detachChildren();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[88]++;
    return result;
  }

  private void rewriteGoogDefineClass(Node exprRoot, ClassDefinition cls) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[89]++;

    // For simplicity add everything into a block, before adding it to the AST.
    Node block = IR.block();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[90]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((exprRoot.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[51]++;
      // example: var ctr = function(){}
      block.addChildToBack(
          IR.var(
          cls.name.cloneTree(), cls.constructor.value)
          .srcref(exprRoot).setJSDocInfo(cls.constructor.info));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[91]++;

    } else {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[52]++;
      // example: ns.ctr = function(){}
      block.addChildToBack(
          fixupSrcref(IR.exprResult(
          IR.assign(
          cls.name.cloneTree(), cls.constructor.value)
          .srcref(exprRoot).setJSDocInfo(cls.constructor.info)
          .srcref(exprRoot))).setJSDocInfo(cls.constructor.info));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[92]++;
    }
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[93]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((cls.superClass != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[53]++;
      // example: goog.inherits(ctr, superClass)
      block.addChildToBack(
          fixupSrcref(IR.exprResult(
              IR.call(
                  NodeUtil.newQualifiedNameNode(
                      compiler.getCodingConvention(), "goog.inherits")
                      .srcrefTree(cls.superClass),
                  cls.name.cloneTree(),
                  cls.superClass.cloneTree()).srcref(cls.superClass))));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[94]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[54]++;}
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[95]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[13]++;



    for (MemberDefinition def : cls.staticProps) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[13]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[14]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[15]++;
}
      // example: ctr.prop = value
      block.addChildToBack(
          fixupSrcref(IR.exprResult(
          fixupSrcref(IR.assign(
              IR.getprop(cls.name.cloneTree(),
                  IR.string(def.name.getString()).srcref(def.name))
                  .srcref(def.name),
              def.value)).setJSDocInfo(def.info))));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[96]++;
      // Handle inner class definitions.
      maybeRewriteClassDefinition(block.getLastChild());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[97]++;
    }
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[98]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[16]++;



    for (MemberDefinition def : cls.props) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[16]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[17]--;
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.loops[18]++;
}
      // example: ctr.prototype.prop = value
      block.addChildToBack(
          fixupSrcref(IR.exprResult(
          fixupSrcref(IR.assign(
              IR.getprop(
                  fixupSrcref(IR.getprop(cls.name.cloneTree(),
                      IR.string("prototype").srcref(def.name))),
                  IR.string(def.name.getString()).srcref(def.name))
                  .srcref(def.name),
              def.value)).setJSDocInfo(def.info))));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[99]++;
      // Handle inner class definitions.
      maybeRewriteClassDefinition(block.getLastChild());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[100]++;
    }
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[101]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((cls.classModifier != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[55]++;
      // example: modifier(ctr)
      block.addChildToBack(
          IR.exprResult(
              fixupFreeCall(
                  IR.call(
                      cls.classModifier,
                      cls.name.cloneTree())
                      .srcref(cls.classModifier)))
              .srcref(cls.classModifier));
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[102]++;

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[56]++;}

    exprRoot.getParent().replaceChild(exprRoot, block);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[103]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[104]++;
  }

  private Node fixupSrcref(Node node) {
    node.srcref(node.getFirstChild());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[105]++;
    return node;
  }

  private Node fixupFreeCall(Node call) {
    Preconditions.checkState(call.isCall());
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[106]++;
    call.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[107]++;
    return call;
  }

  /**
   * @return Whether the call represents a class definition.
   */
  private boolean isGoogDefineClass(Node value) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[108]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((value.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[57]++;
CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.statements[109]++;
      String targetName = value.getFirstChild().getQualifiedName();
      return ("goog.defineClass".equals(targetName) ||
              "goog.labs.classdef.defineClass".equals(targetName));

    } else {
  CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd.branches[58]++;}
    return false;
  }
}

class CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd ());
  }
    public static long[] statements = new long[110];
    public static long[] branches = new long[59];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[29];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ClosureRewriteClass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,2,1,1,1,1,1,3,1,3,1,1,1,2,1,1,1,2,2,1,1,1,1,2};
    for (int i = 1; i <= 28; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$fjfdg27dd8eanvtwl8xap6p9lf73dvkf5ovhd () {
    super("com.google.javascript.jscomp.ClosureRewriteClass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 109; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 58; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ClosureRewriteClass.java");
      for (int i = 1; i <= 109; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 58; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 28; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

