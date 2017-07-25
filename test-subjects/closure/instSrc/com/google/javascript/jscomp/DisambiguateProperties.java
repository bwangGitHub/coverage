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

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.AbstractCompiler.LifeCycleStage;
import com.google.javascript.jscomp.ConcreteType.ConcreteFunctionType;
import com.google.javascript.jscomp.ConcreteType.ConcreteInstanceType;
import com.google.javascript.jscomp.ConcreteType.ConcreteUnionType;
import com.google.javascript.jscomp.ConcreteType.ConcreteUniqueType;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.TypeValidator.TypeMismatch;
import com.google.javascript.jscomp.graph.StandardUnionFind;
import com.google.javascript.jscomp.graph.UnionFind;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;
import com.google.javascript.rhino.jstype.StaticScope;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * DisambiguateProperties renames properties to disambiguate between unrelated
 * fields with the same name. Two properties are considered related if they
 * share a definition on their prototype chains, or if they are potentially
 * referenced together via union types.
 *
 * <p> Renamimg only occurs if there are two or more distinct properties with
 * the same name.
 *
 * <p> This pass allows other passes, such as inlining and code removal to take
 * advantage of type information implicitly.
 *
 * <pre>
 *   Foo.a;
 *   Bar.a;
 * </pre>
 *
 * <p> will become
 *
 * <pre>
 *   Foo.a$Foo;
 *   Bar.a$Bar;
 * </pre>
 *
 */
class DisambiguateProperties<T> implements CompilerPass {
  static {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.ping();
  }

  // To prevent the logs from filling up, we cap the number of warnings
  // that we tell the user to fix per-property.
  private static final int MAX_INVALDIATION_WARNINGS_PER_PROPERTY = 10;
  static {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[1]++;
  }

  private static final Logger logger = Logger.getLogger(
      DisambiguateProperties.class.getName());
  static {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[2]++;
  }

  static class Warnings {
    // TODO(user): {1} and {2} are not exactly useful for most people.
    static final DiagnosticType INVALIDATION = DiagnosticType.disabled(
        "JSC_INVALIDATION",
        "Property disambiguator skipping all instances of property {0} "
        + "because of type {1} node {2}. {3}");

    static final DiagnosticType INVALIDATION_ON_TYPE = DiagnosticType.disabled(
        "JSC_INVALIDATION_TYPE",
        "Property disambiguator skipping instances of property {0} "
        + "on type {1}. {2}");
  }

  private final AbstractCompiler compiler;
  private final TypeSystem<T> typeSystem;

  /**
   * Map of a type to all the related errors that invalidated the type
   * for disambiguation. It has be Object because of the generic nature of
   * this pass.
   */
  private Multimap<Object, JSError> invalidationMap;

  /**
   * In practice any large code base will have thousands and thousands of
   * type invalidations, which makes reporting all of the errors useless.
   * However, certain properties are worth specifically guarding because of the
   * large amount of code that can be removed as dead code. This list contains
   * the properties (eg: "toString") that we care about; if any of these
   * properties is invalidated it causes an error.
   */
  private final Map<String, CheckLevel> propertiesToErrorFor;

  private class Property {
    /** The name of the property. */
    final String name;

    /** All types on which the field exists, grouped together if related. */
    private UnionFind<T> types;

    /**
     * A set of types for which renaming this field should be skipped. This
     * list is first filled by fields defined in the externs file.
     */
    Set<T> typesToSkip = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[3]++;
  }

    /**
     * If true, do not rename any instance of this field, as it has been
     * referenced from an unknown type.
     */
    boolean skipRenaming;

    /** Set of nodes for this field that need renaming. */
    Set<Node> renameNodes = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[4]++;
  }

    /**
     * Map from node to the highest type in the prototype chain containing the
     * field for that node. In the case of a union, the type is the highest type
     * of one of the types in the union.
     */
    final Map<Node, T> rootTypes = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[5]++;
  }

    Property(String name) {
      this.name = name;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[6]++;
    }

    /** Returns the types on which this field is referenced. */
    UnionFind<T> getTypes() {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((types == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[1]++;
        types = new StandardUnionFind<T>();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[8]++;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[2]++;}
      return types;
    }

    /**
     * Record that this property is referenced from this type.
     * @return true if the type was recorded for this property, else false,
     *     which would happen if the type was invalidating.
     */
    boolean addType(T type, T top, T relatedType) {
      checkState(!skipRenaming, "Attempt to record skipped property: %s", name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[9]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((typeSystem.isInvalidatingType(top)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[3]++;
        invalidate();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[11]++;
        return false;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[4]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((typeSystem.isTypeToSkip(top)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[5]++;
          addTypeToSkip(top);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[13]++;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[6]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((relatedType == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[7]++;
          getTypes().add(top);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[15]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[8]++;
          getTypes().union(top, relatedType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[16]++;
        }
        typeSystem.recordInterfaces(type, top, this);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[17]++;
        return true;
      }
    }

    /** Records the given type as one to skip for this property. */
    void addTypeToSkip(T type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[1]++;


      for (T skipType : typeSystem.getTypesToSkipForType(type)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[1]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[2]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[3]++;
}
        typesToSkip.add(skipType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[19]++;
        getTypes().union(skipType, type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[20]++;
      }
    }

    /** Invalidates any types related to invalid types. */
    void expandTypesToSkip() {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
      // If we are not going to rename any properties, then we do not need to
      // update the list of invalid types, as they are all invalid.
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((shouldRename()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[9]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[22]++;
        int count = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[4]++;


        while (true) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[4]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[5]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[6]++;
}
          // It should usually only take one time through this do-while.
          checkState(++count < 10, "Stuck in loop expanding types to skip.");
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[24]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[25]++;

          // Make sure that the representative type for each type to skip is
          // marked as being skipped.
          Set<T> rootTypesToSkip = Sets.newHashSet();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[26]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[7]++;


          for (T subType : typesToSkip) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[7]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[8]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[9]++;
}
            rootTypesToSkip.add(types.find(subType));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[27]++;
          }
          typesToSkip.addAll(rootTypesToSkip);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[28]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[29]++;

          Set<T> newTypesToSkip = Sets.newHashSet();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[30]++;
          Set<T> allTypes = types.elements();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[31]++;
          int originalTypesSize = allTypes.size();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[32]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[10]++;


          for (T subType : allTypes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[10]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[11]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[12]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((typesToSkip.contains(subType)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((typesToSkip.contains(types.find(subType))) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[11]++;
              newTypesToSkip.add(subType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[34]++;

            } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[12]++;}
          }
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[35]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[13]++;



          for (T newType : newTypesToSkip) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[13]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[14]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[15]++;
}
            addTypeToSkip(newType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[36]++;
          }
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;

          // If there were not any new types added, we are done here.
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((types.elements().size() == originalTypesSize) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[13]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[38]++;
            break;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[14]++;}
        }

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[10]++;}
    }

    /** Returns true if any instance of this property should be renamed. */
    boolean shouldRename() {
      return !skipRenaming && types != null
          && types.allEquivalenceClasses().size() > 1;
    }

    /**
     * Returns true if this property should be renamed on this type.
     * expandTypesToSkip() should be called before this, if anything has been
     * added to the typesToSkip list.
     */
    boolean shouldRename(T type) {
      return !skipRenaming && !typesToSkip.contains(type);
    }

    /**
     * Invalidates a field from renaming.  Used for field references on an
     * object with unknown type.
     */
    boolean invalidate() {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[39]++;
      boolean changed = !skipRenaming;
      skipRenaming = true;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[40]++;
      types = null;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[41]++;
      return changed;
    }

    /**
     * Schedule the node to potentially be renamed.
     * @param node the node to rename
     * @param type the highest type in the prototype chain for which the
     *     property is defined
     * @return True if type was accepted without invalidation or if the property
     *     was already invalidated.  False if this property was invalidated this
     *     time.
     */
    boolean scheduleRenaming(Node node, T type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((skipRenaming) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[15]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[43]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((typeSystem.isInvalidatingType(type)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[17]++;
          invalidate();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[44]++;
          return false;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[18]++;}
        renameNodes.add(node);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[45]++;
        rootTypes.put(node, type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[46]++;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[16]++;}
      return true;
    }
  }

  private Map<String, Property> properties = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[47]++;
  }

  static DisambiguateProperties<JSType> forJSTypeSystem(
      AbstractCompiler compiler,
      Map<String, CheckLevel> propertiesToErrorFor) {
    return new DisambiguateProperties<JSType>(
        compiler, new JSTypeSystem(compiler), propertiesToErrorFor);
  }

  static DisambiguateProperties<ConcreteType> forConcreteTypeSystem(
      AbstractCompiler compiler, TightenTypes tt,
      Map<String, CheckLevel> propertiesToErrorFor) {
    return new DisambiguateProperties<ConcreteType>(
        compiler, new ConcreteTypeSystem(tt, compiler.getCodingConvention()),
            propertiesToErrorFor);
  }

  /**
   * This constructor should only be called by one of the helper functions
   * above for either the JSType system, or the concrete type system.
   */
  private DisambiguateProperties(AbstractCompiler compiler,
      TypeSystem<T> typeSystem, Map<String, CheckLevel> propertiesToErrorFor) {
    this.compiler = compiler;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[48]++;
    this.typeSystem = typeSystem;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[49]++;
    this.propertiesToErrorFor = propertiesToErrorFor;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[50]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.propertiesToErrorFor.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[19]++;
      this.invalidationMap = LinkedHashMultimap.create();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[52]++;

    } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[20]++;
      this.invalidationMap = null;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[53]++;
    }
  }

  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(
        compiler.getLifeCycleStage() == LifeCycleStage.NORMALIZED);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[54]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[55]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[16]++;


    for (TypeMismatch mis : compiler.getTypeValidator().getMismatches()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[16]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[17]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[18]++;
}
      addInvalidatingType(mis.typeA, mis.src);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[56]++;
      addInvalidatingType(mis.typeB, mis.src);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[57]++;
    }

    NodeTraversal.traverse(compiler, externs, new FindExternProperties());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[58]++;
    NodeTraversal.traverse(compiler, root, new FindRenameableProperties());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[59]++;
    renameProperties();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[60]++;
  }

  private void recordInvalidationError(JSType t, JSError error) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[61]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((t.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[21]++;
      return;

    } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[22]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[62]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((invalidationMap != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[23]++;
      invalidationMap.put(t, error);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[63]++;

    } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[24]++;}
  }

  /**
   * Invalidates the given type, so that no properties on it will be renamed.
   */
  private void addInvalidatingType(JSType type, JSError error) {
    type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[64]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[65]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[25]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[66]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[19]++;


      for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[19]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[20]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[21]++;
}
        addInvalidatingType(alt, error);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[67]++;
      }

    } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[26]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[68]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((type.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[27]++;
      addInvalidatingType(
          type.toMaybeEnumElementType().getPrimitiveType(), error);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[69]++;

    } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[28]++;
      typeSystem.addInvalidatingType(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[70]++;
      recordInvalidationError(type, error);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[71]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[72]++;
      ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[73]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((objType.getImplicitPrototype() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[29]++;
        typeSystem.addInvalidatingType(objType.getImplicitPrototype());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[74]++;
        recordInvalidationError(objType.getImplicitPrototype(), error);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[75]++;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[30]++;}
    }
}
  }


  /** Returns the property for the given name, creating it if necessary. */
  protected Property getProperty(String name) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[76]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((properties.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[31]++;
      properties.put(name, new Property(name));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[77]++;

    } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[32]++;}
    return properties.get(name);
  }

  /** Public for testing. */
  T getTypeWithProperty(String field, T type) {
    return typeSystem.getTypeWithProperty(field, type);
  }

  /** Tracks the current type system scope while traversing. */
  private abstract class AbstractScopingCallback implements ScopedCallback {
    protected final Stack<StaticScope<T>> scopes =
        new Stack<StaticScope<T>>();
  {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[78]++;
  }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      return true;
    }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[33]++;
        scopes.push(typeSystem.getRootScope());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[80]++;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[34]++;
        scopes.push(typeSystem.getFunctionScope(t.getScopeRoot()));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[81]++;
      }
    }

    @Override
    public void exitScope(NodeTraversal t) {
      scopes.pop();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[82]++;
    }

    /** Returns the current scope at this point in the file. */
    protected StaticScope<T> getScope() {
      return scopes.peek();
    }
  }

  /**
   * Finds all properties defined in the externs file and sets them as
   * ineligible for renaming from the type on which they are defined.
   */
  private class FindExternProperties extends AbstractScopingCallback {
    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[83]++;
int CodeCoverConditionCoverageHelper_C19;
      // TODO(johnlenz): Support object-literal property definitions.
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[35]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[84]++;
        String field = n.getLastChild().getString();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[85]++;
        T type = typeSystem.getType(getScope(), n.getFirstChild(), field);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[86]++;
        Property prop = getProperty(field);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[87]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((typeSystem.isInvalidatingType(type)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[37]++;
          prop.invalidate();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[88]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[38]++;
          prop.addTypeToSkip(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[89]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[90]++;

          // If this is a prototype property, then we want to skip assignments
          // to the instance type as well.  These assignments are not usually
          // seen in the extern code itself, so we must handle them here.
          if ((type = typeSystem.getInstanceFromPrototype(type)) != null) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[39]++;
            prop.getTypes().add(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[91]++;
            prop.typesToSkip.add(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[92]++;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[40]++;}
        }

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[36]++;}
    }
  }

  /**
   * Traverses the tree, building a map from field names to Nodes for all
   * fields that can be renamed.
   */
  private class FindRenameableProperties extends AbstractScopingCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[93]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[41]++;
        handleGetProp(t, n);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[94]++;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[42]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[95]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[43]++;
        handleObjectLit(t, n);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[96]++;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[44]++;}
}
    }

    /**
     * Processes a GETPROP node.
     */
    private void handleGetProp(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[97]++;
      String name = n.getLastChild().getString();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[98]++;
      T type = typeSystem.getType(getScope(), n.getFirstChild(), name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[99]++;

      Property prop = getProperty(name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[100]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((prop.scheduleRenaming(n.getLastChild(),
                                 processProperty(t, prop, type, null))) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[45]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[101]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((propertiesToErrorFor.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[47]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[102]++;
          String suggestion = "";
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[103]++;
int CodeCoverConditionCoverageHelper_C26;
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((type instanceof JSType) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[49]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[104]++;
            JSType jsType = (JSType) type;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[105]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((jsType.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((jsType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[51]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[106]++;
int CodeCoverConditionCoverageHelper_C28;
              if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[53]++;
                suggestion = "The \"this\" object is unknown in the function,"+
                    "consider using @this";
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[107]++;

              } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[54]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[108]++;
                String qName = n.getFirstChild().getQualifiedName();
                suggestion = "Consider casting " + qName +
                    " if you know it's type.";
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[109]++;
              }

            } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[52]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[110]++;
              List<String> errors = Lists.newArrayList();
              printErrorLocations(errors, jsType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[111]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[112]++;
int CodeCoverConditionCoverageHelper_C29;
              if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((errors.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[55]++;
                suggestion = "Consider fixing errors for the following types:\n";
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[113]++;
                suggestion += Joiner.on("\n").join(errors);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[114]++;

              } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[56]++;}
            }

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[50]++;}
          compiler.report(JSError.make(
              t.getSourceName(), n, propertiesToErrorFor.get(name),
              Warnings.INVALIDATION, name,
              (type == null ? "null" : type.toString()),
              n.toString(), suggestion));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[115]++;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[48]++;}

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[46]++;}
    }

    /**
     * Processes a OBJECTLIT node.
     */
    private void handleObjectLit(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[116]++;
      Node child = n.getFirstChild();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[117]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[22]++;


int CodeCoverConditionCoverageHelper_C30;
      while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[22]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[23]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[24]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[118]++;
        // Maybe STRING, GET, SET

        // We should never see a mix of numbers and strings.
        String name = child.getString();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[119]++;
        T type = typeSystem.getType(getScope(), n, name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[120]++;

        Property prop = getProperty(name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[121]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((prop.scheduleRenaming(child,
                                   processProperty(t, prop, type, null))) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[57]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[122]++;
int CodeCoverConditionCoverageHelper_C32;
          // TODO(user): It doesn't look like the user can do much in this
          // case right now.
          if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((propertiesToErrorFor.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[59]++;
            compiler.report(JSError.make(
                t.getSourceName(), child, propertiesToErrorFor.get(name),
                Warnings.INVALIDATION, name,
                (type == null ? "null" : type.toString()), n.toString(), ""));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[123]++;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[60]++;}

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[58]++;}
        child = child.getNext();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[124]++;
      }
    }

    private void printErrorLocations(List<String> errors, JSType t) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[125]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((t.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((t.isAllType()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[61]++;
        return;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[62]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[126]++;
int CodeCoverConditionCoverageHelper_C34;

      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((t.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[63]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[127]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[25]++;


        for (JSType alt : t.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[25]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[26]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[27]++;
}
          printErrorLocations(errors, alt);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[128]++;
        }
        return;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[64]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[129]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[28]++;



      for (JSError error : invalidationMap.get(t)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[28]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[29]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[30]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[130]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((errors.size() > MAX_INVALDIATION_WARNINGS_PER_PROPERTY) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[65]++;
          return;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[66]++;}

        errors.add(
            t.toString() + " at " + error.sourceName + ":" + error.lineNumber);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[131]++;
      }
    }

    /**
     * Processes a property, adding it to the list of properties to rename.
     * @return a representative type for the property reference, which will be
     *   the highest type on the prototype chain of the provided type.  In the
     *   case of a union type, it will be the highest type on the prototype
     *   chain of one of the members of the union.
     */
    private T processProperty(
        NodeTraversal t, Property prop, T type, T relatedType) {
      type = typeSystem.restrictByNotNullOrUndefined(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[132]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[133]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((prop.skipRenaming) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((typeSystem.isInvalidatingType(type)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[67]++;
        return null;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[68]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[134]++;

      Iterable<T> alternatives = typeSystem.getTypeAlternatives(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[135]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((alternatives != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[69]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[136]++;
        T firstType = relatedType;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[137]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[31]++;


        for (T subType : alternatives) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[31]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[32]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[33]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[138]++;
          T lastType = processProperty(t, prop, subType, firstType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[139]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((lastType != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[71]++;
            firstType = firstType == null ? lastType : firstType;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[140]++;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[72]++;}
        }
        return firstType;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[70]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[141]++;
        T topType = typeSystem.getTypeWithProperty(prop.name, type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[142]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((typeSystem.isInvalidatingType(topType)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[73]++;
          return null;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[74]++;}
        prop.addType(type, topType, relatedType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[143]++;
        return topType;
      }
    }
  }

  /** Renames all properties with references on more than one type. */
  void renameProperties() {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[144]++;
    int propsRenamed = 0, propsSkipped = 0, instancesRenamed = 0,
        instancesSkipped = 0, singleTypeProps = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[145]++;

    Set<String> reported = Sets.newHashSet();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[146]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[34]++;


    for (Property prop : properties.values()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[34]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[35]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[36]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[147]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((prop.shouldRename()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[75]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[148]++;
        Map<T, String> propNames = buildPropNames(prop.getTypes(), prop.name);

        ++propsRenamed;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[149]++;
        prop.expandTypesToSkip();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[150]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[151]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[37]++;


        for (Node node : prop.renameNodes) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[37]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[38]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[39]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[152]++;
          T rootType = prop.rootTypes.get(node);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[153]++;
int CodeCoverConditionCoverageHelper_C41;
          if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((prop.shouldRename(rootType)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[77]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[154]++;
            String newName = propNames.get(rootType);
            node.setString(newName);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[155]++;
            compiler.reportCodeChange();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[156]++;
            ++instancesRenamed;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[157]++;

          } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[78]++;
            ++instancesSkipped;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[158]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[159]++;

            CheckLevel checkLevelForProp = propertiesToErrorFor.get(prop.name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[160]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (32)) == 0 || true) &&
 ((checkLevelForProp != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((checkLevelForProp != CheckLevel.OFF) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((reported.contains(prop.name)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 3) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 3) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[79]++;
              reported.add(prop.name);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[161]++;
              compiler.report(JSError.make(
                  NodeUtil.getSourceName(node), node,
                  checkLevelForProp,
                  Warnings.INVALIDATION_ON_TYPE, prop.name,
                  rootType.toString(), ""));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[162]++;

            } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[80]++;}
          }
        }

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[76]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[163]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((prop.skipRenaming) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[81]++;
          ++propsSkipped;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[164]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[82]++;
          ++singleTypeProps;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[165]++;
        }
      }
    }
    logger.fine("Renamed " + instancesRenamed + " instances of "
                + propsRenamed + " properties.");
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[166]++;
    logger.fine("Skipped renaming " + instancesSkipped + " invalidated "
                + "properties, " + propsSkipped + " instances of properties "
                + "that were skipped for specific types and " + singleTypeProps
                + " properties that were referenced from only one type.");
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[167]++;
  }

  /**
   * Chooses a name to use for renaming in each equivalence class and maps
   * each type in that class to it.
   */
  private Map<T, String> buildPropNames(UnionFind<T> types, String name) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[168]++;
    Map<T, String> names = Maps.newHashMap();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[169]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[40]++;


    for (Set<T> set : types.allEquivalenceClasses()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[40]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[41]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[42]++;
}
      checkState(!set.isEmpty());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[170]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[171]++;

      String typeName = null;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[172]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[43]++;


      for (T type : set) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[43]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[44]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[45]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[173]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((typeName == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((type.toString().compareTo(typeName) < 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[83]++;
          typeName = type.toString();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[174]++;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[84]++;}
      }

      String newName;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[175]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 (("{...}".equals(typeName)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[85]++;
        newName = name;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[176]++;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[86]++;
        newName = typeName.replaceAll("[^\\w$]", "_") + "$" + name;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[177]++;
      }
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[178]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[46]++;



      for (T type : set) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[46]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[47]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[48]++;
}
        names.put(type, newName);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[179]++;
      }
    }
    return names;
  }

  /** Returns a map from field name to types for which it will be renamed. */
  Multimap<String, Collection<T>> getRenamedTypesForTesting() {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[180]++;
    Multimap<String, Collection<T>> ret = HashMultimap.create();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[181]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[49]++;


    for (Map.Entry<String, Property> entry: properties.entrySet()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[49]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[50]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[51]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[182]++;
      Property prop = entry.getValue();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[183]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((prop.skipRenaming) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[87]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[184]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[52]++;


        for (Collection<T> c : prop.getTypes().allEquivalenceClasses()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[52]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[53]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[54]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[185]++;
int CodeCoverConditionCoverageHelper_C47;
          if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((c.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((prop.typesToSkip.contains(c.iterator().next())) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[89]++;
            ret.put(entry.getKey(), c);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[186]++;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[90]++;}
        }

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[88]++;}
    }
    return ret;
  }

  /** Interface for providing the type information needed by this pass. */
  private interface TypeSystem<T> {
    // TODO(user): add a getUniqueName(T type) method that is guaranteed
    // to be unique, performant and human-readable.

    /** Returns the top-most scope used by the type system (if any). */
    StaticScope<T> getRootScope();

    /** Returns the new scope started at the given function node. */
    StaticScope<T> getFunctionScope(Node node);

    /**
     * Returns the type of the given node.
     * @param prop Only types with this property need to be returned. In general
     *     with type tightening, this will require no special processing, but in
     *     the case of an unknown JSType, we might need to add in the native
     *     types since we don't track them, but only if they have the given
     *     property.
     */
    T getType(StaticScope<T> scope, Node node, String prop);

    /**
     * Returns true if a field reference on this type will invalidate all
     * references to that field as candidates for renaming. This is true if the
     * type is unknown or all-inclusive, as variables with such a type could be
     * references to any object.
     */
    boolean isInvalidatingType(T type);

    /**
     * Informs the given type system that a type is invalidating due to a type
     * mismatch found during type checking.
     */
    void addInvalidatingType(JSType type);

    /**
     * Returns a set of types that should be skipped given the given type.
     * This is necessary for interfaces when using JSTypes, as all super
     * interfaces must also be skipped.
     */
    ImmutableSet<T> getTypesToSkipForType(T type);

    /**
     * Determines whether the given type is one whose properties should not be
     * considered for renaming.
     */
    boolean isTypeToSkip(T type);

    /** Remove null and undefined from the options in the given type. */
    T restrictByNotNullOrUndefined(T type);

    /**
     * Returns the alternatives if this is a type that represents multiple
     * types, and null if not. Union and interface types can correspond to
     * multiple other types.
     */
    Iterable<T> getTypeAlternatives(T type);

    /**
     * Returns the type in the chain from the given type that contains the given
     * field or null if it is not found anywhere.
     */
    T getTypeWithProperty(String field, T type);

    /**
     * Returns the type of the instance of which this is the prototype or null
     * if this is not a function prototype.
     */
    T getInstanceFromPrototype(T type);

    /**
     * Records that this property could be referenced from any interface that
     * this type, or any type in its superclass chain, implements.
     */
    void recordInterfaces(T type, T relatedType,
                          DisambiguateProperties<T>.Property p);
  }

  /** Implementation of TypeSystem using JSTypes. */
  private static class JSTypeSystem implements TypeSystem<JSType> {
    private final Set<JSType> invalidatingTypes;
    private JSTypeRegistry registry;

    public JSTypeSystem(AbstractCompiler compiler) {
      registry = compiler.getTypeRegistry();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[187]++;
      invalidatingTypes = Sets.newHashSet(
          registry.getNativeType(JSTypeNative.ALL_TYPE),
          registry.getNativeType(JSTypeNative.NO_OBJECT_TYPE),
          registry.getNativeType(JSTypeNative.NO_TYPE),
          registry.getNativeType(JSTypeNative.FUNCTION_PROTOTYPE),
          registry.getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE),
          registry.getNativeType(JSTypeNative.OBJECT_PROTOTYPE),
          registry.getNativeType(JSTypeNative.TOP_LEVEL_PROTOTYPE),
          registry.getNativeType(JSTypeNative.UNKNOWN_TYPE));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[188]++;

    }

    @Override public void addInvalidatingType(JSType type) {
      checkState(!type.isUnionType());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[189]++;
      invalidatingTypes.add(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[190]++;
    }

    @Override public StaticScope<JSType> getRootScope() { return null; }

    @Override public StaticScope<JSType> getFunctionScope(Node node) {
      return null;
    }

    @Override public JSType getType(
        StaticScope<JSType> scope, Node node, String prop) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[191]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((node.getJSType() == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[91]++;
        return registry.getNativeType(JSTypeNative.UNKNOWN_TYPE);

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[92]++;}
      return node.getJSType();
    }

    @Override public boolean isInvalidatingType(JSType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[192]++;
int CodeCoverConditionCoverageHelper_C49;
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (32)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((invalidatingTypes.contains(type)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((type.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 3) && false) /* unresolved types */) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[93]++;
        return true;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[94]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[193]++;

      ObjectType objType = ObjectType.cast(type);
      return objType != null && !objType.hasReferenceName();
    }

    @Override public ImmutableSet<JSType> getTypesToSkipForType(JSType type) {
      type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[194]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[195]++;
int CodeCoverConditionCoverageHelper_C50;
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[95]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[196]++;
        Set<JSType> types = Sets.newHashSet(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[197]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[55]++;


        for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[55]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[56]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[57]++;
}
          types.addAll(getTypesToSkipForTypeNonUnion(alt));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[198]++;
        }
        return ImmutableSet.copyOf(types);

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[96]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[199]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((type.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[97]++;
        return getTypesToSkipForType(
            type.toMaybeEnumElementType().getPrimitiveType());

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[98]++;}
}
      return ImmutableSet.copyOf(getTypesToSkipForTypeNonUnion(type));
    }

    private Set<JSType> getTypesToSkipForTypeNonUnion(JSType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[200]++;
      Set<JSType> types = Sets.newHashSet();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[201]++;
      JSType skipType = type;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[202]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[58]++;


int CodeCoverConditionCoverageHelper_C52;
      while ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((skipType != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[58]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[59]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[60]++;
}
        types.add(skipType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[203]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[204]++;

        ObjectType objSkipType = skipType.toObjectType();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[205]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((objSkipType != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[99]++;
          skipType = objSkipType.getImplicitPrototype();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[206]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[100]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[207]++;
          break;
        }
      }
      return types;
    }

    @Override public boolean isTypeToSkip(JSType type) {
      return type.isEnumType() || (type.autoboxesTo() != null);
    }

    @Override public JSType restrictByNotNullOrUndefined(JSType type) {
      return type.restrictByNotNullOrUndefined();
    }

    @Override public Iterable<JSType> getTypeAlternatives(JSType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[208]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[101]++;
        return type.toMaybeUnionType().getAlternates();

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[102]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[209]++;
        ObjectType objType = type.toObjectType();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[210]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (32)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((objType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((objType.getConstructor().isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 3) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 3) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[103]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[211]++;
          List<JSType> list = Lists.newArrayList();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[212]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[61]++;


          for (FunctionType impl
                   : registry.getDirectImplementors(objType)) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[61]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[62]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[63]++;
}
            list.add(impl.getInstanceType());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[213]++;
          }
          return list;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[104]++;
          return null;
        }
      }
    }

    @Override public ObjectType getTypeWithProperty(String field, JSType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[214]++;
int CodeCoverConditionCoverageHelper_C56;
      if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((type == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[105]++;
        return null;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[106]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[215]++;
int CodeCoverConditionCoverageHelper_C57;

      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((type.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[107]++;
        return getTypeWithProperty(
            field, type.toMaybeEnumElementType().getPrimitiveType());

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[108]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[216]++;
int CodeCoverConditionCoverageHelper_C58;

      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((type instanceof ObjectType) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[109]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[217]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((type.autoboxesTo() != null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[111]++;
          type = type.autoboxesTo();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[218]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[112]++;
          return null;
        }

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[110]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[219]++;
int CodeCoverConditionCoverageHelper_C60;

      // Ignore the prototype itself at all times.
      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 (("prototype".equals(field)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[113]++;
        return null;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[114]++;}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[220]++;

      // We look up the prototype chain to find the highest place (if any) that
      // this appears.  This will make references to overridden properties look
      // like references to the initial property, so they are renamed alike.
      ObjectType foundType = null;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[221]++;
      ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[222]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (32)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((objType.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((objType.getConstructor().isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 3) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 3) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[115]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[223]++;
        ObjectType topInterface = FunctionType.getTopDefiningInterface(
            objType, field);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[224]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((topInterface != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((topInterface.getConstructor() != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[117]++;
          foundType = topInterface.getConstructor().getPrototype();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[225]++;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[118]++;}

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[116]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[226]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[64]++;


int CodeCoverConditionCoverageHelper_C63;
        while ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((objType.getImplicitPrototype() != objType) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[64]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[65]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[66]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[227]++;
int CodeCoverConditionCoverageHelper_C64;
          if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((objType.hasOwnProperty(field)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[119]++;
            foundType = objType;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[228]++;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[120]++;}
          objType = objType.getImplicitPrototype();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[229]++;
        }
      }
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[230]++;
int CodeCoverConditionCoverageHelper_C65;

      // If the property does not exist on the referenced type but the original
      // type is an object type, see if any subtype has the property.
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((foundType == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[121]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[231]++;
        ObjectType maybeType = ObjectType.cast(
            registry.getGreatestSubtypeWithProperty(type, field));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[232]++;
int CodeCoverConditionCoverageHelper_C66;
        // getGreatestSubtypeWithProperty does not guarantee that the property
        // is defined on the returned type, it just indicates that it might be,
        // so we have to double check.
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((maybeType != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((maybeType.hasOwnProperty(field)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[123]++;
          foundType = maybeType;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[233]++;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[124]++;}

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[122]++;}
      return foundType;
    }

    @Override public JSType getInstanceFromPrototype(JSType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[234]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((type.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[125]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[235]++;
        ObjectType prototype = (ObjectType) type;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[236]++;
        FunctionType owner = prototype.getOwnerFunction();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[237]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((owner.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((owner.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[127]++;
          return prototype.getOwnerFunction().getInstanceType();

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[128]++;}

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[126]++;}
      return null;
    }

    @Override
    public void recordInterfaces(JSType type, JSType relatedType,
                                 DisambiguateProperties<JSType>.Property p) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[238]++;
      ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[239]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[129]++;
        FunctionType constructor;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[240]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((objType.isFunctionType()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[131]++;
          constructor = objType.toMaybeFunctionType();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[241]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[132]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[242]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((objType.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[133]++;
          constructor = objType.getOwnerFunction();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[243]++;

        } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[134]++;
          constructor = objType.getConstructor();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[244]++;
        }
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[245]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[67]++;


int CodeCoverConditionCoverageHelper_C72;
        while ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[67]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[68]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[69]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[246]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[70]++;


          for (ObjectType itype : constructor.getImplementedInterfaces()) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[70]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[71]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[72]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[247]++;
            JSType top = getTypeWithProperty(p.name, itype);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[248]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((top != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[135]++;
              p.addType(itype, top, relatedType);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[249]++;

            } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[136]++;
              recordInterfaces(itype, relatedType, p);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[250]++;
            }
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[251]++;
int CodeCoverConditionCoverageHelper_C74;

            // If this interface invalidated this property, return now.
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((p.skipRenaming) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[137]++; return;
} else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[138]++;}
          }
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[252]++;
int CodeCoverConditionCoverageHelper_C75;
          if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((constructor.isInterface()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((constructor.isConstructor()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[139]++;
            constructor = constructor.getSuperClassConstructor();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[253]++;

          } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[140]++;
            constructor = null;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[254]++;
          }
        }

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[130]++;}
    }
  }

  /** Implementation of TypeSystem using concrete types. */
  private static class ConcreteTypeSystem implements TypeSystem<ConcreteType> {
    private final TightenTypes tt;
    private int nextUniqueId;
    private CodingConvention codingConvention;
    private final Set<JSType> invalidatingTypes = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[255]++;
  }

    // An array of native types that are not tracked by type tightening, and
    // thus need to be added in if an unknown type is encountered.
    private static final JSTypeNative [] nativeTypes = new JSTypeNative[] {
        JSTypeNative.BOOLEAN_OBJECT_TYPE,
        JSTypeNative.NUMBER_OBJECT_TYPE,
        JSTypeNative.STRING_OBJECT_TYPE
    };

    public ConcreteTypeSystem(TightenTypes tt, CodingConvention convention) {
      this.tt = tt;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[256]++;
      this.codingConvention = convention;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[257]++;
    }

    @Override public void addInvalidatingType(JSType type) {
      checkState(!type.isUnionType());
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[258]++;
      invalidatingTypes.add(type);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[259]++;
    }

    @Override public StaticScope<ConcreteType> getRootScope() {
      return tt.getTopScope();
    }

    @Override public StaticScope<ConcreteType> getFunctionScope(Node decl) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[260]++;
      ConcreteFunctionType func = tt.getConcreteFunction(decl);
      return (func != null) ?
          func.getScope() : (StaticScope<ConcreteType>) null;
    }

    @Override
    public ConcreteType getType(
        StaticScope<ConcreteType> scope, Node node, String prop) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[261]++;
int CodeCoverConditionCoverageHelper_C76;
      if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((scope != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[141]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[262]++;
        ConcreteType c = tt.inferConcreteType(
            (TightenTypes.ConcreteScope) scope, node);
        return maybeAddAutoboxes(c, node, prop);

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[142]++;
        return null;
      }
    }

    /**
     * Add concrete types for autoboxing types if necessary. The concrete type
     * system does not track native types, like string, so add them if they are
     * present in the JSType for the node.
     */
    private ConcreteType maybeAddAutoboxes(
        ConcreteType cType, Node node, String prop) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[263]++;
      JSType jsType = node.getJSType();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[264]++;
int CodeCoverConditionCoverageHelper_C77;
      if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[143]++;
        return cType;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[144]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[265]++;
int CodeCoverConditionCoverageHelper_C78; if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((jsType.isUnknownType()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[145]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[266]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[73]++;


        for (JSTypeNative nativeType : nativeTypes) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[73]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[74]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[75]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[267]++;
          ConcreteType concrete = tt.getConcreteInstance(
              tt.getTypeRegistry().getNativeObjectType(nativeType));
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[268]++;
int CodeCoverConditionCoverageHelper_C79;
          if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((concrete != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((concrete.getPropertyType(prop).isNone()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[147]++;
            cType = cType.unionWith(concrete);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[269]++;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[148]++;}
        }
        return cType;

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[146]++;}
}

      return maybeAddAutoboxes(cType, jsType, prop);
    }

    private ConcreteType maybeAddAutoboxes(
        ConcreteType cType, JSType jsType, String prop) {
      jsType = jsType.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[270]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[271]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((jsType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[149]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[272]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[76]++;


        for (JSType alt : jsType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[76]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[77]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[78]++;
}
          cType = maybeAddAutoboxes(cType, alt, prop);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[273]++;
        }
        return cType;

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[150]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[274]++;
int CodeCoverConditionCoverageHelper_C81; if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((jsType.isEnumElementType()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[151]++;
        return maybeAddAutoboxes(
            cType, jsType.toMaybeEnumElementType().getPrimitiveType(), prop);

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[152]++;}
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[275]++;
int CodeCoverConditionCoverageHelper_C82;

      if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((jsType.autoboxesTo() != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[153]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[276]++;
        JSType autoboxed = jsType.autoboxesTo();
        return cType.unionWith(tt.getConcreteInstance((ObjectType) autoboxed));

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[154]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[277]++;
int CodeCoverConditionCoverageHelper_C83; if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((jsType.unboxesTo() != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[155]++;
        return cType.unionWith(tt.getConcreteInstance((ObjectType) jsType));

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[156]++;}
}

      return cType;
    }

    @Override public boolean isInvalidatingType(ConcreteType type) {
      // We will disallow types on functions so that 'prototype' is not renamed.
      // TODO(user): Support properties on functions as well.
      return (type == null) || type.isAll() || type.isFunction()
        || (type.isInstance()
            && invalidatingTypes.contains(type.toInstance().instanceType));
    }

    @Override
    public ImmutableSet<ConcreteType> getTypesToSkipForType(ConcreteType type) {
      return ImmutableSet.of(type);
    }

    @Override public boolean isTypeToSkip(ConcreteType type) {
      // Skip anonymous object literals and enum types.
      return type.isInstance()
        && !(type.toInstance().isFunctionPrototype()
             || type.toInstance().instanceType.isInstanceType());
    }

    @Override
    public ConcreteType restrictByNotNullOrUndefined(ConcreteType type) {
      // These are not represented in concrete types.
      return type;
    }

    @Override
    public Iterable<ConcreteType> getTypeAlternatives(ConcreteType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[278]++;
int CodeCoverConditionCoverageHelper_C84;
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((type.isUnion()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[157]++;
        return ((ConcreteUnionType) type).getAlternatives();

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[158]++;
        return null;
      }
    }

    @Override public ConcreteType getTypeWithProperty(String field,
                                                      ConcreteType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[279]++;
int CodeCoverConditionCoverageHelper_C85;
      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((type.isInstance()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[159]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[280]++;
        ConcreteInstanceType instanceType = (ConcreteInstanceType) type;
        return instanceType.getInstanceTypeWithProperty(field);

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[160]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[281]++;
int CodeCoverConditionCoverageHelper_C86; if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((type.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[161]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[282]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 (("prototype".equals(field)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((codingConvention.isSuperClassReference(field)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[163]++;
          return type;

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[164]++;}

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[162]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[283]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((type.isNone()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[165]++;
        // If the receiver is none, then this code is never reached.  We will
        // return a new fake type to ensure that this access is renamed
        // differently from any other, so it can be easily removed.
        return new ConcreteUniqueType(++nextUniqueId);

      } else {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[166]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[284]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((type.isUnion()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[167]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[285]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[79]++;


        // If only one has the property, return that.
        for (ConcreteType t : ((ConcreteUnionType) type).getAlternatives()) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[79]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[80]--;
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.loops[81]++;
}
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[286]++;
          ConcreteType ret = getTypeWithProperty(field, t);
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[287]++;
int CodeCoverConditionCoverageHelper_C90;
          if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((ret != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[169]++;
            return ret;

          } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[170]++;}
        }

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[168]++;}
}
}
}
      return null;
    }

    @Override public ConcreteType getInstanceFromPrototype(ConcreteType type) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[288]++;
int CodeCoverConditionCoverageHelper_C91;
      if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((type.isInstance()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[171]++;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[289]++;
        ConcreteInstanceType instanceType = (ConcreteInstanceType) type;
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.statements[290]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((instanceType.isFunctionPrototype()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[173]++;
          return instanceType.getConstructorType().getInstanceType();

        } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[174]++;}

      } else {
  CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1.branches[172]++;}
      return null;
    }

    @Override
    public void recordInterfaces(ConcreteType type, ConcreteType relatedType,
        DisambiguateProperties<ConcreteType>.Property p) {
      // No need to record interfaces when using concrete types.
    }
  }
}

class CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1 ());
  }
    public static long[] statements = new long[291];
    public static long[] branches = new long[175];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[93];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DisambiguateProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,0,2,1,1,1,1,1,1,1,1,2,1,1,1,1,0,1,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,3,1,2,1,1,2,1,3,1,1,1,1,1,3,1,1,1,1,1,3,2,2,1,1,2,1,2,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 92; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[82];

  public CodeCoverCoverageCounter$4dhvfc8je0vfzlqcgw91blgu7llz4280xgtxhpzvc1 () {
    super("com.google.javascript.jscomp.DisambiguateProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 290; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 174; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 92; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 81; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DisambiguateProperties.java");
      for (int i = 1; i <= 290; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 174; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 92; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 27; i++) {
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

