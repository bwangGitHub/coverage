/*
 * Copyright 2006 The Closure Compiler Authors.
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
import static com.google.javascript.rhino.jstype.JSTypeNative.NULL_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.NUMBER_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.OBJECT_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.REGEXP_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.STRING_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.type.ReverseAbstractInterpreter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.EnumType;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.TernaryValue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>Checks the types of JS expressions against any declared type
 * information.</p>
 *
 */
public class TypeCheck implements NodeTraversal.Callback, CompilerPass {
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.ping();
  }


  //
  // Internal errors
  //
  static final DiagnosticType UNEXPECTED_TOKEN = DiagnosticType.error(
      "JSC_INTERNAL_ERROR_UNEXPECTED_TOKEN",
      "Internal Error: Don't know how to handle {0}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[1]++;
  }


  //
  // User warnings
  //

  protected static final String OVERRIDING_PROTOTYPE_WITH_NON_OBJECT =
      "overriding prototype with non-object";
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[2]++;
  }

  // TODO(user): make all the non private messages private once the
  // TypedScopeCreator has been merged with the type checker.
  static final DiagnosticType DETERMINISTIC_TEST =
      DiagnosticType.warning(
          "JSC_DETERMINISTIC_TEST",
          "condition always evaluates to {2}\n" +
          "left : {0}\n" +
          "right: {1}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[3]++;
  }

  static final DiagnosticType DETERMINISTIC_TEST_NO_RESULT =
      DiagnosticType.warning(
          "JSC_DETERMINISTIC_TEST_NO_RESULT",
          "condition always evaluates to the same value\n" +
          "left : {0}\n" +
          "right: {1}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[4]++;
  }

  static final DiagnosticType INEXISTENT_ENUM_ELEMENT =
      DiagnosticType.warning(
          "JSC_INEXISTENT_ENUM_ELEMENT",
          "element {0} does not exist on this enum");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[5]++;
  }

  // disabled by default. This one only makes sense if you're using
  // well-typed externs.
  static final DiagnosticType INEXISTENT_PROPERTY =
      DiagnosticType.disabled(
          "JSC_INEXISTENT_PROPERTY",
          "Property {0} never defined on {1}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[6]++;
  }

  protected static final DiagnosticType NOT_A_CONSTRUCTOR =
      DiagnosticType.warning(
          "JSC_NOT_A_CONSTRUCTOR",
          "cannot instantiate non-constructor");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[7]++;
  }

  static final DiagnosticType BIT_OPERATION =
      DiagnosticType.warning(
          "JSC_BAD_TYPE_FOR_BIT_OPERATION",
          "operator {0} cannot be applied to {1}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[8]++;
  }

  static final DiagnosticType NOT_CALLABLE =
      DiagnosticType.warning(
          "JSC_NOT_FUNCTION_TYPE",
          "{0} expressions are not callable");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[9]++;
  }

  static final DiagnosticType CONSTRUCTOR_NOT_CALLABLE =
      DiagnosticType.warning(
          "JSC_CONSTRUCTOR_NOT_CALLABLE",
          "Constructor {0} should be called with the \"new\" keyword");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[10]++;
  }

  static final DiagnosticType FUNCTION_MASKS_VARIABLE =
      DiagnosticType.warning(
          "JSC_FUNCTION_MASKS_VARIABLE",
          "function {0} masks variable (IE bug)");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[11]++;
  }

  static final DiagnosticType MULTIPLE_VAR_DEF = DiagnosticType.warning(
      "JSC_MULTIPLE_VAR_DEF",
      "declaration of multiple variables with shared type information");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[12]++;
  }

  static final DiagnosticType ENUM_DUP = DiagnosticType.error("JSC_ENUM_DUP",
      "enum element {0} already defined");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[13]++;
  }

  static final DiagnosticType ENUM_NOT_CONSTANT =
      DiagnosticType.warning("JSC_ENUM_NOT_CONSTANT",
          "enum key {0} must be a syntactic constant");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[14]++;
  }

  static final DiagnosticType INVALID_INTERFACE_MEMBER_DECLARATION =
      DiagnosticType.warning(
          "JSC_INVALID_INTERFACE_MEMBER_DECLARATION",
          "interface members can only be empty property declarations,"
          + " empty functions{0}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[15]++;
  }

  static final DiagnosticType INTERFACE_FUNCTION_NOT_EMPTY =
      DiagnosticType.warning(
          "JSC_INTERFACE_FUNCTION_NOT_EMPTY",
          "interface member functions must have an empty body");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[16]++;
  }

  static final DiagnosticType CONFLICTING_SHAPE_TYPE =
      DiagnosticType.warning(
          "JSC_CONFLICTING_SHAPE_TYPE",
          "{1} cannot extend this type; {0}s can only extend {0}s");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[17]++;
  }

  static final DiagnosticType CONFLICTING_EXTENDED_TYPE =
      DiagnosticType.warning(
          "JSC_CONFLICTING_EXTENDED_TYPE",
          "{1} cannot extend this type; {0}s can only extend {0}s");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[18]++;
  }

  static final DiagnosticType CONFLICTING_IMPLEMENTED_TYPE =
    DiagnosticType.warning(
        "JSC_CONFLICTING_IMPLEMENTED_TYPE",
        "{0} cannot implement this type; " +
        "an interface can only extend, but not implement interfaces");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[19]++;
  }

  static final DiagnosticType BAD_IMPLEMENTED_TYPE =
      DiagnosticType.warning(
          "JSC_IMPLEMENTS_NON_INTERFACE",
          "can only implement interfaces");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[20]++;
  }

  static final DiagnosticType HIDDEN_SUPERCLASS_PROPERTY =
      DiagnosticType.warning(
          "JSC_HIDDEN_SUPERCLASS_PROPERTY",
          "property {0} already defined on superclass {1}; " +
          "use @override to override it");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[21]++;
  }

  static final DiagnosticType HIDDEN_INTERFACE_PROPERTY =
      DiagnosticType.warning(
          "JSC_HIDDEN_INTERFACE_PROPERTY",
          "property {0} already defined on interface {1}; " +
          "use @override to override it");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[22]++;
  }

  static final DiagnosticType HIDDEN_SUPERCLASS_PROPERTY_MISMATCH =
      DiagnosticType.warning("JSC_HIDDEN_SUPERCLASS_PROPERTY_MISMATCH",
          "mismatch of the {0} property type and the type " +
          "of the property it overrides from superclass {1}\n" +
          "original: {2}\n" +
          "override: {3}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[23]++;
  }

  static final DiagnosticType UNKNOWN_OVERRIDE =
      DiagnosticType.warning(
          "JSC_UNKNOWN_OVERRIDE",
          "property {0} not defined on any superclass of {1}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[24]++;
  }

  static final DiagnosticType INTERFACE_METHOD_OVERRIDE =
      DiagnosticType.warning(
          "JSC_INTERFACE_METHOD_OVERRIDE",
          "property {0} is already defined by the {1} extended interface");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[25]++;
  }

  static final DiagnosticType UNKNOWN_EXPR_TYPE =
      DiagnosticType.warning("JSC_UNKNOWN_EXPR_TYPE",
          "could not determine the type of this expression");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[26]++;
  }

  static final DiagnosticType UNRESOLVED_TYPE =
      DiagnosticType.warning("JSC_UNRESOLVED_TYPE",
          "could not resolve the name {0} to a type");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[27]++;
  }

  static final DiagnosticType WRONG_ARGUMENT_COUNT =
      DiagnosticType.warning(
          "JSC_WRONG_ARGUMENT_COUNT",
          "Function {0}: called with {1} argument(s). " +
          "Function requires at least {2} argument(s){3}.");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[28]++;
  }

  static final DiagnosticType ILLEGAL_IMPLICIT_CAST =
      DiagnosticType.warning(
          "JSC_ILLEGAL_IMPLICIT_CAST",
          "Illegal annotation on {0}. @implicitCast may only be used in " +
          "externs.");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[29]++;
  }

  static final DiagnosticType INCOMPATIBLE_EXTENDED_PROPERTY_TYPE =
      DiagnosticType.warning(
          "JSC_INCOMPATIBLE_EXTENDED_PROPERTY_TYPE",
          "Interface {0} has a property {1} with incompatible types in " +
          "its super interfaces {2} and {3}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[30]++;
  }

  static final DiagnosticType EXPECTED_THIS_TYPE =
      DiagnosticType.warning(
          "JSC_EXPECTED_THIS_TYPE",
          "\"{0}\" must be called with a \"this\" type");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[31]++;
  }

  static final DiagnosticType IN_USED_WITH_STRUCT =
      DiagnosticType.warning("JSC_IN_USED_WITH_STRUCT",
                             "Cannot use the IN operator with structs");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[32]++;
  }

  static final DiagnosticType ILLEGAL_PROPERTY_CREATION =
      DiagnosticType.warning("JSC_ILLEGAL_PROPERTY_CREATION",
                             "Cannot add a property to a struct instance " +
                             "after it is constructed.");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[33]++;
  }

  static final DiagnosticType ILLEGAL_OBJLIT_KEY =
      DiagnosticType.warning(
          "ILLEGAL_OBJLIT_KEY",
          "Illegal key, the object literal is a {0}");
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[34]++;
  }

  static final DiagnosticGroup ALL_DIAGNOSTICS = new DiagnosticGroup(
      DETERMINISTIC_TEST,
      DETERMINISTIC_TEST_NO_RESULT,
      INEXISTENT_ENUM_ELEMENT,
      INEXISTENT_PROPERTY,
      NOT_A_CONSTRUCTOR,
      BIT_OPERATION,
      NOT_CALLABLE,
      CONSTRUCTOR_NOT_CALLABLE,
      FUNCTION_MASKS_VARIABLE,
      MULTIPLE_VAR_DEF,
      ENUM_DUP,
      ENUM_NOT_CONSTANT,
      INVALID_INTERFACE_MEMBER_DECLARATION,
      INTERFACE_FUNCTION_NOT_EMPTY,
      CONFLICTING_SHAPE_TYPE,
      CONFLICTING_EXTENDED_TYPE,
      CONFLICTING_IMPLEMENTED_TYPE,
      BAD_IMPLEMENTED_TYPE,
      HIDDEN_SUPERCLASS_PROPERTY,
      HIDDEN_INTERFACE_PROPERTY,
      HIDDEN_SUPERCLASS_PROPERTY_MISMATCH,
      UNKNOWN_OVERRIDE,
      INTERFACE_METHOD_OVERRIDE,
      UNKNOWN_EXPR_TYPE,
      UNRESOLVED_TYPE,
      WRONG_ARGUMENT_COUNT,
      ILLEGAL_IMPLICIT_CAST,
      INCOMPATIBLE_EXTENDED_PROPERTY_TYPE,
      EXPECTED_THIS_TYPE,
      IN_USED_WITH_STRUCT,
      ILLEGAL_PROPERTY_CREATION,
      ILLEGAL_OBJLIT_KEY,
      RhinoErrorReporter.TYPE_PARSE_ERROR,
      TypedScopeCreator.UNKNOWN_LENDS,
      TypedScopeCreator.LENDS_ON_NON_OBJECT,
      TypedScopeCreator.CTOR_INITIALIZER,
      TypedScopeCreator.IFACE_INITIALIZER,
      FunctionTypeBuilder.THIS_TYPE_NON_OBJECT);
  static {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[35]++;
  }

  private final AbstractCompiler compiler;
  private final TypeValidator validator;

  private final ReverseAbstractInterpreter reverseInterpreter;

  private final JSTypeRegistry typeRegistry;
  private Scope topScope;

  private MemoizedScopeCreator scopeCreator;

  private final CheckLevel reportMissingOverride;
  private final CheckLevel reportUnknownTypes;

  // This may be expensive, so don't emit these warnings if they're
  // explicitly turned off.
  private boolean reportMissingProperties = true;
  {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[36]++;
  }

  private InferJSDocInfo inferJSDocInfo = null;
  {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[37]++;
  }

  // These fields are used to calculate the percentage of expressions typed.
  private int typedCount = 0;
  {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[38]++;
  }
  private int nullCount = 0;
  {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[39]++;
  }
  private int unknownCount = 0;
  {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[40]++;
  }
  private boolean inExterns;

  // A state boolean to see we are currently in @notypecheck section of the
  // code.
  private int noTypeCheckSection = 0;
  {
    CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[41]++;
  }

  public TypeCheck(AbstractCompiler compiler,
      ReverseAbstractInterpreter reverseInterpreter,
      JSTypeRegistry typeRegistry,
      Scope topScope,
      MemoizedScopeCreator scopeCreator,
      CheckLevel reportMissingOverride,
      CheckLevel reportUnknownTypes) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[42]++;
    this.validator = compiler.getTypeValidator();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[43]++;
    this.reverseInterpreter = reverseInterpreter;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[44]++;
    this.typeRegistry = typeRegistry;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[45]++;
    this.topScope = topScope;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[46]++;
    this.scopeCreator = scopeCreator;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[47]++;
    this.reportMissingOverride = reportMissingOverride;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[48]++;
    this.reportUnknownTypes = reportUnknownTypes;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[49]++;
    this.inferJSDocInfo = new InferJSDocInfo(compiler);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[50]++;
  }

  public TypeCheck(AbstractCompiler compiler,
      ReverseAbstractInterpreter reverseInterpreter,
      JSTypeRegistry typeRegistry,
      CheckLevel reportMissingOverride,
      CheckLevel reportUnknownTypes) {
    this(compiler, reverseInterpreter, typeRegistry, null, null,
        reportMissingOverride, reportUnknownTypes);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[51]++;
  }

  TypeCheck(AbstractCompiler compiler,
      ReverseAbstractInterpreter reverseInterpreter,
      JSTypeRegistry typeRegistry) {
    this(compiler, reverseInterpreter, typeRegistry, null, null,
         CheckLevel.WARNING, CheckLevel.OFF);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[52]++;
  }

  /** Turn on the missing property check. Returns this for easy chaining. */
  TypeCheck reportMissingProperties(boolean report) {
    reportMissingProperties = report;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[53]++;
    return this;
  }

  /**
   * Main entry point for this phase of processing. This follows the pattern for
   * JSCompiler phases.
   *
   * @param externsRoot The root of the externs parse tree.
   * @param jsRoot The root of the input parse tree to be checked.
   */
  @Override
  public void process(Node externsRoot, Node jsRoot) {
    Preconditions.checkNotNull(scopeCreator);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[54]++;
    Preconditions.checkNotNull(topScope);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[55]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[56]++;

    Node externsAndJs = jsRoot.getParent();
    Preconditions.checkState(externsAndJs != null);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[57]++;
    Preconditions.checkState(
        externsRoot == null || externsAndJs.hasChild(externsRoot));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[58]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[59]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((externsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[1]++;
      check(externsRoot, true);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[60]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[2]++;}
    check(jsRoot, false);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[61]++;
  }

  /** Main entry point of this phase for testing code. */
  public Scope processForTesting(Node externsRoot, Node jsRoot) {
    Preconditions.checkState(scopeCreator == null);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[62]++;
    Preconditions.checkState(topScope == null);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[63]++;

    Preconditions.checkState(jsRoot.getParent() != null);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[64]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[65]++;
    Node externsAndJsRoot = jsRoot.getParent();

    scopeCreator = new MemoizedScopeCreator(new TypedScopeCreator(compiler));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[66]++;
    topScope = scopeCreator.createScope(externsAndJsRoot, null);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[67]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[68]++;

    TypeInferencePass inference = new TypeInferencePass(compiler,
        reverseInterpreter, topScope, scopeCreator);

    inference.process(externsRoot, jsRoot);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[69]++;
    process(externsRoot, jsRoot);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[70]++;

    return topScope;
  }


  public void check(Node node, boolean externs) {
    Preconditions.checkNotNull(node);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[71]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[72]++;

    NodeTraversal t = new NodeTraversal(compiler, this, scopeCreator);
    inExterns = externs;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[73]++;
    t.traverseWithScope(node, topScope);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[74]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[75]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((externs) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[3]++;
      inferJSDocInfo.process(node, null);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[76]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[4]++;
      inferJSDocInfo.process(null, node);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[77]++;
    }
  }


  private void checkNoTypeCheckSection(Node n, boolean enterSection) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[78]++;
    switch (n.getType()) {
      case Token.SCRIPT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[5]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[6]++;
      case Token.VAR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[7]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[8]++;
      case Token.ASSIGN:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[9]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[79]++;
        JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[80]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((info.isNoTypeCheck()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[10]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[81]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((enterSection) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[12]++;
            noTypeCheckSection++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[82]++;

          } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[13]++;
            noTypeCheckSection--;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[83]++;
          }

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[11]++;}
        validator.setShouldReport(noTypeCheckSection == 0);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[84]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[85]++;
        break; default : CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[14]++;
    }
  }

  private void report(NodeTraversal t, Node n, DiagnosticType diagnosticType,
      String... arguments) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[86]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((noTypeCheckSection == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[15]++;
      t.report(n, diagnosticType, arguments);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[87]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[16]++;}
  }

  @Override
  public boolean shouldTraverse(
      NodeTraversal t, Node n, Node parent) {
    checkNoTypeCheckSection(n, true);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[88]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[89]++;
    switch (n.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[17]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[90]++;
        // normal type checking
        final Scope outerScope = t.getScope();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[91]++;
        final String functionPrivateName = n.getFirstChild().getString();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[92]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (128)) == 0 || true) &&
 ((functionPrivateName != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((functionPrivateName.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((outerScope.isDeclared(functionPrivateName, false)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((outerScope.getVar(
                functionPrivateName).getType() instanceof FunctionType) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 4) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 4) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[18]++;
          report(t, n, FUNCTION_MASKS_VARIABLE, functionPrivateName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[93]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[19]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[94]++;

        // TODO(user): Only traverse the function's body. The function's
        // name and arguments are traversed by the scope creator, and ideally
        // should not be traversed by the type checker.
        break; default : CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[20]++;
    }
    return true;
  }

  /**
   * This is the meat of the type checking.  It is basically one big switch,
   * with each case representing one type of parse tree node.  The individual
   * cases are usually pretty straightforward.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   * @param parent The parent of the node n.
   */
  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
    JSType childType;
    JSType leftType, rightType;
    Node left, right;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[95]++;
    // To be explicitly set to false if the node is not typeable.
    boolean typeable = true;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[96]++;

    switch (n.getType()) {
      case Token.CAST:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[21]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[97]++;
        Node expr = n.getFirstChild();
        ensureTyped(t, n, getJSType(expr));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[98]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[99]++;

        // If the cast, tightens the type apply it, so it is available post
        // normalization.
        JSType castType = getJSType(n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[100]++;
        JSType exprType = getJSType(expr);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[101]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((castType.isSubtype(exprType)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[22]++;
          expr.setJSType(castType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[102]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[23]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[103]++;
        break;

      case Token.NAME:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[24]++;
        typeable = visitName(t, n, parent);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[104]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[105]++;
        break;

      case Token.PARAM_LIST:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[25]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[106]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[107]++;
        break;

      case Token.COMMA:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[26]++;
        ensureTyped(t, n, getJSType(n.getLastChild()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[108]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[109]++;
        break;

      case Token.TRUE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[27]++;
      case Token.FALSE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[28]++;
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[110]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[111]++;
        break;

      case Token.THIS:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[29]++;
        ensureTyped(t, n, t.getScope().getTypeOfThis());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[112]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[113]++;
        break;

      case Token.NULL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[30]++;
        ensureTyped(t, n, NULL_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[114]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[115]++;
        break;

      case Token.NUMBER:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[31]++;
        ensureTyped(t, n, NUMBER_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[116]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[117]++;
        break;

      case Token.STRING:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[32]++;
        ensureTyped(t, n, STRING_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[118]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[119]++;
        break;

      case Token.STRING_KEY:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[33]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[120]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[121]++;
        break;

      case Token.GETTER_DEF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[34]++;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[35]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[122]++;
        // Object literal keys are handled with OBJECTLIT
        break;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[36]++;
        ensureTyped(t, n, ARRAY_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[123]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[124]++;
        break;

      case Token.REGEXP:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[37]++;
        ensureTyped(t, n, REGEXP_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[125]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[126]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[38]++;
        visitGetProp(t, n, parent);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[127]++;
        typeable = !(parent.isAssign() &&
                     parent.getFirstChild() == n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[128]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[129]++;
        break;

      case Token.GETELEM:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[39]++;
        visitGetElem(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[130]++;
        // The type of GETELEM is always unknown, so no point counting that.
        // If that unknown leaks elsewhere (say by an assignment to another
        // variable), then it will be counted.
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[131]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[132]++;
        break;

      case Token.VAR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[40]++;
        visitVar(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[133]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[134]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[135]++;
        break;

      case Token.NEW:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[41]++;
        visitNew(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[136]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[137]++;
        break;

      case Token.CALL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[42]++;
        visitCall(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[138]++;
        typeable = !parent.isExprResult();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[139]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[140]++;
        break;

      case Token.RETURN:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[43]++;
        visitReturn(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[141]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[142]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[143]++;
        break;

      case Token.DEC:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[44]++;
      case Token.INC:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[45]++;
        left = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[144]++;
        checkPropCreation(t, left);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[145]++;
        validator.expectNumber(t, left, getJSType(left), "increment/decrement");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[146]++;
        ensureTyped(t, n, NUMBER_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[147]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[148]++;
        break;

      case Token.NOT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[46]++;
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[149]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[150]++;
        break;

      case Token.VOID:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[47]++;
        ensureTyped(t, n, VOID_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[151]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[152]++;
        break;

      case Token.TYPEOF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[48]++;
        ensureTyped(t, n, STRING_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[153]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[154]++;
        break;

      case Token.BITNOT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[49]++;
        childType = getJSType(n.getFirstChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[155]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[156]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((childType.matchesInt32Context()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[50]++;
          report(t, n, BIT_OPERATION, NodeUtil.opToStr(n.getType()),
              childType.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[157]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[51]++;}
        ensureTyped(t, n, NUMBER_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[158]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[159]++;
        break;

      case Token.POS:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[52]++;
      case Token.NEG:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[53]++;
        left = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[160]++;
        validator.expectNumber(t, left, getJSType(left), "sign operator");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[161]++;
        ensureTyped(t, n, NUMBER_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[162]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[163]++;
        break;

      case Token.EQ:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[54]++;
      case Token.NE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[55]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[56]++;
      case Token.SHNE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[57]++; {
        left = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[164]++;
        right = n.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[165]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[166]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((left.isTypeOf()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[58]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[167]++;
int CodeCoverConditionCoverageHelper_C10;
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[60]++;
            checkTypeofString(t, right, right.getString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[168]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[61]++;}

        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[59]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[169]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((right.isTypeOf()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((left.isString()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[62]++;
          checkTypeofString(t, left, left.getString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[170]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[63]++;}
}

        leftType = getJSType(left);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[171]++;
        rightType = getJSType(right);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[172]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[173]++;

        // We do not want to warn about explicit comparisons to VOID. People
        // often do this if they think their type annotations screwed up.
        //
        // We do want to warn about cases where people compare things like
        // (Array|null) == (Function|null)
        // because it probably means they screwed up.
        //
        // This heuristic here is not perfect, but should catch cases we
        // care about without too many false negatives.
        JSType leftTypeRestricted = leftType.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[174]++;
        JSType rightTypeRestricted = rightType.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[175]++;

        TernaryValue result = TernaryValue.UNKNOWN;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[176]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((n.getType() == Token.EQ) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.getType() == Token.NE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[64]++;
          result = leftTypeRestricted.testForEquality(rightTypeRestricted);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[177]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[178]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.isNE()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[66]++;
            result = result.not();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[179]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[67]++;}

        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[65]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[180]++;
int CodeCoverConditionCoverageHelper_C14;
          // SHEQ or SHNE
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((leftTypeRestricted.canTestForShallowEqualityWith(
                  rightTypeRestricted)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[68]++;
            result = n.getType() == Token.SHEQ ?
                TernaryValue.FALSE : TernaryValue.TRUE;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[181]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[69]++;}
        }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[182]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((result != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[70]++;
          report(t, n, DETERMINISTIC_TEST, leftType.toString(),
              rightType.toString(), result.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[183]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[71]++;}
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[184]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[185]++;
        break;
      }

      case Token.LT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[72]++;
      case Token.LE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[73]++;
      case Token.GT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[74]++;
      case Token.GE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[75]++;
        leftType = getJSType(n.getFirstChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[186]++;
        rightType = getJSType(n.getLastChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[187]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[188]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((rightType.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[76]++;
          validator.expectNumber(
              t, n, leftType, "left side of numeric comparison");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[189]++;

        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[77]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[190]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((leftType.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[78]++;
          validator.expectNumber(
              t, n, rightType, "right side of numeric comparison");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[191]++;

        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[79]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[192]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((leftType.matchesNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((rightType.matchesNumberContext()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[80]++;

          // OK.
        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[81]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[193]++;
          // Whether the comparison is numeric will be determined at runtime
          // each time the expression is evaluated. Regardless, both operands
          // should match a string context.
          String message = "left side of comparison";
          validator.expectString(t, n, leftType, message);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[194]++;
          validator.expectNotNullOrUndefined(
              t, n, leftType, message, getNativeType(STRING_TYPE));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[195]++;
          message = "right side of comparison";
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[196]++;
          validator.expectString(t, n, rightType, message);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[197]++;
          validator.expectNotNullOrUndefined(
              t, n, rightType, message, getNativeType(STRING_TYPE));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[198]++;
        }
}
}
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[199]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[200]++;
        break;

      case Token.IN:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[82]++;
        left = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[201]++;
        right = n.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[202]++;
        rightType = getJSType(right);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[203]++;
        validator.expectString(t, left, getJSType(left), "left side of 'in'");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[204]++;
        validator.expectObject(t, n, rightType, "'in' requires an object");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[205]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[206]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((rightType.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[83]++;
          report(t, right, IN_USED_WITH_STRUCT);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[207]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[84]++;}
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[208]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[209]++;
        break;

      case Token.INSTANCEOF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[85]++;
        left = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[210]++;
        right = n.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[211]++;
        rightType = getJSType(right).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[212]++;
        validator.expectAnyObject(
            t, left, getJSType(left), "deterministic instanceof yields false");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[213]++;
        validator.expectActualObject(
            t, right, rightType, "instanceof requires an object");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[214]++;
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[215]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[216]++;
        break;

      case Token.ASSIGN:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[86]++;
        visitAssign(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[217]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[218]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[219]++;
        break;

      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[87]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[88]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[89]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[90]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[91]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[92]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[93]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[94]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[95]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[96]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[97]++;
        checkPropCreation(t, n.getFirstChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[220]++;
        // fall through

      case Token.LSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[98]++;
      case Token.RSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[99]++;
      case Token.URSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[100]++;
      case Token.DIV:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[101]++;
      case Token.MOD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[102]++;
      case Token.BITOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[103]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[104]++;
      case Token.BITAND:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[105]++;
      case Token.SUB:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[106]++;
      case Token.ADD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[107]++;
      case Token.MUL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[108]++;
        visitBinaryOperator(n.getType(), t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[221]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[222]++;
        break;

      case Token.DELPROP:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[109]++;
        ensureTyped(t, n, BOOLEAN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[223]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[224]++;
        break;

      case Token.CASE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[110]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[225]++;
        JSType switchType = getJSType(parent.getFirstChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[226]++;
        JSType caseType = getJSType(n.getFirstChild());
        validator.expectSwitchMatchesCase(t, n, switchType, caseType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[227]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[228]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[229]++;
        break;

      case Token.WITH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[111]++; {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[230]++;
        Node child = n.getFirstChild();
        childType = getJSType(child);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[231]++;
        validator.expectObject(t, child, childType, "with requires an object");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[232]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[233]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[234]++;
        break;
      }

      case Token.FUNCTION:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[112]++;
        visitFunction(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[235]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[236]++;
        break;

      // These nodes have no interesting type behavior.
      case Token.LABEL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[113]++;
      case Token.LABEL_NAME:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[114]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[115]++;
      case Token.BREAK:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[116]++;
      case Token.CATCH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[117]++;
      case Token.TRY:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[118]++;
      case Token.SCRIPT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[119]++;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[120]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[121]++;
      case Token.EMPTY:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[122]++;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[123]++;
      case Token.CONTINUE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[124]++;
      case Token.DEBUGGER:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[125]++;
      case Token.THROW:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[126]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[237]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[238]++;
        break;

      // These nodes require data flow analysis.
      case Token.DO:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[127]++;
      case Token.IF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[128]++;
      case Token.WHILE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[129]++;
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[239]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[240]++;
        break;

      case Token.FOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[130]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[241]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[131]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[242]++;
          Node obj = n.getChildAtIndex(1);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[243]++;
int CodeCoverConditionCoverageHelper_C21;
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((getJSType(obj).isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[133]++;
            report(t, obj, IN_USED_WITH_STRUCT);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[244]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[134]++;}

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[132]++;}
        typeable = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[245]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[246]++;
        break;

      // These nodes are typed during the type inference.
      case Token.AND:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[135]++;
      case Token.HOOK:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[136]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[137]++;
      case Token.OR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[138]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[247]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[139]++; // If we didn't run type inference.
          ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[248]++;

        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[140]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[249]++;
int CodeCoverConditionCoverageHelper_C23;
          // If this is an enum, then give that type to the objectlit as well.
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((parent.getJSType() instanceof EnumType) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[141]++;
            ensureTyped(t, n, parent.getJSType());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[250]++;

          } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[142]++;
            ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[251]++;
          }
        }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[252]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[143]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[253]++;
          JSType typ = getJSType(n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[254]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[1]++;


          for (Node key : n.children()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[1]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[2]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[3]++;
}
            visitObjLitKey(t, key, n, typ);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[255]++;
          }

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[144]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[256]++;
        break;

      default:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[145]++;
        report(t, n, UNEXPECTED_TOKEN, Token.name(n.getType()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[257]++;
        ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[258]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[259]++;
        break;
    }

    // Don't count externs since the user's code may not even use that part.
    typeable = typeable && !inExterns;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[260]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[261]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((typeable) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[146]++;
      doPercentTypedAccounting(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[262]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[147]++;}

    checkNoTypeCheckSection(n, false);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[263]++;
  }

  private void checkTypeofString(NodeTraversal t, Node n, String s) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[264]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (8192)) == 0 || true) &&
 ((s.equals("number")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4096)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (2048)) == 0 || true) &&
 ((s.equals("string")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1024)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (512)) == 0 || true) &&
 ((s.equals("boolean")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (128)) == 0 || true) &&
 ((s.equals("undefined")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((s.equals("function")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((s.equals("object")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((s.equals("unknown")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 7) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 7) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[148]++;
      validator.expectValidTypeofName(t, n, s);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[265]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[149]++;}
  }

  /**
   * Counts the given node in the typed statistics.
   * @param n a node that should be typed
   */
  private void doPercentTypedAccounting(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[266]++;
    JSType type = n.getJSType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[267]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[150]++;
      nullCount++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[268]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[151]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[269]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[152]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[270]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((reportUnknownTypes.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[154]++;
        compiler.report(
            t.makeError(n, reportUnknownTypes, UNKNOWN_EXPR_TYPE));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[271]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[155]++;}
      unknownCount++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[272]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[153]++;
      typedCount++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[273]++;
    }
}
  }

  /**
   * Visits an assignment <code>lvalue = rvalue</code>. If the
   * <code>lvalue</code> is a prototype modification, we change the schema
   * of the object type it is referring to.
   * @param t the traversal
   * @param assign the assign node
   * (<code>assign.isAssign()</code> is an implicit invariant)
   */
  private void visitAssign(NodeTraversal t, Node assign) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[274]++;
    JSDocInfo info = assign.getJSDocInfo();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[275]++;
    Node lvalue = assign.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[276]++;
    Node rvalue = assign.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[277]++;
int CodeCoverConditionCoverageHelper_C30;

    // Check property sets to 'object.property' when 'object' is known.
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((lvalue.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[156]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[278]++;
      Node object = lvalue.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[279]++;
      JSType objectJsType = getJSType(object);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[280]++;
      Node property = lvalue.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[281]++;
      String pname = property.getString();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[282]++;
int CodeCoverConditionCoverageHelper_C31;

      // the first name in this getprop refers to an interface
      // we perform checks in addition to the ones below
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((object.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[158]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[283]++;
        JSType jsType = getJSType(object.getFirstChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[284]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((jsType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((object.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[160]++;
          visitInterfaceGetprop(t, assign, object, pname, lvalue, rvalue);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[285]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[161]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[159]++;}

      checkEnumAlias(t, info, rvalue);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[286]++;
      checkPropCreation(t, lvalue);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[287]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[288]++;
int CodeCoverConditionCoverageHelper_C33;

      // Prototype assignments are special, because they actually affect
      // the definition of a class. These are mostly validated
      // during TypedScopeCreator, and we only look for the "dumb" cases here.
      // object.prototype = ...;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((pname.equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[162]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[289]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((objectJsType != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((objectJsType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[164]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[290]++;
          FunctionType functionType = objectJsType.toMaybeFunctionType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[291]++;
int CodeCoverConditionCoverageHelper_C35;
          if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((functionType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[166]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[292]++;
            JSType rvalueType = rvalue.getJSType();
            validator.expectObject(t, rvalue, rvalueType,
                OVERRIDING_PROTOTYPE_WITH_NON_OBJECT);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[293]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[294]++;
int CodeCoverConditionCoverageHelper_C36;
            // Only assign structs to the prototype of a @struct constructor
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((functionType.makesStructs()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((rvalueType.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[168]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[295]++;
              String funName = functionType.getTypeOfThis().toString();
              compiler.report(t.makeError(assign, CONFLICTING_SHAPE_TYPE,
                                          "struct", funName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[296]++;

            } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[169]++;}
            return;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[167]++;}

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[165]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[163]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[297]++;

      // The generic checks for 'object.property' when 'object' is known,
      // and 'property' is declared on it.
      // object.property = ...;
      ObjectType type = ObjectType.cast(
          objectJsType.restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[298]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[170]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[299]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (32)) == 0 || true) &&
 ((type.hasProperty(pname)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((type.isPropertyTypeInferred(pname)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((propertyIsImplicitCast(type, pname)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[172]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[300]++;
          JSType expectedType = type.getPropertyType(pname);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[301]++;
int CodeCoverConditionCoverageHelper_C39;
          if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((expectedType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[174]++;
            validator.expectCanAssignToPropertyOf(
                t, assign, getJSType(rvalue),
                expectedType, object, pname);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[302]++;
            checkPropertyInheritanceOnGetpropAssign(
                t, assign, object, pname, info, expectedType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[303]++;
            return;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[175]++;}

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[173]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[171]++;}

      // If we couldn't get the property type with normal object property
      // lookups, then check inheritance anyway with the unknown type.
      checkPropertyInheritanceOnGetpropAssign(
          t, assign, object, pname, info, getNativeType(UNKNOWN_TYPE));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[304]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[157]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[305]++;

    // Check qualified name sets to 'object' and 'object.property'.
    // This can sometimes handle cases when the type of 'object' is not known.
    // e.g.,
    // var obj = createUnknownType();
    // /** @type {number} */ obj.foo = true;
    JSType leftType = getJSType(lvalue);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[306]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((lvalue.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[176]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[307]++;
      // variable with inferred type case
      Var var = t.getScope().getVar(lvalue.getQualifiedName());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[308]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[178]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[309]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[180]++;
          return;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[181]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[310]++;
int CodeCoverConditionCoverageHelper_C43;

        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((NodeUtil.getRootOfQualifiedName(lvalue).isThis()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((t.getScope() != var.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[182]++;
          // Don't look at "this.foo" variables from other scopes.
          return;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[183]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[311]++;
int CodeCoverConditionCoverageHelper_C44;

        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((var.getType() != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[184]++;
          leftType = var.getType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[312]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[185]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[179]++;}

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[177]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[313]++;

    // Fall through case for arbitrary LHS and arbitrary RHS.
    Node rightChild = assign.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[314]++;
    JSType rightType = getJSType(rightChild);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[315]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((validator.expectCanAssignTo(
            t, assign, rightType, leftType, "assignment")) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[186]++;
      ensureTyped(t, assign, rightType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[316]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[187]++;
      ensureTyped(t, assign);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[317]++;
    }
  }

  /**
   * After a struct object is created, we can't add new properties to it, with
   * one exception. We allow creation of "static" properties like
   * Foo.prototype.bar = baz;
   * where Foo.prototype is a struct, if the assignment happens at the top level
   * and the constructor Foo is defined in the same file.
   */
  private void checkPropCreation(NodeTraversal t, Node lvalue) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[318]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((lvalue.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[188]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[319]++;
      Node obj = lvalue.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[320]++;
      Node prop = lvalue.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[321]++;
      JSType objType = getJSType(obj);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[322]++;
      String pname = prop.getString();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[323]++;
int CodeCoverConditionCoverageHelper_C47;

      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((objType.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((objType.hasProperty(pname)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[190]++;
        return;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[191]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[324]++;
      Scope s = t.getScope();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[325]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((obj.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((getJSType(s.getRootNode()).isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[192]++;
        return;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[193]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[326]++;
      // Prop created outside ctor, check that it's a static prop
      Node assgnStm = lvalue.getParent().getParent();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[327]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (32)) == 0 || true) &&
 ((objType instanceof ObjectType) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((s.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((NodeUtil.isPrototypePropertyDeclaration(assgnStm)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[194]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[328]++;
        ObjectType instance =
            objType.toObjectType().getOwnerFunction().getInstanceType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[329]++;
        String file = lvalue.getSourceFileName();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[330]++;
        Node ctor = instance.getConstructor().getSource();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[331]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((ctor != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ctor.getSourceFileName().equals(file)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[196]++;
          return;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[197]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[195]++;}
      report(t, prop, ILLEGAL_PROPERTY_CREATION);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[332]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[189]++;}
  }

  private void checkPropertyInheritanceOnGetpropAssign(
      NodeTraversal t, Node assign, Node object, String property,
      JSDocInfo info, JSType propertyType) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[333]++;
int CodeCoverConditionCoverageHelper_C51;
    // Inheritance checks for prototype properties.
    //
    // TODO(nicksantos): This isn't the right place to do this check. We
    // really want to do this when we're looking at the constructor.
    // We'd find all its properties and make sure they followed inheritance
    // rules, like we currently do for @implements to make sure
    // all the methods are implemented.
    //
    // As-is, this misses many other ways to override a property.
    //
    // object.prototype.property = ...;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((object.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[198]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[334]++;
      Node object2 = object.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[335]++;
      String property2 = NodeUtil.getStringValue(object.getLastChild());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[336]++;
int CodeCoverConditionCoverageHelper_C52;

      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 (("prototype".equals(property2)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[200]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[337]++;
        JSType jsType = getJSType(object2);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[338]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((jsType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[202]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[339]++;
          FunctionType functionType = jsType.toMaybeFunctionType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[340]++;
int CodeCoverConditionCoverageHelper_C54;
          if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((functionType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((functionType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[204]++;
            checkDeclaredPropertyInheritance(
                t, assign, functionType, property, info, propertyType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[341]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[205]++;}

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[203]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[201]++;}

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[199]++;}
  }

  /**
   * Visits an object literal field definition <code>key : value</code>.
   *
   * If the <code>lvalue</code> is a prototype modification, we change the
   * schema of the object type it is referring to.
   *
   * @param t the traversal
   * @param key the assign node
   */
  private void visitObjLitKey(
      NodeTraversal t, Node key, Node objlit, JSType litType) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[342]++;
int CodeCoverConditionCoverageHelper_C55;
    // Do not validate object lit value types in externs. We don't really care,
    // and it makes it easier to generate externs.
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((objlit.isFromExterns()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[206]++;
      ensureTyped(t, key);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[343]++;
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[207]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[344]++;
int CodeCoverConditionCoverageHelper_C56;

    // Structs must have unquoted keys and dicts must have quoted keys
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((litType.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((key.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[208]++;
      report(t, key, ILLEGAL_OBJLIT_KEY, "struct");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[345]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[209]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[346]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((litType.isDict()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((key.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[210]++;
      report(t, key, ILLEGAL_OBJLIT_KEY, "dict");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[347]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[211]++;}
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[348]++;

    // TODO(johnlenz): Validate get and set function declarations are valid
    // as is the functions can have "extraneous" bits.

    // For getter and setter property definitions the
    // r-value type != the property type.
    Node rvalue = key.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[349]++;
    JSType rightType = NodeUtil.getObjectLitKeyTypeFromValueType(
        key, getJSType(rvalue));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[350]++;
int CodeCoverConditionCoverageHelper_C58;
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((rightType == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[212]++;
      rightType = getNativeType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[351]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[213]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[352]++;

    Node owner = objlit;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[353]++;

    // Validate value is assignable to the key type.

    JSType keyType = getJSType(key);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[354]++;

    JSType allowedValueType = keyType;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[355]++;
int CodeCoverConditionCoverageHelper_C59;
    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((allowedValueType.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[214]++;
      allowedValueType =
          allowedValueType.toMaybeEnumElementType().getPrimitiveType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[356]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[215]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[357]++;

    boolean valid = validator.expectCanAssignToPropertyOf(t, key,
        rightType, allowedValueType,
        owner, NodeUtil.getObjectLitKeyName(key));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[358]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((valid) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[216]++;
      ensureTyped(t, key, rightType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[359]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[217]++;
      ensureTyped(t, key);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[360]++;
    }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[361]++;

    // Validate that the key type is assignable to the object property type.
    // This is necessary as the objlit may have been cast to a non-literal
    // object type.
    // TODO(johnlenz): consider introducing a CAST node to the AST (or
    // perhaps a parentheses node).

    JSType objlitType = getJSType(objlit);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[362]++;
    ObjectType type = ObjectType.cast(
        objlitType.restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[363]++;
int CodeCoverConditionCoverageHelper_C61;
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[218]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[364]++;
      String property = NodeUtil.getObjectLitKeyName(key);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[365]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (32)) == 0 || true) &&
 ((type.hasProperty(property)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((type.isPropertyTypeInferred(property)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((propertyIsImplicitCast(type, property)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[220]++;
        validator.expectCanAssignToPropertyOf(
            t, key, keyType,
            type.getPropertyType(property), owner, property);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[366]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[221]++;}
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[219]++;}
  }

  /**
   * Returns true if any type in the chain has an implicitCast annotation for
   * the given property.
   */
  private boolean propertyIsImplicitCast(ObjectType type, String prop) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[367]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[4]++;


int CodeCoverConditionCoverageHelper_C63;
    for (;(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); type = type.getImplicitPrototype()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[4]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[5]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[6]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[368]++;
      JSDocInfo docInfo = type.getOwnPropertyJSDocInfo(prop);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[369]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((docInfo.isImplicitCast()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[222]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[223]++;}
    }
    return false;
  }

  /**
   * Given a constructor type and a property name, check that the property has
   * the JSDoc annotation @override iff the property is declared on a
   * superclass. Several checks regarding inheritance correctness are also
   * performed.
   */
  private void checkDeclaredPropertyInheritance(
      NodeTraversal t, Node n, FunctionType ctorType, String propertyName,
      JSDocInfo info, JSType propertyType) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[370]++;
int CodeCoverConditionCoverageHelper_C65;
    // If the supertype doesn't resolve correctly, we've warned about this
    // already.
    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((hasUnknownOrEmptySupertype(ctorType)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[224]++;
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[225]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[371]++;

    FunctionType superClass = ctorType.getSuperClassConstructor();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[372]++;
    boolean superClassHasProperty = superClass != null &&
        superClass.getInstanceType().hasProperty(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[373]++;
    boolean superClassHasDeclaredProperty = superClass != null &&
        superClass.getInstanceType().isPropertyTypeDeclared(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[374]++;

    // For interface
    boolean superInterfaceHasProperty = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[375]++;
    boolean superInterfaceHasDeclaredProperty = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[376]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((ctorType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[226]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[377]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[7]++;


      for (ObjectType interfaceType : ctorType.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[7]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[8]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[9]++;
}
        superInterfaceHasProperty =
            superInterfaceHasProperty ||
            interfaceType.hasProperty(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[378]++;
        superInterfaceHasDeclaredProperty =
            superInterfaceHasDeclaredProperty ||
            interfaceType.isPropertyTypeDeclared(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[379]++;
      }

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[227]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[380]++;
    boolean declaredOverride = info != null && info.isOverride();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[381]++;

    boolean foundInterfaceProperty = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[382]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((ctorType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[228]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[383]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[10]++;


      for (JSType implementedInterface :
          ctorType.getAllImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[10]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[11]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[12]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[384]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((implementedInterface.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((implementedInterface.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[230]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[385]++;
          continue;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[231]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[386]++;
        FunctionType interfaceType =
            implementedInterface.toObjectType().getConstructor();
        Preconditions.checkNotNull(interfaceType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[387]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[388]++;

        boolean interfaceHasProperty =
            interfaceType.getPrototype().hasProperty(propertyName);
        foundInterfaceProperty = foundInterfaceProperty ||
            interfaceHasProperty;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[389]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[390]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((reportMissingOverride.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((declaredOverride) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((interfaceHasProperty) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[232]++;
          // @override not present, but the property does override an interface
          // property
          compiler.report(t.makeError(n, reportMissingOverride,
              HIDDEN_INTERFACE_PROPERTY, propertyName,
              interfaceType.getTopMostDefiningType(propertyName).toString()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[391]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[233]++;}
      }

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[229]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[392]++;
int CodeCoverConditionCoverageHelper_C70;

    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (32)) == 0 || true) &&
 ((declaredOverride) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((superClassHasProperty) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((superInterfaceHasProperty) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[234]++;
      // nothing to do here, it's just a plain new property
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[235]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[393]++;

    ObjectType topInstanceType = superClassHasDeclaredProperty ?
        superClass.getTopMostDefiningType(propertyName) : null;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[394]++;
    boolean declaredLocally =
        ctorType.isConstructor() &&
        (ctorType.getPrototype().hasOwnProperty(propertyName) ||
         ctorType.getInstanceType().hasOwnProperty(propertyName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[395]++;
int CodeCoverConditionCoverageHelper_C71;
    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (128)) == 0 || true) &&
 ((reportMissingOverride.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C71 |= (32)) == 0 || true) &&
 ((declaredOverride) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((superClassHasDeclaredProperty) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((declaredLocally) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 4) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 4) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[236]++;
      // @override not present, but the property does override a superclass
      // property
      compiler.report(t.makeError(n, reportMissingOverride,
          HIDDEN_SUPERCLASS_PROPERTY, propertyName,
          topInstanceType.toString()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[396]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[237]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[397]++;
int CodeCoverConditionCoverageHelper_C72;

    // @override is present and we have to check that it is ok
    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((superClassHasDeclaredProperty) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[238]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[398]++;
      // there is a superclass implementation
      JSType superClassPropType =
          superClass.getInstanceType().getPropertyType(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[399]++;
int CodeCoverConditionCoverageHelper_C73;
      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((propertyType.isSubtype(superClassPropType)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[240]++;
        compiler.report(
            t.makeError(n, HIDDEN_SUPERCLASS_PROPERTY_MISMATCH,
                propertyName, topInstanceType.toString(),
                superClassPropType.toString(), propertyType.toString()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[400]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[241]++;}

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[239]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[401]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((superInterfaceHasDeclaredProperty) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[242]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[402]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[13]++;


      // there is an super interface property
      for (ObjectType interfaceType : ctorType.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[13]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[14]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[15]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[403]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((interfaceType.hasProperty(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[244]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[404]++;
          JSType superPropertyType =
              interfaceType.getPropertyType(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[405]++;
int CodeCoverConditionCoverageHelper_C76;
          if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((propertyType.isSubtype(superPropertyType)) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[246]++;
            topInstanceType = interfaceType.getConstructor().
                getTopMostDefiningType(propertyName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[406]++;
            compiler.report(
                t.makeError(n, HIDDEN_SUPERCLASS_PROPERTY_MISMATCH,
                    propertyName, topInstanceType.toString(),
                    superPropertyType.toString(),
                    propertyType.toString()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[407]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[247]++;}

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[245]++;}
      }

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[243]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[408]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (32)) == 0 || true) &&
 ((foundInterfaceProperty) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((superClassHasProperty) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((superInterfaceHasProperty) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[248]++;
      // there is no superclass nor interface implementation
      compiler.report(
          t.makeError(n, UNKNOWN_OVERRIDE,
              propertyName, ctorType.getInstanceType().toString()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[409]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[249]++;}
}
}
  }

  /**
   * Given a constructor or an interface type, find out whether the unknown
   * type is a supertype of the current type.
   */
  private static boolean hasUnknownOrEmptySupertype(FunctionType ctor) {
    Preconditions.checkArgument(ctor.isConstructor() || ctor.isInterface());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[410]++;
    Preconditions.checkArgument(!ctor.isUnknownType());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[411]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[412]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[16]++;



    // The type system should notice inheritance cycles on its own
    // and break the cycle.
    while (true) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[16]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[17]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[18]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[413]++;
      ObjectType maybeSuperInstanceType =
          ctor.getPrototype().getImplicitPrototype();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[414]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((maybeSuperInstanceType == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[250]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[251]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[415]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((maybeSuperInstanceType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((maybeSuperInstanceType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[252]++;
        return true;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[253]++;}
      ctor = maybeSuperInstanceType.getConstructor();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[416]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[417]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((ctor == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[254]++;
        return false;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[255]++;}
      Preconditions.checkState(ctor.isConstructor() || ctor.isInterface());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[418]++;
    }
  }

  /**
   * Visits an ASSIGN node for cases such as
   * <pre>
   * interface.property2.property = ...;
   * </pre>
   */
  private void visitInterfaceGetprop(NodeTraversal t, Node assign, Node object,
      String property, Node lvalue, Node rvalue) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[419]++;

    JSType rvalueType = getJSType(rvalue);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[420]++;

    // Only 2 values are allowed for methods:
    //    goog.abstractMethod
    //    function () {};
    // or for properties, no assignment such as:
    //    InterfaceFoo.prototype.foobar;

    String abstractMethodName =
        compiler.getCodingConvention().getAbstractMethodName();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[421]++;
int CodeCoverConditionCoverageHelper_C82;
    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((rvalueType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[256]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[422]++;
      // This is bad i18n style but we don't localize our compiler errors.
      String abstractMethodMessage = (abstractMethodName != null)
         ? ", or " + abstractMethodName
         : "";
      compiler.report(
          t.makeError(object, INVALID_INTERFACE_MEMBER_DECLARATION,
              abstractMethodMessage));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[423]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[257]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[424]++;
int CodeCoverConditionCoverageHelper_C83;

    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((assign.getLastChild().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((NodeUtil.isEmptyBlock(assign.getLastChild().getLastChild())) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[258]++;
      compiler.report(
          t.makeError(object, INTERFACE_FUNCTION_NOT_EMPTY,
              abstractMethodName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[425]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[259]++;}
  }

  /**
   * Visits a NAME node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   * @param parent The parent of the node n.
   * @return whether the node is typeable or not
   */
  boolean visitName(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[426]++;
    // At this stage, we need to determine whether this is a leaf
    // node in an expression (which therefore needs to have a type
    // assigned for it) versus some other decorative node that we
    // can safely ignore.  Function names, arguments (children of LP nodes) and
    // variable declarations are ignored.
    // TODO(user): remove this short-circuiting in favor of a
    // pre order traversal of the FUNCTION, CATCH, LP and VAR nodes.
    int parentNodeType = parent.getType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[427]++;
int CodeCoverConditionCoverageHelper_C84;
    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (128)) == 0 || true) &&
 ((parentNodeType == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C84 |= (32)) == 0 || true) &&
 ((parentNodeType == Token.CATCH) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((parentNodeType == Token.PARAM_LIST) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((parentNodeType == Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 4) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 4) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[260]++;
      return false;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[261]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[428]++;

    JSType type = n.getJSType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[429]++;
int CodeCoverConditionCoverageHelper_C85;
    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[262]++;
      type = getNativeType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[430]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[431]++;
      Var var = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[432]++;
int CodeCoverConditionCoverageHelper_C86;
      if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[264]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[433]++;
        JSType varType = var.getType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[434]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((varType != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[266]++;
          type = varType;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[435]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[267]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[265]++;}

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[263]++;}
    ensureTyped(t, n, type);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[436]++;
    return true;
  }

  /**
   * Visits a GETPROP node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   * @param parent The parent of <code>n</code>
   */
  private void visitGetProp(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[437]++;
    // obj.prop or obj.method()
    // Lots of types can appear on the left, a call to a void function can
    // never be on the left. getPropertyType will decide what is acceptable
    // and what isn't.
    Node property = n.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[438]++;
    Node objNode = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[439]++;
    JSType childType = getJSType(objNode);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[440]++;
int CodeCoverConditionCoverageHelper_C88;

    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((childType.isDict()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[268]++;
      report(t, property, TypeValidator.ILLEGAL_PROPERTY_ACCESS, "'.'", "dict");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[441]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[269]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[442]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((validator.expectNotNullOrUndefined(t, n, childType,
        "No properties on this expression", getNativeType(OBJECT_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[270]++;
      checkPropertyAccess(childType, property.getString(), t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[443]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[271]++;}
}
    ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[444]++;
  }

  /**
   * Emit a warning if we can prove that a property cannot possibly be
   * defined on an object. Note the difference between JS and a strictly
   * statically typed language: we're checking if the property
   * *cannot be defined*, whereas a java compiler would check if the
   * property *can be undefined*.
   */
  private void checkPropertyAccess(JSType childType, String propName,
      NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[445]++;
    // If the property type is unknown, check the object type to see if it
    // can ever be defined. We explicitly exclude CHECKED_UNKNOWN (for
    // properties where we've checked that it exists, or for properties on
    // objects that aren't in this binary).
    JSType propType = getJSType(n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[446]++;
int CodeCoverConditionCoverageHelper_C90;
    if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((propType.isEquivalentTo(typeRegistry.getNativeType(UNKNOWN_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[272]++;
      childType = childType.autobox();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[447]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[448]++;
      ObjectType objectType = ObjectType.cast(childType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[449]++;
int CodeCoverConditionCoverageHelper_C91;
      if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[274]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[450]++;
int CodeCoverConditionCoverageHelper_C92;
        // We special-case object types so that checks on enums can be
        // much stricter, and so that we can use hasProperty (which is much
        // faster in most cases).
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C92 |= (8)) == 0 || true) &&
 ((objectType.hasProperty(propName)) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((objectType.isEquivalentTo(
                typeRegistry.getNativeType(UNKNOWN_TYPE))) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[276]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[451]++;
int CodeCoverConditionCoverageHelper_C93;
          if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((objectType instanceof EnumType) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[278]++;
            report(t, n, INEXISTENT_ENUM_ELEMENT, propName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[452]++;

          } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[279]++;
            checkPropertyAccessHelper(objectType, propName, t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[453]++;
          }

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[277]++;}


      } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[275]++;
        checkPropertyAccessHelper(childType, propName, t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[454]++;
      }

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[273]++;}
  }

  private void checkPropertyAccessHelper(JSType objectType, String propName,
      NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[455]++;
int CodeCoverConditionCoverageHelper_C94;
    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C94 |= (32)) == 0 || true) &&
 ((objectType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((reportMissingProperties) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((isPropertyTest(n)) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[280]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[456]++;
int CodeCoverConditionCoverageHelper_C95;
      if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((typeRegistry.canPropertyBeDefined(objectType, propName)) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[282]++;
        report(t, n, INEXISTENT_PROPERTY, propName,
            validator.getReadableJSTypeName(n.getFirstChild(), true));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[457]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[283]++;}

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[281]++;}
  }

  /**
   * Determines whether this node is testing for the existence of a property.
   * If true, we will not emit warnings about a missing property.
   *
   * @param getProp The GETPROP being tested.
   */
  private boolean isPropertyTest(Node getProp) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[458]++;
    Node parent = getProp.getParent();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[459]++;
    switch (parent.getType()) {
      case Token.CALL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[284]++;
        return parent.getFirstChild() != getProp &&
            compiler.getCodingConvention().isPropertyTestFunction(parent);

      case Token.IF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[285]++;
      case Token.WHILE:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[286]++;
      case Token.DO:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[287]++;
      case Token.FOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[288]++;
        return NodeUtil.getConditionExpression(parent) == getProp;

      case Token.INSTANCEOF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[289]++;
      case Token.TYPEOF:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[290]++;
        return true;

      case Token.AND:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[291]++;
      case Token.HOOK:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[292]++;
        return parent.getFirstChild() == getProp;

      case Token.NOT:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[293]++;
        return parent.getParent().isOr() &&
            parent.getParent().getFirstChild() == parent;

      case Token.CAST:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[294]++;
        return isPropertyTest(parent); default : CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[295]++;
    }
    return false;
  }

  /**
   * Visits a GETELEM node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   */
  private void visitGetElem(NodeTraversal t, Node n) {
    validator.expectIndexMatch(
        t, n, getJSType(n.getFirstChild()), getJSType(n.getLastChild()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[460]++;
    ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[461]++;
  }

  /**
   * Visits a VAR node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   */
  private void visitVar(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[462]++;
    // TODO(nicksantos): Fix this so that the doc info always shows up
    // on the NAME node. We probably want to wait for the parser
    // merge to fix this.
    JSDocInfo varInfo = n.hasOneChild() ? n.getJSDocInfo() : null;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[463]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[19]++;


    for (Node name : n.children()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[19]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[20]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[21]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[464]++;
      Node value = name.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[465]++;
      // A null var would indicate a bug in the scope creation logic.
      Var var = t.getScope().getVar(name.getString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[466]++;
int CodeCoverConditionCoverageHelper_C96;

      if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[296]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[467]++;
        JSType valueType = getJSType(value);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[468]++;
        JSType nameType = var.getType();
        nameType = (nameType == null) ? getNativeType(UNKNOWN_TYPE) : nameType;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[469]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[470]++;

        JSDocInfo info = name.getJSDocInfo();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[471]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[298]++;
          info = varInfo;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[472]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[299]++;}

        checkEnumAlias(t, info, value);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[473]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[474]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((var.isTypeInferred()) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[300]++;
          ensureTyped(t, name, valueType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[475]++;

        } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[301]++;
          validator.expectCanAssignTo(
              t, value, valueType, nameType, "initializing variable");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[476]++;
        }

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[297]++;}
    }
  }

  /**
   * Visits a NEW node.
   */
  private void visitNew(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[477]++;
    Node constructor = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[478]++;
    JSType type = getJSType(constructor).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[479]++;
int CodeCoverConditionCoverageHelper_C99;
    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (32)) == 0 || true) &&
 ((type.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((type.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[302]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[480]++;
      FunctionType fnType = type.toMaybeFunctionType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[481]++;
int CodeCoverConditionCoverageHelper_C100;
      if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((fnType != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[304]++;
        visitParameterList(t, n, fnType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[482]++;
        ensureTyped(t, n, fnType.getInstanceType());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[483]++;

      } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[305]++;
        ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[484]++;
      }

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[303]++;
      report(t, n, NOT_A_CONSTRUCTOR);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[485]++;
      ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[486]++;
    }
  }

  /**
   * Check whether there's any property conflict for for a particular super
   * interface
   * @param t The node traversal object that supplies context
   * @param n The node being visited
   * @param functionName The function name being checked
   * @param properties The property names in the super interfaces that have
   * been visited
   * @param currentProperties The property names in the super interface
   * that have been visited
   * @param interfaceType The super interface that is being visited
   */
  private void checkInterfaceConflictProperties(NodeTraversal t, Node n,
      String functionName, HashMap<String, ObjectType> properties,
      HashMap<String, ObjectType> currentProperties,
      ObjectType interfaceType) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[487]++;
    ObjectType implicitProto = interfaceType.getImplicitPrototype();
    Set<String> currentPropertyNames;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[488]++;
int CodeCoverConditionCoverageHelper_C101;
    if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((implicitProto == null) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[306]++;
      // This can be the case if interfaceType is proxy to a non-existent
      // object (which is a bad type annotation, but shouldn't crash).
      currentPropertyNames = ImmutableSet.of();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[489]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[307]++;
      currentPropertyNames = implicitProto.getOwnPropertyNames();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[490]++;
    }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[491]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[22]++;


    for (String name : currentPropertyNames) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[22]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[23]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[24]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[492]++;
      ObjectType oType = properties.get(name);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[493]++;
int CodeCoverConditionCoverageHelper_C102;
      if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((oType != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[308]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[494]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((interfaceType.getPropertyType(name).isEquivalentTo(
            oType.getPropertyType(name))) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[310]++;
          compiler.report(
              t.makeError(n, INCOMPATIBLE_EXTENDED_PROPERTY_TYPE,
                  functionName, name, oType.toString(),
                  interfaceType.toString()));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[495]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[311]++;}

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[309]++;}
      currentProperties.put(name, interfaceType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[496]++;
    }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[497]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[25]++;


    for (ObjectType iType : interfaceType.getCtorExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[25]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[26]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[27]++;
}
      checkInterfaceConflictProperties(t, n, functionName, properties,
          currentProperties, iType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[498]++;
    }
  }

  /**
   * Visits a {@link Token#FUNCTION} node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   */
  private void visitFunction(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[499]++;
    FunctionType functionType = JSType.toMaybeFunctionType(n.getJSType());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[500]++;
    String functionPrivateName = n.getFirstChild().getString();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[501]++;
int CodeCoverConditionCoverageHelper_C104;
    if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((functionType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[312]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[502]++;
      FunctionType baseConstructor = functionType.getSuperClassConstructor();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[503]++;
int CodeCoverConditionCoverageHelper_C105;
      if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (32)) == 0 || true) &&
 ((baseConstructor != getNativeType(OBJECT_FUNCTION_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C105 |= (8)) == 0 || true) &&
 ((baseConstructor != null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((baseConstructor.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 3) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 3) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[314]++;
        compiler.report(
            t.makeError(n, CONFLICTING_EXTENDED_TYPE,
                        "constructor", functionPrivateName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[504]++;

      } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[315]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[505]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((baseConstructor != getNativeType(OBJECT_FUNCTION_TYPE)) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[316]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[506]++;
          ObjectType proto = functionType.getPrototype();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[507]++;
int CodeCoverConditionCoverageHelper_C107;
          if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 ((functionType.makesStructs()) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((proto.isStruct()) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[318]++;
            compiler.report(t.makeError(n, CONFLICTING_SHAPE_TYPE,
                                        "struct", functionPrivateName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[508]++;

          } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[319]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[509]++;
int CodeCoverConditionCoverageHelper_C108; if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (8)) == 0 || true) &&
 ((functionType.makesDicts()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((proto.isDict()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[320]++;
            compiler.report(t.makeError(n, CONFLICTING_SHAPE_TYPE,
                                        "dict", functionPrivateName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[510]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[321]++;}
}

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[317]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[511]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[28]++;


        // All interfaces are properly implemented by a class
        for (JSType baseInterface : functionType.getImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[28]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[29]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[30]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[512]++;
          boolean badImplementedType = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[513]++;
          ObjectType baseInterfaceObj = ObjectType.cast(baseInterface);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[514]++;
int CodeCoverConditionCoverageHelper_C109;
          if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((baseInterfaceObj != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[322]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[515]++;
            FunctionType interfaceConstructor =
              baseInterfaceObj.getConstructor();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[516]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((interfaceConstructor != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((interfaceConstructor.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[324]++;
              badImplementedType = true;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[517]++;

            } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[325]++;}

          } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[323]++;
            badImplementedType = true;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[518]++;
          }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[519]++;
int CodeCoverConditionCoverageHelper_C111;
          if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((badImplementedType) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[326]++;
            report(t, n, BAD_IMPLEMENTED_TYPE, functionPrivateName);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[520]++;

          } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[327]++;}
        }
        // check properties
        validator.expectAllInterfaceProperties(t, n, functionType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[521]++;
      }

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[313]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[522]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((functionType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[328]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[523]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[31]++;


      // Interface must extend only interfaces
      for (ObjectType extInterface : functionType.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[31]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[32]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[33]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[524]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (8)) == 0 || true) &&
 ((extInterface.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((extInterface.getConstructor().isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[330]++;
          compiler.report(
              t.makeError(n, CONFLICTING_EXTENDED_TYPE,
                          "interface", functionPrivateName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[525]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[331]++;}
      }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[526]++;
int CodeCoverConditionCoverageHelper_C114;

      // Check whether the extended interfaces have any conflicts
      if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((functionType.getExtendedInterfacesCount() > 1) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[332]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[527]++;
        // Only check when extending more than one interfaces
        HashMap<String, ObjectType> properties
            = new HashMap<String, ObjectType>();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[528]++;
        HashMap<String, ObjectType> currentProperties
            = new HashMap<String, ObjectType>();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[529]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[34]++;


        for (ObjectType interfaceType : functionType.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[34]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[35]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[36]++;
}
          currentProperties.clear();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[530]++;
          checkInterfaceConflictProperties(t, n, functionPrivateName,
              properties, currentProperties, interfaceType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[531]++;
          properties.putAll(currentProperties);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[532]++;
        }

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[333]++;}

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[329]++;}
}
  }

  /**
   * Visits a CALL node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   */
  private void visitCall(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[533]++;
    Node child = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[534]++;
    JSType childType = getJSType(child).restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[535]++;
int CodeCoverConditionCoverageHelper_C115;

    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((childType.canBeCalled()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[334]++;
      report(t, n, NOT_CALLABLE, childType.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[536]++;
      ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[537]++;
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[335]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[538]++;
int CodeCoverConditionCoverageHelper_C116;

    // A couple of types can be called as if they were functions.
    // If it is a function type, then validate parameters.
    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((childType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[336]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[539]++;
      FunctionType functionType = childType.toMaybeFunctionType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[540]++;

      boolean isExtern = false;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[541]++;
      JSDocInfo functionJSDocInfo = functionType.getJSDocInfo();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[542]++;
int CodeCoverConditionCoverageHelper_C117;
      if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((functionJSDocInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((functionJSDocInfo.getAssociatedNode() != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[338]++;
        isExtern = functionJSDocInfo.getAssociatedNode().isFromExterns();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[543]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[339]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[544]++;
int CodeCoverConditionCoverageHelper_C118;

      // Non-native constructors should not be called directly
      // unless they specify a return type and are defined
      // in an extern.
      if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (512)) == 0 || true) &&
 ((functionType.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (256)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C118 |= (128)) == 0 || true) &&
 ((functionType.isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C118 |= (32)) == 0 || true) &&
 ((functionType.getReturnType().isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((functionType.getReturnType().isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((isExtern) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 5) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 5) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[340]++;
        report(t, n, CONSTRUCTOR_NOT_CALLABLE, childType.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[545]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[341]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[546]++;
int CodeCoverConditionCoverageHelper_C119;

      // Functions with explicit 'this' types must be called in a GETPROP
      // or GETELEM.
      if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2048)) == 0 || true) &&
 ((functionType.isOrdinaryFunction()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1024)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C119 |= (512)) == 0 || true) &&
 ((functionType.getTypeOfThis().isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (256)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C119 |= (128)) == 0 || true) &&
 ((functionType.getTypeOfThis().toObjectType() != null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C119 |= (32)) == 0 || true) &&
 ((functionType.getTypeOfThis().toObjectType().isNativeObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (16)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C119 |= (8)) == 0 || true) &&
 ((child.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((child.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 6) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 6) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[342]++;
        report(t, n, EXPECTED_THIS_TYPE, functionType.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[547]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[343]++;}

      visitParameterList(t, n, functionType);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[548]++;
      ensureTyped(t, n, functionType.getReturnType());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[549]++;

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[337]++;
      ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[550]++;
    }

    // TODO: Add something to check for calls of RegExp objects, which is not
    // supported by IE.  Either say something about the return type or warn
    // about the non-portability of the call or both.
  }

  /**
   * Visits the parameters of a CALL or a NEW node.
   */
  private void visitParameterList(NodeTraversal t, Node call,
      FunctionType functionType) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[551]++;
    Iterator<Node> arguments = call.children().iterator();
    arguments.next();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[552]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[553]++; // skip the function name

    Iterator<Node> parameters = functionType.getParameters().iterator();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[554]++;
    int ordinal = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[555]++;
    Node parameter = null;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[556]++;
    Node argument = null;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[557]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[37]++;


int CodeCoverConditionCoverageHelper_C120;
    while ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (128)) == 0 || true) &&
 ((arguments.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C120 |= (32)) == 0 || true) &&
 ((parameters.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C120 |= (8)) == 0 || true) &&
 ((parameter != null) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((parameter.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 4) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 4) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[37]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[38]--;
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.loops[39]++;
}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[558]++;
int CodeCoverConditionCoverageHelper_C121;
      // If there are no parameters left in the list, then the while loop
      // above implies that this must be a var_args function.
      if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((parameters.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[344]++;
        parameter = parameters.next();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[559]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[345]++;}
      argument = arguments.next();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[560]++;
      ordinal++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[561]++;

      validator.expectArgumentMatchesParameter(t, argument,
          getJSType(argument), getJSType(parameter), call, ordinal);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[562]++;
    }
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[563]++;

    int numArgs = call.getChildCount() - 1;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[564]++;
    int minArgs = functionType.getMinArguments();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[565]++;
    int maxArgs = functionType.getMaxArguments();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[566]++;
int CodeCoverConditionCoverageHelper_C122;
    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (8)) == 0 || true) &&
 ((minArgs > numArgs) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((maxArgs < numArgs) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[346]++;
      report(t, call, WRONG_ARGUMENT_COUNT,
              validator.getReadableJSTypeName(call.getFirstChild(), false),
              String.valueOf(numArgs), String.valueOf(minArgs),
              maxArgs != Integer.MAX_VALUE ?
              " and no more than " + maxArgs + " argument(s)" : "");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[567]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[347]++;}
  }

  /**
   * Visits a RETURN node.
   *
   * @param t The node traversal object that supplies context, such as the
   * scope chain to use in name lookups as well as error reporting.
   * @param n The node being visited.
   */
  private void visitReturn(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[568]++;
    JSType jsType = getJSType(t.getEnclosingFunction());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[569]++;
int CodeCoverConditionCoverageHelper_C123;

    if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((jsType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[348]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[570]++;
      FunctionType functionType = jsType.toMaybeFunctionType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[571]++;

      JSType returnType = functionType.getReturnType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[572]++;
int CodeCoverConditionCoverageHelper_C124;

      // if no return type is specified, undefined must be returned
      // (it's a void function)
      if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((returnType == null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[350]++;
        returnType = getNativeType(VOID_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[573]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[351]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[574]++;

      // fetching the returned value's type
      Node valueNode = n.getFirstChild();
      JSType actualReturnType;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[575]++;
int CodeCoverConditionCoverageHelper_C125;
      if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((valueNode == null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[352]++;
        actualReturnType = getNativeType(VOID_TYPE);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[576]++;
        valueNode = n;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[577]++;

      } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[353]++;
        actualReturnType = getJSType(valueNode);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[578]++;
      }

      // verifying
      validator.expectCanAssignTo(t, valueNode, actualReturnType, returnType,
          "inconsistent return type");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[579]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[349]++;}
  }

  /**
   * This function unifies the type checking involved in the core binary
   * operators and the corresponding assignment operators.  The representation
   * used internally is such that common code can handle both kinds of
   * operators easily.
   *
   * @param op The operator.
   * @param t The traversal object, needed to report errors.
   * @param n The node being checked.
   */
  private void visitBinaryOperator(int op, NodeTraversal t, Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[580]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[581]++;
    JSType leftType = getJSType(left);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[582]++;
    Node right = n.getLastChild();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[583]++;
    JSType rightType = getJSType(right);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[584]++;
    switch (op) {
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[354]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[355]++;
      case Token.LSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[356]++;
      case Token.RSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[357]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[358]++;
      case Token.URSH:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[359]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[585]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((leftType.matchesInt32Context()) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[360]++;
          report(t, left, BIT_OPERATION,
                   NodeUtil.opToStr(n.getType()), leftType.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[586]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[361]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[587]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((rightType.matchesUint32Context()) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[362]++;
          report(t, right, BIT_OPERATION,
                   NodeUtil.opToStr(n.getType()), rightType.toString());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[588]++;

        } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[363]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[589]++;
        break;

      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[364]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[365]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[366]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[367]++;
      case Token.DIV:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[368]++;
      case Token.MOD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[369]++;
      case Token.MUL:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[370]++;
      case Token.SUB:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[371]++;
        validator.expectNumber(t, left, leftType, "left operand");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[590]++;
        validator.expectNumber(t, right, rightType, "right operand");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[591]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[592]++;
        break;

      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[372]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[373]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[374]++;
      case Token.BITAND:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[375]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[376]++;
      case Token.BITOR:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[377]++;
        validator.expectBitwiseable(t, left, leftType,
            "bad left operand to bitwise operator");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[593]++;
        validator.expectBitwiseable(t, right, rightType,
            "bad right operand to bitwise operator");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[594]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[595]++;
        break;

      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[378]++;
      case Token.ADD:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[379]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[596]++;
        break;

      default:
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[380]++;
        report(t, n, UNEXPECTED_TOKEN, Token.name(op));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[597]++;
    }
    ensureTyped(t, n);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[598]++;
  }


  /**
   * <p>Checks enum aliases.
   *
   * <p>We verify that the enum element type of the enum used
   * for initialization is a subtype of the enum element type of
   * the enum the value is being copied in.</p>
   *
   * <p>Example:</p>
   * <pre>var myEnum = myOtherEnum;</pre>
   *
   * <p>Enum aliases are irregular, so we need special code for this :(</p>
   *
   * @param value the value used for initialization of the enum
   */
  private void checkEnumAlias(
      NodeTraversal t, JSDocInfo declInfo, Node value) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[599]++;
int CodeCoverConditionCoverageHelper_C128;
    if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((declInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((declInfo.hasEnumParameterType()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[381]++;
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[382]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[600]++;

    JSType valueType = getJSType(value);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[601]++;
int CodeCoverConditionCoverageHelper_C129;
    if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((valueType.isEnumType()) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[383]++;
      return;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[384]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[602]++;

    EnumType valueEnumType = valueType.toMaybeEnumType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[603]++;
    JSType valueEnumPrimitiveType =
        valueEnumType.getElementsType().getPrimitiveType();
    validator.expectCanAssignTo(t, value, valueEnumPrimitiveType,
        declInfo.getEnumParameterType().evaluate(t.getScope(), typeRegistry),
        "incompatible enum element types");
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[604]++;
  }

  /**
   * This method gets the JSType from the Node argument and verifies that it is
   * present.
   */
  private JSType getJSType(Node n) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[605]++;
    JSType jsType = n.getJSType();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[606]++;
int CodeCoverConditionCoverageHelper_C130;
    if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[385]++;
      // TODO(nicksantos): This branch indicates a compiler bug, not worthy of
      // halting the compilation but we should log this and analyze to track
      // down why it happens. This is not critical and will be resolved over
      // time as the type checker is extended.
      return getNativeType(UNKNOWN_TYPE);

    } else {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[386]++;
      return jsType;
    }
  }

  // TODO(nicksantos): TypeCheck should never be attaching types to nodes.
  // All types should be attached by TypeInference. This is not true today
  // for legacy reasons. There are a number of places where TypeInference
  // doesn't attach a type, as a signal to TypeCheck that it needs to check
  // that node's type.

  /**
   * Ensure that the given node has a type. If it does not have one,
   * attach the UNKNOWN_TYPE.
   */
  private void ensureTyped(NodeTraversal t, Node n) {
    ensureTyped(t, n, getNativeType(UNKNOWN_TYPE));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[607]++;
  }

  private void ensureTyped(NodeTraversal t, Node n, JSTypeNative type) {
    ensureTyped(t, n, getNativeType(type));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[608]++;
  }

  /**
   * Enforces type casts, and ensures the node is typed.
   *
   * A cast in the way that we use it in JSDoc annotations never
   * alters the generated code and therefore never can induce any runtime
   * operation. What this means is that a 'cast' is really just a compile
   * time constraint on the underlying value. In the future, we may add
   * support for run-time casts for compiled tests.
   *
   * To ensure some shred of sanity, we enforce the notion that the
   * type you are casting to may only meaningfully be a narrower type
   * than the underlying declared type. We also invalidate optimizations
   * on bad type casts.
   *
   * @param t The traversal object needed to report errors.
   * @param n The node getting a type assigned to it.
   * @param type The type to be assigned.
   */
  private void ensureTyped(NodeTraversal t, Node n, JSType type) {
    // Make sure FUNCTION nodes always get function type.
    Preconditions.checkState(!n.isFunction() ||
            type.isFunctionType() ||
            type.isUnknownType());
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[609]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[610]++;
    JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[611]++;
int CodeCoverConditionCoverageHelper_C131;
    if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[387]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[612]++;
int CodeCoverConditionCoverageHelper_C132;
      if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[389]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[613]++;
        // TODO(johnlenz): Change this so that we only look for casts on CAST
        // nodes one the misplaced type annotation warning is on by default and
        // people have been given a chance to fix them.  As is, this is here
        // simply for legacy casts.
        JSType infoType = info.getType().evaluate(t.getScope(), typeRegistry);
        validator.expectCanCast(t, n, infoType, type);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[614]++;
        type = infoType;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[615]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[390]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[616]++;
int CodeCoverConditionCoverageHelper_C133;

      if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (8)) == 0 || true) &&
 ((info.isImplicitCast()) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 2) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[391]++;
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[617]++;
        String propName = n.isGetProp() ?
            n.getLastChild().getString() : "(missing)";
        compiler.report(
            t.makeError(n, ILLEGAL_IMPLICIT_CAST, propName));
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[618]++;

      } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[392]++;}

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[388]++;}
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[619]++;
int CodeCoverConditionCoverageHelper_C134;

    if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((n.getJSType() == null) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$3k67fair6e5pawnox28601.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[393]++;
      n.setJSType(type);
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[620]++;

    } else {
  CodeCoverCoverageCounter$3k67fair6e5pawnox28601.branches[394]++;}
  }

  /**
   * Returns the percentage of nodes typed by the type checker.
   * @return a number between 0.0 and 100.0
   */
  double getTypedPercent() {
CodeCoverCoverageCounter$3k67fair6e5pawnox28601.statements[621]++;
    int total = nullCount + unknownCount + typedCount;
    return (total == 0) ? 0.0 : (100.0 * typedCount) / total;
  }

  private JSType getNativeType(JSTypeNative typeId) {
    return typeRegistry.getNativeType(typeId);
  }
}

class CodeCoverCoverageCounter$3k67fair6e5pawnox28601 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3k67fair6e5pawnox28601 ());
  }
    public static long[] statements = new long[622];
    public static long[] branches = new long[395];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[135];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TypeCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,3,1,1,1,1,2,2,1,1,1,1,1,2,1,1,1,1,2,1,1,3,1,1,1,1,1,2,1,2,1,2,1,3,1,1,1,1,2,1,1,1,2,2,3,2,1,1,1,2,1,2,2,1,1,1,1,3,1,2,1,1,1,2,3,3,3,1,1,1,1,1,3,0,1,2,1,1,2,3,1,1,1,1,1,1,1,2,1,3,1,1,1,1,3,1,1,1,1,1,3,1,2,2,1,2,1,1,2,1,1,1,2,3,3,3,1,2,1,1,1,1,1,2,1,1,1,1,2,1};
    for (int i = 1; i <= 134; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$3k67fair6e5pawnox28601 () {
    super("com.google.javascript.jscomp.TypeCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 621; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 394; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 134; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TypeCheck.java");
      for (int i = 1; i <= 621; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 394; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 134; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

