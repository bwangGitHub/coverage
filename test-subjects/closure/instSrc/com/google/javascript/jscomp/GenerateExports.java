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

import com.google.common.base.Preconditions;
import com.google.javascript.jscomp.FindExportableNodes.GenerateNodeContext;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Map;

/**
 * Generates goog.exportSymbol/goog.exportProperty for the @export annotation.
 *
 */
class GenerateExports implements CompilerPass {
  static {
    CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.ping();
  }


  private static final String PROTOTYPE_PROPERTY = "prototype";
  static {
    CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[1]++;
  }

  private final AbstractCompiler compiler;

  private final String exportSymbolFunction;

  private final String exportPropertyFunction;

  /**
   * Creates a new generate exports compiler pass.
   * @param compiler JS compiler.
   * @param exportSymbolFunction function used for exporting symbols.
   * @param exportPropertyFunction function used for exporting property names.
   */
  GenerateExports(AbstractCompiler compiler, String exportSymbolFunction,
      String exportPropertyFunction) {
    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[2]++;
    Preconditions.checkNotNull(exportSymbolFunction);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[3]++;
    Preconditions.checkNotNull(exportPropertyFunction);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[4]++;

    this.compiler = compiler;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[5]++;
    this.exportSymbolFunction = exportSymbolFunction;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[6]++;
    this.exportPropertyFunction = exportPropertyFunction;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[7]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[8]++;
    FindExportableNodes findExportableNodes = new FindExportableNodes(compiler);
    NodeTraversal.traverse(compiler, root, findExportableNodes);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[9]++;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[10]++;
    Map<String, GenerateNodeContext> exports = findExportableNodes
        .getExports();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[11]++;

    CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[1]++;


    for (Map.Entry<String, GenerateNodeContext> entry : exports.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[1]--;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[2]--;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[3]++;
}
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[13]++;
      String export = entry.getKey();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[14]++;
      GenerateNodeContext context = entry.getValue();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[15]++;

      // Emit the proper CALL expression.
      // This is an optimization to avoid exporting everything as a symbol
      // because exporting a property is significantly simpler/faster.
      // Only export the property if the parent is being exported or
      // if the parent is "prototype" and the grandparent is being exported.
      String parent = null;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[16]++;
      String grandparent = null;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[17]++;

      Node node = context.getNode().getFirstChild();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[18]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[1]++;
        parent = node.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[19]++;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((node.getFirstChild().isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getPropertyName(node.getFirstChild()).equals(PROTOTYPE_PROPERTY)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[3]++;
          grandparent = node.getFirstChild().getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[21]++;

        } else {
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[4]++;}

      } else {
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[2]++;}
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[22]++;

      boolean useExportSymbol = true;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((grandparent != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((exports.containsKey(grandparent)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[5]++;
        useExportSymbol = false;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[24]++;

      } else {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[6]++;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((exports.containsKey(parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[7]++;
        useExportSymbol = false;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[26]++;

      } else {
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[8]++;}
}

      Node call;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((useExportSymbol) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[9]++;
        // exportSymbol(publicPath, object);
        call = IR.call(
            NodeUtil.newQualifiedNameNode(
                convention, exportSymbolFunction,
                context.getNode(), export),
            IR.string(export),
            NodeUtil.newQualifiedNameNode(
                convention, export,
                context.getNode(), export));
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[28]++;

      } else {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[10]++;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[29]++;
        // exportProperty(object, publicName, symbol);
        String property = getPropertyName(node);
        call = IR.call(
            NodeUtil.newQualifiedNameNode(
                convention, exportPropertyFunction,
                context.getNode(), exportPropertyFunction),
            NodeUtil.newQualifiedNameNode(
                convention, parent,
                context.getNode(), exportPropertyFunction),
            IR.string(property),
            NodeUtil.newQualifiedNameNode(
                convention, export,
                context.getNode(), exportPropertyFunction));
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[30]++;
      }
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[31]++;

      Node expression = IR.exprResult(call);
      annotate(expression);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[32]++;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[33]++;

      // It's important that any class-building calls (goog.inherits)
      // come right after the class definition, so move the export after that.
      Node insertionPoint = context.getContextNode().getNext();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
      while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((insertionPoint != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((NodeUtil.isExprCall(insertionPoint)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((convention.getClassesDefinedByCall(
              insertionPoint.getFirstChild()) != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[4]--;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[5]--;
  CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.loops[6]++;
}
        insertionPoint = insertionPoint.getNext();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[35]++;
      }
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((insertionPoint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[11]++;
        context.getScriptNode().addChildToBack(expression);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[37]++;

      } else {
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.branches[12]++;
        context.getScriptNode().addChildBefore(expression, insertionPoint);
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[38]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[39]++;
    }
  }

  private void annotate(Node node) {
    NodeTraversal.traverse(
        compiler, node, new PrepareAst.PrepareAnnotations());
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[40]++;
  }

  /**
   * Assumes the node type is correct and returns the property name
   * (not fully qualified).
   * @param node node
   * @return property name.
   */
  private String getPropertyName(Node node) {
    Preconditions.checkArgument(node.isGetProp());
CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p.statements[41]++;
    return node.getLastChild().getString();
  }
}

class CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p ());
  }
    public static long[] statements = new long[42];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GenerateExports.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,2,1,3,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$8c8h6ikq2cnltj1q89m117n9lyc6f6p () {
    super("com.google.javascript.jscomp.GenerateExports.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 41; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.GenerateExports.java");
      for (int i = 1; i <= 41; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

