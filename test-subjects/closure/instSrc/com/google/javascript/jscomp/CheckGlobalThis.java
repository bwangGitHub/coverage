/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Checks for certain uses of the {@code this} keyword that are considered
 * unsafe because they are likely to reference the global {@code this} object
 * unintentionally.
 *
 * <p>A use of {@code this} is considered unsafe if it's on the left side of an
 * assignment or a property access, and not inside one of the following:
 * <ol>
 * <li>a prototype method
 * <li>a function annotated with {@code @constructor}
 * <li>a function annotated with {@code @this}.
 * <li>a function where there's no logical place to put a
 *     {@code this} annotation.
 * </ol>
 *
 * <p>Note that this check does not track assignments of {@code this} to
 * variables or objects. The code
 * <pre>
 * function evil() {
 *   var a = this;
 *   a.useful = undefined;
 * }
 * </pre>
 * will not get flagged, even though it is semantically equivalent to
 * <pre>
 * function evil() {
 *   this.useful = undefined;
 * }
 * </pre>
 * which would get flagged.
 *
 */
final class CheckGlobalThis implements Callback {
  static {
    CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.ping();
  }


  static final DiagnosticType GLOBAL_THIS = DiagnosticType.warning(
      "JSC_USED_GLOBAL_THIS",
      "dangerous use of the global 'this' object");
  static {
    CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[1]++;
  }

  private final AbstractCompiler compiler;

  /**
   * If {@code assignLhsChild != null}, then the node being traversed is
   * a descendant of the first child of an ASSIGN node. assignLhsChild's
   * parent is this ASSIGN node.
   */
  private Node assignLhsChild = null;
  {
    CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[2]++;
  }

  CheckGlobalThis(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[3]++;
  }

  /**
   * Since this pass reports errors only when a global {@code this} keyword
   * is encountered, there is no reason to traverse non global contexts.
   */
  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[1]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[5]++;
      // Don't traverse functions that are constructors or have the @this
      // or @override annotation.
      JSDocInfo jsDoc = getFunctionJsDocInfo(n);
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (512)) == 0 || true) &&
 ((jsDoc != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (256)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C2 |= (128)) == 0 || true) &&
 ((jsDoc.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((jsDoc.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((jsDoc.hasThisType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((jsDoc.isOverride()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 5) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 5) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[3]++;
        return false;

      } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[4]++;}
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[7]++;

      // Don't traverse functions unless they would normally
      // be able to have a @this annotation associated with them. e.g.,
      // var a = function() { }; // or
      // function a() {} // or
      // a.x = function() {}; // or
      // var a = {x: function() {}};
      int pType = parent.getType();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (512)) == 0 || true) &&
 ((pType == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (128)) == 0 || true) &&
 ((pType == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((pType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((pType == Token.ASSIGN) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((// object literal keys
            pType == Token.STRING_KEY) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 5) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 5) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[5]++;
        return false;

      } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[6]++;}
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[9]++;

      // Don't traverse functions that are getting lent to a prototype.
      Node gramps = parent.getParent();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[7]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[11]++;
        JSDocInfo maybeLends = gramps.getJSDocInfo();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((maybeLends != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((maybeLends.getLendsName() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((maybeLends.getLendsName().endsWith(".prototype")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[9]++;
          return false;

        } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[10]++;}

      } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[2]++;}
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[11]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[14]++;
      Node lhs = parent.getFirstChild();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n == lhs) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[13]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
        // Always traverse the left side of the assignment. To handle
        // nested assignments properly (e.g., (a = this).property = c;),
        // assignLhsChild should not be overridden.
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((assignLhsChild == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[15]++;
          assignLhsChild = lhs;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[17]++;

        } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[16]++;}

      } else {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[14]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[18]++;
int CodeCoverConditionCoverageHelper_C9;
        // Only traverse the right side if it's not an assignment to a prototype
        // property or subproperty.
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(lhs)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[17]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[19]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((lhs.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[19]++;
            return false;

          } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[20]++;}
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[20]++;
          Node llhs = lhs.getFirstChild();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[21]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((llhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((llhs.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[21]++;
            return false;

          } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[22]++;}

        } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[18]++;}
      }

    } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[12]++;}

    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[22]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((n.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((shouldReportThis(n)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[23]++;
      compiler.report(t.makeError(n, GLOBAL_THIS));
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[23]++;

    } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[24]++;}
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[24]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n == assignLhsChild) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[25]++;
      assignLhsChild = null;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[25]++;

    } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[26]++;}
  }

  private boolean shouldReportThis(Node n) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[26]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[27]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((assignLhsChild != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[27]++;
      // Always report a THIS on the left side of an assign.
      return true;

    } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[28]++;}

    // Also report a THIS with a property access.
    return parent != null && NodeUtil.isGet(parent);
  }

  /**
   * Gets a function's JSDoc information, if it has any. Checks for a few
   * patterns (ellipses show where JSDoc would be):
   * <pre>
   * ... function() {}
   * ... x = function() {};
   * var ... x = function() {};
   * ... var x = function() {};
   * </pre>
   */
  private JSDocInfo getFunctionJsDocInfo(Node n) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[28]++;
    JSDocInfo jsDoc = n.getJSDocInfo();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[29]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[30]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((jsDoc == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[29]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[31]++;
      int parentType = parent.getType();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[32]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((parentType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((parentType == Token.ASSIGN) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[31]++;
        jsDoc = parent.getJSDocInfo();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[33]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[34]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((jsDoc == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((parentType == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[33]++;
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[35]++;
          Node gramps = parent.getParent();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[36]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((gramps.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[35]++;
            jsDoc = gramps.getJSDocInfo();
CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.statements[37]++;

          } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[34]++;}

      } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[32]++;}

    } else {
  CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt.branches[30]++;}
    return jsDoc;
  }
}

class CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt ());
  }
    public static long[] statements = new long[38];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckGlobalThis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,3,1,3,2,1,1,1,2,2,2,1,1,1,2,2,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$7vgoj7bisgk1vp4liq30eyzlhh6hjdt () {
    super("com.google.javascript.jscomp.CheckGlobalThis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 37; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckGlobalThis.java");
      for (int i = 1; i <= 37; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
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

