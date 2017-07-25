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
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.TypeValidator.TypeMismatch;
import com.google.javascript.jscomp.graph.AdjacencyGraph;
import com.google.javascript.jscomp.graph.Annotation;
import com.google.javascript.jscomp.graph.GraphColoring;
import com.google.javascript.jscomp.graph.GraphColoring.GreedyGraphColoring;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.SubGraph;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;
import com.google.javascript.rhino.jstype.JSTypeRegistry;
import com.google.javascript.rhino.jstype.ObjectType;

import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Renames unrelated properties to the same name, using type information.
 * This allows better compression as more properties can be given short names.
 *
 * <p>Properties are considered unrelated if they are never referenced from the
 * same type or from a subtype of each others' types, thus this pass is only
 * effective if type checking is enabled.
 *
 * Example:
 * <code>
 *   Foo.fooprop = 0;
 *   Foo.fooprop2 = 0;
 *   Bar.barprop = 0;
 * </code>
 *
 * becomes:
 *
 * <code>
 *   Foo.a = 0;
 *   Foo.b = 0;
 *   Bar.a = 0;
 * </code>
 *
 */
class AmbiguateProperties implements CompilerPass {
  static {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.ping();
  }

  private static final Logger logger = Logger.getLogger(
      AmbiguateProperties.class.getName());
  static {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[1]++;
  }

  private final AbstractCompiler compiler;

  private final List<Node> stringNodesToRename = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[2]++;
  }
  private final char[] reservedCharacters;

  /** Map from property name to Property object */
  private final Map<String, Property> propertyMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[3]++;
  }

  /** Property names that don't get renamed */
  private final Set<String> externedNames = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[4]++;
  }

  /** Names to which properties shouldn't be renamed, to avoid name conflicts */
  private final Set<String> quotedNames = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[5]++;
  }

  /** Map from original property name to new name. */
  private final Map<String, String> renamingMap = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[6]++;
  }

  /**
   * Sorts Property objects by their count, breaking ties alphabetically to
   * ensure a deterministic total ordering.
   */
  private static final Comparator<Property> FREQUENCY_COMPARATOR =
      new Comparator<Property>() {
        @Override
        public int compare(Property p1, Property p2) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p1.numOccurrences != p2.numOccurrences) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[1]++;
            return p2.numOccurrences - p1.numOccurrences;

          } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[2]++;}
          return p1.oldName.compareTo(p2.oldName);
        }
      };
  static {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[8]++;
  }

  /** A map from JSType to a unique representative Integer. */
  private BiMap<JSType, Integer> intForType = HashBiMap.create();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[9]++;
  }

  /**
   * A map from JSType to JSTypeBitSet representing the types related
   * to the type.
   */
  private Map<JSType, JSTypeBitSet> relatedBitsets = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[10]++;
  }

  /** A set of types that invalidate properties from ambiguation. */
  private final Set<JSType> invalidatingTypes;

  /**
   * Prefix of properties to skip renaming.  These should be renamed in the
   * RenameProperties pass.
   */
  static final String SKIP_PREFIX = "JSAbstractCompiler";
  static {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[11]++;
  }

  AmbiguateProperties(AbstractCompiler compiler,
      char[] reservedCharacters) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[12]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[13]++;
    this.reservedCharacters = reservedCharacters;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[14]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[15]++;

    JSTypeRegistry r = compiler.getTypeRegistry();
    invalidatingTypes = Sets.newHashSet(
        r.getNativeType(JSTypeNative.ALL_TYPE),
        r.getNativeType(JSTypeNative.NO_OBJECT_TYPE),
        r.getNativeType(JSTypeNative.NO_TYPE),
        r.getNativeType(JSTypeNative.NULL_TYPE),
        r.getNativeType(JSTypeNative.VOID_TYPE),
        r.getNativeType(JSTypeNative.FUNCTION_FUNCTION_TYPE),
        r.getNativeType(JSTypeNative.FUNCTION_INSTANCE_TYPE),
        r.getNativeType(JSTypeNative.FUNCTION_PROTOTYPE),
        r.getNativeType(JSTypeNative.GLOBAL_THIS),
        r.getNativeType(JSTypeNative.OBJECT_TYPE),
        r.getNativeType(JSTypeNative.OBJECT_PROTOTYPE),
        r.getNativeType(JSTypeNative.OBJECT_FUNCTION_TYPE),
        r.getNativeType(JSTypeNative.TOP_LEVEL_PROTOTYPE),
        r.getNativeType(JSTypeNative.UNKNOWN_TYPE));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[16]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[17]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[1]++;



    for (TypeMismatch mis : compiler.getTypeValidator().getMismatches()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[1]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[2]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[3]++;
}
      addInvalidatingType(mis.typeA);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[18]++;
      addInvalidatingType(mis.typeB);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[19]++;
    }
  }

  /**
   * Invalidates the given type, so that no properties on it will be renamed.
   */
  private void addInvalidatingType(JSType type) {
    type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[20]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[3]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[4]++;


      for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[4]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[5]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[6]++;
}
        addInvalidatingType(alt);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[23]++;
      }

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[4]++;}

    invalidatingTypes.add(type);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[24]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[25]++;
    ObjectType objType = ObjectType.cast(type);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((objType != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((objType.isInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[5]++;
      invalidatingTypes.add(objType.getImplicitPrototype());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[27]++;

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[6]++;}
  }

  Map<String, String> getRenamingMap() {
    return renamingMap;
  }

  /** Returns an integer that uniquely identifies a JSType. */
  private int getIntForType(JSType type) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[28]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((intForType.containsKey(type)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[7]++;
      return intForType.get(type).intValue();

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[8]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[29]++;
    int newInt = intForType.size() + 1;
    intForType.put(type, newInt);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[30]++;
    return newInt;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, externs, new ProcessExterns());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[31]++;
    NodeTraversal.traverse(compiler, root, new ProcessProperties());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[32]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[33]++;

    Set<String> reservedNames =
        new HashSet<String>(externedNames.size() + quotedNames.size());
    reservedNames.addAll(externedNames);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[34]++;
    reservedNames.addAll(quotedNames);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[35]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[36]++;

    int numRenamedPropertyNames = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[37]++;
    int numSkippedPropertyNames = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[38]++;
    Set<Property> propsByFreq = new TreeSet<Property>(FREQUENCY_COMPARATOR);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[39]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[7]++;


    for (Property p : propertyMap.values()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[7]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[8]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[9]++;
}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[40]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((p.skipAmbiguating) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[9]++;
        ++numRenamedPropertyNames;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[41]++;
        propsByFreq.add(p);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[42]++;

      } else {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[10]++;
        ++numSkippedPropertyNames;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[43]++;
        reservedNames.add(p.oldName);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[44]++;
      }
    }
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[45]++;

    PropertyGraph graph = new PropertyGraph(Lists.newLinkedList(propsByFreq));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[46]++;
    GraphColoring<Property, Void> coloring =
        new GreedyGraphColoring<Property, Void>(graph, FREQUENCY_COMPARATOR);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[47]++;
    int numNewPropertyNames = coloring.color();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[48]++;

    NameGenerator nameGen = new NameGenerator(
        reservedNames, "", reservedCharacters);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[49]++;
    Map<Integer, String> colorMap = Maps.newHashMap();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[50]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[10]++;


int CodeCoverConditionCoverageHelper_C6;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < numNewPropertyNames) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[10]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[11]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[12]++;
}
      colorMap.put(i, nameGen.generateNextName());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[51]++;
    }
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[52]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[13]++;


    for (GraphNode<Property, Void> node : graph.getNodes()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[13]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[14]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[15]++;
}
      node.getValue().newName = colorMap.get(node.getAnnotation().hashCode());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[53]++;
      renamingMap.put(node.getValue().oldName, node.getValue().newName);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[54]++;
    }
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[55]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[16]++;



    // Update the string nodes.
    for (Node n : stringNodesToRename) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[16]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[17]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[18]++;
}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[56]++;
      String oldName = n.getString();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[57]++;
      Property p = propertyMap.get(oldName);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[58]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((p.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[11]++;
        Preconditions.checkState(oldName.equals(p.oldName));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[59]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[60]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((p.newName.equals(oldName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[13]++;
          n.setString(p.newName);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[61]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[62]++;

        } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[14]++;}

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[12]++;}
    }

    logger.fine("Collapsed " + numRenamedPropertyNames + " properties into "
                + numNewPropertyNames + " and skipped renaming "
                + numSkippedPropertyNames + " properties.");
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[63]++;
  }

  private BitSet getRelatedTypesOnNonUnion(JSType type) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[64]++;
int CodeCoverConditionCoverageHelper_C9;
    // All of the types we encounter should have been added to the
    // relatedBitsets via computeRelatedTypes.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((relatedBitsets.containsKey(type)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[15]++;
      return relatedBitsets.get(type);

    } else {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[16]++;
      throw new RuntimeException("Related types should have been computed for"
                                 + " type: " + type + " but have not been.");
    }
  }

  /**
   * Adds subtypes - and implementors, in the case of interfaces - of the type
   * to its JSTypeBitSet of related types. Union types are decomposed into their
   * alternative types.
   *
   * <p>The 'is related to' relationship is best understood graphically. Draw an
   * arrow from each instance type to the prototype of each of its
   * subclass. Draw an arrow from each prototype to its instance type. Draw an
   * arrow from each interface to its implementors. A type is related to another
   * if there is a directed path in the graph from the type to other. Thus, the
   * 'is related to' relationship is reflexive and transitive.
   *
   * <p>Example with Foo extends Bar which extends Baz and Bar implements I:
   * <pre>
   * Foo -> Bar.prototype -> Bar -> Baz.prototype -> Baz
   *                          ^
   *                          |
   *                          I
   * </pre>
   *
   * <p>Note that we don't need to correctly handle the relationships between
   * functions, because the function type is invalidating (i.e. its properties
   * won't be ambiguated).
   */
  private void computeRelatedTypes(JSType type) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[65]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[17]++;
      type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[66]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[67]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[19]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[68]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[19]++;


        for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[19]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[20]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[21]++;
}
          computeRelatedTypes(alt);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[69]++;
        }
        return;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[20]++;}

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[18]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[70]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((relatedBitsets.containsKey(type)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[21]++;
      // We only need to generate the bit set once.
      return;

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[22]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[71]++;

    JSTypeBitSet related = new JSTypeBitSet(intForType.size());
    relatedBitsets.put(type, related);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[72]++;
    related.set(getIntForType(type));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[73]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[74]++;
int CodeCoverConditionCoverageHelper_C13;

    // A prototype is related to its instance.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type.isFunctionPrototypeType()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[23]++;
      addRelatedInstance(((ObjectType) type).getOwnerFunction(), related);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[75]++;
      return;

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[24]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[76]++;

    // An instance is related to its subclasses.
    FunctionType constructor = type.toObjectType().getConstructor();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[77]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((constructor != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((constructor.getSubTypes() != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[25]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[78]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[22]++;


      for (FunctionType subType : constructor.getSubTypes()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[22]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[23]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[24]++;
}
        addRelatedInstance(subType, related);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[79]++;
      }

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[26]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[80]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[25]++;



    // An interface is related to its implementors.
    for (FunctionType implementor : compiler.getTypeRegistry()
        .getDirectImplementors(type.toObjectType())) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[25]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[26]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[27]++;
}
      addRelatedInstance(implementor, related);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[81]++;
    }
  }

  /**
   * Adds the instance of the given constructor, its implicit prototype and all
   * its related types to the given bit set.
   */
  private void addRelatedInstance(
      FunctionType constructor, JSTypeBitSet related) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[82]++;
int CodeCoverConditionCoverageHelper_C15;
    // TODO(user): A constructor which doesn't have an instance type
    // (e.g. it's missing the @constructor annotation) should be an invalidating
    // type which doesn't reach this code path.
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((constructor.hasInstanceType()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[27]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[83]++;
      ObjectType instanceType = constructor.getInstanceType();
      related.set(getIntForType(instanceType.getImplicitPrototype()));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[84]++;
      computeRelatedTypes(instanceType);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[85]++;
      related.or(relatedBitsets.get(instanceType));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[86]++;

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[28]++;}
  }

  class PropertyGraph implements AdjacencyGraph<Property, Void> {
    protected final Map<Property, PropertyGraphNode> nodes = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[87]++;
  }

    PropertyGraph(Collection<Property> props) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[88]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[28]++;


      for (Property prop : props) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[28]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[29]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[30]++;
}
        nodes.put(prop, new PropertyGraphNode(prop));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[89]++;
      }
    }

    @Override
    public List<GraphNode<Property, Void>> getNodes() {
      return Lists.<GraphNode<Property, Void>>newArrayList(nodes.values());
    }

    @Override
    public GraphNode<Property, Void> getNode(Property property) {
      return nodes.get(property);
    }

    @Override
    public SubGraph<Property, Void> newSubGraph() {
      return new PropertySubGraph();
    }

    @Override
    public void clearNodeAnnotations() {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[90]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[31]++;


      for (PropertyGraphNode node : nodes.values()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[31]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[32]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[33]++;
}
        node.setAnnotation(null);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[91]++;
      }
    }

    @Override
    public int getWeight(Property value) {
      return value.numOccurrences;
    }
  }

  /**
   * A {@link SubGraph} that represents properties. The related types of
   * the properties are used to efficiently calculate adjacency information.
   */
  class PropertySubGraph implements SubGraph<Property, Void> {
    /** Types related to properties referenced in this subgraph. */
    JSTypeBitSet relatedTypes = new JSTypeBitSet(intForType.size());
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[92]++;
  }

    /**
     * Returns true if prop is in an independent set from all properties in this
     * sub graph.  That is, if none of its related types intersects with the
     * related types for this sub graph.
     */
    @Override
    public boolean isIndependentOf(Property prop) {
      return !relatedTypes.intersects(prop.relatedTypes);
    }

    /**
     * Adds the node to the sub graph, adding all its related types to the
     * related types for the sub graph.
     */
    @Override
    public void addNode(Property prop) {
      relatedTypes.or(prop.relatedTypes);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[93]++;
    }
  }

  class PropertyGraphNode implements GraphNode<Property, Void> {
    Property property;
    protected Annotation annotation;

    PropertyGraphNode(Property property) {
      this.property = property;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[94]++;
    }

    @Override
    public Property getValue() {
      return property;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A extends Annotation> A getAnnotation() {
      return (A) annotation;
    }

    @Override
    public void setAnnotation(Annotation data) {
      annotation = data;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[95]++;
    }
  }

  /** A traversal callback that collects externed property names. */
  private class ProcessExterns extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[96]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[29]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[97]++;
          Node dest = n.getFirstChild().getNext();
          externedNames.add(dest.getString());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[98]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[99]++;
          break;
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[30]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[100]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[34]++;


int CodeCoverConditionCoverageHelper_C16;
          for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false);
               child = child.getNext()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[34]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[35]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[36]++;
}
            // names: STRING, GET, SET
            externedNames.add(child.getString());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[101]++;
          }
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[102]++;
          break; default : CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[31]++;
      }
    }
  }

  /** Finds all property references, recording the types on which they occur. */
  private class ProcessProperties extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[103]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[32]++; {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[104]++;
          Node propNode = n.getFirstChild().getNext();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[105]++;
          JSType jstype = getJSType(n.getFirstChild());
          maybeMarkCandidate(propNode, jstype, t);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[106]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[107]++;
          break;
        }
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[33]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[108]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[37]++;


int CodeCoverConditionCoverageHelper_C17;
          // The children of an OBJECTLIT node are keys, where the values
          // are the children of the keys.
          for (Node key = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false);
               key = key.getNext()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[37]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[38]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[39]++;
}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[109]++;
int CodeCoverConditionCoverageHelper_C18;
            // We only want keys that were unquoted.
            // Keys are STRING, GET, SET
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((key.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[34]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[110]++;
              JSType jstype = getJSType(n.getFirstChild());
              maybeMarkCandidate(key, jstype, t);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[111]++;

            } else {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[35]++;
              // Ensure that we never rename some other property in a way
              // that could conflict with this quoted key.
              quotedNames.add(key.getString());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[112]++;
            }
          }
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[113]++;
          break;
        case Token.GETELEM:
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[36]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[114]++;
          // If this is a quoted property access (e.g. x['myprop']), we need to
          // ensure that we never rename some other property in a way that
          // could conflict with this quoted name.
          Node child = n.getLastChild();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[115]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((child.isString()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[37]++;
            quotedNames.add(child.getString());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[116]++;

          } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[38]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[117]++;
          break; default : CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[39]++;
      }
    }

    /**
     * If a property node is eligible for renaming, stashes a reference to it
     * and increments the property name's access count.
     *
     * @param n The STRING node for a property
     * @param t The traversal
     */
    private void maybeMarkCandidate(Node n, JSType type, NodeTraversal t) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[118]++;
      String name = n.getString();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[119]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((externedNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[40]++;
        stringNodesToRename.add(n);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[120]++;
        recordProperty(name, type);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[121]++;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[41]++;}
    }

    private Property recordProperty(String name, JSType type) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[122]++;
      Property prop = getProperty(name);
      prop.addType(type);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[123]++;
      return prop;
    }
  }

  /** Returns true if properties on this type should not be renamed. */
  private boolean isInvalidatingType(JSType type) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[124]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[42]++;
      type = type.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[125]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[126]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((type.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[44]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[127]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[40]++;


        for (JSType alt : type.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[40]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[41]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[42]++;
}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[128]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isInvalidatingType(alt)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[46]++;
            return true;

          } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[47]++;}
        }
        return false;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[45]++;}

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[43]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[129]++;
    ObjectType objType = ObjectType.cast(type);
    return objType == null
        || invalidatingTypes.contains(objType)
        || !objType.hasReferenceName()
        || objType.isUnknownType()
        || objType.isEmptyType() /* unresolved types */
        || objType.isEnumType()
        || objType.autoboxesTo() != null;
  }

  private Property getProperty(String name) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[130]++;
    Property prop = propertyMap.get(name);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[131]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((prop == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[48]++;
      prop = new Property(name);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[132]++;
      propertyMap.put(name, prop);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[133]++;

    } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[49]++;}
    return prop;
  }

  /**
   * This method gets the JSType from the Node argument and verifies that it is
   * present.
   */
  private JSType getJSType(Node n) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[134]++;
    JSType jsType = n.getJSType();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[135]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((jsType == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[50]++;
      // TODO(user): This branch indicates a compiler bug, not worthy of
      // halting the compilation but we should log this and analyze to track
      // down why it happens. This is not critical and will be resolved over
      // time as the type checker is extended.
      return compiler.getTypeRegistry().getNativeType(
          JSTypeNative.UNKNOWN_TYPE);

    } else {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[51]++;
      return jsType;
    }
  }

  /** Encapsulates the information needed for renaming a property. */
  private class Property {
    final String oldName;
    String newName;
    int numOccurrences;
    boolean skipAmbiguating;
    JSTypeBitSet relatedTypes = new JSTypeBitSet(intForType.size());
  {
    CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[136]++;
  }

    Property(String name) {
      this.oldName = name;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[137]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[138]++;
int CodeCoverConditionCoverageHelper_C26;

      // Properties with this suffix are handled in RenameProperties.
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((name.startsWith(SKIP_PREFIX)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[52]++;
        skipAmbiguating = true;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[139]++;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[53]++;}
    }

    /** Add this type to this property, calculating */
    void addType(JSType newType) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[140]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((skipAmbiguating) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[54]++;
        return;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[55]++;}

      ++numOccurrences;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[141]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[142]++;
int CodeCoverConditionCoverageHelper_C28;

      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((newType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[56]++;
        newType = newType.restrictByNotNullOrUndefined();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[143]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[144]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((newType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[58]++;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[145]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[43]++;


          for (JSType alt : newType.toMaybeUnionType().getAlternates()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[43]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[44]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[45]++;
}
            addNonUnionType(alt);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[146]++;
          }
          return;

        } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[59]++;}

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[57]++;}
      addNonUnionType(newType);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[147]++;
    }

    private void addNonUnionType(JSType newType) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[148]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((skipAmbiguating) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isInvalidatingType(newType)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[60]++;
        skipAmbiguating = true;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[149]++;
        return;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[61]++;}
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[150]++;
int CodeCoverConditionCoverageHelper_C31;

      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((relatedTypes.get(getIntForType(newType))) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[62]++;
        computeRelatedTypes(newType);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[151]++;
        relatedTypes.or(getRelatedTypesOnNonUnion(newType));
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[152]++;

      } else {
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.branches[63]++;}
    }
  }

  // A BitSet that stores type info. Adds pretty-print routines.
  private class JSTypeBitSet extends BitSet {
    private static final long serialVersionUID = 1L;

    private JSTypeBitSet(int size) {
      super(size);
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[153]++;
    }

    private JSTypeBitSet() {
      super();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[154]++;
    }

    /**
     * Pretty-printing, for diagnostic purposes.
     */
    @Override
    public String toString() {
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[155]++;
      int from = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[156]++;
      int current = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[157]++;
      List<String> types = Lists.newArrayList();
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[158]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[46]++;


      while (-1 != (current = nextSetBit(from))) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[46]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[47]--;
  CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.loops[48]++;
}
        types.add(intForType.inverse().get(current).toString());
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[159]++;
        from = current + 1;
CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch.statements[160]++;
      }
      return Joiner.on(" && ").join(types);
    }
  }
}

class CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch ());
  }
    public static long[] statements = new long[161];
    public static long[] branches = new long[64];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[32];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AmbiguateProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1};
    for (int i = 1; i <= 31; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[49];

  public CodeCoverCoverageCounter$f2v2yo4t3rr90frvp1ccr73c7ll2m11awx6ch () {
    super("com.google.javascript.jscomp.AmbiguateProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 160; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 63; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 48; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AmbiguateProperties.java");
      for (int i = 1; i <= 160; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 63; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 31; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 16; i++) {
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

