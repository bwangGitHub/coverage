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

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSDocInfo.Visibility;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticSourceFile;

/**
 * A compiler pass that checks that the programmer has obeyed all the access
 * control restrictions indicated by JSDoc annotations, like
 * {@code @private} and {@code @deprecated}.
 *
 * Because access control restrictions are attached to type information,
 * it's important that TypedScopeCreator, TypeInference, and InferJSDocInfo
 * all run before this pass. TypedScopeCreator creates and resolves types,
 * TypeInference propagates those types across the AST, and InferJSDocInfo
 * propagates JSDoc across the types.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class CheckAccessControls implements ScopedCallback, HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.ping();
  }


  static final DiagnosticType DEPRECATED_NAME = DiagnosticType.disabled(
      "JSC_DEPRECATED_VAR",
      "Variable {0} has been deprecated.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[1]++;
  }

  static final DiagnosticType DEPRECATED_NAME_REASON = DiagnosticType.disabled(
      "JSC_DEPRECATED_VAR_REASON",
      "Variable {0} has been deprecated: {1}");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[2]++;
  }

  static final DiagnosticType DEPRECATED_PROP = DiagnosticType.disabled(
      "JSC_DEPRECATED_PROP",
      "Property {0} of type {1} has been deprecated.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[3]++;
  }

  static final DiagnosticType DEPRECATED_PROP_REASON = DiagnosticType.disabled(
      "JSC_DEPRECATED_PROP_REASON",
      "Property {0} of type {1} has been deprecated: {2}");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[4]++;
  }

  static final DiagnosticType DEPRECATED_CLASS = DiagnosticType.disabled(
      "JSC_DEPRECATED_CLASS",
      "Class {0} has been deprecated.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[5]++;
  }

  static final DiagnosticType DEPRECATED_CLASS_REASON = DiagnosticType.disabled(
      "JSC_DEPRECATED_CLASS_REASON",
      "Class {0} has been deprecated: {1}");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[6]++;
  }

  static final DiagnosticType BAD_PRIVATE_GLOBAL_ACCESS =
      DiagnosticType.disabled(
          "JSC_BAD_PRIVATE_GLOBAL_ACCESS",
          "Access to private variable {0} not allowed outside file {1}.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[7]++;
  }

  static final DiagnosticType BAD_PRIVATE_PROPERTY_ACCESS =
      DiagnosticType.disabled(
          "JSC_BAD_PRIVATE_PROPERTY_ACCESS",
          "Access to private property {0} of {1} not allowed here.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[8]++;
  }

  static final DiagnosticType BAD_PROTECTED_PROPERTY_ACCESS =
      DiagnosticType.disabled(
          "JSC_BAD_PROTECTED_PROPERTY_ACCESS",
          "Access to protected property {0} of {1} not allowed here.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[9]++;
  }

  static final DiagnosticType PRIVATE_OVERRIDE =
      DiagnosticType.disabled(
          "JSC_PRIVATE_OVERRIDE",
          "Overriding private property of {0}.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[10]++;
  }

  static final DiagnosticType EXTEND_FINAL_CLASS =
      DiagnosticType.error(
          "JSC_EXTEND_FINAL_CLASS",
          "{0} is not allowed to extend final class {1}.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[11]++;
  }

  static final DiagnosticType VISIBILITY_MISMATCH =
      DiagnosticType.disabled(
          "JSC_VISIBILITY_MISMATCH",
          "Overriding {0} property of {1} with {2} property.");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[12]++;
  }

  static final DiagnosticType CONST_PROPERTY_REASSIGNED_VALUE =
      DiagnosticType.warning(
        "JSC_CONSTANT_PROPERTY_REASSIGNED_VALUE",
        "constant property {0} assigned a value more than once");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[13]++;
  }

  static final DiagnosticType CONST_PROPERTY_DELETED =
      DiagnosticType.warning(
        "JSC_CONSTANT_PROPERTY_DELETED",
        "constant property {0} cannot be deleted");
  static {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[14]++;
  }

  private final AbstractCompiler compiler;
  private final TypeValidator validator;

  // State about the current traversal.
  private int deprecatedDepth = 0;
  {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[15]++;
  }
  private int methodDepth = 0;
  {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[16]++;
  }
  private JSType currentClass = null;
  {
    CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[17]++;
  }

  private final Multimap<String, String> initializedConstantProperties;

  CheckAccessControls(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[18]++;
    this.validator = compiler.getTypeValidator();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[19]++;
    this.initializedConstantProperties = HashMultimap.create();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[20]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[21]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    NodeTraversal.traverse(compiler, scriptRoot, this);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[22]++;
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[23]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[1]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[24]++;
      Node n = t.getScopeRoot();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[25]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isDeprecatedFunction(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[3]++;
        deprecatedDepth++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[27]++;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[4]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((methodDepth == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[5]++;
        currentClass = getClassOfMethod(n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[29]++;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[6]++;}
      methodDepth++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[30]++;

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[2]++;}
  }

  @Override
  public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[7]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[32]++;
      Node n = t.getScopeRoot();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isDeprecatedFunction(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[9]++;
        deprecatedDepth--;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[34]++;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[10]++;}

      methodDepth--;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[35]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((methodDepth == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[11]++;
        currentClass = null;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[37]++;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[12]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[8]++;}
  }

  /**
   * Gets the type of the class that "owns" a method, or null if
   * we know that its un-owned.
   */
  private JSType getClassOfMethod(Node n, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[13]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[39]++;
      Node lValue = parent.getFirstChild();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[15]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[41]++;
        // We have an assignment of the form "a.b = ...".
        JSType lValueType = lValue.getJSType();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((lValueType != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((lValueType.isNominalConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[17]++;
          // If a.b is a constructor, then everything in this function
          // belongs to the "a.b" type.
          return (lValueType.toMaybeFunctionType()).getInstanceType();

        } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[18]++;
          // If a.b is not a constructor, then treat this as a method
          // of whatever type is on "a".
          return normalizeClassType(lValue.getFirstChild().getJSType());
        }

      } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[16]++;
        // We have an assignment of the form "a = ...", so pull the
        // type off the "a".
        return normalizeClassType(lValue.getJSType());
      }

    } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[14]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[43]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[19]++;
      return normalizeClassType(n.getJSType());

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[20]++;}
}

    return null;
  }

  /**
   * Normalize the type of a constructor, its instance, and its prototype
   * all down to the same type (the instance type).
   */
  private JSType normalizeClassType(JSType type) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[21]++;
      return type;

    } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[22]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[45]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type.isNominalConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[23]++;
      return (type.toMaybeFunctionType()).getInstanceType();

    } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[24]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[46]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[25]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[47]++;
      FunctionType owner = ((ObjectType) type).getOwnerFunction();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[48]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((owner.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[27]++;
        return owner.getInstanceType();

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[28]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[26]++;}
}
}
    return type;
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[49]++;
    switch (n.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[29]++;
        checkNameDeprecation(t, n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[50]++;
        checkNameVisibility(t, n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[51]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[52]++;
        break;
      case Token.GETPROP:
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[30]++;
        checkPropertyDeprecation(t, n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[53]++;
        checkPropertyVisibility(t, n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[54]++;
        checkConstantProperty(t, n);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[55]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[56]++;
        break;
      case Token.NEW:
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[31]++;
        checkConstructorDeprecation(t, n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[57]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[58]++;
        break;
      case Token.FUNCTION:
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[32]++;
        checkFinalClassOverrides(t, n, parent);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[59]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[60]++;
        break; default : CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[33]++;
    }
  }

  /**
   * Checks the given NEW node to ensure that access restrictions are obeyed.
   */
  private void checkConstructorDeprecation(NodeTraversal t, Node n,
      Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[61]++;
    JSType type = n.getJSType();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[34]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[63]++;
      String deprecationInfo = getTypeDeprecationInfo(type);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((deprecationInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((shouldEmitDeprecationWarning(t, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[36]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((deprecationInfo.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[38]++;
            compiler.report(
                t.makeError(n, DEPRECATED_CLASS_REASON,
                    type.toString(), deprecationInfo));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[66]++;

        } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[39]++;
          compiler.report(
              t.makeError(n, DEPRECATED_CLASS, type.toString()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[67]++;
        }

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[37]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[35]++;}
  }

  /**
   * Checks the given NAME node to ensure that access restrictions are obeyed.
   */
  private void checkNameDeprecation(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[68]++;
int CodeCoverConditionCoverageHelper_C18;
    // Don't bother checking definitions or constructors.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((parent.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 3) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[40]++;
      return;

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[41]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[69]++;

    Scope.Var var = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[70]++;
    JSDocInfo docInfo = var == null ? null : var.getJSDocInfo();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((docInfo.isDeprecated()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((shouldEmitDeprecationWarning(t, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[42]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[72]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((docInfo.getDeprecationReason() != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[44]++;
        compiler.report(
            t.makeError(n, DEPRECATED_NAME_REASON, n.getString(),
                docInfo.getDeprecationReason()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[73]++;

      } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[45]++;
        compiler.report(
            t.makeError(n, DEPRECATED_NAME, n.getString()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[74]++;
      }

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[43]++;}
  }

  /**
   * Checks the given GETPROP node to ensure that access restrictions are
   * obeyed.
   */
  private void checkPropertyDeprecation(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[75]++;
int CodeCoverConditionCoverageHelper_C21;
    // Don't bother checking constructors.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((parent.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[46]++;
      return;

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[47]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[76]++;

    ObjectType objectType =
        ObjectType.cast(dereference(n.getFirstChild().getJSType()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[77]++;
    String propertyName = n.getLastChild().getString();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[78]++;
int CodeCoverConditionCoverageHelper_C22;

    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[48]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[79]++;
      String deprecationInfo
          = getPropertyDeprecationInfo(objectType, propertyName);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[80]++;
int CodeCoverConditionCoverageHelper_C23;

      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((deprecationInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((shouldEmitDeprecationWarning(t, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[50]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[81]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((deprecationInfo.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[52]++;
          compiler.report(
              t.makeError(n, DEPRECATED_PROP_REASON, propertyName,
                  validator.getReadableJSTypeName(n.getFirstChild(), true),
                  deprecationInfo));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[82]++;

        } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[53]++;
          compiler.report(
              t.makeError(n, DEPRECATED_PROP, propertyName,
                  validator.getReadableJSTypeName(n.getFirstChild(), true)));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[83]++;
        }

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[51]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[49]++;}
  }

  /**
   * Determines whether the given name is visible in the current context.
   * @param t The current traversal.
   * @param name The name node.
   */
  private void checkNameVisibility(NodeTraversal t, Node name, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[84]++;
    Var var = t.getScope().getVar(name.getString());
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[54]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[86]++;
      JSDocInfo docInfo = var.getJSDocInfo();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[87]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[56]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[88]++;
        // If a name is private, make sure that we're in the same file.
        Visibility visibility = docInfo.getVisibility();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[89]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((visibility == Visibility.PRIVATE) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[58]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[90]++;
          StaticSourceFile varSrc = var.getSourceFile();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[91]++;
          StaticSourceFile refSrc = name.getStaticSourceFile();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[92]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (32)) == 0 || true) &&
 ((varSrc != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((refSrc != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((varSrc.getName().equals(refSrc.getName())) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[60]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[93]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((docInfo.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isValidPrivateConstructorAccess(parent)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[62]++;
              return;

            } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[63]++;}

            compiler.report(
                t.makeError(name, BAD_PRIVATE_GLOBAL_ACCESS,
                    name.getString(), varSrc.getName()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[94]++;

          } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[61]++;}

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[59]++;}

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[57]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[55]++;}
  }

  /**
   * Checks if a constructor is trying to override a final class.
   */
  private void checkFinalClassOverrides(NodeTraversal t, Node fn, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[95]++;
    JSType type = fn.getJSType().toMaybeFunctionType();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[96]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((type.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[64]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[97]++;
      JSType finalParentClass = getFinalParentClass(getClassOfMethod(fn, parent));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[98]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((finalParentClass != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[66]++;
        compiler.report(
            t.makeError(fn, EXTEND_FINAL_CLASS,
                type.getDisplayName(), finalParentClass.getDisplayName()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[99]++;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[67]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[65]++;}
  }

  /**
   * Determines whether the given property with @const tag got reassigned
   * @param t The current traversal.
   * @param getprop The getprop node.
   */
  private void checkConstantProperty(NodeTraversal t,
      Node getprop) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[100]++;
    // Check whether the property is modified
    Node parent = getprop.getParent();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[101]++;
    boolean isDelete = parent.isDelProp();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[102]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C32 |= (512)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (128)) == 0 || true) &&
 ((parent.getFirstChild() == getprop) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (64)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((parent.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((parent.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isDelete) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 5) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 5) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[68]++;
      return;

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[69]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[103]++;

    ObjectType objectType =
      ObjectType.cast(dereference(getprop.getFirstChild().getJSType()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[104]++;
    String propertyName = getprop.getLastChild().getString();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[105]++;

    boolean isConstant = isPropertyDeclaredConstant(objectType, propertyName);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[106]++;
int CodeCoverConditionCoverageHelper_C33;

    // Check whether constant properties are reassigned
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((isConstant) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[70]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[107]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isDelete) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[72]++;
        compiler.report(
            t.makeError(getprop, CONST_PROPERTY_DELETED, propertyName));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[108]++;
        return;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[73]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[109]++;

      ObjectType oType = objectType;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[110]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[1]++;


int CodeCoverConditionCoverageHelper_C35;
      while ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((oType != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[1]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[2]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[3]++;
}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[111]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((oType.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[74]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[112]++;
int CodeCoverConditionCoverageHelper_C37;
          if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((initializedConstantProperties.containsEntry(
                  oType.getReferenceName(), propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[76]++;
            compiler.report(
                t.makeError(getprop, CONST_PROPERTY_REASSIGNED_VALUE,
                    propertyName));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[113]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[114]++;
            break;

          } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[77]++;}

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[75]++;}
        oType = oType.getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[115]++;
      }

      Preconditions.checkState(objectType.hasReferenceName());
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[116]++;
      initializedConstantProperties.put(objectType.getReferenceName(),
          propertyName);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[117]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[118]++;
int CodeCoverConditionCoverageHelper_C38;

      // Add the prototype when we're looking at an instance object
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((objectType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[78]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[119]++;
        ObjectType prototype = objectType.getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[120]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((prototype != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[80]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[121]++;
int CodeCoverConditionCoverageHelper_C40;
          if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((prototype.hasProperty(propertyName)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((prototype.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[82]++;
            initializedConstantProperties.put(prototype.getReferenceName(),
                propertyName);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[122]++;

          } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[83]++;}

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[81]++;}

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[79]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[71]++;}
  }

  /**
   * Determines whether the given property is visible in the current context.
   * @param t The current traversal.
   * @param getprop The getprop node.
   */
  private void checkPropertyVisibility(NodeTraversal t,
      Node getprop, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[123]++;
    ObjectType objectType =
        ObjectType.cast(dereference(getprop.getFirstChild().getJSType()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[124]++;
    String propertyName = getprop.getLastChild().getString();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[125]++;
int CodeCoverConditionCoverageHelper_C41;

    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[84]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[126]++;
      // Is this a normal property access, or are we trying to override
      // an existing property?
      boolean isOverride = parent.getJSDocInfo() != null &&
          parent.isAssign() &&
          parent.getFirstChild() == getprop;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[127]++;
int CodeCoverConditionCoverageHelper_C42;

      // Find the lowest property defined on a class with visibility
      // information.
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((isOverride) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[86]++;
        objectType = objectType.getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[128]++;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[87]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[129]++;
      JSDocInfo docInfo = null;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[130]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[4]++;


int CodeCoverConditionCoverageHelper_C43;
      for (;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false);
           objectType = objectType.getImplicitPrototype()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[4]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[5]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[6]++;
}
        docInfo = objectType.getOwnPropertyJSDocInfo(propertyName);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[131]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[132]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((docInfo.getVisibility() != Visibility.INHERITED) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[88]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[133]++;
          break;

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[89]++;}
      }
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[134]++;
int CodeCoverConditionCoverageHelper_C45;

      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((objectType == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[90]++;
        // We couldn't find a visibility modifier; assume it's public.
        return;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[91]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[135]++;

      String referenceSource = getprop.getSourceFileName();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[136]++;
      String definingSource = docInfo.getSourceName();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[137]++;
      boolean sameInput = referenceSource != null
          && referenceSource.equals(definingSource);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[138]++;
      Visibility visibility = docInfo.getVisibility();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[139]++;
      JSType ownerType = normalizeClassType(objectType);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[140]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((isOverride) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[92]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[141]++;
        // Check an ASSIGN statement that's trying to override a property
        // on a superclass.
        JSDocInfo overridingInfo = parent.getJSDocInfo();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[142]++;
        Visibility overridingVisibility = overridingInfo == null ?
            Visibility.INHERITED : overridingInfo.getVisibility();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[143]++;
int CodeCoverConditionCoverageHelper_C47;

        // Check that (a) the property *can* be overridden, and
        // (b) that the visibility of the override is the same as the
        // visibility of the original property.
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((visibility == Visibility.PRIVATE) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((sameInput) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[94]++;
          compiler.report(
              t.makeError(getprop, PRIVATE_OVERRIDE,
                  objectType.toString()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[144]++;

        } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[95]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[145]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((overridingVisibility != Visibility.INHERITED) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((overridingVisibility != visibility) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[96]++;
          compiler.report(
              t.makeError(getprop, VISIBILITY_MISMATCH,
                  visibility.name(), objectType.toString(),
                  overridingVisibility.name()));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[146]++;

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[97]++;}
}

      } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[93]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[147]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((sameInput) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[98]++;
          // private access is always allowed in the same file.
          return;

        } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[99]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[148]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((visibility == Visibility.PRIVATE) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((currentClass == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ownerType.isEquivalentTo(currentClass)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[100]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[149]++;
int CodeCoverConditionCoverageHelper_C51;
          if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((docInfo.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((isValidPrivateConstructorAccess(parent)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[102]++;
            return;

          } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[103]++;}

          // private access is not allowed outside the file from a different
          // enclosing class.
          compiler.report(
              t.makeError(getprop,
                  BAD_PRIVATE_PROPERTY_ACCESS,
                  propertyName,
                  validator.getReadableJSTypeName(
                      getprop.getFirstChild(), true)));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[150]++;

        } else {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[101]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[151]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((visibility == Visibility.PROTECTED) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[104]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[152]++;
int CodeCoverConditionCoverageHelper_C53;
          // There are 3 types of legal accesses of a protected property:
          // 1) Accesses in the same file
          // 2) Overriding the property in a subclass
          // 3) Accessing the property from inside a subclass
          // The first two have already been checked for.
          if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((currentClass == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((currentClass.isSubtype(ownerType)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[106]++;
            compiler.report(
                t.makeError(getprop,  BAD_PROTECTED_PROPERTY_ACCESS,
                    propertyName,
                    validator.getReadableJSTypeName(
                        getprop.getFirstChild(), true)));
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[153]++;

          } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[107]++;}

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[105]++;}
}
}
      }

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[85]++;}
  }

  /**
   * Whether the given access of a private constructor is legal.
   *
   * For example,
   * new PrivateCtor_(); // not legal
   * PrivateCtor_.newInstance(); // legal
   * x instanceof PrivateCtor_ // legal
   *
   * This is a weird special case, because our visibility system is inherited
   * from Java, and JavaScript has no distinction between classes and
   * constructors like Java does.
   *
   * We may want to revisit this if we decide to make the restrictions tighter.
   */
  private static boolean isValidPrivateConstructorAccess(Node parent) {
    return !parent.isNew();
  }

  /**
   * Determines whether a deprecation warning should be emitted.
   * @param t The current traversal.
   * @param n The node which we are checking.
   * @param parent The parent of the node which we are checking.
   */
  private boolean shouldEmitDeprecationWarning(
      NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[154]++;
int CodeCoverConditionCoverageHelper_C54;
    // In the global scope, there are only two kinds of accesses that should
    // be flagged for warnings:
    // 1) Calls of deprecated functions and methods.
    // 2) Instantiations of deprecated classes.
    // For now, we just let everything else by.
    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[108]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[155]++;
int CodeCoverConditionCoverageHelper_C55;
      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!((
(((CodeCoverConditionCoverageHelper_C55 |= (32)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((n.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 3) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 3) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[110]++;
        return false;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[111]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[109]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[156]++;
int CodeCoverConditionCoverageHelper_C56;

    // We can always assign to a deprecated property, to keep it up to date.
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (32)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((n == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[112]++;
      return false;

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[113]++;}

    return !canAccessDeprecatedTypes(t);
  }

  /**
   * Returns whether it's currently OK to access deprecated names and
   * properties.
   *
   * There are 3 exceptions when we're allowed to use a deprecated
   * type or property:
   * 1) When we're in a deprecated function.
   * 2) When we're in a deprecated class.
   * 3) When we're in a static method of a deprecated class.
   */
  private boolean canAccessDeprecatedTypes(NodeTraversal t) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[157]++;
    Node scopeRoot = t.getScopeRoot();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[158]++;
    Node scopeRootParent = scopeRoot.getParent();
    return
      // Case #1
      (deprecatedDepth > 0) ||
      // Case #2
      (getTypeDeprecationInfo(t.getScope().getTypeOfThis()) != null) ||
        // Case #3
      (scopeRootParent != null && scopeRootParent.isAssign() &&
       getTypeDeprecationInfo(
           getClassOfMethod(scopeRoot, scopeRootParent)) != null);
  }

  /**
   * Returns whether this is a function node annotated as deprecated.
   */
  private static boolean isDeprecatedFunction(Node n) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[159]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[114]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[160]++;
      JSType type = n.getJSType();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[161]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[116]++;
        return getTypeDeprecationInfo(type) != null;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[117]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[115]++;}

    return false;
  }

  /**
   * Returns the deprecation reason for the type if it is marked
   * as being deprecated. Returns empty string if the type is deprecated
   * but no reason was given. Returns null if the type is not deprecated.
   */
  private static String getTypeDeprecationInfo(JSType type) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[162]++;
int CodeCoverConditionCoverageHelper_C59;
    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[118]++;
      return null;

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[119]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[163]++;

    JSDocInfo info = type.getJSDocInfo();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[164]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((info.isDeprecated()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[120]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[165]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((info.getDeprecationReason() != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[122]++;
        return info.getDeprecationReason();

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[123]++;}
      return "";

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[121]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[166]++;
    ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[167]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[124]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[168]++;
      ObjectType implicitProto = objType.getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[169]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((implicitProto != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[126]++;
        return getTypeDeprecationInfo(implicitProto);

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[127]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[125]++;}
    return null;
  }

  /**
   * Returns if a property is declared constant.
   */
  private static boolean isPropertyDeclaredConstant(
      ObjectType objectType, String prop) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[170]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[7]++;


int CodeCoverConditionCoverageHelper_C64;
    for (;(((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((// Only objects with reference names can have constant properties.
         objectType != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((objectType.hasReferenceName()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false);

         objectType = objectType.getImplicitPrototype()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[7]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[8]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[9]++;
}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[171]++;
      JSDocInfo docInfo = objectType.getOwnPropertyJSDocInfo(prop);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[172]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((docInfo.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[128]++;
        return true;

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[129]++;}
    }
    return false;
  }

  /**
   * Returns the deprecation reason for the property if it is marked
   * as being deprecated. Returns empty string if the property is deprecated
   * but no reason was given. Returns null if the property is not deprecated.
   */
  private static String getPropertyDeprecationInfo(ObjectType type,
                                                   String prop) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[173]++;
    JSDocInfo info = type.getOwnPropertyJSDocInfo(prop);
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[174]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((info.isDeprecated()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[130]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[175]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((info.getDeprecationReason() != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[132]++;
        return info.getDeprecationReason();

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[133]++;}

      return "";

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[131]++;}
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[176]++;
    ObjectType implicitProto = type.getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[177]++;
int CodeCoverConditionCoverageHelper_C68;
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((implicitProto != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[134]++;
      return getPropertyDeprecationInfo(implicitProto, prop);

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[135]++;}
    return null;
  }

  /**
   * Dereference a type, autoboxing it and filtering out null.
   */
  private static JSType dereference(JSType type) {
    return type == null ? null : type.dereference();
  }

  /**
   * Returns the super class of the given type that has a constructor.
   */
  private JSType getFinalParentClass(JSType type) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[178]++;
int CodeCoverConditionCoverageHelper_C69;
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[136]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[179]++;
      ObjectType iproto = ObjectType.cast(type).getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[180]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[10]++;


int CodeCoverConditionCoverageHelper_C70;
      while ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((iproto != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((iproto.getConstructor() == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[10]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[11]--;
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.loops[12]++;
}
        iproto = iproto.getImplicitPrototype();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[181]++;
      }
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[182]++;
int CodeCoverConditionCoverageHelper_C71;
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((iproto != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[138]++;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[183]++;
        Node source = iproto.getConstructor().getSource();
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[184]++;
        JSDocInfo jsDoc = source != null ? NodeUtil.getBestJSDocInfo(source) : null;
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.statements[185]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((jsDoc != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((jsDoc.isConstant()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[140]++;
          return iproto;

        } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[141]++;}

      } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[139]++;}

    } else {
  CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9.branches[137]++;}
    return null;
  }
}

class CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9 ());
  }
    public static long[] statements = new long[186];
    public static long[] branches = new long[142];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckAccessControls.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,2,1,3,3,1,1,1,2,1,1,1,1,3,2,2,1,3,1,1,1,1,1,1,1,2,1,1,1,2,1,1,2,2,1,3,2,1,2,1,3,3,1,1,1,2,1,1,1,2,2,2,1,1,1,2,1,2};
    for (int i = 1; i <= 72; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$fjanrymgbym8gkcw23kb3r4w6lmmg3rt397q9 () {
    super("com.google.javascript.jscomp.CheckAccessControls.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 185; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 141; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckAccessControls.java");
      for (int i = 1; i <= 185; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 141; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

