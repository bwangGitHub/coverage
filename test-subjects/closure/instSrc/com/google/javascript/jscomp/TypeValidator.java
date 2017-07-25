/*
 * Copyright 2009 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import static com.google.javascript.rhino.jstype.JSTypeNative.ARRAY_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.BOOLEAN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NO_OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_STRING;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeRegistry.OBJECT_INDEX_TEMPLATE;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.UnknownType;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

/**
 * A central reporter for all type violations: places where the programmer
 * has annotated a variable (or property) with one type, but has assigned
 * another type to it.
 *
 * Also doubles as a central repository for all type violations, so that
 * type-based optimizations (like AmbiguateProperties) can be fault-tolerant.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class TypeValidator {
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.ping();
  }


  private final AbstractCompiler compiler;
  private final JSTypeRegistry typeRegistry;
  private final JSType allValueTypes;
  private boolean shouldReport = true;
  {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[1]++;
  }
  private final JSType nullOrUndefined;

  // TODO(nicksantos): Provide accessors to better filter the list of type
  // mismatches. For example, if we pass (Cake|null) where only Cake is
  // allowed, that doesn't mean we should invalidate all Cakes.
  private final List<TypeMismatch> mismatches = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[2]++;
  }

  // User warnings
  private static final String FOUND_REQUIRED =
      "{0}\n" +
      "found   : {1}\n" +
      "required: {2}";
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[3]++;
  }

  // TODO(johnlenz): reenable this after after the next release.
  static final DiagnosticType INVALID_CAST =
      DiagnosticType.disabled("JSC_INVALID_CAST",
          "invalid cast - must be a subtype or supertype\n" +
          "from: {0}\n" +
          "to  : {1}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[4]++;
  }

  static final DiagnosticType TYPE_MISMATCH_WARNING =
      DiagnosticType.warning(
          "JSC_TYPE_MISMATCH",
          "{0}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[5]++;
  }

  static final DiagnosticType MISSING_EXTENDS_TAG_WARNING =
      DiagnosticType.warning(
          "JSC_MISSING_EXTENDS_TAG",
          "Missing @extends tag on type {0}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[6]++;
  }

  static final DiagnosticType DUP_VAR_DECLARATION =
      DiagnosticType.warning("JSC_DUP_VAR_DECLARATION",
          "variable {0} redefined with type {1}, " +
          "original definition at {2}:{3} with type {4}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[7]++;
  }

  static final DiagnosticType HIDDEN_PROPERTY_MISMATCH =
      DiagnosticType.warning("JSC_HIDDEN_PROPERTY_MISMATCH",
          "mismatch of the {0} property type and the type " +
          "of the property it overrides from superclass {1}\n" +
          "original: {2}\n" +
          "override: {3}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[8]++;
  }

  static final DiagnosticType INTERFACE_METHOD_NOT_IMPLEMENTED =
      DiagnosticType.warning(
          "JSC_INTERFACE_METHOD_NOT_IMPLEMENTED",
          "property {0} on interface {1} is not implemented by type {2}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[9]++;
  }

  static final DiagnosticType HIDDEN_INTERFACE_PROPERTY_MISMATCH =
      DiagnosticType.warning(
        "JSC_HIDDEN_INTERFACE_PROPERTY_MISMATCH",
        "mismatch of the {0} property type and the type " +
        "of the property it overrides from interface {1}\n" +
        "original: {2}\n" +
        "override: {3}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[10]++;
  }

  static final DiagnosticType UNKNOWN_TYPEOF_VALUE =
      DiagnosticType.warning("JSC_UNKNOWN_TYPEOF_VALUE", "unknown type: {0}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[11]++;
  }

  static final DiagnosticType ILLEGAL_PROPERTY_ACCESS =
      DiagnosticType.warning("JSC_ILLEGAL_PROPERTY_ACCESS",
                             "Cannot do {0} access on a {1}");
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[12]++;
  }

  static final DiagnosticGroup ALL_DIAGNOSTICS = new DiagnosticGroup(
      INVALID_CAST,
      TYPE_MISMATCH_WARNING,
      MISSING_EXTENDS_TAG_WARNING,
      DUP_VAR_DECLARATION,
      HIDDEN_PROPERTY_MISMATCH,
      INTERFACE_METHOD_NOT_IMPLEMENTED,
      HIDDEN_INTERFACE_PROPERTY_MISMATCH,
      UNKNOWN_TYPEOF_VALUE,
      ILLEGAL_PROPERTY_ACCESS);
  static {
    CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[13]++;
  }

  TypeValidator(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[14]++;
    this.typeRegistry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[15]++;
    this.allValueTypes = typeRegistry.createUnionType(
        STRING_TYPE, NUMBER_TYPE, BOOLEAN_TYPE, NULL_TYPE, VOID_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[16]++;
    this.nullOrUndefined = typeRegistry.createUnionType(
        NULL_TYPE, VOID_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[17]++;
  }

  /**
   * Gets a list of type violations.
   *
   * For each violation, one element is the expected type and the other is
   * the type that is actually found. Order is not significant.
   */
  Iterable<TypeMismatch> getMismatches() {
    return mismatches;
  }

  void setShouldReport(boolean report) {
    this.shouldReport = report;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[18]++;
  }

  // All non-private methods should have the form:
  // expectCondition(NodeTraversal t, Node n, ...);
  // If there is a mismatch, the {@code expect} method should issue
  // a warning and attempt to correct the mismatch, when possible.

  void expectValidTypeofName(NodeTraversal t, Node n, String found) {
    report(JSError.make(t.getSourceName(), n, UNKNOWN_TYPEOF_VALUE, found));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[19]++;
  }

  /**
   * Expect the type to be an object, or a type convertible to object. If the
   * expectation is not met, issue a warning at the provided node's source code
   * position.
   * @return True if there was no warning, false if there was a mismatch.
   */
  boolean expectObject(NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[20]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type.matchesObjectContext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[1]++;
      mismatch(t, n, msg, type, OBJECT_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[21]++;
      return false;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[2]++;}
    return true;
  }

  /**
   * Expect the type to be an object. Unlike expectObject, a type convertible
   * to object is not acceptable.
   */
  void expectActualObject(NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[3]++;
      mismatch(t, n, msg, type, OBJECT_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[23]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[4]++;}
  }

  /**
   * Expect the type to contain an object sometimes. If the expectation is
   * not met, issue a warning at the provided node's source code position.
   */
  void expectAnyObject(NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[24]++;
    JSType anyObjectType = getNativeType(NO_OBJECT_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((anyObjectType.isSubtype(type)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[5]++;
      mismatch(t, n, msg, type, anyObjectType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[26]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[6]++;}
  }

  /**
   * Expect the type to be a string, or a type convertible to string. If the
   * expectation is not met, issue a warning at the provided node's source code
   * position.
   */
  void expectString(NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type.matchesStringContext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[7]++;
      mismatch(t, n, msg, type, STRING_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[28]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[8]++;}
  }

  /**
   * Expect the type to be a number, or a type convertible to number. If the
   * expectation is not met, issue a warning at the provided node's source code
   * position.
   */
  void expectNumber(NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type.matchesNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[9]++;
      mismatch(t, n, msg, type, NUMBER_TYPE);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[30]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[10]++;}
  }

  /**
   * Expect the type to be a valid operand to a bitwise operator. This includes
   * numbers, any type convertible to a number, or any other primitive type
   * (undefined|null|boolean|string).
   */
  void expectBitwiseable(NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((type.matchesNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type.isSubtype(allValueTypes)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[11]++;
      mismatch(t, n, msg, type, allValueTypes);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[32]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[12]++;}
  }

  /**
   * Expect the type to be a number or string, or a type convertible to a number
   * or string. If the expectation is not met, issue a warning at the provided
   * node's source code position.
   */
  void expectStringOrNumber(
      NodeTraversal t, Node n, JSType type, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((type.matchesNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type.matchesStringContext()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[13]++;
      mismatch(t, n, msg, type, NUMBER_STRING);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[34]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[14]++;}
  }

  /**
   * Expect the type to be anything but the null or void type. If the
   * expectation is not met, issue a warning at the provided node's
   * source code position. Note that a union type that includes the
   * void type and at least one other type meets the expectation.
   * @return Whether the expectation was met.
   */
  boolean expectNotNullOrUndefined(
      NodeTraversal t, Node n, JSType type, String msg, JSType expectedType) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (128)) == 0 || true) &&
 ((type.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((type.isSubtype(nullOrUndefined)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((containsForwardDeclaredUnresolvedName(type)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 4) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 4) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[15]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;

      // There's one edge case right now that we don't handle well, and
      // that we don't want to warn about.
      // if (this.x == null) {
      //   this.initializeX();
      //   this.x.foo();
      // }
      // In this case, we incorrectly type x because of how we
      // infer properties locally. See issue 109.
      // http://code.google.com/p/closure-compiler/issues/detail?id=109
      //
      // We do not do this inference globally.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[17]++;
        return true;

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[18]++;}

      mismatch(t, n, msg, type, expectedType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[37]++;
      return false;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[16]++;}
    return true;
  }

  private boolean containsForwardDeclaredUnresolvedName(JSType type) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[19]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[39]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[1]++;


      for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[1]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[2]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[3]++;
}
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((containsForwardDeclaredUnresolvedName(alt)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[21]++;
          return true;

        } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[22]++;}
      }

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[20]++;}
    return type.isNoResolvedType();
  }

  /**
   * Expect that the type of a switch condition matches the type of its
   * case condition.
   */
  void expectSwitchMatchesCase(NodeTraversal t, Node n, JSType switchType,
      JSType caseType) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
    // ECMA-262, page 68, step 3 of evaluation of CaseBlock,
    // but allowing extra autoboxing.
    // TODO(user): remove extra conditions when type annotations
    // in the code base have adapted to the change in the compiler.
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((switchType.canTestForShallowEqualityWith(caseType)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((caseType.autoboxesTo() == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((caseType.autoboxesTo().isSubtype(switchType)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[23]++;
      mismatch(t, n.getFirstChild(),
          "case expression doesn't match switch",
          caseType, switchType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[42]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[24]++;}
  }

  /**
   * Expect that the first type can be addressed with GETELEM syntax,
   * and that the second type is the right type for an index into the
   * first type.
   *
   * @param t The node traversal.
   * @param n The GETELEM node to issue warnings on.
   * @param objType The type of the left side of the GETELEM.
   * @param indexType The type inside the brackets of the GETELEM.
   */
  void expectIndexMatch(NodeTraversal t, Node n, JSType objType,
                        JSType indexType) {
    Preconditions.checkState(n.isGetElem());
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[43]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[44]++;
    Node indexNode = n.getLastChild();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[45]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((objType.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[25]++;
      report(JSError.make(t.getSourceName(), indexNode,
                          ILLEGAL_PROPERTY_ACCESS, "'[]'", "struct"));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[46]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[26]++;}
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[47]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((objType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[27]++;
      expectStringOrNumber(t, indexNode, indexType, "property access");
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[48]++;

    } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[28]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[49]++;
      ObjectType dereferenced = objType.dereference();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[50]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((dereferenced != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((dereferenced
          .getTemplateTypeMap()
          .hasTemplateKey(OBJECT_INDEX_TEMPLATE)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[29]++;
        expectCanAssignTo(t, indexNode, indexType, dereferenced
            .getTemplateTypeMap().getTemplateType(OBJECT_INDEX_TEMPLATE),
            "restricted index type");
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[51]++;

      } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[30]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[52]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((dereferenced != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((dereferenced.isArrayType()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[31]++;
        expectNumber(t, indexNode, indexType, "array access");
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[53]++;

      } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[32]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[54]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((objType.matchesObjectContext()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[33]++;
        expectString(t, indexNode, indexType, "property access");
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[55]++;

      } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[34]++;
        mismatch(t, n, "only arrays or objects can be accessed",
            objType,
            typeRegistry.createUnionType(ARRAY_TYPE, OBJECT_TYPE));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[56]++;
      }
}
}
    }
  }

  /**
   * Expect that the first type can be assigned to a symbol of the second
   * type.
   *
   * @param t The node traversal.
   * @param n The node to issue warnings on.
   * @param rightType The type on the RHS of the assign.
   * @param leftType The type of the symbol on the LHS of the assign.
   * @param owner The owner of the property being assigned to.
   * @param propName The name of the property being assigned to.
   * @return True if the types matched, false otherwise.
   */
  boolean expectCanAssignToPropertyOf(NodeTraversal t, Node n, JSType rightType,
      JSType leftType, Node owner, String propName) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;
    // The NoType check is a hack to make typedefs work OK.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((leftType.isNoType()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((rightType.isSubtype(leftType)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[35]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[58]++;
      // Do not type-check interface methods, because we expect that
      // they will have dummy implementations that do not match the type
      // annotations.
      JSType ownerType = getJSType(owner);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[59]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ownerType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[37]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[60]++;
        FunctionType ownerFn = ownerType.toObjectType().getOwnerFunction();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[61]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((ownerFn.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((rightType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((leftType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[39]++;
          return true;

        } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[40]++;}

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[38]++;}

      mismatch(t, n,
          "assignment to property " + propName + " of " +
          getReadableJSTypeName(owner, true),
          rightType, leftType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[62]++;
      return false;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[36]++;}
    return true;
  }

  /**
   * Expect that the first type can be assigned to a symbol of the second
   * type.
   *
   * @param t The node traversal.
   * @param n The node to issue warnings on.
   * @param rightType The type on the RHS of the assign.
   * @param leftType The type of the symbol on the LHS of the assign.
   * @param msg An extra message for the mismatch warning, if necessary.
   * @return True if the types matched, false otherwise.
   */
  boolean expectCanAssignTo(NodeTraversal t, Node n, JSType rightType,
      JSType leftType, String msg) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[63]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((rightType.isSubtype(leftType)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[41]++;
      mismatch(t, n, msg, rightType, leftType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[64]++;
      return false;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[42]++;}
    return true;
  }

  /**
   * Expect that the type of an argument matches the type of the parameter
   * that it's fulfilling.
   *
   * @param t The node traversal.
   * @param n The node to issue warnings on.
   * @param argType The type of the argument.
   * @param paramType The type of the parameter.
   * @param callNode The call node, to help with the warning message.
   * @param ordinal The argument ordinal, to help with the warning message.
   */
  void expectArgumentMatchesParameter(NodeTraversal t, Node n, JSType argType,
      JSType paramType, Node callNode, int ordinal) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[65]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((argType.isSubtype(paramType)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[43]++;
      mismatch(t, n,
          String.format("actual parameter %d of %s does not match " +
              "formal parameter", ordinal,
              getReadableJSTypeName(callNode.getFirstChild(), false)),
          argType, paramType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[66]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[44]++;}
  }

  /**
   * Expect that the first type can override a property of the second
   * type.
   *
   * @param t The node traversal.
   * @param n The node to issue warnings on.
   * @param overridingType The overriding type.
   * @param hiddenType The type of the property being overridden.
   * @param propertyName The name of the property, for use in the
   *     warning message.
   * @param ownerType The type of the owner of the property, for use
   *     in the warning message.
   */
  void expectCanOverride(NodeTraversal t, Node n, JSType overridingType,
      JSType hiddenType, String propertyName, JSType ownerType) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[67]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((overridingType.isSubtype(hiddenType)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[45]++;
      registerMismatch(overridingType, hiddenType,
          report(t.makeError(n, HIDDEN_PROPERTY_MISMATCH, propertyName,
            ownerType.toString(), hiddenType.toString(),
            overridingType.toString())));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[68]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[46]++;}
  }

  /**
   * Expect that the first type is the direct superclass of the second type.
   *
   * @param t The node traversal.
   * @param n The node where warnings should point to.
   * @param superObject The expected super instance type.
   * @param subObject The sub instance type.
   */
  void expectSuperType(NodeTraversal t, Node n, ObjectType superObject,
      ObjectType subObject) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[69]++;
    FunctionType subCtor = subObject.getConstructor();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[70]++;
    ObjectType implicitProto = subObject.getImplicitPrototype();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[71]++;
    ObjectType declaredSuper =
        implicitProto == null ? null : implicitProto.getImplicitPrototype();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[72]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((declaredSuper != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((superObject instanceof UnknownType) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((declaredSuper.isEquivalentTo(superObject)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[47]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[73]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((declaredSuper.isEquivalentTo(getNativeType(OBJECT_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[49]++;
        registerMismatch(superObject, declaredSuper, report(
            t.makeError(n, MISSING_EXTENDS_TAG_WARNING, subObject.toString())));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[74]++;

      } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[50]++;
        mismatch(t.getSourceName(), n,
            "mismatch in declaration of superclass type",
            superObject, declaredSuper);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[75]++;
      }
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[76]++;
int CodeCoverConditionCoverageHelper_C26;

      // Correct the super type.
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((subCtor.hasCachedValues()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[51]++;
        subCtor.setPrototypeBasedOn(superObject);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[77]++;

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[52]++;}

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[48]++;}
  }

  /**
   * Expect that the first type can be cast to the second type. The first type
   * must have some relationship with the second.
   *
   * @param t The node traversal.
   * @param n The node where warnings should point.
   * @param type The type being cast from.
   * @param castType The type being cast to.
   */
  void expectCanCast(NodeTraversal t, Node n, JSType castType, JSType type) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[78]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((type.canCastTo(castType)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[53]++;
      registerMismatch(type, castType, report(t.makeError(n, INVALID_CAST,
          type.toString(), castType.toString())));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[79]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[54]++;}
  }

  /**
   * Expect that the given variable has not been declared with a type.
   *
   * @param sourceName The name of the source file we're in.
   * @param n The node where warnings should point to.
   * @param parent The parent of {@code n}.
   * @param var The variable that we're checking.
   * @param variableName The name of the variable.
   * @param newType The type being applied to the variable. Mostly just here
   *     for the benefit of the warning.
   * @return The variable we end up with. Most of the time, this will just
   *     be {@code var}, but in some rare cases we will need to declare
   *     a new var with new source info.
   */
  Var expectUndeclaredVariable(String sourceName, CompilerInput input,
      Node n, Node parent, Var var, String variableName, JSType newType) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[80]++;
    Var newVar = var;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[81]++;
    boolean allowDupe = false;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[82]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((NodeUtil.isObjectLitKey(n)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[55]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[83]++;
      JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[84]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[57]++;
        info = parent.getJSDocInfo();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[85]++;

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[58]++;}
      allowDupe =
          info != null && info.getSuppressions().contains("duplicate");
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[86]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[56]++;}
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[87]++;

    JSType varType = var.getType();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[88]++;
int CodeCoverConditionCoverageHelper_C30;

    // Only report duplicate declarations that have types. Other duplicates
    // will be reported by the syntactic scope creator later in the
    // compilation process.
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (128)) == 0 || true) &&
 ((varType != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (32)) == 0 || true) &&
 ((varType != typeRegistry.getNativeType(UNKNOWN_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((newType != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((newType != typeRegistry.getNativeType(UNKNOWN_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 4) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 4) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[59]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[89]++;
int CodeCoverConditionCoverageHelper_C31;
      // If there are two typed declarations of the same variable, that
      // is an error and the second declaration is ignored, except in the
      // case of native types. A null input type means that the declaration
      // was made in TypedScopeCreator#createInitialScope and is a
      // native type. We should redeclare it at the new input site.
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((var.input == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[61]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[90]++;
        Scope s = var.getScope();
        s.undeclare(var);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[91]++;
        newVar = s.declare(variableName, n, varType, input, false);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[92]++;

        n.setJSType(varType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[93]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[94]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[63]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[95]++;
int CodeCoverConditionCoverageHelper_C33;
          if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[65]++;
            n.getFirstChild().setJSType(varType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[96]++;

          } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[66]++;}

        } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[64]++;
          Preconditions.checkState(parent.isFunction());
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[97]++;
          parent.setJSType(varType);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[98]++;
        }

      } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[62]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[99]++;
int CodeCoverConditionCoverageHelper_C34;
        // Always warn about duplicates if the overridden type does not
        // match the original type.
        //
        // If the types match, suppress the warning iff there was a @suppress
        // tag, or if the original declaration was a stub.
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (32)) == 0 || true) &&
 ((allowDupe) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((var.getParentNode().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((newType.isEquivalentTo(varType)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[67]++;
          report(JSError.make(sourceName, n, DUP_VAR_DECLARATION,
              variableName, newType.toString(), var.getInputName(),
              String.valueOf(var.nameNode.getLineno()),
              varType.toString()));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[100]++;

        } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[68]++;}
      }

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[60]++;}

    return newVar;
  }

  /**
   * Expect that all properties on interfaces that this type implements are
   * implemented and correctly typed.
   */
  void expectAllInterfaceProperties(NodeTraversal t, Node n,
      FunctionType type) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[101]++;
    ObjectType instance = type.getInstanceType();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[102]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[4]++;


    for (ObjectType implemented : type.getAllImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[4]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[5]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[6]++;
}
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[103]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((implemented.getImplicitPrototype() != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[69]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[104]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[7]++;


        for (String prop :
             implemented.getImplicitPrototype().getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[7]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[8]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[9]++;
}
          expectInterfaceProperty(t, n, instance, implemented, prop);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[105]++;
        }

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[70]++;}
    }
  }

  /**
   * Expect that the property in an interface that this type implements is
   * implemented and correctly typed.
   */
  private void expectInterfaceProperty(NodeTraversal t, Node n,
      ObjectType instance, ObjectType implementedInterface, String prop) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[106]++;
    StaticSlot<JSType> propSlot = instance.getSlot(prop);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[107]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((propSlot == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[71]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[108]++;
      // Not implemented
      String sourceName = n.getSourceFileName();
      sourceName = sourceName == null ? "" : sourceName;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[109]++;
      registerMismatch(instance, implementedInterface,
          report(JSError.make(sourceName, n,
          INTERFACE_METHOD_NOT_IMPLEMENTED,
          prop, implementedInterface.toString(), instance.toString())));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[110]++;

    } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[72]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[111]++;
      Node propNode = propSlot.getDeclaration() == null ?
          null : propSlot.getDeclaration().getNode();

      // Fall back on the constructor node if we can't find a node for the
      // property.
      propNode = propNode == null ? n : propNode;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[112]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[113]++;

      JSType found = propSlot.getType();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[114]++;
      JSType required
          = implementedInterface.getImplicitPrototype().getPropertyType(prop);
      found = found.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[115]++;
      required = required.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[116]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[117]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((found.isSubtype(required)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[73]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[118]++;
        // Implemented, but not correctly typed
        FunctionType constructor =
            implementedInterface.toObjectType().getConstructor();
        registerMismatch(found, required, report(t.makeError(propNode,
            HIDDEN_INTERFACE_PROPERTY_MISMATCH, prop,
            constructor.getTopMostDefiningType(prop).toString(),
            required.toString(), found.toString())));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[119]++;

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[74]++;}
    }
  }

  /**
   * Report a type mismatch
   */
  private void mismatch(NodeTraversal t, Node n,
                        String msg, JSType found, JSType required) {
    mismatch(t.getSourceName(), n, msg, found, required);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[120]++;
  }

  private void mismatch(NodeTraversal t, Node n,
                        String msg, JSType found, JSTypeNative required) {
    mismatch(t, n, msg, found, getNativeType(required));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[121]++;
  }

  private void mismatch(String sourceName, Node n,
                        String msg, JSType found, JSType required) {
    registerMismatch(found, required, report(
        JSError.make(sourceName, n, TYPE_MISMATCH_WARNING,
                     formatFoundRequired(msg, found, required))));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[122]++;
  }

  private void registerMismatch(JSType found, JSType required, JSError error) {
    // Don't register a mismatch for differences in null or undefined or if the
    // code didn't downcast.
    found = found.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[123]++;
    required = required.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[124]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[125]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((found.isSubtype(required)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((required.isSubtype(found)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[75]++;
      return;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[76]++;}

    mismatches.add(new TypeMismatch(found, required, error));
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[126]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[127]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((found.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((required.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[77]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[128]++;
      FunctionType fnTypeA = found.toMaybeFunctionType();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[129]++;
      FunctionType fnTypeB = required.toMaybeFunctionType();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[130]++;
      Iterator<Node> paramItA = fnTypeA.getParameters().iterator();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[131]++;
      Iterator<Node> paramItB = fnTypeB.getParameters().iterator();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[132]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[10]++;


int CodeCoverConditionCoverageHelper_C40;
      while ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((paramItA.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((paramItB.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[10]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[11]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[12]++;
}
        registerIfMismatch(paramItA.next().getJSType(),
            paramItB.next().getJSType(), error);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[133]++;
      }

      registerIfMismatch(
          fnTypeA.getReturnType(), fnTypeB.getReturnType(), error);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[134]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[78]++;}
  }

  private void registerIfMismatch(
      JSType found, JSType required, JSError error) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[135]++;
int CodeCoverConditionCoverageHelper_C41;
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (32)) == 0 || true) &&
 ((found != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((required != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((found.isSubtype(required)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[79]++;
      registerMismatch(found, required, error);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[136]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[80]++;}
  }

  /**
   * Formats a found/required error message.
   */
  private String formatFoundRequired(String description, JSType found,
      JSType required) {
    return MessageFormat.format(FOUND_REQUIRED, description, found, required);
  }

  /**
   * Given a node, get a human-readable name for the type of that node so
   * that will be easy for the programmer to find the original declaration.
   *
   * For example, if SubFoo's property "bar" might have the human-readable
   * name "Foo.prototype.bar".
   *
   * @param n The node.
   * @param dereference If true, the type of the node will be dereferenced
   *     to an Object type, if possible.
   */
  String getReadableJSTypeName(Node n, boolean dereference) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[137]++;
int CodeCoverConditionCoverageHelper_C42;
    // If we're analyzing a GETPROP, the property may be inherited by the
    // prototype chain. So climb the prototype chain and find out where
    // the property was originally defined.
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[81]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[138]++;
      ObjectType objectType = getJSType(n.getFirstChild()).dereference();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[139]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[83]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[140]++;
        String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[141]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((objectType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((objectType.getConstructor().isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[85]++;
          objectType = FunctionType.getTopDefiningInterface(
              objectType, propName);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[142]++;

        } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[86]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[143]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[13]++;


int CodeCoverConditionCoverageHelper_C45;
          // classes
          while ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((objectType.hasOwnProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[13]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[14]--;
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.loops[15]++;
}
            objectType = objectType.getImplicitPrototype();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[144]++;
          }
        }
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[145]++;
int CodeCoverConditionCoverageHelper_C46;

        // Don't show complex function names or anonymous types.
        // Instead, try to get a human-readable type name.
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (32)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((objectType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((objectType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[87]++;
          return objectType.toString() + "." + propName;

        } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[88]++;}

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[84]++;}

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[82]++;}
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[146]++;

    JSType type = getJSType(n);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[147]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((dereference) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[89]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[148]++;
      ObjectType dereferenced = type.dereference();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[149]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((dereferenced != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[91]++;
        type = dereferenced;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[150]++;

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[92]++;}

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[90]++;}
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[151]++;

    String qualifiedName = n.getQualifiedName();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[152]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (32)) == 0 || true) &&
 ((type.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((type.toObjectType() != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((type.toObjectType().getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[93]++;
      return type.toString();

    } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[94]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[153]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((qualifiedName != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[95]++;
      return qualifiedName;

    } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[96]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[154]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((type.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[97]++;
      // Don't show complex function names.
      return "function";

    } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[98]++;
      return type.toString();
    }
}
}
  }

  /**
   * This method gets the JSType from the Node argument and verifies that it is
   * present.
   */
  private JSType getJSType(Node n) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[155]++;
    JSType jsType = n.getJSType();
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[156]++;
int CodeCoverConditionCoverageHelper_C52;
    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[99]++;
      // TODO(user): This branch indicates a compiler bug, not worthy of
      // halting the compilation but we should log this and analyze to track
      // down why it happens. This is not critical and will be resolved over
      // time as the type checker is extended.
      return getNativeType(UNKNOWN_TYPE);

    } else {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[100]++;
      return jsType;
    }
  }

  private JSType getNativeType(JSTypeNative typeId) {
    return typeRegistry.getNativeType(typeId);
  }

  private JSError report(JSError error) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[157]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((shouldReport) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[101]++;
      compiler.report(error);
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[158]++;

    } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[102]++;}
    return error;
  }

  /**
   * Signals that the first type and the second type have been
   * used interchangeably.
   *
   * Type-based optimizations should take this into account
   * so that they don't wreck code with type warnings.
   */
  static class TypeMismatch {
    final JSType typeA;
    final JSType typeB;
    final JSError src;

    /**
     * It's the responsibility of the class that creates the
     * {@code TypeMismatch} to ensure that {@code a} and {@code b} are
     * non-matching types.
     */
    TypeMismatch(JSType a, JSType b, JSError src) {
      this.typeA = a;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[159]++;
      this.typeB = b;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[160]++;
      this.src = src;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[161]++;
    }

    @Override public boolean equals(Object object) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[162]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((object instanceof TypeMismatch) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[103]++;
CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.statements[163]++;
        TypeMismatch that = (TypeMismatch) object;
        return (that.typeA.isEquivalentTo(this.typeA)
                && that.typeB.isEquivalentTo(this.typeB))
            || (that.typeB.isEquivalentTo(this.typeA)
                && that.typeA.isEquivalentTo(this.typeB));

      } else {
  CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep.branches[104]++;}
      return false;
    }

    @Override public int hashCode() {
      return Objects.hashCode(typeA, typeB);
    }

    @Override public String toString() {
      return "(" + typeA + ", " + typeB + ")";
    }
  }
}

class CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep ());
  }
    public static long[] statements = new long[164];
    public static long[] branches = new long[105];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[55];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TypeValidator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,2,2,3,3,1,1,3,1,1,2,2,1,2,1,3,1,1,1,3,1,1,1,2,1,3,1,1,1,3,1,1,1,2,2,2,3,1,1,2,2,3,1,1,3,1,1,1,1,1};
    for (int i = 1; i <= 54; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$70w79379eru9yfrksfg51bgbslep () {
    super("com.google.javascript.jscomp.TypeValidator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 163; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 104; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 54; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TypeValidator.java");
      for (int i = 1; i <= 163; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 104; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 54; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

