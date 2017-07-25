/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Bob Jervis
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino.jstype;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

import java.util.Set;

/**
 * The object type represents instances of JavaScript objects such as
 * {@code Object}, {@code Date}, {@code Function}.<p>
 *
 * Objects in JavaScript are unordered collections of properties.
 * Each property consists of a name, a value and a set of attributes.<p>
 *
 * Each instance has an implicit prototype property ({@code [[Prototype]]})
 * pointing to an object instance, which itself has an implicit property, thus
 * forming a chain.<p>
 *
 * A class begins life with no name.  Later, a name may be provided once it
 * can be inferred.  Note that the name in this case is strictly for
 * debugging purposes.  Looking up type name references goes through the
 * {@link JSTypeRegistry}.<p>
 */
class PrototypeObjectType extends ObjectType {
  static {
    CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[1]++;
  }

  private final String className;
  private final PropertyMap properties;
  private final boolean nativeType;

  // NOTE(nicksantos): The implicit prototype can change over time.
  // Modeling this is a bear. Always call getImplicitPrototype(), because
  // some subclasses override this to do special resolution handling.
  private ObjectType implicitPrototypeFallback;

  // If this is a function prototype, then this is the owner.
  // A PrototypeObjectType can only be the prototype of one function. If we try
  // to do this for multiple functions, then we'll have to create a new one.
  private FunctionType ownerFunction = null;
  {
    CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[2]++;
  }

  // Whether the toString representation of this should be pretty-printed,
  // by printing all properties.
  private boolean prettyPrint = false;
  {
    CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[3]++;
  }

  private static final int MAX_PRETTY_PRINTED_PROPERTIES = 4;
  static {
    CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[4]++;
  }

  /**
   * Creates an object type.
   *
   * @param className the name of the class.  May be {@code null} to
   *        denote an anonymous class.
   *
   * @param implicitPrototype the implicit prototype
   *        (a.k.a. {@code [[Prototype]]}) as defined by ECMA-262. If the
   *        implicit prototype is {@code null} the implicit prototype will be
   *        set to the {@link JSTypeNative#OBJECT_TYPE}.
   */
  PrototypeObjectType(JSTypeRegistry registry, String className,
      ObjectType implicitPrototype) {
    this(registry, className, implicitPrototype, false, null);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[5]++;
  }

  /**
   * Creates an object type, allowing specification of the implicit prototype,
   * whether the object is native, and any templatized types.
   */
  PrototypeObjectType(JSTypeRegistry registry, String className,
      ObjectType implicitPrototype, boolean nativeType,
      TemplateTypeMap templateTypeMap) {
    super(registry, templateTypeMap);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[6]++;
    this.properties = new PropertyMap();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[7]++;
    this.properties.setParentSource(this);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[8]++;

    this.className = className;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[9]++;
    this.nativeType = nativeType;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[10]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((nativeType) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((implicitPrototype != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[1]++;
      setImplicitPrototype(implicitPrototype);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[12]++;

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[2]++;
      setImplicitPrototype(
          registry.getNativeObjectType(JSTypeNative.OBJECT_TYPE));
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[13]++;
    }
  }

  @Override
  PropertyMap getPropertyMap() {
    return properties;
  }

  @Override
  boolean defineProperty(String name, JSType type, boolean inferred,
      Node propertyNode) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((hasOwnDeclaredProperty(name)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[4]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[15]++;
    Property newProp = new Property(
        name, type, inferred, propertyNode);
    properties.putProperty(name, newProp);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[16]++;
    return true;
  }

  @Override
  public boolean removeProperty(String name) {
    return properties.removeProperty(name);
  }

  @Override
  public void setPropertyJSDocInfo(String propertyName, JSDocInfo info) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[5]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((properties.getOwnProperty(propertyName) == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[7]++;
        // If docInfo was attached, but the type of the property
        // was not defined anywhere, then we consider this an explicit
        // declaration of the property.
        defineInferredProperty(propertyName, getPropertyType(propertyName),
            null);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[19]++;

      } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[8]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[20]++;

      // The prototype property is not represented as a normal Property.
      // We probably don't want to attach any JSDoc to it anyway.
      Property property = properties.getOwnProperty(propertyName);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((property != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[9]++;
        property.setJSDocInfo(info);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[22]++;

      } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[10]++;}

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[6]++;}
  }

  @Override
  public boolean matchesNumberContext() {
    return isNumberObjectType() || isDateType() || isBooleanObjectType() ||
        isStringObjectType() || hasOverridenNativeProperty("valueOf");
  }

  @Override
  public boolean matchesStringContext() {
    return isTheObjectType() || isStringObjectType() || isDateType() ||
        isRegexpType() || isArrayType() || isNumberObjectType() ||
        isBooleanObjectType() || hasOverridenNativeProperty("toString");
  }

  /**
   * Given the name of a native object property, checks whether the property is
   * present on the object and different from the native one.
   */
  private boolean hasOverridenNativeProperty(String propertyName) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[11]++;
      return false;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[12]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[24]++;

    JSType propertyType = getPropertyType(propertyName);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[25]++;
    ObjectType nativeType =
        isFunctionType() ?
        registry.getNativeObjectType(JSTypeNative.FUNCTION_PROTOTYPE) :
        registry.getNativeObjectType(JSTypeNative.OBJECT_PROTOTYPE);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[26]++;
    JSType nativePropertyType = nativeType.getPropertyType(propertyName);
    return propertyType != nativePropertyType;
  }

  @Override
  public JSType unboxesTo() {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isStringObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[13]++;
      return getNativeType(JSTypeNative.STRING_TYPE);

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[14]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[28]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isBooleanObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[15]++;
      return getNativeType(JSTypeNative.BOOLEAN_TYPE);

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[16]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[29]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isNumberObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[17]++;
      return getNativeType(JSTypeNative.NUMBER_TYPE);

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[18]++;
      return super.unboxesTo();
    }
}
}
  }

  @Override
  public boolean matchesObjectContext() {
    return true;
  }

  @Override
  public boolean canBeCalled() {
    return isRegexpType();
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[19]++;
      return getReferenceName();

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[20]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[31]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((prettyPrint) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[21]++;
      // Don't pretty print recursively.
      prettyPrint = false;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[32]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[33]++;

      // Use a tree set so that the properties are sorted.
      Set<String> propertyNames = Sets.newTreeSet();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[34]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[1]++;


int CodeCoverConditionCoverageHelper_C12;
      for (ObjectType current = this;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((current.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((propertyNames.size() <= MAX_PRETTY_PRINTED_PROPERTIES) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false);
           current = current.getImplicitPrototype()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[1]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[2]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[3]++;
}
        propertyNames.addAll(current.getOwnPropertyNames());
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[35]++;
      }
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[36]++;

      StringBuilder sb = new StringBuilder();
      sb.append("{");
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[37]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[38]++;

      int i = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[39]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[4]++;


      for (String property : propertyNames) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[4]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[5]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[6]++;
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[23]++;
          sb.append(", ");
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[41]++;

        } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[24]++;}

        sb.append(property);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[42]++;
        sb.append(": ");
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[43]++;
        sb.append(getPropertyType(property).toStringHelper(forAnnotations));
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[44]++;

        ++i;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[45]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[46]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((forAnnotations) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i == MAX_PRETTY_PRINTED_PROPERTIES) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[25]++;
          sb.append(", ...");
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[47]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[48]++;
          break;

        } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[26]++;}
      }

      sb.append("}");
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[49]++;

      prettyPrint = true;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[50]++;
      return sb.toString();

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[22]++;
      return forAnnotations ? "?" : "{...}";
    }
}
  }

  void setPrettyPrint(boolean prettyPrint) {
    this.prettyPrint = prettyPrint;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[51]++;
  }

  boolean isPrettyPrint() {
    return prettyPrint;
  }

  @Override
  public FunctionType getConstructor() {
    return null;
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return implicitPrototypeFallback;
  }

  /**
   * This should only be reset on the FunctionPrototypeType, only to fix an
   * incorrectly established prototype chain due to the user having a mismatch
   * in super class declaration, and only before properties on that type are
   * processed.
   */
  final void setImplicitPrototype(ObjectType implicitPrototype) {
    checkState(!hasCachedValues());
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[52]++;
    this.implicitPrototypeFallback = implicitPrototype;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[53]++;
  }

  @Override
  public String getReferenceName() {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((className != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[27]++;
      return className;

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[28]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[55]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((ownerFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[29]++;
      return ownerFunction.getReferenceName() + ".prototype";

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[30]++;
      return null;
    }
}
  }

  @Override
  public boolean hasReferenceName() {
    return className != null || ownerFunction != null;
  }

  @Override
  public boolean isSubtype(JSType that) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[56]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((JSType.isSubtypeHelper(this, that)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[31]++;
      return true;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[32]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;

    // Union types
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[33]++;
      // The static {@code JSType.isSubtype} check already decomposed
      // union types, so we don't need to check those again.
      return false;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[34]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[58]++;
int CodeCoverConditionCoverageHelper_C19;

    // record types
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((that.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[35]++;
      return RecordType.isSubtype(this, that.toMaybeRecordType());

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[36]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[59]++;

    // Interfaces
    // Find all the interfaces implemented by this class and compare each one
    // to the interface instance.
    ObjectType thatObj = that.toObjectType();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[60]++;
    FunctionType thatCtor = thatObj == null ? null : thatObj.getConstructor();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[61]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getConstructor().isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[37]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[62]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[7]++;


      for (ObjectType thisInterface : getCtorExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[7]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[8]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[9]++;
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[63]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((thisInterface.isSubtype(that)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[39]++;
          return true;

        } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[40]++;}
      }

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[38]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[64]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((thatCtor != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((thatCtor.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[41]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[65]++;
      Iterable<ObjectType> thisInterfaces = getCtorImplementedInterfaces();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[66]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[10]++;


      for (ObjectType thisInterface : thisInterfaces) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[10]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[11]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[12]++;
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[67]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((thisInterface.isSubtype(that)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[43]++;
          return true;

        } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[44]++;}
      }

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[42]++;}
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[68]++;
int CodeCoverConditionCoverageHelper_C24;

    // other prototype based objects
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((implicitPrototypeChainIsUnknown()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[45]++;
      // If unsure, say 'yes', to avoid spurious warnings.
      // TODO(user): resolve the prototype chain completely in all cases,
      // to avoid guessing.
      return true;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[46]++;}
    return thatObj != null && isImplicitPrototype(thatObj);
  }

  private boolean implicitPrototypeChainIsUnknown() {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[69]++;
    ObjectType p = getImplicitPrototype();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[70]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[13]++;


int CodeCoverConditionCoverageHelper_C25;
    while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[13]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[14]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[15]++;
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[71]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((p.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[47]++;
        return true;

      } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[48]++;}
      p = p.getImplicitPrototype();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[72]++;
    }
    return false;
  }

  @Override
  public boolean hasCachedValues() {
    return super.hasCachedValues();
  }

  /** Whether this is a built-in object. */
  @Override
  public boolean isNativeObjectType() {
    return nativeType;
  }

  @Override
  void setOwnerFunction(FunctionType type) {
    Preconditions.checkState(ownerFunction == null || type == null);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[73]++;
    ownerFunction = type;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[74]++;
  }

  @Override
  public FunctionType getOwnerFunction() {
    return ownerFunction;
  }

  @Override
  public Iterable<ObjectType> getCtorImplementedInterfaces() {
    return isFunctionPrototypeType()
        ? getOwnerFunction().getImplementedInterfaces()
        : ImmutableList.<ObjectType>of();
  }

  @Override
  public Iterable<ObjectType> getCtorExtendedInterfaces() {
    return isFunctionPrototypeType()
        ? getOwnerFunction().getExtendedInterfaces()
        : ImmutableList.<ObjectType>of();
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    setResolvedTypeInternal(this);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[75]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[76]++;

    ObjectType implicitPrototype = getImplicitPrototype();
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((implicitPrototype != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[49]++;
      implicitPrototypeFallback =
          (ObjectType) implicitPrototype.resolve(t, scope);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[78]++;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[50]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[79]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[16]++;


    for (Property prop : properties.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[16]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[17]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[18]++;
}
      prop.setType(safeResolve(prop.getType(), t, scope));
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[80]++;
    }
    return this;
  }

  @Override
  public void matchConstraint(JSType constraint) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[81]++;
int CodeCoverConditionCoverageHelper_C28;
    // We only want to match constraints on anonymous types.
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[51]++;
      return;

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[52]++;}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[82]++;
int CodeCoverConditionCoverageHelper_C29;

    // Handle the case where the constraint object is a record type.
    //
    // param constraint {{prop: (number|undefined)}}
    // function f(constraint) {}
    // f({});
    //
    // We want to modify the object literal to match the constraint, by
    // taking any each property on the record and trying to match
    // properties on this object.
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((constraint.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[53]++;
      matchRecordTypeConstraint(constraint.toObjectType());
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[83]++;

    } else {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[54]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[84]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((constraint.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[55]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[85]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[19]++;


      for (JSType alt : constraint.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[19]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[20]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[21]++;
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[86]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((alt.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[57]++;
          matchRecordTypeConstraint(alt.toObjectType());
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[87]++;

        } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[58]++;}
      }

    } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[56]++;}
}
  }

  public void matchRecordTypeConstraint(ObjectType constraintObj) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[88]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[22]++;


    for (String prop : constraintObj.getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[22]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[23]--;
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.loops[24]++;
}
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[89]++;
      JSType propType = constraintObj.getPropertyType(prop);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[90]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isPropertyTypeDeclared(prop)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[59]++;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[91]++;
        JSType typeToInfer = propType;
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[92]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((hasProperty(prop)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[61]++;
          typeToInfer = getNativeType(JSTypeNative.VOID_TYPE)
              .getLeastSupertype(propType);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[93]++;

        } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[62]++;}
        defineInferredProperty(prop, typeToInfer, null);
CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.statements[94]++;

      } else {
  CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9.branches[60]++;}
    }
  }

}

class CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9 ());
  }
    public static long[] statements = new long[95];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.PrototypeObjectType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1,1,1,3,1,2,1,1,1,1,1,2,1,2,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 33; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$ijhgqjppqqclvgkffir3l3hmaen18r08ty7a9 () {
    super("com.google.javascript.rhino.jstype.PrototypeObjectType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 94; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.PrototypeObjectType.java");
      for (int i = 1; i <= 94; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

