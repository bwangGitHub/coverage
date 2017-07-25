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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;

import java.util.List;

/**
 * A {@code NamedType} is a named reference to some other type.  This provides
 * a convenient mechanism for implementing forward references to types; a
 * {@code NamedType} can be used as a placeholder until its reference is
 * resolved.  It is also useful for representing type names in JsDoc type
 * annotations, some of which may never be resolved (as they may refer to
 * types in host systems not yet supported by JSCompiler, such as the JVM.)<p>
 *
 * An important distinction: {@code NamedType} is a type name reference,
 * whereas {@link ObjectType} is a named type object, such as an Enum name.
 * The Enum itself is typically used only in a dot operator to name one of its
 * constants, or in a declaration, where its name will appear in a
 * NamedType.<p>
 *
 * A {@code NamedType} is not currently a full-fledged typedef, because it
 * cannot resolve to any JavaScript type.  It can only resolve to a named
 * {@link JSTypeRegistry} type, or to {@link FunctionType} or
 * {@link EnumType}.<p>
 *
 * If full typedefs are to be supported, then each method on each type class
 * needs to be reviewed to make sure that everything works correctly through
 * typedefs.  Alternatively, we would need to walk through the parse tree and
 * unroll each reference to a {@code NamedType} to its resolved type before
 * applying the rest of the analysis.<p>
 *
 * TODO(user): Revisit all of this logic.<p>
 *
 * The existing typing logic is hacky.  Unresolved types should get processed
 * in a more consistent way, but with the Rhino merge coming, there will be
 * much that has to be changed.<p>
 *
 */
class NamedType extends ProxyObjectType {
  static {
    CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[1]++;
  }

  private final String reference;
  private final String sourceName;
  private final int lineno;
  private final int charno;

  /**
   * Validates the type resolution.
   */
  private Predicate<JSType> validator;

  /**
   * Property-defining continuations.
   */
  private List<PropertyContinuation> propertyContinuations = null;
  {
    CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[2]++;
  }

  /**
   * Create a named type based on the reference.
   */
  NamedType(JSTypeRegistry registry, String reference,
      String sourceName, int lineno, int charno) {
    super(registry, registry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE));
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[3]++;

    Preconditions.checkNotNull(reference);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[4]++;
    this.reference = reference;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[5]++;
    this.sourceName = sourceName;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[6]++;
    this.lineno = lineno;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[7]++;
    this.charno = charno;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[8]++;
  }

  @Override
  boolean defineProperty(String propertyName, JSType type,
      boolean inferred, Node propertyNode) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isResolved()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[1]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
      // If this is an unresolved object type, we need to save all its
      // properties and define them when it is resolved.
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((propertyContinuations == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[3]++;
        propertyContinuations = Lists.newArrayList();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[11]++;

      } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[4]++;}
      propertyContinuations.add(
          new PropertyContinuation(
              propertyName, type, inferred, propertyNode));
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[12]++;
      return true;

    } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[2]++;
      return super.defineProperty(
          propertyName, type, inferred, propertyNode);
    }
  }

  private void finishPropertyContinuations() {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[13]++;
    ObjectType referencedObjType = getReferencedObjTypeInternal();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((referencedObjType != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((referencedObjType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[5]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((propertyContinuations != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[7]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[1]++;


        for (PropertyContinuation c : propertyContinuations) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[1]--;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[2]--;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[3]++;
}
          c.commit(this);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[17]++;
        }

      } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[6]++;}
    propertyContinuations = null;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[18]++;
  }

  /** Returns the type to which this refers (which is unknown if unresolved). */
  public JSType getReferencedType() {
    return getReferencedTypeInternal();
  }

  @Override
  public String getReferenceName() {
    return reference;
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return reference;
  }

  @Override
  public boolean hasReferenceName() {
    return true;
  }

  @Override
  boolean isNamedType() {
    return true;
  }

  @Override
  public boolean isNominalType() {
    return true;
  }

  @Override
  public int hashCode() {
    return reference.hashCode();
  }

  /**
   * Resolve the referenced type within the enclosing scope.
   */
  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> enclosing) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[19]++;
    // TODO(user): Investigate whether it is really necessary to keep two
    // different mechanisms for resolving named types, and if so, which order
    // makes more sense. Now, resolution via registry is first in order to
    // avoid triggering the warnings built into the resolution via properties.
    boolean resolved = resolveViaRegistry(t);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((detectInheritanceCycle()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[9]++;
      handleTypeCycle(t);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[21]++;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[10]++;}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((resolved) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[11]++;
      super.resolveInternal(t, enclosing);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[23]++;
      finishPropertyContinuations();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[24]++;
      return registry.isLastGeneration() ?
          getReferencedType() : this;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[12]++;}

    resolveViaProperties(t, enclosing);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[25]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((detectInheritanceCycle()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[13]++;
      handleTypeCycle(t);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[27]++;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[14]++;}

    super.resolveInternal(t, enclosing);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[28]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isResolved()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[15]++;
      finishPropertyContinuations();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[30]++;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[16]++;}
    return registry.isLastGeneration() ?
        getReferencedType() : this;
  }

  /**
   * Resolves a named type by looking it up in the registry.
   * @return True if we resolved successfully.
   */
  private boolean resolveViaRegistry(ErrorReporter reporter) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[31]++;
    JSType type = registry.getType(reference);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[17]++;
      setReferencedAndResolvedType(type, reporter);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[33]++;
      return true;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[18]++;}
    return false;
  }

  /**
   * Resolves a named type by looking up its first component in the scope, and
   * subsequent components as properties. The scope must have been fully
   * parsed and a symbol table constructed.
   */
  private void resolveViaProperties(ErrorReporter reporter,
                                    StaticScope<JSType> enclosing) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[34]++;
    JSType value = lookupViaProperties(reporter, enclosing);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
    // last component of the chain
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (128)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((value.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((value.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((value.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[19]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[36]++;
      FunctionType functionType = value.toMaybeFunctionType();
      setReferencedAndResolvedType(functionType.getInstanceType(), reporter);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[37]++;

    } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[20]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[38]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value.isNoObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[21]++;
      setReferencedAndResolvedType(
          registry.getNativeFunctionType(
              JSTypeNative.NO_OBJECT_TYPE).getInstanceType(), reporter);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[39]++;

    } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[22]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[40]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value instanceof EnumType) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[23]++;
      setReferencedAndResolvedType(
          ((EnumType) value).getElementsType(), reporter);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[41]++;

    } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[24]++;
      // We've been running into issues where people forward-declare
      // non-named types. (This is legitimate...our dependency management
      // code doubles as our forward-declaration code.)
      //
      // So if the type does resolve to an actual value, but it's not named,
      // then don't respect the forward declaration.
      handleUnresolvedType(reporter, value == null || value.isUnknownType());
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[42]++;
    }
}
}
  }

  /**
   * Resolves a type by looking up its first component in the scope, and
   * subsequent components as properties. The scope must have been fully
   * parsed and a symbol table constructed.
   * @return The type of the symbol, or null if the type could not be found.
   */
  private JSType lookupViaProperties(ErrorReporter reporter,
      StaticScope<JSType> enclosing) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[43]++;
    String[] componentNames = reference.split("\\.", -1);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[44]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((componentNames[0].length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[25]++;
      return null;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[26]++;}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[45]++;
    StaticSlot<JSType> slot = enclosing.getSlot(componentNames[0]);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[46]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((slot == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[27]++;
      return null;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[28]++;}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[47]++;
    // If the first component has a type of 'Unknown', then any type
    // names using it should be regarded as silently 'Unknown' rather than be
    // noisy about it.
    JSType slotType = slot.getType();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[48]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((slotType == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((slotType.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((slotType.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[29]++;
      return null;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[30]++;}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[49]++;
    JSType value = getTypedefType(reporter, slot);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[50]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[31]++;
      return null;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[32]++;}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[51]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;

    // resolving component by component
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < componentNames.length) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[4]--;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[5]--;
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.loops[6]++;
}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[52]++;
      ObjectType parentClass = ObjectType.cast(value);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[53]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((parentClass == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[33]++;
        return null;

      } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[34]++;}
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[54]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((componentNames[i].length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[35]++;
        return null;

      } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[36]++;}
      value = parentClass.getPropertyType(componentNames[i]);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[55]++;
    }
    return value;
  }

  private void setReferencedAndResolvedType(
      JSType type, ErrorReporter reporter) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[56]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((validator != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[37]++;
      validator.apply(type);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[57]++;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[38]++;}
    setReferencedType(type);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[58]++;
    checkEnumElementCycle(reporter);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[59]++;
    checkProtoCycle(reporter);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[60]++;
    setResolvedTypeInternal(getReferencedType());
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[61]++;
  }

  private void handleTypeCycle(ErrorReporter t) {
    setReferencedType(
        registry.getNativeObjectType(JSTypeNative.UNKNOWN_TYPE));
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[62]++;
    t.warning("Cycle detected in inheritance chain of type " + reference,
        sourceName, lineno, charno);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[63]++;
    setResolvedTypeInternal(getReferencedType());
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[64]++;
  }

  private void checkEnumElementCycle(ErrorReporter t) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[65]++;
    JSType referencedType = getReferencedType();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[66]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((referencedType instanceof EnumElementType) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((((EnumElementType) referencedType).getPrimitiveType() == this) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[39]++;
      handleTypeCycle(t);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[67]++;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[40]++;}
  }

  private void checkProtoCycle(ErrorReporter t) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[68]++;
    JSType referencedType = getReferencedType();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[69]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((referencedType == this) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[41]++;
      handleTypeCycle(t);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[70]++;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[42]++;}
  }

  // Warns about this type being unresolved iff it's not a forward-declared
  // type name.
  private void handleUnresolvedType(
      ErrorReporter t, boolean ignoreForwardReferencedTypes) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[71]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((registry.isLastGeneration()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[43]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[72]++;
      boolean isForwardDeclared =
          ignoreForwardReferencedTypes &&
          registry.isForwardDeclaredType(reference);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[73]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((isForwardDeclared) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((registry.isLastGeneration()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[45]++;
        t.warning("Bad type annotation. Unknown type " + reference,
            sourceName, lineno, charno);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[74]++;

      } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[46]++;
        setReferencedType(
            registry.getNativeObjectType(
                JSTypeNative.NO_RESOLVED_TYPE));
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[75]++;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[76]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((registry.isLastGeneration()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((validator != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[47]++;
          validator.apply(getReferencedType());
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[77]++;

        } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[48]++;}
      }

      setResolvedTypeInternal(getReferencedType());
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[78]++;

    } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[44]++;
      setResolvedTypeInternal(this);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[79]++;
    }
  }

  private JSType getTypedefType(ErrorReporter t, StaticSlot<JSType> slot) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[80]++;
    JSType type = slot.getType();
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[81]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[49]++;
      return type;

    } else {
  CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[50]++;}
    handleUnresolvedType(t, true);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[82]++;
    return null;
  }

  @Override
  public boolean setValidator(Predicate<JSType> validator) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[83]++;
int CodeCoverConditionCoverageHelper_C27;
    // If the type is already resolved, we can validate it now. If
    // the type has not been resolved yet, we need to wait till its
    // resolved before we can validate it.
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.isResolved()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[51]++;
      return super.setValidator(validator);

    } else {
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.branches[52]++;
      this.validator = validator;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[84]++;
      return true;
    }
  }

  /** Store enough information to define a property at a later time. */
  private static final class PropertyContinuation {
    private final String propertyName;
    private final JSType type;
    private final boolean inferred;
    private final Node propertyNode;

    private PropertyContinuation(
        String propertyName,
        JSType type,
        boolean inferred,
        Node propertyNode) {
      this.propertyName = propertyName;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[85]++;
      this.type = type;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[86]++;
      this.inferred = inferred;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[87]++;
      this.propertyNode = propertyNode;
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[88]++;
    }

    void commit(ObjectType target) {
      target.defineProperty(
          propertyName, type, inferred, propertyNode);
CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t.statements[89]++;
    }
  }
}

class CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t ());
  }
    public static long[] statements = new long[90];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.NamedType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,3,2,1,1,1,3,1,1,1,1,1,2,1,1,2,2,1,1};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$3axcieezrn4dqcmc8tqh5t () {
    super("com.google.javascript.rhino.jstype.NamedType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 89; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.NamedType.java");
      for (int i = 1; i <= 89; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

