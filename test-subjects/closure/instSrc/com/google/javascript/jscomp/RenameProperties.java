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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.AbstractCompiler.LifeCycleStage;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.graph.Graph.GraphEdge;
import com.google.javascript.jscomp.graph.LinkedUndirectedGraph;
import com.google.javascript.jscomp.graph.UndiGraph;
import com.google.javascript.jscomp.graph.UndiGraph.UndiGraphEdge;
import com.google.javascript.jscomp.graph.UndiGraph.UndiGraphNode;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nullable;

/**
 * RenameProperties renames properties (including methods) of all JavaScript
 * objects. This includes prototypes, functions, object literals, etc.
 *
 * <p> If provided a VariableMap of previously used names, it tries to reuse
 * those names.
 *
 * <p> To prevent a property from getting renamed you may extern it (add it to
 * your externs file) or put it in quotes.
 *
 * <p> To avoid run-time JavaScript errors, use quotes when accessing properties
 * that are defined using quotes.
 *
 * <pre>
 *   var a = {'myprop': 0}, b = a['myprop'];  // correct
 *   var x = {'myprop': 0}, y = x.myprop;     // incorrect
 * </pre>
 *
 */
class RenameProperties implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.ping();
  }


  private final AbstractCompiler compiler;
  private final boolean generatePseudoNames;

  /** Property renaming map from a previous compilation. */
  private final VariableMap prevUsedPropertyMap;

  private final List<Node> stringNodesToRename = new ArrayList<Node>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[1]++;
  }
  private final Map<Node, Node> callNodeToParentMap =
      new HashMap<Node, Node>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[2]++;
  }
  private final char[] reservedCharacters;

  // Map from property name to Property object
  private final Map<String, Property> propertyMap =
      new HashMap<String, Property>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[3]++;
  }

  /**
   * A graph of property affinity information.
   *
   * Suppose property X and Y are access in the same function N times.
   *
   * The graph would have X -> Y with the edge of N.
   */
  private final UndiGraph<Property, PropertyAffinity> affinityGraph;

  // Property names that don't get renamed
  private final Set<String> externedNames = new HashSet<String>(
      Arrays.asList("prototype"));
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[4]++;
  }

  // Names to which properties shouldn't be renamed, to avoid name conflicts
  private final Set<String> quotedNames = new HashSet<String>();
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[5]++;
  }

  private static final Comparator<Property> FREQUENCY_COMPARATOR =
    new Comparator<Property>() {
      @Override
      public int compare(Property p1, Property p2) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

        /**
         * First a frequently used names would always be picked first.
         */
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((p1.numOccurrences != p2.numOccurrences) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[1]++;
          return p2.numOccurrences - p1.numOccurrences;


        /**
         * If both properties are used equally frequent. We'll let the property
         * with a high affinity score get a name first.
         *
         * see #computeAffinityScores() for how the score is computed.
         */
        } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[2]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[7]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((p1.affinityScore != p2.affinityScore) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[3]++;
          return p2.affinityScore - p1.affinityScore;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[4]++;}
}

        /**
         * Finally, for determinism, we compare them based on the old name.
         */
        return p1.oldName.compareTo(p2.oldName);
       }
    };
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[8]++;
  }

  /**
   * The name of a special function that this pass replaces. It takes one
   * argument: a string literal containing one or more dot-separated JS
   * identifiers. This pass will replace them as though they were JS property
   * references. Here are two examples:
   *    JSCompiler_renameProperty('propertyName') -> 'jYq'
   *    JSCompiler_renameProperty('myProp.nestedProp.innerProp') -> 'e4.sW.C$'
   */
  static final String RENAME_PROPERTY_FUNCTION_NAME =
      "JSCompiler_renameProperty";
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[9]++;
  }

  static final DiagnosticType BAD_CALL = DiagnosticType.error(
      "JSC_BAD_RENAME_PROPERTY_FUNCTION_NAME_CALL",
      "Bad " + RENAME_PROPERTY_FUNCTION_NAME + " call - " +
      "argument must be a string literal");
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[10]++;
  }

  static final DiagnosticType BAD_ARG = DiagnosticType.error(
      "JSC_BAD_RENAME_PROPERTY_FUNCTION_NAME_ARG",
      "Bad " + RENAME_PROPERTY_FUNCTION_NAME + " argument - " +
      "'{0}' is not a valid JavaScript identifier");
  static {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[11]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The JSCompiler
   * @param affinity Optimize for affinity information.
   * @param generatePseudoNames Generate pseudo names. e.g foo -> $foo$ instead
   *        of compact obfuscated names. This is used for debugging.
   */
  RenameProperties(AbstractCompiler compiler, boolean affinity,
      boolean generatePseudoNames) {
    this(compiler, affinity, generatePseudoNames, null, null);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[12]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The JSCompiler.
   * @param affinity Optimize for affinity information.
   * @param generatePseudoNames Generate pseudo names. e.g foo -> $foo$ instead
   *        of compact obfuscated names. This is used for debugging.
   * @param prevUsedPropertyMap The property renaming map used in a previous
   *        compilation.
   */
  RenameProperties(AbstractCompiler compiler, boolean affinity,
      boolean generatePseudoNames, VariableMap prevUsedPropertyMap) {
    this(compiler, affinity, generatePseudoNames, prevUsedPropertyMap, null);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[13]++;
  }

  /**
   * Creates an instance.
   *
   * @param compiler The JSCompiler.
   * @param affinity Optimize for affinity information.
   * @param generatePseudoNames Generate pseudo names. e.g foo -> $foo$ instead
   *        of compact obfuscated names. This is used for debugging.
   * @param prevUsedPropertyMap The property renaming map used in a previous
   *        compilation.
   * @param reservedCharacters If specified these characters won't be used in
   *   generated names
   */
  RenameProperties(AbstractCompiler compiler,
      boolean affinity,
      boolean generatePseudoNames,
      VariableMap prevUsedPropertyMap,
      @Nullable char[] reservedCharacters) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[14]++;
    this.generatePseudoNames = generatePseudoNames;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[15]++;
    this.prevUsedPropertyMap = prevUsedPropertyMap;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[16]++;
    this.reservedCharacters = reservedCharacters;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[17]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((affinity) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[5]++;
      this.affinityGraph = LinkedUndirectedGraph.createWithoutAnnotations();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[19]++;

    } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[6]++;
      this.affinityGraph = null;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[20]++;
    }
  }

  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[21]++;

    NodeTraversal.traverse(compiler, externs, new ProcessExterns());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[22]++;
    NodeTraversal.traverse(compiler, root, new ProcessProperties());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[23]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[24]++;

    Set<String> reservedNames =
        new HashSet<String>(externedNames.size() + quotedNames.size());
    reservedNames.addAll(externedNames);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[25]++;
    reservedNames.addAll(quotedNames);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[26]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;

    // First, try and reuse as many property names from the previous compilation
    // as possible.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((prevUsedPropertyMap != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[7]++;
      reusePropertyNames(reservedNames, propertyMap.values());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[28]++;

    } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[8]++;}

    compiler.addToDebugLog("JS property assignments:");
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[29]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((affinityGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[9]++;
      computeAffinityScores();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[31]++;

    } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[10]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[32]++;

    // Assign names, sorted by descending frequency to minimize code size.
    Set<Property> propsByFreq = new TreeSet<Property>(FREQUENCY_COMPARATOR);
    propsByFreq.addAll(propertyMap.values());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[33]++;
    generateNames(propsByFreq, reservedNames);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[34]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[35]++;

    // Update the string nodes.
    boolean changed = false;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[36]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[1]++;


    for (Node n : stringNodesToRename) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[1]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[2]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[3]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[37]++;
      String oldName = n.getString();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[38]++;
      Property p = propertyMap.get(oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((p.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[11]++;
        Preconditions.checkState(oldName.equals(p.oldName));
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[40]++;
        n.setString(p.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[41]++;
        changed = changed || !p.newName.equals(oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[42]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[12]++;}
    }
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[43]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[4]++;



    // Update the call nodes.
    for (Map.Entry<Node, Node> nodeEntry : callNodeToParentMap.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[4]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[5]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[6]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[44]++;
      Node parent = nodeEntry.getValue();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[45]++;
      Node firstArg = nodeEntry.getKey().getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[46]++;
      StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[47]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[7]++;


      for (String oldName : firstArg.getString().split("[.]")) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[7]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[8]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[9]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[48]++;
        Property p = propertyMap.get(oldName);
        String replacement;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[49]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((p.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[13]++;
          Preconditions.checkState(oldName.equals(p.oldName));
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[50]++;
          replacement = p.newName;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[51]++;

        } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[14]++;
          replacement = oldName;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[52]++;
        }
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[53]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((sb.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[15]++;
          sb.append('.');
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[54]++;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[16]++;}
        sb.append(replacement);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[55]++;
      }
      parent.replaceChild(nodeEntry.getKey(), IR.string(sb.toString()));
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[56]++;
      changed = true;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[57]++;
    }
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[58]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((changed) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[17]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[59]++;

    } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[18]++;}

    compiler.setLifeCycleStage(LifeCycleStage.NORMALIZED_OBFUSCATED);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[60]++;
  }

  /**
   * Runs through the list of properties and renames as many as possible with
   * names from the previous compilation. Also, updates reservedNames with the
   * set of reused names.
   * @param reservedNames Reserved names to use during renaming.
   * @param allProps Properties to rename.
   */
  private void reusePropertyNames(Set<String> reservedNames,
                                  Collection<Property> allProps) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[61]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[10]++;


    for (Property prop : allProps) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[10]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[11]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[12]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[62]++;
      // Check if this node can reuse a name from a previous compilation - if
      // it can set the newName for the property too.
      String prevName = prevUsedPropertyMap.lookupNewName(prop.oldName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[63]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((generatePseudoNames) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((prevName != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[19]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[64]++;
int CodeCoverConditionCoverageHelper_C11;
        // We can reuse prevName if it's not reserved.
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((reservedNames.contains(prevName)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[21]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[65]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[22]++;}

        prop.newName = prevName;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[66]++;
        reservedNames.add(prevName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[67]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[20]++;}
    }
  }

  /**
   * A X property gets an affinity score:
   *
   * score = sum (# of times X appears Y * frequency(Y)) for all Y where
   *   frequency(Y) > frequency (X).
   *
   * This way a property would have a name closer to all high frequency names.
   * Also two property of the same frequency would have very close names if
   * they always appear together.
   */
  private void computeAffinityScores() {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[68]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[13]++;


    for (Property p : propertyMap.values()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[13]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[14]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[15]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[69]++;
      UndiGraphNode<Property, PropertyAffinity> node =
          affinityGraph.getUndirectedGraphNode(p);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[70]++;

      int affinityScore = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[71]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;
      for (Iterator<UndiGraphEdge<Property, PropertyAffinity>> edgeIterator =
          node.getNeighborEdgesIterator();(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((edgeIterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false);) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[16]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[17]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[18]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[72]++;
        UndiGraphEdge<Property,PropertyAffinity> edge = edgeIterator.next();
        affinityScore += edge.getValue().affinity +
            (node == edge.getNodeA() ?
                edge.getNodeB().getValue().numOccurrences :
                edge.getNodeA().getValue().numOccurrences);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[73]++;
      }
      node.getValue().affinityScore = affinityScore;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[74]++;
    }
  }

  /**
   * Generates new names for properties.
   *
   * @param props Properties to generate new names for
   * @param reservedNames A set of names to which properties should not be
   *     renamed
   */
  private void generateNames(Set<Property> props, Set<String> reservedNames) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[75]++;
    NameGenerator nameGen = new NameGenerator(
        reservedNames, "", reservedCharacters);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[76]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[19]++;


    for (Property p : props) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[19]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[20]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[21]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[77]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((generatePseudoNames) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[23]++;
        p.newName = "$" + p.oldName + "$";
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[78]++;

      } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[24]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[79]++;
int CodeCoverConditionCoverageHelper_C14;
        // If we haven't already given this property a reusable name.
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((p.newName == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[25]++;
          p.newName = nameGen.generateNextName();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[80]++;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[26]++;}
      }
      reservedNames.add(p.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[81]++;
      compiler.addToDebugLog(p.oldName + " => " + p.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[82]++;
    }
  }

  /**
   * Gets the property renaming map (the "answer key").
   *
   * @return A mapping from original names to new names
   */
  VariableMap getPropertyMap() {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[83]++;
    ImmutableMap.Builder<String, String> map = ImmutableMap.builder();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[84]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[22]++;


    for (Property p : propertyMap.values()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[22]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[23]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[24]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[85]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((p.newName != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[27]++;
        map.put(p.oldName, p.newName);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[86]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[28]++;}
    }
    return new VariableMap(map.build());
  }

  // -------------------------------------------------------------------------

  /**
   * A traversal callback that collects externed property names.
   */
  private class ProcessExterns extends AbstractPostOrderCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[87]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[29]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[88]++;
          Node dest = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[89]++;
int CodeCoverConditionCoverageHelper_C16;
          if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((dest.isString()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[30]++;
            externedNames.add(dest.getString());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[90]++;

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[31]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[91]++;
          break;
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[32]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[92]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[25]++;


int CodeCoverConditionCoverageHelper_C17;
          for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false);
               child = child.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[25]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[26]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[27]++;
}
            externedNames.add(child.getString());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[93]++;
          }
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[94]++;
          break; default : CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[33]++;
      }
    }
  }


  // -------------------------------------------------------------------------

  /**
   * A traversal callback that collects property names and counts how
   * frequently each property name occurs.
   */
  private class ProcessProperties extends AbstractPostOrderCallback implements
      ScopedCallback {

    private Set<Property> currentHighAffinityProperties = null;
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[95]++;
  }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[96]++;
      switch (n.getType()) {
        case Token.GETPROP:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[34]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[97]++;
          Node propNode = n.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[98]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((propNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[35]++;
            maybeMarkCandidate(propNode);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[99]++;

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[36]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[100]++;
          break;
        case Token.OBJECTLIT:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[37]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[101]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[28]++;


int CodeCoverConditionCoverageHelper_C19;
          for (Node key = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); key = key.getNext()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[28]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[29]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[30]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[102]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((key.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[38]++;
              maybeMarkCandidate(key);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[103]++;

            } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[39]++;
              // Ensure that we never rename some other property in a way
              // that could conflict with this quoted key.
              quotedNames.add(key.getString());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[104]++;
            }
          }
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[105]++;
          break;
        case Token.GETELEM:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[40]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[106]++;
          // If this is a quoted property access (e.g. x['myprop']), we need to
          // ensure that we never rename some other property in a way that
          // could conflict with this quoted name.
          Node child = n.getLastChild();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[107]++;
int CodeCoverConditionCoverageHelper_C21;
          if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((child.isString()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[41]++;
            quotedNames.add(child.getString());
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[108]++;

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[42]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[109]++;
          break;
        case Token.CALL:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[43]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[110]++;
          // We replace a JSCompiler_renameProperty function call with a string
          // containing the renamed property.
          Node fnName = n.getFirstChild();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[111]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((fnName.isName()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((RENAME_PROPERTY_FUNCTION_NAME.equals(fnName.getString())) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[44]++;
            callNodeToParentMap.put(n, parent);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[112]++;
            countCallCandidates(t, n);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[113]++;

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[45]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[114]++;
          break;
        case Token.FUNCTION:
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[46]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[115]++;
int CodeCoverConditionCoverageHelper_C23;
          // We eliminate any stub implementations of JSCompiler_renameProperty
          // that we encounter.
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[47]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[116]++;
            String name = n.getFirstChild().getString();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[117]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((RENAME_PROPERTY_FUNCTION_NAME.equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[49]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[118]++;
int CodeCoverConditionCoverageHelper_C25;
              if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[51]++;
                parent.detachFromParent();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[119]++;

              } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[52]++;
                parent.removeChild(n);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[120]++;
              }
              compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[121]++;

            } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[50]++;}

          } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[48]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[122]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((RENAME_PROPERTY_FUNCTION_NAME.equals(parent.getString())) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[53]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[123]++;
            Node varNode = parent.getParent();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[124]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((varNode.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[55]++;
              varNode.removeChild(parent);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[125]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[126]++;
int CodeCoverConditionCoverageHelper_C28;
              if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((varNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[57]++;
                varNode.detachFromParent();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[127]++;

              } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[58]++;}
              compiler.reportCodeChange();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[128]++;

            } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[56]++;}

          } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[54]++;}
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[129]++;
          break; default : CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[59]++;
      }
    }

    /**
     * If a property node is eligible for renaming, stashes a reference to it
     * and increments the property name's access count.
     *
     * @param n The STRING node for a property
     */
    private void maybeMarkCandidate(Node n) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[130]++;
      String name = n.getString();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[131]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((externedNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[60]++;
        stringNodesToRename.add(n);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[132]++;
        countPropertyOccurrence(name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[133]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[61]++;}
    }

    /**
     * Counts references to property names that occur in a special function
     * call.
     *
     * @param callNode The CALL node for a property
     * @param t The traversal
     */
    private void countCallCandidates(NodeTraversal t, Node callNode) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[134]++;
      Node firstArg = callNode.getFirstChild().getNext();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[135]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((firstArg.isString()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[62]++;
        t.report(callNode, BAD_CALL);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[136]++;
        return;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[63]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[137]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[31]++;



      for (String name : firstArg.getString().split("[.]")) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[31]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[32]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[33]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[138]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(name)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[64]++;
          t.report(callNode, BAD_ARG, name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[139]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[140]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[65]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[141]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((externedNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[66]++;
          countPropertyOccurrence(name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[142]++;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[67]++;}
      }
    }

    /**
     * Increments the occurrence count for a property name.
     *
     * @param name The property name
     */
    private void countPropertyOccurrence(String name) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[143]++;
      Property prop = propertyMap.get(name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[144]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((prop == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[68]++;
        prop = new Property(name);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[145]++;
        propertyMap.put(name, prop);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[146]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[147]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((affinityGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[70]++;
          affinityGraph.createNode(prop);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[148]++;

        } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[71]++;}

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[69]++;}
      prop.numOccurrences++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[149]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[150]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((currentHighAffinityProperties != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[72]++;
        currentHighAffinityProperties.add(prop);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[151]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[73]++;}
    }

    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[152]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((t.getScope().getParent().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[74]++;
        currentHighAffinityProperties = Sets.newHashSet();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[153]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[75]++;}
    }

    @Override
    public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[154]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((affinityGraph == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[76]++;
        return;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[77]++;}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[155]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((t.getScope().getParent().isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[78]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[156]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[34]++;


        for (Property p1 : currentHighAffinityProperties) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[34]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[35]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[36]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[157]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[37]++;


          for (Property p2 : currentHighAffinityProperties) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[37]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[38]--;
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.loops[39]++;
}
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[158]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((p1.oldName.compareTo(p2.oldName) < 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[80]++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[159]++;
              GraphEdge<Property,PropertyAffinity> edge =
                  affinityGraph.getFirstEdge(p1, p2);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[160]++;
int CodeCoverConditionCoverageHelper_C40;
              if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((edge == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[82]++;
                affinityGraph.connect(p1, new PropertyAffinity(1), p2);
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[161]++;

              } else {
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[83]++;
                edge.getValue().increase();
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[162]++;
              }

            } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[81]++;}
          }
        }
        currentHighAffinityProperties = null;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[163]++;

      } else {
  CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.branches[79]++;}
    }
  }

  // -------------------------------------------------------------------------

  /**
   * Encapsulates the information needed for renaming a property.
   */
  private class Property {
    final String oldName;
    String newName;
    int numOccurrences;
    int affinityScore = 0;
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[164]++;
  }

    Property(String name) {
      this.oldName = name;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[165]++;
    }
  }

  private class PropertyAffinity {
    // This will forever be zero if no affinity information was gathered.
    private int affinity = 0;
  {
    CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[166]++;
  }

    private PropertyAffinity(int affinity) {
      this.affinity = affinity;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[167]++;
    }

    private void increase() {
      affinity++;
CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l.statements[168]++;
    }
  }
}

class CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l ());
  }
    public static long[] statements = new long[169];
    public static long[] branches = new long[84];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[41];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RenameProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,2,1,1};
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
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$1wfy4ojobmluvlvv9dkp7w8b14gjvum3l () {
    super("com.google.javascript.jscomp.RenameProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 168; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 83; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 40; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RenameProperties.java");
      for (int i = 1; i <= 168; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 83; i++) {
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
      for (int i = 1; i <= 13; i++) {
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

