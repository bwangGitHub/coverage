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



import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;

/**
 * The type of individual elements of an enum type
 * (see {@link EnumType}).
 */
public class EnumElementType extends ObjectType {
  static {
    CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[1]++;
  }

  /**
   * The primitive type this enum element type wraps. For instance, in
   * the following code defining the {@code LOCAL_CODES} enum
   * <pre>var LOCAL_CODES = {A: 3, B: 9, C: 8}</pre>
   * the primitive type of the the constants is {@code number}.
   */
  private JSType primitiveType;

  // The primitive type, if it is an object.
  private ObjectType primitiveObjectType;

  private final String name;

  EnumElementType(JSTypeRegistry registry, JSType elementType,
      String name) {
    super(registry);
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[2]++;
    this.primitiveType = elementType;
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[3]++;
    this.primitiveObjectType = elementType.toObjectType();
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[4]++;
    this.name = name;
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[5]++;
  }

  @Override public PropertyMap getPropertyMap() {
    return primitiveObjectType == null
        ? PropertyMap.immutableEmptyMap()
        : primitiveObjectType.getPropertyMap();
  }

  @Override
  public EnumElementType toMaybeEnumElementType() {
    return this;
  }

  @Override
  public boolean matchesNumberContext() {
    return primitiveType.matchesNumberContext();
  }

  @Override
  public boolean matchesStringContext() {
    return primitiveType.matchesStringContext();
  }

  @Override
  public boolean matchesObjectContext() {
    return primitiveType.matchesObjectContext();
  }

  @Override
  public boolean canBeCalled() {
    return primitiveType.canBeCalled();
  }

  @Override
  public boolean isObject() {
    return primitiveType.isObject();
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
    return primitiveType.testForEquality(that);
  }

  /**
   * This predicate determines whether objects of this type can have the null
   * value, and therefore can appear in contexts where null is expected.
   *
   * @return true for everything but Number and Boolean types.
   */
  @Override
  public boolean isNullable() {
    return primitiveType.isNullable();
  }

  @Override
  public boolean isNominalType() {
    return hasReferenceName();
  }

  /**
   * If this is equal to a NamedType object, its hashCode must be equal
   * to the hashCode of the NamedType object.
   */
  @Override
  public int hashCode() {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.branches[1]++;
      return getReferenceName().hashCode();

    } else {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.branches[2]++;
      return super.hashCode();
    }
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return forAnnotations ?
        primitiveType.toString() :
        (getReferenceName() + ".<" + primitiveType + ">");
  }

  @Override
  public String getReferenceName() {
    return name;
  }

  @Override
  public boolean hasReferenceName() {
    return true;
  }

  @Override
  public boolean isSubtype(JSType that) {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((JSType.isSubtypeHelper(this, that)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.branches[3]++;
      return true;

    } else {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.branches[4]++;
      return primitiveType.isSubtype(that);
    }
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseEnumElementType(this);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseEnumElementType(this, that);
  }

  @Override
  boolean defineProperty(String propertyName, JSType type,
      boolean inferred, Node propertyNode) {
    // nothing
    return true;
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return null;
  }

  @Override
  public JSType findPropertyType(String propertyName) {
    return primitiveType.findPropertyType(propertyName);
  }

  @Override
  public FunctionType getConstructor() {
    return primitiveObjectType == null ?
        null : primitiveObjectType.getConstructor();
  }

  @Override
  public JSType autoboxesTo() {
    return primitiveType.autoboxesTo();
  }

  /**
   * Gets the primitive type of this enum element.
   */
  public JSType getPrimitiveType() {
    return primitiveType;
  }

  /**
   * Returns the infimum of a enum element type and another type, or null
   * if the infimum is empty.
   *
   * This can be a little bit weird. For example, suppose you have an enum
   * of {(string|number)}, and you want the greatest subtype of the enum
   * and a {number}.
   *
   * The infimum is non-empty. But at the same time, we don't really have
   * a name for this infimum. It's equivalent to "elements of this enum that
   * are numbers".
   *
   * The best we can do is make up a new type. This is similar to what
   * we do in UnionType#meet, which kind-of-sort-of makes sense, because
   * an EnumElementType is a union of instances of a type.
   */
  JSType meet(JSType that) {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[8]++;
    JSType meetPrimitive = primitiveType.getGreatestSubtype(that);
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((meetPrimitive.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.branches[5]++;
      return null;

    } else {
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.branches[6]++;
      return new EnumElementType(registry, meetPrimitive, name);
    }
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    primitiveType = primitiveType.resolve(t, scope);
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[10]++;
    primitiveObjectType = ObjectType.cast(primitiveType);
CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l.statements[11]++;
    return this;
  }
}

class CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.EnumElementType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$83z1hdj8uaoxa50ufd6pkzbik3hz97l () {
    super("com.google.javascript.rhino.jstype.EnumElementType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.EnumElementType.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

