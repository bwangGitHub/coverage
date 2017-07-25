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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * JsonMLUtil contains utilities for the JsonML object.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class JsonMLUtil {
  static {
    CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.ping();
  }


  /**
   * Checks if the specified JsonML element represents an expression.
   */
  public static boolean isExpression(JsonML element) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[1]++;
    switch (element.getType()) {
      case ArrayExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[1]++;
      case AssignExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[2]++;
      case BinaryExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[3]++;
      case CallExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[4]++;
      case ConditionalExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[5]++;
      case CountExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[6]++;
      case DeleteExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[7]++;
      case EvalExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[8]++;
      case FunctionExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[9]++;
      case IdExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[10]++;
      case InvokeExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[11]++;
      case LiteralExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[12]++;
      case LogicalAndExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[13]++;
      case LogicalOrExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[14]++;
      case MemberExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[15]++;
      case NewExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[16]++;
      case ObjectExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[17]++;
      case RegExpExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[18]++;
      case ThisExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[19]++;
      case TypeofExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[20]++;
      case UnaryExpr:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[21]++;
        return true;
      default:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[22]++;
        return false;
    }
  }

  /**
   * Parses JSON string which contains serialized JsonML content.
   * @param jsonml string representation of JsonML
   * @return root element of a JsonML tree
   */
  public static JsonML parseString(String jsonml) throws Exception {
    return parseElement(new JSONArray(jsonml));
  }

  private static JsonML parseElement(JSONArray element)
      throws Exception {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[2]++;
    JsonML jsonMLElement = new JsonML(TagType.valueOf(element.getString(0)));
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[3]++;

    // set attributes for the JsonML element
    JSONObject attrs = element.getJSONObject(1);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[4]++;
    Iterator<?> it = attrs.keys();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[1]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[2]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[3]++;
}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[6]++;
      String key = (String) it.next();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[7]++;
      Object value = attrs.get(key);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[8]++;
      TagAttr tag = TagAttr.get(key);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

      // an unsupported attribute
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((tag == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[23]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[10]++;
        continue;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[24]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;

      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[25]++;
        value = ((Number) value).doubleValue();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[12]++;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[26]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[13]++;

      switch (tag) {
        case NAME:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[27]++;
        case BODY:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[28]++;
        case FLAGS:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[29]++;
        case OP:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[30]++;
        case TYPE:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[31]++;
        case IS_PREFIX:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[32]++;
        case LABEL:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[33]++;
          jsonMLElement.setAttribute(tag, value);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[14]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[15]++;
          break;
        case VALUE:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[34]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
          // we do not want to deal with JSONObject.NULL
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value.equals(null)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[35]++;
            value = null;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[17]++;

          } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[36]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;

          // we want all numbers to be stored as double values
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[37]++;
            jsonMLElement.setAttribute(tag, ((Number) value).doubleValue());
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[19]++;

          } else {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[38]++;
            jsonMLElement.setAttribute(tag, value);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[20]++;
          }
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[21]++;
          break;
        default:
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[39]++;
      }
    }
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;

    // recursively set children for the JsonML element
    for (int i = 2;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < element.length()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[4]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[5]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[6]++;
}
      jsonMLElement.appendChild(parseElement(element.getJSONArray(i)));
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[23]++;
    }

    return jsonMLElement;
  }

  /**
   * Compares two specified JsonML trees.
   *
   * Two JsonML nodes are considered to be equal when the following conditions
   * are met:
   *
   * - have the same type
   * - have the same attributes from the list of attributes to compare
   * - have the same number of children
   * - nodes in each pair of corresponding children are equal
   *
   * Two JsonML trees are equal, if their roots are equal.
   *
   * When two nodes are compared, only the following attributes are taken
   * into account:
   * TagAttr.BODY, TagAttr.FLAGS, TagAttr.IS_PREFIX, TagAttr.LABEL,
   * TagAttr.NAME, TagAttr.OP, TagAttr.TYPE, TagAttr.VALUE
   * Generally, the comparator does not care about debugging attributes.
   *
   * @return
   * Returns string describing the inequality in the following format:
   *
   * The trees are not equal:
   *
   * Tree1:
   * -- string representation of Tree1
   *
   * Tree2:
   * -- string representation of Tree2
   *
   * Subtree1:
   * -- string representation of the subtree of the Tree1 which is not
   * -- equal to the corresponding subtree of the Tree2
   *
   * Subtree2:
   * -- see Subtree1
   *
   * If the trees are equal, null is returned.
   */
  public static String compare(JsonML tree1, JsonML tree2) {
    return (new JsonMLComparator(tree1, tree2)).compare();
  }

  /**
   * Returns true if the trees are equal, false otherwise.
   */
  static boolean compareSilent(JsonML tree1, JsonML tree2) {
    return (new JsonMLComparator(tree1, tree2)).compareSilent();
  }

  /**
   * Helper class which actually compares two given JsonML trees.
   *
   */
  private static class JsonMLComparator {
    private static final TagAttr[] ATTRS_TO_COMPARE = {
      TagAttr.BODY, TagAttr.FLAGS, TagAttr.IS_PREFIX, TagAttr.LABEL,
      TagAttr.NAME, TagAttr.OP, TagAttr.TYPE, TagAttr.VALUE
    };
    private JsonML treeA;
    private JsonML treeB;
    private JsonML mismatchA;
    private JsonML mismatchB;

    JsonMLComparator(JsonML treeA, JsonML treeB) {
      this.treeA = treeA;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[24]++;
      this.treeB = treeB;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[25]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((compareElements(treeA, treeB)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[40]++;
        mismatchA = null;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[27]++;
        mismatchB = null;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[28]++;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[41]++;}
    }

    private boolean setMismatch(JsonML a, JsonML b) {
      mismatchA = a;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[29]++;
      mismatchB = b;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[30]++;
      return false;
    }

    /**
     * Check if two elements are equal (including comparing their children).
     */
    private boolean compareElements(JsonML a, JsonML b) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
      // the elements are considered to be equal if they are both null
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((a == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[42]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[32]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((a == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((b == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[44]++;
          return true;

        } else {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[45]++;
          return setMismatch(a, b);
        }

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[43]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;

      // the elements themselves have to be equivalent
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((areEquivalent(a, b)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[46]++;
        return setMismatch(a, b);

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[47]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[34]++;
int CodeCoverConditionCoverageHelper_C11;

      // they both have to have the same number of children
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((a.childrenSize() != b.childrenSize()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[48]++;
        return setMismatch(a, b);

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[49]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[35]++;

      // all the children has to be the same
      Iterator<JsonML> itA = a.getChildren().listIterator();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[36]++;
      Iterator<JsonML> itB = b.getChildren().listIterator();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[37]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;
      while ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((itA.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[7]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[8]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[9]++;
}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[38]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((compareElements(itA.next(), itB.next())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[50]++;
          return false;

        } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[51]++;}
      }

      return true;
    }

    /**
     * Checks if two elements are semantically the same.
     */
    private boolean areEquivalent(JsonML a, JsonML b) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
      // both elements must have the same type
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((a.getType() != b.getType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[52]++;
        return false;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[53]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[40]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[10]++;



      for (TagAttr attr : ATTRS_TO_COMPARE) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[10]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[11]--;
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.loops[12]++;
}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[41]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((compareAttribute(attr, a, b)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[54]++;
          return false;

        } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[55]++;}
      }
      return true;
    }

    private boolean compareAttribute(TagAttr attr, JsonML a, JsonML b) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[42]++;
      Object valueA = a.getAttributes().get(attr);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[43]++;
      Object valueB = b.getAttributes().get(attr);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[44]++;
int CodeCoverConditionCoverageHelper_C16;

      // none of the elements have the attribute
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((valueA == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((valueB == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[56]++;
        return true;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[57]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;

      // only one of the elements has the attribute
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((valueA == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((valueB == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[58]++;
        return false;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[59]++;}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[46]++;
int CodeCoverConditionCoverageHelper_C18;

      // check if corresponding values are equal
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((valueA.equals(valueB)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[60]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[47]++;
        // there is still a chance that both attributes are numbers, but are
        // represented by different classes

        Double doubleA = null, doubleB = null;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[48]++;
int CodeCoverConditionCoverageHelper_C19;

        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((valueA instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[62]++;
          doubleA = ((Number) valueA).doubleValue();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[49]++;

        } else {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[63]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[50]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((valueA instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[64]++;
          doubleA = Double.valueOf((String) valueA);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[51]++;

        } else {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[65]++;
          return false;
        }
}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[52]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((valueB instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[66]++;
          doubleB = ((Number) valueB).doubleValue();
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[53]++;

        } else {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[67]++;
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[54]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((valueB instanceof String) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[68]++;
          doubleB = Double.valueOf((String) valueB);
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[55]++;

        } else {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[69]++;
          return false;
        }
}
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[56]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((doubleA.equals(doubleB)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[70]++;
          return false;

        } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[71]++;}

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[61]++;}

      return true;
    }

    private boolean compareSilent() {
      return mismatchA == null && mismatchB == null;
    }

    private String compare() {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.statements[57]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((compareSilent()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[72]++;
        return null;

      } else {
  CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d.branches[73]++;}
      return "The trees are not equal: " +
          "\n\nTree1:\n " + treeA.toStringTree() +
          "\n\nTree2:\n " + treeB.toStringTree() +
          "\n\nSubtree1:\n " + mismatchA.toStringTree() +
          "\n\nSubtree2:\n " + mismatchB.toStringTree();

    }
  }
}

class CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[74];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.JsonMLUtil.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,2,2,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 24; i++) {
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

  public CodeCoverCoverageCounter$mbar3x0ot1be4u22p86re5d () {
    super("com.google.javascript.jscomp.jsonml.JsonMLUtil.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 73; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.JsonMLUtil.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 73; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
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

