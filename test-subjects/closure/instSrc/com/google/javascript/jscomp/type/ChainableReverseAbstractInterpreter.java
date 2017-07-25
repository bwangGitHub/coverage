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

import static com.google.javascript.rhino.jstype.JSTypeNative.ALL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.BOOLEAN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.CHECKED_UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.U2U_CONSTRUCTOR_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;

import com.google.common.base.Preconditions;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.EnumElementType;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.TemplatizedType;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.TemplateType;
import com.google.javascript.rhino.jstype.UnionType;
import com.google.javascript.rhino.jstype.Visitor;

/**
 * Chainable reverse abstract interpreter providing basic functionality.
 *
 */
public abstract class ChainableReverseAbstractInterpreter
    implements ReverseAbstractInterpreter {
  static {
    CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.ping();
  }

  protected final CodingConvention convention;
  final JSTypeRegistry typeRegistry;
  private ChainableReverseAbstractInterpreter firstLink;
  private ChainableReverseAbstractInterpreter nextLink;

  /**
   * Constructs an interpreter, which is the only link in a chain. Interpreters
   * can be appended using {@link #append}.
   */
  public ChainableReverseAbstractInterpreter(CodingConvention convention,
      JSTypeRegistry typeRegistry) {
    Preconditions.checkNotNull(convention);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[1]++;
    this.convention = convention;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[2]++;
    this.typeRegistry = typeRegistry;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[3]++;
    firstLink = this;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[4]++;
    nextLink = null;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[5]++;
  }

  /**
   * Appends a link to {@code this}, returning the updated last link.
   * <p>
   * The pattern {@code new X().append(new Y())...append(new Z())} forms a
   * chain starting with X, then Y, then ... Z.
   * @param lastLink a chainable interpreter, with no next link
   * @return the updated last link
   */
  public ChainableReverseAbstractInterpreter append(
      ChainableReverseAbstractInterpreter lastLink) {
    Preconditions.checkArgument(lastLink.nextLink == null);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[6]++;
    this.nextLink = lastLink;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[7]++;
    lastLink.firstLink = this.firstLink;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[8]++;
    return lastLink;
  }

  /**
   * Gets the first link of this chain.
   */
  public ChainableReverseAbstractInterpreter getFirst() {
    return firstLink;
  }

  /**
   * Calculates the preciser scope starting with the first link.
   */
  protected FlowScope firstPreciserScopeKnowingConditionOutcome(Node condition,
      FlowScope blindScope, boolean outcome) {
    return firstLink.getPreciserScopeKnowingConditionOutcome(
        condition, blindScope, outcome);
  }

  /**
   * Delegates the calculation of the preciser scope to the next link.
   * If there is no next link, returns the blind scope.
   */
  protected FlowScope nextPreciserScopeKnowingConditionOutcome(Node condition,
      FlowScope blindScope, boolean outcome) {
    return nextLink != null ? nextLink.getPreciserScopeKnowingConditionOutcome(
        condition, blindScope, outcome) : blindScope;
  }

  /**
   * Returns the type of a node in the given scope if the node corresponds to a
   * name whose type is capable of being refined.
   * @return The current type of the node if it can be refined, null otherwise.
   */
  protected JSType getTypeIfRefinable(Node node, FlowScope scope) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[9]++;
    switch (node.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[1]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[10]++;
        StaticSlot<JSType> nameVar = scope.getSlot(node.getString());
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nameVar != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[2]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[12]++;
          JSType nameVarType = nameVar.getType();
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((nameVarType == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[4]++;
            nameVarType = node.getJSType();
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[14]++;

          } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[5]++;}
          return nameVarType;

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[3]++;}
        return null;

      case Token.GETPROP:
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[6]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[15]++;
        String qualifiedName = node.getQualifiedName();
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((qualifiedName == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[7]++;
          return null;

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[8]++;}
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[17]++;
        StaticSlot<JSType> propVar = scope.getSlot(qualifiedName);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[18]++;
        JSType propVarType = null;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((propVar != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[9]++;
          propVarType = propVar.getType();
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[20]++;

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[10]++;}
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((propVarType == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[11]++;
          propVarType = node.getJSType();
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[22]++;

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[12]++;}
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((propVarType == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[13]++;
          propVarType = getNativeType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[24]++;

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[14]++;}
        return propVarType; default : CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[15]++;
    }
    return null;
  }

  /**
   * Declares a refined type in {@code scope} for the name represented by
   * {@code node}. It must be possible to refine the type of the given node in
   * the given scope, as determined by {@link #getTypeIfRefinable}.
   */
  protected void declareNameInScope(FlowScope scope, Node node, JSType type) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[25]++;
    switch (node.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[16]++;
        scope.inferSlotType(node.getString(), type);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[26]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[27]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[17]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[28]++;
        String qualifiedName = node.getQualifiedName();
        Preconditions.checkNotNull(qualifiedName);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[29]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[30]++;

        JSType origType = node.getJSType();
        origType = origType == null ? getNativeType(UNKNOWN_TYPE) : origType;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[31]++;
        scope.inferQualifiedSlot(node, qualifiedName, origType, type);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[32]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[33]++;
        break;

      case Token.THIS:
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[18]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[34]++;
        // "this" references aren't currently modeled in the CFG.
        break;

      default:
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[19]++;
        throw new IllegalArgumentException("Node cannot be refined. \n" +
            node.toStringTree());
    }
  }

  /**
   * @see #getRestrictedWithoutUndefined(JSType)
   */
  private final Visitor<JSType> restrictUndefinedVisitor =
    new Visitor<JSType>() {
      @Override
      public JSType caseEnumElementType(EnumElementType enumElementType) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[35]++;
        JSType type = enumElementType.getPrimitiveType().visit(this);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((enumElementType.getPrimitiveType().isEquivalentTo(type)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[20]++;
          return enumElementType;

        } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[21]++;
          return type;
        }
      }

      @Override
      public JSType caseAllType() {
        return typeRegistry.createUnionType(OBJECT_TYPE, NUMBER_TYPE,
            STRING_TYPE, BOOLEAN_TYPE, NULL_TYPE);
      }

      @Override
      public JSType caseNoObjectType() {
        return getNativeType(NO_OBJECT_TYPE);
      }

      @Override
      public JSType caseNoType() {
        return getNativeType(NO_TYPE);
      }

      @Override
      public JSType caseBooleanType() {
        return getNativeType(BOOLEAN_TYPE);
      }

      @Override
      public JSType caseFunctionType(FunctionType type) {
        return type;
      }

      @Override
      public JSType caseNullType() {
        return getNativeType(NULL_TYPE);
      }

      @Override
      public JSType caseNumberType() {
        return getNativeType(NUMBER_TYPE);
      }

      @Override
      public JSType caseObjectType(ObjectType type) {
        return type;
      }

      @Override
      public JSType caseStringType() {
        return getNativeType(STRING_TYPE);
      }

      @Override
      public JSType caseUnionType(UnionType type) {
        return type.getRestrictedUnion(getNativeType(VOID_TYPE));
      }

      @Override
      public JSType caseUnknownType() {
        return getNativeType(UNKNOWN_TYPE);
      }

      @Override
      public JSType caseVoidType() {
        return null;
      }

      @Override
      public JSType caseTemplatizedType(TemplatizedType type) {
        return caseObjectType(type);
      }

      @Override
      public JSType caseTemplateType(TemplateType templateType) {
        return caseObjectType(templateType);
      }
    };
  {
    CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[37]++;
  }


  /**
   * @see #getRestrictedWithoutNull(JSType)
   */
  private final Visitor<JSType> restrictNullVisitor =
    new Visitor<JSType>() {
      @Override
      public JSType caseEnumElementType(EnumElementType enumElementType) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[38]++;
        JSType type = enumElementType.getPrimitiveType().visit(this);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((enumElementType.getPrimitiveType().isEquivalentTo(type)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[22]++;
          return enumElementType;

        } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[23]++;
          return type;
        }
      }

      @Override
      public JSType caseAllType() {
        return typeRegistry.createUnionType(OBJECT_TYPE, NUMBER_TYPE,
            STRING_TYPE, BOOLEAN_TYPE, VOID_TYPE);
      }

      @Override
      public JSType caseNoObjectType() {
        return getNativeType(NO_OBJECT_TYPE);
      }

      @Override
      public JSType caseNoType() {
        return getNativeType(NO_TYPE);
      }

      @Override
      public JSType caseBooleanType() {
        return getNativeType(BOOLEAN_TYPE);
      }

      @Override
      public JSType caseFunctionType(FunctionType type) {
        return type;
      }

      @Override
      public JSType caseNullType() {
        return null;
      }

      @Override
      public JSType caseNumberType() {
        return getNativeType(NUMBER_TYPE);
      }

      @Override
      public JSType caseObjectType(ObjectType type) {
        return type;
      }

      @Override
      public JSType caseStringType() {
        return getNativeType(STRING_TYPE);
      }

      @Override
      public JSType caseUnionType(UnionType type) {
        return type.getRestrictedUnion(getNativeType(NULL_TYPE));
      }

      @Override
      public JSType caseUnknownType() {
        return getNativeType(UNKNOWN_TYPE);
      }

      @Override
      public JSType caseVoidType() {
        return getNativeType(VOID_TYPE);
      }

      @Override
      public JSType caseTemplatizedType(TemplatizedType type) {
        return caseObjectType(type);
      }

      @Override
      public JSType caseTemplateType(TemplateType templateType) {
        return caseObjectType(templateType);
      }
    };
  {
    CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[40]++;
  }

  /**
   * A class common to all visitors that need to restrict the type based on
   * {@code typeof}-like conditions.
   */
  abstract class RestrictByTypeOfResultVisitor
      implements Visitor<JSType> {

    /**
     * Abstracts away the similarities between visiting the unknown type and the
     * all type.
     * @param topType {@code UNKNOWN_TYPE} or {@code ALL_TYPE}
     * @return the restricted type
     * @see #caseAllType
     * @see #caseUnknownType
     */
    protected abstract JSType caseTopType(JSType topType);

    @Override
    public JSType caseAllType() {
      return caseTopType(getNativeType(ALL_TYPE));
    }

    @Override
    public JSType caseUnknownType() {
      return caseTopType(getNativeType(CHECKED_UNKNOWN_TYPE));
    }

    @Override
    public JSType caseUnionType(UnionType type) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[41]++;
      JSType restricted = null;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[42]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.loops[1]++;


      for (JSType alternate : type.getAlternates()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.loops[1]--;
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.loops[2]--;
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.loops[3]++;
}
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[43]++;
        JSType restrictedAlternate = alternate.visit(this);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((restrictedAlternate != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[24]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((restricted == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[26]++;
            restricted = restrictedAlternate;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[46]++;

          } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[27]++;
            restricted = restrictedAlternate.getLeastSupertype(restricted);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[47]++;
          }

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[25]++;}
      }
      return restricted;
    }

    @Override
    public JSType caseNoType() {
      return getNativeType(NO_TYPE);
    }

    @Override
    public JSType caseEnumElementType(EnumElementType enumElementType) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[48]++;
      // NOTE(nicksantos): This is a white lie. Suppose we have:
      // /** @enum {string|number} */ var MyEnum = ...;
      // if (goog.isNumber(myEnumInstance)) {
      //   /* what is myEnumInstance here? */
      // }
      // There is no type that represents {MyEnum - string}. What we really
      // need is a notion of "enum subtyping", so that we could dynamically
      // create a subtype of MyEnum restricted by string. In any case,
      // this should catch the common case.
      JSType type = enumElementType.getPrimitiveType().visit(this);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((enumElementType.getPrimitiveType().isEquivalentTo(type)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[28]++;
        return enumElementType;

      } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[29]++;
        return type;
      }
    }

    @Override
    public JSType caseTemplatizedType(TemplatizedType type) {
      return caseObjectType(type);
    }

    @Override
    public JSType caseTemplateType(TemplateType templateType) {
      return caseObjectType(templateType);
    }
  }

  /**
   * A class common to all visitors that need to restrict the type based on
   * some {@code typeof}-like condition being true. All base cases return
   * {@code null}. It is up to the subclasses to override the appropriate ones.
   */
  abstract class RestrictByTrueTypeOfResultVisitor
      extends RestrictByTypeOfResultVisitor {
    @Override
    public JSType caseNoObjectType() {
      return null;
    }

    @Override
    public JSType caseBooleanType() {
      return null;
    }

    @Override
    public JSType caseFunctionType(FunctionType type) {
      return null;
    }

    @Override
    public JSType caseNullType() {
      return null;
    }

    @Override
    public JSType caseNumberType() {
      return null;
    }

    @Override
    public JSType caseObjectType(ObjectType type) {
      return null;
    }

    @Override
    public JSType caseStringType() {
      return null;
    }

    @Override
    public JSType caseVoidType() {
      return null;
    }
  }

  /**
   * A class common to all visitors that need to restrict the type based on
   * some {@code typeof}-like condition being false. All base cases return
   * their type. It is up to the subclasses to override the appropriate ones.
   */
  abstract class RestrictByFalseTypeOfResultVisitor
      extends RestrictByTypeOfResultVisitor {
    @Override
    protected JSType caseTopType(JSType topType) {
      return topType;
    }

    @Override
    public JSType caseNoObjectType() {
      return getNativeType(NO_OBJECT_TYPE);
    }

    @Override
    public JSType caseBooleanType() {
      return getNativeType(BOOLEAN_TYPE);
    }

    @Override
    public JSType caseFunctionType(FunctionType type) {
      return type;
    }

    @Override
    public JSType caseNullType() {
      return getNativeType(NULL_TYPE);
    }

    @Override
    public JSType caseNumberType() {
      return getNativeType(NUMBER_TYPE);
    }

    @Override
    public JSType caseObjectType(ObjectType type) {
      return type;
    }

    @Override
    public JSType caseStringType() {
      return getNativeType(STRING_TYPE);
    }

    @Override
    public JSType caseVoidType() {
      return getNativeType(VOID_TYPE);
    }
  }

  /**
   * @see ChainableReverseAbstractInterpreter#getRestrictedByTypeOfResult
   */
  private class RestrictByOneTypeOfResultVisitor
      extends RestrictByTypeOfResultVisitor {
    /**
     * A value known to be equal or not equal to the result of the
     * {@code typeOf} operation.
     */
    private final String value;

    /**
     * {@code true} if the {@code typeOf} result is known to equal
     * {@code value}; {@code false} if it is known <em>not</em> to equal
     * {@code value}.
     */
    private final boolean resultEqualsValue;

    RestrictByOneTypeOfResultVisitor(String value, boolean resultEqualsValue) {
      this.value = value;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[50]++;
      this.resultEqualsValue = resultEqualsValue;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[51]++;
    }

    /**
     * Computes whether the given result of a {@code typeof} operator matches
     * expectations, i.e. whether a type that gives such a result should be
     * kept.
     */
    private boolean matchesExpectation(String result) {
      return result.equals(value) == resultEqualsValue;
    }

    @Override
    protected JSType caseTopType(JSType topType) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[52]++;
      JSType result = topType;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((resultEqualsValue) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[30]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[54]++;
        JSType typeByName = getNativeTypeForTypeOf(value);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((typeByName != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[32]++;
          result = typeByName;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[56]++;

        } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[33]++;}

      } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[31]++;}
      return result;
    }

    @Override
    public JSType caseNoObjectType() {
      return (value.equals("object") || value.equals("function")) ==
          resultEqualsValue ? getNativeType(NO_OBJECT_TYPE) : null;
    }

    @Override
    public JSType caseBooleanType() {
      return matchesExpectation("boolean") ? getNativeType(BOOLEAN_TYPE) : null;
    }

    @Override
    public JSType caseFunctionType(FunctionType type) {
      return matchesExpectation("function") ? type : null;
    }

    @Override
    public JSType caseNullType() {
      return matchesExpectation("object") ? getNativeType(NULL_TYPE) : null;
    }

    @Override
    public JSType caseNumberType() {
      return matchesExpectation("number") ? getNativeType(NUMBER_TYPE) : null;
    }

    @Override
    public JSType caseObjectType(ObjectType type) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[57]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value.equals("function")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[34]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[58]++;
        JSType ctorType = getNativeType(U2U_CONSTRUCTOR_TYPE);
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((resultEqualsValue) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[36]++;
          // Objects are restricted to "Function", subtypes are left
          return ctorType.getGreatestSubtype(type);

        } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[37]++;
          // Only filter out subtypes of "function"
          return type.isSubtype(ctorType) ? null : type;
        }

      } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[35]++;}
      return matchesExpectation("object") ? type : null;
    }

    @Override
    public JSType caseStringType() {
      return matchesExpectation("string") ? getNativeType(STRING_TYPE) : null;
    }

    @Override
    public JSType caseVoidType() {
      return matchesExpectation("undefined") ? getNativeType(VOID_TYPE) : null;
    }
  }

  /**
   * Returns a version of type where undefined is not present.
   */
  protected final JSType getRestrictedWithoutUndefined(JSType type) {
    return type == null ? null : type.visit(restrictUndefinedVisitor);
  }

  /**
   * Returns a version of type where null is not present.
   */
  protected final JSType getRestrictedWithoutNull(JSType type) {
    return type == null ? null : type.visit(restrictNullVisitor);
  }

  /**
   * Returns a version of {@code type} that is restricted by some knowledge
   * about the result of the {@code typeof} operation.
   * <p>
   * The behavior of the {@code typeof} operator can be summarized by the
   * following table:
   * <table>
   * <tr><th>type</th><th>result</th></tr>
   * <tr><td>{@code undefined}</td><td>"undefined"</td></tr>
   * <tr><td>{@code null}</td><td>"object"</td></tr>
   * <tr><td>{@code boolean}</td><td>"boolean"</td></tr>
   * <tr><td>{@code number}</td><td>"number"</td></tr>
   * <tr><td>{@code string}</td><td>"string"</td></tr>
   * <tr><td>{@code Object} (which doesn't implement [[Call]])</td>
   *     <td>"object"</td></tr>
   * <tr><td>{@code Object} (which implements [[Call]])</td>
   *     <td>"function"</td></tr>
   * </table>
   * @param type the type to restrict
   * @param value A value known to be equal or not equal to the result of the
   *        {@code typeof} operation
   * @param resultEqualsValue {@code true} if the {@code typeOf} result is known
   *        to equal {@code value}; {@code false} if it is known <em>not</em> to
   *        equal {@code value}
   * @return the restricted type or null if no version of the type matches the
   *         restriction
   */
  JSType getRestrictedByTypeOfResult(JSType type, String value,
                                     boolean resultEqualsValue) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[38]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[61]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((resultEqualsValue) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[40]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[62]++;
        JSType result = getNativeTypeForTypeOf(value);
        return result == null ? getNativeType(CHECKED_UNKNOWN_TYPE) : result;

      } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[41]++;
        return null;
      }

    } else {
  CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[39]++;}
    return type.visit(
        new RestrictByOneTypeOfResultVisitor(value, resultEqualsValue));
  }

  JSType getNativeType(JSTypeNative typeId) {
    return typeRegistry.getNativeType(typeId);
  }

  /**
   * If we definitely know what a type is based on the typeof result,
   * return it.  Otherwise, return null.
   *
   * The typeof operation in JS is poorly defined, and this function works
   * for both the native typeof and goog.typeOf. It should not be made public,
   * because its semantics are informally defined, and would be wrong in
   * the general case.
   */
  private JSType getNativeTypeForTypeOf(String value) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[63]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((value.equals("number")) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[42]++;
      return getNativeType(NUMBER_TYPE);

    } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[43]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[64]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((value.equals("boolean")) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[44]++;
      return getNativeType(BOOLEAN_TYPE);

    } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[45]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[65]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((value.equals("string")) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[46]++;
      return getNativeType(STRING_TYPE);

    } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[47]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[66]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((value.equals("undefined")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[48]++;
      return getNativeType(VOID_TYPE);

    } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[49]++;
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.statements[67]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((value.equals("function")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[50]++;
      return getNativeType(U2U_CONSTRUCTOR_TYPE);

    } else {
CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd.branches[51]++;
      return null;
    }
}
}
}
}
  }
}

class CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[52];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[23];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.type.ChainableReverseAbstractInterpreter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 22; i++) {
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

  public CodeCoverCoverageCounter$6jgl08id1fv52tp5tndveshs9a4qoq6lkuiohkt8msgnifd9wiz7kx6x1fktpd () {
    super("com.google.javascript.jscomp.type.ChainableReverseAbstractInterpreter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 51; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 22; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.type.ChainableReverseAbstractInterpreter.java");
      for (int i = 1; i <= 67; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 51; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 22; i++) {
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

