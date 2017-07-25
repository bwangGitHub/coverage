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

package com.google.javascript.jscomp.parsing;

import com.google.javascript.rhino.head.Token;
import com.google.javascript.rhino.head.ast.ArrayLiteral;
import com.google.javascript.rhino.head.ast.Assignment;
import com.google.javascript.rhino.head.ast.AstNode;
import com.google.javascript.rhino.head.ast.AstRoot;
import com.google.javascript.rhino.head.ast.Block;
import com.google.javascript.rhino.head.ast.BreakStatement;
import com.google.javascript.rhino.head.ast.CatchClause;
import com.google.javascript.rhino.head.ast.ConditionalExpression;
import com.google.javascript.rhino.head.ast.ContinueStatement;
import com.google.javascript.rhino.head.ast.DoLoop;
import com.google.javascript.rhino.head.ast.ElementGet;
import com.google.javascript.rhino.head.ast.EmptyExpression;
import com.google.javascript.rhino.head.ast.EmptyStatement;
import com.google.javascript.rhino.head.ast.ExpressionStatement;
import com.google.javascript.rhino.head.ast.ForInLoop;
import com.google.javascript.rhino.head.ast.ForLoop;
import com.google.javascript.rhino.head.ast.FunctionCall;
import com.google.javascript.rhino.head.ast.FunctionNode;
import com.google.javascript.rhino.head.ast.IfStatement;
import com.google.javascript.rhino.head.ast.InfixExpression;
import com.google.javascript.rhino.head.ast.KeywordLiteral;
import com.google.javascript.rhino.head.ast.Label;
import com.google.javascript.rhino.head.ast.LabeledStatement;
import com.google.javascript.rhino.head.ast.Name;
import com.google.javascript.rhino.head.ast.NewExpression;
import com.google.javascript.rhino.head.ast.NumberLiteral;
import com.google.javascript.rhino.head.ast.ObjectLiteral;
import com.google.javascript.rhino.head.ast.ObjectProperty;
import com.google.javascript.rhino.head.ast.ParenthesizedExpression;
import com.google.javascript.rhino.head.ast.PropertyGet;
import com.google.javascript.rhino.head.ast.RegExpLiteral;
import com.google.javascript.rhino.head.ast.ReturnStatement;
import com.google.javascript.rhino.head.ast.Scope;
import com.google.javascript.rhino.head.ast.StringLiteral;
import com.google.javascript.rhino.head.ast.SwitchCase;
import com.google.javascript.rhino.head.ast.SwitchStatement;
import com.google.javascript.rhino.head.ast.ThrowStatement;
import com.google.javascript.rhino.head.ast.TryStatement;
import com.google.javascript.rhino.head.ast.UnaryExpression;
import com.google.javascript.rhino.head.ast.VariableDeclaration;
import com.google.javascript.rhino.head.ast.VariableInitializer;
import com.google.javascript.rhino.head.ast.WhileLoop;
import com.google.javascript.rhino.head.ast.WithStatement;

/**
 * Type safe dispatcher interface for use with new-style Rhino ASTs.
 *
 * The contents of this file was generated using a script; it is
 * likely that the implementation below really belongs in a virtual
 * typeSafeProcess(TypeSafeDispatcher) method implemented by all AST
 * classes - which would make switching based on types and casting
 * unnecessary.
 *
 */
abstract class TypeSafeDispatcher<T> {
  static {
    CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.ping();
  }

  abstract T processArrayLiteral(ArrayLiteral literalNode);
  abstract T processAssignment(Assignment assignmentNode);
  abstract T processAstRoot(AstRoot rootNode);
  abstract T processBlock(Block blockNode);
  abstract T processBreakStatement(BreakStatement statementNode);
  abstract T processCatchClause(CatchClause clauseNode);
  abstract T processConditionalExpression(ConditionalExpression exprNode);
  abstract T processContinueStatement(ContinueStatement statementNode);
  abstract T processDoLoop(DoLoop loopNode);
  abstract T processElementGet(ElementGet getNode);
  abstract T processEmptyExpression(EmptyExpression exprNode);
  abstract T processEmptyStatement(EmptyStatement exprNode);
  abstract T processExpressionStatement(ExpressionStatement statementNode);
  abstract T processForInLoop(ForInLoop loopNode);
  abstract T processForLoop(ForLoop loopNode);
  abstract T processFunctionCall(FunctionCall callNode);
  abstract T processFunctionNode(FunctionNode functionNode);
  abstract T processIfStatement(IfStatement statementNode);
  abstract T processInfixExpression(InfixExpression exprNode);
  abstract T processKeywordLiteral(KeywordLiteral literalNode);
  abstract T processLabel(Label labelNode);
  abstract T processLabeledStatement(LabeledStatement statementNode);
  abstract T processName(Name nameNode);
  abstract T processNewExpression(NewExpression exprNode);
  abstract T processNumberLiteral(NumberLiteral literalNode);
  abstract T processObjectLiteral(ObjectLiteral literalNode);
  abstract T processObjectProperty(ObjectProperty propertyNode);
  abstract T processParenthesizedExpression(ParenthesizedExpression exprNode);
  abstract T processPropertyGet(PropertyGet getNode);
  abstract T processRegExpLiteral(RegExpLiteral literalNode);
  abstract T processReturnStatement(ReturnStatement statementNode);
  abstract T processScope(Scope scopeNode);
  abstract T processStringLiteral(StringLiteral literalNode);
  abstract T processSwitchCase(SwitchCase caseNode);
  abstract T processSwitchStatement(SwitchStatement statementNode);
  abstract T processThrowStatement(ThrowStatement statementNode);
  abstract T processTryStatement(TryStatement statementNode);
  abstract T processUnaryExpression(UnaryExpression exprNode);
  abstract T processVariableDeclaration(VariableDeclaration declarationNode);
  abstract T processVariableInitializer(VariableInitializer initializerNode);
  abstract T processWhileLoop(WhileLoop loopNode);
  abstract T processWithStatement(WithStatement statementNode);

  abstract T processIllegalToken(AstNode node);

  public T process(AstNode node) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[1]++;
    switch (node.getType()) {
      case Token.ADD:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[1]++;
      case Token.AND:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[2]++;
      case Token.BITAND:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[3]++;
      case Token.BITOR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[4]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[5]++;
      case Token.COMMA:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[6]++;
      case Token.DIV:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[7]++;
      case Token.EQ:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[8]++;
      case Token.GE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[9]++;
      case Token.GT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[10]++;
      case Token.IN:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[11]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[12]++;
      case Token.LE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[13]++;
      case Token.LSH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[14]++;
      case Token.LT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[15]++;
      case Token.MOD:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[16]++;
      case Token.MUL:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[17]++;
      case Token.NE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[18]++;
      case Token.OR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[19]++;
      case Token.RSH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[20]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[21]++;
      case Token.SHNE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[22]++;
      case Token.SUB:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[23]++;
      case Token.URSH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[24]++;
        return processInfixExpression((InfixExpression) node);
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[25]++;
        return processArrayLiteral((ArrayLiteral) node);
      case Token.ASSIGN:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[26]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[27]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[28]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[29]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[30]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[31]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[32]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[33]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[34]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[35]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[36]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[37]++;
        return processAssignment((Assignment) node);
      case Token.BITNOT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[38]++;
      case Token.DEC:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[39]++;
      case Token.DELPROP:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[40]++;
      case Token.INC:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[41]++;
      case Token.NEG:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[42]++;
      case Token.NOT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[43]++;
      case Token.POS:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[44]++;
      case Token.TYPEOF:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[45]++;
      case Token.VOID:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[46]++;
        return processUnaryExpression((UnaryExpression) node);
      case Token.BLOCK:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[47]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node instanceof Block) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[48]++;
          return processBlock((Block) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[49]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;  if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((node instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[50]++;
          return processScope((Scope) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[51]++;
          throw new IllegalStateException("Unexpected node type.  class: " +
                                          node.getClass() +
                                          " type: " +
                                          Token.typeToName(node.getType()));
        }
}
      case Token.BREAK:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[52]++;
        return processBreakStatement((BreakStatement) node);
      case Token.CALL:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[53]++;
        return processFunctionCall((FunctionCall) node);
      case Token.CASE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[54]++;
      case Token.DEFAULT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[55]++;
        return processSwitchCase((SwitchCase) node);
      case Token.CATCH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[56]++;
        return processCatchClause((CatchClause) node);
      case Token.COLON:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[57]++;
        return processObjectProperty((ObjectProperty) node);
      case Token.CONTINUE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[58]++;
        return processContinueStatement((ContinueStatement) node);
      case Token.DO:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[59]++;
        return processDoLoop((DoLoop) node);
      case Token.EMPTY:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[60]++;
        return (node instanceof EmptyExpression) ?
            processEmptyExpression((EmptyExpression) node) :
            processEmptyStatement((EmptyStatement) node);
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[61]++;
      case Token.EXPR_VOID:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[62]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[4]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((node instanceof ExpressionStatement) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[63]++;
          return processExpressionStatement((ExpressionStatement) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[64]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[5]++;
int CodeCoverConditionCoverageHelper_C4;  if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((node instanceof LabeledStatement) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[65]++;
          return processLabeledStatement((LabeledStatement) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[66]++;
          throw new IllegalStateException("Unexpected node type.  class: " +
                                          node.getClass() +
                                          " type: " +
                                          Token.typeToName(node.getType()));
        }
}
      case Token.DEBUGGER:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[67]++;
      case Token.FALSE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[68]++;
      case Token.NULL:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[69]++;
      case Token.THIS:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[70]++;
      case Token.TRUE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[71]++;
        return processKeywordLiteral((KeywordLiteral) node);
      case Token.FOR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[72]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[6]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node instanceof ForInLoop) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[73]++;
          return processForInLoop((ForInLoop) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[74]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[7]++;
int CodeCoverConditionCoverageHelper_C6;  if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node instanceof ForLoop) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[75]++;
          return processForLoop((ForLoop) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[76]++;
          throw new IllegalStateException("Unexpected node type.  class: " +
                                          node.getClass() +
                                          " type: " +
                                          Token.typeToName(node.getType()));
        }
}
      case Token.FUNCTION:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[77]++;
        return processFunctionNode((FunctionNode) node);
      case Token.GETELEM:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[78]++;
        return processElementGet((ElementGet) node);
      case Token.GETPROP:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[79]++;
        return processPropertyGet((PropertyGet) node);
      case Token.HOOK:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[80]++;
        return processConditionalExpression((ConditionalExpression) node);
      case Token.IF:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[81]++;
        return processIfStatement((IfStatement) node);
      case Token.LABEL:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[82]++;
        return processLabel((Label) node);
      case Token.LP:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[83]++;
        return processParenthesizedExpression((ParenthesizedExpression) node);
      case Token.NAME:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[84]++;
        return processName((Name) node);
      case Token.NEW:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[85]++;
        return processNewExpression((NewExpression) node);
      case Token.NUMBER:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[86]++;
        return processNumberLiteral((NumberLiteral) node);
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[87]++;
        return processObjectLiteral((ObjectLiteral) node);
      case Token.REGEXP:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[88]++;
        return processRegExpLiteral((RegExpLiteral) node);
      case Token.RETURN:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[89]++;
        return processReturnStatement((ReturnStatement) node);
      case Token.SCRIPT:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[90]++;
        return processAstRoot((AstRoot) node);
      case Token.STRING:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[91]++;
        return processStringLiteral((StringLiteral) node);
      case Token.SWITCH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[92]++;
        return processSwitchStatement((SwitchStatement) node);
      case Token.THROW:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[93]++;
        return processThrowStatement((ThrowStatement) node);
      case Token.TRY:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[94]++;
        return processTryStatement((TryStatement) node);
      case Token.CONST:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[95]++;
      case Token.VAR:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[96]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[8]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node instanceof VariableDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[97]++;
          return processVariableDeclaration((VariableDeclaration) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[98]++;
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.statements[9]++;
int CodeCoverConditionCoverageHelper_C8;  if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node instanceof VariableInitializer) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[99]++;
          return processVariableInitializer((VariableInitializer) node);

        } else {
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[100]++;
          throw new IllegalStateException("Unexpected node type.  class: " +
                                          node.getClass() +
                                          " type: " +
                                          Token.typeToName(node.getType()));
        }
}
      case Token.WHILE:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[101]++;
        return processWhileLoop((WhileLoop) node);
      case Token.WITH:
CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[102]++;
        return processWithStatement((WithStatement) node); default : CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld.branches[103]++;
    }
    return processIllegalToken(node);
  }
}

class CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[104];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.parsing.TypeSafeDispatcher.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$2qkb72sp38a1tt5kq1ibvpepwekdtjchueld () {
    super("com.google.javascript.jscomp.parsing.TypeSafeDispatcher.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 103; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.TypeSafeDispatcher.java");
      for (int i = 1; i <= 9; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 103; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

