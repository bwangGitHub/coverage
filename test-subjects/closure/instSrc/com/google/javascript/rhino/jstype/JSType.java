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

import static com.google.javascript.rhino.jstype.TernaryValue.UNKNOWN;

import com.google.common.base.Predicate;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.jstype.JSTypeRegistry.ResolveMode;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Represents JavaScript value types.<p>
 *
 * Types are split into two separate families: value types and object types.
 *
 * A special {@link UnknownType} exists to represent a wildcard type on which
 * no information can be gathered. In particular, it can assign to everyone,
 * is a subtype of everyone (and everyone is a subtype of it).<p>
 *
 * If you remove the {@link UnknownType}, the set of types in the type system
 * forms a lattice with the {@link #isSubtype} relation defining the partial
 * order of types. All types are united at the top of the lattice by the
 * {@link AllType} and at the bottom by the {@link NoType}.<p>
 *
 */
public abstract class JSType implements Serializable {
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[1]++;
  }

  private boolean resolved = false;
  {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[2]++;
  }
  private JSType resolveResult = null;
  {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[3]++;
  }
  protected final TemplateTypeMap templateTypeMap;

  private boolean inTemplatedCheckVisit = false;
  {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[4]++;
  }
  private static final CanCastToVisitor CAN_CAST_TO_VISITOR =
      new CanCastToVisitor();
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[5]++;
  }

  public static final String UNKNOWN_NAME =
      "Unknown class name";
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[6]++;
  }

  public static final String NOT_A_CLASS =
      "Not declared as a constructor";
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[7]++;
  }

  public static final String NOT_A_TYPE =
      "Not declared as a type name";
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[8]++;
  }

  public static final String EMPTY_TYPE_COMPONENT =
      "Named type with empty name component";
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[9]++;
  }

  /**
   * Total ordering on types based on their textual representation.
   * This is used to have a deterministic output of the toString
   * method of the union type since this output is used in tests.
   */
  static final Comparator<JSType> ALPHA = new Comparator<JSType>() {
    @Override
    public int compare(JSType t1, JSType t2) {
      return t1.toString().compareTo(t2.toString());
    }
  };
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[10]++;
  }

  // A flag set on enum definition tree nodes
  public static final int ENUMDECL = 1;
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[11]++;
  }
  public static final int NOT_ENUMDECL = 0;
  static {
    CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[12]++;
  }

  final JSTypeRegistry registry;

  JSType(JSTypeRegistry registry) {
    this(registry, null);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[13]++;
  }

  JSType(JSTypeRegistry registry, TemplateTypeMap templateTypeMap) {
    this.registry = registry;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[14]++;

    this.templateTypeMap = templateTypeMap == null ?
        registry.createTemplateTypeMap(null, null) : templateTypeMap;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[15]++;
  }

  /**
   * Utility method for less verbose code.
   */
  JSType getNativeType(JSTypeNative typeId) {
    return registry.getNativeType(typeId);
  }

  /**
   * Gets the docInfo for this type. By default, documentation cannot be
   * attached to arbitrary types. This must be overridden for
   * programmer-defined types.
   */
  public JSDocInfo getJSDocInfo() {
    return null;
  }

  /**
   * Returns a user meaningful label for the JSType instance.  For example,
   * Functions and Enums will return their declaration name (if they have one).
   * Some types will not have a meaningful display name.  Calls to
   * hasDisplayName() will return true IFF getDisplayName() will return null
   * or a zero length string.
   *
   * @return the display name of the type, or null if one is not available
   */
  public String getDisplayName() {
    return null;
  }

  /**
   * @return true if the JSType has a user meaningful label.
   */
  public boolean hasDisplayName() {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[16]++;
    String displayName = getDisplayName();
    return displayName != null && !displayName.isEmpty();
  }

  /**
   * Checks whether the property is present on the object.
   * @param pname The property name.
   */
  public boolean hasProperty(String pname) {
    return false;
  }

  public boolean isNoType() {
    return false;
  }

  public boolean isNoResolvedType() {
    return false;
  }

  public boolean isNoObjectType() {
    return false;
  }

  public final boolean isEmptyType() {
    return isNoType() || isNoObjectType() || isNoResolvedType() ||
        (registry.getNativeFunctionType(
             JSTypeNative.LEAST_FUNCTION_TYPE) == this);
  }

  public boolean isNumberObjectType() {
    return false;
  }

  public boolean isNumberValueType() {
    return false;
  }

  /** Whether this is the prototype of a function. */
  public boolean isFunctionPrototypeType() {
    return false;
  }

  public boolean isStringObjectType() {
    return false;
  }

  boolean isTheObjectType() {
    return false;
  }

  public boolean isStringValueType() {
    return false;
  }

  /**
   * Tests whether the type is a string (value or Object).
   * @return {@code this &lt;: (String, string)}
   */
  public final boolean isString() {
    return isSubtype(
        getNativeType(JSTypeNative.STRING_VALUE_OR_OBJECT_TYPE));
  }

  /**
   * Tests whether the type is a number (value or Object).
   * @return {@code this &lt;: (Number, number)}
   */
  public final boolean isNumber() {
    return isSubtype(
        getNativeType(JSTypeNative.NUMBER_VALUE_OR_OBJECT_TYPE));
  }

  public boolean isArrayType() {
    return false;
  }

  public boolean isBooleanObjectType() {
    return false;
  }

  public boolean isBooleanValueType() {
    return false;
  }

  public boolean isRegexpType() {
    return false;
  }

  public boolean isDateType() {
    return false;
  }

  public boolean isNullType() {
    return false;
  }

  public boolean isVoidType() {
    return false;
  }

  public boolean isAllType() {
    return false;
  }

  public boolean isUnknownType() {
    return false;
  }

  public boolean isCheckedUnknownType() {
    return false;
  }

  public final boolean isUnionType() {
    return toMaybeUnionType() != null;
  }

  /**
   * Returns true iff {@code this} can be a {@code struct}.
   * UnionType overrides the method, assume {@code this} is not a union here.
   */
  public boolean isStruct() {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isObject()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[1]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[18]++;
      ObjectType objType = toObjectType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[19]++;
      ObjectType iproto = objType.getImplicitPrototype();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
      // For the case when a @struct constructor is assigned to a function's
      // prototype property
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((iproto != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iproto.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[3]++;
        return true;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[4]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[21]++;
      FunctionType ctor = objType.getConstructor();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
      // This test is true for object literals
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ctor == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[5]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[23]++;
        JSDocInfo info = objType.getJSDocInfo();
        return info != null && info.makesStructs();

      } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[6]++;
        return ctor.makesStructs();
      }

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[2]++;}
    return false;
  }

  /**
   * Returns true iff {@code this} can be a {@code dict}.
   * UnionType overrides the method, assume {@code this} is not a union here.
   */
  public boolean isDict() {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isObject()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[7]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[25]++;
      ObjectType objType = toObjectType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[26]++;
      ObjectType iproto = objType.getImplicitPrototype();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
      // For the case when a @dict constructor is assigned to a function's
      // prototype property
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((iproto != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((iproto.isDict()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[9]++;
        return true;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[10]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[28]++;
      FunctionType ctor = objType.getConstructor();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
      // This test is true for object literals
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ctor == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[11]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[30]++;
        JSDocInfo info = objType.getJSDocInfo();
        return info != null && info.makesDicts();

      } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[12]++;
        return ctor.makesDicts();
      }

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[8]++;}
    return false;
  }

  /**
   * Downcasts this to a UnionType, or returns null if this is not a UnionType.
   *
   * Named in honor of Haskell's Maybe type constructor.
   */
  public UnionType toMaybeUnionType() {
    return null;
  }

  /** Returns true if this is a global this type. */
  public final boolean isGlobalThisType() {
    return this == registry.getNativeType(JSTypeNative.GLOBAL_THIS);
  }

  /** Returns true if toMaybeFunctionType returns a non-null FunctionType. */
  public final boolean isFunctionType() {
    return toMaybeFunctionType() != null;
  }

  /**
   * Downcasts this to a FunctionType, or returns null if this is not
   * a function.
   *
   * For the purposes of this function, we define a MaybeFunctionType as any
   * type in the sub-lattice
   * { x | LEAST_FUNCTION_TYPE <= x <= GREATEST_FUNCTION_TYPE }
   * This definition excludes bottom types like NoType and NoObjectType.
   *
   * This definition is somewhat arbitrary and axiomatic, but this is the
   * definition that makes the most sense for the most callers.
   */
  public FunctionType toMaybeFunctionType() {
    return null;
  }

  /**
   * Null-safe version of toMaybeFunctionType().
   */
  public static FunctionType toMaybeFunctionType(JSType type) {
    return type == null ? null : type.toMaybeFunctionType();
  }

  public final boolean isEnumElementType() {
    return toMaybeEnumElementType() != null;
  }

  /**
   * Downcasts this to an EnumElementType, or returns null if this is not an EnumElementType.
   */
  public EnumElementType toMaybeEnumElementType() {
    return null;
  }

  public boolean isEnumType() {
    return toMaybeEnumType() != null;
  }

  /**
   * Downcasts this to an EnumType, or returns null if this is not an EnumType.
   */
  public EnumType toMaybeEnumType() {
    return null;
  }

  boolean isNamedType() {
    return false;
  }

  public boolean isRecordType() {
    return toMaybeRecordType() != null;
  }

  /**
   * Downcasts this to a RecordType, or returns null if this is not
   * a RecordType.
   */
  RecordType toMaybeRecordType() {
    return null;
  }

  public final boolean isTemplatizedType() {
    return toMaybeTemplatizedType() != null;
  }

  /**
   * Downcasts this to a TemplatizedType, or returns null if this is not
   * a function.
   */
  public TemplatizedType toMaybeTemplatizedType() {
    return null;
  }

  /**
   * Null-safe version of toMaybeTemplatizedType().
   */
  public static TemplatizedType toMaybeTemplatizedType(JSType type) {
    return type == null ? null : type.toMaybeTemplatizedType();
  }

  public final boolean isTemplateType() {
    return toMaybeTemplateType() != null;
  }

  /**
   * Downcasts this to a TemplateType, or returns null if this is not
   * a function.
   */
  public TemplateType toMaybeTemplateType() {
    return null;
  }

  /**
   * Null-safe version of toMaybeTemplateType().
   */
  public static TemplateType toMaybeTemplateType(JSType type) {
    return type == null ? null : type.toMaybeTemplateType();
  }

  public boolean hasAnyTemplateTypes() {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.inTemplatedCheckVisit) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[13]++;
      this.inTemplatedCheckVisit = true;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[32]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[33]++;
      boolean result = hasAnyTemplateTypesInternal();
      this.inTemplatedCheckVisit = false;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[34]++;
      return result;

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[14]++;
      // prevent infinite recursion, this is "not yet".
      return false;
    }
  }

  boolean hasAnyTemplateTypesInternal() {
    return templateTypeMap.hasAnyTemplateTypesInternal();
  }

  /**
   * Returns the template type map associated with this type.
   */
  public TemplateTypeMap getTemplateTypeMap() {
    return templateTypeMap;
  }

  /**
   * Tests whether this type is an {@code Object}, or any subtype thereof.
   * @return {@code this &lt;: Object}
   */
  public boolean isObject() {
    return false;
  }

  /**
   * Whether this type is a {@link FunctionType} that is a constructor or a
   * named type that points to such a type.
   */
  public boolean isConstructor() {
    return false;
  }

  /**
   * Whether this type is a nominal type (a named instance object or
   * a named enum).
   */
  public boolean isNominalType() {
    return false;
  }

  /**
   * Whether this type is the original constructor of a nominal type.
   * Does not include structural constructors.
   */
  public final boolean isNominalConstructor() {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[15]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[36]++;
      FunctionType fn = toMaybeFunctionType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((fn == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[17]++;
        return false;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[18]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;

      // Programmer-defined constructors will have a link
      // back to the original function in the source tree.
      // Structural constructors will not.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((fn.getSource() != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[19]++;
        return true;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[20]++;}

      // Native constructors are always nominal.
      return fn.isNativeObjectType();

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[16]++;}
    return false;
  }

  /**
   * Whether this type is an Instance object of some constructor.
   * Does not necessarily mean this is an {@link InstanceObjectType}.
   */
  public boolean isInstanceType() {
    return false;
  }

  /**
   * Whether this type is a {@link FunctionType} that is an interface or a named
   * type that points to such a type.
   */
  public boolean isInterface() {
    return false;
  }

  /**
   * Whether this type is a {@link FunctionType} that is an ordinary function or
   * a named type that points to such a type.
   */
  public boolean isOrdinaryFunction() {
    return false;
  }

  /**
   * Checks if two types are equivalent.
   */
  public final boolean isEquivalentTo(JSType that) {
    return checkEquivalenceHelper(that, EquivalenceMethod.IDENTITY);
  }

  /**
   * Checks if two types are invariant.
   * @see EquivalenceMethod
   */
  public final boolean isInvariant(JSType that) {
    return checkEquivalenceHelper(that, EquivalenceMethod.INVARIANT);
  }

  /**
   * Whether this type is meaningfully different from {@code that} type for
   * the purposes of data flow analysis.
   *
   * This is a trickier check than pure equality, because it has to properly
   * handle unknown types. See {@code EquivalenceMethod} for more info.
   *
   * @see <a href="http://www.youtube.com/watch?v=_RpSv3HjpEw">Unknown unknowns</a>
   */
  public final boolean differsFrom(JSType that) {
    return !checkEquivalenceHelper(that, EquivalenceMethod.DATA_FLOW);
  }

  /**
   * An equivalence visitor.
   */
  boolean checkEquivalenceHelper(JSType that, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[39]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this == that) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[21]++;
      return true;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[22]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[40]++;

    boolean thisUnknown = isUnknownType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[41]++;
    boolean thatUnknown = that.isUnknownType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[42]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((thisUnknown) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((thatUnknown) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[23]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((eqMethod == EquivalenceMethod.INVARIANT) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[25]++;
        // If we're checking for invariance, the unknown type is invariant
        // with everyone.
        return true;

      } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[26]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[44]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((eqMethod == EquivalenceMethod.DATA_FLOW) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[27]++;
        // If we're checking data flow, then two types are the same if they're
        // both unknown.
        return thisUnknown && thatUnknown;

      } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[28]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[45]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (128)) == 0 || true) &&
 ((thisUnknown) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((thatUnknown) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((isNominalType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 ^ 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((that.isNominalType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 4) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 4) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[29]++;
        // If they're both unknown, but one is a nominal type and the other
        // is not, then we should fail out immediately. This ensures that
        // we won't unbox the unknowns further down.
        return false;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[30]++;}
}
}

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[24]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[46]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[31]++;
      return toMaybeUnionType().checkUnionEquivalenceHelper(
          that.toMaybeUnionType(), eqMethod);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[32]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[47]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((that.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[33]++;
      return toMaybeFunctionType().checkFunctionEquivalenceHelper(
          that.toMaybeFunctionType(), eqMethod);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[34]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((that.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[35]++;
      return toMaybeRecordType().checkRecordEquivalenceHelper(
          that.toMaybeRecordType(), eqMethod);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[36]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[49]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((getTemplateTypeMap().checkEquivalenceHelper(
        that.getTemplateTypeMap(), eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[37]++;
      return false;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[38]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[50]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((isNominalType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((that.isNominalType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[39]++;
      return toObjectType().getReferenceName().equals(
          that.toObjectType().getReferenceName());

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[40]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[51]++;
int CodeCoverConditionCoverageHelper_C21;

    // Unbox other proxies.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this instanceof ProxyObjectType) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[41]++;
      return ((ProxyObjectType) this)
          .getReferencedTypeInternal().checkEquivalenceHelper(
              that, eqMethod);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[42]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[52]++;
int CodeCoverConditionCoverageHelper_C22;

    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((that instanceof ProxyObjectType) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[43]++;
      return checkEquivalenceHelper(
          ((ProxyObjectType) that).getReferencedTypeInternal(),
          eqMethod);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[44]++;}

    // Relies on the fact that for the base {@link JSType}, only one
    // instance of each sub-type will ever be created in a given registry, so
    // there is no need to verify members. If the object pointers are not
    // identical, then the type member must be different.
    return this == that;
  }

  public static boolean isEquivalent(JSType typeA, JSType typeB) {
    return (typeA == null || typeB == null) ?
        typeA == typeB : typeA.isEquivalentTo(typeB);
  }

  @Override
  public boolean equals(Object jsType) {
    return (jsType instanceof JSType) ?
        isEquivalentTo((JSType) jsType) : false;
  }

  @Override
  public int hashCode() {
    return System.identityHashCode(this);
  }

  /**
   * This predicate is used to test whether a given type can appear in a
   * 'Int32' context.  This context includes, for example, the operands of a
   * bitwise or operator.  Since we do not currently support integer types,
   * this is a synonym for {@code Number}.
   */
  public final boolean matchesInt32Context() {
    return matchesNumberContext();
  }

  /**
   * This predicate is used to test whether a given type can appear in a
   * 'Uint32' context.  This context includes the right-hand operand of a shift
   * operator.
   */
  public final boolean matchesUint32Context() {
    return matchesNumberContext();
  }

  /**
   * This predicate is used to test whether a given type can appear in a
   * numeric context, such as an operand of a multiply operator.
   */
  public boolean matchesNumberContext() {
    return false;
  }

  /**
   * This predicate is used to test whether a given type can appear in a
   * {@code String} context, such as an operand of a string concat (+) operator.
   *
   * All types have at least the potential for converting to {@code String}.
   * When we add externally defined types, such as a browser OM, we may choose
   * to add types that do not automatically convert to {@code String}.
   */
  public boolean matchesStringContext() {
    return false;
  }

  /**
   * This predicate is used to test whether a given type can appear in an
   * {@code Object} context, such as the expression in a with statement.
   *
   * Most types we will encounter, except notably {@code null}, have at least
   * the potential for converting to {@code Object}.  Host defined objects can
   * get peculiar.
   */
  public boolean matchesObjectContext() {
    return false;
  }

  /**
   * Coerces this type to an Object type, then gets the type of the property
   * whose name is given.
   *
   * Unlike {@link ObjectType#getPropertyType}, returns null if the property
   * is not found.
   *
   * @return The property's type. {@code null} if the current type cannot
   *     have properties, or if the type is not found.
   */
  public JSType findPropertyType(String propertyName) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[53]++;
    ObjectType autoboxObjType = ObjectType.cast(autoboxesTo());
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[54]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((autoboxObjType != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[45]++;
      return autoboxObjType.findPropertyType(propertyName);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[46]++;}

    return null;
  }

  /**
   * This predicate is used to test whether a given type can be used as the
   * 'function' in a function call.
   *
   * @return {@code true} if this type might be callable.
   */
  public boolean canBeCalled() {
    return false;
  }

  /**
   * Tests whether values of {@code this} type can be safely assigned
   * to values of {@code that} type.<p>
   *
   * The default implementation verifies that {@code this} is a subtype
   * of {@code that}.<p>
   */
  public boolean canCastTo(JSType that) {
    return this.visit(CAN_CAST_TO_VISITOR, that);
  }

  /**
   * Turn a scalar type to the corresponding object type.
   *
   * @return the auto-boxed type or {@code null} if this type is not a scalar.
   */
  public JSType autoboxesTo() {
    return null;
  }

  /**
   * Turn an object type to its corresponding scalar type.
   *
   * @return the unboxed type or {@code null} if this type does not unbox.
   */
  public JSType unboxesTo() {
    return null;
  }

  /**
   * Casts this to an ObjectType, or returns null if this is not an ObjectType.
   * If this is a scalar type, it will *not* be converted to an object type.
   * If you want to simulate JS autoboxing or dereferencing, you should use
   * autoboxesTo() or dereference().
   */
  public ObjectType toObjectType() {
    return this instanceof ObjectType ? (ObjectType) this : null;
  }

  /**
   * Dereference a type for property access.
   *
   * Filters null/undefined and autoboxes the resulting type.
   * Never returns null.
   */
  public JSType autobox() {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[55]++;
    JSType restricted = restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[56]++;
    JSType autobox = restricted.autoboxesTo();
    return autobox == null ? restricted : autobox;
  }

  /**
   * Dereference a type for property access.
   *
   * Filters null/undefined, autoboxes the resulting type, and returns it
   * iff it's an object.
   */
  public final ObjectType dereference() {
    return autobox().toObjectType();
  }

  /**
   * Tests whether {@code this} and {@code that} are meaningfully
   * comparable. By meaningfully, we mean compatible types that do not lead
   * to step 22 of the definition of the Abstract Equality Comparison
   * Algorithm (11.9.3, page 55&ndash;56) of the ECMA-262 specification.<p>
   */
  public final boolean canTestForEqualityWith(JSType that) {
    return testForEquality(that).equals(UNKNOWN);
  }

  /**
   * Compares {@code this} and {@code that}.
   * @return <ul>
   * <li>{@link TernaryValue#TRUE} if the comparison of values of
   *   {@code this} type and {@code that} always succeed (such as
   *   {@code undefined} compared to {@code null})</li>
   * <li>{@link TernaryValue#FALSE} if the comparison of values of
   *   {@code this} type and {@code that} always fails (such as
   *   {@code undefined} compared to {@code number})</li>
   * <li>{@link TernaryValue#UNKNOWN} if the comparison can succeed or
   *   fail depending on the concrete values</li>
   * </ul>
   */
  public TernaryValue testForEquality(JSType that) {
    return testForEqualityHelper(this, that);
  }

  TernaryValue testForEqualityHelper(JSType aType, JSType bType) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[57]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2048)) == 0 || true) &&
 ((bType.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (512)) == 0 || true) &&
 ((bType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (128)) == 0 || true) &&
 ((bType.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((aType.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((aType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((aType.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 6) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 6) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[47]++;
      return UNKNOWN;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[48]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[58]++;

    boolean aIsEmpty = aType.isEmptyType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[59]++;
    boolean bIsEmpty = bType.isEmptyType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[60]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((aIsEmpty) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((bIsEmpty) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[49]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[61]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((aIsEmpty) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((bIsEmpty) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[51]++;
        return TernaryValue.TRUE;

      } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[52]++;
        return UNKNOWN;
      }

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[50]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[62]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((aType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((bType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[53]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[63]++;
      JSType otherType = aType.isFunctionType() ? bType : aType;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[64]++;
      // In theory, functions are comparable to anything except
      // null/undefined. For example, on FF3:
      // function() {} == 'function () {\n}'
      // In practice, how a function serializes to a string is
      // implementation-dependent, so it does not really make sense to test
      // for equality with a string.
      JSType meet = otherType.getGreatestSubtype(
          getNativeType(JSTypeNative.OBJECT_TYPE));
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[65]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((meet.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((meet.isNoObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[55]++;
        return TernaryValue.FALSE;

      } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[56]++;
        return TernaryValue.UNKNOWN;
      }

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[54]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[66]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((bType.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((bType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[57]++;
      return bType.testForEquality(aType);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[58]++;}
    return null;
  }

  /**
   * Tests whether {@code this} and {@code that} are meaningfully
   * comparable using shallow comparison. By meaningfully, we mean compatible
   * types that are not rejected by step 1 of the definition of the Strict
   * Equality Comparison Algorithm (11.9.6, page 56&ndash;57) of the
   * ECMA-262 specification.<p>
   */
  public final boolean canTestForShallowEqualityWith(JSType that) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[67]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((that.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[59]++;
      return isSubtype(that) || that.isSubtype(this);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[60]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[68]++;

    JSType inf = getGreatestSubtype(that);
    return !inf.isEmptyType() ||
        // Our getGreatestSubtype relation on functions is pretty bad.
        // Let's just say it's always ok to compare two functions.
        // Once the TODO in FunctionType is fixed, we should be able to
        // remove this.
        inf == registry.getNativeType(JSTypeNative.LEAST_FUNCTION_TYPE);
  }

  /**
   * Tests whether this type is nullable.
   */
  public boolean isNullable() {
    return isSubtype(getNativeType(JSTypeNative.NULL_TYPE));
  }

  /**
   * Gets the least supertype of this that's not a union.
   */
  public JSType collapseUnion() {
    return this;
  }

  /**
   * Gets the least supertype of {@code this} and {@code that}.
   * The least supertype is the join (&#8744;) or supremum of both types in the
   * type lattice.<p>
   * Examples:
   * <ul>
   * <li>{@code number &#8744; *} = {@code *}</li>
   * <li>{@code number &#8744; Object} = {@code (number, Object)}</li>
   * <li>{@code Number &#8744; Object} = {@code Object}</li>
   * </ul>
   * @return {@code this &#8744; that}
   */
  public JSType getLeastSupertype(JSType that) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[69]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[61]++;
      // Union types have their own implementation of getLeastSupertype.
      return that.toMaybeUnionType().getLeastSupertype(this);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[62]++;}
    return getLeastSupertype(this, that);
  }

  /**
   * A generic implementation meant to be used as a helper for common
   * getLeastSupertype implementations.
   */
  static JSType getLeastSupertype(JSType thisType, JSType thatType) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[70]++;
    boolean areEquivalent = thisType.isEquivalentTo(thatType);
    return areEquivalent ? thisType :
        filterNoResolvedType(
            thisType.registry.createUnionType(thisType, thatType));
  }

  /**
   * Gets the greatest subtype of {@code this} and {@code that}.
   * The greatest subtype is the meet (&#8743;) or infimum of both types in the
   * type lattice.<p>
   * Examples
   * <ul>
   * <li>{@code Number &#8743; Any} = {@code Any}</li>
   * <li>{@code number &#8743; Object} = {@code Any}</li>
   * <li>{@code Number &#8743; Object} = {@code Number}</li>
   * </ul>
   * @return {@code this &#8744; that}
   */
  public JSType getGreatestSubtype(JSType that) {
    return getGreatestSubtype(this, that);
  }

  /**
   * A generic implementation meant to be used as a helper for common
   * getGreatestSubtype implementations.
   */
  static JSType getGreatestSubtype(JSType thisType, JSType thatType) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[71]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((thisType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((thatType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[63]++;
      // The FunctionType sub-lattice is not well-defined. i.e., the
      // proposition
      // A < B => sup(A, B) == B
      // does not hold because of unknown parameters and return types.
      // See the comment in supAndInfHelper for more info on this.
      return thisType.toMaybeFunctionType().supAndInfHelper(
          thatType.toMaybeFunctionType(), false);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[64]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[72]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((thisType.isEquivalentTo(thatType)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[65]++;
      return thisType;

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[66]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[73]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((thisType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((thatType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[67]++;
      // The greatest subtype with any unknown type is the universal
      // unknown type, unless the two types are equal.
      return thisType.isEquivalentTo(thatType) ? thisType :
          thisType.getNativeType(JSTypeNative.UNKNOWN_TYPE);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[68]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[74]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((thisType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[69]++;
      return thisType.toMaybeUnionType().meet(thatType);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[70]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[75]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((thatType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[71]++;
      return thatType.toMaybeUnionType().meet(thisType);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[72]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[76]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((thisType.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[73]++;
      return thisType.toMaybeTemplatizedType().getGreatestSubtypeHelper(
          thatType);

    }  else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[74]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[77]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((thatType.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[75]++;
      return thatType.toMaybeTemplatizedType().getGreatestSubtypeHelper(
          thisType);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[76]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[78]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((thisType.isSubtype(thatType)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[77]++;
      return filterNoResolvedType(thisType);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[78]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[79]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((thatType.isSubtype(thisType)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[79]++;
      return filterNoResolvedType(thatType);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[80]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[80]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((thisType.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[81]++;
      return thisType.toMaybeRecordType().getGreatestSubtypeHelper(thatType);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[82]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[81]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((thatType.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[83]++;
      return thatType.toMaybeRecordType().getGreatestSubtypeHelper(thisType);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[84]++;}
}
}
}
}
}
}
}
}
}
}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[82]++;
int CodeCoverConditionCoverageHelper_C43;

    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((thisType.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[85]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[83]++;
      JSType inf = thisType.toMaybeEnumElementType().meet(thatType);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[84]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((inf != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[87]++;
        return inf;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[88]++;}

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[86]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[85]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((thatType.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[89]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[86]++;
      JSType inf = thatType.toMaybeEnumElementType().meet(thisType);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[87]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((inf != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[91]++;
        return inf;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[92]++;}

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[90]++;}
}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[88]++;
int CodeCoverConditionCoverageHelper_C47;

    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((thisType.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((thatType.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[93]++;
      return thisType.getNativeType(JSTypeNative.NO_OBJECT_TYPE);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[94]++;}
    return thisType.getNativeType(JSTypeNative.NO_TYPE);
  }

  /**
   * When computing infima, we may get a situation like
   * inf(Type1, Type2)
   * where both types are unresolved, so they're technically
   * subtypes of one another.
   *
   * If this happens, filter them down to NoResolvedType.
   */
  static JSType filterNoResolvedType(JSType type) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[89]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((type.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[95]++;
      // inf(UnresolvedType1, UnresolvedType2) needs to resolve
      // to the base unresolved type, so that the relation is symmetric.
      return type.getNativeType(JSTypeNative.NO_RESOLVED_TYPE);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[96]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[90]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[97]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[91]++;
      UnionType unionType = type.toMaybeUnionType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[92]++;
      boolean needsFiltering = false;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[93]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[1]++;


      for (JSType alt : unionType.getAlternates()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[1]--;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[2]--;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[3]++;
}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[94]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((alt.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[99]++;
          needsFiltering = true;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[95]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[96]++;
          break;

        } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[100]++;}
      }
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[97]++;
int CodeCoverConditionCoverageHelper_C51;

      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((needsFiltering) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[101]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[98]++;
        UnionTypeBuilder builder = new UnionTypeBuilder(type.registry);
        builder.addAlternate(type.getNativeType(JSTypeNative.NO_RESOLVED_TYPE));
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[99]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[100]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[4]++;


        for (JSType alt : unionType.getAlternates()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[4]--;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[5]--;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[6]++;
}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[101]++;
int CodeCoverConditionCoverageHelper_C52;
          if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((alt.isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[103]++;
            builder.addAlternate(alt);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[102]++;

          } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[104]++;}
        }
        return builder.build();

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[102]++;}

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[98]++;}
}
    return type;
  }

  /**
   * Computes the restricted type of this type knowing that the
   * {@code ToBoolean} predicate has a specific value. For more information
   * about the {@code ToBoolean} predicate, see
   * {@link #getPossibleToBooleanOutcomes}.
   *
   * @param outcome the value of the {@code ToBoolean} predicate
   *
   * @return the restricted type, or the Any Type if the underlying type could
   *         not have yielded this ToBoolean value
   *
   * TODO(user): Move this method to the SemanticRAI and use the visit
   * method of types to get the restricted type.
   */
  public JSType getRestrictedTypeGivenToBooleanOutcome(boolean outcome) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[103]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((outcome) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this == getNativeType(JSTypeNative.UNKNOWN_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[105]++;
      return getNativeType(JSTypeNative.CHECKED_UNKNOWN_TYPE);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[106]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[104]++;

    BooleanLiteralSet literals = getPossibleToBooleanOutcomes();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[105]++;
int CodeCoverConditionCoverageHelper_C54;
    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((literals.contains(outcome)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[107]++;
      return this;

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[108]++;
      return getNativeType(JSTypeNative.NO_TYPE);
    }
  }

  /**
   * Computes the set of possible outcomes of the {@code ToBoolean} predicate
   * for this type. The {@code ToBoolean} predicate is defined by the ECMA-262
   * standard, 3<sup>rd</sup> edition. Its behavior for simple types can be
   * summarized by the following table:
   * <table>
   * <tr><th>type</th><th>result</th></tr>
   * <tr><td>{@code undefined}</td><td>{false}</td></tr>
   * <tr><td>{@code null}</td><td>{false}</td></tr>
   * <tr><td>{@code boolean}</td><td>{true, false}</td></tr>
   * <tr><td>{@code number}</td><td>{true, false}</td></tr>
   * <tr><td>{@code string}</td><td>{true, false}</td></tr>
   * <tr><td>{@code Object}</td><td>{true}</td></tr>
   * </table>
   * @return the set of boolean literals for this type
   */
  public abstract BooleanLiteralSet getPossibleToBooleanOutcomes();

  /**
   * Computes the subset of {@code this} and {@code that} types if equality
   * is observed. If a value {@code v1} of type {@code null} is equal to a value
   * {@code v2} of type {@code (undefined,number)}, we can infer that the
   * type of {@code v1} is {@code null} and the type of {@code v2} is
   * {@code undefined}.
   *
   * @return a pair containing the restricted type of {@code this} as the first
   *         component and the restricted type of {@code that} as the second
   *         element. The returned pair is never {@code null} even though its
   *         components may be {@code null}
   */
  public TypePair getTypesUnderEquality(JSType that) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[106]++;
int CodeCoverConditionCoverageHelper_C55;
    // unions types
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[109]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[107]++;
      TypePair p = that.toMaybeUnionType().getTypesUnderEquality(this);
      return new TypePair(p.typeB, p.typeA);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[110]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[108]++;

    // other types
    switch (testForEquality(that)) {
      case FALSE:
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[111]++;
        return new TypePair(null, null);

      case TRUE:
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[112]++;
      case UNKNOWN:
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[113]++;
        return new TypePair(this, that); default : CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[114]++;
    }

    // switch case is exhaustive
    throw new IllegalStateException();
  }

  /**
   * Computes the subset of {@code this} and {@code that} types if inequality
   * is observed. If a value {@code v1} of type {@code number} is not equal to a
   * value {@code v2} of type {@code (undefined,number)}, we can infer that the
   * type of {@code v1} is {@code number} and the type of {@code v2} is
   * {@code number} as well.
   *
   * @return a pair containing the restricted type of {@code this} as the first
   *         component and the restricted type of {@code that} as the second
   *         element. The returned pair is never {@code null} even though its
   *         components may be {@code null}
   */
  public TypePair getTypesUnderInequality(JSType that) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[109]++;
int CodeCoverConditionCoverageHelper_C56;
    // unions types
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[115]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[110]++;
      TypePair p = that.toMaybeUnionType().getTypesUnderInequality(this);
      return new TypePair(p.typeB, p.typeA);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[116]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[111]++;

    // other types
    switch (testForEquality(that)) {
      case TRUE:
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[117]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[112]++;
        JSType noType = getNativeType(JSTypeNative.NO_TYPE);
        return new TypePair(noType, noType);

      case FALSE:
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[118]++;
      case UNKNOWN:
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[119]++;
        return new TypePair(this, that); default : CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[120]++;
    }

    // switch case is exhaustive
    throw new IllegalStateException();
  }

  /**
   * Computes the subset of {@code this} and {@code that} types under shallow
   * equality.
   *
   * @return a pair containing the restricted type of {@code this} as the first
   *         component and the restricted type of {@code that} as the second
   *         element. The returned pair is never {@code null} even though its
   *         components may be {@code null}.
   */
  public TypePair getTypesUnderShallowEquality(JSType that) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[113]++;
    JSType commonType = getGreatestSubtype(that);
    return new TypePair(commonType, commonType);
  }

  /**
   * Computes the subset of {@code this} and {@code that} types under
   * shallow inequality.
   *
   * @return A pair containing the restricted type of {@code this} as the first
   *         component and the restricted type of {@code that} as the second
   *         element. The returned pair is never {@code null} even though its
   *         components may be {@code null}
   */
  public TypePair getTypesUnderShallowInequality(JSType that) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[114]++;
int CodeCoverConditionCoverageHelper_C57;
    // union types
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((that.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[121]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[115]++;
      TypePair p = that.toMaybeUnionType().getTypesUnderShallowInequality(this);
      return new TypePair(p.typeB, p.typeA);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[122]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[116]++;
int CodeCoverConditionCoverageHelper_C58;

    // Other types.
    // There are only two types whose shallow inequality is deterministically
    // true -- null and undefined. We can just enumerate them.
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (128)) == 0 || true) &&
 ((isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (32)) == 0 || true) &&
 ((that.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((that.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 4) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 4) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[123]++;
      return new TypePair(null, null);

    } else {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[124]++;
      return new TypePair(this, that);
    }
  }

  /**
   * If this is a union type, returns a union type that does not include
   * the null or undefined type.
   */
  public JSType restrictByNotNullOrUndefined() {
    return this;
  }

  /**
   * Checks whether {@code this} is a subtype of {@code that}.<p>
   *
   * Subtyping rules:
   * <ul>
   * <li>(unknown) &mdash; every type is a subtype of the Unknown type.</li>
   * <li>(no) &mdash; the No type is a subtype of every type.</li>
   * <li>(no-object) &mdash; the NoObject type is a subtype of every object
   * type (i.e. subtypes of the Object type).</li>
   * <li>(ref) &mdash; a type is a subtype of itself.</li>
   * <li>(union-l) &mdash; A union type is a subtype of a type U if all the
   * union type's constituents are a subtype of U. Formally<br>
   * {@code (T<sub>1</sub>, &hellip;, T<sub>n</sub>) &lt;: U} if and only
   * {@code T<sub>k</sub> &lt;: U} for all {@code k &isin; 1..n}.</li>
   * <li>(union-r) &mdash; A type U is a subtype of a union type if it is a
   * subtype of one of the union type's constituents. Formally<br>
   * {@code U &lt;: (T<sub>1</sub>, &hellip;, T<sub>n</sub>)} if and only
   * if {@code U &lt;: T<sub>k</sub>} for some index {@code k}.</li>
   * <li>(objects) &mdash; an Object {@code O<sub>1</sub>} is a subtype
   * of an object {@code O<sub>2</sub>} if it has more properties
   * than {@code O<sub>2</sub>} and all common properties are
   * pairwise subtypes.</li>
   * </ul>
   *
   * @return {@code this &lt;: that}
   */
  public boolean isSubtype(JSType that) {
    return isSubtypeHelper(this, that);
  }

  /**
   * A generic implementation meant to be used as a helper for common subtyping
   * cases.
   */
  static boolean isSubtypeHelper(JSType thisType, JSType thatType) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[117]++;
int CodeCoverConditionCoverageHelper_C59;
    // unknown
    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((thatType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[125]++;
      return true;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[126]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[118]++;
int CodeCoverConditionCoverageHelper_C60;
    // all type
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((thatType.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[127]++;
      return true;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[128]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[119]++;
int CodeCoverConditionCoverageHelper_C61;
    // equality
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((thisType.isEquivalentTo(thatType)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[129]++;
      return true;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[130]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[120]++;
int CodeCoverConditionCoverageHelper_C62;
    // unions
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((thatType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[131]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[121]++;
      UnionType union = thatType.toMaybeUnionType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[122]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[7]++;


      for (JSType element : union.alternates) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[7]--;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[8]--;
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.loops[9]++;
}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[123]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((thisType.isSubtype(element)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[133]++;
          return true;

        } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[134]++;}
      }
      return false;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[132]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[124]++;
int CodeCoverConditionCoverageHelper_C64;

    // templatized types.
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((thisType.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[135]++;
      return !areIncompatibleArrays(thisType, thatType) &&
          thisType.toMaybeTemplatizedType().getReferencedType().isSubtype(
              thatType);

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[136]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[125]++;
int CodeCoverConditionCoverageHelper_C65;
    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((thatType.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[137]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[126]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((isExemptFromTemplateTypeInvariance(thatType)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((thisType.getTemplateTypeMap().checkEquivalenceHelper(
              thatType.getTemplateTypeMap(), EquivalenceMethod.IDENTITY)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[139]++;
        return false;

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[140]++;}

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[138]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[127]++;
int CodeCoverConditionCoverageHelper_C67;

    // proxy types
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((thatType instanceof ProxyObjectType) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[141]++;
      return thisType.isSubtype(
          ((ProxyObjectType) thatType).getReferencedTypeInternal());

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[142]++;}
    return false;
  }

  /**
   * Determines if two types are incompatible Arrays, meaning that their element
   * template types are not subtypes of one another.
   */
  private static boolean areIncompatibleArrays(JSType type1, JSType type2) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[128]++;
    ObjectType type1Obj = type1.toObjectType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[129]++;
    ObjectType type2Obj = type2.toObjectType();
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[130]++;
int CodeCoverConditionCoverageHelper_C68;
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((type1Obj == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((type2Obj == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[143]++;
      return false;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[144]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[131]++;
int CodeCoverConditionCoverageHelper_C69;

    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 (("Array".equals(type1Obj.getReferenceName())) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 (("Array".equals(type2Obj.getReferenceName())) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[145]++;
      return false;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[146]++;}
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[132]++;

    String templateKey = JSTypeRegistry.OBJECT_ELEMENT_TEMPLATE;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[133]++;
    JSType elemType1 = type1.getTemplateTypeMap().getTemplateType(templateKey);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[134]++;
    JSType elemType2 = type2.getTemplateTypeMap().getTemplateType(templateKey);
    return !elemType1.isSubtype(elemType2) && !elemType2.isSubtype(elemType1);
  }

  /**
   * Determines if the specified type is exempt from standard invariant
   * templatized typing rules.
   */
  static boolean isExemptFromTemplateTypeInvariance(JSType type) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[135]++;
    ObjectType objType = type.toObjectType();
    return objType == null ||
        "Array".equals(objType.getReferenceName()) ||
        "Object".equals(objType.getReferenceName());
  }

  /**
   * Visit this type with the given visitor.
   * @see com.google.javascript.rhino.jstype.Visitor
   * @return the value returned by the visitor
   */
  public abstract <T> T visit(Visitor<T> visitor);

  /**
   * Visit the types with the given visitor.
   * @see com.google.javascript.rhino.jstype.RelationshipVisitor
   * @return the value returned by the visitor
   */
  abstract <T> T visit(RelationshipVisitor<T> visitor, JSType that);

  /**
   * Force this type to resolve, even if the registry is in a lazy
   * resolving mode.
   * @see #resolve
   */
  public final JSType forceResolve(ErrorReporter t, StaticScope<JSType> scope) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[136]++;
    ResolveMode oldResolveMode = registry.getResolveMode();
    registry.setResolveMode(ResolveMode.IMMEDIATE);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[137]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[138]++;
    JSType result = resolve(t, scope);
    registry.setResolveMode(oldResolveMode);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[139]++;
    return result;
  }


  /**
   * Resolve this type in the given scope.
   *
   * The returned value must be equal to {@code this}, as defined by
   * {@link #isEquivalentTo}. It may or may not be the same object. This method
   * may modify the internal state of {@code this}, as long as it does
   * so in a way that preserves Object equality.
   *
   * For efficiency, we should only resolve a type once per compilation job.
   * For incremental compilations, one compilation job may need the
   * artifacts from a previous generation, so we will eventually need
   * a generational flag instead of a boolean one.
   */
  public final JSType resolve(ErrorReporter t, StaticScope<JSType> scope) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[140]++;
int CodeCoverConditionCoverageHelper_C70;
    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((resolved) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[147]++;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[141]++;
int CodeCoverConditionCoverageHelper_C71;
      // TODO(nicksantos): Check to see if resolve() looped back on itself.
      // Preconditions.checkNotNull(resolveResult);
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((resolveResult == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$bafyl3yshf8z15ug1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[149]++;
        return registry.getNativeType(JSTypeNative.UNKNOWN_TYPE);

      } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[150]++;}
      return resolveResult;

    } else {
  CodeCoverCoverageCounter$bafyl3yshf8z15ug1.branches[148]++;}
    resolved = true;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[142]++;
    resolveResult = resolveInternal(t, scope);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[143]++;
    resolveResult.setResolvedTypeInternal(resolveResult);
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[144]++;
    return resolveResult;
  }

  /**
   * @see #resolve
   */
  abstract JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope);

  void setResolvedTypeInternal(JSType type) {
    resolveResult = type;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[145]++;
    resolved = true;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[146]++;
  }

  /** Whether the type has been resolved. */
  public final boolean isResolved() {
    return resolved;
  }

  /** Clears the resolved field. */
  public final void clearResolved() {
    resolved = false;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[147]++;
    resolveResult = null;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[148]++;
  }

  /**
   * A null-safe resolve.
   * @see #resolve
   */
  static final JSType safeResolve(
      JSType type, ErrorReporter t, StaticScope<JSType> scope) {
    return type == null ? null : type.resolve(t, scope);
  }

  /**
   * Certain types have constraints on them at resolution-time.
   * For example, a type in an {@code @extends} annotation must be an
   * object. Clients should inject a validator that emits a warning
   * if the type does not validate, and return false.
   */
  public boolean setValidator(Predicate<JSType> validator) {
    return validator.apply(this);
  }

  public static class TypePair {
    public final JSType typeA;
    public final JSType typeB;

    public TypePair(JSType typeA, JSType typeB) {
      this.typeA = typeA;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[149]++;
      this.typeB = typeB;
CodeCoverCoverageCounter$bafyl3yshf8z15ug1.statements[150]++;
    }
  }

  /**
   * A string representation of this type, suitable for printing
   * in warnings.
   */
  @Override
  public String toString() {
    return toStringHelper(false);
  }

  /**
   * A hash code function for diagnosing complicated issues
   * around type-identity.
   */
  public String toDebugHashCodeString() {
    return "{" + hashCode() + "}";
  }

  /**
   * A string representation of this type, suitable for printing
   * in type annotations at code generation time.
   */
  public final String toAnnotationString() {
    return toStringHelper(true);
  }

  /**
   * @param forAnnotations Whether this is for use in code generator
   *     annotations. Otherwise, it's for warnings.
   */
  abstract String toStringHelper(boolean forAnnotations);

  /**
   * Modify this type so that it matches the specified type.
   *
   * This is useful for reverse type-inference, where we want to
   * infer that an object literal matches its constraint (much like
   * how the java compiler does reverse-inference to figure out generics).
   * @param constraint
   */
  public void matchConstraint(JSType constraint) {}
}

class CodeCoverCoverageCounter$bafyl3yshf8z15ug1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bafyl3yshf8z15ug1 ());
  }
    public static long[] statements = new long[151];
    public static long[] branches = new long[151];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[72];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.JSType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,2,1,1,2,1,1,1,2,1,1,3,2,2,2,1,2,1,1,1,3,2,2,2,2,2,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,3,1,1,1,1,1,1,1,2,1,2,2,1,1};
    for (int i = 1; i <= 71; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$bafyl3yshf8z15ug1 () {
    super("com.google.javascript.rhino.jstype.JSType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 150; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 150; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.JSType.java");
      for (int i = 1; i <= 150; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 150; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 71; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

