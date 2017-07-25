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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.EnumType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.ObjectType;

import javax.annotation.Nullable;

/**
 * Set the JSDocInfo on all types.
 *
 * Propagates JSDoc across the type graph, but not across the symbol graph.
 * This means that if you have:
 * <code>
 * var x = new Foo();
 * x.bar;
 * </code>
 * then the JSType attached to x.bar may get associated JSDoc, but the
 * Node and Var will not.
 *
 * JSDoc is initially attached to AST Nodes at parse time.
 * There are 3 ways that JSDoc get propagated across the type system.
 * 1) Nominal types (e.g., constructors) may contain JSDocInfo for their
 *    declaration.
 * 2) Object types have a JSDocInfo slot for each property on that type.
 * 3) Shape types (like structural functions) may have JSDocInfo.
 *
 * #1 and #2 should be self-explanatory, and non-controversial. #3 is
 * a bit trickier. It means that if you have:
 * <code>
 * /** @param {number} x /
 * Foo.prototype.bar = goog.abstractMethod;
 * </code>
 * the JSDocInfo will appear in two places in the type system: in the 'bar'
 * slot of Foo.prototype, and on the function expression type created by
 * this expression.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class InferJSDocInfo extends AbstractPostOrderCallback
    implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.ping();
  }


  private final AbstractCompiler compiler;
  @SuppressWarnings("unused")
  private boolean inExterns;

  InferJSDocInfo(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[1]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((externs != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[1]++;
      inExterns = true;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[3]++;
      NodeTraversal.traverse(compiler, externs, this);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[4]++;

    } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[2]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[3]++;
      inExterns = false;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[6]++;
      NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[7]++;

    } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[4]++;}
  }

  @Override
  public void hotSwapScript(Node root, Node originalRoot) {
    Preconditions.checkNotNull(root);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[8]++;
    Preconditions.checkState(root.isScript());
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[9]++;
    inExterns = false;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[10]++;
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[11]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
    JSDocInfo docInfo;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[12]++;

    switch (n.getType()) {
      // Infer JSDocInfo on types of all type declarations on variables.
      case Token.NAME:
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[5]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[6]++;
          return;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[7]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;

        // Only allow JSDoc on VARs, function declarations, and assigns.
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[8]++;
          return;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[9]++;}

        // There are four places the doc info could live.
        // 1) A FUNCTION node.
        // /** ... */ function f() { ... }
        // 2) An ASSIGN parent.
        // /** ... */ x = function () { ... }
        // 3) A NAME parent.
        // var x, /** ... */ y = function() { ... }
        // 4) A VAR gramps.
        // /** ... */ var x = function() { ... }
        docInfo = n.getJSDocInfo();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[15]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((docInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((parent.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[10]++;
          docInfo = parent.getJSDocInfo();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[17]++;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[11]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[18]++;

        // Try to find the type of the NAME.
        JSType varType = n.getJSType();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((varType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[12]++;
          varType = parent.getJSType();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[20]++;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[13]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;

        // If we have no type to attach JSDocInfo to, then there's nothing
        // we can do.
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((varType == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((docInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[14]++;
          return;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[15]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[22]++;

        // Dereference the type. If the result is not an object, or already
        // has docs attached, then do nothing.
        ObjectType objType = dereferenceToObject(varType);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((objType == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((objType.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[16]++;
          return;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[17]++;}

        attachJSDocInfoToNominalTypeOrShape(objType, docInfo, n.getString());
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[24]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[25]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[18]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
        // Infer JSDocInfo on properties.
        // There are two ways to write doc comments on a property.
        //
        // 1)
        // /** @deprecated */
        // obj.prop = ...
        //
        // 2)
        // /** @deprecated */
        // obj.prop;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[19]++;
          docInfo = n.getJSDocInfo();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[27]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[28]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((docInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[21]++;
            docInfo = parent.getJSDocInfo();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[29]++;

          } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[22]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[23]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[31]++;
            ObjectType lhsType =
                dereferenceToObject(n.getFirstChild().getJSType());
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lhsType != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[25]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[33]++;
              // Put the JSDoc in the property slot, if there is one.
              String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[34]++;
int CodeCoverConditionCoverageHelper_C13;
              if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((lhsType.hasOwnProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[27]++;
                lhsType.setPropertyJSDocInfo(propName, docInfo);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[35]++;

              } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[28]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[36]++;

              // Put the JSDoc in any constructors or function shapes as well.
              ObjectType propType =
                  dereferenceToObject(lhsType.getPropertyType(propName));
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[37]++;
int CodeCoverConditionCoverageHelper_C14;
              if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((propType != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[29]++;
                attachJSDocInfoToNominalTypeOrShape(
                    propType, docInfo, n.getQualifiedName());
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[38]++;

              } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[26]++;}

          } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[20]++;}
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[39]++;
        break; default : CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[31]++;
    }
  }

  /**
   * Dereferences the given type to an object, or returns null.
   */
  private ObjectType dereferenceToObject(JSType type) {
    return ObjectType.cast(type == null ? null : type.dereference());
  }

  /**
   * Handle cases #1 and #3 in the class doc.
   */
  private void attachJSDocInfoToNominalTypeOrShape(
      ObjectType objType, JSDocInfo docInfo, @Nullable String qName) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((objType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((objType.isEnumType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((objType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[32]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[41]++;
int CodeCoverConditionCoverageHelper_C16;
      // Named types.
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((objType.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((objType.getReferenceName().equals(qName)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[34]++;
        objType.setJSDocInfo(docInfo);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[42]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[43]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((objType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((objType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[36]++;
          JSType.toMaybeFunctionType(objType).getInstanceType().setJSDocInfo(
              docInfo);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[44]++;

        } else {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[37]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[45]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((objType instanceof EnumType) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[38]++;
          ((EnumType) objType).getElementsType().setJSDocInfo(docInfo);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[46]++;

        } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[39]++;}
}

      } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[35]++;}

    } else {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[33]++;
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[47]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((objType.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((objType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[40]++;
      // Structural functions.
      objType.setJSDocInfo(docInfo);
CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.statements[48]++;

    } else {
  CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd.branches[41]++;}
}
  }
}

class CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd ());
  }
    public static long[] statements = new long[49];
    public static long[] branches = new long[42];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InferJSDocInfo.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,3,2,2,2,3,1,1,1,1,1,3,2,2,1,2};
    for (int i = 1; i <= 19; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$17f9k8kz1q8lrvz7gjeoux75yecdpd () {
    super("com.google.javascript.jscomp.InferJSDocInfo.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 48; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 41; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InferJSDocInfo.java");
      for (int i = 1; i <= 48; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 41; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

