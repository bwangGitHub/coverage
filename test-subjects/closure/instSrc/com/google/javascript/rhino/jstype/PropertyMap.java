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
 *   Nick Santos
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

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Representation for a collection of properties on an object.
 * @author nicksantos@google.com (Nick Santos)
 */
class PropertyMap implements Serializable {
  static {
    CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[1]++;
  }

  private static final PropertyMap EMPTY_MAP = new PropertyMap(
      ImmutableMap.<String, Property>of());
  static {
    CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[2]++;
  }

  private static final Function<ObjectType, PropertyMap> PROP_MAP_FROM_TYPE =
      new Function<ObjectType, PropertyMap>() {
    @Override public PropertyMap apply(ObjectType t) {
      return t.getPropertyMap();
    }
  };
  static {
    CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[3]++;
  }

  // A place to get the inheritance structure.
  // Because the extended interfaces are resolved dynamically, this gets
  // messy :(. If type-resolution was more well-defined, we could
  // just reference primary parents and secondary parents directly.
  private ObjectType parentSource = null;
  {
    CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[4]++;
  }

  // The map of our own properties.
  private final Map<String, Property> properties;

  PropertyMap() {
    this(Maps.<String, Property>newTreeMap());
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[5]++;
  }

  private PropertyMap(Map<String, Property> underlyingMap) {
    this.properties = underlyingMap;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[6]++;
  }

  static PropertyMap immutableEmptyMap() {
    return EMPTY_MAP;
  }

  void setParentSource(ObjectType ownerType) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this != EMPTY_MAP) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[1]++;
      this.parentSource = ownerType;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[8]++;

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[2]++;}
  }

  /** Returns the direct parent of this property map. */
  PropertyMap getPrimaryParent() {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parentSource == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[3]++;
      return null;

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[4]++;}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[10]++;
    ObjectType iProto = parentSource.getImplicitPrototype();
    return iProto == null ? null : iProto.getPropertyMap();
  }

  /**
   * Returns the secondary parents of this property map, for interfaces that
   * need multiple inheritance.
   */
  Iterable<PropertyMap> getSecondaryParents() {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parentSource == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[5]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[6]++;}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[12]++;
    Iterable<ObjectType> extendedInterfaces =
        parentSource.getCtorExtendedInterfaces();
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;

    // Most of the time, this will be empty.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Iterables.isEmpty(extendedInterfaces)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[7]++;
      return ImmutableList.of();

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[8]++;}

    return Iterables.transform(extendedInterfaces, PROP_MAP_FROM_TYPE);
  }

  Property getSlot(String name) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((properties.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[9]++;
      return properties.get(name);

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[10]++;}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[15]++;
    PropertyMap primaryParent = getPrimaryParent();
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((primaryParent != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[11]++;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[17]++;
      Property prop = primaryParent.getSlot(name);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((prop != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[13]++;
        return prop;

      } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[14]++;}

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[12]++;}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[19]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[1]++;


    for (PropertyMap p : getSecondaryParents()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[1]--;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[2]--;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[3]++;
}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[15]++;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[21]++;
        Property prop = p.getSlot(name);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((prop != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[17]++;
          return prop;

        } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[18]++;}

      } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[16]++;}
    }
    return null;
  }

  Property getOwnProperty(String propertyName) {
    return properties.get(propertyName);
  }

  int getPropertiesCount() {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[23]++;
    PropertyMap primaryParent = getPrimaryParent();
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[24]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((primaryParent == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[19]++;
      return this.properties.size();

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[20]++;}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[25]++;
    Set<String> props = Sets.newHashSet();
    collectPropertyNames(props);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[26]++;
    return props.size();
  }

  boolean hasOwnProperty(String propertyName) {
    return properties.get(propertyName) != null;
  }

  boolean hasProperty(String propertyName) {
    return getSlot(propertyName) != null;
  }

  Set<String> getOwnPropertyNames() {
    return properties.keySet();
  }

  void collectPropertyNames(Set<String> props) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[4]++;


    for (String prop : properties.keySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[4]--;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[5]--;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[6]++;
}
      props.add(prop);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[28]++;
    }
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[29]++;
    PropertyMap primaryParent = getPrimaryParent();
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((primaryParent != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[21]++;
      primaryParent.collectPropertyNames(props);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[31]++;

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[22]++;}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[7]++;


    for (PropertyMap p : getSecondaryParents()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[7]--;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[8]--;
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.loops[9]++;
}
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[33]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[23]++;
        p.collectPropertyNames(props);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[34]++;

      } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[24]++;}
    }
  }

  boolean removeProperty(String name) {
    return properties.remove(name) != null;
  }

  void putProperty(String name, Property newProp) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[35]++;
    Property oldProp = properties.get(name);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((oldProp != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[25]++;
      // This is to keep previously inferred JsDoc info, e.g., in a
      // replaceScript scenario.
      newProp.setJSDocInfo(oldProp.getJSDocInfo());
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[37]++;

    } else {
  CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.branches[26]++;}
    properties.put(name, newProp);
CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5.statements[38]++;
  }

  Iterable<Property> values() {
    return properties.values();
  }
}

class CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5 ());
  }
    public static long[] statements = new long[39];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.PropertyMap.java";
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$4rgekcoygsw90k8buku4pfvj5 () {
    super("com.google.javascript.rhino.jstype.PropertyMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 38; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.PropertyMap.java");
      for (int i = 1; i <= 38; i++) {
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

