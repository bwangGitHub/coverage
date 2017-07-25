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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.LinkedHashMap;

/**
 * Records all of the symbols and properties that should be exported.
 *
 * Currently applies to:
 * - function foo() {}
 * - var foo = function() {}
 * - foo.bar = function() {}
 * - var FOO = ...;
 * - foo.BAR = ...;
 *
 * FOO = BAR = 5;
 * and
 * var FOO = BAR = 5;
 * are not supported because the annotation is ambiguous to whether it applies
 * to all the variables or only the first one.
 *
 */
public class FindExportableNodes extends AbstractPostOrderCallback {
  static {
    CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.ping();
  }


  static final DiagnosticType NON_GLOBAL_ERROR =
      DiagnosticType.error("JSC_NON_GLOBAL_ERROR",
          "@export only applies to symbols/properties defined in the " +
          "global scope.");
  static {
    CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[1]++;
  }

  /**
   * It's convenient to be able to iterate over exports in the order in which
   * they are encountered.
   */
  private final LinkedHashMap<String, GenerateNodeContext> exports;

  private final AbstractCompiler compiler;

  public FindExportableNodes(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[2]++;
    this.exports = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[3]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[4]++;
    JSDocInfo docInfo = n.getJSDocInfo();
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((docInfo.isExport()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[1]++;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[6]++;
      String export = null;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[7]++;
      GenerateNodeContext context = null;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[8]++;

      switch (n.getType()) {
        case Token.FUNCTION:
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[3]++;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[4]++;
            export = NodeUtil.getFunctionName(n);
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[10]++;
            context = new GenerateNodeContext(n, parent, n);
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[11]++;

          } else {
  CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[5]++;}
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[12]++;
          break;
        case Token.ASSIGN:
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[6]++;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[13]++;
          Node grandparent = parent.getParent();
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (128)) == 0 || true) &&
 ((grandparent != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((grandparent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.getLastChild().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 4) || true)) || (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 4) && false)) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[7]++;
            export = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[15]++;
            context = new GenerateNodeContext(n, grandparent, parent);
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[16]++;

          } else {
  CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[8]++;}
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[17]++;
          break;
        case Token.VAR:
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[9]++;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[10]++;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((n.getFirstChild().hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getFirstChild().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[12]++;
              export = n.getFirstChild().getString();
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[20]++;
              context = new GenerateNodeContext(n, parent, n);
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[21]++;

            } else {
  CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[13]++;}

          } else {
  CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[11]++;} default : CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[14]++;
      }
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((export != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[15]++;
        exports.put(export, context);
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[23]++;

      } else {
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[16]++;
        compiler.report(t.makeError(n, NON_GLOBAL_ERROR));
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[24]++;
      }

    } else {
  CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.branches[2]++;}
  }

  public LinkedHashMap<String, GenerateNodeContext> getExports() {
    return exports;
  }

  /**
   * Context holding the node references required for generating the export
   * calls.
   */
  public static class GenerateNodeContext {
    private final Node scriptNode;
    private final Node contextNode;
    private final Node node;

    public GenerateNodeContext(Node node, Node scriptNode, Node contextNode) {
      this.node = node;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[25]++;
      this.scriptNode = scriptNode;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[26]++;
      this.contextNode = contextNode;
CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl.statements[27]++;
    }

    public Node getNode() {
      return node;
    }

    public Node getScriptNode() {
      return scriptNode;
    }

    public Node getContextNode() {
      return contextNode;
    }
  }
}

class CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FindExportableNodes.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,3,1,2,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$g87ys3yp6l5hpacpuxtsoa8bv5uwoeju21tnl () {
    super("com.google.javascript.jscomp.FindExportableNodes.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FindExportableNodes.java");
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
    for (int i = 1; i <= 6; i++) {
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

