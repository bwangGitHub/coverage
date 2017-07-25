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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * An object type with declared template types, such as
 * <code>Array.<string></code>.
 *
 */
public final class TemplatizedType extends ProxyObjectType {
  static {
    CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[1]++;
  }

  final ImmutableList<JSType> templateTypes;

  TemplatizedType(
      JSTypeRegistry registry, ObjectType objectType,
      ImmutableList<JSType> templateTypes) {
    super(registry, objectType, objectType.getTemplateTypeMap().extendValues(
        templateTypes));
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[2]++;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[3]++;

    // Cache which template keys were filled, and what JSTypes they were filled
    // with.
    ImmutableList<String> filledTemplateKeys =
        objectType.getTemplateTypeMap().getUnfilledTemplateKeys();
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[4]++;
    ImmutableList.Builder<JSType> builder = ImmutableList.builder();
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.loops[1]++;


    for (String filledTemplateKey : filledTemplateKeys) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.loops[1]--;
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.loops[2]--;
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.loops[3]++;
}
      builder.add(getTemplateTypeMap().getTemplateType(filledTemplateKey));
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[6]++;
    }
    this.templateTypes = builder.build();
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[7]++;
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[8]++;
    String typeString = super.toStringHelper(forAnnotations);
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((templateTypes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[1]++;
      typeString += ".<" + Joiner.on(",").join(templateTypes) + ">";
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[10]++;

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[2]++;}

    return typeString;
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseTemplatizedType(this);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseTemplatizedType(this, that);
  }

  @Override
  public TemplatizedType toMaybeTemplatizedType() {
    return this;
  }

  @Override
  public ImmutableList<JSType> getTemplateTypes() {
    return templateTypes;
  }

  //@Override
  public boolean isSubtype(JSType that) {
    return isSubtypeHelper(this, that);
  }

  boolean wrapsSameRawType(JSType that) {
    return that.isTemplatizedType() && this.getReferencedTypeInternal()
        .isEquivalentTo(
            that.toMaybeTemplatizedType().getReferencedTypeInternal());
  }

  boolean wrapsRawType(JSType that) {
    return this.getReferencedTypeInternal().isEquivalentTo(that);
  }

  /**
   * Computes the greatest subtype of two related templatized types.
   * @return The greatest subtype.
   */
  JSType getGreatestSubtypeHelper(JSType rawThat) {
    Preconditions.checkNotNull(rawThat);
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[11]++;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((wrapsSameRawType(rawThat)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[3]++;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((rawThat.isTemplatizedType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[5]++;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.isSubtype(rawThat)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[7]++;
          return this;

        } else {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[8]++;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[15]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((rawThat.isSubtype(this)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[9]++;
          return filterNoResolvedType(rawThat);

        } else {
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[10]++;}
}

      } else {
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[6]++;}
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((this.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rawThat.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[11]++;
        return this.getNativeType(JSTypeNative.NO_OBJECT_TYPE);

      } else {
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[12]++;}
      return this.getNativeType(JSTypeNative.NO_TYPE);

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[4]++;}
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[17]++;

    TemplatizedType that = rawThat.toMaybeTemplatizedType();
    Preconditions.checkNotNull(that);
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[18]++;
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((getTemplateTypeMap().checkEquivalenceHelper(
        that.getTemplateTypeMap(), EquivalenceMethod.INVARIANT)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[13]++;
      return this;

    } else {
  CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox.branches[14]++;}

    // For types that have the same raw type but different type parameters,
    // we simply create a type has a "unknown" type parameter.  This is
    // equivalent to the raw type.
    return getReferencedObjTypeInternal();
  }

  @Override
  public TemplateTypeMap getTemplateTypeMap() {
    return templateTypeMap;
  }

  @Override
  public boolean hasAnyTemplateTypesInternal() {
    return templateTypeMap.hasAnyTemplateTypesInternal();
  }

  /**
   * @return The referenced ObjectType wrapped by this TemplatizedType.
   */
  public ObjectType getReferencedType() {
    return getReferencedObjTypeInternal();
  }
}

class CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.TemplatizedType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$9uwic5iewf6cit3cgvo0ttk5b3ahaox () {
    super("com.google.javascript.rhino.jstype.TemplatizedType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.TemplatizedType.java");
      for (int i = 1; i <= 19; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

