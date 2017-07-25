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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Collection;
import java.util.List;

/**
 * A compiler pass for optimize function return results.  Currently this
 * pass looks for results that are complete unused and rewrite then to be:
 *   "return x()" -->"x(); return"
 * , but it can easily be
 * expanded to look for use context to avoid unneeded type coercion:
 *   - "return x.toString()" --> "return x"
 *   - "return !!x" --> "return x"
 * @author johnlenz@google.com (John Lenz)
 */
class OptimizeReturns
    implements OptimizeCalls.CallGraphCompilerPass, CompilerPass {
  static {
    CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.ping();
  }


  private AbstractCompiler compiler;

  OptimizeReturns(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[1]++;
  }

  @Override
  @VisibleForTesting
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[2]++;
    SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
    defFinder.process(externs, root);
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[3]++;
    process(externs, root, defFinder);
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[4]++;
  }

  @Override
  public void process(
      Node externs, Node root, SimpleDefinitionFinder definitions) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[5]++;
    // Find all function nodes whose callers ignore the return values.
    List<Node> toOptimize = Lists.newArrayList();
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[1]++;


    for (DefinitionSite defSite : definitions.getDefinitionSites()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[1]--;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[2]--;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[3]++;
}
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((defSite.inExterns) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((callResultsMaybeUsed(definitions, defSite)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[1]++;
        toOptimize.add(defSite.definition.getRValue());
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[8]++;

      } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[2]++;}
    }
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[9]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[4]++;


    // Optimize the return statements.
    for (Node node : toOptimize) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[4]--;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[5]--;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[6]++;
}
      rewriteReturns(definitions, node);
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[10]++;
    }
  }

  /**
   * Determines if a function result might be used.  A result might be use if:
   * - Function must is exported.
   * - The definition is never accessed outside a function call context.
   */
  private boolean callResultsMaybeUsed(
      SimpleDefinitionFinder defFinder, DefinitionSite definitionSite) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[11]++;

    Definition definition = definitionSite.definition;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[12]++;

    // Assume non-function definitions results are used.
    Node rValue = definition.getRValue();
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((rValue == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[3]++;
      return true;

    } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[4]++;}
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

    // Be conservative, don't try to optimize any declaration that isn't as
    // simple function declaration or assignment.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((SimpleDefinitionFinder.isSimpleFunctionDeclaration(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[5]++;
      return true;

    } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[6]++;}
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((defFinder.canModifyDefinition(definition)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[7]++;
      return true;

    } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[8]++;}
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[16]++;

    Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[17]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[7]++;


    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[7]--;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[8]--;
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.loops[9]++;
}
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[18]++;
      // Assume indirect definitions references use the result
      Node useNodeParent = site.node.getParent();
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isCall(site)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[9]++;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[20]++;
        Node callNode = useNodeParent;
        Preconditions.checkState(callNode.isCall());
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[21]++;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isExpressionResultUsed(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[11]++;
          return true;

        } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[12]++;}

      } else {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[10]++;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
        // Allow a standalone name reference.
        //     var a;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((useNodeParent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[13]++;
          return true;

        } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[14]++;}
      }

      // TODO(johnlenz): Add specialization support.
    }

    // No possible use of the definition result
    return false;
  }

  /**
   * For the supplied function node, rewrite all the return expressions so that:
   *    return foo();
   * becomes:
   *    foo(); return;
   * Useless return will be removed later by the peephole optimization passes.
   */
  private void rewriteReturns(
      final SimpleDefinitionFinder defFinder, Node fnNode) {
    Preconditions.checkState(fnNode.isFunction());
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[24]++;
    NodeUtil.visitPostOrder(
      fnNode.getLastChild(),
      new NodeUtil.Visitor() {
        @Override
        public void visit(Node node) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[26]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((node.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[15]++;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[27]++;
            boolean keepValue = NodeUtil.mayHaveSideEffects(
                node.getFirstChild(), compiler);
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((keepValue) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[17]++;
              defFinder.removeReferences(node.getFirstChild());
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[29]++;

            } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[18]++;}
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[30]++;
            Node result = node.removeFirstChild();
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((keepValue) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[19]++;
              node.getParent().addChildBefore(
                IR.exprResult(result).srcref(result), node);
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[32]++;

            } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[20]++;}
            compiler.reportCodeChange();
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[33]++;

          } else {
  CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.branches[16]++;}
        }
      },
      new NodeUtil.MatchShallowStatement());
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[25]++;
  }

  /**
   * Determines if the name node acts as the function name in a call expression.
   */
  private static boolean isCall(UseSite site) {
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[34]++;
    Node node = site.node;
CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd.statements[35]++;
    Node parent = node.getParent();
    return (parent.getFirstChild() == node) && parent.isCall();
  }
}

class CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.OptimizeReturns.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,2,1,1};
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

  public CodeCoverCoverageCounter$9a23dzbryg7yszfqou1q8fyuhjo9bhd () {
    super("com.google.javascript.jscomp.OptimizeReturns.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.OptimizeReturns.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
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

