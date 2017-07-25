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

package com.google.javascript.jscomp.type;

import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;

import com.google.common.base.Function;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSType.TypePair;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.UnionType;
import com.google.javascript.rhino.jstype.Visitor;

/**
 * A reverse abstract interpreter using the semantics of the JavaScript
 * language as a means to reverse interpret computations. This interpreter
 * expects the parse tree inputs to be typed.
 *
 */
public class SemanticReverseAbstractInterpreter
    extends ChainableReverseAbstractInterpreter {
  static {
    CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.ping();
  }


  /**
   * Merging function for equality between types.
   */
  private static final Function<TypePair, TypePair> EQ =
    new Function<TypePair, TypePair>() {
      @Override
      public TypePair apply(TypePair p) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((p.typeA == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p.typeB == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[1]++;
          return null;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[2]++;}
        return p.typeA.getTypesUnderEquality(p.typeB);
      }
    };
  static {
    CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[2]++;
  }

  /**
   * Merging function for non-equality between types.
   */
  private static final Function<TypePair, TypePair> NE =
    new Function<TypePair, TypePair>() {
      @Override
      public TypePair apply(TypePair p) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((p.typeA == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p.typeB == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[3]++;
          return null;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[4]++;}
        return p.typeA.getTypesUnderInequality(p.typeB);
      }
    };
  static {
    CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[4]++;
  }

  /**
   * Merging function for strict equality between types.
   */
  private static final
      Function<TypePair, TypePair> SHEQ =
    new Function<TypePair, TypePair>() {
      @Override
      public TypePair apply(TypePair p) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((p.typeA == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((p.typeB == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[5]++;
          return null;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[6]++;}
        return p.typeA.getTypesUnderShallowEquality(p.typeB);
      }
    };
  static {
    CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[6]++;
  }

  /**
   * Merging function for strict non-equality between types.
   */
  private static final
      Function<TypePair, TypePair> SHNE =
    new Function<TypePair, TypePair>() {
      @Override
      public TypePair apply(TypePair p) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((p.typeA == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((p.typeB == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[7]++;
          return null;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[8]++;}
        return p.typeA.getTypesUnderShallowInequality(p.typeB);
      }
    };
  static {
    CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[8]++;
  }

  /**
   * Merging function for inequality comparisons between types.
   */
  private final
      Function<TypePair, TypePair> INEQ =
    new Function<TypePair, TypePair>() {
      @Override
      public TypePair apply(TypePair p) {
        return new TypePair(
            getRestrictedWithoutUndefined(p.typeA),
            getRestrictedWithoutUndefined(p.typeB));
      }
    };
  {
    CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[9]++;
  }

  /**
   * Creates a semantic reverse abstract interpreter.
   */
  public SemanticReverseAbstractInterpreter(CodingConvention convention,
      JSTypeRegistry typeRegistry) {
    super(convention, typeRegistry);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[10]++;
  }

  @Override
  public FlowScope getPreciserScopeKnowingConditionOutcome(Node condition,
      FlowScope blindScope, boolean outcome) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[11]++;
    // Check for the typeof operator.
    int operatorToken = condition.getType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[12]++;
    switch (operatorToken) {
      case Token.EQ:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[9]++;
      case Token.NE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[10]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[11]++;
      case Token.SHNE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[12]++;
      case Token.CASE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[13]++;
        Node left;
        Node right;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((operatorToken == Token.CASE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[14]++;
          left = condition.getParent().getFirstChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[14]++; // the switch condition
          right = condition.getFirstChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[15]++;

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[15]++;
          left = condition.getFirstChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[16]++;
          right = condition.getLastChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[17]++;
        }
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[18]++;

        Node typeOfNode = null;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[19]++;
        Node stringNode = null;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((left.isTypeOf()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[16]++;
          typeOfNode = left;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[21]++;
          stringNode = right;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[22]++;

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[17]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[23]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((right.isTypeOf()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((left.isString()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[18]++;
          typeOfNode = right;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[24]++;
          stringNode = left;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[25]++;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[19]++;}
}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((typeOfNode != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((stringNode != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[20]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[27]++;
          Node operandNode = typeOfNode.getFirstChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[28]++;
          JSType operandType = getTypeIfRefinable(operandNode, blindScope);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[29]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((operandType != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[22]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[30]++;
            boolean resultEqualsValue = operatorToken == Token.EQ ||
                operatorToken == Token.SHEQ || operatorToken == Token.CASE;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[24]++;
              resultEqualsValue = !resultEqualsValue;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[32]++;

            } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[25]++;}
            return caseTypeOf(operandNode, operandType, stringNode.getString(),
                resultEqualsValue, blindScope);

          } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[23]++;}

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[21]++;} default : CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[26]++;
    }
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[33]++;
    switch (operatorToken) {
      case Token.AND:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[27]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[34]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[28]++;
          return caseAndOrNotShortCircuiting(condition.getFirstChild(),
              condition.getLastChild(), blindScope, true);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[29]++;
          return caseAndOrMaybeShortCircuiting(condition.getFirstChild(),
              condition.getLastChild(), blindScope, true);
        }

      case Token.OR:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[30]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[35]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[31]++;
          return caseAndOrNotShortCircuiting(condition.getFirstChild(),
              condition.getLastChild(), blindScope, false);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[32]++;
          return caseAndOrMaybeShortCircuiting(condition.getFirstChild(),
              condition.getLastChild(), blindScope, false);
        }

      case Token.EQ:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[33]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[34]++;
          return caseEquality(condition, blindScope, EQ);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[35]++;
          return caseEquality(condition, blindScope, NE);
        }

      case Token.NE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[36]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[37]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[37]++;
          return caseEquality(condition, blindScope, NE);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[38]++;
          return caseEquality(condition, blindScope, EQ);
        }

      case Token.SHEQ:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[39]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[40]++;
          return caseEquality(condition, blindScope, SHEQ);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[41]++;
          return caseEquality(condition, blindScope, SHNE);
        }

      case Token.SHNE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[42]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[39]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[43]++;
          return caseEquality(condition, blindScope, SHNE);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[44]++;
          return caseEquality(condition, blindScope, SHEQ);
        }

      case Token.NAME:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[45]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[46]++;
        return caseNameOrGetProp(condition, blindScope, outcome);

      case Token.ASSIGN:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[47]++;
        return firstPreciserScopeKnowingConditionOutcome(
            condition.getFirstChild(),
            firstPreciserScopeKnowingConditionOutcome(
                condition.getFirstChild().getNext(), blindScope, outcome),
            outcome);

      case Token.NOT:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[48]++;
        return firstPreciserScopeKnowingConditionOutcome(
            condition.getFirstChild(), blindScope, !outcome);

      case Token.LE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[49]++;
      case Token.LT:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[50]++;
      case Token.GE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[51]++;
      case Token.GT:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[52]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[40]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[53]++;
          return caseEquality(condition, blindScope, INEQ);

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[54]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[41]++;
        break;

      case Token.INSTANCEOF:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[55]++;
        return caseInstanceOf(
            condition.getFirstChild(), condition.getLastChild(), blindScope,
            outcome);

      case Token.IN:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[56]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[42]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((condition.getFirstChild().isString()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[57]++;
          return caseIn(condition.getLastChild(),
              condition.getFirstChild().getString(), blindScope);

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[58]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[43]++;
        break;

      case Token.CASE:
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[59]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[44]++;
        Node left =
            condition.getParent().getFirstChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[45]++; // the switch condition
        Node right = condition.getFirstChild();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[46]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[60]++;
          return caseEquality(left, right, blindScope, SHEQ);

        } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[61]++;
          return caseEquality(left, right, blindScope, SHNE);
        } default : CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[62]++;
    }
    return nextPreciserScopeKnowingConditionOutcome(
        condition, blindScope, outcome);
  }

  private FlowScope caseEquality(Node condition, FlowScope blindScope,
      Function<TypePair, TypePair> merging) {
    return caseEquality(condition.getFirstChild(), condition.getLastChild(),
                        blindScope, merging);
  }

  private FlowScope caseEquality(Node left, Node right, FlowScope blindScope,
      Function<TypePair, TypePair> merging) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[47]++;
    // left type
    JSType leftType = getTypeIfRefinable(left, blindScope);
    boolean leftIsRefineable;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[48]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((leftType != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[63]++;
      leftIsRefineable = true;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[49]++;

    } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[64]++;
      leftIsRefineable = false;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[50]++;
      leftType = left.getJSType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[51]++;
    }
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[52]++;

    // right type
    JSType rightType = getTypeIfRefinable(right, blindScope);
    boolean rightIsRefineable;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[53]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((rightType != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[65]++;
      rightIsRefineable = true;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[54]++;

    } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[66]++;
      rightIsRefineable = false;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[55]++;
      rightType = right.getJSType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[56]++;
    }
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[57]++;

    // merged types
    TypePair merged = merging.apply(new TypePair(leftType, rightType));
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[58]++;
int CodeCoverConditionCoverageHelper_C22;

    // creating new scope
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((merged != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[67]++;
      return maybeRestrictTwoNames(
          blindScope,
          left, leftType, leftIsRefineable ? merged.typeA : null,
          right, rightType, rightIsRefineable ? merged.typeB : null);

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[68]++;}
    return blindScope;
  }

  private FlowScope caseAndOrNotShortCircuiting(Node left, Node right,
        FlowScope blindScope, boolean condition) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[59]++;
    // left type
    JSType leftType = getTypeIfRefinable(left, blindScope);
    boolean leftIsRefineable;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[60]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((leftType != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[69]++;
      leftIsRefineable = true;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[61]++;

    } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[70]++;
      leftIsRefineable = false;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[62]++;
      leftType = left.getJSType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[63]++;
      blindScope = firstPreciserScopeKnowingConditionOutcome(
          left, blindScope, condition);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[64]++;
    }
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[65]++;

    // restricting left type
    JSType restrictedLeftType = (leftType == null) ? null :
        leftType.getRestrictedTypeGivenToBooleanOutcome(condition);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[66]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((restrictedLeftType == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[71]++;
      return firstPreciserScopeKnowingConditionOutcome(
          right, blindScope, condition);

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[72]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[67]++;

    // right type
    JSType rightType = getTypeIfRefinable(right, blindScope);
    boolean rightIsRefineable;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[68]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((rightType != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[73]++;
      rightIsRefineable = true;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[69]++;

    } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[74]++;
      rightIsRefineable = false;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[70]++;
      rightType = right.getJSType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[71]++;
      blindScope = firstPreciserScopeKnowingConditionOutcome(
          right, blindScope, condition);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[72]++;
    }
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[73]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((condition) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[75]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[74]++;
      JSType restrictedRightType = (rightType == null) ? null :
          rightType.getRestrictedTypeGivenToBooleanOutcome(condition);

      // creating new scope
      return maybeRestrictTwoNames(
          blindScope,
          left, leftType, leftIsRefineable ? restrictedLeftType : null,
          right, rightType, rightIsRefineable ? restrictedRightType : null);

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[76]++;}
    return blindScope;
  }

  private FlowScope caseAndOrMaybeShortCircuiting(Node left, Node right,
      FlowScope blindScope, boolean condition) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[75]++;
    FlowScope leftScope = firstPreciserScopeKnowingConditionOutcome(
        left, blindScope, !condition);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[76]++;
    StaticSlot<JSType> leftVar = leftScope.findUniqueRefinedSlot(blindScope);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((leftVar == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[77]++;
      return blindScope;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[78]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[78]++;
    FlowScope rightScope = firstPreciserScopeKnowingConditionOutcome(
        left, blindScope, condition);
    rightScope = firstPreciserScopeKnowingConditionOutcome(
        right, rightScope, !condition);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[79]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[80]++;
    StaticSlot<JSType> rightVar = rightScope.findUniqueRefinedSlot(blindScope);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[81]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((rightVar == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((leftVar.getName().equals(rightVar.getName())) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[79]++;
      return blindScope;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[80]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[82]++;
    JSType type = leftVar.getType().getLeastSupertype(rightVar.getType());
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[83]++;
    FlowScope informed = blindScope.createChildFlowScope();
    informed.inferSlotType(leftVar.getName(), type);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[84]++;
    return informed;
  }

  /**
   * If the restrictedType differs from the originalType, then we should
   * branch the current flow scope and create a new flow scope with the name
   * declared with the new type.
   *
   * We try not to create spurious child flow scopes as this makes type
   * inference slower.
   *
   * We also do not want spurious slots around in type inference, because
   * we use these as a signal for "checked unknown" types. A "checked unknown"
   * type is a symbol that the programmer has already checked and verified that
   * it's defined, even if we don't know what it is.
   *
   * It is OK to pass non-name nodes into this method, as long as you pass
   * in {@code null} for a restricted type.
   */
  private FlowScope maybeRestrictName(
      FlowScope blindScope, Node node, JSType originalType, JSType restrictedType) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[85]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((restrictedType != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((restrictedType != originalType) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[81]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[86]++;
      FlowScope informed = blindScope.createChildFlowScope();
      declareNameInScope(informed, node, restrictedType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[87]++;
      return informed;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[82]++;}
    return blindScope;
  }

  /**
   * @see #maybeRestrictName
   */
  private FlowScope maybeRestrictTwoNames(
      FlowScope blindScope,
      Node left, JSType originalLeftType, JSType restrictedLeftType,
      Node right, JSType originalRightType, JSType restrictedRightType) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[88]++;
    boolean shouldRefineLeft =
        restrictedLeftType != null && restrictedLeftType != originalLeftType;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[89]++;
    boolean shouldRefineRight =
        restrictedRightType != null && restrictedRightType != originalRightType;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[90]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((shouldRefineLeft) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((shouldRefineRight) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[83]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[91]++;
      FlowScope informed = blindScope.createChildFlowScope();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[92]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((shouldRefineLeft) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[85]++;
        declareNameInScope(informed, left, restrictedLeftType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[93]++;

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[86]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[94]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((shouldRefineRight) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[87]++;
        declareNameInScope(informed, right, restrictedRightType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[95]++;

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[88]++;}
      return informed;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[84]++;}
    return blindScope;
  }

  private FlowScope caseNameOrGetProp(Node name, FlowScope blindScope,
      boolean outcome) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[96]++;
    JSType type = getTypeIfRefinable(name, blindScope);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[97]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[89]++;
      return maybeRestrictName(
          blindScope, name, type,
          type.getRestrictedTypeGivenToBooleanOutcome(outcome));

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[90]++;}
    return blindScope;
  }

  private FlowScope caseTypeOf(Node node, JSType type, String value,
        boolean resultEqualsValue, FlowScope blindScope) {
    return maybeRestrictName(
        blindScope, node, type,
        getRestrictedByTypeOfResult(type, value, resultEqualsValue));
  }

  private FlowScope caseInstanceOf(Node left, Node right, FlowScope blindScope,
      boolean outcome) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[98]++;
    JSType leftType = getTypeIfRefinable(left, blindScope);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[99]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((leftType == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[91]++;
      return blindScope;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[92]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[100]++;
    JSType rightType = right.getJSType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[101]++;
    ObjectType targetType =
        typeRegistry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[102]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((rightType != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((rightType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[93]++;
      targetType = rightType.toMaybeFunctionType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[103]++;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[94]++;}
    Visitor<JSType> visitor;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[104]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[95]++;
      visitor = new RestrictByTrueInstanceOfResultVisitor(targetType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[105]++;

    } else {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[96]++;
      visitor = new RestrictByFalseInstanceOfResultVisitor(targetType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[106]++;
    }
    return maybeRestrictName(
        blindScope, left, leftType, leftType.visit(visitor));
  }

  /**
   * Given 'property in object', ensures that the object has the property in the
   * informed scope by defining it as a qualified name if the object type lacks
   * the property and it's not in the blind scope.
   * @param object The node of the right-side of the in.
   * @param propertyName The string of the left-side of the in.
   */
  private FlowScope caseIn(Node object, String propertyName, FlowScope blindScope) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[107]++;
    JSType jsType = object.getJSType();
    jsType = this.getRestrictedWithoutNull(jsType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[108]++;
    jsType = this.getRestrictedWithoutUndefined(jsType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[109]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[110]++;

    boolean hasProperty = false;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[111]++;
    ObjectType objectType = ObjectType.cast(jsType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[112]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[97]++;
      hasProperty = objectType.hasProperty(propertyName);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[113]++;

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[98]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[114]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((hasProperty) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[99]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[115]++;
      String qualifiedName = object.getQualifiedName();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[116]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[101]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[117]++;
        String propertyQualifiedName = qualifiedName + "." + propertyName;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[118]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((blindScope.getSlot(propertyQualifiedName) == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[103]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[119]++;
          FlowScope informed = blindScope.createChildFlowScope();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[120]++;
          JSType unknownType = typeRegistry.getNativeType(
              JSTypeNative.UNKNOWN_TYPE);
          informed.inferQualifiedSlot(
              object, propertyQualifiedName, unknownType, unknownType);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[121]++;
          return informed;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[104]++;}

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[102]++;}

    } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[100]++;}
    return blindScope;
  }

  /**
   * @see SemanticReverseAbstractInterpreter#caseInstanceOf
   */
  private class RestrictByTrueInstanceOfResultVisitor
      extends RestrictByTrueTypeOfResultVisitor {
    private final ObjectType target;

    RestrictByTrueInstanceOfResultVisitor(ObjectType target) {
      this.target = target;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[122]++;
    }

    @Override
    protected JSType caseTopType(JSType type) {
      return applyCommonRestriction(type);
    }

    @Override
    public JSType caseUnknownType() {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[123]++;
      FunctionType funcTarget = JSType.toMaybeFunctionType(target);
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[124]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((funcTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((funcTarget.hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[105]++;
        return funcTarget.getInstanceType();

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[106]++;}
      return getNativeType(UNKNOWN_TYPE);
    }

    @Override
    public JSType caseObjectType(ObjectType type) {
      return applyCommonRestriction(type);
    }

    @Override
    public JSType caseUnionType(UnionType type) {
      return applyCommonRestriction(type);
    }

    @Override
    public JSType caseFunctionType(FunctionType type) {
      return caseObjectType(type);
    }

    private JSType applyCommonRestriction(JSType type) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[125]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((target.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[107]++;
        return type;

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[108]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[126]++;

      FunctionType funcTarget = target.toMaybeFunctionType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[127]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((funcTarget.hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[109]++;
        return type.getGreatestSubtype(funcTarget.getInstanceType());

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[110]++;}

      return null;
    }
  }

  /**
   * @see SemanticReverseAbstractInterpreter#caseInstanceOf
   */
  private class RestrictByFalseInstanceOfResultVisitor
      extends RestrictByFalseTypeOfResultVisitor {
    private final ObjectType target;

    RestrictByFalseInstanceOfResultVisitor(ObjectType target) {
      this.target = target;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[128]++;
    }

    @Override
    public JSType caseObjectType(ObjectType type) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[129]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((target.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[111]++;
        return type;

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[112]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[130]++;

      FunctionType funcTarget = target.toMaybeFunctionType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[131]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((funcTarget.hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[113]++;
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[132]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((type.isSubtype(funcTarget.getInstanceType())) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[115]++;
          return null;

        } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[116]++;}

        return type;

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[114]++;}

      return null;
    }

    @Override
    public JSType caseUnionType(UnionType type) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[133]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((target.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[117]++;
        return type;

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[118]++;}
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[134]++;

      FunctionType funcTarget = target.toMaybeFunctionType();
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.statements[135]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((funcTarget.hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[119]++;
        return type.getRestrictedUnion(funcTarget.getInstanceType());

      } else {
  CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl.branches[120]++;}

      return null;
    }

    @Override
    public JSType caseFunctionType(FunctionType type) {
      return caseObjectType(type);
    }
  }
}

class CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl ());
  }
    public static long[] statements = new long[136];
    public static long[] branches = new long[121];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[49];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.type.SemanticReverseAbstractInterpreter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,2,2,1,2,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 48; i++) {
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

  public CodeCoverCoverageCounter$14yr6l0xzcwhtxuiilono8eslr201mgfzgpomkmd239otdcau330dmxo95ffl () {
    super("com.google.javascript.jscomp.type.SemanticReverseAbstractInterpreter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 135; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 120; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.type.SemanticReverseAbstractInterpreter.java");
      for (int i = 1; i <= 135; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 120; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 48; i++) {
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

