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

import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;


/**
 * Visitor that performs naming operations on anonymous functions by
 * means of the FunctionNamer interface.  Anonymous functions are
 * named based on context.  For example, the anonymous function on the
 * RHS based on the property at the LHS of the assignment operator.
 *
 * goog.string.htmlEscape = function(str) {
 * }
 *
 */
class AnonymousFunctionNamingCallback
    extends AbstractPostOrderCallback {
  static {
    CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.ping();
  }

  private final FunctionNamer namer;

  /**
   * Interface used by AnonymousFunctionNamingCallback to set the name
   * of anonymous functions.
   */
  interface FunctionNamer {
    /**
     * Generates a string representation of a node for use by
     * setFunctionName.
     */
    String getName(Node node);

    /**
     * Sets the name of an anonymous function.
     * @param fnNode The function node to update
     * @param name The name
     */
    void setFunctionName(String name, Node fnNode);

    /**
     * Generate a name by "concatenating" the output of multiple calls
     * to getName.
     */
    String getCombinedName(String lhs, String rhs);
  }

  AnonymousFunctionNamingCallback(FunctionNamer namer) {
    this.namer = namer;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[1]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[2]++;
    switch (n.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[1]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[3]++;
        // this handles functions that are assigned to variables or
        // properties
        // e.g. goog.string.htmlEscape = function(str) {
        // }

        // get the function name and see if it's empty
        Node functionNameNode = n.getFirstChild();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[4]++;
        String functionName = functionNameNode.getString();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((functionName.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[2]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[4]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[7]++;
            // this is an assignment to a property, generally either a
            // static function or a prototype function
            // e.g. goog.string.htmlEscape = function() { } or
            //      goog.structs.Map.prototype.getCount = function() { }
            Node lhs = parent.getFirstChild();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[8]++;
            String name = namer.getName(lhs);
            namer.setFunctionName(name, n);
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[9]++;

          } else {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[5]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[6]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[11]++;
            // this is an assignment to a variable
            // e.g. var handler = function() {}
            String name = namer.getName(parent);
            namer.setFunctionName(name, n);
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[12]++;

          } else {
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[7]++;}
}

        } else {
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[3]++;}
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[13]++;
        break;
      case Token.ASSIGN:
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[8]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[14]++;
        // this handles functions that are assigned to a prototype through
        // an object literal
        // e.g. BuzzApp.prototype = {
        //        Start : function() { }
        //      }
        Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[15]++;
        Node rhs = lhs.getNext();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((rhs.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[9]++;
          nameObjectLiteralMethods(rhs, namer.getName(lhs));
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[17]++;

        } else {
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[10]++;} default : CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[11]++;
    }
  }

  private void nameObjectLiteralMethods(Node objectLiteral, String context) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
    for (Node keyNode = objectLiteral.getFirstChild();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((keyNode != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false);
         keyNode = keyNode.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.loops[1]--;
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.loops[2]--;
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.loops[3]++;
}
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[19]++;
      Node valueNode = keyNode.getFirstChild();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;

      // Object literal keys may be STRING_KEY, GETTER_DEF, SETTER_DEF.
      // Get and Set are skipped because they can not be named.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((keyNode.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[12]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[21]++;
        // concatenate the context and key name to get a new qualified name.
        String name = namer.getCombinedName(context, namer.getName(keyNode));
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[22]++;

        int type = valueNode.getType();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[14]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[24]++;
          // set name if function is anonymous
          Node functionNameNode = valueNode.getFirstChild();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[25]++;
          String functionName = functionNameNode.getString();
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((functionName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[16]++;
            namer.setFunctionName(name, valueNode);
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[27]++;

          } else {
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[17]++;}

        } else {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[15]++;
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[28]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[18]++;
          // process nested object literal
          nameObjectLiteralMethods(valueNode, name);
CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.statements[29]++;

        } else {
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[19]++;}
}

      } else {
  CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl.branches[13]++;}
    }
  }
}

class CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[20];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AnonymousFunctionNamingCallback.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$37u5n44u9euszrghuw6aaljfkxl9xm7u6aeecqnq6yo99vbqkvtu8jfl () {
    super("com.google.javascript.jscomp.AnonymousFunctionNamingCallback.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 19; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AnonymousFunctionNamingCallback.java");
      for (int i = 1; i <= 29; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 19; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

