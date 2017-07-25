/*
 * Copyright 2004 The Closure Compiler Authors.
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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticReference;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.StaticSourceFile;
import com.google.javascript.rhino.jstype.StaticSymbolTable;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Scope contains information about a variable scope in JavaScript.
 * Scopes can be nested, a scope points back to its parent scope.
 * A Scope contains information about variables defined in that scope.
 * <p>
 * A Scope is also used as a lattice element for flow-sensitive type inference.
 * As a lattice element, a Scope is viewed as a map from names to types. A name
 * not in the map is considered to have the bottom type. The join of two maps m1
 * and m2 is the map of the union of names with {@link JSType#getLeastSupertype}
 * to meet the m1 type and m2 type.
 *
 * @see NodeTraversal
 * @see DataFlowAnalysis
 *
 */
public class Scope
    implements StaticScope<JSType>, StaticSymbolTable<Scope.Var, Scope.Var> {
  static {
    CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.ping();
  }

  private final Map<String, Var> vars = new LinkedHashMap<String, Var>();
  {
    CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[1]++;
  }
  private final Scope parent;
  private final int depth;
  private final Node rootNode;

  /** Whether this is a bottom scope for the purposes of type inference. */
  private final boolean isBottom;

  private Var arguments;

  private static final Predicate<Var> DECLARATIVELY_UNBOUND_VARS_WITHOUT_TYPES =
      new Predicate<Var>() {
    @Override public boolean apply(Var var) {
      return var.getParentNode() != null &&
          var.getType() == null && // no declared type
          var.getParentNode().isVar() &&
          !var.isExtern();
    }
  };
  static {
    CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[2]++;
  }

  /** Stores info about a variable */
  public static class Var
      implements StaticSlot<JSType>, StaticReference<JSType> {
    /** name */
    final String name;

    /** Var node */
    final Node nameNode;

    /**
     * The variable's type.
     */
    private JSType type;

    /**
     * Whether the variable's type has been inferred or is declared. An inferred
     * type may change over time (as more code is discovered), whereas a
     * declared type is a static contract that must be matched.
     */
    private final boolean typeInferred;

    /** Input source */
    final CompilerInput input;

    /**
     * The index at which the var is declared. e..g if it's 0, it's the first
     * declared variable in that scope
     */
    final int index;

    /** The enclosing scope */
    final Scope scope;

    /** @see #isMarkedEscaped */
    private boolean markedEscaped = false;
  {
    CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[3]++;
  }

    /** @see #isMarkedAssignedExactlyOnce */
    private boolean markedAssignedExactlyOnce = false;
  {
    CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[4]++;
  }

    /**
     * Creates a variable.
     *
     * @param inferred whether its type is inferred (as opposed to declared)
     */
    private Var(boolean inferred, String name, Node nameNode, JSType type,
                Scope scope, int index, CompilerInput input) {
      this.name = name;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[5]++;
      this.nameNode = nameNode;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[6]++;
      this.type = type;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[7]++;
      this.scope = scope;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[8]++;
      this.index = index;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[9]++;
      this.input = input;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[10]++;
      this.typeInferred = inferred;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[11]++;
    }

    /**
     * Gets the name of the variable.
     */
    @Override
    public String getName() {
      return name;
    }

    /**
     * Gets the node for the name of the variable.
     */
    @Override
    public Node getNode() {
      return nameNode;
    }

    CompilerInput getInput() {
      return input;
    }

    @Override
    public StaticSourceFile getSourceFile() {
      return nameNode.getStaticSourceFile();
    }

    @Override
    public Var getSymbol() {
      return this;
    }

    @Override
    public Var getDeclaration() {
      return nameNode == null ? null : this;
    }

    /**
     * Gets the parent of the name node.
     */
    public Node getParentNode() {
      return nameNode == null ? null : nameNode.getParent();
    }

    /**
     * Whether this is a bleeding function (an anonymous named function
     * that bleeds into the inner scope).
     */
    public boolean isBleedingFunction() {
      return NodeUtil.isFunctionExpression(getParentNode());
    }

    /**
     * Gets the scope where this variable is declared.
     */
    Scope getScope() {
      return scope;
    }

    /**
     * Returns whether this is a global variable.
     */
    public boolean isGlobal() {
      return scope.isGlobal();
    }

    /**
     * Returns whether this is a local variable.
     */
    public boolean isLocal() {
      return scope.isLocal();
    }

    /**
     * Returns whether this is defined in an extern file.
     */
    boolean isExtern() {
      return input == null || input.isExtern();
    }

    /**
     * Returns {@code true} if the variable is declared as a constant,
     * based on the value reported by {@code NodeUtil}.
     */
    public boolean isConst() {
      return nameNode != null && NodeUtil.isConstantName(nameNode);
    }

    /**
     * Returns {@code true} if the variable is declared as a define.
     * A variable is a define if it is annotated by {@code @define}.
     */
    public boolean isDefine() {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[12]++;
      JSDocInfo info = getJSDocInfo();
      return info != null && info.isDefine();
    }

    public Node getInitialValue() {
      return NodeUtil.getRValueOfLValue(nameNode);
    }

    /**
     * Gets this variable's type. To know whether this type has been inferred,
     * see {@code #isTypeInferred()}.
     */
    @Override
    public JSType getType() {
      return type;
    }

    /**
     * Returns the name node that produced this variable.
     */
    public Node getNameNode() {
      return nameNode;
    }

    /**
     * Gets the JSDocInfo for the variable.
     */
    @Override
    public JSDocInfo getJSDocInfo() {
      return nameNode == null ? null : NodeUtil.getBestJSDocInfo(nameNode);
    }

    /**
     * Sets this variable's type.
     * @throws IllegalStateException if the variable's type is not inferred
     */
    void setType(JSType type) {
      Preconditions.checkState(isTypeInferred());
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[13]++;
      this.type = type;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[14]++;
    }

    /**
     * Resolve this variable's type.
     */
    void resolveType(ErrorReporter errorReporter) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[1]++;
        type = type.resolve(errorReporter, scope);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[16]++;

      } else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[2]++;}
    }

    /**
     * Returns whether this variable's type is inferred. To get the variable's
     * type, see {@link #getType()}.
     */
    @Override
    public boolean isTypeInferred() {
      return typeInferred;
    }

    public String getInputName() {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((input == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[3]++;
        return "<non-file>";
}
      else {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[4]++;
        return input.getName();
}
    }

    public boolean isNoShadow() {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[18]++;
      JSDocInfo info = getJSDocInfo();
      return info != null && info.isNoShadow();
    }

    @Override public boolean equals(Object other) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((other instanceof Var) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[5]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[6]++;}
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[20]++;

      Var otherVar = (Var) other;
      return otherVar.nameNode == nameNode;
    }

    @Override public int hashCode() {
      return nameNode.hashCode();
    }

    @Override
    public String toString() {
      return "Scope.Var " + name + "{" + type + "}";
    }

    /**
     * Record that this is escaped by an inner scope.
     *
     * In other words, it's assigned in an inner scope so that it's much harder
     * to make assertions about its value at a given point.
     */
    void markEscaped() {
      markedEscaped = true;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[21]++;
    }

    /**
     * Whether this is escaped by an inner scope.
     * Notice that not all scope creators record this information.
     */
    boolean isMarkedEscaped() {
      return markedEscaped;
    }

    /**
     * Record that this is assigned exactly once..
     *
     * In other words, it's assigned in an inner scope so that it's much harder
     * to make assertions about its value at a given point.
     */
    void markAssignedExactlyOnce() {
      markedAssignedExactlyOnce = true;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[22]++;
    }

    /**
     * Whether this is assigned exactly once.
     * Notice that not all scope creators record this information.
     */
    boolean isMarkedAssignedExactlyOnce() {
      return markedAssignedExactlyOnce;
    }
  }

  /**
   * A special subclass of Var used to distinguish "arguments" in the current
   * scope.
   */
  // TODO(johnlenz): Include this the list of Vars for the scope.
  public static class Arguments extends Var {
    Arguments(Scope scope) {
      super(
        false, // no inferred
        "arguments", // always arguments
        null,  // no declaration node
        // TODO(johnlenz): provide the type of "Arguments".
        null,  // no type info
        scope,
        -1,    // no variable index
        null   // input
        );
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[23]++;
    }

    @Override public boolean equals(Object other) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((other instanceof Arguments) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[7]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[8]++;}
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[25]++;

      Arguments otherVar = (Arguments) other;
      return otherVar.scope.getRootNode() == scope.getRootNode();
    }

    @Override public int hashCode() {
      return System.identityHashCode(this);
    }
  }

  /**
   * Creates a Scope given the parent Scope and the root node of the scope.
   * @param parent  The parent Scope. Cannot be null.
   * @param rootNode  Typically the FUNCTION node.
   */
  Scope(Scope parent, Node rootNode) {
    Preconditions.checkNotNull(parent);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[26]++;
    Preconditions.checkArgument(rootNode != parent.rootNode);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[27]++;

    this.parent = parent;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[28]++;
    this.rootNode = rootNode;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[29]++;
    this.isBottom = false;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[30]++;
    this.depth = parent.depth + 1;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[31]++;
  }

  /**
   * Creates a empty Scope (bottom of the lattice).
   * @param rootNode Typically a FUNCTION node or the global BLOCK node.
   * @param isBottom Whether this is the bottom of a lattice. Otherwise,
   *     it must be a global scope.
   */
  private Scope(Node rootNode, boolean isBottom) {
    this.parent = null;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[32]++;
    this.rootNode = rootNode;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[33]++;
    this.isBottom = isBottom;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[34]++;
    this.depth = 0;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[35]++;
  }

  static Scope createGlobalScope(Node rootNode) {
    return new Scope(rootNode, false);
  }

  static Scope createLatticeBottom(Node rootNode) {
    return new Scope(rootNode, true);
  }

  /** The depth of the scope. The global scope has depth 0. */
  int getDepth() {
    return depth;
  }

  /** Whether this is the bottom of the lattice. */
  boolean isBottom() {
    return isBottom;
  }

  /**
   * Gets the container node of the scope. This is typically the FUNCTION
   * node or the global BLOCK/SCRIPT node.
   */
  @Override
  public Node getRootNode() {
    return rootNode;
  }

  public Scope getParent() {
    return parent;
  }

  Scope getGlobalScope() {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[36]++;
    Scope result = this;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[37]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
    while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((result.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.loops[1]--;
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.loops[2]--;
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.loops[3]++;
}
      result = result.getParent();
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[38]++;
    }
    return result;
  }

  @Override
  public StaticScope<JSType> getParentScope() {
    return parent;
  }

  /**
   * Gets the type of {@code this} in the current scope.
   */
  @Override
  public JSType getTypeOfThis() {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[9]++;
      return ObjectType.cast(rootNode.getJSType());

    } else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[10]++;}

    Preconditions.checkState(rootNode.isFunction());
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[40]++;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[41]++;
    JSType nodeType = rootNode.getJSType();
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((nodeType != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nodeType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[11]++;
      return nodeType.toMaybeFunctionType().getTypeOfThis();

    } else {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[12]++;
      return parent.getTypeOfThis();
    }
  }

  /**
   * Declares a variable whose type is inferred.
   *
   * @param name name of the variable
   * @param nameNode the NAME node declaring the variable
   * @param type the variable's type
   * @param input the input in which this variable is defined.
   */
  Var declare(String name, Node nameNode, JSType type, CompilerInput input) {
    return declare(name, nameNode, type, input, true);
  }

  /**
   * Declares a variable.
   *
   * @param name name of the variable
   * @param nameNode the NAME node declaring the variable
   * @param type the variable's type
   * @param input the input in which this variable is defined.
   * @param inferred Whether this variable's type is inferred (as opposed
   *     to declared).
   */
  Var declare(String name, Node nameNode,
      JSType type, CompilerInput input, boolean inferred) {
    Preconditions.checkState(name != null && name.length() > 0);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[43]++;

    // Make sure that it's declared only once
    Preconditions.checkState(vars.get(name) == null);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[44]++;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[45]++;

    Var var = new Var(inferred, name, nameNode, type, this, vars.size(), input);
    vars.put(name, var);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[46]++;
    return var;
  }

  /**
   * Undeclares a variable, to be used when the compiler optimizes out
   * a variable and removes it from the scope.
   */
  void undeclare(Var var) {
    Preconditions.checkState(var.scope == this);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[47]++;
    Preconditions.checkState(vars.get(var.name) == var);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[48]++;
    vars.remove(var.name);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[49]++;
  }

  @Override
  public Var getSlot(String name) {
    return getVar(name);
  }

  @Override
  public Var getOwnSlot(String name) {
    return vars.get(name);
  }

  /**
   * Returns the variable, may be null
   */
  public Var getVar(String name) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[50]++;
    Var var = vars.get(name);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[13]++;
      return var;

    } else {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[14]++;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[52]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[15]++; // Recurse up the parent Scope
      return parent.getVar(name);

    } else {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[16]++;
      return null;
    }
}
  }

  /**
   * Get a unique VAR object to represents "arguments" within this scope
   */
  public Var getArgumentsVar() {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((arguments == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[17]++;
      arguments = new Arguments(this);
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[54]++;

    } else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[18]++;}
    return arguments;
  }

  /**
   * Returns true if a variable is declared.
   */
  public boolean isDeclared(String name, boolean recurse) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[55]++;
    Scope scope = this;
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((scope.vars.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[19]++;
      return true;
} else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[20]++;}
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.statements[57]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((scope.parent != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((recurse) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[21]++;
      return scope.parent.isDeclared(name, recurse);

    } else {
  CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd.branches[22]++;}
    return false;
  }

  /**
   * Return an iterator over all of the variables declared in this scope.
   */
  public Iterator<Var> getVars() {
    return vars.values().iterator();
  }

  /**
   * Return an iterable over all of the variables declared in this scope.
   */
  Iterable<Var> getVarIterable() {
    return vars.values();
  }

  @Override
  public Iterable<Var> getReferences(Var var) {
    return ImmutableList.of(var);
  }

  @Override
  public StaticScope<JSType> getScope(Var var) {
    return var.scope;
  }

  @Override
  public Iterable<Var> getAllSymbols() {
    return Collections.unmodifiableCollection(vars.values());
  }

  /**
   * Returns number of variables in this scope
   */
  public int getVarCount() {
    return vars.size();
  }

  /**
   * Returns whether this is the global scope.
   */
  public boolean isGlobal() {
    return parent == null;
  }

  /**
   * Returns whether this is a local scope (i.e. not the global scope).
   */
  public boolean isLocal() {
    return !isGlobal();
  }

  /**
   * Gets all variables declared with "var" but without declared types attached.
   */
  public Iterator<Var> getDeclarativelyUnboundVarsWithoutTypes() {
    return Iterators.filter(
        getVars(), DECLARATIVELY_UNBOUND_VARS_WITHOUT_TYPES);
  }
}

class CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.Scope.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,2};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$1s4ikxvbkk1cpbhd () {
    super("com.google.javascript.jscomp.Scope.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Scope.java");
      for (int i = 1; i <= 57; i++) {
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
    for (int i = 1; i <= 12; i++) {
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

