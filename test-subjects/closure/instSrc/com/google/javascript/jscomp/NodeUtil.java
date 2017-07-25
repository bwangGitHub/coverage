/*
 * Copyright 2004 The Closure Compiler Authors.
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
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.StaticSourceFile;
import com.google.javascript.rhino.jstype.TernaryValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * NodeUtil contains generally useful AST utilities.
 *
 */
public final class NodeUtil {
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.ping();
  }


  static final long MAX_POSITIVE_INTEGER_NUMBER = (long) Math.pow(2, 53);
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[1]++;
  }

  static final String JSC_PROPERTY_NAME_FN = "JSCompiler_renameProperty";
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[2]++;
  }

  static final char LARGEST_BASIC_LATIN = 0x7f;
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[3]++;
  }

  /** the set of builtin constructors that don't have side effects. */
  private static final Set<String> CONSTRUCTORS_WITHOUT_SIDE_EFFECTS =
      new HashSet<String>(Arrays.asList(
        "Array",
        "Date",
        "Error",
        "Object",
        "RegExp",
        "XMLHttpRequest"));
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[4]++;
  }

  // Utility class; do not instantiate.
  private NodeUtil() {}

  /**
   * Gets the boolean value of a node that represents a expression. This method
   * effectively emulates the <code>Boolean()</code> JavaScript cast function.
   * Note: unlike getBooleanValue this function does not return UNKNOWN
   * for expressions with side-effects.
   */
  static TernaryValue getImpureBooleanValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[5]++;
    switch (n.getType()) {
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[1]++;
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[2]++;
        // For ASSIGN and COMMA the value is the value of the RHS.
        return getImpureBooleanValue(n.getLastChild());
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[3]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[6]++;
        TernaryValue value = getImpureBooleanValue(n.getLastChild());
        return value.not();
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[4]++; {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[7]++;
        TernaryValue lhs = getImpureBooleanValue(n.getFirstChild());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[8]++;
        TernaryValue rhs = getImpureBooleanValue(n.getLastChild());
        return lhs.and(rhs);
      }
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[5]++;  {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[9]++;
        TernaryValue lhs = getImpureBooleanValue(n.getFirstChild());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[10]++;
        TernaryValue rhs = getImpureBooleanValue(n.getLastChild());
        return lhs.or(rhs);
      }
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[6]++;  {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[11]++;
        TernaryValue trueValue = getImpureBooleanValue(
            n.getFirstChild().getNext());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[12]++;
        TernaryValue falseValue = getImpureBooleanValue(n.getLastChild());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((trueValue.equals(falseValue)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[7]++;
          return trueValue;

        } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[8]++;
          return TernaryValue.UNKNOWN;
        }
      }
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[9]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[10]++;
        // ignoring side-effects
        return TernaryValue.TRUE;

      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[11]++;
        return TernaryValue.FALSE;

      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[12]++;
        return getPureBooleanValue(n);
    }
  }

  /**
   * Gets the boolean value of a node that represents a literal. This method
   * effectively emulates the <code>Boolean()</code> JavaScript cast function
   * except it return UNKNOWN for known values with side-effects, use
   * getImpureBooleanValue if you don't care about side-effects.
   */
  static TernaryValue getPureBooleanValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[14]++;
    switch (n.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[13]++;
        return TernaryValue.forBoolean(n.getString().length() > 0);

      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[14]++;
        return TernaryValue.forBoolean(n.getDouble() != 0);

      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[15]++;
        return getPureBooleanValue(n.getLastChild()).not();

      case Token.NULL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[16]++;
      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[17]++;
        return TernaryValue.FALSE;

      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[18]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(n.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[19]++;
          return TernaryValue.FALSE;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[20]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[16]++;
        break;

      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[21]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[17]++;
        String name = n.getString();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 (("undefined".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 (("NaN".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[22]++;
          // We assume here that programs don't change the value of the keyword
          // undefined to something other than the value undefined.
          return TernaryValue.FALSE;

        } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[23]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[19]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (("Infinity".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[24]++;
          return TernaryValue.TRUE;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[25]++;}
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[20]++;
        break;

      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[26]++;
      case Token.REGEXP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[27]++;
        return TernaryValue.TRUE;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[28]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[29]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[30]++;
          return TernaryValue.TRUE;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[31]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[22]++;
        break; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[32]++;
    }

    return TernaryValue.UNKNOWN;
  }

  /**
   * Gets the value of a node as a String, or null if it cannot be converted.
   * When it returns a non-null String, this method effectively emulates the
   * <code>String()</code> JavaScript cast function.
   */
  static String getStringValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[23]++;
    // TODO(user): regex literals as well.
    switch (n.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[33]++;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[34]++;
        return n.getString();

      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[35]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[24]++;
        String name = n.getString();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 (("undefined".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 (("Infinity".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (("NaN".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[36]++;
          return name;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[37]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[26]++;
        break;

      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[38]++;
        return getStringValue(n.getDouble());

      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[39]++;
        return "false";

      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[40]++;
        return "true";

      case Token.NULL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[41]++;
        return "null";

      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[42]++;
        return "undefined";

      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[43]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[27]++;
        TernaryValue child = getPureBooleanValue(n.getFirstChild());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((child != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[44]++;
          return child.toBoolean(true) ? "false" : "true";
 // reversed.
        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[45]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[29]++;
        break;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[46]++;
        return arrayToString(n);

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[47]++;
        return "[object Object]"; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[48]++;
    }
    return null;
  }

  static String getStringValue(double value) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[30]++;
    long longValue = (long) value;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;

    // Return "1" instead of "1.0"
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((longValue == value) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[49]++;
      return Long.toString(longValue);

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[50]++;
      return Double.toString(value);
    }
  }

  /**
   * When converting arrays to string using Array.prototype.toString or
   * Array.prototype.join, the rules for conversion to String are different
   * than converting each element individually.  Specifically, "null" and
   * "undefined" are converted to an empty string.
   * @param n A node that is a member of an Array.
   * @return The string representation.
   */
  static String getArrayElementStringValue(Node n) {
    return (NodeUtil.isNullOrUndefined(n) || n.isEmpty())
        ? "" : getStringValue(n);
  }

  static String arrayToString(Node literal) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[32]++;
    Node first = literal.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[33]++;
    StringBuilder result = new StringBuilder();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[34]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
    for (Node n = first;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[1]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[2]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[3]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[35]++;
      String childValue = getArrayElementStringValue(n);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((childValue == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[51]++;
        return null;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[52]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n != first) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[53]++;
        result.append(',');
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[38]++;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[54]++;}
      result.append(childValue);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[39]++;
    }
    return result.toString();
  }

  /**
   * Gets the value of a node as a Number, or null if it cannot be converted.
   * When it returns a non-null Double, this method effectively emulates the
   * <code>Number()</code> JavaScript cast function.
   */
  static Double getNumberValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[40]++;
    switch (n.getType()) {
      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[55]++;
        return 1.0;

      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[56]++;
      case Token.NULL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[57]++;
        return 0.0;

      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[58]++;
        return n.getDouble();

      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[59]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(n.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[60]++;
          return null;

        } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[61]++;
          return Double.NaN;
        }

      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[62]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[42]++;
        // Check for known constants
        String name = n.getString();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[43]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((name.equals("undefined")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[63]++;
          return Double.NaN;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[64]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((name.equals("NaN")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[65]++;
          return Double.NaN;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[66]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[45]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((name.equals("Infinity")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[67]++;
          return Double.POSITIVE_INFINITY;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[68]++;}
        return null;

      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[69]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[46]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((n.getChildCount() == 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getString().equals("Infinity")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 3) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[70]++;
          return Double.NEGATIVE_INFINITY;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[71]++;}
        return null;

      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[72]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[47]++;
        TernaryValue child = getPureBooleanValue(n.getFirstChild());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[48]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((child != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[73]++;
          return child.toBoolean(true) ? 0.0 : 1.0;
 // reversed.
        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[74]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[49]++;
        break;

      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[75]++;
        return getStringNumberValue(n.getString());

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[76]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[77]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[50]++;
        String value = getStringValue(n);
        return value != null ? getStringNumberValue(value) : null; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[78]++;
    }

    return null;
  }

  static Double getStringNumberValue(String rawJsString) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[51]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((rawJsString.contains("\u000b")) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[79]++;
      // vertical tab is not always whitespace
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[80]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[52]++;

    String s = trimJsWhiteSpace(rawJsString);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[53]++;
int CodeCoverConditionCoverageHelper_C19;
    // return ScriptRuntime.toNumber(s);
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((s.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[81]++;
      return 0.0;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[82]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[54]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (128)) == 0 || true) &&
 ((s.length() > 2) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((s.charAt(0) == '0') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((s.charAt(1) == 'x') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((s.charAt(1) == 'X') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 4) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[83]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[55]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      // Attempt to convert hex numbers.
      try {
CodeCoverTryBranchHelper_Try1 = true;
        return Double.valueOf(Integer.parseInt(s.substring(2), 16));
      } catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[86]++;
        return Double.NaN;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[85]++;
}
  }

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[84]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[56]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2048)) == 0 || true) &&
 ((s.length() > 3) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1024)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C21 |= (512)) == 0 || true) &&
 ((s.charAt(0) == '-') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (128)) == 0 || true) &&
 ((s.charAt(0) == '+') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (64)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((s.charAt(1) == '0') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((s.charAt(2) == 'x') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((s.charAt(2) == 'X') && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 6) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 6) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[87]++;
      // hex numbers with explicit signs vary between browsers.
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[88]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[57]++;
int CodeCoverConditionCoverageHelper_C22;

    // Firefox and IE treat the "Infinity" differently. Firefox is case
    // insensitive, but IE treats "infinity" as NaN.  So leave it alone.
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((s.equals("infinity")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((s.equals("-infinity")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((s.equals("+infinity")) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[89]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[90]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[58]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

    try {
CodeCoverTryBranchHelper_Try2 = true;
      return Double.parseDouble(s);
    } catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[92]++;
      return Double.NaN;
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[91]++;
}
  }
  }

  static String trimJsWhiteSpace(String s) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[59]++;
    int start = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[60]++;
    int end = s.length();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[61]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[4]++;


int CodeCoverConditionCoverageHelper_C23;
    while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((end > 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isStrWhiteSpaceChar(s.charAt(end - 1)) == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[4]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[5]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[6]++;
}
      end--;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[62]++;
    }
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[63]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[7]++;


int CodeCoverConditionCoverageHelper_C24;
    while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((start < end) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isStrWhiteSpaceChar(s.charAt(start)) == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[7]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[8]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[9]++;
}
      start++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[64]++;
    }
    return s.substring(start, end);
  }

  /**
   * Copied from Rhino's ScriptRuntime
   */
  public static TernaryValue isStrWhiteSpaceChar(int c) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[65]++;
    switch (c) {
      case '\u000B':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[93]++; // <VT>
        return TernaryValue.UNKNOWN;  // IE says "no", ECMAScript says "yes"
      case ' ':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[94]++; // <SP>
      case '\n':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[95]++; // <LF>
      case '\r':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[96]++; // <CR>
      case '\t':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[97]++; // <TAB>
      case '\u00A0':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[98]++; // <NBSP>
      case '\u000C':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[99]++; // <FF>
      case '\u2028':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[100]++; // <LS>
      case '\u2029':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[101]++; // <PS>
      case '\uFEFF':
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[102]++; // <BOM>
        return TernaryValue.TRUE;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[103]++;
        return (Character.getType(c) == Character.SPACE_SEPARATOR)
            ? TernaryValue.TRUE : TernaryValue.FALSE;
    }
  }

  /**
   * Gets the function's name. This method recognizes five forms:
   * <ul>
   * <li>{@code function name() ...}</li>
   * <li>{@code var name = function() ...}</li>
   * <li>{@code qualified.name = function() ...}</li>
   * <li>{@code var name2 = function name1() ...}</li>
   * <li>{@code qualified.name2 = function name1() ...}</li>
   * </ul>
   * In two last cases with named function expressions, the second name is
   * returned (the variable of qualified name).
   *
   * @param n a node whose type is {@link Token#FUNCTION}
   * @return the function's name, or {@code null} if it has no name
   */
  static String getFunctionName(Node n) {
    Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[66]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[67]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[68]++;
    switch (parent.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[104]++;
        // var name = function() ...
        // var name2 = function name1() ...
        return parent.getQualifiedName();

      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[105]++;
        // qualified.name = function() ...
        // qualified.name2 = function name1() ...
        return parent.getFirstChild().getQualifiedName();

      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[106]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[69]++;
        // function name() ...
        String name = n.getFirstChild().getQualifiedName();
        return name;
    }
  }

  /**
   * Gets the function's name. This method recognizes the forms:
   * <ul>
   * <li>{@code &#123;'name': function() ...&#125;}</li>
   * <li>{@code &#123;name: function() ...&#125;}</li>
   * <li>{@code function name() ...}</li>
   * <li>{@code var name = function() ...}</li>
   * <li>{@code qualified.name = function() ...}</li>
   * <li>{@code var name2 = function name1() ...}</li>
   * <li>{@code qualified.name2 = function name1() ...}</li>
   * </ul>
   *
   * @param n a node whose type is {@link Token#FUNCTION}
   * @return the function's name, or {@code null} if it has no name
   */
  public static String getNearestFunctionName(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[70]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[107]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[108]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[71]++;

    String name = getFunctionName(n);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[72]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[109]++;
      return name;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[110]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[73]++;

    // Check for the form { 'x' : function() { } }
    Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[74]++;
    switch (parent.getType()) {
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[111]++;
      case Token.GETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[112]++;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[113]++;
        // Return the name of the literal's key.
        return parent.getString();
      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[114]++;
        return getStringValue(parent); default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[115]++;
    }

    return null;
  }


  /**
   * Returns true if this is an immutable value.
   */
  static boolean isImmutableValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[75]++;
    switch (n.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[116]++;
      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[117]++;
      case Token.NULL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[118]++;
      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[119]++;
      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[120]++;
        return true;
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[121]++;
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[122]++;
        return isImmutableValue(n.getFirstChild());
      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[123]++;
      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[124]++;
        return isImmutableValue(n.getFirstChild());
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[125]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[76]++;
        String name = n.getString();
        // We assume here that programs don't change the value of the keyword
        // undefined to something other than the value undefined.
        return "undefined".equals(name)
            || "Infinity".equals(name)
            || "NaN".equals(name); default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[126]++;
    }

    return false;
  }

  /**
   * Returns true if the operator on this node is symmetric
   */
  static boolean isSymmetricOperation(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[77]++;
    switch (n.getType()) {
      case Token.EQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[127]++; // equal
      case Token.NE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[128]++; // not equal
      case Token.SHEQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[129]++; // exactly equal
      case Token.SHNE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[130]++; // exactly not equal
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[131]++; // multiply, unlike add it only works on numbers
                      // or results NaN if any of the operators is not a number
        return true; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[132]++;
    }
    return false;
  }

  /**
   * Returns true if the operator on this node is relational.
   * the returned set does not include the equalities.
   */
  static boolean isRelationalOperation(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[78]++;
    switch (n.getType()) {
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[133]++; // equal
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[134]++; // not equal
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[135]++; // exactly equal
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[136]++; // exactly not equal
        return true; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[137]++;
    }
    return false;
  }

  /**
   * Returns the inverse of an operator if it is invertible.
   * ex. '>' ==> '<'
   */
  static int getInverseOperator(int type) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[79]++;
    switch (type) {
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[138]++;
        return Token.LT;
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[139]++;
        return Token.GT;
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[140]++;
        return Token.LE;
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[141]++;
        return Token.GE; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[142]++;
    }
    return Token.ERROR;
  }

  /**
   * Returns true if this is a literal value. We define a literal value
   * as any node that evaluates to the same thing regardless of when or
   * where it is evaluated. So /xyz/ and [3, 5] are literals, but
   * the name a is not.
   *
   * Function literals do not meet this definition, because they
   * lexically capture variables. For example, if you have
   * <code>
   * function() { return a; }
   * </code>
   * If it is evaluated in a different scope, then it
   * captures a different variable. Even if the function did not read
   * any captured variables directly, it would still fail this definition,
   * because it affects the lifecycle of variables in the enclosing scope.
   *
   * However, a function literal with respect to a particular scope is
   * a literal.
   *
   * @param includeFunctions If true, all function expressions will be
   *     treated as literals.
   */
  static boolean isLiteralValue(Node n, boolean includeFunctions) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[80]++;
    switch (n.getType()) {
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[143]++;
        return isLiteralValue(n.getFirstChild(), includeFunctions);

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[144]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[81]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[10]++;


int CodeCoverConditionCoverageHelper_C27;
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false);
             child = child.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[10]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[11]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[12]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[82]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && ((!
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((child.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isLiteralValue(child, includeFunctions)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[145]++;
            return false;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[146]++;}
        }
        return true;

      case Token.REGEXP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[147]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[83]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[13]++;


int CodeCoverConditionCoverageHelper_C29;
        // Return true only if all children are const.
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false);
             child = child.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[13]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[14]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[15]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[84]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isLiteralValue(child, includeFunctions)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[148]++;
            return false;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[149]++;}
        }
        return true;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[150]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[85]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[16]++;


int CodeCoverConditionCoverageHelper_C31;
        // Return true only if all values are const.
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false);
             child = child.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[16]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[17]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[18]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[86]++;
int CodeCoverConditionCoverageHelper_C32;
          if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isLiteralValue(child.getFirstChild(), includeFunctions)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[151]++;
            return false;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[152]++;}
        }
        return true;

      case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[153]++;
        return includeFunctions && !NodeUtil.isFunctionDeclaration(n);

      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[154]++;
        return isImmutableValue(n);
    }
  }

  /**
   * Determines whether the given value may be assigned to a define.
   *
   * @param val The value being assigned.
   * @param defines The list of names of existing defines.
   */
  static boolean isValidDefineValue(Node val, Set<String> defines) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[87]++;
    switch (val.getType()) {
      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[155]++;
      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[156]++;
      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[157]++;
      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[158]++;
        return true;

      // Binary operators are only valid if both children are valid.
      case Token.ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[159]++;
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[160]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[161]++;
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[162]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[163]++;
      case Token.DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[164]++;
      case Token.EQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[165]++;
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[166]++;
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[167]++;
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[168]++;
      case Token.LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[169]++;
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[170]++;
      case Token.MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[171]++;
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[172]++;
      case Token.NE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[173]++;
      case Token.RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[174]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[175]++;
      case Token.SHNE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[176]++;
      case Token.SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[177]++;
      case Token.URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[178]++;
        return isValidDefineValue(val.getFirstChild(), defines)
            && isValidDefineValue(val.getLastChild(), defines);

      // Unary operators are valid if the child is valid.
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[179]++;
      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[180]++;
      case Token.POS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[181]++;
        return isValidDefineValue(val.getFirstChild(), defines);

      // Names are valid if and only if they are defines themselves.
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[182]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[183]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[88]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((val.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[184]++;
          return defines.contains(val.getQualifiedName());

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[185]++;} default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[186]++;
    }
    return false;
  }

  /**
   * Returns whether this a BLOCK node with no children.
   *
   * @param block The node.
   */
  static boolean isEmptyBlock(Node block) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[89]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((block.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[187]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[188]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[90]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[19]++;


int CodeCoverConditionCoverageHelper_C35;

    for (Node n = block.getFirstChild();(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[19]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[20]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[21]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[91]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((n.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[189]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[190]++;}
    }
    return true;
  }

  static boolean isSimpleOperator(Node n) {
    return isSimpleOperatorType(n.getType());
  }

  /**
   * A "simple" operator is one whose children are expressions,
   * has no direct side-effects (unlike '+='), and has no
   * conditional aspects (unlike '||').
   */
  static boolean isSimpleOperatorType(int type) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[92]++;
    switch (type) {
      case Token.ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[191]++;
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[192]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[193]++;
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[194]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[195]++;
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[196]++;
      case Token.DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[197]++;
      case Token.EQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[198]++;
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[199]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[200]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[201]++;
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[202]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[203]++;
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[204]++;
      case Token.LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[205]++;
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[206]++;
      case Token.MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[207]++;
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[208]++;
      case Token.NE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[209]++;
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[210]++;
      case Token.RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[211]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[212]++;
      case Token.SHNE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[213]++;
      case Token.SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[214]++;
      case Token.TYPEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[215]++;
      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[216]++;
      case Token.POS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[217]++;
      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[218]++;
      case Token.URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[219]++;
        return true;

      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[220]++;
        return false;
    }
  }

  /**
   * Creates an EXPR_RESULT.
   *
   * @param child The expression itself.
   * @return Newly created EXPR node with the child as subexpression.
   */
  static Node newExpr(Node child) {
    return IR.exprResult(child).srcref(child);
  }

  /**
   * Returns true if the node may create new mutable state, or change existing
   * state.
   *
   * @see <a href="http://www.xkcd.org/326/">XKCD Cartoon</a>
   */
  static boolean mayEffectMutableState(Node n) {
    return mayEffectMutableState(n, null);
  }

  static boolean mayEffectMutableState(Node n, AbstractCompiler compiler) {
    return checkForStateChangeHelper(n, true, compiler);
  }

  /**
   * Returns true if the node which may have side effects when executed.
   */
  static boolean mayHaveSideEffects(Node n) {
    return mayHaveSideEffects(n, null);
  }

  static boolean mayHaveSideEffects(Node n, AbstractCompiler compiler) {
    return checkForStateChangeHelper(n, false, compiler);
  }

  /**
   * Returns true if some node in n's subtree changes application state.
   * If {@code checkForNewObjects} is true, we assume that newly created
   * mutable objects (like object literals) change state. Otherwise, we assume
   * that they have no side effects.
   */
  private static boolean checkForStateChangeHelper(
      Node n, boolean checkForNewObjects, AbstractCompiler compiler) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[93]++;
    // Rather than id which ops may have side effects, id the ones
    // that we know to be safe
    switch (n.getType()) {
      // other side-effect free statements and expressions
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[221]++;
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[222]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[223]++;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[224]++;
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[225]++;
      case Token.IF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[226]++;
      case Token.IN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[227]++;
      case Token.PARAM_LIST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[228]++;
      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[229]++;
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[230]++;
      case Token.THIS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[231]++;
      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[232]++;
      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[233]++;
      case Token.NULL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[234]++;
      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[235]++;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[236]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[237]++;
      case Token.TRY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[238]++;
      case Token.EMPTY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[239]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[94]++;
        break;

      // Throws are by definition side effects
      case Token.THROW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[240]++;
        return true;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[241]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[95]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((checkForNewObjects) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[242]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[243]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[96]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[22]++;


int CodeCoverConditionCoverageHelper_C38;
        for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[22]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[23]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[24]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[97]++;
int CodeCoverConditionCoverageHelper_C39;
          if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((checkForStateChangeHelper(
                  c.getFirstChild(), checkForNewObjects, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[244]++;
            return true;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[245]++;}
        }
        return false;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[246]++;
      case Token.REGEXP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[247]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[98]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((checkForNewObjects) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[248]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[249]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[99]++;
        break;

      case Token.VAR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[250]++;    // empty var statement (no declaration)
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[251]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[100]++;
int CodeCoverConditionCoverageHelper_C41;   // variable by itself
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((n.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[252]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[253]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[101]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[254]++;
        // Function expressions don't have side-effects, but function
        // declarations change the namespace. Either way, we don't need to
        // check the children, since they aren't executed at declaration time.
        return checkForNewObjects || !isFunctionExpression(n);

      case Token.NEW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[255]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[102]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((checkForNewObjects) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[256]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[257]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[103]++;
int CodeCoverConditionCoverageHelper_C43;

        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((constructorCallHasSideEffects(n)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[258]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[104]++;
          // loop below will see if the constructor parameters have
          // side-effects
          break;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[259]++;}
        return true;

      case Token.CALL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[260]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[105]++;
int CodeCoverConditionCoverageHelper_C44;
        // calls to functions that have no side effects have the no
        // side effect property set.
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((functionCallHasSideEffects(n, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[261]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[106]++;
          // loop below will see if the function parameters have
          // side-effects
          break;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[262]++;}
        return true;

      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[263]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[107]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((isSimpleOperator(n)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[264]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[108]++;
          break;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[265]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[109]++;
int CodeCoverConditionCoverageHelper_C46;

        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[266]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[110]++;
          Node assignTarget = n.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[111]++;
int CodeCoverConditionCoverageHelper_C47;
          if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((assignTarget.isName()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[268]++;
            return true;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[269]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[112]++;
int CodeCoverConditionCoverageHelper_C48;

          // Assignments will have side effects if
          // a) The RHS has side effects, or
          // b) The LHS has side effects, or
          // c) A name on the LHS will exist beyond the life of this statement.
          if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((checkForStateChangeHelper(
                  n.getFirstChild(), checkForNewObjects, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((checkForStateChangeHelper(
                  n.getLastChild(), checkForNewObjects, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[270]++;
            return true;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[271]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[113]++;
int CodeCoverConditionCoverageHelper_C49;

          if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((isGet(assignTarget)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[272]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[114]++;
            // If the object being assigned to is a local object, don't
            // consider this a side-effect as it can't be referenced
            // elsewhere.  Don't do this recursively as the property might
            // be an alias of another object, unlike a literal below.
            Node current = assignTarget.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[115]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((evaluatesToLocalValue(current)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[274]++;
              return false;

            } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[275]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[116]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[25]++;


int CodeCoverConditionCoverageHelper_C51;

            // A literal value as defined by "isLiteralValue" is guaranteed
            // not to be an alias, or any components which are aliases of
            // other objects.
            // If the root object is a literal don't consider this a
            // side-effect.
            while ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((isGet(current)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[25]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[26]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[27]++;
}
              current = current.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[117]++;
            }

            return !isLiteralValue(current, true);

          } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[273]++;
            // TODO(johnlenz): remove this code and make this an exception. This
            // is here only for legacy reasons, the AST is not valid but
            // preserve existing behavior.
            return !isLiteralValue(assignTarget, true);
          }

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[267]++;}

        return true;
    }
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[118]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[28]++;


int CodeCoverConditionCoverageHelper_C52;

    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[28]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[29]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[30]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[119]++;
int CodeCoverConditionCoverageHelper_C53;
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((checkForStateChangeHelper(c, checkForNewObjects, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[276]++;
        return true;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[277]++;}
    }

    return false;
  }

  /**
   * Do calls to this constructor have side effects?
   *
   * @param callNode - constructor call node
   */
  static boolean constructorCallHasSideEffects(Node callNode) {
    return constructorCallHasSideEffects(callNode, null);
  }

  static boolean constructorCallHasSideEffects(
      Node callNode, AbstractCompiler compiler) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[120]++;
int CodeCoverConditionCoverageHelper_C54;
    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((callNode.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[278]++;
      throw new IllegalStateException(
          "Expected NEW node, got " + Token.name(callNode.getType()));

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[279]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[121]++;
int CodeCoverConditionCoverageHelper_C55;

    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((callNode.isNoSideEffectsCall()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[280]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[281]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[122]++;

    Node nameNode = callNode.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[123]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((nameNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((CONSTRUCTORS_WITHOUT_SIDE_EFFECTS.contains(nameNode.getString())) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[282]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[283]++;}

    return true;
  }

  // A list of built-in object creation or primitive type cast functions that
  // can also be called as constructors but lack side-effects.
  // TODO(johnlenz): consider adding an extern annotation for this.
  private static final Set<String> BUILTIN_FUNCTIONS_WITHOUT_SIDEEFFECTS =
      ImmutableSet.of(
          "Object", "Array", "String", "Number", "Boolean", "RegExp", "Error");
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[124]++;
  }
  private static final Set<String> OBJECT_METHODS_WITHOUT_SIDEEFFECTS =
      ImmutableSet.of("toString", "valueOf");
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[125]++;
  }
  private static final Set<String> REGEXP_METHODS =
      ImmutableSet.of("test", "exec");
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[126]++;
  }
  private static final Set<String> STRING_REGEXP_METHODS =
      ImmutableSet.of("match", "replace", "search", "split");
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[127]++;
  }

  /**
   * Returns true if calls to this function have side effects.
   *
   * @param callNode - function call node
   */
  static boolean functionCallHasSideEffects(Node callNode) {
    return functionCallHasSideEffects(callNode, null);
  }

  /**
   * Returns true if calls to this function have side effects.
   *
   * @param callNode The call node to inspected.
   * @param compiler A compiler object to provide program state changing
   *     context information. Can be null.
   */
  static boolean functionCallHasSideEffects(
      Node callNode, @Nullable AbstractCompiler compiler) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[128]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((callNode.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[284]++;
      throw new IllegalStateException(
          "Expected CALL node, got " + Token.name(callNode.getType()));

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[285]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[129]++;
int CodeCoverConditionCoverageHelper_C58;

    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((callNode.isNoSideEffectsCall()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[286]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[287]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[130]++;

    Node nameNode = callNode.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[131]++;
int CodeCoverConditionCoverageHelper_C59;

    // Built-in functions with no side effects.
    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((nameNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[288]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[132]++;
      String name = nameNode.getString();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[133]++;
int CodeCoverConditionCoverageHelper_C60;
      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((BUILTIN_FUNCTIONS_WITHOUT_SIDEEFFECTS.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[290]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[291]++;}

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[289]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[134]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((nameNode.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[292]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[135]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((callNode.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((OBJECT_METHODS_WITHOUT_SIDEEFFECTS.contains(
                nameNode.getLastChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[294]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[295]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[136]++;
int CodeCoverConditionCoverageHelper_C63;

      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((callNode.isOnlyModifiesThisCall()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((evaluatesToLocalValue(nameNode.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[296]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[297]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[137]++;
int CodeCoverConditionCoverageHelper_C64;

      // Math.floor has no side-effects.
      // TODO(nicksantos): This is a terrible terrible hack, until
      // I create a definitionProvider that understands namespacing.
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((nameNode.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[298]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[138]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 (("Math.floor".equals(nameNode.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[300]++;
          return false;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[301]++;}

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[299]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[139]++;
int CodeCoverConditionCoverageHelper_C66;

      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((compiler != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((compiler.hasRegExpGlobalReferences()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[302]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[140]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((nameNode.getFirstChild().isRegExp()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((REGEXP_METHODS.contains(nameNode.getLastChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[304]++;
          return false;

        } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[305]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[141]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((nameNode.getFirstChild().isString()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((STRING_REGEXP_METHODS.contains(
                nameNode.getLastChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[306]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[142]++;
          Node param = nameNode.getNext();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[143]++;
int CodeCoverConditionCoverageHelper_C69;
          if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((param.isString()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((param.isRegExp()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[308]++;
            return false;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[309]++;}

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[307]++;}
}

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[303]++;}

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[293]++;}
}

    return true;
  }

  /**
   * @return Whether the call has a local result.
   */
  static boolean callHasLocalResult(Node n) {
    Preconditions.checkState(n.isCall());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[144]++;
    return (n.getSideEffectFlags() & Node.FLAG_LOCAL_RESULTS) > 0;
  }

  /**
   * @return Whether the new has a local result.
   */
  static boolean newHasLocalResult(Node n) {
    Preconditions.checkState(n.isNew());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[145]++;
    return n.isOnlyModifiesThisCall();
  }

  /**
   * Returns true if the current node's type implies side effects.
   *
   * This is a non-recursive version of the may have side effects
   * check; used to check wherever the current node's type is one of
   * the reason's why a subtree has side effects.
   */
  static boolean nodeTypeMayHaveSideEffects(Node n) {
    return nodeTypeMayHaveSideEffects(n, null);
  }

  static boolean nodeTypeMayHaveSideEffects(Node n, AbstractCompiler compiler) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[146]++;
int CodeCoverConditionCoverageHelper_C70;
    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[310]++;
      return true;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[311]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[147]++;

    switch(n.getType()) {
      case Token.DELPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[312]++;
      case Token.DEC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[313]++;
      case Token.INC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[314]++;
      case Token.THROW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[315]++;
        return true;
      case Token.CALL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[316]++;
        return NodeUtil.functionCallHasSideEffects(n, compiler);
      case Token.NEW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[317]++;
        return NodeUtil.constructorCallHasSideEffects(n, compiler);
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[318]++;
        // A variable definition.
        return n.hasChildren();
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[319]++;
        return false;
    }
  }

  /**
   * @return Whether the tree can be affected by side-effects or
   * has side-effects.
   */
  static boolean canBeSideEffected(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[148]++;
    Set<String> emptySet = Collections.emptySet();
    return canBeSideEffected(n, emptySet);
  }

  /**
   * @param knownConstants A set of names known to be constant value at
   * node 'n' (such as locals that are last written before n can execute).
   * @return Whether the tree can be affected by side-effects or
   * has side-effects.
   */
  static boolean canBeSideEffected(Node n, Set<String> knownConstants) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[149]++;
    switch (n.getType()) {
      case Token.CALL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[320]++;
      case Token.NEW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[321]++;
        // Function calls or constructor can reference changed values.
        // TODO(johnlenz): Add some mechanism for determining that functions
        // are unaffected by side effects.
        return true;
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[322]++;
        // Non-constant names values may have been changed.
        return !isConstantName(n)
            && !knownConstants.contains(n.getString());

      // Properties on constant NAMEs can still be side-effected.
      case Token.GETPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[323]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[324]++;
        return true;

      case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[325]++;
        // Function expression are not changed by side-effects,
        // and function declarations are not part of expressions.
        Preconditions.checkState(isFunctionExpression(n));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[150]++;
        return false; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[326]++;
    }
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[151]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[31]++;


int CodeCoverConditionCoverageHelper_C71;

    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[31]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[32]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[33]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[152]++;
int CodeCoverConditionCoverageHelper_C72;
      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((canBeSideEffected(c, knownConstants)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[327]++;
        return true;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[328]++;}
    }

    return false;
  }

  /*
   *  0 comma ,
   *  1 assignment = += -= *= /= %= <<= >>= >>>= &= ^= |=
   *  2 conditional ?:
   *  3 logical-or ||
   *  4 logical-and &&
   *  5 bitwise-or |
   *  6 bitwise-xor ^
   *  7 bitwise-and &
   *  8 equality == !=
   *  9 relational < <= > >=
   * 10 bitwise shift << >> >>>
   * 11 addition/subtraction + -
   * 12 multiply/divide * / %
   * 13 negation/increment ! ~ - ++ --
   * 14 call, member () [] .
   */
  static int precedence(int type) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[153]++;
    switch (type) {
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[329]++;  return 0;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[330]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[331]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[332]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[333]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[334]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[335]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[336]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[337]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[338]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[339]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[340]++;
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[341]++; return 1;
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[342]++;   return 2;  // ?: operator
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[343]++;     return 3;
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[344]++;    return 4;
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[345]++;  return 5;
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[346]++; return 6;
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[347]++; return 7;
      case Token.EQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[348]++;
      case Token.NE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[349]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[350]++;
      case Token.SHNE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[351]++;   return 8;
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[352]++;
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[353]++;
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[354]++;
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[355]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[356]++;
      case Token.IN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[357]++;     return 9;
      case Token.LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[358]++;
      case Token.RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[359]++;
      case Token.URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[360]++;   return 10;
      case Token.SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[361]++;
      case Token.ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[362]++;    return 11;
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[363]++;
      case Token.MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[364]++;
      case Token.DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[365]++;    return 12;
      case Token.INC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[366]++;
      case Token.DEC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[367]++;
      case Token.NEW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[368]++;
      case Token.DELPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[369]++;
      case Token.TYPEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[370]++;
      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[371]++;
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[372]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[373]++;
      case Token.POS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[374]++;
      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[375]++;    return 13;

      case Token.CALL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[376]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[377]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[378]++;
      // Data values
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[379]++;
      case Token.EMPTY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[380]++;  // TODO(johnlenz): remove this.
      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[381]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[382]++;
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[383]++;
      case Token.NULL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[384]++;
      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[385]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[386]++;
      case Token.REGEXP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[387]++;
      case Token.STRING:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[388]++;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[389]++;
      case Token.THIS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[390]++;
      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[391]++;
        return 15;
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[392]++;
        return 16;

      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[393]++; throw new Error("Unknown precedence for " +
                               Token.name(type) +
                               " (type " + type + ")");
    }
  }

  static boolean isUndefined(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[154]++;
    switch (n.getType()) {
      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[394]++;
        return true;
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[395]++;
        return n.getString().equals("undefined"); default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[396]++;
    }
    return false;
  }

  static boolean isNullOrUndefined(Node n) {
    return n.isNull() || isUndefined(n);
  }

  static final Predicate<Node> IMMUTABLE_PREDICATE = new Predicate<Node>() {
    @Override
    public boolean apply(Node n) {
      return isImmutableValue(n);
    }
  };
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[155]++;
  }

  static boolean isImmutableResult(Node n) {
    return allResultsMatch(n, IMMUTABLE_PREDICATE);
  }

  /**
   * Apply the supplied predicate against
   * all possible result Nodes of the expression.
   */
  static boolean allResultsMatch(Node n, Predicate<Node> p) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[156]++;
    switch (n.getType()) {
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[397]++;
        return allResultsMatch(n.getFirstChild(), p);
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[398]++;
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[399]++;
        return allResultsMatch(n.getLastChild(), p);
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[400]++;
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[401]++;
        return allResultsMatch(n.getFirstChild(), p)
            && allResultsMatch(n.getLastChild(), p);
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[402]++;
        return allResultsMatch(n.getFirstChild().getNext(), p)
            && allResultsMatch(n.getLastChild(), p);
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[403]++;
        return p.apply(n);
    }
  }

  /**
   * Apply the supplied predicate against
   * all possible result Nodes of the expression.
   */
  static boolean anyResultsMatch(Node n, Predicate<Node> p) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[157]++;
    switch (n.getType()) {
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[404]++;
        return anyResultsMatch(n.getFirstChild(), p);
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[405]++;
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[406]++;
        return anyResultsMatch(n.getLastChild(), p);
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[407]++;
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[408]++;
        return anyResultsMatch(n.getFirstChild(), p)
            || anyResultsMatch(n.getLastChild(), p);
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[409]++;
        return anyResultsMatch(n.getFirstChild().getNext(), p)
            || anyResultsMatch(n.getLastChild(), p);
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[410]++;
        return p.apply(n);
    }
  }

  static class NumbericResultPredicate implements Predicate<Node> {
    @Override
    public boolean apply(Node n) {
      return isNumericResultHelper(n);
    }
  }

  static final NumbericResultPredicate NUMBERIC_RESULT_PREDICATE =
      new NumbericResultPredicate();
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[158]++;
  }

  /**
   * Returns true if the result of node evaluation is always a number
   */
  static boolean isNumericResult(Node n) {
    return allResultsMatch(n, NUMBERIC_RESULT_PREDICATE);
  }

  static boolean isNumericResultHelper(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[159]++;
    switch (n.getType()) {
      case Token.ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[411]++;
        return !mayBeString(n.getFirstChild())
            && !mayBeString(n.getLastChild());
      case Token.BITNOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[412]++;
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[413]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[414]++;
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[415]++;
      case Token.LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[416]++;
      case Token.RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[417]++;
      case Token.URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[418]++;
      case Token.SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[419]++;
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[420]++;
      case Token.MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[421]++;
      case Token.DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[422]++;
      case Token.INC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[423]++;
      case Token.DEC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[424]++;
      case Token.POS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[425]++;
      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[426]++;
      case Token.NUMBER:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[427]++;
        return true;
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[428]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[160]++;
        String name = n.getString();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[161]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((name.equals("NaN")) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[429]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[430]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[162]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((name.equals("Infinity")) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[431]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[432]++;}
        return false;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[433]++;
        return false;
    }
  }

  static class BooleanResultPredicate implements Predicate<Node> {
    @Override
    public boolean apply(Node n) {
      return isBooleanResultHelper(n);
    }
  }

  static final BooleanResultPredicate BOOLEAN_RESULT_PREDICATE =
      new BooleanResultPredicate();
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[163]++;
  }

  /**
   * @return Whether the result of node evaluation is always a boolean
   */
  static boolean isBooleanResult(Node n) {
    return allResultsMatch(n, BOOLEAN_RESULT_PREDICATE);
  }

  static boolean isBooleanResultHelper(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[164]++;
    switch (n.getType()) {
      // Primitives
      case Token.TRUE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[434]++;
      case Token.FALSE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[435]++;
      // Comparisons
      case Token.EQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[436]++;
      case Token.NE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[437]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[438]++;
      case Token.SHNE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[439]++;
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[440]++;
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[441]++;
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[442]++;
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[443]++;
      // Queries
      case Token.IN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[444]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[445]++;
      // Inversion
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[446]++;
      // delete operator returns a boolean.
      case Token.DELPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[447]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[448]++;
        return false;
    }
  }



  static class MayBeStringResultPredicate implements Predicate<Node> {
    @Override
    public boolean apply(Node n) {
      return mayBeStringHelper(n);
    }
  }

  static final MayBeStringResultPredicate MAY_BE_STRING_PREDICATE =
      new MayBeStringResultPredicate();
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[165]++;
  }

  /**
   * @returns Whether the results is possibly a string.
   */
  static boolean mayBeString(Node n) {
    return mayBeString(n, true);
  }

  static boolean mayBeString(Node n, boolean recurse) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[166]++;
int CodeCoverConditionCoverageHelper_C75;
    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((recurse) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[449]++;
      return anyResultsMatch(n, MAY_BE_STRING_PREDICATE);

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[450]++;
      return mayBeStringHelper(n);
    }
  }

  static boolean mayBeStringHelper(Node n) {
    return !isNumericResult(n) && !isBooleanResult(n)
        && !isUndefined(n) && !n.isNull();
  }

  /**
   * Returns true if the operator is associative.
   * e.g. (a * b) * c = a * (b * c)
   * Note: "+" is not associative because it is also the concatenation
   * for strings. e.g. "a" + (1 + 2) is not "a" + 1 + 2
   */
  static boolean isAssociative(int type) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[167]++;
    switch (type) {
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[451]++;
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[452]++;
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[453]++;
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[454]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[455]++;
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[456]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[457]++;
        return false;
    }
  }

  /**
   * Returns true if the operator is commutative.
   * e.g. (a * b) * c = c * (b * a)
   * Note 1: "+" is not commutative because it is also the concatenation
   * for strings. e.g. "a" + (1 + 2) is not "a" + 1 + 2
   * Note 2: only operations on literals and pure functions are commutative.
   */
  static boolean isCommutative(int type) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[168]++;
    switch (type) {
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[458]++;
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[459]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[460]++;
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[461]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[462]++;
        return false;
    }
  }

  static boolean isAssignmentOp(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[169]++;
    switch (n.getType()){
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[463]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[464]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[465]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[466]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[467]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[468]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[469]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[470]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[471]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[472]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[473]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[474]++;
        return true; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[475]++;
    }
    return false;
  }

  static int getOpFromAssignmentOp(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[170]++;
    switch (n.getType()){
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[476]++;
        return Token.BITOR;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[477]++;
        return Token.BITXOR;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[478]++;
        return Token.BITAND;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[479]++;
        return Token.LSH;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[480]++;
        return Token.RSH;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[481]++;
        return Token.URSH;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[482]++;
        return Token.ADD;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[483]++;
        return Token.SUB;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[484]++;
        return Token.MUL;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[485]++;
        return Token.DIV;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[486]++;
        return Token.MOD; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[487]++;
    }
    throw new IllegalArgumentException("Not an assignment op:" + n);
  }

  /**
   * Determines if the given node contains a function statement or function
   * expression.
   */
  static boolean containsFunction(Node n) {
    return containsType(n, Token.FUNCTION);
  }

  /**
   * Returns true if the shallow scope contains references to 'this' keyword
   */
  static boolean referencesThis(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[171]++;
    Node start = (n.isFunction()) ? n.getLastChild() : n;
    return containsType(start, Token.THIS, MATCH_NOT_FUNCTION);
  }

  /**
   * Is this a GETPROP or GETELEM node?
   */
  static boolean isGet(Node n) {
    return n.isGetProp() || n.isGetElem();
  }

  /**
   * Is this node the name of a variable being declared?
   *
   * @param n The node
   * @return True if {@code n} is NAME and {@code parent} is VAR
   */
  static boolean isVarDeclaration(Node n) {
    // There is no need to verify that parent != null because a NAME node
    // always has a parent in a valid parse tree.
    return n.isName() && n.getParent().isVar();
  }

  /**
   * For an assignment or variable declaration get the assigned value.
   * @return The value node representing the new value.
   */
  static Node getAssignedValue(Node n) {
    Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[172]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[173]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[174]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[488]++;
      return n.getFirstChild();

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[489]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[175]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[490]++;
      return n.getNext();

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[491]++;
      return null;
    }
}
  }

  /**
   * Is this node an assignment expression statement?
   *
   * @param n The node
   * @return True if {@code n} is EXPR_RESULT and {@code n}'s
   *     first child is ASSIGN
   */
  static boolean isExprAssign(Node n) {
    return n.isExprResult()
        && n.getFirstChild().isAssign();
  }

  /**
   * Is this node a call expression statement?
   *
   * @param n The node
   * @return True if {@code n} is EXPR_RESULT and {@code n}'s
   *     first child is CALL
   */
  static boolean isExprCall(Node n) {
    return n.isExprResult()
        && n.getFirstChild().isCall();
  }

  /**
   * @return Whether the node represents a FOR-IN loop.
   */
  static boolean isForIn(Node n) {
    return n.isFor()
        && n.getChildCount() == 3;
  }

  /**
   * Determines whether the given node is a FOR, DO, or WHILE node.
   */
  static boolean isLoopStructure(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[176]++;
    switch (n.getType()) {
      case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[492]++;
      case Token.DO:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[493]++;
      case Token.WHILE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[494]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[495]++;
        return false;
    }
  }

  /**
   * @param n The node to inspect.
   * @return If the node, is a FOR, WHILE, or DO, it returns the node for
   * the code BLOCK, null otherwise.
   */
  static Node getLoopCodeBlock(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[177]++;
    switch (n.getType()) {
      case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[496]++;
      case Token.WHILE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[497]++;
        return n.getLastChild();
      case Token.DO:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[498]++;
        return n.getFirstChild();
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[499]++;
        return null;
    }
  }

  /**
   * @return Whether the specified node has a loop parent that
   * is within the current scope.
   */
  static boolean isWithinLoop(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[178]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[34]++;


    for (Node parent : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[34]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[35]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[36]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[179]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((NodeUtil.isLoopStructure(parent)) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[500]++;
        return true;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[501]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[180]++;
int CodeCoverConditionCoverageHelper_C79;

      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[502]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[181]++;
        break;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[503]++;}
    }
    return false;
  }

  /**
   * Determines whether the given node is a FOR, DO, WHILE, WITH, or IF node.
   */
  static boolean isControlStructure(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[182]++;
    switch (n.getType()) {
      case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[504]++;
      case Token.DO:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[505]++;
      case Token.WHILE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[506]++;
      case Token.WITH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[507]++;
      case Token.IF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[508]++;
      case Token.LABEL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[509]++;
      case Token.TRY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[510]++;
      case Token.CATCH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[511]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[512]++;
      case Token.CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[513]++;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[514]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[515]++;
        return false;
    }
  }

  /**
   * Determines whether the given node is code node for FOR, DO,
   * WHILE, WITH, or IF node.
   */
  static boolean isControlStructureCodeBlock(Node parent, Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[183]++;
    switch (parent.getType()) {
      case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[516]++;
      case Token.WHILE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[517]++;
      case Token.LABEL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[518]++;
      case Token.WITH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[519]++;
        return parent.getLastChild() == n;
      case Token.DO:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[520]++;
        return parent.getFirstChild() == n;
      case Token.IF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[521]++;
        return parent.getFirstChild() != n;
      case Token.TRY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[522]++;
        return parent.getFirstChild() == n || parent.getLastChild() == n;
      case Token.CATCH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[523]++;
        return parent.getLastChild() == n;
      case Token.SWITCH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[524]++;
      case Token.CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[525]++;
        return parent.getFirstChild() != n;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[526]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[527]++;
        Preconditions.checkState(isControlStructure(parent));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[184]++;
        return false;
    }
  }

  /**
   * Gets the condition of an ON_TRUE / ON_FALSE CFG edge.
   * @param n a node with an outgoing conditional CFG edge
   * @return the condition node or null if the condition is not obviously a node
   */
  static Node getConditionExpression(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[185]++;
    switch (n.getType()) {
      case Token.IF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[528]++;
      case Token.WHILE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[529]++;
        return n.getFirstChild();
      case Token.DO:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[530]++;
        return n.getLastChild();
      case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[531]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[186]++;
        switch (n.getChildCount()) {
          case 3:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[532]++;
            return null;
          case 4:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[533]++;
            return n.getFirstChild().getNext(); default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[534]++;
        }
        throw new IllegalArgumentException("malformed 'for' statement " + n);
      case Token.CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[535]++;
        return null; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[536]++;
    }
    throw new IllegalArgumentException(n + " does not have a condition.");
  }

  /**
   * @return Whether the node is of a type that contain other statements.
   */
  static boolean isStatementBlock(Node n) {
    return n.isScript() || n.isBlock();
  }

  /**
   * @return Whether the node is used as a statement.
   */
  static boolean isStatement(Node n) {
    return isStatementParent(n.getParent());
  }

  static boolean isStatementParent(Node parent) {
    // It is not possible to determine definitely if a node is a statement
    // or not if it is not part of the AST.  A FUNCTION node can be
    // either part of an expression or a statement.
    Preconditions.checkState(parent != null);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[187]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[188]++;
    switch (parent.getType()) {
      case Token.SCRIPT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[537]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[538]++;
      case Token.LABEL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[539]++;
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[540]++;
        return false;
    }
  }

  /** Whether the node is part of a switch statement. */
  static boolean isSwitchCase(Node n) {
    return n.isCase() || n.isDefaultCase();
  }

  /**
   * @return Whether the name is a reference to a variable, function or
   *       function parameter (not a label or a empty function expression name).
   */
  static boolean isReferenceName(Node n) {
    return n.isName() && !n.getString().isEmpty();
  }

  /** Whether the child node is the FINALLY block of a try. */
  static boolean isTryFinallyNode(Node parent, Node child) {
    return parent.isTry() && parent.getChildCount() == 3
        && child == parent.getLastChild();
  }

  /** Whether the node is a CATCH container BLOCK. */
  static boolean isTryCatchNodeContainer(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[189]++;
    Node parent = n.getParent();
    return parent.isTry()
        && parent.getFirstChild().getNext() == n;
  }

  /** Safely remove children while maintaining a valid node structure. */
  static void removeChild(Node parent, Node node) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[190]++;
int CodeCoverConditionCoverageHelper_C80;
    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((isTryFinallyNode(parent, node)) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[541]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[191]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((NodeUtil.hasCatchHandler(getCatchBlock(parent))) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[543]++;
        // A finally can only be removed if there is a catch.
        parent.removeChild(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[192]++;

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[544]++;
        // Otherwise, only its children can be removed.
        node.detachChildren();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[193]++;
      }

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[542]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[194]++;
int CodeCoverConditionCoverageHelper_C82; if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((node.isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[545]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[195]++;
      // The CATCH can can only be removed if there is a finally clause.
      Node tryNode = node.getParent().getParent();
      Preconditions.checkState(NodeUtil.hasFinally(tryNode));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[196]++;
      node.detachFromParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[197]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[546]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[198]++;
int CodeCoverConditionCoverageHelper_C83; if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((isTryCatchNodeContainer(node)) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[547]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[199]++;
      // The container node itself can't be removed, but the contained CATCH
      // can if there is a 'finally' clause
      Node tryNode = node.getParent();
      Preconditions.checkState(NodeUtil.hasFinally(tryNode));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[200]++;
      node.detachChildren();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[201]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[548]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[202]++;
int CodeCoverConditionCoverageHelper_C84; if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((node.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[549]++;
      // Simply empty the block.  This maintains source location and
      // "synthetic"-ness.
      node.detachChildren();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[203]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[550]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[204]++;
int CodeCoverConditionCoverageHelper_C85; if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((isStatementBlock(parent)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((isSwitchCase(node)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[551]++;
      // A statement in a block can simply be removed.
      parent.removeChild(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[205]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[552]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[206]++;
int CodeCoverConditionCoverageHelper_C86; if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[553]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[207]++;
int CodeCoverConditionCoverageHelper_C87;
      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((parent.hasMoreThanOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[555]++;
        parent.removeChild(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[208]++;

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[556]++;
        // Remove the node from the parent, so it can be reused.
        parent.removeChild(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[209]++;
        // This would leave an empty VAR, remove the VAR itself.
        removeChild(parent.getParent(), parent);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[210]++;
      }

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[554]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[211]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((parent.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((node == parent.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[557]++;
      // Remove the node from the parent, so it can be reused.
      parent.removeChild(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[212]++;
      // A LABEL without children can not be referred to, remove it.
      removeChild(parent.getParent(), parent);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[213]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[558]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[214]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (8)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((parent.getChildCount() == 4) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[559]++;
      // Only Token.FOR can have an Token.EMPTY other control structure
      // need something for the condition. Others need to be replaced
      // or the structure removed.
      parent.replaceChild(node, IR.empty());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[215]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[560]++;
      throw new IllegalStateException("Invalid attempt to remove node: " +
          node.toString() + " of " + parent.toString());
    }
}
}
}
}
}
}
}
  }

  /**
   * Add a finally block if one does not exist.
   */
  static void maybeAddFinally(Node tryNode) {
    Preconditions.checkState(tryNode.isTry());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[216]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[217]++;
int CodeCoverConditionCoverageHelper_C90;
    if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((NodeUtil.hasFinally(tryNode)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[561]++;
      tryNode.addChildrenToBack(IR.block().srcref(tryNode));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[218]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[562]++;}
  }

  /**
   * Merge a block with its parent block.
   * @return Whether the block was removed.
   */
  static boolean tryMergeBlock(Node block) {
    Preconditions.checkState(block.isBlock());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[219]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[220]++;
    Node parent = block.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[221]++;
int CodeCoverConditionCoverageHelper_C91;
    // Try to remove the block if its parent is a block/script or if its
    // parent is label and it has exactly one child.
    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((isStatementBlock(parent)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[563]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[222]++;
      Node previous = block;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[223]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[37]++;


int CodeCoverConditionCoverageHelper_C92;
      while ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[37]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[38]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[39]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[224]++;
        Node child = block.removeFirstChild();
        parent.addChildAfter(child, previous);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[225]++;
        previous = child;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[226]++;
      }
      parent.removeChild(block);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[227]++;
      return true;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[564]++;
      return false;
    }
  }

  /**
   * @param node A node
   * @return Whether the call is a NEW or CALL node.
   */
  static boolean isCallOrNew(Node node) {
    return node.isCall() || node.isNew();
  }

  /**
   * Return a BLOCK node for the given FUNCTION node.
   */
  static Node getFunctionBody(Node fn) {
    Preconditions.checkArgument(fn.isFunction());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[228]++;
    return fn.getLastChild();
  }

  /**
   * Is this node a function declaration? A function declaration is a function
   * that has a name that is added to the current scope (i.e. a function that
   * is not part of a expression; see {@link #isFunctionExpression}).
   */
  static boolean isFunctionDeclaration(Node n) {
    return n.isFunction() && isStatement(n);
  }

  /**
   * Is this node a hoisted function declaration? A function declaration in the
   * scope root is hoisted to the top of the scope.
   * See {@link #isFunctionDeclaration}).
   */
  static boolean isHoistedFunctionDeclaration(Node n) {
    return isFunctionDeclaration(n)
        && (n.getParent().isScript()
            || n.getParent().getParent().isFunction());
  }

  /**
   * Is a FUNCTION node an function expression? An function expression is one
   * that has either no name or a name that is not added to the current scope.
   *
   * <p>Some examples of function expressions:
   * <pre>
   * (function () {})
   * (function f() {})()
   * [ function f() {} ]
   * var f = function f() {};
   * for (function f() {};;) {}
   * </pre>
   *
   * <p>Some examples of functions that are <em>not</em> expressions:
   * <pre>
   * function f() {}
   * if (x); else function f() {}
   * for (;;) { function f() {} }
   * </pre>
   *
   * @param n A node
   * @return Whether n is an function used within an expression.
   */
  static boolean isFunctionExpression(Node n) {
    return n.isFunction() && !isStatement(n);
  }

  /**
   * Returns whether this is a bleeding function (an anonymous named function
   * that bleeds into the inner scope).
   */
  static boolean isBleedingFunctionName(Node n) {
    return n.isName() && !n.getString().isEmpty() &&
        isFunctionExpression(n.getParent());
  }

  /**
   * Determines if a node is a function expression that has an empty body.
   *
   * @param node a node
   * @return whether the given node is a function expression that is empty
   */
  static boolean isEmptyFunctionExpression(Node node) {
    return isFunctionExpression(node) && isEmptyBlock(node.getLastChild());
  }

  /**
   * Determines if a function takes a variable number of arguments by
   * looking for references to the "arguments" var_args object.
   */
  static boolean isVarArgsFunction(Node function) {
    // TODO(johnlenz): rename this function
    Preconditions.checkArgument(function.isFunction());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[229]++;
    return isNameReferenced(
        function.getLastChild(),
        "arguments",
        MATCH_NOT_FUNCTION);
  }

  /**
   * @return Whether node is a call to methodName.
   *    a.f(...)
   *    a['f'](...)
   */
  static boolean isObjectCallMethod(Node callNode, String methodName) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[230]++;
int CodeCoverConditionCoverageHelper_C93;
    if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((callNode.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[565]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[231]++;
      Node functionIndentifyingExpression = callNode.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[232]++;
int CodeCoverConditionCoverageHelper_C94;
      if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((isGet(functionIndentifyingExpression)) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[567]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[233]++;
        Node last = functionIndentifyingExpression.getLastChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[234]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((last != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((last.isString()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[569]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[235]++;
          String propName = last.getString();
          return (propName.equals(methodName));

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[570]++;}

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[568]++;}

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[566]++;}
    return false;
  }


  /**
   * @return Whether the callNode represents an expression in the form of:
   *    x.call(...)
   *    x['call'](...)
   */
  static boolean isFunctionObjectCall(Node callNode) {
    return isObjectCallMethod(callNode, "call");
  }

  /**
   * @return Whether the callNode represents an expression in the form of:
   *    x.apply(...)
   *    x['apply'](...)
   */
  static boolean isFunctionObjectApply(Node callNode) {
    return isObjectCallMethod(callNode, "apply");
  }

  /**
   * Determines whether this node is strictly on the left hand side of an assign
   * or var initialization. Notably, this does not include all L-values, only
   * statements where the node is used only as an L-value.
   *
   * @param n The node
   * @param parent Parent of the node
   * @return True if n is the left hand of an assign
   */
  static boolean isVarOrSimpleAssignLhs(Node n, Node parent) {
    return (parent.isAssign() && parent.getFirstChild() == n) ||
           parent.isVar();
  }

  /**
   * Determines whether this node is used as an L-value. Notice that sometimes
   * names are used as both L-values and R-values.
   *
   * We treat "var x;" as a pseudo-L-value, which kind of makes sense if you
   * treat it as "assignment to 'undefined' at the top of the scope". But if
   * we're honest with ourselves, it doesn't make sense, and we only do this
   * because it makes sense to treat this as syntactically similar to
   * "var x = 0;".
   *
   * @param n The node
   * @return True if n is an L-value.
   */
  public static boolean isLValue(Node n) {
    Preconditions.checkArgument(n.isName() || n.isGetProp() ||
        n.isGetElem());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[236]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[237]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[238]++;
int CodeCoverConditionCoverageHelper_C96;
    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[571]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[572]++;}
    return (NodeUtil.isAssignmentOp(parent) && parent.getFirstChild() == n)
        || (NodeUtil.isForIn(parent) && parent.getFirstChild() == n)
        || parent.isVar()
        || (parent.isFunction() && parent.getFirstChild() == n)
        || parent.isDec()
        || parent.isInc()
        || parent.isParamList()
        || parent.isCatch();
  }

  /**
   * Determines whether a node represents an object literal key
   * (e.g. key1 in {key1: value1, key2: value2}).
   *
   * @param node A node
   */
  static boolean isObjectLitKey(Node node) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[239]++;
    switch (node.getType()) {
      case Token.STRING_KEY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[573]++;
      case Token.GETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[574]++;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[575]++;
        return true; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[576]++;
    }
    return false;
  }

  /**
   * Get the name of an object literal key.
   *
   * @param key A node
   */
  static String getObjectLitKeyName(Node key) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[240]++;
    switch (key.getType()) {
      case Token.STRING_KEY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[577]++;
      case Token.GETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[578]++;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[579]++;
        return key.getString(); default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[580]++;
    }
    throw new IllegalStateException("Unexpected node type: " + key);
  }

  /**
   * @param key A OBJECTLIT key node.
   * @return The type expected when using the key.
   */
  static JSType getObjectLitKeyTypeFromValueType(Node key, JSType valueType) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[241]++;
int CodeCoverConditionCoverageHelper_C97;
    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((valueType != null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[581]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[242]++;
      switch (key.getType()) {
        case Token.GETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[583]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[243]++;
int CodeCoverConditionCoverageHelper_C98;
          // GET must always return a function type.
          if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((valueType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[584]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[244]++;
            FunctionType fntype = valueType.toMaybeFunctionType();
            valueType = fntype.getReturnType();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[245]++;

          } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[585]++;
            return null;
          }
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[246]++;
          break;
        case Token.SETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[586]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[247]++;
int CodeCoverConditionCoverageHelper_C99;
          if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((valueType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[587]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[248]++;
            // SET must always return a function type.
            FunctionType fntype = valueType.toMaybeFunctionType();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[249]++;
            Node param = fntype.getParametersNode().getFirstChild();
            // SET function must always have one parameter.
            valueType = param.getJSType();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[250]++;

          } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[588]++;
            return null;
          }
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[251]++;
          break; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[589]++;
      }

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[582]++;}
    return valueType;
  }

  /**
   * Determines whether a node represents an object literal get or set key
   * (e.g. key1 in {get key1() {}, set key2(a){}).
   *
   * @param node A node
   */
  static boolean isGetOrSetKey(Node node) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[252]++;
    switch (node.getType()) {
      case Token.GETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[590]++;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[591]++;
        return true; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[592]++;
    }
    return false;
  }

  /**
   * Converts an operator's token value (see {@link Token}) to a string
   * representation.
   *
   * @param operator the operator's token value to convert
   * @return the string representation or {@code null} if the token value is
   * not an operator
   */
  static String opToStr(int operator) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[253]++;
    switch (operator) {
      case Token.BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[593]++; return "|";
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[594]++; return "||";
      case Token.BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[595]++; return "^";
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[596]++; return "&&";
      case Token.BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[597]++; return "&";
      case Token.SHEQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[598]++; return "===";
      case Token.EQ:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[599]++; return "==";
      case Token.NOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[600]++; return "!";
      case Token.NE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[601]++; return "!=";
      case Token.SHNE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[602]++; return "!==";
      case Token.LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[603]++; return "<<";
      case Token.IN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[604]++; return "in";
      case Token.LE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[605]++; return "<=";
      case Token.LT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[606]++; return "<";
      case Token.URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[607]++; return ">>>";
      case Token.RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[608]++; return ">>";
      case Token.GE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[609]++; return ">=";
      case Token.GT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[610]++; return ">";
      case Token.MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[611]++; return "*";
      case Token.DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[612]++; return "/";
      case Token.MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[613]++; return "%";
      case Token.BITNOT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[614]++; return "~";
      case Token.ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[615]++; return "+";
      case Token.SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[616]++; return "-";
      case Token.POS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[617]++; return "+";
      case Token.NEG:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[618]++; return "-";
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[619]++; return "=";
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[620]++; return "|=";
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[621]++; return "^=";
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[622]++; return "&=";
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[623]++; return "<<=";
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[624]++; return ">>=";
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[625]++; return ">>>=";
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[626]++; return "+=";
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[627]++; return "-=";
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[628]++; return "*=";
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[629]++; return "/=";
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[630]++; return "%=";
      case Token.VOID:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[631]++; return "void";
      case Token.TYPEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[632]++; return "typeof";
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[633]++; return "instanceof";
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[634]++; return null;
    }
  }

  /**
   * Converts an operator's token value (see {@link Token}) to a string
   * representation or fails.
   *
   * @param operator the operator's token value to convert
   * @return the string representation
   * @throws Error if the token value is not an operator
   */
  static String opToStrNoFail(int operator) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[254]++;
    String res = opToStr(operator);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[255]++;
int CodeCoverConditionCoverageHelper_C100;
    if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((res == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[635]++;
      throw new Error("Unknown op " + operator + ": " +
                      Token.name(operator));

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[636]++;}
    return res;
  }

  /**
   * @return true if n or any of its children are of the specified type
   */
  static boolean containsType(Node node,
                              int type,
                              Predicate<Node> traverseChildrenPred) {
    return has(node, new MatchNodeType(type), traverseChildrenPred);
  }

  /**
   * @return true if n or any of its children are of the specified type
   */
  static boolean containsType(Node node, int type) {
    return containsType(node, type, Predicates.<Node>alwaysTrue());
  }


  /**
   * Given a node tree, finds all the VAR declarations in that tree that are
   * not in an inner scope. Then adds a new VAR node at the top of the current
   * scope that redeclares them, if necessary.
   */
  static void redeclareVarsInsideBranch(Node branch) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[256]++;
    Collection<Node> vars = getVarsDeclaredInBranch(branch);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[257]++;
int CodeCoverConditionCoverageHelper_C101;
    if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((vars.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[637]++;
      return;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[638]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[258]++;

    Node parent = getAddingRoot(branch);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[259]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[40]++;


    for (Node nameNode : vars) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[40]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[41]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[42]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[260]++;
      Node var = IR.var(
          IR.name(nameNode.getString())
              .srcref(nameNode))
          .srcref(nameNode);
      copyNameAnnotations(nameNode, var.getFirstChild());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[261]++;
      parent.addChildToFront(var);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[262]++;
    }
  }

  /**
   * Copy any annotations that follow a named value.
   * @param source
   * @param destination
   */
  static void copyNameAnnotations(Node source, Node destination) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[263]++;
int CodeCoverConditionCoverageHelper_C102;
    if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((source.getBooleanProp(Node.IS_CONSTANT_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[639]++;
      destination.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[264]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[640]++;}
  }

  /**
   * Gets a Node at the top of the current scope where we can add new var
   * declarations as children.
   */
  private static Node getAddingRoot(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[265]++;
    Node addingRoot = null;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[266]++;
    Node ancestor = n;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[267]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[43]++;


    while (null != (ancestor = ancestor.getParent())) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[43]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[44]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[45]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[268]++;
      int type = ancestor.getType();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[269]++;
int CodeCoverConditionCoverageHelper_C104;
      if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((type == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[641]++;
        addingRoot = ancestor;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[270]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[271]++;
        break;

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[642]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[272]++;
int CodeCoverConditionCoverageHelper_C105; if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((type == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[643]++;
        addingRoot = ancestor.getLastChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[273]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[274]++;
        break;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[644]++;}
}
    }

    // make sure that the adding root looks ok
    Preconditions.checkState(addingRoot.isBlock() ||
        addingRoot.isScript());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[275]++;
    Preconditions.checkState(addingRoot.getFirstChild() == null ||
        !addingRoot.getFirstChild().isScript());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[276]++;
    return addingRoot;
  }

  /**
   * Creates a node representing a qualified name.
   *
   * @param name A qualified name (e.g. "foo" or "foo.bar.baz")
   * @return A NAME or GETPROP node
   */
  public static Node newQualifiedNameNode(
      CodingConvention convention, String name) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[277]++;
    int endPos = name.indexOf('.');
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[278]++;
int CodeCoverConditionCoverageHelper_C106;
    if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((endPos == -1) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[645]++;
      return newName(convention, name);

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[646]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[279]++;
    Node node = newName(convention, name.substring(0, endPos));
    int startPos;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[280]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[46]++;


int CodeCoverConditionCoverageHelper_C107;
    do {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[46]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[47]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[48]++;
}
      startPos = endPos + 1;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[281]++;
      endPos = name.indexOf('.', startPos);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[282]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[283]++;
      String part = (endPos == -1
                     ? name.substring(startPos)
                     : name.substring(startPos, endPos));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[284]++;
      Node propNode = IR.string(part);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[285]++;
int CodeCoverConditionCoverageHelper_C108;
      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((convention.isConstantKey(part)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[647]++;
        propNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[286]++;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[648]++;}
      node = IR.getprop(node, propNode);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[287]++;
    } while ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((endPos != -1) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false));

    return node;
  }

  /**
   * Creates a node representing a qualified name, copying over the source
   * location information from the basis node and assigning the given original
   * name to the node.
   *
   * @param name A qualified name (e.g. "foo" or "foo.bar.baz")
   * @param basisNode The node that represents the name as currently found in
   *     the AST.
   * @param originalName The original name of the item being represented by the
   *     NAME node. Used for debugging information.
   *
   * @return A NAME or GETPROP node
   */
  static Node newQualifiedNameNode(
      CodingConvention convention, String name, Node basisNode,
      String originalName) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[288]++;
    Node node = newQualifiedNameNode(convention, name);
    setDebugInformation(node, basisNode, originalName);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[289]++;
    return node;
  }

  /**
   * Gets the root node of a qualified name. Must be either NAME or THIS.
   */
  static Node getRootOfQualifiedName(Node qName) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[290]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[49]++;


    for (Node current = qName;true;
         current = current.getFirstChild()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[49]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[50]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[51]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[291]++;
int CodeCoverConditionCoverageHelper_C110;
      if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (8)) == 0 || true) &&
 ((current.isName()) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((current.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[649]++;
        return current;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[650]++;}
      Preconditions.checkState(current.isGetProp());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[292]++;
    }
  }

  /**
   * Sets the debug information (source file info and original name)
   * on the given node.
   *
   * @param node The node on which to set the debug information.
   * @param basisNode The basis node from which to copy the source file info.
   * @param originalName The original name of the node.
   */
  static void setDebugInformation(Node node, Node basisNode,
                                  String originalName) {
    node.copyInformationFromForTree(basisNode);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[293]++;
    node.putProp(Node.ORIGINALNAME_PROP, originalName);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[294]++;
  }

  private static Node newName(
      CodingConvention convention, String name) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[295]++;
    Node nameNode = IR.name(name);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[296]++;
int CodeCoverConditionCoverageHelper_C111;
    if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((convention.isConstant(name)) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[651]++;
      nameNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[297]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[652]++;}
    return nameNode;
  }

  /**
   * Creates a new node representing an *existing* name, copying over the source
   * location information from the basis node.
   *
   * @param name The name for the new NAME node.
   * @param srcref The node that represents the name as currently found in
   *     the AST.
   *
   * @return The node created.
   */
  static Node newName(CodingConvention convention, String name, Node srcref) {
    return newName(convention, name).srcref(srcref);
  }

  /**
   * Creates a new node representing an *existing* name, copying over the source
   * location information from the basis node and assigning the given original
   * name to the node.
   *
   * @param name The name for the new NAME node.
   * @param basisNode The node that represents the name as currently found in
   *     the AST.
   * @param originalName The original name of the item being represented by the
   *     NAME node. Used for debugging information.
   *
   * @return The node created.
   */
  static Node newName(
      CodingConvention convention, String name,
      Node basisNode, String originalName) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[298]++;
    Node nameNode = newName(convention, name, basisNode);
    nameNode.putProp(Node.ORIGINALNAME_PROP, originalName);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[299]++;
    return nameNode;
  }

  /** Test if all characters in the string are in the Basic Latin (aka ASCII)
   * character set - that they have UTF-16 values equal to or below 0x7f.
   * This check can find which identifiers with Unicode characters need to be
   * escaped in order to allow resulting files to be processed by non-Unicode
   * aware UNIX tools and editors.
   * *
   * See http://en.wikipedia.org/wiki/Latin_characters_in_Unicode
   * for more on Basic Latin.
   *
   * @param s The string to be checked for ASCII-goodness.
   *
   * @return True if all characters in the string are in Basic Latin set.
   */
  static boolean isLatin(String s) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[300]++;
    int len = s.length();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[301]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[52]++;


int CodeCoverConditionCoverageHelper_C112;
    for (int index = 0;(((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((index < len) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[52]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[53]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[54]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[302]++;
      char c = s.charAt(index);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[303]++;
int CodeCoverConditionCoverageHelper_C113;
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((c > LARGEST_BASIC_LATIN) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[653]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[654]++;}
    }
    return true;
  }

  /**
   * Determines whether the given name is a valid variable name.
   */
  static boolean isValidSimpleName(String name) {
    return TokenStream.isJSIdentifier(name) &&
        !TokenStream.isKeyword(name) &&
        // no Unicode escaped characters - some browsers are less tolerant
        // of Unicode characters that might be valid according to the
        // language spec.
        // Note that by this point, Unicode escapes have been converted
        // to UTF-16 characters, so we're only searching for character
        // values, not escapes.
        isLatin(name);
  }

  /**
   * Determines whether the given name is a valid qualified name.
   */
  // TODO(nicksantos): This should be moved into a "Language" API,
  // so that the results are different for es5 and es3.
  public static boolean isValidQualifiedName(String name) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[304]++;
int CodeCoverConditionCoverageHelper_C114;
    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8)) == 0 || true) &&
 ((name.endsWith(".")) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((name.startsWith(".")) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[655]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[656]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[305]++;
    String[] parts = name.split("\\.");
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[306]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[55]++;


    for (String part : parts) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[55]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[56]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[57]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[307]++;
int CodeCoverConditionCoverageHelper_C115;
      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((isValidSimpleName(part)) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[657]++;
        return false;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[658]++;}
    }
    return true;
  }

  /**
   * Determines whether the given name can appear on the right side of
   * the dot operator. Many properties (like reserved words) cannot.
   */
  static boolean isValidPropertyName(String name) {
    return isValidSimpleName(name);
  }

  private static class VarCollector implements Visitor {
    final Map<String, Node> vars = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[308]++;
  }

    @Override
    public void visit(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[309]++;
int CodeCoverConditionCoverageHelper_C116;
      if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[659]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[310]++;
        Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[311]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[661]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[312]++;
          String name = n.getString();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[313]++;
int CodeCoverConditionCoverageHelper_C118;
          if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((vars.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[663]++;
            vars.put(name, n);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[314]++;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[664]++;}

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[662]++;}

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[660]++;}
    }
  }

  /**
   * Retrieves vars declared in the current node tree, excluding descent scopes.
   */
  static Collection<Node> getVarsDeclaredInBranch(Node root) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[315]++;
    VarCollector collector = new VarCollector();
    visitPreOrder(
        root,
        collector,
        MATCH_NOT_FUNCTION);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[316]++;
    return collector.vars.values();
  }

  /**
   * @return {@code true} if the node an assignment to a prototype property of
   *     some constructor.
   */
  static boolean isPrototypePropertyDeclaration(Node n) {
    return isExprAssign(n) &&
        isPrototypeProperty(n.getFirstChild().getFirstChild());
  }

  /**
   * @return Whether the node represents a qualified prototype property.
   */
  static boolean isPrototypeProperty(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[317]++;
    String lhsString = n.getQualifiedName();
    return lhsString != null && lhsString.contains(".prototype.");
  }

  /**
   * @return The class name part of a qualified prototype name.
   */
  static Node getPrototypeClassName(Node qName) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[318]++;
    Node cur = qName;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[319]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[58]++;


int CodeCoverConditionCoverageHelper_C119;
    while ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((cur.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[58]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[59]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[60]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[320]++;
int CodeCoverConditionCoverageHelper_C120;
      if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((cur.getLastChild().getString().equals("prototype")) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[665]++;
        return cur.getFirstChild();

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[666]++;
        cur = cur.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[321]++;
      }
    }
    return null;
  }

  /**
   * @return The string property name part of a qualified prototype name.
   */
  static String getPrototypePropertyName(Node qName) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[322]++;
    String qNameStr = qName.getQualifiedName();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[323]++;
    int prototypeIdx = qNameStr.lastIndexOf(".prototype.");
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[324]++;
    int memberIndex = prototypeIdx + ".prototype".length() + 1;
    return qNameStr.substring(memberIndex);
  }

  /**
   * Create a node for an empty result expression:
   *   "void 0"
   */
  static Node newUndefinedNode(Node srcReferenceNode) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[325]++;
    Node node = IR.voidNode(IR.number(0));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[326]++;
int CodeCoverConditionCoverageHelper_C121;
    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((srcReferenceNode != null) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[667]++;
        node.copyInformationFromForTree(srcReferenceNode);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[327]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[668]++;}
    return node;
  }

  /**
   * Create a VAR node containing the given name and initial value expression.
   */
  static Node newVarNode(String name, Node value) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[328]++;
    Node nodeName = IR.name(name);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[329]++;
int CodeCoverConditionCoverageHelper_C122;
    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[669]++;
      Preconditions.checkState(value.getNext() == null);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[330]++;
      nodeName.addChildToBack(value);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[331]++;
      nodeName.srcref(value);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[332]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[670]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[333]++;
    Node var = IR.var(nodeName).srcref(nodeName);

    return var;
  }

  /**
   * A predicate for matching name nodes with the specified node.
   */
  private static class MatchNameNode implements Predicate<Node>{
    final String name;

    MatchNameNode(String name){
      this.name = name;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[334]++;
    }

    @Override
    public boolean apply(Node n) {
      return n.isName() && n.getString().equals(name);
    }
  }

  /**
   * A predicate for matching nodes with the specified type.
   */
  static class MatchNodeType implements Predicate<Node>{
    final int type;

    MatchNodeType(int type){
      this.type = type;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[335]++;
    }

    @Override
    public boolean apply(Node n) {
      return n.getType() == type;
    }
  }


  /**
   * A predicate for matching var or function declarations.
   */
  static class MatchDeclaration implements Predicate<Node> {
    @Override
    public boolean apply(Node n) {
      return isFunctionDeclaration(n) || n.isVar();
    }
  }

  /**
   * A predicate for matching anything except function nodes.
   */
  private static class MatchNotFunction implements Predicate<Node>{
    @Override
    public boolean apply(Node n) {
      return !n.isFunction();
    }
  }

  static final Predicate<Node> MATCH_NOT_FUNCTION = new MatchNotFunction();
  static {
    CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[336]++;
  }

  /**
   * A predicate for matching statements without exiting the current scope.
   */
  static class MatchShallowStatement implements Predicate<Node>{
    @Override
    public boolean apply(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[337]++;
      Node parent = n.getParent();
      return n.isBlock()
          || (!n.isFunction() && (parent == null
              || isControlStructure(parent)
              || isStatementBlock(parent)));
    }
  }

  /**
   * Finds the number of times a type is referenced within the node tree.
   */
  static int getNodeTypeReferenceCount(
      Node node, int type, Predicate<Node> traverseChildrenPred) {
    return getCount(node, new MatchNodeType(type), traverseChildrenPred);
  }

  /**
   * Whether a simple name is referenced within the node tree.
   */
  static boolean isNameReferenced(Node node,
                                  String name,
                                  Predicate<Node> traverseChildrenPred) {
    return has(node, new MatchNameNode(name), traverseChildrenPred);
  }

  /**
   * Whether a simple name is referenced within the node tree.
   */
  static boolean isNameReferenced(Node node, String name) {
    return isNameReferenced(node, name, Predicates.<Node>alwaysTrue());
  }

  /**
   * Finds the number of times a simple name is referenced within the node tree.
   */
  static int getNameReferenceCount(Node node, String name) {
    return getCount(
        node, new MatchNameNode(name), Predicates.<Node>alwaysTrue());
  }

  /**
   * @return Whether the predicate is true for the node or any of its children.
   */
  static boolean has(Node node,
                     Predicate<Node> pred,
                     Predicate<Node> traverseChildrenPred) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[338]++;
int CodeCoverConditionCoverageHelper_C123;
    if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((pred.apply(node)) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[671]++;
      return true;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[672]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[339]++;
int CodeCoverConditionCoverageHelper_C124;

    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((traverseChildrenPred.apply(node)) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[673]++;
      return false;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[674]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[340]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[61]++;


int CodeCoverConditionCoverageHelper_C125;

    for (Node c = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[61]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[62]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[63]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[341]++;
int CodeCoverConditionCoverageHelper_C126;
      if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((has(c, pred, traverseChildrenPred)) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[675]++;
        return true;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[676]++;}
    }

    return false;
  }

  /**
   * @return The number of times the the predicate is true for the node
   * or any of its children.
   */
  static int getCount(
      Node n, Predicate<Node> pred, Predicate<Node> traverseChildrenPred) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[342]++;
    int total = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[343]++;
int CodeCoverConditionCoverageHelper_C127;

    if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((pred.apply(n)) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[677]++;
      total++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[344]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[678]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[345]++;
int CodeCoverConditionCoverageHelper_C128;

    if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((traverseChildrenPred.apply(n)) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[679]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[346]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[64]++;


int CodeCoverConditionCoverageHelper_C129;
      for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[64]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[65]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[66]++;
}
        total += getCount(c, pred, traverseChildrenPred);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[347]++;
      }

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[680]++;}

    return total;
  }

  /**
   * Interface for use with the visit method.
   * @see #visit
   */
  static interface Visitor {
    void visit(Node node);
  }

  /**
   * A pre-order traversal, calling Visitor.visit for each child matching
   * the predicate.
   */
  static void visitPreOrder(Node node,
                     Visitor visitor,
                     Predicate<Node> traverseChildrenPred) {
    visitor.visit(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[348]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[349]++;
int CodeCoverConditionCoverageHelper_C130;

    if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((traverseChildrenPred.apply(node)) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[681]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[350]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[67]++;


int CodeCoverConditionCoverageHelper_C131;
      for (Node c = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[67]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[68]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[69]++;
}
        visitPreOrder(c, visitor, traverseChildrenPred);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[351]++;
      }

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[682]++;}
  }

  /**
   * A post-order traversal, calling Visitor.visit for each child matching
   * the predicate.
   */
  static void visitPostOrder(Node node,
                     Visitor visitor,
                     Predicate<Node> traverseChildrenPred) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[352]++;
int CodeCoverConditionCoverageHelper_C132;
    if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((traverseChildrenPred.apply(node)) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[683]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[353]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[70]++;


int CodeCoverConditionCoverageHelper_C133;
      for (Node c = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[70]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[71]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[72]++;
}
        visitPostOrder(c, visitor, traverseChildrenPred);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[354]++;
      }

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[684]++;}

    visitor.visit(node);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[355]++;
  }

  /**
   * @return Whether a TRY node has a finally block.
   */
  static boolean hasFinally(Node n) {
    Preconditions.checkArgument(n.isTry());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[356]++;
    return n.getChildCount() == 3;
  }

  /**
   * @return The BLOCK node containing the CATCH node (if any)
   * of a TRY.
   */
  static Node getCatchBlock(Node n) {
    Preconditions.checkArgument(n.isTry());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[357]++;
    return n.getFirstChild().getNext();
  }

  /**
   * @return Whether BLOCK (from a TRY node) contains a CATCH.
   * @see NodeUtil#getCatchBlock
   */
  static boolean hasCatchHandler(Node n) {
    Preconditions.checkArgument(n.isBlock());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[358]++;
    return n.hasChildren() && n.getFirstChild().isCatch();
  }

  /**
    * @param fnNode The function.
    * @return The Node containing the Function parameters.
    */
  public static Node getFunctionParameters(Node fnNode) {
    // Function NODE: [ FUNCTION -> NAME, LP -> ARG1, ARG2, ... ]
    Preconditions.checkArgument(fnNode.isFunction());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[359]++;
    return fnNode.getFirstChild().getNext();
  }

  /**
   * <p>Determines whether a variable is constant:
   * <ol>
   * <li>In Normalize, any name that matches the
   *     {@link CodingConvention#isConstant(String)} is annotated with an
   *     IS_CONSTANT_NAME property.
   * </ol>
   *
   * @param node A NAME or STRING node
   * @return True if a name node represents a constant variable
   */
  static boolean isConstantName(Node node) {
    return node.getBooleanProp(Node.IS_CONSTANT_NAME);
  }

  /** Whether the given name is constant by coding convention. */
  static boolean isConstantByConvention(
      CodingConvention convention, Node node, Node parent) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[360]++;
int CodeCoverConditionCoverageHelper_C134;
    if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (8)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((node == parent.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[685]++;
      return convention.isConstantKey(node.getString());

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[686]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[361]++;
int CodeCoverConditionCoverageHelper_C135; if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((isObjectLitKey(node)) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[687]++;
      return convention.isConstantKey(node.getString());

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[688]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[362]++;
int CodeCoverConditionCoverageHelper_C136; if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[689]++;
      return convention.isConstant(node.getString());

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[690]++;}
}
}
    return false;
  }

  /**
   * Get the JSDocInfo for a function.
   */
  public static JSDocInfo getFunctionJSDocInfo(Node n) {
    Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[363]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[364]++;
    JSDocInfo fnInfo = n.getJSDocInfo();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[365]++;
int CodeCoverConditionCoverageHelper_C137;
    if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (8)) == 0 || true) &&
 ((fnInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[691]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[366]++;
      // Look for the info on other nodes.
      Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[367]++;
int CodeCoverConditionCoverageHelper_C138;
      if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[693]++;
        // on ASSIGNs
        fnInfo = parent.getJSDocInfo();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[368]++;

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[694]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[369]++;
int CodeCoverConditionCoverageHelper_C139; if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[695]++;
        // on var NAME = function() { ... };
        fnInfo = parent.getParent().getJSDocInfo();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[370]++;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[696]++;}
}

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[692]++;}
    return fnInfo;
  }

  /**
   * @param n The node.
   * @return The source name property on the node or its ancestors.
   */
  public static String getSourceName(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[371]++;
    String sourceName = null;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[372]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[73]++;


int CodeCoverConditionCoverageHelper_C140;
    while ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 2) && false)) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[73]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[74]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[75]++;
}
      sourceName = n.getSourceFileName();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[373]++;
      n = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[374]++;
    }
    return sourceName;
  }

  /**
   * @param n The node.
   * @return The source name property on the node or its ancestors.
   */
  public static StaticSourceFile getSourceFile(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[375]++;
    StaticSourceFile sourceName = null;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[376]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[76]++;


int CodeCoverConditionCoverageHelper_C141;
    while ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((sourceName == null) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false)) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[76]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[77]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[78]++;
}
      sourceName = n.getStaticSourceFile();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[377]++;
      n = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[378]++;
    }
    return sourceName;
  }

  /**
   * @param n The node.
   * @return The InputId property on the node or its ancestors.
   */
  public static InputId getInputId(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[379]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[79]++;


int CodeCoverConditionCoverageHelper_C142;
    while ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) && false)) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[79]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[80]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[81]++;
}
      n = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[380]++;
    }

    return (n != null && n.isScript()) ? n.getInputId() : null;
  }

  /**
   * A new CALL node with the "FREE_CALL" set based on call target.
   */
  static Node newCallNode(Node callTarget, Node... parameters) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[381]++;
    boolean isFreeCall = !isGet(callTarget);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[382]++;
    Node call = IR.call(callTarget);
    call.putBooleanProp(Node.FREE_CALL, isFreeCall);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[383]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[384]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[82]++;


    for (Node parameter : parameters) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[82]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[83]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[84]++;
}
      call.addChildToBack(parameter);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[385]++;
    }
    return call;
  }

  /**
   * @return Whether the node is known to be a value that is not referenced
   * elsewhere.
   */
  static boolean evaluatesToLocalValue(Node value) {
    return evaluatesToLocalValue(value, Predicates.<Node>alwaysFalse());
  }

  /**
   * @param locals A predicate to apply to unknown local values.
   * @return Whether the node is known to be a value that is not a reference
   *     outside the expression scope.
   */
  static boolean evaluatesToLocalValue(Node value, Predicate<Node> locals) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[386]++;
    switch (value.getType()) {
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[697]++;
        return evaluatesToLocalValue(value.getFirstChild(), locals);
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[698]++;
        // A result that is aliased by a non-local name, is the effectively the
        // same as returning a non-local name, but this doesn't matter if the
        // value is immutable.
        return NodeUtil.isImmutableValue(value.getLastChild())
            || (locals.apply(value)
                && evaluatesToLocalValue(value.getLastChild(), locals));
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[699]++;
        return evaluatesToLocalValue(value.getLastChild(), locals);
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[700]++;
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[701]++;
        return evaluatesToLocalValue(value.getFirstChild(), locals)
           && evaluatesToLocalValue(value.getLastChild(), locals);
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[702]++;
        return evaluatesToLocalValue(value.getFirstChild().getNext(), locals)
           && evaluatesToLocalValue(value.getLastChild(), locals);
      case Token.INC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[703]++;
      case Token.DEC:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[704]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[387]++;
int CodeCoverConditionCoverageHelper_C143;
        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((value.getBooleanProp(Node.INCRDECR_PROP)) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[705]++;
          return evaluatesToLocalValue(value.getFirstChild(), locals);

        } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[706]++;
          return true;
        }
      case Token.THIS:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[707]++;
        return locals.apply(value);
      case Token.NAME:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[708]++;
        return isImmutableValue(value) || locals.apply(value);
      case Token.GETELEM:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[709]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[710]++;
        // There is no information about the locality of object properties.
        return locals.apply(value);
      case Token.CALL:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[711]++;
        return callHasLocalResult(value)
            || isToStringMethodCall(value)
            || locals.apply(value);
      case Token.NEW:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[712]++;
        return newHasLocalResult(value)
               || locals.apply(value);
      case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[713]++;
      case Token.REGEXP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[714]++;
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[715]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[716]++;
        // Literals objects with non-literal children are allowed.
        return true;
      case Token.DELPROP:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[717]++;
      case Token.IN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[718]++;
        // TODO(johnlenz): should IN operator be included in #isSimpleOperator?
        return true;
      default:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[719]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[388]++;
int CodeCoverConditionCoverageHelper_C144;
        // Other op force a local value:
        //  x = '' + g (x is now an local string)
        //  x -= g (x is now an local number)
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (32)) == 0 || true) &&
 ((isAssignmentOp(value)) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 ((isSimpleOperator(value)) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((isImmutableValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 3) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 3) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[720]++;
          return true;

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[721]++;}

        throw new IllegalStateException(
            "Unexpected expression node" + value +
            "\n parent:" + value.getParent());
    }
  }

  /**
   * Given the first sibling, this returns the nth
   * sibling or null if no such sibling exists.
   * This is like "getChildAtIndex" but returns null for non-existent indexes.
   */
  private static Node getNthSibling(Node first, int index) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[389]++;
    Node sibling = first;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[390]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[85]++;


int CodeCoverConditionCoverageHelper_C145;
    while ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (8)) == 0 || true) &&
 ((index != 0) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((sibling != null) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 2) && false)) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[85]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[86]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[87]++;
}
      sibling = sibling.getNext();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[391]++;
      index--;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[392]++;
    }
    return sibling;
  }

  /**
   * Given the function, this returns the nth
   * argument or null if no such parameter exists.
   */
  static Node getArgumentForFunction(Node function, int index) {
    Preconditions.checkState(function.isFunction());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[393]++;
    return getNthSibling(
        function.getFirstChild().getNext().getFirstChild(), index);
  }

  /**
   * Given the new or call, this returns the nth
   * argument of the call or null if no such argument exists.
   */
  static Node getArgumentForCallOrNew(Node call, int index) {
    Preconditions.checkState(isCallOrNew(call));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[394]++;
    return getNthSibling(
      call.getFirstChild().getNext(), index);
  }

  /**
   * Returns whether this is a target of a call or new.
   */
  static boolean isCallOrNewTarget(Node target) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[395]++;
    Node parent = target.getParent();
    return parent != null
        && NodeUtil.isCallOrNew(parent)
        && parent.getFirstChild() == target;
  }

  private static boolean isToStringMethodCall(Node call) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[396]++;
    Node getNode = call.getFirstChild();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[397]++;
int CodeCoverConditionCoverageHelper_C146;
    if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((isGet(getNode)) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[722]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[398]++;
      Node propNode = getNode.getLastChild();
      return propNode.isString() && "toString".equals(propNode.getString());

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[723]++;}
    return false;
  }

  /** Find the best JSDoc for the given node. */
  static JSDocInfo getBestJSDocInfo(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[399]++;
    JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[400]++;
int CodeCoverConditionCoverageHelper_C147;
    if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[724]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[401]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[402]++;
int CodeCoverConditionCoverageHelper_C148;
      if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[726]++;
        return null;

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[727]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[403]++;
int CodeCoverConditionCoverageHelper_C149;

      if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[728]++;
        return getBestJSDocInfo(parent);

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[729]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[404]++;
int CodeCoverConditionCoverageHelper_C150; if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[730]++;
        return getBestJSDocInfo(parent);

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[731]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[405]++;
int CodeCoverConditionCoverageHelper_C151; if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((isObjectLitKey(parent)) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[732]++;
        return parent.getJSDocInfo();

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[733]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[406]++;
int CodeCoverConditionCoverageHelper_C152; if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[734]++;
        return parent.getJSDocInfo();

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[735]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[407]++;
int CodeCoverConditionCoverageHelper_C153; if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (8)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((parent.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[736]++;
        return parent.getJSDocInfo();

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[737]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[408]++;
int CodeCoverConditionCoverageHelper_C154; if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C154 |= (2048)) == 0 || true) &&
 ((parent.isHook()) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C154 |= (512)) == 0 || true) &&
 ((parent.getFirstChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (256)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C154 |= (128)) == 0 || true) &&
 ((parent.isOr()) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C154 |= (32)) == 0 || true) &&
 ((parent.isAnd()) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C154 |= (8)) == 0 || true) &&
 ((parent.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 6) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 6) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[738]++;
        return getBestJSDocInfo(parent);

      } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[739]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[409]++;
int CodeCoverConditionCoverageHelper_C155; if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((parent.isCast()) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[740]++;
        return parent.getJSDocInfo();

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[741]++;}
}
}
}
}
}
}

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[725]++;}
    return info;
  }

  /** Find the l-value that the given r-value is being assigned to. */
  static Node getBestLValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[410]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[411]++;
    boolean isFunctionDeclaration = isFunctionDeclaration(n);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[412]++;
int CodeCoverConditionCoverageHelper_C156;
    if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((isFunctionDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[742]++;
      return n.getFirstChild();

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[743]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[413]++;
int CodeCoverConditionCoverageHelper_C157; if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[744]++;
      return parent;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[745]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[414]++;
int CodeCoverConditionCoverageHelper_C158; if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[746]++;
      return parent.getFirstChild();

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[747]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[415]++;
int CodeCoverConditionCoverageHelper_C159; if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((isObjectLitKey(parent)) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[748]++;
      return parent;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[749]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[416]++;
int CodeCoverConditionCoverageHelper_C160; if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C160 |= (2048)) == 0 || true) &&
 ((parent.isHook()) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C160 |= (512)) == 0 || true) &&
 ((parent.getFirstChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (256)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C160 |= (128)) == 0 || true) &&
 ((parent.isOr()) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C160 |= (32)) == 0 || true) &&
 ((parent.isAnd()) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C160 |= (8)) == 0 || true) &&
 ((parent.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 6) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 6) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[750]++;
      return getBestLValue(parent);

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[751]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[417]++;
int CodeCoverConditionCoverageHelper_C161; if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((parent.isCast()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[752]++;
      return getBestLValue(parent);

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[753]++;}
}
}
}
}
}
    return null;
  }

  /** Gets the r-value of a node returned by getBestLValue. */
  static Node getRValueOfLValue(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[418]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[419]++;
    switch (parent.getType()) {
      case Token.ASSIGN:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[754]++;
        return n.getNext();
      case Token.VAR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[755]++;
        return n.getFirstChild();
      case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[756]++;
        return parent; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[757]++;
    }
    return null;
  }

  /** Get the owner of the given l-value node. */
  static Node getBestLValueOwner(@Nullable Node lValue) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[420]++;
int CodeCoverConditionCoverageHelper_C162;
    if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((lValue == null) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((lValue.getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[758]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[759]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[421]++;
int CodeCoverConditionCoverageHelper_C163;
    if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((isObjectLitKey(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[760]++;
      return getBestLValue(lValue.getParent());

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[761]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[422]++;
int CodeCoverConditionCoverageHelper_C164; if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((isGet(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[762]++;
      return lValue.getFirstChild();

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[763]++;}
}

    return null;
  }

  /** Get the name of the given l-value node. */
  static String getBestLValueName(@Nullable Node lValue) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[423]++;
int CodeCoverConditionCoverageHelper_C165;
    if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (8)) == 0 || true) &&
 ((lValue == null) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((lValue.getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[764]++;
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[765]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[424]++;
int CodeCoverConditionCoverageHelper_C166;
    if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((isObjectLitKey(lValue)) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[766]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[425]++;
      Node owner = getBestLValue(lValue.getParent());
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[426]++;
int CodeCoverConditionCoverageHelper_C167;
      if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((owner != null) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[768]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[427]++;
        String ownerName = getBestLValueName(owner);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[428]++;
int CodeCoverConditionCoverageHelper_C168;
        if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((ownerName != null) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[770]++;
          return ownerName + "." + getObjectLitKeyName(lValue);

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[771]++;}

      } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[769]++;}
      return null;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[767]++;}
    return lValue.getQualifiedName();
  }

  /**
   * @returns false iff the result of the expression is not consumed.
   */
  static boolean isExpressionResultUsed(Node expr) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[429]++;
    // TODO(johnlenz): consider sharing some code with trySimpleUnusedResult.
    Node parent = expr.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[430]++;
    switch (parent.getType()) {
      case Token.BLOCK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[772]++;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[773]++;
        return false;
      case Token.CAST:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[774]++;
        return isExpressionResultUsed(parent);
      case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[775]++;
      case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[776]++;
      case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[777]++;
        return (expr == parent.getFirstChild())
            ? true : isExpressionResultUsed(parent);
      case Token.COMMA:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[778]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[431]++;
        Node gramps = parent.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[432]++;
int CodeCoverConditionCoverageHelper_C169;
        if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (8)) == 0 || true) &&
 ((gramps.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((parent == gramps.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[779]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[433]++;
int CodeCoverConditionCoverageHelper_C170;
          // Semantically, a direct call to eval is different from an indirect
          // call to an eval. See ECMA-262 S15.1.2.1. So it's OK for the first
          // expression to a comma to be a no-op if it's used to indirect
          // an eval. This we pretend that this is "used".
          if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (128)) == 0 || true) &&
 ((expr == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C170 |= (32)) == 0 || true) &&
 ((parent.getChildCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C170 |= (8)) == 0 || true) &&
 ((expr.getNext().isName()) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 (("eval".equals(expr.getNext().getString())) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 4) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 4) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[781]++;
            return true;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[782]++;}

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[780]++;}

        return (expr == parent.getFirstChild())
            ? false : isExpressionResultUsed(parent);
      case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[783]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[434]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[784]++;
          // Only an expression whose result is in the condition part of the
          // expression is used.
          return (parent.getChildAtIndex(1) == expr);

        } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[785]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[435]++;
        break; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[786]++;
    }
    return true;
  }

  /**
   * @param n The expression to check.
   * @return Whether the expression is unconditionally executed only once in the
   *     containing execution scope.
   */
  static boolean isExecutedExactlyOnce(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[436]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[88]++;


    inspect: do {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[88]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[89]--;
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.loops[90]++;
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[437]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[438]++;
      switch (parent.getType()) {
        case Token.IF:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[787]++;
        case Token.HOOK:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[788]++;
        case Token.AND:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[789]++;
        case Token.OR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[790]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[439]++;
int CodeCoverConditionCoverageHelper_C173;
          if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[791]++;
            return false;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[792]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[440]++;
          // other ancestors may be conditional
          continue inspect;
        case Token.FOR:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[793]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[441]++;
int CodeCoverConditionCoverageHelper_C174;
          if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[794]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[442]++;
int CodeCoverConditionCoverageHelper_C175;
            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((parent.getChildAtIndex(1) != n) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[796]++;
              return false;

            } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[797]++;}

          } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[795]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[443]++;
int CodeCoverConditionCoverageHelper_C176;
            if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[798]++;
              return false;

            } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[799]++;}
          }
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[444]++;
          // other ancestors may be conditional
          continue inspect;
        case Token.WHILE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[800]++;
        case Token.DO:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[801]++;
          return false;
        case Token.TRY:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[802]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[445]++;
int CodeCoverConditionCoverageHelper_C177;
          // Consider all code under a try/catch to be conditionally executed.
          if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C177 |= (8)) == 0 || true) &&
 ((hasFinally(parent)) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((parent.getLastChild() != n) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 2) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 2) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[803]++;
            return false;

          } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[804]++;}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[446]++;
          continue inspect;
        case Token.CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[805]++;
        case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[806]++;
          return false;
        case Token.SCRIPT:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[807]++;
        case Token.FUNCTION:
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[808]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[447]++;
          // Done, we've reached the scope root.
          break inspect; default : CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[809]++;
      }
    } while ((n = n.getParent()) != null);
    return true;
  }

  /**
   * @return An appropriate AST node for the boolean value.
   */
  static Node booleanNode(boolean value) {
    return value ? IR.trueNode() : IR.falseNode();
  }

  /**
   * @return An appropriate AST node for the double value.
   */
  static Node numberNode(double value, Node srcref) {
    Node result;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[448]++;
int CodeCoverConditionCoverageHelper_C178;
    if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((Double.isNaN(value)) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[810]++;
      result = IR.name("NaN");
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[449]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[811]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[450]++;
int CodeCoverConditionCoverageHelper_C179; if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((value == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[812]++;
      result = IR.name("Infinity");
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[451]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[813]++;
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[452]++;
int CodeCoverConditionCoverageHelper_C180; if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((value == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[814]++;
      result = IR.neg(IR.name("Infinity"));
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[453]++;

    } else {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[815]++;
      result = IR.number(value);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[454]++;
    }
}
}
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[455]++;
int CodeCoverConditionCoverageHelper_C181;
    if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((srcref != null) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[816]++;
      result.srcrefTree(srcref);
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[456]++;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[817]++;}
    return result;
  }

  static boolean isNaN(Node n) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.statements[457]++;
int CodeCoverConditionCoverageHelper_C182;
    if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C182 |= (8192)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (4096)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C182 |= (2048)) == 0 || true) &&
 ((n.getString().equals("NaN")) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1024)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C182 |= (512)) == 0 || true) &&
 ((n.getType() == Token.DIV) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C182 |= (128)) == 0 || true) &&
 ((n.getFirstChild().isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C182 |= (32)) == 0 || true) &&
 ((n.getFirstChild().getDouble() == 0) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C182 |= (8)) == 0 || true) &&
 ((n.getLastChild().isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((n.getLastChild().getDouble() == 0) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 7) || true)) || (CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 7) && false)) {
CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[818]++;
      return true;

    } else {
  CodeCoverCoverageCounter$gqhemrjldeicuv9al10h.branches[819]++;}
    return false;
  }
}

class CodeCoverCoverageCounter$gqhemrjldeicuv9al10h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gqhemrjldeicuv9al10h ());
  }
    public static long[] statements = new long[458];
    public static long[] branches = new long[820];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[183];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NodeUtil.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,3,1,1,1,1,1,1,1,1,1,3,1,1,1,3,3,3,2,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,2,2,1,1,2,2,2,3,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,2,2,1,1,1,1,1,2,1,1,1,1,1,1,1,0,1,1,1,1,1,0,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,2,2,1,3,2,1,1,1,1,1,1,1,2,3,1,1,1,1,1,3,1,2,1,1,2,1,1,1,2,3,1,0,1,1,1,1,2,1,1,1,1,3};
    for (int i = 1; i <= 182; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[91];

  public CodeCoverCoverageCounter$gqhemrjldeicuv9al10h () {
    super("com.google.javascript.jscomp.NodeUtil.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 457; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 819; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 182; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 90; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NodeUtil.java");
      for (int i = 1; i <= 457; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 819; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 182; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 30; i++) {
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

