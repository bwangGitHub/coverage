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

package com.google.javascript.jscomp;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Reference;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.VariableVisibilityAnalysis.VariableVisibility;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * A pass that analyzes side effects to determine when it is safe to move
 * code from one program point to another.
 *
 * In its current form, SideEffectsAnalysis is very incomplete; this is
 * mostly a sketch to prototype the interface and the broad strokes of
 * a possible implementation based on flow-insensitive MOD and REF sets.
 *
 * See:
 *
 * Banning, John. "An efficient way to find the side effects of procedure
 *      calls and the aliases of variables." POPL '79.
 *
 * For an introduction to MOD and REF sets.
 *
 * @author dcc@google.com (Devin Coughlin)
 */
 class SideEffectsAnalysis implements CompilerPass {
  static {
    CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.ping();
  }


   /**
    * The type of location abstraction to use for this analysis.
    */
  enum LocationAbstractionMode {
    /** See {@link DegenerateLocationAbstraction} for details. */
    DEGENERATE,
    /** See {@link VisibilityLocationAbstraction} for details. */
    VISIBILITY_BASED
  }

  private static final Predicate<Node> NOT_FUNCTION_PREDICATE =
      new Predicate<Node>() {
    @Override
    public boolean apply(Node input) {
      return !input.isFunction();
    }
  };
  static {
    CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[1]++;
  }

  private AbstractCompiler compiler;

  /** The location abstraction used to calculate the effects of code */
  private LocationAbstraction locationAbstraction;

  /** The kind of location abstraction to use */
  private final LocationAbstractionMode locationAbstractionIdentifier;

  /**
   * Constructs a new SideEffectsAnalysis with the given location abstraction.
   *
   * @param compiler A compiler instance
   * @param locationAbstractionMode The location abstraction to use. {@code
   *    DEGENERATE} will use {@link DegenerateLocationAbstraction} while
   *    {@code VISIBILITY_BASED} will use {@link VisibilityLocationAbstraction}
   *
   */
  public SideEffectsAnalysis(AbstractCompiler compiler,
      LocationAbstractionMode locationAbstractionMode) {
    this.compiler = compiler;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[2]++;

    this.locationAbstractionIdentifier = locationAbstractionMode;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[3]++;
  }

  public SideEffectsAnalysis(AbstractCompiler compiler) {
    this(compiler, LocationAbstractionMode.DEGENERATE);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[5]++;
    switch(locationAbstractionIdentifier) {
      case DEGENERATE:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[1]++;
        locationAbstraction = new DegenerateLocationAbstraction();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[6]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[7]++;
        break;
      case VISIBILITY_BASED:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[2]++;
        locationAbstraction = createVisibilityAbstraction(externs, root);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[8]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[9]++;
        break;
      default:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[3]++;
        throw new IllegalStateException("Unrecognized location abstraction " +
            "identifier: " + locationAbstractionIdentifier);
    }

    // In the future, this method
    // will construct a callgraph and calculate side effects summaries
    // for all functions.
    // TODO(dcc): Add per-function side effects summaries.
  }

  private LocationAbstraction createVisibilityAbstraction(Node externs,
      Node root) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[10]++;
    VariableVisibilityAnalysis variableVisibility =
        new VariableVisibilityAnalysis(compiler);

    variableVisibility.process(externs, root);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[11]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[12]++;

    VariableUseDeclarationMap variableMap =
        new VariableUseDeclarationMap(compiler);

    variableMap.mapUses(root);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[13]++;

   return new VisibilityLocationAbstraction(compiler,
       variableVisibility, variableMap);
  }

  /**
   * Determines whether it is safe to move code ({@code source}) across
   * an environment to another program point (immediately preceding
   * {@code destination}).
   *
   * <p>The notion of "environment" is optimization-specific, but it should
   * include any code that could be executed between the source program point
   * and the destination program point.
   *
   * {@code destination} must not be a descendant of {@code source}.
   *
   * @param source The node that would be moved
   * @param environment An environment representing the code across which
   *    the source will be moved.
   * @param destination The node before which the source would be moved
   * @return Whether it is safe to move the source to the destination
   */
  public boolean safeToMoveBefore(Node source,
      AbstractMotionEnvironment environment,
      Node destination) {
    Preconditions.checkNotNull(locationAbstraction);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[14]++;
    Preconditions.checkArgument(!nodeHasAncestor(destination, source));
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[15]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;

    // It is always safe to move pure code.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isPure(source)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[4]++;
      return true;

    } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[5]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;

    // Don't currently support interprocedural analysis
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((nodeHasCall(source)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[6]++;
      return false;

    } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[7]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[18]++;

    LocationSummary sourceLocationSummary =
        locationAbstraction.calculateLocationSummary(source);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[19]++;

    EffectLocation sourceModSet = sourceLocationSummary.getModSet();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;

    // If the source has side effects, then we require that the source
    // is executed exactly as many times as the destination.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((sourceModSet.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nodesHaveSameControlFlow(source, destination)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[8]++;
        return false;

    } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[9]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[21]++;

    EffectLocation sourceRefSet = sourceLocationSummary.getRefSet();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[22]++;

    Set<Node> environmentNodes = environment.calculateEnvironment();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[1]++;



    for (Node environmentNode : environmentNodes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[1]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[2]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[3]++;
}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((nodeHasCall(environmentNode)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[10]++;
        return false;

      } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[11]++;}
    }
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[25]++;

    LocationSummary environmentLocationSummary =
        locationAbstraction.calculateLocationSummary(environmentNodes);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[26]++;

    EffectLocation environmentModSet = environmentLocationSummary.getModSet();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[27]++;

    EffectLocation environmentRefSet = environmentLocationSummary.getRefSet();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;

    // If MOD(environment) intersects REF(source) then moving the
    // source across the environment could cause the source
    // to read an incorrect value.
    // If REF(environment) intersects MOD(source) then moving the
    // source across the environment could cause the environment
    // to read an incorrect value.
    // If MOD(environment) intersects MOD(source) then moving the
    // source across the environment could cause some later code that reads
    // a modified location to get an incorrect value.

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((environmentModSet.intersectsLocation(sourceRefSet)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((environmentRefSet.intersectsLocation(sourceModSet)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((environmentModSet.intersectsLocation(sourceModSet)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[12]++;
      return true;

    } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[13]++;}

    return false;
  }

  /**
   * Returns true if the node is pure, that is it side effect free and does it
   * not depend on its environment?
   * @param node node to check.
   */
  private boolean isPure(Node node) {
    // For now, we conservatively assume all code is not pure.
    // TODO(dcc): Implement isPure().
    return false;
  }

  /**
   * Returns true if the two nodes have the same control flow properties,
   * that is, is node1 be executed every time node2 is executed and vice versa?
   */
  private static boolean nodesHaveSameControlFlow(Node node1, Node node2) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[29]++;
    /*
     * We conservatively approximate this with the following criteria:
     *
     * Define the "deepest control dependent block" for a node to be the
     * closest ancestor whose *parent* is a control structure and where that
     * ancestor may or may be executed depending on the parent.
     *
     * So, for example, in:
     * if (a) {
     *  b;
     * } else {
     *  c;
     * }
     *
     * a has not deepest control dependent block.
     * b's deepest control dependent block is the "then" block of the IF.
     * c's deepest control dependent block is the "else" block of the IF.
     *
     * We'll say two nodes have the same control flow if
     *
     * 1) they have the same deepest control dependent block
     * 2) that block is either a CASE (which can't have early exits) or it
     * doesn't have any early exits (e.g. breaks, continues, returns.)
     *
     */

    Node node1DeepestControlDependentBlock =
        closestControlDependentAncestor(node1);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[30]++;

    Node node2DeepestControlDependentBlock =
      closestControlDependentAncestor(node2);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node1DeepestControlDependentBlock ==
        node2DeepestControlDependentBlock) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[14]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node2DeepestControlDependentBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[16]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        // CASE is complicated because we have to deal with fall through and
        // because some BREAKs are early exits and some are not.
        // For now, we don't allow movement within a CASE.
        //
        // TODO(dcc): be less conservative about movement within CASE
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node2DeepestControlDependentBlock.isCase()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[18]++;
          return false;

        } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[19]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[34]++;

        // Don't allow breaks, continues, returns in control dependent
        // block because we don't actually create a control-flow graph
        // and so don't know if early exits site between the source
        // and the destination.
        //
        // This is overly conservative as it doesn't allow, for example,
        // moving in the following case:
        // while (a) {
        //   source();
        //
        //   while(b) {
        //     break;
        //   }
        //
        //   destination();
        // }
        //
        // To fully support this kind of movement, we'll probably have to use
        // a CFG-based analysis rather than just looking at the AST.
        //
        // TODO(dcc): have nodesHaveSameControlFlow() use a CFG
        Predicate<Node> isEarlyExitPredicate = new Predicate<Node>() {
          @Override
          public boolean apply(Node input) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[35]++;
            int nodeType = input.getType();

            return nodeType == Token.RETURN
                || nodeType == Token.BREAK
                || nodeType == Token.CONTINUE;
          }
        };

        return !NodeUtil.has(node2DeepestControlDependentBlock,
            isEarlyExitPredicate, NOT_FUNCTION_PREDICATE);

      } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[17]++;
        return true;
      }

    } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[15]++;
      return false;
    }
  }

  /**
   * Returns true if the number of times the child executes depends on the
   * parent.
   *
   * For example, the guard of an IF is not control dependent on the
   * IF, but its two THEN/ELSE blocks are.
   *
   * Also, the guard of WHILE and DO are control dependent on the parent
   * since the number of times it executes depends on the parent.
   */
  private static boolean isControlDependentChild(Node child) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[36]++;
    Node parent = child.getParent();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[20]++;
      return false;

    } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[21]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[38]++;

    ArrayList<Node> siblings = Lists.newArrayList(parent.children());
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[39]++;

    int indexOfChildInParent = siblings.indexOf(child);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[40]++;

    switch(parent.getType()) {
      case Token.IF:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[22]++;
      case Token.HOOK:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[23]++;
        return (indexOfChildInParent == 1 || indexOfChildInParent == 2);
      case Token.WHILE:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[24]++;
      case Token.DO:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[25]++;
        return true;
      case Token.FOR:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[26]++;
        // Only initializer is not control dependent
        return indexOfChildInParent != 0;
      case Token.SWITCH:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[27]++;
          return indexOfChildInParent > 0;
      case Token.AND:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[28]++;
        return true;
      case Token.OR:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[29]++;
        return true;
      case Token.FUNCTION:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[30]++;
        return true;

      default:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[31]++;
        return false;
    }
  }

  private static Node closestControlDependentAncestor(Node node) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isControlDependentChild(node)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[32]++;
      return node;

    } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[33]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[42]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[4]++;



    // Note: node is not considered one of its ancestors
    for (Node ancestor : node.getAncestors()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[4]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[5]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[6]++;
}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[43]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isControlDependentChild(ancestor)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[34]++;
        return ancestor;

      } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[35]++;}
    }

    return null;
  }

  /**
   * Returns true if {@code possibleAncestor} is an ancestor of{@code node}.
   * A node is not considered to be an ancestor of itself.
   */
  private static boolean nodeHasAncestor(Node node, Node possibleAncestor) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[44]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[7]++;


    // Note node is not in node.getAncestors()

    for (Node ancestor : node.getAncestors()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[7]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[8]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[9]++;
}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((ancestor == possibleAncestor) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[36]++;
        return true;

      } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[37]++;}
    }

    return false;
  }

  /**
   * Returns true if a node has a CALL or a NEW descendant.
   */
  private boolean nodeHasCall(Node node) {
    return NodeUtil.has(node, new Predicate<Node>() {
      @Override
      public boolean apply(Node input) {
        return input.isCall() || input.isNew();
      }},
      NOT_FUNCTION_PREDICATE);
  }

  /**
   * Represents an environment across which code might be moved, i.e. the set
   * of code that could be run in between the source and the destination.
   *
   * SideEffectAnalysis characterizes the code to be moved and the environment
   * in order to determine if they interact in such a way as to make the move
   * unsafe.
   *
   * Since determining the environment for an optimization can be tricky,
   * we provide several concrete subclasses that common classes of optimizations
   * may be able to reuse.
   */
  public abstract static class AbstractMotionEnvironment {

    /**
     * Calculates the set of nodes that this environment represents.
     */
    public abstract Set<Node> calculateEnvironment();
  }

  /**
   * An environment for motion within a function. Given a
   * control flow graph and a source and destination node in the control
   * flow graph, instances of this object will calculate the environment between
   * the source and destination.
   */
  public static class IntraproceduralMotionEnvironment
      extends AbstractMotionEnvironment {

    /**
     * Creates an intraprocedural motion environment.
     *
     * @param controlFlowGraph A control flow graph for function in which
     * code will be moved
     * @param cfgSource The code to be moved
     * @param cfgDestination The node immediately before which cfgSource
     * will be moved
     */
    public IntraproceduralMotionEnvironment(
        ControlFlowGraph<Node> controlFlowGraph,
        Node cfgSource,
        Node cfgDestination) {

    }

    @Override
    public Set<Node> calculateEnvironment() {
      // TODO(dcc): Implement IntraproceduralMotionEnvironment
      return null;
    }
  }

  /**
   * An environment for motion between modules. Given a
   * module graph and as well as source and destination nodes and modules,
   * instances of this object will calculate the environment between the source
   * and destination.
   */
  public static class CrossModuleMotionEnvironment
      extends AbstractMotionEnvironment {

    /**
     * Creates a cross module code motion environment.
     *
     * @param sourceNode The code to be moved
     * @param sourceModule The module for the code to be moved
     * @param destinationNode The node before which sourceNode will be inserted
     * @param destinationModule The module that destination is in
     * @param moduleGraph The module graph of the entire program
     */
    public CrossModuleMotionEnvironment(Node sourceNode,
        JSModule sourceModule,
        Node destinationNode,
        JSModule destinationModule,
        JSModuleGraph moduleGraph) {

    }

    @Override
    public Set<Node> calculateEnvironment() {
      // TODO(dcc): Implement CrossModuleMotionEnvironment
      return null;
    }
  }
    /**
     * A low-level concrete environment that allows the client to specify
     * the environment nodes directly. Clients may wish to use this environment
     * if none of the higher-level environments fit their needs.
     */
  public static class RawMotionEnvironment
      extends AbstractMotionEnvironment {
    Set<Node> environment;

    public RawMotionEnvironment(Set<Node> environment) {
      this.environment = environment;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[46]++;
    }

    @Override
    public Set<Node> calculateEnvironment() {
      return environment;
    }
  }

  /*
   * A combined representation for location set summaries.
   *
   * Basically, it is often easier to shuffle MOD/REF around together; this is
   * a value class for that purpose.
   */
  private static class LocationSummary {

    private EffectLocation modSet;
    private EffectLocation refSet;

    public LocationSummary(EffectLocation modSet, EffectLocation refSet) {
      this.modSet = modSet;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[47]++;
      this.refSet = refSet;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[48]++;
    }

    public EffectLocation getModSet() {
      return modSet;
    }

    public EffectLocation getRefSet() {
      return refSet;
    }
  }

  /**
   * Interface representing the notion of an effect location -- an abstract
   * location that can be modified or referenced.
   *
   * <p>Since there are an infinite number of possible concrete locations
   * in a running program, this abstraction must be imprecise (i.e. there
   * will be some distinct concrete locations that are indistinguishable
   * under the abstraction).
   *
   * <p>Different location abstractions will provide their
   * own implementations of this interface, based on the level and kind
   * of precision they provide.
   */
  private static interface EffectLocation {

    /**
     * Does the receiver's effect location intersect a given effect location?
     * That is, could any of the concrete storage locations (fields, variables,
     * etc.) represented by the receiver be contained in the set of concrete
     * storage locations represented by the given abstract effect location.
     */
    public boolean intersectsLocation(EffectLocation otherLocation);

    /**
     * Returns the result of merging the given effect location with
     * the receiver. The concrete locations represented by the result must
     * include all the concrete locations represented by each of the merged
     * locations and may also possibly include more (i.e., a join may
     * introduce a loss of precision).
     */
    public EffectLocation join(EffectLocation otherLocation);

    /**
     * Does the effect location represent any possible concrete locations?
     */
    public boolean isEmpty();
  }

  /**
   * An abstract class representing a location abstraction. (Here "abstraction"
   * means an imprecise representation of concrete side effects.)
   *
   * <p>Implementations of this class will each provide own their
   * implementation(s) of SideEffectLocation and methods to determine the side
   * effect locations of a given piece of code.
   */
  private abstract static class LocationAbstraction  {

    /** Calculates the abstraction-specific side effects
     * for the node.
     */
    abstract LocationSummary calculateLocationSummary(Node node);

    /**
     * Returns an abstraction-specific EffectLocation representing
     * no location.
     *
     * <p>The bottom location joined with any location should return
     * that location.
     */
    abstract EffectLocation getBottomLocation();

    /**
     * Calculates the abstraction-specific side effects
     * for the node.
     */
    public LocationSummary calculateLocationSummary(Set<Node> nodes) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[49]++;
      EffectLocation modAccumulator = getBottomLocation();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[50]++;
      EffectLocation refAccumulator = getBottomLocation();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[51]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[10]++;



      for (Node node : nodes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[10]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[11]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[12]++;
}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[52]++;
        LocationSummary nodeLocationSummary = calculateLocationSummary(node);

        modAccumulator = modAccumulator.join(nodeLocationSummary.getModSet());
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[53]++;
        refAccumulator = refAccumulator.join(nodeLocationSummary.getRefSet());
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[54]++;
      }

      return new LocationSummary(modAccumulator, refAccumulator);
    }
  }
  /**
   * A very imprecise location abstraction in which there are only two abstract
   * locations: one representing all concrete locations and one for bottom
   * (no concrete locations).
   *
   * This implementation is a thin wrapper on NodeUtil.mayHaveSideEffects()
   * and NodeUtil.canBeSideEffected() -- it doesn't add any real value other
   * than to prototype the LocationAbstraction interface.
   */
  private static class DegenerateLocationAbstraction
      extends LocationAbstraction {

    private static final EffectLocation EVERY_LOCATION =
        new DegenerateEffectLocation();

    private static final EffectLocation NO_LOCATION =
        new DegenerateEffectLocation();

    @Override
    EffectLocation getBottomLocation() {
      return NO_LOCATION;
    }

    @Override
    public LocationSummary calculateLocationSummary(Node node) {
      return new LocationSummary(calculateModSet(node), calculateRefSet(node));
    }

    EffectLocation calculateRefSet(Node node) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[55]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.canBeSideEffected(node)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[38]++;
        return EVERY_LOCATION;

      } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[39]++;
        return NO_LOCATION;
      }
    }

    EffectLocation calculateModSet(Node node) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[56]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(node)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[40]++;
        return EVERY_LOCATION;

      } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[41]++;
        return NO_LOCATION;
      }
    }

    private static class DegenerateEffectLocation implements EffectLocation {
       @Override
      public EffectLocation join(EffectLocation otherLocation) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((otherLocation == EVERY_LOCATION) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[42]++;
          return otherLocation;

        } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[43]++;
          return this;
        }
      }

      @Override
      public boolean intersectsLocation(EffectLocation otherLocation) {
        return this == EVERY_LOCATION && otherLocation == EVERY_LOCATION;
      }

      @Override
      public boolean isEmpty() {
        return this == NO_LOCATION;
      }
    }
  }

  /**
   * A location abstraction based on the visibility of concrete locations.
   *
   * A global variables are treated as one common location, as are all heap
   * storage locations.
   *
   * Local variables are broken up into two classes, one for truly local
   * variables and one for local variables captured by an inner scope. Each
   * of these classes has their own separate location representing the
   * variables in the class.
   *
   * Parameter variables are considered to be heap locations since they
   * can be accessed via the arguments object which itself can be aliased.
   *
   * A more precise analysis could:
   *    1) put parameters on the heap only when "arguments" is actually used
   *        in a method
   *    2) recognize that GETPROPs cannot access or modify parameters, only
   *        GETELEMs
   *
   * TODO(dcc): Don't merge parameters with the heap unless necessary.
   *
   * Internally, abstract locations are represented as integers
   * with bits set (masks) representing the storage classes in the location, so
   * that joining is bit-wise ORing and intersection is bitwise AND.
   */
  private static class VisibilityLocationAbstraction
      extends LocationAbstraction {

    /** The "bottom" location. Used to signify an empty location set */
    private static final int VISIBILITY_LOCATION_NONE = 0;

    /** The "top" location. Used to signify the set containing all locations */
    private static final int UNKNOWN_LOCATION_MASK = 0xFFFFFFFF;

    private static final int LOCAL_VARIABLE_LOCATION_MASK = 1 << 1;

    private static final int CAPTURED_LOCAL_VARIABLE_LOCATION_MASK = 1 << 2;

    private static final int GLOBAL_VARIABLE_LOCATION_MASK = 1 << 3;

    private static final int HEAP_LOCATION_MASK = 1 << 4;

    AbstractCompiler compiler;

    VariableVisibilityAnalysis variableVisibilityAnalysis;
    VariableUseDeclarationMap variableUseMap;

    private VisibilityLocationAbstraction(AbstractCompiler compiler,
        VariableVisibilityAnalysis variableVisibilityAnalysis,
        VariableUseDeclarationMap variableUseMap) {
      this.compiler = compiler;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[58]++;
      this.variableVisibilityAnalysis = variableVisibilityAnalysis;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[59]++;
      this.variableUseMap = variableUseMap;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[60]++;
    }

    /**
     * Calculates the MOD/REF summary for the given node.
     */
    @Override
    LocationSummary calculateLocationSummary(Node node) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[61]++;
      int visibilityRefLocations = VISIBILITY_LOCATION_NONE;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[62]++;
      int visibilityModLocations = VISIBILITY_LOCATION_NONE;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[63]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[13]++;



      for (Node reference : findStorageLocationReferences(node)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[13]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[14]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[15]++;
}
        int effectMask;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((reference.isName()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[44]++;
          // Variable access
          effectMask = effectMaskForVariableReference(reference);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[65]++;

         } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[45]++;
          // Heap access
          effectMask = HEAP_LOCATION_MASK;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[66]++;
        }
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[67]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((storageNodeIsLValue(reference)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[46]++;
          visibilityModLocations |= effectMask;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[68]++;

        } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[47]++;}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((storageNodeIsRValue(reference)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[48]++;
          visibilityRefLocations |= effectMask;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[70]++;

        } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[49]++;}
      }
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[71]++;

      VisibilityBasedEffectLocation modSet =
          new VisibilityBasedEffectLocation(visibilityModLocations);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[72]++;

      VisibilityBasedEffectLocation refSet =
        new VisibilityBasedEffectLocation(visibilityRefLocations);

      return new LocationSummary(modSet, refSet);
    }

    /**
     * Returns the set of references to storage locations (both variables
     * and the heap) under {@code root}.
     */
    private Set<Node> findStorageLocationReferences(Node root) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[73]++;
      final Set<Node> references = Sets.newHashSet();

      NodeTraversal.traverse(compiler, root, new AbstractShallowCallback() {
        @Override
        public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[75]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((NodeUtil.isGet(n)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[50]++;
            references.add(n);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[76]++;

          } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[51]++;}
        }
      });
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[74]++;

      return references;
    }

    /**
     * Calculates the effect mask for a variable reference.
     */
    private int effectMaskForVariableReference(Node variableReference) {
      Preconditions.checkArgument(variableReference.isName());
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[77]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[78]++;

      int effectMask = VISIBILITY_LOCATION_NONE;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[79]++;

      Node declaringNameNode =
        variableUseMap.findDeclaringNameNodeForUse(variableReference);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[80]++;
int CodeCoverConditionCoverageHelper_C20;

      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((declaringNameNode != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[52]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[81]++;
        VariableVisibility visibility =
          variableVisibilityAnalysis.getVariableVisibility(declaringNameNode);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[82]++;

        switch (visibility) {
          case LOCAL:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[54]++;
            effectMask = LOCAL_VARIABLE_LOCATION_MASK;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[83]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[84]++;
            break;
          case CAPTURED_LOCAL:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[55]++;
            effectMask = CAPTURED_LOCAL_VARIABLE_LOCATION_MASK;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[85]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[86]++;
            break;
          case PARAMETER:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[56]++;
            // Parameters are considered to be on the heap since they
            // can be accessed via the arguments object.
            effectMask = HEAP_LOCATION_MASK;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[87]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[88]++;
            break;
          case GLOBAL:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[57]++;
            effectMask = GLOBAL_VARIABLE_LOCATION_MASK;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[89]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[90]++;
            break;
          default:
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[58]++;
            throw new IllegalStateException("Unrecognized variable" +
                " visibility: " + visibility);
        }

      } else {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[53]++;
        // Couldn't find a variable for the reference
        effectMask = UNKNOWN_LOCATION_MASK;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[91]++;
      }

      return effectMask;
    }

    @Override
    EffectLocation getBottomLocation() {
      return new VisibilityBasedEffectLocation(VISIBILITY_LOCATION_NONE);
    }

    /**
     * Returns true if the node is a storage node.
     *
     * Only NAMEs, GETPROPs, and GETELEMs are storage nodes.
     */
    private static boolean isStorageNode(Node node) {
      return node.isName() || NodeUtil.isGet(node);
    }

    /**
     * Return true if the storage node is an r-value.
     */
    private static boolean storageNodeIsRValue(Node node) {
      Preconditions.checkArgument(isStorageNode(node));
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[92]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[93]++;

      // We consider all names to be r-values unless
      // LHS of Token.ASSIGN
      // LHS of of for in expression
      // Child of VAR

      Node parent = node.getParent();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[94]++;
int CodeCoverConditionCoverageHelper_C21;

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((storageNodeIsLValue(node)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[59]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[95]++;
        // Assume l-value is NOT an r-value
        // unless it is a non-simple assign
        // or an increment/decrement

        boolean nonSimpleAssign =
          NodeUtil.isAssignmentOp(parent) && !parent.isAssign();

        return (nonSimpleAssign
            || parent.isDec()
            || parent.isInc());

      } else {
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.branches[60]++;}

      return true;
    }

    /**
     * Return true if the storage node is an l-value.
     */
    private static boolean storageNodeIsLValue(Node node) {
      Preconditions.checkArgument(isStorageNode(node));
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[96]++;
      return NodeUtil.isLValue(node);
    }

    /**
     * An abstract effect location based the visibility of the
     * concrete storage location.
     *
     * See {@link VisibilityLocationAbstraction} for deeper description
     * of this abstraction.
     *
     * The effect locations are stored as bits set on an integer, so
     * intersect, join, etc. are the standard bitwise operations.
     */
    private static class VisibilityBasedEffectLocation
        implements EffectLocation {
      int visibilityMask = VISIBILITY_LOCATION_NONE;
  {
    CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[97]++;
  }

      public VisibilityBasedEffectLocation(int visibilityMask) {
        this.visibilityMask = visibilityMask;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[98]++;
      }

      @Override
      public boolean intersectsLocation(EffectLocation otherLocation) {
        Preconditions.checkArgument(otherLocation instanceof
            VisibilityBasedEffectLocation);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[99]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[100]++;

        int otherMask =
            ((VisibilityBasedEffectLocation) otherLocation).visibilityMask;

        return (visibilityMask & otherMask) > 0;
      }

      @Override
      public boolean isEmpty() {
        return visibilityMask == VISIBILITY_LOCATION_NONE;
      }

      @Override
      public EffectLocation join(EffectLocation otherLocation) {
        Preconditions.checkArgument(otherLocation instanceof
            VisibilityBasedEffectLocation);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[101]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[102]++;

        int otherMask =
            ((VisibilityBasedEffectLocation) otherLocation).visibilityMask;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[103]++;

        int joinedMask = visibilityMask | otherMask;

        return new VisibilityBasedEffectLocation(joinedMask);
      }
    }
  }

  /**
   * Maps NAME nodes that refer to variables to the NAME
   * nodes that declared them.
   */
  private static class VariableUseDeclarationMap {

    private AbstractCompiler compiler;

    // Maps a using name to its declaring name
    private Map<Node, Node> referencesByNameNode;

    public VariableUseDeclarationMap(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[104]++;
    }

    /**
     * Adds a map from each use NAME in {@code root} to its corresponding
     * declaring name, *provided the declaration is also under root*.
     *
     * If the declaration is not under root, then the reference will
     * not be added to the map.
     */
    public void mapUses(Node root) {
      referencesByNameNode = Maps.newHashMap();
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[105]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[106]++;

      ReferenceCollectingCallback callback =
        new ReferenceCollectingCallback(compiler,
            ReferenceCollectingCallback.DO_NOTHING_BEHAVIOR);

      NodeTraversal.traverse(compiler, root, callback);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[107]++;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[108]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[16]++;



      for (Var variable : callback.getAllSymbols()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[16]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[17]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[18]++;
}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[109]++;
        ReferenceCollection referenceCollection =
            callback.getReferences(variable);
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[110]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[19]++;



        for (Reference reference : referenceCollection.references) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[19]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[20]--;
  CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.loops[21]++;
}
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[111]++;
          Node referenceNameNode = reference.getNode();

          // Note that this counts a declaration as a reference to itself
          referencesByNameNode.put(referenceNameNode, variable.getNameNode());
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[112]++;
        }
      }
    }

    /**
     * Returns the NAME node for the declaration of the variable
     * that {@code usingNameNode} refers to, if it is in the map,
     * or {@code null} otherwise.
     */
    public Node findDeclaringNameNodeForUse(Node usingNameNode) {
      Preconditions.checkArgument(usingNameNode.isName());
CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5.statements[113]++;

      return referencesByNameNode.get(usingNameNode);
    }
  }
}

class CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5 ());
  }
    public static long[] statements = new long[114];
    public static long[] branches = new long[61];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[22];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SideEffectsAnalysis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1};
    for (int i = 1; i <= 21; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$j830ejciehxnfvptc0bbxrupessmxfxvrrtr5 () {
    super("com.google.javascript.jscomp.SideEffectsAnalysis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 113; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 60; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SideEffectsAnalysis.java");
      for (int i = 1; i <= 113; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 60; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 21; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

