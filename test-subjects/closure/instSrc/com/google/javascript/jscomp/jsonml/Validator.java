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

import java.util.Arrays;

/**
 * Statically validates JsonML elements.
 *
 * It is done in constant time: no subtree is traversed, but the element
 * is validated based only on its properties. Sometimes, also its children
 * are taken into account.
 *
 * Usually it checks if the specified element has a correct number of children,
 * and if all require attributes exist. It does not enforce all restrictions
 * which are implied by ES3 or ES5 specification.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class Validator {
  static {
    CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.ping();
  }

  public static final String MISSING_ARGUMENT = "" +
      "No %s attribute specified for %s.";
  static {
    CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[1]++;
  }
  public static final String NOT_ENOUGH_CHILDREN_FMT = "" +
      "Not enough children for %s. Expected: %d. Found: %d.";
  static {
    CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[2]++;
  }
  public static final String TOO_MANY_CHILDREN_FMT = "" +
      "Too many children for %s. Expected: %d. Found: %d.";
  static {
    CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[3]++;
  }
  public static final String WRONG_CHILD_TYPE_FMT = "" +
      "Wrong type of child number %d for %s. Expected: %s. Found: %s.";
  static {
    CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[4]++;
  }

  // used to check if a JsonML element represents an expression
  public static TagType[] exprTypes = {
      TagType.ArrayExpr, TagType.AssignExpr, TagType.BinaryExpr,
      TagType.CallExpr, TagType.ConditionalExpr, TagType.CountExpr,
      TagType.DeleteExpr, TagType.EvalExpr, TagType.IdExpr, TagType.InvokeExpr,
      TagType.LiteralExpr, TagType.LogicalAndExpr, TagType.LogicalOrExpr,
      TagType.MemberExpr, TagType.NewExpr, TagType.ObjectExpr,
      TagType.RegExpExpr, TagType.ThisExpr, TagType.TypeofExpr,
      TagType.UnaryExpr, TagType.FunctionExpr
  };
  static {
    CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[5]++;
  }

  private final StringBuilder b;
  private boolean error;

  private Validator() {
    b = new StringBuilder();
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[6]++;
    error = false;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[7]++;
  }

  /**
   * Validates the specified JsonML element.
   * @param element JsonML element to validate
   * @return error message if the element could not be
   * validated, an empty string otherwise
   */
  public static String validate(JsonML element) {
    return (new Validator()).doValidate(element);
  }

  /**
   * Validates the specified JsonML element.
   */
  private String doValidate(JsonML element) {
    String message;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[8]++;
    switch (element.getType()) {
      case AssignExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[1]++;
        validateAssignExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[9]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[10]++;
        break;
      case BinaryExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[2]++;
        validateBinaryExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[11]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[12]++;
        break;
      case BreakStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[3]++;
      case ContinueStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[4]++;
        validateJmpStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[13]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[14]++;
        break;
      case Case:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[5]++;
        validateCase(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[15]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[16]++;
        break;
      case CatchClause:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[6]++;
        validateCatchClause(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[17]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[18]++;
        break;
      case ConditionalExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[7]++;
        validateConditionalExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[19]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[20]++;
        break;
      case CountExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[8]++;
        validateCountExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[21]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[22]++;
        break;
      case DataProp:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[9]++;
        validateProp(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[23]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[24]++;
        break;
      case GetterProp:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[10]++;
        validateProp(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[25]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[26]++;
        break;
      case SetterProp:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[11]++;
        validateProp(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[27]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[28]++;
        break;
      case DeleteExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[12]++;
        validateDeleteExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[29]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[30]++;
        break;
      case DoWhileStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[13]++;
        validateDoWhileStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[31]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[32]++;
        break;
      case EmptyStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[14]++;
        validateEmptyStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[33]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[34]++;
        break;
      case ForInStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[15]++;
        validateForInStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[35]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[36]++;
        break;
      case ForStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[16]++;
        validateForStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[37]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[38]++;
        break;
      case FunctionDecl:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[17]++;
        validateFunctionDecl(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[39]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[40]++;
        break;
      case FunctionExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[18]++;
        validateFunctionExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[41]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[42]++;
        break;
      case IdExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[19]++;
        validateIdExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[43]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[44]++;
        break;
      case IdPatt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[20]++;
        validateIdPatt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[45]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[46]++;
        break;
      case IfStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[21]++;
        validateIfStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[47]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[48]++;
        break;
      case InvokeExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[22]++;
        validateInvokeExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[49]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[50]++;
        break;
      case LabelledStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[23]++;
        validateLabelledStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[51]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[52]++;
        break;
      case LiteralExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[24]++;
        validateLiteralExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[53]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[54]++;
        break;
      case LogicalAndExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[25]++;
      case LogicalOrExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[26]++;
        validateLogicalExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[55]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[56]++;
        break;
      case MemberExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[27]++;
        validateMemberExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[57]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[58]++;
        break;
      case NewExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[28]++;
        validateNewExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[59]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[60]++;
        break;
      case ObjectExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[29]++;
        validateObjectExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[61]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[62]++;
        break;
      case ParamDecl:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[30]++;
        validateParamDecl(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[63]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[64]++;
        break;
      case RegExpExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[31]++;
        validateRegExpExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[65]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[66]++;
        break;
      case ReturnStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[32]++;
        validateReturnStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[67]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[68]++;
        break;
      case SwitchStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[33]++;
        validateSwitchStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[69]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[70]++;
        break;
      case ThisExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[34]++;
        validateThisExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[71]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[72]++;
        break;
      case ThrowStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[35]++;
        validateThrowStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[73]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[74]++;
        break;
      case TryStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[36]++;
        validateTryStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[75]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[76]++;
        break;
      case TypeofExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[37]++;
        validateTypeofExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[77]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[78]++;
        break;
      case UnaryExpr:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[38]++;
        validateUnaryExpr(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[79]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[80]++;
        break;
      case VarDecl:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[39]++;
        validateVarDecl(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[81]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[82]++;
        break;
      case WhileStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[40]++;
        validateWhileStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[83]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[84]++;
        break;
      case WithStmt:
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[41]++;
        validateWithStmt(element);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[85]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[86]++;
        break; default : CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[42]++;
    }
    return b.length() != 0 ? b.toString() : null;
  }

  private void validateAssignExpr(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[87]++;
    validateArgument(element, TagAttr.OP);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[88]++;
  }

  private void validateBinaryExpr(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[89]++;
    validateArgument(element, TagAttr.OP);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[90]++;
  }

  private void validateCase(JsonML element) {
    validateMinChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[91]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[92]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[43]++;
      validateIsChildExpression(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[93]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[44]++;}
  }

  private void validateCatchClause(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[94]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[95]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[45]++;
      validateChildType(element, TagType.IdPatt , 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[96]++;
      validateChildType(element, TagType.BlockStmt, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[97]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[46]++;}
  }

  private void validateConditionalExpr(JsonML element) {
    validateChildrenSize(element, 3);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[98]++;
  }

  private void validateCountExpr(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[99]++;
    validateArgument(element, TagAttr.IS_PREFIX);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[100]++;
    validateArgument(element, TagAttr.OP);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[101]++;
  }

  private void validateProp(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[102]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[103]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[47]++;
      validateArgument(element, TagAttr.NAME);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[104]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[48]++;}
  }

  private void validateDeleteExpr(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[105]++;
    // TODO(dhans): maybe add that it has to be expression
  }

  private void validateDoWhileStmt(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[106]++;
    // TODO(dhans): maybe add that the second child has to be an exception
  }

  private void validateEmptyStmt(JsonML element) {
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[107]++;
  }

  private void validateForInStmt(JsonML element) {
    validateChildrenSize(element, 3);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[108]++;
  }

  private void validateForStmt(JsonML element) {
    validateChildrenSize(element, 4);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[109]++;
  }

  private void validateFunctionDecl(JsonML element) {
    validateFunction(element, true);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[110]++;
  }

  private void validateFunctionExpr(JsonML element) {
    validateFunction(element, false);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[111]++;
  }

  private void validateIdExpr(JsonML element) {
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[112]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[113]++;
int CodeCoverConditionCoverageHelper_C4;
    if  ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[49]++;
      validateArgument(element, TagAttr.NAME);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[114]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[50]++;}
  }

  private void validateIdPatt(JsonML element) {
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[115]++;
    validateArgument(element, TagAttr.NAME);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[116]++;
  }

  private void validateIfStmt(JsonML element) {
    validateChildrenSize(element, 3);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[117]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[118]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[51]++;

      // TODO(dhans): check the first child is condition
    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[52]++;}
  }

  private void validateInvokeExpr(JsonML element) {
    validateMinChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[119]++;
    validateArgument(element, TagAttr.OP);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[120]++;
  }

  private void validateJmpStmt(JsonML element) {
    // for both BreakStmt and ContinueStmt
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[121]++;
  }

  private void validateLabelledStmt(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[122]++;
    validateArgument(element, TagAttr.LABEL);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[123]++;
  }

  private void validateLiteralExpr(JsonML element) {
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[124]++;
    validateArgument(element, TagAttr.TYPE);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[125]++;
    validateArgument(element, TagAttr.VALUE);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[126]++;
  }

  private void validateLogicalExpr(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[127]++;
  }

  private void validateMemberExpr(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[128]++;
    validateArgument(element, TagAttr.OP);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[129]++;
  }

  private void validateNewExpr(JsonML element) {
    validateMinChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[130]++;
  }

  private void validateObjectExpr(JsonML element) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[131]++;
    TagType[] expected =
        {TagType.DataProp, TagType.GetterProp, TagType.SetterProp};
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[132]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < element.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[1]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[2]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[3]++;
}
      validateChildType(element, expected, i);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[133]++;
    }
  }

  private void validateParamDecl(JsonML element) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[134]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < element.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[4]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[5]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[6]++;
}
      validateChildType(element, TagType.IdPatt, i);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[135]++;
    }
  }

  private void validateRegExpExpr(JsonML element) {
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[136]++;
    validateArgument(element, TagAttr.BODY);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[137]++;
    validateArgument(element, TagAttr.FLAGS);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[138]++;
  }

  private void validateReturnStmt(JsonML element) {
    validateMaxChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[139]++;
  }

  private void validateSwitchStmt(JsonML element) {
    validateMinChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[140]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[141]++;
    boolean defaultStmt = false;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[142]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[7]++;


int CodeCoverConditionCoverageHelper_C8;
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i < element.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[7]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[8]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[9]++;
}
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[143]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((defaultStmt) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[53]++;
        validateChildType(element,
            new TagType[] {TagType.Case, TagType.DefaultCase}, i);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[144]++;

      } else {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[54]++;
        validateChildType(element, TagType.Case, i);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[145]++;
      }
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[146]++;
int CodeCoverConditionCoverageHelper_C10;

      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[55]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[147]++;
        break;

      } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[56]++;}
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[148]++;
int CodeCoverConditionCoverageHelper_C11;

      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((element.getChild(i).getType() == TagType.DefaultCase) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[57]++;
        defaultStmt = true;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[149]++;

      } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[58]++;}
    }
  }

  private void validateThisExpr(JsonML element) {
    validateChildrenSize(element, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[150]++;
  }

  private void validateThrowStmt(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[151]++;
  }

  private void validateTryStmt(JsonML element) {
    validateChildrenSize(element, 2, 3);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[152]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[153]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[59]++;
      return;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[60]++;}

    validateChildType(element, TagType.BlockStmt, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[154]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[155]++;

    TagType[] types = new TagType[] { TagType.CatchClause, TagType.Empty };
    validateChildType(element, types, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[156]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[157]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((element.childrenSize() > 2) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[61]++;
      validateChildType(element, TagType.BlockStmt, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[158]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[62]++;}
  }

  private void validateFunction(JsonML element, boolean needsName) {
    validateMinChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[159]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[160]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[63]++;
      return;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[64]++;}
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[161]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((needsName) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[65]++;
      validateChildType(
          element, new TagType[] { TagType.IdPatt }, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[162]++;

    } else {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[66]++;
      validateChildType(
          element, new TagType[] { TagType.IdPatt, TagType.Empty }, 0);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[163]++;
    }

    validateChildType(element, TagType.ParamDecl, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[164]++;
  }

  private void validateTypeofExpr(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[165]++;
  }

  private void validateUnaryExpr(JsonML element) {
    validateChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[166]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[167]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[67]++;
      validateArgument(element, TagAttr.OP);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[168]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[68]++;}
  }

  private void validateVarDecl(JsonML element) {
    validateMinChildrenSize(element, 1);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[169]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[170]++;

    TagType[] types = new TagType[] { TagType.InitPatt, TagType.IdPatt };
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[171]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[10]++;


int CodeCoverConditionCoverageHelper_C17;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < element.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[10]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[11]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[12]++;
}
      validateChildType(element, types, i);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[172]++;
    }
  }

  private void validateWhileStmt(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[173]++;
    //TODO(dhans): check if the first child is expression
  }

  private void validateWithStmt(JsonML element) {
    validateChildrenSize(element, 2);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[174]++;
    //TODO(dhans): check if the first child is expression
  }

  private void validateArgument(JsonML element, TagAttr attr) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[175]++;
    Object value = element.getAttribute(attr);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[176]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[69]++;

      // there is an exceptional situation when the value can be null
      // {'value': null, 'type': 'null'}
      String type;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[177]++;
      if ((type = (String) element.getAttribute(TagAttr.TYPE)) != null && type.equals("null")) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[71]++;
        return;

      } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[72]++;}

      error = true;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[178]++;
      appendLine(String.format(
          MISSING_ARGUMENT,
          attr, element.getType()));
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[179]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[70]++;}
  }

  private void validateChildrenSize(JsonML element, int expected) {
    validateChildrenSize(element, expected, expected);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[180]++;
  }

  private void validateChildrenSize(JsonML element, int min, int max) {
    validateMinChildrenSize(element, min);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[181]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[182]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((error) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[73]++;
      validateMaxChildrenSize(element, max);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[183]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[74]++;}
  }

  private void validateMinChildrenSize(JsonML element, int min) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[184]++;
    int size = element.childrenSize();
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[185]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((size < min) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[75]++;
      appendLine(String.format(
          NOT_ENOUGH_CHILDREN_FMT,
          element.getType(), min, size));
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[186]++;
      error = true;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[187]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[76]++;}
  }

  private void validateMaxChildrenSize(JsonML element, int max) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[188]++;
    int size = element.childrenSize();
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[189]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((size > max) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[77]++;
      appendLine(String.format(
          TOO_MANY_CHILDREN_FMT,
          element.getType().toString(), max, size));
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[190]++;
      error = true;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[191]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[78]++;}
  }

  private void validateIsChildExpression(JsonML element, int index) {
    validateChildType(element, exprTypes, index);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[192]++;
  }

  private void validateChildType(JsonML element, TagType expected,
      int index) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[193]++;
    TagType[] types = { expected };
    validateChildType(element, types, index);
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[194]++;
  }

  private void validateChildType(JsonML element, TagType[] expected,
      int index) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[195]++;
    TagType type = element.getChild(index).getType();
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[196]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((Arrays.asList(expected).contains(type)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[79]++;
      appendLine(String.format(
          WRONG_CHILD_TYPE_FMT,
          index, element.getType(), printList(expected), type));
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[197]++;
      error = true;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[198]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[80]++;}
  }

  private void appendLine(String line) {
    b.append(String.format("%s", line));
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[199]++;
  }

  // public for test purposes only
  public static String printList(Object[] list) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[200]++;
    StringBuilder builder = new StringBuilder("");
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[201]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((list.length == 1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[81]++;
      builder.append(list[0].toString());
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[202]++;

    } else {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[82]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[203]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((list.length > 1) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[83]++;
      builder.append('[');
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[204]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[205]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[13]++;


int CodeCoverConditionCoverageHelper_C26;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < list.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[13]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[14]--;
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.loops[15]++;
}
        builder.append(list[i].toString());
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[206]++;
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[207]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < list.length - 1) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[85]++;
          builder.append(", ");
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[208]++;

        } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[86]++;}
      }
      builder.append("]");
CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.statements[209]++;

    } else {
  CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx.branches[84]++;}
}
    return builder.toString();
  }

}

class CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx ());
  }
    public static long[] statements = new long[210];
    public static long[] branches = new long[87];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.Validator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
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

  public CodeCoverCoverageCounter$3n2bs0nxxam275207fcpcx () {
    super("com.google.javascript.jscomp.jsonml.Validator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 209; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 86; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.Validator.java");
      for (int i = 1; i <= 209; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 86; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
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

