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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.AbstractCompiler.LifeCycleStage;
import com.google.javascript.jscomp.MakeDeclaredNamesUnique.BoilerplateRenamer;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Map;
import java.util.Set;

/**
 * The goal with this pass is to simplify the other passes,
 * by making less complex statements.
 *
 * Starting with statements like:
 *   var a = 0, b = foo();
 *
 * Which become:
 *   var a = 0;
 *   var b = foo();
 *
 * The key here is only to break down things that help the other passes
 * and can be put back together in a form that is at least as small when
 * all is said and done.
 *
 * This pass currently does the following:
 * 1) Simplifies the AST by splitting var statements, moving initializers
 *    out of for loops, and converting whiles to fors.
 * 2) Moves hoisted functions to the top of function scopes.
 * 3) Rewrites unhoisted named function declarations to be var declarations.
 * 4) Makes all variable names globally unique (extern or otherwise) so that
 *    no value is ever shadowed (note: "arguments" may require special
 *    handling).
 * 5) Removes duplicate variable declarations.
 * 6) Marks constants with the IS_CONSTANT_NAME annotation.
 * 7) Finds properties marked @expose, and rewrites them in [] notation.
 *
 * @author johnlenz@google.com (johnlenz)
 */
// public for ReplaceDebugStringsTest
class Normalize implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.ping();
  }


  private final AbstractCompiler compiler;
  private final boolean assertOnChange;
  private static final boolean CONVERT_WHILE_TO_FOR = true;
  static {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[1]++;
  }
  static final boolean MAKE_LOCAL_NAMES_UNIQUE = true;
  static {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[2]++;
  }

  public static final DiagnosticType CATCH_BLOCK_VAR_ERROR =
    DiagnosticType.error(
        "JSC_CATCH_BLOCK_VAR_ERROR",
        "The use of scope variable {0} is not allowed within a catch block " +
        "with a catch exception of the same name.");
  static {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[3]++;
  }


  Normalize(AbstractCompiler compiler, boolean assertOnChange) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[4]++;
    this.assertOnChange = assertOnChange;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[5]++;

    // TODO(nicksantos): assertOnChange should only be true if the tree
    // is normalized.
  }

  static Node parseAndNormalizeSyntheticCode(
      AbstractCompiler compiler, String code, String prefix) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[6]++;
    Node js = compiler.parseSyntheticCode(code);
    NodeTraversal.traverse(compiler, js,
        new Normalize.NormalizeStatements(compiler, false));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[7]++;
    NodeTraversal.traverse(
        compiler, js,
        new MakeDeclaredNamesUnique(
            new BoilerplateRenamer(
                compiler.getCodingConvention(),
                compiler.getUniqueNameIdSupplier(),
                prefix)));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[8]++;
    return js;
  }

  static Node parseAndNormalizeTestCode(
      AbstractCompiler compiler, String code) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[9]++;
    Node js = compiler.parseTestCode(code);
    NodeTraversal.traverse(compiler, js,
        new Normalize.NormalizeStatements(compiler, false));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[10]++;
    NodeTraversal.traverse(
        compiler, js,
        new MakeDeclaredNamesUnique());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[11]++;
    return js;
  }

  private void reportCodeChange(String changeDescription) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((assertOnChange) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[1]++;
      throw new IllegalStateException(
          "Normalize constraints violated:\n" + changeDescription);

    } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[2]++;}
    compiler.reportCodeChange();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[13]++;
  }

  @Override
  public void process(Node externs, Node root) {
    new NodeTraversal(
        compiler, new NormalizeStatements(compiler, assertOnChange))
        .traverseRoots(externs, root);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[14]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((MAKE_LOCAL_NAMES_UNIQUE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[3]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[16]++;
      MakeDeclaredNamesUnique renamer = new MakeDeclaredNamesUnique();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[17]++;
      NodeTraversal t = new NodeTraversal(compiler, renamer);
      t.traverseRoots(externs, root);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[18]++;

    } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[4]++;}
    // It is important that removeDuplicateDeclarations runs after
    // MakeDeclaredNamesUnique in order for catch block exception names to be
    // handled properly. Specifically, catch block exception names are
    // only valid within the catch block, but our current Scope logic
    // has no concept of this and includes it in the containing function
    // (or global scope). MakeDeclaredNamesUnique makes the catch exception
    // names unique so that removeDuplicateDeclarations() will properly handle
    // cases where a function scope variable conflict with a exception name:
    //   function f() {
    //      try {throw 0;} catch(e) {e; /* catch scope 'e'*/}
    //      var e = 1; // f scope 'e'
    //   }
    // otherwise 'var e = 1' would be rewritten as 'e = 1'.
    // TODO(johnlenz): Introduce a separate scope for catch nodes.
    removeDuplicateDeclarations(externs, root);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[19]++;
    new PropagateConstantAnnotationsOverVars(compiler, assertOnChange)
        .process(externs, root);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[20]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[21]++;

    FindExposeAnnotations findExposeAnnotations = new FindExposeAnnotations();
    NodeTraversal.traverse(compiler, root, findExposeAnnotations);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[22]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((findExposeAnnotations.exposedProperties.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[5]++;
      NodeTraversal.traverse(compiler, root,
          new RewriteExposedProperties(
              findExposeAnnotations.exposedProperties));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[24]++;

    } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[6]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((compiler.getLifeCycleStage().isNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[7]++;
      compiler.setLifeCycleStage(LifeCycleStage.NORMALIZED);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[26]++;

    } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[8]++;}
  }

  /**
   * Find all the @expose annotations.
   */
  private static class FindExposeAnnotations extends AbstractPostOrderCallback {
    private final Set<String> exposedProperties = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[27]++;
  }

    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[9]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[29]++;
        Node assign = n.getFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[30]++;
        Node lhs = assign.getFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isMarkedExpose(assign)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[11]++;
          exposedProperties.add(lhs.getLastChild().getString());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[32]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[12]++;}

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[10]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[33]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((n.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isMarkedExpose(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[13]++;
        exposedProperties.add(n.getString());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[34]++;

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[14]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[35]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((n.getParent().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isMarkedExpose(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[15]++;
        exposedProperties.add(n.getLastChild().getString());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[36]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[16]++;}
}
}
    }

    private boolean isMarkedExpose(Node n) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[37]++;
      JSDocInfo info = n.getJSDocInfo();
      return info != null && info.isExpose();
    }
  }

  /**
   * Rewrite all exposed properties in [] form.
   */
  private class RewriteExposedProperties
      extends AbstractPostOrderCallback {
    private final Set<String> exposedProperties;

    RewriteExposedProperties(Set<String> exposedProperties) {
      this.exposedProperties = exposedProperties;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[38]++;
    }

    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[17]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[40]++;
        String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((exposedProperties.contains(propName)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[19]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[42]++;
          Node obj = n.removeFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[43]++;
          Node prop = n.removeFirstChild();
          n.getParent().replaceChild(n, IR.getelem(obj, prop));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[44]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[45]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[20]++;}

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[18]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[46]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[21]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[47]++;
        String propName = n.getString();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[48]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((exposedProperties.contains(propName)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[23]++;
          n.setQuotedString();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[49]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[50]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[24]++;}

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[22]++;}
}
    }
  }

  /**
   * Propagate constant annotations over the Var graph.
   */
  static class PropagateConstantAnnotationsOverVars
      extends AbstractPostOrderCallback
      implements CompilerPass {
    private final AbstractCompiler compiler;
    private final boolean assertOnChange;

    PropagateConstantAnnotationsOverVars(
        AbstractCompiler compiler, boolean forbidChanges) {
      this.compiler = compiler;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[51]++;
      this.assertOnChange = forbidChanges;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[52]++;
    }

    @Override
    public void process(Node externs, Node root) {
      new NodeTraversal(compiler, this).traverseRoots(externs, root);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[53]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[54]++;
int CodeCoverConditionCoverageHelper_C13;
      // Note: Constant properties annotations are not propagated.
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[25]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[55]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n.getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[27]++;
          return;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[28]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[56]++;

        JSDocInfo info = null;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[57]++;
        // Find the JSDocInfo for a top-level variable.
        Var var = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[58]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[29]++;
          info = var.getJSDocInfo();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[59]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[30]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[60]++;

        boolean shouldBeConstant =
            (info != null && info.isConstant()) ||
            NodeUtil.isConstantByConvention(
                compiler.getCodingConvention(), n, parent);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[61]++;
        boolean isMarkedConstant = n.getBooleanProp(Node.IS_CONSTANT_NAME);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[62]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((shouldBeConstant) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isMarkedConstant) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[31]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((assertOnChange) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[33]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[64]++;
            String name = n.getString();
            throw new IllegalStateException(
                "Unexpected const change.\n" +
                "  name: "+ name + "\n" +
                "  parent:" + n.getParent().toStringTree());

          } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[34]++;}
          n.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[65]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[32]++;}

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[26]++;}
    }
  }

  /**
   * Walk the AST tree and verify that constant names are used consistently.
   */
  static class VerifyConstants extends AbstractPostOrderCallback
      implements CompilerPass {

    final private AbstractCompiler compiler;
    final private boolean checkUserDeclarations;

    VerifyConstants(AbstractCompiler compiler, boolean checkUserDeclarations) {
      this.compiler = compiler;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[66]++;
      this.checkUserDeclarations = checkUserDeclarations;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[67]++;
    }

    @Override
    public void process(Node externs, Node root) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[68]++;
      Node externsAndJs = root.getParent();
      Preconditions.checkState(externsAndJs != null);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[69]++;
      Preconditions.checkState(externsAndJs.hasChild(externs));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[70]++;

      NodeTraversal.traverseRoots(
          compiler, Lists.newArrayList(externs, root), this);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[71]++;
    }

    private Map<String, Boolean> constantMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[72]++;
  }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[35]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[74]++;
        String name = n.getString();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[75]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n.getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[37]++;
          return;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[38]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[76]++;

        boolean isConst = n.getBooleanProp(Node.IS_CONSTANT_NAME);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((checkUserDeclarations) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[39]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[78]++;
          boolean expectedConst = false;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[79]++;
          CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[80]++;
int CodeCoverConditionCoverageHelper_C21;
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((NodeUtil.isConstantName(n)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.isConstantByConvention(convention, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[41]++;
            expectedConst = true;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[81]++;

          } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[42]++;
            expectedConst = false;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[82]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[83]++;

            JSDocInfo info = null;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[84]++;
            Var var = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[43]++;
              info = var.getJSDocInfo();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[86]++;

            } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[44]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[87]++;
int CodeCoverConditionCoverageHelper_C23;

            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((info.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[45]++;
              expectedConst = true;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[88]++;

            } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[46]++;
              expectedConst = false;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[89]++;
            }
          }
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[90]++;
int CodeCoverConditionCoverageHelper_C24;

          if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((expectedConst) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[47]++;
            Preconditions.checkState(expectedConst == isConst,
                "The name %s is not annotated as constant.", name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[91]++;

          } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[48]++;
            Preconditions.checkState(expectedConst == isConst,
                "The name %s should not be annotated as constant.", name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[92]++;
          }

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[40]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[93]++;

        Boolean value = constantMap.get(name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[94]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[49]++;
          constantMap.put(name, isConst);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[95]++;

        } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[50]++;
          Preconditions.checkState(value.booleanValue() == isConst,
              "The name %s is not consistently annotated as constant.", name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[96]++;
        }

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[36]++;}
    }
  }

  /**
   * Simplify the AST:
   *   - VAR declarations split, so they represent exactly one child
   *     declaration.
   *   - WHILEs are converted to FORs
   *   - FOR loop are initializers are moved out of the FOR structure
   *   - LABEL node of children other than LABEL, BLOCK, WHILE, FOR, or DO are
   *     moved into a block.
   *   - Add constant annotations based on coding convention.
   */
  static class NormalizeStatements implements Callback {
    private final AbstractCompiler compiler;
    private final boolean assertOnChange;

    NormalizeStatements(AbstractCompiler compiler, boolean assertOnChange) {
      this.compiler = compiler;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[97]++;
      this.assertOnChange = assertOnChange;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[98]++;
    }

    private void reportCodeChange(String changeDescription) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((assertOnChange) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[51]++;
        throw new IllegalStateException(
            "Normalize constraints violated:\n" + changeDescription);

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[52]++;}
      compiler.reportCodeChange();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[100]++;
    }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      doStatementNormalizations(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[101]++;

      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[102]++;
      switch (n.getType()) {
        case Token.WHILE:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[53]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[103]++;
int CodeCoverConditionCoverageHelper_C27;
          if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((CONVERT_WHILE_TO_FOR) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[54]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[104]++;
            Node expr = n.getFirstChild();
            n.setType(Token.FOR);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[105]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[106]++;
            Node empty = IR.empty();
            empty.copyInformationFrom(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[107]++;
            n.addChildBefore(empty, expr);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[108]++;
            n.addChildAfter(empty.cloneNode(), expr);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[109]++;
            reportCodeChange("WHILE node");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[110]++;

          } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[55]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[111]++;
          break;

        case Token.FUNCTION:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[56]++;
          normalizeFunctionDeclaration(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[112]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[113]++;
          break;

        case Token.NAME:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[57]++;
        case Token.STRING:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[58]++;
        case Token.STRING_KEY:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[59]++;
        case Token.GETTER_DEF:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[60]++;
        case Token.SETTER_DEF:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[61]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[114]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((compiler.getLifeCycleStage().isNormalizedObfuscated()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[62]++;
            annotateConstantsByConvention(n, parent);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[115]++;

          } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[63]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[116]++;
          break;

        case Token.CAST:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[64]++;
          parent.replaceChild(n, n.removeFirstChild());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[117]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[118]++;
          break; default : CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[65]++;
      }
    }

    /**
     * Mark names and properties that are constants by convention.
     */
    private void annotateConstantsByConvention(Node n, Node parent) {
      Preconditions.checkState(
          n.isName()
          || n.isString()
          || n.isStringKey()
          || n.isGetterDef()
          || n.isSetterDef());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[119]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[120]++;

      // There are only two cases where a string token
      // may be a variable reference: The right side of a GETPROP
      // or an OBJECTLIT key.
      boolean isObjLitKey = NodeUtil.isObjectLitKey(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[121]++;
      boolean isProperty = isObjLitKey ||
          (parent.isGetProp() &&
           parent.getLastChild() == n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[122]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isProperty) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[66]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[123]++;
        boolean isMarkedConstant = n.getBooleanProp(Node.IS_CONSTANT_NAME);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[124]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((isMarkedConstant) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((NodeUtil.isConstantByConvention(
                compiler.getCodingConvention(), n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[68]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[125]++;
int CodeCoverConditionCoverageHelper_C31;
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((assertOnChange) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[70]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[126]++;
            String name = n.getString();
            throw new IllegalStateException(
                "Unexpected const change.\n" +
                "  name: "+ name + "\n" +
                "  parent:" + n.getParent().toStringTree());

          } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[71]++;}
          n.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[127]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[69]++;}

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[67]++;}
    }

    /**
     * Rewrite named unhoisted functions declarations to a known
     * consistent behavior so we don't to different logic paths for the same
     * code. From:
     *    function f() {}
     * to:
     *    var f = function () {};
     */
    private void normalizeFunctionDeclaration(Node n) {
      Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[128]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[129]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((NodeUtil.isHoistedFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[72]++;
        rewriteFunctionDeclaration(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[130]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[73]++;}
    }

    /**
     * Rewrite the function declaration from:
     *   function x() {}
     *   FUNCTION
     *     NAME
     *     LP
     *     BLOCK
     * to:
     *   var x = function() {};
     *   VAR
     *     NAME
     *       FUNCTION
     *         NAME (w/ empty string)
     *         LP
     *         BLOCK
     */
    private void rewriteFunctionDeclaration(Node n) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[131]++;
      // Prepare a spot for the function.
      Node oldNameNode = n.getFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[132]++;
      Node fnNameNode = oldNameNode.cloneNode();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[133]++;
      Node var = IR.var(fnNameNode).srcref(n);

      // Prepare the function
      oldNameNode.setString("");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[134]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[135]++;

      // Move the function
      Node parent = n.getParent();
      parent.replaceChild(n, var);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[136]++;
      fnNameNode.addChildToFront(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[137]++;

      reportCodeChange("Function declaration");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[138]++;
    }

    /**
     * Do normalizations that introduce new siblings or parents.
     */
    private void doStatementNormalizations(Node n) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[139]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[74]++;
        normalizeLabels(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[140]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[75]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[141]++;
int CodeCoverConditionCoverageHelper_C34;

      // Only inspect the children of SCRIPTs, BLOCKs and LABELs, as all these
      // are the only legal place for VARs and FOR statements.
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(n)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((n.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[76]++;
        extractForInitializer(n, null, null);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[142]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[77]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[143]++;
int CodeCoverConditionCoverageHelper_C35;

      // Only inspect the children of SCRIPTs, BLOCKs, as all these
      // are the only legal place for VARs.
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(n)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[78]++;
        splitVarDeclarations(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[144]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[79]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[145]++;
int CodeCoverConditionCoverageHelper_C36;

      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[80]++;
        moveNamedFunctions(n.getLastChild());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[146]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[81]++;}
    }

    // TODO(johnlenz): Move this to NodeTypeNormalizer once the unit tests are
    // fixed.
    /**
     * Limit the number of special cases where LABELs need to be handled. Only
     * BLOCK and loops are allowed to be labeled.  Loop labels must remain in
     * place as the named continues are not allowed for labeled blocks.
     */
    private void normalizeLabels(Node n) {
      Preconditions.checkArgument(n.isLabel());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[147]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[148]++;

      Node last = n.getLastChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[149]++;
      switch (last.getType()) {
        case Token.LABEL:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[82]++;
        case Token.BLOCK:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[83]++;
        case Token.FOR:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[84]++;
        case Token.WHILE:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[85]++;
        case Token.DO:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[86]++;
          return;
        default:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[87]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[150]++;
          Node block = IR.block();
          block.copyInformationFrom(last);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[151]++;
          n.replaceChild(last, block);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[152]++;
          block.addChildToFront(last);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[153]++;
          reportCodeChange("LABEL normalization");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[154]++;
          return;
      }
    }

    /**
     * Bring the initializers out of FOR loops.  These need to be placed
     * before any associated LABEL nodes. This needs to be done from the top
     * level label first so this is called as a pre-order callback (from
     * shouldTraverse).
     *
     * @param n The node to inspect.
     * @param before The node to insert the initializer before.
     * @param beforeParent The parent of the node before which the initializer
     *     will be inserted.
     */
    private void extractForInitializer(
        Node n, Node before, Node beforeParent) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[155]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[1]++;


int CodeCoverConditionCoverageHelper_C37;

      for (Node next, c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); c = next) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[1]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[2]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[3]++;
}
        next = c.getNext();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[156]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[157]++;
        Node insertBefore = (before == null) ? c : before;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[158]++;
        Node insertBeforeParent = (before == null) ? n : beforeParent;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[159]++;
        switch (c.getType()) {
          case Token.LABEL:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[88]++;
            extractForInitializer(c, insertBefore, insertBeforeParent);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[160]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[161]++;
            break;
          case Token.FOR:
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[89]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[162]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(c)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[90]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[163]++;
              Node first = c.getFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[164]++;
int CodeCoverConditionCoverageHelper_C39;
              if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((first.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[92]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[165]++;
                // Transform:
                //    for (var a = 1 in b) {}
                // to:
                //    var a = 1; for (a in b) {};
                Node newStatement = first;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[166]++;
                // Clone just the node, to remove any initialization.
                Node name = newStatement.getFirstChild().cloneNode();
                first.getParent().replaceChild(first, name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[167]++;
                insertBeforeParent.addChildBefore(newStatement, insertBefore);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[168]++;
                reportCodeChange("FOR-IN var declaration");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[169]++;

              } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[93]++;}

            } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[91]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[170]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c.getFirstChild().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[94]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[171]++;
              Node init = c.getFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[172]++;
              Node empty = IR.empty();
              empty.copyInformationFrom(c);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[173]++;
              c.replaceChild(init, empty);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[174]++;

              Node newStatement;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[175]++;
int CodeCoverConditionCoverageHelper_C41;
              // Only VAR statements, and expressions are allowed,
              // but are handled differently.
              if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((init.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[96]++;
                newStatement = init;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[176]++;

              } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[97]++;
                newStatement = NodeUtil.newExpr(init);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[177]++;
              }

              insertBeforeParent.addChildBefore(newStatement, insertBefore);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[178]++;
              reportCodeChange("FOR initializer");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[179]++;

            } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[95]++;}
}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[180]++;
            break; default : CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[98]++;
        }
      }
    }

    /**
     * Split a var node such as:
     *   var a, b;
     * into individual statements:
     *   var a;
     *   var b;
     * @param n The whose children we should inspect.
     */
    private void splitVarDeclarations(Node n) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[181]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[4]++;


int CodeCoverConditionCoverageHelper_C42;
      for (Node next, c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); c = next) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[4]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[5]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[6]++;
}
        next = c.getNext();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[182]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[183]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((c.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[99]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[184]++;
int CodeCoverConditionCoverageHelper_C44;
          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((assertOnChange) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((c.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[101]++;
            throw new IllegalStateException("Empty VAR node.");

          } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[102]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[185]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[7]++;


int CodeCoverConditionCoverageHelper_C45;

          while ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((c.getFirstChild() != c.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[7]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[8]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[9]++;
}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[186]++;
            Node name = c.getFirstChild();
            c.removeChild(name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[187]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[188]++;
            Node newVar = IR.var(name).srcref(n);
            n.addChildBefore(newVar, c);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[189]++;
            reportCodeChange("VAR with multiple children");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[190]++;
          }

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[100]++;}
      }
    }

    /**
     * Move all the functions that are valid at the execution of the first
     * statement of the function to the beginning of the function definition.
     */
    private void moveNamedFunctions(Node functionBody) {
      Preconditions.checkState(
          functionBody.getParent().isFunction());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[191]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[192]++;
      Node previous = null;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[193]++;
      Node current = functionBody.getFirstChild();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[194]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[10]++;


int CodeCoverConditionCoverageHelper_C46;
      // Skip any declarations at the beginning of the function body, they
      // are already in the right place.
      while ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(current)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[10]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[11]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[12]++;
}
        previous = current;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[195]++;
        current = current.getNext();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[196]++;
      }
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[197]++;

      // Find any remaining declarations and move them.
      Node insertAfter = previous;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[198]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[13]++;


int CodeCoverConditionCoverageHelper_C47;
      while ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[13]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[14]--;
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.loops[15]++;
}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[199]++;
        // Save off the next node as the current node maybe removed.
        Node next = current.getNext();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[200]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(current)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[103]++;
          // Remove the declaration from the body.
          Preconditions.checkNotNull(previous);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[201]++;
          functionBody.removeChildAfter(previous);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[202]++;

          // Read the function at the top of the function body (after any
          // previous declarations).
          insertAfter = addToFront(functionBody, current, insertAfter);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[203]++;
          reportCodeChange("Move function declaration not at top of function");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[204]++;

        } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[104]++;
          // Update the previous only if the current node hasn't been moved.
          previous = current;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[205]++;
        }
        current = next;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[206]++;
      }
    }

    /**
     * @param after The child node to insert the newChild after, or null if
     *     newChild should be added to the front of parent's child list.
     * @return The inserted child node.
     */
    private Node addToFront(Node parent, Node newChild, Node after) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[207]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((after == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[105]++;
        parent.addChildToFront(newChild);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[208]++;

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[106]++;
        parent.addChildAfter(newChild, after);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[209]++;
      }
      return newChild;
    }
  }

  /**
   * Remove duplicate VAR declarations.
   */
  private void removeDuplicateDeclarations(Node externs, Node root) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[210]++;
    Callback tickler = new ScopeTicklingCallback();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[211]++;
    ScopeCreator scopeCreator =  new SyntacticScopeCreator(
        compiler, new DuplicateDeclarationHandler());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[212]++;
    NodeTraversal t = new NodeTraversal(compiler, tickler, scopeCreator);
    t.traverseRoots(externs, root);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[213]++;
  }

  /**
   * ScopeCreator duplicate declaration handler.
   */
  private final class DuplicateDeclarationHandler implements
      SyntacticScopeCreator.RedeclarationHandler {

    private Set<Var> hasOkDuplicateDeclaration = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[214]++;
  }

    /**
     * Remove duplicate VAR declarations encountered discovered during
     * scope creation.
     */
    @Override
    public void onRedeclaration(
        Scope s, String name, Node n, CompilerInput input) {
      Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[215]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[216]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[217]++;
      Var v = s.getVar(name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[218]++;
int CodeCoverConditionCoverageHelper_C50;

      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((s.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[107]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[219]++;
int CodeCoverConditionCoverageHelper_C51;
        // We allow variables to be duplicate declared if one
        // declaration appears in source and the other in externs.
        // This deals with issues where a browser built-in is declared
        // in one browser but not in another.
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((v.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((input.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[109]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[220]++;
int CodeCoverConditionCoverageHelper_C52;
          if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((hasOkDuplicateDeclaration.add(v)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[111]++;
            return;

          } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[112]++;}

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[110]++;}

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[108]++;}
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[221]++;
int CodeCoverConditionCoverageHelper_C53;

      // If name is "arguments", Var maybe null.
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((v.getParentNode().isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[113]++;
        // Redeclaration of a catch expression variable is hard to model
        // without support for "with" expressions.
        // The ECMAScript spec (section 12.14), declares that a catch
        // "catch (e) {}" is handled like "with ({'e': e}) {}" so that
        // "var e" would refer to the scope variable, but any following
        // reference would still refer to "e" of the catch expression.
        // Until we have support for this disallow it.
        // Currently the Scope object adds the catch expression to the
        // function scope, which is technically not true but a good
        // approximation for most uses.

        // TODO(johnlenz): Consider improving how scope handles catch
        // expression.

        // Use the name of the var before it was made unique.
        name = MakeDeclaredNamesUnique.ContextualRenameInverter.getOrginalName(
            name);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[222]++;
        compiler.report(
            JSError.make(
                input.getName(), n,
                CATCH_BLOCK_VAR_ERROR, name));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[223]++;

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[114]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[224]++;
int CodeCoverConditionCoverageHelper_C54; if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[115]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[225]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((v.getParentNode().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[117]++;
          s.undeclare(v);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[226]++;
          s.declare(name, n, n.getJSType(), v.input);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[227]++;
          replaceVarWithAssignment(v.getNameNode(), v.getParentNode(),
              v.getParentNode().getParent());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[228]++;

        } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[118]++;}

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[116]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[229]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[119]++;
        Preconditions.checkState(parent.hasOneChild());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[230]++;

        replaceVarWithAssignment(n, parent, parent.getParent());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[231]++;

      } else {
  CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[120]++;}
}
}
    }

    /**
     * Remove the parent VAR. There are three cases that need to be handled:
     *   1) "var a = b;" which is replaced with "a = b"
     *   2) "label:var a;" which is replaced with "label:;". Ideally, the
     *      label itself would be removed but that is not possible in the
     *      context in which "onRedeclaration" is called.
     *   3) "for (var a in b) ..." which is replaced with "for (a in b)..."
     *      Cases we don't need to handle are VARs with multiple children,
     *      which have already been split into separate declarations, so there
     *      is no need to handle that here, and "for (var a;;);", which has
     *      been moved out of the loop.
     *      The result of this is that in each case the parent node is replaced
     *      which is generally dangerous in a traversal but is fine here with
     *      the scope creator, as the next node of interest is the parent's
     *      next sibling.
     */
    private void replaceVarWithAssignment(Node n, Node parent, Node gramps) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[232]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[121]++;
        // The  *  is being initialize, preserve the new value.
        parent.removeChild(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[233]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[234]++;
        // Convert "var name = value" to "name = value"
        Node value = n.getFirstChild();
        n.removeChild(value);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[235]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[236]++;
        Node replacement = IR.assign(n, value);
        replacement.copyInformationFrom(parent);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[237]++;
        gramps.replaceChild(parent, NodeUtil.newExpr(replacement));
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[238]++;

      } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[122]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[239]++;
int CodeCoverConditionCoverageHelper_C58;
        // It is an empty reference remove it.
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[123]++;
          gramps.removeChild(parent);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[240]++;

        } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[124]++;
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[241]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((gramps.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[125]++;
          // This is the "for (var a in b)..." case.  We don't need to worry
          // about initializers in "for (var a;;)..." as those are moved out
          // as part of the other normalizations.
          parent.removeChild(n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[242]++;
          gramps.replaceChild(parent, n);
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[243]++;

        } else {
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.branches[126]++;
          Preconditions.checkState(gramps.isLabel());
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[244]++;
          // We should never get here. LABELs with a single VAR statement should
          // already have been normalized to have a BLOCK.
          throw new IllegalStateException("Unexpected LABEL");
        }
}
      }
      reportCodeChange("Duplicate VAR declaration");
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[245]++;
    }
  }

  /**
   * A simple class that causes scope to be created.
   */
  private final class ScopeTicklingCallback
      implements NodeTraversal.ScopedCallback {
    @Override
    public void enterScope(NodeTraversal t) {
      // Cause the scope to be created, which will cause duplicate
      // to be found.
      t.getScope();
CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5.statements[246]++;
    }

    @Override
    public void exitScope(NodeTraversal t) {
      // Nothing to do.
    }

    @Override
    public boolean shouldTraverse(
        NodeTraversal nodeTraversal, Node n, Node parent) {
      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
      // Nothing to do.
    }
  }
}

class CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5 ());
  }
    public static long[] statements = new long[247];
    public static long[] branches = new long[127];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[60];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.Normalize.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,3,1,1,1,1,1,1,1,2,1,1,1,1,2,1,2,1,1,1,1,1,2,2,1,2,1,2,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,2,2,1,2,2,1,1,1,1,1};
    for (int i = 1; i <= 59; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$3b0c77ipbfcxhgxz64otb5 () {
    super("com.google.javascript.jscomp.Normalize.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 246; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 126; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 59; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Normalize.java");
      for (int i = 1; i <= 246; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 126; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 59; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

