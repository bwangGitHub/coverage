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
import com.google.common.collect.ImmutableList;

import java.io.Serializable;

/**
 * Manages a mapping from TemplateType to its resolved JSType. Provides utility
 * methods for cloning/extending the map.
 *
 * @author izaakr@google.com (Izaak Rubin)
 */
public class TemplateTypeMap implements Serializable {
  static {
    CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.ping();
  }

  private final ImmutableList<String> templateKeys;
  private final ImmutableList<JSType> templateValues;
  final JSTypeRegistry registry;

  TemplateTypeMap(JSTypeRegistry registry,
                  ImmutableList<String> templateKeys,
                  ImmutableList<JSType> templateValues) {
    Preconditions.checkNotNull(templateKeys);
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[1]++;
    Preconditions.checkNotNull(templateValues);
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[2]++;

    this.registry = registry;
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[3]++;
    this.templateKeys = templateKeys;
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[4]++;
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[5]++;

    int nKeys = templateKeys.size();
    this.templateValues = templateValues.size() > nKeys ?
        templateValues.subList(0, nKeys) : templateValues;
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[6]++;
  }

  /**
   * Returns a list of all template keys.
   */
  public ImmutableList<String> getTemplateKeys() {
    return templateKeys;
  }

  /**
   * Returns true if this map contains the specified template key, false
   * otherwise.
   */
  public boolean hasTemplateKey(String templateKey) {
    return templateKeys.contains(templateKey);
  }

  /**
   * Returns the number of template keys in this map that do not have a
   * corresponding JSType value.
   */
  int numUnfilledTemplateKeys() {
    return templateKeys.size() - templateValues.size();
  }

  /**
   * Returns a list of template keys in this map that do not have corresponding
   * JSType values.
   */
  ImmutableList<String> getUnfilledTemplateKeys() {
    return templateKeys.subList(templateValues.size(), templateKeys.size());
  }

  /**
   * Returns true if there is a JSType value associated with the specified
   * template key; false otherwise.
   */
  public boolean hasTemplateType(String key) {
    return getTemplateTypeInternal(key) != null;
  }

  /**
   * Returns the JSType value associated with the specified template key. If no
   * JSType value is associated, returns UNKNOWN_TYPE.
   */
  public JSType getTemplateType(String key) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[7]++;
    JSType templateType = getTemplateTypeInternal(key);
    return (templateType == null) ?
        registry.getNativeType(JSTypeNative.UNKNOWN_TYPE) : templateType;
  }

  /**
   * Returns the JSType value associated with the specified template key. If no
   * JSType value is associated, returns null.
   */
  private JSType getTemplateTypeInternal(String key) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[8]++;
    int index = templateKeys.indexOf(key);
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((index >= templateValues.size()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[1]++;
      return null;

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[2]++;}
    return templateValues.get(index);
  }

  /**
   * Determines if this map and the specified map have equivalent template
   * types.
   */
  public boolean checkEquivalenceHelper(
      TemplateTypeMap that, EquivalenceMethod eqMethod) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[10]++;
    int thisNumKeys = templateKeys.size();
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[11]++;
    int thatNumKeys = that.getTemplateKeys().size();
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < Math.min(thisNumKeys, thatNumKeys)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[1]--;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[2]--;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[3]++;
}
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[13]++;
      JSType thisTemplateType = getTemplateType(templateKeys.get(i));
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[14]++;
      JSType thatTemplateType = that.getTemplateType(
          that.getTemplateKeys().get(i));
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((thisTemplateType.checkEquivalenceHelper(
          thatTemplateType, eqMethod)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[3]++;
        return false;

      } else {
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[4]++;}
    }

    return thisNumKeys == thatNumKeys ||
        eqMethod == EquivalenceMethod.INVARIANT;
  }

  /**
   * Returns a new TemplateTypeMap whose keys have been extended with the
   * specified list.
   */
  TemplateTypeMap extendKeys(ImmutableList<String> newKeys) {
    return registry.createTemplateTypeMap(
        concatImmutableLists(templateKeys, newKeys), templateValues);
  }

  /**
   * Returns a new TemplateTypeMap whose values have been extended with the
   * specified list.
   */
  TemplateTypeMap extendValues(ImmutableList<JSType> newValues) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[16]++;
    // Ignore any new template values that will not align with an existing
    // template key.
    int numUnfilledKeys = numUnfilledTemplateKeys();
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((numUnfilledKeys < newValues.size()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[5]++;
      newValues = newValues.subList(0, numUnfilledKeys);
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[18]++;

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[6]++;}

    return registry.createTemplateTypeMap(
        templateKeys, concatImmutableLists(templateValues, newValues));
  }

  /**
   * Concatenates two ImmutableList instances. If either input is empty, the
   * other is returned; otherwise, a new ImmutableList instance is created that
   * contains the contents of both arguments.
   */
  private <T> ImmutableList<T> concatImmutableLists(
    ImmutableList<T> first, ImmutableList<T> second) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((first.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[7]++;
      return second;

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[8]++;}
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((second.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[9]++;
      return first;

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[10]++;}
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[21]++;
    ImmutableList.Builder<T> builder = ImmutableList.builder();
    builder.addAll(first);
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[22]++;
    builder.addAll(second);
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[23]++;
    return builder.build();
  }

  boolean hasAnyTemplateTypesInternal() {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[4]++;


    for (JSType templateValue : templateValues) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[4]--;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[5]--;
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.loops[6]++;
}
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((templateValue.hasAnyTemplateTypes()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[11]++;
        return true;

      } else {
  CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd.branches[12]++;}
    }
    return false;
  }
}

class CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.TemplateTypeMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$9uwic5iewf6bmy0uuxekzbhbbiqxwtd () {
    super("com.google.javascript.rhino.jstype.TemplateTypeMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.TemplateTypeMap.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

