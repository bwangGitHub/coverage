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

import static com.google.javascript.rhino.jstype.JSTypeNative.U2U_CONSTRUCTOR_TYPE;
import static com.google.javascript.rhino.jstype.JSTypeNative.UNKNOWN_TYPE;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ConcreteType.ConcreteFunctionType;
import com.google.javascript.jscomp.ConcreteType.ConcreteInstanceType;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticReference;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSlot;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Computes the set of possible concrete types for every variable, property,
 * function argument, and function return value in the program.  Unlike a normal
 * reference type annotation, a concrete type of A indicates that an instance of
 * A -- not a subclass of A -- is a possible value.
 *
 * Also unlike normal type checking, this pass does not assume that all defined
 * functions are actually called.  Instead, it assumes only that the top-level
 * code is executed plus any implicit calls detected, such as calls to functions
 * exported via goog.exportSymbol or Element.addEventListener.  Hence, this pass
 * also performs a very strict form of dead code detection.  Elimination of dead
 * code will occur because the disambiguation pass can rename all uncalled
 * functions to have distinct names, which will then appear to be uncalled to
 * the normal unused property remover.
 *
 * Since concrete types are all reference types, we only care about the limited
 * set of actions that apply to them:  assignments to variables/properties,
 * method calls, and return statements.  To speed up and simplify the
 * implementation, the first time a scope is processed, we make one pass through
 * it {@link CreateScope} to translate it into a list of Actions.  Each Action
 * can translate itself into a list of assignments:  method calls are just
 * assignments to the parameter variables, while return statements are
 * assignments to a special $return slot.  Each time a scope is (re-)processed,
 * we iterate over the assignments produced by the actions and update the types
 * of the target slots.  Once we complete a pass through all scopes with no
 * changes, we are done.
 *
 */
class TightenTypes implements CompilerPass, ConcreteType.Factory {
  static {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.ping();
  }

  public static final String NON_HALTING_ERROR_MSG =
    "TightenTypes pass appears to be stuck in an infinite loop.";
  static {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[1]++;
  }

  /** The compiler that invoked this pass. */
  private final AbstractCompiler compiler;

  /**
   * Map of function type information to their concrete wrappers.  These must be
   * reused so that each declaration has only a single concrete type, which will
   * hold all the known types that flow to its arguments and return value.
   */
  private final Map<Node, ConcreteFunctionType> functionFromDeclaration =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[2]++;
  }

  /**
   * Secondary index of concrete functions by JSType.  This is necessary for
   * retrieving the concrete type of a superclass, where the actual declaration
   * is not at hand.  Note that we must use an identity hash map here because
   * functions are compared using the signature only.
   */
  private final Map<FunctionType, ConcreteFunctionType> functionFromJSType =
      Maps.newIdentityHashMap();
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[3]++;
  }

  /**
   * Map of instance type information to their concrete wrappers.  These must be
   * reused so that each property has only one variable, which will store all
   * known types that flow to that variable.
   */
  private final Map<ObjectType, ConcreteInstanceType> instanceFromJSType =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[4]++;
  }

  /**
   * Memoized results of "createTypeIntersection" calls.
   */
  private final Map<ConcreteJSTypePair, ConcreteType> typeIntersectionMemos =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[5]++;
  }

  /** Scope storing the top-level variables and functions. */
  private ConcreteScope topScope;

  TightenTypes(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[6]++;
  }

  /** Returns the top scope computed during the pass. */
  ConcreteScope getTopScope() { return topScope; }

  /** Convenience method to get the type registry of the compiler. */
  @Override
  public JSTypeRegistry getTypeRegistry() { return compiler.getTypeRegistry(); }

  /** All concrete instance types encountered during flow analysis. */
  private Set<ConcreteType> allInstantiatedTypes = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[7]++;
  }

  @Override
  public void process(Node externRoot, Node jsRoot) {
    // Create the scope of top-level variables and functions.
    topScope = new ConcreteScope(null);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[8]++;
    topScope.initForExternRoot(externRoot);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[9]++;
    topScope.initForScopeRoot(jsRoot);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[10]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[11]++;

    // Process the assignments in each scope in the working set until no more
    // changes are detected.  Each time a new scope is discovered (starting with
    // the top-level scope), it is added to the working set to be processed.
    // Since changes in almost any scope can affect another, we iterate over all
    // discovered scopes until no further changes occur.

    long maxIterations = 1000;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[12]++;
    long iterations = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[13]++;

    Set<ConcreteScope> workSet = Sets.newHashSet(topScope);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[14]++;
    List<ConcreteScope> workList = Lists.newArrayList(topScope);

    boolean changed;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[1]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[2]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[3]++;
}
      changed = false;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[16]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < workList.size()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[4]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[5]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[6]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[18]++;
        ConcreteScope scope = workList.get(i);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[19]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[7]++;


        for (Action action : scope.getActions()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[7]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[8]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[9]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[20]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[10]++;


          for (Assignment assign : action.getAssignments(scope)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[10]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[11]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[12]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((assign.slot.addConcreteType(assign.type)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[1]++;
              changed = true;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[22]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[23]++;
              ConcreteScope varScope = assign.slot.getScope();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
              if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((varScope != scope) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((workSet.contains(varScope)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[3]++;
                workSet.add(varScope);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[25]++;
                workList.add(varScope);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[26]++;

              } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[4]++;}

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[2]++;}
          }
        }
      }
      Preconditions.checkState(++iterations != maxIterations,
          NON_HALTING_ERROR_MSG);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[27]++;
    } while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false));
  }

  /**
   * Represents a scope in which a set of slots are declared.  The scope also
   * includes code, which is normalized to a set of actions (which may affect
   * slots in other scopes as well).
   */
  class ConcreteScope implements StaticScope<ConcreteType> {
    private final ConcreteScope parent;
    private final Map<String, ConcreteSlot> slots;
    private final List<Action> actions;

    ConcreteScope(ConcreteScope parent) {
      this.parent = parent;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[28]++;
      this.slots = Maps.newHashMap();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[29]++;
      this.actions = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[30]++;
    }

    @Override
    public Node getRootNode() { return null; }

    @Override
    public StaticScope<ConcreteType> getParentScope() { return parent; }

    @Override
    public StaticSlot<ConcreteType> getOwnSlot(String name) {
      return slots.get(name);
    }

    @Override
    public StaticSlot<ConcreteType> getSlot(String name) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[31]++;
      StaticSlot<ConcreteType> var = getOwnSlot(name);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[32]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[5]++;
        return var;

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[6]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[33]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[7]++;
        return parent.getSlot(name);

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[8]++;
        return null;
      }
}
    }

    /** Returns all the slots in this scope. */
    Collection<ConcreteSlot> getSlots() { return slots.values(); }

    @Override
    public ConcreteType getTypeOfThis() {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[34]++;
      // Since the slot doesn't have a reference to its ConcreteType, we can't
      // reference the ConcreteFunctionType directly to get the typeOfThis.
      ConcreteSlot thisVar = slots.get(ConcreteFunctionType.THIS_SLOT_NAME);
      return (thisVar != null) ? thisVar.getType() : ConcreteType.NONE;
    }

    /** Add a declaration for the given variable. */
    void declareSlot(String name, Node declaration) {
      slots.put(name, new ConcreteSlot(this, name));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[35]++;
    }

    /** Add a declaration for the given variable with the given type. */
    void declareSlot(String name, Node declaration, ConcreteType type) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[36]++;
      ConcreteSlot var = new ConcreteSlot(this, name);
      var.addConcreteType(type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[37]++;
      slots.put(name, var);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[38]++;
    }

    /** Returns all the actions performed in the code of this scope. */
    List<Action> getActions() { return actions; }

    /** Finds assignments and variables from the function body. */
    void initForScopeRoot(Node decl) {
      Preconditions.checkNotNull(decl);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[39]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((decl.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[9]++;
        decl = decl.getLastChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[41]++;

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[10]++;}
      Preconditions.checkArgument(decl.isBlock());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[42]++;

      NodeTraversal.traverse(compiler, decl, new CreateScope(this, false));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[43]++;
    }

    /** Finds assignments and variables from the given externs. */
    void initForExternRoot(Node decl) {
      Preconditions.checkNotNull(decl);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[44]++;
      Preconditions.checkArgument(decl.isBlock());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[45]++;

      NodeTraversal.traverse(compiler, decl, new CreateScope(this, true));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[46]++;
    }

    /** Adds the given action to the list for the code in this scope. */
    void addAction(Action action) { actions.add(action);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[47]++; }

    @Override public String toString() {
      return getTypeOfThis().toString() + " " + getSlots();
    }
  }

  /** Represents a variable or function declared in a scope. */
  static class ConcreteSlot implements StaticSlot<ConcreteType> {
    private final ConcreteScope scope;
    private final String name;
    private ConcreteType type;

    ConcreteSlot(ConcreteScope scope, String name) {
      this.scope = scope;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[48]++;
      this.name = name;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[49]++;
      this.type = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[50]++;
    }

    /** Returns the scope in which this slot exists. */
    ConcreteScope getScope() { return scope; }

    /** Returns the name of this slot in its scope. */
    @Override public String getName() { return name; }

    @Override public ConcreteType getType() { return type; }

    /** Whether this type was inferred rather than declared (always true). */
    @Override public boolean isTypeInferred() { return true; }

    @Override public StaticReference<ConcreteType> getDeclaration() {
      return null;
    }

    @Override public JSDocInfo getJSDocInfo() {
      return null;
    }

    /**
     * Adds the given type to the possible concrete types for this slot.
     * Returns whether the added type was not already known.
     */
    boolean addConcreteType(ConcreteType type) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[51]++;
      ConcreteType origType = this.type;
      this.type = origType.unionWith(type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[52]++;
      return !this.type.equals(origType);
    }

    @Override public String toString() {
      return getName() + ": " + getType();
    }
  }

  /**
   * Represents a type of action performed in the body of scope that may affect
   * the concrete types of slot.  Example actions are a function call, a
   * variable assignment, and a property assignment.  The function call will
   * create assignments for each of the function parameters, for the "this"
   * slot, and for the "call" slot.  Property and variable assignment actions
   * create assignments for the property or variable they represent.
   */
  private static interface Action {
    /** Returns all assignments that may occur by this action. */
    Collection<Assignment> getAssignments(ConcreteScope scope);
  }

  /** Represents an assignment to a variable of a set of possible types. */
  private static class Assignment {
    private final ConcreteSlot slot;
    private final ConcreteType type;

    Assignment(ConcreteSlot slot, ConcreteType type) {
      this.slot = slot;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[53]++;
      this.type = type;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[54]++;

      Preconditions.checkNotNull(slot);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[55]++;
      Preconditions.checkNotNull(type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[56]++;
    }
  }

  /** Records an assignment of an expression to a variable. */
  private class VariableAssignAction implements Action {
    private final ConcreteSlot slot;
    private final Node expression;

    VariableAssignAction(ConcreteSlot slot, Node expr) {
      this.slot = slot;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[57]++;
      this.expression = expr;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[58]++;

      Preconditions.checkNotNull(slot);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[59]++;
      Preconditions.checkNotNull(expr);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[60]++;
    }

    @Override
    public Collection<Assignment> getAssignments(ConcreteScope scope) {
      return Lists.newArrayList(
          new Assignment(slot, inferConcreteType(scope, expression)));
    }
  }

  /** Records an assignment of an expression to a property of an object. */
  private class PropertyAssignAction implements Action {
    private final Node receiver;
    private final String propName;
    private final Node expression;

    PropertyAssignAction(Node receiver, Node expr) {
      this.receiver = receiver;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[61]++;
      this.propName = receiver.getNext().getString();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[62]++;
      this.expression = expr;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[63]++;

      Preconditions.checkNotNull(receiver);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[64]++;
      Preconditions.checkNotNull(propName);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[65]++;
      Preconditions.checkNotNull(expr);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[66]++;
    }

    /**
     * Returns all assignments that could occur as a result of this property
     * assign action. Each type in the receiver is checked for a property
     * {@code propName}, and if that property exists, it is assigned the type
     * of {@code expression}.
     */
    @Override
    public Collection<Assignment> getAssignments(ConcreteScope scope) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[67]++;
      ConcreteType recvType = inferConcreteType(scope, receiver);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[68]++;
      ConcreteType exprType = inferConcreteType(scope, expression);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[69]++;

      List<Assignment> assigns = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[70]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[13]++;


      for (StaticSlot<ConcreteType> prop
           : recvType.getPropertySlots(propName)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[13]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[14]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[15]++;
}
        assigns.add(new Assignment((ConcreteSlot) prop, exprType));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[71]++;
      }
      return assigns;
    }
  }

  /** Helper class to build a FunctionCall object. */
  private class FunctionCallBuilder {
    private boolean isNewCall = false;
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[72]++;
  }
    private boolean isCallFunction = false;
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[73]++;
  }
    private final Node receiver;
    private final Node firstArgument;
    private String propName = null;
  {
    CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[74]++;
  }

    FunctionCallBuilder(Node receiver, Node firstArgument) {
      this.receiver = receiver;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[75]++;
      this.firstArgument = firstArgument;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[76]++;
    }

    FunctionCallBuilder setPropName(String propName) {
      this.propName = propName;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[77]++;
      return this;
    }

    /** Should be called iff this is a new call, e.g. new Object(); */
    FunctionCallBuilder setIsNewCall(boolean isNew) {
      Preconditions.checkState(!(isCallFunction && isNew),
          "A function call cannot be of the form: new Object.call()");
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[78]++;

      isNewCall = isNew;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[79]++;
      return this;
    }

    /**
     *  Should be called iff this is a {@code call()} function call,
     *  e.g. Array.prototype.slice.call(arguments, 0);
     */
    FunctionCallBuilder setIsCallFunction() {
      Preconditions.checkState(!isNewCall,
          "A function call cannot be of the form: new Object.call()");
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[80]++;

      isCallFunction = true;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[81]++;
      return this;
    }

    Action build() {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[82]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[11]++;
        return new NativeCallFunctionCall(receiver, propName, firstArgument);

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[12]++;
        return new FunctionCall(isNewCall, receiver, propName, firstArgument);
      }
    }
  }

  /**
   * Returns a list of assignments that will result from a function call with
   * the given concrete types.
   */
  private List<Assignment> getFunctionCallAssignments(ConcreteType recvType,
      ConcreteType thisType, List<ConcreteType> argTypes) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[83]++;
    List<Assignment> assigns = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[84]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[16]++;


    for (ConcreteFunctionType fType : recvType.getFunctions()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[16]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[17]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[18]++;
}
      assigns.add(new Assignment((ConcreteSlot) fType.getCallSlot(), fType));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[85]++;
      assigns.add(new Assignment((ConcreteSlot) fType.getThisSlot(), thisType));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[86]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[87]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[19]++;


int CodeCoverConditionCoverageHelper_C9;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i < argTypes.size()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[19]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[20]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[21]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[88]++;
        ConcreteSlot variable = (ConcreteSlot) fType.getParameterSlot(i);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[89]++;
int CodeCoverConditionCoverageHelper_C10;
        // TODO(johnlenz): Support "arguments" references in function bodies.
        // For now, ignore anonymous arguments.
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((variable != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[13]++;
          assigns.add(new Assignment(variable, argTypes.get(i)));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[90]++;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[14]++;}
      }
    }
    return assigns;
  }

  /**
   * Records a call to a function with a given set of concrete types.  This is
   * used for function calls that originate outside the scope of the user code.
   * E.g. callbacks from an extern function.
   */
  private class ExternFunctionCall implements Action {
    private Node receiver;
    private ConcreteType thisType;
    private List<ConcreteType> argTypes;

    ExternFunctionCall(Node receiver, ConcreteType thisType,
                       List<ConcreteType> argTypes) {
      this.receiver = receiver;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[91]++;
      this.thisType = thisType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[92]++;
      this.argTypes = argTypes;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[93]++;
    }

    @Override
    public Collection<Assignment> getAssignments(ConcreteScope scope) {
      return getFunctionCallAssignments(inferConcreteType(scope, receiver),
                                        thisType, argTypes);
    }
  }

  /** Records a call to a function with a given set of arguments. */
  private class FunctionCall implements Action {
    private final boolean isNewCall;
    private final Node receiver;
    private final String propName;
    private final Node firstArgument;

    /**
     * The function called is {@code receiver} or, if {@code propName} is
     * non-null, the {@propName} field of {@code receiver}.
     */
    FunctionCall(boolean isNewCall, Node receiver, String propName,
                 Node firstArgument) {
      this.isNewCall = isNewCall;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[94]++;
      this.receiver = receiver;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[95]++;
      this.propName = propName;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[96]++;
      this.firstArgument = firstArgument;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[97]++;

      Preconditions.checkNotNull(receiver);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[98]++;
    }

    @Override
    public Collection<Assignment> getAssignments(ConcreteScope scope) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[99]++;
      ConcreteType thisType = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[100]++;
      ConcreteType recvType = inferConcreteType(scope, receiver);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[101]++;
int CodeCoverConditionCoverageHelper_C11;

      // If a property name was specified, then the receiver is actually the
      // type of this and the actual receiver is the type of that property.
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((propName != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[15]++;
        thisType = recvType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[102]++;
        recvType = thisType.getPropertyType(propName);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[103]++;

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[16]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[104]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((recvType.isAll()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[17]++;
        // TODO(user): ensure that this will trigger for code like
        // functions[3]();
        throw new AssertionError(
            "Found call on all type, which makes tighten types useless.");

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[18]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[105]++;
int CodeCoverConditionCoverageHelper_C13;

      // If this is a call to new, then a new instance of the receiver is
      // created and passed in as the value of this.
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isNewCall) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[19]++;
        thisType = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[106]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[107]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[22]++;


        for (ConcreteInstanceType instType
             : recvType.getFunctionInstanceTypes()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[22]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[23]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[24]++;
}
          thisType = thisType.unionWith(instType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[108]++;
        }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[109]++;
        boolean added = allInstantiatedTypes.add(thisType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[110]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((added) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[21]++;
          // A new type instance invalidates the cached type intersections.
          typeIntersectionMemos.clear();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[111]++;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[22]++;}

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[20]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[112]++;

      List<ConcreteType> argTypes = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[113]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[25]++;


int CodeCoverConditionCoverageHelper_C15;
      for (Node arg = firstArgument;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); arg = arg.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[25]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[26]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[27]++;
}
        argTypes.add(inferConcreteType(scope, arg));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[114]++;
      }

      return getFunctionCallAssignments(recvType, thisType, argTypes);
    }
  }

  /** Records a call to the native call() function. */
  private class NativeCallFunctionCall implements Action {
    private final Node receiver;
    private final String propName;
    private final Node firstArgument;

    NativeCallFunctionCall(Node receiver, String propName, Node firstArgument) {
      this.receiver = receiver;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[115]++;
      this.propName = propName;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[116]++;
      this.firstArgument = firstArgument;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[117]++;

      Preconditions.checkNotNull(receiver);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[118]++;
    }

    @Override
    public Collection<Assignment> getAssignments(ConcreteScope scope) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[119]++;
      ConcreteType thisType = (firstArgument != null)
          ? inferConcreteType(scope, firstArgument)
          : getTopScope().getTypeOfThis();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[120]++;
      ConcreteType recvType = inferConcreteType(scope, receiver);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[121]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((recvType instanceof ConcreteInstanceType) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((((ConcreteInstanceType) recvType).isFunctionPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[23]++;
        recvType = thisType.getPropertyType(propName);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[122]++;

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[24]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[123]++;
      List<ConcreteType> argTypes = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[124]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[28]++;


int CodeCoverConditionCoverageHelper_C17;
      // Skip the first argument for call() as it is the 'this' object.
      for (Node arg = firstArgument.getNext();(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false);
           arg = arg.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[28]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[29]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[30]++;
}
        argTypes.add(inferConcreteType(scope, arg));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[125]++;
      }
      return getFunctionCallAssignments(recvType, thisType, argTypes);
    }
  }

  /** Adds all the variables and assignments to a given scope from the code. */
  private class CreateScope extends AbstractShallowCallback {
    private final ConcreteScope scope;
    private final boolean inExterns;

    CreateScope(ConcreteScope scope, boolean inExterns) {
      this.scope = scope;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[126]++;
      this.inExterns = inExterns;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[127]++;
    }

    // TODO(user): handle object literals like { a: new Foo };
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[128]++;
      switch (n.getType()) {
        case Token.VAR:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[25]++;
          // Variable declaration, e.g. var a = b;
          Node name;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[129]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[31]++;


int CodeCoverConditionCoverageHelper_C18;
          for (name = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); name = name.getNext()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[31]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[32]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[33]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[130]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[26]++;
              // In externs, we have to trust the type information because there
              // are not necessarily assignments to the variables, calls to the
              // functions, etc.
              scope.declareSlot(name.getString(), n, createType(name, scope));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[131]++;

            } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[27]++;
              scope.declareSlot(name.getString(), n);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[132]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[133]++;
int CodeCoverConditionCoverageHelper_C20;
              if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((name.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[28]++;
                addActions(createAssignmentActions(
                    name, name.getFirstChild(), n));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[134]++;

              } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[29]++;}
            }
          }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[135]++;
          break;

        case Token.GETPROP:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[30]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[136]++;
int CodeCoverConditionCoverageHelper_C21;
          // Property access, e.g. a.b = c;
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[31]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[137]++;
            ConcreteType type = inferConcreteType(getTopScope(), n);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[138]++;
int CodeCoverConditionCoverageHelper_C22;
            // We only need to set a type if one hasn't been assigned by
            // something else, e.g. an ASSIGN node.
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((type.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[33]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[139]++;
              ConcreteScope scope =
                  (ConcreteScope) inferConcreteType(getTopScope(),
                      n.getFirstChild()).getScope();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[140]++;
int CodeCoverConditionCoverageHelper_C23;
              if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[35]++;
                type = createType(n.getJSType());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[141]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[142]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((type.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((type.isAll()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[37]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[143]++;
                  break;

                } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[38]++;}
                type = createUnionWithSubTypes(type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[144]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[145]++;
                Node nameNode = n.getLastChild();
                scope.declareSlot(nameNode.getString(), n, type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[146]++;

              } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[36]++;}

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[34]++;}

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[32]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[147]++;
          break;

        case Token.FUNCTION:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[39]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[148]++;
int CodeCoverConditionCoverageHelper_C25;
          // Function declaration, e.g. function Foo() {};
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[40]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[149]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((n.getJSType().isNoObjectType()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[42]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[150]++;
              ConcreteFunctionType type = createConcreteFunction(n, scope);
              scope.declareSlot(n.getFirstChild().getString(), n, type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[151]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[152]++;
int CodeCoverConditionCoverageHelper_C27;

              if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((type.getInstanceType() != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[44]++;
                // We must assume all extern types are instantiated since they
                // can be created by the browser itself.
                allInstantiatedTypes.add(type.getInstanceType());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[153]++;

              } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[45]++;}

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[43]++;}

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[41]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[154]++;
          break;

        case Token.ASSIGN:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[46]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[155]++;
          // Variable assignment, e.g. a = b;
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[156]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[47]++;
            // Again, we have to trust the externs.
            ConcreteScope scope;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[157]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[49]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[158]++;
              ConcreteType type = inferConcreteType(getTopScope(),
                  lhs.getFirstChild());
              scope = (ConcreteScope) type.getScope();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[159]++;

            } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[50]++;
              scope = getTopScope();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[160]++;
            }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[161]++;
int CodeCoverConditionCoverageHelper_C30;

            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[51]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[162]++; break;
} else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[52]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[163]++;

            ConcreteType type = inferConcreteType(getTopScope(), n);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[164]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((type.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((type.isAll()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[53]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[165]++;
              break;

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[54]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[166]++;
int CodeCoverConditionCoverageHelper_C32;

            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((type.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[55]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[167]++;
              JSType lhsType = lhs.getJSType();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[168]++;
int CodeCoverConditionCoverageHelper_C33;
              if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((lhsType == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[57]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[169]++;
                break;

              } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[58]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[170]++;
              FunctionType funType =
                  lhsType.restrictByNotNullOrUndefined().toMaybeFunctionType();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[171]++;
int CodeCoverConditionCoverageHelper_C34;
              if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((funType == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[59]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[172]++;
                break;

              } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[60]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[173]++;
              ConcreteType retType = createType(funType.getReturnType());
              retType = createUnionWithSubTypes(retType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[174]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[175]++;
              ConcreteType newret = type.toFunction().getReturnSlot()
                  .getType().unionWith(retType);
              ((ConcreteScope) type.getScope()).declareSlot(
                  ConcreteFunctionType.RETURN_SLOT_NAME, n, newret);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[176]++;

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[56]++;}
            scope.declareSlot(lhs.getLastChild().getString(), n, type);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[177]++;

          } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[48]++;
            addActions(createAssignmentActions(lhs, n.getLastChild(), n));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[178]++;
          }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[179]++;
          break;

        case Token.NEW:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[61]++;
        case Token.CALL:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[62]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[180]++;
          Node receiver = n.getFirstChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[181]++;
int CodeCoverConditionCoverageHelper_C35;
          if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((receiver.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[63]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[182]++;
            Node first = receiver.getFirstChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[183]++;
int CodeCoverConditionCoverageHelper_C36;
            // Special case the call() function.
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 (("call".equals(first.getNext().getString())) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[65]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[184]++;
int CodeCoverConditionCoverageHelper_C37;
              if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((first.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[67]++;
                // foo.bar.call()
                addAction(new FunctionCallBuilder(first, receiver.getNext())
                    .setPropName(first.getFirstChild().getNext().getString())
                    .setIsCallFunction()
                    .build());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[185]++;

              } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[68]++;
                // bar.call()
                addAction(new FunctionCallBuilder(
                    first, receiver.getNext()).setIsCallFunction()
                          .build());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[186]++;
              }

            } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[66]++;
              // foo.bar()
              addAction(new FunctionCallBuilder(first, receiver.getNext())
                  .setPropName(first.getNext().getString())
                  .build());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[187]++;
            }

          } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[64]++;
            // foo() or new Foo()
            addAction(new FunctionCallBuilder(receiver, receiver.getNext())
                      .setIsNewCall(n.isNew())
                      .build());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[188]++;
          }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[189]++;
          break;

        case Token.NAME:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[69]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[190]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((parent.isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[70]++;
            // The variable in a catch statement gets defined in the scope of
            // the catch block. We approximate that, as does the normal type
            // system, by declaring a variable for it in the scope in which the
            // catch is declared.
            scope.declareSlot(n.getString(), n,
                createUnionWithSubTypes(
                  createType(getTypeRegistry().getType("Error")).toInstance()));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[191]++;

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[71]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[192]++;
          break;

        case Token.RETURN:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[72]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[193]++;
int CodeCoverConditionCoverageHelper_C39;
          if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((n.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[73]++;
            addAction(new VariableAssignAction(
                (ConcreteSlot) scope.getOwnSlot(
                    ConcreteFunctionType.RETURN_SLOT_NAME), n.getFirstChild()));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[194]++;

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[74]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[195]++;
          break; default : CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[75]++;
      }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[196]++;

      Collection<Action> actions = getImplicitActions(n);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[197]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((actions != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[76]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[198]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[34]++;


        for (Action action : actions) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[34]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[35]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[36]++;
}
          addAction(action);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[199]++;
        }

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[77]++;}
    }

    /** Adds the given action to the scope (in non-externs only). */
    private void addAction(Action action) {
      Preconditions.checkState(!inExterns, "Unexpected action in externs.");
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[200]++;
      scope.addAction(action);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[201]++;
    }

    /** Adds the given action to the scope (in non-externs only). */
    private void addActions(List<Action> actions) {
      Preconditions.checkState(!inExterns, "Unexpected action in externs.");
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[202]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[203]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[37]++;


      for (Action action : actions) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[37]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[38]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[39]++;
}
        scope.addAction(action);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[204]++;
      }
    }

    /**
     * Returns an action for assigning the right-hand-side to the left or null
     * if this assignment should be ignored.
     */
    private List<Action> createAssignmentActions(
        Node lhs, Node rhs, Node parent) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[205]++;
      switch (lhs.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[78]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[206]++;
          ConcreteSlot var = (ConcreteSlot) scope.getSlot(lhs.getString());
          Preconditions.checkState(var != null,
              "Type tightener could not find variable with name %s",
              lhs.getString());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[207]++;
          return Lists.<Action>newArrayList(
              new VariableAssignAction(var, rhs));

        case Token.GETPROP:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[79]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[208]++;
          Node receiver = lhs.getFirstChild();
          return Lists.<Action>newArrayList(
              new PropertyAssignAction(receiver, rhs));

        case Token.GETELEM:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[80]++;
          return Lists.newArrayList();

        default:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[81]++;
          throw new AssertionError(
              "Bad LHS for assignment: " + parent.toStringTree());
      }
    }

    private ExternFunctionCall createExternFunctionCall(
        Node receiver, JSType jsThisType, FunctionType fun) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[209]++;
      List<ConcreteType> argTypes = Lists.newArrayList();
      ConcreteType thisType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[210]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((fun != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[82]++;
        thisType = createType(jsThisType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[211]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[212]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[40]++;


        for (Node arg : fun.getParameters()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[40]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[41]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[42]++;
}
          argTypes.add(createType(arg, scope));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[213]++;
        }

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[83]++;
        thisType = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[214]++;
      }
      return new ExternFunctionCall(receiver, thisType, argTypes);
    }

    private JSType getJSType(Node n) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[215]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((n.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[84]++;
        return n.getJSType();

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[85]++;
        return getTypeRegistry().getNativeType(UNKNOWN_TYPE);
      }
    }

    /**
     * Returns any actions that are implicit in the given code.  This can return
     * null instead of an empty collection if none are found.
     */
    private Collection<Action> getImplicitActions(Node n) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[216]++;
      switch (n.getType()) {
        case Token.CALL:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[86]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[217]++;
          // Functions passed to externs functions are considered called.
          // E.g. window.setTimeout(callback, 100);
          // TODO(user): support global extern function calls if necessary
          // TODO(user): handle addEventListener for the case of an object
          //     implementing the EventListener interface.
          Node receiver = n.getFirstChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[218]++;
int CodeCoverConditionCoverageHelper_C43;
          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((receiver.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[87]++;
            return getImplicitActionsFromCall(n, receiver.getJSType());

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[88]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[219]++;
          break;

        case Token.ASSIGN:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[89]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[220]++;
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[221]++;
int CodeCoverConditionCoverageHelper_C44;
          // Functions assigned to externs properties are considered called.
          // E.g. element.onclick = function handle(evt) {};
          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[90]++;
            return getImplicitActionsFromProp(lhs.getFirstChild().getJSType(),
                lhs.getLastChild().getString(), n.getLastChild());

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[91]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[222]++;
          break; default : CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[92]++;
      }
      return null;
    }

    private Collection<Action> getImplicitActionsFromCall(
        Node n, JSType recvType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[223]++;
      Node receiver = n.getFirstChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[224]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((recvType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[93]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[225]++;
        List<Action> actions = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[226]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[43]++;


        for (JSType alt : recvType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[43]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[44]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[45]++;
}
          actions.addAll(getImplicitActionsFromCall(n, alt));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[227]++;
        }
        return actions;

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[94]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[228]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((recvType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[95]++;
        return Lists.<Action>newArrayList();

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[96]++;}
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[229]++;

      ObjectType objType = ObjectType.cast(
          getJSType(receiver.getFirstChild())
          .restrictByNotNullOrUndefined());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[230]++;
      String prop = receiver.getLastChild().getString();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[231]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (32)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((objType.isPropertyInExterns(prop)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 (((recvType.toMaybeFunctionType()).getParameters() != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 3) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 3) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[97]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[232]++;
        List<Action> actions = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[233]++;

        // Look for a function type in the argument list.
        Iterator<Node> paramIter =
            (recvType.toMaybeFunctionType()).getParameters().iterator();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[234]++;
        Iterator<Node> argumentIter = n.children().iterator();
        argumentIter.next();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[235]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[236]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[46]++;


int CodeCoverConditionCoverageHelper_C48; // Skip the function name.
        while ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((paramIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((argumentIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[46]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[47]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[48]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[237]++;
          Node arg = argumentIter.next();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[238]++;
          Node param = paramIter.next();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[239]++;
int CodeCoverConditionCoverageHelper_C49;
          if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((arg.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((arg.getJSType().isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[99]++;
            actions.addAll(getImplicitActionsFromArgument(
                arg,
                arg.getJSType().toMaybeFunctionType().getTypeOfThis()
                    .toObjectType(),
                param.getJSType()));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[240]++;

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[100]++;}
        }
        return actions;

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[98]++;}
      return Lists.<Action>newArrayList();
    }

    private Collection<Action> getImplicitActionsFromArgument(
        Node arg, ObjectType thisType, JSType paramType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[241]++;
int CodeCoverConditionCoverageHelper_C50;
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((paramType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[101]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[242]++;
        List<Action> actions = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[243]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[49]++;


        for (JSType paramAlt : paramType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[49]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[50]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[51]++;
}
          actions.addAll(
              getImplicitActionsFromArgument(arg, thisType, paramAlt));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[244]++;
        }
        return actions;

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[102]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[245]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((paramType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[103]++;
        return Lists.<Action>newArrayList(createExternFunctionCall(
            arg, thisType, paramType.toMaybeFunctionType()));

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[104]++;
        return Lists.<Action>newArrayList(createExternFunctionCall(
            arg, thisType, null));
      }
}
    }

    private Collection<Action> getImplicitActionsFromProp(
        JSType jsType, String prop, Node fnNode) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[246]++;
      List<Action> actions = Lists.newArrayList();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[247]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((jsType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[105]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[248]++;
        boolean found = false;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[249]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[52]++;


        for (JSType alt : jsType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[52]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[53]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[54]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[250]++;
          ObjectType altObj = ObjectType.cast(alt);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[251]++;
int CodeCoverConditionCoverageHelper_C53;
          if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((altObj != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[107]++;
            actions.addAll(getImplicitActionsFromPropNonUnion(
                  altObj, prop, fnNode));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[252]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[253]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((altObj.hasProperty(prop)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[109]++;
              found = true;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[254]++;

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[110]++;}

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[108]++;}
        }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[255]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((found) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[111]++;
          return actions;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[112]++;}

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[106]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[256]++;
        ObjectType objType = ObjectType.cast(jsType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[257]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (32)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((objType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((objType.hasProperty(prop)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[113]++;
          return getImplicitActionsFromPropNonUnion(objType, prop, fnNode);

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[114]++;}
      }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[258]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[55]++;



      // If we didn't find a type that has the property, then check if there
      // exists a property with this name anywhere in the externs.
      for (ObjectType type :
               getTypeRegistry().getEachReferenceTypeWithProperty(prop)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[55]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[56]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[57]++;
}
        actions.addAll(
            getImplicitActionsFromPropNonUnion(
                  type, prop, fnNode));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[259]++;
      }
      return actions;
    }

    private Collection<Action> getImplicitActionsFromPropNonUnion(
        ObjectType jsType, String prop, Node fnNode) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[260]++;
      JSType propType = jsType.getPropertyType(prop)
          .restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[261]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((jsType.isPropertyInExterns(prop)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((propType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[115]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[262]++;
        ObjectType thisType = jsType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[263]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((jsType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[117]++;
          thisType = thisType.getOwnerFunction().getInstanceType();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[264]++;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[118]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[265]++;
        FunctionType callType = propType.toMaybeFunctionType();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[266]++;
        Action action = createExternFunctionCall(
            fnNode, thisType, callType);
        return Lists.<Action>newArrayList(action);

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[116]++;}
      return Lists.<Action>newArrayList();
    }
  }

  /** Returns a concrete type from the JSType of the given variable. */
  private ConcreteType createType(Node name, ConcreteScope scope) {
    Preconditions.checkNotNull(name);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[267]++;
    Preconditions.checkArgument(name.isName());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[268]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[269]++;
int CodeCoverConditionCoverageHelper_C59;

    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((name.getJSType() == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[119]++;
      return ConcreteType.ALL;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[120]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[270]++;
int CodeCoverConditionCoverageHelper_C60;

    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((name.getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((name.getFirstChild().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[121]++;
      return createConcreteFunction(name.getFirstChild(), scope);

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[122]++;}

    return createType(name.getJSType());
  }

  /** Returns a concrete type from the given JSType. */
  private ConcreteType createType(JSType jsType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[271]++;
int CodeCoverConditionCoverageHelper_C61;
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((jsType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((jsType.isEmptyType()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[123]++;
      return ConcreteType.ALL;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[124]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[272]++;
int CodeCoverConditionCoverageHelper_C62;

    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((jsType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[125]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[273]++;
      ConcreteType type = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[274]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[58]++;


      for (JSType alt : jsType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[58]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[59]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[60]++;
}
        type = type.unionWith(createType(alt));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[275]++;
      }
      return type;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[126]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[276]++;
int CodeCoverConditionCoverageHelper_C63;

    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((jsType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[127]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[277]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((getConcreteFunction(jsType.toMaybeFunctionType()) != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[129]++;
        return getConcreteFunction(jsType.toMaybeFunctionType());

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[130]++;}
      // Since we don't have a declaration, it's not concrete.
      return ConcreteType.ALL;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[128]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[278]++;
int CodeCoverConditionCoverageHelper_C65;

    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((jsType.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[131]++;
      return createConcreteInstance(jsType.toObjectType());

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[132]++;}

    return ConcreteType.NONE;  // Not a reference type.
  }

  /**
   * Returns a concrete type from the given JSType that includes the concrete
   * types for subtypes and implementing types for any interfaces.
   */
  private ConcreteType createTypeWithSubTypes(JSType jsType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[279]++;
    ConcreteType ret = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[280]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((jsType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[133]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[281]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[61]++;


      for (JSType alt : jsType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[61]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[62]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[63]++;
}
        ret = ret.unionWith(createTypeWithSubTypes(alt));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[282]++;
      }

    } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[134]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[283]++;
      ObjectType instType = ObjectType.cast(jsType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[284]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (32)) == 0 || true) &&
 ((instType != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((instType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((instType.getConstructor().isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[135]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[285]++;
        Collection<FunctionType> implementors =
            getTypeRegistry().getDirectImplementors(instType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[286]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[64]++;



        for (FunctionType implementor : implementors) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[64]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[65]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[66]++;
}
          ret = ret.unionWith(createTypeWithSubTypes(
              implementor.getInstanceType()));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[287]++;
        }

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[136]++;
        ret = ret.unionWith(createUnionWithSubTypes(createType(jsType)));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[288]++;
      }
    }
    return ret;
  }

  /** Computes the concrete types that can result from the given expression. */
  ConcreteType inferConcreteType(ConcreteScope scope, Node expr) {
    Preconditions.checkNotNull(scope);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[289]++;
    Preconditions.checkNotNull(expr);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[290]++;
    ConcreteType ret;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[291]++;
    switch (expr.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[137]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[292]++;
        StaticSlot<ConcreteType> slot = scope.getSlot(expr.getString());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[293]++;
int CodeCoverConditionCoverageHelper_C68;

        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((slot != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[138]++;
          ret = slot.getType();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[294]++;

        } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[139]++;
          // This should occur only for extern variables, which we are assuming
          // do not ever get assigned instances of user types.
          ret = ConcreteType.ALL;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[295]++;
        }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[296]++;
        break;

      case Token.THIS:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[140]++;
        ret = scope.getTypeOfThis();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[297]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[298]++;
        break;

      case Token.ASSIGN:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[141]++;
        // Using the right-hand side is more specific since the left hand side
        // is a variable of some sort that can be assigned elsewhere.
        ret = inferConcreteType(scope, expr.getLastChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[299]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[300]++;
        break;

      case Token.COMMA:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[142]++;
        ret = inferConcreteType(scope, expr.getLastChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[301]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[302]++;
        break;

      case Token.AND:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[143]++;
        // Since a reference type is always true, only the right hand side could
        // actually be returned.
        ret = inferConcreteType(scope, expr.getLastChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[303]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[304]++;
        break;

      case Token.OR:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[144]++;
        ret = inferConcreteType(scope, expr.getFirstChild()).unionWith(
                   inferConcreteType(scope, expr.getLastChild()));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[305]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[306]++;
        break;

      case Token.HOOK:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[145]++;
        ret = inferConcreteType(scope,
                   expr.getFirstChild().getNext()).unionWith(
                       inferConcreteType(scope, expr.getLastChild()));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[307]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[308]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[146]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[309]++;
        ConcreteType recvType = inferConcreteType(scope, expr.getFirstChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[310]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((recvType.isAll()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[147]++;
          ret = recvType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[311]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[312]++;
          break;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[148]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[313]++;
        Node prop = expr.getLastChild();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[314]++;
        String propName = prop.getString();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[315]++;
        ConcreteType type = recvType.getPropertyType(propName);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[316]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 (("prototype".equals(propName)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[149]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[317]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[67]++;


          for (ConcreteFunctionType funType : recvType.getFunctions()) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[67]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[68]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[69]++;
}
            type = type.unionWith(funType.getPrototypeType());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[318]++;
          }

        } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[150]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[319]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention()
                   .isSuperClassReference(propName)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[151]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[320]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[70]++;


          for (ConcreteFunctionType superType : recvType.getSuperclassTypes()) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[70]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[71]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[72]++;
}
            type = type.unionWith(superType.getPrototypeType());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[321]++;
          }

        } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[152]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[322]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 (("call".equals(propName)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[153]++;
          type = recvType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[323]++;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[154]++;}
}
}
        ret = type;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[324]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[325]++;
        break;

      case Token.GETELEM:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[155]++;
        ret = ConcreteType.ALL;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[326]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[327]++;
        break;

      case Token.CALL:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[156]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[328]++;
        // TODO(user): Support apply on functions.
        // TODO(user): Create goog.bind that curries some arguments.
        ConcreteType targetType =
            inferConcreteType(scope, expr.getFirstChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[329]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((targetType.isAll()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[157]++;
          ret = targetType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[330]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[331]++;
          break;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[158]++;}
        ret = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[332]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[333]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[73]++;


        for (ConcreteFunctionType funType : targetType.getFunctions()) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[73]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[74]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[75]++;
}
          ret = ret.unionWith(funType.getReturnSlot().getType());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[334]++;
        }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[335]++;
        break;

      case Token.NEW:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[159]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[336]++;
        ConcreteType constructorType =
            inferConcreteType(scope, expr.getFirstChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[337]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((constructorType.isAll()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[160]++;
          throw new AssertionError("Attempted new call on all type!");

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[161]++;}
        ret = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[338]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[339]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[76]++;


        for (ConcreteInstanceType instType
             : constructorType.getFunctionInstanceTypes()) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[76]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[77]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[78]++;
}
          ret = ret.unionWith(instType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[340]++;
        }
        allInstantiatedTypes.add(ret);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[341]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[342]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[162]++;
        ret = createConcreteFunction(expr, scope);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[343]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[344]++;
        break;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[163]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[345]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((expr.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((expr.getJSType().isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[164]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[346]++;
          JSType exprType = expr.getJSType().restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[347]++;
          ConcreteType inst = createConcreteInstance(exprType.toObjectType());
          allInstantiatedTypes.add(inst);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[348]++;
          ret = inst;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[349]++;

        } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[165]++;
          ret = ConcreteType.ALL;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[350]++;
        }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[351]++;
        break;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[166]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[352]++;
        ObjectType arrayType = (ObjectType) getTypeRegistry()
            .getNativeType(JSTypeNative.ARRAY_TYPE);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[353]++;
        ConcreteInstanceType inst = createConcreteInstance(arrayType);
        allInstantiatedTypes.add(inst);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[354]++;
        ret = inst;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[355]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[356]++;
        break;

      default:
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[167]++;
        ret = ConcreteType.NONE;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[357]++;
    }
    return createTypeIntersection(ret, expr.getJSType());
  }

  private ConcreteType createTypeIntersection(
      ConcreteType concreteType, JSType jsType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[358]++;
    // TODO(johnlenz): Even with memoizing all the time of this pass is still
    // spent in this function (due to invalidation caused by changes to
    // allInstantiatedTypes), specifically calls to ConcreteUnionType.unionWith
    ConcreteJSTypePair key = new ConcreteJSTypePair(concreteType, jsType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[359]++;
    ConcreteType ret = typeIntersectionMemos.get(key);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[360]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((ret != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[168]++;
      return ret;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[169]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[361]++;
int CodeCoverConditionCoverageHelper_C77;

    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (32)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((jsType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((concreteType.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 3) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 3) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[170]++;
      ret = concreteType;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[362]++;

    } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[171]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[363]++;
int CodeCoverConditionCoverageHelper_C78; if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((concreteType.isUnion()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((concreteType.isSingleton()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[172]++;
      ret = concreteType.intersectWith(createTypeWithSubTypes(jsType));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[364]++;

    } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[173]++;
      Preconditions.checkState(concreteType.isAll());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[365]++;
      ret = createTypeWithSubTypes(jsType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[366]++;
    }
}
    ret = ret.intersectWith(ConcreteType.createForTypes(allInstantiatedTypes));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[367]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[368]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[79]++;



    // Keep all function types, as restricting to instantiated types will only
    // keep instance types.
    // TODO(user): only keep functions that match the JS type.
    for (ConcreteFunctionType functionType : concreteType.getFunctions()) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[79]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[80]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[81]++;
}
      ret = ret.unionWith(functionType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[369]++;
    }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[370]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[82]++;



    // The prototype type is special as it should only appear from a direct
    // reference to Foo.prototype, and not via a type cast, thus, do not filter
    // them out.  We do not include them in the list of instantiated types.
    for (ConcreteInstanceType prototype : concreteType.getPrototypeTypes()) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[82]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[83]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[84]++;
}
      ret = ret.unionWith(prototype);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[371]++;
    }
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[372]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[85]++;



    // Anonymous object types and enums will get removed in the createForTypes
    // call, so add them back in as well.
    for (ConcreteInstanceType instance : concreteType.getInstances()) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[85]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[86]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[87]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[373]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((instance.instanceType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((instance.isFunctionPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[174]++;
        ret = ret.unionWith(instance);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[374]++;

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[175]++;}
    }

    typeIntersectionMemos.put(key, ret);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[375]++;
    return ret;
  }

  @Override
  public ConcreteFunctionType createConcreteFunction(
      Node decl, StaticScope<ConcreteType> parent) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[376]++;
    ConcreteFunctionType funType = functionFromDeclaration.get(decl);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[377]++;
int CodeCoverConditionCoverageHelper_C80;
    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((funType == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[176]++;
      functionFromDeclaration.put(decl,
          funType = new ConcreteFunctionType(this, decl, parent));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[378]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[379]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((decl.getJSType() != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[178]++;
        functionFromJSType.put(decl.getJSType().toMaybeFunctionType(), funType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[380]++;

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[179]++;}

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[177]++;}
    return funType;
  }

  @Override
  public ConcreteInstanceType createConcreteInstance(ObjectType instanceType) {
    // This should be an instance or function prototype object, not a function.
    Preconditions.checkArgument(
        !instanceType.isFunctionType() ||
        instanceType == getTypeRegistry().getNativeType(U2U_CONSTRUCTOR_TYPE));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[381]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[382]++;
    ConcreteInstanceType instType = instanceFromJSType.get(instanceType);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[383]++;
int CodeCoverConditionCoverageHelper_C82;
    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((instType == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[180]++;
      instanceFromJSType.put(instanceType,
          instType = new ConcreteInstanceType(this, instanceType));
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[384]++;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[181]++;}
    return instType;
  }

  /** Returns the (already created) function with the given declaration. */
  ConcreteFunctionType getConcreteFunction(Node decl) {
    return functionFromDeclaration.get(decl);
  }

  /** Returns the function (if any) for the given node. */
  @Override
  public ConcreteFunctionType getConcreteFunction(FunctionType functionType) {
    return functionFromJSType.get(functionType);
  }

  /** Returns the function (if any) for the given node. */
  @Override
  public ConcreteInstanceType getConcreteInstance(ObjectType instanceType) {
    return instanceFromJSType.get(instanceType);
  }

  @Override
  public StaticScope<ConcreteType> createFunctionScope(
      Node decl, StaticScope<ConcreteType> parent) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[385]++;
    ConcreteScope scope = new ConcreteScope((ConcreteScope) parent);
    scope.declareSlot(ConcreteFunctionType.CALL_SLOT_NAME, decl);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[386]++;
    scope.declareSlot(ConcreteFunctionType.THIS_SLOT_NAME, decl);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[387]++;
    scope.declareSlot(ConcreteFunctionType.RETURN_SLOT_NAME, decl);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[388]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[389]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[88]++;


int CodeCoverConditionCoverageHelper_C83;
    for (Node n = decl.getFirstChild().getNext().getFirstChild();(((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false);
         n = n.getNext()) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[88]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[89]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[90]++;
}
      scope.declareSlot(n.getString(), n);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[390]++;
    }
    // TODO(user): Create an 'arguments' variable that returns the union
    //     of the concrete types of all parameters.
    scope.initForScopeRoot(decl.getLastChild());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[391]++;
    return scope;
  }

  @Override
  public StaticScope<ConcreteType> createInstanceScope(
      ObjectType instanceType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[392]++;
    ConcreteScope parentScope = null;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[393]++;
    ObjectType implicitProto = instanceType.getImplicitPrototype();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[394]++;
int CodeCoverConditionCoverageHelper_C84;
    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((implicitProto != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((implicitProto.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[182]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[395]++;
      ConcreteInstanceType prototype = createConcreteInstance(implicitProto);
      parentScope = (ConcreteScope) prototype.getScope();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[396]++;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[183]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[397]++;
    ConcreteScope scope = new ConcreteScope(parentScope);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[398]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[91]++;


    for (String propName : instanceType.getOwnPropertyNames()) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[91]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[92]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[93]++;
}
      scope.declareSlot(propName, null);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[399]++;
    }
    return scope;
  }

  /**
   * Returns a ConcreteType that is the union of the given type and all of its
   * subtypes.  This assumes that the passed in type is an instance type,
   * otherwise an empty set is returned. The returned set will be instance
   * types.
   */
  ConcreteType createUnionWithSubTypes(ConcreteType type) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[400]++;
    Set<ConcreteType> set = null;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[401]++;
int CodeCoverConditionCoverageHelper_C85;
    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((type.isInstance()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[184]++;
      set = getSubTypes(type.toInstance());
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[402]++;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[185]++;}

    return ConcreteType.createForTypes(set).unionWith(type);
  }

  /** Returns the set of subtypes of the given type. */
  private Set<ConcreteType> getSubTypes(ConcreteInstanceType type) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[403]++;
int CodeCoverConditionCoverageHelper_C86;
    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((type.getConstructorType() == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[186]++;
      return null;

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[187]++;}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[404]++;

    Set<ConcreteType> set = Sets.newHashSet();
    getSubTypes(type.getConstructorType().getJSType(), set);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[405]++;
    return set;
  }

  /**
   * Adds all subtypes of the given type to the provided set.
   * @return false if the all type was encountered, else true.
   */
  private boolean getSubTypes(FunctionType type, Set<ConcreteType> set) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[406]++;
int CodeCoverConditionCoverageHelper_C87;
    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((type.getSubTypes() != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[188]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[407]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[94]++;


      for (FunctionType sub : type.getSubTypes()) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[94]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[95]--;
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.loops[96]++;
}
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[408]++;
        ConcreteType concrete = createType(sub);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[409]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((concrete.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((concrete.toFunction().getInstanceType() != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[190]++;
          concrete = concrete.toFunction().getInstanceType();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[410]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[411]++;
int CodeCoverConditionCoverageHelper_C89;
          if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((set.contains(concrete)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[192]++;
            set.add(concrete);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[412]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[413]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((getSubTypes(sub, set)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[194]++;
              return false;

            } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[195]++;}

          } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[193]++;}

        } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[191]++;
          // The only time we should find a subtype that doesn't have an
          // instance type is for the odd case of ActiveXObject, which is
          // of the NoObject type and will be returned as a subtype of Object.
          set.clear();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[414]++;
          set.add(ConcreteType.ALL);
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[415]++;
          return false;
        }
      }

    } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[189]++;}
    return true;
  }

  /**
   * A simple class used to pair a concrete type and a JS type.  Used to
   * memoize the results of a "createTypeIntersection" call.
   */
  static class ConcreteJSTypePair {
    final ConcreteType concrete;
    final JSType jstype;
    final int hashcode;

    ConcreteJSTypePair(ConcreteType concrete, JSType jstype) {
      this.concrete = concrete;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[416]++;
      this.jstype = jstype;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[417]++;
      this.hashcode = concrete.hashCode() + getJSTypeHashCode();
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[418]++;
    }

    private int getJSTypeHashCode() {
      return jstype != null ? jstype.hashCode() : 0;
    }

    private boolean equalsJSType(JSType jsType) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[419]++;
int CodeCoverConditionCoverageHelper_C91;
      if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((jstype == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[196]++;
        return jstype == jsType;

      } else {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[197]++;
        return jsType.equals(this.jstype);
      }
    }

    @Override
    public boolean equals(Object o) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[420]++;
int CodeCoverConditionCoverageHelper_C92;
      if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((o instanceof ConcreteJSTypePair) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[198]++;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[421]++;
        ConcreteJSTypePair pair = (ConcreteJSTypePair) o;
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.statements[422]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((pair.concrete.equals(this.concrete)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((equalsJSType(pair.jstype)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[200]++;
          return true;

        } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[201]++;}

      } else {
  CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d.branches[199]++;}
      return false;
    }

    @Override
    public int hashCode() {
      return hashcode;
    }
  }
}

class CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d ());
  }
    public static long[] statements = new long[423];
    public static long[] branches = new long[202];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[94];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.TightenTypes.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,2,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,2,2,1,1,3,2,2,1,1,1,1,1,1,3,2,1,1,2,2,1,1,1,1,1,3,1,1,1,1,1,1,1,2,1,3,2,2,1,1,1,1,2,1,1,1,2,1,1,2,1,2};
    for (int i = 1; i <= 93; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[97];

  public CodeCoverCoverageCounter$zjbuef118iaagmvg3ga9756p9d () {
    super("com.google.javascript.jscomp.TightenTypes.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 422; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 201; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 93; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 96; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.TightenTypes.java");
      for (int i = 1; i <= 422; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 201; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 93; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 32; i++) {
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

