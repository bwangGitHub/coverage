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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This describes the Closure-specific JavaScript coding conventions.
 *
 */
public class ClosureCodingConvention extends CodingConventions.Proxy {
  static {
    CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.ping();
  }


  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[1]++;
  }

  static final DiagnosticType OBJECTLIT_EXPECTED = DiagnosticType.warning(
      "JSC_REFLECT_OBJECTLIT_EXPECTED",
      "Object literal expected as second argument");
  static {
    CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[2]++;
  }

  private final Set<String> indirectlyDeclaredProperties;

  public ClosureCodingConvention() {
    this(CodingConventions.getDefault());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[3]++;
  }

  public ClosureCodingConvention(CodingConvention wrapped) {
    super(wrapped);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[4]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[5]++;

    Set<String> props = Sets.newHashSet(
        "superClass_",
        "instance_",
        "getInstance");
    props.addAll(wrapped.getIndirectlyDeclaredProperties());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[6]++;
    indirectlyDeclaredProperties = ImmutableSet.copyOf(props);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[7]++;
  }

  /**
   * Closure's goog.inherits adds a {@code superClass_} property to the
   * subclass, and a {@code constructor} property.
   */
  @Override
  public void applySubclassRelationship(FunctionType parentCtor,
      FunctionType childCtor, SubclassType type) {
    super.applySubclassRelationship(parentCtor, childCtor, type);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[8]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type == SubclassType.INHERITS) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[1]++;
      childCtor.defineDeclaredProperty("superClass_",
          parentCtor.getPrototype(), childCtor.getSource());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[10]++;
      childCtor.getPrototype().defineDeclaredProperty("constructor",
          // Notice that constructor functions do not need to be covariant
          // on the superclass.
          // So if G extends F, new G() and new F() can accept completely
          // different argument types, but G.prototype.constructor needs
          // to be covariant on F.prototype.constructor.
          // To get around this, we just turn off type-checking on arguments
          // and return types of G.prototype.constructor.
          childCtor.cloneWithoutArrowType(),
          childCtor.getSource());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[11]++;

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[2]++;}
  }

  /**
   * {@inheritDoc}
   *
   * <p>Understands several different inheritance patterns that occur in
   * Google code (various uses of {@code inherits} and {@code mixin}).
   */
  @Override
  public SubclassRelationship getClassesDefinedByCall(Node callNode) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[12]++;
    SubclassRelationship relationship =
        super.getClassesDefinedByCall(callNode);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((relationship != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[3]++; return relationship;
} else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[4]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[14]++;

    Node callName = callNode.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[15]++;
    SubclassType type = typeofClassDefiningName(callName);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[5]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[17]++;
      Node subclass = null;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[18]++;
      Node superclass = callNode.getLastChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[19]++;

      // There are six possible syntaxes for a class-defining method:
      // SubClass.inherits(SuperClass)
      // goog.inherits(SubClass, SuperClass)
      // goog$inherits(SubClass, SuperClass)
      // SubClass.mixin(SuperClass.prototype)
      // goog.mixin(SubClass.prototype, SuperClass.prototype)
      // goog$mixin(SubClass.prototype, SuperClass.prototype)
      boolean isDeprecatedCall = callNode.getChildCount() == 2 &&
          callName.isGetProp();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isDeprecatedCall) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[7]++;
        // SubClass.inherits(SuperClass)
        subclass = callName.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[21]++;

      } else {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[8]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[22]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((callNode.getChildCount() == 3) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[9]++;
        // goog.inherits(SubClass, SuperClass)
        subclass = callName.getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[23]++;

      } else {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[10]++;
        return null;
      }
}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type == SubclassType.MIXIN) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[11]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        // Only consider mixins that mix two prototypes as related to
        // inheritance.
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((endsWithPrototype(superclass)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[13]++;
          return null;

        } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[14]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isDeprecatedCall) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[15]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((endsWithPrototype(subclass)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[17]++;
            return null;

          } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[18]++;}
          // Strip off the prototype from the name.
          subclass = subclass.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[28]++;

        } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[16]++;}
        superclass = superclass.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[29]++;

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[12]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;

      // bail out if either of the side of the "inherits"
      // isn't a real class name. This prevents us from
      // doing something weird in cases like:
      // goog.inherits(MySubClass, cond ? SuperClass1 : BaseClass2)
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((subclass != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((subclass.isUnscopedQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((superclass.isUnscopedQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[19]++;
        return new SubclassRelationship(type, subclass, superclass);

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[20]++;}

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[6]++;}

    return null;
  }

  /**
   * Determines whether the given node is a class-defining name, like
   * "inherits" or "mixin."
   * @return The type of class-defining name, or null.
   */
  private SubclassType typeofClassDefiningName(Node callName) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[31]++;
    // Check if the method name matches one of the class-defining methods.
    String methodName = null;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[32]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((callName.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[21]++;
      methodName = callName.getLastChild().getString();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[33]++;

    } else {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[22]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[34]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((callName.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[23]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[35]++;
      String name = callName.getString();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[36]++;
      int dollarIndex = name.lastIndexOf('$');
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((dollarIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[25]++;
        methodName = name.substring(dollarIndex + 1);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[38]++;

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[26]++;}

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[24]++;}
}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((methodName != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[27]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((methodName.equals("inherits")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[29]++;
        return SubclassType.INHERITS;

      } else {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[30]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[41]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((methodName.equals("mixin")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[31]++;
        return SubclassType.MIXIN;

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[32]++;}
}

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[28]++;}
    return null;
  }

  @Override
  public boolean isSuperClassReference(String propertyName) {
    return "superClass_".equals(propertyName) ||
        super.isSuperClassReference(propertyName);
  }

  /**
   * Given a qualified name node, returns whether "prototype" is at the end.
   * For example:
   * a.b.c => false
   * a.b.c.prototype => true
   */
  private boolean endsWithPrototype(Node qualifiedName) {
    return qualifiedName.isGetProp() &&
        qualifiedName.getLastChild().getString().equals("prototype");
  }

  /**
   * Extracts X from goog.provide('X'), if the applied Node is goog.
   *
   * @return The extracted class name, or null.
   */
  @Override
  public String extractClassNameIfProvide(Node node, Node parent){
    return extractClassNameIfGoog(node, parent, "goog.provide");
  }

  /**
   * Extracts X from goog.require('X'), if the applied Node is goog.
   *
   * @return The extracted class name, or null.
   */
  @Override
  public String extractClassNameIfRequire(Node node, Node parent){
    return extractClassNameIfGoog(node, parent, "goog.require");
  }

  private static String extractClassNameIfGoog(Node node, Node parent,
      String functionName){
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[42]++;
    String className = null;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[43]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprCall(parent)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[33]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[44]++;
      Node callee = node.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[45]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((callee != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((callee.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[35]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[46]++;
        String qualifiedName = callee.getQualifiedName();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[47]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((functionName.equals(qualifiedName)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[37]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[48]++;
          Node target = callee.getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[49]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((target.isString()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[39]++;
            className = target.getString();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[50]++;

          } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[40]++;}

        } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[38]++;}

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[36]++;}

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[34]++;}
    return className;
  }

  /**
   * Use closure's implementation.
   * @return closure's function name for exporting properties.
   */
  @Override
  public String getExportPropertyFunction() {
    return "goog.exportProperty";
  }

  /**
   * Use closure's implementation.
   * @return closure's function name for exporting symbols.
   */
  @Override
  public String getExportSymbolFunction() {
    return "goog.exportSymbol";
  }

  @Override
  public List<String> identifyTypeDeclarationCall(Node n) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[51]++;
    Node callName = n.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[52]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 (("goog.addDependency".equals(callName.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((n.getChildCount() >= 3) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[41]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[53]++;
      Node typeArray = callName.getNext().getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[54]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((typeArray.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[43]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[55]++;
        List<String> typeNames = Lists.newArrayList();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[56]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.loops[1]++;


int CodeCoverConditionCoverageHelper_C23;
        for (Node name = typeArray.getFirstChild();(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false);
             name = name.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.loops[1]--;
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.loops[2]--;
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.loops[3]++;
}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[57]++;
int CodeCoverConditionCoverageHelper_C24;
          if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((name.isString()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[45]++;
            typeNames.add(name.getString());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[58]++;

          } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[46]++;}
        }
        return typeNames;

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[44]++;}

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[42]++;}
    return super.identifyTypeDeclarationCall(n);
  }

  @Override
  public String getAbstractMethodName() {
    return "goog.abstractMethod";
  }

  @Override
  public String getSingletonGetterClassName(Node callNode) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[59]++;
    Node callArg = callNode.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[60]++;
    String callName = callArg.getQualifiedName();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[61]++;
int CodeCoverConditionCoverageHelper_C25;

    // Use both the original name and the post-CollapseProperties name.
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 (("goog.addSingletonGetter".equals(callName)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 (("goog$addSingletonGetter".equals(callName)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((callNode.getChildCount() != 2) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[47]++;
      return super.getSingletonGetterClassName(callNode);

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[48]++;}

    return callArg.getNext().getQualifiedName();
  }

  @Override
  public void applySingletonGetter(FunctionType functionType,
      FunctionType getterType, ObjectType objectType) {
    super.applySingletonGetter(functionType, getterType, objectType);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[62]++;
    functionType.defineDeclaredProperty("getInstance", getterType,
        functionType.getSource());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[63]++;
    functionType.defineDeclaredProperty("instance_", objectType,
        functionType.getSource());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[64]++;
  }

  @Override
  public String getGlobalObject() {
    return "goog.global";
  }

  private final Set<String> propertyTestFunctions = ImmutableSet.of(
      "goog.isDef", "goog.isNull", "goog.isDefAndNotNull",
      "goog.isString", "goog.isNumber", "goog.isBoolean",
      "goog.isFunction", "goog.isArray", "goog.isObject");
  {
    CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[65]++;
  }

  @Override
  public boolean isPropertyTestFunction(Node call) {
    Preconditions.checkArgument(call.isCall());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[66]++;
    return propertyTestFunctions.contains(
        call.getFirstChild().getQualifiedName()) ||
        super.isPropertyTestFunction(call);
  }

  @Override
  public ObjectLiteralCast getObjectLiteralCast(Node callNode) {
    Preconditions.checkArgument(callNode.isCall());
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[67]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[68]++;
    ObjectLiteralCast proxyCast = super.getObjectLiteralCast(callNode);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[69]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((proxyCast != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[49]++;
      return proxyCast;

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[50]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[70]++;

    Node callName = callNode.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[71]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 (("goog.reflect.object".equals(callName.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((callNode.getChildCount() != 3) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[51]++;
      return null;

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[52]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[72]++;

    Node typeNode = callName.getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[73]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((typeNode.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[53]++;
      return null;

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[54]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[74]++;

    Node objectNode = typeNode.getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[75]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((objectNode.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[55]++;
      return new ObjectLiteralCast(null, null, OBJECTLIT_EXPECTED);

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[56]++;}

    return new ObjectLiteralCast(
        typeNode.getQualifiedName(), typeNode.getNext(), null);
  }

  @Override
  public boolean isOptionalParameter(Node parameter) {
    return false;
  }

  @Override
  public boolean isVarArgsParameter(Node parameter) {
    return false;
  }

  @Override
  public boolean isPrivate(String name) {
    return false;
  }

  @Override
  public Collection<AssertionFunctionSpec> getAssertionFunctions() {
    return ImmutableList.<AssertionFunctionSpec>of(
        new AssertionFunctionSpec("goog.asserts.assert"),
        new AssertionFunctionSpec("goog.asserts.assertNumber",
            JSTypeNative.NUMBER_TYPE),
        new AssertionFunctionSpec("goog.asserts.assertString",
            JSTypeNative.STRING_TYPE),
        new AssertionFunctionSpec("goog.asserts.assertFunction",
            JSTypeNative.FUNCTION_INSTANCE_TYPE),
        new AssertionFunctionSpec("goog.asserts.assertObject",
            JSTypeNative.OBJECT_TYPE),
        new AssertionFunctionSpec("goog.asserts.assertArray",
            JSTypeNative.ARRAY_TYPE),
        new AssertInstanceofSpec("goog.asserts.assertInstanceof")
    );
  }

  @Override
  public Bind describeFunctionBind(Node n, boolean useTypeInfo) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[76]++;
    Bind result = super.describeFunctionBind(n, useTypeInfo);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[77]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[57]++;
      return result;

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[58]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[78]++;
int CodeCoverConditionCoverageHelper_C31;

    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[59]++;
      return null;

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[60]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[79]++;

    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[80]++;
    String name = callTarget.getQualifiedName();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[81]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[61]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[82]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((name.equals("goog.bind")) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((name.equals("goog$bind")) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[63]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[83]++;
        // goog.bind(fn, self, args...);
        Node fn = callTarget.getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[84]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((fn == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[65]++;
          return null;

        } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[66]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[85]++;
        Node thisValue = safeNext(fn);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[86]++;
        Node parameters = safeNext(thisValue);
        return new Bind(fn, thisValue, parameters);

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[64]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[87]++;
int CodeCoverConditionCoverageHelper_C35;

      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((name.equals("goog.partial")) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((name.equals("goog$partial")) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[67]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[88]++;
        // goog.partial(fn, args...);
        Node fn = callTarget.getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[89]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((fn == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[69]++;
          return null;

        } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[70]++;}
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[90]++;
        Node thisValue = null;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[91]++;
        Node parameters = safeNext(fn);
        return new Bind(fn, thisValue, parameters);

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[68]++;}

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[62]++;}

    return null;
  }

  @Override
  public Collection<String> getIndirectlyDeclaredProperties() {
    return indirectlyDeclaredProperties;
  }

  private Node safeNext(Node n) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[92]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[71]++;
      return n.getNext();

    } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[72]++;}
    return null;
  }

  /**
   * A function that will throw an exception when if the value is not
   * an instanceof a specific type.
   */
  public static class AssertInstanceofSpec extends AssertionFunctionSpec {
    public AssertInstanceofSpec(String functionName) {
      super(functionName, JSTypeNative.OBJECT_TYPE);
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[93]++;
    }

    /**
     * Returns the type for a type assertion, or null if the function asserts
     * that the node must not be null or undefined.
     */
    @Override
    public JSType getAssertedType(Node call, JSTypeRegistry registry) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[94]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((call.getChildCount() > 2) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[73]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[95]++;
        Node constructor = call.getFirstChild().getNext().getNext();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[96]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[75]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[97]++;
          JSType ownerType = constructor.getJSType();
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[98]++;
int CodeCoverConditionCoverageHelper_C40;
          if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((ownerType != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((ownerType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((ownerType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) || true)) || (CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) && false)) {
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[77]++;
CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.statements[99]++;
            FunctionType functionType = ((FunctionType) ownerType);
            return functionType.getInstanceType();

          } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[78]++;}

        } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[76]++;}

      } else {
  CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t.branches[74]++;}
      return super.getAssertedType(call, registry);
    }
  }


}

class CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t ());
  }
    public static long[] statements = new long[100];
    public static long[] branches = new long[79];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ClosureCodingConvention.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,2,1,2,2,1,1,1,3,1,2,1,1,1,1,1,2,1,2,1,1,1,1,3};
    for (int i = 1; i <= 40; i++) {
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

  public CodeCoverCoverageCounter$unslygbv1gjc46f72604oxkwol63a0x06crmghbih5t () {
    super("com.google.javascript.jscomp.ClosureCodingConvention.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 99; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 78; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ClosureCodingConvention.java");
      for (int i = 1; i <= 99; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 78; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 40; i++) {
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

