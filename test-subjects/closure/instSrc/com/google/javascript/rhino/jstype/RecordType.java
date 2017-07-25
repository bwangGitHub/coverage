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

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.RecordTypeBuilder.RecordProperty;

import java.util.Map;
import java.util.Set;

/**
 * A record (structural) type.
 *
 * Subtyping: The subtyping of a record type is defined via structural
 * comparison of a record type's properties. For example, a record
 * type of the form { a : TYPE_1 } is a supertype of a record type
 * of the form { b : TYPE_2, a : TYPE_1 } because B can be assigned to
 * A and matches all constraints. Similarly, a defined type can be assigned
 * to a record type so long as that defined type matches all property
 * constraints of the record type. A record type of the form { a : A, b : B }
 * can be assigned to a record of type { a : A }.
 *
 */
class RecordType extends PrototypeObjectType {
  static {
    CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[1]++;
  }

  private final boolean declared;
  private boolean isFrozen = false;
  {
    CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[2]++;
  }

  RecordType(JSTypeRegistry registry, Map<String, RecordProperty> properties) {
    this(registry, properties, true);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[3]++;
  }

  /**
   * Creates a record type.
   *
   * @param registry The type registry under which this type lives.
   * @param properties A map of all the properties of this record type.
   * @param declared Whether this is a declared or synthesized type.
   *     A synthesized record type is just used for bookkeeping
   *     in the type system. A declared record type was actually used in the
   *     user's program.
   * @throws IllegalStateException if the {@code RecordProperty} associated
   *         with a property is null.
   */
  RecordType(JSTypeRegistry registry, Map<String, RecordProperty> properties,
      boolean declared) {
    super(registry, null, null);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[4]++;
    setPrettyPrint(true);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[5]++;
    this.declared = declared;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[6]++;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[1]++;



    for (String property : properties.keySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[1]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[2]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[3]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[8]++;
      RecordProperty prop = properties.get(property);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((prop == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[1]++;
        throw new IllegalStateException(
            "RecordProperty associated with a property should not be null!");

      } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[2]++;}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((declared) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[3]++;
        defineDeclaredProperty(
            property, prop.getType(), prop.getPropertyNode());
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[11]++;

      } else {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[4]++;
        defineSynthesizedProperty(
            property, prop.getType(), prop.getPropertyNode());
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[12]++;
      }
    }

    // Freeze the record type.
    isFrozen = true;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[13]++;
  }

  /** @return Is this synthesized for internal bookkeeping? */
  boolean isSynthetic() {
    return !declared;
  }

  boolean checkRecordEquivalenceHelper(
      RecordType otherRecord, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[14]++;
    Set<String> keySet = getOwnPropertyNames();
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[15]++;
    Set<String> otherKeySet = otherRecord.getOwnPropertyNames();
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((otherKeySet.equals(keySet)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[5]++;
      return false;

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[6]++;}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[4]++;


    for (String key : keySet) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[4]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[5]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[6]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((otherRecord.getPropertyType(key).checkEquivalenceHelper(
              getPropertyType(key), eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[7]++;
        return false;

      } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[8]++;}
    }
    return true;
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return registry.getNativeObjectType(JSTypeNative.OBJECT_TYPE);
  }

  @Override
  boolean defineProperty(String propertyName, JSType type,
      boolean inferred, Node propertyNode) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isFrozen) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[9]++;
      return false;

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[10]++;}

    return super.defineProperty(propertyName, type, inferred,
        propertyNode);
  }

  JSType getGreatestSubtypeHelper(JSType that) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((that.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[11]++;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[21]++;
      RecordType thatRecord = that.toMaybeRecordType();
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[22]++;
      RecordTypeBuilder builder = new RecordTypeBuilder(registry);
      builder.setSynthesized(true);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[23]++;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[7]++;



      // The greatest subtype consists of those *unique* properties of both
      // record types. If any property conflicts, then the NO_TYPE type
      // is returned.
      for (String property : getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[7]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[8]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[9]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((thatRecord.hasProperty(property)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((thatRecord.getPropertyType(property).isInvariant(
                getPropertyType(property))) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[13]++;
          return registry.getNativeObjectType(JSTypeNative.NO_TYPE);

        } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[14]++;}

        builder.addProperty(property, getPropertyType(property),
            getPropertyNode(property));
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[26]++;
      }
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[27]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[10]++;



      for (String property : thatRecord.getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[10]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[11]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[12]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hasProperty(property)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[15]++;
          builder.addProperty(property, thatRecord.getPropertyType(property),
              thatRecord.getPropertyNode(property));
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[29]++;

        } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[16]++;}
      }

      return builder.build();

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[12]++;}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[30]++;

    JSType greatestSubtype = registry.getNativeType(
        JSTypeNative.NO_OBJECT_TYPE);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[31]++;
    JSType thatRestrictedToObj =
        registry.getNativeType(JSTypeNative.OBJECT_TYPE)
        .getGreatestSubtype(that);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((thatRestrictedToObj.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[17]++;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[33]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[13]++;


      // In this branch, the other type is some object type. We find
      // the greatest subtype with the following algorithm:
      // 1) For each property "x" of this record type, take the union
      //    of all classes with a property "x" with a compatible property type.
      //    and which are a subtype of {@code that}.
      // 2) Take the intersection of all of these unions.
      for (String propName : getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[13]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[14]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[15]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[34]++;
        JSType propType = getPropertyType(propName);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[35]++;
        UnionTypeBuilder builder = new UnionTypeBuilder(registry);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[36]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[16]++;


        for (ObjectType alt :
                 registry.getEachReferenceTypeWithProperty(propName)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[16]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[17]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[18]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[37]++;
          JSType altPropType = alt.getPropertyType(propName);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (128)) == 0 || true) &&
 ((altPropType != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((alt.isEquivalentTo(this)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((alt.isSubtype(that)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((propType.isInvariant(altPropType)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[19]++;
            builder.addAlternate(alt);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[39]++;

          } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[20]++;}
        }
        greatestSubtype = greatestSubtype.getLeastSupertype(builder.build());
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[40]++;
      }

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[18]++;}
    return greatestSubtype;
  }

  @Override
  RecordType toMaybeRecordType() {
    return this;
  }

  @Override
  public boolean isSubtype(JSType that) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[41]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((JSType.isSubtypeHelper(this, that)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[21]++;
      return true;

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[22]++;}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[42]++;
int CodeCoverConditionCoverageHelper_C12;

    // Top of the record types is the empty record, or OBJECT_TYPE.
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((registry.getNativeObjectType(
            JSTypeNative.OBJECT_TYPE).isSubtype(that)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[23]++;
      return true;

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[24]++;}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;

    // A type is a subtype of a record type if it itself is a record
    // type and it has at least the same members as the parent record type
    // with the same types.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((that.isRecordType()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[25]++;
      return false;

    } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[26]++;}

    return RecordType.isSubtype(this, that.toMaybeRecordType());
  }

  /** Determines if typeA is a subtype of typeB */
  static boolean isSubtype(ObjectType typeA, RecordType typeB) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[44]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[19]++;


    // typeA is a subtype of record type typeB iff:
    // 1) typeA has all the properties declared in typeB.
    // 2) And for each property of typeB,
    //    2a) if the property of typeA is declared, it must be equal
    //        to the type of the property of typeB,
    //    2b) otherwise, it must be a subtype of the property of typeB.
    //
    // To figure out why this is true, consider the following pseudo-code:
    // /** @type {{a: (Object,null)}} */ var x;
    // /** @type {{a: !Object}} */ var y;
    // var z = {a: {}};
    // x.a = null;
    //
    // y cannot be assigned to x, because line 4 would violate y's declared
    // properties. But z can be assigned to x. Even though z and y are the
    // same type, the properties of z are inferred--and so an assignment
    // to the property of z would not violate any restrictions on it.
    for (String property : typeB.getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[19]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[20]--;
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.loops[21]++;
}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[45]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((typeA.hasProperty(property)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[27]++;
        return false;

      } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[28]++;}
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[46]++;

      JSType propA = typeA.getPropertyType(property);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[47]++;
      JSType propB = typeB.getPropertyType(property);
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[48]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((typeA.isPropertyTypeDeclared(property)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[29]++;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[49]++;
int CodeCoverConditionCoverageHelper_C16;
        // If one declared property isn't invariant,
        // then the whole record isn't covariant.
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((propA.isInvariant(propB)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[31]++;
          return false;

        } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[32]++;}

      } else {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[30]++;
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.statements[50]++;
int CodeCoverConditionCoverageHelper_C17;
        // If one inferred property isn't a subtype,
        // then the whole record isn't covariant.
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((propA.isSubtype(propB)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[33]++;
          return false;

        } else {
  CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep.branches[34]++;}
      }
    }

    return true;
  }
}

class CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep ());
  }
    public static long[] statements = new long[51];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.RecordType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,3,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$op0utynsnh3vznyvnmw1hep () {
    super("com.google.javascript.rhino.jstype.RecordType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 50; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.RecordType.java");
      for (int i = 1; i <= 50; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
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
      for (int i = 1; i <= 7; i++) {
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

