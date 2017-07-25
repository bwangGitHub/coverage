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

import com.google.common.collect.Maps;
import com.google.javascript.rhino.Node;

import java.util.Collections;
import java.util.HashMap;

/**
 * A builder for record types.
 *
 */
public class RecordTypeBuilder {
  static {
    CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.ping();
  }

  private boolean isEmpty = true;
  {
    CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[1]++;
  }
  private boolean isDeclared = true;
  {
    CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[2]++;
  }
  private final JSTypeRegistry registry;
  private final HashMap<String, RecordProperty> properties = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[3]++;
  }

  public RecordTypeBuilder(JSTypeRegistry registry) {
    this.registry = registry;
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[4]++;
  }

  /** See the comments on RecordType about synthetic types. */
  void setSynthesized(boolean synthesized) {
    isDeclared = !synthesized;
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[5]++;
  }

  /**
   * Adds a property with the given name and type to the record type.
   * @param name the name of the new property
   * @param type the JSType of the new property
   * @param propertyNode the node that holds this property definition
   * @return The builder itself for chaining purposes, or null if there's
   *          a duplicate.
   */
  public RecordTypeBuilder addProperty(String name, JSType type, Node
      propertyNode) {
    isEmpty = false;
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[6]++;
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((properties.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.branches[1]++;
      return null;

    } else {
  CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.branches[2]++;}
    properties.put(name, new RecordProperty(type, propertyNode));
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[8]++;
    return this;
  }

  /**
   * Creates a record.
   * @return The record type.
   */
  public JSType build() {
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
     // If we have an empty record, simply return the object type.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isEmpty) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.branches[3]++;
       return registry.getNativeObjectType(JSTypeNative.OBJECT_TYPE);

    } else {
  CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.branches[4]++;}

    return new RecordType(
        registry, Collections.unmodifiableMap(properties), isDeclared);
  }

  static class RecordProperty {
    private final JSType type;
    private final Node propertyNode;

    RecordProperty(JSType type, Node propertyNode) {
      this.type = type;
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[10]++;
      this.propertyNode = propertyNode;
CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp.statements[11]++;
    }

    public JSType getType() {
      return type;
    }

    public Node getPropertyNode() {
      return propertyNode;
    }
  }
}

class CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.RecordTypeBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$dipdf9l0rckrvs28m1etw67o0k0tkx8jmp () {
    super("com.google.javascript.rhino.jstype.RecordTypeBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.RecordTypeBuilder.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

