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
 *   John Lenz
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

package com.google.javascript.rhino;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * An AST construction helper class
 * @author johnlenz@google.com (John Lenz)
 */
public class IR {
  static {
    CodeCoverCoverageCounter$5n7klajrsyp.ping();
  }


  private IR() {}

  public static Node empty() {
    return new Node(Token.EMPTY);
  }

  public static Node function(Node name, Node params, Node body) {
    Preconditions.checkState(name.isName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[1]++;
    Preconditions.checkState(params.isParamList());
CodeCoverCoverageCounter$5n7klajrsyp.statements[2]++;
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[3]++;
    return new Node(Token.FUNCTION, name, params, body);
  }

  public static Node paramList() {
    return new Node(Token.PARAM_LIST);
  }

  public static Node paramList(Node param) {
    Preconditions.checkState(param.isName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[4]++;
    return new Node(Token.PARAM_LIST, param);
  }

  public static Node paramList(Node ... params) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[5]++;
    Node paramList = paramList();
CodeCoverCoverageCounter$5n7klajrsyp.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[1]++;


    for (Node param : params) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[1]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[2]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[3]++;
}
      Preconditions.checkState(param.isName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[7]++;
      paramList.addChildToBack(param);
CodeCoverCoverageCounter$5n7klajrsyp.statements[8]++;
    }
    return paramList;
  }

  public static Node paramList(List<Node> params) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[9]++;
    Node paramList = paramList();
CodeCoverCoverageCounter$5n7klajrsyp.statements[10]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[4]++;


    for (Node param : params) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[4]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[5]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[6]++;
}
      Preconditions.checkState(param.isName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[11]++;
      paramList.addChildToBack(param);
CodeCoverCoverageCounter$5n7klajrsyp.statements[12]++;
    }
    return paramList;
  }

  public static Node block() {
CodeCoverCoverageCounter$5n7klajrsyp.statements[13]++;
    Node block = new Node(Token.BLOCK);
    return block;
  }

  public static Node block(Node stmt) {
    Preconditions.checkState(mayBeStatement(stmt));
CodeCoverCoverageCounter$5n7klajrsyp.statements[14]++;
CodeCoverCoverageCounter$5n7klajrsyp.statements[15]++;
    Node block = new Node(Token.BLOCK, stmt);
    return block;
  }

  public static Node block(Node ... stmts) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[16]++;
    Node block = block();
CodeCoverCoverageCounter$5n7klajrsyp.statements[17]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[7]++;


    for (Node stmt : stmts) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[7]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[8]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[9]++;
}
      Preconditions.checkState(mayBeStatement(stmt));
CodeCoverCoverageCounter$5n7klajrsyp.statements[18]++;
      block.addChildToBack(stmt);
CodeCoverCoverageCounter$5n7klajrsyp.statements[19]++;
    }
    return block;
  }

  public static Node block(List<Node> stmts) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[20]++;
    Node paramList = block();
CodeCoverCoverageCounter$5n7klajrsyp.statements[21]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[10]++;


    for (Node stmt : stmts) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[10]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[11]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[12]++;
}
      Preconditions.checkState(mayBeStatement(stmt));
CodeCoverCoverageCounter$5n7klajrsyp.statements[22]++;
      paramList.addChildToBack(stmt);
CodeCoverCoverageCounter$5n7klajrsyp.statements[23]++;
    }
    return paramList;
  }

  private static Node blockUnchecked(Node stmt) {
    return new Node(Token.BLOCK, stmt);
  }

  public static Node script() {
CodeCoverCoverageCounter$5n7klajrsyp.statements[24]++;
    // TODO(johnlenz): finish setting up the SCRIPT node
    Node block = new Node(Token.SCRIPT);
    return block;
  }

  public static Node script(Node ... stmts) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[25]++;
    Node block = script();
CodeCoverCoverageCounter$5n7klajrsyp.statements[26]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[13]++;


    for (Node stmt : stmts) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[13]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[14]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[15]++;
}
      Preconditions.checkState(mayBeStatementNoReturn(stmt));
CodeCoverCoverageCounter$5n7klajrsyp.statements[27]++;
      block.addChildToBack(stmt);
CodeCoverCoverageCounter$5n7klajrsyp.statements[28]++;
    }
    return block;
  }

  public static Node script(List<Node> stmts) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[29]++;
    Node paramList = script();
CodeCoverCoverageCounter$5n7klajrsyp.statements[30]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[16]++;


    for (Node stmt : stmts) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[16]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[17]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[18]++;
}
      Preconditions.checkState(mayBeStatementNoReturn(stmt));
CodeCoverCoverageCounter$5n7klajrsyp.statements[31]++;
      paramList.addChildToBack(stmt);
CodeCoverCoverageCounter$5n7klajrsyp.statements[32]++;
    }
    return paramList;
  }

  public static Node var(Node name, Node value) {
    Preconditions.checkState(name.isName() && !name.hasChildren());
CodeCoverCoverageCounter$5n7klajrsyp.statements[33]++;
    Preconditions.checkState(mayBeExpression(value));
CodeCoverCoverageCounter$5n7klajrsyp.statements[34]++;
    name.addChildToFront(value);
CodeCoverCoverageCounter$5n7klajrsyp.statements[35]++;
    return var(name);
  }

  public static Node var(Node name) {
    Preconditions.checkState(name.isName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[36]++;
    return new Node(Token.VAR, name);
  }

  public static Node returnNode() {
    return new Node(Token.RETURN);
  }

  public static Node returnNode(Node expr) {
    Preconditions.checkState(mayBeExpression(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[37]++;
    return new Node(Token.RETURN, expr);
  }

  public static Node throwNode(Node expr) {
    Preconditions.checkState(mayBeExpression(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[38]++;
    return new Node(Token.THROW, expr);
  }

  public static Node exprResult(Node expr) {
    Preconditions.checkState(mayBeExpression(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[39]++;
    return new Node(Token.EXPR_RESULT, expr);
  }

  public static Node ifNode(Node cond, Node then) {
    Preconditions.checkState(mayBeExpression(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[40]++;
    Preconditions.checkState(then.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[41]++;
    return new Node(Token.IF, cond, then);
  }

  public static Node ifNode(Node cond, Node then, Node elseNode) {
    Preconditions.checkState(mayBeExpression(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[42]++;
    Preconditions.checkState(then.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[43]++;
    Preconditions.checkState(elseNode.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[44]++;
    return new Node(Token.IF, cond, then, elseNode);
  }

  public static Node doNode(Node body, Node cond) {
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[45]++;
    Preconditions.checkState(mayBeExpression(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[46]++;
    return new Node(Token.DO, body, cond);
  }

  public static Node forIn(Node target, Node cond, Node body) {
    Preconditions.checkState(target.isVar() || mayBeExpression(target));
CodeCoverCoverageCounter$5n7klajrsyp.statements[47]++;
    Preconditions.checkState(mayBeExpression(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[48]++;
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[49]++;
    return new Node(Token.FOR, target, cond, body);
  }

  public static Node forNode(Node init, Node cond, Node incr, Node body) {
    Preconditions.checkState(init.isVar() || mayBeExpressionOrEmpty(init));
CodeCoverCoverageCounter$5n7klajrsyp.statements[50]++;
    Preconditions.checkState(mayBeExpressionOrEmpty(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[51]++;
    Preconditions.checkState(mayBeExpressionOrEmpty(incr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[52]++;
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[53]++;
    return new Node(Token.FOR, init, cond, incr, body);
  }

  public static Node switchNode(Node cond, Node ... cases) {
    Preconditions.checkState(mayBeExpression(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[54]++;
CodeCoverCoverageCounter$5n7klajrsyp.statements[55]++;
    Node switchNode = new Node(Token.SWITCH, cond);
CodeCoverCoverageCounter$5n7klajrsyp.statements[56]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[19]++;


    for (Node caseNode : cases) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[19]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[20]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[21]++;
}
      Preconditions.checkState(caseNode.isCase() || caseNode.isDefaultCase());
CodeCoverCoverageCounter$5n7klajrsyp.statements[57]++;
      switchNode.addChildToBack(caseNode);
CodeCoverCoverageCounter$5n7klajrsyp.statements[58]++;
    }
    return switchNode;
  }

  public static Node caseNode(Node expr, Node body) {
    Preconditions.checkState(mayBeExpression(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[59]++;
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[60]++;
    body.putBooleanProp(Node.SYNTHETIC_BLOCK_PROP, true);
CodeCoverCoverageCounter$5n7klajrsyp.statements[61]++;
    return new Node(Token.CASE, expr, body);
  }

  public static Node defaultCase(Node body) {
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[62]++;
    body.putBooleanProp(Node.SYNTHETIC_BLOCK_PROP, true);
CodeCoverCoverageCounter$5n7klajrsyp.statements[63]++;
    return new Node(Token.DEFAULT_CASE, body);
  }

  public static Node label(Node name, Node stmt) {
    // TODO(johnlenz): additional validation here.
    Preconditions.checkState(name.isLabelName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[64]++;
    Preconditions.checkState(mayBeStatement(stmt));
CodeCoverCoverageCounter$5n7klajrsyp.statements[65]++;
CodeCoverCoverageCounter$5n7klajrsyp.statements[66]++;
    Node block = new Node(Token.LABEL, name, stmt);
    return block;
  }

  public static Node labelName(String name) {
    Preconditions.checkState(!name.isEmpty());
CodeCoverCoverageCounter$5n7klajrsyp.statements[67]++;
    return Node.newString(Token.LABEL_NAME, name);
  }

  public static Node tryFinally(Node tryBody, Node finallyBody) {
    Preconditions.checkState(tryBody.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[68]++;
    Preconditions.checkState(finallyBody.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[69]++;
CodeCoverCoverageCounter$5n7klajrsyp.statements[70]++;
    Node catchBody = block().copyInformationFrom(tryBody);
    return new Node(Token.TRY, tryBody, catchBody, finallyBody);
  }

  public static Node tryCatch(Node tryBody, Node catchNode) {
    Preconditions.checkState(tryBody.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[71]++;
    Preconditions.checkState(catchNode.isCatch());
CodeCoverCoverageCounter$5n7klajrsyp.statements[72]++;
CodeCoverCoverageCounter$5n7klajrsyp.statements[73]++;
    Node catchBody = blockUnchecked(catchNode).copyInformationFrom(catchNode);
    return new Node(Token.TRY, tryBody, catchBody);
  }

  public static Node tryCatchFinally(
      Node tryBody, Node catchNode, Node finallyBody) {
    Preconditions.checkState(finallyBody.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[74]++;
CodeCoverCoverageCounter$5n7klajrsyp.statements[75]++;
    Node tryNode = tryCatch(tryBody, catchNode);
    tryNode.addChildToBack(finallyBody);
CodeCoverCoverageCounter$5n7klajrsyp.statements[76]++;
    return tryNode;
  }

  public static Node catchNode(Node expr, Node body) {
    Preconditions.checkState(expr.isName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[77]++;
    Preconditions.checkState(body.isBlock());
CodeCoverCoverageCounter$5n7klajrsyp.statements[78]++;
    return new Node(Token.CATCH, expr, body);
  }

  public static Node breakNode() {
    return new Node(Token.BREAK);
  }

  public static Node breakNode(Node name) {
    // TODO(johnlenz): additional validation here.
    Preconditions.checkState(name.isLabelName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[79]++;
    return new Node(Token.BREAK, name);
  }

  public static Node continueNode() {
    return new Node(Token.CONTINUE);
  }

  public static Node continueNode(Node name) {
    // TODO(johnlenz): additional validation here.
    Preconditions.checkState(name.isLabelName());
CodeCoverCoverageCounter$5n7klajrsyp.statements[80]++;
    return new Node(Token.CONTINUE, name);
  }


  //

  public static Node call(Node target, Node ... args) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[81]++;
    Node call = new Node(Token.CALL, target);
CodeCoverCoverageCounter$5n7klajrsyp.statements[82]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[22]++;


    for (Node arg : args) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[22]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[23]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[24]++;
}
      Preconditions.checkState(mayBeExpression(arg));
CodeCoverCoverageCounter$5n7klajrsyp.statements[83]++;
      call.addChildToBack(arg);
CodeCoverCoverageCounter$5n7klajrsyp.statements[84]++;
    }
    return call;
  }

  public static Node newNode(Node target, Node ... args) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[85]++;
    Node newcall = new Node(Token.NEW, target);
CodeCoverCoverageCounter$5n7klajrsyp.statements[86]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[25]++;


    for (Node arg : args) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[25]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[26]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[27]++;
}
      Preconditions.checkState(mayBeExpression(arg));
CodeCoverCoverageCounter$5n7klajrsyp.statements[87]++;
      newcall.addChildToBack(arg);
CodeCoverCoverageCounter$5n7klajrsyp.statements[88]++;
    }
    return newcall;
  }

  public static Node name(String name) {
    return Node.newString(Token.NAME, name);
  }

  public static Node getprop(Node target, Node prop) {
    Preconditions.checkState(mayBeExpression(target));
CodeCoverCoverageCounter$5n7klajrsyp.statements[89]++;
    Preconditions.checkState(prop.isString());
CodeCoverCoverageCounter$5n7klajrsyp.statements[90]++;
    return new Node(Token.GETPROP, target, prop);
  }

  public static Node getelem(Node target, Node elem) {
    Preconditions.checkState(mayBeExpression(target));
CodeCoverCoverageCounter$5n7klajrsyp.statements[91]++;
    Preconditions.checkState(mayBeExpression(elem));
CodeCoverCoverageCounter$5n7klajrsyp.statements[92]++;
    return new Node(Token.GETELEM, target, elem);
  }

  public static Node assign(Node target, Node expr) {
    Preconditions.checkState(isAssignmentTarget(target));
CodeCoverCoverageCounter$5n7klajrsyp.statements[93]++;
    Preconditions.checkState(mayBeExpression(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[94]++;
    return new Node(Token.ASSIGN, target, expr);
  }

  public static Node hook(Node cond, Node trueval, Node falseval) {
    Preconditions.checkState(mayBeExpression(cond));
CodeCoverCoverageCounter$5n7klajrsyp.statements[95]++;
    Preconditions.checkState(mayBeExpression(trueval));
CodeCoverCoverageCounter$5n7klajrsyp.statements[96]++;
    Preconditions.checkState(mayBeExpression(falseval));
CodeCoverCoverageCounter$5n7klajrsyp.statements[97]++;
    return new Node(Token.HOOK, cond, trueval, falseval);
  }

  public static Node comma(Node expr1, Node expr2) {
    return binaryOp(Token.COMMA, expr1, expr2);
  }

  public static Node and(Node expr1, Node expr2) {
    return binaryOp(Token.AND, expr1, expr2);
  }

  public static Node or(Node expr1, Node expr2) {
    return binaryOp(Token.OR, expr1, expr2);
  }

  public static Node not(Node expr1) {
    return unaryOp(Token.NOT, expr1);
  }

  /**
   * "=="
   */
  public static Node eq(Node expr1, Node expr2) {
    return binaryOp(Token.EQ, expr1, expr2);
  }

  /**
   * "==="
   */
  public static Node sheq(Node expr1, Node expr2) {
    return binaryOp(Token.SHEQ, expr1, expr2);
  }

  public static Node voidNode(Node expr1) {
    return unaryOp(Token.VOID, expr1);
  }

  public static Node neg(Node expr1) {
    return unaryOp(Token.NEG, expr1);
  }

  public static Node pos(Node expr1) {
    return unaryOp(Token.POS, expr1);
  }

  public static Node cast(Node expr1) {
    return unaryOp(Token.CAST, expr1);
  }

  public static Node add(Node expr1, Node expr2) {
    return binaryOp(Token.ADD, expr1, expr2);
  }

  public static Node sub(Node expr1, Node expr2) {
    return binaryOp(Token.SUB, expr1, expr2);
  }

  // TODO(johnlenz): the rest of the ops

  // literals
  public static Node objectlit(Node ... propdefs) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[98]++;
    Node objectlit = new Node(Token.OBJECTLIT);
CodeCoverCoverageCounter$5n7klajrsyp.statements[99]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[28]++;


    for (Node propdef : propdefs) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[28]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[29]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[30]++;
}
      Preconditions.checkState(
          propdef.isStringKey() ||
          propdef.isGetterDef() || propdef.isSetterDef());
CodeCoverCoverageCounter$5n7klajrsyp.statements[100]++;
      Preconditions.checkState(propdef.hasOneChild());
CodeCoverCoverageCounter$5n7klajrsyp.statements[101]++;
      objectlit.addChildToBack(propdef);
CodeCoverCoverageCounter$5n7klajrsyp.statements[102]++;
    }
    return objectlit;
  }

  // TODO(johnlenz): quoted props

  public static Node propdef(Node string, Node value) {
    Preconditions.checkState(string.isStringKey());
CodeCoverCoverageCounter$5n7klajrsyp.statements[103]++;
    Preconditions.checkState(!string.hasChildren());
CodeCoverCoverageCounter$5n7klajrsyp.statements[104]++;
    Preconditions.checkState(mayBeExpression(value));
CodeCoverCoverageCounter$5n7klajrsyp.statements[105]++;
    string.addChildToFront(value);
CodeCoverCoverageCounter$5n7klajrsyp.statements[106]++;
    return string;
  }

  public static Node arraylit(Node ... exprs) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[107]++;
    Node arraylit = new Node(Token.ARRAYLIT);
CodeCoverCoverageCounter$5n7klajrsyp.statements[108]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$5n7klajrsyp.loops[31]++;


    for (Node expr : exprs) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[31]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[32]--;
  CodeCoverCoverageCounter$5n7klajrsyp.loops[33]++;
}
      Preconditions.checkState(mayBeExpressionOrEmpty(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[109]++;
      arraylit.addChildToBack(expr);
CodeCoverCoverageCounter$5n7klajrsyp.statements[110]++;
    }
    return arraylit;
  }

  public static Node regexp(Node expr) {
    Preconditions.checkState(expr.isString());
CodeCoverCoverageCounter$5n7klajrsyp.statements[111]++;
    return new Node(Token.REGEXP, expr);
  }

  public static Node regexp(Node expr, Node flags) {
    Preconditions.checkState(expr.isString());
CodeCoverCoverageCounter$5n7klajrsyp.statements[112]++;
    Preconditions.checkState(flags.isString());
CodeCoverCoverageCounter$5n7klajrsyp.statements[113]++;
    return new Node(Token.REGEXP, expr, flags);
  }

  public static Node string(String s) {
    return Node.newString(s);
  }

  public static Node stringKey(String s) {
    return Node.newString(Token.STRING_KEY, s);
  }

  public static Node number(double d) {
    return Node.newNumber(d);
  }

  public static Node thisNode() {
    return new Node(Token.THIS);
  }

  public static Node trueNode() {
    return new Node(Token.TRUE);
  }

  public static Node falseNode() {
    return new Node(Token.FALSE);
  }

  public static Node nullNode() {
    return new Node(Token.NULL);
  }

  // helper methods

  private static Node binaryOp(int token, Node expr1, Node expr2) {
    Preconditions.checkState(mayBeExpression(expr1));
CodeCoverCoverageCounter$5n7klajrsyp.statements[114]++;
    Preconditions.checkState(mayBeExpression(expr2));
CodeCoverCoverageCounter$5n7klajrsyp.statements[115]++;
    return new Node(token, expr1, expr2);
  }

  private static Node unaryOp(int token, Node expr) {
    Preconditions.checkState(mayBeExpression(expr));
CodeCoverCoverageCounter$5n7klajrsyp.statements[116]++;
    return new Node(token, expr);
  }

  private static boolean mayBeExpressionOrEmpty(Node n) {
    return n.isEmpty() || mayBeExpression(n);
  }

  private static boolean isAssignmentTarget(Node n) {
    return n.isName() || n.isGetProp() || n.isGetElem();
  }

  // NOTE: some nodes are neither statements nor expression nodes:
  //   SCRIPT, LABEL_NAME, PARAM_LIST, CASE, DEFAULT_CASE, CATCH
  //   GETTER_DEF, SETTER_DEF

  /**
   * It isn't possible to always determine if a detached node is a expression,
   * so make a best guess.
   */
  private static boolean mayBeStatementNoReturn(Node n) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[117]++;
    switch (n.getType()) {
      case Token.EMPTY:
CodeCoverCoverageCounter$5n7klajrsyp.branches[1]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$5n7klajrsyp.branches[2]++;
        // EMPTY and FUNCTION are used both in expression and statement
        // contexts
        return true;

      case Token.BLOCK:
CodeCoverCoverageCounter$5n7klajrsyp.branches[3]++;
      case Token.BREAK:
CodeCoverCoverageCounter$5n7klajrsyp.branches[4]++;
      case Token.CONST:
CodeCoverCoverageCounter$5n7klajrsyp.branches[5]++;
      case Token.CONTINUE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[6]++;
      case Token.DEBUGGER:
CodeCoverCoverageCounter$5n7klajrsyp.branches[7]++;
      case Token.DO:
CodeCoverCoverageCounter$5n7klajrsyp.branches[8]++;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[9]++;
      case Token.FOR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[10]++;
      case Token.IF:
CodeCoverCoverageCounter$5n7klajrsyp.branches[11]++;
      case Token.LABEL:
CodeCoverCoverageCounter$5n7klajrsyp.branches[12]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[13]++;
      case Token.THROW:
CodeCoverCoverageCounter$5n7klajrsyp.branches[14]++;
      case Token.TRY:
CodeCoverCoverageCounter$5n7klajrsyp.branches[15]++;
      case Token.VAR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[16]++;
      case Token.WHILE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[17]++;
      case Token.WITH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[18]++;
        return true;

      default:
CodeCoverCoverageCounter$5n7klajrsyp.branches[19]++;
        return false;
    }
  }

  /**
   * It isn't possible to always determine if a detached node is a expression,
   * so make a best guess.
   */
  private static boolean mayBeStatement(Node n) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[118]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((mayBeStatementNoReturn(n)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5n7klajrsyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5n7klajrsyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5n7klajrsyp.branches[20]++;
      return n.isReturn();

    } else {
  CodeCoverCoverageCounter$5n7klajrsyp.branches[21]++;}
    return true;
  }

  /**
   * It isn't possible to always determine if a detached node is a expression,
   * so make a best guess.
   */
  private static boolean mayBeExpression(Node n) {
CodeCoverCoverageCounter$5n7klajrsyp.statements[119]++;
    switch (n.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$5n7klajrsyp.branches[22]++;
        // FUNCTION is used both in expression and statement
        // contexts.
        return true;

      case Token.ADD:
CodeCoverCoverageCounter$5n7klajrsyp.branches[23]++;
      case Token.AND:
CodeCoverCoverageCounter$5n7klajrsyp.branches[24]++;
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[25]++;
      case Token.ASSIGN:
CodeCoverCoverageCounter$5n7klajrsyp.branches[26]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[27]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[28]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$5n7klajrsyp.branches[29]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[30]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[31]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[32]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$5n7klajrsyp.branches[33]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$5n7klajrsyp.branches[34]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$5n7klajrsyp.branches[35]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$5n7klajrsyp.branches[36]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$5n7klajrsyp.branches[37]++;
      case Token.BITAND:
CodeCoverCoverageCounter$5n7klajrsyp.branches[38]++;
      case Token.BITOR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[39]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[40]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[41]++;
      case Token.CALL:
CodeCoverCoverageCounter$5n7klajrsyp.branches[42]++;
      case Token.CAST:
CodeCoverCoverageCounter$5n7klajrsyp.branches[43]++;
      case Token.COMMA:
CodeCoverCoverageCounter$5n7klajrsyp.branches[44]++;
      case Token.DEC:
CodeCoverCoverageCounter$5n7klajrsyp.branches[45]++;
      case Token.DELPROP:
CodeCoverCoverageCounter$5n7klajrsyp.branches[46]++;
      case Token.DIV:
CodeCoverCoverageCounter$5n7klajrsyp.branches[47]++;
      case Token.EQ:
CodeCoverCoverageCounter$5n7klajrsyp.branches[48]++;
      case Token.FALSE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[49]++;
      case Token.GE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[50]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$5n7klajrsyp.branches[51]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$5n7klajrsyp.branches[52]++;
      case Token.GT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[53]++;
      case Token.HOOK:
CodeCoverCoverageCounter$5n7klajrsyp.branches[54]++;
      case Token.IN:
CodeCoverCoverageCounter$5n7klajrsyp.branches[55]++;
      case Token.INC:
CodeCoverCoverageCounter$5n7klajrsyp.branches[56]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$5n7klajrsyp.branches[57]++;
      case Token.LE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[58]++;
      case Token.LSH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[59]++;
      case Token.LT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[60]++;
      case Token.MOD:
CodeCoverCoverageCounter$5n7klajrsyp.branches[61]++;
      case Token.MUL:
CodeCoverCoverageCounter$5n7klajrsyp.branches[62]++;
      case Token.NAME:
CodeCoverCoverageCounter$5n7klajrsyp.branches[63]++;
      case Token.NE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[64]++;
      case Token.NEG:
CodeCoverCoverageCounter$5n7klajrsyp.branches[65]++;
      case Token.NEW:
CodeCoverCoverageCounter$5n7klajrsyp.branches[66]++;
      case Token.NOT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[67]++;
      case Token.NUMBER:
CodeCoverCoverageCounter$5n7klajrsyp.branches[68]++;
      case Token.NULL:
CodeCoverCoverageCounter$5n7klajrsyp.branches[69]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$5n7klajrsyp.branches[70]++;
      case Token.OR:
CodeCoverCoverageCounter$5n7klajrsyp.branches[71]++;
      case Token.POS:
CodeCoverCoverageCounter$5n7klajrsyp.branches[72]++;
      case Token.REGEXP:
CodeCoverCoverageCounter$5n7klajrsyp.branches[73]++;
      case Token.RSH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[74]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$5n7klajrsyp.branches[75]++;
      case Token.SHNE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[76]++;
      case Token.STRING:
CodeCoverCoverageCounter$5n7klajrsyp.branches[77]++;
      case Token.SUB:
CodeCoverCoverageCounter$5n7klajrsyp.branches[78]++;
      case Token.THIS:
CodeCoverCoverageCounter$5n7klajrsyp.branches[79]++;
      case Token.TYPEOF:
CodeCoverCoverageCounter$5n7klajrsyp.branches[80]++;
      case Token.TRUE:
CodeCoverCoverageCounter$5n7klajrsyp.branches[81]++;
      case Token.URSH:
CodeCoverCoverageCounter$5n7klajrsyp.branches[82]++;
      case Token.VOID:
CodeCoverCoverageCounter$5n7klajrsyp.branches[83]++;
        return true;

      default:
CodeCoverCoverageCounter$5n7klajrsyp.branches[84]++;
        return false;
    }
  }
}

class CodeCoverCoverageCounter$5n7klajrsyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5n7klajrsyp ());
  }
    public static long[] statements = new long[120];
    public static long[] branches = new long[85];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.IR.java";
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
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$5n7klajrsyp () {
    super("com.google.javascript.rhino.IR.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 119; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 84; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.IR.java");
      for (int i = 1; i <= 119; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 84; i++) {
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
      for (int i = 1; i <= 11; i++) {
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

