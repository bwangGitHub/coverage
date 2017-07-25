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
 *   Bob Jervis
 *   Google Inc.
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

package com.google.javascript.rhino.jstype;

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * A builder for the Rhino Node representing Function parameters.
 * @author nicksantos@google.com (Nick Santos)
 */
public class FunctionParamBuilder {
  static {
    CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.ping();
  }


  private final JSTypeRegistry registry;
  private final Node root = new Node(Token.PARAM_LIST);
  {
    CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[1]++;
  }

  public FunctionParamBuilder(JSTypeRegistry registry) {
    this.registry = registry;
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[2]++;
  }

  /**
   * Add parameters of the given type to the end of the param list.
   * @return False if this is called after optional params are added.
   */
  public boolean addRequiredParams(JSType ...types) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((hasOptionalOrVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[2]++;}
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[1]++;



    for (JSType type : types) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[1]--;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[2]--;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[3]++;
}
      newParameter(type);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[5]++;
    }
    return true;
  }

  /**
   * Add optional parameters of the given type to the end of the param list.
   * @param types Types for each optional parameter. The builder will make them
   *     undefine-able.
   * @return False if this is called after var args are added.
   */
  public boolean addOptionalParams(JSType ...types) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((hasVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[4]++;}
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[7]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[4]++;



    for (JSType type : types) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[4]--;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[5]--;
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.loops[6]++;
}
      newParameter(registry.createOptionalType(type)).setOptionalArg(true);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[8]++;
    }
    return true;
  }

  /**
   * Add variable arguments to the end of the parameter list.
   * @return False if this is called after var args are added.
   */
  public boolean addVarArgs(JSType type) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((hasVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[5]++;
      return false;

    } else {
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[6]++;}
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;

    // There are two types of variable argument functions:
    // 1) Programmer-defined var args
    // 2) Native bottom types that can accept any argument.
    // For the first one, "undefined" is a valid value for all arguments.
    // For the second, we do not want to cast it up to undefined.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[7]++;
      type = registry.createOptionalType(type);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[11]++;

    } else {
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[8]++;}
    newParameter(type).setVarArgs(true);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[12]++;
    return true;
  }

  /**
   * Copies the parameter specification from the given node.
   */
  public Node newParameterFromNode(Node n) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[13]++;
    Node newParam = newParameter(n.getJSType());
    newParam.setVarArgs(n.isVarArgs());
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[14]++;
    newParam.setOptionalArg(n.isOptionalArg());
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[15]++;
    return newParam;
  }

  /**
   * Copies the parameter specification from the given node,
   * but makes sure it's optional.
   */
  public Node newOptionalParameterFromNode(Node n) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[16]++;
    Node newParam = newParameterFromNode(n);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((newParam.isVarArgs()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((newParam.isOptionalArg()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[9]++;
      newParam.setOptionalArg(true);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[18]++;

    } else {
  CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.branches[10]++;}
    return newParam;
  }

  // Add a parameter to the list with the given type.
  private Node newParameter(JSType type) {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[19]++;
    Node paramNode = Node.newString(Token.NAME, "");
    paramNode.setJSType(type);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[20]++;
    root.addChildToBack(paramNode);
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[21]++;
    return paramNode;
  }

  public Node build() {
    return root;
  }

  private boolean hasOptionalOrVarArgs() {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[22]++;
    Node lastChild = root.getLastChild();
    return lastChild != null &&
        (lastChild.isOptionalArg() || lastChild.isVarArgs());
  }

  public boolean hasVarArgs() {
CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh.statements[23]++;
    Node lastChild = root.getLastChild();
    return lastChild != null && lastChild.isVarArgs();
  }
}

class CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.jstype.FunctionParamBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$37h87pukihgtdb5r8km0wdaib206rs49a5jenoh () {
    super("com.google.javascript.rhino.jstype.FunctionParamBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.jstype.FunctionParamBuilder.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

