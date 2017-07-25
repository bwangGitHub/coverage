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
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Iterator;
import java.util.Set;

/**
 * Converts internal AST into JsonML tree.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class Writer {
  static {
    CodeCoverCoverageCounter$da731ziqxfmeposbl.ping();
  }


  /**
   * Creates JsonML tree based on a specified AST.
   * @param root AST node
   * @return root of a created JsonML tree
   */
  public JsonML processAst(Node root) {
    Preconditions.checkNotNull(root);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[1]++;
    Preconditions.checkArgument(
      root.getType() == Token.BLOCK || root.getType() == Token.SCRIPT);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[2]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[3]++;

    JsonML rootElement = new JsonML(TagType.BlockStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((root.getType() == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[1]++;
      processNode(root, rootElement);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[5]++;
      return rootElement.getChild(0);

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[2]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[6]++;
      Node child = root.getFirstChild();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
      while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[1]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[2]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[3]++;
}
        processNode(child, rootElement);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[8]++;
        child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[9]++;
      }
      // TODO(johnlenz): Add support for multiple scripts.
      return rootElement.getChild(0);
    }
  }

  /**
   * Dispatches an AST node to a function which converts it to JsonML.
   * @param node AST node to convert into JsonML element.
   * @param currentParent element to which newly created JsonML element will
   * be attached as a child
   */
  private void processNode(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[10]++;
    switch (node.getType()) {
      case Token.RETURN:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[3]++;
        processReturn(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[11]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[12]++;
        break;
      case Token.BITOR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[4]++;
        processBinaryExpr(node, currentParent, "|");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[13]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[14]++;
        break;
      case Token.BITXOR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[5]++;
        processBinaryExpr(node, currentParent, "^");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[15]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[16]++;
        break;
      case Token.BITAND:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[6]++;
        processBinaryExpr(node, currentParent, "&");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[17]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[18]++;
        break;
      case Token.EQ:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[7]++;
        processBinaryExpr(node, currentParent, "==");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[19]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[20]++;
        break;
      case Token.NE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[8]++;
        processBinaryExpr(node, currentParent, "!=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[21]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[22]++;
        break;
      case Token.LT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[9]++;
        processBinaryExpr(node, currentParent, "<");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[23]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[24]++;
        break;
      case Token.LE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[10]++;
        processBinaryExpr(node, currentParent, "<=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[25]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[26]++;
        break;
      case Token.GT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[11]++;
        processBinaryExpr(node, currentParent, ">");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[27]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[28]++;
        break;
      case Token.GE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[12]++;
        processBinaryExpr(node, currentParent, ">=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[29]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[30]++;
        break;
      case Token.LSH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[13]++;
        processBinaryExpr(node, currentParent, "<<");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[31]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[32]++;
        break;
      case Token.RSH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[14]++;
        processBinaryExpr(node, currentParent, ">>");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[33]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[34]++;
        break;
      case Token.URSH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[15]++;
        processBinaryExpr(node, currentParent, ">>>");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[35]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[36]++;
        break;
      case Token.ADD:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[16]++;
        processBinaryExpr(node, currentParent, "+");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[37]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[38]++;
        break;
      case Token.SUB:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[17]++;
        processBinaryExpr(node, currentParent, "-");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[39]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[40]++;
        break;
      case Token.MUL:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[18]++;
        processBinaryExpr(node, currentParent, "*");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[41]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[42]++;
        break;
      case Token.DIV:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[19]++;
        processBinaryExpr(node, currentParent, "/");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[43]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[44]++;
        break;
      case Token.MOD:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[20]++;
        processBinaryExpr(node, currentParent, "%");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[45]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[46]++;
        break;
      case Token.NOT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[21]++;
        processUnaryExpr(node, currentParent, "!");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[47]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[48]++;
        break;
      case Token.BITNOT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[22]++;
        processUnaryExpr(node, currentParent, "~");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[49]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[50]++;
        break;
      case Token.POS:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[23]++;
        processUnaryExpr(node, currentParent, "+");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[51]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[52]++;
        break;
      case Token.NEG:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[24]++;
        processUnaryExpr(node, currentParent, "-");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[53]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[54]++;
        break;
      case Token.NEW:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[25]++;
        processNew(node, currentParent, TagType.NewExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[55]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[56]++;
        break;
      case Token.DELPROP:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[26]++;
        processOneArgExpr(node, currentParent, TagType.DeleteExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[57]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[58]++;
        break;
      case Token.TYPEOF:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[27]++;
        processOneArgExpr(node, currentParent, TagType.TypeofExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[59]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[60]++;
        break;
      case Token.GETPROP:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[28]++;
        processMemberExpr(node, currentParent, ".");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[61]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[62]++;
        break;
      case Token.GETELEM:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[29]++;
        processMemberExpr(node, currentParent, "[]");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[63]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[64]++;
        break;
      case Token.CALL:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[30]++;
        processCall(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[65]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[66]++;
        break;
      case Token.NAME:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[31]++;
        processName(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[67]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[68]++;
        break;
      case Token.NUMBER:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[32]++;
      case Token.STRING:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[33]++;
      case Token.NULL:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[34]++;
      case Token.FALSE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[35]++;
      case Token.TRUE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[36]++;
        processLiteral(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[69]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[70]++;
        break;
      case Token.THIS:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[37]++;
        processThis(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[71]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[72]++;
        break;
      case Token.SHEQ:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[38]++;
        processBinaryExpr(node, currentParent, "===");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[73]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[74]++;
        break;
      case Token.SHNE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[39]++;
        processBinaryExpr(node, currentParent, "!==");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[75]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[76]++;
        break;
      case Token.REGEXP:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[40]++;
        processRegExp(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[77]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[78]++;
        break;
      case Token.THROW:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[41]++;
        processThrow(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[79]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[80]++;
        break;
      case Token.IN:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[42]++;
        processBinaryExpr(node, currentParent, "in");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[81]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[82]++;
        break;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[43]++;
        processBinaryExpr(node, currentParent, "instanceof");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[83]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[84]++;
        break;
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[44]++;
        processArrayLiteral(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[85]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[86]++;
        break;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[45]++;
        processObjectLiteral(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[87]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[88]++;
        break;
      case Token.TRY:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[46]++;
        processTry(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[89]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[90]++;
        break;
      case Token.COMMA:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[47]++;
        processBinaryExpr(node, currentParent, ",");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[91]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[92]++;
        break;
      case Token.ASSIGN:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[48]++;
        processAssignExpr(node, currentParent, "=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[93]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[94]++;
        break;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[49]++;
        processAssignExpr(node, currentParent, "|=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[95]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[96]++;
        break;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[50]++;
        processAssignExpr(node, currentParent, "^=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[97]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[98]++;
        break;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[51]++;
        processAssignExpr(node, currentParent, "&=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[99]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[100]++;
        break;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[52]++;
        processAssignExpr(node, currentParent, "<<=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[101]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[102]++;
        break;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[53]++;
        processAssignExpr(node, currentParent, ">>=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[103]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[104]++;
        break;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[54]++;
        processAssignExpr(node, currentParent, ">>>=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[105]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[106]++;
        break;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[55]++;
        processAssignExpr(node, currentParent, "+=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[107]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[108]++;
        break;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[56]++;
        processAssignExpr(node, currentParent, "-=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[109]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[110]++;
        break;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[57]++;
        processAssignExpr(node, currentParent, "*=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[111]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[112]++;
        break;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[58]++;
        processAssignExpr(node, currentParent, "/=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[113]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[114]++;
        break;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[59]++;
        processAssignExpr(node, currentParent, "%=");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[115]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[116]++;
        break;
      case Token.HOOK:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[60]++;
        processHook(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[117]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[118]++;
        break;
      case Token.OR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[61]++;
        processLogicalExpr(node, currentParent, "||");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[119]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[120]++;
        break;
      case Token.AND:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[62]++;
        processLogicalExpr(node, currentParent, "&&");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[121]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[122]++;
        break;
      case Token.INC:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[63]++;
        processIncrDecrExpr(node, currentParent, "++");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[123]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[124]++;
        break;
      case Token.DEC:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[64]++;
        processIncrDecrExpr(node, currentParent, "--");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[125]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[126]++;
        break;
      case Token.FUNCTION:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[65]++;
        processFunction(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[127]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[128]++;
        break;
      case Token.IF:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[66]++;
        processIf(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[129]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[130]++;
        break;
      case Token.SWITCH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[67]++;
        processSwitch(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[131]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[132]++;
        break;
      case Token.CASE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[68]++;
        processCase(node, currentParent, TagType.Case);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[133]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[134]++;
        break;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[69]++;
        processCase(node, currentParent, TagType.DefaultCase);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[135]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[136]++;
        break;
      case Token.WHILE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[70]++;
        processLoop(node, currentParent, TagType.WhileStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[137]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[138]++;
        break;
      case Token.DO:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[71]++;
        processLoop(node, currentParent, TagType.DoWhileStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[139]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[140]++;
        break;
      case Token.FOR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[72]++;
        processForLoop(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[141]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[142]++;
        break;
      case Token.BREAK:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[73]++;
        processJmp(node, currentParent, TagType.BreakStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[143]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[144]++;
        break;
      case Token.CONTINUE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[74]++;
        processJmp(node, currentParent, TagType.ContinueStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[145]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[146]++;
        break;
      case Token.VAR:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[75]++;
        processVar(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[147]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[148]++;
        break;
      case Token.WITH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[76]++;
        processWith(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[149]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[150]++;
        break;
      case Token.CATCH:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[77]++;
        processCatch(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[151]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[152]++;
        break;
      case Token.VOID:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[78]++;
        processUnaryExpr(node, currentParent, "void");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[153]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[154]++;
        break;
      case Token.EMPTY:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[79]++;
        processEmpty(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[155]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[156]++;
        break;
      case Token.BLOCK:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[80]++;
        processBlock(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[157]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[158]++;
        break;
      case Token.LABEL:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[81]++;
        processLabel(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[159]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[160]++;
        break;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[82]++;
        processExprResult(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[161]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[162]++;
        break;
      case Token.SCRIPT:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[83]++;
        processScript(node, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[163]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[164]++;
        break; default : CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[84]++;
    }
  }

  private void processAssignExpr(Node node, JsonML currentParent, String op) {
    processTwoArgExpr(node, currentParent, TagType.AssignExpr, op);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[165]++;
  }

  private void processArrayLiteral(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[166]++;
    JsonML element = new JsonML(TagType.ArrayExpr);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[167]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[168]++;
    Iterator<Node> it = node.children().iterator();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[169]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
    while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[4]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[5]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[6]++;
}
      processNode(it.next(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[170]++;
    }
  }

  private void processBinaryExpr(Node node, JsonML currentParent,
      String op) {
    processTwoArgExpr(node, currentParent, TagType.BinaryExpr, op);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[171]++;
  }

  private void processBlock(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[172]++;
    JsonML element = new JsonML(TagType.BlockStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[173]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((currentParent != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[85]++;
      currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[174]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[86]++;}

    processDirectives(node, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[175]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[176]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[7]++;



    for (Node child : node.children()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[7]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[8]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[9]++;
}
      processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[177]++;
    }
  }

  private void processCall(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[178]++;
    Iterator<Node> it = node.children().iterator();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[179]++;
    Node child = it.next();
    JsonML element;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[180]++;
    // the first child may indicate that it is invoke expression
    // or a standard function call
    switch (child.getType()) {
      case Token.GETPROP:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[87]++;         // a.x()
      case Token.GETELEM:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[88]++;         // a[x]()
        // we have to process this node here and cannot call processNode(child)
        // other children of CALL represent arguments, so we need to have
        // access to them while processing InvokeExpr
        element = new JsonML(TagType.InvokeExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[181]++;
        element.setAttribute(
            TagAttr.OP,
            child.getType() == Token.GETPROP ? "." : "[]");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[182]++;
        currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[183]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[184]++;

        // there should be exactly two children
        Node grandchild = child.getFirstChild();
        processNode(grandchild, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[185]++;
        processNode(grandchild.getNext(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[186]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[187]++;


        break;
      case Token.NAME:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[89]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[188]++;
int CodeCoverConditionCoverageHelper_C5;
        // Caja treats calls to eval in a special way
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((child.getString().equals("eval")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[90]++;
          element = new JsonML(TagType.EvalExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[189]++;

        } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[91]++;
          // element representing function name is created
          element = new JsonML(TagType.IdExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[190]++;
          element.setAttribute(TagAttr.NAME, child.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[191]++;
          // element representing function is created
          element = new JsonML(TagType.CallExpr, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[192]++;
        }
        currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[193]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[194]++;
        break;
      default:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[92]++;
       // it addresses all cases where the first argument evaluates to
       // another expression
       element = new JsonML(TagType.CallExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[195]++;
       currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[196]++;
       processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[197]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[198]++;
       break;
    }
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[199]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[10]++;


int CodeCoverConditionCoverageHelper_C6;

    // there may be arguments applied
    while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[10]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[11]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[12]++;
}
      processNode(it.next(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[200]++;
    }
  }

  private void processCase(Node node, JsonML currentParent,
      TagType type) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[201]++;
    JsonML element = new JsonML(type);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[202]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[203]++;

    Node child = node.getFirstChild();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[204]++;
int CodeCoverConditionCoverageHelper_C7;
    // for case, the first child represents its argument
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type == TagType.Case) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[93]++;
      processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[205]++;
      child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[206]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[94]++;}

    // it should be a BLOCK which is required by rhino for compatibility
    // the writer skips the node and move on to its children
    Preconditions.checkNotNull(child);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[207]++;
    Preconditions.checkState(child.getType() == Token.BLOCK);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[208]++;
    child = child.getFirstChild();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[209]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[210]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[13]++;


int CodeCoverConditionCoverageHelper_C8;
    while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[13]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[14]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[15]++;
}
      processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[211]++;
      child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[212]++;
    }
  }

  private void processCatch(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[213]++;
    JsonML element = new JsonML(TagType.CatchClause);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[214]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[215]++;

    // the first child represents exception's name
    Node child = node.getFirstChild();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[216]++;
    JsonML patt = new JsonML(TagType.IdPatt);
    patt.setAttribute(TagAttr.NAME, child.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[217]++;
    element.appendChild(patt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[218]++;

    // the second child represents content
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[219]++;
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[220]++;
  }

  private void processEmpty(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[221]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((currentParent.getType() == TagType.ArrayExpr) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[95]++;
      // Empty expression are only found in Array literals
      currentParent.appendChild(new JsonML(TagType.Empty));
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[222]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[96]++;
      currentParent.appendChild(new JsonML(TagType.EmptyStmt));
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[223]++;
    }
  }

  private void processExprResult(Node node, JsonML currentParent) {
    // this not interesting to JsonML, so we just need to skip it
    processNode(node.getFirstChild(), currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[224]++;
  }

  private void processForLoop(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[225]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(node)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[97]++;
      processLoop(node, currentParent, TagType.ForInStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[226]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[98]++;
      processLoop(node, currentParent, TagType.ForStmt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[227]++;
    }
  }

  private void processFunction(Node node, JsonML currentParent) {
    JsonML element;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[228]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(node)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[99]++;
      element = new JsonML(TagType.FunctionDecl);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[229]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[100]++;  // isFunctionExpresion == true
      element = new JsonML(TagType.FunctionExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[230]++;
    }
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[231]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[232]++;

    // the first child represents function's name
    Node child = node.getFirstChild();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[233]++;
    String name = child.getString();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[234]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((name.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[101]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[235]++;
      JsonML nameElement = new JsonML(TagType.IdPatt);
      nameElement.setAttribute(TagAttr.NAME, name);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[236]++;
      element.appendChild(nameElement);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[237]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[102]++;
      element.appendChild(new JsonML(TagType.Empty));
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[238]++;
    }

    // the second child is a wrapper for formal parameters
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[239]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[240]++;
    JsonML params = new JsonML(TagType.ParamDecl);
    element.appendChild(params);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[241]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[242]++;
    Iterator<Node> it = child.children().iterator();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[243]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[16]++;


int CodeCoverConditionCoverageHelper_C13;
    while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[16]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[17]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[18]++;
}
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[244]++;
      JsonML param = new JsonML(TagType.IdPatt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[245]++;
      Node nameNode = it.next();
      param.setAttribute(TagAttr.NAME, nameNode.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[246]++;
      params.appendChild(param);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[247]++;
    }

    // the third child represents function's body
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[248]++;

    // it can contain some directives
    processDirectives(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[249]++;

    it = child.children().iterator();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[250]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[251]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
    while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[19]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[20]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[21]++;
}
      processNode(it.next(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[252]++;
    }
  }

  private void processHook(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[253]++;
    JsonML element = new JsonML(TagType.ConditionalExpr);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[254]++;
    processChildren(node, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[255]++;
  }

  private void processIf(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[256]++;
    JsonML element = new JsonML(TagType.IfStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[257]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[258]++;
    Iterator<Node> it = node.children().iterator();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[259]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[22]++;


int CodeCoverConditionCoverageHelper_C15;

    // there should be at least one child
    while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[22]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[23]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[24]++;
}
      processNode(it.next(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[260]++;
    }
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[261]++;
    // append EmptyStmt for each missing part
    int childCount = node.getChildCount();
    Preconditions.checkState(childCount >= 2);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[262]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[263]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((childCount < 3) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[103]++; // no "else" part for sure
      element.appendChild(new JsonML(TagType.EmptyStmt));
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[264]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[104]++;}
  }

  private void processIncrDecrExpr(Node node, JsonML currentParent,
      String op) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[265]++;
    JsonML element = new JsonML(TagType.CountExpr);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[266]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[267]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((op.equals("++")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[105]++;
      element.setAttribute(TagAttr.OP, "++");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[268]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[106]++; // op.equals("--")
      element.setAttribute(TagAttr.OP, "--");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[269]++;
    }
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[270]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.INCRDECR_PROP) == 1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[107]++;
      element.setAttribute(TagAttr.IS_PREFIX, false);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[271]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[108]++; // INCRDECR_PROP == 0
      element.setAttribute(TagAttr.IS_PREFIX, true);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[272]++;
    }

    // there is exactly one child
    processNode(node.getFirstChild(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[273]++;
  }

  private void processJmp(Node node, JsonML currentParent,
      TagType type) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[274]++;
    JsonML element = new JsonML(type);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[275]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[276]++;

    // optional child may point to a label
    Node child = node.getFirstChild();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[277]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[109]++;
      element.setAttribute(TagAttr.LABEL, child.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[278]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[110]++;}
  }

  private void processLabel(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[279]++;
    JsonML element = new JsonML(TagType.LabelledStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[280]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[281]++;

    // the first child represents label's name
    Node child = node.getFirstChild();
    element.setAttribute(TagAttr.LABEL, child.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[282]++;

    // the second child represents labeled content
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[283]++;
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[284]++;
  }

  private void processLiteral(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[285]++;
    JsonML element = new JsonML(TagType.LiteralExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[286]++;
    switch (node.getType()) {
      case Token.NUMBER:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[111]++;
        element.setAttribute(TagAttr.TYPE, "number");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[287]++;
        element.setAttribute(TagAttr.VALUE, node.getDouble());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[288]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[289]++;
        break;
      case Token.STRING:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[112]++;
        element.setAttribute(TagAttr.TYPE, "string");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[290]++;
        element.setAttribute(TagAttr.VALUE, node.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[291]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[292]++;
        break;
      case Token.NULL:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[113]++;
        element.setAttribute(TagAttr.TYPE, "null");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[293]++;
        element.setAttribute(TagAttr.VALUE, null);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[294]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[295]++;
        break;
      case Token.TRUE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[114]++;
        element.setAttribute(TagAttr.TYPE, "boolean");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[296]++;
        element.setAttribute(TagAttr.VALUE, true);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[297]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[298]++;
        break;
      case Token.FALSE:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[115]++;
        element.setAttribute(TagAttr.TYPE, "boolean");
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[299]++;
        element.setAttribute(TagAttr.VALUE, false);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[300]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[301]++;
        break;
      default:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[116]++;
        throw new IllegalArgumentException("Illegal type of node.");
    }
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[302]++;
  }

  private void processLogicalExpr(Node node, JsonML currentParent,
      String op) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[303]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((op.equals("||")) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[117]++;
      processTwoArgExpr(node, currentParent, TagType.LogicalOrExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[304]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[118]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[305]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((op.endsWith("&&")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[119]++;
      processTwoArgExpr(node, currentParent, TagType.LogicalAndExpr);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[306]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[120]++;
      throw new IllegalArgumentException("Unsupported value of op argument.");
    }
}
  }

  private void processLoop(Node node, JsonML currentParent,
      TagType type) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[307]++;
    JsonML element = new JsonML(type);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[308]++;
    processChildren(node, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[309]++;
  }

  private void processMemberExpr(Node node, JsonML currentParent,
      String op) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[310]++;
    JsonML element = new JsonML(TagType.MemberExpr);
    element.setAttribute(TagAttr.OP, op);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[311]++;
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[312]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[313]++;

    // there should be exactly two children
    Node child = node.getFirstChild();
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[314]++;
    processNode(child.getNext(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[315]++;
  }

  private void processName(Node node, JsonML currentParent) {
    Preconditions.checkState(!node.hasChildren());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[316]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[317]++;

    JsonML element = new JsonML(TagType.IdExpr);
    element.setAttribute(TagAttr.NAME, node.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[318]++;
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[319]++;
  }

  private void processNew(Node node, JsonML currentParent, TagType type) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[320]++;
    JsonML element = new JsonML(type);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[321]++;

    processChildren(node, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[322]++;
  }

  private void processObjectLiteral(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[323]++;
    JsonML element = new JsonML(TagType.ObjectExpr);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[324]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[325]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[25]++;


    for (Node key : node.children()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[25]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[26]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[27]++;
}
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[326]++;
      Node value = key.getFirstChild();
      JsonML item;
      Object name;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[327]++;
      switch (key.getType()) {
        case Token.STRING_KEY:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[121]++;
          item = new JsonML(TagType.DataProp);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[328]++;
          name = key.getString();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[329]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[330]++;
          break;
        case Token.GETTER_DEF:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[122]++;
          item = new JsonML(TagType.GetterProp);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[331]++;
          name = key.getString();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[332]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[333]++;
          break;
        case Token.SETTER_DEF:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[123]++;
          item = new JsonML(TagType.SetterProp);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[334]++;
          name = key.getString();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[335]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[336]++;
          break;
        default:
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[124]++;
          throw new IllegalArgumentException("Illegal type of node.");
      }
      item.setAttribute(TagAttr.NAME, name);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[337]++;
      processNode(value, item);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[338]++;
      element.appendChild(item);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[339]++;
    }
  }

  private void processRegExp(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[340]++;
    JsonML element = new JsonML(TagType.RegExpExpr);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[341]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[342]++;

    // first child represents expression's body
    Node child = node.getFirstChild();
    element.setAttribute(TagAttr.BODY, child.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[343]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[344]++;

    // optional second child represents flags
    String flags = "";
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[345]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[346]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[125]++;
      flags = child.getString();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[347]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[126]++;}
    element.setAttribute(TagAttr.FLAGS, flags);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[348]++;
  }

  private void processSwitch(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[349]++;
    JsonML element = new JsonML(TagType.SwitchStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[350]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[351]++;

    // the first child represents expression
    Node child = node.getFirstChild();
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[352]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[353]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[28]++;


int CodeCoverConditionCoverageHelper_C23;

    // next children represent particular cases
    for (Node c = child.getNext();(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[28]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[29]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[30]++;
}
      processNode(c, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[354]++;
    }
  }

  private void processThis(Node node, JsonML currentParent) {
    currentParent.appendChild(new JsonML(TagType.ThisExpr));
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[355]++;
  }

  private void processThrow(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[356]++;
    JsonML element = new JsonML(TagType.ThrowStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[357]++;

    // there is exactly one child
    processNode(node.getFirstChild(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[358]++;
  }

  private void processTry(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[359]++;
    JsonML element = new JsonML(TagType.TryStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[360]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[361]++;

    // first child represents actual try block
    Node child = node.getFirstChild();
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[362]++;

    // second child (precisely: child of that child) represents catch block
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[363]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[364]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((child.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[127]++;
      processNode(child.getFirstChild(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[365]++;

    } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[128]++;  // catch block is not present
      element.appendChild(new JsonML(TagType.Empty));
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[366]++;
    }

    //optional third child represents finally block
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[367]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[368]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[129]++;
      processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[369]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[130]++;}
  }

  private void processTwoArgExpr(Node node, JsonML currentParent,
      TagType type) {
    processTwoArgExpr(node, currentParent, type, null);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[370]++;
  }

  private void processTwoArgExpr(Node node, JsonML currentParent,
      TagType type, String op) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[371]++;
    JsonML element = new JsonML(type);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[372]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((op != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[131]++;
      element.setAttribute(TagAttr.OP, op);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[373]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[132]++;}
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[374]++;

    Preconditions.checkState(node.getChildCount() == 2);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[375]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[376]++;
    Node child = node.getFirstChild();
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[377]++;
    processNode(child.getNext(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[378]++;
  }

  /**
   * Process nodes which JsonML represents by UnaryExpr.
   * @param node node to process
   * @param op operation for this unary expression - depends on node type
   */
  private void processUnaryExpr(Node node, JsonML currentParent,
      String op) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[379]++;
    JsonML element = new JsonML(TagType.UnaryExpr);
    element.setAttribute(TagAttr.OP, op);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[380]++;
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[381]++;

    processNode(node.getFirstChild(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[382]++;
  }

  private void processVar(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[383]++;
    JsonML element = new JsonML(TagType.VarDecl);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[384]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[385]++;

    //there may be many actual declarations
    Iterator<Node> it = node.children().iterator();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[386]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[31]++;


int CodeCoverConditionCoverageHelper_C27;
    while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[31]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[32]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[33]++;
}
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[387]++;
      Node child = it.next();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[388]++;  // this node represents var's id
                               // its own child represents initial value

      JsonML id = new JsonML(TagType.IdPatt);
      id.setAttribute(TagAttr.NAME, child.getString());
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[389]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[390]++;
int CodeCoverConditionCoverageHelper_C28;

      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((child.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[133]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[391]++;
        JsonML patt = new JsonML(TagType.InitPatt);
        element.appendChild(patt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[392]++;
        patt.appendChild(id);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[393]++;
        processNode(child.getFirstChild(), patt);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[394]++;

      } else {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[134]++;
        element.appendChild(id);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[395]++;
      }
    }
  }

  private void processReturn(Node currentNode, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[396]++;
    JsonML element = new JsonML(TagType.ReturnStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[397]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[398]++;
int CodeCoverConditionCoverageHelper_C29;

    // there is exactly one child if return statement is not empty
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((currentNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[135]++;
      processNode(currentNode.getFirstChild(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[399]++;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[136]++;}
  }

  private void processScript(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[400]++;
    JsonML element = new JsonML(TagType.Program);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[401]++;

    processDirectives(node, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[402]++;

    processChildren(node, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[403]++;
  }

  private void processWith(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[404]++;
    JsonML element = new JsonML(TagType.WithStmt);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[405]++;
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[406]++;

    // the first child represent object
    Node child = node.getFirstChild();
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[407]++;

    // the second one represents content
    child = child.getNext();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[408]++;
    processNode(child, element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[409]++;
  }

  private void processChildren(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[410]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[34]++;


    for (Node child : node.children()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[34]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[35]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[36]++;
}
      processNode(child, currentParent);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[411]++;
    }
  }

  private void processDirectives(Node node, JsonML currentParent) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[412]++;
    Set<String> directives = node.getDirectives();
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[413]++;
int CodeCoverConditionCoverageHelper_C30;

    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((directives == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$da731ziqxfmeposbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[137]++;
      return;

    } else {
  CodeCoverCoverageCounter$da731ziqxfmeposbl.branches[138]++;}
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[414]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[37]++;



    for (String directive : directives) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[37]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[38]--;
  CodeCoverCoverageCounter$da731ziqxfmeposbl.loops[39]++;
}
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[415]++;
      JsonML element = new JsonML(TagType.PrologueDecl);
      element.setAttribute(TagAttr.DIRECTIVE, directive);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[416]++;
      element.setAttribute(TagAttr.VALUE, directive);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[417]++;
      currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[418]++;
    }
  }

  private void processOneArgExpr(Node node, JsonML currentParent,
      TagType type) {
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[419]++;
    JsonML element = new JsonML(type);
    currentParent.appendChild(element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[420]++;

    // there is only one child node
    processNode(node.getFirstChild(), element);
CodeCoverCoverageCounter$da731ziqxfmeposbl.statements[421]++;
  }
}

class CodeCoverCoverageCounter$da731ziqxfmeposbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$da731ziqxfmeposbl ());
  }
    public static long[] statements = new long[422];
    public static long[] branches = new long[139];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[31];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.Writer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 30; i++) {
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

  public CodeCoverCoverageCounter$da731ziqxfmeposbl () {
    super("com.google.javascript.jscomp.jsonml.Writer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 421; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 138; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 30; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.Writer.java");
      for (int i = 1; i <= 421; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 138; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 30; i++) {
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

