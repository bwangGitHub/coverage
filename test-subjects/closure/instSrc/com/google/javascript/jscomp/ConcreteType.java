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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.graph.LatticeElement;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticScope;
import com.google.javascript.rhino.jstype.StaticSlot;
import com.google.javascript.rhino.jstype.UnknownType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Represents a reference type for which the exact definition in the source is
 * known.  Unlike a {@code JSType} reference type, a concrete instance type of A
 * indicates that an instance of A -- not a subclass of A -- is a possible
 * value.  Other concrete types are functions (whose definitions are known),
 * arrays containing concrete types, and unions of concrete types.
 *
 * These types are computed by {@code TightenTypes}.
 *
 */
abstract class ConcreteType implements LatticeElement {
  static {
    CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.ping();
  }

  /** Static instance of the empty set of concrete types. */
  static final ConcreteType NONE = new ConcreteNoneType();
  static {
    CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[1]++;
  }

  /** Static instance of the set of all concrete types. */
  static final ConcreteType ALL = new ConcreteAll();
  static {
    CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[2]++;
  }

  /** Constant empty list of function types. */
  private static final List<ConcreteFunctionType> NO_FUNCTIONS =
      Lists.<ConcreteFunctionType>newArrayList();
  static {
    CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[3]++;
  }

  /** Constant empty list of instance types. */
  private static final List<ConcreteInstanceType> NO_INSTANCES =
      Lists.<ConcreteInstanceType>newArrayList();
  static {
    CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[4]++;
  }

  /** Constant empty list of slots. */
  private static final List<StaticSlot<ConcreteType>> NO_SLOTS =
      Lists.<StaticSlot<ConcreteType>>newArrayList();
  static {
    CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[5]++;
  }

  protected static ConcreteType createForTypes(Collection<ConcreteType> types) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((types == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((types.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[1]++;
      return NONE;

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[2]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[7]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((types.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[3]++;
      return types.iterator().next();

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[4]++;
      return new ConcreteUnionType(Sets.newHashSet(types));
    }
}
  }

  /** Indicates whether this is an empty set of types. */
  boolean isNone() { return false; }

  /** Indicates whether this type is a function. */
  boolean isFunction() { return false; }

  /**
   * Indicates whether this type is an instance of some type (or a prototype
   * instance of a type).
   * */
  boolean isInstance() { return false; }

  /** Indicates whether this type is a union of concrete types. */
  boolean isUnion() { return false; }

  /** Indicates whether this type is the set of all types. */
  boolean isAll() { return false; }

  /** Indicates whether this represents exactly one type. */
  boolean isSingleton() { return !isNone() && !isUnion() && !isAll(); }

  /** Returns this as a function, if it is one, or null, if not. */
  ConcreteFunctionType toFunction() { return null; }

  /** Returns this as an instance, if it is one, or null, if not. */
  ConcreteInstanceType toInstance() { return null; }

  /** Returns this as a union, if it is one, or null, if not. */
  ConcreteUnionType toUnion() { return null; }

  /** Returns the scope for the type, or null if not applicable. */
  StaticScope<ConcreteType> getScope() { return null; }

  /** Returns the union of this type with the given one. */
  ConcreteType unionWith(ConcreteType other) {
    Preconditions.checkState(this.isSingleton());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[8]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;  // Sets must override.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((other.isSingleton()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[5]++;
      return other.unionWith(this);

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[6]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[10]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((equals(other)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[7]++;
      return this;

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[8]++;
      return new ConcreteUnionType(this, other);
    }
}
  }

  /** Returns the intersection of this type with the given one. */
  ConcreteType intersectWith(ConcreteType other) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((other.isSingleton()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[9]++;
      return other.intersectWith(this);

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[10]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[12]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((equals(other)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[11]++;
      return this;

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[12]++;
      return NONE;
    }
}
  }

  /**
   * Calls {@code filter()} on each type, adding it to the returned list if it
   * is not null.
   */
  private <C> List<C> getMatchingTypes(TypeFilter<C> filter) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[13]++;
    C type = null;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[14]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isUnion()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[13]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[15]++;
      List<C> list = Lists.newArrayList();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[1]++;


      for (ConcreteType alt : toUnion().getAlternatives()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[1]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[2]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[3]++;
}
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[17]++;
        if ((type = filter.filter(alt)) != null) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[15]++;
          list.add(type);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[18]++;

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[16]++;}
      }
      return list;

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[14]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[19]++; if ((type = filter.filter(this)) != null) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[17]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[20]++;
      List<C> list = Lists.newArrayList();
      list.add(type);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[21]++;
      return list;

    } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[18]++;
      return filter.emptyList;
    }
}
  }

  /**
   * Provides one function to filter an input, either returning the filtered
   * version of the input, or null if the input does not have a corresponding
   * output.
   */
  abstract class TypeFilter<C> {
    /** The empty list for a caller to use if there are no non-null outputs. */
    final List<C> emptyList;

    TypeFilter(List<C> emptyList) {
      this.emptyList = emptyList;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[22]++;
    }

    abstract protected C filter(ConcreteType type);
  }

  /** Returns all function types in this set. */
  List<ConcreteFunctionType> getFunctions() {
    return getMatchingTypes(new TypeFilter<ConcreteFunctionType>(NO_FUNCTIONS) {
      @Override public ConcreteFunctionType filter(ConcreteType type) {
        return type.isFunction() ? type.toFunction() : null;
      }
    });
  }

  /** Returns all instance types in this set. */
  List<ConcreteInstanceType> getInstances() {
    return getMatchingTypes(new TypeFilter<ConcreteInstanceType>(NO_INSTANCES) {
      @Override public ConcreteInstanceType filter(ConcreteType type) {
        return type.isInstance() ? type.toInstance() : null;
      }
    });
  }

  /** Returns the (non-null) instance types of all functions in this set. */
  List<ConcreteInstanceType> getFunctionInstanceTypes() {
    return getMatchingTypes(new TypeFilter<ConcreteInstanceType>(NO_INSTANCES) {
      @Override public ConcreteInstanceType filter(ConcreteType type) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[19]++;
          return type.toFunction().getInstanceType();

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[20]++;}
        return null;
      }
    });
  }

  /** Returns all (non-null) function prototype types in this set. */
  List<ConcreteInstanceType> getPrototypeTypes() {
    return getMatchingTypes(new TypeFilter<ConcreteInstanceType>(NO_INSTANCES) {
      @Override public ConcreteInstanceType filter(ConcreteType type) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[24]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((type.isInstance()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type.toInstance().isFunctionPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[21]++;
          return type.toInstance();

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[22]++;}
        return null;
      }
    });
  }

  /** Returns the (non-null) superclasses of all functions in this set. */
  List<ConcreteFunctionType> getSuperclassTypes() {
    return getMatchingTypes(new TypeFilter<ConcreteFunctionType>(NO_FUNCTIONS) {
      @Override public ConcreteFunctionType filter(ConcreteType type) {
        return type.isFunction()
          && type.toFunction().getSuperclassType() != null
          ? type.toFunction().getSuperclassType() : null;
      }
    });
  }

  /** Returns the (non-null) index-th parameters of functions in this set. */
  List<StaticSlot<ConcreteType>> getParameterSlots(final int index) {
    return getMatchingTypes(new TypeFilter<StaticSlot<ConcreteType>>(NO_SLOTS) {
      @Override public StaticSlot<ConcreteType> filter(ConcreteType type) {
        return type.isFunction()
            && toFunction().getParameterSlot(index) != null
            ? toFunction().getParameterSlot(index) : null;
      }
    });
  }

  /**
   * Returns the (non-null) slots for properties with the given name in all
   * instance types in this set.
   */
  List<StaticSlot<ConcreteType>> getPropertySlots(final String name) {
    return getMatchingTypes(new TypeFilter<StaticSlot<ConcreteType>>(NO_SLOTS) {
      @Override public StaticSlot<ConcreteType> filter(ConcreteType type) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[25]++;
        StaticSlot<ConcreteType> slot = null;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[26]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((type.isInstance()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[23]++;
          slot = type.toInstance().getPropertySlot(name);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[27]++;

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[24]++;}
        return slot;
      }
    });
  }

  /**
   * Returns the concrete type for the given property from the given type.
   * If the given type is a union type, returns the union of types for the slots
   * of the property.
   */
  ConcreteType getPropertyType(final String name) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[28]++;
    ConcreteType ret = NONE;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[29]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[4]++;


    for (StaticSlot<ConcreteType> slot : getPropertySlots(name)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[4]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[5]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[6]++;
}
      ret = ret.unionWith(slot.getType());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[30]++;
    }
    return ret;
  }

  /** Implements the empty set of types. */
  private static class ConcreteNoneType extends ConcreteType {
    @Override boolean isNone() { return true; }

    @Override ConcreteType unionWith(ConcreteType other) { return other; }

    @Override ConcreteType intersectWith(ConcreteType other) { return NONE; }

    @Override public String toString() { return "()"; }
  }

  /**
   * Represents a specific function in the source code.  Note that we assume the
   * factory creates only a single instance of this class for a given
   * declaration, so we do not need to override {@code Object.equals}.
   *
   * {@code bodyScope} contains a slot for each local variable in the function
   * body's scope as well as special slots to keep track of whether the
   * function is called, the this type, and the return type.
   */
  static class ConcreteFunctionType extends ConcreteType {
    /** Name used for the call slot (see {@code getCallSlot}). */
    static final String CALL_SLOT_NAME = ":call";

    /** Name used for the this slot (see {@code getThisSlot}). */
    static final String THIS_SLOT_NAME = ":this";

    /** Name used for the return slot (see {@code getReturnSlot}). */
    static final String RETURN_SLOT_NAME = ":return";

    private final Factory factory;
    private final Node declaration;
    private final StaticScope<ConcreteType> parentScope;
    private StaticScope<ConcreteType> bodyScope;
    private ConcreteInstanceType instanceType;
    private ConcreteInstanceType prototypeType;

    ConcreteFunctionType(Factory factory,
                         Node declaration,
                         StaticScope<ConcreteType> parentScope) {
      this.factory = factory;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[31]++;
      this.declaration = declaration;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[32]++;
      this.parentScope = parentScope;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[33]++;

      Preconditions.checkArgument(declaration.isFunction());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[34]++;
      Preconditions.checkArgument(declaration.getJSType() != null);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[35]++;
      Preconditions.checkArgument(declaration.getJSType().isFunctionType());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[36]++;
    }

    @Override boolean isFunction() { return true; }

    @Override ConcreteFunctionType toFunction() { return this; }

    /**
     * Returns the slot representing that a call to it occurred.  This is
     * assigned a type if the function is called.  This ensures that the body of
     * the function is processed even if it has no arguments or if the arguments
     * do not take any concrete types as arguments.
     */
    StaticSlot<ConcreteType> getCallSlot() {
      return getScope().getOwnSlot(CALL_SLOT_NAME);
    }

    /** Returns the slot representing the value of 'this' in the body. */
    StaticSlot<ConcreteType> getThisSlot() {
      return getScope().getOwnSlot(THIS_SLOT_NAME);
    }

    /** Returns the slot representing the values returned. */
    StaticSlot<ConcreteType> getReturnSlot() {
      return getScope().getOwnSlot(RETURN_SLOT_NAME);
    }

    /** Returns the slot representing the index-th parameter. */
    StaticSlot<ConcreteType> getParameterSlot(int index) {
      return getScope().getOwnSlot(getParameterName(index));
    }

    /** Returns the name for the index-th parameter within the function. */
    private String getParameterName(int index) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[37]++;
      int count = 0;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;
      for (Node n = getFirstParameter();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[7]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[8]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[9]++;
}
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((count++ == index) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[25]++;
          return n.getString();

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[26]++;}
      }
      return null;
    }

    /** Returns the node containing the first parameter's name. */
    private Node getFirstParameter() {
      return declaration.getFirstChild().getNext().getFirstChild();
    }

    /** Returns the JSType of this function. */
    public FunctionType getJSType() {
      return JSType.toMaybeFunctionType(declaration.getJSType());
    }

    /**
     * Returns the concrete type representing instances of this type or null if
     * it has none.
     */
    ConcreteInstanceType getInstanceType() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((instanceType == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[27]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[41]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((getJSType().isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[29]++;
          instanceType =
              factory.createConcreteInstance(getJSType().getInstanceType());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[42]++;

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[30]++;}

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[28]++;}
      return instanceType;
    }

    /** Returns the concrete type representing the prototype of this type. */
    ConcreteInstanceType getPrototypeType() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[43]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((prototypeType == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[31]++;
        prototypeType =
            factory.createConcreteInstance(getJSType().getPrototype());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[44]++;

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[32]++;}
      return prototypeType;
    }

    /** Returns the type of the superclass (or null if none exists). */
    ConcreteFunctionType getSuperclassType() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[45]++;
      FunctionType superConstructor = getJSType().getSuperClassConstructor();
      return (superConstructor != null)
          ? factory.getConcreteFunction(superConstructor) : null;
    }

    /** Returns the scope for the body of this function. */
    @Override StaticScope<ConcreteType> getScope() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[46]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((bodyScope == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[33]++;
        bodyScope = factory.createFunctionScope(declaration, parentScope);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[47]++;

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[34]++;}
      return bodyScope;
    }

    /**
     * Informally, a function is represented by
     * {@code function (params): returnType} where the {@code params} is a comma
     * separated list of types, the first one being a special
     * {@code this:T} if the function expects a known type for {@code this}.
     */
    @Override public String toString() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[48]++;
      StringBuilder b = new StringBuilder(32);
      b.append("function (");
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[49]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[50]++;
      boolean hasKnownTypeOfThis = !getThisSlot().getType().isNone();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[51]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((hasKnownTypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[35]++;
        b.append("this:");
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[52]++;
        b.append(getThisSlot().getType().toString());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[53]++;

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[36]++;}
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[54]++;

      Node n = getFirstParameter();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[55]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((hasKnownTypeOfThis) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[37]++;
        b.append(", ");
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[56]++;

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[38]++;}
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[57]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[10]++;


int CodeCoverConditionCoverageHelper_C21;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); ++i, n = n.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[10]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[11]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[12]++;
}
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[58]++;
        String paramName = n.getString();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[59]++;
        StaticSlot<ConcreteType> var = getScope().getOwnSlot(paramName);
        b.append(var.getType());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[60]++;
        getParameterSlot(i).getType();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[61]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[62]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[39]++;
          b.append(", ");
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[63]++;

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[40]++;}
      }

      b.append(")");
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[64]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[65]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getReturnSlot().getType() != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[41]++;
        b.append(": ");
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[66]++;
        b.append(getReturnSlot().getType().toString());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[67]++;

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[42]++;}
      return b.toString();
    }
  }

  /**
   * Represents a specific constructor in the source code.  Note that we assume
   * the factory creates only a single instance of this class for a given
   * declaration, so we do not need to override {@code Object.equals}.
   *
   * The {@code StaticScope} contains a slot for each property defined on the
   * instance type and the scope parent chain follows the prototype chain
   * hierarchy.
   */
  static class ConcreteInstanceType extends ConcreteType {
    /** Factory for creating types and scopes. */
    private final Factory factory;

    /** Stores the normal type information for this instance. */
    public final ObjectType instanceType;

    /** The type information for the implicit prototype of this type, if any. */
    private ConcreteInstanceType prototype;

    /**
     * A scope containing the properties of this instance, created on demand.
     * Its parent scope corresponds to the scope of the implicit prototype.
     */
    private StaticScope<ConcreteType> scope;

    ConcreteInstanceType(Factory factory, ObjectType instanceType) {
      this.factory = factory;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[68]++;
      this.instanceType = instanceType;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[69]++;

      Preconditions.checkArgument(!(instanceType instanceof UnknownType));
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[70]++;
    }

    @Override boolean isInstance() { return true; }

    @Override ConcreteInstanceType toInstance() { return this; }

    /** Determines whether this is a function prototype type. */
    boolean isFunctionPrototype() {
      return instanceType.isFunctionPrototypeType();
    }

    /** Returns the slot representing the property with the given name. */
    StaticSlot<ConcreteType> getPropertySlot(String propName) {
      return getScope().getSlot(propName);
    }

    /**
     * Returns the closest instance type in the prototype chain that contains
     * the given property.
     */
    ConcreteInstanceType getInstanceTypeWithProperty(String propName) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[71]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((getScope().getOwnSlot(propName) != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[43]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[72]++;
int CodeCoverConditionCoverageHelper_C25;
        // Normalize the instance type into the prototype, to be as
        // consistent as possible with non-type tightened behavior.
        //
        // TODO(nicksantos|user): There's a larger issue here.
        // When JSCompiler infers property types on instance types,
        // that means that someone is just assigning a property
        // without declaring it. In this case, we can't meaningfully
        // tell when the property is being pulled off the subtype
        // vs. when it's being pulled off the supertype.  So we should
        // probably invalidate properties of this sort.
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((instanceType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[45]++;
          return getConstructorType().getPrototypeType();

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[46]++;}
        return this;

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[44]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[73]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((getImplicitPrototype() != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[47]++;
        return getImplicitPrototype().getInstanceTypeWithProperty(propName);

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[48]++;
        return null;
      }
}
    }

    /** Returns the type representing the implicit prototype. */
    ConcreteInstanceType getImplicitPrototype() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[74]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((prototype == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((instanceType.getImplicitPrototype() != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[49]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[75]++;
        ObjectType proto = instanceType.getImplicitPrototype();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[76]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((proto != instanceType) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((proto instanceof UnknownType) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[51]++;
          prototype = factory.createConcreteInstance(proto);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[77]++;

        } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[52]++;}

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[50]++;}
      return prototype;
    }

    /** Returns the type of the constructor or null if this has none. */
    ConcreteFunctionType getConstructorType() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[78]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((instanceType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[53]++;
        return factory.getConcreteFunction(instanceType.getOwnerFunction());

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[54]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[79]++;
        FunctionType constructor = instanceType.getConstructor();
        return (constructor != null)
            ? factory.getConcreteFunction(constructor) : null;
      }
    }

    /** Returns the scope of this type in the prototype chain. */
    @Override StaticScope<ConcreteType> getScope() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[80]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[55]++;
        scope = factory.createInstanceScope(instanceType);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[81]++;

      } else {
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[56]++;}
      return scope;
    }

    @Override public String toString() { return instanceType.toString(); }
  }

  /**
   * Represents a finite set of possible alternatives for this type.  Note that
   * we make no effort to merge different array types into one array type, so
   * clients should not assume there is only one array in a set.
   */
  static class ConcreteUnionType extends ConcreteType {
    private final Set<ConcreteType> alternatives;

    ConcreteUnionType(ConcreteType... alternatives) {
      this(Sets.newHashSet(alternatives));
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[82]++;
    }

    ConcreteUnionType(Set<ConcreteType> alternatives) {
      Preconditions.checkArgument(alternatives.size() > 1);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[83]++;
      this.alternatives = alternatives;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[84]++;
    }

    @Override boolean isUnion() { return true; }

    @Override ConcreteUnionType toUnion() { return this; }

    @Override ConcreteType unionWith(ConcreteType other) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[85]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((other.isSingleton()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[57]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[86]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((alternatives.contains(other)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[59]++;
          return this;

        } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[60]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[87]++;
          Set<ConcreteType> alts = Sets.newHashSet(alternatives);
          alts.add(other);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[88]++;
          return new ConcreteUnionType(alts);
        }

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[58]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[89]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((other.isUnion()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[61]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[90]++;
        ConcreteUnionType otherUnion = other.toUnion();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[91]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((alternatives.containsAll(otherUnion.alternatives)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[63]++;
          return this;

        } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[64]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[92]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((otherUnion.alternatives.containsAll(alternatives)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[65]++;
          return otherUnion;

        } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[66]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[93]++;
          Set<ConcreteType> alts = Sets.newHashSet(alternatives);
          alts.addAll(otherUnion.alternatives);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[94]++;
          return new ConcreteUnionType(alts);
        }
}

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[62]++;
        Preconditions.checkArgument(other.isNone() || other.isAll());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[95]++;
        return other.unionWith(this);
      }
}
    }

    @Override ConcreteType intersectWith(ConcreteType other) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[96]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((other.isSingleton()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[67]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[97]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((alternatives.contains(other)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[69]++;
          return other;

        } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[70]++;
          return NONE;
        }

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[68]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[98]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((other.isUnion()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[71]++;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[99]++;
        Set<ConcreteType> types = Sets.newHashSet(alternatives);
        types.retainAll(other.toUnion().alternatives);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[100]++;
        return createForTypes(types);

      } else {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.branches[72]++;
        Preconditions.checkArgument(other.isNone() || other.isAll());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[101]++;
        return other.intersectWith(this);
      }
}
    }

    /** Returns all of the types in this set of alternatives. */
    Set<ConcreteType> getAlternatives() { return alternatives; }

    @Override public boolean equals(Object obj) {
      return (obj instanceof ConcreteUnionType)
             && alternatives.equals(((ConcreteUnionType) obj).alternatives);
    }

    @Override public int hashCode() {
      return alternatives.hashCode() ^ 0x5f6e7d8c;
    }

    @Override public String toString() {
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[102]++;
      List<String> names = Lists.newArrayList();
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[103]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[13]++;


      for (ConcreteType type : alternatives) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[13]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[14]--;
  CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.loops[15]++;
}
        names.add(type.toString());
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[104]++;
      }
      Collections.sort(names);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[105]++;

      return "(" + Joiner.on(",").join(names) + ")";
    }
  }

  /** Implements the set of all concrete types. */
  private static class ConcreteAll extends ConcreteType {
    @Override boolean isAll() { return true; }

    @Override ConcreteType unionWith(ConcreteType other) { return this; }

    @Override ConcreteType intersectWith(ConcreteType other) { return other; }

    @Override public String toString() { return "*"; }
  }

  /**
   * Represents an opaque singleton type that is different from any other.
   * This is used by DisambiguateProperties to rename GETPROP nodes that are
   * never reached in the TightenTypes flow analysis. This helps subsequent
   * passes remove unreferenced properties and functions.  ID passed to the
   * constructor should be unique per-instance as it is used for generating
   * nice, unique, names in {@code toString()}.
   */
  static class ConcreteUniqueType extends ConcreteType {
    private final int id;

    ConcreteUniqueType(int id) {
      this.id = id;
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[106]++;

      Preconditions.checkArgument(id >= 0);
CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx.statements[107]++;
    }

    @Override public boolean equals(Object o) {
      return (o instanceof ConcreteUniqueType)
          && (id == ((ConcreteUniqueType) o).id);
    }

    @Override public int hashCode() {
      return ConcreteUniqueType.class.hashCode() ^ id;
    }

    @Override public String toString() { return "Unique$" + id; }
  }

  /**
   * Factory for function and instance (singleton) types and scopes.  It is
   * important that both function and instance types are singletons because
   * callers may try to create the same one multiple times, and if multiple
   * exist, they will not necessarily all receive the same type information.
   */
  interface Factory {
    /** Returns the singleton concrete type for the given function. */
    ConcreteFunctionType createConcreteFunction(
        Node declaration, StaticScope<ConcreteType> parent);

    /** Returns the singleton concrete type for the given instance type. */
    ConcreteInstanceType createConcreteInstance(ObjectType instanceType);

    /**
     * Returns the already created concrete function type for the given type or
     * null if none exists.
     */
    ConcreteFunctionType getConcreteFunction(FunctionType function);

    /**
     * Returns the already created concrete instance type for the given type or
     * null if none exists.
     */
    ConcreteInstanceType getConcreteInstance(ObjectType instance);

    /**
     * Returns a (nested) scope for the given function.  This will include
     * slots for $call, $return, each parameter, and the slots declared in the
     * body of the function.
     */
    StaticScope<ConcreteType> createFunctionScope(
        Node declaration, StaticScope<ConcreteType> parent);

    /**
     * Returns a scope for the given instance type, nested inside the given
     * scope of the prototype.  This will include slots for each of the
     * properties on our type.
     */
    StaticScope<ConcreteType> createInstanceScope(ObjectType instanceType);

    /** Returns the type registry used by this factory. */
    JSTypeRegistry getTypeRegistry();
  }
}

class CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx ());
  }
    public static long[] statements = new long[108];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ConcreteType.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,0,0,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 38; i++) {
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

  public CodeCoverCoverageCounter$se1bu0gw8gai6rzi2lcg6t1xsx () {
    super("com.google.javascript.jscomp.ConcreteType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 107; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 72; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ConcreteType.java");
      for (int i = 1; i <= 107; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 72; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
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

