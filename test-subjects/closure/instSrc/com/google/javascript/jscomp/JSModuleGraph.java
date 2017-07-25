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
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.deps.SortedDependencies;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;
import com.google.javascript.jscomp.deps.SortedDependencies.MissingProvideException;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A {@link JSModule} dependency graph that assigns a depth to each module and
 * can answer depth-related queries about them. For the purposes of this class,
 * a module's depth is defined as the number of hops in the longest path from
 * the module to a module with no dependencies.
 *
 */
public class JSModuleGraph {
  static {
    CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.ping();
  }


  private List<JSModule> modules;

  /**
   * Lists of modules at each depth. <code>modulesByDepth.get(3)</code> is a
   * list of the modules at depth 3, for example.
   */
  private List<List<JSModule>> modulesByDepth;

  /**
   * dependencyMap is a cache of dependencies that makes the dependsOn
   * function faster.  Each map entry associates a starting
   * JSModule with the set of JSModules that are transitively dependent on the
   * starting module.
   *
   * If the cache returns null, then the entry hasn't been filled in for that
   * module.
   *
   * dependencyMap should be filled from leaf to root so that
   * getTransitiveDepsDeepestFirst can use its results directly.
   */
  private Map<JSModule, Set<JSModule>> dependencyMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[1]++;
  }

  /**
   * Creates a module graph from a list of modules in dependency order.
   */
  public JSModuleGraph(JSModule[] modulesInDepOrder) {
    this(ImmutableList.copyOf(modulesInDepOrder));
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[2]++;
  }

  /**
   * Creates a module graph from a list of modules in dependency order.
   */
  public JSModuleGraph(List<JSModule> modulesInDepOrder) {
    Preconditions.checkState(
        modulesInDepOrder.size() == Sets.newHashSet(modulesInDepOrder).size(),
        "Found duplicate modules");
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[3]++;
    modules = ImmutableList.copyOf(modulesInDepOrder);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[4]++;
    modulesByDepth = Lists.newArrayList();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[5]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[1]++;



    for (JSModule module : modulesInDepOrder) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[1]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[2]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[3]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[7]++;
      int depth = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[8]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[4]++;


      for (JSModule dep : module.getDependencies()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[4]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[5]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[6]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[9]++;
        int depDepth = dep.getDepth();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((depDepth < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[1]++;
          throw new ModuleDependenceException(String.format(
              "Modules not in dependency order: %s preceded %s",
              module.getName(), dep.getName()),
              module, dep);

        } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[2]++;}
        depth = Math.max(depth, depDepth + 1);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[11]++;
      }

      module.setDepth(depth);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[12]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((depth == modulesByDepth.size()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[3]++;
        modulesByDepth.add(new ArrayList<JSModule>());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[14]++;

      } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[4]++;}
      modulesByDepth.get(depth).add(module);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[15]++;
    }
  }

  /**
   * Gets an iterable over all modules in dependency order.
   */
  Iterable<JSModule> getAllModules() {
    return modules;
  }

  /**
   * Gets the total number of modules.
   */
  int getModuleCount() {
    return modules.size();
  }

  /**
   * Gets the root module.
   */
  JSModule getRootModule() {
    return Iterables.getOnlyElement(modulesByDepth.get(0));
  }

  /**
   * Returns a JSON representation of the JSModuleGraph. Specifically a
   * JSONArray of "Modules" where each module has a
   * - "name"
   * - "dependencies" (list of module names)
   * - "transitive-dependencies" (list of module names, deepest first)
   * - "inputs" (list of file names)
   * @return List of module JSONObjects.
   */
  JSONArray toJson() {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[16]++;
    JSONArray modules = new JSONArray();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[17]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[7]++;


    for (JSModule module : getAllModules()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[7]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[8]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[9]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[18]++;
      JSONObject node = new JSONObject();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[19]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        node.put("name", module.getName());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[20]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[21]++;
        JSONArray deps = new JSONArray();
        node.put("dependencies", deps);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[22]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[23]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[10]++;


        for (JSModule m : module.getDependencies()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[10]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[11]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[12]++;
}
          deps.put(m.getName());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[24]++;
        }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[25]++;
        JSONArray transitiveDeps = new JSONArray();
        node.put("transitive-dependencies", transitiveDeps);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[26]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[27]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[13]++;


        for (JSModule m : getTransitiveDepsDeepestFirst(module)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[13]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[14]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[15]++;
}
          transitiveDeps.put(m.getName());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[28]++;
        }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[29]++;
        JSONArray inputs = new JSONArray();
        node.put("inputs", inputs);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[30]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[31]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[16]++;


        for (CompilerInput input : module.getInputs()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[16]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[17]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[18]++;
}
          inputs.put(input.getSourceFile().getOriginalPath());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[32]++;
        }
        modules.put(node);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[33]++;
      } catch (JSONException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[6]++;
        Throwables.propagate(e);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[34]++;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[5]++;
}
  }
    }
    return modules;
  }

  /**
   * Determines whether this module depends on a given module. Note that a
   * module never depends on itself, as that dependency would be cyclic.
   */
  public boolean dependsOn(JSModule src, JSModule m) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[35]++;
    Set<JSModule> deps = dependencyMap.get(src);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((deps == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[7]++;
      deps = getTransitiveDepsDeepestFirst(src);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[37]++;
      dependencyMap.put(src, deps);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[38]++;

    } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[8]++;}

    return deps.contains(m);
  }

  /**
   * Finds the deepest common dependency of two modules, not including the two
   * modules themselves.
   *
   * @param m1 A module in this graph
   * @param m2 A module in this graph
   * @return The deepest common dep of {@code m1} and {@code m2}, or null if
   *     they have no common dependencies
   */
  JSModule getDeepestCommonDependency(JSModule m1, JSModule m2) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[39]++;
    int m1Depth = m1.getDepth();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[40]++;
    int m2Depth = m2.getDepth();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[41]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[19]++;


int CodeCoverConditionCoverageHelper_C4;
    // According our definition of depth, the result must have a strictly
    // smaller depth than either m1 or m2.
    for (int depth = Math.min(m1Depth, m2Depth) - 1;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((depth >= 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); depth--) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[19]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[20]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[21]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[42]++;
      List<JSModule> modulesAtDepth = modulesByDepth.get(depth);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[43]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[22]++;


int CodeCoverConditionCoverageHelper_C5;
      // Look at the modules at this depth in reverse order, so that we use the
      // original ordering of the modules to break ties (later meaning deeper).
      for (int i = modulesAtDepth.size() - 1;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[22]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[23]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[24]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[44]++;
        JSModule m = modulesAtDepth.get(i);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((dependsOn(m1, m)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dependsOn(m2, m)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[9]++;
          return m;

        } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[10]++;}
      }
    }
    return null;
  }

  /**
   * Finds the deepest common dependency of two modules, including the
   * modules themselves.
   *
   * @param m1 A module in this graph
   * @param m2 A module in this graph
   * @return The deepest common dep of {@code m1} and {@code m2}, or null if
   *     they have no common dependencies
   */
  public JSModule getDeepestCommonDependencyInclusive(
      JSModule m1, JSModule m2) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[46]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((m2 == m1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dependsOn(m2, m1)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[11]++;
      return m1;

    } else {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[12]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[47]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((dependsOn(m1, m2)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[13]++;
      return m2;

    } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[14]++;}
}

    return getDeepestCommonDependency(m1, m2);
  }

  /** Returns the deepest common dependency of the given modules. */
  public JSModule getDeepestCommonDependencyInclusive(
      Collection<JSModule> modules) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[48]++;
    Iterator<JSModule> iter = modules.iterator();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[49]++;
    JSModule dep = iter.next();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[50]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[25]++;


int CodeCoverConditionCoverageHelper_C9;
    while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[25]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[26]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[27]++;
}
      dep = getDeepestCommonDependencyInclusive(dep, iter.next());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[51]++;
    }
    return dep;
  }

  /**
   * Creates an iterable over the transitive dependencies of module {@code m}
   * in a non-increasing depth ordering. The result does not include the module
   * {@code m}.
   *
   * @param m A module in this graph
   * @return The transitive dependencies of module {@code m}
   */
  Set<JSModule> getTransitiveDepsDeepestFirst(JSModule m) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[52]++;
    Set<JSModule> deps = dependencyMap.get(m);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((deps != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[15]++;
      return deps;

    } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[16]++;}
    deps = new TreeSet<JSModule>(new InverseDepthComparator());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[54]++;
    addDeps(deps, m);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[55]++;
    dependencyMap.put(m, deps);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[56]++;
    return deps;
  }

  /**
   * Adds a module's transitive dependencies to a set.
   */
  private void addDeps(Set<JSModule> deps, JSModule m) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[57]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[28]++;


    for (JSModule dep : m.getDependencies()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[28]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[29]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[30]++;
}
      deps.add(dep);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[58]++;
      addDeps(deps, dep);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[59]++;
    }
  }

  /**
   * Replaces any files that are found multiple times with a single instance in
   * the closest parent module that is common to all modules where it appears.
   *
   * JSCompiler normally errors if you attempt to compile modules containing the
   * same file.  This method can be used to remove duplicates before compiling
   * to avoid such an error.
   */
  public void coalesceDuplicateFiles() {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[60]++;
    Multimap<String, JSModule> fileRefs = LinkedHashMultimap.create();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[61]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[31]++;


    for (JSModule module : modules) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[31]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[32]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[33]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[62]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[34]++;


      for (CompilerInput jsFile : module.getInputs()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[34]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[35]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[36]++;
}
        fileRefs.put(jsFile.getName(), module);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[63]++;
      }
    }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[64]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[37]++;



    for (String path : fileRefs.keySet()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[37]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[38]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[39]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[65]++;
      Collection<JSModule> refModules = fileRefs.get(path);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[66]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((refModules.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[17]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[67]++;
        JSModule depModule = getDeepestCommonDependencyInclusive(refModules);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[68]++;
        CompilerInput file = refModules.iterator().next().getByName(path);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[69]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[40]++;


        for (JSModule module : refModules) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[40]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[41]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[42]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[70]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((module != depModule) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[19]++;
            module.removeByName(path);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[71]++;

          } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[20]++;}
        }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[72]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((refModules.contains(depModule)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[21]++;
          depModule.add(file);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[73]++;

        } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[22]++;}

      } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[18]++;}
    }
  }

  /**
   * Applies a DependencyOptions in "dependency sorting" and "dependency pruning"
   * mode to the given list of inputs. Returns a new list with the files sorted
   * and removed. This module graph will be updated to reflect the new list.
   *
   * If you need more fine-grained dependency management, you should create your
   * own DependencyOptions and call
   * {@code manageDependencies(DependencyOptions, List<CompilerInput>)}.
   *
   * @param entryPoints The entry points into the program.
   *     Expressed as JS symbols.
   * @param inputs The original list of sources. Used to ensure that the sort
   *     is stable.
   * @throws CircularDependencyException if there is a circular dependency
   *     between the provides and requires.
   * @throws MissingProvideException if an entry point was not provided
   *     by any of the inputs.
   * @see DependencyOptions for more info on how this works.
   */
  public List<CompilerInput> manageDependencies(
      List<String> entryPoints,
      List<CompilerInput> inputs)
      throws CircularDependencyException, MissingProvideException {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[74]++;
    DependencyOptions depOptions = new DependencyOptions();
    depOptions.setDependencySorting(true);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[75]++;
    depOptions.setDependencyPruning(true);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[76]++;
    depOptions.setEntryPoints(entryPoints);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[77]++;
    return manageDependencies(depOptions, inputs);
  }

  /**
   * Apply the dependency options to the list of sources, returning a new
   * source list re-ordering and dropping files as necessary.
   * This module graph will be updated to reflect the new list.
   *
   * @param inputs The original list of sources. Used to ensure that the sort
   *     is stable.
   * @throws CircularDependencyException if there is a circular dependency
   *     between the provides and requires.
   * @throws MissingProvideException if an entry point was not provided
   *     by any of the inputs.
   * @see DependencyOptions for more info on how this works.
   */
  public List<CompilerInput> manageDependencies(
      DependencyOptions depOptions,
      List<CompilerInput> inputs)
      throws CircularDependencyException, MissingProvideException {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[78]++;

    SortedDependencies<CompilerInput> sorter =
        new SortedDependencies<CompilerInput>(inputs);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[79]++;
    Set<CompilerInput> entryPointInputs = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[80]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((depOptions.shouldPruneDependencies()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[23]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[81]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((depOptions.shouldDropMoochers()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[25]++;
        entryPointInputs.addAll(sorter.getInputsWithoutProvides());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[82]++;

      } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[26]++;}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[83]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[43]++;



      for (String entryPoint : depOptions.getEntryPoints()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[43]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[44]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[45]++;
}
        entryPointInputs.add(sorter.getInputProviding(entryPoint));
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[84]++;
      }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[85]++;

      CompilerInput baseJs = sorter.maybeGetInputProviding("goog");
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[86]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((baseJs != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[27]++;
        entryPointInputs.add(baseJs);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[87]++;

      } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[28]++;}

    } else {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[24]++;
      entryPointInputs.addAll(inputs);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[88]++;
    }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[89]++;

    // The order of inputs, sorted independently of modules.
    List<CompilerInput> absoluteOrder =
        sorter.getDependenciesOf(inputs, depOptions.shouldSortDependencies());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[90]++;

    // Figure out which sources *must* be in each module.
    ListMultimap<JSModule, CompilerInput> entryPointInputsPerModule =
        LinkedListMultimap.create();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[91]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[46]++;


    for (CompilerInput input : entryPointInputs) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[46]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[47]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[48]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[92]++;
      JSModule module = input.getModule();
      Preconditions.checkNotNull(module);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[93]++;
      entryPointInputsPerModule.put(module, input);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[94]++;
    }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[95]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[49]++;



    // Clear the modules of their inputs. This also nulls out
    // the input's reference to its module.
    for (JSModule module : getAllModules()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[49]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[50]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[51]++;
}
      module.removeAll();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[96]++;
    }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[97]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[52]++;



    // Figure out which sources *must* be in each module, or in one
    // of that module's dependencies.
    for (JSModule module : entryPointInputsPerModule.keySet()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[52]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[53]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[54]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[98]++;
      List<CompilerInput> transitiveClosure =
          sorter.getDependenciesOf(
              entryPointInputsPerModule.get(module),
              depOptions.shouldSortDependencies());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[99]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[55]++;


      for (CompilerInput input : transitiveClosure) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[55]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[56]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[57]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[100]++;
        JSModule oldModule = input.getModule();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[101]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((oldModule == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[29]++;
          input.setModule(module);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[102]++;

        } else {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[30]++;
          input.setModule(null);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[103]++;
          input.setModule(
              getDeepestCommonDependencyInclusive(oldModule, module));
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[104]++;
        }
      }
    }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[105]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[58]++;



    // All the inputs are pointing to the modules that own them. Yeah!
    // Update the modules to reflect this.
    for (CompilerInput input : absoluteOrder) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[58]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[59]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[60]++;
}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[106]++;
      JSModule module = input.getModule();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[107]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((module != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[31]++;
        module.add(input);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[108]++;

      } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[32]++;}
    }
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[109]++;

    // Now, generate the sorted result.
    List<CompilerInput> result = Lists.newArrayList();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[110]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[61]++;


    for (JSModule module : getAllModules()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[61]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[62]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[63]++;
}
      result.addAll(module.getInputs());
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[111]++;
    }

    return result;
  }

  LinkedDirectedGraph<JSModule, String> toGraphvizGraph() {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[112]++;
    LinkedDirectedGraph<JSModule, String> graphViz =
        LinkedDirectedGraph.create();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[113]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[64]++;


    for (JSModule module : getAllModules()) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[64]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[65]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[66]++;
}
      graphViz.createNode(module);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[114]++;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[115]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[67]++;


      for (JSModule dep : module.getDependencies()) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[67]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[68]--;
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.loops[69]++;
}
        graphViz.createNode(dep);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[116]++;
        graphViz.connect(module, "->", dep);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[117]++;
      }
    }
    return graphViz;
  }

  /**
   * A module depth comparator that considers a deeper module to be "less than"
   * a shallower module. Uses module names to consistently break ties.
   */
  private class InverseDepthComparator implements Comparator<JSModule> {
    @Override
    public int compare(JSModule m1, JSModule m2) {
      return depthCompare(m2, m1);
    }
  }

  private int depthCompare(JSModule m1, JSModule m2) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[118]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((m1 == m2) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[33]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.branches[34]++;}
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[119]++;
    int d1 = m1.getDepth();
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[120]++;
    int d2 = m2.getDepth();
    return d1 < d2 ? -1 : d2 == d1 ? m1.getName().compareTo(m2.getName()) : 1;
  }

  /*
   * Exception class for declaring when the modules being fed into a
   * JSModuleGraph as input aren't in dependence order, and so can't be
   * processed for caching of various dependency-related queries.
   */

  protected static class ModuleDependenceException
      extends IllegalArgumentException {
    private static final long serialVersionUID = 1;

    private final JSModule module;
    private final JSModule dependentModule;

    protected ModuleDependenceException(String message,
        JSModule module, JSModule dependentModule) {
      super(message);
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[121]++;
      this.module = module;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[122]++;
      this.dependentModule = dependentModule;
CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275.statements[123]++;
    }

    public JSModule getModule() {
      return module;
    }

    public JSModule getDependentModule() {
      return dependentModule;
    }
  }

}

class CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275 ());
  }
    public static long[] statements = new long[124];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JSModuleGraph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[70];

  public CodeCoverCoverageCounter$66ied3124k489r22lp4iyo8vp275 () {
    super("com.google.javascript.jscomp.JSModuleGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 123; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 69; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JSModuleGraph.java");
      for (int i = 1; i <= 123; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 23; i++) {
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

