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

import static com.google.javascript.rhino.jstype.JSTypeNative.ARRAY_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_VOID;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_STRING_BOOLEAN;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.Visitor;

import java.util.Map;

/**
 * A reverse abstract interpreter (RAI) for specific closure patterns such as
 * {@code goog.isDef}.
 *
 */
public class ClosureReverseAbstractInterpreter
    extends ChainableReverseAbstractInterpreter {
  static {
    CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.ping();
  }


  /**
   * For when {@code goog.isArray} returns true.
   */
  private final Visitor<JSType> restrictToArrayVisitor =
      new RestrictByTrueTypeOfResultVisitor() {
        @Override
        protected JSType caseTopType(JSType topType) {
          // Ideally, we would like to return any subtype of Array.
          // Since that's not possible, we don't restrict the type.
          return topType;
        }

        @Override
        public JSType caseObjectType(ObjectType type) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[1]++;
          JSType arrayType = getNativeType(ARRAY_TYPE);
          return arrayType.isSubtype(type) ? arrayType : null;
        }
      };
  {
    CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[2]++;
  }

  /**
   * For when {@code goog.isArray} returns false.
   */
  private final Visitor<JSType> restrictToNotArrayVisitor =
      new RestrictByFalseTypeOfResultVisitor() {
        @Override
        public JSType caseObjectType(ObjectType type) {
          return type.isSubtype(getNativeType(ARRAY_TYPE)) ? null : type;
        }
      };
  {
    CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[3]++;
  }

  /**
   * For when {@code goog.isObject} returns true. This includes functions, but
   * not {@code null}.
   */
  private final Visitor<JSType> restrictToObjectVisitor =
      new RestrictByTrueTypeOfResultVisitor() {
        @Override
        protected JSType caseTopType(JSType topType) {
          return getNativeType(NO_OBJECT_TYPE);
        }

        @Override
        public JSType caseObjectType(ObjectType type) {
          return type;
        }

        @Override
        public JSType caseFunctionType(FunctionType type) {
          return type;
        }
      };
  {
    CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[4]++;
  }

  /**
   * For when {@code goog.isObject} returns false.
   */
  private final Visitor<JSType> restrictToNotObjectVisitor =
      new RestrictByFalseTypeOfResultVisitor() {

        @Override
        public JSType caseAllType() {
          return typeRegistry.createUnionType(
              getNativeType(NUMBER_STRING_BOOLEAN), getNativeType(NULL_VOID));
        }

        @Override
        public JSType caseObjectType(ObjectType type) {
          return null;
        }

        @Override
        public JSType caseFunctionType(FunctionType type) {
          return null;
        }
      };
  {
    CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[5]++;
  }

  /** Functions used to restrict types. */
  private Map<String, Function<TypeRestriction, JSType>> restricters;

  /**
   * Creates a {@link ClosureReverseAbstractInterpreter}.
   */
  public ClosureReverseAbstractInterpreter(CodingConvention convention,
      final JSTypeRegistry typeRegistry) {
    super(convention, typeRegistry);
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[6]++;
    this.restricters =
      new ImmutableMap.Builder<String, Function<TypeRestriction, JSType>>()
      .put("isDef", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p.outcome) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[1]++;
            return getRestrictedWithoutUndefined(p.type);

          } else {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[2]++;
            return  p.type != null ?
                getNativeType(VOID_TYPE).getGreatestSubtype(p.type) : null;
          }
         }
      })
      .put("isNull", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p.outcome) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[3]++;
            return p.type != null ?
                getNativeType(NULL_TYPE).getGreatestSubtype(p.type) : null;

          } else {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[4]++;
            return getRestrictedWithoutNull(p.type);
          }
        }
      })
      .put("isDefAndNotNull", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((p.outcome) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[5]++;
            return getRestrictedWithoutUndefined(
                getRestrictedWithoutNull(p.type));

          } else {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[6]++;
            return p.type != null ?
                getNativeType(NULL_VOID).getGreatestSubtype(p.type) : null;
          }
        }
      })
      .put("isString", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
          return getRestrictedByTypeOfResult(p.type, "string", p.outcome);
        }
      })
      .put("isBoolean", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
          return getRestrictedByTypeOfResult(p.type, "boolean", p.outcome);
        }
      })
      .put("isNumber", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
          return getRestrictedByTypeOfResult(p.type, "number", p.outcome);
        }
      })
      .put("isFunction", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
          return getRestrictedByTypeOfResult(p.type, "function", p.outcome);
        }
      })
      .put("isArray", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((p.type == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[7]++;
            return p.outcome ? getNativeType(ARRAY_TYPE) : null;

          } else {
  CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[8]++;}
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[12]++;

          Visitor<JSType> visitor = p.outcome ? restrictToArrayVisitor :
              restrictToNotArrayVisitor;
          return p.type.visit(visitor);
        }
      })
      .put("isObject", new Function<TypeRestriction, JSType>() {
        @Override
        public JSType apply(TypeRestriction p) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((p.type == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[9]++;
            return p.outcome ? getNativeType(OBJECT_TYPE) : null;

          } else {
  CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[10]++;}
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[14]++;

          Visitor<JSType> visitor = p.outcome ? restrictToObjectVisitor :
              restrictToNotObjectVisitor;
          return p.type.visit(visitor);
        }
      })
      .build();
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[7]++;
  }

  @Override
  public FlowScope getPreciserScopeKnowingConditionOutcome(Node condition,
      FlowScope blindScope, boolean outcome) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((condition.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((condition.getChildCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[11]++;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[16]++;
      Node callee = condition.getFirstChild();
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[17]++;
      Node param = condition.getLastChild();
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((callee.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((param.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[13]++;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[19]++;
        JSType paramType =  getTypeIfRefinable(param, blindScope);
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[20]++;
        Node left = callee.getFirstChild();
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[21]++;
        Node right = callee.getLastChild();
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((left.isName()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 (("goog".equals(left.getString())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[15]++;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[23]++;
          Function<TypeRestriction, JSType> restricter =
              restricters.get(right.getString());
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((restricter != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[17]++;
            return restrictParameter(param, paramType, blindScope, restricter,
                outcome);

          } else {
  CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[16]++;}

      } else {
  CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[14]++;}

    } else {
  CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[12]++;}
    return nextPreciserScopeKnowingConditionOutcome(
        condition, blindScope, outcome);
  }

  private FlowScope restrictParameter(Node parameter, JSType type,
      FlowScope blindScope, Function<TypeRestriction, JSType> restriction,
      boolean outcome) {
    // restricting
    type = restriction.apply(new TypeRestriction(type, outcome));
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[25]++;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;

    // changing the scope
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[19]++;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[27]++;
      FlowScope informed = blindScope.createChildFlowScope();
      declareNameInScope(informed, parameter, type);
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[28]++;
      return informed;

    } else {
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.branches[20]++;
      return blindScope;
    }
  }

  private static class TypeRestriction {
    private final JSType type;
    private final boolean outcome;

    private TypeRestriction(JSType type, boolean outcome) {
      this.type = type;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[29]++;
      this.outcome = outcome;
CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x.statements[30]++;
    }
  }
}

class CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.type.ClosureReverseAbstractInterpreter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,3,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$4no0m0wczmqmmizzsj5vybequ27y0si6x5mz4vqum05vqvj8bfr3ndb1g0x () {
    super("com.google.javascript.jscomp.type.ClosureReverseAbstractInterpreter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.type.ClosureReverseAbstractInterpreter.java");
      for (int i = 1; i <= 30; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

