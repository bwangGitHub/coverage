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

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;

/**
 * Utility class that extracts the qualified name out of a node.
 * Useful when trying to get a human-friendly string representation of
 * a property node that can be used to describe the node or name
 * related nodes based on it (as done by the NameAnonymousFunctions
 * compiler pass).
 *
 */
class NodeNameExtractor {
  static {
    CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.ping();
  }

  private final char delimiter;
  private int nextUniqueInt = 0;
  {
    CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[1]++;
  }

  NodeNameExtractor(char delimiter) {
    this.delimiter = delimiter;
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[2]++;
  }

  /**
   * Returns a qualified name of the specified node. Dots and brackets
   * are changed to the delimiter passed in when constructing the
   * NodeNameExtractor object.  We also replace ".prototype" with the
   * delimiter to keep names short, while still differentiating them
   * from static properties.  (Prototype properties will end up
   * looking like "a$b$$c" if this.delimiter = '$'.)
   */
  String getName(Node node) {
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[3]++;
    switch (node.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[1]++;
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[4]++;
        Node functionNameNode = node.getFirstChild();
        return functionNameNode.getString();
      case Token.GETPROP:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[2]++;
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[5]++;
        Node lhsOfDot = node.getFirstChild();
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[6]++;
        Node rhsOfDot = lhsOfDot.getNext();
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[7]++;
        String lhsOfDotName = getName(lhsOfDot);
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[8]++;
        String rhsOfDotName = getName(rhsOfDot);
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (("prototype".equals(rhsOfDotName)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[3]++;
          return lhsOfDotName + delimiter;

        } else {
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[4]++;
          return lhsOfDotName + delimiter + rhsOfDotName;
        }
      case Token.GETELEM:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[5]++;
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[10]++;
        Node outsideBrackets = node.getFirstChild();
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[11]++;
        Node insideBrackets = outsideBrackets.getNext();
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[12]++;
        String nameOutsideBrackets = getName(outsideBrackets);
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[13]++;
        String nameInsideBrackets = getName(insideBrackets);
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (("prototype".equals(nameInsideBrackets)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[6]++;
          return nameOutsideBrackets + delimiter;

        } else {
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[7]++;
          return nameOutsideBrackets + delimiter + nameInsideBrackets;
        }
      case Token.NAME:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[8]++;
        return node.getString();
      case Token.STRING:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[9]++;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[10]++;
        return TokenStream.isJSIdentifier(node.getString()) ?
            node.getString() : ("__" + nextUniqueInt++);
      case Token.NUMBER:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[11]++;
        return NodeUtil.getStringValue(node);
      case Token.THIS:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[12]++;
        return "this";
      case Token.CALL:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[13]++;
        return getName(node.getFirstChild());
      default:
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[14]++;
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[15]++;
        StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (Node child = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false);
             child = child.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.loops[1]--;
  CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.loops[2]--;
  CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.loops[3]++;
}
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((sb.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[15]++;
            sb.append(delimiter);
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[18]++;

          } else {
  CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.branches[16]++;}
          sb.append(getName(child));
CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht.statements[19]++;
        }
        return sb.toString();
    }
  }
}

class CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NodeNameExtractor.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$cvb389b18atb0r94nrykdvuzsqsz1b6mht () {
    super("com.google.javascript.jscomp.NodeNameExtractor.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NodeNameExtractor.java");
      for (int i = 1; i <= 19; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

