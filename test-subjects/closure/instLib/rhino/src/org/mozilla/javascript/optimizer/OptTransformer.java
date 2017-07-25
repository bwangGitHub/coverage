/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.*;
import org.mozilla.javascript.ast.ScriptNode;
import java.util.Map;

/**
 * This class performs node transforms to prepare for optimization.
 *
 * @see NodeTransformer
 */

class OptTransformer extends NodeTransformer {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.ping();
  }


    OptTransformer(Map<String,OptFunctionNode> possibleDirectCalls, ObjArray directCallTargets)
    {
        this.possibleDirectCalls = possibleDirectCalls;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[1]++;
        this.directCallTargets = directCallTargets;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[2]++;
    }

    @Override
    protected void visitNew(Node node, ScriptNode tree) {
        detectDirectCall(node, tree);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[3]++;
        super.visitNew(node, tree);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[4]++;
    }

    @Override
    protected void visitCall(Node node, ScriptNode tree) {
        detectDirectCall(node, tree);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[5]++;
        super.visitCall(node, tree);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[6]++;
    }

    private void detectDirectCall(Node node, ScriptNode tree)
    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((tree.getType() == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[1]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[8]++;
            Node left = node.getFirstChild();
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[9]++;

            // count the arguments
            int argCount = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[10]++;
            Node arg = left.getNext();
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
            while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.loops[1]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.loops[2]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.loops[3]++;
}
                arg = arg.getNext();
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[12]++;
                argCount++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[13]++;
            }
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((argCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[3]++;
                OptFunctionNode.get(tree).itsContainsCalls0 = true;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[15]++;

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[4]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;

            /*
             * Optimize a call site by converting call("a", b, c) into :
             *
             *  FunctionObjectFor"a" <-- instance variable init'd by constructor
             *
             *  // this is a DIRECTCALL node
             *  fn = GetProp(tmp = GetBase("a"), "a");
             *  if (fn == FunctionObjectFor"a")
             *      fn.call(tmp, b, c)
             *  else
             *      ScriptRuntime.Call(fn, tmp, b, c)
             */
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((possibleDirectCalls != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[5]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[17]++;
                String targetName = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((left.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[7]++;
                    targetName = left.getString();
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[19]++;

                } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[8]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[20]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((left.getType() == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[9]++;
                    targetName = left.getFirstChild().getNext().getString();
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[21]++;

                } else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[10]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[22]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((left.getType() == Token.GETPROPNOWARN) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[11]++;
                    throw Kit.codeBug();

                } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[12]++;}
}
}
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((targetName != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[13]++;
                    OptFunctionNode ofn;
                    ofn = possibleDirectCalls.get(targetName);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[24]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
                    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((ofn != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((argCount == ofn.fnode.getParamCount()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ofn.fnode.requiresActivation()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false))
                    {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[15]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
                        // Refuse to directCall any function with more
                        // than 32 parameters - prevent code explosion
                        // for wacky test cases
                        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((argCount <= 32) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[17]++;
                            node.putProp(Node.DIRECTCALL_PROP, ofn);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[27]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
                            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ofn.isTargetOfDirectCall()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[19]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[29]++;
                                int index = directCallTargets.size();
                                directCallTargets.add(ofn);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[30]++;
                                ofn.setDirectTargetIndex(index);
CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.statements[31]++;

                            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[20]++;}

                        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[18]++;}

                    } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[16]++;}

                } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[14]++;}

            } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5.branches[2]++;}
    }

    private Map<String,OptFunctionNode> possibleDirectCalls;
    private ObjArray directCallTargets;
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5 ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.optimizer.RHINO-SRC-OptTransformer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,3,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevuwo89k26dkhhmqwjszzou19wzbfj5 () {
    super("org.mozilla.javascript.optimizer.RHINO-SRC-OptTransformer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.optimizer.RHINO-SRC-OptTransformer.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

