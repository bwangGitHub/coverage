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
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;

/**
 * The bottom Object type, representing the subclass of all objects.
 *
 * Although JavaScript programmers can't explicitly denote the bottom
 * Object type, it comes up in static analysis. For example, if we have:
 * <code>
 * var x = function() {};
 * if (x instanceof Array) {
 *   f(x);
 * }
 * </code>
 * We need to be able to assign {@code x} a type within the {@code f(x)}
 * call. It has no possible type, but {@code x} would not be legal if f
 * expected a string. So we assign it the {@code NoObjectType}.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Bottom_type">Bottom types</a>
 */
public class NoObjectType extends FunctionType {
  static {
    CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.statements[1]++;
  }

  NoObjectType(JSTypeRegistry registry) {
    super(registry, null, null,
          registry.createArrowType(null, null),
          null, null, true, true);
CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.statements[2]++;
    getInternalArrowType().returnType = this;
CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.statements[3]++;
    this.setInstanceType(this);
CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.statements[4]++;
  }

  @Override
  public boolean isSubtype(JSType that) {
CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((JSType.isSubtypeHelper(this, that)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.branches[1]++;
      return true;

    } else {
CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt.branches[2]++;
      return that.isObject() && !that.isNoType() && !that.isNoResolvedType();
    }
  }

  @Override
  public FunctionType toMaybeFunctionType() {
    return null;
  }

  @Override
  public boolean isNoObjectType() {
    return true;
  }

  @Override
  public ObjectType getImplicitPrototype() {
    return null;
  }

  @Override
  public String getReferenceName() {
    return null;
  }

  @Override
  public boolean matchesNumberContext() {
    return true;
  }

  @Override
  public boolean matchesObjectContext() {
    return true;
  }

  @Override
  public boolean matchesStringContext() {
    return true;
  }

  @Override
  public int hashCode() {
    return System.identityHashCode(this);
  }

  @Override
  boolean defineProperty(String propertyName, JSType type,
      boolean inferred, Node propertyNode) {
    // nothing, all properties are defined
    return true;
  }

  @Override
  public boolean removeProperty(String name) {
    return false;
  }

  @Override
  public void setPropertyJSDocInfo(String propertyName, JSDocInfo info) {
    // Do nothing, specific properties do not have JSDocInfo.
  }

  @Override
  public <T> T visit(Visitor<T> visitor) {
    return visitor.caseNoObjectType();
  }

  @Override <T> T visit(RelationshipVisitor<T> visitor, JSType that) {
    return visitor.caseNoObjectType(that);
  }

  @Override
  String toStringHelper(boolean forAnnotations) {
    return forAnnotations ? "?" : "NoObject";
  }

  @Override
  public FunctionType getConstructor() {
    return null;
  }

  @Override
  JSType resolveInternal(ErrorReporter t, StaticScope<JSType> scope) {
    return this;
  }
}

class CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.NoObjectType.java";
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

  public CodeCoverCoverageCounter$x0qrwl9xs7idnh5ymnz7lhqvdt () {
    super("com.google.javascript.rhino.jstype.NoObjectType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("com.google.javascript.rhino.jstype.NoObjectType.java");
      for (int i = 1; i <= 5; i++) {
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

