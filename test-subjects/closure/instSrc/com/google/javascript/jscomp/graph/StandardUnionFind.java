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
package com.google.javascript.jscomp.graph;

import javax.annotation.Nullable;
import com.google.common.base.Objects;
import static com.google.common.base.Preconditions.checkArgument;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import static com.google.common.collect.Iterators.filter;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A Union-Find implementation.
 *
 * <p>This class implements Union-Find algorithm with rank and path
 * compression.
 *
 * <p>See <a
 * href="http://www.algorithmist.com/index.php?title=Union_Find&oldid=7575">
 * algorithmist</a> for more detail.
 *
 * @param <E> element type
 */
public class StandardUnionFind<E> implements Serializable, UnionFind<E> {
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.ping();
  }


  private static final long serialVersionUID = -1L;
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[1]++;
  }

  /** All values with the same root node are in the same equivalence set. */
  private final Map<E, Node<E>> elmap = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[2]++;
  }

  /** Creates an empty UnionFind structure. */
  public StandardUnionFind() {
  }

  /**
   * Creates an UnionFind structure being a copy of other structure.
   * The created structure is optimal in a sense that the paths to
   * the root from any element will have a length of at most 1.
   *
   * @param other structure to be copied
   */
  public StandardUnionFind(UnionFind<E> other) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[1]++;


    for (E elem : other.elements()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[1]--;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[2]--;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[3]++;
}
      union(elem, other.find(elem));
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[4]++;
    }
  }

  @Override
  public void add(E e) {
    union(e, e);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[5]++;
  }

  @Override
  public E union(E a, E b) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[6]++;
    Node<E> nodeA = findRootOrCreateNode(a);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[7]++;
    Node<E> nodeB = findRootOrCreateNode(b);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nodeA == nodeB) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[1]++;
      return nodeA.element;

    } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[2]++;}
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((nodeA.rank > nodeB.rank) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[3]++;
      nodeB.parent = nodeA;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[10]++;
      nodeA.size += nodeB.size;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[11]++;
      return nodeA.element;

    } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[4]++;}
    nodeA.parent = nodeB;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[12]++;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nodeA.rank == nodeB.rank) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[5]++;
      nodeB.rank++;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[14]++;

    } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[6]++;}
    nodeB.size += nodeA.size;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[15]++;
    return nodeB.element;
  }

  @Override
  public E find(E e) {
    checkArgument(elmap.containsKey(e), "Element does not exist: %s", e);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[16]++;
    return findRoot(elmap.get(e)).element;
  }

  @Override
  public boolean areEquivalent(E a, E b) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[17]++;
    E aRep = find(a);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[18]++;
    E bRep = find(b);
    return aRep == bRep;
  }

  @Override
  public Set<E> elements() {
    return Collections.unmodifiableSet(elmap.keySet());
  }

  @Override
  public Collection<Set<E>> allEquivalenceClasses() {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[19]++;
    Map<Node<E>, ImmutableSet.Builder<E>> groupsTmp = Maps.newHashMap();
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[20]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[4]++;


    for (Node<E> elem : elmap.values()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[4]--;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[5]--;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[6]++;
}
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[21]++;
      Node<E> root = findRoot(elem);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[22]++;
      ImmutableSet.Builder<E> builder = groupsTmp.get(root);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((builder == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[7]++;
        builder = ImmutableSet.builder();
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[24]++;
        groupsTmp.put(root, builder);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[25]++;

      } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[8]++;}
      builder.add(elem.element);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[26]++;
    }
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[27]++;
    ImmutableList.Builder<Set<E>> result = ImmutableList.builder();
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[7]++;


    for (ImmutableSet.Builder<E> group : groupsTmp.values()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[7]--;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[8]--;
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.loops[9]++;
}
      result.add(group.build());
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[29]++;
    }
    return result.build();
  }

  /**
   * If e is already in a non-trivial equivalence class, that is, a class with
   * more than two elements, then return the {@link Node} corresponding to the
   * representative element. Otherwise, if e sits in an equivalence class by
   * itself, then create a {@link Node}, put it into elmap and return it.
   */
  private Node<E> findRootOrCreateNode(E e) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[30]++;
    Node<E> node = elmap.get(e);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[9]++;
      return findRoot(node);

    } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[10]++;}
    node = new Node<E>(e);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[32]++;
    elmap.put(e, node);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[33]++;
    return node;
  }

  /**
   * Given a {@link Node}, walk the parent field as far as possible, until
   * reaching the root, which is the {@link Node} for the current
   * representative of this equivalence class. To achieve low runtime
   * complexity, also compress the path, by making each node a direct child of
   * the root.
   */
  private Node<E> findRoot(Node<E> node) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node.parent != node) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[11]++;
      node.parent = findRoot(node.parent);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[35]++;

    } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[12]++;}
    return node.parent;
  }

  @Override
  public Set<E> findAll(final E value) {
    checkArgument(elmap.containsKey(value), "Element does not exist: " + value);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[36]++;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[37]++;

    final Predicate<Object> isSameRoot = new Predicate<Object>() {

      /** some node that's close to the root, or null */
      Node<E> nodeForValue = elmap.get(value);

      @Override
      public boolean apply(@Nullable Object b) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((Objects.equal(value, b)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[13]++;
          return true;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[14]++;}
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[39]++;
        Node<E> nodeForB = elmap.get(b);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((nodeForB == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[15]++;
          return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.branches[16]++;}
        nodeForValue = findRoot(nodeForValue);
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[41]++;
        return findRoot(nodeForB) == nodeForValue;
      }
    };

    return new AbstractSet<E>() {

      @Override public boolean contains(Object o) {
        return isSameRoot.apply(o);
      }

      @Override public Iterator<E> iterator() {
        return filter(elmap.keySet().iterator(),
            isSameRoot);
      }

      @Override public int size() {
        return findRoot(elmap.get(value)).size;
      }
    };
  }

  /** The internal node representation. */
  private static class Node<E> {
    /** The parent node of this element. */
    Node<E> parent;

    /** The element represented by this node. */
    final E element;

    /** A bound on the depth of the subtree rooted to this node. */
    int rank = 0;
  {
    CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[42]++;
  }

    /**
     * If this node is the root of a tree, this is the number of elements in the
     * tree. Otherwise, it's undefined.
     */
    int size = 1;
  {
    CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[43]++;
  }

    Node(E element) {
      this.parent = this;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[44]++;
      this.element = element;
CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl.statements[45]++;
    }
  }
}

class CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl ());
  }
    public static long[] statements = new long[46];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.StandardUnionFind.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$doyh2wigzhi2w5rhh0okeh637rihm2hmjl () {
    super("com.google.javascript.jscomp.graph.StandardUnionFind.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 45; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.StandardUnionFind.java");
      for (int i = 1; i <= 45; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

