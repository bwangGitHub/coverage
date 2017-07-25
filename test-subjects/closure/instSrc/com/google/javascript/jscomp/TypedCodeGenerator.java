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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.Set;

/**
 * A code generator that outputs type annotations for functions and
 * constructors.
 */
class TypedCodeGenerator extends CodeGenerator {
  static {
    CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.ping();
  }

  private final JSTypeRegistry registry;
  TypedCodeGenerator(
      CodeConsumer consumer, CompilerOptions options, JSTypeRegistry registry) {
    super(consumer, options);
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[1]++;
    Preconditions.checkNotNull(registry);
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[2]++;
    this.registry = registry;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[3]++;
  }

  @Override
  void add(Node n, Context context) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[4]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((parent.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[1]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[3]++;
        add(getFunctionAnnotation(n));
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[7]++;

      } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[4]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[5]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[9]++;
        Node rhs = n.getFirstChild().getLastChild();
        add(getTypeAnnotation(rhs));
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[10]++;

      } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[6]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[7]++;
        add(getTypeAnnotation(n.getFirstChild().getFirstChild()));
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[12]++;

      } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[8]++;}
}
}

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[2]++;}

    super.add(n, context);
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[13]++;
  }

  private String getTypeAnnotation(Node node) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[14]++;
    // Only add annotations for things with JSDoc, or function literals.
    JSDocInfo jsdoc = NodeUtil.getBestJSDocInfo(node);
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((jsdoc == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[9]++;
      return "";

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[10]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[16]++;

    JSType type = node.getJSType();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[11]++;
      return "";

    } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[12]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[18]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[13]++;
      return getFunctionAnnotation(node);

    } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[14]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[19]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type.isEnumType()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[15]++;
      return "/** @enum {" +
          type.toMaybeEnumType().getElementsType().toAnnotationString() +
          "} */\n";

    } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[16]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[20]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((type.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((type.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[17]++;
      return "/** @type {" + node.getJSType().toAnnotationString() + "} */\n";

    } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[18]++;
      return "";
    }
}
}
}
  }

  /**
   * @param fnNode A node for a function for which to generate a type annotation
   */
  private String getFunctionAnnotation(Node fnNode) {
    Preconditions.checkState(fnNode.isFunction());
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[21]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[22]++;
    JSType type = fnNode.getJSType();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[19]++;
      return "";

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[20]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[24]++;

    FunctionType funType = type.toMaybeFunctionType();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((JSType.isEquivalent(
        type, registry.getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[21]++;
      return "/** @type {!Function} */\n";

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[22]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[26]++;

    StringBuilder sb = new StringBuilder("/**\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[27]++;
int CodeCoverConditionCoverageHelper_C12;


    // We need to use the child nodes of the function as the nodes for the
    // parameters of the function type do not have the real parameter names.
    // FUNCTION
    //   NAME
    //   LP
    //     NAME param1
    //     NAME param2
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((fnNode != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[23]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[28]++;
      Node paramNode = NodeUtil.getFunctionParameters(fnNode).getFirstChild();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[1]++;



      // Param types
      for (Node n : funType.getParameters()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[1]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[2]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[3]++;
}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[30]++;
int CodeCoverConditionCoverageHelper_C13;
        // Bail out if the paramNode is not there.
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((paramNode == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[25]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[31]++;
          break;

        } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[26]++;}
        sb.append(" * ");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[32]++;
        appendAnnotation(sb, "param", getParameterNodeJSDocType(n));
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[33]++;
        sb.append(" ")
            .append(paramNode.getString())
            .append("\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[34]++;
        paramNode = paramNode.getNext();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[35]++;
      }

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[24]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[36]++;

    // Return type
    JSType retType = funType.getReturnType();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[37]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((retType != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((retType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((funType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[27]++; // Interfaces never return a value.
      sb.append(" * ");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[38]++;
      appendAnnotation(sb, "return", retType.toAnnotationString());
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[39]++;
      sb.append("\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[40]++;

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[28]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[41]++;
int CodeCoverConditionCoverageHelper_C15;

    // Constructor/interface
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((funType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((funType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[29]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[42]++;

      FunctionType superConstructor = funType.getSuperClassConstructor();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[43]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((superConstructor != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[31]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[44]++;
        ObjectType superInstance =
          funType.getSuperClassConstructor().getInstanceType();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((superInstance.toString().equals("Object")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[33]++;
          sb.append(" * ");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[46]++;
          appendAnnotation(sb, "extends", superInstance.toAnnotationString());
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[47]++;
          sb.append("\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[48]++;

        } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[34]++;}

      } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[32]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[49]++;
int CodeCoverConditionCoverageHelper_C18;

      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((funType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[35]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[50]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[4]++;


        for (ObjectType interfaceType : funType.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[4]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[5]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[6]++;
}
          sb.append(" * ");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[51]++;
          appendAnnotation(sb, "extends", interfaceType.toAnnotationString());
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[52]++;
          sb.append("\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[53]++;
        }

      } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[36]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[54]++;

      // Avoid duplicates, add implemented type to a set first
      Set<String> interfaces = Sets.newTreeSet();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[55]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[7]++;


      for (ObjectType interfaze : funType.getImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[7]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[8]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[9]++;
}
        interfaces.add(interfaze.toAnnotationString());
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[56]++;
      }
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[57]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[10]++;


      for (String interfaze : interfaces) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[10]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[11]--;
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.loops[12]++;
}
        sb.append(" * ");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[58]++;
        appendAnnotation(sb, "implements", interfaze);
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[59]++;
        sb.append("\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[60]++;
      }
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[61]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((funType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[37]++;
        sb.append(" * @constructor\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[62]++;

      } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[38]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[63]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((funType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[39]++;
        sb.append(" * @interface\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[64]++;

      } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[40]++;}
}

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[30]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[65]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((funType.getTemplateTypeMap().getTemplateKeys().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[41]++;
      sb.append(" * @template ");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[66]++;
      sb.append(Joiner.on(",").join(
          funType.getTemplateTypeMap().getTemplateKeys()));
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[67]++;
      sb.append("\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[68]++;

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[42]++;}
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[69]++;
int CodeCoverConditionCoverageHelper_C22;

    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((fnNode != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fnNode.getBooleanProp(Node.IS_DISPATCHER)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[43]++;
      sb.append(" * @javadispatch\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[70]++;

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[44]++;}

    sb.append(" */\n");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[71]++;
    return sb.toString();
  }

  private void appendAnnotation(StringBuilder sb, String name, String type) {
    sb.append("@").append(name).append(" {").append(type).append("}");
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[72]++;
  }

  /**
   * Creates a JSDoc-suitable String representation the type of a parameter.
   *
   * @param parameterNode The parameter node.
   */
  private String getParameterNodeJSDocType(Node parameterNode) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[73]++;
    JSType parameterType = parameterNode.getJSType();
    String typeString;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[74]++;
int CodeCoverConditionCoverageHelper_C23;

    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((parameterNode.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[45]++;
      typeString = restrictByUndefined(parameterType).toAnnotationString() +
          "=";
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[75]++;

    } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[46]++;
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[76]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parameterNode.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[47]++;
      typeString = "..." +
          restrictByUndefined(parameterType).toAnnotationString();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[77]++;

    } else {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[48]++;
      typeString = parameterType.toAnnotationString();
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[78]++;
    }
}

    return typeString;
  }

  private JSType restrictByUndefined(JSType type) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.statements[79]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[49]++;
      return type.toMaybeUnionType().getRestrictedUnion(
          registry.getNativeType(JSTypeNative.VOID_TYPE));

    } else {
  CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p.branches[50]++;}
    return type;
  }
}

class CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p ());
  }
    public static long[] statements = new long[80];
    public static long[] branches = new long[51];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TypedCodeGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,2,2,2,1,1,1,3,2,1,1,1,3,2,1,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$2qkb72sz2nc5y1jjt1b0zd5s25zxfmod9j6p () {
    super("com.google.javascript.jscomp.TypedCodeGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 79; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 50; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TypedCodeGenerator.java");
      for (int i = 1; i <= 79; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 50; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

