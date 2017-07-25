/*
 * Copyright 2010 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.deps;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * A sorted list of inputs with dependency information. Uses a stable
 * topological sort to make sure that an input always comes after its
 * dependencies.
 *
 * Also exposes other information about the inputs, like which inputs
 * do not provide symbols.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public class SortedDependencies<INPUT extends DependencyInfo> {
  static {
    CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.ping();
  }


  private final List<INPUT> inputs;

  // A topologically sorted list of the inputs.
  private final List<INPUT> sortedList;

  // A list of all the inputs that do not have provides.
  private final List<INPUT> noProvides;

  private final Map<String, INPUT> provideMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[1]++;
  }

  public SortedDependencies(List<INPUT> inputs)
      throws CircularDependencyException {
    this.inputs = Lists.newArrayList(inputs);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[2]++;
    noProvides = Lists.newArrayList();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[3]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[1]++;



    // Collect all symbols provided in these files.
    for (INPUT input : inputs) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[1]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[2]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[3]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[5]++;
      Collection<String> currentProvides = input.getProvides();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((currentProvides.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[1]++;
        noProvides.add(input);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[7]++;

      } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[2]++;}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[8]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[4]++;



      for (String provide : currentProvides) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[4]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[5]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[6]++;
}
        provideMap.put(provide, input);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[9]++;
      }
    }
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[10]++;

    // Get the direct dependencies.
    final Multimap<INPUT, INPUT> deps = HashMultimap.create();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[11]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[7]++;


    for (INPUT input : inputs) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[7]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[8]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[9]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[12]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[10]++;


      for (String req : input.getRequires()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[10]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[11]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[12]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[13]++;
        INPUT dep = provideMap.get(req);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((dep != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dep != input) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[3]++;
          deps.put(input, dep);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[15]++;

        } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[4]++;}
      }
    }

    // Sort the inputs by sucking in 0-in-degree nodes until we're done.
    sortedList = topologicalStableSort(inputs, deps);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[16]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

    // The dependency graph of inputs has a cycle iff sortedList is a proper
    // subset of inputs. Also, it has a cycle iff the subgraph
    // (inputs - sortedList) has a cycle. It's fairly easy to prove this
    // by the lemma that a graph has a cycle iff it has a subgraph where
    // no nodes have out-degree 0. I'll leave the proof of this as an exercise
    // to the reader.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sortedList.size() < inputs.size()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[5]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[18]++;
      List<INPUT> subGraph = Lists.newArrayList(inputs);
      subGraph.removeAll(sortedList);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[19]++;

      throw new CircularDependencyException(
          cycleToString(findCycle(subGraph, deps)));

    } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[6]++;}
  }

  /**
   * Return the input that gives us the given symbol.
   * @throws MissingProvideException An exception if there is no
   *     input for this symbol.
   */
  public INPUT getInputProviding(String symbol)
      throws MissingProvideException {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((provideMap.containsKey(symbol)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[7]++;
      return provideMap.get(symbol);

    } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[8]++;}
    throw new MissingProvideException(symbol);
  }

  /**
   * Return the input that gives us the given symbol, or null.
   */
  public INPUT maybeGetInputProviding(String symbol) {
    return provideMap.get(symbol);
  }

  /**
   * Returns the first circular dependency found. Expressed as a list of
   * items in reverse dependency order (the second element depends on the
   * first, etc.).
   */
  private List<INPUT> findCycle(
      List<INPUT> subGraph, Multimap<INPUT, INPUT> deps) {
    return findCycle(subGraph.get(0), Sets.<INPUT>newHashSet(subGraph),
        deps, Sets.<INPUT>newHashSet());
  }

  private List<INPUT> findCycle(
      INPUT current, Set<INPUT> subGraph, Multimap<INPUT, INPUT> deps,
      Set<INPUT> covered) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((covered.add(current)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[9]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[22]++;
      List<INPUT> cycle = findCycle(
          findRequireInSubGraphOrFail(current, subGraph),
          subGraph, deps, covered);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;

      // Don't add the input to the list if the cycle has closed already.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((cycle.get(0) != cycle.get(cycle.size() - 1)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[11]++;
        cycle.add(current);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[24]++;

      } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[12]++;}

      return cycle;

    } else {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[10]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[25]++;
      // Explicitly use the add() method, to prevent a generics constructor
      // warning that is dumb. The condition it's protecting is
      // obscure, and I think people have proposed that it be removed.
      List<INPUT> cycle = Lists.<INPUT>newArrayList();
      cycle.add(current);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[26]++;
      return cycle;
    }
  }

  private INPUT findRequireInSubGraphOrFail(INPUT input, Set<INPUT> subGraph) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[27]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[13]++;


    for (String symbol : input.getRequires()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[13]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[14]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[15]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[28]++;
      INPUT candidate = provideMap.get(symbol);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((subGraph.contains(candidate)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[13]++;
        return candidate;

      } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[14]++;}
    }
    throw new IllegalStateException("no require found in subgraph");
  }

  /**
   * @param cycle A cycle in reverse-dependency order.
   */
  private String cycleToString(List<INPUT> cycle) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[30]++;
    List<String> symbols = Lists.newArrayList();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[31]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[16]++;


int CodeCoverConditionCoverageHelper_C8;
    for (int i = cycle.size() - 1;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[16]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[17]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[18]++;
}
      symbols.add(cycle.get(i).getProvides().iterator().next());
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[32]++;
    }
    symbols.add(symbols.get(0));
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[33]++;
    return Joiner.on(" -> ").join(symbols);
  }

  public List<INPUT> getSortedList() {
    return Collections.<INPUT>unmodifiableList(sortedList);
  }

  /**
   * Gets all the dependencies of the given roots. The inputs must be returned
   * in a stable order. In other words, if A comes before B, and A does not
   * transitively depend on B, then A must also come before B in the returned
   * list.
   */
  public List<INPUT> getSortedDependenciesOf(List<INPUT> roots) {
    return getDependenciesOf(roots, true);
  }

  /**
   * Gets all the dependencies of the given roots. The inputs must be returned
   * in a stable order. In other words, if A comes before B, and A does not
   * transitively depend on B, then A must also come before B in the returned
   * list.
   *
   * @param sorted If true, get them in topologically sorted order. If false,
   *     get them in the original order they were passed to the compiler.
   */
  public List<INPUT> getDependenciesOf(List<INPUT> roots, boolean sorted) {
    Preconditions.checkArgument(inputs.containsAll(roots));
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[34]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[35]++;
    Set<INPUT> included = Sets.newHashSet();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[36]++;
    Deque<INPUT> worklist = new ArrayDeque<INPUT>(roots);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[37]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[19]++;


int CodeCoverConditionCoverageHelper_C9;
    while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((worklist.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[19]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[20]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[21]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[38]++;
      INPUT current = worklist.pop();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((included.add(current)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[15]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[40]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[22]++;


        for (String req : current.getRequires()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[22]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[23]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[24]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[41]++;
          INPUT dep = provideMap.get(req);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((dep != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[17]++;
            worklist.add(dep);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[43]++;

          } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[18]++;}
        }

      } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[16]++;}
    }
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[44]++;

    ImmutableList.Builder<INPUT> builder = ImmutableList.builder();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[45]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[25]++;


    for (INPUT current : (sorted ? sortedList : inputs)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[25]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[26]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[27]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((included.contains(current)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[19]++;
        builder.add(current);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[47]++;

      } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[20]++;}
    }
    return builder.build();
  }

  public List<INPUT> getInputsWithoutProvides() {
    return Collections.<INPUT>unmodifiableList(noProvides);
  }

  private static <T> List<T> topologicalStableSort(
      List<T> items, Multimap<T, T> deps) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[48]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((items.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[21]++;
      // Priority queue blows up if we give it a size of 0. Since we need
      // to special case this either way, just bail out.
      return Lists.newArrayList();

    } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[22]++;}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[49]++;

    final Map<T, Integer> originalIndex = Maps.newHashMap();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[50]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[28]++;


int CodeCoverConditionCoverageHelper_C14;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < items.size()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[28]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[29]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[30]++;
}
      originalIndex.put(items.get(i), i);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[51]++;
    }
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[52]++;

    PriorityQueue<T> inDegreeZero = new PriorityQueue<T>(items.size(),
        new Comparator<T>() {
      @Override
      public int compare(T a, T b) {
        return originalIndex.get(a).intValue() -
            originalIndex.get(b).intValue();
      }
    });
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[53]++;
    List<T> result = Lists.newArrayList();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[54]++;

    Multiset<T> inDegree = HashMultiset.create();
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[55]++;
    Multimap<T, T> reverseDeps = ArrayListMultimap.create();
    Multimaps.invertFrom(deps, reverseDeps);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[56]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[57]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[31]++;



    // First, add all the inputs with in-degree 0.
    for (T item : items) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[31]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[32]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[33]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[58]++;
      Collection<T> itemDeps = deps.get(item);
      inDegree.add(item, itemDeps.size());
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[59]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[60]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((itemDeps.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[23]++;
        inDegreeZero.add(item);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[61]++;

      } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[24]++;}
    }
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[62]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[34]++;


int CodeCoverConditionCoverageHelper_C16;

    // Then, iterate to a fixed point over the reverse dependency graph.
    while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((inDegreeZero.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[34]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[35]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[36]++;
}
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[63]++;
      T item = inDegreeZero.remove();
      result.add(item);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[64]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[65]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[37]++;


      for (T inWaiting : reverseDeps.get(item)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[37]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[38]--;
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.loops[39]++;
}
        inDegree.remove(inWaiting, 1);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[66]++;
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[67]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((inDegree.count(inWaiting) == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[25]++;
          inDegreeZero.add(inWaiting);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[68]++;

        } else {
  CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.branches[26]++;}
      }
    }

    return result;
  }

  public static class CircularDependencyException extends Exception {
    CircularDependencyException(String message) {
      super(message);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[69]++;
    }
  }

  public static class MissingProvideException extends Exception {
    MissingProvideException(String provide) {
      super(provide);
CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t.statements[70]++;
    }
  }
}

class CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t ());
  }
    public static long[] statements = new long[71];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.SortedDependencies.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$2pco0d11b7rf4xwmzf8v90wfaw4on2cz421t () {
    super("com.google.javascript.jscomp.deps.SortedDependencies.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 70; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.deps.SortedDependencies.java");
      for (int i = 1; i <= 70; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

