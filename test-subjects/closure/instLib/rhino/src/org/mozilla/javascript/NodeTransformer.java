/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

import java.util.ArrayList;
import java.util.List;

/**
 * This class transforms a tree to a lower-level representation for codegen.
 *
 * @see Node
 */

public class NodeTransformer
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.ping();
  }


    public NodeTransformer()
    {
    }

    public final void transform(ScriptNode tree)
    {
        transformCompilationUnit(tree);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[1]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i != tree.getFunctionCount()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[3]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[3]++;
            FunctionNode fn = tree.getFunctionNode(i);
            transform(fn);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[4]++;
        }
    }

    private void transformCompilationUnit(ScriptNode tree)
    {
        loops = new ObjArray();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[5]++;
        loopEnds = new ObjArray();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[6]++;

        // to save against upchecks if no finally blocks are used.
        hasFinally = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[7]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[8]++;

        // Flatten all only if we are not using scope objects for block scope
        boolean createScopeObjects = tree.getType() != Token.FUNCTION ||
                                  ((FunctionNode)tree).requiresActivation();
        tree.flattenSymbolTable(!createScopeObjects);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[9]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;

        //uncomment to print tree before transformation
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[1]++; System.out.println(tree.toStringTree(tree));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[11]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[2]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[12]++;
        boolean inStrictMode = tree instanceof AstRoot &&
                               ((AstRoot)tree).isInStrictMode();
        transformCompilationUnit_r(tree, tree, tree, createScopeObjects,
                                   inStrictMode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[13]++;
    }

    private void transformCompilationUnit_r(final ScriptNode tree,
                                            final Node parent,
                                            Scope scope,
                                            boolean createScopeObjects,
                                            boolean inStrictMode)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[14]++;
        Node node = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[4]++;


      siblingLoop:
        for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[6]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[16]++;
            Node previous = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[3]++;
                node = parent.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[18]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[4]++;
                previous = node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[19]++;
                node = node.getNext();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[20]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[5]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[22]++;
                break;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[6]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[23]++;

            int type = node.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (512)) == 0 || true) &&
 ((createScopeObjects) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (256)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C6 |= (128)) == 0 || true) &&
 ((type == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((type == Token.LOOP) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((type == Token.ARRAYCOMP) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 5) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 5) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[7]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[25]++;
                Scope newScope = (Scope) node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((newScope.getSymbolTable() != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[9]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[27]++;
                    // transform to let statement so we get a with statement
                    // created to contain scoped let variables
                    Node let = new Node(type == Token.ARRAYCOMP ? Token.LETEXPR
                                                                : Token.LET);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[28]++;
                    Node innerLet = new Node(Token.LET);
                    let.addChildToBack(innerLet);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[29]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[30]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[7]++;


                    for (String name: newScope.getSymbolTable().keySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[9]++;
}
                        innerLet.addChildToBack(Node.newString(Token.NAME, name));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[31]++;
                    }
                    newScope.setSymbolTable(null);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[32]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[33]++; // so we don't transform again
                    Node oldNode = node;
                    node = replaceCurrent(parent, previous, node, let);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[34]++;
                    type = node.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[35]++;
                    let.addChildToBack(oldNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[36]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[10]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[8]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[37]++;

            switch (type) {

              case Token.LABEL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[11]++;
              case Token.SWITCH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[12]++;
              case Token.LOOP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[13]++;
                loops.push(node);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[38]++;
                loopEnds.push(((Jump)node).target);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[39]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[40]++;
                break;

              case Token.WITH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[14]++;
              {
                loops.push(node);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[41]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[42]++;
                Node leave = node.getNext();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((leave.getType() != Token.LEAVEWITH) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[15]++;
                    Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[44]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[16]++;}
                loopEnds.push(leave);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[45]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[46]++;
                break;
              }

              case Token.TRY:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[17]++;
              {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[47]++;
                Jump jump = (Jump)node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[48]++;
                Node finallytarget = jump.getFinally();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((finallytarget != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[18]++;
                    hasFinally = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[50]++;
                    loops.push(node);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[51]++;
                    loopEnds.push(finallytarget);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[52]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[19]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[53]++;
                break;
              }

              case Token.TARGET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[20]++;
              case Token.LEAVEWITH:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[21]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((loopEnds.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((loopEnds.peek() == node) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[22]++;
                    loopEnds.pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[55]++;
                    loops.pop();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[56]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[23]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[57]++;
                break;

              case Token.YIELD:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[24]++;
                ((FunctionNode)tree).addResumptionPoint(node);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[58]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[59]++;
                break;

              case Token.RETURN:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[25]++;
              {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[60]++;
                boolean isGenerator = tree.getType() == Token.FUNCTION
                    && ((FunctionNode)tree).isGenerator();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[61]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[26]++;
                    node.putIntProp(Node.GENERATOR_END_PROP, 1);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[62]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[27]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[63]++;
int CodeCoverConditionCoverageHelper_C12;
                /* If we didn't support try/finally, it wouldn't be
                 * necessary to put LEAVEWITH nodes here... but as
                 * we do need a series of JSR FINALLY nodes before
                 * each RETURN, we need to ensure that each finally
                 * block gets the correct scope... which could mean
                 * that some LEAVEWITH nodes are necessary.
                 */
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((hasFinally) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[28]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[64]++;
                    break;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[29]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[65]++;     // skip the whole mess.
                Node unwindBlock = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[66]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
                for (int i=loops.size()-1;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[10]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[11]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[12]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[67]++;
                    Node n = (Node) loops.get(i);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[68]++;
                    int elemtype = n.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[69]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((elemtype == Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((elemtype == Token.WITH) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[30]++;
                        Node unwind;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[70]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((elemtype == Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[32]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[71]++;
                            Jump jsrnode = new Jump(Token.JSR);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[72]++;
                            Node jsrtarget = ((Jump)n).getFinally();
                            jsrnode.target = jsrtarget;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[73]++;
                            unwind = jsrnode;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[74]++;

                        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[33]++;
                            unwind = new Node(Token.LEAVEWITH);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[75]++;
                        }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
                        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((unwindBlock == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[34]++;
                            unwindBlock = new Node(Token.BLOCK,
                                                   node.getLineno());
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[77]++;

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[35]++;}
                        unwindBlock.addChildToBack(unwind);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[78]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[31]++;}
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[79]++;
int CodeCoverConditionCoverageHelper_C17;
                if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((unwindBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[36]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[80]++;
                    Node returnNode = node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[81]++;
                    Node returnExpr = returnNode.getFirstChild();
                    node = replaceCurrent(parent, previous, node, unwindBlock);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[82]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[83]++;
int CodeCoverConditionCoverageHelper_C18;
                    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((returnExpr == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isGenerator) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[38]++;
                        unwindBlock.addChildToBack(returnNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[84]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[39]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[85]++;
                        Node store = new Node(Token.EXPR_RESULT, returnExpr);
                        unwindBlock.addChildToFront(store);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[86]++;
                        returnNode = new Node(Token.RETURN_RESULT);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[87]++;
                        unwindBlock.addChildToBack(returnNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[88]++;
                        // transform return expression
                        transformCompilationUnit_r(tree, store, scope,
                                                   createScopeObjects,
                                                   inStrictMode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[89]++;
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[90]++;
                    // skip transformCompilationUnit_r to avoid infinite loop
                    continue siblingLoop;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[37]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[91]++;
                break;
              }

              case Token.BREAK:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[40]++;
              case Token.CONTINUE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[41]++;
              {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[92]++;
                Jump jump = (Jump)node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[93]++;
                Jump jumpStatement = jump.getJumpStatement();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[94]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((jumpStatement == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[42]++; Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[95]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[43]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[96]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[13]++;



                for (int i = loops.size(); ;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[13]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[14]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[15]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[97]++;
int CodeCoverConditionCoverageHelper_C21;
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i == 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[44]++;
                        // Parser/IRFactory ensure that break/continue
                        // always has a jump statement associated with it
                        // which should be found
                        throw Kit.codeBug();

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[45]++;}
                    --i;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[98]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[99]++;
                    Node n = (Node) loops.get(i);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[100]++;
int CodeCoverConditionCoverageHelper_C22;
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n == jumpStatement) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[46]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[101]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[47]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[102]++;

                    int elemtype = n.getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[103]++;
int CodeCoverConditionCoverageHelper_C23;
                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((elemtype == Token.WITH) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[48]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[104]++;
                        Node leave = new Node(Token.LEAVEWITH);
                        previous = addBeforeCurrent(parent, previous, node,
                                                    leave);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[105]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[49]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[106]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((elemtype == Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[50]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[107]++;
                        Jump tryNode = (Jump)n;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[108]++;
                        Jump jsrFinally = new Jump(Token.JSR);
                        jsrFinally.target = tryNode.getFinally();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[109]++;
                        previous = addBeforeCurrent(parent, previous, node,
                                                    jsrFinally);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[110]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[51]++;}
}
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[111]++;
int CodeCoverConditionCoverageHelper_C25;

                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((type == Token.BREAK) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[52]++;
                    jump.target = jumpStatement.target;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[112]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[53]++;
                    jump.target = jumpStatement.getContinue();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[113]++;
                }
                jump.setType(Token.GOTO);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[114]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[115]++;

                break;
              }

              case Token.CALL:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[54]++;
                visitCall(node, tree);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[116]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[117]++;
                break;

              case Token.NEW:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[55]++;
                visitNew(node, tree);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[118]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[119]++;
                break;

              case Token.LETEXPR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[56]++;
              case Token.LET:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[57]++; {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[120]++;
                Node child = node.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[121]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((child.getType() == Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[58]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[122]++;
                  // We have a let statement or expression rather than a
                  // let declaration
                  boolean createWith = tree.getType() != Token.FUNCTION
                      || ((FunctionNode)tree).requiresActivation();
                  node = visitLet(createWith, parent, previous, node);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[123]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[124]++;
                  break;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[59]++;
                  // fall through to process let declaration...
                }
              }
              /* fall through */
              case Token.CONST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[60]++;
              case Token.VAR:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[61]++;
              {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[125]++;
                Node result = new Node(Token.BLOCK);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[126]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[16]++;


int CodeCoverConditionCoverageHelper_C27;
                for (Node cursor = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((cursor != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false);) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[16]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[17]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[18]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[127]++;
                    // Move cursor to next before createAssignment gets chance
                    // to change n.next
                    Node n = cursor;
                    cursor = cursor.getNext();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[128]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[129]++;
int CodeCoverConditionCoverageHelper_C28;
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[62]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[130]++;
int CodeCoverConditionCoverageHelper_C29;
                        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[64]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[131]++;
                            continue;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[65]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[132]++;
                        Node init = n.getFirstChild();
                        n.removeChild(init);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[133]++;
                        n.setType(Token.BINDNAME);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[134]++;
                        n = new Node(type == Token.CONST ?
                                         Token.SETCONST :
                                         Token.SETNAME,
                                     n, init);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[135]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[63]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[136]++;
int CodeCoverConditionCoverageHelper_C30;
                        // May be a destructuring assignment already transformed
                        // to a LETEXPR
                        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.getType() != Token.LETEXPR) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[66]++;
                            throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[67]++;}
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[137]++;
                    Node pop = new Node(Token.EXPR_VOID, n, node.getLineno());
                    result.addChildToBack(pop);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[138]++;
                }
                node = replaceCurrent(parent, previous, node, result);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[139]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[140]++;
                break;
              }

              case Token.TYPEOFNAME:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[68]++; {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[141]++;
                Scope defining = scope.getDefiningScope(node.getString());
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[142]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((defining != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[69]++;
                    node.setScope(defining);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[143]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[70]++;}
              }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[144]++;
              break;

              case Token.TYPEOF:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[71]++;
              case Token.IFNE:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[72]++; {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[145]++;
                  /* We want to suppress warnings for undefined property o.p
                   * for the following constructs: typeof o.p, if (o.p),
                   * if (!o.p), if (o.p == undefined), if (undefined == o.p)
                   */
            	  Node child = node.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[146]++;
int CodeCoverConditionCoverageHelper_C32;
            	  if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((type == Token.IFNE) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[73]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[147]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[19]++;


int CodeCoverConditionCoverageHelper_C33;
                	  while ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((child.getType() == Token.NOT) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[19]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[20]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[21]++;
}
                	      child = child.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[148]++;
                	  }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[149]++;
int CodeCoverConditionCoverageHelper_C34;
                	  if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((child.getType() == Token.EQ) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((child.getType() == Token.NE) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false))
                	  {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[75]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[150]++;
                	      Node first = child.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[151]++;
                	      Node last = child.getLastChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[152]++;
int CodeCoverConditionCoverageHelper_C35;
                	      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((first.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((first.getString().equals("undefined")) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[77]++;
                	          child = last;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[153]++;
}
                	      else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[78]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[154]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((last.getType() == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((last.getString().equals("undefined")) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[79]++;
                              child = first;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[155]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[80]++;}
}

                	  } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[76]++;}

            	  } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[74]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[156]++;
int CodeCoverConditionCoverageHelper_C37;
            	  if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((child.getType() == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[81]++;
            		  child.setType(Token.GETPROPNOWARN);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[157]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[82]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[158]++;
            	  break;
              }

              case Token.SETNAME:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[83]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[159]++;
int CodeCoverConditionCoverageHelper_C38;
                  if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((inStrictMode) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[84]++;
                      node.setType(Token.STRICT_SETNAME);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[160]++;

                  } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[85]++;}
                  /* fall through */
              case Token.NAME:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[86]++;
              case Token.SETCONST:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[87]++;
              case Token.DELPROP:
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[88]++;
              {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[161]++;
int CodeCoverConditionCoverageHelper_C39;
                // Turn name to var for faster access if possible
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((createScopeObjects) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[89]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[162]++;
                    break;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[90]++;}
                Node nameSource;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[163]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((type == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[91]++;
                    nameSource = node;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[164]++;

                } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[92]++;
                    nameSource = node.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[165]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[166]++;
int CodeCoverConditionCoverageHelper_C41;
                    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((nameSource.getType() != Token.BINDNAME) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[93]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[167]++;
int CodeCoverConditionCoverageHelper_C42;
                        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((type == Token.DELPROP) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[95]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[168]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[96]++;}
                        throw Kit.codeBug();

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[94]++;}
                }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[169]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((nameSource.getScope() != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[97]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[170]++;
                    break;
 // already have a scope set
                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[98]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[171]++;
                String name = nameSource.getString();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[172]++;
                Scope defining = scope.getDefiningScope(name);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[173]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((defining != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[99]++;
                    nameSource.setScope(defining);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[174]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[175]++;
int CodeCoverConditionCoverageHelper_C45;
                    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((type == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[101]++;
                        node.setType(Token.GETVAR);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[176]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[102]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[177]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((type == Token.SETNAME) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((type == Token.STRICT_SETNAME) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[103]++;
                        node.setType(Token.SETVAR);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[178]++;
                        nameSource.setType(Token.STRING);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[179]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[104]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[180]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((type == Token.SETCONST) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[105]++;
                        node.setType(Token.SETCONSTVAR);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[181]++;
                        nameSource.setType(Token.STRING);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[182]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[106]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[183]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((type == Token.DELPROP) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[107]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[184]++;
                        // Local variables are by definition permanent
                        Node n = new Node(Token.FALSE);
                        node = replaceCurrent(parent, previous, node, n);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[185]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[108]++;
                        throw Kit.codeBug();
                    }
}
}
}

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[100]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[186]++;
                break;
              } default : CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[109]++;
            }

            transformCompilationUnit_r(tree, node,
                node instanceof Scope ? (Scope)node : scope,
                createScopeObjects, inStrictMode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[187]++;
        }
    }

    protected void visitNew(Node node, ScriptNode tree) {
    }

    protected void visitCall(Node node, ScriptNode tree) {
    }

    protected Node visitLet(boolean createWith, Node parent, Node previous,
                            Node scopeNode)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[188]++;
        Node vars = scopeNode.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[189]++;
        Node body = vars.getNext();
        scopeNode.removeChild(vars);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[190]++;
        scopeNode.removeChild(body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[191]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[192]++;
        boolean isExpression = scopeNode.getType() == Token.LETEXPR;
        Node result;
        Node newVars;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[193]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((createWith) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[110]++;
            result = new Node(isExpression ? Token.WITHEXPR : Token.BLOCK);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[194]++;
            result = replaceCurrent(parent, previous, scopeNode, result);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[195]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[196]++;
            ArrayList<Object> list = new ArrayList<Object>();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[197]++;
            Node objectLiteral = new Node(Token.OBJECTLIT);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[198]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[22]++;


int CodeCoverConditionCoverageHelper_C50;
            for (Node v=vars.getFirstChild();(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); v = v.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[22]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[23]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[24]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[199]++;
                Node current = v;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[200]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((current.getType() == Token.LETEXPR) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[112]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[201]++;
                    // destructuring in let expr, e.g. let ([x, y] = [3, 4]) {}
                    List<?> destructuringNames = (List<?>)
                        current.getProp(Node.DESTRUCTURING_NAMES);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[202]++;
                    Node c = current.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[203]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((c.getType() != Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[114]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[115]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[204]++;
int CodeCoverConditionCoverageHelper_C53;
                    // Add initialization code to front of body
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((isExpression) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[116]++;
                        body = new Node(Token.COMMA, c.getNext(), body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[205]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[117]++;
                        body = new Node(Token.BLOCK,
                            new Node(Token.EXPR_VOID, c.getNext()),
                            body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[206]++;
                    }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[207]++;
int CodeCoverConditionCoverageHelper_C54;
                    // Update "list" and "objectLiteral" for the variables
                    // defined in the destructuring assignment
                    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((destructuringNames != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[118]++;
                        list.addAll(destructuringNames);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[208]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[209]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[25]++;


int CodeCoverConditionCoverageHelper_C55;
                        for (int i=0;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((i < destructuringNames.size()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[25]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[26]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[27]++;
}
                            objectLiteral.addChildToBack(
                                new Node(Token.VOID, Node.newNumber(0.0)));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[210]++;
                        }

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[119]++;}
                    current = c.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[211]++;
 // should be a NAME, checked below
                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[113]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[212]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((current.getType() != Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[120]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[121]++;}
                list.add(ScriptRuntime.getIndexObject(current.getString()));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[213]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[214]++;
                Node init = current.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[215]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((init == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[122]++;
                    init = new Node(Token.VOID, Node.newNumber(0.0));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[216]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[123]++;}
                objectLiteral.addChildToBack(init);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[217]++;
             }
             objectLiteral.putProp(Node.OBJECT_IDS_PROP, list.toArray());
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[218]++;
             newVars = new Node(Token.ENTERWITH, objectLiteral);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[219]++;
             result.addChildToBack(newVars);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[220]++;
             result.addChildToBack(new Node(Token.WITH, body));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[221]++;
             result.addChildToBack(new Node(Token.LEAVEWITH));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[222]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[111]++;
            result = new Node(isExpression ? Token.COMMA : Token.BLOCK);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[223]++;
            result = replaceCurrent(parent, previous, scopeNode, result);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[224]++;
            newVars = new Node(Token.COMMA);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[225]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[226]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[28]++;


int CodeCoverConditionCoverageHelper_C58;
            for (Node v=vars.getFirstChild();(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); v = v.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[28]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[29]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.loops[30]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[227]++;
                Node current = v;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[228]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((current.getType() == Token.LETEXPR) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[124]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[229]++;
                    // destructuring in let expr, e.g. let ([x, y] = [3, 4]) {}
                    Node c = current.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[230]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((c.getType() != Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[126]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[127]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[231]++;
int CodeCoverConditionCoverageHelper_C61;
                    // Add initialization code to front of body
                    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((isExpression) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[128]++;
                        body = new Node(Token.COMMA, c.getNext(), body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[232]++;

                    } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[129]++;
                        body = new Node(Token.BLOCK,
                            new Node(Token.EXPR_VOID, c.getNext()),
                            body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[233]++;
                    }
                    // We're removing the LETEXPR, so move the symbols
                    Scope.joinScopes((Scope)current,
                                          (Scope)scopeNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[234]++;
                    current = c.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[235]++;
 // should be a NAME, checked below
                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[125]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[236]++;
int CodeCoverConditionCoverageHelper_C62;
                if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((current.getType() != Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[130]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[131]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[237]++;
                Node stringNode = Node.newString(current.getString());
                stringNode.setScope((Scope)scopeNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[238]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[239]++;
                Node init = current.getFirstChild();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[240]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((init == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[132]++;
                    init = new Node(Token.VOID, Node.newNumber(0.0));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[241]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[133]++;}
                newVars.addChildToBack(new Node(Token.SETVAR, stringNode, init));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[242]++;
            }
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[243]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((isExpression) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[134]++;
                result.addChildToBack(newVars);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[244]++;
                scopeNode.setType(Token.COMMA);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[245]++;
                result.addChildToBack(scopeNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[246]++;
                scopeNode.addChildToBack(body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[247]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[248]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((body instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[136]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[249]++;
                    Scope scopeParent = ((Scope) body).getParentScope();
                    ((Scope) body).setParentScope((Scope)scopeNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[250]++;
                    ((Scope) scopeNode).setParentScope(scopeParent);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[251]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[137]++;}

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[135]++;
                result.addChildToBack(new Node(Token.EXPR_VOID, newVars));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[252]++;
                scopeNode.setType(Token.BLOCK);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[253]++;
                result.addChildToBack(scopeNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[254]++;
                scopeNode.addChildrenToBack(body);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[255]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[256]++;
int CodeCoverConditionCoverageHelper_C66;
                if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((body instanceof Scope) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[138]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[257]++;
                    Scope scopeParent = ((Scope) body).getParentScope();
                    ((Scope) body).setParentScope((Scope)scopeNode);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[258]++;
                    ((Scope) scopeNode).setParentScope(scopeParent);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[259]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[139]++;}
            }
        }
        return result;
    }

    private static Node addBeforeCurrent(Node parent, Node previous,
                                         Node current, Node toAdd)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[260]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((previous == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[140]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[261]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((current == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[142]++; Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[262]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[143]++;}
            parent.addChildToFront(toAdd);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[263]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[141]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[264]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((current == previous.getNext()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[144]++; Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[265]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[145]++;}
            parent.addChildAfter(toAdd, previous);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[266]++;
        }
        return toAdd;
    }

    private static Node replaceCurrent(Node parent, Node previous,
                                       Node current, Node replacement)
    {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[267]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((previous == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[146]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[268]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((current == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[148]++; Kit.codeBug();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[269]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[149]++;}
            parent.replaceChild(current, replacement);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[270]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[147]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[271]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((previous.next == current) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[150]++;
            // Check cachedPrev.next == current is necessary due to possible
            // tree mutations
            parent.replaceChildAfter(previous, replacement);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[272]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.branches[151]++;
            parent.replaceChild(current, replacement);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1.statements[273]++;
        }
}
        return replacement;
    }

    private ObjArray loops;
    private ObjArray loopEnds;
    private boolean hasFinally;
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1 ());
  }
    public static long[] statements = new long[274];
    public static long[] branches = new long[152];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NodeTransformer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,0,1,1,3,1,1,1,2,1,1,1,2,1,1,1,2,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 72; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpufkbqshsh1qy2w6ccnwnhw8g39oogo1 () {
    super("org.mozilla.javascript.RHINO-SRC-NodeTransformer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 273; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 151; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NodeTransformer.java");
      for (int i = 1; i <= 273; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 151; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

