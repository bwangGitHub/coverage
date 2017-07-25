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
import com.google.javascript.rhino.Node;
import java.io.Serializable;
import java.util.*;

/**
 * Extract a list of all function nodes defined in a JavaScript
 * program, assigns them globally unique ids and computes their fully
 * qualified names.  Function names are derived from the property they
 * are assigned to and the scope they are defined in.  For instance,
 * the following code
 *
 * goog.widget = function(str) {
 *   this.member_fn = function() {}
 *   local_fn = function() {}
 *   goog.array.map(arr, function(){});
 * }
 *
 * defines the following functions
 *
 *  goog.widget
 *  goog.widget.member_fn
 *  goog.widget::local_fn
 *  goog.widget::<anonymous>
 *
 */

class FunctionNames implements CompilerPass, Serializable {
  static {
    CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[1]++;
  }

  private final transient AbstractCompiler compiler;
  private final Map<Node, FunctionRecord> functionMap = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[2]++;
  }
  private final transient FunctionListExtractor functionListExtractor;

  FunctionNames(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[3]++;
    this.functionListExtractor = new FunctionListExtractor(functionMap);
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, functionListExtractor);
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[5]++;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[6]++;
    FunctionExpressionNamer namer = new FunctionExpressionNamer(functionMap);
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[7]++;
    AnonymousFunctionNamingCallback namingCallback =
        new AnonymousFunctionNamingCallback(namer);
    NodeTraversal.traverse(compiler, root, namingCallback);
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[8]++;
  }

  public Iterable<Node> getFunctionNodeList() {
    return functionMap.keySet();
  }

  public int getFunctionId(Node f) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[9]++;
    FunctionRecord record = functionMap.get(f);
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((record != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[1]++;
      return record.id;

    } else {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[2]++;
      return -1;
    }
  }

  public String getFunctionName(Node f) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[11]++;
    FunctionRecord record = functionMap.get(f);
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((record == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[3]++;
      // Function node was added during compilation and has no name.
      return null;

    } else {
  CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[4]++;}
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[13]++;

    String str = record.name;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((str.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[5]++;
      str = "<anonymous>";
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[15]++;

    } else {
  CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[6]++;}
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[16]++;

    Node parent = record.parent;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[7]++;
      str = getFunctionName(parent) + "::" + str;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[18]++;

    } else {
  CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[8]++;}

    // this.foo -> foo
    str = str.replaceAll("::this\\.", ".");
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[19]++;
    // foo.prototype.bar -> foo.bar
    // AnonymousFunctionNamingCallback already replaces ".prototype."
    // with "..", just remove the extra dot.
    str = str.replaceAll("\\.\\.", ".");
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[20]++;
    // remove toplevel anonymous blocks, if they exists.
    str = str.replaceFirst("^(<anonymous>::)*", "");
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[21]++;
    return str;
  }

  private static class FunctionRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    public final int id;
    public final Node parent;
    public String name;

    FunctionRecord(int id, Node parent, String name) {
      this.id = id;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[22]++;
      this.parent = parent;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[23]++;
      this.name = name;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[24]++;
    }
  }

  private static class FunctionListExtractor extends AbstractPostOrderCallback {
    private final Map<Node, FunctionRecord> functionMap;
    private int nextId = 0;
  {
    CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[25]++;
  }

    FunctionListExtractor(Map<Node, FunctionRecord> functionMap) {
      this.functionMap = functionMap;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[26]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[9]++;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[28]++;
        Node functionNameNode = n.getFirstChild();
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[29]++;
        String functionName = functionNameNode.getString();
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[30]++;

        Node enclosingFunction = t.getEnclosingFunction();

        functionMap.put(n,
            new FunctionRecord(nextId, enclosingFunction, functionName));
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[31]++;
        nextId++;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[32]++;

      } else {
  CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.branches[10]++;}
    }
  }

  private static class FunctionExpressionNamer
      implements AnonymousFunctionNamingCallback.FunctionNamer {
    private static final char DELIMITER = '.';
    private static final NodeNameExtractor extractor =
        new NodeNameExtractor(DELIMITER);
    private final Map<Node, FunctionRecord> functionMap;

    FunctionExpressionNamer(Map<Node, FunctionRecord> functionMap) {
      this.functionMap = functionMap;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[33]++;
    }

    @Override
    public final String getName(Node node) {
      return extractor.getName(node);
    }

    @Override
    public final void setFunctionName(String name, Node fnNode) {
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[34]++;
      FunctionRecord record = functionMap.get(fnNode);
      assert(record != null);
      assert(record.name.isEmpty());
      record.name = name;
CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht.statements[35]++;
    }

    @Override
    public final String getCombinedName(String lhs, String rhs) {
      return lhs + DELIMITER + rhs;
    }
  }
}

class CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FunctionNames.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5uxo1wo46tf39arsessdntf272ht () {
    super("com.google.javascript.jscomp.FunctionNames.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FunctionNames.java");
      for (int i = 1; i <= 35; i++) {
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

