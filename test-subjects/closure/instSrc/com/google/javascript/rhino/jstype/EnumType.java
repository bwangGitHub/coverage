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

import static com.google.javascript.rhino.jstype.TernaryValue.FALSE;
import static com.google.javascript.rhino.jstype.TernaryValue.TRUE;

import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.Node;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An enum type representing a branded collection of elements. Each element
 * is referenced by its name, and has an {@link EnumElementType} type.
 */
public class EnumType extends PrototypeObjectType {
  static {
    CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[1]++;
  }

  /**
   * The object literal or alias which this type represents.
   * It may be {@code null}.
   */
  private final Node source;

  // the type of the individual elements
  private EnumElementType elementsType;
  // the elements' names (they all have the same type)
  private final Set<String> elements = new HashSet<String>();
  {
    CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[2]++;
  }

  /**
   * Creates an enum type.
   *
   * @param name the enum's name
   * @param elementsType the base type of the individual elements
   */
  EnumType(JSTypeRegistry registry, String name, Node source,
      JSType elementsType) {
    super(registry, "enum{" + name + "}", null);
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[3]++;
    this.source = source;
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[4]++;
    this.elementsType = new EnumElementType(registry, elementsType, name);
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[5]++;
  }

  /**
   * Gets the source node or null if this is an unknown enum.
   */
  public Node getSource() {
    return source;
  }

  @Override
  public EnumType toMaybeEnumType() {
    return this;
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return registry.getNativeObjectType(JSTypeNative.OBJECT_TYPE);
  }

  /**
   * Gets the elements defined on this enum.
   * @return the elements' names defined on this enum. The returned set is
   *         immutable.
   */
  public Set<String> getElements() {
    return Collections.unmodifiableSet(elements);
  }

  /**
   * Defines a new element on this enum.
   * @param name the name of the new element
   * @param definingNode the {@code Node} that defines this new element
   * @return true iff the new element is added successfully
   */
  public boolean defineElement(String name, Node definingNode) {
    elements.add(name);
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[6]++;
    return defineDeclaredProperty(name, elementsType, definingNode);
  }

  /**
   * Gets the elements' type.
   */
  public EnumElementType getElementsType() {
    return elementsType;
  }

  @Override
  public TernaryValue testForEquality(JSType that) {
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[7]++;
    TernaryValue result = super.testForEquality(that);
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.branches[1]++;
      return result;

    } else {
  CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.branches[2]++;}
    return this.isEquivalentTo(that) ? TRUE : FALSE;
  }

  @Override
  public boolean isSubtype(JSType that) {
    return that.isEquivalentTo(getNativeType(JSTypeNative.OBJECT_TYPE)) ||
        that.isEquivalentTo(getNativeType(JSTypeNative.OBJECT_PROTOTYPE)) ||
        JSType.isSubtypeHelper(this, that);
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return forAnnotations ? "Object" : getReferenceName();
  }

  @Override
  public String getDisplayName() {
    return elementsType.getDisplayName();
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseObjectType(this);
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseObjectType(this, that);
  }

  @Override
  public FunctionType getConstructor() {
    return null;
  }

  @Override
  public boolean matchesNumberContext() {
    return false;
  }

  @Override
  public boolean matchesStringContext() {
    return true;
  }

  @Override
  public boolean matchesObjectContext() {
    return true;
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    elementsType = (EnumElementType) elementsType.resolve(t, scope);
CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl.statements[9]++;
    return super.resolveInternal(t, scope);
  }
}

class CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.EnumType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$etbnojzxiufq3vmcmnvl () {
    super("com.google.javascript.rhino.jstype.EnumType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.EnumType.java");
      for (int i = 1; i <= 9; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

