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
import com.google.javascript.rhino.Node;

/**
 * An object type that is an instance of some function constructor.
 */
class InstanceObjectType extends PrototypeObjectType {
  static {
    CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[1]++;
  }

  private final FunctionType constructor;

  InstanceObjectType(JSTypeRegistry registry, FunctionType constructor) {
    this(registry, constructor, false);
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[2]++;
  }

  InstanceObjectType(JSTypeRegistry registry, FunctionType constructor,
                     boolean isNativeType) {
    super(registry, null, null, isNativeType, constructor.getTemplateTypeMap());
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[3]++;
    Preconditions.checkNotNull(constructor);
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[4]++;
    this.constructor = constructor;
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[5]++;
  }

  @Override
  public String getReferenceName() {
    return getConstructor().getReferenceName();
  }

  @Override
  public boolean hasReferenceName() {
    return getConstructor().hasReferenceName();
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return getConstructor().getPrototype();
  }

  @Override
  public FunctionType getConstructor() {
    return constructor;
  }

  @Override
  boolean defineProperty(String name, JSType type, boolean inferred,
      Node propertyNode) {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[6]++;
    ObjectType proto = getImplicitPrototype();
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((proto != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((proto.hasOwnDeclaredProperty(name)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.branches[2]++;}
    return super.defineProperty(name, type, inferred, propertyNode);
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((constructor.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.branches[3]++;
      return constructor.getReferenceName();

    } else {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.branches[4]++;
      return super.toStringHelper(forAnnotations);
    }
  }

  @Override
  boolean isTheObjectType() {
    return getConstructor().isNativeObjectType()
        && "Object".equals(getReferenceName());
  }

  @Override
  public boolean isInstanceType() {
    return true;
  }

  @Override
  public boolean isArrayType() {
    return getConstructor().isNativeObjectType()
        && "Array".equals(getReferenceName());
  }

  @Override
  public boolean isStringObjectType() {
    return getConstructor().isNativeObjectType()
        && "String".equals(getReferenceName());
  }

  @Override
  public boolean isBooleanObjectType() {
    return getConstructor().isNativeObjectType()
        && "Boolean".equals(getReferenceName());
  }

  @Override
  public boolean isNumberObjectType() {
    return getConstructor().isNativeObjectType()
        && "Number".equals(getReferenceName());
  }

  @Override
  public boolean isDateType() {
    return getConstructor().isNativeObjectType()
        && "Date".equals(getReferenceName());
  }

  @Override
  public boolean isRegexpType() {
    return getConstructor().isNativeObjectType()
        && "RegExp".equals(getReferenceName());
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
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.branches[5]++;
      return getReferenceName().hashCode();

    } else {
CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd.branches[6]++;
      return super.hashCode();
    }
  }

  @Override
  public Iterable<ObjectType> getCtorImplementedInterfaces() {
    return getConstructor().getImplementedInterfaces();
  }

  @Override
  public Iterable<ObjectType> getCtorExtendedInterfaces() {
    return getConstructor().getExtendedInterfaces();
  }

  // The owner will always be a resolved type, so there's no need to set
  // the constructor in resolveInternal.
  // (it would lead to infinite loops if we did).
  // JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope);
}

class CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.InstanceObjectType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1};
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

  public CodeCoverCoverageCounter$2dogh3thucz0qxfubyhn6pda4kc6jf8xxkdd () {
    super("com.google.javascript.rhino.jstype.InstanceObjectType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.InstanceObjectType.java");
      for (int i = 1; i <= 9; i++) {
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

