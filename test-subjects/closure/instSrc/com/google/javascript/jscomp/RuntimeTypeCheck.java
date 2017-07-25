/*
 * Copyright 2010 The Closure Compiler Authors.
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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import javax.annotation.Nullable;

/**
 * Inserts run-time type assertions.
 *
 * <p>We add markers to user-defined interfaces and classes in order to check if
 * an object conforms to that type.
 *
 * <p>For each function, we insert a run-time type assertion for each parameter
 * and return value for which the compiler has a type.
 *
 * <p>The JavaScript code which implements the type assertions is in
 * js/runtime-type-check.js.
 *
 */
class RuntimeTypeCheck implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.ping();
  }


  private static final Comparator<JSType> ALPHA = new Comparator<JSType>() {
    @Override
    public int compare(JSType t1, JSType t2) {
      return getName(t1).compareTo(getName(t2));
    }

    private String getName(JSType type) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[1]++;
        return ((ObjectType) type).getReferenceName();

      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[2]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[2]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (512)) == 0 || true) &&
 ((type.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (128)) == 0 || true) &&
 ((type.isBooleanValueType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((type.isNumberValueType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((type.isStringValueType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 5) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 5) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[3]++;
        return type.toString();

      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[4]++;
        // Type unchecked at runtime, so we don't care about the sorting order.
        return "";
      }
}
    }
  };
  static {
    CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[3]++;
  }

  private final AbstractCompiler compiler;
  private final String logFunction;

  RuntimeTypeCheck(AbstractCompiler compiler, @Nullable String logFunction) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[4]++;
    this.logFunction = logFunction;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new AddMarkers(compiler));
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[6]++;
    NodeTraversal.traverse(compiler, root, new AddChecks());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[7]++;
    addBoilerplateCode();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[8]++;
  }

  /**
   * Inserts marker properties for user-defined interfaces and classes.
   *
   * <p>For example, for a class C, we add
   * {@code C.prototype['instance_of__C']}, and for each interface I it
   * implements , we add {@code C.prototype['implements__I']}.
   *
   * <p>Since interfaces are not a run-time JS concept, we use these markers to
   * recognize an interface implementation at runtime. We also use markers for
   * user-defined classes, so that we can easily recognize them independently of
   * which module they are defined in and whether the module is loaded.
   */
  private static class AddMarkers
      extends NodeTraversal.AbstractPostOrderCallback {

    private final AbstractCompiler compiler;

    private AddMarkers(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[9]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[5]++;
        visitFunction(n);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[11]++;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[6]++;}
    }

    private void visitFunction(Node n) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[12]++;
      FunctionType funType = n.getJSType().toMaybeFunctionType();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((funType != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((funType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[7]++;
        return;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[8]++;}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[14]++;

      Node nodeToInsertAfter = findNodeToInsertAfter(n);

      nodeToInsertAfter = addMarker(funType, nodeToInsertAfter, null);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[15]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[16]++;

      TreeSet<ObjectType> stuff = Sets.newTreeSet(ALPHA);
      Iterables.addAll(stuff, funType.getAllImplementedInterfaces());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[17]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[1]++;


      for (ObjectType interfaceType : stuff) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[1]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[2]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[3]++;
}
        nodeToInsertAfter =
            addMarker(funType, nodeToInsertAfter, interfaceType);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[19]++;
      }
    }

    private Node addMarker(
            FunctionType funType,
            Node nodeToInsertAfter,
            @Nullable ObjectType interfaceType) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((funType.getSource() == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[9]++;
        return nodeToInsertAfter;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[10]++;}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[21]++;

      String className = NodeUtil.getFunctionName(funType.getSource());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;

      // This can happen with anonymous classes declared with the type
      // {@code Function}.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((className == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[11]++;
        return nodeToInsertAfter;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[12]++;}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[23]++;

      Node classNode = NodeUtil.newQualifiedNameNode(
          compiler.getCodingConvention(), className);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[24]++;

      Node marker = IR.string(
              interfaceType == null ?
              "instance_of__" + className :
              "implements__" + interfaceType.getReferenceName());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[25]++;

      Node assign = IR.exprResult(IR.assign(
          IR.getelem(
              IR.getprop(
                  classNode,
                  IR.string("prototype")), marker),
          IR.trueNode()));

      nodeToInsertAfter.getParent().addChildAfter(assign, nodeToInsertAfter);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[26]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[27]++;
      nodeToInsertAfter = assign;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[28]++;
      return nodeToInsertAfter;
    }

    /**
     * Find the node to insert the markers after. Typically, this node
     * corresponds to the constructor declaration, but we want to skip any of
     * the white-listed function calls.
     *
     * @param n the constructor function node
     * @return the node to insert after
     */
    private Node findNodeToInsertAfter(Node n) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[29]++;
      Node nodeToInsertAfter = findEnclosingConstructorDeclaration(n);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[30]++;

      Node next = nodeToInsertAfter.getNext();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[31]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
      while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isClassDefiningCall(next)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[4]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[5]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[6]++;
}
        nodeToInsertAfter = next;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[32]++;
        next = nodeToInsertAfter.getNext();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[33]++;
      }

      return nodeToInsertAfter;
    }

    private Node findEnclosingConstructorDeclaration(Node n) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
      while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((n.getParent().isScript()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n.getParent().isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[7]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[8]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[9]++;
}
        n = n.getParent();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[35]++;
      }
      return n;
    }

    private boolean isClassDefiningCall(Node next) {
      return NodeUtil.isExprCall(next) &&
          compiler.getCodingConvention().getClassesDefinedByCall(
              next.getFirstChild()) != null;
    }
  }

  /**
   * Insert calls to the run-time type checking function {@code checkType}, which
   * takes an expression to check and a list of checkers (one of which must
   * match). It returns the expression back to facilitate checking of return
   * values. We have checkers for value types, class types (user-defined and
   * externed), and interface types.
   */
  private class AddChecks
      extends NodeTraversal.AbstractPostOrderCallback {

    private AddChecks() {
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[13]++;
        visitFunction(n);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[37]++;

      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[14]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[38]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[15]++;
        visitReturn(t, n);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[39]++;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[16]++;}
}
    }

    /**
     * Insert checks for the parameters of the function.
     */
    private void visitFunction(Node n) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[40]++;
      FunctionType funType = JSType.toMaybeFunctionType(n.getJSType());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[41]++;
      Node block = n.getLastChild();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[42]++;
      Node paramName = NodeUtil.getFunctionParameters(n).getFirstChild();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[43]++;
      Node insertionPoint = null;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[44]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[10]++;


int CodeCoverConditionCoverageHelper_C11;

      // To satisfy normalization constraints, the type checking must be
      // added after any inner function declarations.
      for (Node next = block.getFirstChild();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(next)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false);
           next = next.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[10]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[11]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[12]++;
}
        insertionPoint = next;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[45]++;
      }
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[46]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[13]++;



      for (Node paramType : funType.getParameters()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[13]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[14]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[15]++;
}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[47]++;
int CodeCoverConditionCoverageHelper_C12;
        // Can this ever happen?
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((paramName == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[17]++;
          return;

        } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[18]++;}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[48]++;

        Node checkNode = createCheckTypeCallNode(
            paramType.getJSType(), paramName.cloneTree());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((checkNode == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[19]++;
          // We don't know how to check this parameter type.
          paramName = paramName.getNext();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[50]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[51]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[20]++;}

        checkNode = IR.exprResult(checkNode);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[52]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((insertionPoint == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[21]++;
          block.addChildToFront(checkNode);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[54]++;

        } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[22]++;
          block.addChildAfter(checkNode, insertionPoint);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[55]++;
        }

        compiler.reportCodeChange();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[56]++;
        paramName = paramName.getNext();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[57]++;
        insertionPoint = checkNode;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[58]++;
      }
    }

    private void visitReturn(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[59]++;
      Node function = t.getEnclosingFunction();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[60]++;
      FunctionType funType = function.getJSType().toMaybeFunctionType();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[61]++;

      Node retValue = n.getFirstChild();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((retValue == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[23]++;
        return;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[24]++;}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[63]++;

      Node checkNode = createCheckTypeCallNode(
          funType.getReturnType(), retValue.cloneTree());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((checkNode == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[25]++;
        return;

      } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[26]++;}

      n.replaceChild(retValue, checkNode);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[65]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[66]++;
    }

    /**
     * Creates a function call to check that the given expression matches the
     * given type at runtime.
     *
     * <p>For example, if the type is {@code (string|Foo)}, the function call is
     * {@code checkType(expr, [valueChecker('string'), classChecker('Foo')])}.
     *
     * @return the function call node or {@code null} if the type is not checked
     */
    private Node createCheckTypeCallNode(JSType type, Node expr) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[67]++;
      Node arrayNode = IR.arraylit();
      Collection<JSType> alternates;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[68]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[27]++;
        alternates = Sets.newTreeSet(ALPHA);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[69]++;
        Iterables.addAll(alternates, type.toMaybeUnionType().getAlternates());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[70]++;

      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[28]++;
        alternates = ImmutableList.of(type);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[71]++;
      }
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[72]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[16]++;


      for (JSType alternate : alternates) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[16]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[17]--;
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.loops[18]++;
}
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[73]++;
        Node checkerNode = createCheckerNode(alternate);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((checkerNode == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[29]++;
          return null;

        } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[30]++;}
        arrayNode.addChildToBack(checkerNode);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[75]++;
      }
      return IR.call(jsCode("checkType"), expr, arrayNode);
    }

    /**
     * Creates a node which evaluates to a checker for the given type (which
     * must not be a union). We have checkers for value types, classes and
     * interfaces.
     *
     * @return the checker node or {@code null} if the type is not checked
     */
    private Node createCheckerNode(JSType type) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[76]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((type.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[31]++;
        return jsCode("nullChecker");


      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[32]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[77]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (128)) == 0 || true) &&
 ((type.isBooleanValueType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((type.isNumberValueType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((type.isStringValueType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((type.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[33]++;
        return IR.call(
            jsCode("valueChecker"),
            IR.string(type.toString()));


      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[34]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[78]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((type.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[35]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[79]++;
        ObjectType objType = (ObjectType) type;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[80]++;

        String refName = objType.getReferenceName();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[81]++;

        StaticSourceFile sourceFile =
            NodeUtil.getSourceFile(objType.getConstructor().getSource());
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[82]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((sourceFile == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((sourceFile.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[37]++;
          return IR.call(
                  jsCode("externClassChecker"),
                  IR.string(refName));

        } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[38]++;}

        return IR.call(
                jsCode(objType.getConstructor().isInterface() ?
                        "interfaceChecker" : "classChecker"),
                IR.string(refName));


      } else {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[36]++;
        // We don't check this type (e.g. unknown & all types).
        return null;
      }
}
}
    }
  }

  private void addBoilerplateCode() {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[83]++;
    Node newNode = compiler.ensureLibraryInjected("runtime_type_check");
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[84]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((newNode != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((logFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[39]++;
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[85]++;
      // Inject the custom log function.
      Node logOverride = IR.exprResult(
          IR.assign(
              NodeUtil.newQualifiedNameNode(
                  compiler.getCodingConvention(),
                  "$jscomp.typecheck.log"),
              NodeUtil.newQualifiedNameNode(
                  compiler.getCodingConvention(),
                  logFunction)));
      newNode.getParent().addChildAfter(logOverride, newNode);
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[86]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.statements[87]++;

    } else {
  CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x.branches[40]++;}
  }

  private Node jsCode(String prop) {
    return NodeUtil.newQualifiedNameNode(
        compiler.getCodingConvention(), "$jscomp.typecheck." + prop);
  }
}

class CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x ());
  }
    public static long[] statements = new long[88];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[24];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RuntimeTypeCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,1,2,1,1,2,2,1,1,2,1,1,1,1,1,1,1,1,3,1,2,2};
    for (int i = 1; i <= 23; i++) {
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

  public CodeCoverCoverageCounter$1whtex6o0qkwc2sh7dtgcapbqcepvz80x () {
    super("com.google.javascript.jscomp.RuntimeTypeCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 87; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 23; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RuntimeTypeCheck.java");
      for (int i = 1; i <= 87; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 40; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 23; i++) {
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

