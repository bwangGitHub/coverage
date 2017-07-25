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
 *   John Lenz
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
import com.google.common.collect.Lists;
import com.google.javascript.rhino.Node;

import java.util.List;

/**
 * A visitor implementation that enables type substitutions.
 *
 * @author johnlenz@google.com (John Lenz)
 */
public class ModificationVisitor implements Visitor<JSType> {
  static {
    CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.ping();
  }


  private final JSTypeRegistry registry;

  public ModificationVisitor(JSTypeRegistry registry) {
    this.registry = registry;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[1]++;
  }

  @Override
  public JSType caseNoType() {
    return getNativeType(JSTypeNative.NO_TYPE);
  }

  @Override
  public JSType caseEnumElementType(EnumElementType type) {
    return type;
  }

  @Override
  public JSType caseAllType() {
    return getNativeType(JSTypeNative.ALL_TYPE);
  }

  @Override
  public JSType caseBooleanType() {
    return getNativeType(JSTypeNative.BOOLEAN_TYPE);
  }

  @Override
  public JSType caseNoObjectType() {
    return getNativeType(JSTypeNative.NO_OBJECT_TYPE);
  }

  @Override
  public JSType caseFunctionType(FunctionType type) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isNativeFunctionType(type)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[1]++;
      return type;

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[2]++;}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;

    // TODO(johnlenz): remove this simplifying assumption...
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type.isOrdinaryFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[3]++;
      return type;

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[4]++;}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[4]++;

    boolean changed = false;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[5]++;

    JSType beforeThis = type.getTypeOfThis();
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[6]++;
    JSType afterThis = coerseToThisType(beforeThis.visit(this));
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((beforeThis != afterThis) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[5]++;
      changed = true;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[8]++;

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[6]++;}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[9]++;

    JSType beforeReturn = type.getReturnType();
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[10]++;
    JSType afterReturn = beforeReturn.visit(this);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((beforeReturn != afterReturn) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[7]++;
      changed = true;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[12]++;

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[8]++;}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[13]++;

    FunctionParamBuilder paramBuilder = new FunctionParamBuilder(registry);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[1]++;


    for (Node paramNode : type.getParameters()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[1]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[2]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[3]++;
}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[15]++;
      JSType beforeParamType = paramNode.getJSType();
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[16]++;
      JSType afterParamType = beforeParamType.visit(this);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((beforeParamType != afterParamType) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[9]++;
        changed = true;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[18]++;

      } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[10]++;}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paramNode.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[11]++;
        paramBuilder.addOptionalParams(afterParamType);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[20]++;

      } else {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[12]++;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[21]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paramNode.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[13]++;
        paramBuilder.addVarArgs(afterParamType);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[22]++;

      } else {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[14]++;
        paramBuilder.addRequiredParams(afterParamType);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[23]++;
      }
}
    }
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[15]++;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[25]++;
      // TODO(johnlenz): should we support preserving template keys?
      FunctionBuilder builder = new FunctionBuilder(registry);
      builder.withParams(paramBuilder);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[26]++;
      builder.withReturnType(afterReturn);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[27]++;
      builder.withTypeOfThis(afterThis);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[28]++;
      return builder.build();

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[16]++;}

    return type;
  }

  private JSType coerseToThisType(JSType type) {
    return type != null ? type : registry.getNativeObjectType(
        JSTypeNative.UNKNOWN_TYPE);
  }

  @Override
  public JSType caseObjectType(ObjectType objType) {
    return objType;
  }

  @Override
  public JSType caseTemplatizedType(TemplatizedType type) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[29]++;
    boolean changed = false;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[30]++;
    ObjectType beforeBaseType = type.getReferencedType();
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[31]++;
    ObjectType afterBaseType = ObjectType.cast(beforeBaseType.visit(this));
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((beforeBaseType != afterBaseType) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[17]++;
      changed = true;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[33]++;

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[18]++;}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[34]++;

    ImmutableList.Builder<JSType> builder = ImmutableList.builder();
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[35]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[4]++;


    for (JSType beforeTemplateType : type.getTemplateTypes()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[4]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[5]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[6]++;
}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[36]++;
      JSType afterTemplateType = beforeTemplateType.visit(this);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((beforeTemplateType != afterTemplateType) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[19]++;
        changed = true;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[38]++;

      } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[20]++;}
      builder.add(afterTemplateType);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[39]++;
    }
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[21]++;
      type = registry.createTemplatizedType(afterBaseType, builder.build());
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[41]++;

    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[22]++;}
    return type;
  }

  @Override
  public JSType caseUnknownType() {
    return getNativeType(JSTypeNative.UNKNOWN_TYPE);
  }

  @Override
  public JSType caseNullType() {
    return getNativeType(JSTypeNative.NULL_TYPE);
  }

  @Override
  public JSType caseNumberType() {
    return getNativeType(JSTypeNative.NUMBER_TYPE);
  }

  @Override
  public JSType caseStringType() {
    return getNativeType(JSTypeNative.STRING_TYPE);
  }

  @Override
  public JSType caseVoidType() {
    return getNativeType(JSTypeNative.VOID_TYPE);
  }

  @Override
  public JSType caseUnionType(UnionType type) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[42]++;
    boolean changed = false;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[43]++;
    List<JSType> results = Lists.newArrayList();
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[44]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[7]++;


    for (JSType alternative : type.getAlternates()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[7]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[8]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[9]++;
}
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[45]++;
      JSType replacement = alternative.visit(this);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((replacement != alternative) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[23]++;
        changed = true;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[47]++;

      } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[24]++;}
      results.add(replacement);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[48]++;
    }
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[25]++;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[50]++;
      UnionTypeBuilder builder = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[51]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[10]++;


      for (JSType alternate : results) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[10]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[11]--;
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.loops[12]++;
}
        builder.addAlternate(alternate);
CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.statements[52]++;
      }
      return builder.build();
  // maybe not a union
    } else {
  CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx.branches[26]++;}

    return type;
  }

  @Override
  public JSType caseTemplateType(TemplateType type) {
    return type;
  }

  private JSType getNativeType(JSTypeNative nativeType) {
    return registry.getNativeType(nativeType);
  }

  private boolean isNativeFunctionType(FunctionType type) {
    return type.isNativeObjectType();
  }
}

class CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx ());
  }
    public static long[] statements = new long[53];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.ModificationVisitor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$huhte0qc37bd3vdgl6ixwkm8sa29p9lm3nrkx () {
    super("com.google.javascript.rhino.jstype.ModificationVisitor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 52; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.ModificationVisitor.java");
      for (int i = 1; i <= 52; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

