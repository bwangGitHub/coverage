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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeUtil.Visitor;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A nifty set of functions to deal with the issues of replacing function
 * parameters with a set of call argument expressions.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class FunctionArgumentInjector {
  static {
    CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.ping();
  }


  // A string to use to represent "this".  Anything that is not a valid
  // identifier can be used, so we use "this".
  static final String THIS_MARKER = "this";
  static {
    CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[1]++;
  }

  private FunctionArgumentInjector() {
    // A private constructor to prevent instantiation.
  }

  /**
   * With the map provided, replace the names with expression trees.
   * @param node The root of the node tree within which to perform the
   *     substitutions.
   * @param parent The parent root node.
   * @param replacements The map of names to template node trees with which
   *     to replace the name Nodes.
   * @returns The root node or its replacement.
   */
  static Node inject(AbstractCompiler compiler, Node node, Node parent,
      Map<String, Node> replacements) {
    return inject(compiler, node, parent, replacements, true);
  }

  static Node inject(AbstractCompiler compiler, Node node, Node parent,
      Map<String, Node> replacements, boolean replaceThis) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[1]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[3]++;
      Node replacementTemplate = replacements.get(node.getString());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((replacementTemplate != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[3]++;
        // This should not be replacing declared names.
        Preconditions.checkState(!parent.isFunction()
            || !parent.isVar()
            || !parent.isCatch());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[5]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[6]++;
        // The name may need to be replaced more than once,
        // so we need to clone the node.
        Node replacement = replacementTemplate.cloneTree();
        parent.replaceChild(node, replacement);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[7]++;
        return replacement;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[4]++;}

    } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[2]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((replaceThis) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((node.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[5]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[9]++;
      Node replacementTemplate = replacements.get(THIS_MARKER);
      Preconditions.checkNotNull(replacementTemplate);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[10]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((replacementTemplate.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[7]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[12]++;
        // The name may need to be replaced more than once,
        // so we need to clone the node.
        Node replacement = replacementTemplate.cloneTree();
        parent.replaceChild(node, replacement);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[13]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;

        // Remove the value.  This isn't required but it ensures that we won't
        // inject side-effects multiple times as it will trigger the null
        // check above if we do.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(replacementTemplate, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[9]++;
          replacements.remove(THIS_MARKER);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[15]++;

        } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[10]++;}

        return replacement;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[8]++;}

    } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[6]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[16]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[11]++;
      // Once we enter another scope the "this" value changes, don't try
      // to replace it within an inner scope.
      replaceThis = false;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[17]++;

    } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[12]++;}
}
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;

    for (Node c = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[1]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[2]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[3]++;
}
      // We have to reassign c in case it was replaced, because the removed c's
      // getNext() would no longer be correct.
      c = inject(compiler, c, node, replacements, replaceThis);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[19]++;
    }

    return node;
  }

  /**
   * Get a mapping for function parameter names to call arguments.
   */
  static LinkedHashMap<String, Node> getFunctionCallParameterMap(
      Node fnNode, Node callNode, Supplier<String> safeNameIdSupplier) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[20]++;
    // Create an argName -> expression map
    // NOTE: A linked map is created here to provide ordering.
    LinkedHashMap<String, Node> argMap = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[21]++;

    // CALL NODE: [ NAME, ARG1, ARG2, ... ]
    Node cArg = callNode.getFirstChild().getNext();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((cArg != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[13]++;
      argMap.put(THIS_MARKER, cArg);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[23]++;
      cArg = cArg.getNext();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[24]++;

    } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[14]++;
      // 'apply' isn't supported yet.
      Preconditions.checkState(!NodeUtil.isFunctionObjectApply(callNode));
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[25]++;
      argMap.put(THIS_MARKER, NodeUtil.newUndefinedNode(callNode));
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[26]++;
    }
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[4]++;



    for (Node fnArg : NodeUtil.getFunctionParameters(fnNode).children()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[4]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[5]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[6]++;
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((cArg != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[15]++;
        argMap.put(fnArg.getString(), cArg);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[29]++;
        cArg = cArg.getNext();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[30]++;

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[16]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[31]++;
        Node srcLocation = callNode;
        argMap.put(fnArg.getString(), NodeUtil.newUndefinedNode(srcLocation));
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[32]++;
      }
    }
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[33]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;

    // Add temp names for arguments that don't have named parameters in the
    // called function.
    while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cArg != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[7]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[8]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[9]++;
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[34]++;
      String uniquePlaceholder =
        getUniqueAnonymousParameterName(safeNameIdSupplier);
      argMap.put(uniquePlaceholder, cArg);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[35]++;
      cArg = cArg.getNext();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[36]++;
    }

    return argMap;
  }

  /**
   * Parameter names will be name unique when at a later time.
   */
  private static String getUniqueAnonymousParameterName(
      Supplier<String> safeNameIdSupplier) {
    return "JSCompiler_inline_anon_param_" + safeNameIdSupplier.get();
  }

  /**
   * Retrieve a set of names that can not be safely substituted in place.
   * Example:
   *   function(a) {
   *     a = 0;
   *   }
   * Inlining this without taking precautions would cause the call site value
   * to be modified (bad).
   */
  static Set<String> findModifiedParameters(Node fnNode) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[37]++;
    Set<String> names = getFunctionParameterSet(fnNode);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[38]++;
    Set<String> unsafeNames = Sets.newHashSet();
    return findModifiedParameters(
        fnNode.getLastChild(), null, names, unsafeNames, false);
  }

  /**
   * Check for uses of the named value that imply a pass-by-value
   * parameter is expected.  This is used to prevent cases like:
   *
   *   function (x) {
   *     x=2;
   *     return x;
   *   }
   *
   * We don't want "undefined" to be substituted for "x", and get
   *   undefined=2
   *
   * @param n The node in question.
   * @param parent The parent of the node.
   * @param names The set of names to check.
   * @param unsafe The set of names that require aliases.
   * @param inInnerFunction Whether the inspection is occurring on a inner
   *     function.
   */
  private static Set<String> findModifiedParameters(
      Node n, Node parent, Set<String> names, Set<String> unsafe,
      boolean inInnerFunction) {
    Preconditions.checkArgument(unsafe != null);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[39]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[40]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[17]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((names.contains(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[19]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((inInnerFunction) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((canNameValueChange(n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[21]++;
          unsafe.add(n.getString());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[43]++;

        } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[22]++;}

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[20]++;}

    } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[18]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[44]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[23]++;
      // A function parameter can not be replaced with a direct inlined value
      // if it is referred to by an inner function. The inner function
      // can out live the call we are replacing, so inner function must
      // capture a unique name.  This approach does not work within loop
      // bodies so those are forbidden elsewhere.
      inInnerFunction = true;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[45]++;

    } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[24]++;}
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[46]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[10]++;



    for (Node c : n.children()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[10]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[11]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[12]++;
}
      findModifiedParameters(c, n, names, unsafe, inInnerFunction);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[47]++;
    }

    return unsafe;
  }

  /**
   * This is similar to NodeUtil.isLValue except that object properties and
   * array member modification aren't important ("o" in "o.a = 2" is still "o"
   * after assignment, where in as "o = x", "o" is now "x").
   *
   * This also looks for the redefinition of a name.
   *   function (x){var x;}
   *
   * @param n The NAME node in question.
   * @param parent The parent of the node.
   */
  private static boolean canNameValueChange(Node n, Node parent) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[48]++;
    int type = parent.getType();
    return (type == Token.VAR || type == Token.INC || type == Token.DEC ||
        (NodeUtil.isAssignmentOp(parent) && parent.getFirstChild() == n));
  }

  /**
   * Updates the set of parameter names in set unsafe to include any
   * arguments from the call site that require aliases.
   * @param fnNode The FUNCTION node to be inlined.
   * @param argMap The argument list for the call to fnNode.
   * @param namesNeedingTemps The set of names to update.
   */
  static void maybeAddTempsForCallArguments(
      Node fnNode, Map<String, Node> argMap, Set<String> namesNeedingTemps,
      CodingConvention convention) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[49]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((argMap.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[25]++;
      // No arguments to check, we are done.
      return;

    } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[26]++;}

    Preconditions.checkArgument(fnNode.isFunction());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[50]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[51]++;
    Node block = fnNode.getLastChild();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[52]++;

    Set<String> parameters = argMap.keySet();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[53]++;

    // Get the list of parameters that may need temporaries due to
    // side-effects.
    Set<String> namesAfterSideEffects = findParametersReferencedAfterSideEffect(
        parameters, block);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[54]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[13]++;



    // Check for arguments that are evaluated more than once.
    for (Map.Entry<String, Node> entry : argMap.entrySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[13]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[14]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[15]++;
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[55]++;
      String argName = entry.getKey();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((namesNeedingTemps.contains(argName)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[27]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[57]++;
        continue;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[28]++;}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[58]++;
      Node cArg = entry.getValue();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[59]++;
      boolean safe = true;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[60]++;
      int references = NodeUtil.getNameReferenceCount(block, argName);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[61]++;
int CodeCoverConditionCoverageHelper_C17;

      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((NodeUtil.mayEffectMutableState(cArg)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((references > 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[29]++;
        // Note: Mutable arguments should be assigned to temps, as the
        // may be within in a loop:
        //   function x(a) {
        //     for(var i=0; i<0; i++) {
        //       foo(a);
        //     }
        //   x( [] );
        //
        //   The parameter in the call to foo should not become "[]".
        safe = false;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[62]++;

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[30]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[63]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(cArg)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[31]++;
        // Even if there are no references, we still need to evaluate the
        // expression if it has side-effects.
        safe = false;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[64]++;

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[32]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[65]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((NodeUtil.canBeSideEffected(cArg)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((namesAfterSideEffects.contains(argName)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[33]++;
        safe = false;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[66]++;

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[34]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[67]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((references > 1) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[35]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[68]++;
        // Safe is a misnomer, this is a check for "large".
        switch (cArg.getType()) {
          case Token.NAME:
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[37]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[69]++;
            String name = cArg.getString();
            safe = !(convention.isExported(name));
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[70]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[71]++;
            break;
          case Token.THIS:
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[38]++;
            safe = true;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[72]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[73]++;
            break;
          case Token.STRING:
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[39]++;
            safe = (cArg.getString().length() < 2);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[74]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[75]++;
            break;
          default:
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[40]++;
            safe = NodeUtil.isImmutableValue(cArg);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[76]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[77]++;
            break;
        }

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[36]++;}
}
}
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[78]++;
int CodeCoverConditionCoverageHelper_C21;

      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((safe) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[41]++;
        namesNeedingTemps.add(argName);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[79]++;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[42]++;}
    }
  }

  /**
   * Boot strap a traversal to look for parameters referenced
   * after a non-local side-effect.
   * NOTE: This assumes no-inner functions.
   * @param parameters The set of parameter names.
   * @param root The function code block.
   * @return The subset of parameters referenced after the first
   *     seen non-local side-effect.
   */
  private static Set<String> findParametersReferencedAfterSideEffect(
      Set<String> parameters, Node root) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[80]++;

    // TODO(johnlenz): Consider using scope for this.
    Set<String> locals = Sets.newHashSet(parameters);
    gatherLocalNames(root, locals);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[81]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[82]++;

    ReferencedAfterSideEffect collector = new ReferencedAfterSideEffect(
        parameters, locals);
    NodeUtil.visitPostOrder(
        root,
        collector,
        collector);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[83]++;
    return collector.getResults();
  }

  /**
   * Collect parameter names referenced after a non-local side-effect.
   *
   * Assumptions:
   * - We assume parameters are not modified in the function body
   * (that is checked separately).
   * - There are no inner functions (also checked separately).
   *
   * As we are trying to replace parameters with there passed in values
   * we are interested in anything that may affect those value.  So, ignoring
   * changes to local variables, we look for things that may affect anything
   * outside the local-state.  Once such a side-effect is seen any following
   * reference to the function parameters are collected.  These will need
   * to be assigned to temporaries to prevent changes to their value as would
   * have happened during the function call.
   *
   * To properly handle loop structures all references to the function
   * parameters are recorded and the decision to keep or throw away those
   * references is deferred until exiting the loop structure.
   */
  private static class ReferencedAfterSideEffect
      implements Visitor, Predicate<Node> {
    private final Set<String> parameters;
    private final Set<String> locals;
    private boolean sideEffectSeen = false;
  {
    CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[84]++;
  }
    private Set<String> parametersReferenced = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[85]++;
  }
    private int loopsEntered = 0;
  {
    CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[86]++;
  }

    ReferencedAfterSideEffect(Set<String> parameters, Set<String> locals) {
      this.parameters = parameters;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[87]++;
      this.locals = locals;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[88]++;
    }

    Set<String> getResults() {
      return parametersReferenced;
    }

    @Override
    public boolean apply(Node node) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[89]++;
int CodeCoverConditionCoverageHelper_C22;
      // Keep track of any loop structures entered.
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((NodeUtil.isLoopStructure(node)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[43]++;
        loopsEntered++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[90]++;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[44]++;}

      // If we have found all the parameters, don't bother looking
      // at the children.
      return !(sideEffectSeen
          && parameters.size() == parametersReferenced.size());
    }

    boolean inLoop() {
      return loopsEntered != 0;
    }

    @Override
    public void visit(Node n) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[91]++;
int CodeCoverConditionCoverageHelper_C23;
      // If we are exiting a loop.
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((NodeUtil.isLoopStructure(n)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[45]++;
        loopsEntered--;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[92]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[93]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((inLoop()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((sideEffectSeen) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[47]++;
          // Now that the loops has been fully traversed and
          // no side-effects have been seen, throw away
          // the references seen in them.
          parametersReferenced.clear();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[94]++;

        } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[48]++;}

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[46]++;}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[95]++;
int CodeCoverConditionCoverageHelper_C25;

      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((sideEffectSeen) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[49]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[96]++;
int CodeCoverConditionCoverageHelper_C26;
        // Look for side-effects.
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((hasNonLocalSideEffect(n)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[51]++;
          sideEffectSeen = true;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[97]++;

        } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[52]++;}

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[50]++;}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[98]++;
int CodeCoverConditionCoverageHelper_C27;

      // If traversing the nodes of a loop save any references
      // that are seen.
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((inLoop()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((sideEffectSeen) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[53]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[99]++;
int CodeCoverConditionCoverageHelper_C28;
        // Record references to parameters.
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[55]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[100]++;
          String name = n.getString();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[101]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((parameters.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[57]++;
            parametersReferenced.add(name);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[102]++;

          } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[58]++;}

        } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[56]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[103]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[59]++;
          parametersReferenced.add(THIS_MARKER);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[104]++;

        } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[60]++;}
}

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[54]++;}
    }

    /**
     * @return Whether the node may have non-local side-effects.
     */
    private boolean hasNonLocalSideEffect(Node n) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[105]++;
      boolean sideEffect = false;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[106]++;
      int type = n.getType();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[107]++;
int CodeCoverConditionCoverageHelper_C31;
      // Note: Only care about changes to non-local names, specifically
      // ignore VAR declaration assignments.
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (32)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((type == Token.INC) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((type == Token.DEC) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 3) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[61]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[108]++;
        Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[109]++;
int CodeCoverConditionCoverageHelper_C32;
        // Ignore changes to local names.
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isLocalName(lhs)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[63]++;
          sideEffect = true;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[110]++;

        } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[64]++;}

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[62]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[111]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((type == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[65]++;
        sideEffect = NodeUtil.functionCallHasSideEffects(n);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[112]++;

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[66]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[113]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[67]++;
        sideEffect = NodeUtil.constructorCallHasSideEffects(n);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[114]++;

      } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[68]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[115]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((type == Token.DELPROP) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[69]++;
        sideEffect = true;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[116]++;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[70]++;}
}
}
}

      return sideEffect;
    }

    /**
     * @return Whether node is a reference to locally declared name.
     */
    private boolean isLocalName(Node node) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[117]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[71]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[118]++;
        String name = node.getString();
        return locals.contains(name);

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[72]++;}
      return false;
    }
  }

  /**
   * Gather any names declared in the local scope.
   */
  private static void gatherLocalNames(Node n, Set<String> names) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[119]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[73]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[120]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[75]++;
        names.add(n.getFirstChild().getString());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[121]++;

      } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[76]++;}
      // Don't traverse into inner function scopes;
      return;

    } else {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[74]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[122]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[77]++;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[123]++;
      switch (n.getParent().getType()) {
        case Token.VAR:
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[79]++;
        case Token.CATCH:
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[80]++;
          names.add(n.getString());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[124]++; default : CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[81]++;
      }

    } else {
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.branches[78]++;}
}
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[125]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[16]++;


int CodeCoverConditionCoverageHelper_C40;

    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[16]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[17]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[18]++;
}
      gatherLocalNames(c, names);
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[126]++;
    }
  }

  /**
   * Get a set of function parameter names.
   */
  private static Set<String> getFunctionParameterSet(Node fnNode) {
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[127]++;
    Set<String> set = Sets.newHashSet();
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[128]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[19]++;


    for (Node n : NodeUtil.getFunctionParameters(fnNode).children()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[19]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[20]--;
  CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.loops[21]++;
}
      set.add(n.getString());
CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx.statements[129]++;
    }
    return set;
  }

}

class CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx ());
  }
    public static long[] statements = new long[130];
    public static long[] branches = new long[82];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FunctionArgumentInjector.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,2,1,1,1,1,2,1,1,1,2,1,2,1,1,1,1,2,1,1,2,1,1,1,3,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 40; i++) {
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

  public CodeCoverCoverageCounter$6bujop7oak2v6bqo8viftr4j6wea48fuot9921qtk0lsx () {
    super("com.google.javascript.jscomp.FunctionArgumentInjector.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 129; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 81; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FunctionArgumentInjector.java");
      for (int i = 1; i <= 129; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 81; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 40; i++) {
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

