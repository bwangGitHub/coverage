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

/**
 * A "can cast to" relationship visitor.
 */
class CanCastToVisitor implements RelationshipVisitor<Boolean> {
  static {
    CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.ping();
  }


  @Override
  public Boolean caseUnknownType(JSType thisType, JSType thatType) {
    return true;
  }

  @Override
  public Boolean caseNoType(JSType thatType) {
    return true;
  }

  @Override
  public Boolean caseNoObjectType(JSType thatType) {
    return true; // TODO(johnlenz): restrict to objects
  }

  @Override
  public Boolean caseAllType(JSType thatType) {
    return true;
  }

  boolean canCastToUnion(JSType thisType, UnionType unionType) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[1]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[1]++;


    for (JSType type : unionType.getAlternates()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[1]--;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[2]--;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[3]++;
}
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((thisType.visit(this, type)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[1]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[2]++;}
    }
    return false;
  }

  boolean canCastToFunction(JSType thisType, FunctionType functionType) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thisType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[3]++;
      // TODO(johnlenz): visit function parts
      return true;

    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[4]++;
      return thisType.isSubtype(functionType)
          || functionType.isSubtype(thisType);
    }
  }

  private boolean isInterface(JSType type) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[4]++;
    ObjectType objType = type.toObjectType();
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[5]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[6]++;
      JSType constructor = objType.getConstructor();
      return constructor != null && constructor.isInterface();

    } else {
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[6]++;}
    return false;
  }

  Boolean castCastToHelper(JSType thisType, JSType thatType) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((thatType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((thatType.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((thatType.isNoObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thatType.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[7]++;
      return true;

    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[8]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[8]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((thisType.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((thatType.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[9]++;
      return true;
  // TODO(johnlenz): are there any misuses we can catch?
    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[10]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[9]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((isInterface(thisType)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isInterface(thatType)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[11]++;
      return true;
  // TODO(johnlenz): are there any misuses we can catch?
    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[12]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[10]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((thatType.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[13]++;
      return thisType.visit(this,
          thatType.toMaybeEnumElementType().getPrimitiveType());

    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[14]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[11]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((thatType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[15]++;
      return canCastToUnion(thisType, thatType.toMaybeUnionType());

    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[16]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[12]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((thatType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[17]++;
      return canCastToFunction(thisType, thatType.toMaybeFunctionType());

    } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[18]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[13]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((thatType.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[19]++;
      // TODO(johnlenz): once the templated type work is finished,
      // restrict the type parameters.
      return thisType.visit(this,
          thatType.toMaybeTemplatizedType().getReferencedTypeInternal());

    } else {
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[20]++;}
}
}
}
}
}
}

    return thisType.isSubtype(thatType) || thatType.isSubtype(thisType);
  }

  @Override
  public Boolean caseValueType(ValueType thisType, JSType thatType) {
    return castCastToHelper(thisType, thatType);
  }

  @Override
  public Boolean caseObjectType(ObjectType thisType, JSType thatType) {
    return castCastToHelper(thisType, thatType);
  }

  @Override
  public Boolean caseFunctionType(FunctionType thisType, JSType thatType) {
    return castCastToHelper(thisType, thatType);
  }

  @Override
  public Boolean caseUnionType(UnionType thisType, JSType thatType) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[14]++;
    boolean visited = false;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[4]++;


    for (JSType type : thisType.getAlternates()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[4]--;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[5]--;
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.loops[6]++;
}
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[16]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((type.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[21]++;

        // Don't allow if the only match between the types is null or void,
        // otherwise any nullable type would be castable to any other nullable
        // type and we don't want that.
      } else {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[22]++;
        visited = true;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[17]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[18]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type.visit(this, thatType)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[23]++;
          return true;

        } else {
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[24]++;}
      }
    }
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[19]++;
int CodeCoverConditionCoverageHelper_C13;

    // Special case the "null|undefined" union and allow it to be cast
    // to any cast to any type containing allowing either null|undefined.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((visited) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[25]++;
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[20]++;
      JSType NULL_TYPE = thisType.getNativeType(JSTypeNative.NULL_TYPE);
CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.statements[21]++;
      JSType VOID_TYPE = thisType.getNativeType(JSTypeNative.VOID_TYPE);
      return NULL_TYPE.visit(this, thatType) || VOID_TYPE.visit(this, thatType);

    } else {
  CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp.branches[26]++;}

    return false;
  }

  @Override
  public Boolean caseTemplatizedType(
      TemplatizedType thisType, JSType thatType) {
    // TODO(johnlenz): once the templated type work is finished,
    // restrict the type parameters.
    return thisType.getReferencedTypeInternal().visit(this, thatType);
  }

  @Override
  public Boolean caseTemplateType(TemplateType thisType, JSType thatType) {
    return true;
  }

  @Override
  public Boolean caseEnumElementType(
      EnumElementType typeType, JSType thatType) {
    return typeType.getPrimitiveType().visit(this, thatType);
  }
}

class CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.CanCastToVisitor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,2,2,1,1,1,1,2,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$1jyxbzwizcvjv6xkbq8fgv7jqjy2ie0yp () {
    super("com.google.javascript.rhino.jstype.CanCastToVisitor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
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
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.CanCastToVisitor.java");
      for (int i = 1; i <= 21; i++) {
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

