/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.*;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;

public final class OptFunctionNode
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.ping();
  }

    OptFunctionNode(FunctionNode fnode)
    {
        this.fnode = fnode;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[1]++;
        fnode.setCompilerData(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[2]++;
    }

    public static OptFunctionNode get(ScriptNode scriptOrFn, int i)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[3]++;
        FunctionNode fnode = scriptOrFn.getFunctionNode(i);
        return (OptFunctionNode)fnode.getCompilerData();
    }

    public static OptFunctionNode get(ScriptNode scriptOrFn)
    {
        return (OptFunctionNode)scriptOrFn.getCompilerData();
    }

    public boolean isTargetOfDirectCall()
    {
        return directTargetIndex >= 0;
    }

    public int getDirectTargetIndex()
    {
        return directTargetIndex;
    }

    void setDirectTargetIndex(int directTargetIndex)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        // One time action
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((directTargetIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.directTargetIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[1]++;
            Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[5]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[2]++;}
        this.directTargetIndex = directTargetIndex;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[6]++;
    }

    void setParameterNumberContext(boolean b)
    {
        itsParameterNumberContext = b;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[7]++;
    }

    public boolean getParameterNumberContext()
    {
        return itsParameterNumberContext;
    }

    public int getVarCount()
    {
        return fnode.getParamAndVarCount();
    }

    public boolean isParameter(int varIndex)
    {
        return varIndex < fnode.getParamCount();
    }

    public boolean isNumberVar(int varIndex)
    {
        varIndex -= fnode.getParamCount();
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[8]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((varIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((numberVarFlags != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[3]++;
            return numberVarFlags[varIndex];

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[4]++;}
        return false;
    }

    void setIsNumberVar(int varIndex)
    {
        varIndex -= fnode.getParamCount();
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[10]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
        // Can only be used with non-parameters
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((varIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[5]++; Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[12]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[6]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((numberVarFlags == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[7]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[14]++;
            int size = fnode.getParamAndVarCount() - fnode.getParamCount();
            numberVarFlags = new boolean[size];
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[15]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[8]++;}
        numberVarFlags[varIndex] = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[16]++;
    }

    public int getVarIndex(Node n)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[17]++;
        int index = n.getIntProp(Node.VARIABLE_PROP, -1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[9]++;
            Node node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[19]++;
            int type = n.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[11]++;
                node = n;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[21]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[12]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[22]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((type == Token.SETVAR) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type == Token.SETCONSTVAR) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[13]++;
                node = n.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[23]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[14]++;
                throw Kit.codeBug();
            }
}
            index = fnode.getIndexForNameNode(node);
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[24]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[15]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[16]++;}
            n.putIntProp(Node.VARIABLE_PROP, index);
CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[26]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.branches[10]++;}
        return index;
    }

    public final FunctionNode fnode;

    private boolean[] numberVarFlags;
    private int directTargetIndex = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht.statements[27]++;
  }
    private boolean itsParameterNumberContext;
    boolean itsContainsCalls0;
    boolean itsContainsCalls1;
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.optimizer.RHINO-SRC-OptFunctionNode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,2,1};
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpujscam2ng7vri5ntnmu5k4596ar4uht () {
    super("org.mozilla.javascript.optimizer.RHINO-SRC-OptFunctionNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
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
    log.startNamedSection("org.mozilla.javascript.optimizer.RHINO-SRC-OptFunctionNode.java");
      for (int i = 1; i <= 27; i++) {
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

