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

import com.google.common.collect.ImmutableList;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

import java.util.Collections;

/**
 * An object type which uses composition to delegate all calls.
 *
 * @see NamedType
 * @see TemplatizedType
 *
 */
class ProxyObjectType extends ObjectType {
  static {
    CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[1]++;
  }

  private JSType referencedType;
  private ObjectType referencedObjType;

  ProxyObjectType(JSTypeRegistry registry, JSType referencedType) {
    this(registry, referencedType, null);
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[2]++;
  }

  ProxyObjectType(JSTypeRegistry registry, JSType referencedType,
                  TemplateTypeMap templateTypeMap) {
    super(registry, templateTypeMap);
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[3]++;
    setReferencedType(referencedType);
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[4]++;
  }

  @Override
  PropertyMap getPropertyMap() {
    return referencedObjType == null
        ? PropertyMap.immutableEmptyMap() : referencedObjType.getPropertyMap();
  }

  JSType getReferencedTypeInternal() {
    return referencedType;
  }

  ObjectType getReferencedObjTypeInternal() {
    return referencedObjType;
  }

  void setReferencedType(JSType referencedType) {
    this.referencedType = referencedType;
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[5]++;
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((referencedType instanceof ObjectType) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[1]++;
      this.referencedObjType = (ObjectType) referencedType;
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[7]++;

    } else {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[2]++;
      this.referencedObjType = null;
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[8]++;
    }
  }

  @Override
  public String getReferenceName() {
    return referencedObjType == null ?
        "" : referencedObjType.getReferenceName();
  }

  @Override
  public boolean hasReferenceName() {
    return referencedObjType == null ?
        null : referencedObjType.hasReferenceName();
  }

  @Override
  public boolean matchesNumberContext() {
    return referencedType.matchesNumberContext();
  }

  @Override
  public boolean matchesStringContext() {
    return referencedType.matchesStringContext();
  }

  @Override
  public boolean matchesObjectContext() {
    return referencedType.matchesObjectContext();
  }

  @Override
  public boolean canBeCalled() {
    return referencedType.canBeCalled();
  }

  @Override
  public boolean isNoType() {
    return referencedType.isNoType();
  }

  @Override
  public boolean isNoObjectType() {
    return referencedType.isNoObjectType();
  }

  @Override
  public boolean isNoResolvedType() {
    return referencedType.isNoResolvedType();
  }

  @Override
  public boolean isUnknownType() {
    return referencedType.isUnknownType();
  }

  @Override
  public boolean isCheckedUnknownType() {
    return referencedType.isCheckedUnknownType();
  }

  @Override
  public boolean isNullable() {
    return referencedType.isNullable();
  }

  @Override
  public EnumType toMaybeEnumType() {
    return referencedType.toMaybeEnumType();
  }

  @Override
  public boolean isConstructor() {
    return referencedType.isConstructor();
  }

  @Override
  public boolean isNominalType() {
    return referencedType.isNominalType();
  }

  @Override
  public boolean isInstanceType() {
    return referencedType.isInstanceType();
  }

  @Override
  public boolean isInterface() {
    return referencedType.isInterface();
  }

  @Override
  public boolean isOrdinaryFunction() {
    return referencedType.isOrdinaryFunction();
  }

  @Override
  public boolean isAllType() {
    return referencedType.isAllType();
  }

  @Override
  public boolean isStruct() {
    return referencedType.isStruct();
  }

  @Override
  public boolean isDict() {
    return referencedType.isDict();
  }

  @Override
  public boolean isNativeObjectType() {
    return referencedObjType == null
        ? false : referencedObjType.isNativeObjectType();
  }

  @Override
  RecordType toMaybeRecordType() {
    return referencedType.toMaybeRecordType();
  }

  @Override
  public UnionType toMaybeUnionType() {
    return referencedType.toMaybeUnionType();
  }

  @Override
  public FunctionType toMaybeFunctionType() {
    return referencedType.toMaybeFunctionType();
  }

  @Override
  public EnumElementType toMaybeEnumElementType() {
    return referencedType.toMaybeEnumElementType();
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
    return referencedType.testForEquality(that);
  }

  @Override
  public boolean isSubtype(JSType that) {
    return referencedType.isSubtype(that);
  }

  @Override
  public FunctionType getOwnerFunction() {
    return referencedObjType == null
        ? null : referencedObjType.getOwnerFunction();
  }

  @Override
  public Iterable<ObjectType> getCtorImplementedInterfaces() {
    return referencedObjType == null ? Collections.<ObjectType>emptyList() :
        referencedObjType.getCtorImplementedInterfaces();
  }

  @Override
  public int hashCode() {
    return referencedType.hashCode();
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return referencedType.toStringHelper(forAnnotations);
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return referencedObjType == null ? null :
        referencedObjType.getImplicitPrototype();
  }

  @Override
  boolean defineProperty(String propertyName, JSType type,
      boolean inferred, Node propertyNode) {
    return referencedObjType == null ? true :
        referencedObjType.defineProperty(
            propertyName, type, inferred, propertyNode);
  }

  @Override
  public boolean removeProperty(String name) {
    return referencedObjType == null ? false :
        referencedObjType.removeProperty(name);
  }

  @Override
  public JSType findPropertyType(String propertyName) {
    return referencedType.findPropertyType(propertyName);
  }

  @Override
  public JSDocInfo getJSDocInfo() {
    return referencedType.getJSDocInfo();
  }

  @Override
  public void setJSDocInfo(JSDocInfo info) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((referencedObjType != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[3]++;
      referencedObjType.setJSDocInfo(info);
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[10]++;

    } else {
  CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[4]++;}
  }

  @Override
  public void setPropertyJSDocInfo(String propertyName, JSDocInfo info) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((referencedObjType != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[5]++;
      referencedObjType.setPropertyJSDocInfo(propertyName, info);
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[12]++;

    } else {
  CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[6]++;}
  }

  @Override
  public FunctionType getConstructor() {
    return referencedObjType == null ? null :
        referencedObjType.getConstructor();
  }

  @Override
  public ImmutableList<JSType> getTemplateTypes() {
    return referencedObjType == null ? null :
        referencedObjType.getTemplateTypes();
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return referencedType.visit(visitor);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return referencedType.visit(visitor, that);
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    setReferencedType(referencedType.resolve(t, scope));
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[13]++;
    return this;
  }

  @Override
  public String toDebugHashCodeString() {
    return "{proxy:" + referencedType.toDebugHashCodeString() + "}";
  }

  @Override
  public JSType getTypeOfThis() {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((referencedObjType != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[7]++;
      return referencedObjType.getTypeOfThis();

    } else {
  CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[8]++;}
    return super.getTypeOfThis();
  }

  @Override
  public JSType collapseUnion() {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((referencedType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[9]++;
      return referencedType.collapseUnion();

    } else {
  CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.branches[10]++;}
    return this;
  }

  @Override
  public void matchConstraint(JSType constraint) {
    referencedType.matchConstraint(constraint);
CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5.statements[16]++;
  }

  @Override
  public TemplatizedType toMaybeTemplatizedType() {
    return referencedType.toMaybeTemplatizedType();
  }

  @Override
  public TemplateType toMaybeTemplateType() {
    return referencedType.toMaybeTemplateType();
  }

  @Override
  public boolean hasAnyTemplateTypesInternal() {
    return referencedType.hasAnyTemplateTypes();
  }

  @Override
  public TemplateTypeMap getTemplateTypeMap() {
    return referencedType.getTemplateTypeMap();
  }
}

class CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5 ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.ProxyObjectType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$9eanhvanu6hncded8m80gsm3e5rt0f5 () {
    super("com.google.javascript.rhino.jstype.ProxyObjectType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.ProxyObjectType.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

