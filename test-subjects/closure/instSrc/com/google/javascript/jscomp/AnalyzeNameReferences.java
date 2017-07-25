/*
 * Copyright 2009 The Closure Compiler Authors.
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

import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NameReferenceGraph.Name;
import com.google.javascript.jscomp.NameReferenceGraph.Reference;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal;
import com.google.javascript.jscomp.graph.Annotation;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal.EdgeCallback;
import com.google.javascript.rhino.Node;

/**
 * Analyzes names and references usage by determining:
 * <p><ol>
 * <li>If the name is reachable from the {@link NameReferenceGraph#MAIN}.</li>
 * <li>as well as the deepest common module that references it.</li>
 * </ol>
 *
 * The two pieces of information will be annotated to {@link NameReferenceGraph}
 * by {@link NameInfo} objects.
 *
 * This is an analysis based on {@link AnalyzeNameReferences} using the more
 * accurate graph and will soon replace it.
 *
 */
class AnalyzeNameReferences implements CompilerPass {
  static {
    CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.ping();
  }


  private NameReferenceGraph graph;
  private final JSModuleGraph moduleGraph;
  private final AbstractCompiler compiler;

  AnalyzeNameReferences(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[1]++;
    this.moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[3]++;
    NameReferenceGraphConstruction gc =
        new NameReferenceGraphConstruction(compiler);
    gc.process(externs, root);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[4]++;
    graph = gc.getNameReferenceGraph();
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[5]++;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[6]++;
    FixedPointGraphTraversal<Name, Reference> t =
        FixedPointGraphTraversal.newTraversal(new PropagateReferences());
    getInfo(graph.MAIN).markReference(null);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[7]++;
    t.computeFixedPoint(graph, Sets.newHashSet(graph.MAIN));
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[8]++;
  }

  public NameReferenceGraph getGraph() {
    return graph;
  }

  private class PropagateReferences implements EdgeCallback<Name, Reference> {
    @Override
    public boolean traverseEdge(Name start, Reference edge, Name dest) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[9]++;
      NameInfo startInfo = getInfo(start);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[10]++;
      NameInfo destInfo = getInfo(dest);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((startInfo.isReferenced()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[1]++;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[12]++;
        JSModule startModule = startInfo.getDeepestCommonModuleRef();
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((startModule != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(startModule, edge.getModule())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[3]++;
          return destInfo.markReference(startModule);

        } else {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[4]++;
          return destInfo.markReference(edge.getModule());
        }

      } else {
  CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[2]++;}
      return false;
    }
  }

  private NameInfo getInfo(Name symbol) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[14]++;
    GraphNode<Name, Reference> name = graph.getNode(symbol);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[15]++;
    NameInfo info = name.getAnnotation();
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[5]++;
      info = new NameInfo();
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[17]++;
      name.setAnnotation(info);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[18]++;

    } else {
  CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[6]++;}
    return info;
  }

  final class NameInfo implements Annotation {
    private boolean referenced = false;
  {
    CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[19]++;
  }
    private JSModule deepestCommonModuleRef = null;
  {
    CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[20]++;
  }

    /** Determines whether we've marked a reference to this property name. */
    boolean isReferenced() {
      return referenced;
    }

    /**
     * Returns the deepest common module of all the references to this
     * property.
     */
    JSModule getDeepestCommonModuleRef() {
      return deepestCommonModuleRef;
    }

    /**
     * Mark a reference in a given module to this property name, and record
     * the deepest common module reference.
     * @param module The module where it was referenced.
     * @return Whether the name info has changed.
     */
    boolean markReference(JSModule module) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[21]++;
      boolean hasChanged = false;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((referenced) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[7]++;
        referenced = true;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[23]++;
        hasChanged = true;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[24]++;

      } else {
  CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[8]++;}
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[9]++;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[26]++;
        JSModule originalDeepestCommon = deepestCommonModuleRef;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((deepestCommonModuleRef == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[11]++;
          deepestCommonModuleRef = module;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[28]++;

        } else {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[12]++;
          deepestCommonModuleRef =
              moduleGraph.getDeepestCommonDependencyInclusive(
                  deepestCommonModuleRef, module);
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[29]++;
        }
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((originalDeepestCommon != deepestCommonModuleRef) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[13]++;
          hasChanged = true;
CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.statements[31]++;

        } else {
  CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[14]++;}

      } else {
  CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1.branches[10]++;}
      return hasChanged;
    }
  }
}

class CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1 ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AnalyzeNameReferences.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$l6l3fhzuvtrgwg9h93op8saf49jcs051j738wrc1 () {
    super("com.google.javascript.jscomp.AnalyzeNameReferences.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AnalyzeNameReferences.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
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

