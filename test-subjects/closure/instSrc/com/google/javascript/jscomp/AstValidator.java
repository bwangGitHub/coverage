/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * This class walks the AST and validates that the structure is correct.
 *
 * @author johnlenz@google.com (John Lenz)
 */
public class AstValidator implements CompilerPass {
  static {
    CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.ping();
  }


  // Possible enhancements:
  // * verify NAME, LABEL_NAME, GETPROP property name and unquoted
  // object-literal keys are valid JavaScript identifiers.
  // * optionally verify every node has source location information.
  // * optionally verify every node has an assigned JSType
  //

  public interface ViolationHandler {
    void handleViolation(String message, Node n);
  }

  private final ViolationHandler violationHandler;

  public AstValidator(ViolationHandler handler) {
    this.violationHandler = handler;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[1]++;
  }

  public AstValidator() {
    this.violationHandler = new ViolationHandler() {
      @Override
      public void handleViolation(String message, Node n) {
        throw new IllegalStateException(
            message + " Reference node " + n.toString());
      }
    };
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((externs != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[1]++;
      validateCodeRoot(externs);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[4]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[2]++;}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[3]++;
      validateCodeRoot(root);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[6]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[4]++;}
  }

  public void validateRoot(Node n) {
    validateNodeType(Token.BLOCK, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[7]++;
    validateIsSynthetic(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[8]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[9]++;
    validateCodeRoot(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[10]++;
    validateCodeRoot(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[11]++;
  }

  public void validateCodeRoot(Node n) {
    validateNodeType(Token.BLOCK, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[12]++;
    validateIsSynthetic(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[13]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[1]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[2]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[3]++;
}
      validateScript(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[15]++;
    }
  }

  public void validateScript(Node n) {
    validateNodeType(Token.SCRIPT, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[16]++;
    validateHasSourceName(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[17]++;
    validateHasInputId(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[18]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[4]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[5]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[6]++;
}
      validateStatement(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[20]++;
    }
  }

  public void validateStatement(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[21]++;
    switch (n.getType()) {
      case Token.LABEL:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[5]++;
        validateLabel(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[22]++;
        return;
      case Token.BLOCK:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[6]++;
        validateBlock(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[23]++;
        return;
      case Token.FUNCTION:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[7]++;
        validateFunctionStatement(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[24]++;
        return;
      case Token.WITH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[8]++;
        validateWith(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[25]++;
        return;
      case Token.FOR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[9]++;
        validateFor(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[26]++;
        return;
      case Token.WHILE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[10]++;
        validateWhile(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[27]++;
        return;
      case Token.DO:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[11]++;
        validateDo(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[28]++;
        return;
      case Token.SWITCH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[12]++;
        validateSwitch(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[29]++;
        return;
      case Token.IF:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[13]++;
        validateIf(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[30]++;
        return;
      case Token.VAR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[14]++;
        validateVar(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[31]++;
        return;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[15]++;
        validateExprStmt(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[32]++;
        return;
      case Token.RETURN:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[16]++;
        validateReturn(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[33]++;
        return;
      case Token.THROW:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[17]++;
        validateThrow(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[34]++;
        return;
      case Token.TRY:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[18]++;
        validateTry(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[35]++;
        return;
      case Token.BREAK:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[19]++;
        validateBreak(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[36]++;
        return;
      case Token.CONTINUE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[20]++;
        validateContinue(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[37]++;
        return;
      case Token.EMPTY:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[21]++;
        validateChildless(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[38]++;
        return;
      case Token.DEBUGGER:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[22]++;
        validateChildless(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[39]++;
        return;
      default:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[23]++;
        violation("Expected statement but was "
            + Token.name(n.getType()) + ".", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[40]++;
    }
  }

  public void validateExpression(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[41]++;
    switch (n.getType()) {
      // Childless expressions
      case Token.FALSE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[24]++;
      case Token.NULL:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[25]++;
      case Token.THIS:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[26]++;
      case Token.TRUE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[27]++;
        validateChildless(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[42]++;
        return;

      // General unary ops
      case Token.DELPROP:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[28]++;
      case Token.POS:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[29]++;
      case Token.NEG:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[30]++;
      case Token.NOT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[31]++;
      case Token.INC:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[32]++;
      case Token.DEC:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[33]++;
      case Token.TYPEOF:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[34]++;
      case Token.VOID:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[35]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[36]++;
      case Token.CAST:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[37]++;
        validateUnaryOp(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[43]++;
        return;

      // General binary ops
      case Token.COMMA:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[38]++;
      case Token.OR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[39]++;
      case Token.AND:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[40]++;
      case Token.BITOR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[41]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[42]++;
      case Token.BITAND:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[43]++;
      case Token.EQ:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[44]++;
      case Token.NE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[45]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[46]++;
      case Token.SHNE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[47]++;
      case Token.LT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[48]++;
      case Token.GT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[49]++;
      case Token.LE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[50]++;
      case Token.GE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[51]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[52]++;
      case Token.IN:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[53]++;
      case Token.LSH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[54]++;
      case Token.RSH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[55]++;
      case Token.URSH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[56]++;
      case Token.SUB:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[57]++;
      case Token.ADD:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[58]++;
      case Token.MUL:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[59]++;
      case Token.MOD:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[60]++;
      case Token.DIV:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[61]++;
        validateBinaryOp(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[44]++;
        return;

      // Assignments
      case Token.ASSIGN:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[62]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[63]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[64]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[65]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[66]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[67]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[68]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[69]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[70]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[71]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[72]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[73]++;
        validateAssignmentExpression(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[45]++;
        return;

      case Token.HOOK:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[74]++;
        validateTrinaryOp(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[46]++;
        return;

      // Node types that require special handling
      case Token.STRING:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[75]++;
        validateString(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[47]++;
        return;

      case Token.NUMBER:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[76]++;
        validateNumber(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[48]++;
        return;

      case Token.NAME:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[77]++;
        validateName(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[49]++;
        return;

      case Token.GETELEM:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[78]++;
        validateBinaryOp(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[50]++;
        return;

      case Token.GETPROP:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[79]++;
        validateGetProp(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[51]++;
        return;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[80]++;
        validateArrayLit(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[52]++;
        return;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[81]++;
        validateObjectLit(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[53]++;
        return;

      case Token.REGEXP:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[82]++;
        validateRegExpLit(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[54]++;
        return;

      case Token.CALL:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[83]++;
        validateCall(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[55]++;
        return;

      case Token.NEW:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[84]++;
        validateNew(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[56]++;
        return;

      case Token.FUNCTION:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[85]++;
        validateFunctionExpression(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[57]++;
        return;

      default:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[86]++;
        violation("Expected expression but was "
            + Token.name(n.getType()), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[58]++;
    }
  }

  private void validateBlock(Node n) {
    validateNodeType(Token.BLOCK, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[59]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[60]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[7]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[8]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[9]++;
}
      validateStatement(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[61]++;
    }
  }

  private void validateSyntheticBlock(Node n) {
    validateNodeType(Token.BLOCK, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[62]++;
    validateIsSynthetic(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[63]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[64]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[10]++;


int CodeCoverConditionCoverageHelper_C6;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[10]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[11]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[12]++;
}
      validateStatement(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[65]++;
    }
  }

  private void validateIsSynthetic(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[66]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.getBooleanProp(Node.SYNTHETIC_BLOCK_PROP)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[87]++;
      violation("Missing 'synthetic block' annotation.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[67]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[88]++;}
  }

  private void validateHasSourceName(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[68]++;
    String sourceName = n.getSourceFileName();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[69]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((sourceName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[89]++;
      violation("Missing 'source name' annotation.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[70]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[90]++;}
  }

  private void validateHasInputId(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[71]++;
    InputId inputId = n.getInputId();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[72]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((inputId == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[91]++;
      violation("Missing 'input id' annotation.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[73]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[92]++;}
  }

  private void validateLabel(Node n) {
    validateNodeType(Token.LABEL, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[74]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[75]++;
    validateLabelName(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[76]++;
    validateStatement(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[77]++;
  }

  private void validateLabelName(Node n) {
    validateNodeType(Token.LABEL_NAME, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[78]++;
    validateNonEmptyString(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[79]++;
    validateChildCount(n, 0);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[80]++;
  }

  private void validateNonEmptyString(Node n) {
    validateNonNullString(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[81]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[82]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[93]++;
      violation("Expected non-empty string.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[83]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[94]++;}
  }

  private void validateNonNullString(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[84]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.getString() == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[95]++;
      violation("Expected non-null string.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[85]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[96]++;}
  }

  private void validateName(Node n) {
    validateNodeType(Token.NAME, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[86]++;
    validateNonEmptyString(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[87]++;
    validateChildCount(n, 0);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[88]++;
  }

  private void validateOptionalName(Node n) {
    validateNodeType(Token.NAME, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[89]++;
    validateNonNullString(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[90]++;
    validateChildCount(n, 0);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[91]++;
  }

  private void validateFunctionStatement(Node n) {
    validateNodeType(Token.FUNCTION, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[92]++;
    validateChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[93]++;
    validateName(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[94]++;
    validateParameters(n.getChildAtIndex(1));
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[95]++;
    validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[96]++;
  }

  private void validateFunctionExpression(Node n) {
    validateNodeType(Token.FUNCTION, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[97]++;
    validateChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[98]++;
    validateOptionalName(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[99]++;
    validateParameters(n.getChildAtIndex(1));
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[100]++;
    validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[101]++;
  }

  private void validateParameters(Node n) {
    validateNodeType(Token.PARAM_LIST, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[102]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[103]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[13]++;


int CodeCoverConditionCoverageHelper_C12;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[13]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[14]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[15]++;
}
      validateName(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[104]++;
    }
  }

  private void validateCall(Node n) {
    validateNodeType(Token.CALL, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[105]++;
    validateMinimumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[106]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[107]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[16]++;


int CodeCoverConditionCoverageHelper_C13;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[16]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[17]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[18]++;
}
      validateExpression(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[108]++;
    }
  }

  private void validateNew(Node n) {
    validateNodeType(Token.NEW, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[109]++;
    validateMinimumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[110]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[111]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[19]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[20]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[21]++;
}
      validateExpression(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[112]++;
    }
  }

  private void validateVar(Node n) {
    validateNodeType(Token.VAR, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[113]++;
    this.validateMinimumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[114]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[115]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[22]++;


int CodeCoverConditionCoverageHelper_C15;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[22]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[23]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[24]++;
}
      // Don't use the validateName here as the NAME is allowed to have
      // a child.
      validateNodeType(Token.NAME, c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[116]++;
      validateNonEmptyString(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[117]++;
      validateMaximumChildCount(c, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[118]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[119]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[97]++;
        validateExpression(c.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[120]++;

      } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[98]++;}
    }
  }

  private void validateFor(Node n) {
    validateNodeType(Token.FOR, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[121]++;
    validateMinimumChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[122]++;
    validateMaximumChildCount(n, 4);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[123]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[124]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[99]++;
      // FOR-IN
      validateChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[125]++;
      validateVarOrAssignmentTarget(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[126]++;
      validateExpression(n.getChildAtIndex(1));
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[127]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[100]++;
      // FOR
      validateChildCount(n, 4);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[128]++;
      validateVarOrOptionalExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[129]++;
      validateOptionalExpression(n.getChildAtIndex(1));
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[130]++;
      validateOptionalExpression(n.getChildAtIndex(2));
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[131]++;
    }
    validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[132]++;
  }

  private void validateVarOrOptionalExpression(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[133]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[101]++;
      validateVar(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[134]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[102]++;
      validateOptionalExpression(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[135]++;
    }
  }

  private void validateVarOrAssignmentTarget(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[136]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[103]++;
      // Only one NAME can be declared for FOR-IN expressions.
      this.validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[137]++;
      validateVar(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[138]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[104]++;
      validateAssignmentTarget(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[139]++;
    }
  }

  private void validateWith(Node n) {
    validateNodeType(Token.WITH, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[140]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[141]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[142]++;
    validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[143]++;
  }

  private void validateWhile(Node n) {
    validateNodeType(Token.WHILE, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[144]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[145]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[146]++;
    validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[147]++;
  }

  private void validateDo(Node n) {
    validateNodeType(Token.DO, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[148]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[149]++;
    validateBlock(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[150]++;
    validateExpression(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[151]++;
  }

  private void validateIf(Node n) {
    validateNodeType(Token.IF, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[152]++;
    validateMinimumChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[153]++;
    validateMaximumChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[154]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[155]++;
    validateBlock(n.getChildAtIndex(1));
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[156]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[157]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n.getChildCount() == 3) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[105]++;
      validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[158]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[106]++;}
  }

  private void validateExprStmt(Node n) {
    validateNodeType(Token.EXPR_RESULT, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[159]++;
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[160]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[161]++;
  }

  private void validateReturn(Node n) {
    validateNodeType(Token.RETURN, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[162]++;
    validateMaximumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[163]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[164]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[107]++;
      validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[165]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[108]++;}
  }

  private void validateThrow(Node n) {
    validateNodeType(Token.THROW, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[166]++;
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[167]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[168]++;
  }

  private void validateBreak(Node n) {
    validateNodeType(Token.BREAK, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[169]++;
    validateMaximumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[170]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[171]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[109]++;
      validateLabelName(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[172]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[110]++;}
  }

  private void validateContinue(Node n) {
    validateNodeType(Token.CONTINUE, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[173]++;
    validateMaximumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[174]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[175]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[111]++;
      validateLabelName(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[176]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[112]++;}
  }

  private void validateTry(Node n) {
    validateNodeType(Token.TRY, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[177]++;
    validateMinimumChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[178]++;
    validateMaximumChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[179]++;
    validateBlock(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[180]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[181]++;

    boolean seenCatchOrFinally = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[182]++;

    // Validate catch
    Node catches = n.getChildAtIndex(1);
    validateNodeType(Token.BLOCK, catches);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[183]++;
    validateMaximumChildCount(catches, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[184]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[185]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((catches.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[113]++;
      validateCatch(catches.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[186]++;
      seenCatchOrFinally = true;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[187]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[114]++;}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[188]++;
int CodeCoverConditionCoverageHelper_C25;

    // Validate finally
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((n.getChildCount() == 3) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[115]++;
      validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[189]++;
      seenCatchOrFinally = true;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[190]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[116]++;}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[191]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((seenCatchOrFinally) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[117]++;
      violation("Missing catch or finally for try statement.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[192]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[118]++;}
  }

  private void validateCatch(Node n) {
    validateNodeType(Token.CATCH, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[193]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[194]++;
    validateName(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[195]++;
    validateBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[196]++;
  }

  private void validateSwitch(Node n) {
    validateNodeType(Token.SWITCH, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[197]++;
    validateMinimumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[198]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[199]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[200]++;
    int defaults = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[201]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[25]++;


int CodeCoverConditionCoverageHelper_C27;
    for (Node c = n.getFirstChild().getNext();(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[25]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[26]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[27]++;
}
      validateSwitchMember(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[202]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[203]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c.isDefaultCase()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[119]++;
        defaults++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[204]++;

      } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[120]++;}
    }
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[205]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((defaults > 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[121]++;
      violation("Expected at most 1 'default' in switch but was "
          + defaults, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[206]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[122]++;}
  }

  private void validateSwitchMember(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[207]++;
    switch (n.getType()) {
      case Token.CASE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[123]++;
        validateCase(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[208]++;
        return;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[124]++;
        validateDefault(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[209]++;
        return;
      default:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[125]++;
        violation("Expected switch member but was "
            + Token.name(n.getType()), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[210]++;
    }
  }

  private void validateDefault(Node n) {
    validateNodeType(Token.DEFAULT_CASE, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[211]++;
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[212]++;
    validateSyntheticBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[213]++;
  }

  private void validateCase(Node n) {
    validateNodeType(Token.CASE, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[214]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[215]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[216]++;
    validateSyntheticBlock(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[217]++;
  }

  private void validateOptionalExpression(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[218]++;
int CodeCoverConditionCoverageHelper_C30;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[126]++;
      validateChildless(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[219]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[127]++;
      validateExpression(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[220]++;
    }
  }

  private void validateChildless(Node n) {
    validateChildCount(n, 0);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[221]++;
  }

  private void validateAssignmentExpression(Node n) {
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[222]++;
    validateAssignmentTarget(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[223]++;
    validateExpression(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[224]++;
  }

  private void validateAssignmentTarget(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[225]++;
    switch (n.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[128]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[129]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[130]++;
        validateExpression(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[226]++;
        return;
      default:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[131]++;
        violation("Expected assignment target expression but was "
            + Token.name(n.getType()), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[227]++;
    }
  }

  private void validateGetProp(Node n) {
    validateNodeType(Token.GETPROP, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[228]++;
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[229]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[230]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[231]++;
    Node prop = n.getLastChild();
    validateNodeType(Token.STRING, prop);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[232]++;
    validateNonEmptyString(prop);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[233]++;
  }

  private void validateRegExpLit(Node n) {
    validateNodeType(Token.REGEXP, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[234]++;
    validateMinimumChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[235]++;
    validateMaximumChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[236]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[237]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[28]++;


int CodeCoverConditionCoverageHelper_C31;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[28]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[29]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[30]++;
}
      validateString(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[238]++;
    }
  }

  private void validateString(Node n) {
    validateNodeType(Token.STRING, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[239]++;
    validateChildCount(n, 0);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[240]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[241]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      // Validate that getString doesn't throw
      n.getString();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[242]++;
    } catch (UnsupportedOperationException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[133]++;
      violation("Invalid STRING node.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[243]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[132]++;
}
  }
  }

  private void validateNumber(Node n) {
    validateNodeType(Token.NUMBER, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[244]++;
    validateChildCount(n, 0);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[245]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[246]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      // Validate that getDouble doesn't throw
      n.getDouble();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[247]++;
    } catch (UnsupportedOperationException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[135]++;
      violation("Invalid NUMBER node.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[248]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[134]++;
}
  }
  }

  private void validateArrayLit(Node n) {
    validateNodeType(Token.ARRAYLIT, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[249]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[250]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[31]++;


int CodeCoverConditionCoverageHelper_C32;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[31]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[32]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[33]++;
}
      // EMPTY is allowed to represent empty slots.
      validateOptionalExpression(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[251]++;
    }
  }

  private void validateObjectLit(Node n) {
    validateNodeType(Token.OBJECTLIT, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[252]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[253]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[34]++;


int CodeCoverConditionCoverageHelper_C33;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[34]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[35]--;
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.loops[36]++;
}
      validateObjectLitKey(c);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[254]++;
    }
  }

  private void validateObjectLitKey(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[255]++;
    switch (n.getType()) {
      case Token.GETTER_DEF:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[136]++;
        validateObjectLitGetKey(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[256]++;
        return;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[137]++;
        validateObjectLitSetKey(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[257]++;
        return;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[138]++;
        validateObjectLitStringKey(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[258]++;
        return;
      default:
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[139]++;
        violation("Expected object literal key expression but was "
              + Token.name(n.getType()), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[259]++;
    }
  }

  private void validateObjectLitGetKey(Node n) {
    validateNodeType(Token.GETTER_DEF, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[260]++;
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[261]++;
    validateObjectLiteralKeyName(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[262]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[263]++;
    Node function = n.getFirstChild();
    validateFunctionExpression(function);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[264]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[265]++;
int CodeCoverConditionCoverageHelper_C34;
    // objlit get functions must be nameless, and must have zero parameters.
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((function.getFirstChild().getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[140]++;
      violation("Expected unnamed function expression.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[266]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[141]++;}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[267]++;
    Node functionParams = function.getChildAtIndex(1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[268]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((functionParams.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[142]++;
      violation("get methods must not have parameters.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[269]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[143]++;}
  }

  private void validateObjectLitSetKey(Node n) {
    validateNodeType(Token.SETTER_DEF, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[270]++;
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[271]++;
    validateObjectLiteralKeyName(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[272]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[273]++;
    Node function = n.getFirstChild();
    validateFunctionExpression(function);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[274]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[275]++;
int CodeCoverConditionCoverageHelper_C36;
    // objlit set functions must be nameless, and must have 1 parameter.
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((function.getFirstChild().getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[144]++;
      violation("Expected unnamed function expression.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[276]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[145]++;}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[277]++;
    Node functionParams = function.getChildAtIndex(1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[278]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((functionParams.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[146]++;
      violation("set methods must have exactly one parameter.", n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[279]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[147]++;}
  }

  private void validateObjectLitStringKey(Node n) {
    validateNodeType(Token.STRING_KEY, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[280]++;
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[281]++;
    validateObjectLiteralKeyName(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[282]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[283]++;
  }

  private void validateObjectLiteralKeyName(Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[284]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((n.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[148]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[285]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
      try {
CodeCoverTryBranchHelper_Try3 = true;
        // Validate that getString doesn't throw
        n.getString();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[286]++;
      } catch (UnsupportedOperationException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[151]++;
        violation("getString failed for" + Token.name(n.getType()), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[287]++;
      } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[150]++;
}
  }

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[149]++;
      validateNonEmptyString(n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[288]++;
    }
  }

  private void validateUnaryOp(Node n) {
    validateChildCount(n, 1);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[289]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[290]++;
  }

  private void validateBinaryOp(Node n) {
    validateChildCount(n, 2);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[291]++;
    validateExpression(n.getFirstChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[292]++;
    validateExpression(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[293]++;
  }

  private void validateTrinaryOp(Node n) {
    validateChildCount(n, 3);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[294]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[295]++;
    Node first = n.getFirstChild();
    validateExpression(first);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[296]++;
    validateExpression(first.getNext());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[297]++;
    validateExpression(n.getLastChild());
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[298]++;
  }

  private void violation(String message, Node n) {
    violationHandler.handleViolation(message, n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[299]++;
  }

  private void validateNodeType(int type, Node n) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[300]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((n.getType() != type) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[152]++;
      violation(
          "Expected " + Token.name(type) + " but was "
              + Token.name(n.getType()), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[301]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[153]++;}
  }

  private void validateChildCount(Node n, int i) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[302]++;
    boolean valid = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[303]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[154]++;
      valid = !n.hasChildren();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[304]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[155]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[305]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i == 1) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[156]++;
      valid = n.hasOneChild();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[306]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[157]++;
      valid = (n.getChildCount() == i);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[307]++;
    }
}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[308]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((valid) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[158]++;
      violation(
          "Expected " + i + " children, but was "
              + n.getChildCount(), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[309]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[159]++;}
  }

  private void validateMinimumChildCount(Node n, int i) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[310]++;
    boolean valid = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[311]++;
int CodeCoverConditionCoverageHelper_C43;
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i == 1) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[160]++;
      valid = n.hasChildren();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[312]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[161]++;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[313]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((i == 2) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[162]++;
      valid = n.hasMoreThanOneChild();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[314]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[163]++;
      valid = n.getChildCount() >= i;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[315]++;
    }
}
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[316]++;
int CodeCoverConditionCoverageHelper_C45;

    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((valid) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[164]++;
      violation(
          "Expected at least " + i + " children, but was "
              + n.getChildCount(), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[317]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[165]++;}
  }

  private void validateMaximumChildCount(Node n, int i) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[318]++;
    boolean valid = false;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[319]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i == 1) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[166]++;
      valid = !n.hasMoreThanOneChild();
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[320]++;

    } else {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[167]++;
      valid = n.getChildCount() <= i;
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[321]++;
    }
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[322]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((valid) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[168]++;
      violation(
          "Expected no more than " + i + " children, but was "
              + n.getChildCount(), n);
CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.statements[323]++;

    } else {
  CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5.branches[169]++;}
  }
}

class CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5 ());
  }
    public static long[] statements = new long[324];
    public static long[] branches = new long[170];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[48];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AstValidator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 47; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$rjyomyf9w50g6ma8n3ty8jz4v5 () {
    super("com.google.javascript.jscomp.AstValidator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 323; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 169; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AstValidator.java");
      for (int i = 1; i <= 323; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 169; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

