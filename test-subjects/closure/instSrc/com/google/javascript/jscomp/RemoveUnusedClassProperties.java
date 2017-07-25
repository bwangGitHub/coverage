/*
 * Copyright 2011 The Closure Compiler Authors.
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
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Set;

/**
 * Look for internal properties set using "this" but never read.  Explicitly
 * ignored is the possibility that these properties
 * may be indirectly referenced using "for-in" or "Object.keys".  This is the
 * same assumption used with RemoveUnusedPrototypeProperties but is by slightly
 * wider in scope.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class RemoveUnusedClassProperties
    implements CompilerPass, NodeTraversal.Callback {
  static {
    CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.ping();
  }

  final AbstractCompiler compiler;
  private boolean inExterns;
  private Set<String> used = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[1]++;
  }
  private List<Node> candidates = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[2]++;
  }

  RemoveUnusedClassProperties(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[3]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverseRoots(compiler, this, externs, root);
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[4]++;
    removeUnused();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[5]++;
  }

  private void removeUnused() {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.loops[1]++;


    for (Node n : candidates) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.loops[1]--;
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.loops[2]--;
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.loops[3]++;
}
      Preconditions.checkState(n.isGetProp());
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[7]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((used.contains(n.getLastChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[1]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[9]++;
        Node parent = n.getParent();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[3]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[11]++;
          Node assign = parent;
          Preconditions.checkState(assign != null
              && NodeUtil.isAssignmentOp(assign)
              && assign.getFirstChild() == n);
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[12]++;
          // 'this.x = y' to 'y'
          assign.getParent().replaceChild(assign,
              assign.getLastChild().detachFromParent());
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[13]++;

        } else {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[4]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[14]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parent.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[5]++;
          parent.getParent().replaceChild(parent, IR.number(0));
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[15]++;

        } else {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[6]++;
          throw new IllegalStateException("unexpected: "+ parent);
        }
}
        compiler.reportCodeChange();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[16]++;

      } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[2]++;}
    }
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[7]++;
      this.inExterns = n.getStaticSourceFile().isExtern();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[18]++;

    } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[8]++;}
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[19]++;
     switch (n.getType()) {
       case Token.GETPROP:
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[9]++; {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[20]++;
         String propName = n.getLastChild().getString();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
         if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isPinningPropertyUse(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[10]++;
           used.add(propName);
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[22]++;

         } else {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[11]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
           // This is a definition of a property but it is only removable
           // if it is defined on "this".
           if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[12]++;
             candidates.add(n);
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[24]++;

           } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[13]++;}
         }
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[25]++;
         break;
       }

       case Token.CALL:
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[14]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[26]++;
         // Look for properties referenced through "JSCompiler_propertyRename".
         Node target = n.getFirstChild();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
         if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((n.hasMoreThanOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((target.isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((target.getString().equals(NodeUtil.JSC_PROPERTY_NAME_FN)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[15]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[28]++;
           Node propName = target.getNext();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
           if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((propName.isString()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[17]++;
             used.add(propName.getString());
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[30]++;

           } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[18]++;}

         } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[16]++;}
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[31]++;
         break; default : CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[19]++;
     }
  }

  /**
   * @return Whether the property is used in a way that prevents its removal.
   */
  private boolean isPinningPropertyUse(Node n) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[32]++;
    // Rather than looking for cases that are uses, we assume all references are
    // pinning uses unless they are:
    //  - a simple assignment (x.a = 1)
    //  - a compound assignment or increment (x++, x += 1) whose result is
    //    otherwise unused

    Node parent = n.getParent();
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[33]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[20]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[22]++;
        // A simple assignment doesn't pin the property.
        return false;

      } else {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[23]++;
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.statements[35]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((parent.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((parent.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) || true)) || (CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) && false)) {
CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[24]++;
        // In general, compound assignments are both reads and writes, but
        // if the property is never otherwise read we can consider it simply
        // a write.
        // However if the assign expression is used as part of a larger
        // expression, we much consider it a read. For example:
        //    x = (y.a += 1);
        return NodeUtil.isExpressionResultUsed(parent);

      } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[25]++;}
}

    } else {
  CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29.branches[21]++;}
    return true;
  }
}

class CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29 ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[26];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RemoveUnusedClassProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,2,1,3,1,1,1,3};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$21xigqfutyac2m5s3q0skkwcchlhvnek4kcko2bh4vulml8x29 () {
    super("com.google.javascript.jscomp.RemoveUnusedClassProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 25; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RemoveUnusedClassProperties.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 25; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

