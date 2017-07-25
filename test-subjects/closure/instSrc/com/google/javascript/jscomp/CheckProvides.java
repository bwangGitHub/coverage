/*
 * Copyright 2008 The Closure Compiler Authors.
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

import com.google.common.collect.Maps;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSDocInfo.Visibility;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Map;

/**
 * Insures '@constructor X' has a 'goog.provide("X")' .
 *
 */
class CheckProvides implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.ping();
  }

  private final AbstractCompiler compiler;
  private final CheckLevel checkLevel;
  private final CodingConvention codingConvention;

  static final DiagnosticType MISSING_PROVIDE_WARNING = DiagnosticType.disabled(
      "JSC_MISSING_PROVIDE",
      "missing goog.provide(''{0}'')");
  static {
    CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[1]++;
  }

  CheckProvides(AbstractCompiler compiler, CheckLevel checkLevel) {
    this.compiler = compiler;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[2]++;
    this.checkLevel = checkLevel;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[3]++;
    this.codingConvention = compiler.getCodingConvention();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
    hotSwapScript(root, null);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[5]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[6]++;
    CheckProvidesCallback callback =
        new CheckProvidesCallback(codingConvention);
    new NodeTraversal(compiler, callback).traverse(scriptRoot);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[7]++;
  }

  private class CheckProvidesCallback extends AbstractShallowCallback {
    private final Map<String, Node> provides = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[8]++;
  }
    private final Map<String, Node> ctors = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[9]++;
  }
    private final CodingConvention convention;

    CheckProvidesCallback(CodingConvention convention){
      this.convention = convention;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[10]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[11]++;
      switch (n.getType()) {
        case Token.CALL:
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[1]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[12]++;
          String providedClassName =
            codingConvention.extractClassNameIfProvide(n, parent);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((providedClassName != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[2]++;
            provides.put(providedClassName, n);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[14]++;

          } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[3]++;}
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[15]++;
          break;
        case Token.FUNCTION:
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[4]++;
          visitFunctionNode(n, parent);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[16]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[17]++;
          break;
        case Token.SCRIPT:
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[5]++;
          visitScriptNode();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[18]++; default : CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[6]++;
      }
    }

    private void visitFunctionNode(Node n, Node parent) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[19]++;
      Node name = null;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[20]++;
      JSDocInfo info = parent.getJSDocInfo();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[7]++;
        name = parent.getFirstChild();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[22]++;

      } else {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[8]++;
        // look to the child, maybe it's a named function
        info = n.getJSDocInfo();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[23]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((info.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[9]++;
          name = n.getFirstChild();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[25]++;

        } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[10]++;}
      }
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[11]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[27]++;
        String qualifiedName = name.getQualifiedName();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.convention.isPrivate(qualifiedName)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[13]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[29]++;
          Visibility visibility = info.getVisibility();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((visibility.equals(JSDocInfo.Visibility.PRIVATE)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[15]++;
            ctors.put(qualifiedName, name);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[31]++;

          } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[14]++;}

      } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[12]++;}
    }

    private void visitScriptNode() {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[32]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[1]++;


      for (Map.Entry<String, Node> ctorEntry : ctors.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[1]--;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[2]--;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[3]++;
}
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[33]++;
        String ctor = ctorEntry.getKey();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[34]++;
        int index = -1;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[35]++;
        boolean found = false;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[36]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
        do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[4]--;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[5]--;
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.loops[6]++;
}
          index = ctor.indexOf('.', index +1);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[37]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[38]++;
          String provideKey = index == -1 ? ctor : ctor.substring(0, index);
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((provides.containsKey(provideKey)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[17]++;
            found = true;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[40]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[41]++;
            break;

          } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[18]++;}
        } while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((index != -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false));
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((found) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[19]++;
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[43]++;
          Node n = ctorEntry.getValue();
          compiler.report(
              JSError.make(n.getSourceFileName(), n,
                  checkLevel, MISSING_PROVIDE_WARNING, ctorEntry.getKey()));
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[44]++;

        } else {
  CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.branches[20]++;}
      }
      provides.clear();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[45]++;
      ctors.clear();
CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip.statements[46]++;
    }
  }
}

class CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip ());
  }
    public static long[] statements = new long[47];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckProvides.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,2,1,1,1,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$5lsutpaa0qoded06i939lua9h8ip () {
    super("com.google.javascript.jscomp.CheckProvides.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 46; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckProvides.java");
      for (int i = 1; i <= 46; i++) {
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
    for (int i = 1; i <= 9; i++) {
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

