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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Creates synthetic blocks to optimizations from moving code
 * past markers in the source.
 *
 */
class CreateSyntheticBlocks implements CompilerPass {
  static {
    CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.ping();
  }

  static final DiagnosticType UNMATCHED_START_MARKER = DiagnosticType.warning(
      "JSC_UNMATCHED_START_MARKER", "Unmatched {0}");
  static {
    CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[1]++;
  }

  static final DiagnosticType UNMATCHED_END_MARKER = DiagnosticType.warning(
      "JSC_UNMATCHED_END_MARKER", "Unmatched {1} - {0} not in the same block");
  static {
    CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[2]++;
  }

  static final DiagnosticType INVALID_MARKER_USAGE = DiagnosticType.warning(
      "JSC_INVALID_MARKER_USAGE", "Marker {0} can only be used in a simple "
           + "call expression");
  static {
    CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[3]++;
  }

  private final AbstractCompiler compiler;

  /** Name of the start marker. */
  private final String startMarkerName;

  /** Name of the end marker. */
  private final String endMarkerName;

  /**
   * Markers can be nested.
   */
  private final Deque<Node> markerStack = new ArrayDeque<Node>();
  {
    CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[4]++;
  }

  private final List<Marker> validMarkers = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[5]++;
  }

  private class Marker {
    final Node startMarker;
    final Node endMarker;
    public Marker(Node startMarker, Node endMarker) {
      this.startMarker = startMarker;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[6]++;
      this.endMarker = endMarker;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[7]++;
    }
  }

  public CreateSyntheticBlocks(AbstractCompiler compiler,
      String startMarkerName, String endMarkerName) {
    this.compiler = compiler;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[8]++;
    this.startMarkerName = startMarkerName;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[9]++;
    this.endMarkerName = endMarkerName;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[10]++;
  }

  @Override
  public void process(Node externs, Node root) {
    // Find and validate the markers.
    NodeTraversal.traverse(compiler, root, new Callback());
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[11]++;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[1]++;



    // Complain about any unmatched markers.
    for (Node node : markerStack) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[1]--;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[2]--;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[3]++;
}
      compiler.report(
          JSError.make(NodeUtil.getSourceName(node),
          node,
          UNMATCHED_START_MARKER, startMarkerName));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[13]++;
    }
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[4]++;



    // Add the block for the valid marker sets.
    for (Marker marker : validMarkers) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[4]--;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[5]--;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[6]++;
}
      addBlocks(marker);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[15]++;
    }
  }

  /**
   * @param marker The marker to add synthetic blocks for.
   */
  private void addBlocks(Marker marker) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[16]++;
    // Add block around the template section so that it looks like this:
    //  BLOCK (synthetic)
    //    START
    //      BLOCK (synthetic)
    //        BODY
    //    END
    // This prevents the start or end markers from mingling with the code
    // in the block body.


    Node originalParent = marker.endMarker.getParent();
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[17]++;
    Node outerBlock = IR.block();
    outerBlock.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[18]++;
    originalParent.addChildBefore(outerBlock, marker.startMarker);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[19]++;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[20]++;

    Node innerBlock = IR.block();
    innerBlock.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[21]++;
    // Move everything after the start Node up to the end Node into the inner
    // block.
    moveSiblingExclusive(originalParent, innerBlock,
        marker.startMarker,
        marker.endMarker);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[22]++;

    // Add the start node.
    outerBlock.addChildToBack(originalParent.removeChildAfter(outerBlock));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[23]++;
    // Add the inner block
    outerBlock.addChildToBack(innerBlock);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[24]++;
    // and finally the end node.
    outerBlock.addChildToBack(originalParent.removeChildAfter(outerBlock));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[25]++;

    compiler.reportCodeChange();
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[26]++;
  }

  /**
   * Move the Nodes between start and end from the source block to the
   * destination block. If start is null, move the first child of the block.
   * If end is null, move the last child of the block.
   */
  private void moveSiblingExclusive(
      Node src, Node dest, @Nullable Node start, @Nullable Node end) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[27]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[7]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((childAfter(src, start) != end) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[7]--;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[8]--;
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.loops[9]++;
}
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[28]++;
      Node child = removeChildAfter(src, start);
      dest.addChildToBack(child);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[29]++;
    }
  }

  /**
   * Like Node.getNext, that null is used to signal the child before the
   * block.
   */
  private Node childAfter(Node parent, @Nullable Node siblingBefore) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[30]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((siblingBefore == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[1]++;
      return parent.getFirstChild();

    } else {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[2]++;
      return siblingBefore.getNext();
    }
  }

  /**
   * Like removeChildAfter, the firstChild is removed
   */
  private Node removeChildAfter(Node parent, @Nullable Node siblingBefore) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((siblingBefore == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[3]++;
      return parent.removeFirstChild();

    } else {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[4]++;
      return parent.removeChildAfter(siblingBefore);
    }
  }

  private class Callback extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[5]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[6]++;}
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[33]++;

      Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[34]++;
      String callName = callTarget.getString();
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((startMarkerName.equals(callName)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[7]++;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[9]++;
          compiler.report(
              t.makeError(n, INVALID_MARKER_USAGE, startMarkerName));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[37]++;
          return;

        } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[10]++;}
        markerStack.push(parent);
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[38]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[8]++;}
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((endMarkerName.equals(callName)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[12]++;}
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[40]++;

      Node endMarkerNode = parent;
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((endMarkerNode.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[13]++;
        compiler.report(
            t.makeError(n, INVALID_MARKER_USAGE, endMarkerName));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[42]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[14]++;}
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;

      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((markerStack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[15]++;
        compiler.report(t.makeError(n, UNMATCHED_END_MARKER,
            startMarkerName, endMarkerName));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[44]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[16]++;}
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[45]++;

      Node startMarkerNode = markerStack.pop();
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((endMarkerNode.getParent() != startMarkerNode.getParent()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[17]++;
        // The end marker isn't in the same block as the start marker.
        compiler.report(t.makeError(n, UNMATCHED_END_MARKER,
            startMarkerName, endMarkerName));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[47]++;
        return;

      } else {
  CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.branches[18]++;}

      // This is a valid marker set add it to the list of markers to process.
      validMarkers.add(new Marker(startMarkerNode, endMarkerNode));
CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt.statements[48]++;
    }
  }
}

class CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt ());
  }
    public static long[] statements = new long[49];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CreateSyntheticBlocks.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$lu2tjk205tleylwfq27qrr44m108ha8dkpzt4jtt () {
    super("com.google.javascript.jscomp.CreateSyntheticBlocks.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 48; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CreateSyntheticBlocks.java");
      for (int i = 1; i <= 48; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

