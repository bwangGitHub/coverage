/*
 * Copyright 2008 The Closure Compiler Authors.
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

import static com.google.javascript.jscomp.TypeCheck.BAD_IMPLEMENTED_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.FUNCTION_FUNCTION_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.VOID_TYPE;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSTypeExpression;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionBuilder;
import com.google.javascript.rhino.jstype.FunctionParamBuilder;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * A builder for FunctionTypes, because FunctionTypes are so
 * ridiculously complex. All methods return {@code this} for ease of use.
 *
 * Right now, this mostly uses JSDocInfo to infer type information about
 * functions. In the long term, developers should extend it to use other
 * signals by overloading the various "inferXXX" methods. For example, we
 * might want to use {@code goog.inherits} calls as a signal for inheritance, or
 * {@code return} statements as a signal for return type.
 *
 * NOTE(nicksantos): Organizationally, this feels like it should be in Rhino.
 * But it depends on some coding convention stuff that's really part
 * of JSCompiler.
 *
 * @author nicksantos@google.com (Nick Santos)
 * @author pascallouis@google.com (Pascal-Louis Perez)
 */
final class FunctionTypeBuilder {
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.ping();
  }


  private final String fnName;
  private final AbstractCompiler compiler;
  private final CodingConvention codingConvention;
  private final JSTypeRegistry typeRegistry;
  private final Node errorRoot;
  private final String sourceName;
  private final Scope scope;

  private FunctionContents contents = UnknownFunctionContents.get();
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[1]++;
  }

  private JSType returnType = null;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[2]++;
  }
  private boolean returnTypeInferred = false;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[3]++;
  }
  private List<ObjectType> implementedInterfaces = null;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[4]++;
  }
  private List<ObjectType> extendedInterfaces = null;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[5]++;
  }
  private ObjectType baseType = null;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[6]++;
  }
  private JSType thisType = null;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[7]++;
  }
  private boolean isConstructor = false;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[8]++;
  }
  private boolean makesStructs = false;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[9]++;
  }
  private boolean makesDicts = false;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[10]++;
  }
  private boolean isInterface = false;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[11]++;
  }
  private Node parametersNode = null;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[12]++;
  }
  private ImmutableList<String> templateTypeNames = ImmutableList.of();
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[13]++;
  }

  static final DiagnosticType EXTENDS_WITHOUT_TYPEDEF = DiagnosticType.warning(
      "JSC_EXTENDS_WITHOUT_TYPEDEF",
      "@extends used without @constructor or @interface for {0}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[14]++;
  }

  static final DiagnosticType EXTENDS_NON_OBJECT = DiagnosticType.warning(
      "JSC_EXTENDS_NON_OBJECT",
      "{0} @extends non-object type {1}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[15]++;
  }

  static final DiagnosticType RESOLVED_TAG_EMPTY = DiagnosticType.warning(
      "JSC_RESOLVED_TAG_EMPTY",
      "Could not resolve type in {0} tag of {1}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[16]++;
  }

  static final DiagnosticType IMPLEMENTS_WITHOUT_CONSTRUCTOR =
      DiagnosticType.warning(
          "JSC_IMPLEMENTS_WITHOUT_CONSTRUCTOR",
          "@implements used without @constructor or @interface for {0}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[17]++;
  }

  static final DiagnosticType CONSTRUCTOR_REQUIRED =
      DiagnosticType.warning("JSC_CONSTRUCTOR_REQUIRED",
                             "{0} used without @constructor for {1}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[18]++;
  }

  static final DiagnosticType VAR_ARGS_MUST_BE_LAST = DiagnosticType.warning(
      "JSC_VAR_ARGS_MUST_BE_LAST",
      "variable length argument must be last");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[19]++;
  }

  static final DiagnosticType OPTIONAL_ARG_AT_END = DiagnosticType.warning(
      "JSC_OPTIONAL_ARG_AT_END",
      "optional arguments must be at the end");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[20]++;
  }

  static final DiagnosticType INEXISTANT_PARAM = DiagnosticType.warning(
      "JSC_INEXISTANT_PARAM",
      "parameter {0} does not appear in {1}''s parameter list");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[21]++;
  }

  static final DiagnosticType TYPE_REDEFINITION = DiagnosticType.warning(
      "JSC_TYPE_REDEFINITION",
      "attempted re-definition of type {0}\n"
      + "found   : {1}\n"
      + "expected: {2}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[22]++;
  }

  static final DiagnosticType TEMPLATE_TYPE_DUPLICATED = DiagnosticType.warning(
      "JSC_TEMPLATE_TYPE_DUPLICATED",
      "Only one parameter type must be the template type");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[23]++;
  }

  static final DiagnosticType TEMPLATE_TYPE_EXPECTED = DiagnosticType.warning(
      "JSC_TEMPLATE_TYPE_EXPECTED",
      "The template type must be a parameter type");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[24]++;
  }

  static final DiagnosticType THIS_TYPE_NON_OBJECT =
      DiagnosticType.warning(
          "JSC_THIS_TYPE_NON_OBJECT",
          "@this type of a function must be an object\n" +
          "Actual type: {0}");
  static {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[25]++;
  }

  private class ExtendedTypeValidator implements Predicate<JSType> {
    @Override
    public boolean apply(JSType type) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[26]++;
      ObjectType objectType = ObjectType.cast(type);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[27]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((objectType == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[1]++;
        reportWarning(EXTENDS_NON_OBJECT, formatFnName(), type.toString());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[28]++;
        return false;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[2]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[29]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((objectType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[3]++;
        reportWarning(RESOLVED_TAG_EMPTY, "@extends", formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[30]++;
        return false;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[4]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[31]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((objectType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[5]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((hasMoreTagsToResolve(objectType)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[7]++;
          return true;

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[8]++;
          reportWarning(RESOLVED_TAG_EMPTY, "@extends", fnName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[33]++;
          return false;
        }

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[6]++;
        return true;
      }
}
}
    }
  }

  private class ImplementedTypeValidator implements Predicate<JSType> {
    @Override
    public boolean apply(JSType type) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[34]++;
      ObjectType objectType = ObjectType.cast(type);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((objectType == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[9]++;
        reportError(BAD_IMPLEMENTED_TYPE, fnName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[36]++;
        return false;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[10]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[37]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((objectType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[11]++;
        reportWarning(RESOLVED_TAG_EMPTY, "@implements", fnName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[38]++;
        return false;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[12]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[39]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((objectType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[13]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hasMoreTagsToResolve(objectType)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[15]++;
          return true;

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[16]++;
          reportWarning(RESOLVED_TAG_EMPTY, "@implements", fnName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[41]++;
          return false;
        }

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[14]++;
        return true;
      }
}
}
    }
  }

  /**
   * @param fnName The function name.
   * @param compiler The compiler.
   * @param errorRoot The node to associate with any warning generated by
   *     this builder.
   * @param sourceName A source name for associating any warnings that
   *     we have to emit.
   * @param scope The syntactic scope.
   */
  FunctionTypeBuilder(String fnName, AbstractCompiler compiler,
      Node errorRoot, String sourceName, Scope scope) {
    Preconditions.checkNotNull(errorRoot);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[42]++;

    this.fnName = fnName == null ? "" : fnName;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[43]++;
    this.codingConvention = compiler.getCodingConvention();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[44]++;
    this.typeRegistry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[45]++;
    this.errorRoot = errorRoot;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[46]++;
    this.sourceName = sourceName;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[47]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[48]++;
    this.scope = scope;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[49]++;
  }

  /** Format the function name for use in warnings. */
  String formatFnName() {
    return fnName.isEmpty() ? "<anonymous>" : fnName;
  }

  /**
   * Sets the contents of this function.
   */
  FunctionTypeBuilder setContents(@Nullable FunctionContents contents) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((contents != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[17]++;
      this.contents = contents;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[51]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[18]++;}
    return this;
  }

  /**
   * Infer the parameter and return types of a function from
   * the parameter and return types of the function it is overriding.
   *
   * @param oldType The function being overridden. Does nothing if this is null.
   * @param paramsParent The LP node of the function that we're assigning to.
   *     If null, that just means we're not initializing this to a function
   *     literal.
   */
  FunctionTypeBuilder inferFromOverriddenFunction(
      @Nullable FunctionType oldType, @Nullable Node paramsParent) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((oldType == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[19]++;
      return this;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[20]++;}

    returnType = oldType.getReturnType();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[53]++;
    returnTypeInferred = oldType.isReturnTypeInferred();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[54]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[55]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paramsParent == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[21]++;
      // Not a function literal.
      parametersNode = oldType.getParametersNode();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[56]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[57]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parametersNode == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[23]++;
        parametersNode = new FunctionParamBuilder(typeRegistry).build();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[58]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[24]++;}

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[22]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[59]++;
      // We're overriding with a function literal. Apply type information
      // to each parameter of the literal.
      FunctionParamBuilder paramBuilder =
          new FunctionParamBuilder(typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[60]++;
      Iterator<Node> oldParams = oldType.getParameters().iterator();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[61]++;
      boolean warnedAboutArgList = false;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[62]++;
      boolean oldParamsListHitOptArgs = false;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[63]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
      for (Node currentParam = paramsParent.getFirstChild();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((currentParam != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); currentParam = currentParam.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[1]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[2]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[3]++;
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((oldParams.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[25]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[65]++;
          Node oldParam = oldParams.next();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[66]++;
          Node newParam = paramBuilder.newParameterFromNode(oldParam);

          oldParamsListHitOptArgs = oldParamsListHitOptArgs ||
              oldParam.isVarArgs() ||
              oldParam.isOptionalArg();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[67]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[68]++;
int CodeCoverConditionCoverageHelper_C15;

          // The subclass method might write its var_args as individual
          // arguments.
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((currentParam.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((newParam.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[27]++;
            newParam.setVarArgs(false);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[69]++;
            newParam.setOptionalArg(true);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[70]++;

          } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[28]++;}

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[26]++;
          warnedAboutArgList |= addParameter(
              paramBuilder,
              typeRegistry.getNativeType(UNKNOWN_TYPE),
              warnedAboutArgList,
              codingConvention.isOptionalParameter(currentParam) ||
                  oldParamsListHitOptArgs,
              codingConvention.isVarArgsParameter(currentParam));
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[71]++;
        }
      }
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[72]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;

      // Clone any remaining params that aren't in the function literal,
      // but make them optional.
      while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((oldParams.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[4]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[5]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[6]++;
}
        paramBuilder.newOptionalParameterFromNode(oldParams.next());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[73]++;
      }

      parametersNode = paramBuilder.build();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[74]++;
    }
    return this;
  }

  /**
   * Infer the return type from JSDocInfo.
   */
  FunctionTypeBuilder inferReturnType(@Nullable JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[75]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((info.hasReturnType()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[29]++;
      returnType = info.getReturnType().evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[76]++;
      returnTypeInferred = false;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[77]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[30]++;}

    return this;
  }

  /**
   * Infer the role of the function (whether it's a constructor or interface)
   * and what it inherits from in JSDocInfo.
   */
  FunctionTypeBuilder inferInheritance(@Nullable JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[78]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[31]++;
      isConstructor = info.isConstructor();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[79]++;
      makesStructs = info.makesStructs();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[80]++;
      makesDicts = info.makesDicts();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[81]++;
      isInterface = info.isInterface();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[82]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((makesStructs) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isConstructor) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[33]++;
        reportWarning(CONSTRUCTOR_REQUIRED, "@struct", formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[84]++;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[34]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[85]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((makesDicts) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isConstructor) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[35]++;
        reportWarning(CONSTRUCTOR_REQUIRED, "@dict", formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[86]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[36]++;}
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;

      // base type
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((info.hasBaseType()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[37]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[88]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isConstructor) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[39]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[89]++;
          JSType maybeBaseType =
              info.getBaseType().evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[90]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((maybeBaseType != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((maybeBaseType.setValidator(new ExtendedTypeValidator())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[41]++;
            baseType = (ObjectType) maybeBaseType;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[91]++;

          } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[42]++;}

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[40]++;
          reportWarning(EXTENDS_WITHOUT_TYPEDEF, formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[92]++;
        }

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[38]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[93]++;
int CodeCoverConditionCoverageHelper_C24;

      // Implemented interfaces (for constructors only).
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((info.getImplementedInterfaceCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[43]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[94]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isConstructor) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[45]++;
          implementedInterfaces = Lists.newArrayList();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[95]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[96]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[7]++;


          for (JSTypeExpression t : info.getImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[7]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[8]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[9]++;
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[97]++;
            JSType maybeInterType = t.evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[98]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((maybeInterType != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((maybeInterType.setValidator(new ImplementedTypeValidator())) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[47]++;
              implementedInterfaces.add((ObjectType) maybeInterType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[99]++;

            } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[48]++;}
          }

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[46]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[100]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isInterface) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[49]++;
          reportWarning(
              TypeCheck.CONFLICTING_IMPLEMENTED_TYPE, formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[101]++;

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[50]++;
          reportWarning(CONSTRUCTOR_REQUIRED, "@implements", formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[102]++;
        }
}

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[44]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[103]++;
int CodeCoverConditionCoverageHelper_C28;

      // extended interfaces (for interfaces only)
      // We've already emitted a warning if this is not an interface.
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isInterface) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[51]++;
        extendedInterfaces = Lists.newArrayList();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[104]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[105]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[10]++;


        for (JSTypeExpression t : info.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[10]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[11]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[12]++;
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[106]++;
          JSType maybeInterfaceType = t.evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[107]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((maybeInterfaceType != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((maybeInterfaceType.setValidator(new ExtendedTypeValidator())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[53]++;
            extendedInterfaces.add((ObjectType) maybeInterfaceType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[108]++;

          } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[54]++;}
        }

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[52]++;}

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[32]++;}

    return this;
  }

  /**
   * Infers the type of {@code this}.
   * @param type The type of this if the info is missing.
   */
  FunctionTypeBuilder inferThisType(JSDocInfo info, JSType type) {
    // Look at the @this annotation first.
    inferThisType(info);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[109]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[110]++;
int CodeCoverConditionCoverageHelper_C30;

    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((thisType == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[55]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[111]++;
      ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[112]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((info.hasType()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[57]++;
        thisType = objType;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[113]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[58]++;}

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[56]++;}

    return this;
  }

  /**
   * Infers the type of {@code this}.
   * @param info The JSDocInfo for this function.
   */
  FunctionTypeBuilder inferThisType(JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[114]++;
    JSType maybeThisType = null;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[115]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((info.hasThisType()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[59]++;
      // TODO(johnlenz): In ES5 strict mode a function can have a null or
      // undefined "this" value, but all the existing "@this" annotations
      // don't declare restricted types.
      maybeThisType = info.getThisType().evaluate(scope, typeRegistry)
          .restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[116]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[60]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[117]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((maybeThisType != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[61]++;
      thisType = maybeThisType;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[118]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[62]++;}

    return this;
  }

  /**
   * Infer the parameter types from the doc info alone.
   */
  FunctionTypeBuilder inferParameterTypes(JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[119]++;
    // Create a fake args parent.
    Node lp = IR.paramList();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[120]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[13]++;


    for (String name : info.getParameterNames()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[13]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[14]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[15]++;
}
      lp.addChildToBack(IR.name(name));
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[121]++;
    }

    return inferParameterTypes(lp, info);
  }

  /**
   * Infer the parameter types from the list of argument names and
   * the doc info.
   */
  FunctionTypeBuilder inferParameterTypes(@Nullable Node argsParent,
      @Nullable JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[122]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((argsParent == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[63]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[123]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[65]++;
        return this;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[66]++;
        return inferParameterTypes(info);
      }

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[64]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[124]++;

    // arguments
    Node oldParameterType = null;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[125]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((parametersNode != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[67]++;
      oldParameterType = parametersNode.getFirstChild();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[126]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[68]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[127]++;

    FunctionParamBuilder builder = new FunctionParamBuilder(typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[128]++;
    boolean warnedAboutArgList = false;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[129]++;
    Set<String> allJsDocParams = (info == null) ?
        Sets.<String>newHashSet() :
        Sets.newHashSet(info.getParameterNames());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[130]++;
    boolean isVarArgs = false;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[131]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[16]++;


    for (Node arg : argsParent.children()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[16]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[17]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[18]++;
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[132]++;
      String argumentName = arg.getString();
      allJsDocParams.remove(argumentName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[133]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[134]++;

      // type from JSDocInfo
      JSType parameterType = null;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[135]++;
      boolean isOptionalParam = isOptionalParameter(arg, info);
      isVarArgs = isVarArgsParameter(arg, info);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[136]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[137]++;
int CodeCoverConditionCoverageHelper_C37;

      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((info.hasParameterType(argumentName)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[69]++;
        parameterType =
            info.getParameterType(argumentName).evaluate(scope, typeRegistry);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[138]++;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[70]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[139]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((oldParameterType != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((oldParameterType.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[71]++;
        parameterType = oldParameterType.getJSType();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[140]++;
        isOptionalParam = oldParameterType.isOptionalArg();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[141]++;
        isVarArgs = oldParameterType.isVarArgs();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[142]++;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[72]++;
        parameterType = typeRegistry.getNativeType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[143]++;
      }
}

      warnedAboutArgList |= addParameter(
          builder, parameterType, warnedAboutArgList,
          isOptionalParam,
          isVarArgs);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[144]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[145]++;
int CodeCoverConditionCoverageHelper_C39;

      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((oldParameterType != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[73]++;
        oldParameterType = oldParameterType.getNext();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[146]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[74]++;}
    }
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[147]++;
int CodeCoverConditionCoverageHelper_C40;

    // Copy over any old parameters that aren't in the param list.
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[75]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[148]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[19]++;


int CodeCoverConditionCoverageHelper_C41;
      while ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((oldParameterType != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[19]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[20]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[21]++;
}
        builder.newParameterFromNode(oldParameterType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[149]++;
        oldParameterType = oldParameterType.getNext();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[150]++;
      }

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[76]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[151]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[22]++;



    for (String inexistentName : allJsDocParams) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[22]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[23]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[24]++;
}
      reportWarning(INEXISTANT_PARAM, inexistentName, formatFnName());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[152]++;
    }

    parametersNode = builder.build();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[153]++;
    return this;
  }

  /**
   * @return Whether the given param is an optional param.
   */
  private boolean isOptionalParameter(
      Node param, @Nullable JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[154]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((codingConvention.isOptionalParameter(param)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[77]++;
      return true;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[78]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[155]++;

    String paramName = param.getString();
    return info != null && info.hasParameterType(paramName) &&
        info.getParameterType(paramName).isOptionalArg();
  }

  /**
   * Determine whether this is a var args parameter.
   * @return Whether the given param is a var args param.
   */
  private boolean isVarArgsParameter(
      Node param, @Nullable JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[156]++;
int CodeCoverConditionCoverageHelper_C43;
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((codingConvention.isVarArgsParameter(param)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[79]++;
      return true;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[80]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[157]++;

    String paramName = param.getString();
    return info != null && info.hasParameterType(paramName) &&
        info.getParameterType(paramName).isVarArgs();
  }

  /**
   * Infer the template type from the doc info.
   */
  FunctionTypeBuilder inferTemplateTypeName(@Nullable JSDocInfo info) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[158]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[81]++;
      templateTypeNames = info.getTemplateTypeNames();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[159]++;
      typeRegistry.setTemplateTypeNames(templateTypeNames);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[160]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[82]++;}
    return this;
  }

  /**
   * Add a parameter to the param list.
   * @param builder A builder.
   * @param paramType The parameter type.
   * @param warnedAboutArgList Whether we've already warned about arg ordering
   *     issues (like if optional args appeared before required ones).
   * @param isOptional Is this an optional parameter?
   * @param isVarArgs Is this a var args parameter?
   * @return Whether a warning was emitted.
   */
  private boolean addParameter(FunctionParamBuilder builder,
      JSType paramType, boolean warnedAboutArgList,
      boolean isOptional, boolean isVarArgs) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[161]++;
    boolean emittedWarning = false;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[162]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((isOptional) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[83]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[163]++;
int CodeCoverConditionCoverageHelper_C46;
      // Remembering that an optional parameter has been encountered
      // so that if a non optional param is encountered later, an
      // error can be reported.
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((builder.addOptionalParams(paramType)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((warnedAboutArgList) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[85]++;
        reportWarning(VAR_ARGS_MUST_BE_LAST);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[164]++;
        emittedWarning = true;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[165]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[86]++;}

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[84]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[166]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((isVarArgs) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[87]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[167]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((builder.addVarArgs(paramType)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((warnedAboutArgList) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[89]++;
        reportWarning(VAR_ARGS_MUST_BE_LAST);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[168]++;
        emittedWarning = true;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[169]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[90]++;}

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[88]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[170]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((builder.addRequiredParams(paramType)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((warnedAboutArgList) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[91]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[171]++;
int CodeCoverConditionCoverageHelper_C50;
        // An optional parameter was seen and this argument is not an optional
        // or var arg so it is an error.
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((builder.hasVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[93]++;
          reportWarning(VAR_ARGS_MUST_BE_LAST);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[172]++;

        } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[94]++;
          reportWarning(OPTIONAL_ARG_AT_END);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[173]++;
        }
        emittedWarning = true;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[174]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[92]++;}
    }
}
    return emittedWarning;
  }

  /**
   * Builds the function type, and puts it in the registry.
   */
  FunctionType buildAndRegister() {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[175]++;
int CodeCoverConditionCoverageHelper_C51;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((returnType == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[95]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[176]++;
int CodeCoverConditionCoverageHelper_C52;
      // Infer return types.
      // We need to be extremely conservative about this, because of two
      // competing needs.
      // 1) If we infer the return type of f too widely, then we won't be able
      //    to assign f to other functions.
      // 2) If we infer the return type of f too narrowly, then we won't be
      //    able to override f in subclasses.
      // So we only infer in cases where the user doesn't expect to write
      // @return annotations--when it's very obvious that the function returns
      // nothing.
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (32)) == 0 || true) &&
 ((contents.mayHaveNonEmptyReturns()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((contents.mayHaveSingleThrow()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((contents.mayBeFromExterns()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 3) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 3) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[97]++;
        returnType = typeRegistry.getNativeType(VOID_TYPE);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[177]++;
        returnTypeInferred = true;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[178]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[98]++;}

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[96]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[179]++;
int CodeCoverConditionCoverageHelper_C53;

    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((returnType == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[99]++;
      returnType = typeRegistry.getNativeType(UNKNOWN_TYPE);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[180]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[100]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[181]++;
int CodeCoverConditionCoverageHelper_C54;

    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((parametersNode == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[101]++;
      throw new IllegalStateException(
          "All Function types must have params and a return type");

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[102]++;}

    FunctionType fnType;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[182]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((isConstructor) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[103]++;
      fnType = getOrCreateConstructor();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[183]++;

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[104]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[184]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((isInterface) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[105]++;
      fnType = typeRegistry.createInterfaceType(
          fnName, contents.getSourceNode());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[185]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[186]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((getScopeDeclaredIn().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((fnName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[107]++;
        typeRegistry.declareType(fnName, fnType.getInstanceType());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[187]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[108]++;}
      maybeSetBaseType(fnType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[188]++;

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[106]++;
      fnType = new FunctionBuilder(typeRegistry)
          .withName(fnName)
          .withSourceNode(contents.getSourceNode())
          .withParamsNode(parametersNode)
          .withReturnType(returnType, returnTypeInferred)
          .withTypeOfThis(thisType)
          .withTemplateKeys(templateTypeNames)
          .build();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[189]++;
      maybeSetBaseType(fnType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[190]++;
    }
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[191]++;
int CodeCoverConditionCoverageHelper_C58;

    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((implementedInterfaces != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[109]++;
      fnType.setImplementedInterfaces(implementedInterfaces);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[192]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[110]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[193]++;
int CodeCoverConditionCoverageHelper_C59;

    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((extendedInterfaces != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[111]++;
      fnType.setExtendedInterfaces(extendedInterfaces);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[194]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[112]++;}

    typeRegistry.clearTemplateTypeNames();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[195]++;

    return fnType;
  }

  private void maybeSetBaseType(FunctionType fnType) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[196]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((fnType.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((baseType != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[113]++;
      fnType.setPrototypeBasedOn(baseType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[197]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[114]++;}
  }

  /**
   * Returns a constructor function either by returning it from the
   * registry if it exists or creating and registering a new type. If
   * there is already a type, then warn if the existing type is
   * different than the one we are creating, though still return the
   * existing function if possible.  The primary purpose of this is
   * that registering a constructor will fail for all built-in types
   * that are initialized in {@link JSTypeRegistry}.  We a) want to
   * make sure that the type information specified in the externs file
   * matches what is in the registry and b) annotate the externs with
   * the {@link JSType} from the registry so that there are not two
   * separate JSType objects for one type.
   */
  private FunctionType getOrCreateConstructor() {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[198]++;
    FunctionType fnType = typeRegistry.createConstructorType(
        fnName, contents.getSourceNode(), parametersNode, returnType, null);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[199]++;
    JSType existingType = typeRegistry.getType(fnName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[200]++;
int CodeCoverConditionCoverageHelper_C61;

    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((makesStructs) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[115]++;
      fnType.setStruct();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[201]++;

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[116]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[202]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((makesDicts) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[117]++;
      fnType.setDict();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[203]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[118]++;}
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[204]++;
int CodeCoverConditionCoverageHelper_C63;
    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((existingType != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[119]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[205]++;
      boolean isInstanceObject = existingType.isInstanceType();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[206]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((isInstanceObject) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((fnName.equals("Function")) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[121]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[207]++;
        FunctionType existingFn =
            isInstanceObject ?
            existingType.toObjectType().getConstructor() :
            typeRegistry.getNativeFunctionType(FUNCTION_FUNCTION_TYPE);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[208]++;
int CodeCoverConditionCoverageHelper_C65;

        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((existingFn.getSource() == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[123]++;
          existingFn.setSource(contents.getSourceNode());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[209]++;

        } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[124]++;}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[210]++;
int CodeCoverConditionCoverageHelper_C66;

        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((existingFn.hasEqualCallType(fnType)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[125]++;
          reportWarning(TYPE_REDEFINITION, formatFnName(),
              fnType.toString(), existingFn.toString());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[211]++;

        } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[126]++;}

        return existingFn;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[122]++;
        // We fall through and return the created type, even though it will fail
        // to register. We have no choice as we have to return a function. We
        // issue an error elsewhere though, so the user should fix it.
      }

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[120]++;}

    maybeSetBaseType(fnType);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[212]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[213]++;
int CodeCoverConditionCoverageHelper_C67;

    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((getScopeDeclaredIn().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((fnName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[127]++;
      typeRegistry.declareType(fnName, fnType.getInstanceType());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[214]++;

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[128]++;}
    return fnType;
  }

  private void reportWarning(DiagnosticType warning, String ... args) {
    compiler.report(JSError.make(sourceName, errorRoot, warning, args));
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[215]++;
  }

  private void reportError(DiagnosticType error, String ... args) {
    compiler.report(JSError.make(sourceName, errorRoot, error, args));
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[216]++;
  }

  /**
   * Determines whether the given JsDoc info declares a function type.
   */
  static boolean isFunctionTypeDeclaration(JSDocInfo info) {
    return info.getParameterCount() > 0 ||
        info.hasReturnType() ||
        info.hasThisType() ||
        info.isConstructor() ||
        info.isInterface();
  }

  /**
   * The scope that we should declare this function in, if it needs
   * to be declared in a scope. Notice that TypedScopeCreator takes
   * care of most scope-declaring.
   */
  private Scope getScopeDeclaredIn() {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[217]++;
    int dotIndex = fnName.indexOf(".");
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[218]++;
int CodeCoverConditionCoverageHelper_C68;
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((dotIndex != -1) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[129]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[219]++;
      String rootVarName = fnName.substring(0, dotIndex);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[220]++;
      Var rootVar = scope.getVar(rootVarName);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[221]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((rootVar != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[131]++;
        return rootVar.getScope();

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[132]++;}

    } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[130]++;}
    return scope;
  }

  /**
   * Check whether a type is resolvable in the future
   * If this has a supertype that hasn't been resolved yet, then we can assume
   * this type will be OK once the super type resolves.
   * @param objectType
   * @return true if objectType is resolvable in the future
   */
  private static boolean hasMoreTagsToResolve(ObjectType objectType) {
    Preconditions.checkArgument(objectType.isUnknownType());
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[222]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[223]++;
int CodeCoverConditionCoverageHelper_C70;
    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((objectType.getImplicitPrototype() != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[133]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[224]++;
int CodeCoverConditionCoverageHelper_C71;
      // constructor extends class
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((objectType.getImplicitPrototype().isResolved()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[135]++;
        return false;

      } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[136]++;
        return true;
      }

    } else {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[134]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[225]++;
      // interface extends interfaces
      FunctionType ctor = objectType.getConstructor();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[226]++;
int CodeCoverConditionCoverageHelper_C72;
      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((ctor != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[137]++;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[227]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[25]++;


        for (ObjectType interfaceType : ctor.getExtendedInterfaces()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[25]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[26]--;
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.loops[27]++;
}
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[228]++;
int CodeCoverConditionCoverageHelper_C73;
          if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((interfaceType.isResolved()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[139]++;
            return true;

          } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[140]++;}
        }

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[138]++;}
      return false;
    }
  }

  /** Holds data dynamically inferred about functions. */
  static interface FunctionContents {
    /** Returns the source node of this function. May be null. */
    Node getSourceNode();

    /** Returns if the function may be in externs. */
    boolean mayBeFromExterns();

    /** Returns if a return of a real value (not undefined) appears. */
    boolean mayHaveNonEmptyReturns();

    /** Returns if this consists of a single throw. */
    boolean mayHaveSingleThrow();

    /** Gets a list of variables in this scope that are escaped. */
    Iterable<String> getEscapedVarNames();

    /** Gets a list of variables whose properties are escaped. */
    Set<String> getEscapedQualifiedNames();

    /** Gets the number of times each variable has been assigned. */
    Multiset<String> getAssignedNameCounts();
  }

  static class UnknownFunctionContents implements FunctionContents {
    private static UnknownFunctionContents singleton =
        new UnknownFunctionContents();

    static FunctionContents get() {
      return singleton;
    }

    @Override
    public Node getSourceNode() {
      return null;
    }

    @Override
    public boolean mayBeFromExterns() {
      return true;
    }

    @Override
    public boolean mayHaveNonEmptyReturns() {
      return true;
    }

    @Override
    public boolean mayHaveSingleThrow() {
      return true;
    }

    @Override
    public Iterable<String> getEscapedVarNames() {
      return ImmutableList.of();
    }

    @Override
    public Set<String> getEscapedQualifiedNames() {
      return ImmutableSet.of();
    }

    @Override
    public Multiset<String> getAssignedNameCounts() {
      return ImmutableMultiset.of();
    }
  }

  static class AstFunctionContents implements FunctionContents {
    private final Node n;
    private boolean hasNonEmptyReturns = false;
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[229]++;
  }
    private Set<String> escapedVarNames;
    private Set<String> escapedQualifiedNames;
    private final Multiset<String> assignedVarNames = HashMultiset.create();
  {
    CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[230]++;
  }

    AstFunctionContents(Node n) {
      this.n = n;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[231]++;
    }

    @Override
    public Node getSourceNode() {
      return n;
    }

    @Override
    public boolean mayBeFromExterns() {
      return n.isFromExterns();
    }

    @Override
    public boolean mayHaveNonEmptyReturns() {
      return hasNonEmptyReturns;
    }

    void recordNonEmptyReturn() {
      hasNonEmptyReturns = true;
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[232]++;
    }

    @Override
    public boolean mayHaveSingleThrow() {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[233]++;
      Node block = n.getLastChild();
      return block.hasOneChild() && block.getFirstChild().isThrow();
    }

    @Override
    public Iterable<String> getEscapedVarNames() {
      return escapedVarNames == null
          ? ImmutableList.<String>of() : escapedVarNames;
    }

    void recordEscapedVarName(String name) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[234]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((escapedVarNames == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[141]++;
        escapedVarNames = Sets.newHashSet();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[235]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[142]++;}
      escapedVarNames.add(name);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[236]++;
    }

    @Override
    public Set<String> getEscapedQualifiedNames() {
      return escapedQualifiedNames == null
          ? ImmutableSet.<String>of() : escapedQualifiedNames;
    }

    void recordEscapedQualifiedName(String name) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[237]++;
int CodeCoverConditionCoverageHelper_C75;
      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((escapedQualifiedNames == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[143]++;
        escapedQualifiedNames = Sets.newHashSet();
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[238]++;

      } else {
  CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.branches[144]++;}
      escapedQualifiedNames.add(name);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[239]++;
    }

    @Override
    public Multiset<String> getAssignedNameCounts() {
      return assignedVarNames;
    }

    void recordAssignedName(String name) {
      assignedVarNames.add(name);
CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep.statements[240]++;
    }
  }
}

class CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep ());
  }
    public static long[] statements = new long[241];
    public static long[] branches = new long[145];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[76];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FunctionTypeBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,2,1,1,2,1,1,2,1,1,2,1,3,2,1,1,1,1,2,2,1,1,2,1,1,1,1,2,1,2,2,1,1,3,1,1,1,1,2,1,1,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 75; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$g8lyu2m0ckxdovgzlprczut4bksawgwrk51ep () {
    super("com.google.javascript.jscomp.FunctionTypeBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 240; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 144; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 75; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FunctionTypeBuilder.java");
      for (int i = 1; i <= 240; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 144; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 75; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

