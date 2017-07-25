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

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Records information about functions and modules.
 *
 */
class RecordFunctionInformation extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.ping();
  }

  private final Compiler compiler;
  private final FunctionNames functionNames;
  private final JSModuleGraph moduleGraph;

  /**
   * Protocol buffer builder.
   */
  private final FunctionInformationMap.Builder mapBuilder;

  /**
   * Creates a record function information compiler pass.
   *
   * @param compiler       The JSCompiler
   * @param functionNames  Assigned function identifiers.
   */
  RecordFunctionInformation(Compiler compiler,
      FunctionNames functionNames) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[1]++;
    this.moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[2]++;
    this.functionNames = functionNames;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[3]++;
    this.mapBuilder = FunctionInformationMap.newBuilder();
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[4]++;
  }

  /**
   * Returns the built-out map.
   */
  FunctionInformationMap getMap() {
    return mapBuilder.build();
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[5]++;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((moduleGraph == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[1]++;
      addModuleInformation(null);
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[7]++;

    } else {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[2]++;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[8]++;
      // The test expects a consistent module order.
      TreeSet<JSModule> modules = Sets.newTreeSet(new Comparator<JSModule>() {
        @Override
        public int compare(JSModule o1, JSModule o2) {
          return o1.getName().compareTo(o2.getName());
        }
      });
      Iterables.addAll(modules, moduleGraph.getAllModules());
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[9]++;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.loops[1]++;


      for (JSModule m : modules) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.loops[1]--;
  CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.loops[2]--;
  CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.loops[3]++;
}
        addModuleInformation(m);
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[11]++;
      }
    }
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[4]++;}
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[13]++;

    int id = functionNames.getFunctionId(n);
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((id < 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[5]++;
      // Function node was added during compilation; don't instrument.
      return;

    } else {
  CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[6]++;}
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[15]++;

    String compiledSource = compiler.toSource(n);
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[16]++;
    JSModule module = t.getModule();
    mapBuilder.addEntry(FunctionInformationMap.Entry.newBuilder()
      .setId(id)
      .setSourceName(NodeUtil.getSourceName(n))
      .setLineNumber(n.getLineno())
      .setModuleName(moduleGraph == null ? "" : module.getName())
      .setSize(compiledSource.length())
      .setName(functionNames.getFunctionName(n))
      .setCompiledSource(compiledSource).build());
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[17]++;
  }

  /**
   * Record a module's compiled source.  The view of the source we get
   * from function sources alone is not complete; it doesn't contain
   * assignments and function calls in the global scope which are
   * crucial to understanding how the application works.
   *
   * This version of the source is also written out to js_output_file,
   * module_output_path_prefix or other places.  Duplicating it here
   * simplifies the process of writing tools that combine and present
   * module and function for debugging purposes.
   */
  private void addModuleInformation(JSModule module) {
    String name;
    String source;
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((module != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[7]++;
      name = module.getName();
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[19]++;
      source = compiler.toSource(module);
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[20]++;

    } else {
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.branches[8]++;
      name = "";
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[21]++;
      source = compiler.toSource();
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[22]++;
    }

    mapBuilder.addModule(FunctionInformationMap.Module.newBuilder()
        .setName(name)
        .setCompiledSource(source).build());
CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep.statements[23]++;
  }
}

class CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RecordFunctionInformation.java";
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

  public CodeCoverCoverageCounter$1gmrje0nfpzds9czvr5p2ttaiigy996kozgkzpfjmwq7pep () {
    super("com.google.javascript.jscomp.RecordFunctionInformation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.RecordFunctionInformation.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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

