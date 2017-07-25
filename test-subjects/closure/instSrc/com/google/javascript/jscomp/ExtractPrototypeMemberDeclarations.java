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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.LinkedList;
import java.util.List;

/**
 * When there are multiple prototype member declarations to the same class,
 * use a temp variable to alias the prototype object.
 *
 * Example:
 *
 * <pre>
 * function B() { ... }                 \
 * B.prototype.foo = function() { ... }  \___ {@link ExtractionInstance}
 * ...                                   /
 * B.prototype.bar = function() { ... } /
 *          ^---------------------------------{@link PrototypeMemberDeclaration}
 * </pre>
 * <p>becomes
 * <pre>
 * function B() { ... }
 * x = B.prototype;
 * x.foo = function() { ... }
 * ...
 * x.bar = function() { ... }
 * </pre>
 *
 * <p> Works almost like a redundant load elimination but limited to only
 * recognizing the class prototype declaration idiom. First it only works within
 * a basic block because we avoided {@link DataFlowAnalysis} for compilation
 * performance. Secondly, we can avoid having to compute how long to
 * sub-expressing has to be. Example:
 * <pre>
 * a.b.c.d = ...
 * a.b.c = ...
 * a.b = ...
 * a.b.c = ...
 * </pre>
 * <p> Further more, we only introduce one temp variable to hold a single
 * prototype at a time. So all the {@link PrototypeMemberDeclaration}
 * to be extracted must be in a single line. We call this a single
 * {@link ExtractionInstance}.
 *
 * <p>Alternatively, for users who do not want a global variable to be
 * introduced, we will create an anonymous function instead.
 * <pre>
 * function B() { ... }
 * (function (x) {
 *   x.foo = function() { ... }
 *   ...
 *   x.bar = function() { ... }
 * )(B.prototype)
 * </pre>
 *
 * The RHS of the declarations can have side effects, however, one good way to
 * break this is the following:
 * <pre>
 * function B() { ... }
 * B.prototype.foo = (function() { B.prototype = somethingElse(); return 0 })();
 * ...
 * </pre>
 * Such logic is highly unlikely and we will assume that it never occurs.
 *
 */
class ExtractPrototypeMemberDeclarations implements CompilerPass {
  static {
    CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.ping();
  }


  // The name of variable that will temporary hold the pointer to the prototype
  // object. Of cause, we assume that it'll be renamed by RenameVars.
  private String prototypeAlias = "JSCompiler_prototypeAlias";
  {
    CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[1]++;
  }

  private final AbstractCompiler compiler;

  private final Pattern pattern;

  enum Pattern {
    USE_GLOBAL_TEMP(
        // Global Overhead.
        // We need a temp variable to hold all the prototype.
        "var t;".length(),
        // Per Extract overhead:
        // Every extraction instance must first use the temp variable to point
        // to the prototype object.
        "t=y.prototype;".length(),
        // TODO(user): Check to to see if AliasExterns is on
        // The gain we get per prototype declaration. Assuming it can be
        // aliased.
        "t.y=".length() - "x[p].y=".length()),

    USE_ANON_FUNCTION(
       // Global Overhead:
       0,
       // Per-extraction overhead:
       // This is the cost of a single anoynmous function.
       "(function(t){})(y.prototype);".length(),
       // Per-prototype member declaration overhead:
       // Here we assumes that they don't have AliasExterns on (in SIMPLE mode).
       "t.y=".length() - "x.prototype.y=".length());


    private final int globalOverhead;
    private final int perExtractionOverhead;
    private final int perMemberOverhead;

    Pattern(int globalOverHead, int perExtractionOverhead, int perMemberOverhead) {
      this.globalOverhead = globalOverHead;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[2]++;
      this.perExtractionOverhead = perExtractionOverhead;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[3]++;
      this.perMemberOverhead = perMemberOverhead;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[4]++;
    }
  }

  ExtractPrototypeMemberDeclarations(AbstractCompiler compiler, Pattern pattern) {
    this.compiler = compiler;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[5]++;
    this.pattern = pattern;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[7]++;
    GatherExtractionInfo extractionInfo = new GatherExtractionInfo();
    NodeTraversal.traverse(compiler, root, extractionInfo);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[8]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((extractionInfo.shouldExtract()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[1]++;
      doExtraction(extractionInfo);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[10]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[11]++;

    } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[2]++;}
  }

  /**
   * Declares the temp variable to point to prototype objects and iterates
   * through all ExtractInstance and performs extraction there.
   */
  private void doExtraction(GatherExtractionInfo info) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;

    // Insert a global temp if we are using the USE_GLOBAL_TEMP pattern.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((pattern == Pattern.USE_GLOBAL_TEMP) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[3]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[13]++;
      Node injectionPoint = compiler.getNodeForCodeInsertion(null);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[14]++;

      Node var = NodeUtil.newVarNode(prototypeAlias, null)
          .copyInformationFromForTree(injectionPoint);

      injectionPoint.addChildrenToFront(var);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[15]++;

    } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[4]++;}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[1]++;


    // Go through all extraction instances and extract each of them.
    for (ExtractionInstance instance : info.instances) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[1]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[2]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[3]++;
}
      extractInstance(instance);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[17]++;
    }
  }

  /**
   * At a given ExtractionInstance, stores and prototype object in the temp
   * variable and rewrite each member declaration to assign to the temp variable
   * instead.
   */
  private void extractInstance(ExtractionInstance instance) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[18]++;
    PrototypeMemberDeclaration first = instance.declarations.getFirst();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[19]++;
    String className = first.qualifiedClassName;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pattern == Pattern.USE_GLOBAL_TEMP) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[5]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[21]++;
      // Use the temp variable to hold the prototype.
      Node stmt = new Node(first.node.getType(),
         IR.assign(
              IR.name(prototypeAlias),
              NodeUtil.newQualifiedNameNode(
                  compiler.getCodingConvention(), className + ".prototype",
                  instance.parent, className + ".prototype")))
          .copyInformationFromForTree(first.node);

      instance.parent.addChildBefore(stmt, first.node);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[22]++;

    } else {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[6]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[23]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((pattern == Pattern.USE_ANON_FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)){
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[7]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[24]++;
      Node block = IR.block();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[25]++;
      Node func = IR.function(
           IR.name(""),
           IR.paramList(IR.name(prototypeAlias)),
           block);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[26]++;

      Node call = IR.call(func,
           NodeUtil.newQualifiedNameNode(
               compiler.getCodingConvention(), className + ".prototype",
               instance.parent, className + ".prototype"));
      call.putIntProp(Node.FREE_CALL, 1);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[27]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[28]++;

      Node stmt = new Node(first.node.getType(), call);
      stmt.copyInformationFromForTree(first.node);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[29]++;
      instance.parent.addChildBefore(stmt, first.node);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[30]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[31]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[4]++;


      for (PrototypeMemberDeclaration declar : instance.declarations) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[4]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[5]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[6]++;
}
        block.addChildToBack(declar.node.detachFromParent());
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[32]++;
      }

    } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[8]++;}
}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[33]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[7]++;


    // Go thought each member declaration and replace it with an assignment
    // to the prototype variable.
    for (PrototypeMemberDeclaration declar : instance.declarations) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[7]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[8]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[9]++;
}
      replacePrototypeMemberDeclaration(declar);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[34]++;
    }
  }

  /**
   * Replaces a member declaration to an assignment to the temp prototype
   * object.
   */
  private void replacePrototypeMemberDeclaration(
      PrototypeMemberDeclaration declar) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[35]++;
    // x.prototype.y = ...  ->  t.y = ...
    Node assignment = declar.node.getFirstChild();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[36]++;
    Node lhs = assignment.getFirstChild();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[37]++;
    Node name = NodeUtil.newQualifiedNameNode(
        compiler.getCodingConvention(),
        prototypeAlias + "." + declar.memberName, declar.node,
        declar.memberName);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[38]++;

    // Save the full prototype path on the left hand side of the assignment
    // for debugging purposes.
    // declar.lhs = x.prototype.y so first child of the first child
    // is 'x'.
    Node accessNode = declar.lhs.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[39]++;
    Object originalName = accessNode.getProp(Node.ORIGINALNAME_PROP);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[40]++;

    String className = "?";
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;

    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((originalName != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[9]++;
      className = originalName.toString();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[42]++;

    } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[10]++;}

    NodeUtil.setDebugInformation(name.getFirstChild(), lhs,
                                 className + ".prototype");
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[43]++;

    assignment.replaceChild(lhs, name);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[44]++;
  }

  /**
   * Collects all the possible extraction instances in a node traversal.
   */
  private class GatherExtractionInfo extends AbstractShallowCallback {

    private List<ExtractionInstance> instances = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[45]++;
  }
    private int totalDelta = pattern.globalOverhead;
  {
    CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[46]++;
  }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[47]++;
int CodeCoverConditionCoverageHelper_C6;

      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[12]++;}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[48]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;

      for (Node cur = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); cur = cur.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[10]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[11]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[12]++;
}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[49]++;
        PrototypeMemberDeclaration prototypeMember =
            PrototypeMemberDeclaration.extractDeclaration(cur);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((prototypeMember == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[13]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[51]++;
          continue;

        } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[14]++;}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[52]++;

        // Found a good site here. The constructor will computes the chain of
        // declarations that is qualified for extraction.
        ExtractionInstance instance =
            new ExtractionInstance(prototypeMember, n);
        cur = instance.declarations.getLast().node;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[53]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;

        // Only add it to our work list if the extraction at this instance
        // makes the code smaller.
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((instance.isFavorable()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[15]++;
          instances.add(instance);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[55]++;
          totalDelta += instance.delta;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[56]++;

        } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[16]++;}
      }
    }

    /**
     * @return <@code true> if the sum of all the extraction instance gain
     * outweighs the overhead of the temp variable declaration.
     */
    private boolean shouldExtract() {
      return totalDelta < 0;
    }
  }

  private class ExtractionInstance {
    LinkedList<PrototypeMemberDeclaration> declarations = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[57]++;
  }
    private int delta = 0;
  {
    CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[58]++;
  }
    private final Node parent;

    private ExtractionInstance(PrototypeMemberDeclaration head, Node parent) {
      this.parent = parent;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[59]++;
      declarations.add(head);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[60]++;
      delta = pattern.perExtractionOverhead + pattern.perMemberOverhead;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[61]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[62]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[13]++;


int CodeCoverConditionCoverageHelper_C10;

      for (Node cur = head.node.getNext();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); cur = cur.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[13]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[14]--;
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.loops[15]++;
}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;

        // We can skip over any named functions because they have no effect on
        // the control flow. In fact, they are lifted to the beginning of the
        // block. This happens a lot when devirtualization breaks the whole
        // chain.
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((cur.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[17]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[64]++;
          continue;

        } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[18]++;}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[65]++;

        PrototypeMemberDeclaration prototypeMember =
            PrototypeMemberDeclaration.extractDeclaration(cur);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((prototypeMember == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((head.isSameClass(prototypeMember)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[19]++;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[67]++;
          break;

        } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[20]++;}
        declarations.add(prototypeMember);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[68]++;
        delta += pattern.perMemberOverhead;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[69]++;
      }
    }

    /**
     * @return {@code true} if extracting all the declarations at this instance
     * will overweight the overhead of aliasing the prototype object.
     */
    boolean isFavorable() {
      return delta <= 0;
    }
  }

  /**
   * Abstraction for a prototype member declaration.
   *
   * <p>{@code a.b.c.prototype.d = ....}
   */
  private static class PrototypeMemberDeclaration {
    final String memberName;
    final Node node;
    final String qualifiedClassName;
    final Node lhs;

    private PrototypeMemberDeclaration(Node lhs, Node node) {
      this.lhs = lhs;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[70]++;
      this.memberName = NodeUtil.getPrototypePropertyName(lhs);
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[71]++;
      this.node = node;
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[72]++;
      this.qualifiedClassName =
          NodeUtil.getPrototypeClassName(lhs).getQualifiedName();
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[73]++;
    }

    private boolean isSameClass(PrototypeMemberDeclaration other) {
      return qualifiedClassName.equals(other.qualifiedClassName);
    }

    /**
     * @return A prototype member declaration representation if there is one
     * else it returns {@code null}.
     */
    private static PrototypeMemberDeclaration extractDeclaration(Node n) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isPrototypePropertyDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[21]++;
        return null;

      } else {
  CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.branches[22]++;}
CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d.statements[75]++;
      Node lhs = n.getFirstChild().getFirstChild();
      return new PrototypeMemberDeclaration(lhs, n);
    }
  }
}

class CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d ());
  }
    public static long[] statements = new long[76];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ExtractPrototypeMemberDeclarations.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,2,1};
    for (int i = 1; i <= 13; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$y4hwl4d1ky66txes98xzdeqkztj6i1xxqcvsmrs01r1uz7n8v7uvjbgray5d () {
    super("com.google.javascript.jscomp.ExtractPrototypeMemberDeclarations.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 75; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ExtractPrototypeMemberDeclarations.java");
      for (int i = 1; i <= 75; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

