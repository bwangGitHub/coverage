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

import static com.google.javascript.rhino.jstype.TernaryValue.FALSE;
import static com.google.javascript.rhino.jstype.TernaryValue.UNKNOWN;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

import java.util.Set;

/**
 * Object type.
 *
 * In JavaScript, all object types have properties, and each of those
 * properties has a type. Property types may be DECLARED, INFERRED, or
 * UNKNOWN.
 *
 * DECLARED properties have an explicit type annotation, as in:
 * <code>
 * /xx @type {number} x/
 * Foo.prototype.bar = 1;
 * </code>
 * This property may only hold number values, and an assignment to any
 * other type of value is an error.
 *
 * INFERRED properties do not have an explicit type annotation. Rather,
 * we try to find all the possible types that this property can hold.
 * <code>
 * Foo.prototype.bar = 1;
 * </code>
 * If the programmer assigns other types of values to this property,
 * the property will take on the union of all these types.
 *
 * UNKNOWN properties are properties on the UNKNOWN type. The UNKNOWN
 * type has all properties, but we do not know whether they are
 * declared or inferred.
 *
 */
public abstract class ObjectType extends JSType implements StaticScope<JSType> {
  static {
    CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.ping();
  }

  private boolean visited;
  private JSDocInfo docInfo = null;
  {
    CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[1]++;
  }
  private boolean unknown = true;
  {
    CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[2]++;
  }

  ObjectType(JSTypeRegistry registry) {
    super(registry);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[3]++;
  }

  ObjectType(JSTypeRegistry registry, TemplateTypeMap templateTypeMap) {
    super(registry, templateTypeMap);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[4]++;
  }

  @Override
  public Node getRootNode() { return null; }

  @Override
  public ObjectType getParentScope() {
    return getImplicitPrototype();
  }

  /**
   * Returns the property map that manages the set of properties for an object.
   */
  PropertyMap getPropertyMap() {
    return PropertyMap.immutableEmptyMap();
  }

  /**
   * Default getSlot implementation. This gets overridden by FunctionType
   * for lazily-resolved prototypes.
   */
  @Override
  public Property getSlot(String name) {
    return getPropertyMap().getSlot(name);
  }

  @Override
  public Property getOwnSlot(String name) {
    return getPropertyMap().getOwnProperty(name);
  }

  @Override
  public JSType getTypeOfThis() {
    return null;
  }

  /**
   * Gets the declared default element type.
   * @see TemplatizedType
   */
  public ImmutableList<JSType> getTemplateTypes() {
    return null;
  }

  /**
   * Gets the docInfo for this type.
   */
  @Override
  public JSDocInfo getJSDocInfo() {
    return docInfo;
  }

  /**
   * Sets the docInfo for this type from the given
   * {@link JSDocInfo}. The {@code JSDocInfo} may be {@code null}.
   */
  public void setJSDocInfo(JSDocInfo info) {
    docInfo = info;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[5]++;
  }

  /**
   * Detects a cycle in the implicit prototype chain. This method accesses
   * the {@link #getImplicitPrototype()} method and must therefore be
   * invoked only after the object is sufficiently initialized to respond to
   * calls to this method.<p>
   *
   * @return True iff an implicit prototype cycle was detected.
   */
  final boolean detectImplicitPrototypeCycle() {
    // detecting cycle
    this.visited = true;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[6]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[7]++;
    ObjectType p = getImplicitPrototype();
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[1]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[2]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[3]++;
}
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p.visited) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[1]++;
        return true;

      } else {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[2]++;
        p.visited = true;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[10]++;
      }
      p = p.getImplicitPrototype();
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[11]++;
    }

    // clean up
    p = this;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[12]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
    do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[4]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[5]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[6]++;
}
      p.visited = false;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[14]++;
      p = p.getImplicitPrototype();
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[15]++;
    } while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false));
    return false;
  }

  /**
   * Detects cycles in either the implicit prototype chain, or the implemented/extended
   * interfaces.<p>
   *
   * @return True iff a cycle was detected.
   */
  final boolean detectInheritanceCycle() {
    // TODO(user): This should get moved to preventing cycles in FunctionTypeBuilder
    // rather than removing them here after they have been created.
    // Also, this doesn't do the right thing for extended interfaces, though that is
    // masked by another bug.
    return detectImplicitPrototypeCycle()
        || Iterables.contains(this.getCtorImplementedInterfaces(), this)
        || Iterables.contains(this.getCtorExtendedInterfaces(), this);
  }

  /**
   * Gets the reference name for this object. This includes named types
   * like constructors, prototypes, and enums. It notably does not include
   * literal types like strings and booleans and structural types.
   * @return the object's name or {@code null} if this is an anonymous
   *         object
   */
  public abstract String getReferenceName();

  /**
   * Due to the complexity of some of our internal type systems, sometimes
   * we have different types constructed by the same constructor.
   * In other parts of the type system, these are called delegates.
   * We construct these types by appending suffixes to the constructor name.
   *
   * The normalized reference name does not have these suffixes, and as such,
   * recollapses these implicit types back to their real type.
   */
  public String getNormalizedReferenceName() {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[16]++;
    String name = getReferenceName();
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[3]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[18]++;
      int pos = name.indexOf("(");
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((pos != -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[5]++;
        return name.substring(0, pos);

      } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[4]++;}
    return name;
  }

  @Override
  public String getDisplayName() {
    return getNormalizedReferenceName();
  }

  /**
   * Creates a suffix for a proxy delegate.
   * @see #getNormalizedReferenceName
   */
  public static String createDelegateSuffix(String suffix) {
    return "(" + suffix + ")";
  }

  /**
   * Returns true if the object is named.
   * @return true if the object is named, false if it is anonymous
   */
  public boolean hasReferenceName() {
    return false;
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[20]++;
    // super
    TernaryValue result = super.testForEquality(that);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[7]++;
      return result;

    } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[8]++;}
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
    // objects are comparable to everything but null/undefined
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((that.isSubtype(
            getNativeType(JSTypeNative.OBJECT_NUMBER_STRING_BOOLEAN))) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[9]++;
      return UNKNOWN;

    } else {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[10]++;
      return FALSE;
    }
  }

  /**
   * Gets this object's constructor.
   * @return this object's constructor or {@code null} if it is a native
   * object (constructed natively v.s. by instantiation of a function)
   */
  public abstract FunctionType getConstructor();

  /**
   * Gets the implicit prototype (a.k.a. the {@code [[Prototype]]} property).
   */
  public abstract ObjectType getImplicitPrototype();

  /**
   * Defines a property whose type is explicitly declared by the programmer.
   * @param propertyName the property's name
   * @param type the type
   * @param propertyNode the node corresponding to the declaration of property
   *        which might later be accessed using {@code getPropertyNode}.
   */
  public final boolean defineDeclaredProperty(String propertyName,
      JSType type, Node propertyNode) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[23]++;
    boolean result = defineProperty(propertyName, type, false, propertyNode);
    // All property definitions go through this method
    // or defineInferredProperty. Because the properties defined an an
    // object can affect subtyping, it's slightly more efficient
    // to register this after defining the property.
    registry.registerPropertyOnType(propertyName, this);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[24]++;
    return result;
  }

  /**
   * Defines a property whose type is on a synthesized object. These objects
   * don't actually exist in the user's program. They're just used for
   * bookkeeping in the type system.
   */
  public final boolean defineSynthesizedProperty(String propertyName,
      JSType type, Node propertyNode) {
    return defineProperty(propertyName, type, false, propertyNode);
  }

  /**
   * Defines a property whose type is inferred.
   * @param propertyName the property's name
   * @param type the type
   * @param propertyNode the node corresponding to the inferred definition of
   *        property that might later be accessed using {@code getPropertyNode}.
   */
  public final boolean defineInferredProperty(String propertyName,
      JSType type, Node propertyNode) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[25]++;
    StaticSlot<JSType> originalSlot = getSlot(propertyName);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hasProperty(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[11]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isPropertyTypeDeclared(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[13]++;
        // We never want to hide a declared property with an inferred property.
        return true;

      } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[14]++;}
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[28]++;
      JSType originalType = getPropertyType(propertyName);
      type = originalType == null ? type :
          originalType.getLeastSupertype(type);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[29]++;

    } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[12]++;}
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[30]++;

    boolean result = defineProperty(propertyName, type, true,
        propertyNode);

    // All property definitions go through this method
    // or defineDeclaredProperty. Because the properties defined an an
    // object can affect subtyping, it's slightly more efficient
    // to register this after defining the property.
    registry.registerPropertyOnType(propertyName, this);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[31]++;

    return result;
  }

  /**
   * Defines a property.<p>
   *
   * For clarity, callers should prefer {@link #defineDeclaredProperty} and
   * {@link #defineInferredProperty}.
   *
   * @param propertyName the property's name
   * @param type the type
   * @param inferred {@code true} if this property's type is inferred
   * @param propertyNode the node that represents the definition of property.
   *        Depending on the actual sub-type the node type might be different.
   *        The general idea is to have an estimate of where in the source code
   *        this property is defined.
   * @return True if the property was registered successfully, false if this
   *        conflicts with a previous property type declaration.
   */
  abstract boolean defineProperty(String propertyName, JSType type,
      boolean inferred, Node propertyNode);

  /**
   * Removes the declared or inferred property from this ObjectType.
   *
   * @param propertyName the property's name
   * @return true if the property was removed successfully. False if the
   *         property did not exist, or could not be removed.
   */
  public boolean removeProperty(String propertyName) {
    return false;
  }

  /**
   * Gets the node corresponding to the definition of the specified property.
   * This could be the node corresponding to declaration of the property or the
   * node corresponding to the first reference to this property, e.g.,
   * "this.propertyName" in a constructor. Note this is mainly intended to be
   * an estimate of where in the source code a property is defined. Sometime
   * the returned node is not even part of the global AST but in the AST of the
   * JsDoc that defines a type.
   *
   * @param propertyName the name of the property
   * @return the {@code Node} corresponding to the property or null.
   */
  public Node getPropertyNode(String propertyName) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[32]++;
    Property p = getSlot(propertyName);
    return p == null ? null : p.getNode();
  }

  /**
   * Gets the docInfo on the specified property on this type.  This should not
   * be implemented recursively, as you generally need to know exactly on
   * which type in the prototype chain the JSDocInfo exists.
   */
  public JSDocInfo getOwnPropertyJSDocInfo(String propertyName) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[33]++;
    Property p = getOwnSlot(propertyName);
    return p == null ? null : p.getJSDocInfo();
  }

  /**
   * Sets the docInfo for the specified property from the
   * {@link JSDocInfo} on its definition.
   * @param info {@code JSDocInfo} for the property definition. May be
   *        {@code null}.
   */
  public void setPropertyJSDocInfo(String propertyName, JSDocInfo info) {
    // by default, do nothing
  }

  @Override
  public JSType findPropertyType(String propertyName) {
    return hasProperty(propertyName) ?
        getPropertyType(propertyName) : null;
  }

  /**
   * Gets the property type of the property whose name is given. If the
   * underlying object does not have this property, the Unknown type is
   * returned to indicate that no information is available on this property.
   *
   * This gets overridden by FunctionType for lazily-resolved call() and
   * bind() functions.
   *
   * @return the property's type or {@link UnknownType}. This method never
   *         returns {@code null}.
   */
  public JSType getPropertyType(String propertyName) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[34]++;
    StaticSlot<JSType> slot = getSlot(propertyName);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[15]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((isNoResolvedType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isCheckedUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[17]++;
        return getNativeType(JSTypeNative.CHECKED_UNKNOWN_TYPE);

      } else {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[18]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[37]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[19]++;
        return getNativeType(JSTypeNative.NO_TYPE);

      } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[20]++;}
}
      return getNativeType(JSTypeNative.UNKNOWN_TYPE);

    } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[16]++;}
    return slot.getType();
  }

  @Override
  public boolean hasProperty(String propertyName) {
    // Unknown types have all properties.
    return isEmptyType() || isUnknownType() || getSlot(propertyName) != null;
  }

  /**
   * Checks whether the property whose name is given is present directly on
   * the object.  Returns false even if it is declared on a supertype.
   */
  public boolean hasOwnProperty(String propertyName) {
    return getOwnSlot(propertyName) != null;
  }

  /**
   * Returns the names of all the properties directly on this type.
   *
   * Overridden by FunctionType to add "prototype".
   */
  public Set<String> getOwnPropertyNames() {
    return getPropertyMap().getOwnPropertyNames();
  }

  /**
   * Checks whether the property's type is inferred.
   */
  public boolean isPropertyTypeInferred(String propertyName) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[38]++;
    StaticSlot<JSType> slot = getSlot(propertyName);
    return slot == null ? false : slot.isTypeInferred();
  }

  /**
   * Checks whether the property's type is declared.
   */
  public boolean isPropertyTypeDeclared(String propertyName) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[39]++;
    StaticSlot<JSType> slot = getSlot(propertyName);
    return slot == null ? false : !slot.isTypeInferred();
  }

  /**
   * Whether the given property is declared on this object.
   */
  final boolean hasOwnDeclaredProperty(String name) {
    return hasOwnProperty(name) && isPropertyTypeDeclared(name);
  }

  /** Checks whether the property was defined in the externs. */
  public boolean isPropertyInExterns(String propertyName) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[40]++;
    Property p = getSlot(propertyName);
    return p == null ? false : p.isFromExterns();
  }

  /**
   * Gets the number of properties of this object.
   */
  public int getPropertiesCount() {
    return getPropertyMap().getPropertiesCount();
  }

  /**
   * Returns a list of properties defined or inferred on this type and any of
   * its supertypes.
   */
  public Set<String> getPropertyNames() {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[41]++;
    Set<String> props = Sets.newTreeSet();
    collectPropertyNames(props);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[42]++;
    return props;
  }

  /**
   * Adds any properties defined on this type or its supertypes to the set.
   */
  final void collectPropertyNames(Set<String> props) {
    getPropertyMap().collectPropertyNames(props);
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[43]++;
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseObjectType(this);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseObjectType(this, that);
  }

  /**
   * Checks that the prototype is an implicit prototype of this object. Since
   * each object has an implicit prototype, an implicit prototype's
   * implicit prototype is also this implicit prototype's.
   *
   * @param prototype any prototype based object
   *
   * @return {@code true} if {@code prototype} is {@code equal} to any
   *         object in this object's implicit prototype chain.
   */
  final boolean isImplicitPrototype(ObjectType prototype) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[44]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;
    for (ObjectType current = this;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false);
         current = current.getImplicitPrototype()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[7]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[8]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[9]++;
}
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[45]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((current.isEquivalentTo(prototype)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[21]++;
        return true;

      } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[22]++;}
    }
    return false;
  }

  @Override
  public BooleanLiteralSet getPossibleToBooleanOutcomes() {
    return BooleanLiteralSet.TRUE;
  }

  /**
   * We treat this as the unknown type if any of its implicit prototype
   * properties is unknown.
   */
  @Override
  public boolean isUnknownType() {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[46]++;
int CodeCoverConditionCoverageHelper_C15;
    // If the object is unknown now, check the supertype again,
    // because it might have been resolved since the last check.
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((unknown) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[23]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[47]++;
      ObjectType implicitProto = getImplicitPrototype();
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[48]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((implicitProto == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((implicitProto.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[25]++;
        unknown = false;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[49]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[50]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[10]++;


        for (ObjectType interfaceType : getCtorExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[10]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[11]--;
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.loops[12]++;
}
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[51]++;
int CodeCoverConditionCoverageHelper_C17;
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((interfaceType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[27]++;
            unknown = true;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[52]++;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[53]++;
            break;

          } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[28]++;}
        }

      } else {
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[26]++;
        unknown = implicitProto.isUnknownType();
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[54]++;
      }

    } else {
  CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.branches[24]++;}
    return unknown;
  }

  @Override
  public boolean isObject() {
    return true;
  }

  /**
   * Returns true if any cached values have been set for this type.  If true,
   * then the prototype chain should not be changed, as it might invalidate the
   * cached values.
   */
  public boolean hasCachedValues() {
    return !unknown;
  }

  /**
   * Clear cached values. Should be called before making changes to a prototype
   * that may have been changed since creation.
   */
  public void clearCachedValues() {
    unknown = true;
CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt.statements[55]++;
  }

  /** Whether this is a built-in object. */
  public boolean isNativeObjectType() {
    return false;
  }

  /**
   * A null-safe version of JSType#toObjectType.
   */
  public static ObjectType cast(JSType type) {
    return type == null ? null : type.toObjectType();
  }

  @Override
  public final boolean isFunctionPrototypeType() {
    return getOwnerFunction() != null;
  }

  /** Gets the owner of this if it's a function prototype. */
  public FunctionType getOwnerFunction() {
    return null;
  }

  /** Sets the owner function. By default, does nothing. */
  void setOwnerFunction(FunctionType type) {}

  /**
   * Gets the interfaces implemented by the ctor associated with this type.
   * Intended to be overridden by subclasses.
   */
  public Iterable<ObjectType> getCtorImplementedInterfaces() {
    return ImmutableSet.of();
  }

  /**
   * Gets the interfaces extended by the interface associated with this type.
   * Intended to be overridden by subclasses.
   */
  public Iterable<ObjectType> getCtorExtendedInterfaces() {
    return ImmutableSet.of();
  }
}

class CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt ());
  }
    public static long[] statements = new long[56];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.ObjectType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$nsj2bpkjtiy0az3ajalo3tt () {
    super("com.google.javascript.rhino.jstype.ObjectType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 55; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.ObjectType.java");
      for (int i = 1; i <= 55; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

