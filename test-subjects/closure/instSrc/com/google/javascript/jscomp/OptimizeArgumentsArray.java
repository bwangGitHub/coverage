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
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Deque;
import java.util.List;

/**
 * Optimization for functions that have {@code var_args} or access the
 * arguments array.
 *
 * <p>Example:
 * <pre>
 * function() { alert(arguments[0] + argument[1]) }
 * </pre>
 * to:
 * <pre>
 * function(a, b) { alert(a, b) }
 * </pre>
 *
 * Each newly inserted variable name will be unique very much like the output
 * of the AST found after the {@link Normalize} pass.
 *
 */
class OptimizeArgumentsArray implements CompilerPass, ScopedCallback {
  static {
    CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.ping();
  }


  // The arguments object as described by ECMAScript version 3
  // section 10.1.8
  private static final String ARGUMENTS = "arguments";
  static {
    CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[1]++;
  }

  // To ensure that the newly introduced parameter names are unique. We will
  // use this string as prefix unless the caller specify a different prefix.
  private static final String PARAMETER_PREFIX =
      "JSCompiler_OptimizeArgumentsArray_p";
  static {
    CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[2]++;
  }

  // The prefix for the newly introduced parameter name.
  private final String paramPredix;

  // To make each parameter name unique in the function. We append an
  // unique integer at the end.
  private int uniqueId = 0;
  {
    CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[3]++;
  }

  // Reference to the compiler object to notify any changes to source code AST.
  private final AbstractCompiler compiler;

  // A stack of arguments access list to the corresponding outer functions.
  private final Deque<List<Node>> argumentsAccessStack = Lists.newLinkedList();
  {
    CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[4]++;
  }

  // This stores a list of argument access in the current scope.
  private List<Node> currentArgumentsAccess = null;
  {
    CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[5]++;
  }

  /**
   * Construct this pass and use {@link #PARAMETER_PREFIX} as the prefix for
   * all parameter names that it introduces.
   */
  OptimizeArgumentsArray(AbstractCompiler compiler) {
    this(compiler, PARAMETER_PREFIX);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[6]++;
  }

  /**
   * @param paramPrefix the prefix to use for all parameter names that this
   *     pass introduces
   */
  OptimizeArgumentsArray(AbstractCompiler compiler, String paramPrefix) {
    this.compiler = Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[7]++;
    this.paramPredix = Preconditions.checkNotNull(paramPrefix);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[8]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, Preconditions.checkNotNull(root), this);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[9]++;
  }

  @Override
  public void enterScope(NodeTraversal traversal) {
    Preconditions.checkNotNull(traversal);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[10]++;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[11]++;

    // This optimization is valid only within a function so we are going to
    // skip over the initial entry to the global scope.
    Node function = traversal.getScopeRoot();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((function.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[2]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;

    // Introduces a new access list and stores the access list of the outer
    // scope in the stack if necessary.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((currentArgumentsAccess != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[3]++;
      argumentsAccessStack.push(currentArgumentsAccess);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[14]++;

    } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[4]++;}
    currentArgumentsAccess = Lists.newLinkedList();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[15]++;
  }

  @Override
  public void exitScope(NodeTraversal traversal) {
    Preconditions.checkNotNull(traversal);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[16]++;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

    // This is the case when we are exiting the global scope where we had never
    // collected argument access list. Since we do not perform this optimization
    // for the global scope, we will skip this exit point.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((currentArgumentsAccess == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[6]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;

    // Attempt to replace the argument access and if the AST has been change,
    // report back to the compiler.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((tryReplaceArguments(traversal.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[7]++;
      traversal.getCompiler().reportCodeChange();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[19]++;

    } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[8]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;

    // After the attempt to replace the arguments. The currentArgumentsAccess
    // is stale and as we exit the Scope, no longer holds all the access to the
    // current scope anymore. We'll pop the access list from the outer scope
    // and set it as currentArgumentsAcess if the outer scope is not the global
    // scope.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((argumentsAccessStack.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[9]++;
      currentArgumentsAccess = argumentsAccessStack.pop();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[21]++;

    } else {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[10]++;
      currentArgumentsAccess = null;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[22]++;
    }
  }

  @Override
  public boolean shouldTraverse(
      NodeTraversal nodeTraversal, Node node, Node parent) {
    // We will continuously recurse down the AST regardless of the node types.
    return true;
  }

  @Override
  public void visit(NodeTraversal traversal, Node node, Node parent) {
    Preconditions.checkNotNull(traversal);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[23]++;
    Preconditions.checkNotNull(node);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[24]++;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;


    // Searches for all the references to the arguments array.

    // We don't have an arguments list set up for this scope. This implies we
    // are currently in the global scope so we will not record any arguments
    // array access.
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((currentArgumentsAccess == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[11]++;
      return;

    } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[12]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;

    // Otherwise, we are in a function scope and we should record if the current
    // name is referring to the implicit arguments array.
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ARGUMENTS.equals(node.getString())) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[13]++;
      currentArgumentsAccess.add(node);
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[27]++;

    } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[14]++;}
  }

  /**
   * Tries to optimize all the arguments array access in this scope by assigning
   * a name to each element.
   *
   * @param scope scope of the function
   * @return true if any modification has been done to the AST
   */
  private boolean tryReplaceArguments(Scope scope) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[28]++;

    Node parametersList = scope.getRootNode().getFirstChild().getNext();
    Preconditions.checkState(parametersList.isParamList());
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[29]++;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[30]++;

    // Keep track of rather this function modified the AST and needs to be
    // reported back to the compiler later.
    boolean changed = false;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[31]++;

    // Number of parameter that can be accessed without using the arguments
    // array.
    int numNamedParameter = parametersList.getChildCount();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[32]++;

    // We want to guess what the highest index that has been access from the
    // arguments array. We will guess that it does not use anything index higher
    // than the named parameter list first until we see other wise.
    int highestIndex = numNamedParameter - 1;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[33]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[1]++;



    // Iterate through all the references to arguments array in the function to
    // determine the real highestIndex.
    for (Node ref : currentArgumentsAccess) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[1]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[2]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[3]++;
}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[34]++;

      Node getElem = ref.getParent();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;

      // Bail on anything but argument[c] access where c is a constant.
      // TODO(user): We might not need to bail out all the time, there might
      // be more cases that we can cover.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((getElem.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[15]++;
        return false;

      } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[16]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[36]++;

      Node index = ref.getNext();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;

      // We have something like arguments[x] where x is not a constant. That
      // means at least one of the access is not known.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((index.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[17]++;
        // TODO(user): Its possible not to give up just yet. The type
        // inference did a 'semi value propagation'. If we know that string
        // is never a subclass of the type of the index. We'd know that
        // it is never 'callee'.
        return false;
 // Give up.
      } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[18]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[38]++;

      Node getElemParent = getElem.getParent();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
      // When we have argument[0](), replacing it with a() is semantically
      // different if argument[0] is a function call that refers to 'this'
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((getElemParent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((getElemParent.getFirstChild() == getElem) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[19]++;
        // TODO(user): We can consider using .call() if aliasing that
        // argument allows shorter alias for other arguments.
        return false;

      } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[20]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[40]++;

      // Replace the highest index if we see an access that has a higher index
      // than all the one we saw before.
      int value = (int) index.getDouble();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[41]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value > highestIndex) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[21]++;
        highestIndex = value;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[42]++;

      } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[22]++;}
    }
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[43]++;

    // Number of extra arguments we need.
    // For example: function() { arguments[3] } access index 3 so
    // it will need 4 extra named arguments to changed into:
    // function(a,b,c,d) { d }.
    int numExtraArgs = highestIndex - numNamedParameter + 1;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[44]++;

    // Temporary holds the new names as string for quick access later.
    String[] argNames = new String[numExtraArgs];
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[45]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[4]++;


int CodeCoverConditionCoverageHelper_C12;

    // Insert the formal parameter to the method's signature.
    // Example: function() --> function(r0, r1, r2)
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < numExtraArgs) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[4]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[5]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[6]++;
}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[46]++;
      String name = getNewName();
      argNames[i] = name;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[47]++;
      parametersList.addChildrenToBack(IR.name(name));
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[48]++;
      changed = true;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[49]++;
    }
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[50]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[7]++;



    // This loop performs the replacement of arguments[x] -> a if x is known.
    for (Node ref : currentArgumentsAccess) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[7]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[8]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[9]++;
}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[51]++;
      Node index = ref.getNext();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;

      // Skip if it is unknown.
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((index.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[23]++;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[53]++;
        continue;

      } else {
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[24]++;}
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[54]++;
      int value = (int) index.getDouble();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[55]++;
int CodeCoverConditionCoverageHelper_C14;

      // Unnamed parameter.
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value >= numNamedParameter) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[25]++;
        ref.getParent().getParent().replaceChild(ref.getParent(),
            IR.name(argNames[value - numNamedParameter]));
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[56]++;

      } else {
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.branches[26]++;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[57]++;

        // Here, for no apparent reason, the user is accessing a named parameter
        // with arguments[idx]. We can replace it with the actual name for them.
        Node name = parametersList.getFirstChild();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[58]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[10]++;


int CodeCoverConditionCoverageHelper_C15;

        // This is a linear search for the actual name from the signature.
        // It is not necessary to make this fast because chances are the user
        // will not deliberately write code like this.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < value) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[10]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[11]--;
  CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.loops[12]++;
}
          name = name.getNext();
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[59]++;
        }
        ref.getParent().getParent().replaceChild(ref.getParent(),
            IR.name(name.getString()));
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[60]++;
      }
      changed = true;
CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh.statements[61]++;
    }

    return changed;
  }

  /**
   * Generate a unique name for the next parameter.
   */
  private String getNewName() {
    return paramPredix + uniqueId++;
  }
}

class CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh ());
  }
    public static long[] statements = new long[62];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.OptimizeArgumentsArray.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$52vsx2qx4r3wwwm13qfnxv13pywm6u6g4mw3banskh () {
    super("com.google.javascript.jscomp.OptimizeArgumentsArray.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 61; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.OptimizeArgumentsArray.java");
      for (int i = 1; i <= 61; i++) {
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
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

