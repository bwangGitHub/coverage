/*
 * Copyright 2010 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.jsonml;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.AbstractCompiler;
import com.google.javascript.jscomp.DiagnosticType;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Traverse JsonML source tree and generates AST.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class Reader {
  static {
    CodeCoverCoverageCounter$cikqwp813n22kkorl.ping();
  }


  static final DiagnosticType JSONML_SYNTAX = DiagnosticType.error(
      "JSONML_SYNTAX", "Syntax error: {0}");
  static {
    CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[1]++;
  }

  /** Root element of JsonML tree which contains JavaScript source. */
  private JsonML rootElement;

  /** Name of JavaScript source file */
  private String sourceName;

  /** Error reporter */
  private ErrorReporter errorReporter;

  /** List of ES5 directives supported by JsonML */
  private final Set<String> ALLOWED_DIRECTIVES = Sets.newHashSet("use strict");
  {
    CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[2]++;
  }

  /** Number of node in JsonML order which is currently processed */
  private int nodeIndex;

  /**
   * Inner class which is responsible for passing reader errors
   * to the JS compiler.
   */
  private class ErrorReporter {
    private AbstractCompiler compiler;

    ErrorReporter(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[3]++;
    }

    private void report(JsonML element, String...arguments)
        throws JsonMLException {
      report(JSONML_SYNTAX, element, arguments);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[4]++;
    }

    private void report(DiagnosticType type, JsonML element,
        String... arguments) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[5]++;
      // nodeIndex is the number of the node in which the error occurred
      // we will store it in line number
      int lineno = nodeIndex;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[6]++;
      int charno = -1;

      report(JSError.make(sourceName, lineno, charno, type, arguments));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[7]++;
    }

    /**
     * Reports a new parser error to the compiler and terminates the job.
     * @param error JSError instance to be passed to the compiler
     */
    private void report(JSError error) throws JsonMLException {
      report(error, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[8]++;
    }

    /**
     * Reports a new parser error to the compiler and terminates the job
     * if the error is fatal.
     * @param error JSError instance to be passed to the compiler
     * @param terminal if true, parsing is terminated by throwing exception
     */
    private void report(JSError error, boolean terminal)
        throws JsonMLException {
      compiler.report(error);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[9]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((terminal) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[1]++;
        throw new JsonMLException();

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[2]++;}
    }
  }

  // TODO(dhans): Maybe this state can be replaced with a simpler check
  /**
   * Stores state if EXPR_RESULT node should be inserted. The reason why
   * we have to keep track on that is JsonML representation does not have this
   * information.
   */
  private boolean insertExprResultState = true;
  {
    CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[11]++;
  }

  public void setRootElement(JsonML rootElement) {
    this.rootElement = rootElement;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[12]++;
  }

  /**
   * Generates AST for a specified JsonML source file.
   * @return root node of the generated AST
   * @throws JsonMLException if an error occurs
   */
  public Node parse(AbstractCompiler compiler) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((compiler == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[3]++;
      // TODO(dhans): Review error handling
      // maybe throw an exception that compiler is required for errors.
      return null;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[4]++;}

    errorReporter = this.new ErrorReporter(compiler);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[14]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[15]++;
    Node root = IR.block();
    nodeIndex = -1;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[16]++;

    Preconditions.checkState(rootElement.getType() == TagType.Program);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[17]++;
    transformElement(rootElement, root);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[18]++;
    return root.removeFirstChild();
  }

  /**
   * Retrieves value of an attribute, but does not throw an exception if
   * the attribute is not present for a specified JsonML element.
   * @param type desired type of the attribute
   * @return value of the attribute or null if it is not specified
   * @throws JsonMLException i.e. when the value has a wrong type
   */
  private <T> T getOptionalAttribute(JsonML element, TagAttr attr,
      Class<T> type) throws JsonMLException {
    return getAttribute(element, attr, type, true);
  }

  /**
   * Retrieves value of an attribute and throws an exception if
   * the attribute is not present for a specified JsonML element.
   * @param type desired type of the attribute
   * @return value of the attribute
   * @throws JsonMLException i.e. when the attribute does not exist
   */
  private <T> T getAttribute(JsonML element, TagAttr attr, Class<T> type)
      throws JsonMLException {
    return getAttribute(element, attr, type, false);
  }

  private <T> T getAttribute(JsonML element, TagAttr attr, Class<T> type,
      boolean optional) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[19]++;
    Object value = element.getAttribute(attr);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[5]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((optional) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[7]++;
        return null;

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[8]++;}

      throw new JsonMLException(
          "Missing " + attr.name() + " attribute for "
          + element.getType().name() + " element.");

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[6]++;}
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;

    // Double type is a special case, as it might be represented by all
    // Number types or even by certain strings which contain only digit chars
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type.equals(Double.class)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[9]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[11]++;
        return type.cast(((Number) value).doubleValue());

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[12]++;}
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[24]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((value instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[13]++;
        return type.cast(Double.valueOf((String) value));

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[14]++;}

      throw new JsonMLException(
          "Wrong type of " + attr.name() + " attribute. "
          + "Received: " + value.getClass() + ". Expected: " + type.getName());

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[10]++;}
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type.isInstance(value)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[15]++;
      return type.cast(value);

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[16]++;}

    throw new JsonMLException(
        "Wrong type of " + attr.name() + "attribute. "
        + "Received: " + value.getClass() + ". Expected: " + type.getName());
  }

  /**
   * Retrieves an attribute whose type should be Object.
   */
  private Object getObjectAttribute(JsonML element, TagAttr attr)
      throws JsonMLException {
    return getAttribute(element, attr, Object.class);
  }

  /**
   * Retrieves an attribute whose type should be String.
   */
  private String getStringAttribute(JsonML element, TagAttr attr)
      throws JsonMLException {
    return getAttribute(element, attr, String.class);
  }

  private void validate(JsonML element) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[26]++;
    String errorMessage = Validator.validate(element);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((errorMessage != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[17]++;
      errorReporter.report(element, errorMessage);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[28]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[18]++;}
  }

  /**
   * Recursively transforms JsonML tree into AST.
   *
   * @param element JsonML element to transform
   * @param parent current parent AST node, i.e. when the element is
   * transformed
   * to a new AST node, it should be added as a last child to the parent Node.
   */
  private void transformElement(JsonML element, Node parent)
      throws JsonMLException {
    // next element is transformed
    nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[29]++;

    // the element has to be validated
    validate(element);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[30]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;

    // determine if EXPR_RESULT should be inserted
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((insertExprResultState) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((JsonMLUtil.isExpression(element)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[19]++;
      transformExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[32]++;
      return;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[20]++;}
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[33]++;

    switch (element.getType()) {
      case ArrayExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[21]++;
        transformArrayExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[34]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[35]++;
        break;
      case AssignExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[22]++;
        transformAssignExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[36]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[37]++;
        break;
      case BinaryExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[23]++;
        transformBinaryExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[38]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[39]++;
        break;
      case BlockStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[24]++;
        transformBlock(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[40]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[41]++;
        break;
      case BreakStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[25]++;
        transformBreakStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[42]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[43]++;
        break;
      case CallExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[26]++;
        transformCallExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[44]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[45]++;
        break;
      case Case:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[27]++;
        transformCase(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[46]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[47]++;
        break;
      case CatchClause:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[28]++;
        transformCatchClause(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[48]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[49]++;
        break;
      case ConditionalExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[29]++;
        transformConditionalExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[50]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[51]++;
        break;
      case ContinueStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[30]++;
        transformContinueStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[52]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[53]++;
        break;
      case CountExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[31]++;
        transformCountExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[54]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[55]++;
        break;
      case DataProp:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[32]++;
        transformDataProp(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[56]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[57]++;
        break;
      case GetterProp:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[33]++;
        transformGetterProp(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[58]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[59]++;
        break;
      case SetterProp:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[34]++;
        transformSetterProp(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[60]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[61]++;
        break;
      case DefaultCase:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[35]++;
        transformDefaultCase(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[62]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[63]++;
        break;
      case DeleteExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[36]++;
        transformDeleteExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[64]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[65]++;
        break;
      case DoWhileStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[37]++;
        transformDoWhileStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[66]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[67]++;
        break;
      case Empty:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[38]++;
        transformEmpty(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[68]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[69]++;
        break;
      case EmptyStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[39]++;
        transformEmptyStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[70]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[71]++;
        break;
      case EvalExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[40]++;
        transformEvalExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[72]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[73]++;
        break;
      case ForInStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[41]++;
        transformForInStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[74]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[75]++;
        break;
      case ForStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[42]++;
        transformForStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[76]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[77]++;
        break;
      case FunctionDecl:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[43]++;
        transformFunctionDecl(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[78]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[79]++;
        break;
      case FunctionExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[44]++;
        transformFunctionExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[80]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[81]++;
        break;
      case IdExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[45]++;
        transformIdExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[82]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[83]++;
        break;
      case IdPatt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[46]++;
        transformIdPatt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[84]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[85]++;
        break;
      case IfStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[47]++;
        transformIfStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[86]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[87]++;
        break;
      case InitPatt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[48]++;
        transformInitPatt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[88]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[89]++;
        break;
      case InvokeExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[49]++;
        transformInvokeExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[90]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[91]++;
        break;
      case LabelledStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[50]++;
        transformLabelledStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[92]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[93]++;
        break;
      case LiteralExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[51]++;
        transformLiteralExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[94]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[95]++;
        break;
      case LogicalAndExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[52]++;
        transformLogicalAndExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[96]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[97]++;
        break;
      case LogicalOrExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[53]++;
        transformLogicalOrExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[98]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[99]++;
        break;
      case MemberExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[54]++;
        transformMemberExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[100]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[101]++;
        break;
      case NewExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[55]++;
        transformNewExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[102]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[103]++;
        break;
      case ObjectExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[56]++;
        transformObjectExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[104]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[105]++;
        break;
      case ParamDecl:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[57]++;
        transformParamDecl(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[106]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[107]++;
        break;
      case Program:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[58]++;
        transformProgram(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[108]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[109]++;
        break;
      case PrologueDecl:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[59]++;
        transformPrologueDecl(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[110]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[111]++;
        break;
      case RegExpExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[60]++;
        transformRegExpExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[112]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[113]++;
        break;
      case ReturnStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[61]++;
        transformReturnStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[114]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[115]++;
        break;
      case SwitchStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[62]++;
        transformSwitchStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[116]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[117]++;
        break;
      case ThisExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[63]++;
        transformThisExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[118]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[119]++;
        break;
      case ThrowStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[64]++;
        transformThrowStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[120]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[121]++;
        break;
      case TryStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[65]++;
        transformTryStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[122]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[123]++;
        break;
      case TypeofExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[66]++;
        transformTypeofExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[124]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[125]++;
        break;
      case UnaryExpr:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[67]++;
        transformUnaryExpr(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[126]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[127]++;
        break;
      case VarDecl:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[68]++;
        transformVarDecl(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[128]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[129]++;
        break;
      case WhileStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[69]++;
        transformWhileStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[130]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[131]++;
        break;
      case WithStmt:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[70]++;
        transformWithStmt(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[132]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[133]++;
        break; default : CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[71]++;
    }
  }

  /*
   * Helper functions.
   * Usually called by functions which process particular JsonML elements.
   */

  private void transformAllChildren(JsonML element, Node parent,
      boolean newState) throws JsonMLException {
    transformElements(element.getChildren(), parent, newState);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[134]++;
  }

  private void transformAllChildren(JsonML element, Node parent)
      throws JsonMLException {
    transformElements(element.getChildren(), parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[135]++;
  }

  private void transformAllChildrenFromIndex(JsonML element, Node parent,
      int fromIndex, boolean newState) throws JsonMLException {
    transformElements(element.getChildren().subList(
        fromIndex, element.childrenSize()), parent, newState);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[136]++;
  }

  private void transformElements(List<JsonML> elements, Node parent,
      boolean newState) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[137]++;
    boolean oldState = insertExprResultState;
    insertExprResultState = newState;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[138]++;
    transformElements(elements, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[139]++;
    insertExprResultState = oldState;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[140]++;
  }

  private void transformElements(List<JsonML> elements, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[141]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[1]++;


    for (JsonML element : elements) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[1]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[2]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[3]++;
}
      transformElement(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[142]++;
    }
  }

  /**
   * Responsible for inserting EXPR_RESULT nodes.
   */
  private boolean transformExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[143]++;
    boolean result = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[144]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((insertExprResultState) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[72]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[145]++;
      Node node = new Node(Token.EXPR_RESULT);
      parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[146]++;
      insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[147]++;
      nodeIndex--;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[148]++; // the same node will be transformed again
      transformElement(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[149]++;
      insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[150]++;
      result = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[151]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[73]++;}
    return result;
  }

  /**
   * Generic function responsible for dealing with JsonML elements describing
   * for loop (ForStmt and ForInStmt).
   */
  private void transformForLoop(JsonML element, Node parent, int childno)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[152]++;
    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[153]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[154]++;

    Node node = createNode(Token.FOR, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[155]++;

    JsonML child;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[156]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < childno) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[4]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[5]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[6]++;
}
      child = element.getChild(i);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[157]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[158]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((child.getType() == TagType.EmptyStmt) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((child.getType() == TagType.Empty) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[74]++;
        nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[159]++;
        node.addChildToBack(IR.empty());
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[160]++;

      } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[75]++;
        transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[161]++;
      }
    }

    transformPotentiallyUnwrappedBlock(element.getChild(childno), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[162]++;
    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[163]++;
  }

  /**
   * Generic function responsible for dealing with the following JsonML
   * elements: BreakStmt and ContinueStmt.
   */
  private void transformJumpStmt(JsonML element, Node parent, int type)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[164]++;
    Node node = createNode(type, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[165]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[166]++;

    String label = getOptionalAttribute(element, TagAttr.LABEL, String.class);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[167]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[76]++;
      node.addChildToBack(IR.labelName(label));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[168]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[77]++;}
  }

  /**
   * Generic function responsible for dealing with JsonML elements describing
   * logical two arguments expressions: LogicalAndExpr and LogicalOrExpr.
   */
  private void transformLogicalExpr(JsonML element, Node parent, int type)
      throws JsonMLException {
    transformTwoArgumentExpr(element, parent, type);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[169]++;
  }

  /**
   * Generic function responsible for dealing with all kind of expressions
   * which are passed exactly two arguments.
   */
  private void transformTwoArgumentExpr(JsonML element, Node parent,
      int type) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[170]++;
    Node node = createNode(type, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[171]++;
    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[172]++;
  }

  /**
   * Transforms an element which should be transformed into a BLOCK node, but
   * may not be represented by BlockStmt. In this case, additional BLOCK node
   * is created.
   */
  private void transformPotentiallyUnwrappedBlock(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[173]++;
int CodeCoverConditionCoverageHelper_C15;

    // in theory it should be always EmptyStmt, but due to possible
    // compatibility issues Empty element is allowed as well
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((element.getType() == TagType.EmptyStmt) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((element.getType() == TagType.Empty) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[78]++;
      nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[174]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[175]++;
      // Empty elements are only replaced by BLOCK node
      Node block = IR.block();
      parent.addChildToBack(block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[176]++;
      block.putBooleanProp(Node.EMPTY_BLOCK, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[177]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[79]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[178]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((element.getType() != TagType.BlockStmt) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[80]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[179]++;
      Node block = IR.block();
      parent.addChildToBack(block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[180]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[181]++;
      boolean state = insertExprResultState;
      insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[182]++;
      transformElement(element, block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[183]++;
      insertExprResultState = state;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[184]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[81]++;
      nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[185]++;
      transformBlock(element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[186]++;
    }
}
  }

  /*
   * Main functions.
   * Functions responsible for handling particular JsonML elements. Depending
   * on type, transformElement function dispatches actual work to
   * the corresponding function below.
   */

  private void transformArrayExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[187]++;

    Node node = createNode(Token.ARRAYLIT, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[188]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[189]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[7]++;



    // iterate through all the children and look for empty elements
    for (JsonML child : element.getChildren()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[7]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[8]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[9]++;
}
      transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[190]++;
    }
  }

  private void transformAssignExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[191]++;
    String op = getStringAttribute(element, TagAttr.OP);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[192]++;
    int type = Operator.getNodeTypeForAssignOp(op);
    transformTwoArgumentExpr(element, parent, type);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[193]++;
  }

  private void transformBinaryExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[194]++;
    String op = getStringAttribute(element, TagAttr.OP);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[195]++;
    int type = Operator.getNodeTypeForBinaryOp(op);
    transformTwoArgumentExpr(element, parent, type);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[196]++;
  }

  private void transformBlock(JsonML element, Node parent)
      throws JsonMLException {
    transformBlock(element, parent, 0, element.childrenSize());
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[197]++;
  }

  private void transformBlock(JsonML element, Node parent, int start)
      throws JsonMLException {
    transformBlock(element, parent, start, element.childrenSize());
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[198]++;
  }

  private void transformBlock(JsonML element, Node parent, int start, int end)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[199]++;
    Node node = createNode(Token.BLOCK, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[200]++;
    transformElements(element.getChildren(start, end), node, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[201]++;
  }

  private void transformBreakStmt(JsonML element, Node parent)
      throws JsonMLException {
    transformJumpStmt(element, parent, Token.BREAK);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[202]++;
  }

  private void transformCallExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[203]++;

    Node node = createNode(Token.CALL, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[204]++;

    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[205]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[206]++;

    // Keep track of of the "this" context of a call.  A call without an
    // explicit "this" is a free call.
    Node first = node.getFirstChild();
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[207]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((first.getType() != Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((first.getType() != Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[82]++;
      node.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[208]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[83]++;}
  }

  private void transformCase(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[209]++;

    Node node = createNode(Token.CASE, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[210]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[211]++;

    // the first element represents case id
    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[212]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[213]++;

    // always insert an extra BLOCK node
    Node block = IR.block();
    block.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[214]++;
    node.addChildToBack(block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[215]++;

    transformAllChildrenFromIndex(element, block, 1, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[216]++;
  }

  private void transformCatchClause(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[217]++;

    Node node = createNode(Token.CATCH, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[218]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[219]++;

    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[220]++;

    // the second child represents actual block
    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[221]++;
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[222]++;
  }

  private void transformConditionalExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[223]++;
    Node node = createNode(Token.HOOK, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[224]++;

    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[225]++;
  }

  private void transformContinueStmt(JsonML element, Node parent)
      throws JsonMLException {
    transformJumpStmt(element, parent, Token.CONTINUE);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[226]++;
  }

  /*
   * CountExpr are both incrementing and decrementing expressions (++x, --x)
   */
  private void transformCountExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[227]++;
    String op = getStringAttribute(element, TagAttr.OP);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[228]++;

    int type = Operator.getNodeTypeForCountOp(op);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[229]++;

    Boolean isPrefix = getAttribute(element, TagAttr.IS_PREFIX, Boolean.class);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[230]++;
    Node node = createNode(type, element);
    node.putIntProp(Node.INCRDECR_PROP, isPrefix ? 0 : 1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[231]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[232]++;

    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[233]++;
  }

  /*
   * DataProp is the name for an object property which is initialized
   * when the object is created by object literal.
   * For example, in {x: 1, y: 2} each property is represented by its own
   * DataProp.
   */
  private void transformDataProp(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[234]++;
    Object name = getObjectAttribute(element, TagAttr.NAME);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[235]++;

    Node node = null;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[236]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[84]++;
      node = IR.stringKey(getStringValue(((Number) name).doubleValue()));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[237]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[85]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[238]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((name instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[86]++;
      node = IR.stringKey((String) name);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[239]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[87]++;
      throw new IllegalStateException(
          "The name of the property has invalid type.");
    }
}

    setPosition(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[240]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[241]++;

    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[242]++;
  }

  private static String getStringValue(double value) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[243]++;
    long longValue = (long) value;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[244]++;
int CodeCoverConditionCoverageHelper_C20;

    // Return "1" instead of "1.0"
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((longValue == value) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[88]++;
      return Long.toString(longValue);

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[89]++;
      return Double.toString(value);
    }
  }

  /*
   * GetterProp is a object literal entry for a getter.
   * For example, {get x() {return 1}}
   */
  private void transformGetterProp(JsonML element, Node parent)
      throws JsonMLException {
    transformProp(Token.GETTER_DEF, element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[245]++;
  }

  /*
   * GetterProp is a object literal entry for a getter.
   * For example, {set x() {return 1}}
   */
  private void transformSetterProp(JsonML element, Node parent)
      throws JsonMLException {
    transformProp(Token.SETTER_DEF, element, parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[246]++;
  }

  private void transformProp(int tokenType, JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[247]++;
    Object name = getObjectAttribute(element, TagAttr.NAME);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[248]++;

    Node node = null;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[249]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((name instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[90]++;
      // TODO(johnlenz): convert the number to a quoted string.
      throw new IllegalStateException(
         "Not yet supported.");

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[91]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[250]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((name instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[92]++;
      node = Node.newString(tokenType, (String) name);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[251]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[93]++;
      throw new IllegalStateException(
          "The name of the property has invalid type.");
    }
}

    setPosition(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[252]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[253]++;

    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[254]++;
  }


  private void transformDefaultCase(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[255]++;
    Node node = createNode(Token.DEFAULT_CASE, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[256]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[257]++;

    // the first child represent body
    Node block = IR.block();
    block.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[258]++;
    node.addChildToBack(block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[259]++;

    transformAllChildren(element, block, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[260]++;
  }

  private void transformDeleteExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[261]++;

    Node node = createNode(Token.DELPROP, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[262]++;

    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[263]++;
  }

  private void transformDoWhileStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[264]++;
    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[265]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[266]++;

    Node node = createNode(Token.DO, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[267]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[268]++;

    // the first child represents body
    JsonML child = element.getChild(0);
    transformPotentiallyUnwrappedBlock(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[269]++;

    // the second child represents condition
    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[270]++;
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[271]++;

    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[272]++;
  }

  private void transformEmpty(JsonML element, Node parent) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[273]++;
    switch (parent.getType()) {
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[94]++;
        parent.addChildToBack(IR.empty());
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[274]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[275]++;
        break;
      case Token.FUNCTION:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[95]++;
        parent.addChildToBack(IR.name(""));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[276]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[277]++;
        break;
      default:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[96]++;
        throw new IllegalArgumentException("Unexpected Empty element.");
    }
  }

  private void transformEmptyStmt(JsonML element, Node parent) {
    Preconditions.checkState(
        parent.getType() == Token.BLOCK || parent.getType() == Token.SCRIPT);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[278]++;
    parent.addChildToBack(IR.empty());
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[279]++;
  }

  private void transformEvalExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[280]++;

    Node node = createNode(Token.CALL, element);
    node.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[281]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[282]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[283]++;

    Node child = IR.name("eval");
    child.putBooleanProp(Node.DIRECT_EVAL, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[284]++;
    node.addChildToBack(child);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[285]++;

    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[286]++;
  }

  private void transformForInStmt(JsonML element, Node parent)
      throws JsonMLException {
    transformForLoop(element, parent, 2);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[287]++;
  }

  private void transformForStmt(JsonML element, Node parent)
      throws JsonMLException {
    transformForLoop(element, parent, 3);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[288]++;
  }

  private void transformFunction(JsonML element, Node parent,
      boolean needsName) throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[289]++;
    Node node = createNode(Token.FUNCTION, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[290]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[291]++;

    JsonML child = element.getChild(0);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[292]++;
    String name = "";

    // it be already validated at this point that a non empty name exists
    // if it is a function declaration
    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[293]++;

    transformElement(element.getChild(1), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[294]++;

    // other children represents function body which should be
    // wrapped inside a block node
    transformBlock(element, node, 2);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[295]++;
  }

  private void transformFunctionDecl(JsonML element, Node parent)
      throws JsonMLException {
    transformFunction(element, parent, true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[296]++;
  }

  private void transformFunctionExpr(JsonML element, Node parent)
      throws JsonMLException {
    transformFunction(element, parent, false);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[297]++;
  }

  private void transformIdExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[298]++;
    String name = getStringAttribute(element, TagAttr.NAME);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[299]++;
    Node node = IR.name(name);
    setPosition(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[300]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[301]++;
  }

  /*
   * InitPatt represents all variable declarations value initialization.
   * It has two children: name of the variable and the initial value.
   */
  private void transformInitPatt(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[302]++;
    JsonML child = element.getChild(0);
    nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[303]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[304]++;
    Node node = IR.name(
        getAttribute(child, TagAttr.NAME, String.class));
    setPosition(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[305]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[306]++;

    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[307]++;
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[308]++;
  }

  private void transformIdPatt(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[309]++;
    Node node = IR.name(
        getStringAttribute(element, TagAttr.NAME));
    setPosition(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[310]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[311]++;
  }

  private void transformIfStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[312]++;
    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[313]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[314]++;

    Node node = createNode(Token.IF, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[315]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[316]++;

    // the first child represents condition
    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[317]++;

    // the second child is required
    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[318]++;
    transformPotentiallyUnwrappedBlock(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[319]++;

    // the third child represents else part and is not required by AST
    child = element.getChild(2);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[320]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[321]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((child.getType() != TagType.EmptyStmt) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((child.getType() != TagType.Empty) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[97]++;
      transformPotentiallyUnwrappedBlock(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[322]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[98]++;
      nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[323]++;
    }
    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[324]++;
  }

  private void transformInvokeExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[325]++;
    Node node = createNode(Token.CALL, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[326]++;

    transformMemberExpr(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[327]++;

    transformElements(element.getChildren(2, element.childrenSize()), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[328]++;
  }

  private void transformLabelledStmt(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[329]++;
    String label = getStringAttribute(element, TagAttr.LABEL);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[330]++;
    Node node = createNode(Token.LABEL, element);
    node.addChildToBack(IR.labelName(label));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[331]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[332]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[333]++;

    JsonML child = element.getChild(0);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[334]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((child.getType() == TagType.EmptyStmt) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[99]++;
      nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[335]++;
      node.addChildToBack(IR.empty());
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[336]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[100]++;
      transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[337]++;
    }
  }

  private void transformLiteralExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[338]++;

    Node node = null;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[339]++;
    Type type = Type.get(getStringAttribute(element, TagAttr.TYPE));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[340]++;
    switch (type) {
      case BOOLEAN:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[101]++; {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[341]++;
        Boolean value = getAttribute(element, TagAttr.VALUE, Boolean.class);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[342]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((value) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[102]++;
          node = IR.trueNode();
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[343]++;

        } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[103]++;
          node = IR.falseNode();
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[344]++;
        }
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[345]++;
        break;
      }

      case NULL:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[104]++; {
        // needed to throw an exception if value is not null
        getAttribute(element, TagAttr.VALUE, null);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[346]++;
        node = IR.nullNode();
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[347]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[348]++;
        break;
      }

      case NUMBER:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[105]++; {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[349]++;
        Double value = getAttribute(element, TagAttr.VALUE, Double.class);
        node = IR.number(value);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[350]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[351]++;
        break;
      }

      case STRING:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[106]++; {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[352]++;
        String value = getStringAttribute(element, TagAttr.VALUE);
        node = IR.string(value);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[353]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[354]++;
        break;
      }

      default:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[107]++;
        throw new JsonMLException("Unrecognized type attribute.");
    }

    setPosition(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[355]++;
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[356]++;
  }

  private void transformLogicalAndExpr(JsonML element, Node parent)
      throws JsonMLException {
    transformLogicalExpr(element, parent, Token.AND);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[357]++;
  }

  private void transformLogicalOrExpr(JsonML element, Node parent)
      throws JsonMLException {
    transformLogicalExpr(element, parent, Token.OR);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[358]++;
  }

  private void transformMemberExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[359]++;

    String op = getAttribute(element, TagAttr.OP, String.class);
    int type;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[360]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((op.equals(".")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[108]++;
      type = Token.GETPROP;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[361]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[109]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[362]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((op.equals("[]")) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[110]++;
      type = Token.GETELEM;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[363]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[111]++;
      throw new JsonMLException("Invalid OP argument: " + op);
    }
}
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[364]++;

    Node node = createNode(type, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[365]++;

    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[366]++;
    transformElement(element.getChild(1), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[367]++;
  }

  private void transformNewExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[368]++;
    Node node = createNode(Token.NEW, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[369]++;
    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[370]++;
  }

  private void transformObjectExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[371]++;
    Node node = createNode(Token.OBJECTLIT, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[372]++;

    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[373]++;
  }

  private void transformParamDecl(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[374]++;
    // formal arguments should be wrapped by LP node
    Node node = createNode(Token.PARAM_LIST, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[375]++;

    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[376]++;
  }

  private void transformProgram(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkNotNull(parent);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[377]++;
    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[378]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[379]++;

    Node script = IR.script();
    parent.addChildToBack(script);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[380]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[381]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[10]++;



    for (JsonML child : element.getChildren()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[10]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[11]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[12]++;
}
      transformElement(child, script);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[382]++;
    }
  }

  private void transformPrologueDecl(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[383]++;
    String directive = getStringAttribute(element, TagAttr.DIRECTIVE);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[384]++;
int CodeCoverConditionCoverageHelper_C28;

    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((ALLOWED_DIRECTIVES.contains(directive)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[112]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[385]++;
      Set<String> directives = parent.getDirectives();
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[386]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((directives == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[114]++;
        directives = Sets.newHashSet();
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[387]++;

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[115]++;}
      directives.add(directive);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[388]++;
      parent.setDirectives(directives);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[389]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[113]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[390]++;
      // for a directive which is not supported, we create a regular node
      Node node = IR.exprResult(IR.string(directive));
      parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[391]++;
    }
  }

  private void transformRegExpExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[392]++;
    Node node = createNode(Token.REGEXP, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[393]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[394]++;

    String body = getStringAttribute(element, TagAttr.BODY);
    node.addChildToBack(IR.string(body));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[395]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[396]++;

    String flags = getStringAttribute(element, TagAttr.FLAGS);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[397]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((flags.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[116]++;
      node.addChildToBack(IR.string(flags));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[398]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[117]++;}
  }

  private void transformReturnStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[399]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[400]++;
    Node node = createNode(Token.RETURN, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[401]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[402]++;
int CodeCoverConditionCoverageHelper_C31;

    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((element.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[118]++;
      insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[403]++;
      transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[404]++;
      insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[405]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[119]++;}
  }

  private void transformSwitchStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[406]++;
    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[407]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[408]++;

    Node node = createNode(Token.SWITCH, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[409]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[410]++;

    // make sure it has at least one child
    // the first child represents switch param
    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[411]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[412]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[13]++;


int CodeCoverConditionCoverageHelper_C32;

    // the rest of the children represent cases
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < element.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[13]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[14]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[15]++;
}
      child = element.getChild(i);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[413]++;
      // make sure it is case or default
      transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[414]++;
    }

    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[415]++;
  }

  /**
   * @throws JsonMLException
   */
  private void transformThisExpr(JsonML element, Node parent)
      throws JsonMLException {
    parent.addChildToBack(createNode(Token.THIS, element));
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[416]++;
  }

  private void transformThrowStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[417]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[418]++;
    Node node = createNode(Token.THROW, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[419]++;

    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[420]++;
    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[421]++;
    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[422]++;
  }

  private void transformTryStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[423]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[424]++;
    Node node = createNode(Token.TRY, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[425]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[426]++;

    // the first child represents try body
    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[427]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[428]++;

    // the second child represents catch
    Node block = IR.block();
    node.addChildToBack(block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[429]++;
    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[430]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[431]++;
int CodeCoverConditionCoverageHelper_C33;

    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((child.getType() == TagType.CatchClause) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[120]++;
      transformElement(child, block);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[432]++;

    } else {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[121]++;
      // catch clause is not present, but the element has to be counted
      nodeIndex++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[433]++;
    }
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[434]++;
int CodeCoverConditionCoverageHelper_C34;

    // if the third child is present, it represents finally
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((element.childrenSize() == 3) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[122]++;
      child = element.getChild(2);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[435]++;
      transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[436]++;

    } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[123]++;}
  }

  private void transformTypeofExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[437]++;
    Node node = createNode(Token.TYPEOF, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[438]++;
    transformElement(element.getChild(0), node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[439]++;
  }

  private void transformUnaryExpr(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[440]++;
    String op = getStringAttribute(element, TagAttr.OP);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[441]++;
    int type = Operator.getNodeTypeForUnaryOp(op);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[442]++;

    Node node = createNode(type, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[443]++;

    transformAllChildren(element, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[444]++;
  }

  private void transformVarDecl(JsonML element, Node parent)
      throws JsonMLException {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[445]++;
    Node node = createNode(Token.VAR, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[446]++;

    transformAllChildren(element, node, false);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[447]++;
  }

  private void transformWhileStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[448]++;
    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[449]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[450]++;

    Node node = createNode(Token.WHILE, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[451]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[452]++;

    // the first child represents loop condition
    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[453]++;

    // the second child represents loop body
    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[454]++;
    transformPotentiallyUnwrappedBlock(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[455]++;

    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[456]++;
  }

  private void transformWithStmt(JsonML element, Node parent)
      throws JsonMLException {
    Preconditions.checkState(insertExprResultState == true);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[457]++;
    insertExprResultState = false;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[458]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[459]++;

    Node node = createNode(Token.WITH, element);
    parent.addChildToBack(node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[460]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[461]++;

    // the first child represents object
    JsonML child = element.getChild(0);
    transformElement(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[462]++;

    // the second child represents body
    child = element.getChild(1);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[463]++;
    transformPotentiallyUnwrappedBlock(child, node);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[464]++;

    insertExprResultState = true;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[465]++;
  }

  /**
   * Creates a node which refers to a particular JsonML element.
   */
  private Node createNode(int type, JsonML element) {
    return new Node(type, nodeIndex, -1);
  }

  /**
   * Sets position for a node which refers to a particular JsonML element.
   * The position says which number (in pre-order) has the corresponding
   * JsonML element in the tree.
   */
  private void setPosition(Node node) {
    node.setLineno(nodeIndex);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[466]++;
  }

  /**
   * Internal representation for operators which are used by JsonML as
   * attributes for various elements.
   */
  private enum Operator {
    // Assign Operators
    ASSIGN("="),
    ASSIGN_BITOR("|="),
    ASSIGN_BITXOR("^="),
    ASSIGN_BITAND("&="),
    ASSIGN_LSH("<<="),
    ASSIGN_RSH(">>="),
    ASSIGN_URSH(">>>="),
    ASSIGN_ADD("+="),
    ASSIGN_SUB("-="),
    ASSIGN_MUL("*="),
    ASSIGN_DIV("/="),
    ASSIGN_MOD("%="),

    // Binary Operators
    BITOR("|"),
    BITXOR("^"),
    BITAND("&"),
    EQ("=="),
    NE("!="),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    LSH("<<"),
    RSH(">>"),
    URSH(">>>"),
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    MOD("%"),
    SHEQ("==="),
    SHNE("!=="),
    COMMA(","),
    INSTANCEOF("instanceof"),
    IN("in"),

    // Count Operators
    DEC("--"),
    INC("++"),

    // Unary Operators
    NOT("!"),
    BITNOT("~"),
    POS("+_unary"), // "+" would be a duplicate with ADD
    NEG("-_unary"), // "-" would be a duplicate with SUB
    VOID("void");

    private final String name;
    private static Map<String, Operator> lookup = Maps.newHashMap();

    // Maps string representation of operators with corresponding enums
    static {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[467]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[16]++;


      for (Operator op : Operator.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[16]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[17]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[18]++;
}
        lookup.put(op.getName(), op);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[468]++;
      }
    }

    private String getName() {
      return this.name;
    }

    private Operator(String name) {
      this.name = name;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[469]++;
    }

    private static Operator get(String name) {
      return lookup.get(name);
    }

    /**
     * Returns assign operator associated with a specified name.
     */
    private static int getNodeTypeForAssignOp(String name) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[470]++;
      Operator op = get(name);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[471]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((op == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[124]++;
        return Token.ERROR;

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[125]++;}

      int type;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[472]++;
      switch (op) {
        case ASSIGN:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[126]++;
          type = Token.ASSIGN;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[473]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[474]++;
          break;
        case ASSIGN_BITOR:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[127]++;
          type = Token.ASSIGN_BITOR;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[475]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[476]++;
          break;
        case ASSIGN_BITXOR:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[128]++;
          type = Token.ASSIGN_BITXOR;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[477]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[478]++;
          break;
        case ASSIGN_BITAND:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[129]++;
          type = Token.ASSIGN_BITAND;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[479]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[480]++;
          break;
        case ASSIGN_LSH:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[130]++;
          type = Token.ASSIGN_LSH;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[481]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[482]++;
          break;
        case ASSIGN_RSH:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[131]++;
          type = Token.ASSIGN_RSH;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[483]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[484]++;
          break;
        case ASSIGN_URSH:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[132]++;
          type = Token.ASSIGN_URSH;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[485]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[486]++;
          break;
        case ASSIGN_ADD:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[133]++;
          type = Token.ASSIGN_ADD;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[487]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[488]++;
          break;
        case ASSIGN_SUB:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[134]++;
          type = Token.ASSIGN_SUB;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[489]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[490]++;
          break;
        case ASSIGN_MUL:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[135]++;
          type = Token.ASSIGN_MUL;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[491]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[492]++;
          break;
        case ASSIGN_DIV:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[136]++;
          type = Token.ASSIGN_DIV;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[493]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[494]++;
          break;
        case ASSIGN_MOD:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[137]++;
          type = Token.ASSIGN_MOD;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[495]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[496]++;
          break;
        default:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[138]++;
          throw new IllegalArgumentException(""
              + "Invalid type of assign expression.");
      }
      return type;
    }

    /**
     * Returns binary operator associated with a specified name.
     */
    private static int getNodeTypeForBinaryOp(String name) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[497]++;
      Operator op = get(name);

      int type;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[498]++;
      switch (op) {
        case BITOR:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[139]++;
          type = Token.BITOR;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[499]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[500]++;
          break;
        case BITXOR:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[140]++;
          type = Token.BITXOR;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[501]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[502]++;
          break;
        case BITAND:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[141]++;
          type = Token.BITAND;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[503]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[504]++;
          break;
        case EQ:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[142]++;
          type = Token.EQ;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[505]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[506]++;
          break;
        case NE:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[143]++;
          type = Token.NE;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[507]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[508]++;
          break;
        case LT:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[144]++;
          type = Token.LT;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[509]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[510]++;
          break;
        case LE:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[145]++;
          type = Token.LE;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[511]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[512]++;
          break;
        case GT:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[146]++;
          type = Token.GT;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[513]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[514]++;
          break;
        case GE:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[147]++;
          type = Token.GE;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[515]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[516]++;
          break;
        case LSH:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[148]++;
          type = Token.LSH;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[517]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[518]++;
          break;
        case RSH:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[149]++;
          type = Token.RSH;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[519]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[520]++;
          break;
        case URSH:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[150]++;
          type = Token.URSH;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[521]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[522]++;
          break;
        case ADD:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[151]++;
          type = Token.ADD;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[523]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[524]++;
          break;
        case SUB:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[152]++;
          type = Token.SUB;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[525]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[526]++;
          break;
        case MUL:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[153]++;
          type = Token.MUL;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[527]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[528]++;
          break;
        case DIV:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[154]++;
          type = Token.DIV;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[529]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[530]++;
          break;
        case MOD:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[155]++;
          type = Token.MOD;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[531]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[532]++;
          break;
        case SHEQ:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[156]++;
          type = Token.SHEQ;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[533]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[534]++;
          break;
        case SHNE:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[157]++;
          type = Token.SHNE;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[535]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[536]++;
          break;
        case COMMA:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[158]++;
          type = Token.COMMA;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[537]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[538]++;
          break;
        case INSTANCEOF:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[159]++;
          type = Token.INSTANCEOF;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[539]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[540]++;
          break;
        case IN:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[160]++;
          type = Token.IN;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[541]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[542]++;
          break;
        default:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[161]++;
          throw new IllegalArgumentException(""
              + "Invalid type of binary expression.");
      }
      return type;
    }

    /**
     * Returns count operator(++, --)  associated with a specified name.
     */
    private static int getNodeTypeForCountOp(String name) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[543]++;
      Operator op = get(name);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[544]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((op == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[162]++;
        return Token.ERROR;

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[163]++;}

      int type;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[545]++;
      switch (op) {
        case DEC:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[164]++;
          type = Token.DEC;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[546]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[547]++;
          break;
        case INC:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[165]++;
          type = Token.INC;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[548]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[549]++;
          break;
        default:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[166]++;
          throw new IllegalArgumentException(""
              + "Invalid type of count expression.");
      }
      return type;
    }

    /**
     * Returns assign operator associated with a specified name.
     */
    private static int getNodeTypeForUnaryOp(String name) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[550]++;
      String realName = new String(name);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[551]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((name.equals("+")) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((name.equals("-")) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$cikqwp813n22kkorl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[167]++;
        realName += "_unary";
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[552]++;

      } else {
  CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[168]++;}
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[553]++;
      Operator op = get(realName);

      int type;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[554]++;
      switch (op) {
        case NOT:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[169]++;
          type = Token.NOT;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[555]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[556]++;
          break;
        case BITNOT:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[170]++;
          type = Token.BITNOT;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[557]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[558]++;
          break;
        case POS:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[171]++;
          type = Token.POS;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[559]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[560]++;
          break;
        case NEG:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[172]++;
          type = Token.NEG;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[561]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[562]++;
          break;
        case VOID:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[173]++;
          type = Token.VOID;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[563]++;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[564]++;
          break;
        default:
CodeCoverCoverageCounter$cikqwp813n22kkorl.branches[174]++;
          throw new IllegalArgumentException(""
              + "Invalid type of unary expression.");
      }
      return type;
    }
  }

  /**
   * Internal representation of possible types of arguments of JsonML.
   */
  private enum Type {
    BOOLEAN("boolean"),
    NULL("null"),
    NUMBER("number"),
    STRING("string");

    private final String name;
    private static Map<String, Type> lookup = new HashMap<String, Type>();

    static {
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[565]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[19]++;


      for (Type type : Type.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[19]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[20]--;
  CodeCoverCoverageCounter$cikqwp813n22kkorl.loops[21]++;
}
        lookup.put(type.getName(), type);
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[566]++;
      }
    }

    private String getName() {
      return this.name;
    }

    private Type(String name) {
      this.name = name;
CodeCoverCoverageCounter$cikqwp813n22kkorl.statements[567]++;
    }

    private static Type get(String name) {
      return lookup.get(name);
    }
  }
}

class CodeCoverCoverageCounter$cikqwp813n22kkorl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$cikqwp813n22kkorl ());
  }
    public static long[] statements = new long[568];
    public static long[] branches = new long[175];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[38];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.Reader.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,2,1,1,2,1,2,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2};
    for (int i = 1; i <= 37; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$cikqwp813n22kkorl () {
    super("com.google.javascript.jscomp.jsonml.Reader.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 567; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 174; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.Reader.java");
      for (int i = 1; i <= 567; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 174; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

