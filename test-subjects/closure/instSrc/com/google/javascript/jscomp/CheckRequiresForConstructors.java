/*
 * Copyright 2008 The Closure Compiler Authors.
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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Set;


/**
 * This pass walks the AST to create a Collection of 'new' nodes and
 * 'goog.require' nodes. It reconciles these Collections, creating a
 * warning for each discrepancy.
 *
 */
class CheckRequiresForConstructors implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.ping();
  }

  private final AbstractCompiler compiler;
  private final CodingConvention codingConvention;
  private final CheckLevel level;

  // Warnings
  static final DiagnosticType MISSING_REQUIRE_WARNING = DiagnosticType.disabled(
      "JSC_MISSING_REQUIRE_WARNING",
      "''{0}'' used but not goog.require''d");
  static {
    CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[1]++;
  }

  CheckRequiresForConstructors(AbstractCompiler compiler,
      CheckLevel level) {
    this.compiler = compiler;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[2]++;
    this.codingConvention = compiler.getCodingConvention();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[3]++;
    this.level = level;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[4]++;
  }

  /**
   * Uses Collections of new and goog.require nodes to create a compiler warning
   * for each new class name without a corresponding goog.require().
   */
  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[5]++;
    Callback callback = new CheckRequiresForConstructorsCallback();
    new NodeTraversal(compiler, callback).traverseRoots(externs, root);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[6]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[7]++;
    Callback callback = new CheckRequiresForConstructorsCallback();
    new NodeTraversal(compiler, callback).traverseWithScope(scriptRoot,
        SyntacticScopeCreator.generateUntypedTopScope(compiler));
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[8]++;
  }

  // Return true if the name is a class name (starts with an uppercase
  // character, but is not in all caps).
  private static boolean isClassName(String name) {
    return (name != null && name.length() > 1
            && Character.isUpperCase(name.charAt(0))
            && !name.equals(name.toUpperCase()));
  }

  // Return the shortest prefix of the className that refers to a class,
  // or null if no part refers to a class.
  private static String getOutermostClassName(String className) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[1]++;


    for (String part : className.split("\\.")) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[1]--;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[2]--;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[3]++;
}
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isClassName(part)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[1]++;
        return className.substring(0, className.indexOf(part) +
                                   part.length());

      } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[2]++;}
    }

    return null;
  }

  /**
   * This class "records" each constructor and goog.require visited and creates
   * a warning for each new node without an appropriate goog.require node.
   *
   */
  private class CheckRequiresForConstructorsCallback implements Callback {
    private final List<String> constructors = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[11]++;
  }
    private final List<String> requires = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[12]++;
  }
    private final List<Node> newNodes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[13]++;
  }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      return parent == null || !parent.isScript() ||
          !t.getInput().isExtern();
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
      JSDocInfo info;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[14]++;
      switch (n.getType()) {
        case Token.ASSIGN:
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[3]++;
          info = (JSDocInfo) n.getProp(Node.JSDOC_INFO_PROP);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[15]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[4]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[17]++;
            String qualifiedName = n.getFirstChild().getQualifiedName();
            constructors.add(qualifiedName);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[18]++;

          } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[5]++;}
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[19]++;
          break;
        case Token.FUNCTION:
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[6]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[7]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[9]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[22]++;
              String functionName = parent.getString();
              info = (JSDocInfo) parent.getProp(Node.JSDOC_INFO_PROP);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[23]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
              if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[11]++;
                constructors.add(functionName);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[25]++;

              } else {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[12]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[26]++;
                Node gramps = parent.getParent();
                Preconditions.checkState(
                    gramps != null && gramps.isVar());
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[27]++;
                info = (JSDocInfo) gramps.getProp(Node.JSDOC_INFO_PROP);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[28]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[13]++;
                  constructors.add(functionName);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[30]++;

                } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[14]++;}
              }

            } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[10]++;}

          } else {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[8]++;
            info = (JSDocInfo) n.getProp(Node.JSDOC_INFO_PROP);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[31]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[15]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[33]++;
              String functionName = n.getFirstChild().getString();
              constructors.add(functionName);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[34]++;

            } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[16]++;}
          }
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[35]++;
          break;
        case Token.CALL:
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[17]++;
          visitCallNode(n, parent);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[36]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[37]++;
          break;
        case Token.SCRIPT:
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[18]++;
          visitScriptNode(t);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[38]++;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[39]++;
          break;
        case Token.NEW:
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[19]++;
          visitNewNode(t, n);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[40]++; default : CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[20]++;
      }
    }

    private void visitScriptNode(NodeTraversal t) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[41]++;
      Set<String> classNames = Sets.newHashSet();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[42]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[4]++;


      for (Node node : newNodes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[4]--;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[5]--;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[6]++;
}
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[43]++;
        String className = node.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[44]++;
        String outermostClassName = getOutermostClassName(className);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[45]++;
        boolean notProvidedByConstructors =
            (constructors == null || !constructors.contains(className));
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[46]++;
        boolean notProvidedByRequires =
            (requires == null || (!requires.contains(className)
                                  && !requires.contains(outermostClassName)));
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[47]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((notProvidedByConstructors) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((notProvidedByRequires) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((classNames.contains(className)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[21]++;
          compiler.report(
              t.makeError(node, level, MISSING_REQUIRE_WARNING, className));
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[48]++;
          classNames.add(className);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[49]++;

        } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[22]++;}
      }
      // for the next script, if there is one, we don't want the new, ctor, and
      // require nodes to spill over.
      this.newNodes.clear();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[50]++;
      this.requires.clear();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[51]++;
      this.constructors.clear();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[52]++;
    }

    private void visitCallNode(Node n, Node parent) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[53]++;
      String required = codingConvention.extractClassNameIfRequire(n, parent);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((required != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[23]++;
        requires.add(required);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[55]++;

      } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[24]++;}
    }

    private void visitNewNode(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[56]++;
      Node qNameNode = n.getFirstChild();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[57]++;
int CodeCoverConditionCoverageHelper_C10;

      // If the ctor is something other than a qualified name, ignore it.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((qNameNode.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[25]++;
        return;

      } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[26]++;}
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[58]++;

      // Grab the root ctor namespace.
      Node nameNode = qNameNode;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[59]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;
      for (;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((nameNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); nameNode = nameNode.getFirstChild()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[7]--;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[8]--;
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.loops[9]++;
}}
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[60]++;
int CodeCoverConditionCoverageHelper_C12;

      // We only consider programmer-defined constructors that are
      // global variables, or are defined on global variables.
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((nameNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[27]++;
        return;

      } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[28]++;}
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[61]++;

      String name = nameNode.getString();
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[62]++;
      Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[63]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((var.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((var.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[29]++;
        return;

      } else {
  CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.branches[30]++;}
      newNodes.add(n);
CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69.statements[64]++;
    }
  }
}

class CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69 ());
  }
    public static long[] statements = new long[65];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckRequiresForConstructors.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,2,2,2,3,1,1,1,1,3};
    for (int i = 1; i <= 13; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$by3ht0uk5bk6m58fsqzzq9ena2lccir0cyvybxde9caij84ig69 () {
    super("com.google.javascript.jscomp.CheckRequiresForConstructors.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 64; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckRequiresForConstructors.java");
      for (int i = 1; i <= 64; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

